package hotel_mgt;

import static org.junit.Assert.*;

import org.junit.Test;

public class serviceTest {

	@Test
	public void test_addorRemoveResource() {
		//fail("Not yet implemented");
		service s= new service();
		s.set_name("Custom");
		resource r=new resource();
		r.set_resourceName("test");
		r.set_resourceCost(110.0);
		int i=s.resources.size();
		
		s.add_resource(r);
		int j=s.resources.size();
		assertEquals(1,j-i);
		s.remove_resource(r);
		int k=s.resources.size();
		assertEquals(1,j-i);
		assertEquals(1,j-k);
		
		
	}

}
