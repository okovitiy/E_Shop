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
import ua.nure.okovitiy.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.okovitiy.SummaryTask4.web.command.ListOrdersCommand;
import ua.nure.okovitiy.SummaryTask4.Path;

/**
 * List orders command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class ListOrdersCommand extends Command {

	private static final long serialVersionUID = -7805212957706778647L;

	private static final Logger LOG = Logger.getLogger(ListOrdersCommand.class);

	/**
	 * Serializable comparator used with TreeMap container. When the servlet
	 * container tries to serialize the session it may fail because the session
	 * can contain TreeMap object with not serializable comparator.
	 * 
	 * @author Andrew Okovitiy
	 * 
	 */
	private static class CompareById implements Comparator<UserOrderBean>,
			Serializable {
		private static final long serialVersionUID = -1573481565177573283L;

		public int compare(UserOrderBean bean1, UserOrderBean bean2) {
			if (bean1.getOrderId() > bean2.getOrderId()) {
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

	/**
	 * Execute the command.
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws IOException, ServletException
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("Commands starts");

		HttpSession session = request.getSession();
		List<UserOrderBean> userOrderBeanList = DBManager.getInstance()
				.getAllUserOrderBeans();
		LOG.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);

		userOrderBeanList = unitePriceById(userOrderBeanList);
		Collections.sort(userOrderBeanList, compareById);

		// put user order beans list to request
		session.setAttribute("userOrderBeanList", userOrderBeanList);
		LOG.trace("Set the request attribute: userOrderBeanList --> "
				+ userOrderBeanList);

		LOG.debug("Commands finished");
		return Path.PAGE_LIST_ORDERS;
	}

	/**
	 * @param list
	 * 
	 * @return List of User Order Beans
	 */
	private static List<UserOrderBean> unitePriceById(List<UserOrderBean> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getOrderId() == list.get(j).getOrderId()) {
					list.get(i).setCartPrice(
							list.get(i).getCartPrice()
									+ list.get(j).getCartPrice());
					list.remove(j);
					j--;
				}
			}
		}
		return list;
	}

}
