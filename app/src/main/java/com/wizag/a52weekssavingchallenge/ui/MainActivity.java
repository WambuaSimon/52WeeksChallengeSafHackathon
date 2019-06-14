package com.wizag.a52weekssavingchallenge.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.wizag.a52weekssavingchallenge.R;
import com.wizag.a52weekssavingchallenge.model.Amount;
import com.wizag.a52weekssavingchallenge.model.Depo;
import com.wizag.a52weekssavingchallenge.adapter.AmountAdapter;
import com.wizag.a52weekssavingchallenge.utils.MinMaxFilter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.starting_amount)
    EditText starting_amount;

    private List<Amount> amountList = new ArrayList<>();
    AmountAdapter amountAdapter;

    List<Depo> depoList;
    String starting_amount_txt;
    Disposable d2;
    int depositValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("#52WeekChallenge");
        ButterKnife.bind(this);


        depoList = new ArrayList<>();

        //*initializing adapter*/
        amountAdapter = new AmountAdapter(this, amountList, new AmountAdapter.AmountAdapterListener() {
            @Override
            public void cardOnClick(View v, int position) {
//                Toast.makeText(MainActivity.this, amountList, Toast.LENGTH_SHORT).show();
            }
        });

        /*setting up recyclerview*/
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(amountAdapter);

        /*starting value to be between 0 to 50m*/
        starting_amount.setFilters(new InputFilter[]{new MinMaxFilter("0", "50000000")});


        starting_amount_txt = starting_amount.getText().toString();
        if (starting_amount_txt.isEmpty()) {
            starting_amount.setText("0");
        }

        /*implementing rxBinding to starting amount EditText*/
        d2 = RxTextView.textChanges(starting_amount)
//                .filter(s -> s.toString().length() > 6)
//                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        amountList.clear();

                        /*creating 52 weeks*/
                        for (int i = 0; i <= 52; i++) {
                            Amount amount = new Amount();
                            Depo depo = new Depo();
                            amount.setWeek(i);


                            /*calculate deposit*/
                            int result = 0;
                            amount.setDeposit("0");
                            String num = charSequence.toString();
                            if (!num.isEmpty()) {
                                result = Integer.parseInt(num) * i;
                                amount.setDeposit(String.valueOf(result));


                            }


                            if (amount.getTotal() == null) {
                                amount.setTotal("0");
                            }



                            if (i == 1) {
                                amount.setTotal(String.valueOf(result));


                            } else if(i!=1){


                                /*get Total*/
//                                int deposit = Integer.parseInt(amount.getDeposit());
                                int week = amount.getWeek();


//                                int factor = deposit * 2;
                                int total = (result * week) + result;
                                amount.setTotal(String.valueOf(total));

                            }
                            amountList.add(amount);


                        }

                        amountAdapter.notifyDataSetChanged();


                    }
                });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        d2.dispose();
    }
}
