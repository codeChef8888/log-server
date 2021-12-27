package io.java.Log.Server.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import io.java.Log.Server.Models.UserRegistrationDetails;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserRegistrationDetails, Integer> {

	public UserRegistrationDetails findByUserName(String userName);

	@Query(value = "SELECT * FROM user_details u WHERE EXTRACT(YEAR FROM u.created_date)= :year AND EXTRACT(MONTH FROM u.created_date)= :month", nativeQuery = true)
	public List<UserRegistrationDetails> findAllRegistrationWithGivenDate(@Param("year") int year,
			@Param("month") int month);
	
	@Query(value = "SELECT * FROM user_details u WHERE u.created_date BETWEEN :date1 AND :date2", nativeQuery = true)
	public List<UserRegistrationDetails> findAllBetweenDates(@Param("date1") Date date1, @Param("date2") Date date2);
	
}
