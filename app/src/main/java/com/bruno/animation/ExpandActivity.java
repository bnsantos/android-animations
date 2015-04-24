package com.bruno.animation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
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
    private Mode mMode = Mode.EXPAND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);

        mMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);

        mCircleView = (ImageView) findViewById(R.id.imageView);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMode==Mode.EXPAND){
                    mMode = Mode.SHRINK;
                    mButton.setText(R.string.shrink);
                    mButton.setBackgroundResource(R.drawable.round_white);
                    mButton.setTextColor(getResources().getColor(android.R.color.black));
                    ResizeAnimation resizeAnimation = new ResizeAnimation(mCircleView, mMetrics.heightPixels, mMetrics.heightPixels);
                    resizeAnimation.setDuration(1200);
                    mCircleView.startAnimation(resizeAnimation);
                }else{
                    mMode = Mode.EXPAND;
                    mButton.setText(R.string.expand);
                    mButton.setBackgroundResource(R.drawable.round_colored);
                    mButton.setTextColor(getResources().getColor(android.R.color.white));
                    ResizeAnimation resizeAnimation = new ResizeAnimation(mCircleView, mButton.getWidth(), mButton.getHeight());
                    resizeAnimation.setDuration(300);
                    mCircleView.startAnimation(resizeAnimation);
                }
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

    public enum Mode{
        EXPAND, SHRINK
    }
}
