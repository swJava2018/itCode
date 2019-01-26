package com.it.code;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Process_Style2 {
			
	public HttpURLConnection process1() throws IOException {
		// 1. HTTP 占쏙옙체
		URL url = new URL("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		return con;
	}
	
	public HashMap<String, String> process2(String day) throws IOException {
		// 2. HTTP 占쏙옙체占쏙옙 Request Parameters 占쌩곤옙
		HashMap<String, String> parameters = new HashMap<>();
		parameters.put("authkey", "wu4z4qw9YE2LzsAqgg1vkLuIRRrsjrYc");
		parameters.put("searchdate", day);
		parameters.put("data", "AP01");
		return parameters;
	}
	
	public void process3(HttpURLConnection con, HashMap<String, String> parameters) throws IOException {
		// 3. HTTP 占쏙옙체占쏙옙 Write(占쏙옙占쏙옙) 占쏙옙占� 占쏙옙占쏙옙
		con.setDoOutput(true); 
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
		out.flush();
		out.close();
	}
	
	public void process4(HttpURLConnection con) throws IOException {
		// 4. HTTP 占쏙옙체占쏙옙 占쌩곤옙 占쏙옙占쏙옙 (i.e. 占쏙옙占쏙옙 占쏙옙占� 占시곤옙, 占쏙옙占쏙옙 占싻깍옙 占시곤옙)
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000); 
//		con.setRequestMethod("POST");
	}
	
	public void process5(HttpURLConnection con) throws IOException {		
		// 5. HTTP 占쏙옙체占쏙옙 占싻깍옙
		int status = con.getResponseCode();
		String data = null;
		if(status == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			data = content.toString();
			in.close();			
		}
		con.disconnect();
		
		System.out.println(data);
		
		JsonParser parser = new JsonParser();
		JsonArray arr = (JsonArray) parser.parse(data);
		for(int i=0; i<arr.size(); i++) {
			JsonObject obj = (JsonObject) arr.get(i);
			String name = obj.get("cur_unit").getAsString();
			if(name.equals("GBP")) {
				
				System.out.println(name  + " : " + obj.get("deal_bas_r").getAsString());
			}					
		}	
	}
}
