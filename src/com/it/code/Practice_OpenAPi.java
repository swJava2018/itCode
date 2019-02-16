package com.it.code;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class Practice_OpenAPi {
	/*
	 *	Reference : https://www.baeldung.com/java-http-request
	 *	Class : HttpUrlConnection
	 */
	public static void main(String[] args) throws IOException  {	
//		Process_Style1 proc1 = new Process_Style1();
		Process_Style2 proc2 = new Process_Style2();
		
//		proc1.process1();
//		proc1.process2("20181116");
//		proc1.process3();
//		proc1.process4();
//		proc1.process5();
		
		HttpURLConnection con = proc2.process1();
		HashMap<String, String> params = proc2.process2("20181116");
		proc2.process3(con, params);
		proc2.process4(con);
		proc2.process5(con);

		System.out.println("test");
	}
}
	



