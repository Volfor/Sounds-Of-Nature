package com.github.volfor.sondsofnature.listening;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.ViewPagerAdapter;
import com.github.volfor.sondsofnature.databinding.ActivityListeningBinding;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class ListeningActivity extends AppCompatActivity {

    ActivityListeningBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listening);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(binding.pager);
        binding.tabLayout.setupWithViewPager(binding.pager);
    }

    private void setupViewPager(ViewPager pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        bundle.putInt("type", ListeningAdapter.ANIMALS);

        ListeningFragment animalsFragment = new ListeningFragment();
        animalsFragment.setArguments(bundle);

        bundle = new Bundle();
        bundle.putInt("type", ListeningAdapter.TRANSPORT);

        ListeningFragment transportFragment = new ListeningFragment();
        transportFragment.setArguments(bundle);

        adapter.addFragment(animalsFragment, "Animals");
        adapter.addFragment(transportFragment, "Transport");

        pager.setAdapter(adapter);
    }

}
