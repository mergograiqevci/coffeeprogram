package application;

public class porositItem {
	String name;
	double price;
	String remove;
	porositItem()
	{
		name="";
		price=0;
		remove="";
	}
	porositItem(String name,double price,String remove)
	{
		this.name=name;
		this.price=price;
		this.remove=remove;
	}
	public String getName()
	{
		return name;
	}
	public double getPrice()
	{
		return price;
	}
	public String getRemove()
	{
		return remove;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setPrice(double price)
	{
		this.price=price;
	}
	public void setRemove(String remove)
	{
		this.remove=remove;
	}
}
