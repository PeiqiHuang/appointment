package appointment.time;


import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;

import appointment.AppointmentService;
import appointment.user.User;
import appointment.user.UserAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TimeAction extends ActionSupport implements ModelDriven<Time>, UserAware {
	
	public String insert() {
//		System.out.println("--- insert ---");
//		System.out.println("insert = " + time);
		return SUCCESS;
	}
	
	public String submit() {
		appointmentService.submitTime(time);
		return SUCCESS;
	}
	
	public String show() {
		times = appointmentService.showAllTime();
		try {
			board = appointmentService.getBoard();
		} catch (IOException e) {
			System.out.println("获取公告栏失败！");
			e.printStackTrace();
		}
//		System.out.println("show = " + times);
		return SUCCESS;
	}
	
	public String del() {
//		System.out.println("del = " + time);
		appointmentService.deleteTime(time);
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

	private Time time = new Time();

	@Override
	public Time getModel() {
		return time;
	}

	private List<Time> times;
	
	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	@Override
	public void validate() {
		String method = ServletActionContext.getRequest().getRequestURI();
//		System.out.println("validate method = " + method);
		if (method.contains("submit")) {
			if (time.getTime().length() == 0) {
				addFieldError( "time", getText("required") );
			}
			if (time.getDescription().length() == 0) {
				addFieldError( "description", getText("required") );
			}
			if (null == time.getCount() || time.getCount() < 0) {
				addFieldError( "count", getText("count.int.required") );
			}
		}
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
