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

public class PersonShowAction extends ActionSupport implements UserAware {
	
	public String execute() {
		List<Person> list = appointmentService.getTodayPerson();
		// 修改逾期支付的病人的状态
		appointmentService.updateStatus(list);
		setPersons(list);
		return SUCCESS;
	}
	
	AppointmentService appointmentService;

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	
	private List<Person> persons;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}


	
}
