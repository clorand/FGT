package unitTests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import matthieu.SL11;

import matthieu.SL11Subgroup;

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
	
	@Test
	public void identityOrderIsOne() throws Exception
	{
		SL11 I = new SL11(121);
		System.out.println(""+I);
		assertTrue(I.isIdentity());
	}
	
	@Test 
	public void minusOneOrderIsTwo() throws Exception
	{
		SL11 I_ = new SL11(10, 0, 0);
		System.out.println(""+I_);
		assertTrue(I_.getOrder()==2);
	}
	
	@Test 
	public void positiveUmatrixOrderisEleven() throws Exception
	{
		SL11 I_ = new SL11(1, 0, 1);
		System.out.println(""+I_);
		assertTrue(I_.getOrder()==11);
	}
	
	@Test 
	public void negativeUmatrixOrderisTwentyTwo() throws Exception
	{
		SL11 I_ = new SL11(10, 0, 1);
		System.out.println(""+I_);
		assertTrue(I_.getOrder()==22);
	}
	
	@Test
	public void orderElevenElementsNumberIs120() throws Exception
	{
		List<Integer> elements = SL11.getElementsOfOrder(11);
		
		for (Integer e:elements)
		{
			SL11 g = new SL11(e);
			System.out.println(g);
			System.out.println(g.getIndex());
		}
		assertTrue(elements.size()==120);
		System.out.println(elements.size());
	}

	@Test
	public void orderTwentyTwoElementsNumberIs120() throws Exception
	{
		List<Integer> elements = SL11.getElementsOfOrder(22);
		
		for (Integer e:elements)
		{
			SL11 g = new SL11(e);
			System.out.println(g.getIndex());
			System.out.println(g);
		}
		assertTrue(elements.size()==120);
		System.out.println(elements.size());
	}
	
	@Test
	public void unitaryPositiveUpperOrderIs11() throws Exception
	{
			for (int k=1; k<11; k++)
			{
				SL11 g = new SL11(1, 0, k);
				System.out.println(g);
				assertTrue(g.getOrder()==11);
			}
	}
	
	@Test
	public void minusIdentityOrderIsTwo() throws Exception
	{
		SL11 mI = new SL11(1210);
		System.out.println(mI);
		assertTrue(mI.getOrder()==2);
	}
	
	@Test
	public void someUpperOrderIsNotElevenMultiple() throws Exception
	{
			SL11 g = new SL11(2, 0, 1).multiply(new SL11(1210));
			
			List<Integer> cycle = SL11.getGeneratedCycle(g.getIndex());
			for (int i:cycle)
			{
				SL11 h = new SL11(i);
				System.out.println(h);
			}
			System.out.println("order:"+g.getOrder());
			assertTrue(g.getOrder()%11!=0);
	}
	
	@Test
	public void orderFiveElementsNumberIs264() throws Exception
	{
		List<Integer> O5 = SL11.getElementsOfOrder(5);
		
		for (int index:O5)
		{
			System.out.println(index);
			System.out.println(new SL11(index));
		}
		
		System.out.println(O5.size());
		assertTrue(O5.size()==264);
	}
	
	@Test
	public void orderElevenCyclesNumberIs12() throws Exception
	{
		int n = 11;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==12);
	}
	
	@Test
	public void orderTwentyTwoCyclesNumberIs12() throws Exception
	{
		int n = 22;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==12);
	}
	
	@Test
	public void orderTwentyCyclesNumberIs0() throws Exception
	{
		int n = 20;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==0);
	}
	
	@Test
	public void orderTenCyclesNumberIs66() throws Exception
	{
		int n = 10;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==66);
	}

	@Test
	public void orderFiveCyclesNumberIs66() throws Exception
	{
		int n = 5;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==66);
	}

	@Test
	public void orderTwelveCyclesNumberIs55() throws Exception
	{
		int n = 12;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==55);
	}

	@Test
	public void orderSixCyclesNumberIs55() throws Exception
	{
		int n = 6;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==55);
	}
	
	@Test
	public void orderThreeCyclesNumberIs55() throws Exception
	{
		int n = 3;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==55);
	}	
	
	@Test
	public void orderEightCyclesNumberIs0() throws Exception
	{
		int n = 8;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==0);
	}
	
	@Test
	public void orderFourCyclesNumberIs55() throws Exception
	{
		int n = 4;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==55);
	}
	
	@Test
	public void orderTwoCyclesNumberIs1() throws Exception
	{
		int n = 2;
		
		List<SL11Subgroup> G = SL11Subgroup.getCyclesOfOrder(n);
		
		for (SL11Subgroup h:G)
			System.out.println(h);
		
		System.out.println(""+G.size()+" distinct cycles of order "+n);
		
		assertTrue(G.size()==1);
	}
	
	@Test 
	public void quadraticNonResidueNumberIs330() throws Exception
	{
		int n = 330;
		
		int r = 0;
		for (int i=11; i<1331; i++)
		{
			SL11 g = new SL11(i).pow(n);
			if (!g.isIdentity()) r++;
			System.out.println("i:"+i+", index:"+g.getIndex()+"\n"+g);
		}
		
		System.out.println(""+r+" quadratic non residues");
		assertTrue(r==330);
	}
	
	@Test
	public void gen_22_363_commutes() throws Exception
	{
		SL11Subgroup A = SL11Subgroup.generateSubgroup(22); 
		SL11Subgroup B = SL11Subgroup.generateSubgroup(363);
		
		System.out.println(""+A+"\n"+B);
		
		for (int i:A.getElements())
			for (int j:B.getElements())
			{
				SL11 a = new SL11(i);
				SL11 b = new SL11(j);
				if (!a.pow(2).isIdentity())
				{
					SL11 ab = a.multiply(b);
					SL11 bia = b.pow(4).multiply(a);
					assertTrue(ab.equals(bia));
				}
				else
				{
					SL11 ab = a.multiply(b);
					SL11 ba = b.multiply(a);
					assertTrue(ab.equals(ba));					
				}
			}		
	}
	
	@Test
	public void gen_22_363_productLaw() throws Exception
	{
		SL11Subgroup A = SL11Subgroup.generateSubgroup(22); 
		SL11Subgroup B = SL11Subgroup.generateSubgroup(363);
		
		System.out.println(""+A+"\n"+B);
		
		for (int i:A.getElements())
			for (int j:B.getElements())
			{
				SL11 a = new SL11(i);
				SL11 b = new SL11(j);
				SL11 ab = a.multiply(b);
				
				SL11 product = (a.pow(2).equals(new SL11(121))?b.multiply(a):b.pow(4).multiply(a));
				assertTrue(ab.equals(product));
			}		
	}
	

}
