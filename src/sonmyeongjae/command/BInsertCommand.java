package sonmyeongjae.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sonmyeongjae.Dao.BoarderDAO;
import sonmyeongjae.Dto.BoarderDTO;

public class BInsertCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoarderDTO dto = new BoarderDTO();	
		
		dto.setNo(request.getParameter("no"));		
		dto.setTitle(request.getParameter("title"));
		dto.setWriter(request.getParameter("writer"));
		dto.setContent(request.getParameter("content"));
		
		
		BoarderDAO dao = new BoarderDAO();	
		dao.insert(dto);			
	}
}