package com.septian.project004;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.septian.project004.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initview();
    }

    // menampilkan titik tiga menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    Fragment fragment = null;
    // handler titik tiga menu


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                fragment = new HomeFragment();
                getSupportActionBar().setTitle("Home");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.nav_contact_us:
                fragment = new ContactUsFragment();
                getSupportActionBar().setTitle("Contact Us");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case  R.id.nav_about_us:
                fragment = new AboutUsFragment();
                getSupportActionBar().setTitle("About Us");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            default:
                Toast.makeText(this, "No Menu is Selected ", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initview() {
        // custom toolbar
        setSupportActionBar(binding.toolbar);

        // default fragment dibuka pertama kali
        getSupportActionBar().setTitle("Home");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, new HomeFragment())
                .commit();
        binding.navView.setCheckedItem(R.id.nav_home);

        // membuka drawer
        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar,
        R.string.open, R.string.close);

        //drawer back button
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        // sinkronasi drawer
        toggle.syncState();

        // salah satu menu navigasi dipilih
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           Fragment fragment = null; // saya akan membuat fragment pertama kali null

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        getSupportActionBar().setTitle("Home");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;

                    case R.id.nav_contact_us:
                        fragment = new ContactUsFragment();
                        getSupportActionBar().setTitle("Contact Us");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;

                    case R.id.nav_about_us:
                        fragment = new AboutUsFragment();
                        getSupportActionBar().setTitle("About Us");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                }
                return true;
            }
        });
    }

    private void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);

        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}