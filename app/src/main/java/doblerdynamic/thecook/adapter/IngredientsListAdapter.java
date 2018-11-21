package doblerdynamic.thecook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import doblerdynamic.thecook.R;
import doblerdynamic.thecook.model.Ingredient;

public class IngredientsListAdapter extends BaseAdapter {

    private List<Ingredient> item;

    // override other abstract methods here
    public IngredientsListAdapter(List<Ingredient> ingredientList) {
        this.item = ingredientList;
    }

    @Override
    public int getCount() {
        if (this.item != null) {
            return this.item.size();
        }
        return 0;
    }

    @Override
    public Ingredient getItem(int i) {
        return this.item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(container.getContext())
                    .inflate(R.layout.lv_list_ingredients, container, false);

//                convertView = getLayoutInflater().inflate(R.layout.lv_list_ingredients, container, false);
        }

        ((TextView) convertView.findViewById(R.id.tv_ingredients_lv_title)).setText(getItem(position).getIngredient());
        ((TextView) convertView.findViewById(R.id.tv_ingredients_lv_quanitty)).setText(getItem(position).getQuantity());
        ((TextView) convertView.findViewById(R.id.tv_ingredients_lv_measure)).setText(getItem(position).getMeasure());
//            (TextView) convertView.findViewById(android.R.id.tv_ingredients_lv_)).setText(getItem(position));


        return convertView;
    }
}
//}
