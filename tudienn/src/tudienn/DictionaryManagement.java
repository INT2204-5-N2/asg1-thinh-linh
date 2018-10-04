/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudienn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author thinhnguyen
 */
public class DictionaryManagement {
    private int size ;
    
    
   
   
    
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
//    public static void main(String[] args) {
//        DictionaryManagement a = new DictionaryManagement();
//        Dictionary d = a.insertFromCommandline();
//        for(Word word : d.getWords()){
//             System.out.println(word.getWord_explain());
//             System.out.println(word.getWord_target());
//        }
//       
//                
//    }
}
