package appointment.person;


import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import appointment.AppointmentService;
import appointment.time.Time;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PersonDeleteAction extends ActionSupport implements ModelDriven<Person> {
	
	public String execute() {
//		System.out.println("person delete -> " + person);
		appointmentService.deletePerson(person);
		return SUCCESS;
	}
	
	AppointmentService appointmentService;

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
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
	
}
