/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TFIDF;

/**
 *
 * @author asus
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class tf_idf {
    public static void main(String[] args) throws IOException {tf_idf tfidf = new tf_idf();}
    public tf_idf () throws IOException {

//    
        
        String [] fiture = {"gurbenur", "dki", "nyebarin", "isu", "presiden", "duit", "mati", "menkes", "santuy", 
                            "larang", "masuk", "deblek", "beloon", "kemenkes", "lot", "anies", "basewedan", "salah", 
                            "dukung", "cocol", "jokowi", "lamban", "omong", "meda", "arang", "bacot", 
                            "panggung", "jikiwi", "tidur", "politikus","makan", "uang", "gerak"};
//
//        try {
            BufferedReader in = new BufferedReader( new FileReader("D:\\Aiy\\ImplementasiTextMining\\src\\data\\outputPreprocessing.csv"));
            HashMap<Integer, ArrayList<String>> dokument = new HashMap<Integer, ArrayList<String>>();  
//            String data = " ";
            String data = in.readLine();
         
            for(int i = 0; i < data.length(); i++){
                while((data != null)) {
//                while(((data = in.readLine()) != null)) {
                    data = in.readLine();
                    ArrayList<String> dokumentMeta = new ArrayList<>();
                    String kalimat = " ";
                    dokumentMeta.add(kalimat);
                    dokument.put(i, dokumentMeta);


        tfidf_method tfObject = new tfidf_method();
        /*
        Prosess Set Fiture
        */
        tfObject.setQuery(fiture);
        
        /*
        Proses Perhitungan TF
        */
        HashMap<Integer, ArrayList<Integer>> dokumentTF = new HashMap<Integer, ArrayList<Integer>>();
        System.out.println("Data TF : " + tfObject.tf(dokument));
        dokumentTF = tfObject.tf(dokument);
        
        HashMap<Integer, ArrayList<Integer>> dokumentDF = new HashMap<Integer, ArrayList<Integer>>();
        System.out.println("Data DF : " + tfObject.df(dokumentTF));
        dokumentDF = tfObject.df(dokumentTF);
        
        System.out.println("Data IDF : " + tfObject.idf(dokumentDF));
        ArrayList<Double> documentIdf = tfObject.idf(dokumentDF);
        
        System.out.println("Data TF_IDF : " + tfObject.tf_idf(tfObject.tf(dokument), documentIdf));
    }
            }
    }
}