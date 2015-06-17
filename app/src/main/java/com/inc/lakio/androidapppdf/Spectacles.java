package com.inc.lakio.androidapppdf;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.inc.lakio.androidapppdf.Controller.MainController;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Show;
import com.inc.lakio.androidapppdf.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Spectacles extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spectacles);

        refreshPlanningList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spectacles, menu);
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

    public void refreshPlanningList() {

        mainController = new MainController();

        //Test de fonctionnement
        /*ArrayList<Show> truc = new ArrayList<>();
        Show machin = new Show();
        machin.setName("truc");
        Date chose = new Date();
        chose.setTime(1434528174);
        ArrayList<Representation> bidulle = new ArrayList<>();
        bidulle.add(new Representation(machin.getId(),chose));

        machin.setSchedules(bidulle);

        truc.add(machin);*/

        ListShowsAdapter adapter = new ListShowsAdapter(this, mainController.getGlobalPlanning());
        //ListShowsAdapter adapter = new ListShowsAdapter(this, truc);

        ListView list = (ListView)findViewById(R.id.listPlanning);
        list.setAdapter(adapter);

    }
}
