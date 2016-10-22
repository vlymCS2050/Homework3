/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencycounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

/**
 *
 * @author Vicky Lym, October 19, 2016
 */
public class FrequencyCounter {

    // Do not instantiate.
    private FrequencyCounter() { }

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     *
     * @param args the command-line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);

        int minlen1 = Integer.parseInt(args[0]);
        int minlen8 = Integer.parseInt(args[1]);
        int minlen10 = Integer.parseInt(args[2]);
        
        int distinct = 0, words = 0;

        ST<String, Integer> st = new ST<String, Integer>();
        ST<String, Integer> stM1 = new ST<String, Integer>();
        ST<String, Integer> stM8 = new ST<String, Integer>();
        ST<String, Integer> stM10 = new ST<String, Integer>();
        int getters = 0;
        int putters = 0;

        String key;
        int keyleng;
     
        String fileName="src/tale.txt";
        // String fileName="src/tinyTale.txt";
        
        String lastSTM1 = "";
        int priorSTM1 = 0;    
        String lastSTM8 = "";
        int priorSTM8 = 0;
        String lastSTM10 = "";
        int priorSTM10 = 0;
        int wordCnt = 0;
        
        //Instantiate the BufferedReader Class
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = in.readLine()) != null)
                {
                    String[] pieces = line.split("\\s+");
                    for (String pieceKey : pieces) {
                        words++;
                    if (pieceKey.length() < minlen1) {
                        wordCnt ++;
                    }    
                    if (pieceKey.length() >= minlen1) {
                        if (st.contains(pieceKey)) {
                            getters ++;
                            putters ++;
                            
                            st.put(pieceKey, st.get(pieceKey) + 1);
                        }
                        else {
                            st.put(pieceKey, 1);
                            putters ++;
                            distinct++;
                        }
                    }
                    if (pieceKey.length() < minlen1) continue;
                    if (!stM1.contains(pieceKey)) {
                        stM1.put(pieceKey, 1);
                        priorSTM1 ++;
                        lastSTM1 = pieceKey;
                    }
                    else {
                        stM1.put(pieceKey, stM1.get(pieceKey) + 1);
                        priorSTM1 ++;
                        lastSTM1 = pieceKey;
                    }  
                
                    if (pieceKey.length() < minlen8) continue;
                    if (!stM8.contains(pieceKey)) {
                        stM8.put(pieceKey, 1);
                        priorSTM8 ++;
                        lastSTM8 = pieceKey;
                    }
                    else    {                     
                        stM8.put(pieceKey, stM8.get(pieceKey) + 1);
                        priorSTM8 ++;
                        lastSTM8 = pieceKey;
                    } 

                    if (pieceKey.length() < minlen10) continue; 
                    if (!stM10.contains(pieceKey)) {
                        stM10.put(pieceKey, 1);
                        priorSTM10 ++;
                        lastSTM10 = pieceKey;
                    }
                    else {
                        stM10.put(pieceKey, stM10.get(pieceKey) + 1);
                        priorSTM10 ++;
                        lastSTM10 = pieceKey;
                    }
                }
            }   // find a key with the highest frequency count
            String max = "";
            st.put(max, 0);
            putters ++;
            for (String word : st.keys()) {
                getters ++;
                getters ++;
                    
                if (st.get(word) > st.get(max))
                    max = word;
            }   // System.out.println(max + " " + st.get(max));
            System.out.println("Max word is " + max + "    Max Count " + st.get(max) + "\n");
            System.out.println("distinct = " + distinct);
            System.out.println("words    = " + words + "\n");
            System.out.println("Calculation for Putters is # Words + one for MAX - Words < minlen");
            System.out.println("Calculation for Getters is # Words + 1 + # Distinct Words + 1");
            System.out.println("Putters " + putters + " Getters " + getters);
            System.out.println("Number of Words < minlen  " + wordCnt + "\n");
            
            String maxM1 = "";
            stM1.put(maxM1, 0);
            for (String word : stM1.keys())
               if (stM1.get(word) > stM1.get(maxM1))
                    maxM1 = word;
            System.out.println("Length of 1 Cutoff Max Word " + maxM1 + " " + stM1.get(maxM1));
            
            String maxM8 = "";
            stM8.put(maxM8, 0);
            for (String word : stM8.keys())
                if (stM8.get(word) > stM8.get(maxM8))
                    maxM8 = word;
            System.out.println("Length of 8 Cutoff Max Word " + maxM8 + " " + stM8.get(maxM8));
            
            String maxM10 = "";
            stM10.put(maxM10, 0);
            for (String word : stM10.keys())
                if (stM10.get(word) > stM10.get(maxM10))
                    maxM10 = word;
            System.out.println("Length of 10 Cutoff Max Word " + maxM10 + " " + stM10.get(maxM10) +"\n");
            
            priorSTM1 --;
            priorSTM8 --;
            priorSTM10 --;
            System.out.println("Length 1 Prior Word Count " + priorSTM1 + " Last Word " + lastSTM1);
            System.out.println("Length 8 Prior Word Count " + priorSTM8 + " Last Word " + lastSTM8);
            System.out.println("Length 10 Prior Word Count " + priorSTM10 + " Last Word " + lastSTM10 + "\n");
            
            in.close();
        }
        catch (Exception e)
        {
           System.err.format("Exception occurred trying to read '%s': " + e.getMessage(), fileName);
           e.printStackTrace();
           // return null;
        } 
    }
}
