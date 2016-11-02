package appointment.util;

import java.util.Map;

import appointment.user.User;
import appointment.user.UserAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/*
 * This interceptor provides authentication for the secure actions of the application.
 * It does two things.  First, it checks the session scope map to see if there's user 
 * object present, which inidcates that the current user is already logged in.  If this
 * object is not present, the interceptor alters the workflow of the request by returning 
 * a login control string that causes the request to forward to the login page.
 * 
 * If the user object is present in the session map, then the interceptor injects the user
 * object into the action by calling the setUser method, and then allows the processing of 
 * the request to continue.  
 */

public class AuthenticationInterceptor implements Interceptor {

	public void destroy() {
	}

	public void init() {
	}

	public String intercept( ActionInvocation actionInvocation ) throws Exception {

		/* Get the session map from the invocation context ( the ActionContext actually )
		 * and check for the user object.  Note, we are not going directly to the Servlet
		 * API's session object, even though this is most likely the map being returned 
		 * by this code; we need to keep our Struts 2 components cleanly separated from the 
		 * Servlet API so that our testing can be as simple as faking a map, rather than
		 * faking Servlet API objects.
		 */
		Map session = actionInvocation.getInvocationContext().getSession();
		
		User user = (User) session.get( AppointmentConstant.USER );
		
		/* 
		 * If use doesn't exist return the LOGIN control string.  This will cause the 
		 * execution of this action to stop and the request will return the globally defined
		 * login result.
		 */
		if (user == null) {
		    return Action.LOGIN;
		} 
		
		
		/*
		 * If the user is logged in, get the action, inject the user, then continue the 
		 * execution of this request by invoking the rest of the interceptor stack, and ultimately,
		 * the action. 
		 */
		else {
				
		    Action action = ( Action ) actionInvocation.getAction();
		    
		    if (action instanceof UserAware) {
		        ((UserAware)action).setUser(user);
		    }
		    
		    /*
		     * We just return the control string from the invoke method.  If we wanted, we could hold the string for
		     * a few lines and do some post processing.  When we do return the string, execution climbs back out of the 
		     * recursive hole, through the higher up interceptors, and finally arrives back at the actionInvocation itself,
		     * who then fires the result based upon the result string returned.
		     */
//		    System.out.println("Logged in: interceptor");
		    return actionInvocation.invoke();
		}

	}
	
}
