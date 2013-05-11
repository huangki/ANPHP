package com.example.anphp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView txv_Title;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txv_Title = (TextView) findViewById(R.id.textView1);
		//String  postUrl = "http://10.0.2.2/connect/";
		 
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("CatchNumber", "2"));
		
		HttpPost httpRequest = new HttpPost("http://10.0.2.2/connect"); 
	 	try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			 
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String strResult = EntityUtils.toString(httpResponse.getEntity()); 
				// 資料庫抓過來後 我使用text列出
				txv_Title.setText(strResult);
			} else {
				txv_Title.setText("Error Response:"
						+ httpResponse.getStatusLine().toString());
			}
		} catch (ClientProtocolException e) {
			txv_Title.setText(e.getMessage().toString());
			e.printStackTrace();
		} catch (IOException e) {
			txv_Title.setText(e.getMessage().toString());
			e.printStackTrace();
		} catch (Exception e) {
			txv_Title.setText(e.getMessage().toString());
			e.printStackTrace();
		}
 
	}

}
