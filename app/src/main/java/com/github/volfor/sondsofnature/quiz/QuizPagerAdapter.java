package com.github.volfor.sondsofnature.quiz;

import android.os.Bundle;
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
    private int type;


    public QuizPagerAdapter(FragmentManager fm, int type) {
        super(fm);
        this.type = type;
        addQuizTask();
    }

    public void addQuizTask() {
        pageCount++;

        Bundle bundle = new Bundle();
        bundle.putInt("type", type);

        QuizTaskFragment fragment = new QuizTaskFragment();
        fragment.setArguments(bundle);

        fragments.add(fragment);
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
