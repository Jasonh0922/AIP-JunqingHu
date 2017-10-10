package com.bit.pms.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.pms.dto.DateTime;

@RestController
@RequestMapping("/api")
public class DateTimeController {

	@RequestMapping("/sys/datetime")
	public ResponseEntity<DateTime> getDateTime() {
		DateTime dt = new DateTime();
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dateString = df.format(date);
		dt.setDate(dateString.split(" ")[0]);
		dt.setTime(dateString.split(" ")[1]);
		return new ResponseEntity<DateTime>(dt, HttpStatus.OK);
	}
}
