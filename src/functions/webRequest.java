package functions;

import java.net.*;
import java.util.Iterator;
import java.io.*;

/* json-simple-1.1.jar */
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class webRequest {
	static Iterator<?> iterator; 
	
	public static Iterator<?> json(String url){
		try {
			URL website = new URL(url);
			URLConnection yc = website.openConnection();
	        BufferedReader in = new BufferedReader(
	        		new InputStreamReader(
	        		yc.getInputStream())
	        );
	        String inputLine;
	        String hole = "";

	        while ((inputLine = in.readLine()) != null) 
	        	hole += inputLine;
	        in.close();
	        
	        JSONParser parser = new JSONParser();
	        Object obj = parser.parse(hole);
            JSONObject jsonObject = (JSONObject) obj;            
            
            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("data");
            iterator = msg.iterator();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return iterator;
	}
}
