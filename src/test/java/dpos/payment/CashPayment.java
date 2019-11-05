package dpos.payment;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class CashPayment {
	
	@Test
	public void paymentCash() throws Exception {
		GetDetails start =new GetDetails();
		start.payment();
	}

}
