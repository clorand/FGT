package matthieu;

import java.math.BigInteger;

abstract class Fq 
{
	private BigInteger M = new BigInteger("6");
	
	static final BigInteger ZERO = new BigInteger("0");
	
	static final BigInteger ONE = new BigInteger("1");
	
	private BigInteger value;

	public BigInteger getValue()
	{
		return this.value;
	}
	
	public void setModulus(BigInteger M_)
	{
		this.M = M_;
	}
	
	public Fq()
	{
		
	}
	
	public Fq(BigInteger b)
	{
		setValue(b);
	}
	
	public void setValue(int f)
	{
		this.value = new BigInteger(""+f).mod(M);
	}
	
	public void setValue(BigInteger f)
	{
		this.value = f.mod(M);
	}
	
	public String toString()
	{
		return ""+this.value;
	}
	

	public abstract Fq add(Fq f);
	public abstract Fq substract(Fq f);
	public abstract Fq multiply(Fq f);
	public abstract Fq divide(Fq f);
	public abstract Fq inverse();
	public abstract Fq negate();
	
	public  BigInteger upperAdd(Fq f)
	{
		return (this.value.add(f.getValue())).mod(M);
	}

	public  BigInteger upperSubtract(Fq f)
	{
		return (this.value.subtract(f.getValue())).mod(M);
	}

	public  BigInteger upperMultiply(Fq f)
	{
		return (this.value.multiply(f.getValue())).mod(M);
	}
	
	public  BigInteger upperDivide(Fq f)
	{
		return (this.value.multiply(f.getValue().modInverse(M))).mod(M);
	}
	
	public BigInteger upperInverse()
	{
		return this.value.modInverse(M);
	}
	  
	public BigInteger upperNegate()
	{
		return (this.value.negate().mod(M));
	}
	
	public int mod(int n)
	{
		return (getValue().intValue() % n);
	}
	
	@Override
	public boolean equals(Object o)
	{
    // If the object is compared with itself then return true 
    if (o == this) {
        return true;
    }

    /* Check if o is an instance of Complex or not
      "null instanceof [type]" also returns false */
    if (!(o instanceof Fq)) {
        return false;
    }
     
    // typecast o to Complex so that we can compare data members
    Fq f = (Fq) o;
     
    // Compare the data members and return accordingly
    return this.value.equals(f.getValue());
	}
	
	public static void main(String[] args)
	{

	}
}