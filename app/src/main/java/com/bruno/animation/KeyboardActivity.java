package com.bruno.animation;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;


public class KeyboardActivity extends ActionBarActivity {
    private Button mShowButton;
    private EditText mEditText;
    private LinearLayout mBottomLayout;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        mBottomLayout = (LinearLayout) findViewById(R.id.bottomLayout);
        mShowButton = (Button) findViewById(R.id.showBtn);
        mEditText = (EditText) findViewById(R.id.editText);
        mListView = (ListView) findViewById(R.id.listView);

        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBottomLayout.getVisibility()==View.VISIBLE){
                    mBottomLayout.setVisibility(View.GONE);
                    mShowButton.setText(R.string.show);

                    InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.toggleSoftInputFromWindow(mEditText.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
                }else{
                    mBottomLayout.setVisibility(View.VISIBLE);
                    mShowButton.setText(R.string.hide);

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
                }
            }
        });


        String[] countries = getResources().getStringArray(R.array.countries);
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries));
        mListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        mListView.setStackFromBottom(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_keyboard, menu);
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
}
