/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freqcnt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

/**
 *
 * @author Vicky Lym, October 19, 2016
 */
public class FreqCnt {

    // Do not instantiate.
    private FreqCnt() { }

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

        int minlen = Integer.parseInt(args[0]);
        
        int distinct = 0, words = 0;
        ST<String, Integer> st = new ST<String, Integer>();

        int getters = 0;
        int putters = 0;
        String lastWord = "";
        int priorCnt = 0;
        String[] maxWord = new String[10];
        int[] maxCntr = new int[10];
        int maxWordLen = 0;
        int cntM = 0;
        int diffOfLen = 0;
        int loopCtr = 0;
        int wordCnt = 0;

        String key;
        int keyleng;
     
        String fileName="src/tale.txt";
        int wordLen = 0;
        String wordTxt = "";
        // String fileName="src/tinyTale.txt";
        
        //Instantiate the BufferedReader Class
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = in.readLine()) != null)
                {
                    String[] pieces = line.split("\\s+");
                
                    for (String pieceKey : pieces) {
                        words++;
                        
                    if (pieceKey.length() < minlen) {
                        wordCnt ++;
                        continue;
                    }    
                    if (st.contains(pieceKey)) {
                        getters ++;
                        putters ++;
                        lastWord = pieceKey;
                        priorCnt ++;
                        
                        st.put(pieceKey, st.get(pieceKey) + 1);
                    }
                    else {
                        lastWord = pieceKey;
                        priorCnt ++;
                        st.put(pieceKey, 1);
                        putters ++;
                        distinct++;
                    }
                }
            }   // find a key with the highest frequency count
            String max;
            putters ++;
            for (cntM = 0; cntM < 10; cntM++) {
                max = "";
                st.put(max, 0);   
                for (String word : st.keys()) {
 
                    if (st.get(word) > st.get(max))
                        max = word;
                }   // System.out.println(max + " " + st.get(max));
                maxWord[cntM] = max;
                if (maxWordLen < max.length())
                    maxWordLen = max.length();
                maxCntr[cntM] = st.get(max);
                st.delete(max);
            }
            maxWordLen += 4;
            // System.out.println("distinct = " + distinct);
            // System.out.println("words    = " + words);
            // System.out.println("Putters " + putters + " Getters " + getters);
            // System.out.println("Word Count for words < minlen  " + wordCnt + "\n");
            priorCnt --;
            // System.out.println("Last Word " + lastWord + " Prior Cnt " + priorCnt + "\n");
            System.out.println("List Top 10 words and their counts:");
            for (cntM = 0; cntM < 10; cntM++) {
                  wordTxt = maxWord[cntM];
                  wordLen = wordTxt.length();
                  diffOfLen = maxWordLen - wordLen;
                  System.out.print(maxWord[cntM]);
                  for (loopCtr = 1; loopCtr <= diffOfLen; loopCtr++)
                      System.out.print(" ");
                  System.out.println(maxCntr[cntM]);                  
            }
            
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

