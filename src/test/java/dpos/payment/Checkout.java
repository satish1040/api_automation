package dpos.payment;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class Checkout {
	
	@Test
	public void createOrder() throws Exception {
		GetDetails start =new GetDetails();
		start.checkout();
	}

}
