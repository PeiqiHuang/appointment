package appointment.board;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import appointment.AppointmentService;
import appointment.time.Time;
import appointment.user.User;
import appointment.user.UserAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BoardAction extends ActionSupport implements UserAware {
	
	public String set() {
		try {
//			System.out.println("set board -> " + getBoard());
//			String path =ServletActionContext.getServletContext().getRealPath("/");
			appointmentService.setBoard(this.getBoard());
		} catch (IOException e) {
			System.out.println("set board go to exception");
			
		}
		return SUCCESS;
	}
	
	public String doctors() {
		try {
			appointmentService.setBoardDoctors(this.getDoctors());
		} catch (IOException e) {
			System.out.println("set board doctors go to exception");
			
		}
		return SUCCESS;
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

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}


	
}
