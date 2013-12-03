package com.wanmei.laohuemulator;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.wanmei.laohuemulator.adapter.PageAdapter;
import com.wanmei.laohuemulator.net.NetData;

public class MainActivity extends FragmentActivity implements OnClickListener,
		OnPageChangeListener {

	// private ActionBar mab;
	private View actionbarLayout = null;

	private Button appbutton[] = new Button[7];

	private Button findgame, Bplatform, Btype, Blanguage , emulatorsetbutton;

	private ListView mlistview;

	private FragmentManager mFragmentManager;

	private ViewPager mviewpage;

	private ArrayList<View> listViews; // Tab页面列表

	// menu list

	private ListView emulatorlist, helperlist;

	private int buttonid = 2;

	private NetData mnetdata;

	/**
	 * 主内容的布局。
	 */
	private View content;

	/**
	 * menu的布局。
	 */
	private View menu;

	/**
	 * 屏幕宽度值。
	 */
	private int screenWidth;

	/**
	 * menu最多可以滑动到的左边缘。值由menu布局的宽度来定，marginLeft到达此值之后，不能再减少。
	 */
	private int leftEdge;

	/**
	 * menu完全显示时，留给content的宽度值。
	 */
	private int menuPadding = 230;

	/**
	 * menu布局的参数，通过此参数来更改leftMargin的值。
	 */
	private LinearLayout.LayoutParams menuParams;

	/**
	 * menu当前是显示还是隐藏。只有完全显示或隐藏menu时才会更改此值，滑动过程中此值无效。
	 */
	private boolean isMenuVisible = false;
	
	private boolean isEsetVisible = false;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initValues();
		initView();
		InitViewPager();

		mFragmentManager = getSupportFragmentManager();
	}

	private void initValues() {
		WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth = window.getDefaultDisplay().getWidth();
		content = findViewById(R.id.content);
		menu = findViewById(R.id.menu);
		menuParams = (LinearLayout.LayoutParams) menu.getLayoutParams();
		// 将menu的宽度设置为屏幕宽度减去menuPadding
		menuParams.width = screenWidth - menuPadding;
		// 左边缘的值赋值为menu宽度的负数
		leftEdge = -menuParams.width;
		// menu的leftMargin设置为左边缘的值，这样初始化时menu就变为不可见
		menuParams.leftMargin = leftEdge;
		// 将content的宽度设置为屏幕宽度
		content.getLayoutParams().width = screenWidth;
		emulatorsetbutton = (Button)menu.findViewById(R.id.emulator_set);
		emulatorsetbutton.setOnClickListener(this);

		helperlist = (ListView) menu.findViewById(R.id.menu_helper);
		helperlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				switch(arg2){
				
				case 0:
					
					break;

				case 1:
					
					intent.setClass(getApplicationContext(), FeedBackActivity.class);
					startActivity(intent);
					break;

				case 2:
					break;

				case 3:
					Log.e("suiyi", "arg2:"+arg2);
					intent = new Intent();
					intent.setClass(getApplicationContext(), AboatActivity.class);
					startActivity(intent);
					break;
				}
                
			}
		});
		initMenuList();
	}

	private void initMenuList() {
		String[] item_helper = { "设置游戏存储路径", "用户反馈",
				"检测更新版本（v1.0）" , "关于" };
		String[] item_helper_item = { "(..........)" ,"" ,"" ,""};
		ArrayList<HashMap<String, Object>> listItems1 = new ArrayList<HashMap<String, Object>>();
		
		int len = item_helper.length;
		for (int i = 0; i < len; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("item_helper", item_helper[i]);
			map.put("item_helper_item", item_helper_item[i]);
			listItems1.add(map);
		}
		SimpleAdapter	adapterSimple = new SimpleAdapter(
				getApplicationContext(), listItems1, R.layout.menu_list_item,
				new String[] { "item_helper" , "item_helper_item"}, new int[] { R.id.menu_item ,R.id.menu_item_item});
		helperlist.setAdapter(adapterSimple);
	}
	

	private void initView() {

		appbutton[0] = (Button) this.findViewById(R.id.menubutton);
		appbutton[0].setOnClickListener(this);
		appbutton[1] = (Button) this.findViewById(R.id.search);
		appbutton[1].setOnClickListener(this);
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
		listViews.add(mInflater.inflate(R.layout.arcade_game_page, null));
		listViews.add(mInflater.inflate(R.layout.bully_game_page, null));
		listViews.add(mInflater.inflate(R.layout.gba_game_page, null));
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

	// @Override
	// protected void onStart() {
	// // TODO Auto-generated method stub
	// super.onStart();
	// mab = getActionBar();
	// mab.setTitle(R.string.title);
	// mab.setDisplayShowCustomEnabled(true);
	// mab.setDisplayShowHomeEnabled(false);
	// actionbarLayout = LayoutInflater.from(this).inflate(
	// R.layout.actionbar_view_vortical, null);
	//
	// mab.setCustomView(actionbarLayout);
	// }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		switch (id) {

		case R.id.menubutton:

			if (isMenuVisible) {
				Toast.makeText(getApplicationContext(), "我是兔子",
						Toast.LENGTH_SHORT).show();
				isMenuVisible = false;
				menuParams.width = screenWidth - menuPadding;
				// 左边缘的值赋值为menu宽度的负数
				leftEdge = -menuParams.width;
				// menu的leftMargin设置为左边缘的值，这样初始化时menu就变为不可见
				menuParams.leftMargin = leftEdge;
				// 将content的宽度设置为屏幕宽度
				content.getLayoutParams().width = screenWidth;
				menu.setLayoutParams(menuParams);
			} else {
				isMenuVisible = true;
				menuParams.leftMargin = 1;
				menu.setLayoutParams(menuParams);
			}

			break;
		case R.id.search:
			Toast.makeText(getApplicationContext(), "我也是兔子", Toast.LENGTH_SHORT)
					.show();
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), searchActivity.class);
			startActivity(intent);
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
		case R.id.emulator_set:
			Log.e("suiyi", "emulatorset click");
			emulatorlist = (ListView) menu.findViewById(R.id.menu_emulator);
			emulatorlist.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub

				}
			});
			String[] item = { "PSP", "GBA", "NES" };
			String[] item1 = { "(未安装，点击安装)", "(未安装，点击安装)", "(未安装，点击安装)" };			
			ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();
			int len = item.length;
			for (int i = 0; i < len; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("item", item[i]);
				map.put("item1", item1[i]);
				listItems.add(map);
			}
			// 设定一个适配器
			SimpleAdapter adapterSimple = new SimpleAdapter(
					getApplicationContext(), listItems, R.layout.menu_list_item,
					new String[] { "item" ,"item1"}, new int[] { R.id.menu_item ,R.id.menu_item_item });
			emulatorlist.setAdapter(adapterSimple);
			if(isEsetVisible){
				emulatorlist.setVisibility(View.GONE);
				isEsetVisible = false;
			}else{

				isEsetVisible = true;
				emulatorlist.setVisibility(View.VISIBLE);
			}
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
		case 2:
			setButtonclick(arg0 + 2);
			break;
		case 3:
			setButtonclick(arg0 + 2);
			break;
		case 4:
			setButtonclick(arg0 + 2);
			break;
		}

	}

}
