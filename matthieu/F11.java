package matthieu;

import java.math.BigInteger;

public class F11 
{
	private static final BigInteger M = new BigInteger("11");
	
	private BigInteger value;

	public BigInteger getValue()
	{
		return this.value;
	}
	
	public F11(int f)
	{
		this.value = new BigInteger(""+f).mod(M);
	}
	
	public F11(BigInteger f)
	{
		this.value = f.mod(M);
	}
	
	public String toString()
	{
		return ""+this.value;
	}
	
	public  F11 add(F11 f)
	{
		return new F11((this.value.add(f.getValue())).mod(M));
	}

	public  F11 subtract(F11 f)
	{
		return new F11((this.value.subtract(f.getValue())).mod(M));
	}

	public  F11 multiply(F11 f)
	{
		return new F11((this.value.multiply(f.getValue())).mod(M));
	}
	
	public  F11 divide(F11 f)
	{
		return new F11((this.value.multiply(f.getValue().modInverse(M))).mod(M));
	}
	
	public static void main(String[] args)
	{
		for (int i=0; i<11; i++)
			for (int j=0; j<11; j++)
		{
			F11 fi = new F11(i);
			F11 fj = new F11(j);
			System.out.println(""+fi+"+"+fj+"="+fi.add(fj));
		}
		
		for (int i=0; i<11; i++)
			for (int j=0; j<11; j++)
		{
			F11 fi = new F11(i);
			F11 fj = new F11(j);
			System.out.println(""+fi+"-"+fj+"="+fi.subtract(fj));
		}
		
		for (int i=0; i<11; i++)
			for (int j=0; j<11; j++)
		{
			F11 fi = new F11(i);
			F11 fj = new F11(j);
			System.out.println(""+fi+"*"+fj+"="+fi.multiply(fj));
		}
		
		for (int i=1; i<11; i++)
			for (int j=1; j<11; j++)
		{
			F11 fi = new F11(i);
			F11 fj = new F11(j);
			System.out.println(""+fi+"/"+fj+"="+fi.divide(fj));
		}
		
	}

}