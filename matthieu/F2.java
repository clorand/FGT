package matthieu;

import java.math.BigInteger;

public class F2 extends Fq {


	private static final int q = 2;
	
	public F2(BigInteger f) {
		super();
		setModulus(new BigInteger(""+q));
		setValue(f);
	}
	
	
	public F2(int i)
	{
		 this(new BigInteger(""+i));
	}


	@Override
	public F2 add(Fq f) 			{ return new F2(upperAdd(f)); }
	
	@Override
	public F2 substract(Fq f) { return new F2(upperSubtract(f));}
	
	@Override
	public F2 multiply(Fq f) { return new F2(upperMultiply(f));}

	@Override
	public F2 divide(Fq f) { return new F2(upperDivide(f));}

	@Override
	public F2 inverse() { return new F2(upperInverse());}

	@Override
	public F2 negate() { return new F2(upperNegate());}

	
	public static void main(String args[])
	{
		F2 ONE  = new F2(new BigInteger(""+1));
		F2 ZERO = new F2(new BigInteger(""+0));
		
		
		System.out.println(ZERO);
		System.out.println(ONE);
		System.out.println(ONE.add(ONE));
		System.out.println(ONE.substract(ONE));
	}
}
