package com.bit.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bit.pms.dto.GeneralResult;
import com.bit.pms.enums.ResultStatus;
import com.bit.pms.model.Preset;
import com.bit.pms.repository.PresetRepository;
import com.bit.pms.repository.UserRepository;
import com.bit.pms.service.EmailCheckService;
import com.bit.pms.service.impl.PasswordService;

import sun.misc.BASE64Encoder;

@RestController
@RequestMapping("/api")
public class EmailController {
	@Autowired
	private EmailCheckService emailCheckService;

	@Autowired
	private PresetRepository presetRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserRepository userRepository;

	@Value("${serverHost}")
	private String host;

	@Value("${spring.mail.username}")
	private String from;

	private static final String STATUS_0 = "0";

	@RequestMapping(value = "/email/{email:.+}", method = RequestMethod.GET)
	public ResponseEntity<GeneralResult> isValidEmail(@PathVariable("email") String email) {
		GeneralResult result = new GeneralResult(ResultStatus.FAIL.name());
		if (emailCheckService.isValidEmail(email)) {
			result.setStatus(ResultStatus.SUCCESS.name());
		}
		return new ResponseEntity<GeneralResult>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/email/{email:.+}", method = RequestMethod.POST)
	public void sendEmail(@PathVariable("email") String email) {
		try {
			String flag = PasswordService.getInstance().encrypt(email).replaceAll("=", "-");
			Preset preset = new Preset();
			preset.setEmail(email);
			preset.setFlag(flag);
			preset.setStatus(STATUS_0);

			presetRepository.save(preset);

			String msg = host + "/pwd-reset.html?flag=" + flag;

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setTo(email);
			message.setSubject("password reset - no reply");
			message.setText("Please follow below link to reset your password:\n" + msg);
			javaMailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/email/{pwd}/f/{flag}", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<GeneralResult> resetPwd(@PathVariable("pwd") String pwd, @PathVariable("flag") String flag) {

		GeneralResult result = new GeneralResult(ResultStatus.FAIL.name());

		Preset preset = presetRepository.findByFlagAndStatus(flag, STATUS_0);

		if (preset != null) {
			// update password
			String email = preset.getEmail();
			String pwdBase64 = (new BASE64Encoder()).encode((email + ":" + pwd).getBytes())
					.replaceAll(System.getProperty("line.separator"), "");
			try {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				userRepository.updatePwdByEmail(email, encoder.encode(pwdBase64));
				presetRepository.updateStatusById(preset.getId());
				result.setStatus(ResultStatus.SUCCESS.name());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<GeneralResult>(result, HttpStatus.OK);
	}

}
