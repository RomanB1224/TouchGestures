package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

//for gesture detector we have to implement two things
public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;


    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    //define a gesture detector to listen to events on the scroll view
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);
        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        //we are hooking up the gesture detector to our scroll view (can hook it up to any layout)
        //4 out of 5 gestures handled with this
        gestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
        //special case double tap
        gestureDetector.setOnDoubleTapListener(this);


    }
    //any touch event is now dispatched from the activity to the ScrollView
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
       super.dispatchTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        singleTaps++;
        singleTapTextView.setText(String.valueOf(singleTaps));
        //append to our gesture log:
        gesturesLogTextView.append("\nonSingleTapConfirmed touch event");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        doubleTaps++;
        gesturesLogTextView.append("\nonDoubleTap touch event");
        doubleTapTextView.setText(String.valueOf(doubleTaps));
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTapConfirmed touch event");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown touch event");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShowPress touch event");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp touch event");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        ++scrolls;
        scrollTextView.setText(String.valueOf(scrolls));
       gesturesLogTextView.append("\nonScroll: distanceX is " + v
        + ", distanceY is " + v1 );
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        longPresses++;
        longPressTextView.setText(String.valueOf(longPresses));
        gesturesLogTextView.append("\nonLongPress touch event");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        ++flings;
        flingTextView.setText(String.valueOf(flings));
        gesturesLogTextView.append("\nonFling: velocityX is " + v + ", velocityY is " + v1);
        return true;
    }

    public void clearAll(View view)
    {
        gesturesLogTextView.setText("");
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;

        singleTapTextView.setText("0");
        doubleTapTextView.setText("0");
        longPressTextView.setText("0");
        scrollTextView.setText("0");
        flingTextView.setText("0");

    }
}
