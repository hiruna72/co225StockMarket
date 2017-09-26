

import java.io.IOException;
import java.util.ArrayList;

public interface AccessStockData {
	ArrayList<String> loadStockData(String symbol) throws IOException; 
	String getPrice(String symbol) throws IOException;
	String getName(String symbol) throws IOException;
	boolean changePrice(String symbol, String value) throws IOException;
}
