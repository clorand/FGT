package matthieu;

import java.math.BigInteger;

public class F5 extends Fq {

	private static final int q = 5;

	public F5(BigInteger f) {
		super();
		setModulus(new BigInteger(""+q));
		setValue(f);
	}
	
	
	public F5(int i)
	{
		 this(new BigInteger(""+i));
	}


	@Override
	public F5 add(Fq f) 			{ return new F5(upperAdd(f)); }
	
	@Override
	public F5 substract(Fq f) { return new F5(upperSubtract(f));}
	
	@Override
	public F5 multiply(Fq f) { return new F5(upperMultiply(f));}

	@Override
	public F5 divide(Fq f) { return new F5(upperDivide(f));}

	@Override
	public F5 inverse() { return new F5(upperInverse());}

	@Override
	public F5 negate() { return new F5(upperNegate());}

	
	public static void main(String args[])
	{
		F5 ZERO = new F5(new BigInteger(""+0));
		F5 ONE  = new F5(new BigInteger(""+1));
		F5 TWO  = new F5(new BigInteger(""+2));
		
		
		System.out.println(ZERO);
		System.out.println(ONE);
		System.out.println(ONE.add(ONE));
		System.out.println(ONE.substract(ONE));
		System.out.println(ONE.divide(TWO));
	}
}
