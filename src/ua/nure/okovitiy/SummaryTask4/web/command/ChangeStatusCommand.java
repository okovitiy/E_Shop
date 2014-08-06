package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;
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
 * Change status command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class ChangeStatusCommand extends Command {

	private static final long serialVersionUID = 1319342976240501165L;

	private static final Logger LOG = Logger
			.getLogger(ChangeStatusCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();
		String[] statuses = request.getParameterValues("changeStatus");

		@SuppressWarnings("unchecked")
		List<UserOrderBean> list = (List<UserOrderBean>) session
				.getAttribute("userOrderBeanList");

		list = changeStatuses(list, statuses);

		session.setAttribute("userOrderBeanList", list);

		LOG.debug("Command finished");
		return Path.COMMAND_LIST_ORDERS;
	}

	private static List<UserOrderBean> changeStatuses(List<UserOrderBean> list,
			String[] statuses) {
		for (int i = 0; (i < list.size() && i < statuses.length); i++) {
			if (!list.get(i).getStatusName().equals(statuses[i])) {
				list.get(i).setStatusName(statuses[i]);
				DBManager.getInstance().changeOrderStatus(
						list.get(i).getStatusName(), list.get(i).getOrderId());
			}
		}
		return list;
	}
}
