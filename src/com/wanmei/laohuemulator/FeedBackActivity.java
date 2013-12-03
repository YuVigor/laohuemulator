package com.wanmei.laohuemulator;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FeedBackActivity extends Activity implements OnClickListener {

	private TextView feedqqem , feedmessage;
	private Button submit ,back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.feed_back_layout);
		initview();
	}
	
	private void initview(){
		
		feedqqem = (TextView)this.findViewById(R.id.feed_qqem);
		feedmessage = (TextView)this.findViewById(R.id.feedback_message);
		submit = (Button)this.findViewById(R.id.feedback_submit);
		submit.setOnClickListener(this);
		back = (Button)this.findViewById(R.id.feed_back_button);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		if(id == R.id.feed_back_button){
			finish();
		}else{
			
			String addrs_message = feedqqem.getText().toString();
			String feed_message = feedmessage.getText().toString();
			Log.e("suiyi" , "submit"+addrs_message+";"+feed_message);
			if( addrs_message.equals("") ||feed_message.equals("")){
				Log.e("suiyi" , "submit");
				Toast.makeText(getApplicationContext(), R.string.empty_message, Toast.LENGTH_SHORT).show();
			    return;
			}
			new FeedBackTask().execute(addrs_message ,feed_message);
		}
		
	}
	
	private class FeedBackTask extends AsyncTask<String, String, String>{

		StringBuilder message =null;
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			message = new StringBuilder();
			message.append("qq or emaill:");
			message.append(arg0[0]);
			message.append(";message:");
			message.append(arg0[1]);
			
			return message.toString();
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
		}
	}
	
	
}
