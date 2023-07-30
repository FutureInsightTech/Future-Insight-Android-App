package com.futureinsight.futureinsight;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;

import com.org.futureinsight.R;
import com.futureinsight.futureinsight.Utility.NetworkBroadcast;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.org.futureinsight.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    private long pressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            binding = ActivityHomeBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            setSupportActionBar(binding.appBarHome.toolbar);
            DrawerLayout drawer = binding.drawerLayout;
            NavigationView navigationView = binding.navView;
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);


            //checking internet & Calling & Setting the broadcast receiver
            broadcastReceiver = new NetworkBroadcast();
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        }catch (Exception e) {
            System.out.println("Exception: " + e);
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Application is Down");
        }

    }
//    Setting Button in the UI and it is disable
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

//    This is the logic for press back button
    public void backbutton()
    {
        if(pressTime+2000>System.currentTimeMillis())
        {
            super.onBackPressed();
            finish();
        }
        pressTime= System.currentTimeMillis();

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //Destory Reciever to free resources allocated
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

}