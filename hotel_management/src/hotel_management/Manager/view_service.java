package hotel_management.Manager;

import java.io.IOException;

import hotel_management.Hotel_mgt;
import javafx.fxml.FXML;

public class view_service {


 @FXML
 private void view_services() throws IOException
 {
	 Hotel_mgt.view_service();
 }
 @FXML
 private void add_service() throws IOException
 {
	 Hotel_mgt.add_service();
 }
 @FXML
 private void remove_service( ) throws IOException
 {
	 Hotel_mgt.remove_service();
 }
 
 @FXML
 private void add_resource() throws IOException
 {
	 // call hotel_mgt function
	 Hotel_mgt.add_resource();
 }
 
 @FXML 
 private void remove_resource() throws IOException
 {
	// call hotel_mgt function
	 Hotel_mgt.remove_resource();
 }
 
 @FXML
 private void view_guests() throws IOException
 {
	// call hotel_mgt function
	 Hotel_mgt.view_guests();
 }
 
 @FXML
 private void calculate_profit() throws IOException
 {
	// call hotel_mgt function
	 Hotel_mgt.view_profits();
 }
 @FXML
 private void view_feedback() throws IOException
 {
	 Hotel_mgt.view_feedback();
 }
 @FXML
 private void service_consumption() throws IOException
 {
	 Hotel_mgt.service_consumption();
 }
 
}
