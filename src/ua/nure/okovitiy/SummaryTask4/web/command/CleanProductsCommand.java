package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.okovitiy.SummaryTask4.Path;
import ua.nure.okovitiy.SummaryTask4.db.bean.CartProductBean;

/**
 * Clean products command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class CleanProductsCommand extends Command {

	private static final long serialVersionUID = -4782343404020520255L;

	private static final Logger LOG = Logger
			.getLogger(CleanProductsCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Commands starts");

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<CartProductBean> cartProductBeans = (List<CartProductBean>) session
				.getAttribute("cartList");

		String[] itemId = request.getParameterValues("itemId");

		if (!(itemId == null || cartProductBeans == null)) {
			int[] itemIds = new int[itemId.length];
			for (int i = 0; i < itemIds.length; i++) {
				itemIds[i] = Integer.parseInt(itemId[i]);
			}
			for (int i = 0; i < itemIds.length; i++) {
				for (int j = 0; j < cartProductBeans.size(); j++) {
					if (cartProductBeans.get(j).getId() == itemIds[i]) {
						cartProductBeans.remove(j);
					}
				}
			}
		}

		session.setAttribute("cartList", cartProductBeans);
		LOG.trace("Set the request attribute: products --> " + cartProductBeans);

		LOG.debug("Command finished");
		return Path.PAGE_CART;
	}

}
