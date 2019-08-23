package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface demo {
	@WebMethod
	public String Hello() ;
}
