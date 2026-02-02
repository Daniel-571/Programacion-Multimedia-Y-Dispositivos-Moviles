package com.example.tema7;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.example.tema7.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);

            TabLayout tabLayout = findViewById(R.id.tabs);
            ViewPager viewPager=findViewById(R.id.viewPager);

            ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new fragment_pestana1(),"hola 1");
            adapter.addFragment(new fragment_pestana2(),"hola 2");
            adapter.addFragment(new fragment_pestana3(),"hola 3");

            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }
        tabLayout.addOnSelectedListener(new TabLayout.OnTabSelectedListener){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                int position=tab.getPosition();
            Toast.makeText(MainActivity.this,"Pestaña "+(position + 1)+" seleccionada", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onTabUnselected(TabLayout.Tab tab){

        }
    }
    }
