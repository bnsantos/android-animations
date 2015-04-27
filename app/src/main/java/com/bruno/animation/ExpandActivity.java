package com.bruno.animation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by bruno on 24/04/15.
 */
public class ExpandActivity extends ActionBarActivity {
    private ImageView mCircleView;
    private Button mButton;
    private DisplayMetrics mMetrics;
    private Mode mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);

        mMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);

        mCircleView = (ImageView) findViewById(R.id.imageView);

        mButton = (Button) findViewById(R.id.button);

        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        selectAnimation(isFingerOutsideButton(event.getX(), event.getY(), v));
                        return true;
                    case MotionEvent.ACTION_UP:
                        shrink();
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        expand();
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isFingerOutsideButton(float x, float y, View v){
        float centerX = v.getHeight() / 2;
        float centerY = v.getWidth() / 2;
        double d = x - centerX;
        double d1 = y - centerY;

        return Math.pow(d1, 2) + Math.pow(d, 2) > Math.pow(v.getHeight() / 2, 2);
    }

    private void selectAnimation(boolean isOutside){
        if(isOutside){
            if(mMode!=null&&mMode!=Mode.SHRINK){
                shrink();
            }
        }else{
            if(mMode!=null&&mMode!=Mode.EXPAND){
                expand();
            }
        }
    }

    private void expand(){
        mMode = Mode.EXPAND;
        mButton.setBackgroundResource(R.drawable.round_white);
        mButton.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        ResizeAnimation resizeAnimation = new ResizeAnimation(mCircleView, mMetrics.heightPixels, mMetrics.heightPixels);
        resizeAnimation.setDuration(200);
        mCircleView.startAnimation(resizeAnimation);
    }

    private void shrink(){
        mMode = Mode.SHRINK;
        mButton.setBackgroundResource(R.drawable.round_colored);
        mButton.setTextColor(getResources().getColor(android.R.color.white));
        ResizeAnimation resizeAnimation = new ResizeAnimation(mCircleView, mButton.getWidth(), mButton.getHeight());
        resizeAnimation.setDuration(100);
        mCircleView.startAnimation(resizeAnimation);
    }

    public enum Mode{
        EXPAND, SHRINK
    }
}
