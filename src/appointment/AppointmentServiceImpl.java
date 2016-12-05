package appointment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import appointment.person.Person;
import appointment.person.PersonDAO;
import appointment.time.Time;
import appointment.time.TimeDAO;
import appointment.user.User;
import appointment.util.AppointmentConstant;

public class AppointmentServiceImpl implements AppointmentService {
	
	public static final Integer WAIT_TIME = 60*30;
	
	private TimeDAO timeDAO;

	private PersonDAO personDAO;
	
	public TimeDAO getTimeDAO() {
		return timeDAO;
	}

	public void setTimeDAO(TimeDAO timeDAO) {
		this.timeDAO = timeDAO;
	}

	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public void submitTime(Time time) {
		timeDAO.save(time);
	}

	@Override
	public void deleteTime(Time time) {
		timeDAO.delete(time);
		
	}

	@Override
	public List<Time> showAllTime() {
		return timeDAO.findAll();
	}

	@Override
	public List<Time> getAvailableTime(String id) {
//		System.out.println("availableTime -> id -> " + id);
		List<Person> persons = personDAO.findToday();
		List<Time> times = timeDAO.findAll();
		List<Time> availableTime = timeDAO.findAll();
		/*
		 * if id is not null, it means is a update process
		 * there is no need to filter
		 */
		if ("" == id || null == id) {
			for (Time time : times) {
				int current = time.getCount();
				if (current <= 0) {
					availableTime.remove(time);
					continue;
				}
				for (Person person : persons) {
					if (time.equals(person.getTime()) && person.getPaid() >= 0) {
						if (--current <= 0) {
							availableTime.remove(time);
							break;
						}
					}
				}
			}
		}
		
//		System.out.println("availableTime -> " + availableTime);
		return availableTime;
	}
	
	/**
	 * only time window is not full
	 * and person is unique today
	 * return true
	 */
	@Override
	public boolean isInsertValid(Person person) {
		/* update process */
		if (null != person.getId()) {
			return true;
		}
		List<Time> availableList = getAvailableTime("");
		for (Time time : availableList) {
			if (time.equals(person.getTime())) {
				List<Person> todayPersonList = personDAO.findToday();
				if (todayPersonList.isEmpty()) {
					return true;
				}
				for (Person p : todayPersonList) {
					if (person.getName().equals(p.getName())
							&& person.getGender().equals(p.getGender())
							&& person.getAge().equals(p.getAge())
							&& person.getPhone().equals(p.getPhone())) {
						return false;
					}
				}
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Map<Boolean, String> getGenders() {
		Map<Boolean, String> map = new HashMap<Boolean, String>();
		map.put(false, "女");
		map.put(true, "男");
		return map;
	}

	@Override
	public Time getTimeById(Integer id) {
		return timeDAO.findById(id);
	}

	@Override
	public void savePerson(Person person) {
		personDAO.save(person);
	}

	@Override
	public List<Person> getTodayPerson() {
		return personDAO.findToday();
	}

	@Override
	public void deletePerson(Person person) {
		personDAO.delete(person);
	}

	@Override
	public User authenticateUser(String username, String password) {
		if (username.equals(AppointmentConstant.USERNAME)
				&& password.equals(AppointmentConstant.PASSWORD)) {
			return new User(username, password);
		}
		return null;
	}

	@Override
	public Long leftSecond(String phone) {
		List<Person> list = personDAO.findToday();
//		System.out.println("leftSecond -> list -> " + list);
		Person person = null;
//		System.out.println("leftSecond -> phone -> " + phone);
		for (Person p : list) {
//			System.out.println("leftSecond -> p.getPhone() -> " + p.getPhone());
			if (p.getPhone().equals(phone)) {
//				System.out.println("find a person!");
				person = p;
			}
		}
//		System.out.println("leftSecond -> person -> " + person);
		if (null == person)
			return -1L;
		else if (person.getPaid() > 0) {
			return -2L;
		} else if  (person.getPaid() < 0) {
			return -3L;
		}
		Date ts = person.getDate();
		Date now = new Date();
//		System.out.println("leftSecond -> ts -> " + ts.getTime());
		Long leftSecond = now.getTime() - ts.getTime();
//		System.out.println("leftSecond -> leftSecond -> " + leftSecond);
		leftSecond /= 1000;
		leftSecond = WAIT_TIME - leftSecond;
		return leftSecond;
	}

	@Override
	public void updateStatus(List<Person> persons) {
//		System.out.println("updateStatus -> " + persons);
		for (Person person : persons) {
			Long leftSecond = leftSecond(person.getPhone());
			if (leftSecond < -2) {
				person.setPaid(-1);
//				System.out.println("this one need to set paid -1 -> " + person);
				savePerson(person);
			}
		}
	}
	
	private String getRootPath() throws IOException {
		String path = ServletActionContext.getServletContext().getRealPath("/");
		File file = new File(path + "assets");
		if (!file.exists()) {
			file.mkdir();
		}
		return file.getAbsolutePath();
	}
	
	private String getBoardPath() throws IOException {
		return getRootPath()  +File.separator + AppointmentConstant.FILE_BOARD_NAME;
	}

	@Override
	public void setBoard(String board) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(getBoardPath()));
//		System.out.println("setBoard -> " + getBoardPath());
		pw.println(board);
		pw.close();
	}

	@Override
	public String getBoard() throws IOException {
		String filePath = getBoardPath();
		if (!new File(filePath).exists()) {
			return "";
		}
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = null;
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while((line = br.readLine()) != null) {
			if (i++ > 0) sb.append("\n");
			sb.append(line);
		}
		return sb.toString();
	}

	@Override
	public Person getTodayPerson(String phone) {
		List<Person> list = personDAO.findToday();
		for (Person person : list) {
			if (person.getPhone().equals(phone)) {
				return person;
			}
		}
		return null;
	}

	@Override
	public List<Person> getPersonsByDate(String date) {
		return personDAO.getPersonsByDate(date);
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		 
		AppointmentService appointmentService = (AppointmentService) context.getBean("appointmentService");
		
		System.out.println(appointmentService.getAvailableTime(""));
	}

	@Override
	public BigInteger getMonthCount(String date) {
		return personDAO.getMonthCount(date);
	}

}
