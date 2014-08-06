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
import ua.nure.okovitiy.SummaryTask4.db.bean.CartProductBean;
import ua.nure.okovitiy.SummaryTask4.db.entity.User;

/**
 * Make order command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class MakeOrderCommand extends Command {

	private static final long serialVersionUID = -2081693120949720265L;

	private static final Logger LOG = Logger.getLogger(MakeOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Commands starts");

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<CartProductBean> cartProductBeans = (List<CartProductBean>) session
				.getAttribute("cartList");
		User user = (User) session.getAttribute("user");
		if (!(cartProductBeans == null || user == null)) {
			DBManager.getInstance().makeOrder(user, cartProductBeans);
		}

		// cleaning the cart list
		cartProductBeans = null;
		session.setAttribute("cartList", cartProductBeans);
		LOG.trace("Set the request attribute(null): products --> "
				+ cartProductBeans);

		LOG.debug("Command finished");
		return Path.COMMAND_PERSONAL_ACCOUNT;
	}

}
