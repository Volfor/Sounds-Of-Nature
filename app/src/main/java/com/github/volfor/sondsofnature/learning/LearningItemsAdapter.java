package com.github.volfor.sondsofnature.learning;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.volfor.sondsofnature.R;
import com.github.volfor.sondsofnature.databinding.ItemCardBinding;
import com.github.volfor.sondsofnature.models.GameCard;

import java.util.List;

import static com.github.volfor.sondsofnature.Utils.getAnimalCards;
import static com.github.volfor.sondsofnature.Utils.getTransportCards;
import static com.github.volfor.sondsofnature.learning.LearningActivity.ANIMALS;
import static com.github.volfor.sondsofnature.learning.LearningActivity.TRANSPORT;

/**
 * Created by Volfor on 03.01.2017.
 * http://github.com/Volfor
 */

public class LearningItemsAdapter extends RecyclerView.Adapter<LearningItemsAdapter.ViewHolder> {

    private List<GameCard> cards;

    public LearningItemsAdapter(Context context, int type) {
        switch (type) {
            case ANIMALS:
                cards = getAnimalCards(context);
                break;
            case TRANSPORT:
                cards = getTransportCards(context);
                break;
            default:
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindCard(cards.get(position));
    }

    @Override
    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCardBinding binding;

        public ViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        void bindCard(GameCard card) {
            binding.setItem(new LearningItemViewModel(card));
        }
    }

}
