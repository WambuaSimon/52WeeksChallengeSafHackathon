package com.wizag.a52weekssavingchallenge.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.wizag.a52weekssavingchallenge.R;
import com.wizag.a52weekssavingchallenge.model.Amount;
import com.wizag.a52weekssavingchallenge.model.Depo;
import com.wizag.a52weekssavingchallenge.ui.adapter.AmountAdapter;
import com.wizag.a52weekssavingchallenge.utils.EditTextValidator;

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
    List<Integer> amount;
    List<Depo> depoList;
    String starting_amount_txt;
    Disposable d2;
    String current_week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("#52WeekChallenge");
        ButterKnife.bind(this);

        amount = new ArrayList<Integer>();
        depoList = new ArrayList<>();

        amountAdapter = new AmountAdapter(this, amountList, new AmountAdapter.AmountAdapterListener() {
            @Override
            public void cardOnClick(View v, int position) {
//                Toast.makeText(MainActivity.this, amountList, Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(amountAdapter);


        starting_amount_txt = starting_amount.getText().toString();
      
        d2 = RxTextView.textChanges(starting_amount)
//                .filter(s -> s.toString().length() > 6)
//                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        amountList.clear();

                        for (int i = 1; i <= 52; i++) {
                            Amount amount = new Amount();
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


                            }

                            if (i != 1) {
                                int depo = Integer.parseInt(amount.getDeposit());

                                int factor = depo * 2;
                                int total = factor + depo;
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
