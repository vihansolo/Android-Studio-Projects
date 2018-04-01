package com.suraj.androidtraining.Activity;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.suraj.androidtraining.R;

public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBar actionBar;
    private BottomSheetBehavior mBottomSheetBehaviour;
    private Button btnBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        btnBottomSheet = findViewById(R.id.btnBottomSheet);
        btnBottomSheet.setOnClickListener(this);
        setActionBar();

        View nestedScrollView = findViewById(R.id.nestedScrollView);
        mBottomSheetBehaviour = BottomSheetBehavior.from(nestedScrollView);
//        mBottomSheetBehaviour.setPeekHeight(200);    //Set the peek height

//        mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);    // Will show the bottom sheet
//        mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);    // Will hide the bottom sheet

        mBottomSheetBehaviour.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                String state = "";

                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING: {
                        state = "DRAGGING";
                        break;
                    }
                    case BottomSheetBehavior.STATE_SETTLING: {
                        state = "SETTLING";
                        break;
                    }
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        state = "EXPANDED";
                        break;
                    }
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        state = "COLLAPSED";
                        break;
                    }
                    case BottomSheetBehavior.STATE_HIDDEN: {
                        state = "HIDDEN";
                        break;
                    }
                }

                //Toast.makeText(BottomSheetActivity.this, "Bottom Sheet State Changed to: " + state, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });
    }

    private void setActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Bottom Sheet Demo");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBottomSheet:
                mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);    // Will show the bottom sheet
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
