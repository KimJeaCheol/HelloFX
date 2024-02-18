package Service;

import java.util.List;

import DAO.BoardDAO;
import DTO.Board;

public class BoardServiceImpl implements BoardService{

	private BoardDAO boardDao = new BoardDAO();
	
	@Override
	public List<Board> list() {
		// TODO Auto-generated method stub
		List<Board> boardList = boardDao.list();
		return boardList;
	}

	@Override
	public Board select(int boardNo) {
		// TODO Auto-generated method stub
		Board board = boardDao.select(boardNo);
		return board;
	}

	@Override
	public int insert(Board board) {
		// TODO Auto-generated method stub
		int result = boardDao.insert(board);
		return result;
	}

	@Override
	public int update(Board board) {
		// TODO Auto-generated method stub
		int result = boardDao.update(board);
		return result;
	}

	@Override
	public int delete(int boardNo) {
		// TODO Auto-generated method stub
		int result = boardDao.delete(boardNo);
		return result;
	}
	
}
