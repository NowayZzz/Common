package zp.com.test;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class TestWebservice {
	@WebMethod
	public String getHello(String name){
		return "Hello "+name;
	}
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9001/service/getHello", new TestWebservice());
		System.out.println("push success!");
	}
}
