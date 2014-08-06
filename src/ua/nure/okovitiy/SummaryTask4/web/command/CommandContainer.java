package ua.nure.okovitiy.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.okovitiy.SummaryTask4.web.command.UpdateSettingsCommand;

/**
 * Holder for all commands.<br/>
 * 
 * @author Andrew Okovitiy
 * 
 */
public class CommandContainer {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {

		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());
		commands.put("sortProducts", new SortProductsCommand());
		commands.put("addToCart", new AddToCartCommand());
		commands.put("viewRegistration", new ViewRegistrationFormCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("cleanProducts", new CleanProductsCommand());
		commands.put("listProducts", new ListProductsCommand());

		// client commands
		commands.put("makeOrder", new MakeOrderCommand());
		commands.put("persanalAccount", new PersonalAccountCommand());

		// admin commands
		commands.put("listOrders", new ListOrdersCommand());
		commands.put("sortOrders", new SortOrdersCommand());
		commands.put("changeStatus", new ChangeStatusCommand());
		commands.put("viewProducts", new AdminListProductsCommand());
		commands.put("removeProduct", new RemoveProductCommand());
		commands.put("addProduct", new AddProductCommand());
		commands.put("listUsers", new ListUsersCommand());
		commands.put("blockUsers", new BlockUserCommand());
		commands.put("addAdmin", new AddAdminCommand());

		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}

}
