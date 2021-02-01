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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import jsastrawi.cli.output.Output;
import jsastrawi.cli.output.BufferedOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jsastrawi.morphology.DefaultLemmatizer;
import jsastrawi.morphology.Lemmatizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;


/**
 *
 * @author empty
 */
public class tfidf_method {
    
    private double weightTerm;
    private int documentTotal;
    private String query [];
    
    public void setQuery(String query[]){
        this.query = query;
    }
    public String [] getQuery(){
        return this.query;
    }
    
    /**
     * Method for set number of document;
     * data type passing variable : integer;
     * You can use Method tf(HashMap<Integer, ArrayList<String>> dokument) for automatic set this value;
     * @param total 
     */
    public void setDocumentTotal(int total){
        this.documentTotal = total;
    }
    
    /**
     * Method for get number of document;
     * return data type : integer;
     * @return 
     */
    public int getDocumentTotal(){
        return this.documentTotal;
    }
    
    /**
     * Method untuk menghitung jumlah Term yang terdapat pada suatu dokumen;
     * data type Passing Variable : HashMap<Integer, ArrayList<String>> ;
     * Automatic set total dokument, setDocumentTotal(jmlDokumen);
     * @param dokument;
     */
    public  HashMap<Integer, ArrayList<Integer>>  tf(HashMap<Integer, ArrayList<String>> dokument) {
        String _query[] = this.query;
        
        ArrayList<String> docArray = new ArrayList<String>();
        HashMap<Integer, ArrayList<String>> hashmap = new HashMap<Integer, ArrayList<String>>();
        
        
        HashMap<Integer, ArrayList<Integer>> vectorDocument = new HashMap<Integer, ArrayList<Integer>>();
        
        hashmap = dokument;
        int nilai = 0;
        for(int i = 0; i < hashmap.size(); i++){
            docArray = hashmap.get(i);
            ArrayList<Integer> valueVector = new ArrayList<Integer>();
            for(int b = 0; b < _query.length; b++){
                for(int a = 0; a < docArray.size(); a++){
                    if(docArray.get(a).equals(_query[b])){
                        nilai+=1;   
                    }
                    //System.out.println(i + " " + docArray.get(a) + " " + _query[b] + " " + nilai);
                }
                valueVector.add(nilai);
                nilai = 0;
                vectorDocument.put(i, valueVector);
            }
            
        }
        
        this.setDocumentTotal(hashmap.size());
        return vectorDocument;
    }

    /**
     * 
     * Method untuk menghitung jumlah Term dokumen pada data
     * 
     */
    public HashMap<Integer, ArrayList<Integer>> df(HashMap<Integer, ArrayList<Integer>> dokument) {
        
        HashMap<Integer, ArrayList<Integer>> dokumentTf = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, ArrayList<Integer>> dokumentDf = new HashMap<Integer, ArrayList<Integer>>();
        
        dokumentTf = dokument;
        
        ArrayList<Integer> value = new ArrayList<Integer>();
        ArrayList<Integer> valueDf = new ArrayList<Integer>();
        
        int nilai = 0;
        int sizeIndex = this.query.length;
        for(int a = 0; a < sizeIndex; a++){
            nilai = 0;
            for(int i = 0; i < dokumentTf.size();i ++){
                value = dokumentTf.get(i);
                nilai += value.get(a);
            }
            valueDf.add(nilai);            
        }    
        dokumentDf.put(0, valueDf);
        
        return dokumentDf;
    }

    /**
     * Method untuk menghitung invers dokumen frekuensi 
     * formula : idf=1/df;
     * passing parameter data type : HashMap<Integer, ArrayList<Integer>> document;
     * return variable data type : ArrayList<Double>;
     * @param document
     * @return 
     */
    public ArrayList<Double> idf(HashMap<Integer, ArrayList<Integer>> document) {
        HashMap<Integer, ArrayList<Integer>> documentDf  = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<Integer> valueDf = new ArrayList<Integer>();
        ArrayList<Double> valueIdf = new ArrayList<Double>();
        
        documentDf = document;
        double hasilIdf = 0.0;
        
        for(int i = 0; i < documentDf.size(); i++){
            valueDf = documentDf.get(i);
            for(int a = 0; a < valueDf.size(); a++){
                double decimalValueDf = valueDf.get(a);
                hasilIdf = (1/decimalValueDf);
                if((Double.isNaN(hasilIdf)) || (Double.POSITIVE_INFINITY == hasilIdf) || (Double.NEGATIVE_INFINITY == hasilIdf)){
                    hasilIdf = 0;
                }
                valueIdf.add(hasilIdf);
            }
        }
        
        return valueIdf;
    }
    
    /**
     * Method untuk menghitung invers dokumen frekuensi 
     * formula : idf=log(N/df);
     * where : N = total of document, df = frequency of document;
     * passing parameter data type : HashMap<Integer, ArrayList<Integer>> document;
     * return variable data type : ArrayList<Double>;
     * @param document
     * @return 
     */
    public ArrayList<Double> idfLog(HashMap<Integer, ArrayList<Integer>> document) {
        HashMap<Integer, ArrayList<Integer>> documentDf  = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<Integer> valueDf = new ArrayList<Integer>();
        ArrayList<Double> valueIdf = new ArrayList<Double>();
        
        documentDf = document;
        double hasilIdf = 0.0;
        
        for(int i = 0; i < documentDf.size(); i++){
            valueDf = documentDf.get(i);
            for(int a = 0; a < valueDf.size(); a++){
                double decimalValueDf = valueDf.get(a);
                hasilIdf = Math.log((documentDf.size()/decimalValueDf));
                if((Double.isNaN(hasilIdf)) || (Double.POSITIVE_INFINITY == hasilIdf) || (Double.NEGATIVE_INFINITY == hasilIdf)){
                    hasilIdf = 0;
                }
                valueIdf.add(hasilIdf);
            }
        }
        
        return valueIdf;
    }

    /**
     * Method untuk menghitung bobot Term;
     * formula : weight = tf*idf;
     * passing variable data type : HashMap<Integer, ArrayList<Integer>> documentTf, ArrayList<Double> documentIdf;
     * return variable data type : HashMap<Integer, ArrayList<Double>>;
     * @param documentTf
     * @param documentIdf
     * @return 
     */
    public HashMap<Integer, ArrayList<Double>> tf_idf(HashMap<Integer, ArrayList<Integer>> documentTf, ArrayList<Double> documentIdf) {
        HashMap<Integer, ArrayList<Double>> resultTfIdf = new HashMap<Integer, ArrayList<Double>>();
        
        ArrayList<Integer> arrayListTf = new ArrayList<Integer>();
        
        this.weightTerm = 0.0;
        for(int i=0; i < documentTf.size(); i++){
            ArrayList<Double> arrayResultTfIdf = new ArrayList<Double>();
            arrayListTf = documentTf.get(i);
            for(int a=0; a < arrayListTf.size(); a++){
                double decimalValueDf = arrayListTf.get(a);
                weightTerm = decimalValueDf*documentIdf.get(a);
                arrayResultTfIdf.add(weightTerm);
            }
            resultTfIdf.put(i, arrayResultTfIdf);
        }
        
        return resultTfIdf;
    }
}
