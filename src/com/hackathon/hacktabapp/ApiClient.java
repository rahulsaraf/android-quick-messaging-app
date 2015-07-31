/**
 * 
 */
package com.hackathon.hacktabapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.EditText;

/**
 * @author Rahul
 *
 */
public class ApiClient {

	String url = "http://192.168.0.8:8080/";
	//String url = "http://localhost:8080/";
	String userCheck = "register/userCheck";
	String registerUser = "/register/user";
	
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	static String result="";
	
//	@Override
//	protected String doInBackground(String... params) {
//		
//		String urlString=params[0]; // URL to call
//		 
//	       String resultToDisplay = connectToApiClient(urlString);	 
//	        return resultToDisplay;
//	}

	private String connectToApiClient(String urlString) {
		String resultToDisplay = "";
	 
	       InputStream in = null;
	       
	       
	       // HTTP Get
	       try {
	 
	           URL url = new URL(urlString);
	 
	           HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

	           urlConnection.setRequestMethod("GET");
	           urlConnection.setRequestProperty("Accept", "application/json");
	           urlConnection.connect();
	           
	    
	   		if (urlConnection.getResponseCode() != 200) {
	   			System.out.println("Failed : HTTP error code : "
	   					+ urlConnection.getResponseCode());
	   		}
	    
	           in = new BufferedInputStream(urlConnection.getInputStream());
	 
	        } catch (Exception e ) {
	 
	           System.out.println(e.getMessage());
	 
	           return e.getMessage();
	 
	        }    
	       
	       
	       try{
	           BufferedReader reader = new BufferedReader(new InputStreamReader(
	        		   in, "iso-8859-1"), 8);
	           StringBuilder sb = new StringBuilder();
	           String line = null;
	           while ((line = reader.readLine()) != null) {
	               sb.append(line + "\n");
	           }
	           in.close();
	           result = sb.toString();
	       } catch (Exception e) {
	           System.out.println("Buffer Error"+ "Error converting result " + e.toString());
	       }
		return resultToDisplay;
	}
	
	public Boolean checkIfUserRegistered(String imei) throws InterruptedException{
		connectToApiClient(url+userCheck+"?imei="+imei);
		return new Boolean(result);
		
	}

	public Boolean registerUser(String userNameTx,String emailAddressTx, String imeiNum, String phoneNumberTx) {
		connectToApiClient(url+registerUser+"?userName="+userNameTx+"&emailAdd="+emailAddressTx+"&imei="+imeiNum+"&mobNo="+phoneNumberTx);
		return new Boolean(result);		
	}

}


