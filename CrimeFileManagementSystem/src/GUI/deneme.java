package GUI;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class deneme {
	//indicates the report classes(complaint, crime, missing, 
	public void insertEditDeleteReport(char operation,String name,String content, String location) {
		if(operation=='i') {
			
		}
		
	}
	public static void fillJTable(JTable table) {
		DefaultTableModel model= (DefaultTableModel)table.getModel();
		Object[] row;
		/*while(rs.next()) {
			row=new Object[3];
			row[0]= rs.getString(1);
			row[1]= rs.getString(2);
			row[2]= rs.getString(3);
			
			model.addRow(row);
		}*/
		
		
		 
		
	}

}
