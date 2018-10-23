package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Controller {

    @FXML
    private Pane Paneid;

    @FXML
    private TextField txt;

    public void browseAction(ActionEvent actionEvent) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) Paneid.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            txt.setText(file.getAbsolutePath());
        }
    }

        public void buttonOkAction (ActionEvent actionEvent) throws IOException {
            File dir = new File(txt.getText());
            List<String> names = new ArrayList<>();
            readFiles(dir, names);
            Collections.sort(names);
            setText(names);
            File file = new File(txt.getText() + "\\File Names.txt");
            java.awt.Desktop.getDesktop().open(file);
        }

    public  static void readFiles(File baseDirectory, List<String> names){
        if(baseDirectory.isDirectory()){
            for(File file : baseDirectory.listFiles()){
                if(file.isFile()) names.add(file.getName());
                else readFiles(file, names);

            }
        }
    }

    public void setText(List<String> names) throws IOException {
        FileWriter writer = new FileWriter(txt.getText() + "\\File Names.txt");
        for (String s : names) {
            writer.write(s + "\r\n");
        }
        writer.close();
    }
}

