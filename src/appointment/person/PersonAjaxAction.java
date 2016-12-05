package appointment.person;


import java.math.BigInteger;
import java.util.List;

import appointment.AppointmentService;
import appointment.time.Time;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class PersonAjaxAction extends ActionSupport {
	
	public String getPersonsByDate() {
//		System.out.println("getPersonsByDate date -> " + date);
		List<Person> persons = appointmentService.getPersonsByDate(date);
//		System.out.println("getPersonsByDate persons -> " + persons);
		Gson gson = new GsonBuilder().setDateFormat("HH:mm").create();
		result = gson.toJson(persons);
//		System.out.println("getPersonsByDate persons = " + result);
		return SUCCESS;
	}
	
	public String getMonthCount() {
		result = appointmentService.getMonthCount(date).toString();
		return SUCCESS;
	}
	
	AppointmentService appointmentService;

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	private String result;
	
	public String getResult() {
		return result;
	}

//	public void setResult(String result) {
//		this.result = result;
//	}
//
//	private List<Person> persons;
//
//	public List<Person> getPersons() {
//		return persons;
//	}
//
//	public void setPersons(List<Person> persons) {
//		this.persons = persons;
//	}
//	
//	private Person person;
//	
//	public Person getPerson() {
//		return person;
//	}
//
//	public void setPerson(Person person) {
//		this.person = person;
//	}
//	
//	private List times;
//
//	public List getTimes() {
//		return times;
//	}
//
//	public void setTimes(List times) {
//		this.times = times;
//	}

	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
