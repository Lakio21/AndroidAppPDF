package com.inc.lakio.androidapppdf;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.inc.lakio.androidapppdf.Controller.ActivitiesController;
import com.inc.lakio.androidapppdf.Controller.MainController;

import java.util.ArrayList;


public class Planning extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_planning, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickVoirPlanning(View view) {
        ActivitiesController.navigate(this, PlanningView.class);
    }

    public void onClickCreerPlanning(View view) {
        ActivitiesController.navigate(this, PlanningCreate.class);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }


}
