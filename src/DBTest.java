
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DTO.Board;
import Service.BoardService;
import Service.BoardServiceImpl;


public class DBTest {
	static List<Board> boardList = new ArrayList<Board>();
	static Scanner sc = new Scanner(System.in);
	static BoardService boardService = new BoardServiceImpl();
	
	
	public static void main(String[] args) {
		int menuNo = 0; //메뉴번호
		do {
			menu();
			//메뉴판 메소드 출력
			menuNo = sc.nextInt();
			sc.nextLine();
			
			if(menuNo == 0 ) {
				break;
			}
			
			switch (menuNo) {
				case 1: {
					list(); break;
				}case 2: {
					read(); break;
				}case 3: {
					insert(); break;
				}case 4: {
					update(); break;
				}case 5: {
					delete(); break;
				}
				default:
					System.err.println("0~5 사이의 숫자를 입력해주세요.");
			}
		} while (menuNo != 0);
		System.out.println("프로그램을 종료 합니다...");
	}
	
	private static void delete() {
		// TODO Auto-generated method stub
		System.out.println("##### 게시글 삭제 #####");
		System.out.println("게시글 번호 : ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		int result= boardService.delete(boardNo);
		if(result > 0) {
			System.out.println("게시글이 삭제되었습니다.");
		}
	}

	private static void update() {
		// TODO Auto-generated method stub
		System.out.println("##### 게시글 수정 #####");
		System.out.println("게시글 번호 : ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		Board board = input();
		board.setBoardNo(boardNo);
		
		int result = boardService.update(board);
		if(result > 0) {
			System.out.println("게시글이 수정되었습니다.");
		}
		
	}

	private static void insert() {
		// TODO Auto-generated method stub
		System.out.println("##### 게시글 쓰기 #####");
		Board board = input();
		int result = boardService.insert(board); //게시글 쓰기 요청
		if(result > 0) {
			System.out.println("게시글이 작성되었습니다.");
		}
	}
	
	private static Board input() {
		// TODO Auto-generated method stub
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("작성자 : ");
		String writer = sc.nextLine();
		System.out.print("내용 :");
		String content = sc.nextLine();
		
		Board board = new Board(title, writer, content);
		return board;
	}

	private static void read() {
		// TODO Auto-generated method stub
		System.out.println("##### 게시글 읽기 #####");
		System.out.println("글번호 : ");
		int boardNo = sc.nextInt();
		Board board = boardService.select(boardNo); //게시글 목록 요청
		print(board);
	}

	private static void list() {
		// TODO Auto-generated method stub
		System.out.println("##### 게시글 목록 #####");
		boardList = boardService.list(); //게시글 목록 요청
		printAll(boardList); //게시글 목록 출력 메소드 호출
	}


	private static void printAll(List<Board> list) {
		// TODO Auto-generated method stub
		if(list == null || list.isEmpty()) {
			System.out.println("조회된 게시글이 없습니다.");
		}
		
		for(Board board : list) {
			print(board);
		}
		
	}

	private static void print(Board board) {
		// TODO Auto-generated method stub
		if(board == null) {
			System.out.println("조회되지 않는 게시글");
			return;
		}
		
		int boardNo = board.getBoardNo();
		String title = board.getTitle();
		String writer = board.getWriter();
		String content = board.getContent();
		Date regDate = board.getRegDate();
		Date updDate = board.getUpdDate();
		
		SimpleDateFormat dateFormate = new SimpleDateFormat("YYYY/MM/dd - HH:mm:ss");
		String regDateStr = dateFormate.format(regDate);
		String updDateStr = dateFormate.format(updDate);
		
		System.out.println("[글번호 : " + boardNo + "]");
		System.out.println("제목 : " + title);
		System.out.println("작성자 : " + writer);
		System.out.println("내용 :"+ content);
		System.out.println("등록일시 : " + regDateStr);
		System.out.println("수정일시 : " + updDateStr);
		System.out.println("--------------------------");
		
	}

	private static void menu() {
		System.out.println("##### 게시판 #####");
		System.out.println("1.게시글 목록");
		System.out.println("2.게시글 읽기");
		System.out.println("3.게시글 쓰기");
		System.out.println("4.게시글 수정");
		System.out.println("5.게시글 삭제");
		System.out.println("0.프로그램 종료");
		System.out.print("##### 번호 입력 : ");
	}
	
}
