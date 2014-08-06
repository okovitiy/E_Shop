package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.nure.okovitiy.SummaryTask4.Path;
import ua.nure.okovitiy.SummaryTask4.db.DBManager;

/**
 * Add a new product command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class AddProductCommand extends Command {

	private static final long serialVersionUID = -1670392730590214605L;

	private static final Logger LOG = Logger.getLogger(AddProductCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Command starts");

		DBManager manager = DBManager.getInstance();

		String idStr = request.getParameter("id");
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		Date date = new java.sql.Date(System.currentTimeMillis());

		// error handler
		String errorMessage = null;
		String forward = Path.PAGE_ERROR_PAGE;

		if (category == null || name == null || priceStr == null
				|| color == null || size == null || category.isEmpty()
				|| name.isEmpty() || priceStr.isEmpty() || color.isEmpty()
				|| size.isEmpty()) {
			errorMessage = "All fields must be completed.";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		final int lengthStr = 30, lengthInt = 9;
		if (name.length() > lengthStr || priceStr.length() > lengthInt
				|| size.length() > lengthStr) {
			errorMessage = "Fields must contain a maximum of 30 characters. Price and id - 9 characters";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		try {
			int price = Integer.parseInt(priceStr);
			if (idStr == null || idStr.isEmpty()) {
				manager.addProduct(category, name, price, color, size, date);
			} else {
				if (idStr.length() < lengthInt) {
					int id = Integer.parseInt(idStr);
					if (manager.hasProductInDB(id)) {
						manager.updateProduct(id, category, name, price, color,
								size, date);
					} else {
						errorMessage = "A product with this id doesn't exists";
						request.setAttribute("errorMessage", errorMessage);
						LOG.error("errorMessage --> " + errorMessage);
						return forward;
					}
				} else {
					errorMessage = "Fields id must contain a maximum 9 characters";
					request.setAttribute("errorMessage", errorMessage);
					LOG.error("errorMessage --> " + errorMessage);
					return forward;
				}

			}

		} catch (NumberFormatException e) {
			errorMessage = "Price must be entered in numeric format.";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}

		LOG.debug("Command finished");
		response.sendRedirect("controller?command=viewProducts");
		return null;
	}

}