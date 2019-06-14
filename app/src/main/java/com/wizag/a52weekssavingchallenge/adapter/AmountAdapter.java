package com.wizag.a52weekssavingchallenge.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wizag.a52weekssavingchallenge.R;
import com.wizag.a52weekssavingchallenge.model.Amount;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AmountAdapter extends RecyclerView.Adapter<AmountAdapter.MyViewHolder> {

    private Context context;
    private List<Amount> amountList;
    public AmountAdapterListener onClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card)
        CardView card;

        @BindView(R.id.week)
        TextView week;

        @BindView(R.id.deposit)
        TextView deposit;

        @BindView(R.id.total)
        TextView total;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public AmountAdapter(Context context, List<Amount> amountList, AmountAdapterListener listener) {
        this.context = context;
        this.amountList = amountList;
        this.onClickListener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.amount_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AmountAdapter.MyViewHolder holder, int position) {
        Amount note = amountList.get(position);
        holder.week.setText(Integer.toString(note.getWeek()));


        holder.deposit.setText(note.getDeposit());
        // Changing card color to random color
        holder.card.setCardBackgroundColor(getRandomMaterialColor("400"));
        holder.total.setText(note.getTotal());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.cardOnClick(v, position);

            }
        });


    }

    private int getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = context.getResources().getIdentifier("mdcolor_" + typeColor, "array", context.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }


    @Override
    public int getItemCount() {
        return amountList.size();
    }

    public interface AmountAdapterListener {

        void cardOnClick(View v, int position);


    }


}
