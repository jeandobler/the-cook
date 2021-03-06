package doblerdynamic.thecook.activity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import doblerdynamic.thecook.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class DetailsActivityTest {


    @Rule
    public ActivityTestRule<DetailsActivity> mActivityTestRule =
            new ActivityTestRule<>(DetailsActivity.class);

    @Test
    public void clickOnListAndOpenStepsIntent() {

        onView(withId(R.id.rv_details_steps))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.tv_fragment_step_description_label))
                .check(matches(withText(R.string.description)));

    }

    @Test
    public void clickBack() {

        onView(withId(android.R.id.home))
                .perform(click());

        onView(withId(R.id.tv_recipe_title))
                .check(matches(withText(R.string.testNutellaPie)));

    }
}