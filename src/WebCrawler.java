import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.util.Scanner;

public class WebCrawler {

    public static Queue<String> oura = new LinkedList<>();
    public static Set<String> visitedUrl= new HashSet<>();
    public static String expression = "http[s]*://(\\w+\\.)*(\\w+)";

    public static void algBfs(String root) throws IOException {
        oura.add(root);
        BufferedReader reader = null;

        while (!oura.isEmpty()){
            String urlCrawl = oura.poll();
            System.out.println("\nIstoselida crawled : " + urlCrawl);

            //orio 200 istoselides
            if(visitedUrl.size() > 200){
                return;
            }
            boolean test = false;
            URL url= null;

            while (!test){
                try{
                    url = new URL(urlCrawl);
                    reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    test=true;
                }catch (MalformedURLException e){
                    System.out.println("Malformed istoselida : " +urlCrawl);
                    urlCrawl= oura.poll();
                    test=false;

                }catch (IOException ioe){
                    System.out.println("IOE gia istoselida : " +urlCrawl);
                    urlCrawl= oura.poll();
                    test=false;

                }
            }
            StringBuilder strBuild = new StringBuilder();
            String temporary = null;

            while((temporary = reader.readLine()) != null ){
                strBuild.append(temporary);
            }

            temporary = strBuild.toString();
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(temporary);

            while(matcher.find()) {
                String site = matcher.group();

                if(!visitedUrl.contains(site)){
                    visitedUrl.add(site);
                    System.out.println("Istoselida prostethike gia crawl : " + site);
                    oura.add(site);
                }
            }

        }

        if(reader != null){
            reader.close();
        }
    }
    public static void Apotelesmata() {
        System.out.println("\nApotelesmata :");
        System.out.println("\nIstostelides pou eginan crawl :" +visitedUrl.size() + "\n");

        for (String str : visitedUrl){
            System.out.println("" + str);
            try{
                FileWriter writer = new FileWriter("Sitelist.txt");
                writer.write(str + "\n");
                writer.close();
                System.out.println("Successfully wrote to the file.");
            }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        try{
            algBfs("http://www.mypage.gr");
            Apotelesmata();
            System.out.println("Search word: ");
            Scanner scanner = new Scanner(System.in);
            String searchWord = scanner.nextLine();


        }catch (IOException e){
        }

    }

}
