package main;

import ws.*;
public class Main {
	

	public static void main(String[] args) {
		try {
			DemoImplService demoImplService = new DemoImplServiceLocator();
			Demo demo = demoImplService.getDemoImplPort();
			System.out.println(demo.hello());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
