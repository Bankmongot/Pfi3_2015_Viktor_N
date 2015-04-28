package v.assignment_4;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfoDialog extends FragmentInfo implements View.OnClickListener {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Viktor Nilsson");
        View v = inflater.inflate(R.layout.fragment__dialog, container, false);



        TextView tv_description = (TextView) v.findViewById(R.id.tv_dialog_description);
        tv_description.setText(R.string.presentation);
        ImageView iv = (ImageView) v.findViewById(R.id.iv_dialog);
        v.setOnClickListener(this);
        tv_description.setOnClickListener(this);
        iv.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
       FragmentInfoDialog.this.dismiss();

    }
}
