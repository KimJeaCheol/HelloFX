package Controller;

import java.io.IOException;

import DTO.Board;
import Service.BoardService;
import Service.BoardServiceImpl;
import Util.SceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReadController {
    
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfWriter;
    @FXML
    private TextArea taContent;

    private BoardService boardService = new BoardServiceImpl();

    int boardNo;

    public void read(int boardNo) {
        System.out.println("게시글 번호 : "+boardNo);
        this.boardNo = boardNo;
        Board board = boardService.select(boardNo);
        tfTitle.setText(board.getTitle());
        tfWriter.setText(board.getWriter());
        taContent.setText(board.getContent());
    }

    public void moveToList(ActionEvent event) throws IOException {
        SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
    }
    
    public void moveToUpdate(ActionEvent event) throws IOException {
        UpdateController updateController = (UpdateController) SceneUtil.getInstance().getController(UI.UPDATE.getPath());
        updateController.read(boardNo);
        Parent root = SceneUtil.getInstance().getRoot();
        SceneUtil.getInstance().switchScene(event, UI.UPDATE.getPath(), root);
        
    }
}
