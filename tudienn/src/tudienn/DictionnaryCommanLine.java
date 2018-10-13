/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudienn;

import java.util.List;

/**
 *
 * @author thinhnguyen
 */
public class DictionnaryCommanLine {
    private static DictionaryManagement dictionaryManagement = new  DictionaryManagement();
 /**
  * Hàm hiển thị tất cả cá word của từ điển
  * @param dictionary 
  */
    public static void showAllWords(Dictionary dictionary){
        List<Word> ws = dictionary.getWords();
        System.out.println("No\t |English \t |Vietnamese");
        for(int i=0; i < ws.size(); i++){
            System.out.println( (i+1) +"\t |"+ ws.get(i).getWord_target() + "\t |" +ws.get(i).getWord_explain());
        }
//        for(Word w : ws)
//        {
//            System.out.println( "\t "+ w.getWord_target() +"\t "+w.getWord_explain());
//        }
    }
    
    public static void dictionaryBasic() {
        Dictionary d = dictionaryManagement.insertFromCommandline();
        showAllWords(d);
        
    }
    public static void dictionaryAdvanced(){
        Dictionary d = dictionaryManagement.insertFromFile();
        //showAllWords(d);
        String aString= dictionaryManagement.dictionaryLookup(d);
        System.out.println("tieng viet: "+aString);
        d = dictionaryManagement.AddWord(d);
    }
    public static void main(String[] args) {
        dictionaryAdvanced();
    }
}
