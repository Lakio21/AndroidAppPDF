package com.inc.lakio.androidapppdf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.inc.lakio.androidapppdf.Controller.ActivitiesController;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Show;

import java.util.ArrayList;


public class PlanningCreate extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ArrayList<Show> representationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_create);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ListView listPlanning = (ListView) findViewById(R.id.listRepresentation);
        listPlanning.setAdapter(new ListPlanningAdapter(this, representationList));

        Intent intent = getIntent();

        if(intent != null)
        {
            if(representationList != null) {
                representationList.add((Show) intent.getSerializableExtra("entityObject"));
                listPlanning.getAdapter().notify();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle SavedInstanceState) {
        super.onSaveInstanceState(SavedInstanceState);

        SavedInstanceState.putSerializable("listPlanning", representationList);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            representationList = (ArrayList<Show>) savedInstanceState.getSerializable("listPlanning");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_planning_create, menu);
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

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    public void onClickCreatePlanning(View view)
    {
        ActivitiesController.navigate(this, PlanningCreateDetail.class);
    }
}
