package com.example.materialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fab = findViewById(R.id.fab);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(android.R.drawable.ic_menu_edit);
        tabLayout.getTabAt(1).setIcon(android.R.drawable.ic_menu_gallery);
        tabLayout.getTabAt(2).setIcon(android.R.drawable.ic_menu_info_details);


        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Acción realizada", Snackbar.LENGTH_LONG)
                        .setAction("OK", v -> {
                            FragmentoUno f = (FragmentoUno) getSupportFragmentManager()
                                    .findFragmentByTag("android:switcher:" + R.id.viewPager + ":0");

                            if (f != null) {
                                f.limpiarCorreo();
                            }
                        })
                        .show();
            }
        });
    }
}
