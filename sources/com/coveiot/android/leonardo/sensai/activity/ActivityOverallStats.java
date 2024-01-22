package com.coveiot.android.leonardo.sensai.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityOverallStatsBinding;
import com.coveiot.android.leonardo.sensai.adapter.OverallStatsListAdapter;
import com.coveiot.android.leonardo.sensai.adapter.PagerAdapterOverallStats;
import com.coveiot.android.leonardo.sensai.fragment.FragmentBattingOverallStats;
import com.coveiot.android.leonardo.sensai.fragment.FragmentBowlingOverallStats;
import com.coveiot.android.leonardo.sensai.viewmodel.OverallStatsViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityOverallStats extends BaseActivity implements OverallStatsListAdapter.OnItemClickListener {
    public ActivityOverallStatsBinding p;
    public OverallStatsListAdapter t;
    @NotNull
    public PagerAdapterOverallStats u;
    public Fragment v;
    public Fragment w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<Fragment> q = new ArrayList<>();
    @NotNull
    public String[] r = new String[0];
    @NotNull
    public String[] s = new String[0];

    public ActivityOverallStats() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        this.u = new PagerAdapterOverallStats(supportFragmentManager);
    }

    public static final void r(ActivityOverallStats this$0, View view) {
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

    @NotNull
    public final PagerAdapterOverallStats getPagerAdapterOverallStats() {
        return this.u;
    }

    @NotNull
    public final String[] getRangeList() {
        return this.s;
    }

    public final void init() {
        String[] stringArray = getResources().getStringArray(R.array.overall_stats_range);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray…rray.overall_stats_range)");
        this.s = stringArray;
        ActivityOverallStatsBinding activityOverallStatsBinding = this.p;
        ActivityOverallStatsBinding activityOverallStatsBinding2 = null;
        if (activityOverallStatsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOverallStatsBinding = null;
        }
        activityOverallStatsBinding.rvDays.setLayoutManager(new LinearLayoutManager(this, 0, true));
        this.t = new OverallStatsListAdapter(this, ArraysKt___ArraysKt.toList(this.s), this);
        ActivityOverallStatsBinding activityOverallStatsBinding3 = this.p;
        if (activityOverallStatsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOverallStatsBinding3 = null;
        }
        RecyclerView recyclerView = activityOverallStatsBinding3.rvDays;
        OverallStatsListAdapter overallStatsListAdapter = this.t;
        if (overallStatsListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overallStatsListAdapter");
            overallStatsListAdapter = null;
        }
        recyclerView.setAdapter(overallStatsListAdapter);
        if (!(this.r.length == 0)) {
            this.r = new String[0];
        }
        String[] stringArray2 = getResources().getStringArray(R.array.overall_stats);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "resources.getStringArray(R.array.overall_stats)");
        this.r = stringArray2;
        this.u.setTitles(stringArray2);
        ActivityOverallStatsBinding activityOverallStatsBinding4 = this.p;
        if (activityOverallStatsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOverallStatsBinding4 = null;
        }
        activityOverallStatsBinding4.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        ActivityOverallStatsBinding activityOverallStatsBinding5 = this.p;
        if (activityOverallStatsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOverallStatsBinding5 = null;
        }
        activityOverallStatsBinding5.tabLayout.setTabTextColors(getResources().getColor(R.color.color_717171), getResources().getColor(R.color.colorPrimary));
        this.q.clear();
        int length = this.r.length;
        for (int i = 0; i < length; i++) {
            String str = this.r[i];
            String string = getResources().getString(R.string.batting);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.batting)");
            if (StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) string, false, 2, (Object) null)) {
                this.q.add(FragmentBattingOverallStats.Companion.newInstance(0));
            } else {
                String str2 = this.r[i];
                String string2 = getResources().getString(R.string.bowling);
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.bowling)");
                if (StringsKt__StringsKt.contains$default((CharSequence) str2, (CharSequence) string2, false, 2, (Object) null)) {
                    this.q.add(FragmentBowlingOverallStats.Companion.newInstance(0));
                }
            }
        }
        this.u.setFragments(this.q);
        ActivityOverallStatsBinding activityOverallStatsBinding6 = this.p;
        if (activityOverallStatsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOverallStatsBinding6 = null;
        }
        activityOverallStatsBinding6.viewPager.setAdapter(this.u);
        ActivityOverallStatsBinding activityOverallStatsBinding7 = this.p;
        if (activityOverallStatsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOverallStatsBinding7 = null;
        }
        activityOverallStatsBinding7.viewPager.setOffscreenPageLimit(this.r.length);
        ActivityOverallStatsBinding activityOverallStatsBinding8 = this.p;
        if (activityOverallStatsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOverallStatsBinding8 = null;
        }
        TabLayout tabLayout = activityOverallStatsBinding8.tabLayout;
        ActivityOverallStatsBinding activityOverallStatsBinding9 = this.p;
        if (activityOverallStatsBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOverallStatsBinding9 = null;
        }
        tabLayout.setupWithViewPager(activityOverallStatsBinding9.viewPager);
        ActivityOverallStatsBinding activityOverallStatsBinding10 = this.p;
        if (activityOverallStatsBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityOverallStatsBinding2 = activityOverallStatsBinding10;
        }
        activityOverallStatsBinding2.viewPager.setCurrentItem(0);
        this.v = this.u.getFragments().get(0);
        this.w = this.u.getFragments().get(1);
    }

    public final void initToolbar() {
        ActivityOverallStatsBinding activityOverallStatsBinding = this.p;
        ActivityOverallStatsBinding activityOverallStatsBinding2 = null;
        if (activityOverallStatsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOverallStatsBinding = null;
        }
        TextView textView = (TextView) activityOverallStatsBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivityOverallStatsBinding activityOverallStatsBinding3 = this.p;
        if (activityOverallStatsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityOverallStatsBinding2 = activityOverallStatsBinding3;
        }
        textView.setText(getString(R.string.overall_stats));
        ((TextView) activityOverallStatsBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityOverallStats.r(ActivityOverallStats.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityOverallStatsBinding inflate = ActivityOverallStatsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
        init();
        OverallStatsViewModel overallStatsViewModel = (OverallStatsViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(OverallStatsViewModel.class);
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.OverallStatsListAdapter.OnItemClickListener
    public void onItemClicked(int i) {
        Fragment fragment = this.v;
        Fragment fragment2 = null;
        if (fragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("battingFragment");
            fragment = null;
        }
        if (fragment instanceof FragmentBattingOverallStats) {
            Fragment fragment3 = this.v;
            if (fragment3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("battingFragment");
                fragment3 = null;
            }
            ((FragmentBattingOverallStats) fragment3).setPositionFromActivity(i);
        }
        Fragment fragment4 = this.w;
        if (fragment4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bowlingFragment");
            fragment4 = null;
        }
        if (fragment4 instanceof FragmentBowlingOverallStats) {
            Fragment fragment5 = this.w;
            if (fragment5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bowlingFragment");
            } else {
                fragment2 = fragment5;
            }
            ((FragmentBowlingOverallStats) fragment2).setPositionFromActivity(i);
        }
    }

    public final void setPagerAdapterOverallStats(@NotNull PagerAdapterOverallStats pagerAdapterOverallStats) {
        Intrinsics.checkNotNullParameter(pagerAdapterOverallStats, "<set-?>");
        this.u = pagerAdapterOverallStats;
    }

    public final void setRangeList(@NotNull String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.s = strArr;
    }
}
