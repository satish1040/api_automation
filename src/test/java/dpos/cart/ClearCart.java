package dpos.cart;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class ClearCart {
	
	@Test
	public void clear() throws Exception {
		GetDetails start =new GetDetails();
		start.clearCart();
	}

}
