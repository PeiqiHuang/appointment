package appointment.person;


import appointment.AppointmentService;

import com.opensymphony.xwork2.ActionSupport;

public class PersonCopyAction extends ActionSupport {
	
	public String execute() {
//		System.out.println("person COPY -> " + id);
//		System.out.println("person COPY -> " + date);
		appointmentService.copyPerson(id, date);
		return SUCCESS;
	}
	
	AppointmentService appointmentService;

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	private Integer id;
	private String date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
