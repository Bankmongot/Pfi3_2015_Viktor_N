package v.assignment_3_skanetrafiken.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import v.assignment_3_skanetrafiken.ExpandFragment;
import v.assignment_3_skanetrafiken.R;
import v.assignment_3_skanetrafiken.SkaneAPI.model.Journey;


public class MainAct extends ActionBarActivity {
    private ArrayList<Journey> journeyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Set MainFragment as active layout. */
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ExpandFragment mofo = new ExpandFragment();
        ft.replace(R.id.cont, mofo);
        ft.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
