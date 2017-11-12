package com.example.vipshah.espressodemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testValidation() {
        Espresso.onView(withId(R.id.number1EditText)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.number2EditText)).perform(ViewActions.clearText());

        Espresso.onView(withId(R.id.addButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.answerTextView))
                .check(ViewAssertions.matches(withText("Please input both numbers!")));
    }

    @Test
    public void testCalculations() {

        // Test Addition
        Espresso.onView(withId(R.id.number1EditText)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.number2EditText)).perform(ViewActions.clearText());

        Espresso.onView(withId(R.id.number1EditText)).perform(ViewActions.typeText("10"));
        Espresso.onView(withId(R.id.number2EditText)).perform(ViewActions.typeText("20"));

        Espresso.onView(withId(R.id.addButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.answerTextView)).check(ViewAssertions.matches(withText("30.0")));

        // Test Subtraction
        Espresso.onView(withId(R.id.number1EditText)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.number2EditText)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.number1EditText)).perform(ViewActions.typeText("30"));
        Espresso.onView(withId(R.id.number2EditText)).perform(ViewActions.typeText("20"));

        Espresso.onView(withId(R.id.subtractButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.answerTextView)).check(ViewAssertions.matches(withText("10.0")));

        // Test Multiplication
        Espresso.onView(withId(R.id.number1EditText)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.number2EditText)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.number1EditText)).perform(ViewActions.typeText("50"));
        Espresso.onView(withId(R.id.number2EditText)).perform(ViewActions.typeText("5"));

        Espresso.onView(withId(R.id.multiplyButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.answerTextView)).check(ViewAssertions.matches(withText("250.0")));

        // Test Division
        Espresso.onView(withId(R.id.number1EditText)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.number2EditText)).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.number1EditText)).perform(ViewActions.typeText("100"));
        Espresso.onView(withId(R.id.number2EditText)).perform(ViewActions.typeText("25"));

        Espresso.onView(withId(R.id.divideButton)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.answerTextView)).check(ViewAssertions.matches(withText("4.0")));

    }
}
