package ua.nure.okovitiy.SummaryTask4.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

import ua.nure.okovitiy.SummaryTask4.db.bean.CartProductBean;
import ua.nure.okovitiy.SummaryTask4.db.bean.OrderBean;
import ua.nure.okovitiy.SummaryTask4.db.bean.ProductBean;
import ua.nure.okovitiy.SummaryTask4.db.bean.UserBean;
import ua.nure.okovitiy.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.okovitiy.SummaryTask4.db.entity.Product;
import ua.nure.okovitiy.SummaryTask4.db.entity.User;

/**
 * DB manager. Works with Apache Derby DB. Only the required DAO methods are
 * defined!
 * 
 * @author Andrew Okovitiy
 * 
 */
public final class DBManager {

	private static final Logger LOG = Logger.getLogger(DBManager.class);

	private static DBManager instance;

	private DBManager() {

	}

	/**
	 * 
	 * @return object of DBManager
	 */
	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	/**
	 * Returns a DB connection from the Pool Connections. Before using this
	 * method you must configure the Date Source and the Connections Pool in
	 * your WEB_APP_ROOT/META-INF/context.xml file.
	 * 
	 * @return A DB connection.
	 */
	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");

			// ST4DB - the name of data source
			DataSource ds = (DataSource) envContext.lookup("jdbc/SMT4DB");
			con = ds.getConnection();
		} catch (NamingException ex) {
			LOG.error("Cannot obtain a connection from the pool", ex);
		}
		return con;
	}

	/**
	 * Returns all products.
	 * 
	 * @return List of product entities.
	 */
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM products");
			while (rs.next()) {
				products.add(extractProduct(rs));
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain menu items", ex);
		} finally {
			close(rs);
			close(stmt);
			commitAndClose(con);
		}
		return products;
	}

	/**
	 * Returns all product beans.
	 * 
	 * @return List of product beans.
	 */
	public List<ProductBean> getAllProductBeans() {
		List<ProductBean> productBeans = new ArrayList<ProductBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt
					.executeQuery("SELECT p.id, c.name, p.name, p.price, p.color, p.size, p.novelty, p.quantity "
							+ "FROM products p, categories c "
							+ "WHERE p.category_id=c.id");
			while (rs.next()) {
				productBeans.add(extractProductBean(rs));
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain product beans", ex);
		} finally {
			close(rs);
			close(stmt);
			commitAndClose(con);
		}
		return productBeans;
	}

	/**
	 * Returns all user order beans.
	 * 
	 * @return List of user order beans.
	 */
	public List<UserOrderBean> getAllUserOrderBeans() {
		List<UserOrderBean> productBeans = new ArrayList<UserOrderBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt
					.executeQuery("SELECT o.id, u.login, u.name, c.price, s.name "
							+ "FROM orders o, users u, statuses s, carts c, products p "
							+ "WHERE o.status_id=s.id and o.user_id=u.id and c.order_id=o.id and p.id=c.product_id");
			while (rs.next()) {
				productBeans.add(extractUserOrderBean(rs));
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain product beans", ex);
		} finally {
			close(rs);
			close(stmt);
			commitAndClose(con);
		}
		return productBeans;
	}

	/**
	 * Returns all user beans.
	 * 
	 * @return List of user beans.
	 */
	public List<UserBean> getAllUserBeans() {
		List<UserBean> userBeans = new ArrayList<UserBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt
					.executeQuery("SELECT u.id, u.login, u.name, u.email, r.name "
							+ "FROM users u, roles r " + "WHERE u.role_id=r.id");
			while (rs.next()) {
				userBeans.add(extractUserBean(rs));
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain product beans", ex);
		} finally {
			close(rs);
			close(stmt);
			commitAndClose(con);
		}
		return userBeans;
	}

	/**
	 * Returns cart product beans with given identifiers.
	 * 
	 * @param productId
	 *            Identifiers of product items.
	 * @return List of cart product beans.
	 */
	public List<CartProductBean> getCartBeanByProductId(int... productId) {
		List<CartProductBean> productBeans = new ArrayList<CartProductBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			for (int i : productId) {
				pstmt = con.prepareStatement("SELECT p.id, p.name, p.price "
						+ "FROM products p " + "WHERE p.id=?");
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					productBeans.add(extractCartProductBean(rs));
				}
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain cart product beans", ex);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return productBeans;
	}

	/**
	 * Returns quantity of product.
	 * 
	 * @param productId
	 *            Identifiers of product items.
	 * @return Integer quantity of product.
	 */
	public int getQuantityOfProductsByID(int productId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int quantity = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT p.quantity "
					+ "FROM products p " + "WHERE p.id=?");
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				quantity = rs.getInt("quantity");
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain quantity of product", ex);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return quantity;
	}

	/**
	 * Returns products with given category id.
	 * 
	 * @param categoryId
	 *            Identifiers of category items.
	 * @return List of products.
	 */
	public List<Product> getProductsByCategoryId(int categoryId) {
		List<Product> products = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con
					.prepareStatement("SELECT * FROM products WHERE category_id=?");
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				products.add(extractProduct(rs));
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain products", ex);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return products;
	}

	/**
	 * Returns all users.
	 * 
	 * @return List of users.
	 */
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt
					.executeQuery("SELECT * FROM users WHERE users.role_id != 0");
			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain users", ex);
		} finally {
			close(rs);
			close(stmt);
			commitAndClose(con);
		}
		return users;
	}

	/**
	 * Returns user with given login.
	 * 
	 * @param login
	 * 
	 * @return User object.
	 */
	public User getUserByLogin(String login) {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM users WHERE login = ?");
			pstmt.setString(1, login);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				return extractUser(resultSet);
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain user by login", ex);
		} finally {
			close(resultSet);
			close(pstmt);
			commitAndClose(con);
		}
		return null;
	}

	/**
	 * Update user.
	 * 
	 * @param user
	 *            user to update.
	 */
	public void updateUser(User user) {
		Connection con = null;
		try {
			con = getConnection();
			updateUser(con, user);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot update a user", ex);
		} finally {
			commitAndClose(con);
		}
	}

	/**
	 * Update user.
	 * 
	 * @param user
	 *            user to update.
	 * @throws SQLException
	 */
	public void updateUser(Connection con, User user) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con
					.prepareStatement("UPDATE users SET password=?, name=?, locale_name=? "
							+ "WHERE id=?");
			int k = 1;
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getName());
			pstmt.setString(k++, user.getLocaleName());
			pstmt.setLong(k, user.getId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * Make order.
	 * 
	 * @param user
	 *            , id products
	 * 
	 * @return true if order made
	 */
	public boolean makeOrder(User user, int... productId) {
		PreparedStatement pstmt1 = null, pstmt2 = null;
		Connection con = null;
		boolean result = false;
		int orderId = 0;
		try {
			con = getConnection();
			pstmt1 = con.prepareStatement(
					"INSERT INTO orders VALUES (DEFAULT, ?, 1)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt1.setInt(1, user.getId());
			result = pstmt1.executeUpdate() > 0;

			ResultSet rs = pstmt1.getGeneratedKeys();
			try {
				if (rs.next()) {
					orderId = rs.getInt(1);
				}
			} finally {
				close(rs);
			}
			pstmt2 = con.prepareStatement("INSERT INTO carts VALUES (?, ?)");
			for (int i : productId) {
				pstmt2.setInt(1, orderId);
				pstmt2.setInt(2, i);
				pstmt2.executeUpdate();
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot make the order", ex);
		} finally {
			close(pstmt1);
			close(pstmt2);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Make order.
	 * 
	 * @param user
	 *            , id products
	 * 
	 * @return true if order made
	 */
	public boolean makeOrder(User user, List<CartProductBean> list) {
		PreparedStatement pstmt1 = null, pstmt2 = null;
		Connection con = null;
		boolean result = false;
		int orderId = 0;
		try {
			con = getConnection();
			pstmt1 = con.prepareStatement(
					"INSERT INTO orders VALUES (DEFAULT, ?, 1)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt1.setInt(1, user.getId());
			result = pstmt1.executeUpdate() > 0;

			ResultSet rs = pstmt1.getGeneratedKeys();
			try {
				if (rs.next()) {
					orderId = rs.getInt(1);
				}
			} finally {
				close(rs);
			}
			pstmt2 = con
					.prepareStatement("INSERT INTO carts VALUES (?, ?, ?, ?)");
			for (CartProductBean cpb : list) {
				int i = 1;
				pstmt2.setInt(i++, orderId);
				pstmt2.setInt(i++, cpb.getId());
				pstmt2.setInt(i++, cpb.getQuantity());
				pstmt2.setInt(i++, cpb.getQuantity() * cpb.getPrice());
				pstmt2.executeUpdate();
			}
			updateQuantity(list, con);
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot make the order", ex);
		} finally {
			close(pstmt1);
			close(pstmt2);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Update quantity of products.
	 * 
	 * @param list
	 *            list of product beans.
	 * @throws SQLException
	 */
	private void updateQuantity(List<CartProductBean> list, Connection con)
			throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con
					.prepareStatement("UPDATE products SET quantity=quantity - ?"
							+ "WHERE id=?");
			for (CartProductBean cpb : list) {
				pstmt.setInt(1, cpb.getQuantity());
				pstmt.setInt(2, cpb.getId());
				pstmt.executeUpdate();
			}

		} finally {
			close(pstmt);
		}

	}

	/**
	 * Remove product by id.
	 * 
	 * @param id
	 *            products
	 * 
	 * @return true if products removed
	 */
	public boolean removeProductsById(int productId) {
		PreparedStatement pstmt1 = null, pstmt2 = null;
		Connection con = null;
		boolean result = false;
		try {
			con = getConnection();
			pstmt1 = con
					.prepareStatement("DELETE FROM carts WHERE carts.product_id=?");
			pstmt2 = con
					.prepareStatement("DELETE FROM products WHERE products.id=?");
			pstmt1.setInt(1, productId);
			pstmt2.setInt(1, productId);
			pstmt1.executeUpdate();
			pstmt2.executeUpdate();

		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot make the order", ex);
		} finally {
			close(pstmt1);
			close(pstmt2);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Returns list of order beans.
	 * 
	 * @param User
	 *            .
	 * @return List of order beans
	 */
	public List<OrderBean> getAllOrderBeansByUser(User user) {
		List<OrderBean> orderBeans = new ArrayList<OrderBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT o.id, s.name "
					+ "FROM statuses s, orders o "
					+ "WHERE o.status_id=s.id and o.user_id=?");
			pstmt.setInt(1, user.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderBeans.add(extractOrderBean(rs));
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot obtain order bean items", ex);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return orderBeans;
	}

	/**
	 * Create a new user.
	 * 
	 * @param name
	 *            , login, password
	 * 
	 * @return true if user created
	 */
	public boolean createUser(String name, String login, String password,
			String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		boolean result = false;
		try {
			con = getConnection();
			pstmt = con
					.prepareStatement("INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?, NULL, 1)");
			int k = 1;
			pstmt.setString(k++, name);
			pstmt.setString(k++, login);
			pstmt.setString(k++, password);
			pstmt.setString(k++, email);
			if (!hasLoginInDB(con, login)) {
				pstmt.executeUpdate();
			} else {
				LOG.error("A user with this login already exists");
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot add the user", ex);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Create a new user.
	 * 
	 * @param name
	 *            , login, password
	 * 
	 * @return true if user created
	 */
	public boolean createAdmin(String name, String login, String password,
			String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		boolean result = false;
		try {
			con = getConnection();
			pstmt = con
					.prepareStatement("INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?, NULL, 0)");
			int k = 1;
			pstmt.setString(k++, name);
			pstmt.setString(k++, login);
			pstmt.setString(k++, password);
			pstmt.setString(k++, email);
			if (!hasLoginInDB(con, login)) {
				pstmt.executeUpdate();
			} else {
				LOG.error("A user with this login already exists");
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error("Cannot add the user", ex);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Checks if there is a login in DB.
	 * 
	 * @param connection
	 *            , login
	 * 
	 * @return true if login is in DB
	 */
	public boolean hasLoginInDB(String login) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM users WHERE login = ?");
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			LOG.error("A user with this login already exists", e);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Change order's status.
	 * 
	 * @param statusName
	 *            , orderId
	 * 
	 * @return true if status changed
	 */
	public boolean changeOrderStatus(String statusName, int orderId) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean result = false;
		try {
			con = getConnection();
			int statusId = getStatusIdByName(con, statusName);
			pstmt = con
					.prepareStatement("UPDATE orders SET orders.status_id=? WHERE orders.id=?");
			pstmt.setInt(1, statusId);
			pstmt.setInt(2, orderId);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			LOG.error("Can't update the status", e);
		} finally {
			close(pstmt);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Change user's role.
	 * 
	 * @param roleName
	 *            , userId
	 * 
	 * @return true if role changed
	 */
	public boolean changeUserRole(String roleName, int userId) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean result = false;
		try {
			con = getConnection();
			int roleId = getRoleIdByName(con, roleName);
			pstmt = con
					.prepareStatement("UPDATE users SET users.role_id=? WHERE users.id=?");
			pstmt.setInt(1, roleId);
			pstmt.setInt(2, userId);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			LOG.error("Can't update the role", e);
		} finally {
			close(pstmt);
			commitAndClose(con);
		}
		return result;

	}

	/**
	 * Add the product.
	 * 
	 * @param category
	 *            , name, price, color, size, date
	 * 
	 * @return true if product added
	 */
	public boolean addProduct(String category, String name, int price,
			String color, String size, Date date) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean result = false;
		try {
			con = getConnection();
			int categoryId = getCategoryIdByName(con, category);
			pstmt = con
					.prepareStatement("INSERT INTO products VALUES (DEFAULT, ?, ?, ?, ?, ?, 1, ?)");
			int k = 1;
			pstmt.setString(k++, name);
			pstmt.setInt(k++, price);
			pstmt.setString(k++, color);
			pstmt.setString(k++, size);
			pstmt.setDate(k++, date);
			pstmt.setInt(k++, categoryId);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			LOG.error("Can't add the product", e);
		} finally {
			close(pstmt);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Update the product.
	 * 
	 * @param id
	 *            , category, name, price, color, size, date
	 * 
	 * @return true if product updated
	 */
	public boolean updateProduct(int id, String category, String name,
			int price, String color, String size, Date date) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean result = false;
		try {
			con = getConnection();
			int categoryId = getCategoryIdByName(con, category);
			pstmt = con
					.prepareStatement("UPDATE products SET name=?, price=?, color=?, size=?, novelty=?, category_id=? WHERE id=?");
			int k = 1;
			pstmt.setString(k++, name);
			pstmt.setInt(k++, price);
			pstmt.setString(k++, color);
			pstmt.setString(k++, size);
			pstmt.setDate(k++, date);
			pstmt.setInt(k++, categoryId);
			pstmt.setInt(k++, id);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			LOG.error("Can't update the product", e);
		} finally {
			close(pstmt);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Checks if there is a product in DB.
	 * 
	 * @param idProduct
	 * 
	 * @return true if product is in DB
	 */
	public boolean hasProductInDB(int idProduct) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM products WHERE id = ?");
			pstmt.setInt(1, idProduct);
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			LOG.error("A product with this id doesn't exists", e);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Checks if there is a product in orders.
	 * 
	 * @param idProduct
	 * 
	 * @return true if product is in DB
	 */
	public boolean hasProductInOrders(int idProduct) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		boolean result = false;

		try {
			con = getConnection();
			pstmt = con
					.prepareStatement("SELECT * FROM carts WHERE product_id = ?");
			pstmt.setInt(1, idProduct);
			rs = pstmt.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			LOG.error("A product with this id doesn't exists", e);
		} finally {
			close(rs);
			close(pstmt);
			commitAndClose(con);
		}
		return result;
	}

	/**
	 * Get category id by category name.
	 * 
	 * @param con
	 *            , categoryName
	 * 
	 * @return int category id.
	 * 
	 * @throws SQLException
	 */
	private int getCategoryIdByName(Connection con, String categoryName)
			throws SQLException {
		PreparedStatement pstmt = con
				.prepareStatement("SELECT * FROM categories WHERE name = ?");
		pstmt.setString(1, categoryName);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("ID");
		} else {
			throw new SQLException("Category with that name does not exist");
		}
	}

	/**
	 * Get status id by status name.
	 * 
	 * @param con
	 *            , name
	 * 
	 * @return int status id.
	 * 
	 * @throws SQLException
	 */
	private int getStatusIdByName(Connection con, String name)
			throws SQLException {
		PreparedStatement pstmt = con
				.prepareStatement("SELECT * FROM statuses WHERE name = ?");
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("ID");
		} else {
			throw new SQLException("Status with that name does not exist");
		}

	}

	/**
	 * Get role by name.
	 * 
	 * @param con
	 *            , roleName
	 * 
	 * @return int role id.
	 * 
	 * @throws SQLException
	 */
	private int getRoleIdByName(Connection con, String roleName)
			throws SQLException {
		PreparedStatement pstmt = con
				.prepareStatement("SELECT * FROM roles WHERE name = ?");
		pstmt.setString(1, roleName);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("ID");
		} else {
			throw new SQLException("Role with that name does not exist");
		}
	}

	/**
	 * Checks if there is a login in DB.
	 * 
	 * @param connection
	 *            , login
	 * 
	 * @return true if login is in DB
	 * 
	 * @throws SQLException
	 */
	private static boolean hasLoginInDB(Connection con, String login)
			throws SQLException {
		PreparedStatement pstmt = con
				.prepareStatement("SELECT * FROM users WHERE login = ?");
		pstmt.setString(1, login);
		ResultSet rs = pstmt.executeQuery();
		return rs.next();
	}

	/**
	 * Extracts a order bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user order bean will be extracted.
	 * @return OrderBean object
	 * 
	 * @throws SQLException
	 */
	private OrderBean extractOrderBean(ResultSet rs) throws SQLException {
		OrderBean orderBean = new OrderBean();
		orderBean.setId(rs.getInt("ID"));
		orderBean.setStatusOrder(rs.getString("name"));
		return orderBean;
	}

	/**
	 * Extracts a user from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user order bean will be extracted.
	 * @return User object
	 * 
	 * @throws SQLException
	 */
	private static User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("ID"));
		user.setName(rs.getString("name"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setRoleId(rs.getInt("role_id"));
		return user;
	}

	/**
	 * Extracts a product from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user order bean will be extracted.
	 * @return Product object
	 * 
	 * @throws SQLException
	 */
	private Product extractProduct(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt("ID"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getInt("price"));
		product.setColor(rs.getString("color"));
		product.setSize(rs.getString("size"));
		product.setNovelty(rs.getDate("novelty"));
		product.setQuantity(rs.getInt("quantity"));
		product.setCategoryId(rs.getInt("category_id"));
		return product;
	}

	/**
	 * Extracts a product bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user order bean will be extracted.
	 * @return ProductBean object
	 * 
	 * @throws SQLException
	 */
	private ProductBean extractProductBean(ResultSet rs) throws SQLException {
		ProductBean productBean = new ProductBean();
		productBean.setId(rs.getInt("ID"));
		productBean.setCategory(rs.getString(2));
		productBean.setName(rs.getString(3));
		productBean.setPrice(rs.getInt("price"));
		productBean.setColor(rs.getString("color"));
		productBean.setSize(rs.getString("size"));
		productBean.setNovelty(rs.getDate("novelty"));
		productBean.setQuantity(rs.getInt("quantity"));
		return productBean;
	}

	/**
	 * Extracts a user order bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user order bean will be extracted.
	 * @return UserOrderBean object
	 * 
	 * @throws SQLException
	 */
	private UserOrderBean extractUserOrderBean(ResultSet rs)
			throws SQLException {
		UserOrderBean userOrderBean = new UserOrderBean();
		userOrderBean.setOrderId(rs.getInt("ID"));
		userOrderBean.setUserLogin(rs.getString("login"));
		userOrderBean.setUserName(rs.getString("name"));
		userOrderBean.setCartPrice(rs.getInt("price"));
		userOrderBean.setStatusName(rs.getString(5));
		return userOrderBean;
	}

	/**
	 * Extracts a user bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user order bean will be extracted.
	 * @return UserBean object
	 * 
	 * @throws SQLException
	 */
	private static UserBean extractUserBean(ResultSet rs) throws SQLException {
		UserBean userBean = new UserBean();
		userBean.setUserId(rs.getInt("ID"));
		userBean.setUserLogin(rs.getString("login"));
		userBean.setUserName(rs.getString("name"));
		userBean.setUserEmail(rs.getString("email"));
		userBean.setRoleName(rs.getString(5));
		return userBean;
	}

	/**
	 * Extracts a cart product bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user order bean will be extracted.
	 * @return CartProductBean object
	 * 
	 * @throws SQLException
	 */
	private CartProductBean extractCartProductBean(ResultSet rs)
			throws SQLException {
		int quantity = 1;
		CartProductBean cartProductBean = new CartProductBean();
		cartProductBean.setId(rs.getInt("ID"));
		cartProductBean.setProductName(rs.getString("name"));
		cartProductBean.setPrice(rs.getInt("price"));
		cartProductBean.setQuantity(quantity);
		return cartProductBean;
	}

	/**
	 * Commits and close the given connection.
	 * 
	 * @param con
	 *            Connection to be committed and closed.
	 */
	private void commitAndClose(Connection con) {
		if (con != null) {
			try {
				con.commit();
				con.close();
			} catch (SQLException ex) {
				LOG.error("Cannot commit transaction and close connection", ex);
			}
		}
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error("Cannot close a result set", ex);
			}
		}
	}

	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error("Cannot close a statement", ex);
			}
		}
	}

	/**
	 * Rollbacks and close the given connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked and closed.
	 */
	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

}