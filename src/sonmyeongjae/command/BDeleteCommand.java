package sonmyeongjae.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sonmyeongjae.Dao.BoarderDAO;

public class BDeleteCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8"); 
		  String no = request.getParameter("no");
		  
		  BoarderDAO dao = new BoarderDAO();
		  
		  dao.delete(no);		  
	}
}
