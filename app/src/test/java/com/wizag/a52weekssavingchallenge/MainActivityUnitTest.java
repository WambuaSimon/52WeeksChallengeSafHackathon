package com.wizag.a52weekssavingchallenge;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.wizag.a52weekssavingchallenge.ui.MainActivity;
import com.wizag.a52weekssavingchallenge.utils.EditTextValidator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }

    private MainActivity mainActivity;
    EditText amt;
    Context context;
    @Before
    public void setUp(){
        context = RuntimeEnvironment.application.getApplicationContext();
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        amt = mainActivity.findViewById(R.id.starting_amount);
    }

    @Test
    public void shouldNotBeNull() {
          assertNotNull(mainActivity);
    }
    @Test
    public void startingValueNotEmpty(){
          assertNotNull(amt);
        assertEquals(View.VISIBLE,amt.getVisibility());
    }
}