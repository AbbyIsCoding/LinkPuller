import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class LinkPullerTest {

    public static void main(String[] args) {
        LinkPullerTest l = new LinkPullerTest();
    }

    public LinkPullerTest() {

        try {
            System.out.println();
            URL url = new URL("https://www.milton.edu/");
            //url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {


                if (line.contains("https")) {
                    int start = line.indexOf("https");
                    String line1 = line.substring(start);
                    System.out.println("not chopped " +line1);

                    int end = line1.indexOf("https", start + 1);

                    while (end >= 1) {

                        start = line1.indexOf("https");

                        // if (end == -1) {

                        if(line1.indexOf(" ", start + 1)>0){
                            end = line1.indexOf(" ", start + 1);
                        }
                        else if (line1.indexOf(" *", start + 1)>0){
                            end = line1.indexOf(" *", start + 1);
                        }


                        System.out.println(start);

                        System.out.println(end);
                        System.out.println("line is: " + line1);

                        // }

                        String indexLine = line1.substring(0, end);
                        System.out.println(indexLine);
//                        pa.append(line);


                        // moved this to the end from the beginning of while loop bc we want to change where we are searching
                        start = end;
                        end = line1.indexOf("https", start + 1);
                        line1 = line1.substring(end);
                    }
                    if (end == -1) {
                        end = line1.indexOf("\" ", start + 1);

                    }

                    String indexLine = line1.substring(0, end);
                    System.out.println(indexLine);
//                    pa.append(line);
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }


    }
}
