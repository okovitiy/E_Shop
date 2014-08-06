package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.nure.okovitiy.SummaryTask4.Path;
import ua.nure.okovitiy.SummaryTask4.db.DBManager;
import ua.nure.okovitiy.SummaryTask4.db.entity.User;

/**
 * Update settings command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger
			.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Command starts");

		// UPDATE USER

		User user = (User) request.getSession().getAttribute("user");
		boolean updateUser = false;

		// update name
		String name = request.getParameter("name");
		if (name != null && !name.isEmpty()) {
			user.setName(name);
			LOG.trace("User first name was set: name --> " + name);
			updateUser = true;
		}

		String localeToSet = request.getParameter("localeToSet");
		if (localeToSet != null && !localeToSet.isEmpty()) {
			HttpSession session = request.getSession();
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale",
					localeToSet);
			session.setAttribute("defaultLocale", localeToSet);
			LOG.trace("User locale was set: localeToSet --> " + localeToSet);
			if (name != null && !name.isEmpty()) {
				user.setLocaleName(localeToSet);
				updateUser = true;
			}
		}

		if (updateUser) {
			DBManager.getInstance().updateUser(user);
		}

		LOG.debug("Command finished");
		return Path.PAGE_SETTINGS;
	}

}