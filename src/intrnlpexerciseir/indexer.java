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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lucky
 */
public class indexer {
    private String path;
    private Database db;
    private ArrayList<String> skipWords;
    private ArrayList<String> fileContent;
    private HashMap<String, String> wordIndex;
    private HashMap<String, String> wordIndexTFIDF;
    private HashMap<String, HashMap<String, String>> documents;
    
    public indexer(String path)
    {
        this.path = path;
        db = new Database();
        skipWords = new ArrayList<>();
        wordIndex = new HashMap<>();
        wordIndexTFIDF = new HashMap<>();
        fileContent = new ArrayList<>();
    }
    public void index(String word) throws IOException
    {
        String query = word;
        
        for (int i = 0; i < fileContent.size(); i++)
            if(fileContent.get(i).toLowerCase().contains(query))
            {
              //wordIndex.
                String wtf = String.format("%.2f",this.getWTF(word, fileContent.get(i)));
              if (!wordIndex.containsKey(query))
              {
                
                wordIndex.put(query, " " + String.valueOf(i + 1) + "-" + wtf + ", ");
              }
              else if (!wordIndex.get(query).contains(" " + String.valueOf(i + 1) + "-" + wtf  + ", "))
                wordIndex.replace(query, wordIndex.get(query) + String.valueOf(i + 1) + "-" + wtf + ", ");
            }
                            //else existence[0][j] = 0;
                        
                        //inputStream = new BufferedReader(new FileReader(f));
                        //while ((line = inputStream.readLine()) != null) {
                       //     Matcher m = p.matcher(line);
                        //    while(m.find())
                        //        ctr++;         
                        //}
                        //occurrence[0][j] = ctr;
            //System.out.println(query + " = " + filesNo);

        //print existence table
        //System.out.println("Existence: ");
       // for(int i = 0; i<q.length; i++)
       // {
            
        //    System.out.print(q[i] + "   ");
        //    for(int j = 0; j<files.length;j++)
        //        System.out.print(existence[i][j] + "  ");
        //    System.out.println();
        //}
        
        
        //print occurrence table
        //System.out.println("Occurrence: ");
       // for(int i = 0; i<q.length; i++)
       // {
            
       //     System.out.print(q[i] + "   ");
        //    for(int j = 0; j<files.length;j++)
        //        System.out.print(occurrence[i][j] + "  ");
        //    System.out.println();
        //}
    }
    
    public void getSkipWords()
    {
        File f = new File("C:\\Users\\Jolo Simeon\\Projects\\Indexing\\fil-function-words.txt");
        String text = "";
        if(f.isFile()) 
                {
                    BufferedReader inputStream = null;
                    try 
                    {
                        String line;
                        inputStream = new BufferedReader(new FileReader(f));
                        

                        while ((line = inputStream.readLine()) != null) {
                            //System.out.println(line + " ");
                            text = text + line + " ";
                        }
                    }
                    catch (FileNotFoundException ex)
                    {
                        System.out.println("File not found");
                    } 
                    catch (IOException ex)
                    {
                        System.out.println("Error reading file");
                    }
                    finally {
                        if (inputStream != null) {
                            try
                            {
                                inputStream.close();
                            } catch (IOException ex)
                            {
                                System.out.println("Cannot close inputstream");
                            }
                        }
                    }
                    
                    
                }
                Pattern p = Pattern.compile("[aA-zZ]+");
                Matcher m = p.matcher(text);
                while(m.find())
                    if (!skipWords.contains(m.group()))
                        skipWords.add(m.group());
    }
    
    public void saveToDatabase()
    {
        for (Map.Entry<String, String> entry : wordIndex.entrySet())
        {
            db.addRow(entry.getKey(), entry.getValue());
        }
        
    }
    
    public void readAllFiles()
    {
       String[] files = new String[303];   
       for(int i = 0; i< 303;i++)
        {
            files[i] = "C:\\Users\\Jolo Simeon\\Projects\\Indexing\\resources\\Tagalog News - " + i + ".txt";
        }
        for (int j = 0; j<303; j++) 
            {   String bigS = "";
                //int ctr = 0;
                System.out.println("Reading file: " + j);
                //Pattern p = Pattern.compile("(" + query + ")");
                File f = new File(files[j]);
                if(f.isFile()) {
                    BufferedReader inputStream = null;
                    
                    try 
                    {
                        inputStream = new BufferedReader(new FileReader(f));
                        String line;
                        
                         while ((line = inputStream.readLine()) != null) {
                            //System.out.println(line + " " + f.getName());
                            bigS = bigS + line + " ";
                        }
                        fileContent.add(bigS);
                    }
                    catch (FileNotFoundException ex)
                    {
                        System.out.println("File not found");
                    } 
                    catch (IOException ex)
                    {
                        System.out.println("Error reading file");
                    }
                    finally {
                        if (inputStream != null) {
                            try
                            {
                                inputStream.close();
                            } catch (IOException ex)
                            {
                                System.out.println("Cannot close inputstream");
                            }
                        }
                    }
                }
            }
    }
    
