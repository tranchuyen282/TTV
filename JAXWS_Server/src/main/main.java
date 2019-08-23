package main;

import javax.xml.ws.*;
import ws.DemoImpl;

public class main {
	public static void main(String[] args) {
		try {
			Endpoint.publish("http://localhost:9000/ws/demo", new DemoImpl());
			System.out.println("Connect...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
