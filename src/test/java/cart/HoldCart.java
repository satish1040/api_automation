package cart;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class HoldCart {
	
	@Test
	public void holdCart() throws Exception {
		GetDetails start =new GetDetails();
		start.cartHold();
	}

}
