package com.example.alquilervehiculosmobile.vistas;

import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;

import com.example.alquilervehiculosmobile.R;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new PrincipalFragment()).commit();
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_principal));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_menu_principal) {
            fragmentManager.beginTransaction().replace(R.id.container, new PrincipalFragment()).commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.menu_principal));
        } else if (id == R.id.nav_administrar_vehiculos) {
            fragmentManager.beginTransaction().replace(R.id.container, new AdministrarVehiculoFragment()).commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.administrar_vehiculos));
        } else if (id == R.id.nav_administrar_usuarios) {
            fragmentManager.beginTransaction().replace(R.id.container, new AdministrarUsuarioFragment()).commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.administrar_usuarios));
        } else if (id == R.id.nav_administrar_alquileres) {
            fragmentManager.beginTransaction().replace(R.id.container, new AdministrarAlquilerFragment()).commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.administrar_alquileres));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
