package matthieu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class SL11Subgroup implements Comparable<SL11Subgroup>
{
	
	private List<Integer> elements = new ArrayList<Integer>();
	private int order;
	
	
	
	public List<Integer> getElements() {
		return elements;
	}

	public void setElements(List<Integer> elements) {
		this.elements = elements;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	
	public SL11Subgroup() throws Exception
	{
		this(121); /*Trivial group by default*/
	}

	public SL11Subgroup(int i) throws Exception
	{
		SL11 generator = new SL11(i);
		SL11 element = new SL11(i);
		elements.add(i);
	
		while (element.getIndex()!=121)
		{
			element = element.multiply(generator);
			elements.add(element.getIndex());
		}
		
		//Collections.sort(elements);
		
		order = elements.size();
	}
	
	private void addElement(int i)
	{
		if (!contains(i))
			elements.add(i);
		
		//Collections.sort(elements);
		order = order+1;
	}
	
	public List<Integer> getLeftCoset(int i) throws Exception
	{
		List<Integer> ret = new ArrayList<Integer>();
		SL11 a = new SL11(i);
		
		for (int e:getElements())
		{
			SL11 h = new SL11(e);
			SL11 ah = a.multiply(h);
			ret.add(ah.getIndex());
		}
		
		return ret;
	}
	
	public List<Integer> getRightCoset(int i) throws Exception
	{
		List<Integer> ret = new ArrayList<Integer>();
		SL11 a = new SL11(i);
		
		for (int e:getElements())
		{
			SL11 h = new SL11(e);
			SL11 ha = h.multiply(a);
			ret.add(ha.getIndex());
		}
		
		return ret;
	}
	

	public static SL11Subgroup generateSubgroup(int a) throws Exception
	{
		return generateSubgroup(a,a);
	}

	
	public static SL11Subgroup generateSubgroup(int a, int b) throws Exception
	{
		SL11Subgroup G = new SL11Subgroup();
		
		if (a==b)
		{
			G = new SL11Subgroup(a);
		}
		else
		{	
			int order = 0;							
			
			while (order != G.getOrder())
			{
				order = G.getOrder();							
				for (int i:G.getLeftCoset(a))
				{
					if (!G.contains(i))
						G.addElement(i);
				}
				
				for (int i:G.getLeftCoset(b))
				{
					if (! G.contains(i))
						G.addElement(i);
				}
				
				for (int i:G.getRightCoset(a))
				{
					if (! G.contains(i))
						G.addElement(i);
				}
				
				for (int i:G.getRightCoset(b))
				{
					if (! G.contains(i))
						G.addElement(i);
				}
				
				//System.out.println("old order: "+order+", new order:"+G.getOrder()+"\n"+G);	
			}
		}
		
		return G;
		
	}
	

	public boolean contains(int g)
	{
		return elements.contains(g);
	}
	
	public List<Integer> getSortedElements()
	{
		List<Integer> sortedElements = new ArrayList<Integer>();
		sortedElements.addAll(elements);
		Collections.sort(sortedElements);
		
		return sortedElements;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getSortedElements());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SL11Subgroup other = (SL11Subgroup) obj;
		return Objects.equals(getSortedElements(), other.getSortedElements());
	}

	@Override
	public int compareTo(SL11Subgroup g) {
		int ret = 0;
		
		if (this.getOrder()!=g.getOrder())
		{
			ret = (this.getOrder()>g.getOrder()?1:-1);
		}
		else
		{
			List<Integer> g1 = this.getElements();
			List<Integer> g2 = g.getElements();
			for (int i=0; i<g.getOrder(); i++)
			{
				if (g1.get(i)!=g2.get(i))
				{
					return g1.get(i).compareTo(g2.get(i));
				}
			}
		}
		
		return ret;
	}

	
	public static List<SL11Subgroup> getCyclesOfOrder(int n) throws Exception
	{
		List<SL11Subgroup> ret = new ArrayList<SL11Subgroup>();
		
		for (int index=11; index<1331; index++)
		{
			SL11Subgroup g = new SL11Subgroup(index);
			if ((g.getOrder()==n)&&(!ret.contains(g)))
			{
				ret.add(g);
			}
		}
		
		return ret;
	}
	
	public String toString()
	{
		String index = "";
		String sep   = "";
		String up    = "";
		String down  = "";
		
		for (int e:elements)
		{
			SL11 sl;
			try {
				sl = new SL11(e);
				index += sl.getIndex()+"\t|";
				up    += sl.getA()+" "+sl.getB()+"\t| " ;
				down  += sl.getC()+" "+sl.getD()+"\t| " ;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		sep = "-"+"-------+".repeat(order);
		
		return          "order: "+getOrder()+"\n"
										+sep  + "\n"
										+index + "\n"
										+sep  + "\n"
				   					+up   + "\n"
				   					+down + "\n"
				   					+sep  + "\n\n";
	}
	

	
	public static void main(String[] args) throws Exception
	{
		
		
		String out = "";

		for (int i=11; i<121; i++)
		{	
			for (int j=11; j<121; j++)
			{
				SL11Subgroup G =  generateSubgroup(i, j);
		    out += G.getOrder()+" \t";
			}
			out += "\n";
			
			System.out.println(""+out);
		}
		
		

		/*
		System.out.println(generateSubgroup(11, 22));
		
		System.out.println(generateSubgroup(22, 363));
		
		System.out.println(generateSubgroup(11, 11));
		
		System.out.println(generateSubgroup(22, 22));
			
		System.out.println(generateSubgroup(363, 363));

		System.out.println(generateSubgroup(242, 242));
		
		int n = 5;

		System.out.println(new SL11(11).pow(n));
		
		System.out.println(new SL11(22).pow(n));
		
		System.out.println(new SL11(242).pow(n));
		
		System.out.println(new SL11(363).pow(n));
		*/
		
		/*
		SL11Subgroup A = generateSubgroup(22); 
		SL11Subgroup B = generateSubgroup(363);
		
		System.out.println(A);
		System.out.println(B);
		
		for (int i:A.elements)
		{
			SL11Subgroup G = new SL11Subgroup();
			for (int j:B.elements)
			{
				SL11 a = new SL11(i);
				SL11 b = new SL11(j);
				G.addElement(a.multiply(b).getIndex());
			}
			System.out.println(G);
		}
	}*/
	}
	
}
	
