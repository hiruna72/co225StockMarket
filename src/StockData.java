
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StockData implements AccessStockData{
		private HashMap<String,ArrayList<String>> results;
	 StockData() {
    	results = new HashMap<String, ArrayList<String>>();
        String csvFile = "C:/Hiruna/eclipse/workLoad/co225Project2/src/stocks.csv";
        String line = "";
        String cvsSplitBy = ",";
        boolean firstLine=true;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

				// use comma as separator
            	if(!firstLine){
            		   firstLine=false;
            	}
                
            	else{
            		ArrayList<String> tempLine=new ArrayList<String>();
            		
            		String[] data = line.split(cvsSplitBy);
                    for(int i=1;i<data.length;i++){
                    	if(data[i].length()>0)
                    		tempLine.add(data[i]);
                    	//System.out.println(tempLine.get(i-1));
                    }
            		results.put(data[0],tempLine);
            	}
            		
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	 public ArrayList<String> loadStockData(String symbol) throws IOException{
		 return this.results.get(symbol);
	 }
	 public String getPrice(String symbol) throws IOException{
		 return this.results.get(symbol).get(5);
	 }
	 public String getName(String symbol) throws IOException{
		 return this.results.get(symbol).get(0);
	 }
	@Override
	public synchronized boolean changePrice(String symbol,String value) throws IOException {
			this.results.get(symbol).set(5, value);
			return true;
	}
	 

}