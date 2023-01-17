package matthieu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	public SL11 transpose() throws Exception
	{
		SL11 ret = null;
		int index = this.getIndex();
		
		int i = index / 121;
		int j = index / 11 % 11;
		int k = index % 11;
		
		if (i == 0)
		{
			F11 l = new F11(j);
			F11 u = new F11(k);
			F11 l_ = l.inverse().negate();
			F11 u_ = ZERO.subtract(u.multiply(l).multiply(l));
			ret = new SL11(i, l_.getValue().intValue(), u_.getValue().intValue());
		}
		else 
		{
			ret = new SL11(i, k, j);
		}
		
		
		return ret;
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
			this.a = a_;
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
		
	public SL11 pow(int n) throws Exception
	{
		SL11 ret = new SL11(121);
		SL11 g = this.copy();
		
		if (n%2==1)
			ret = ret.multiply(g);
		
		int i = n/2;
		
		while (i>0)
		{
			g = g.multiply(g);
			if (i%2==1)
				ret = ret.multiply(g);
			i = i/2;
		}
		
		return ret;
	}
	
	public SL11 copy() throws Exception
	{
		return new SL11(this.getIndex());
	}
	
	public boolean isIdentity()
	{
		return this.getIndex()==121;
	}
	
	public int getOrder(int i) throws Exception
	{
		int order = 1;
		SL11 sl = new SL11(i);
		SL11 g = sl.copy();
		
		while (!sl.isIdentity())
		{
			sl = sl.multiply(g);
		}
		
		return order;
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
	
	public boolean isMinusUnity()
	{
		return   getA().equals(new F11(10))
					&& b.equals(ZERO)
					&& c.equals(ZERO)
					&& d.equals(new F11(10));
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

	public SL11 negate() throws Exception
	{
		SL11 ret = new SL11(this.getIndex());
		return ret.multiply(new SL11(1210));
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

	public static void generateA0Table() throws Exception
	{
		FileWriter fw = null;
		
    try {
      fw = new FileWriter("A0Table.txt");  
      
  		for (int i=11; i<121; i++)
  		{
  			String line = "";		 
  			for (int j=11; j<121; j++)
  			{
  				SL11Subgroup G =  SL11Subgroup.generateSubgroup(i, j);
  				line += G.getOrder()+" \t";

  			}
  			System.out.println(""+i+": \t"+line);
  			fw.write(line+"\n");
  		}
      fw.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }		
	}
	
	public static void generateA0TransposeTable() throws Exception
	{
		FileWriter fw = null;
		
    try {
      fw = new FileWriter("A0TransposeTable.txt");  
      
  		for (int i=11; i<121; i++)
  		{
  			String line = "";		 
  			int i_ = new SL11(i).transpose().getIndex();
  			for (int j=11; j<121; j++)
  			{
  				SL11Subgroup G =  SL11Subgroup.generateSubgroup(i_, j);
  				line += G.getOrder()+" \t";

  			}
  			System.out.println(""+i+": \t"+line);
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
	
	public static List<Integer> getElementsOfOrder(int n) throws Exception
	{
		List<Integer> elements = new ArrayList<Integer>();
		
		for (int index=11; index<1331;index++)
		{
			SL11 sl = new SL11(index);
			if ((sl.getOrder()==n)&&!(elements.contains(sl.getIndex())))
				elements.add(sl.getIndex());
		}
		
		Collections.sort(elements);
		
		return elements;
	}
	
	public static List<Integer> getGeneratedCycle(int n) throws Exception
	{
		List<Integer> elements = new ArrayList<Integer>();
		SL11 g = new SL11(n);
		SL11 sl = new SL11(n);
		int order = g.getOrder();
		elements.add(121);
		elements.add(g.getIndex());
		
		
		for (int i=2; i<order; i++)
		{
			sl = sl.multiply(g);
			elements.add(sl.getIndex());
		}
		
		Collections.sort(elements);
		return elements;
	}
	
	public static void main(String[] args) throws Exception
	{

		long startTime = System.nanoTime();
		
		generateA0TransposeTable();
		
    long elapsedTime = System.nanoTime() - startTime;
    
    System.out.println("Execution Time: "
            + elapsedTime/1000000000
            + " seconds.");

	}	
}
