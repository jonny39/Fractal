package edu.byu.fractal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class FractalViewerFragment extends android.app.Fragment {

    public FractalViewerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fractal_viewer, container, false);
        FloatingActionButton colorChooserButton = (FloatingActionButton) v.findViewById(R.id.color_chooser_button);
        colorChooserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(new ColorChooserFragment());
            }
        });
        SharedPreferences pref = getActivity().getSharedPreferences(ColorChooserFragment.PACKAGE_NAME, Context.MODE_PRIVATE);
        int color = pref.getInt(ColorChooserFragment.COLOR_1,-1);
        if(color != -1)
        {
            Toast.makeText(getActivity(),"You choose the color " + color + "!", Toast.LENGTH_SHORT).show();
        }

        return v;
    }
}
