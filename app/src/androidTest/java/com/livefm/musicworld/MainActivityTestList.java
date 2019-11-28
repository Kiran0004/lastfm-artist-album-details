package com.livefm.musicworld;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.livefm.musicworld.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.notNullValue;
import androidx.test.rule.ActivityTestRule;

@RunWith(AndroidJUnit4.class)
public class MainActivityTestList {

    @Rule
   public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureListViewIsPresent() throws Exception {
        onView(withId(R.id.title)).check(matches(notNullValue()));
        onView(withId(R.id.title)).check(matches(withText("Music Album Details")));
        onView(withId(R.id.selectionlbl)).check(matches(notNullValue()));
        onView(withId(R.id.selectionlbl)).check(matches(withText("Select category")));
        onView(withId(R.id.categorySpinner)).check(matches(notNullValue()));
        onView(withId(R.id.searchlbl)).check(matches(notNullValue()));
        onView(withId(R.id.search_text)).check(matches(notNullValue()));
       onView(withId(R.id.my_recycler_view)).check(matches(hasDescendant(withText("beat"))));


    }

}