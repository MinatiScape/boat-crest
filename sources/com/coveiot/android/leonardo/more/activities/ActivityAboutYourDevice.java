package com.coveiot.android.leonardo.more.activities;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.FirmwareUpdateActivityFactory;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAboutYourDevice extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void B(ActivityAboutYourDevice this$0, DeviceInfoData deviceInfoData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.E();
        Intrinsics.checkNotNull(deviceInfoData);
        ((TextView) this$0._$_findCachedViewById(R.id.tv_firmware_version)).setText(deviceInfoData.getFwVersion());
        BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this$0).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
        bleDeviceInfo.setMacAddress(BleApiManager.getInstance(this$0).getBleApi().getMacAddress());
        bleDeviceInfo.setFirmwareRevision(deviceInfoData.getFwVersion());
        bleDeviceInfo.setSerialNumber(deviceInfoData.getSerialNo());
        bleDeviceInfo.setHwRevision(deviceInfoData.getHwVersion());
        SessionManager.getInstance(this$0).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
    }

    public static final void C(ActivityAboutYourDevice this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.E();
        BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this$0).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
        if (bleDeviceInfo != null) {
            ((TextView) this$0._$_findCachedViewById(R.id.tv_firmware_version)).setText(bleDeviceInfo.getFirmwareRevision());
        }
    }

    public static final void w(ActivityAboutYourDevice this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            String string = this$0.getString(R.string.user_manual);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.user_manual)");
            companion.navigateToWebViewer(this$0, string, AppConstants.USER_MANUAL_URL.getValue());
        } else {
            this$0.showNoInternetMessage();
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.ABOUT_YOUR_DEVICE_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_USER_MANUAL_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.USER_MANUAL_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public static final void x(final ActivityAboutYourDevice this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            String string = this$0.getString(R.string.please_wait);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
            this$0.showProgresswithMsg(string);
            BleApiManager.getInstance(this$0).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAboutYourDevice$initClickListeners$2$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    ActivityAboutYourDevice.this.dismissProgress();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof BatteryLevelResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                        Integer batteryLevel = ((BatteryLevelResponse) responseData).getBatteryLevel();
                        Intrinsics.checkNotNull(batteryLevel);
                        int intValue = batteryLevel.intValue();
                        if (intValue >= Integer.parseInt(AppConstants.BATTERY_LEVEL_FOR_FW_UPDATE.getValue())) {
                            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = (SoftwareUpdateRes.DataBean.FirmwareBean) new Gson().fromJson(SessionManager.getInstance(ActivityAboutYourDevice.this).getOptionalFirmwareBeanString(), (Class<Object>) SoftwareUpdateRes.DataBean.FirmwareBean.class);
                            if (firmwareBean != null) {
                                ActivityAboutYourDevice.this.dismissProgress();
                                FirmwareUpdateActivityFactory.Companion.navigateToFirmwareUpdateActivity(ActivityAboutYourDevice.this, firmwareBean);
                            }
                        } else if (intValue <= 0 && PayUtils.INSTANCE.isCurrentFirmwareHasIssueWithBatteryPercentage(ActivityAboutYourDevice.this)) {
                            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = (SoftwareUpdateRes.DataBean.FirmwareBean) new Gson().fromJson(SessionManager.getInstance(ActivityAboutYourDevice.this).getOptionalFirmwareBeanString(), (Class<Object>) SoftwareUpdateRes.DataBean.FirmwareBean.class);
                            if (firmwareBean2 != null) {
                                ActivityAboutYourDevice.this.dismissProgress();
                                FirmwareUpdateActivityFactory.Companion.navigateToFirmwareUpdateActivity(ActivityAboutYourDevice.this, firmwareBean2);
                            }
                        } else {
                            ActivityAboutYourDevice.this.dismissProgress();
                            ActivityAboutYourDevice activityAboutYourDevice = ActivityAboutYourDevice.this;
                            Toast.makeText(activityAboutYourDevice, activityAboutYourDevice.getString(R.string.make_sure_battery_device), 1).show();
                        }
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.band_not_connected), 0).show();
    }

    public static final void y(ActivityAboutYourDevice this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public final void A(final DeviceInfoData deviceInfoData) {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.f
            @Override // java.lang.Runnable
            public final void run() {
                ActivityAboutYourDevice.B(ActivityAboutYourDevice.this, deviceInfoData);
            }
        });
    }

    public final void D() {
        String optionalFirmwareBeanString = SessionManager.getInstance(this).getOptionalFirmwareBeanString();
        if (!(optionalFirmwareBeanString == null || optionalFirmwareBeanString.length() == 0)) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.btn_update)).setVisibility(0);
        } else {
            ((ConstraintLayout) _$_findCachedViewById(R.id.btn_update)).setVisibility(8);
        }
    }

    public final void E() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_model_number);
        DeviceModelBean deviceModelBean = SessionManager.getInstance(this).getDeviceModelBean();
        String str = null;
        if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
            DeviceModelBean deviceModelBean2 = SessionManager.getInstance(this).getDeviceModelBean();
            if (deviceModelBean2 != null) {
                str = deviceModelBean2.getName();
            }
        } else {
            str = DeviceUtils.Companion.getModelNumber(this);
        }
        textView.setText(str);
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
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_user_manual)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAboutYourDevice.w(ActivityAboutYourDevice.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAboutYourDevice.x(ActivityAboutYourDevice.this, view);
            }
        });
    }

    public final void initMislinous() {
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.about_watch));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAboutYourDevice.y(ActivityAboutYourDevice.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_about_your_device);
        initToolbar();
        initClickListeners();
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            int i = R.id.cl_firmware_version;
            ((ConstraintLayout) _$_findCachedViewById(i)).setAlpha(1.0f);
            ((ConstraintLayout) _$_findCachedViewById(i)).setEnabled(true);
            v();
        } else {
            int i2 = R.id.cl_firmware_version;
            ((ConstraintLayout) _$_findCachedViewById(i2)).setAlpha(0.3f);
            ((ConstraintLayout) _$_findCachedViewById(i2)).setEnabled(false);
            z();
        }
        PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        ((TextView) _$_findCachedViewById(R.id.tv_app_version)).setText(packageInfo.versionName + HexStringBuilder.COMMENT_BEGIN_CHAR + packageInfo.versionCode + HexStringBuilder.COMMENT_END_CHAR);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (SessionManager.getInstance(this).isForceFirmwareUpdated()) {
            LogHelper.e("ActivityAboutYourDevice onResume", "bleDeviceInfo " + SessionManager.getInstance(this).getBleDeviceInfo());
            BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
            if (bleDeviceInfo != null) {
                ((TextView) _$_findCachedViewById(R.id.tv_firmware_version)).setText(bleDeviceInfo.getFirmwareRevision());
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        D();
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void v() {
        /*
            Method dump skipped, instructions count: 405
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.activities.ActivityAboutYourDevice.v():void");
    }

    public final void z() {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.e
            @Override // java.lang.Runnable
            public final void run() {
                ActivityAboutYourDevice.C(ActivityAboutYourDevice.this);
            }
        });
    }
}
