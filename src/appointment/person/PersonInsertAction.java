package appointment.person;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import appointment.AppointmentService;
import appointment.time.Time;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PersonInsertAction extends ActionSupport implements ModelDriven<Person> {
	
	public String execute() {
//		setAvailableTime(appointmentService.getAvailableTime());
		try {
			board = appointmentService.getBoard();
			doctors = appointmentService.getBoardDoctors();
//			System.out.println("person insert board ->" + board);
		} catch (IOException e) {
			System.out.println("获取公告栏失败！");
			e.printStackTrace();
		}
		
		// if history view, the add date should not be today
		updatePersonDate(addDate);
		
		return SUCCESS;
	}
	
	public String success() {
		appointmentService.setSuccess(person);
		return SUCCESS;
	}	
	
	private void updatePersonDate(String addDate) {
//		System.out.println("updatePersonDate addDate = " + addDate);
		if (null == addDate) {
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1;
		try {
			d1 = sdf.parse(addDate);
		} catch (Exception e) {
			System.out.println("updatePersonDate failed!");
			return;
		}
		Calendar c1 = Calendar.getInstance();              
	    c1.setTime(d1);                                 
	    int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);     
        Calendar c2 = Calendar.getInstance();    
        c2.setTime(new Date());      
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH)+1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);   
        if(year1 == year2 && month1 == month2 && day1 == day2){
        	return;
        } else {
//        	System.out.println("updatePersonDate set Date = " + year1 + " " + month1 + " " +day1);
        	person.setDate(new Timestamp(d1.getTime()));
        }
	}

	AppointmentService appointmentService;

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	private String board;

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}
	
	private String doctors;

	public String getDoctors() {
		return doctors;
	}

	public void setDoctors(String doctors) {
		this.doctors = doctors;
	}
	private Person person = new Person();

	@Override
	public Person getModel() {
		if (null == person.getTime()) {
			this.person.setTime(new Time());
		} else if (null != person.getTime().getId()) {
			this.person.setTime(
					appointmentService.getTimeById(
							person.getTime().getId()));
		}
		return person;
	}
	
	private String addDate;

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String date) {
		this.addDate = date;
	}
	
//	private List<Time> availableTime;
//
//	public List<Time> getAvailableTime() {
//		return availableTime;
//	}
//
//	public void setAvailableTime(List<Time> availableTime) {
//		this.availableTime = availableTime;
//	}
}
