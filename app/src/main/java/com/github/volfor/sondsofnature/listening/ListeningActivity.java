package com.github.volfor.sondsofnature.listening;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.Utils;
import com.github.volfor.sondsofnature.ViewPagerAdapter;
import com.github.volfor.sondsofnature.databinding.ActivityListeningBinding;
import com.github.volfor.sondsofnature.events.ListenEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class ListeningActivity extends AppCompatActivity {

    public static final int ANIMALS = 546;
    public static final int TRANSPORT = 547;

    private ActivityListeningBinding binding;
    private MediaPlayer player;

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
        bundle.putInt("type", ANIMALS);

        ListeningFragment animalsFragment = new ListeningFragment();
        animalsFragment.setArguments(bundle);

        bundle = new Bundle();
        bundle.putInt("type", TRANSPORT);

        ListeningFragment transportFragment = new ListeningFragment();
        transportFragment.setArguments(bundle);

        adapter.addFragment(animalsFragment, "Animals");
        adapter.addFragment(transportFragment, "Transport");

        pager.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Utils.releasePlayer(player);
    }

    @Subscribe
    public void onListenEvent(ListenEvent e) {
        Utils.releasePlayer(player);

        player = MediaPlayer.create(this, e.soundId);
        player.start();
    }

}
