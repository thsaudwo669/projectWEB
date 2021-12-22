 package sonmyeongjae.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import sonmyeongjae.Dto.BoarderDTO;

public class BoarderDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BoarderDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/JSP");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
//Connection 해제를 위한 메소드
	public void close() {
		try {
			if(con !=null) {
				con.close();
				con=null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
// 전체 글 목록보기	
	public ArrayList<BoarderDTO> list(){
		String sql = "SELECT * FROM BOARD";
		
		ArrayList<BoarderDTO> dtos = new ArrayList<BoarderDTO>();	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {		
				BoarderDTO dto = new BoarderDTO();
				dto.setNo(rs.getString("NO"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegdate(rs.getDate("REGDATE"));
				dtos.add(dto);		
			}
			rs.close(); pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dtos;
	}
// 글 추가하기
	public boolean insert(BoarderDTO dto) {	//DB에 저장이 잘되면 true, 잘안되었으면  false를 반환
		String sql = "insert into board(no, title, writer, content, regdate) values(NO_SEQ.nextval,?,?,?, SYSDATE)"; 
		boolean check = false;
		try {  
			con = ds.getConnection();  //Connection객체 CP에서 얻어오기
			pstmt =con.prepareStatement(sql);  	//Connection객체를 통해 SQL문 준비
			//pstmt.setString(1, dto.getNo());	//SQL문과 데이터 바인팅
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			
			int x = pstmt.executeUpdate();	

			if(x<1) {	
				System.out.println("정상적으로 저장되지 않았습니다.");
			}else {		
				check=true;
			}
			pstmt.close();
		}catch(SQLException ex) {
			System.out.println("SQL insert 오류 : " + ex.getLocalizedMessage());
			check = false;
		}
		return check;
	}
//글 상세 보기	
	public BoarderDTO view(String no) {
		String sql ="select title, writer,content,regdate from board where no=?";
		BoarderDTO dto = new BoarderDTO();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {		//상세보기를 위한 한 레코드셋을 DTO에 저장
				dto.setNo(no);
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getDate("regdate"));
			}
			
			rs.close();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;	//DTO객체에 데이터를 담아서 반환
	}
	

	
// 글 정보 수정하기	
	public boolean update(BoarderDTO dto) {
		String sql = "update board set title=?, writer=?, content=?, regdate=? where no=?";
		boolean check = false;
		try {
			con = ds.getConnection();
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			pstmt.setDate(4, dto.getRegdate());
			pstmt.setString(5, dto.getNo());
			
			int x = pstmt.executeUpdate();	

			if(x<1) {
				System.out.println("정상적으로 저장되지 않았습니다.");
			}else {
				check=true;
			}
			pstmt.close();
		}catch(SQLException ex) {
			System.out.println("SQL insert 오류 : " + ex.getLocalizedMessage());
			check = false;
		}
		return check;
	}
// 글 삭제 하기		
	public boolean delete(String no) {
		String sql = "delete from board where no=?";
		boolean check = false;
		try {
			con = ds.getConnection();
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, no);
			
			int x = pstmt.executeUpdate();	

			if(x<1) {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}else {
				check=true;
			}
			pstmt.close();
		}catch(SQLException ex) {
			System.out.println("SQL insert 오류 : " + ex.getLocalizedMessage());
			check = false;
		}
		return check;
	}
}	
