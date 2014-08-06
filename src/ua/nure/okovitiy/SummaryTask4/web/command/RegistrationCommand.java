package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.nure.okovitiy.SummaryTask4.Path;
import ua.nure.okovitiy.SummaryTask4.db.DBManager;

/**
 * Registration command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class RegistrationCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger
			.getLogger(RegistrationCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Command starts.");

		DBManager manager = DBManager.getInstance();
		String name = request.getParameter("name");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		String email = request.getParameter("email");

		// error handler
		String errorMessage = null;
		String forward = Path.PAGE_ERROR_PAGE;

		if (name == null || login == null || password == null
				|| repeatPassword == null || email == null || name.isEmpty()
				|| login.isEmpty() || password.isEmpty()
				|| repeatPassword.isEmpty() || email.isEmpty()) {
			errorMessage = "All fields must be completed.";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		final int length = 20;
		if (name.length() > length || login.length() > length || password.length() > length
				|| email.length() > length) {
			errorMessage = "Fields must contain a maximum of 20 characters.";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		if (password.equals(repeatPassword)) {
			if (manager.hasLoginInDB(login)) {
				errorMessage = "A user with this login already exists. Please re-enter.";
				request.setAttribute("errorMessage", errorMessage);
				LOG.error("errorMessage --> " + errorMessage);
				return forward;
			} else {
				manager.createUser(name, login, password, email);
			}
		} else {
			errorMessage = "Passwords do not match. Please re-enter.";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		LOG.debug("Command finished");
		return Path.PAGE_LOGIN;
	}

}