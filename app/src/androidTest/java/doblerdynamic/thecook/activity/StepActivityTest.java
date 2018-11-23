package doblerdynamic.thecook.activity;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import doblerdynamic.thecook.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class StepActivityTest {


    @Rule
    public ActivityTestRule<StepsActivity> mActivityTestRule =
            new ActivityTestRule<>(StepsActivity.class);

    @Test
    public void clickOnBackAndForward() {

        onView(withId(R.id.btn_back))
                .perform(click());

        onView(withId(R.id.tv_fragment_step_description_label))
                .check(matches(withText(R.string.description)));

        onView(withId(R.id.btn_next))
                .perform(click());

        onView(withId(R.id.tv_fragment_step_description_label))
                .check(matches(withText(R.string.description)));

    }
}