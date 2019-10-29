package cart;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class VoidLine {
	
	@Test
	public void voidLine() throws Exception {
		GetDetails start =new GetDetails();
		start.getVoid();
	}

}
