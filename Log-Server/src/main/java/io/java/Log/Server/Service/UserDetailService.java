package io.java.Log.Server.Service;


import java.text.ParseException;

import java.util.List;



import io.java.Log.Server.Models.UserRegistrationDetails;


public interface UserDetailService {

	public void registerUserDetails(UserRegistrationDetails user);

	public Object getAllRegistrationDetails();

	public UserRegistrationDetails getUserByName(String userName);

	public List<UserRegistrationDetails> getUserRegDetailsByGivenDate(String since) throws ParseException;

	public List<UserRegistrationDetails> getRegistrationDetailsBetween(String start, String end) throws ParseException;

}
