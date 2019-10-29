package cart;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class AuthTokenVerifyer {
	
	@Test
	public void tokenValidation() throws Exception {
		GetDetails start =new GetDetails();
		start.verifyToken();
	}

}
