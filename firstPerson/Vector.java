package firstPerson;

public class Vector 
{
	public double x;
	public double y;
	
	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector(Vector v)
	{
		this.x = v.x;
		this.y = v.y;
	}
	
	public Vector(double d)
	{
		this.x = d;
		this.y = d;
	}	
	
	public double getLength()
	{
		return Math.sqrt((this.x * this.x) + (this.y * this.y));
	}
	
	public void multiply(double d)
	{
		this.x *= d;
		this.y *= d;
	}
	
	public void divide(double d)
	{
		this.x /= 50;
		this.y /= 50;
	}
	
	public void add(Vector v)
	{
		this.x += v.x;
		this.y += v.y;
	}
}
