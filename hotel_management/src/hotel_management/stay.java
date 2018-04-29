package hotel_management;




public class stay {
	private Guest guest; 
	private boolean status;
	
	public stay()
	{
		guest= new Guest();
		status=false;
	}
	public Guest get_guest()
	{
		return guest;
		
	}
	
	public void set_guest(Guest g)
	{
		guest.set_name(g.get_name());
		guest.set_adrs(g.get_adrs());
		guest.set_checkInDate(g.get_checkInDate());
		guest.set_checkOutDate(g.get_checkOut_date());
		guest.set_dob(g.get_dob());
		guest.set_serviceName(g.get_ServiceName());
		guest.set_contactno(g.get_contactno());
	}
	public boolean get_status()
	{
	 return status;
	}
	public void set_status(boolean flag)
	{
		status=flag;
	}

}
