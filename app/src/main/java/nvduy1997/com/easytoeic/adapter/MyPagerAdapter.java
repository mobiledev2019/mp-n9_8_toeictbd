package nvduy1997.com.easytoeic.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> myFragment = new ArrayList<>();
    private ArrayList<String> myTitle = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void AddFragmentPager(Fragment frg, String title){
        myFragment.add(frg);
        myTitle.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return myFragment.get(i);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return myTitle.get(position);
    }
}
