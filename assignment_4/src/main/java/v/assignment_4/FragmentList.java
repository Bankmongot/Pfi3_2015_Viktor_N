package v.assignment_4;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import v.assignment_4.R;

import java.util.ArrayList;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class FragmentList extends Fragment {

    // Create an arraylist of planets.

    public static ArrayList<Planet> planets = new ArrayList<Planet>();
    public static int currentPlanet;
    //This method comes first so lets create the books here
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Clear arraylist and add desired planets, giving them a the following values; "name", "radius" and "avarage temperature" and a drawable image

        planets.clear();
        Drawable d = getResources().getDrawable(R.drawable.mars);
        planets.add(new Planet(getResources().getString(R.string.mars),getResources().getString(R.string.marsNfo),getResources().getString(R.string.marsSize),getResources().getString(R.string.marsTemp) ,d));
        d = getResources().getDrawable(R.drawable.earth);
        planets.add(new Planet(getResources().getString(R.string.earth),getResources().getString(R.string.earthNfo),getResources().getString(R.string.earthSize),getResources().getString(R.string.earthTemp) ,d));
        d = getResources().getDrawable(R.drawable.venus);
        planets.add(new Planet(getResources().getString(R.string.venus),getResources().getString(R.string.venusNfo),getResources().getString(R.string.venusSize),getResources().getString(R.string.venusTemp) ,d));
        d = getResources().getDrawable(R.drawable.mercury);
        planets.add(new Planet(getResources().getString(R.string.mercury),getResources().getString(R.string.mercuryNfo),getResources().getString(R.string.mercurySize),getResources().getString(R.string.mercuryTemp) ,d));
        d = getResources().getDrawable(R.drawable.saturn);
        planets.add(new Planet(getResources().getString(R.string.saturn),getResources().getString(R.string.saturnNfo),getResources().getString(R.string.saturnSize),getResources().getString(R.string.saturnTemp) ,d));
        d = getResources().getDrawable(R.drawable.neptune);
        planets.add(new Planet(getResources().getString(R.string.neptune),getResources().getString(R.string.neptuneNfo),getResources().getString(R.string.neptuneSize),getResources().getString(R.string.neptuneTemp) ,d));
        d = getResources().getDrawable(R.drawable.jupiter);
        planets.add(new Planet(getResources().getString(R.string.jupiter),getResources().getString(R.string.jupiterNfo),getResources().getString(R.string.jupiterSize),getResources().getString(R.string.jupiterTemp) ,d));
        d = getResources().getDrawable(R.drawable.uranus);
        planets.add(new Planet(getResources().getString(R.string.uranus),getResources().getString(R.string.uranusNfo),getResources().getString(R.string.uranusSize),getResources().getString(R.string.uranusTemp) ,d));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        Log.i("FragmentList", "Number of planets added: " + planets.size());
        MyListAdapter la = new MyListAdapter(getActivity(),planets);
        GridView lv = (GridView) v.findViewById(R.id.gridView);
        lv.setAdapter(la);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPlanet = position;

                // Log and toast on list interaction
                Log.i("FragmentList", "Clicked on position: " + position +"/ " + planets.get(position).getName());
                //display in short period of time
                Toast.makeText(getActivity(), planets.get(position).getName(),
                        Toast.LENGTH_SHORT).show();

                // Fragment transaction on list interaction

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentPlanet bf = new FragmentPlanet();
                // byt layout till fragment2
                ft.replace(R.id.main_layout,bf);
                ft.commit();

            }

        });
        return v;
    }

    // Methods to use in order to get desired info for the fragment_planet fragment

    public String getName(){
        return planets.get(currentPlanet).getName();
    }

    public String getFacts(){
        return planets.get(currentPlanet).getInfo();

    }
    public String getStats(){
        return planets.get(currentPlanet).getSummary();

    }
    public Drawable getImage(){
        return planets.get(currentPlanet).getImage();
    }

}