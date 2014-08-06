package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.okovitiy.SummaryTask4.Path;
import ua.nure.okovitiy.SummaryTask4.db.DBManager;
import ua.nure.okovitiy.SummaryTask4.db.bean.UserOrderBean;

/**
 * Sort orders command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class SortOrdersCommand extends Command {

	private static final long serialVersionUID = -8813595304520713819L;

	private static final Logger LOG = Logger.getLogger(SortOrdersCommand.class);

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

		LOG.debug("Commands starts");
		HttpSession session = request.getSession();

		List<UserOrderBean> userOrderBeanList = DBManager.getInstance()
				.getAllUserOrderBeans();
		Collections.sort(userOrderBeanList, compareById);
		LOG.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);

		// get user order beans by status
		String status = request.getParameter("status");
		userOrderBeanList = getUserOrderBeansByStatus(userOrderBeanList, status);

		// put user order beans list to request
		session.setAttribute("userOrderBeanList", userOrderBeanList);
		LOG.trace("Set the request attribute: userOrderBeanList --> "
				+ userOrderBeanList);

		LOG.debug("Commands finished");
		return Path.PAGE_LIST_ORDERS;
	}

	/**
	 * @param userOrderBeanList
	 * @param status
	 * 
	 * @return List of User Order Beans
	 */
	private static List<UserOrderBean> getUserOrderBeansByStatus(
			List<UserOrderBean> userOrderBeanList, String status) {
		if (status.equals("all")) {
			return userOrderBeanList;
		} else {
			List<UserOrderBean> result = new ArrayList<UserOrderBean>();
			for (UserOrderBean userOrderBean : userOrderBeanList) {
				if (status.equals(userOrderBean.getStatusName())) {
					result.add(userOrderBean);
				}
			}
			return result;
		}
	}

	/**
	 * Comparator of User order beans by Id
	 * 
	 */
	private static class CompareById implements Comparator<UserOrderBean>,
			Serializable {

		private static final long serialVersionUID = 6697075244415900824L;

		public int compare(UserOrderBean uob1, UserOrderBean uob2) {
			if (uob1.getOrderId() > uob2.getOrderId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/**
	 * @return Comparator of User order beans by Id
	 * 
	 */
	private static Comparator<UserOrderBean> compareById = new CompareById();

}
