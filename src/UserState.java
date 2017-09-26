import java.io.IOException;

public class UserState implements ConsoleIO {
	private boolean connecting,initial,logged,getSymbol,bidOn;
	private String symbol;
	UserState(){
		connecting = false;
		initial=true;
		logged=false;
		getSymbol=false;
		bidOn=false;
	}
	
	
	@Override
	public String getCommand(String command) throws IOException {
		if(initial){
			initial=false;
			return "Welcome to Auction (Type 'quit' to terminate)"; 
		}
		else if(!connecting && !initial){
			connecting = true;
			return "Enter User Name";
		}
		else if(connecting && !logged && command.length()>0){
			logged = true;
			return "Enter Symbol";
		}
		else if(logged && !getSymbol && AuctionServer.stockDataSet.loadStockData(command)!=null){
			getSymbol=true;
			this.symbol=command;
			return "currentPrice: "+AuctionServer.stockDataSet.getPrice(command)+"\n Do you want to bid? (yes/no)";
		}
		else if(!bidOn && logged && !getSymbol && AuctionServer.stockDataSet.loadStockData(command)==null){
			return "Enter Symbol";
		}
		else if(getSymbol && (command.equals("yes") || command.equals("Yes") || command.equals("YES"))){
			bidOn=true;
			return "Enter Price:";
		}
		else if(bidOn && getSymbol){
			try{
				bidOn=false;
				getSymbol=false;
				int value=Integer.valueOf(command);
				AuctionServer.stockDataSet.changePrice(this.symbol, command);
				return "Bidding Successfull";
			}
			catch (Exception pe){
				return "Bidding Failed";
			}
		}
		else{
			getSymbol=false;
			return "Enter Symbol";
		}
		
	}

}
