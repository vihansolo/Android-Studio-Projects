package com.suraj.androidtraining.Activity;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.suraj.androidtraining.R;
import com.suraj.androidtraining.Utils.Constant;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private ActionBar actionBar;
    private ImageView imgSampleLogo;
    private int mAnimationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        findViews();
        setActionBar();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            if(bundle.containsKey(Constant.MSTR_ANIMATION_TYPE_KEY)) {
                mAnimationType = bundle.getInt(Constant.MSTR_ANIMATION_TYPE_KEY);
                setAnimation(mAnimationType);
            }
        }
    }

    private void findViews() {
        imgSampleLogo = findViewById(R.id.imgSampleLogo);
        imgSampleLogo.setOnClickListener(this);
    }

    private void setActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /** set the Animation according to type
     * @param AnimationType animation type to be set
     */
    private void setAnimation(int AnimationType) {
        switch (AnimationType) {
            case Constant.MI_ANIMATION_FADE:
                actionBar.setTitle("FADE Animation");
                break;
            case Constant.MI_ANIMATION_ROTATE:
                actionBar.setTitle("Rotate Animation");
                break;
            case Constant.MI_ANIMATION_SCALE:
                actionBar.setTitle("SCALE Animation");
                break;
            case Constant.MI_ANIMATION_SLIDE:
                actionBar.setTitle("SLIDE Animation");
                break;
            case Constant.MI_ANIMATION_SHARED_ELEMENT:
                actionBar.setTitle("SHARED Animation");
                break;
            default:
                break;
        }
    }

    private void doAnimation() {
        switch (mAnimationType) {
            case Constant.MI_ANIMATION_FADE:
                fadeInAnim();
                break;
            case Constant.MI_ANIMATION_ROTATE:
                rotateAnim();
                break;
            case Constant.MI_ANIMATION_SCALE:
                scaleAnim();
                break;
            case Constant.MI_ANIMATION_SLIDE:
                slideAnim();
                break;
            case Constant.MI_ANIMATION_SHARED_ELEMENT:
                doShareElementTransition();
                break;
            default:
                break;
        }
    }

    private void doShareElementTransition() {
        Intent intent = new Intent(this, SharedElementTransitionActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this,
                        imgSampleLogo, "logo");
        startActivity(intent, options.toBundle());
    }

    private void fadeInAnim() {
        loadAnim(R.anim.fade_in);
    }

    private void rotateAnim() {
        loadAnim(R.anim.rotate);
    }

    private void scaleAnim() {
        loadAnim(R.anim.scale);
    }

    private void slideAnim() {
        loadAnim(R.anim.slide);
    }

    private void loadAnim(int animType) {
        Animation animation = AnimationUtils.loadAnimation(this, animType);
        imgSampleLogo.clearAnimation();
        imgSampleLogo.startAnimation(animation);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgSampleLogo:
                doAnimation();
                break;
            default:
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
