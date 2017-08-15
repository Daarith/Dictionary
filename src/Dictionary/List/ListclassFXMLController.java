/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary.List;

import Connection.DB;
import Dictionary.InputData.InputDateFXMLController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ListclassFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableView<ListClass> tableview;

    @FXML
    private TableColumn<ListClass, String> clmKeywordinKhmer;

    @FXML
    private TableColumn<ListClass, String> clmKeywordinEnglish;

    @FXML
    private TableColumn<ListClass, String> clmKeywordinFrance;

    @FXML
    private TableColumn<ListClass, String> clmRead;

    @FXML
    private TableColumn<ListClass, String> clmWordClass;

    @FXML
    private TableColumn<ListClass, String> clmCommittee;

    @FXML
    private TableColumn<ListClass, String> clmMajor;

    @FXML
    private TableColumn<ListClass, String> clmDate;
    
    @FXML
    private TextField txtSearch;
    
    @FXML
    private Button btnShowAll, btnBack;
    
    
    private ObservableList<ListClass> loadObservableList;
    
    private void readDataFromDB() throws ClassNotFoundException, SQLException{
        
        loadObservableList = FXCollections.observableArrayList();
        Connection connection = DB.dictionary();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String sql = "select * from dictionary.vocabulary";
        
        preparedStatement = connection.prepareStatement(sql);
        
        resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()){
            
            loadObservableList.add(new ListClass(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), 
                    resultSet.getString(6), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));
            
        }
        
        inputDataToTable();
        
        tableview.setItems(loadObservableList);
        
    }
    
    private void inputDataToTable(){
        
        clmKeywordinKhmer.setCellValueFactory(new PropertyValueFactory<ListClass, String>("KeywordinKhmer"));
        clmKeywordinEnglish.setCellValueFactory(new PropertyValueFactory<>("KeywordinEnglish"));
        clmKeywordinFrance.setCellValueFactory(new PropertyValueFactory<>("KeywordinFrance"));
        clmRead.setCellValueFactory(new PropertyValueFactory<>("Read"));
        clmWordClass.setCellValueFactory(new PropertyValueFactory<>("Wordclass"));
        clmCommittee.setCellValueFactory(new PropertyValueFactory<>("Committee"));
        clmMajor.setCellValueFactory(new PropertyValueFactory<>("Major"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        
        
    }
    
    private void textFieldChange(){
        
        txtSearch.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
 
                 FilteredList<ListClass> filteredList = new FilteredList<ListClass>(loadObservableList, p -> true);
                SortedList<ListClass> sortedList = new SortedList<ListClass>(filteredList);
                filteredList.setPredicate(Dictionary -> {
                if (Dictionary.getKeywordinKhmer().contains(newValue) || Dictionary.getKeywordinEnglish().contains(newValue) || Dictionary.getKeywordinFrance().contains(newValue)) {
                        return true;
                    }

                 return false;
                });
                
                sortedList.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedList);
            
            }
            
            
        });
        
       
    }
    
    @FXML
    private void onBackClick(){
        
        InputDateFXMLController.stage.close();
    }
    
    @FXML
    private void showAll() throws ClassNotFoundException, SQLException{
        
        readDataFromDB();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        textFieldChange();
        
        try {
             // TODO

             readDataFromDB();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(ListclassFXMLController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ListclassFXMLController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
    
}