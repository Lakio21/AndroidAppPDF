package com.inc.lakio.androidapppdf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.inc.lakio.androidapppdf.Controller.MainController;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Show;

import java.util.ArrayList;


public class SpectacleDetail extends Activity {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    MainController mainController;
    GridView grid;
    TextView tvDuree;
    TextView tvDescription;
    TextView tvEmplacement;
    TextView tvNoteMoyenne;
    EditText tbNote;
    Show spectacle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spectacle_detail);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        GridView grid = (GridView) findViewById(R.id.gridView);
        tvDuree = (TextView) findViewById(R.id.tvDuree);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvEmplacement = (TextView) findViewById(R.id.tvEmplacement);
        tvNoteMoyenne = (TextView) findViewById(R.id.tvNoteMoyenne);
        tbNote = (EditText) findViewById(R.id.tbVote);

        Intent intent = getIntent();

        if (intent != null) {
            spectacle = (Show) intent.getSerializableExtra("entityObject");

            grid.setAdapter(new GridViewHoraireAdapter(this, spectacle.getSchedules()));

            tvDuree.setText("Durée : " + spectacle.getDuration() + " min");
            tvDescription.setText(spectacle.getDescription());
            tvEmplacement.setText("Emplacement : " + spectacle.getLocationTag());
            tvNoteMoyenne.setText("Note moyenne : " + spectacle.getAverageNote());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spectacle_detail, menu);
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

    public void onClickVoter(View view)
    {
        if(Integer.parseInt(tbNote.getText().toString()) <= 0 || Integer.parseInt(tbNote.getText().toString()) >= 5)
        {
            mainController.vote(spectacle, Integer.parseInt(tbNote.getText().toString()));
        }
        else
        {
            Toast.makeText(this, "Note invalide",Toast.LENGTH_SHORT);
        }
    }

    public void onClickAnecdote(View view)
    {

    }
}
