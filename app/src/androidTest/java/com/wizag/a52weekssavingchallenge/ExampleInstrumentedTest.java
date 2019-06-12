package com.wizag.a52weekssavingchallenge;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wizag.a52weekssavingchallenge.ui.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.assertEquals;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.wizag.a52weekssavingchallenge", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            true);


    /*check if recyclerview is visible*/
    @Test
    public void testSampleRecyclerVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }


    /*check if all items are visible when scrolled*/
    @Test
    public void testCaseForRecyclerScroll() {

        // Get total item of RecyclerView
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.recyclerView);
        int itemCount = recyclerView.getAdapter().getItemCount();

        // Scroll to end of page with position
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

///*check if a specific view is visible or not*/
//    @Test
//    public void testCaseForRecyclerItemView() {
//
//        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
//                .inRoot(RootMatchers.withDecorView(
//                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
//                .check(matches(withViewAtPosition(1, Matchers.allOf(
//                        ViewMatchers.withId(R.id.week), isDisplayed()))));
//    }
//
//
//    public Matcher<View> withViewAtPosition(final int position, final Matcher<View> itemMatcher) {
//        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
//            @Override
//            public void describeTo(Description description) {
//                itemMatcher.describeTo(description);
//            }
//
//            @Override
//            protected boolean matchesSafely(RecyclerView recyclerView) {
//                final RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
//                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
//            }
//        };
//    }
}
