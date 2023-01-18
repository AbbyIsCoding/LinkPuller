import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class LinkPullerTest2 {

    public static void main(String[] args) {
        LinkPullerTest2 l = new LinkPullerTest2();
    }

    public LinkPullerTest2() {

        try {
            System.out.println();
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {

                if (line.contains("https")) {

                    int start = line.indexOf("https");
                    int end = line.indexOf("\"", start);
                   // int end2 = line.indexOf("'",start);
//                    System.out.println("NEW LINE IS\n" + line + "\n   CHOPPED VERSION:");

                    while (end >= 1) {
                        String link = line.substring(start, end);
                        System.out.println(link);


                        line = line.substring(end+1);
                        start = line.indexOf("https");

                        end = line.indexOf("\"", start);
                    }

                }

            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }


    }
}
