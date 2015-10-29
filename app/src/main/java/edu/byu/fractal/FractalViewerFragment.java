package edu.byu.fractal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        return v;
    }
}
