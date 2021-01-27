/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KlasifikasiNaiveBayes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    // Proses Training 
    public static void main(String[] args) throws IOException, Exception {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("D:\\Aiy\\ImplementasiTextMining\\src\\data new\\data training\\TrainingBaru.csv")));
        long time = System.currentTimeMillis();
        int rows = 400;
        int columns = 2;
        String[][] TRAINING_DATA = new String[rows][columns];
        int q = 0;
        while (sc.hasNextLine()) {
            for (String[] TRAINING_DATA1 : TRAINING_DATA) {
                String[] line = sc.nextLine().trim().split(",");
                for (int j = 0; j < line.length; j++) {
                    TRAINING_DATA1[j] = line[j];
                    q++;
                }
            }
        }
        
        System.out.println("------------------------------------------------");
        System.out.println("Nama\t" + "\tSentimen\t" + "\tNon");
        System.out.println("\t" + "\tNegatif\t" + "\tSentimen Negatif");
        System.out.println("------------------------------------------------");
        
        // Get komentar            
        String[][] data = new String[TRAINING_DATA.length - 1][];
        IntStream.range(0, TRAINING_DATA.length - 1).forEach(i
                -> data[i] = TRAINING_DATA[i + 1][0].toString().toLowerCase().split(" "));
        
        // Get label
        String[] label = new String[TRAINING_DATA.length - 1];
        IntStream.range(0, TRAINING_DATA.length - 1).forEach(row -> label[row] = TRAINING_DATA[row + 1][1]);
        HashSet<String> vocab = new HashSet<String>();
        IntStream.range(0, data.length).forEach(row
                -> IntStream.range(0, data[row].length).forEach(column -> vocab.add(data[row][column])));
        handleCommandLine(new NaiveBayes(data, label, new ArrayList<String>(vocab)));
        time = System.currentTimeMillis()-time;
        System.out.println();
        System.out.println("Time:"+time/1000d+"s");        
    }
    
    // Proses Testing 
    @SuppressWarnings("empty-statement")
    static void handleCommandLine(NaiveBayes nb) throws IOException {
        System.out.println();
        Scanner sc = new Scanner(new BufferedReader(new FileReader("D:\\Aiy\\ImplementasiTextMining\\src\\data new\\data testing\\TestingBaru.csv")));
        int rows = 100;
        int columns = 2;
        String[][] DATA_TESTING = new String[rows][columns];
        int q = 0;
        while (sc.hasNextLine()) {
            for (String[] DATA_TESTING1 : DATA_TESTING) {
                String[] line = sc.nextLine().trim().split(",");
                for (int j = 0; j < line.length; j++) {
                    DATA_TESTING1[j] = line[j];
                }
            }
        }
        HashSet<String> nama1 = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            nama1.add(DATA_TESTING[i][0]);
        }
        
        // Confusion Matrix 
        String[] nama = nama1.toArray(new String[nama1.size()]);
        String[] values;
        double TP=0;
        double TN=0;
        double FN=0;
        double FP=0;
        double truePredik = 100;
        double falsePredik = 0;
        values = new String[DATA_TESTING.length];
        int[]jum_komN = new int[nama.length];
        int[]jum_komF = new int[nama.length];   
        for (int i = 0; i < rows; i++) {
            values = DATA_TESTING[i][1].split(" ");

            String komentar = nb.classify(values);
            for(q=0;q<nama.length;q++){
                if(DATA_TESTING[i][0].equals(nama[q])){
                    if(komentar.contains("Fitnah")){
                    jum_komF[q]++;
                    TP++;
                    }
                    if(komentar.contains("Nonfitnah")){
                    jum_komN[q]++;
                    TN++;
                    }
                }
            }
        }
        for(q=0;q<jum_komN.length;q++){
           System.out.println(nama[q] + "\t" + "\t" + jum_komF[q] + "\t\t" + jum_komN[q]);  
            
        } 
        if(TN > falsePredik){
            FN = TN - falsePredik ;
        }
        if(TP > truePredik){
            FP = TP - truePredik;
        }
        
        // Pengukuran Peforma 
        double accuracy = (TP+TN)/(TP+TN+FP+FN);
        double precision = TP /(TP+FP);
        double recall = TP/(FN+TP);
        double FMeasure = 2*(precision*recall)/(precision+recall);

        TP = TP - FN;
        TN = TN - FP;
        System.out.println("\nTP : "+TP+"\nTN : "+TN+"\nFP : "+FP+"\nFN : " +FN+"\nAccuracy : "+accuracy+"\nPrecision : "+precision+"\nRecall : "+recall+"\nF-Measure : "+FMeasure);
   
    }
}