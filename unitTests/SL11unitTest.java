package unitTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import matthieu.SL11;

public class SL11unitTest {
	
	@Test
	public void indexShouldEqualConstructorArgument() 
	{
		
		for (int index=0; index<1331; index++)
		try {
				SL11 sl = new SL11(index);
				assertTrue(sl.getIndex()==index);
			}catch(Exception e) 
		 {
				System.out.println(""+e);
		 }
	}
	
	@Test
	public void determinantShouldRemainOneAfterProduct()
	{
		for (int i=11; i<1331; i++)
			for (int j=11; j<1331; j++)
			{
				try {
					SL11 sl1 = new SL11(i);
					SL11 sl2 = new SL11(j);
					SL11 sl = sl1.multiply(sl2);
					System.out.println("i:"+i+", j:"+j+", p:"+sl.getIndex());
					assertTrue(sl.determinant()==1);
				}
				catch(Exception e)
				{
					System.out.println(""+e);
				}
				
			}
		
	}

}
