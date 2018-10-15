/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudienn;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import static tudienn.DictionnaryCommanLine.dictionarySearcher;

/**
 * FXML Controller class
 *
 * @author thinhnguyen
 */
public class DictionaryApplicationController extends Application{
    
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private static Dictionary d;
    private static Word w = new Word("","nope" ) ;
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
    public void ShowList(ActionEvent event){
       // listView = dictionaryManagement.
    } 
    public void clickVoice(ActionEvent event){
        dictionaryManagement.speech(wE);
    }
    public void clickAddWord(ActionEvent event){
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("addWord.fxml"));
            Parent root1 = (Parent)fXMLLoader.load();
            Stage stage = new Stage();
            stage.setTitle("THÊM TỪ");
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.WINDOW_MODAL);
           // stage.initOwner();
            stage.show();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }    
 
    }
    public void clickAdd2(ActionEvent event){
        String eW = addWE.getText();
        String eV = addWE.getText();
        d = dictionaryManagement.AddWord(eV, eV, d);
        
    }
    
    public static void main(String[] args) {
        DictionaryApplicationController dac = new DictionaryApplicationController();
        dac.dictionaryAdvanced();
        launch(args);
    }
    @Override
    public void start( Stage primaryStage){
       try{
           Parent root = FXMLLoader.load(getClass().getResource("DictionaryApplication.fxml"));
           Scene scene = new Scene(root);
           primaryStage.setScene(scene);
           primaryStage.setTitle("Dictionary by NGUYEN DINH THINH");
           primaryStage.show();
           
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
           
    }
}
