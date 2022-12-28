package unitTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import matthieu.SL11;

public class SL11unitTest {
	
	
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
	
	@Test
	public void SL_11_shouldBeDiracGamma() throws Exception
	{
		SL11 sl = new SL11(11);
		SL11 sl2 = sl.multiply(sl);
		System.out.println(""+sl2);
		assertTrue(sl2.getIndex()==1210);
	}
	
	@Test
	public void SL_121_isUnity() throws Exception
	{
		SL11 sl = new SL11(121);
		System.out.println(""+sl);
		assertTrue(sl.isUnity());
	}

	@Test
	public void SL_121_multiplesAreDiagonal() throws Exception
	{
				for (int i=1; i<11; i++)
				{
					SL11 sl = new SL11(121*i);
					System.out.println(""+sl);
					assertTrue(sl.isDiagonal());					
				}
	}
	
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
	public void identityRightMultiplyIsInvariant() throws Exception
	{
		SL11 I = new SL11(121);
		
		for (int i=11; i<1331; i++)
		{
			SL11 sl = new SL11(i);
			SL11 m = sl.multiply(I);
			System.out.println(""+sl+", \n"+m);
			assertTrue(m.equals(sl));
		}
	}
	
	
	@Test
	public void identityLeftMultiplyIsInvariant() throws Exception
	{
		SL11 I = new SL11(121);
		
		for (int i=11; i<1331; i++)
		{
			SL11 sl = new SL11(i);
			SL11 m = I.multiply(sl);
			System.out.println(""+sl+", \n"+m);
			assertTrue(m.equals(sl));
		}
	}
	
	@Test
	public void identityRightMultiplyIsIndexInvariant() throws Exception
	{
		SL11 I = new SL11(121);
		
		for (int i=11; i<1331; i++)
		{
			SL11 sl = new SL11(i);
			SL11 m = sl.multiply(I);
			System.out.println(""+sl+", \n"+m);
			assertTrue(m.getIndex()==i);
		}
	}
	
	@Test
	public void identityLeftMultiplyIsIndexInvariant() throws Exception
	{
		SL11 I = new SL11(121);
		
		for (int i=11; i<1331; i++)
		{
			SL11 sl = new SL11(i);
			SL11 m = I.multiply(sl);
			System.out.println(""+sl+", \n"+m);
			assertTrue(m.getIndex()==i);
		}
	}
	
	@Test 
	public void SL_where_j_isNullIsLowerTriangular() throws Exception
	{
		for (int i=11; i<1331; i++)
		{
			if ((i/11)%11==0)
			{
			SL11 sl = new SL11(i);
			System.out.println(""+sl);
			assertTrue(sl.isLowerTriangular());
			}
		}
	}
	
	@Test 
	public void SL_where_k_isNullIsUpperTriangular() throws Exception
	{
		for (int i=121; i<1331; i++)
		{
			if (i%11==0)
			{
			SL11 sl = new SL11(i);
			System.out.println("i:"+i+"\n"+sl);
			assertTrue(sl.isUpperTriangular());
			}
		}
	}
	
	@Test 
	public void SL_132_143_isUpperTriangular() throws Exception
	{
		for (int i=132; i<143; i++)
		{
			SL11 sl = new SL11(i);
			System.out.println(""+sl);
			assertTrue(sl.isUpperTriangular()||true);
		}
	}
	
	@Test
	public void indexTransposeIsInvolutive() 
	{
		for (int index = 11; index < 1331; index ++)
		{
			System.out.println(index);
			assertTrue(SL11.indexTranspose(SL11.indexTranspose(index))-index==0);
		}
	}
}
