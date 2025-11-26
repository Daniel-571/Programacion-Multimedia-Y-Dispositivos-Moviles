package com.example.menulistview;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Item> shoppingList;
    private ShoppingListAdapter adapter;
    private ListView listView;
    private EditText editName, editQuantity;
    private Spinner spinnerImages;
    private int[] images = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoppingList = new ArrayList<>();
        adapter = new ShoppingListAdapter(this, shoppingList);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        editName = findViewById(R.id.editName);
        editQuantity = findViewById(R.id.editQuantity);
        spinnerImages = findViewById(R.id.spinnerImages);

        spinnerImages = findViewById(R.id.spinnerImages);
        ImageSpinnerAdapter spinnerAdapter = new ImageSpinnerAdapter(this, images);
        spinnerImages.setAdapter(spinnerAdapter);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            String name = editName.getText().toString();
            int quantity = Integer.parseInt(editQuantity.getText().toString());
            int imageRes = (int) spinnerImages.getSelectedItem();
            shoppingList.add(new Item(name, quantity, imageRes));
            adapter.notifyDataSetChanged();
        });
    }

    // Menú contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, getString(R.string.add));
        menu.add(0, v.getId(), 0, getString(R.string.delete));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle().equals(getString(R.string.delete))) {
            shoppingList.remove(info.position);
            adapter.notifyDataSetChanged();
        }
        return true;
    }
}
