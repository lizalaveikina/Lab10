package ucu.edu.ua;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.sql.SQLException;

@AllArgsConstructor
public class TimeDocument implements Document {

    public String gcsPath;

    @Override
    public String parse() throws IOException, SQLException {

        String smartText = time(new SmartDocument(gcsPath));

        String cachedText = time(new CachedDocument(gcsPath));

        return cachedText != null ? cachedText : smartText;
    }

    private String time(Document document) {

        try {

            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            String text = document.parse();

            String type = document instanceof SmartDocument ? "smart" : "cached";

            System.out.println(type + " =>" + (end - start) / 1000.0 + "mlls");

            return text;
        } 
        
        catch (IOException | SQLException e) {

            e.printStackTrace();
            return null;
        }
    }
}