    public void getWords() throws IOException
    {
        System.out.println("Hello");
        String target_dir = "C:\\Users\\Jolo Simeon\\Projects\\Indexing\\resources";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();
        
        readAllFiles();
        
        
            for (int j = 0; j<303; j++) 
            {
                String bigS = "";
                File f = files[j];
                System.out.println(j);
                if(f.isFile()) 
                {
                    BufferedReader inputStream = null;
                    try 
                    {
                        inputStream = new BufferedReader(new FileReader(f));
                        String line;

                        while ((line = inputStream.readLine()) != null) {
                            //System.out.println(line + " " + f.getName());
                            bigS = bigS + line + " ";
                        }
                    }
                    catch (FileNotFoundException ex)
                    {
                        System.out.println("File not found");
                    } 
                    catch (IOException ex)
                    {
                        System.out.println("Error reading file");
                    }
                    finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                    
                    
                }
                Pattern p = Pattern.compile("[aA-zZ]+");
                Matcher m = p.matcher(bigS);
                while(m.find())
                    if (!skipWords.contains(m.group().toLowerCase()))
                        this.index(m.group().toLowerCase());
                    //System.out.println(m.group());
            }
            //saveToDatabase();
    }
    
    public ArrayList getFiles(String word)
    {
        String file[];
        Integer test[] = new Integer[50];
        ArrayList<String> list = new ArrayList();
        //ArrayList<Integer> fileNos = new ArrayList();
        String files = db.getFiles(word);
        if (files != null)
        {
            file = files.split(",");

            for(int i=0;i<file.length;i++)
            {
                //fileNos.add(Integer.valueOf(file[i]));
                System.out.print(file[i]);
                list.add(file[i]);
               //test[i] = Integer.valueOf(file[i]);
            }
        }
        //System.out.println("n");
        //return test;
        System.out.println(word + "=" + files);

        return list;
        
    }
    
    public void retrieveAll()
    {
        documents = db.getAllFiles();
    }
    
    public void Search(String query)
    {
        String words[];
        words = query.split("-");
        HashMap<String, Double> alldocs = new HashMap();
        for (int i = 0; i < words.length; i++)
        {
            
            HashMap<String, String> scores = documents.get(words[i]);
            int num = 1; //0 for TF, 1 for TF*IDF
            if (scores!=null)
                for (Map.Entry<String, String> entry : scores.entrySet())
                {
                    String[] tfandtfIDF = entry.getValue().split("-");
                    if (!alldocs.containsKey(entry.getKey()))
                        alldocs.put(entry.getKey(), Double.valueOf(tfandtfIDF[num]));
                    else
                    {
                        String[] tfandtfIDF2 = entry.getValue().split("-");
                        alldocs.replace(entry.getKey(), Double.valueOf(tfandtfIDF2[num]) + Double.valueOf(tfandtfIDF[num]));

                    }
                }
        }
        Map<String, Double> map = alldocs;
        Map<String, Double> sortedMap = sortByComparator(map, false);
        System.out.println(alldocs.size());
        for (Map.Entry<String, Double> entry : sortedMap.entrySet())
        {
            System.out.println(entry.getKey() + " - " + String.format("%.2f", entry.getValue()));
        }
            
    }
    
    private static Map<String, Double> sortByComparator(Map<String, Double> unsortMap, boolean order)
    {

        List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Double>>()
        {
            public int compare(Entry<String, Double> o1,
                    Entry<String, Double> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Entry<String, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    
    public double getWTF(String word, String bigS)
    {
        bigS = bigS.toLowerCase();
        Pattern p = Pattern.compile("(" + word + ")");
        Matcher m = p.matcher(bigS);
        int tf = 0; //term frequency
        double wtf = 0; // weighted term frequency
        while(m.find())
            tf++;
        
        if(tf > 0)
            wtf = 1 + Math.log10(tf);
        
        return wtf;
        
    }
    
    public double getIDF(String word)
    {
        
        int N = 303; //the N in the forumla based on what i think is the total no of terms in the document
        int df;
        String fetch;
        fetch =  db.getFiles(word);
        
        String[] files = fetch.split(",");
        df = files.length;
        double idf;
        
        idf = Math.log10(N/df);
        
        return idf;
        
    }
    
    public double getTF_IDF_W(String word, String bigS)
    {
        
        double tf_idf_w;
        
        tf_idf_w = getWTF(word,bigS) * getIDF(word);
        
        return tf_idf_w;
        
    }
    
    public void DoTFIDF()
    {

        for (Map.Entry<String, String> entry : wordIndex.entrySet())
        {
           String newEnt = "";
           String oldS = entry.getValue();
           String[] files = oldS.split(",");
           for(int i = 0; i < files.length - 1; i++)
           {   String[] tfs = files[i].split("-");
               String tf_idf = String.format("%.2f",(Math.log10(303/files.length)*(Double.valueOf(tfs[1]))));
               files[i] = files[i] + "-" + tf_idf + ", ";
               newEnt = newEnt + files[i];
           }
           wordIndexTFIDF.put(entry.getKey(), newEnt);
            
        }
        saveToDatabaseTFIDF(); //not sure kung tama pa to
    }
    
    public void saveToDatabaseTFIDF()
    {
        
        for (Map.Entry<String, String> entry : wordIndexTFIDF.entrySet())
        {
            db.addRow(entry.getKey(), entry.getValue());
        }
        
    }
}
