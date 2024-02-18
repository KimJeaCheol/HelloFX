package DAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DTO.Board;

public class BoardDAO extends JDBConnection {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public List<Board> list(){
		List<Board> boardList = new ArrayList<Board>();
		String sql = " SELECT * "
				   + " FROM board ";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(dateFormat.format(rs.getTimestamp("reg_date")));
				board.setUpdDate(dateFormat.format(rs.getTimestamp("upd_date")));
				boardList.add(board);
			}
		} catch (SQLException e) {
			System.out.println("게시글 목록 조회시, 에러 발생");
			e.printStackTrace();
			// TODO: handle exception
		}
		return boardList;
	}
	
	public Board select(int boardNo) {
		Board board = new Board();
		String sql = " SELECT * "
				   + " FROM board "
				   + " WHERE board_no = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(dateFormat.format(rs.getTimestamp("reg_date")));
				board.setUpdDate(dateFormat.format(rs.getTimestamp("upd_date")));
			}else {
				board = null;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("게시글 조회시, 에러 발생");
			e.printStackTrace();
		}
		return board;
	}
	
	public int insert(Board board) {
		int result = 0;
		String sql = " INSERT INTO board( title, writer, content ) "
				   + " VALUES( ? , ? , ?) ";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getWriter());
			psmt.setString(3, board.getContent());
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("게시글 등록시, 에러 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public int update(Board board) { 
		int result = 0;
		String sql = " UPDATE board "
				   + " SET title =? , writer =?, content=?, upd_date = now() "
				   + " WHERE board_no = ? ";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getWriter());
			psmt.setString(3, board.getContent());
			psmt.setInt(4, board.getBoardNo());
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("게시글 등록시, 에러 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public int delete(int boardNo) {
		int result = 0;
		String sql = " DELETE FROM board "
				   + " WHERE board_no = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("게시글 삭제시, 에러 발생");
			e.printStackTrace();
		}
		return result;
	}
}
