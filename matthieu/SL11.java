package matthieu;

import java.io.FileWriter;
import java.io.IOException;

public class SL11 
{
	private F11 a;
	private F11 b;
	private F11 c;
	private F11 d;
	
	private int index;
	
	private static final F11 ZERO =  new F11(0);
	private static final F11  ONE =  new F11(1);
	
	public F11 getA() {
		return a;
	}

	public void setA(F11 a) {
		this.a = a;
	}
	
	public F11 getB() {
		return b;
	}

	public void setB(F11 b) {
		this.b = b;
	}

	public F11 getC() {
		return c;
	}

	public void setC(F11 c) {
		this.c = c;
	}

	public F11 getD() {
		return d;
	}

	public void setD(F11 d) {
		this.d = d;
	}
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
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
				this.setA(a_);
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
			this.setA(a_);
			this.b = a_.multiply(u_);
			this.c = l_.divide(a_);
			this.d = (ONE.add(l_.multiply(u_))).divide(a_);
		}
	}
	
	public int determinant()
	{
		F11 det = getA().multiply(d).subtract(b.multiply(c));
		return det.getValue().intValue();
	}
	
	public SL11 multiply(SL11 sl) throws Exception
	{
		F11 a = this.getA().multiply(sl.getA()).add(this.b.multiply(sl.c));
		F11 b = this.getA().multiply(sl.b).add(this.b.multiply(sl.d));
		F11 c = this.c.multiply(sl.getA()).add(this.d.multiply(sl.c));
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
    
    return (this.getA().equals(sl.getA()))
    		 &&(this.b.equals(sl.b))
    		 &&(this.c.equals(sl.c))
    		 &&(this.d.equals(sl.d)); 
	}
	
	public boolean isUnity()
	{
		return   getA().equals(ONE)
					&& b.equals(ZERO)
					&& c.equals(ZERO)
					&& d.equals(ONE);
	}
	
	public boolean isDiagonal() {
		return b.equals(ZERO)
				&& c.equals(ZERO);
	}
	
	public boolean isLowerTriangular() {
		return c.equals(ZERO);
	}

	public boolean isUpperTriangular() {
		return b.equals(ZERO);
	}

	
	public String toString()
	{
		return ""+getA()+" "+b+"\n"+""+c+" "+d+"\n";
	}
	
	public static int indexTranspose(int index)
	{
		if (index < 121)
		{
			return index;
		}
		else
		{
		int i = index/121 % 11;
		int j = index/11 % 11;
		int k = index %11;
		
		return (121*i + 11*k + j);
		}
	}

	public static void generateMultiplicationTable()
	{
		FileWriter fw = null;
		
    try {
      fw = new FileWriter("MultiplicationTable.txt");  
  		for (int i=11; i<1331; i++)
  		{
  			String line = "";		 
  			for (int j=11; j<1331; j++)
  			{
  				try 
  				{
  					SL11 sl1 = new SL11(i);
  					SL11 sl2 = new SL11(j);
  					SL11 sl = sl1.multiply(sl2);
  					//System.out.println("i:"+i+", j:"+j+", p:"+sl.getIndex());
  					line += ""+sl.getIndex()+" ";
  				}
  				catch(Exception e)
  				{
  					System.out.println(""+e);
  				}	
  			}
  			fw.write(line+"\n");
  		}
      fw.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }		
	}
	
	public static void generateTransposeMultiplicationTable()
	{
		FileWriter fw = null;
		
    try {
      fw = new FileWriter("TransposeMultiplicationTable.txt");  
  		for (int i=11; i<1331; i++)
  		{
  			String line = "";		 
  			for (int j=11; j<1331; j++)
  			{
  				try 
  				{
  					SL11 sl1 = new SL11(indexTranspose(i));
  					SL11 sl2 = new SL11(j);
  					SL11 sl = sl1.multiply(sl2);
  					line += ""+sl.getIndex()+" ";
  				}
  				catch(Exception e)
  				{
  					System.out.println(""+e);
  				}	
  			}
  			fw.write(line+"\n");
  		}
      fw.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }		
	}

	
	public static void generateUpperTable()
	{
		FileWriter fw = null;
		
    try {
      fw = new FileWriter("UpperTable.txt");  
  		for (int i=11; i<1331; i++)
  		{
  			String line = "";		 
  			for (int j=11; j<1331; j++)
  			{
  				try 
  				{
  					SL11 sl1 = new SL11(indexTranspose(i));
  					SL11 sl2 = new SL11(j);
  					SL11 sl = sl1.multiply(sl2);
  					//System.out.println("i:"+i+", j:"+j+", p:"+sl.getIndex());
  					int isUpper = (sl.isUpperTriangular()?1:0);
  					line += ""+isUpper+" ";
  				}
  				catch(Exception e)
  				{
  					System.out.println(""+e);
  				}	
  			}
  			fw.write(line+"\n");
  		}
      fw.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
		
	}
	
	public static void generateLowerTable()
	{
		FileWriter fw = null;
		
    try {
      fw = new FileWriter("LowerTable.txt");  
  		for (int i=11; i<1331; i++)
  		{
  			String line = "";		 
  			for (int j=11; j<1331; j++)
  			{
  				try 
  				{
  					SL11 sl1 = new SL11(indexTranspose(i));
  					SL11 sl2 = new SL11(j);
  					SL11 sl = sl1.multiply(sl2);
  					//System.out.println("i:"+i+", j:"+j+", p:"+sl.getIndex());
  					int isUpper = (sl.isLowerTriangular()?1:0);
  					line += ""+isUpper+" ";
  				}
  				catch(Exception e)
  				{
  					System.out.println(""+e);
  				}	
  			}
  			fw.write(line+"\n");
  		}
      fw.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }		
	}
	
	public static void generateDiagonalTable()
	{
		FileWriter fw = null;
		
    try {
      fw = new FileWriter("DiagonalTable.txt");  
  		for (int i=11; i<1331; i++)
  		{
  			String line = "";		 
  			for (int j=11; j<1331; j++)
  			{
  				try 
  				{
  					SL11 sl1 = new SL11(indexTranspose(i));
  					SL11 sl2 = new SL11(j);
  					SL11 sl = sl1.multiply(sl2);
  					//System.out.println("i:"+i+", j:"+j+", p:"+sl.getIndex());
  					int isUpper = (sl.isDiagonal()?1:0);
  					line += ""+isUpper+" ";
  				}
  				catch(Exception e)
  				{
  					System.out.println(""+e);
  				}	
  			}
  			fw.write(line+"\n");
  		}
      fw.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }		
	}

	
	
	public int getOrder() throws Exception
	{
		SL11 sl = new SL11(index);
		int order = 1;
	
		while (sl.getIndex()!=121)
		{
			sl = sl.multiply(this);
			order ++;
		}
		
		return order;
	}
	
	public static void main(String[] args) throws Exception
	{
		
		//generateMultiplicationTable();
		//generateTransposeMultiplicationTable();
		//generateUpperTable();
		//generateLowerTable();
		//generateDiagonalTable();

		/*
		 * 
		int maxOrder = 0;
		for (int index = 11; index<1331; index++)
		{
			SL11 sl = new SL11(index);
			int order = sl.getOrder();
			if (order>maxOrder)
				maxOrder = order;
			if (order==3)
			System.out.println("index:"+sl.getIndex()+", order: "+order);
		}
		
		System.out.println("max order="+maxOrder);
		*/
	}

	
}
