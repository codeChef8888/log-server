package io.java.Log.Server.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.java.Log.Server.Models.UserRegistrationDetails;
import io.java.Log.Server.Service.UserDetailService;

@RestController
@RequestMapping("/users")
public class UserRegistrationController {
	@Autowired
	UserDetailService userDetailService;

	@PostMapping("/register")
	public void UserRegistrationLog(@RequestBody UserRegistrationDetails user) {

		userDetailService.registerUserDetails(user);
	}

	@GetMapping("/list")
	public ResponseEntity<Object> ViewRegisterUserLogList() {
		return new ResponseEntity<Object>(userDetailService.getAllRegistrationDetails(), HttpStatus.OK);

	}

	@GetMapping("/list/{userName}")
	public UserRegistrationDetails getUserByUserName(@PathVariable String userName) {
		return userDetailService.getUserByName(userName);
	}

	@GetMapping("/list/search/byDate/{atDate}")
	public List<UserRegistrationDetails> getRegistrationDetailsAtDate(
			@PathVariable("atDate") @DateTimeFormat(pattern = "yyyy-MM") Date since) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String date = dateFormat.format(since);
		System.out.println(date);
		return userDetailService.getUserRegDetailsByGivenDate(date);
	}
	
	@GetMapping("/list/search/betweenDates/{startDate}/{endDate}")
	public List<UserRegistrationDetails> getRegistrationDetailsFrom(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = dateFormat.format(startDate);
		String date2 = dateFormat.format(endDate);

		System.out.println(date1 + "  and  " + date2);
		return userDetailService.getRegistrationDetailsBetween(date1, date2);
	}

}
