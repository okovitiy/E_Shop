package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface for the Command pattern implementation.
 * 
 * @author Andrew Okovitiy
 * 
 */
public abstract class Command implements Serializable {

	private static final long serialVersionUID = 5767263539806311780L;

	/**
	 * Execution method for command.
	 * 
	 * @return Address to go once the command is executed.
	 */
	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}
