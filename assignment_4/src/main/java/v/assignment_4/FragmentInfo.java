package v.assignment_4;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Viktors on 2015-04-28.
 */
public class FragmentInfo extends DialogFragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__dialog, container, false);

        TextView tv_description = (TextView) v.findViewById(R.id.tv_dialog_description);

        ImageView iv = (ImageView) v.findViewById(R.id.iv_dialog);
        v.setOnClickListener(this);
        tv_description.setOnClickListener(this);
        iv.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {

        FragmentInfo.this.dismiss();

    }
}
