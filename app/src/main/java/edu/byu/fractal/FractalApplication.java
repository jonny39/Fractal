package edu.byu.fractal;

import android.app.Application;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by Jessica Erickson on 11/18/2015.
 */
public class FractalApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Prefs.initPrefs(this);
    }
}
