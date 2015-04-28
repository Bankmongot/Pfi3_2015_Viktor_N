package v.assignment_4;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class FragmentPlanet extends Fragment {




    public FragmentPlanet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;

        v = inflater.inflate(R.layout.fragment_planet, container, false);
        TextView planetName = (TextView) v.findViewById(R.id.textViewNamn);
        //        Book b = (Book) getArguments().getSerializable("book");
        FragmentList fl = new FragmentList();
        planetName.setText(fl.getName());
        TextView planetStats = (TextView) v.findViewById(R.id.textViewStats);
        planetStats.setText(fl.getStats());
        TextView planetFacts = (TextView) v.findViewById(R.id.textViewInfo);
        planetFacts.setText(fl.getFacts());
        ImageView planetImage = (ImageView) v.findViewById(R.id.planetImage);
        planetImage.setImageDrawable(fl.getImage());
        return v;
    }




}