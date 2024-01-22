package com.coveiot.leaderboard.views.activities;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.eventbus.CloveEventBusManager;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.leaderboard.views.fragment.LeaderBoardFTUFragment;
/* loaded from: classes9.dex */
public class LeaderBoardActivity extends BaseActivity {
    public ImageView p;

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LeaderBoardActivity.this.onLocationClick();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_leader_board);
        ButterKnife.bind(this);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.p = (ImageView) findViewById(R.id.location);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.contentContainer);
        this.p.setOnClickListener(new a());
    }

    public void onLocationClick() {
        LeaderBoardNavigator.navigateToSelectAddressScreen(this);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        q(this);
        CloveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        CloveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    public final void q(Context context) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (!LeaderBoardDataUtiils.isFirstTimeLeaderBoardShown(context)) {
            beginTransaction.replace(R.id.contentContainer, new LeaderBoardFTUFragment());
            beginTransaction.commit();
        } else if (LeaderBoardDataUtiils.isLocationOnBoardingDone(this)) {
        } else {
            LeaderBoardNavigator.navigateToSelectAddressScreen(this);
            finish();
        }
    }
}
