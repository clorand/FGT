package matthieu;

public class SL2 
{
	
	private static final int q = 2;
	private static final F2 ZERO = new F2(0);
	private static final F2 ONE = new F2(1);
	
	private F2 a;
	private F2 b;
	private F2 c;
	private F2 d;
	
	
	public SL2(F2 a_, F2 b_, F2 c_, F2 d_)
	{
		a = a_;
		b = b_;
		c = c_;
		d = d_;
	}
	
	
	public SL2(int index) throws Exception
	{
		index = index + q;
		
		F2 u_ = new F2(index % q);
		F2 l_ = new F2((index / q) %q);
		F2 a_ = new F2((index / q / q) %q);
		
		
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

	public SL2 multiply(SL2 g)
	{
		
		F2 A = a.multiply(g.a).add(b.multiply(g.c));
		F2 B = a.multiply(g.b).add(b.multiply(g.d));
		F2 C = c.multiply(g.a).add(d.multiply(g.c));
		F2 D = c.multiply(g.b).add(d.multiply(g.d));
		
		return new SL2(A, B, C, D);
	}
	
	public F2 determinant()
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
			SL2 g = new SL2(i);
			System.out.println(""+i+"\n"+g);
		}
		
	}

}
