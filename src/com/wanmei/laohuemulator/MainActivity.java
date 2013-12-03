package com.wanmei.laohuemulator;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.wanmei.laohuemulator.adapter.PageAdapter;
import com.wanmei.laohuemulator.net.NetData;

public class MainActivity extends FragmentActivity implements OnClickListener,
		OnPageChangeListener {

	private ActionBar mab;
	private View actionbarLayout = null;

	private Button appbutton[] = new Button[7];

	private Button findgame, Bplatform, Btype, Blanguage;

	private ListView mlistview;

	private FragmentManager mFragmentManager;

	private ViewPager mviewpage;

	private ArrayList<View> listViews; // Tab页面列表

	private int buttonid = 2;

	private NetData mnetdata;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		InitViewPager();
		mFragmentManager = getSupportFragmentManager();
	}

	private void initView() {

		appbutton[2] = (Button) this.findViewById(R.id.mygame);
		appbutton[2].setOnClickListener(this);
		appbutton[2].setSelected(true);
		buttonid = 2;
		appbutton[3] = (Button) this.findViewById(R.id.allgame);
		appbutton[3].setOnClickListener(this);
		appbutton[4] = (Button) this.findViewById(R.id.game1);
		appbutton[4].setOnClickListener(this);
		appbutton[5] = (Button) this.findViewById(R.id.game2);
		appbutton[5].setOnClickListener(this);
		appbutton[6] = (Button) this.findViewById(R.id.game3);
		appbutton[6].setOnClickListener(this);
	}

	/**
	 * 02 初始化ViewPager 03
	 */

	private void InitViewPager() {
		mviewpage = (ViewPager) findViewById(R.id.vPager);
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.my_game_page, null));
		listViews.add(mInflater.inflate(R.layout.all_game_page, null));
		mviewpage.setAdapter(new PageAdapter(listViews));
		mviewpage.setCurrentItem(0);
		mviewpage.setOnPageChangeListener(this);
		setInitview();
	}

	private void setInitview() {
		View pageview = listViews.get(0);
		findgame = (Button) pageview.findViewById(R.id.look_game);
		findgame.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	private void setButtonclick(int buttonid) {

		for (int i = 2; i <= 6; i++) {
			if (i == buttonid)
				appbutton[i].setSelected(true);
			else
				appbutton[i].setSelected(false);
		}

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mab = getActionBar();
		mab.setTitle(R.string.title);
		mab.setDisplayShowCustomEnabled(true);
		mab.setDisplayShowHomeEnabled(false);
		actionbarLayout = LayoutInflater.from(this).inflate(
				R.layout.actionbar_view_vortical, null);
		appbutton[0] = (Button) actionbarLayout.findViewById(R.id.menu);
		appbutton[0].setOnClickListener(this);
		appbutton[1] = (Button) actionbarLayout.findViewById(R.id.search);
		appbutton[1].setOnClickListener(this);
		mab.setCustomView(actionbarLayout);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);

		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Log.e("suiyi", "ORIENTATION_PORTRAIT");
			actionbarLayout = LayoutInflater.from(this).inflate(
					R.layout.actionbar_view_horizontal, null);
			appbutton[0] = (Button) actionbarLayout.findViewById(R.id.menu);
			appbutton[0].setOnClickListener(this);
			appbutton[1] = (Button) actionbarLayout.findViewById(R.id.search);
			appbutton[1].setOnClickListener(this);
			mab.setCustomView(actionbarLayout);
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			Log.e("suiyi", "ORIENTATION_LANDSCAPE");
			actionbarLayout = LayoutInflater.from(this).inflate(
					R.layout.actionbar_view_vortical, null);
			appbutton[0] = (Button) actionbarLayout.findViewById(R.id.menu);
			appbutton[0].setOnClickListener(this);
			appbutton[1] = (Button) actionbarLayout.findViewById(R.id.search);
			appbutton[1].setOnClickListener(this);
			mab.setCustomView(actionbarLayout);
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		switch (id) {

		case R.id.menu:
			Toast.makeText(getApplicationContext(), "我是兔子", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.search:
			Toast.makeText(getApplicationContext(), "我也是兔子", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.mygame:
			Log.e("suiyi", "click mygame button");
			buttonid = 2;
			setButtonclick(buttonid);
			mviewpage.setCurrentItem(buttonid - 2);
			break;
		case R.id.allgame:
			Log.e("suiyi", "click allgame button");
			buttonid = 3;
			setButtonclick(buttonid);
			mviewpage.setCurrentItem(buttonid - 2);
			break;
		case R.id.game1:
			Log.e("suiyi", "click allgame button");
			buttonid = 4;
			setButtonclick(buttonid);
			mviewpage.setCurrentItem(buttonid - 2);
			break;
		case R.id.game2:
			Log.e("suiyi", "click allgame button");
			buttonid = 5;
			setButtonclick(buttonid);
			mviewpage.setCurrentItem(buttonid - 2);
			break;
		case R.id.game3:
			Log.e("suiyi", "click allgame button");
			buttonid = 6;
			setButtonclick(buttonid);
			mviewpage.setCurrentItem(buttonid - 2);
			break;
		case R.id.look_game:
			Log.e("suiyi", "click allgame button");
			buttonid = 3;
			setButtonclick(buttonid);
			mviewpage.setCurrentItem(buttonid - 2);
			break;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	private void initAllGameView() {

		View view = listViews.get(1);
		Bplatform = (Button) view.findViewById(R.id.platform);
		Bplatform.setOnClickListener(this);
		Btype = (Button) view.findViewById(R.id.type);
		Btype.setOnClickListener(this);
		Blanguage = (Button) view.findViewById(R.id.language);
		Blanguage.setOnClickListener(this);
		mlistview = (ListView) view.findViewById(R.id.game_show_list);
        mnetdata = new NetData(getApplicationContext());
		// 对listView进行适配
		mlistview.setAdapter(mnetdata.getAdapter());

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {

		case 0:
			setButtonclick(arg0 + 2);
			break;
		case 1:
			setButtonclick(arg0 + 2);
			initAllGameView();
			break;

		}

	}

}
