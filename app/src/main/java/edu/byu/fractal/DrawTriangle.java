package edu.byu.fractal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;

import com.pixplicity.easyprefs.library.Prefs;

import static java.lang.Math.*;

/**
 * Created by Jessica Erickson on 11/18/2015.
 */
public class DrawTriangle extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Point point1_draw;
    Point point2_draw;
    Point point3_draw;
    Path path = new Path();
    Context mContext;

    public DrawTriangle(Context context) {
        super(context);
        this.mContext = context;
        point1_draw = new Point(getCenterX(),getCenterY()-150);
        point2_draw = new Point(getCenterX()-200,getCenterY()+100);
        point3_draw = new Point(getCenterX()+200,getCenterY()+100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTriangle(canvas);
    }

    private void drawTriangle(Canvas canvas) {
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        paint.setStrokeWidth(2);
        int color = Prefs.getInt(ColorChooserFragment.COLOR_1, -1);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(point1_draw.x, point1_draw.y);
        path.lineTo(point2_draw.x, point2_draw.y);
        path.lineTo(point3_draw.x, point3_draw.y);
        path.lineTo(point1_draw.x, point1_draw.y);
        path.close();

        canvas.drawPath(path, paint);

    }

    private int getCenterX() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.x/2;
    }
    private int getCenterY() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.y/2;
    }
}
