package com.wanmei.laohuemulator.adapter;

import java.util.List;
import java.util.Map;

import com.wanmei.laohuemulator.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class GameListAdapter extends SimpleAdapter {

	ViewHolder mviewhodler;
	Context mcontext;
	List<? extends Map<String, ?>> mdata ; 
	public GameListAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		mcontext = context;
		mdata = data;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=LayoutInflater.from(mcontext).inflate(R.layout.grid_item_layout,null);
			mviewhodler = new ViewHolder();
			mviewhodler.mimageview = (ImageView)convertView.findViewById(R.id.item_imageView);
			mviewhodler.mtextview = (TextView)convertView.findViewById(R.id.item_textView);
			convertView.setTag(mviewhodler);
		}else{
			mviewhodler = (ViewHolder) convertView.getTag();
		}
//		mviewhodler.mimageview.setImageResource(mdata.get(position).get(arg0));
		
		return convertView;
	}

	static class ViewHolder{
		
		public ImageView mimageview ;
		
		public TextView  mtextview;
		
		public Button mbutton;
	}
}
