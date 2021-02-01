/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TFIDF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 /**
 *
 * @author asus
 */
public class TFIDF {
    /**
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
    // Menghitung nlai TF
    public double tf(String term) throws FileNotFoundException {
        double result = 0;
        double size = -1;
        String csvFile = "D:\\Aiy\\ImplementasiTextMining\\src\\data new\\outputPreproNew.csv";
        File iFile = new File(csvFile);
        Scanner sCsv = new Scanner(iFile);

        while(sCsv.hasNext()){
            String splitComma[] = sCsv.nextLine().split(",");
            String splitDoc[] = splitComma[1].split(" ");
            
            for(int x=0; x< splitDoc.length; x++){
                if(splitDoc[x].equalsIgnoreCase(term)){
                    result++;
                }
                
            } 
            size++;
        }
        return result;
    }

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    
    //Memberi bobot atau menghitung value IDF
    public double idf(String term) throws FileNotFoundException {
        int result = 0;
        double size = - 1;
        double hasil = 0;
        double akhir = 0;
        String csvFile = "D:\\Aiy\\ImplementasiTextMining\\src\\data new\\outputPreproNew.csv";
        File iFile = new File(csvFile);
        Scanner sCsv = new Scanner(iFile);

        while(sCsv.hasNext()){
            String splitComma[] = sCsv.nextLine().split(",");
            String splitDoc[] = splitComma[1].split(" ");
            
            for(int x=0; x< splitDoc.length; x++){
                if(splitDoc[x].equalsIgnoreCase(term)){
                    result++;
                    break;
                }
                
            }
            
            size++;
            
        }
        hasil = size / result;
        return Math.log10(hasil);
}

        
        
    

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
//    public double tfIdf(List<String> doc, BufferedReader br, String term) {
//        return tf(doc, term) * idf(br, term);
//
//    }
    // Mengitung bobot TF-IDF
    public static void main(String[] args) throws FileNotFoundException {
        TFIDF calculator = new TFIDF();
        BufferedReader br = null;
        String csvFile = "D:\\Aiy\\ImplementasiTextMining\\src\\data new\\outputPreproNew.csv";
        File iFile = new File(csvFile);
        ArrayList<String> queryList = new ArrayList<>();
        Scanner sCsv = new Scanner(iFile);
        String line = "";
        
        try{
            br = new BufferedReader(new FileReader(csvFile));
            int a = 0;
            File file = new File("D:\\Aiy\\ImplementasiTextMining\\src\\data new\\query.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                queryList.add(scan.next());
            }
            while (sCsv.hasNext()) {
                line = sCsv.next();
                String split[] = line.split(",");
                int count = 0;
                for (int x = 0; x < split.length; x++) {
                    for (int y = 0; y < queryList.size(); y++){
                        if (split[x].equals(queryList.get(y))) {
                            System.out.println("-" + split[x]);
                            System.out.println("TF = " + calculator.tf(split[x]));
                            System.out.println("IDF = " + calculator.idf(split[x]));
                            System.out.println("TF-IDF = " + calculator.idf(split[x]) * calculator.tf(split[x]));
                            System.out.println();
                            count++;
                        }
                    }
                }
                if (count == 0) {
//                    System.out.println("Tidak ada query terpilih\n");
                }
        }


    }catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

  
}