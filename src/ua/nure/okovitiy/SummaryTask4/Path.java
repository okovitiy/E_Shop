package ua.nure.okovitiy.SummaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author Anderw Okovitiy
 * 
 */
public final class Path {
	
	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE_LIST_PRODUCTS = "/WEB-INF/jsp/client/list_products.jsp";
	public static final String PAGE_ADMIN_LIST_PRODUCTS = "/WEB-INF/jsp/admin/products.jsp";
	public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
	public static final String PAGE_LIST_USERS = "/WEB-INF/jsp/admin/users.jsp";
	public static final String PAGE_CART = "cart.jsp";
	public static final String PAGE_PERSONAL_ACCOUNT = "/WEB-INF/jsp/client/personal_account.jsp";
	public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/registration.jsp";
	// commands
	public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
	public static final String COMMAND_LIST_PRODUCTS = "/controller?command=listProducts";
	public static final String COMMAND_PERSONAL_ACCOUNT = "/controller?command=persanalAccount";
	public static final String COMMAND_ADMIN_LIST_PRODUCTS = "/controller?command=viewProducts";
	public static final String COMMAND_LIST_USERS = "/controller?command=listUsers";
	
}