
package matthieu;

import java.math.BigInteger;

public class pF11 
{
	
	public static final String INF = "INF";
	
	private Object value;
	
	public pF11(F11 f)
	{
		this.value = f;
	}
	
	public pF11(BigInteger b)
	{
		this.value = new F11(b);
	}
	
	public pF11(int i)
	{
		this.value = new F11(i);
	}
	
	public pF11(String s)
	{
		BigInteger b;
		
		try 
		{
			this.value = new BigInteger(s);
		}
		catch(Exception e)
		{
			this.value = INF;
		}
	}
	
	public Object getValue()
	{
		return value;
	}
	
	public String toString()
	{
		return ""+this.value;
	}
	
	public boolean isInf()
	{
		return (this.value instanceof String)&&((String)this.value).equals(INF);
	}
	
	public static void main(String[] args)
	{
		for (int i=0; i<11; i++)
		{
				pF11 f = new pF11(i);
				System.out.println(""+f+", "+f.isInf());
		}
		
		pF11 f = new pF11("inf");
		System.out.println(""+f+", "+f.isInf());
		
	}

}
