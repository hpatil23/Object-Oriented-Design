package hotel_management;


public class resource {
	private String name;
	private double cost;
	
	public String get_resourceName()
	{
		return name;
	}
	public double get_resourceCost()
	{
		return cost;
	}
	public void set_resourceName(String name)
	{
		this.name=name;
	}
	public void set_resourceCost(double cost)
	{
		this.cost=cost;
	}

}
