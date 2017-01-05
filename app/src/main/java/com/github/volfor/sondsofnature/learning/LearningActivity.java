package com.github.volfor.sondsofnature.learning;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.github.volfor.sondsofnature.EventActivity;
import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.Utils;
import com.github.volfor.sondsofnature.databinding.ActivityLearningBinding;
import com.github.volfor.sondsofnature.events.ListenEvent;

import org.greenrobot.eventbus.Subscribe;

import static com.github.volfor.sondsofnature.root.MainActivity.ANIMALS;
import static com.github.volfor.sondsofnature.root.MainActivity.TRANSPORT;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class LearningActivity extends EventActivity {

    private ActivityLearningBinding binding;
    private MediaPlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_learning);

        setSupportActionBar(binding.include.toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupViewPager(binding.pager);
        binding.tabLayout.setupWithViewPager(binding.pager);
    }

    private void setupViewPager(ViewPager pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        bundle.putInt("type", ANIMALS);

        LearningFragment animalsFragment = new LearningFragment();
        animalsFragment.setArguments(bundle);

        bundle = new Bundle();
        bundle.putInt("type", TRANSPORT);

        LearningFragment transportFragment = new LearningFragment();
        transportFragment.setArguments(bundle);

        adapter.addFragment(animalsFragment, "Animals");
        adapter.addFragment(transportFragment, "Transport");

        pager.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        Utils.releasePlayer(player);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onListenEvent(ListenEvent e) {
        Utils.releasePlayer(player);

        player = MediaPlayer.create(this, e.soundId);
        player.start();
    }

}
