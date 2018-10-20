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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * FXML Controller class
 *
 * @author thinh&linh 
 */
public class DictionaryApplicationController extends Application implements  Initializable{
   
   // private final FXMLLoader fXMLLoader1 = new FXMLLoader(getClass().getResource("DelWord.fxml"));
    
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private static Dictionary d;
    private static Word w = new Word("","nope") ;
  
    private static String wE; // Word English tim dc, su dung cho void
    private static String wV; // word Explain tim duoc
   
    public void dictionaryAdvanced(){
         d = dictionaryManagement.insertFromFile();
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
    @FXML
    private TextField wEdit;
    @FXML
    private TextField editedTarget;
    @FXML
    private TextField editedExplain;
    @FXML 
    private Button edit;
    
    
    
 /**
  * bam enter == click seach
  * @param event 
  */  
public void displaySuggest(ActionEvent event){
    wSearch.setOnKeyPressed(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
            if(event.getCode() == KeyCode.ENTER){
                String sFromTextField = wSearch.getText();
                sFromTextField =sFromTextField.trim();
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
            }
        }
    });
 } 
/**
 * xử lí click button Search
 * @param event 
 */ 
    public void clickSearch(ActionEvent event){
        String sFromTextField = wSearch.getText();
        sFromTextField =sFromTextField.trim();
        
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
            
        
        
        
       
    }
    /**
     * phat am 
     * @param event 
     */
    public void clickVoice(ActionEvent event){
        if(wSearch.getText().length() ==0){
            dictionaryManagement.speech("Nope");
        }
        else dictionaryManagement.speech(wE);
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
          // addWordWindow = new Stage();
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
        eW = eW.trim();
        String eV = addWV.getText();
        eV= eV.trim();
        if(eW.length() ==0 || eV.length() ==0){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setContentText("BẠN CHƯA NHẬP TỪ MỚI HOẶC GIẢI NGHĨA!!");
           
           alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("thêm từ: " + eW + " vào từ điển???");
            
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
        
        
        
    }
    /**
     * 
     * @param event 
     */
   public void clickDelWord(ActionEvent event){
       try {
           FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("DelWord.fxml"));
            Parent root1 = (Parent)fXMLLoader.load();
           
           Stage delWordWindow = new Stage();
          
           delWordWindow.setTitle("XÓA TỪ");
           delWordWindow.setScene(new Scene(root1));
    
           
           delWordWindow.initModality(Modality.APPLICATION_MODAL);
           delWordWindow.show();
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
       s=s.trim();
       Word wD = dictionaryManagement.dictionaryLookup(s, d);
       if(wD.getWord_explain().equals("khong tim dc tu")){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setContentText("Không tìm thấy từ: " + s);
           
           alert.show();
           
       }
       else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("xóa từ: " + s + " ra khỏi từ điển???");
           
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
     
   }
   public void clickEditWord(ActionEvent event){
       try {
           FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("EditWord.fxml"));
            Parent root1 = (Parent)fXMLLoader.load();
           
           Stage editWordWindow = new Stage();
           
           editWordWindow.setTitle("SỬA TỪ");
           editWordWindow.setScene(new Scene(root1));
    
          
           editWordWindow.initModality(Modality.APPLICATION_MODAL);
           editWordWindow.show();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
   } 
    public void clickEdit(ActionEvent event){
       String wEdit_ = wEdit.getText();
       wEdit_ = wEdit_.trim();
       
       String editedTarget_ = editedTarget.getText();
       editedTarget_ = editedTarget_.trim();
       
       String editedExplain_ = editedExplain.getText();
       editedExplain_ = editedExplain_.trim();
       
       
       if(wEdit_.length()==0){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setContentText("Bạn phải nhập từ cần sửa vào!!!");
           
           alert.show();
           
       }
       else{
           Word wD = dictionaryManagement.dictionaryLookup(wEdit_, d);
           
            if(wD.getWord_explain().equals("khong tim dc tu")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("từ bạn muốn sửa không có trong từ điển!!!");
           
                alert.show();
            }
            else if(editedTarget_.length() == 0 && editedExplain_.length()==0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Hãy sửa chính tả hoặc sửa nghĩa của từ vừa nhập!!!");
           
                    alert.show();
                    }
            else if(editedTarget_.length() != 0 && editedExplain_.length()==0){ // sửa chính tả
            
            
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("sửa: " +wEdit_+ " -----> "+ editedTarget_ );
             
                    Optional<ButtonType> option = alert.showAndWait();
            
                    if(option.get() == ButtonType.OK){
                        
                        
                        d = dictionaryManagement.AddWord(editedTarget_, wD.getWord_explain(), d);
                        d = dictionaryManagement.DelWord(wEdit_, d);
            
                        Stage stage = (Stage)edit.getScene().getWindow();
                        stage.close();
                        }
                    else if(option.get() == ButtonType.CANCEL){
                            alert.close();
            
                            }   
        
                    }
            else if(editedTarget_.length() == 0 && editedExplain_.length()!=0){ // sửa nghĩa
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("sửa nghĩa của: " + wEdit_ + "   --->   "+ editedExplain_ );
             
                    Optional<ButtonType> option = alert.showAndWait();
            
                    if(option.get() == ButtonType.OK){
            
                        
                        d = dictionaryManagement.AddWord(wEdit_,editedExplain_ , d);
                        d = dictionaryManagement.DelWord(wEdit_, d); // vì từ mới được thêm vào cuối file nên nó sẽ ko xóa từ vừa sửa 
            
                        Stage stage = (Stage)edit.getScene().getWindow();
                        stage.close();
                        }
                    else if(option.get() == ButtonType.CANCEL){
                            alert.close();
            
                            }
                    }
            else if(editedTarget_.length() != 0 && editedExplain_.length()!=0){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("sửa: " + wEdit_ +"???");
             
                    Optional<ButtonType> option = alert.showAndWait();
            
                    if(option.get() == ButtonType.OK){
            
                        
                        d = dictionaryManagement.AddWord(editedTarget_,editedExplain_ , d);
                        d = dictionaryManagement.DelWord(wEdit_, d); // vì từ mới được thêm vào cuối file nên nó sẽ ko xóa từ vừa sửa 
            
                        Stage stage = (Stage)edit.getScene().getWindow();
                        stage.close();
                        }
                    else if(option.get() == ButtonType.CANCEL){
                            alert.close();
            
                            }
            }
            
      
       
       
   }
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
                   //System.out.println("window is closing");
                   dictionaryManagement.dictionaryExportToFile(d);
               }
           });
           
           
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
           
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //List<String> listWord = dictionaryManagement.dictionarySearcher(d, wSearch.getText());
       //TextFields = 
    }
}
