package com.coveiot.leaderboard.views.activities;

import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.model.LeaderBoardNotificationModel;
import com.coveiot.leaderboard.views.fragment.MultipleBadgeNotificationFragment;
import com.coveiot.leaderboard.views.fragment.OneBadgeNotificationFragment;
import com.google.gson.Gson;
/* loaded from: classes9.dex */
public class LeaderBoardNotificationActivity extends BaseActivity {
    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_leader_board_notification);
        int i = R.id.notification_container;
        FrameLayout frameLayout = (FrameLayout) findViewById(i);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String string = extras.getString("data");
            LeaderBoardNotificationModel leaderBoardNotificationModel = (LeaderBoardNotificationModel) new Gson().fromJson(string, (Class<Object>) LeaderBoardNotificationModel.class);
            if (leaderBoardNotificationModel != null) {
                if (leaderBoardNotificationModel.getNoOfBadges() > 1) {
                    beginTransaction.replace(i, new MultipleBadgeNotificationFragment());
                    beginTransaction.commit();
                    return;
                }
                beginTransaction.replace(i, OneBadgeNotificationFragment.newInstance(string));
                beginTransaction.commit();
            }
        }
    }
}
