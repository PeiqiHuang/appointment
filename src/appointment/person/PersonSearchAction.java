package appointment.person;


import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import appointment.AppointmentService;
import appointment.time.Time;
import appointment.user.User;
import appointment.user.UserAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PersonSearchAction extends ActionSupport {
	
	public String execute() {
//		System.out.println("search -> " + getPhone());
		setLeftSecond(appointmentService.leftSecond(getPhone()));
		if (leftSecond == -2L) {
			setPerson(appointmentService.getTodayPerson(getPhone()));
		}
		return SUCCESS;
	}
	
	private String phone;
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String p) {
		phone = p;
	}
	
	private Person person;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	private Long leftSecond;

	public Long getLeftSecond() {
		return leftSecond;
	}

	public void setLeftSecond(Long leftSecond) {
		this.leftSecond = leftSecond;
	}

	AppointmentService appointmentService;

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	
}
