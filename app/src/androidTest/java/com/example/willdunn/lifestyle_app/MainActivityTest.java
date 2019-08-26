package com.example.willdunn.lifestyle_app;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
    mainActivity = mainActivityActivityTestRule.getActivity();
    }

    //tests if the view is launched correctly for the MainActivity
    @Test
    public void testLaunch(){
        View view = mainActivity.findViewById(R.id.tv_Name);
        assertNotNull(view);
    }

    /*test to see if the elements are loading correctly*/



    /*exhaustive randomization tests to ensure the profile can handle random entries*/



    /*tests to validate that only name is needed to be entered when a profile is created*/



    /*ensure identical functionality when orientation changes; use setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);*/


}