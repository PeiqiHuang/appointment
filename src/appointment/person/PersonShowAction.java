package appointment.person;


import java.math.BigInteger;
import java.util.List;

import appointment.AppointmentService;
import appointment.user.User;
import appointment.user.UserAware;

import com.opensymphony.xwork2.ActionSupport;

public class PersonShowAction extends ActionSupport implements UserAware {
	
	public String execute() {
		List<Person> list = appointmentService.getTodayPerson();
		// 修改逾期支付的病人的状态
		appointmentService.updateStatus(list);
		setPersons(list);
		// setMonthCount(appointmentService.getMonthCount(date));
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
	private BigInteger monthCount;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public BigInteger getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(BigInteger monthCount) {
		this.monthCount = monthCount;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}


	
}
