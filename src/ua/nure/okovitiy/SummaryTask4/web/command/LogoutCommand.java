package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.okovitiy.SummaryTask4.web.command.LogoutCommand;
import ua.nure.okovitiy.SummaryTask4.Path;

/**
 * Logout command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = -5834484888825715763L;
	
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		LOG.debug("Command finished");
		return Path.PAGE_LOGIN;
	}

}
