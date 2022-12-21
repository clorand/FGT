package matthieu;

public class SL11 
{
	private F11 a;
	private F11 b;
	private F11 c;
	private F11 d;
	
	private int index;
	
	private static final F11 ZERO =  new F11(0);
	private static final F11  ONE =  new F11(1);
	
	public SL11(int index) throws Exception
	{
		this(index/121, (index/11)%11, index%11);
	}
	
	public SL11(int i, int j, int k) throws Exception
	{		
		 this(new F11(i), new F11(j), new F11(k));
		 this.setIndex(121*i+11*j+k);
	}
	
	private SL11(F11 a_, F11 l_, F11 u_) throws Exception
	{
		if (a_.equals(ZERO))
		{
			if (!l_.equals(ZERO))
			{
				this.a = a_;
				this.b = ZERO.subtract(l_.inverse());
				this.c = l_;
				this.d = u_.multiply(l_);
			}
			else
			{
				throw new Exception("a and l parameters cannot be both zeros: index must be between 11 and 1330");
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
	
	public int determinant()
	{
		F11 det = a.multiply(d).subtract(b.multiply(c));
		return det.getValue().intValue();
	}
	
	public SL11 multiply(SL11 sl) throws Exception
	{
		F11 a = this.a.multiply(sl.a).add(this.b.multiply(sl.c));
		F11 b = this.a.multiply(sl.b).add(this.b.multiply(sl.d));
		F11 c = this.c.multiply(sl.a).add(this.d.multiply(sl.c));
		F11 d = this.c.multiply(sl.b).add(this.d.multiply(sl.d));
		
		int i=0, j=0, k=0;
		
		if (ZERO.equals(a))
		{
			i = 0;
			j = c.getValue().intValue();
			k = (d.divide(c)).getValue().intValue();
		}
		else
		{
			i = a.getValue().intValue();
			j = (c.multiply(a)).getValue().intValue();
			k = (b.divide(a)).getValue().intValue();
		}
		
		return new SL11(i, j, k);
	}
	
	@Override
	public boolean equals(Object o)
	{
    if (o == this) {
        return true;
    }
    
    if (!(o instanceof SL11)) {
        return false;
    }
     
    SL11 sl = (SL11) o;
    
    return (this.a.equals(sl.a))
    		 &&(this.b.equals(sl.b))
    		 &&(this.c.equals(sl.c))
    		 &&(this.d.equals(sl.d)); 
	}
	
	
	
	public String toString()
	{
		return ""+a+" "+b+"\n"+""+c+" "+d+"\n";
	}

	
	public static void main(String[] args)
	{
	
		SL11 sl;
	
		int n = 0;
		for (int i=0; i<11; i++)
			for (int j=0; j<11; j++)
				for (int k=0; k<11; k++)
				{
					try {
						sl = new SL11(i, j, k);
						int k_ = n%11;
						int j_ = (n/11)%11;
						int i_ = (n/121)%11;
						int n_ = 121*i+11*j+k;
						//System.out.println("n:"+n+", n_:"+n_+", index:"+sl.index);
						System.out.println((sl.equals(new SL11(n)))+"\n"+sl);
						//System.out.println("i :"+i+", j :"+j+", k :"+k+"\ni_:"+i_+", j_:"+j_+", k_:"+k_);
					}
					catch(Exception e)
					{
						System.out.println(""+n+"\n"+e);
					}
					n++;
				}
		
		
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
