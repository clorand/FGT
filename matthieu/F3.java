package matthieu;

import java.math.BigInteger;

public class F3 extends Fq {

	private static final int q = 3;

	public F3(BigInteger f) {
		super();
		setModulus(new BigInteger(""+q));
		setValue(f);
	}
	
	
	public F3(int i)
	{
		 this(new BigInteger(""+i));
	}


	@Override
	public F3 add(Fq f) 			{ return new F3(upperAdd(f)); }
	
	@Override
	public F3 substract(Fq f) { return new F3(upperSubtract(f));}
	
	@Override
	public F3 multiply(Fq f) { return new F3(upperMultiply(f));}

	@Override
	public F3 divide(Fq f) { return new F3(upperDivide(f));}

	@Override
	public F3 inverse() { return new F3(upperInverse());}

	@Override
	public F3 negate() { return new F3(upperNegate());}

	
	public static void main(String args[])
	{
		F3 ZERO = new F3(new BigInteger(""+0));
		F3 ONE  = new F3(new BigInteger(""+1));
		F3 TWO  = new F3(new BigInteger(""+2));
		
		
		System.out.println(ZERO);
		System.out.println(ONE);
		System.out.println(ONE.add(ONE));
		System.out.println(ONE.substract(ONE));
		System.out.println(ONE.divide(TWO));
	}
}
