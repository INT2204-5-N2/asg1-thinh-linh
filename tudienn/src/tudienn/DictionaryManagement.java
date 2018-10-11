/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudienn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
            br = new BufferedReader(new FileReader("D:\\GITHUB\\dictionary\\tudienn\\src\\tudienn\\E_V.txt"));
            String textInLine;
            while((textInLine = br.readLine()) != null){
                Word word = new Word();
                int index = textInLine.indexOf("<html>");
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
    public void dictionaryLookup( Dictionary d){
       Scanner input = new Scanner(System.in);
        System.out.println("Nhap tu can tra cuu: ");
       String s = input.nextLine();
       boolean check = false;
       for(Word w : d.getWords()){
           if(w.getWord_target().equals(s)){
               check = true;
               if(check) System.out.println( "tieng viet: "+w.getWord_explain());
               break;
           } 
        
    }
      
           if(!check) System.out.println("khong tim dc tu");
}
} 
