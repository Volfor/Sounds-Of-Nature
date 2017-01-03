package com.github.volfor.sondsofnature.listening;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.databinding.FragmentListeningBinding;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class ListeningFragment extends Fragment {

    FragmentListeningBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listening, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int type = getArguments().getInt("type");

        ListeningAdapter adapter = new ListeningAdapter(getContext(), type);

        binding.list.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.list.setAdapter(adapter);
    }

}
