package com.coveiot.android.permissionsandsettings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.androidappkillmanager.AndroidAutoKillManager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.permissionsandsettings.databinding.ActivityConfigScreenBinding;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.SessionManager;
import java.util.HashMap;
/* loaded from: classes5.dex */
public class ConfigureSettingsActivity extends BaseActivity {
    public ActivityConfigScreenBinding p;
    public Intent q;
    public Intent r;
    public Intent s;
    public String t = "no";
    public String u = "no";
    public String v = "no";

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(View view) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.CONFIGURE_APP_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_SYSTEM_SETTINGS.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.DISABLE_BATTERY_OPTIMIZATION_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        try {
            Intent androidBatteryOptimizationIntent = AndroidAutoKillManager.getInstance(this).getAndroidBatteryOptimizationIntent();
            this.q = androidBatteryOptimizationIntent;
            if (androidBatteryOptimizationIntent != null) {
                startActivity(androidBatteryOptimizationIntent);
                this.t = "Yes";
            } else {
                this.t = "Yes";
                Toast.makeText(this, getString(R.string.batter_optimization_diabled), 1).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(View view) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.CONFIGURE_APP_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_SYSTEM_SETTINGS.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.DISABLE_BATTERY_OPTIMIZATION_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        try {
            Intent androidBatteryOptimizationIntent = AndroidAutoKillManager.getInstance(this).getAndroidBatteryOptimizationIntent();
            this.q = androidBatteryOptimizationIntent;
            if (androidBatteryOptimizationIntent != null) {
                startActivity(androidBatteryOptimizationIntent);
                this.t = "Yes";
            } else {
                this.t = "Yes";
                Toast.makeText(this, getString(R.string.batter_optimization_diabled), 1).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(View view) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.CONFIGURE_APP_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_SYSTEM_SETTINGS.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.ENABLE_AUTO_START_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        try {
            Intent autoStartIntent = AndroidAutoKillManager.getInstance(this).getAutoStartIntent();
            this.r = autoStartIntent;
            if (autoStartIntent != null) {
                startActivity(autoStartIntent);
                this.u = "Yes";
            } else {
                this.u = "Yes";
                Toast.makeText(this, getString(R.string.auto_start_enabled), 1).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(View view) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.CONFIGURE_APP_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_SYSTEM_SETTINGS.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.DISABLE_POWER_OPTIMIZATION_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        try {
            Intent manufacturerBatteryOptimizationIntent = AndroidAutoKillManager.getInstance(this).getManufacturerBatteryOptimizationIntent();
            this.s = manufacturerBatteryOptimizationIntent;
            if (manufacturerBatteryOptimizationIntent != null) {
                startActivity(manufacturerBatteryOptimizationIntent);
                this.v = "Yes";
            } else {
                this.v = "Yes";
                Toast.makeText(this, getString(R.string.power_managment_settings_enabled), 1).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(View view) {
        if (!SessionManager.getInstance(this).getIsBatteryOptimisationDone().booleanValue()) {
            SessionManager.getInstance(this).saveIsBatteryOptimisationDone(Boolean.TRUE);
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.CONFIGURE_APP_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_PERMISSIONS_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NEXT_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(CleverTapConstants.CustomEventProperties.ONBOARD_PERMISSION_BATTERY.getValue(), this.t);
        hashMap.put(CleverTapConstants.CustomEventProperties.ONBOARD_PERMISSION_AUTO_START.getValue(), this.u);
        hashMap.put(CleverTapConstants.CustomEventProperties.ONBOARD_PERMISSION_POWER.getValue(), this.v);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getDeviceId(this));
        hashMap.putAll(companion.getWatchDetails(this));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_ACESS_ALL_GRANTED.getValue(), hashMap);
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityConfigScreenBinding inflate = ActivityConfigScreenBinding.inflate(getLayoutInflater());
        this.p = inflate;
        setContentView(inflate.getRoot());
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.CONFIGURE_APP_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
        AndroidAutoKillManager.getInstance(this).init();
        this.q = AndroidAutoKillManager.getInstance(this).getAndroidBatteryOptimizationIntent();
        this.r = AndroidAutoKillManager.getInstance(this).getAutoStartIntent();
        this.s = AndroidAutoKillManager.getInstance(this).getManufacturerBatteryOptimizationIntent();
        if (this.q == null) {
            this.t = "Yes";
            this.p.tvDisableBatteryOptimisation.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.permissionsandsettings.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ConfigureSettingsActivity.this.v(view);
                }
            });
        } else {
            this.p.tvDisableBatteryOptimisation.setVisibility(0);
            this.p.tvDisableBatteryOptimisation.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.permissionsandsettings.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ConfigureSettingsActivity.this.w(view);
                }
            });
        }
        if (this.r == null) {
            this.p.clAutoStart.setVisibility(8);
            this.u = "NA";
        } else {
            this.p.tvEnableAutoStart.setVisibility(0);
            this.p.tvEnableAutoStart.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.permissionsandsettings.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ConfigureSettingsActivity.this.x(view);
                }
            });
        }
        if (this.s == null) {
            this.p.clPowerOptimisation.setVisibility(8);
            this.v = "NA";
        } else {
            this.p.tvDisablePowerOptimisation.setVisibility(0);
            this.p.tvDisablePowerOptimisation.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.permissionsandsettings.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ConfigureSettingsActivity.this.y(view);
                }
            });
        }
        this.p.btnContinueToHomepage.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.permissionsandsettings.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConfigureSettingsActivity.this.z(view);
            }
        });
    }
}
