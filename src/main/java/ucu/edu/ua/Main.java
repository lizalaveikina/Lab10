package ucu.edu.ua;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        
        try {

            String cachedDocumentResult = new CachedDocument("gs://cv-examples/wiki.png").parse();
            System.out.println(cachedDocumentResult);

            new TimeDocument("gs://cv-examples/wiki.png").parse();

        } 
        
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
