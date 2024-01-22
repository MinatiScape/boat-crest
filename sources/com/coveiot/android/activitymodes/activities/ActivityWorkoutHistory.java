package com.coveiot.android.activitymodes.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.adapters.PagerAdapterWorkoutHistory;
import com.coveiot.android.activitymodes.fragments.FragmentWorkoutSessionListing;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityWorkoutHistory extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<Fragment> p = new ArrayList<>();
    @NotNull
    public String[] q = new String[0];

    public static final void r(ActivityWorkoutHistory this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void init() {
        if (!(this.q.length == 0)) {
            this.q = new String[0];
        }
        String[] stringArray = getResources().getStringArray(R.array.activity_modes);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.activity_modes)");
        this.q = stringArray;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        PagerAdapterWorkoutHistory pagerAdapterWorkoutHistory = new PagerAdapterWorkoutHistory(supportFragmentManager);
        pagerAdapterWorkoutHistory.setTitles(this.q);
        int i = R.id.tabLayout_history;
        Resources resources = getResources();
        int i2 = R.color.colorPrimary;
        ((TabLayout) _$_findCachedViewById(i)).setSelectedTabIndicatorColor(resources.getColor(i2));
        ((TabLayout) _$_findCachedViewById(i)).setTabTextColors(getResources().getColor(R.color.color_717171), getResources().getColor(i2));
        this.p.clear();
        int length = this.q.length;
        for (int i3 = 0; i3 < length; i3++) {
            String str = this.q[i3];
            String string = getResources().getString(R.string.walk);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.walk)");
            if (StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) string, false, 2, (Object) null)) {
                this.p.add(FragmentWorkoutSessionListing.Companion.newInstance(ActivityMode.WALK));
            } else {
                String str2 = this.q[i3];
                String string2 = getResources().getString(R.string.run);
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.run)");
                if (StringsKt__StringsKt.contains$default((CharSequence) str2, (CharSequence) string2, false, 2, (Object) null)) {
                    this.p.add(FragmentWorkoutSessionListing.Companion.newInstance(ActivityMode.RUN));
                }
            }
        }
        pagerAdapterWorkoutHistory.setFragments(this.p);
        int i4 = R.id.viewpager_home;
        ((ViewPager) _$_findCachedViewById(i4)).setAdapter(pagerAdapterWorkoutHistory);
        ((ViewPager) _$_findCachedViewById(i4)).setOffscreenPageLimit(this.q.length);
        ((TabLayout) _$_findCachedViewById(R.id.tabLayout_history)).setupWithViewPager((ViewPager) _$_findCachedViewById(i4));
        if (getIntent().hasExtra("ACTIVITY_TYPE_EXTRA")) {
            ((ViewPager) _$_findCachedViewById(i4)).setCurrentItem(getIntent().getIntExtra("ACTIVITY_TYPE_EXTRA", 0));
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_workout_history);
        init();
        ((ImageButton) _$_findCachedViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.g2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutHistory.r(ActivityWorkoutHistory.this, view);
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.ACTIVITY_WORKOUT_HISTORY;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }
}
