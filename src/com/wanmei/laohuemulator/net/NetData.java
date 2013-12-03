package com.wanmei.laohuemulator.net;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.widget.SimpleAdapter;

import com.wanmei.laohuemulator.R;

public class NetData {

	Context context;

	public NetData(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	private String[] item = { "我叫mt", "老虎游戏 ", "笑傲江湖", "剑侠奇缘" };

	ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();

	public SimpleAdapter getAdapter() {

		// 创建一个ArrayList列表,内部存的是HashMap列表
		ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();
		// 将数组信息分别存入ArrayList中
		int len = item.length;
		for (int i = 0; i < len; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.ic_launcher);
			map.put("item", item[i]);
			listItems.add(map);
		}
		// HashMap中的Key信息,要与grid_item.xml中的信息作对应
		String[] from = { "image", "item" };
		// grid_item.xml中对应的ImageView控件和TextView控件
		int[] to = { R.id.item_imageView, R.id.item_textView };
		// 设定一个适配器
		SimpleAdapter adapterSimple = new SimpleAdapter(this.context,
				listItems, R.layout.grid_item_layout, from, to);
		return adapterSimple;
	}

}
