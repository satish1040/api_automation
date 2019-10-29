package cart;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class ChangeQuantity {
	
	@Test
	public void addItem() throws Exception {
		GetDetails start =new GetDetails();
		start.changeQuantity();
	}

}
