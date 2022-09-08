import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Query{


    public static List Query (String searchWord ) {
        Map<String,List<Integer>> map = readIndex();
        Map<Integer,String> sites = readSites();

        List<String> results = new ArrayList();
        Set<Integer> set = new TreeSet();

        // kanonikopoihsh ths search word se ola mikra kai xwris eidikous xarakthres
        searchWord = searchWord.replaceAll("[\\d[^\\w\\s]]+", "").replaceAll("(\\s{2,})", " ");
        searchWord = searchWord.toLowerCase();

        // H frash xwrizetai se 3exwristes lexeis
        StringTokenizer tokenizer = new StringTokenizer( searchWord, " " );

        // elegxos gia thn upar3h ths le3hs
        while ( tokenizer.hasMoreTokens() ) {
            String word = tokenizer.nextToken();

            // Lista me arxeia poy periexoun kathe lexh
            if ( map.containsKey(word) ) {
                List<Integer> list = map.get(word);
                System.out.println( list.toString() + list.size() );
                StringTokenizer tokenizer1 = new StringTokenizer( list.toString(), "[,] " );
                while ( tokenizer1.hasMoreTokens() ) {
                    set.add( Integer.parseInt( tokenizer1.nextToken() ) );
                }
            }
        }


        //Lista me path arxeiwn, analoga me to index tous
        Iterator<Integer> iterator = set.iterator();
        while ( iterator.hasNext() ) {
            results.add( sites.get( iterator.next() ) );
        }

        return results;
    }

    /*
    Diabazei to index file kai analuei ta dedomena se Map
    */
    private static Map readIndex() {

        // Edw apothikeuetai o index
        Map<String,List<Integer>> map = new TreeMap();

        try {

            BufferedReader reader = new BufferedReader ( new FileReader( "index.txt" ) );
            String grammh = reader.readLine();

            while( grammh != null ){

                StringTokenizer tokenizer = new StringTokenizer( grammh, "\t" );


                String word = tokenizer.nextToken();


                List lista = new ArrayList();
                while ( tokenizer.hasMoreTokens() ) {
                    String pair = tokenizer.nextToken();
                    StringTokenizer tokenizer1 = new StringTokenizer ( pair, "," );
                    int x = Integer.parseInt( tokenizer1.nextToken() );
                    lista.add(x);
                }

                map.put(word, lista);
                grammh = reader.readLine();
            }
            return map;
        }
        catch (IOException ex) {
            System.out.println("Apotuxia diavasmatos index\n" + ex );
        }

        return null;
    }

    /*
    Diabazei to Sitelist.txt kai parnei ta 2 prwta pedia:to index kai to path toy site.
    */
    private static Map readSites() {

        Map<Integer,String> map = new TreeMap();

        try {
            BufferedReader reader = new BufferedReader ( new FileReader ( "Sitelist.txt" ) );
            String grammh = reader.readLine();

            while( grammh != null ){

                StringTokenizer tokenizer = new StringTokenizer( grammh, "," );

                int id = Integer.parseInt( tokenizer.nextToken() );
                String path = tokenizer.nextToken();
                map.put( id, path );

                grammh = reader.readLine();
            }
            return map;
        }
        catch (IOException ex) {
            System.out.println("Apotuxia diavasmatos index.\n" + ex );
        }

        return null;
    }
}
