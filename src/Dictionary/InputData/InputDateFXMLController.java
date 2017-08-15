/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary.InputData;

import Connection.DB;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Rith Record
 */
public class InputDateFXMLController implements Initializable {
    File flFullPathAndFileName;
    FileInputStream fileInputStream;
    @FXML ImageView Img;
    @FXML TextField ID;
    @FXML TextField KeywordKhmer;
    @FXML TextField KeywordEnglish;
    @FXML TextField KeywordFrance;
    @FXML TextField Reading;
    @FXML TextField Wordclass;
    @FXML TextArea Description;
    @FXML TextField Committee;
    @FXML TextField Skillblog;
    @FXML DatePicker TxtDate;
    @FXML TextField Addition;
    @FXML TextField Other;
    @FXML Button Btnsave;
    @FXML Button btnTableview;
    boolean checkdata =false ;
    boolean checkdata1 = false;
    public int intId, TempintId=0;
    public static  Stage stage;
    
    
    private void Alreadyhave() throws ClassNotFoundException, SQLException, IOException{
           btnSearch();
        
        if(checkdata ==false)
        {
            Connection Con = DB.dictionary();
        PreparedStatement preparedStatement = null;
        String Sql = " INSERT INTO dictionary.vocabulary (KeywordKhmer,KeywordEnglish,KeywordFrance,Reading"
                   + ",WordClass,Description,Picture,Committee,SkillBLOG,Date,Addition,Other)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        preparedStatement = Con.prepareStatement(Sql);
        System.out.print(Sql);
        
        preparedStatement.setString(1, KeywordKhmer.getText());
        preparedStatement.setString(2, KeywordEnglish.getText());
        preparedStatement.setString(3, KeywordFrance.getText());
        preparedStatement.setString(4, Reading.getText());
        preparedStatement.setString(5, Wordclass.getText());
        preparedStatement.setString(6, Description.getText());
        
        
        if(flFullPathAndFileName == null )
                {
                    preparedStatement.setBinaryStream(7, null);
                }
                else
                {
                    FileInputStream fis = new FileInputStream(flFullPathAndFileName);
                    preparedStatement.setBinaryStream(7, fis,(int) flFullPathAndFileName.length());
                }
        
        preparedStatement.setString(8, Committee.getText());
        preparedStatement.setString(9, Skillblog.getText());
        
        if(TxtDate.getValue() ==null)       
            
           preparedStatement.setDate(10, new java.sql.Date(new java.util.Date().getTime()));
        else
    
        preparedStatement.setDate(10, java.sql.Date.valueOf(TxtDate.getValue()));        
        
        
        
        preparedStatement.setString(11, Addition.getText());
        preparedStatement.setString(12, Other.getText());
        
        
        preparedStatement.executeUpdate();        
        Con.close();
        }    
           else
            {                    
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Already have",ButtonType.OK,ButtonType.CANCEL);
                alert.setHeaderText(null);
                alert.showAndWait();
                if(alert.getResult().equals(ButtonType.OK))
                {
                    Description.requestFocus();
                }                    
            }
    }
    
    @FXML private void btnSave() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{

    if(intId!=0 || TempintId==1){
    }
    else
    {
        Alreadyhave();
             

        TempintId = 1;
    }
    }
    
