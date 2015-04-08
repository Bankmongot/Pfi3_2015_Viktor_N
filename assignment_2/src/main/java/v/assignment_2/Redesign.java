package v.assignment_2;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Redesign extends Fragment implements View.OnClickListener {


    public Redesign() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v2 = inflater.inflate(R.layout.fragment_redesign, container, false);
        View myButtonView2 = v2.findViewById(R.id.sok_btn);
        myButtonView2.setOnClickListener(this);
        return v2;
    }


    @Override
    public void onClick(View v2) {
        MainActivity a2 = (MainActivity) getActivity();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft2 = fm.beginTransaction();
        Start bf2 = new Start();
        ft2.replace(R.id.main_lyt,bf2);
        ft2.commit();
    }
}
