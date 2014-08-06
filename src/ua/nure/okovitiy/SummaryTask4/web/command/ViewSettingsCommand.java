package ua.nure.okovitiy.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.okovitiy.SummaryTask4.web.command.ViewSettingsCommand;
import ua.nure.okovitiy.SummaryTask4.Path;

/**
 * View settings command.
 * 
 * @author Andrew Okovitiy
 * 
 */
class ViewSettingsCommand extends Command {

	private static final long serialVersionUID = 130572827745744414L;

	private static final Logger LOG = Logger
			.getLogger(ViewSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("Command starts");

		LOG.debug("Command finished");
		return Path.PAGE_SETTINGS;
	}

}
