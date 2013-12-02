package com.wanmei.laohuemulator;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements TabListener , OnClickListener{


	private ActionBar mab;
	private View actionbarLayout = null;  
	
	private Button Bmenu , Bsearch;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 // 获取TabHost对象  
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);  
        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost  
        tabHost.setup();  
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("我的游戏",  
                getResources().getDrawable(R.drawable.ic_launcher)).setContent(  
                R.id.view1));  
  
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("全部游戏")  
                .setContent(R.id.view3));  
  
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("街机厅")  
                .setContent(R.id.view2));  
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("小霸王")  
                .setContent(R.id.view4));
        tabHost.addTab(tabHost.newTabSpec("tab5").setIndicator("掌机GBA")  
                .setContent(R.id.view5));
        
	}
//    private setTabHostItem()
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		mab = getActionBar();
		mab.setTitle(R.string.title);
		mab.setDisplayShowCustomEnabled(true);
		mab.setDisplayShowHomeEnabled(false);
//		
//		if(this.getWindowManager().getDefaultDisplay().getOrientation()==1){
//			Log.e("suiyi", "1111111111");
//
//		}else if(this.getWindowManager().getDefaultDisplay().getOrientation()==2){
//			Log.e("suiyi", "2222222222");
//			actionbarLayout = LayoutInflater.from(this).inflate(R.layout.actionbar_view_horizontal, null);  
//		}
		actionbarLayout = LayoutInflater.from(this).inflate(R.layout.actionbar_view_vortical, null);  			
		Bmenu = (Button)actionbarLayout.findViewById(R.id.menu);
		Bmenu.setOnClickListener(this);
		Bsearch = (Button)actionbarLayout.findViewById(R.id.search);
		Bsearch.setOnClickListener(this);
		mab.setCustomView(actionbarLayout);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);

		if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
			Log.e("suiyi", "ORIENTATION_PORTRAIT");
			actionbarLayout = LayoutInflater.from(this).inflate(R.layout.actionbar_view_horizontal, null);  
			Bmenu = (Button)actionbarLayout.findViewById(R.id.menu);
			Bmenu.setOnClickListener(this);
			Bsearch = (Button)actionbarLayout.findViewById(R.id.search);
			Bsearch.setOnClickListener(this);
			mab.setCustomView(actionbarLayout);
		}else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
			Log.e("suiyi", "ORIENTATION_LANDSCAPE");	
			actionbarLayout = LayoutInflater.from(this).inflate(R.layout.actionbar_view_vortical, null);  
			Bmenu = (Button)actionbarLayout.findViewById(R.id.menu);
			Bmenu.setOnClickListener(this);
			Bsearch = (Button)actionbarLayout.findViewById(R.id.search);
			Bsearch.setOnClickListener(this);
			mab.setCustomView(actionbarLayout);
		}
		
	}
	
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		switch(id){
		   
		case R.id.menu:
			Toast.makeText(getApplicationContext(), "我是兔子", Toast.LENGTH_SHORT).show();
			break;
		case R.id.search:
			Toast.makeText(getApplicationContext(), "我也是兔子", Toast.LENGTH_SHORT).show();
			break;
		case R.id.view1:
			Toast.makeText(getApplicationContext(), "view1", Toast.LENGTH_SHORT).show();
			break;
		case R.id.view2:
			Toast.makeText(getApplicationContext(), "view2", Toast.LENGTH_SHORT).show();
			break;
		case R.id.view3:
			Toast.makeText(getApplicationContext(), "view3", Toast.LENGTH_SHORT).show();
			break;
		}
		
	}

}
