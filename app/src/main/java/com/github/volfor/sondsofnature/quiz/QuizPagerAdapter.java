package com.github.volfor.sondsofnature.quiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Volfor on 04.01.2017.
 * http://github.com/Volfor
 */

public class QuizPagerAdapter extends FragmentPagerAdapter {

    private int pageCount = 0;
    private List<Fragment> fragments = new ArrayList<>();

    public QuizPagerAdapter(FragmentManager fm) {
        super(fm);
        addQuizTask();
    }

    public void addQuizTask() {
        pageCount++;
        fragments.add(new QuizTaskFragment());
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
