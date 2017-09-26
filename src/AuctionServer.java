
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AuctionServer { 
  
    private static ServerSocket serverSocket; 
    private static int socketNumber; 
    public static StockData stockDataSet;
           
    public AuctionServer(int socket, StockData stockDataSet) throws IOException { 
    	try{
    		this.stockDataSet=stockDataSet;
    		serverSocket = new ServerSocket(socket); 
    		socketNumber = socket; 
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }

   
    public void server_loop() throws IOException { 
	while(true) { 
	    Socket socket = serverSocket.accept(); 	    
	    CommServer worker = new CommServer(socket); 
	    worker.start(); 
	}
    }

    
}



