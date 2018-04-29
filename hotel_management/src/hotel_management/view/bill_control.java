package hotel_management.view;

import java.io.IOException;

import hotel_management.Guest;
import hotel_management.bill;
import hotel_management.hotel_database;
import hotel_management.stay;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class bill_control {

@FXML
private Label name;

@FXML 
private Label contactno;
@FXML
private Label checkInDate;
@FXML
private Label checkOutDate;
@FXML
private Label Service;
@FXML
private Label Points;
@FXML
private Label bill_amount;

@FXML 
private Button close;

@FXML
private void close() throws IOException
{
	Stage st= (Stage) close.getScene().getWindow();
	st.close();
}
@FXML
private void initialize()
{
	
	stay s=hotel_database.get_hotelStays().get(hotel_database.get_hotelStays().size()-1);
	Guest g= s.get_guest();
	name.setText(g.get_name());
    contactno.setText(g.get_contactno());
    checkInDate.setText(g.get_checkInDate().toString());
    checkOutDate.setText(g.get_checkOut_date().toString());
    Service.setText(g.get_ServiceName());
    bill b= new bill();
    b.guest_Name=g.get_name();
    double cost=b.generate_bill(g);
    bill_amount.setText(Double.toString(cost));
    Points.setText(Integer.toString(g.get_points().get_points()));
    s.set_status(true);
    
}

	
}
