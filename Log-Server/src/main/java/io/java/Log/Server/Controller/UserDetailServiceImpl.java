package io.java.Log.Server.Controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.java.Log.Server.Models.UserRegistrationDetails;
import io.java.Log.Server.Repository.UserDetailsRepo;
import io.java.Log.Server.Service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService{

	@Autowired
	UserDetailsRepo userDetailsRepo;
	
	@Override
	public void registerUserDetails(UserRegistrationDetails user) {
		Date date = new Date();
		long time = date.getTime();
		Timestamp tm = new Timestamp(time);
		user.setCreatedDate(tm);
		userDetailsRepo.save(user);
		
	}

	@Override
	public Object getAllRegistrationDetails() {
		List<UserRegistrationDetails> details = new ArrayList<>();
		userDetailsRepo.findAll().forEach(details::add);
		return details;
	}

	@Override
	public UserRegistrationDetails getUserByName(String userName) {
		return userDetailsRepo.findByUserName(userName);
	}

	@Override
	public List<UserRegistrationDetails> getUserRegDetailsByGivenDate(String since) throws ParseException {
		System.out.println("service" + since);
		List<UserRegistrationDetails> users = new ArrayList<>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = format.parse(since);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		System.out.println(year);
		System.out.println(month);
		userDetailsRepo.findAllRegistrationWithGivenDate(year, month + 1).forEach(users::add);

//		for(UserRegistrationDetails u:users) {
//			System.out.println(users);
//		}

		return users;
	}

	@Override
	public List<UserRegistrationDetails> getRegistrationDetailsBetween(String start, String end) throws ParseException {
		System.out.println(start + "yoyoyoyo" + end);
		return userDetailsRepo.findAllBetweenDates(new SimpleDateFormat("yyyy-MM-dd").parse(start),
				new SimpleDateFormat("yyyy-MM-dd").parse(end));
	}

}
