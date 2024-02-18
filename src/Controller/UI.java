package Controller;

public enum UI {
    
    MAIN("/Main.fxml"),
    INSERT("/UI/Insert.fxml"),
    READ("/UI/Read.fxml"),
    UPDATE("/UI/Update.fxml");

    private final String path;

    UI(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
