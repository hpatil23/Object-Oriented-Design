package hotel_mgt;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
public class hotel_mgt {
	
	public static String readLine() {
        String line = null;
        Console c = System.console();
        if (c != null) {
             line = c.readLine();
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                 line = bufferedReader.readLine();
            } catch (IOException e) { 
                //Ignore    
            }
        }
        return line;
    }
	public static void main(String args[])
	{
		// for demo on console, UI build and integration in final phase
		// Imp. Actors will give input from Console, Choose menu option by using numbers
	   String c_input;
	   boolean exit_flag;   // for Hotel Management System Exit
	   boolean actor_flag;  // for Actor exit, System goes back to choose Actor
	   System.out.println("Hotel Management System, Phase-3 Demo");
	   System.out.println("Initial Setup for Hotel Resources, Services, Manager and Receptionist");
	   
	   // Create Manager and Receptionist object
	   manager m =new manager("M","101");             // Manager Name : M ID: 101
	   receptionist r= new receptionist("R","201");   // 
	   
	   // initial resources in restaurent 
	   String[] resNames= {"Room","Food", "Casino","Pool"};
	   double[] resCost= {200,100,500,50};
	   for(int i=0;i<4;i++)   // Adding resources into hotel resources list in hotel_database
	   {
		   resource res= new resource();
		   res.set_resourceName(resNames[i]);
		   res.set_resourceCost(resCost[i]);
		   hotel_database.resources.add(res);
	   }
	   
	   // initial 3 services offered by hotel, added for the demo.
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
	   
	  /* for(service se: hotel_database.services)
	   {
		   se.print_ResourceList();
	   }*/
	   // Create 
	   do {
	   exit_flag=false;
	   System.out.println("Choose Actor:\n 1. Guest\n 2. Manager\n 3. Receptionist\n 4. Exit");
	   System.out.println("Input :");
	   c_input=readLine();
	   int menu_option= Integer.parseInt(c_input);
	   if(menu_option==4)
	   {
		exit_flag=true;    //exit the loop and close the demo
	   }
	   else
	   {
		   //Display options for selected Actor
		   if(menu_option==1)       // Guest Menu
		   {	do {
			   		actor_flag=false;
			   		System.out.println("Welcome: Guest");
			   		System.out.println(" 1. Check-In Request");
			   		System.out.println(" 2. Check-Out Request");
			   		System.out.println(" 3. Exit Guest Menu");
			   		int a_input=Integer.parseInt(readLine());
			   		if(a_input==3)     // condition for exit of Guest Actor
			   		{
			   			actor_flag=true;
			   		}
			   		else    // Menu Cases to work on
			   		{
			   			if(a_input==1) // Guest Request for Check-In
			   			{
			   			   Guest g= new Guest();
			   			   g.checkin_request();    // guest check-In request function to initate Check-In
			   			}
			   			else if(a_input==2)
			   			{
			   				Guest g= new Guest();
			   				g.checkOut_request();   // request to check out 
			   			}
			   		}
		   			}while(actor_flag==false);
		   }
		   
		   else if(menu_option==2)  // Manager Menu
		   {do {
			   actor_flag=false;
			   System.out.println("Welcome: Manager");
			   System.out.println(" 1. List All Services");
			   System.out.println(" 2. Find Service by Name");
			   System.out.println(" 3. Add a New Service");
			   System.out.println(" 4. Remove Service");
			   System.out.println(" 5. Find Guest");
			   System.out.println(" 6. Exit Manager Menu");
			   int a_input=Integer.parseInt(readLine());
			   if(a_input==6)
			   {
				   actor_flag=true;   
			   }
			   else
			   {
				   if(a_input==1)   // to List Current All Services in Hotel
				   {
					   m.list_service();
				   }
				   else if(a_input==2)
				   {
					   System.out.println("Enter Service Name: ");
					   String s=hotel_mgt.readLine();
					  boolean found= m.find_service(s);    // to find the service by its name
					  if(found==false)
					  {
						  System.out.println("Service not found");
					  }
				   }
				   else if(a_input==3)
				   {	
					   m.add_service();     // to add new service into system
				   }
				   else if(a_input==5)
				   {
					   System.out.println("Enter Guest Name: ");
					   String g_name=hotel_mgt.readLine();
					   m.find_guest(g_name);
				   }
				   else if(a_input==4)
				   {
					   m.remove_service();
				   }
			   }
		   }while(actor_flag==false);
		   }
		   else                     // Receptionist Menu 
		   {
			   do
			   {
				   actor_flag=false;
				   System.out.println("Welcome: Receptionist");
				   System.out.println(" 1. List Active Guests");
				   System.out.println(" 2. Check-In Guest");
				   System.out.println(" 3. Check-out Guest");
				   System.out.println(" 4. Exit Receptionist Menu");
				   int a_input=Integer.parseInt(readLine());
				   
				   if(a_input==4)
				   {
					   actor_flag=true;   
				   }
				   else
				   {
					   if(a_input==1)  // list All active Guests in system
					   {
						   r.list_activeGuests();  
					   }
					   else if(a_input==2)  // check-IN waiting guest
					   {
						   r.checkIn_guest();    // function to check in guest
					   }
					   else if(a_input==3)   // Check-out Guest
					   {
						  r.checkOut_guest();   // function to check out guest
					   }
				   }
				   
			   }while(actor_flag==false);
		   }
	   }
	   
	   }while(exit_flag==false);
	   System.out.println("System Exit");
	}
}
