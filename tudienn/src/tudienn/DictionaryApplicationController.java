/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudienn;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import static tudienn.DictionnaryCommanLine.dictionarySearcher;

/**
 * FXML Controller class
 *
 * @author thinhnguyen
 */
public class DictionaryApplicationController extends Application{
   // private  Stage addWordWindow ;
    
   // private final FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("addWord.fxml"));
    private final FXMLLoader fXMLLoader1 = new FXMLLoader(getClass().getResource("DelWord.fxml"));
    
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private static Dictionary d;
    private static Word w = new Word("","nope") ;
    private static String sFromTextField ;
    private static String wE; // Word English tim dc, su dung cho void
    private static String wV; // word Explain tim duoc
   
    public void dictionaryAdvanced(){
         d = dictionaryManagement.insertFromFile();
         
        //showAllWords(d);
        //Word w = dictionaryManagement.dictionaryLookup(d);
        //if(w != null){ 
        //System.out.println("tieng viet: "+ w.getWord_explain());
        //dictionaryManagement.speech(w.getWord_target());
        //}
        //else System.out.println( "khong tim dc");
        //List<String> a = dictionarySearcher(d, "tra");
        //for(String s: a){
        //    System.out.println(s);
        //}
        
        //d = dictionaryManagement.AddWord(d);
    }
    /**
     * Initializes the controller class.
     */
    @FXML 
    private TextField wSearch;
    @FXML
    private Button search;
    @FXML
    private Button addWord;
    @FXML
    private Button editWord ;
    @FXML
    private Button delWord;
    @FXML
    private Button voice;
    @FXML 
    private WebView webView;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField addWE;
    @FXML
    private TextField addWV;
    @FXML
    private Button Add;
    @FXML
    private TextField delW;
    @FXML
    private Button del;
    
   
/**
 * xử lí click button Search
 * @param event 
 */   
    public void clickSearch(ActionEvent event){
        sFromTextField = wSearch.getText();
        w = dictionaryManagement.dictionaryLookup(sFromTextField, d);
        wE = w.getWord_target();
        String html = w.getWord_explain();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(html);
        
        List<String> listWord = dictionaryManagement.dictionarySearcher(d, sFromTextField);
        listView.getItems().clear();
        for(String w: listWord){
            listView.getItems().add(w);
        }
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
             //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //WebEngine webEngine = webView.getEngine();
                wE = dictionaryManagement.dictionaryLookup(newValue, d).getWord_target();
                webEngine.loadContent(dictionaryManagement.dictionaryLookup(newValue, d).getWord_explain());
            }
            
        });
            
        
        
        
       // System.out.print(s);
    }
    /**
     * phat am 
     * @param event 
     */
    public void clickVoice(ActionEvent event){
        dictionaryManagement.speech(wE);
    }
    /**
     * 
     * @param event 
     */
    public void clickAddWord(ActionEvent event){
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("addWord.fxml"));
            Parent root1 = (Parent)fXMLLoader.load();
           // addWordWindow = new Stage();
           Stage addWordWindow = new Stage();
           addWordWindow = new Stage();
            addWordWindow.setTitle("THÊM TỪ");
            addWordWindow.setScene(new Scene(root1));
    
           // stage.initOwner();
           addWordWindow.initModality(Modality.APPLICATION_MODAL);
           addWordWindow.show();
            
          //  Stage stage = (Stage)addWord.getScene().getWindow();
         //   addWordWindow.initOwner(stage);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }    
       // this.clickAdd2(event);
    }
    /**
     * 
     * @param event 
     */
    public void clickAdd2(ActionEvent event){
        String eW = addWE.getText();
        String eV = addWV.getText();
        if(eW.length() ==0 || eV.length() ==0){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setContentText("BẠN CHƯA NHẬP TỪ MỚI HOẶC GIẢI NGHĨA!!");
           
           alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("thêm từ: " + eW + "vào từ điển???");
           // alert.show();
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK){
            d = dictionaryManagement.AddWord(eW, eV, d);
            Stage stage = (Stage)Add.getScene().getWindow();
            stage.close();
            }
            else if(option.get() == ButtonType.CANCEL){
                alert.close();
            }
        }
        
        //addWordWindow.close();
        //Add.setCancelButton(true);
        
    }
    /**
     * 
     * @param event 
     */
   public void clickDelWord(ActionEvent event){
       try {
           FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("DelWord.fxml"));
            Parent root1 = (Parent)fXMLLoader.load();
           // addWordWindow = new Stage();
           Stage addWordWindow = new Stage();
           addWordWindow = new Stage();
            addWordWindow.setTitle("XÓA TỪ");
            addWordWindow.setScene(new Scene(root1));
    
           // stage.initOwner();
           addWordWindow.initModality(Modality.APPLICATION_MODAL);
           addWordWindow.show();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
   }
   /**
    * 
    * @param event 
    */
   public void clickDel2(ActionEvent event){
       String s = delW.getText();
       Word wD = dictionaryManagement.dictionaryLookup(s, d);
       if(wD.getWord_explain().equals("khong tim dc tu")){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setContentText("Không tìm thấy từ: " + s);
           
           alert.show();
           
       }
       else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("xóa từ: " + s + " ra khỏi từ điển???");
           // alert.show();
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK){
            d = dictionaryManagement.DelWord(s, d);
            Stage stage = (Stage)del.getScene().getWindow();
            stage.close();
            }
            else if(option.get() == ButtonType.CANCEL){
                alert.close();
            }
            
            
          
       }
      // else d = dictionaryManagement.DelWord(s, d);
   }
   public void clickEditWord(ActionEvent event){
       
   } 
   public void clickEdit(ActionEvent event){
       
   }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        DictionaryApplicationController dac = new DictionaryApplicationController();
        dac.dictionaryAdvanced();
        launch(args);
    }
    @Override
    public void start( Stage primaryStage) throws IOException{
        
       try{
           Parent root = FXMLLoader.load(getClass().getResource("DictionaryApplication.fxml"));
           Scene scene = new Scene(root);
           primaryStage.setScene(scene);
           primaryStage.setTitle("Thinh&Linh Dictionary");
           primaryStage.show();
           
           
            // bat su kien dau X
           primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
               @Override
               public void handle(WindowEvent we){
                   System.out.println("window is closing");
                   dictionaryManagement.dictionaryExportToFile(d);
               }
           });
           
           
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
           
    }
}
