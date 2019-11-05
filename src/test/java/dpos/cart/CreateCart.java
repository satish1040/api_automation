package dpos.cart;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class CreateCart {
	
	@Test
	public void cartCreate() throws Exception {
		GetDetails start =new GetDetails();
		start.cartCreate();
	}

}
