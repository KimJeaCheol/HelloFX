package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DTO.Board;
import Service.BoardService;
import Service.BoardServiceImpl;
import Util.SceneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML
    private TableView<Board> boardTableView;
    @FXML
    private TableColumn<Board, Integer> colBoardNo;
    @FXML
    private TableColumn<Board, String> colTitle;
    @FXML
    private TableColumn<Board, String> colWriter;
    @FXML
    private TableColumn<Board, Integer> colRegDate;
    @FXML
    private TableColumn<Board, Integer> colUpdDate;
    @FXML
    private TableColumn<Board, CheckBox> colCbDelete;
    @FXML
    private CheckBox cbAll;

    BoardService boardService = new BoardServiceImpl();

    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        List<Board> boardList = new ArrayList<Board>();
        boardList = boardService.list();

        for (Board board : boardList) {
            System.out.println(board);
        }

        ObservableList<Board> list = FXCollections.observableArrayList(boardList);

        colBoardNo.setCellValueFactory(new PropertyValueFactory<>("BoardNo"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colWriter.setCellValueFactory(new PropertyValueFactory<>("Writer"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("RegDate"));
        colUpdDate.setCellValueFactory(new PropertyValueFactory<>("UpdDate"));
        colCbDelete.setCellValueFactory(new PropertyValueFactory<>("CbDelete"));

        boardTableView.setItems(list);

        cbAll.setSelected(false);

        cbAll.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event evnet) {
                CheckBox checkBox = (CheckBox) evnet.getSource();
                boolean checkAll = checkBox.isSelected();
                boardTableView.getItems().stream().forEach((board) -> {
                    ((CheckBox) board.getCbDelete()).setSelected(checkAll);
                });
            }
        });

        boardTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                if (event.getClickCount() == 2 && boardTableView.getSelectionModel().getSelectedItem() !=null) {
                    System.out.println("더블클릭");
                    int boardNo = boardTableView.getSelectionModel().getSelectedItem().getBoardNo();
                    try {

                        ReadController readController = (ReadController) SceneUtil.getInstance().getController(UI.READ.getPath());
                        readController.read(boardNo);
                        
                        Parent root = SceneUtil.getInstance().getRoot();
                        SceneUtil.getInstance().switchScene(event, UI.READ.getPath(), root);

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            
        });
    }

    public void close(ActionEvent event) {
        SceneUtil.getInstance().close(event);
    }

    public void deleteSelected(ActionEvent event) {
        boardTableView.getItems().stream().forEach((board) -> {
            CheckBox cbDelete = (CheckBox) board.getCbDelete();
            boolean checked = cbDelete.isSelected();
            if (checked) {
                int boardNo = board.getBoardNo();
                boardService.delete(boardNo);
            }
        });

        initialize(null, null);
    }
    
    public void moveToInsert(ActionEvent event) throws Exception{
        SceneUtil.getInstance().switchScene(event, UI.INSERT.getPath());
    }
    
}
