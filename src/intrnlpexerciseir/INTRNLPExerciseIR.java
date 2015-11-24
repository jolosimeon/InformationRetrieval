/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intrnlpexerciseir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lucky
 */
public class INTRNLPExerciseIR {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws IOException
    {
      indexer in = new indexer(" ");
      //in.getSkipWords();
      //in.getWords();
      String input;
      Scanner sc = new Scanner(System.in);
      input = sc.next();
      in.Search(input);
    }
}
