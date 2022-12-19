package matthieu;

import java.math.BigInteger;

public class pF11 
{
	
	public static final String INF = "INF";

	public static final String NAN = "NAN";

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
		if ((i>=0)&&(i<11))
			this.value = new F11(i);
		else
			this.value = INF;
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
			if (INF.equals(s))
			{
				this.value = INF;
			}
			else
			{
				this.value = NAN;
			}
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

	public boolean isNan()
	{
		return (this.value instanceof String)&&((String)this.value).equals(NAN);
	}

	
	public F11 toF11() throws Exception
	{
		F11 ret = null;
		
		if (this.isInf())
		{
			throw new Exception("Projective Field Value is infinite");
		}
		else
		{
			ret = ((F11)this.getValue());
		}
		
		return ret;
	}
	
	
	public pF11 getInf()
	{
		return new pF11("INF");
	}

	public pF11 getNan()
	{
		return new pF11("NAN");
	}

	
	public  pF11 add(pF11 pf)
	{
		pF11 ret = null;
		
		try 
		{
			F11 f0 = this.toF11();
			F11 f1 = pf.toF11();
			ret = new pF11(f0.add(f1));
		}
			catch(Exception e)
		{
			ret = getInf();
		}
			
		return ret;
	}

	public  pF11 subtract(pF11 pf)
	{
		pF11 ret = null;
		
		try 
		{
			F11 f0 = this.toF11();
			F11 f1 = pf.toF11();
			ret = new pF11(f0.subtract(f1));
		}
			catch(Exception e)
		{
			ret = getInf();
		}
			
		return ret;
	}

	public  pF11 multiply(pF11 pf)
	{
		pF11 ZERO = new pF11(0);
		
		if ((this.isInf()&&ZERO.equals(pf))||(this.equals(ZERO)&&pf.isInf()))
		{
			return new pF11("NAN");
		}
		else
		{
			if (this.isInf()||pf.isInf())
			{
				return new pF11("INF");
			}
			else
			{
				F11 f0 = (F11) this.value;
				F11 f1 = (F11) pf.value;
				
				return new pF11(f0.multiply(f1));
			}
		}
		
	}

	public  pF11 divide(pF11 pf)
	{
		pF11 ZERO = new pF11(0);
		pF11 ret = null;
		
		if ((this.isInf()&&pf.isInf())||(this.equals(ZERO)&&pf.equals(ZERO)))
		{
			return new pF11("NAN");
		}
		else
		{
			if (this.isInf()||pf.isInf())
			{
				if (this.isInf())
					return new pF11("INF");
				
				if (pf.isInf())
					return new pF11(0);
			}
			else
			{
				if (pf.equals(ZERO))
				{
					return new pF11("INF");
				}
				else
				{
					F11 f0 = (F11) this.value;
					F11 f1 = (F11) pf.value;
				
					return new pF11(f0.divide(f1));
				}
			}
		}
		
		return ret;
	}
	
	@Override
	public boolean equals(Object o)
	{
    if (o == this) {
        return true;
    }
    
    if (!(o instanceof pF11)) {
        return false;
    }
     
    pF11 pf = (pF11) o;
    
    
    if ((this.value instanceof F11)&&(pf.value instanceof F11))
    {
    	F11 f1 = (F11) this.value;
    	F11 f2 = (F11) pf.value;
    	
    	return f1.equals(f2);
    }
    else
    {
    	return (""+this.value).equals(""+pf.value);
    }
    
	}

	
	public static void main(String[] args)
	{
		for (int i=0; i<12; i++)
		{
				pF11 f = new pF11(i);
				System.out.println(""+f+", "+f.isInf());
		}
		
		for (int i=0; i<12; i++)
		{
			for (int j=0; j<12; j++)
			{
				pF11 fi = new pF11(i);
				pF11 fj = new pF11(j);
				System.out.print(" "+fi.add(fj));
			}
			System.out.println("");
		}

		System.out.println("");
		
		for (int i=0; i<12; i++)
		{
			for (int j=0; j<12; j++)
			{
				pF11 fi = new pF11(i);
				pF11 fj = new pF11(j);
				System.out.print(" "+fi.subtract(fj));
			}
			System.out.println("");
		}
		
		pF11 nan = new pF11("NAN");
		
		System.out.println(""+nan);

		for (int i=0; i<12; i++)
		{
			for (int j=0; j<12; j++)
			{
				pF11 fi = new pF11(i);
				pF11 fj = new pF11(j);
				System.out.print(" "+fi.equals(fj));
			}
			System.out.println("");
		}

		for (int i=0; i<12; i++)
		{
			for (int j=0; j<12; j++)
			{
				pF11 fi = new pF11(i);
				pF11 fj = new pF11(j);
				System.out.print(" "+fi.multiply(fj));
			}
			System.out.println("");
		}

		for (int i=0; i<12; i++)
		{
			for (int j=0; j<12; j++)
			{
				pF11 fi = new pF11(i);
				pF11 fj = new pF11(j);
				System.out.print(" "+fi.divide(fj));
			}
			System.out.println("");
		}
		
		
	}
}
