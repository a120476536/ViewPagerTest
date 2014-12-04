package com.qiao.viewpagertest;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiao.Fragment.FragmentOne;
import com.qiao.Fragment.FragmentThr;
import com.qiao.Fragment.FragmentTwo;


/**
 * viewpager搭配fragment效果
 * @author 有点凉了
 * QQ群：123869487
 * 求基友共同进步，求大神入群指点
 *
 */
public class MainActivity extends FragmentActivity {
	private static final String TAG="MainActivity";
	private ViewPager viewPager;
	private TextView [] title = null;//
	private List<Fragment> list = null;//
	private ImageView dors[] = null;
	private LinearLayout layout_main_title;
	private LinearLayout layout_main_point;
	private int[] imgIds = { R.drawable.dot_1, R.drawable.dot_1,
			R.drawable.dot_1 };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		layout_main_title = (LinearLayout) findViewById(R.id.layout_main_title);
		layout_main_point = (LinearLayout) findViewById(R.id.layout_main_point);
		/**
		 * 标题改变
		 */
		titleChange();
		/**
		 * 页面改变，viewPager内置fragment
		 */
		pageChange();
	}

	private void pageChange() {
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		list = new ArrayList<Fragment>();
		FragmentOne fragmentOne = new FragmentOne();
		list.add(fragmentOne);
		FragmentTwo fragmentTwo = new FragmentTwo();
		list.add(fragmentTwo);
		FragmentThr fragmentThr = new FragmentThr();
		list.add(fragmentThr);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < title.length; i++) {
					dors[i].setEnabled(true);
					title[i].setEnabled(true);
//					title[i].setShadowLayer(0, 3, 3, 0xFFFF00FF);
				}
				title[arg0].setEnabled(false);
				dors[arg0].setEnabled(false);
				
				  AnimationSet animationSet = new AnimationSet(true);
				  /**
				   * 缩放效果动画
				   */
//				 ScaleAnimation scaleAnimation = new ScaleAnimation(0, 0.9f,0,0.9f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//				 scaleAnimation.setDuration(600);
//				 animationSet.addAnimation(scaleAnimation);
				/**
				 * 移动效果动画
				 * 
				 * 
				 * 以下移动效果目前最佳
				 * 
				 */
				  
				  /**
				   * 从右往左
				   */
//				TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1f,Animation.RELATIVE_TO_SELF, 0f,Animation.RELATIVE_TO_SELF, 0f,Animation.RELATIVE_TO_SELF, 0f);
//				translateAnimation.setDuration(600);
//				animationSet.addAnimation(translateAnimation);
//				title[arg0].startAnimation(animationSet);
//				dors[arg0].startAnimation(animationSet);
				  /**
				   * 从左往右
				   */
				  TranslateAnimation translateAnimation2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
				  translateAnimation2.setDuration(600);
				  animationSet.addAnimation(translateAnimation2);
				 title[arg0].startAnimation(animationSet);
				 dors[arg0].startAnimation(animationSet);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		pointChange();
	}

	private void pointChange() {
		dors = new ImageView[imgIds.length];
		for (int i = 0; i < dors.length; i++) {
			dors[i] = (ImageView) layout_main_point.getChildAt(i);
			dors[i].setEnabled(true);
		}
		dors[0].setEnabled(false);
	}

	class MyAdapter extends FragmentPagerAdapter{
		private List<Fragment> list = null;
		public MyAdapter(FragmentManager fm,List<Fragment> list) {
			super(fm);
			this.list=list;
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			super.destroyItem(container, position, object);
		}
		
	}
	private void titleChange() {
		title = new TextView[layout_main_title.getChildCount()];
		for (int i = 0; i < title.length; i++) {
			title[i]=(TextView) layout_main_title.getChildAt(i);
			title[i].setEnabled(true);
			title[i].setTag(i);
			title[i].setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					/**
					 * 给当前的viewPager贴上对应的标签
					 */
					viewPager.setCurrentItem((Integer)v.getTag());
				}
			});
			/**
			 * 已经处于页面前端的无法被再次选择
			 */
			title[0].setEnabled(false);
//			 AnimationSet animationSet = new AnimationSet(true);
////			 ScaleAnimation scaleAnimation = new ScaleAnimation(0, 0.9f,0,0.9f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
////			 scaleAnimation.setDuration(600);
////			 animationSet.addAnimation(scaleAnimation);
//			 TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0.5f);
//			 translateAnimation.setDuration(600);
//			  animationSet.addAnimation(translateAnimation);
//			 title[0].startAnimation(animationSet);
//			title[0].setShadowLayer(5, 3, 3, 0xFFFF00FF);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



}
