package com.coveiot.android.weeklyreport.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.WeeklyReportData;
import com.coveiot.android.weeklyreport.fragments.FragmentEmailEdit;
import com.coveiot.android.weeklyreport.fragments.FragmentSubscriptionSuccess;
import com.coveiot.android.weeklyreport.fragments.FragmentUnSubscription;
import com.coveiot.android.weeklyreport.fragments.FragmentUpdateEmail;
import com.coveiot.android.weeklyreport.fragments.FragmentVerifyEmail;
import com.coveiot.android.weeklyreport.utils.ViewModelFactory;
import com.coveiot.android.weeklyreport.utils.WeeklyReportConstant;
import com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WeeklyReportPrefData;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class ActivityWeeklyReport extends BaseActivity implements FragmentEmailEdit.NextScreenListener, FragmentSubscriptionSuccess.NextScreenListener, FragmentVerifyEmail.NextScreenListener, FragmentUnSubscription.NextScreenListener, FragmentUpdateEmail.NextScreenListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void s(ActivityWeeklyReport this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static /* synthetic */ void setTitle$default(ActivityWeeklyReport activityWeeklyReport, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        activityWeeklyReport.setTitle(str, z);
    }

    public static final void x(ActivityWeeklyReport this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.startActivity(new Intent(this$0, ActivityWeeklyReportHistory.class));
        } else {
            this$0.showNoInternetMessage();
        }
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

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWeeklyReport.s(ActivityWeeklyReport.this, view);
            }
        });
    }

    @Override // com.coveiot.android.weeklyreport.fragments.FragmentEmailEdit.NextScreenListener, com.coveiot.android.weeklyreport.fragments.FragmentSubscriptionSuccess.NextScreenListener, com.coveiot.android.weeklyreport.fragments.FragmentVerifyEmail.NextScreenListener, com.coveiot.android.weeklyreport.fragments.FragmentUnSubscription.NextScreenListener, com.coveiot.android.weeklyreport.fragments.FragmentUpdateEmail.NextScreenListener
    public void nextScreen(@NotNull WeeklyReportData weeklyReportData) {
        Intrinsics.checkNotNullParameter(weeklyReportData, "weeklyReportData");
        String screenType = weeklyReportData.getScreenType();
        if (Intrinsics.areEqual(screenType, WeeklyReportConstant.VERIFY.getValue())) {
            w(weeklyReportData.getEmailId());
        } else if (Intrinsics.areEqual(screenType, WeeklyReportConstant.UNSUBSCRIBE.getValue())) {
            v();
        } else if (Intrinsics.areEqual(screenType, WeeklyReportConstant.SUBSCRIBE_SUCCESS.getValue())) {
            u();
        } else if (Intrinsics.areEqual(screenType, WeeklyReportConstant.FINISH.getValue())) {
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_weekly_report_common);
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WeeklyReportViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            this,\n  …ortViewModel::class.java)");
        WeeklyReportViewModel weeklyReportViewModel = (WeeklyReportViewModel) viewModel;
        Bundle extras = getIntent().getExtras();
        if (m.equals$default(extras != null ? extras.getString(WeeklyReportConstant.UPDATE_EMAIL.getValue()) : null, WeeklyReportConstant.UPDATE_EMAIL.getValue(), false, 2, null)) {
            y();
        } else {
            Bundle extras2 = getIntent().getExtras();
            if (m.equals$default(extras2 != null ? extras2.getString(WeeklyReportConstant.HOME_DASHBOARD.getValue()) : null, WeeklyReportConstant.HOME_DASHBOARD.getValue(), false, 2, null)) {
                Bundle extras3 = getIntent().getExtras();
                String string = extras3 != null ? extras3.getString(WeeklyReportConstant.EMAIL_ID.getValue()) : null;
                if (string != null) {
                    w(string);
                }
            } else {
                Bundle extras4 = getIntent().getExtras();
                if (m.equals$default(extras4 != null ? extras4.getString(WeeklyReportConstant.UNSUBSCRIBE.getValue()) : null, WeeklyReportConstant.UNSUBSCRIBE.getValue(), false, 2, null)) {
                    v();
                } else {
                    Bundle extras5 = getIntent().getExtras();
                    if (m.equals$default(extras5 != null ? extras5.getString(WeeklyReportConstant.SUBSCRIBE_SUCCESS.getValue()) : null, WeeklyReportConstant.SUBSCRIBE_SUCCESS.getValue(), false, 2, null)) {
                        u();
                    } else if (!ThemesUtils.INSTANCE.isPairDeviceLater(this)) {
                        WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(this).getWeeklyReportData();
                        if (weeklyReportData != null && weeklyReportData.isSubscribed()) {
                            u();
                        } else {
                            t();
                        }
                    } else {
                        t();
                    }
                }
            }
        }
        initToolbar();
    }

    public final void setTitle(@NotNull String title, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        ((TextView) findViewById(R.id.toolbar_title)).setText(title);
        if (z) {
            int i = R.id.clMenuIcon;
            View findViewById = findViewById(i);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<ConstraintLayout>(R.id.clMenuIcon)");
            visible(findViewById);
            ((ConstraintLayout) findViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.activities.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityWeeklyReport.x(ActivityWeeklyReport.this, view);
                }
            });
            return;
        }
        View findViewById2 = findViewById(R.id.clMenuIcon);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<ConstraintLayout>(R.id.clMenuIcon)");
        gone(findViewById2);
    }

    public final void t() {
        String string = getResources().getString(R.string.fitness_report);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.fitness_report)");
        setTitle(string, true);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.container_layout;
        FragmentEmailEdit.Companion companion = FragmentEmailEdit.Companion;
        beginTransaction.replace(i, companion.newInstance()).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    public final void u() {
        String string = getResources().getString(R.string.fitness_report);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.fitness_report)");
        setTitle(string, true);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.container_layout;
        FragmentSubscriptionSuccess.Companion companion = FragmentSubscriptionSuccess.Companion;
        beginTransaction.replace(i, companion.newInstance()).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    public final void v() {
        String string = getResources().getString(R.string.fitness_report);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.fitness_report)");
        setTitle(string, false);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.container_layout;
        FragmentUnSubscription.Companion companion = FragmentUnSubscription.Companion;
        beginTransaction.replace(i, companion.newInstance()).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    public final void w(String str) {
        String string = getResources().getString(R.string.verify_email);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.verify_email)");
        setTitle$default(this, string, false, 2, null);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.container_layout;
        FragmentVerifyEmail.Companion companion = FragmentVerifyEmail.Companion;
        beginTransaction.replace(i, companion.newInstance(str)).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    public final void y() {
        String string = getResources().getString(R.string.update_email);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.update_email)");
        setTitle$default(this, string, false, 2, null);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.container_layout;
        FragmentUpdateEmail.Companion companion = FragmentUpdateEmail.Companion;
        beginTransaction.replace(i, companion.newInstance()).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }
}
