package appointment.user;


import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import appointment.AppointmentService;
import appointment.util.AppointmentConstant;

import com.opensymphony.xwork2.ActionSupport;

/*
 * The Login action takes a username and password from the request
 * and authenticates those tokens agains the portfolioService objects
 * authentication methods.  If the user is valid, the user object
 * is stored in the session map. 
 * 
 * To receive the session map, this action implements the sessionAware
 * interface.  This decouples the action from the servlet API.  Of course,
 * the action could use the ActionContext to get this map, or even the 
 * underlying Servlet Session object, but these would be needless bindings 
 * to the Servlet API, certainly complicating testing. 
 */

public class Logout extends ActionSupport implements SessionAware {
	

	public String execute(){
		User user = (User) session.get(AppointmentConstant.USER);
		if (user != null)
			session.remove(AppointmentConstant.USER);
		
		return SUCCESS;
	}
	
	
	/* JavaBeans Properties to Receive Request Parameters */
	
	private String username;
	private String password;
	private Map session;


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public void setSession(Map session) {
		this.session = session;
		
	}
	
	

}
