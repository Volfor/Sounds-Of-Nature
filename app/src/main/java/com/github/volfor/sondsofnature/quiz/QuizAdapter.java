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

public class QuizAdapter extends FragmentPagerAdapter {

    private int PAGE_COUNT = 0;
    private List<Fragment> fragments = new ArrayList<>();

    public QuizAdapter(FragmentManager fm) {
        super(fm);
        addQuizTask();
    }

    public void addQuizTask() {
        PAGE_COUNT++;
        fragments.add(new QuizTaskFragment());
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
