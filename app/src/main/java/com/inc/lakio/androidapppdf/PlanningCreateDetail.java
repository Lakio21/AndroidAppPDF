package com.inc.lakio.androidapppdf;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.inc.lakio.androidapppdf.Controller.ActivitiesController;
import com.inc.lakio.androidapppdf.Controller.MainController;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Show;

import java.util.ArrayList;
import java.util.List;


public class PlanningCreateDetail extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ArrayList<Show> showsList;
    private ArrayList<Representation> representationList;
    private Show selectedShow;
    private Representation selectedSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_create_detail);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ListView listSpectacle = (ListView) findViewById(R.id.listSpectacle);
        listSpectacle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                refreshHoraire(showsList.get(position).getSchedules());
            }
        });

        ListView listHoraire = (ListView) findViewById(R.id.listHoraires);
        listHoraire.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSchedule = representationList.get(position);
            }
        });

        refreshSpectacles();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_planning_create_detail, menu);
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
        if (id == R.id.listSpectacle) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    public void onClickAddSpectacle(View view) {

        Show add = selectedShow;
        selectedShow.setSelectedSchedule(selectedSchedule);
        ActivitiesController.navigate(this, PlanningCreate.class, add);
    }


    public void refreshSpectacles() {


        MainController mainController = new MainController();

        showsList = mainController.getGlobalPlanning();
        ListShowsAdapter adapter = new ListShowsAdapter(this, showsList);

        ListView list = (ListView) findViewById(R.id.listSpectacle);
        list.setAdapter(adapter);


    }

    public void refreshHoraire(ArrayList<Representation> listSchedule) {

        representationList = listSchedule;
        MainController mainController = new MainController();

        showsList = mainController.getGlobalPlanning();
        ListRepresentationAdapter adapter = new ListRepresentationAdapter(this, listSchedule);

        ListView listHortaire = (ListView) findViewById(R.id.listHoraires);
        listHortaire.setAdapter(adapter);
    }
}
