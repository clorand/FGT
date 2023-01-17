package matthieu;

public class SL5 
{
	
	private static final int q = 5;
	private static final F5 ZERO = new F5(0);
	private static final F5 ONE = new F5(1);
	
	private F5 a;
	private F5 b;
	private F5 c;
	private F5 d;
	
	public SL5(F5 a_, F5 b_, F5 c_, F5 d_)
	{
		a = a_;
		b = b_;
		c = c_;
		d = d_;
	}
	
	
	public SL5(int index) throws Exception
	{
		index = index + q;
		
		F5 u_ = new F5(index % q);
		F5 l_ = new F5((index/q) %q);
		F5 a_ = new F5((index/q/q) %q);
		
		
		if (a_.equals(ZERO))
		{
			if (!l_.equals(ZERO))
			{
				this.a = a_;
				this.b = ZERO.substract(l_.inverse());
				this.c = l_;
				this.d = u_.multiply(l_);
			}
			else
			{
				throw new Exception("a and l parameters cannot be both zeros: index must be between q and q^3");
			}
		}
		else
		{
			this.a = a_;
			this.b = a_.multiply(u_);
			this.c = l_.divide(a_);
			this.d = (ONE.add(l_.multiply(u_))).divide(a_);
		}
		
	}

	public SL5 multiply(SL5 g)
	{
		
		F5 A = a.multiply(g.a).add(b.multiply(g.c));
		F5 B = a.multiply(g.b).add(b.multiply(g.d));
		F5 C = c.multiply(g.a).add(d.multiply(g.c));
		F5 D = c.multiply(g.b).add(d.multiply(g.d));
		
		return new SL5(A, B, C, D);
	}
	
	public F5 determinant()
	{
		return a.multiply(d).substract(b.multiply(c));
	}
	
	public String toString()
	{ 		
		return ""+a+" "+b+"\n"
					+""+c+" "+d+"\n";
	}
	
	public static void main(String[] args) throws Exception
	{
	
		for (int i=0; i<q*q*q-q; i++)
		{
			SL5 g = new SL5(i);
			System.out.println(""+i+"\n"+g);
		}
		
	}

}