     @FXML
    private void onTableViewClick() throws IOException{
        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/Dictionary/List/ListclassFXML.fxml"));
        Parent root = fXMLLoader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    
    private void Search(String Keyword, String Sql) throws ClassNotFoundException, SQLException, IOException{
        Connection Con = DB.dictionary();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TempintId=0;
        
        preparedStatement = Con.prepareStatement(Sql);
        preparedStatement.setString(1, Keyword);
        resultSet = preparedStatement.executeQuery();
        
         if(resultSet.next()){
             checkdata = true;
            java.sql.Date date = resultSet.getDate(11);
            intId=resultSet.getInt(1);
            KeywordKhmer.setText(resultSet.getString(2));
            KeywordEnglish.setText(resultSet.getString(3));
            KeywordFrance.setText(resultSet.getString(4));
            Reading.setText(resultSet.getString(5));
            Wordclass.setText(resultSet.getString(6));
            Description.setText(resultSet.getString(7));
            Committee.setText(resultSet.getString(9));
            Skillblog.setText(resultSet.getString(10));
            TxtDate.setValue(date.toLocalDate());
            Addition.setText(resultSet.getString(12));
            Other.setText(resultSet.getString(13));
            
            InputStream is = resultSet.getBinaryStream(8); // read Blob as Stream
                    if(is!=null)
                    {
                        BufferedImage read = ImageIO.read(is);
                        Image image = SwingFXUtils.toFXImage(read, null);
                        Img.setImage(image); 
                    }
            return;
           }
         else{
             checkdata = false;
         }   
    }
    
    @FXML private void btnSearch() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
        if (KeywordEnglish.getText() != null)
        {
            Search(KeywordEnglish.getText(), "select * from dictionary.vocabulary where KeywordEnglish = ? ");
        }       
        if (KeywordKhmer.getText() != null)
        {
            Search(KeywordKhmer.getText(), "select * from dictionary.vocabulary where KeywordKhmer = ? ");
        }        
        if (KeywordFrance.getText() != null)
        {
            Search(KeywordFrance.getText(), "select * from dictionary.vocabulary where KeywordFrance = ? ");
        }     
        }

    @FXML private void btnSavechange() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
        Connection Con = DB.dictionary();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String Sql ="Update  dictionary.vocabulary Set KeywordKhmer=?, KeywordEnglish =? ,keywordFrance =?, Reading = ?"
                + ",WordClass = ?,Description = ?,Picture = ?,Committee = ?,SkillBLOG = ?,Date = ?,Addition = ?,Other = ? where ID = ?";
        preparedStatement = Con.prepareStatement(Sql);
        preparedStatement.setString(1, KeywordKhmer.getText());
        preparedStatement.setString(2, KeywordEnglish.getText());
        preparedStatement.setString(3, KeywordFrance.getText());
        preparedStatement.setString(4, Reading.getText());
        preparedStatement.setString(5, Wordclass.getText());
        preparedStatement.setString(6, Description.getText());
        
        if(flFullPathAndFileName == null )
                {
                    preparedStatement.setBinaryStream(7, null);
                }
                else
                {
                    FileInputStream fis = new FileInputStream(flFullPathAndFileName);
                    preparedStatement.setBinaryStream(7, fis,(int) flFullPathAndFileName.length());
                }
        
        preparedStatement.setString(8, Committee.getText());
        preparedStatement.setString(9, Skillblog.getText());
        
        if(TxtDate == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "not bl");
            alert.setHeaderText(null);
            alert.showAndWait();
            }
        else{
            preparedStatement.setString(10, TxtDate.getValue().toString());
        }
        
        preparedStatement.setString(11, Addition.getText());
        preparedStatement.setString(12, Other.getText());
        preparedStatement.setInt(13, intId);
        
        
        preparedStatement.executeUpdate();
        
        preparedStatement.close();
        Con.close();
        
    }
    
    @FXML private void BtnDelete() throws ClassNotFoundException, SQLException{
        Btnsave.setDisable(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.showAndWait();
        if(alert.getResult().equals(ButtonType.YES)){
            
        Connection connection = DB.dictionary();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String sql = "delete from dictionary.vocabulary where ID = ?";
        
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, intId);
       
        preparedStatement.execute();
        
        preparedStatement.close();
        connection.close();
        
        } else{
            
            return;
        }
        
        alert = new Alert(Alert.AlertType.INFORMATION, "Successfully");
        alert.setHeaderText(null);
        alert.showAndWait();


        
        
    }
    
    @FXML private void BtnReset(){
        
        System.exit(0);
        
    
    } 
    
    
    private void AddImage(ImageView imgv) throws IOException
    {
        FileChooser fileChooser = new FileChooser();
           
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG); 
        flFullPathAndFileName = fileChooser.showOpenDialog(null); //fileChooser.showOpenMultipleDialog(null); fileChooser.showSaveDialog(null);
        if(flFullPathAndFileName != null) // FullPathAndFileName = null if Cancel of Dialog is clicked.
        {
            
                BufferedImage bufferedImage = ImageIO.read(flFullPathAndFileName);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imgv.setImage(image);
            }
        
        else {imgv.setImage(null);}
    }
    @FXML 
    private void AddImageview() throws IOException{
        AddImage(Img);
    }
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
