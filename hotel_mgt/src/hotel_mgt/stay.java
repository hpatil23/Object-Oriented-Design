package hotel_mgt;

public class stay {
	private Guest guest;
	private String check_In_date;
	private String check_Out_date; 
	private boolean serviceChange;
	
	
	public Guest get_guest()
	{
		return guest;
	}
	public String get_checkIndate() {
		return check_In_date;
	}
	public String get_checkOutdate() {
		return check_Out_date;
	}
	public boolean get_serviceChange()
	{
		return serviceChange;
	}
	public void set_guest(Guest g)
	{
		guest=g;
	}
	public void set_checkIndate(String date)
	{
		check_In_date=date;
	}
	public void set_checkOutDate(String date)
	{
		check_Out_date=date;
	}
	public void set_serviceChange(boolean change)
	{
		serviceChange=change;
	}

}
