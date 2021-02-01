/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Asus
 */
public class CaseFolding {
    
    public ArrayList<String> caseFoldingResult = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CaseFolding cf = new CaseFolding();
    }
    // Uppercase to Lowercase
    public CaseFolding() {
        Cleansing cleansing = new Cleansing();
        // lopping upparecase to lowercase
        for (int a = 0; a < cleansing.cleansingResult.size(); a++) {
            String toLower = cleansing.cleansingResult.get(a);
            caseFoldingResult.add(toLower.toLowerCase());
            System.out.println(toLower.toLowerCase());         
        }
        
         
    }       
    
}
