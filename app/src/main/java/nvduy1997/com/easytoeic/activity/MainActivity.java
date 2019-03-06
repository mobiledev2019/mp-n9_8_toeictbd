
package nvduy1997.com.easytoeic.activity;

import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.fragment.ListTestFragment;
import nvduy1997.com.easytoeic.fragment.ReadingFragment;
import nvduy1997.com.easytoeic.fragment.QuestionTestFragment;
import nvduy1997.com.easytoeic.fragment.VOAFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ReadingFragment.OnClickOpenFragment, ListTestFragment.OnClickOpenTestP5 {

    private ReadingFragment readingFragment;
    private ListTestFragment listTestFragment;
    private QuestionTestFragment questionTestFragment;
    public static final String TAG = "ID_TEST";

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

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.vocabolary) {

        } else if (id == R.id.mini_test) {

        } else if (id == R.id.listen_engish) {

        } else if (id == R.id.readding_engish) {
            readingFragment = new ReadingFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, readingFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.dictionary) {

        } else if (id == R.id.voa) {
            VOAFragment voaFragment= new VOAFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, voaFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.login) {

        } else if (id == R.id.share) {

        } else if (id == R.id.about_me) {

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClickOpenFragment() {
        if (listTestFragment == null) {
            listTestFragment = new ListTestFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listTestFragment).commit();
        Log.d("onClickOpenFragment", "onClickOpenFragment: ");
    }


    @Override
    public void onClickOpenPart5(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(TAG, id);
        if (questionTestFragment == null) {
            questionTestFragment = new QuestionTestFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, questionTestFragment).commit();
        questionTestFragment.setArguments(bundle);
    }
    
}
