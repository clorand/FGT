package matthieu;

import java.math.BigInteger;

public class F6 extends Fq {

	private static final int q = 6;

	public F6(BigInteger f) {
		super();
		setModulus(new BigInteger(""+q));
		setValue(f);
	}
	
	
	public F6(int i)
	{
		 this(new BigInteger(""+i));
	}


	@Override
	public F6 add(Fq f) 			{ return new F6(upperAdd(f)); }
	
	@Override
	public F6 substract(Fq f) { return new F6(upperSubtract(f));}
	
	@Override
	public F6 multiply(Fq f) { return new F6(upperMultiply(f));}

	@Override
	public F6 divide(Fq f) { return new F6(upperDivide(f));}

	@Override
	public F6 inverse() { return new F6(upperInverse());}

	@Override
	public F6 negate() { return new F6(upperNegate());}

	
	public static void main(String args[])
	{
		F6 ZERO = new F6(new BigInteger(""+0));
		F6 ONE  = new F6(new BigInteger(""+1));
		F6 TWO  = new F6(new BigInteger(""+2));
		F6 FIVE  = new F6(new BigInteger(""+5));
		
		
		System.out.println(ZERO);
		System.out.println(ONE);
		System.out.println(ONE.add(ONE));
		System.out.println(ONE.substract(ONE));
		System.out.println(ONE.divide(FIVE));
	}
}
