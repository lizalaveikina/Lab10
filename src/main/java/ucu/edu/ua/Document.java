package ucu.edu.ua;

import java.sql.SQLException;
import java.io.IOException;


public interface Document {
    String parse() throws IOException, SQLException;
}