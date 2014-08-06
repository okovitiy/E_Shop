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
import ua.nure.okovitiy.SummaryTask4.db.Role;
import ua.nure.okovitiy.SummaryTask4.db.bean.ProductBean;
import ua.nure.okovitiy.SummaryTask4.db.entity.User;

/**
 * Sort products command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class SortProductsCommand extends Command {

	private static final long serialVersionUID = -4088773403446786429L;

	private static final Logger LOG = Logger
			.getLogger(ListProductsCommand.class);

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

		String errorMessage = null;
		String forward = Path.PAGE_ERROR_PAGE;

		// get products list
		List<ProductBean> productBeans = DBManager.getInstance()
				.getAllProductBeans();
		LOG.trace("Found in DB: productsList --> " + productBeans);

		// get products by price
		String minPrice = request.getParameter("minPrice");

		final int length = 9;
		if (minPrice.length() > length) {
			minPrice = minPrice.substring(0, length);
		}
		String maxPrice = request.getParameter("maxPrice");
		if (maxPrice.length() > length) {
			maxPrice = maxPrice.substring(0, length);
		}

		if (!minPrice.isEmpty() || !maxPrice.isEmpty()) {
			try {
				int min = Integer.MIN_VALUE;
				int max = Integer.MAX_VALUE;
				if (!minPrice.isEmpty()) {
					min = Integer.parseInt(minPrice);
				}
				if (!maxPrice.isEmpty()) {
					max = Integer.parseInt(maxPrice);
				}
				productBeans = getProductsByPrice(productBeans, min, max);
			} catch (NumberFormatException e) {
				errorMessage = "Price must be entered in numeric format.";
				request.setAttribute("errorMessage", errorMessage);
				LOG.error("errorMessage --> " + errorMessage);
				return forward;
			}
		}

		// get products by color
		String color = request.getParameter("color");
		productBeans = getProductsByColor(productBeans, color);

		// get products by category
		String category = request.getParameter("category");
		productBeans = getProductsByCategory(productBeans, category);

		// sort
		String sort = request.getParameter("sort");
		LOG.trace("Sorting by --> " + sort);
		switch (sort) {
		case "byNameAz":
			Collections.sort(productBeans, compareByNameAz);
			break;
		case "byNameZa":
			Collections.sort(productBeans, compareByNameZa);
			break;
		case "byPriceUp":
			Collections.sort(productBeans, compareByPriceUp);
			break;
		case "byPriceDown":
			Collections.sort(productBeans, compareByPriceDown);
			break;
		case "byNovelty":
			Collections.sort(productBeans, compareByNovelty);
			break;
		default:
			Collections.sort(productBeans, compareById);
			break;
		}

		request.setAttribute("productBeans", productBeans);
		LOG.trace("Set the request attribute: products --> " + productBeans);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		LOG.debug("Command finished");

		if (!(user == null)) {
			Role userRole = Role.getRole(user);
			if (userRole == Role.ADMIN) {
				return Path.PAGE_ADMIN_LIST_PRODUCTS;
			}
		}
		return Path.PAGE_LIST_PRODUCTS;

	}

	/**
	 * @param productBeans
	 * @param minPrice
	 * @param maxPrice
	 * 
	 * @return List of Product Beans
	 */
	private static List<ProductBean> getProductsByPrice(
			List<ProductBean> productBeans, int minPrice, int maxPrice) {
		List<ProductBean> result = new ArrayList<ProductBean>();
		for (ProductBean product : productBeans) {
			if (product.getPrice() > minPrice && product.getPrice() < maxPrice) {
				result.add(product);
			}
		}
		return result;
	}

	/**
	 * @param productBeans
	 * @param color
	 * 
	 * @return List of Product Beans
	 */
	private static List<ProductBean> getProductsByColor(
			List<ProductBean> productBeans, String color) {
		if (color.equals("all")) {
			return productBeans;
		} else {
			List<ProductBean> result = new ArrayList<ProductBean>();
			for (ProductBean productBean : productBeans) {
				if (color.equals(productBean.getColor())) {
					result.add(productBean);
				}
			}
			return result;
		}

	}

	/**
	 * @param productBeans
	 * @param category
	 * 
	 * @return List of Product Beans
	 */
	private static List<ProductBean> getProductsByCategory(
			List<ProductBean> productBeans, String category) {
		if (category.equals("all")) {
			return productBeans;
		} else {
			List<ProductBean> result = new ArrayList<ProductBean>();
			for (ProductBean productBean : productBeans) {
				if (category.equals(productBean.getCategory())) {
					result.add(productBean);
				}
			}
			return result;
		}
	}

	/**
	 * Comparator of Product bean by Id
	 * 
	 */
	private static class CompareById implements Comparator<ProductBean>,
			Serializable {
		private static final long serialVersionUID = -1573481565177573283L;

		public int compare(ProductBean pr1, ProductBean pr2) {
			if (pr1.getId() > pr2.getId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/**
	 * Comparator of Product bean by name a-z
	 * 
	 */
	private static class CompareByNameAz implements Comparator<ProductBean>,
			Serializable {

		private static final long serialVersionUID = 2364845283711592043L;

		public int compare(ProductBean pr1, ProductBean pr2) {
			return pr1.getName().compareTo(pr2.getName());
		}
	}

	/**
	 * Comparator of Product bean by name z-a
	 * 
	 */
	private static class CompareByNameZa implements Comparator<ProductBean>,
			Serializable {

		private static final long serialVersionUID = -5450391113377940512L;

		public int compare(ProductBean pr1, ProductBean pr2) {
			return pr2.getName().compareTo(pr1.getName());
		}
	}

	/**
	 * Comparator of Product bean by price up
	 * 
	 */
	private static class CompareByPriceUp implements Comparator<ProductBean>,
			Serializable {

		private static final long serialVersionUID = -6628453344557018949L;

		public int compare(ProductBean pr1, ProductBean pr2) {
			if (pr1.getPrice() > pr2.getPrice()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/**
	 * Comparator of Product bean by price down
	 * 
	 */
	private static class CompareByPriceDown implements Comparator<ProductBean>,
			Serializable {

		private static final long serialVersionUID = -8458727732182910735L;

		public int compare(ProductBean pr1, ProductBean pr2) {
			if (pr2.getPrice() > pr1.getPrice()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/**
	 * Comparator of Product bean by novelty
	 * 
	 */
	private static class CompareByNovelty implements Comparator<ProductBean>,
			Serializable {

		private static final long serialVersionUID = 4251289664671792396L;

		public int compare(ProductBean pr1, ProductBean pr2) {
			return pr1.getNovelty().compareTo(pr2.getNovelty());
		}
	}

	/**
	 * @return Comparator of Product bean by Id
	 * 
	 */
	private static Comparator<ProductBean> compareById = new CompareById();
	
	/**
	 * @return Comparator of Product bean by name a-z
	 * 
	 */
	private static Comparator<ProductBean> compareByNameAz = new CompareByNameAz();
	
	/**
	 * @return Comparator of Product bean by name z-a
	 * 
	 */
	private static Comparator<ProductBean> compareByNameZa = new CompareByNameZa();
	
	/**
	 * @return Comparator of Product bean by price up
	 * 
	 */
	private static Comparator<ProductBean> compareByPriceUp = new CompareByPriceUp();
	
	/**
	 * @return Comparator of Product bean by price down
	 * 
	 */
	private static Comparator<ProductBean> compareByPriceDown = new CompareByPriceDown();
	
	/**
	 * @return Comparator of Product bean by novelty
	 * 
	 */
	private static Comparator<ProductBean> compareByNovelty = new CompareByNovelty();
}
