package v.assignment_3_skanetrafiken;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import v.assignment_3_skanetrafiken.SkaneAPI.model.Journey;
import java.util.ArrayList;


public class MyAdapter extends BaseExpandableListAdapter {
    private ArrayList<Journey> myItems;
    private Context c;

    public MyAdapter(Context c, ArrayList<Journey> myItems){
        this.c=c;
        this.myItems = myItems;
    }

    @Override
    public int getGroupCount() {
        return myItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.groupexpand_layout,null);

        ImageView setPic = (ImageView) convertView.findViewById(R.id.imageView);
        ImageView setWarning = (ImageView) convertView.findViewById(R.id.imageView2);

        //START STATION
        TextView tvCourse = (TextView) convertView.findViewById(R.id.tv_One);
        String course = myItems.get(groupPosition).getLineTypeName();
        tvCourse.setText(String.valueOf((course)));


        switch (course){
            case "Tåg":
             setPic.setImageResource(R.drawable.trainicon);
                break;
            case "Stadsbuss":
               setPic.setImageResource(R.drawable.busicon);
                break;
            case "Gång":

                break;
            case "Regionbuss":
                // setPic.setImageResource(R.drawable.busr);
                break;
        }
        //END STATION
        TextView tvDate =(TextView) convertView.findViewById(R.id.tv_Two);
        String date = myItems.get(groupPosition).getTimeToDeparture();

        tvDate.setText("Departs in: "+String.valueOf(date)+" minutes.");

        int minutesLeft = Integer.parseInt(date);

        if(minutesLeft <=5){
            setWarning.setImageResource(R.drawable.hurrypic);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.group_child_layout,null);

        // JOURNEY TIME
        TextView tvOne = (TextView) convertView.findViewById(R.id.tv_cOne);
        String moment = myItems.get(groupPosition).getTravelMinutes();
        tvOne.setText("The journey is " + moment + " minutes long.");

        // DEP TIME DEV
        TextView tvTwo = (TextView) convertView.findViewById(R.id.tv_cTwo);
        String room = myItems.get(groupPosition).getDepTimeDeviation();

        if(room!="") {
            int delayVal = Integer.parseInt(room);
            if (delayVal > 0) {
                tvTwo.setText("Departs " + delayVal + " minutes late.");
            } else if(delayVal <0){
                tvTwo.setText(delayVal + " minutes early.");
            } else{
                tvTwo.setText("On schedule.");
            }
        } else{
            tvTwo.setVisibility(View.GONE);
        }

        // TIME TO DEPARTURE
        /*TextView tvStart = (TextView) convertView.findViewById(R.id.tv_cThree);
        String start = myItems.get(groupPosition).getTimeToDeparture();
        tvStart.setText(start); */

        // TRAVEL MINUTES
        /*TextView tvEnd = (TextView) convertView.findViewById(R.id.tv_cFour);
        String end = myItems.get(groupPosition).getTravelMinutes();
        tvEnd.setText(end); */

        return convertView;
    }


    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}