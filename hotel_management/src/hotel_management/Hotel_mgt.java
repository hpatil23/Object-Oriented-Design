package hotel_management;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Hotel_mgt extends Application {
	public static Stage priStage;
	public static BorderPane bp;   // initial border pane, for GUI of project
	public static BorderPane actors; // Guest, Manager and Receptionist 
	@Override
	public void start(Stage primaryStage) throws IOException {  // added IOException for display_MainView() function
		priStage=primaryStage;
		priStage.setTitle("Hotel Management System");
		String path="view/MainView.fxml";
		display_View(path,"bp");   // to display first GUI window
		Scene s= new Scene(bp);
		priStage.setScene(s);
		priStage.show();
		                        // to display actors
		String path_actor="view/Actors.fxml";
		display_View(path_actor,"actors");
		bp.setCenter(actors);
		
	}
public static void display_View(String path, String bp_name) throws IOException
{
	FXMLLoader ld= new FXMLLoader();
	
	ld.setLocation(Hotel_mgt.class.getResource(path));
	if(bp_name.equals("bp"))
	{
		bp=ld.load();
	}
	else if(bp_name.equals("actors"))
	{
		actors=ld.load();
	}
}
public static void  display_guests() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Guests/Guest.fxml"));
	BorderPane guestMenu=ld.load();
	bp.setCenter(guestMenu);
	
}

public static void display_manager() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Manager/Manager.fxml"));
	BorderPane managerMenu=ld.load();
	bp.setCenter(managerMenu);
}

public static void display_receptionist() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Receptionist/Receptionist.fxml"));
	BorderPane receptionistMenu=ld.load();
	bp.setCenter(receptionistMenu);
}
public static void get_guestInfo() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Guests/Guest_Details.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Guest Check-In Request");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}

public static void view_service() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Manager/view_services.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Hotel Services");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}
public static void add_service() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Manager/add_service.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Add Service");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}

public static void remove_service() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Manager/remove_service.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Remove Services");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}

public static void remove_resource() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Manager/remove_resource.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Remove Resource");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}
public static void add_resource() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Manager/add_resource.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Add Resource");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}
public static void confirm_guestCheckIn() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Receptionist/confirm_checkIn.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Confirm Check-In");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}

public static void view_guests() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("view/view_guests.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("View Guests");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}

public static void guest_checkout_req() throws IOException
{FXMLLoader ld=new FXMLLoader();
ld.setLocation(Hotel_mgt.class.getResource("Guests/checkout_request.fxml"));
BorderPane guestDetails=ld.load();
Stage s = new Stage();
s.setTitle("Check-out Request");
s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
s.initOwner(priStage);
Scene sc =new Scene(guestDetails);
s.setScene(sc);
s.showAndWait();
	
}

public static void confirm_guestCheckOut() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Receptionist/confirm_checkOut.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Check-out Confirmation");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}

public static void bill_generate() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("view/Bill.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Guest Bill");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}

public static void submit_feedback( )throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Guests/give_feedback.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Feedback submission");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}

public static void view_feedback() throws IOException
{FXMLLoader ld=new FXMLLoader();
ld.setLocation(Hotel_mgt.class.getResource("view/view_feedback.fxml"));
BorderPane guestDetails=ld.load();
Stage s = new Stage();
s.setTitle("Received Feedbacks");
s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
s.initOwner(priStage);
Scene sc =new Scene(guestDetails);
s.setScene(sc);
s.showAndWait();
	
}

public static void service_consumption() throws IOException
{FXMLLoader ld=new FXMLLoader();
ld.setLocation(Hotel_mgt.class.getResource("Manager/service_consumption.fxml"));
BorderPane guestDetails=ld.load();
Stage s = new Stage();
s.setTitle("Service Usage");
s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
s.initOwner(priStage);
Scene sc =new Scene(guestDetails);
s.setScene(sc);
s.showAndWait();
	
}

public static void view_profits() throws IOException
{
	FXMLLoader ld=new FXMLLoader();
	ld.setLocation(Hotel_mgt.class.getResource("Manager/view_profits.fxml"));
	BorderPane guestDetails=ld.load();
	Stage s = new Stage();
	s.setTitle("Hotel Profits");
	s.initModality(Modality.WINDOW_MODAL);  //to stop all back UI buttons to perform till this page is completed
	s.initOwner(priStage);
	Scene sc =new Scene(guestDetails);
	s.setScene(sc);
	s.showAndWait();
}
public static void main(String[] args) {
	
	//  adding initial resources and services into the hotel
	   String[] resNames= {"Room","Food", "Casino","Pool"};
	   double[] resCost= {200,100,500,50};
	   for(int i=0;i<4;i++)   // Adding resources into hotel resources list in hotel_database
	   {
		   resource res= new resource();
		   res.set_resourceName(resNames[i]);
		   res.set_resourceCost(resCost[i]);
		   hotel_database.resources.add(res);
	   }
	   String[] serName= {"Basic Room","Delux","Premium"};
	   for(int i=0;i<3;i++)
	   {
		   service s= new service();
		   s.set_name(serName[i]);
		   hotel_database.services.add(s);
	   }
	   
	// Adding required Resources to first 3 Services
	   //1
	   hotel_database.services.get(0).add_resource(hotel_database.resources.get(0));
	   
	   //2
	   hotel_database.services.get(1).add_resource(hotel_database.resources.get(0));
	   hotel_database.services.get(1).add_resource(hotel_database.resources.get(1));
	   
	   //3
	   hotel_database.services.get(2).add_resource(hotel_database.resources.get(0));
	   hotel_database.services.get(2).add_resource(hotel_database.resources.get(1));
	   hotel_database.services.get(2).add_resource(hotel_database.resources.get(2));
	   hotel_database.services.get(2).add_resource(hotel_database.resources.get(3));
		launch(args);
	}
}
