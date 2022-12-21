package matthieu;

public class SL11 
{
	private F11 a;
	private F11 b;
	private F11 c;
	private F11 d;
	
	public SL11(F11 a_, F11 l_, F11 u_)
	{
		this.a = a_;
		this.b = a_.multiply(u_);
		this.c = l_.divide(a_);
		this.d = (new F11(1).add(l_.multiply(u_))).divide(a_);
	}
	
	public String toString()
	{
		return ""+a+" "+b+"\n"+""+c+" "+d+"\n";
	}
	
	public static void main(String[] args)
	{
		F11 a = new F11(1);
		F11 l = new F11(2);
		F11 u = new F11(3);
		
		SL11 sl = new SL11(a, l , u);
		
		System.out.println(""+sl);
		int n = 0;
		for (int i=1; i<11; i++)
			for (int j=0; j<11; j++)
				for (int k=0; k<11; k++)
				{
					a = new F11(i);
					l = new F11(j);
					u = new F11(k);
					sl = new SL11(a, l, u);
					System.out.println(""+n+"\n"+sl);
					n++;
				}
	}

}
