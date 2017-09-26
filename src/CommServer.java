

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class CommServer extends Thread {  
    private Socket connectionSocket; 
    private UserState user;
    public CommServer(Socket s) { 
    	this.connectionSocket = s; 
    	this.user=new UserState();
    }
    

    public void run() { 
	try { 
	    BufferedReader in = new 
		BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
	    PrintWriter out = new 
		PrintWriter(new OutputStreamWriter(this.connectionSocket.getOutputStream()));	 
	    String line=null;
	   // out.println();
	   // line = in.readLine();
	    out.println(user.getCommand(line));
	    for(line = in.readLine(); 
		line != null && !line.equals("quit"); 
		line = in.readLine()) { 
	    	out.println(user.getCommand(line));
		//out.println(stockDataSet.loadStockData(line)); 
		//out.print(line + "\n"); 
		out.flush();	    
	    } 
	}
	catch (IOException e) { 
	    System.out.println(e); 
	} 
	try { 	    
	    this.connectionSocket.close(); 
	} catch(IOException e) {}
	
    }
	    
	
}




//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//class CommServer extends Thread {  
//
//    private Socket connectionSocket; 
//
//    public CommServer(Socket s) { 
//	this.connectionSocket = s; 
//    }
//    
//
//    public void run() { 
//	try { 
//	    BufferedReader in = new 
//		BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
//	    PrintWriter out = new 
//		PrintWriter(new OutputStreamWriter(this.connectionSocket.getOutputStream()));	 
//	    String line; 
//	    for(line = in.readLine(); 
//		line != null && !line.equals("quit"); 
//		line = in.readLine()) { 
//		out.println(line); 
//		out.print(line + "\n"); 
//		out.flush();	    
//	    } // for loop
//	}// try 
//	catch (IOException e) { 
//	    System.out.println(e); 
//	} 
//	try { 	    
//	    this.connectionSocket.close(); 
//	} catch(IOException e) {}
//	
//    }
//	    
//	
//}