package doblerdynamic.thecook.activity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import doblerdynamic.thecook.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnListAndOpenDetailsIntent() {

        onView(withId(R.id.rv_main_recipe))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(R.string.testNutellaPie)), click()));

        onView(withId(R.id.tv_title_ingredients)).check(matches(withText(R.string.ingredientsLabel)));

    }
}