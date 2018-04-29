package hotel_management.Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hotel_management.bill;
import hotel_management.hotel_database;
import hotel_management.stay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class view_profits {
ObservableList<String> profits= FXCollections.observableArrayList();
	
	@FXML
	 public Button close;
	@FXML
	public ListView<String> date_wise_profits;
	
	@FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	
	 @FXML
	 private void initialize()
	 {
		 List<stay> stays=hotel_database.get_hotelStays();
		 List<String> all_dates= new ArrayList<String>();
		 List<String> date_profit= new ArrayList<String>();
		 
		 double total=0.0;
		// all dates
		for(stay s:stays)
		{
			if(s.get_status()==true)
			{
				all_dates.add(s.get_guest().get_checkOut_date().toString());
			}
		}
		
		// getting unique dates
		
		List<String> unique_dates= new ArrayList<String>();
		
		if(!all_dates.isEmpty())
		{
			String temp=all_dates.get(0);
			unique_dates.add(temp);
			
			for(String s:all_dates)
			{
				if(!temp.equalsIgnoreCase(s))
				{
					unique_dates.add(s);
					temp=s;
				}
			}
		}
		bill b=new bill();
	  double dayWise_profit;
	  for(String date: unique_dates)
	  {
		  dayWise_profit=0.0;
		  for(stay s:stays)
		  { if(s.get_status()==true) {
			  if(date.equalsIgnoreCase(s.get_guest().get_checkOut_date().toString()))
			  {
				  dayWise_profit=dayWise_profit+b.generate_bill(s.get_guest());
			  }
		  }
		  }
		  total=total+dayWise_profit;
		  date_profit.add(Double.toString(dayWise_profit));
	  }
	  
	  profits.add("Date\t\t\tProfit");
	  int i=0;
	  for(String date: unique_dates)
	  {
		  profits.add(date+"\t\t"+date_profit.get(i));
		  i++;
	  }
	  profits.add("Total("+total+")");
	  date_wise_profits.setItems(profits);
	  
	  
	 }


}
