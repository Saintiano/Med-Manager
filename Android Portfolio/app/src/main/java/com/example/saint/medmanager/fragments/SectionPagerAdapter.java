package com.example.saint.medmanager.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
* Class for adding fragments for tabs
*/

public class SectionPagerAdapter extends FragmentPagerAdapter {

    //storing fragments in a list
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);



    }

    @Override
    public Fragment getItem(int position) {

        //get the item position from the list
        return mFragmentList.get(position);

    }


    @Override
    public int getCount() {

        return mFragmentList.size();

    }


    //method to add fragments to the list
    public void addFragments(Fragment fragment){

        mFragmentList.add(fragment);

    }

}
