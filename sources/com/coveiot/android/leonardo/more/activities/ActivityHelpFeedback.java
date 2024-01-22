package com.coveiot.android.leonardo.more.activities;

import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.more.adapters.SettingsTitleAdapterWithListner;
import com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityReportIssue;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.ItemClickListenerNew;
import com.coveiot.android.theme.model.SettingsListItemModelWithListener;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityHelpFeedback extends BaseActivity implements ItemClickListenerNew {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public BottomSheetDialogTwoButtons p;

    public static final void r(ActivityHelpFeedback this$0, View view) {
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

    @Nullable
    public final BottomSheetDialogTwoButtons getEnableBluetoothDialog() {
        return this.p;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.help_support));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.je
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityHelpFeedback.r(ActivityHelpFeedback.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_help_feedback);
        initToolbar();
        ImageView iv_powered_by_logo = (ImageView) _$_findCachedViewById(R.id.iv_powered_by_logo);
        Intrinsics.checkNotNullExpressionValue(iv_powered_by_logo, "iv_powered_by_logo");
        ThemesUtils.setPoweredByLogoIcon(this, iv_powered_by_logo, false);
        ArrayList arrayList = new ArrayList();
        String string = getString(R.string.troubleshooting);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.troubleshooting)");
        arrayList.add(new SettingsListItemModelWithListener(null, string, this, null, null));
        if (DeviceUtils.Companion.isCZDevice(this)) {
            String string2 = getString(R.string.watch_recovery);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.watch_recovery)");
            arrayList.add(new SettingsListItemModelWithListener(null, string2, this, null, null));
        }
        String string3 = getString(R.string.contact_us_feedback);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.contact_us_feedback)");
        arrayList.add(new SettingsListItemModelWithListener(null, string3, this, null, null));
        String string4 = getString(R.string.faqs);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.faqs)");
        arrayList.add(new SettingsListItemModelWithListener(null, string4, this, null, null));
        DeviceModelBean deviceModelBean = SessionManager.getInstance(this).getDeviceModelBean();
        Boolean isWatchDiagnosticsSupported = deviceModelBean != null ? deviceModelBean.getIsWatchDiagnosticsSupported() : null;
        if (isWatchDiagnosticsSupported != null && isWatchDiagnosticsSupported.booleanValue()) {
            String string5 = getString(R.string.run_watch_diagnostics);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.run_watch_diagnostics)");
            arrayList.add(new SettingsListItemModelWithListener(null, string5, this, null, null));
        }
        int i = R.id.rcv_help_feedback;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(this));
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(new SettingsTitleAdapterWithListner(arrayList));
    }

    @Override // com.coveiot.android.theme.ItemClickListenerNew
    public void onItemSelected(@NotNull String itemName) {
        Intrinsics.checkNotNullParameter(itemName, "itemName");
        if (Intrinsics.areEqual(itemName, getString(R.string.troubleshooting))) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.CV_CONN_TROUBLESHOOT.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.HELP_FEEDBACK_SCREEN.getValue());
            analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.MORE_SCREEN.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            AppNavigator.Companion.navigateToTroubleshootConnectivity(this);
        } else if (Intrinsics.areEqual(itemName, getString(R.string.troubleshoot_notification1))) {
            AppNavigator.Companion.navigateToTroubleshootNotification$default(AppNavigator.Companion, this, false, 2, null);
        } else if (Intrinsics.areEqual(itemName, getString(R.string.feedback))) {
            if (AppUtils.isNetConnected(this)) {
                Intent intent = new Intent(this, ActivityReportIssue.class);
                intent.putExtra("type", com.ido.ble.event.stat.one.d.O);
                intent.putExtra(AppConstants.PHONE_NUMBER.getValue(), SessionManager.getInstance(this).getUserDetails().getMobileNumber());
                startActivity(intent);
            } else {
                showNoInternetMessage();
            }
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.HELP_FEEDBACK_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_FEEDBACK_SCREEN.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.FEEDBACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
        } else if (Intrinsics.areEqual(itemName, getString(R.string.contact_us_feedback))) {
            if (AppUtils.isNetConnected(this)) {
                startActivity(new Intent(this, ActivityContactusFeedback.class));
                AnalyticsLog analyticsLog3 = new AnalyticsLog();
                analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.HELP_FEEDBACK_SCREEN.getValue());
                analyticsLog3.setDescription(FirebaseEventParams.Description.OPEN_FEEDBACK_SCREEN.getValue());
                analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.FEEDBACK_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
                return;
            }
            showNoInternetMessage();
        } else if (Intrinsics.areEqual(itemName, getString(R.string.export_logs))) {
            PayUtils.INSTANCE.saveLogFile(this);
        } else if (Intrinsics.areEqual(itemName, getString(R.string.watch_recovery))) {
            Object systemService = getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (((BluetoothManager) systemService).getAdapter() != null) {
                Object systemService2 = getSystemService("bluetooth");
                Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
                if (((BluetoothManager) systemService2).getAdapter().isEnabled()) {
                    startActivity(new Intent(this, ActivityFirmwareRestore.class));
                    return;
                }
            }
            Toast.makeText(this, (int) R.string.bluetooth_off_message, 0).show();
        } else if (Intrinsics.areEqual(itemName, getString(R.string.contact_us))) {
            if (!AppUtils.isNetConnected(this)) {
                Toast.makeText(this, (int) R.string.noconnection, 0).show();
                return;
            }
            AppNavigator.Companion companion = AppNavigator.Companion;
            String string = getString(R.string.contact_us);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.contact_us)");
            companion.navigateToWebViewer(this, string, AppConstants.SUPPORT_URL.getValue());
        } else if (Intrinsics.areEqual(itemName, getString(R.string.faqs))) {
            if (!AppUtils.isNetConnected(this)) {
                Toast.makeText(this, (int) R.string.noconnection, 0).show();
                return;
            }
            if (SessionManager.getInstance(this).getFAQUrl() != null) {
                AppNavigator.Companion companion2 = AppNavigator.Companion;
                String string2 = getString(R.string.faqs);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.faqs)");
                String fAQUrl = SessionManager.getInstance(this).getFAQUrl();
                Intrinsics.checkNotNullExpressionValue(fAQUrl, "getInstance(this).faqUrl");
                companion2.navigateToWebViewer(this, string2, fAQUrl);
            } else {
                AppNavigator.Companion companion3 = AppNavigator.Companion;
                String string3 = getString(R.string.faqs);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.faqs)");
                companion3.navigateToWebViewer(this, string3, AppConstants.FAQ_URL.getValue());
            }
            AnalyticsLog analyticsLog4 = new AnalyticsLog();
            analyticsLog4.setEventName(FirebaseEventParams.EventName.CV_CONN_TROUBLESHOOT.getValue());
            analyticsLog4.setScreenName(FirebaseEventParams.ScreenName.TROUBLESHOOT_AND_FAQS.getValue());
            analyticsLog4.setPreviousScreenName(FirebaseEventParams.ScreenName.MORE_SCREEN.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog4);
        } else if (Intrinsics.areEqual(itemName, getString(R.string.run_watch_diagnostics))) {
            AppNavigator.Companion.navigateToWatchDiagnostics(this, true);
        }
    }

    public final void setEnableBluetoothDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.p = bottomSheetDialogTwoButtons;
    }
}
