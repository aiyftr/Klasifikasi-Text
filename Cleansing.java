/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.io.FileNotFoundException;       
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 *
 * @author Asus
 */
public class Cleansing {
    public ArrayList<String> cleansingResult = new ArrayList();
    public ArrayList<String> list = new ArrayList();
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cleansing clean = new Cleansing();
    }
    
    // Constructor 
    public Cleansing(){
        // File csv (dataset)
        String file = "D:\\Aiy\\ImplementasiTextMining\\src\\data new\\datasetNew.csv";
         BufferedReader br = null;
        String line = "";
        
        try{
            br = new BufferedReader(new FileReader(file));
            int counter = 0;
            String rawSentence = "";
            while ((line = br.readLine()) != null) {
        
    
       if (counter == 0){
           counter++;
       }else{
               String split [] = line.split(",");
               int totalKarakterNo = split[0].length();
               int totalKarakterKelas = split[1].length();
               int total = totalKarakterNo + totalKarakterKelas + 2;
               StringBuilder sb = new StringBuilder(line);
               sb.replace(0, total, "");
               rawSentence = sb.toString();
               
               // menghilangkan spesial karakter / karakter selain hurf
               StringTokenizer st = new StringTokenizer (rawSentence, "1234567890`~!@#$%^&*()-_+={}[]|;:'<>,.?/\"");
               
               String completeSentence ="";
               while (st.hasMoreElements()){
                   completeSentence += st.nextToken() + " ";
               }
               System.out.println(completeSentence.toLowerCase());
               
               cleansingResult.add(completeSentence);
               list.add(split[1]);
               
               }
        }
    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
  }

       
        
            
            
     
       
    
    
  
    

