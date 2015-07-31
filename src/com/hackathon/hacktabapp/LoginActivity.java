package com.hackathon.hacktabapp;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity 
{
	
	ApiClient client = new ApiClient();
	String imeiNum;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        imeiNum = telephonyManager.getDeviceId();
        Boolean result=false;
		try {
			result = client.checkIfUserRegistered(imeiNum);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        if(result){
        	
        	Intent intent = new Intent(this,AndroidTabLayoutActivity.class);
        	startActivity(intent);
        }else{
        	setContentView(R.layout.activity_login);	
        }
    }
    
    public void validateLogin(View v) throws SQLException, ClassNotFoundException
    {
    	EditText emailAddress = (EditText) findViewById(R.id.editText1);
    	EditText phoneNumber = (EditText) findViewById(R.id.editText2);
    	EditText userName = (EditText) findViewById(R.id.editText3);
    	String emailAddressTx = emailAddress.getText().toString();
    	String phoneNumberTx = phoneNumber.getText().toString();
    	String userNameTx = userName.getText().toString();
    	client.registerUser(userNameTx,emailAddressTx,imeiNum,phoneNumberTx);
    	Intent intent = new Intent(this,AndroidTabLayoutActivity.class);
    	startActivity(intent);
       }
}
