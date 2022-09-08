import java.io.*;
import java.util.*;

public class Indexer {


    public HashMap<String,Integer> index;



    public Indexer(String[] array_str){

        for(String str: array_str){
            // An ena string den einai sto map to hdh to prosthetei
            if (!index.containsKey(str))
                index.put(str, 1);

                // an uparxei hdh anebainei to index kata 1
            else
                index.put(str, index.get(str)+1);
        }
    }



    //Methodos pou metradei index ths kathe lexhs sto arxeia kai ta topothetei se map
    public Indexer (String filename) throws Exception{
        index = new HashMap<String,Integer>();

        BufferedReader fin = new BufferedReader(new FileReader(filename));
        String[] split;
        String grammh = fin.readLine();


        while(grammh != null){

            grammh = grammh.toLowerCase();

            // Diaxwrismos lexewn kai topothethsh se array
            split = grammh.split("\\s+");

            // Metrhsh index
            for (String word: split){
                if (!index.containsKey(word))
                    index.put(word, 1);

                else
                    index.put(word, index.get(word)+1);
            }

            grammh = fin.readLine();
        }

        fin.close();
    }

    //epistofh index enos string
    public int getIndex(String str){
        if (!index.containsKey(str))
            return 0;
        return index.get(str);
    }

    // Methodos dexeteai arxeio kai epistrefei map me ola ta strings kai ta index tous
    public static HashMap<String,Integer> TokenIndex(String filename) throws Exception{
        Indexer file = new Indexer(filename);
        return file.index;
    }


}