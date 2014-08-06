package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.okovitiy.SummaryTask4.web.command.LoginCommand;
import ua.nure.okovitiy.SummaryTask4.Path;
import ua.nure.okovitiy.SummaryTask4.db.Role;
import ua.nure.okovitiy.SummaryTask4.db.entity.User;
import ua.nure.okovitiy.SummaryTask4.db.DBManager;

/**
 * Login command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = 8109754716328896450L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from the request
		DBManager manager = DBManager.getInstance();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: loging --> " + login);

		String password = request.getParameter("password");

		// error handler
		String errorMessage = null;
		String forward = Path.PAGE_ERROR_PAGE;

		if (login == null || password == null || login.isEmpty()
				|| password.isEmpty()) {
			errorMessage = "Login/password cannot be empty";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		User user = manager.getUserByLogin(login);
		LOG.trace("Found in DB: user --> " + user);

		if (user == null || !password.equals(user.getPassword())) {
			errorMessage = "Cannot find user with such login/password";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		} else {
			Role userRole = Role.getRole(user);
			if (userRole == Role.BLOCKED_CLIENT) {
				errorMessage = "Your account has been blocked.";
				request.setAttribute("errorMessage", errorMessage);
				LOG.error("errorMessage --> " + errorMessage);
				return forward;
			}
			LOG.trace("userRole --> " + userRole);

			if (userRole == Role.ADMIN) {
				forward = Path.COMMAND_LIST_ORDERS;
			}

			if (userRole == Role.CLIENT) {
				forward = Path.COMMAND_LIST_PRODUCTS;
			}

			session.setAttribute("user", user);
			LOG.trace("Set the session attribute: user --> " + user);

			session.setAttribute("userRole", userRole);
			LOG.trace("Set the session attribute: userRole --> " + userRole);

			LOG.info("User " + user + " logged as "
					+ userRole.toString().toLowerCase());
		}

		LOG.debug("Command finished");
		return forward;
	}

}
