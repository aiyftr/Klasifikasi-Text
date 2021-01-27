/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KlasifikasiNaiveBayes;

import java.util.ArrayList;
import java.util.stream.IntStream;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class NaiveBayes {
    private double priorProb;
    private RealMatrix fitProb, nonProb;
    private ArrayList<String> vocab = null;
    public NaiveBayes(String[][] data, String[] label, ArrayList<String> vocab) throws Exception{
        this.vocab = vocab;
        double[][] probArray = new double[data.length][];
        IntStream.range(0, data.length).forEach(i -> probArray[i] = mapDocToVocab(data[i]));
        calcPriorProb(label).calcCondProbs(MatrixUtils.createRealMatrix(probArray), label);
    }
    
    // method penentuan kelas
    public String classify(String[] docArray){
        String komentar = "Nonfitnah";
        RealMatrix doc = MatrixUtils.createRowRealMatrix(mapDocToVocab(docArray));
        double fLogSums = Math.log(priorProb) + doc.multiply(fitProb.transpose()).getData()[0][0];
        double nLogSums = Math.log(1- priorProb) + doc.multiply(nonProb.transpose()).getData()[0][0];
        if (fLogSums > nLogSums) komentar = "Fitnah";
        return komentar;    
    }  
    
    // method untuk menghitung kalkulasi dari (calcPriorProb)
    private NaiveBayes calcPriorProb(String[] label) {
        // menhitung probabilitas kelas 
        int sum = 0;
        for (int i=0; i<label.length; i++) 
            if (label[i].equals("Fitnah")) sum +=1;
        priorProb = sum/(double) label.length;
        return this;             
    }
    
    
    //method yang akan digunakan untuk menghitung tahap conditional probability
    private NaiveBayes calcCondProbs(RealMatrix prob, String[] label){
        //peluang sebuah word/vocab masuk ke dalam kelas fitnah
        RealMatrix nProbNum = MatrixUtils.createRowRealMatrix(new double [vocab.size()]);
        // size () = untuk mencari panjang dari ArrayList (word yang akan muncul)
        for (int i=0; i< vocab.size(); i++) nProbNum.setEntry(0, i, 1.0);
        //peluang sebuah word masuk ke dalam kelas nonfitnah
        RealMatrix fProbNum = MatrixUtils.createRowRealMatrix(new double [vocab.size()]);
        for (int i=0; i< vocab.size(); i++) fProbNum.setEntry(0, i, 1.0);
        double nProbDenom = vocab.size();
        double fProbDenom = vocab.size();
        
        for (int i =0; i<label.length; i++){
            if (label[i].equals("Fitnah")){
                fProbNum = fProbNum.add(prob.getRowMatrix(i));
                for (int j =0; j<prob.getData()[0].length; j++) fProbDenom += prob.getEntry(i, j);   
            }
            else {
                nProbNum = nProbNum.add(prob.getRowMatrix(i));
                for (int j =0; j<prob.getData()[0].length; j++) nProbDenom += prob.getEntry(i, j); 
            }
        }
        fitProb = log(fProbNum.scalarMultiply(1/fProbDenom));
        nonProb = log(nProbNum.scalarMultiply(1/nProbDenom));
        return this;
    }
    private RealMatrix log(RealMatrix matrix){
        double[] returnData = new double[matrix.getData()[0].length];
        IntStream.range(0, returnData.length).forEach(j -> returnData[j] = Math.log(matrix.getData()[0][j]));
        return MatrixUtils.createRowRealMatrix(returnData);
    }
    
    // Cmap = memilih atau menentukan probablitas kelas yang paling tingi dalam melakukan klasifikasi 
    //        berdasarkan perhitungan kelas masing-masing
    private double[] mapDocToVocab(String[] doc){
        double[] mappedDoc = new double[vocab.size()];
        IntStream.range(0, vocab.size()).forEach(i -> mappedDoc[i] = 0);
        for(int i=0; i<doc.length; i++)
            for(int j=0; j<vocab.size(); j++)
                if (doc[i].equalsIgnoreCase(vocab.get(j))) mappedDoc[j] +=1;
        return mappedDoc;
    }
}
