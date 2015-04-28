package v.assignment_3_skanetrafiken;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;
import v.assignment_3_skanetrafiken.SkaneAPI.control.Constants;
import v.assignment_3_skanetrafiken.SkaneAPI.model.Journey;
import v.assignment_3_skanetrafiken.SkaneAPI.model.Journeys;
import java.util.ArrayList;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class ExpandFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ArrayList<Journey> journeyList = new ArrayList<Journey>();
    private MyAdapter myAdapter;
    private int isSpinnerInitial = 0;
    private int x = 1;
    private ProgressDialog nDialog;
    int fav1 = 9999999;
    int fav2 = 9999999;
    boolean newFav = true;

    public ExpandFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ExpandableListView ev = (ExpandableListView) v.findViewById(R.id.expandableListView);
        myAdapter = new MyAdapter(getActivity(),journeyList);
        ev.setAdapter(myAdapter);

        Spinner sp = (Spinner)v.findViewById(R.id.spinFran);
        sp.setOnItemSelectedListener(this);

        Spinner sp2 = (Spinner)v.findViewById(R.id.spinTill);
        sp2.setOnItemSelectedListener(this);

        return v;
    }

    ///Listens to menu selection.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.refresh) {
            Log.i("ExpFragment", "Refresh pressed.");
            refreshResults();
            return true;
        } else if(id == R.id.favorite){
            Log.i("ExpFragment", "Favorite pressed.");
            setFavorite();
            return true;
        } else if(id == R.id.deleteFav){
            Log.i("ExpFragment", "delFav pressed.");
            delFavorite();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }


    //Listens to the spinnerit p
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinTill:
                if(isSpinnerInitial <3){
                    isSpinnerInitial++;
                } else{
                    refreshResults();
                    break;
                }
            case R.id.spinFran:
                if(isSpinnerInitial <3){
                    isSpinnerInitial++;
                } else{
                    refreshResults();
                    break;
                }
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void refreshResults(){
        Log.i("ExpFragment", "nr:" + x);
        Spinner sp = (Spinner)getView().findViewById(R.id.spinTill);
        Spinner sp2 = (Spinner)getView().findViewById(R.id.spinFran);

        int i = sp.getSelectedItemPosition();
        String[] sa = getResources().getStringArray(R.array.stationNbr);
        String sb = sa[i];

        int i2 = sp2.getSelectedItemPosition();
        String[] sa2 = getResources().getStringArray(R.array.stationNbr);
        String sb2 = sa2[i2];

        if(i==i2){
            messageBox("Select a different choice.");
        }else {
            String searchURL = Constants.getURL(sb, sb2, 10);
            new doInBackground().execute(searchURL);
            x++;
        }
    }

    public void setFavorite(){
        if(newFav == true) {
            Spinner sp = (Spinner) getView().findViewById(R.id.spinTill);
            Spinner sp2 = (Spinner) getView().findViewById(R.id.spinFran);

            int temp1 = sp.getSelectedItemPosition();
            int temp2 = sp2.getSelectedItemPosition();

            if(temp1==temp2){
                messageBox("Select a different choice.");
            } else{
                fav1 = temp1;
                fav2 = temp2;
                messageBox("Favorite set!");
                newFav = false;
            }

        } else{
            useFavorite();
        }
    }

    public void useFavorite(){
        if(fav1 == 9999999){
            messageBox("Favorite not set.");
        } else {
            Spinner sp = (Spinner) getView().findViewById(R.id.spinTill);
            Spinner sp2 = (Spinner) getView().findViewById(R.id.spinFran);

            sp.setSelection(fav1);
            sp2.setSelection(fav2);
            messageBox("Favorite used.");
            refreshResults();
        }
    }

    public void delFavorite(){
        fav1 = 9999999;
        fav2 = 9999999;
        newFav = true;
        messageBox("Favorite deleted.");
    }

    private class doInBackground extends AsyncTask<String,Void,Long> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            nDialog = new ProgressDialog(getActivity(), R.style.MyTheme);
            nDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
            nDialog.setCancelable(true);
            nDialog.show();
        }

        @Override
        protected Long doInBackground(String... params) {
            try {
                Journeys journeys = v.assignment_3_skanetrafiken.SkaneAPI.parser.Parser.getJourneys(params[0]); //There can be many in the params Array
                journeyList.clear();
                journeyList.addAll(journeys.getJourneys());
            }
            catch(Exception e) {
                messageBox(getString(R.string.errorNoNet));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Long result) { //Called when the AsyncTask is all done
            nDialog.dismiss();
            myAdapter.notifyDataSetChanged();
        }
    }

    private void messageBox(String message) {
        Toast.makeText(getView().getContext(), message, Toast.LENGTH_SHORT).show();
    }

}