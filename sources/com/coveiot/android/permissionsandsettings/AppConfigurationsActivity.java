package com.coveiot.android.permissionsandsettings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.androidappkillmanager.AndroidAutoKillManager;
import com.coveiot.android.permissionsandsettings.databinding.ActivityAppConfigScreenBinding;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
/* loaded from: classes5.dex */
public class AppConfigurationsActivity extends BaseActivity {
    public ActivityAppConfigScreenBinding p;
    public Intent q;
    public Intent r;
    public Intent s;

    /* loaded from: classes5.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppConfigurationsActivity.super.onBackPressed();
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
            } else {
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
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.DISABLE_BATTERY_OPTIMIZATION_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        try {
            Intent androidBatteryOptimizationIntent = AndroidAutoKillManager.getInstance(this).getAndroidBatteryOptimizationIntent();
            this.q = androidBatteryOptimizationIntent;
            if (androidBatteryOptimizationIntent != null) {
                startActivity(androidBatteryOptimizationIntent);
            } else {
                Toast.makeText(this, getString(R.string.batter_optimization_diabled), 1).show();
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
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.ENABLE_AUTO_START_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        try {
            Intent autoStartIntent = AndroidAutoKillManager.getInstance(this).getAutoStartIntent();
            this.r = autoStartIntent;
            if (autoStartIntent != null) {
                startActivity(autoStartIntent);
            } else {
                Toast.makeText(this, getString(R.string.auto_start_enabled), 1).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(View view) {
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
            } else {
                Toast.makeText(this, getString(R.string.power_managment_settings_enabled), 1).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityAppConfigScreenBinding inflate = ActivityAppConfigScreenBinding.inflate(getLayoutInflater());
        this.p = inflate;
        setContentView(inflate.getRoot());
        v();
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
            this.p.clPowerOptimisation.setVisibility(8);
            this.p.clBattery.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.permissionsandsettings.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AppConfigurationsActivity.this.w(view);
                }
            });
        } else {
            this.p.clBattery.setVisibility(0);
            this.p.clBattery.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.permissionsandsettings.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AppConfigurationsActivity.this.x(view);
                }
            });
        }
        if (this.r == null) {
            this.p.listItemDivider.setVisibility(8);
        } else {
            this.p.listItemDivider.setVisibility(0);
        }
        if (this.s == null) {
            this.p.listItemDivider2.setVisibility(8);
        } else {
            this.p.listItemDivider2.setVisibility(0);
        }
        if (this.r == null) {
            this.p.clAutoStart.setVisibility(8);
        } else {
            this.p.clAutoStart.setVisibility(0);
            this.p.clAutoStart.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.permissionsandsettings.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AppConfigurationsActivity.this.y(view);
                }
            });
        }
        if (this.s == null) {
            this.p.clPowerOptimisation.setVisibility(8);
            return;
        }
        this.p.clPowerOptimisation.setVisibility(0);
        this.p.clPowerOptimisation.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.permissionsandsettings.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AppConfigurationsActivity.this.z(view);
            }
        });
    }

    public final void v() {
        ((TextView) this.p.toolbar.findViewById(R.id.toolbar_title)).setText(R.string.app_configuration);
        this.p.toolbar.findViewById(R.id.toolbar_back_arrow).setOnClickListener(new a());
    }
}
