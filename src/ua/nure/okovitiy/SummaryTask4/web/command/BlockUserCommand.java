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
import ua.nure.okovitiy.SummaryTask4.db.bean.UserBean;

/**
 * Block user command.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class BlockUserCommand extends Command {

	private static final long serialVersionUID = -778535254076631303L;

	private static final Logger LOG = Logger.getLogger(BlockUserCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();
		String[] roleNames = request.getParameterValues("roleName");

		@SuppressWarnings("unchecked")
		List<UserBean> list = (List<UserBean>) session
				.getAttribute("userBeans");

		list = changeRoles(list, roleNames);

		session.setAttribute("userOrderBeanList", list);

		LOG.debug("Command finished");
		return Path.COMMAND_LIST_USERS;
	}

	private static List<UserBean> changeRoles(List<UserBean> list,
			String[] roleNames) {
		if (list.size() == roleNames.length) {
			for (int i = 0; (i < list.size()); i++) {
				if (!list.get(i).getRoleName().equals(roleNames[i])) {
					list.get(i).setRoleName(roleNames[i]);
					DBManager.getInstance().changeUserRole(
							list.get(i).getRoleName(), list.get(i).getUserId());
				}
			}
		}
		return list;
	}
}
