package com.bit.pms.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.bit.pms.dto.BitUser;
import com.bit.pms.model.User;

public class BitUserFactory {
	private BitUserFactory() {
	}

	public static BitUser create(User user) {
		return new BitUser(user.getId().toString(), user.getFirstName(), user.getLastName(), user.getPwd(),
				user.getEmail(), user.getMobile(), toGrantedAuthorities(user.getRoles()));
	}

	private static Collection<? extends GrantedAuthority> toGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		if (roles != null && roles.size() > 0) {
			for (String role : roles) {
				SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
				grantedAuthorities.add(simpleGrantedAuthority);
			}
		}
		return grantedAuthorities;
	}
}
