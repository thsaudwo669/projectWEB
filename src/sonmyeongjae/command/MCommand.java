package sonmyeongjae.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response);

}
