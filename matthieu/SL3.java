package matthieu;

public class SL3 
{
	
	private static final int q = 3;
	private static final F3 ZERO = new F3(0);
	private static final F3 ONE = new F3(1);
	
	private F3 a;
	private F3 b;
	private F3 c;
	private F3 d;
	
	
	public SL3(F3 a_, F3 b_, F3 c_, F3 d_)
	{
		a = a_;
		b = b_;
		c = c_;
		d = d_;
	}
	
	
	public SL3(int index) throws Exception
	{
		index = index + q;
		
		F3 u_ = new F3(index % q);
		F3 l_ = new F3((index/q) %q);
		F3 a_ = new F3((index/q/q) %q);
		
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

	public SL3 multiply(SL3 g)
	{
		
		F3 A = a.multiply(g.a).add(b.multiply(g.c));
		F3 B = a.multiply(g.b).add(b.multiply(g.d));
		F3 C = c.multiply(g.a).add(d.multiply(g.c));
		F3 D = c.multiply(g.b).add(d.multiply(g.d));
		
		return new SL3(A, B, C, D);
	}
	
	public F3 determinant()
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
		int o = 0;
		
		for (int i=0; i<q*q*q-q; i++)
		{
			SL3 g = new SL3(i);
			System.out.println(""+i+"\n"+g);
			System.out.println("det="+g.determinant());
			o ++;
		}
		
		System.out.println(""+o+" elements detected");
		
	}

}
