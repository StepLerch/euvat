package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import service.VATManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable {
    private static final int THREE_COUNTRIES = 3;
    private static final int ALL_EU_COUNTRIES = 28;

    public MainPaneController(){

    }

    @FXML
    private ListView resultList;
    ObservableList<String> listItems;

    @FXML
    private void showAll(ActionEvent event)
    {
        List<String> vatRecords = VATManager.getInstance().getTopStandardVATRateRecords(ALL_EU_COUNTRIES);
        updateListItems(vatRecords);
    }

    @FXML
    private void showTop3(ActionEvent event)
    {
        List<String> vatRecords = VATManager.getInstance().getTopStandardVATRateRecords(THREE_COUNTRIES);
        updateListItems(vatRecords);
    }

    @FXML
    private void showBottom3(ActionEvent event){
        List<String> vatRecords = VATManager.getInstance().getBottomStandardVATRateRecords(THREE_COUNTRIES);
        updateListItems(vatRecords);
    }

    private void updateListItems(List<String> vatRecords){
        if(vatRecords == null || vatRecords.isEmpty()){
            castErrorDialogue("Unable to load data, see log for more details.");
            return;
        }
        listItems = FXCollections.observableArrayList (vatRecords);
        this.resultList.setItems(listItems);
    }

    private void castErrorDialogue(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
