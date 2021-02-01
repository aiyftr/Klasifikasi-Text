/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import jsastrawi.morphology.DefaultLemmatizer;
import jsastrawi.morphology.Lemmatizer;

/**
 *
 * @author asus
 */
public class Stemming {
    public ArrayList<String> resultStemming = new ArrayList<>();
     public static void main(String[] args) throws IOException{
     Stemming stemm = new Stemming();
     }
    public Stemming() throws IOException {
    CaseFolding caseFolding = new CaseFolding();
        //Jsastrawi lemmatizer membutuhkan
        //kamus kata dasar dalam bentuk <String>
        Set<String> dictionary = new HashSet<String>();
        //Memuat file kata dasar dari Jsastrawi
        InputStream in = Lemmatizer.class.getResourceAsStream("/root-words.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) {
            dictionary.add(line);
        }
        
        //Selesai melakukan setup Jsastrawi
        Lemmatizer lemmatizer = new DefaultLemmatizer(dictionary);
//        System.out.println(lemmatizer.lemmatize("memakan"));
        String completeWord ="";
        for (int a = 0; a < caseFolding.caseFoldingResult.size(); a++) {
            String kalimat = caseFolding.caseFoldingResult.get(a);
            String[] splitKalimat = kalimat.split(" ");
            for (int b = 0; b < splitKalimat.length; b++) {
                String stemWord = lemmatizer.lemmatize(splitKalimat[b]);
                completeWord += stemWord + " ";
//                System.out.println(stemWord);
                
            }
            resultStemming.add(completeWord);
            System.out.println(completeWord);
//            
            completeWord ="";
            
      }
   }
}
