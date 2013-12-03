package com.wanmei.laohuemulator;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class searchActivity extends Activity implements OnClickListener{

	private Button backbutton , searchbutton ;
	private EditText et ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_activity);
		backbutton = (Button)this.findViewById(R.id.feed_back_button);
		backbutton.setOnClickListener(this);
		searchbutton = (Button)this.findViewById(R.id.search_button);
		searchbutton.setOnClickListener(this);
		et = (EditText)this.findViewById(R.id.search_message);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		switch(id){
				case R.id.feed_back_button:
					finish();
					break;
				case R.id.search_button:
					
					String searchtext = et.getText().toString();
					if(searchtext.equals("")||searchtext.equals(" ")){
						Toast.makeText(getApplicationContext(), "搜索内容为空", Toast.LENGTH_SHORT).show();
						return;
					}
					
					break;
		}
	}
	private class searchTask extends AsyncTask<String, Integer, String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	}
	
}
