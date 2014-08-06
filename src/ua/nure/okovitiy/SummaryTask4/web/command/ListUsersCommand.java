package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.okovitiy.SummaryTask4.db.DBManager;
import ua.nure.okovitiy.SummaryTask4.db.bean.UserBean;
import ua.nure.okovitiy.SummaryTask4.web.command.ListUsersCommand;
import ua.nure.okovitiy.SummaryTask4.Path;

/**
 * List users command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class ListUsersCommand extends Command {

	private static final long serialVersionUID = 1094133346815712353L;

	private static final Logger LOG = Logger.getLogger(ListUsersCommand.class);

	/**
	 * Execute the command.
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws IOException, ServletException
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		List<UserBean> userBeans = DBManager.getInstance().getAllUserBeans();
		LOG.trace("Found in DB: userBeans --> " + userBeans);

		Collections.sort(userBeans, compareById);

		session.setAttribute("userBeans", userBeans);
		LOG.trace("Set the request attribute: userBeans --> " + userBeans);

		LOG.debug("Command finished");
		return Path.PAGE_LIST_USERS;
	}

	/**
	 * Serializable comparator used with TreeMap container. When the servlet
	 * container tries to serialize the session it may fail because the session
	 * can contain TreeMap object with not serializable comparator.
	 * 
	 * @author Andrew Okovitiy
	 * 
	 */
	private static class CompareById implements Comparator<UserBean>,
			Serializable {

		private static final long serialVersionUID = -8524896675451071085L;

		public int compare(UserBean bean1, UserBean bean2) {
			if (bean1.getUserId() > bean2.getUserId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/**
	 * @return Comparator of User beans by Id
	 * 
	 */
	private static Comparator<UserBean> compareById = new CompareById();

}
