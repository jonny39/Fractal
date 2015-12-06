package edu.byu.fractal;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class FractalViewerFragment extends android.app.Fragment {

    private List<TriangleStuff> mFractalLayers = new ArrayList<>();
    int layers =0;
    private ViewGroup viewGroup;

    public FractalViewerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fractal_viewer, container, false);
        Button colorChooserButton = (Button) v.findViewById(R.id.color_chooser_button);
        colorChooserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showFragment(new ColorChooserFragment());
            }
        });
        int color = Prefs.getInt(ColorChooserFragment.COLOR_1, -1);
        viewGroup = (ViewGroup) v.findViewById(R.id.fractal_view_group);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layers < 8) {
                    drawLayer();
                }
            }
        });

        return v;
    }

    private int getCenterX() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.x/2;
    }
    private int getCenterY() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.y/2;
    }

    private void drawLayer()
    {
        //Toast.makeText(getActivity(), "Layer " + String.valueOf(layers+1), Toast.LENGTH_SHORT).show();

        if (layers == 0) {
            Point firstBase = new Point(getCenterX()-30,getCenterY());
            int angle = 90;
            int sideLength1 = 450;
            Triangle triangle = new Triangle(getActivity(), firstBase, angle, sideLength1);
            triangle.color = Prefs.getInt(ColorChooserFragment.COLOR_1,-1);
            TriangleStuff rightSide = new TriangleStuff(triangle.getRightMidpoint(),triangle.getRightAngle(),triangle.getSideLength()/2);
            TriangleStuff leftSide = new TriangleStuff(triangle.getLeftMidpoint(),triangle.getLeftAngle(),triangle.getSideLength()/2);
            TriangleStuff bottomSide = new TriangleStuff(triangle.getBasePoint(),triangle.getBaseAngle(),triangle.getSideLength()/2);
            mFractalLayers.add(rightSide);
            mFractalLayers.add(leftSide);
            mFractalLayers.add(bottomSide);
            layers++;
            draw(triangle);
        }
        else if (layers == 1) {
            Triangle triangleRight = new Triangle(getActivity(),mFractalLayers.get(0).point,mFractalLayers.get(0).angle,mFractalLayers.get(0).length);
            draw(triangleRight);
            triangleRight.color = Prefs.getInt(ColorChooserFragment.COLOR_2,-1);
            Triangle triangleLeft = new Triangle(getActivity(),mFractalLayers.get(1).point,mFractalLayers.get(1).angle,mFractalLayers.get(1).length);
            draw(triangleLeft);
            triangleLeft.color = Prefs.getInt(ColorChooserFragment.COLOR_2,-1);
            Triangle triangleBottom = new Triangle(getActivity(),mFractalLayers.get(2).point,mFractalLayers.get(2).angle,mFractalLayers.get(2).length);
            draw(triangleBottom);
            triangleBottom.color = Prefs.getInt(ColorChooserFragment.COLOR_2,-1);
            mFractalLayers.clear();
            addStuff(triangleRight);
            addStuff(triangleLeft);
            addStuff(triangleBottom);
            layers++;
        }
        else {
            List<TriangleStuff> currentLayer = new ArrayList<>();
            currentLayer.addAll(mFractalLayers);
            mFractalLayers.clear();
            for(TriangleStuff t:currentLayer) {
                Triangle triangle = new Triangle(getActivity(), t.point, t.angle, t.length);
                if(layers %2 == 0)
                {
                    triangle.color = Prefs.getInt(ColorChooserFragment.COLOR_1,-1);
                }
                else
                {
                    triangle.color = Prefs.getInt(ColorChooserFragment.COLOR_2,-1);
                }
                draw(triangle);
                addStuff(triangle);
            }
            layers++;
        }

    }

    private void addStuff(Triangle triangle) {
        TriangleStuff rightSide = new TriangleStuff(triangle.getRightMidpoint(),triangle.getRightAngle(),triangle.getSideLength()/2);
        TriangleStuff leftSide = new TriangleStuff(triangle.getLeftMidpoint(),triangle.getLeftAngle(),triangle.getSideLength()/2);
        mFractalLayers.add(rightSide);
        mFractalLayers.add(leftSide);
    }

    private void draw(Triangle triangle) {
        viewGroup.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        viewGroup.addView(triangle);

    }
}
