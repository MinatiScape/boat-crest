package com.coveiot.android.leonardo.more.activities;

import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityTroubleshootnFAQ extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void A(ActivityTroubleshootnFAQ this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, (int) R.string.noconnection, 0).show();
        } else if (SessionManager.getInstance(this$0).getFAQUrl() != null) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            String string = this$0.getString(R.string.faqs);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.faqs)");
            String fAQUrl = SessionManager.getInstance(this$0).getFAQUrl();
            Intrinsics.checkNotNullExpressionValue(fAQUrl, "getInstance(this).faqUrl");
            companion.navigateToWebViewer(this$0, string, fAQUrl);
        } else {
            AppNavigator.Companion companion2 = AppNavigator.Companion;
            String string2 = this$0.getString(R.string.faqs);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.faqs)");
            companion2.navigateToWebViewer(this$0, string2, AppConstants.FAQ_URL.getValue());
        }
    }

    public static final void B(ActivityTroubleshootnFAQ this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppNavigator.Companion.navigateToWatchDiagnostics(this$0, true);
    }

    public static final void C(ActivityTroubleshootnFAQ this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppNavigator.Companion.navigateToWatchDiagnostics(this$0, false);
    }

    public static final void D(ActivityTroubleshootnFAQ this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void x(ActivityTroubleshootnFAQ this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_CONN_TROUBLESHOOT.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.TROUBLESHOOT_AND_FAQS.getValue());
        analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        AppNavigator.Companion.navigateToTroubleshootConnectivity(this$0);
    }

    public static final void y(ActivityTroubleshootnFAQ this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object systemService = this$0.getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        if (((BluetoothManager) systemService).getAdapter() != null) {
            Object systemService2 = this$0.getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (((BluetoothManager) systemService2).getAdapter().isEnabled()) {
                this$0.startActivity(new Intent(this$0, ActivityFirmwareRestore.class));
                return;
            }
        }
        Toast.makeText(this$0, (int) R.string.bluetooth_off_message, 0).show();
    }

    public static final void z(ActivityTroubleshootnFAQ this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppNavigator.Companion.navigateToTroubleshootNotification(this$0, true);
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

    public final void initClickListeners() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_troubleshoot_connectivity)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.jj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityTroubleshootnFAQ.x(ActivityTroubleshootnFAQ.this, view);
            }
        });
        int i = R.id.cl_restore_layout;
        ((ConstraintLayout) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.kj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityTroubleshootnFAQ.y(ActivityTroubleshootnFAQ.this, view);
            }
        });
        if (DeviceUtils.Companion.isCZDevice(this)) {
            ((ConstraintLayout) _$_findCachedViewById(i)).setVisibility(0);
        } else {
            ((ConstraintLayout) _$_findCachedViewById(i)).setVisibility(8);
        }
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_troubleshoot_notification)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.mj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityTroubleshootnFAQ.z(ActivityTroubleshootnFAQ.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_faqs)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.hj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityTroubleshootnFAQ.A(ActivityTroubleshootnFAQ.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.clRunWatchDiagnostics)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.lj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityTroubleshootnFAQ.B(ActivityTroubleshootnFAQ.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.clWatchDiagnosticsTestHistory)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ij
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityTroubleshootnFAQ.C(ActivityTroubleshootnFAQ.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.troubleshootnFAQ));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.gj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityTroubleshootnFAQ.D(ActivityTroubleshootnFAQ.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_troubleshoot_faq);
        initToolbar();
        initClickListeners();
        ImageView iv_powered_by_logo = (ImageView) _$_findCachedViewById(R.id.iv_powered_by_logo);
        Intrinsics.checkNotNullExpressionValue(iv_powered_by_logo, "iv_powered_by_logo");
        ThemesUtils.setPoweredByLogoIcon(this, iv_powered_by_logo, false);
        DeviceModelBean deviceModelBean = SessionManager.getInstance(this).getDeviceModelBean();
        Boolean isWatchDiagnosticsSupported = deviceModelBean != null ? deviceModelBean.getIsWatchDiagnosticsSupported() : null;
        if (isWatchDiagnosticsSupported != null && isWatchDiagnosticsSupported.booleanValue()) {
            ConstraintLayout clRunWatchDiagnostics = (ConstraintLayout) _$_findCachedViewById(R.id.clRunWatchDiagnostics);
            Intrinsics.checkNotNullExpressionValue(clRunWatchDiagnostics, "clRunWatchDiagnostics");
            visible(clRunWatchDiagnostics);
            return;
        }
        ConstraintLayout clRunWatchDiagnostics2 = (ConstraintLayout) _$_findCachedViewById(R.id.clRunWatchDiagnostics);
        Intrinsics.checkNotNullExpressionValue(clRunWatchDiagnostics2, "clRunWatchDiagnostics");
        gone(clRunWatchDiagnostics2);
    }
}
