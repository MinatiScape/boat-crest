package com.coveiot.leaderboard.views.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class AllBadgesActivity extends BaseActivity {
    public ConstraintLayout p;

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AllBadgesActivity.this.onBackPressed();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_badges);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.toolbar);
        this.p = constraintLayout;
        ((TextView) constraintLayout.findViewById(R.id.toolbar_title)).setText(R.string.earn_more_badges);
        this.p.setOnClickListener(new a());
    }
}
