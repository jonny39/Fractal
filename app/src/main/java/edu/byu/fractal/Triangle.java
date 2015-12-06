package edu.byu.fractal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

import com.pixplicity.easyprefs.library.Prefs;

import static java.lang.Math.*;

/**
 * Created by Jonathan Erickson on 11/18/2015.
 */
public class Triangle extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Point northPoint;
    private Point rightPoint;
    private Point leftPoint;
    private Point basePoint;
    private Point rightMidpoint;
    private Point leftMidpoint;
    private Path path = new Path();
    private Context mContext;
    private double northAngle;
    private double sideLength;
    int color;

    public Triangle(Context context, Point basePoint, double northAngle, double sideLength) {
        super(context);
        this.mContext = context;
        double crossLength = sqrt(.75) * sideLength;
        double northX = (double) basePoint.x - (crossLength * cos(toRadians(northAngle)));
        double northY = (double) basePoint.y - (crossLength * sin(toRadians(northAngle)));
        northPoint = new Point((int) northX, (int) northY);

        double rightX =  (double) basePoint.x + sideLength / 2 * cos(toRadians(northAngle - 90));
        double rightY = (double) basePoint.y + sideLength / 2 * sin(toRadians(northAngle - 90));
        rightPoint = new Point((int)rightX, (int)rightY);

        double leftX = basePoint.x + sideLength / 2 * cos(toRadians(northAngle + 90));
        double leftY = basePoint.y + sideLength / 2 * sin(toRadians(northAngle + 90));
        leftPoint = new Point((int)leftX,(int)leftY);

        double rightMidX = northX + (rightX - northX)*.5;
        double rightMidY = northY + (rightY - northY)*.5;
        rightMidpoint = new Point((int)rightMidX,(int)rightMidY);

        double leftMidX = northX - (northX - leftX)*.5;
        double leftMidY = northY + (leftY - northY)*.5;
        leftMidpoint = new Point ((int) leftMidX, (int) leftMidY);
        
        this.northAngle = northAngle;
        this.sideLength = sideLength;
        this.basePoint = basePoint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTriangle(canvas);
    }

    private void drawTriangle(Canvas canvas) {

        paint.setStrokeWidth(2);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(northPoint.x, northPoint.y);
        path.lineTo(rightPoint.x, rightPoint.y);
        path.lineTo(leftPoint.x, leftPoint.y);
        path.lineTo(northPoint.x, northPoint.y);
        path.close();

        canvas.drawPath(path, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawPath(path, paint);
    }

    public Point getBasePoint() {
        return basePoint;
    }

    public Point getRightMidpoint() {
        return rightMidpoint;
    }

    public Point getLeftMidpoint() {
        return leftMidpoint;
    }

    public double getRightAngle() {
        return northAngle + 60;
    }

    public double getLeftAngle() {
        return northAngle -60;
    }

    public double getBaseAngle() {
        return northAngle + 180;
    }

    public double getSideLength() {
        return sideLength;
    }
}
