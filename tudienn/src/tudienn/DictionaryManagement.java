/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudienn;

import com.sun.speech.freetts.VoiceManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
/**
 * Class DictionaryManagement 
 * @author thinhnguyen
 */
public class DictionaryManagement {
    private int size ;
    
    
    
   
   
/**
 * Hàm insertFromCommandline() có chức năng nhập liệu: 
Nhập vào bàn phím số lượng từ vựng (Word). 
Format nhập dữ liệu từ điển Anh – Việt 
Dòng 1: Nhập từ tiếng Anh 
Dòng 2: Nhập giải thích sang tiếng Việt 
 * @return Dictionary 
 */   
    public Dictionary insertFromCommandline(){
         Scanner input = new Scanner(System.in);
        System.out.println("Nhap vao so luong tu! ");
        size = Integer.parseInt(input.nextLine());
       Dictionary dictionary = new Dictionary();
       List<Word> listWords= new ArrayList<Word>();
        for(int i=0; i<size; i++){
            System.out.println("tu thu " + i);
            Word word = new Word();
             String wordTaget = input.nextLine();
              String wordExplain = input.nextLine();
            word.setWord_target(wordTaget);
            word.setWord_explain(wordExplain);
            listWords.add(word);
        }
        dictionary.setWords(listWords);
        return dictionary;
 
    }
/**
 * Hàm insertFromFile() nhập dữ liệu từ điển từ tệp E_V.txt 
 * @return 
 */
    public Dictionary insertFromFile(){
        Dictionary d = new Dictionary();
        List<Word> listWords= new ArrayList<Word>();
        BufferedReader br = null;
        try{
             File file = new File("E_V.txt");
             
              
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
            String textInLine;
            while((textInLine = br.readLine()) != null){
                Word word = new Word();
                int index = textInLine.indexOf("<html>");
                //textInLine = tieng anh + <html> + giai nghia + <html>;
                 String target = textInLine.substring(0, index);
                 target = target.trim();
                 String explain = textInLine.substring(index);
                 
                 word.setWord_explain(explain);
                 word.setWord_target(target);
                 listWords.add(word);

                
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        d.setWords(listWords);
        return d;
    }
/**
 * hàm tra cứu từ điển bằng dòng lệnh
 * @param d 
 */    
    public Word dictionaryLookup(String s, Dictionary d){
      // Scanner input = new Scanner(System.in);
       // System.out.println("Nhap tu can tra cuu: ");
       
      // String s = input.nextLine();
       boolean check = false;
       for(Word w : d.getWords()){
           if(w.getWord_target().equals(s)){
               check = true;
               if(check) //System.out.println( "tieng viet: "+w.getWord_explain());
               
               return w;
               
           } 
        
    }
        
          return new Word("", "khong tim dc tu");
          //return null;
}
    public Dictionary AddWord(String wTarget, String wExplain,Dictionary d){
        Word w = new Word();
        
        //Scanner input = new Scanner(System.in);
        //String wTarget = input.nextLine();
        //String wExplain = input.nextLine();
        wExplain =  "<html><h1>"+ wExplain +"</h1><h2>"+wExplain+ "</h2></html>";
        w.setWord_explain(wExplain);
        w.setWord_target(wTarget);
        d.getWords().add(w);
        //ghi vao file
//        BufferedWriter bw = null;
//        FileWriter fw = null;
//        try {
//            File file = new File("D:\\GITHUB\\asg1-thinh-linh\\tudienn\\src\\tudienn\\E_V.txt");
//             fw = new FileWriter(file.getAbsoluteFile(), true);
//             bw = new BufferedWriter(fw);
//             bw.write("\n"+wTarget+wExplain);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//        try {
//            if(bw != null)
//                bw.close();
//            if(fw!=null)
//                fw.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
        return d;
        
        
        
    }
    public List<String> dictionarySearcher(Dictionary d ,String s){
        List<String> dsList = new ArrayList<String>();
        for(Word w : d.getWords()){
            if(w.getWord_target().indexOf(s) ==0 ){
                dsList.add(w.getWord_target());
            }
        }
        return dsList;
    }
     public void speech(String text){
        VoiceManager voiceManager = VoiceManager.getInstance();
        com.sun.speech.freetts.Voice sVoice = voiceManager.getVoice("kevin16");
        sVoice.allocate();
        sVoice.speak(text);
        sVoice.deallocate();
    }
    public Dictionary DelWord(String s, Dictionary d){
       Word w = this.dictionaryLookup(s, d);
        //if(w.getWord_explain().equals("khong tim dc tu")) return d;
        //else {
            d.getWords().remove(w);
            return d;
        }
    
    public Dictionary EditWord(String wE, String editWT, String editWE, Dictionary d){
        Word word = this.dictionaryLookup(wE, d);
        if(word.getWord_explain().equals("khong tim dc tu")) return d;
        else if(editWT == ""){
            for(Word w: d.getWords()){
                if(w.getWord_target().equals(wE)){
                    
                }
            }
        }
        return d;
    }
    /**
     * 
     * @param d 
     */
    public void dictionaryExportToFile(Dictionary d){
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            
            File file = new File("E_V.txt");
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF8"));
            //fw = new FileWriter(file.getAbsoluteFile());
           // bw = new BufferedWriter(fw);
            List<Word> wordsToFile = d.getWords();
             for(Word w: wordsToFile){
                 bw.write(w.getWord_target()+w.getWord_explain()+"\n");
             }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        try {
            if(bw != null)
                bw.close();
            if(fw!=null)
                fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
    
} 
