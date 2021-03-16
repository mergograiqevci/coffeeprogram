package application;

public class newItem {

	String name;
	double price;
	String quantity;
	newItem()
	{
		name="";
		price=0;
		quantity="1";
	}
	newItem(String name,String quantity,double price)
	{
		this.name=name;
		this.price=price;
		this.quantity=quantity;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setQuantity(String quantity)
	{
		this.quantity=quantity;
	}
	public void setPrice(double price)
	{
		this.price=price;
	}
	public String getName()
	{
		return name;
	}
	public String getQuantity()
	{
		return quantity;
	}
	public double getPrice()
	{
		return price;
	}
	
	
	
}
