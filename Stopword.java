/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Asus
 */
public class Stopword {
    public ArrayList<String> resultStopword = new ArrayList<>();
    public static void main(String[] args) throws IOException{
     Stopword stopword = new Stopword();
    }
    public Stopword() throws IOException {
        Stemming stem = new Stemming();
        Cleansing clean = new Cleansing();
        ArrayList<String> temp = new ArrayList();
        // print output to csv
        PrintWriter pw = new PrintWriter("D:\\Aiy\\ImplementasiTextMining\\src\\data new\\outputPreproNew.csv");
        // kamus stopword
        File fr = new File("D:\\Aiy\\ImplementasiTextMining\\src\\data new\\stopWord.txt");
        Scanner br = new Scanner(fr);
        while (br.hasNext()) {
            temp.add(br.next());
        }
        for (int x = 0; x < stem.resultStemming.size(); x++){
            String raw = stem.resultStemming.get(x);
            String[] split = raw.split(" ");
            String text;
            for (int a = 0; a < split.length; a++){
                String kata = split[a];
                for (int z = 0; z < temp.size(); z++){
                    if(kata.equals(temp.get(z))){
                    split[a] = "";
                    }
                }
            }
            
                pw.print(clean.list.get(x) + ",");
        for (int y = 0; y < split.length -1; y++){
            if (split[y].isEmpty()){
                
            } else {
                pw.print(split[y] + " ");
                
            }
        }
        pw.println();
        
            
        }
        pw.close();
    }
}
