package com.coveiot.leaderboard.views.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class SelectAddressActivity extends BaseActivity {
    public ConstraintLayout p;

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SelectAddressActivity.this.onBackPressed();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.CONFIRMATION_POPUP.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BOTTOM_MENU_RANK_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_select_address);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.toolbar);
        this.p = constraintLayout;
        ((TextView) constraintLayout.findViewById(R.id.toolbar_title)).setText(R.string.rank_badges);
        this.p.setOnClickListener(new a());
    }
}
