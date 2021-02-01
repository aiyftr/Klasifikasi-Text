/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ngrams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Trigrams {
    public ArrayList<String> Bigram = new ArrayList();


    public static void main(String[] args) {
		
        Trigrams bg = new Trigrams();
    
    }
        public Trigrams(){
            String file = "D:\\Aiy\\ImplementasiTextMining\\src\\data new\\outputToNgram.csv";
            BufferedReader br = null;
            String line = "";
            try{
                br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                    
                
                    ArrayList<String> words = Ngrams.sanitiseToWords(line);
                    // size 3 trigram 
                    int[] sizes = new int[] {3};
                    int n = sizes.length;
		
                         for(int i = 0; i < n; i++) {
                            ArrayList<String> ngrams = Ngrams.ngrams(words, sizes[i]);
                            int c = ngrams.size();
                            int last = c - 1;
			
                            String message = "Size: "+ sizes[i] +" Returns: "+ c +" (";
			
                                for(int j = 0 ; j <= last; j++) {
				message +="'"+ ngrams.get(j) +"'";
				
                                    if(j != last) {
					message += ", ";
                                    }
                                }
			
			message += ")";
			Bigram.add(message);
			System.out.println(message);
                        }
                
                }
                    
	}catch (FileNotFoundException e) {
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
