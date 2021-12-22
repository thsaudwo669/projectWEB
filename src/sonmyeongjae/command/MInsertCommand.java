package sonmyeongjae.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sonmyeongjae.Dao.MemberDao;
import sonmyeongjae.Dto.MemberDto;

public class MInsertCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); 
		MemberDto dto = new MemberDto();
		
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		
		MemberDao dao = new MemberDao();
		dao.insert(dto);

	}

}
