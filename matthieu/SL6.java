package matthieu;

public class SL6 
{
	
	private static final int q = 6;
	private static final F6 ZERO = new F6(0);
	private static final F6 ONE = new F6(1);
	
	private F6 a;
	private F6 b;
	private F6 c;
	private F6 d;
	
	public SL6(F6 a_, F6 b_, F6 c_, F6 d_)
	{
		a = a_;
		b = b_;
		c = c_;
		d = d_;
	}
	
	
	public SL6(int index) throws Exception
	{
		index = index + q;
		
		F6 u_ = new F6(index % q);
		F6 l_ = new F6((index/q) %q);
		F6 a_ = new F6((index/q/q) %q);
		
		
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

	public SL6 multiply(SL6 g)
	{
		
		F6 A = a.multiply(g.a).add(b.multiply(g.c));
		F6 B = a.multiply(g.b).add(b.multiply(g.d));
		F6 C = c.multiply(g.a).add(d.multiply(g.c));
		F6 D = c.multiply(g.b).add(d.multiply(g.d));
		
		return new SL6(A, B, C, D);
	}
	
	public F6 determinant()
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
			SL6 g = new SL6(i);
			System.out.println(""+i+"\n"+g);
		}
		
	}

}
