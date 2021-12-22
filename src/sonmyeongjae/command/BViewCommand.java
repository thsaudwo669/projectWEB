package sonmyeongjae.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sonmyeongjae.Dao.BoarderDAO;
import sonmyeongjae.Dto.BoarderDTO;

public class BViewCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String no = request.getParameter("no");
		
		BoarderDAO dao = new BoarderDAO();

		BoarderDTO dto = dao.view(no);	
		
		request.setAttribute("dto", dto);
	}

}