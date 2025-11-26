package com.example.menulistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ShoppingListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> items;

    public ShoppingListAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview, parent, false);
        }

        ImageView image = convertView.findViewById(R.id.itemImage);
        TextView name = convertView.findViewById(R.id.itemName);
        TextView quantity = convertView.findViewById(R.id.itemQuantity);
        Button deleteBtn = convertView.findViewById(R.id.btnDelete);

        final Item item = items.get(position);

        image.setImageResource(item.getImagenResId());
        name.setText(item.getNombre());
        quantity.setText(context.getString(R.string.quantity_format, item.getCantidad()));

        deleteBtn.setOnClickListener(v -> {
            items.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
