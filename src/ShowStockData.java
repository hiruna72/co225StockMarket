
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class ShowStockData extends JPanel implements ActionListener{
	private JFrame f2;
	private JPanel p2;
	private StockData stockDataSet;
	private Timer watch;
	DefaultTableModel model;
	JTable table;
	private static String[] symbols = {"FB" , "VRTU","MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"};
	
	ShowStockData() throws IOException{
		super(new BorderLayout());
		stockDataSet=new StockData();
		f2 = new JFrame("Stock Data");
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(300, 150)); 
        watch = new Timer(500, this);
        
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(model);
        model.addColumn("Symbol");
        model.addColumn("Name");
        model.addColumn("Price");
        this.addTableRow(model, "Symbol", "Name", "Price");
        for(int i = 0; i < 8; i++){
			addTableRow(model, symbols[i],stockDataSet.getName(symbols[i]),stockDataSet.getPrice(symbols[i]));
		}
        
        table.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);

        table.setRowHeight(40);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.ORANGE);

        watch.start();
        f2.add(table);
        f2.pack();
        f2.setVisible(true);
	}
	
	private void addTableRow(DefaultTableModel model, String col1, String col2, String col3){
        model.addRow(new Object[]{col1, col2, col3});
    }
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 8; i++){
            try {
            	model.setValueAt(AuctionServer.stockDataSet.getPrice(symbols[i]),i+1, 2);
            	//System.out.println("updated");
				//addTableRow(model, symbols[i],stockDataSet.getName(symbols[i]),stockDataSet.getPrice(symbols[i]));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
		
	}
}
