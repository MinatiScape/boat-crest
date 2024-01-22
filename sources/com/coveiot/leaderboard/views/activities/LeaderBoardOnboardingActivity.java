package com.coveiot.leaderboard.views.activities;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.views.fragment.AddressFTUFragment;
import com.coveiot.leaderboard.views.fragment.LeaderBoardFTUFragment;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class LeaderBoardOnboardingActivity extends BaseActivity {
    public ViewPager p;
    public CirclePageIndicator q;

    /* loaded from: classes9.dex */
    public class a extends FragmentPagerAdapter {
        public final List<Fragment> h;
        public final List<String> i;

        public a(LeaderBoardOnboardingActivity leaderBoardOnboardingActivity, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.h = new ArrayList();
            this.i = new ArrayList();
        }

        public void addFragment(Fragment fragment, String str) {
            this.h.add(fragment);
            this.i.add(str);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.h.size();
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            return this.h.get(i);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return this.i.get(i);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.p.getCurrentItem() == 1) {
            this.p.setCurrentItem(0);
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_leader_board_onboarding);
        this.p = (ViewPager) findViewById(R.id.viewpager);
        this.q = (CirclePageIndicator) findViewById(R.id.circlePageIndicator);
        q(this.p);
        this.q.setViewPager(this.p);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.FTU_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void q(ViewPager viewPager) {
        a aVar = new a(this, getSupportFragmentManager());
        aVar.addFragment(new LeaderBoardFTUFragment(), "");
        aVar.addFragment(new AddressFTUFragment(), "");
        viewPager.setAdapter(aVar);
    }
}
