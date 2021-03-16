package com.cj.uiapplication.adapter.layout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class LayoutFragmentPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public LayoutFragmentPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public LayoutFragmentPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments.clear();
        this.fragments.addAll(fragments);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null?fragments.size():0;
    }
}
