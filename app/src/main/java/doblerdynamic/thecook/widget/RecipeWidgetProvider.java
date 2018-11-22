package doblerdynamic.thecook.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.List;

import doblerdynamic.thecook.R;
import doblerdynamic.thecook.activity.DetailsActivity;
import doblerdynamic.thecook.model.Ingredient;


public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, int head, List<Ingredient> ingredientList) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);

        if (head != -1) {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(context.getString(R.string.recipeIndex), head);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            String text = context.getString(R.string.ingredientsLabel);
            text += "\n";
            for (int count = 0; ingredientList.size() > count; count++) {
                text += String.valueOf(count + 1);
                text += ": " + ingredientList.get(count).getIngredient();
                text += " " + ingredientList.get(count).getQuantity();
                text += " " + ingredientList.get(count).getMeasure();
                text += "\n";
            }

            views.setTextViewText(R.id.tv_recipe_ingredients, text);
            views.setOnClickPendingIntent(R.id.tv_recipe_ingredients, pendingIntent);
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    /**
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     * @param head
     * @param text
     */
    public static void onUpdateRecipe(Context context, AppWidgetManager appWidgetManager
            , int[] appWidgetIds
            , int head, List<Ingredient> text) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, head, text);
        }


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

