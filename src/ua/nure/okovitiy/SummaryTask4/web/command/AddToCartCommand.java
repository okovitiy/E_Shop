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

/**
 * Add to cart command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class AddToCartCommand extends Command {

	private static final long serialVersionUID = -3379162513526017164L;

	private static final Logger LOG = Logger.getLogger(AddToCartCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Commands starts");

		DBManager manager = DBManager.getInstance();
		boolean flag = false;

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<CartProductBean> list = (List<CartProductBean>) session
				.getAttribute("cartList");

		// get all id
		String[] itemId = request.getParameterValues("itemId");

		// get cart product bean
		if (!(itemId == null)) {
			int[] itemIds = new int[itemId.length];
			for (int i = 0; i < itemIds.length; i++) {
				itemIds[i] = Integer.parseInt(itemId[i]);
			}

			List<CartProductBean> cartProductBeans = manager
					.getCartBeanByProductId(itemIds);

			if (!(list == null)) {
				cartProductBeans.addAll(list);
			}

			cartProductBeans = combineProducts(cartProductBeans);

			for (CartProductBean cpb : cartProductBeans) {
				int ququantity = manager.getQuantityOfProductsByID(cpb.getId());
				if (ququantity < cpb.getQuantity()) {
					flag = true;
					cpb.setQuantity(ququantity);
				}
			}

			LOG.trace("Found in DB: cartList --> " + cartProductBeans);

			session.setAttribute("cartList", cartProductBeans);
			LOG.trace("Set the request attribute: products --> "
					+ cartProductBeans);
		}

		// error handler
		String errorMessage = null;
		String forward = Path.PAGE_ERROR_PAGE;
		if (flag) {
			errorMessage = "Selected item is not available.";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		LOG.debug("Command finished");
		response.sendRedirect(Path.PAGE_CART);
		return null;
	}

	private static List<CartProductBean> combineProducts(
			List<CartProductBean> cpb1) {
		for (int i = 0; i < cpb1.size(); i++) {
			for (int j = i + 1; j < cpb1.size(); j++) {
				if (cpb1.get(i).getId() == cpb1.get(j).getId()) {
					cpb1.get(i).setQuantity(
							cpb1.get(i).getQuantity()
									+ cpb1.get(j).getQuantity());
					cpb1.remove(j);
					j--;
				}
			}
		}
		return cpb1;
	}
}
