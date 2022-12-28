package matthieu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SL11Subgroup 
{
	
	private List<Integer> elements = new ArrayList<Integer>();
	private int order;
	
	
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
		
		Collections.sort(elements);
		
		order = elements.size();
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		sep = "-"+"-------+".repeat(order);
		
		return          sep  + "\n"+
										index + "\n"
										+sep  + "\n"
				   					+up   + "\n"
				   					+down + "\n"
				   					+sep  + "\n\n";
	}
	
	public static void main(String[] args) throws Exception
	{
		for(int generator = 11; generator<1331; generator++)
		{
			SL11Subgroup g = new SL11Subgroup(generator);
			if (g.order==22)
				System.out.println(""+g);
		}
	}

}
