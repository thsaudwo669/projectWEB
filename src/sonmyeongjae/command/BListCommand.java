package sonmyeongjae.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sonmyeongjae.Dao.BoarderDAO;
import sonmyeongjae.Dto.BoarderDTO;

public class BListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoarderDAO dao = new BoarderDAO();
		
	
		ArrayList<BoarderDTO> dtos = dao.list();
		

		request.setAttribute("dtos", dtos);
	}
}