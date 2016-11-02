package appointment.person;


import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import appointment.AppointmentService;
import appointment.time.Time;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PersonSubmitAction extends ActionSupport implements ModelDriven<Person> {
	
	public String execute() {
		System.out.println("submit -> person -> " + person);
		appointmentService.savePerson(person);
		if (null != person.getId())
			return "main";
		else
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
	
	@Override
	public void validate() {
//		System.out.println("person submit validate person = " + person);
		if (!appointmentService.isInsertValid(person)) {
//			System.out.println("validate isInsertValid false");
			addFieldError( "time.id", getText("person.insert.invalid") );
		}
		if (person.getName().length() == 0) {
//			System.out.println("validate getName false");
			addFieldError( "name", getText("required") );
		}
		if (null == person.getGender()) {
//			System.out.println("validate getGender false");
			addFieldError( "gender", getText("required") );
		}
		if (person.getAge().length() == 0) {
//			System.out.println("validate getAge false");
			addFieldError( "age", getText("required") );
		}
		if (null == person.getPhone()) {
//			System.out.println("validate getPhone false");
			addFieldError( "phone", getText("required") );
		}
		try {
			Long.parseLong(person.getPhone());
		} catch (NumberFormatException e) {
			addFieldError( "phone", "必须为数字" );
		}
	}
	
	
	
}
