import java.io.IOException;

public class Main {
	public static final int BASE_PORT = 2000; 
	
	public static void main(String[] args) {
		
		AuctionServer server = null;
		try {
			StockData stockDataSet=new StockData();;
			ShowStockData stockTable= new ShowStockData();
			server = new AuctionServer(BASE_PORT,stockDataSet);
			server.server_loop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
