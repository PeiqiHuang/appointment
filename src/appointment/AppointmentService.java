package appointment;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import appointment.person.Person;
import appointment.time.Time;
import appointment.user.User;

public interface AppointmentService {
	
	void submitTime(Time time);
	
	Time getTimeById(Integer id);
	
	void deleteTime(Time time);
	
	List<Time> showAllTime();
	
	List<Time> getAvailableTime(String id, String addDate);
	
	List<Time> getAvailableTime();
	
	void savePerson(Person person);
	
	void deletePerson(Person person);
	
	boolean isInsertValid(Person person);
	
	Map<Boolean, String> getGenders();
	
	List<Person> getTodayPerson();

	User authenticateUser(String username, String password);

	Long leftSecond(String phone);

	void updateStatus(List<Person> persons);

	void setBoard(String board) throws IOException;
	
	void setBoardDoctors(String doctors) throws IOException;
	
	String getBoard() throws IOException;

	Person getTodayPerson(String phone);

	List<Person> getPersonsByDate(String date);

	BigInteger getMonthCount(String date);

	void setSuccess(Person person);

	String getBoardDoctors() throws IOException;

}
