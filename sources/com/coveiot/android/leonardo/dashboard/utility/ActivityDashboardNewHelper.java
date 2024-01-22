package com.coveiot.android.leonardo.dashboard.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.blankj.utilcode.util.StringUtils;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.HealthNavigationElement;
import com.coveiot.android.leonardo.dashboard.model.VitalsModel;
import com.coveiot.android.leonardo.more.activities.ActivityBTCalling;
import com.coveiot.android.leonardo.more.activities.ActivityNotifications;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.sportsnotification.SportsDisclaimerActivity;
import com.coveiot.android.sportsnotification.SportsNotificationActivity;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.watchfaceui.utils.AppNavigator;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SWatchFaceList;
import com.coveiot.coveaccess.watchface.WatchFaceApiManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ActivityDashboardNewHelper {
    @NotNull
    public static final ActivityDashboardNewHelper INSTANCE = new ActivityDashboardNewHelper();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f4812a = SportsDisclaimerActivity.IS_FROM;

    public static /* synthetic */ void getDiyWatchFaceLists$default(ActivityDashboardNewHelper activityDashboardNewHelper, Activity activity, SuccessResultListener successResultListener, int i, Object obj) {
        if ((i & 2) != 0) {
            successResultListener = null;
        }
        activityDashboardNewHelper.getDiyWatchFaceLists(activity, successResultListener);
    }

    public final void a(Context context) {
        Intent intent = new Intent(context, SportsDisclaimerActivity.class);
        intent.putExtra(f4812a, SportsDisclaimerActivity.FRAGMENTHOMEDASHBOARD);
        context.startActivity(intent);
    }

    public final void getDiyWatchFaceLists(@NotNull final Activity context, @Nullable final SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        WatchFaceApiManager.getWatchFaceList(new CoveApiListener<SWatchFaceList, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.utility.ActivityDashboardNewHelper$getDiyWatchFaceLists$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                SuccessResultListener successResultListener2 = successResultListener;
                if (successResultListener2 != null) {
                    successResultListener2.onError(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SWatchFaceList sWatchFaceList) {
                if (sWatchFaceList != null && sWatchFaceList.getData() != null && sWatchFaceList.getData().getTotalItems() != null) {
                    SessionManager.getInstance(context).saveDiyWatchFaceListItems(sWatchFaceList.getData().getTotalItems());
                }
                SuccessResultListener successResultListener2 = successResultListener;
                if (successResultListener2 != null) {
                    successResultListener2.onSuccess();
                }
            }
        }, null, "DIY", ((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision(), 0, 20);
    }

    @NotNull
    public final String getIS_FROM() {
        return f4812a;
    }

    public final int getVitalPosition(@NotNull HealthNavigationElement healthNavigationElement, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(healthNavigationElement, "healthNavigationElement");
        Intrinsics.checkNotNullParameter(context, "context");
        return getVitalsList(context).indexOf(new VitalsModel("", 0, healthNavigationElement));
    }

    @NotNull
    public final ArrayList<VitalsModel> getVitalsList(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList<VitalsModel> arrayList = new ArrayList<>();
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isStepsSupported()) {
            String string = context.getString(R.string.steps);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.steps)");
            arrayList.add(new VitalsModel(string, 2131232553, HealthNavigationElement.STEPS));
        }
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isSleepSupported()) {
            String string2 = context.getString(R.string.sleep);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.sleep)");
            arrayList.add(new VitalsModel(string2, R.drawable.ic_sleep, HealthNavigationElement.SLEEP));
        }
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isHeartRateSupported()) {
            String string3 = context.getString(R.string.heart_rate);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.heart_rate)");
            arrayList.add(new VitalsModel(string3, R.drawable.ic_heart, HealthNavigationElement.HEART_RATE));
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isSmaDevice(context) || companion.isMatrixDevice(context) || ((BleApiManager.getInstance(context).getDeviceType().equals(DeviceType.kh17) && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) || BleApiManager.getInstance(context).getDeviceType().equals(DeviceType.crpGPF5) || companion.isIDODevice(context) || BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported() || companion.isEastApexDevice(context) || (companion.isTouchELXDevice(context) && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()))) {
            String string4 = context.getString(R.string.spo2);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.spo2)");
            arrayList.add(new VitalsModel(string4, R.drawable.ic_spo2, HealthNavigationElement.SPO2));
        }
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isManualStressMeasurementSupported() || BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
            String string5 = context.getString(R.string.stress);
            Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.stress)");
            arrayList.add(new VitalsModel(string5, R.drawable.ic_stress, HealthNavigationElement.STRESS));
        }
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isManualHRVMeasurementSupported() || BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isHRVHistorySupported()) {
            String string6 = context.getString(R.string.hrv_caps);
            Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.hrv_caps)");
            arrayList.add(new VitalsModel(string6, R.drawable.ic_hrv, HealthNavigationElement.HRV));
        }
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported()) {
            String string7 = context.getString(R.string.nightly_breathing_rate);
            Intrinsics.checkNotNullExpressionValue(string7, "context.getString(R.string.nightly_breathing_rate)");
            arrayList.add(new VitalsModel(string7, R.drawable.ic_nightly_breathing_rate, HealthNavigationElement.RESPIRATORY_RATE));
        }
        if (UserDataManager.getInstance(context).isEnableSleepEnergyScoreFeature(context)) {
            String string8 = context.getString(R.string.energy_meter);
            Intrinsics.checkNotNullExpressionValue(string8, "context.getString(R.string.energy_meter)");
            arrayList.add(new VitalsModel(string8, R.drawable.ic_energy_meter, HealthNavigationElement.ENERGY_METER));
        }
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
            String string9 = context.getString(R.string.temperature);
            Intrinsics.checkNotNullExpressionValue(string9, "context.getString(R.string.temperature)");
            arrayList.add(new VitalsModel(string9, R.drawable.ic_temperature, HealthNavigationElement.TEMPERATURE));
        }
        if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
            String string10 = context.getString(R.string.ambient_sound_level_txt);
            Intrinsics.checkNotNullExpressionValue(string10, "context.getString(R.stri….ambient_sound_level_txt)");
            arrayList.add(new VitalsModel(string10, R.drawable.ic_ambient_sound_level, HealthNavigationElement.AMBIENT_SOUND));
        }
        return arrayList;
    }

    public final void navigateToBluetoothCallingActivity(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.startActivity(new Intent(context, ActivityBTCalling.class));
    }

    public final void navigateToNotificationActivity(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.startActivity(new Intent(context, ActivityNotifications.class));
    }

    public final void navigateToProfileActivity(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AppNavigator.Companion.navigateToProfileScreen(context);
    }

    public final void navigateToSportsActivity(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (SyncManager.getInstance().isSyncInProgress()) {
            Toast.makeText(context, context.getResources().getString(R.string.syncing_please_wait), 1).show();
        } else if (!AppUtils.isNetConnected(context)) {
            Toast.makeText(context, (int) R.string.please_check_network, 0).show();
        } else {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.CRICKET_ICON_TAP.getValue());
            analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            if (SportsPreference.Companion.isDisclaimerAccepted(context)) {
                context.startActivity(new Intent(context, SportsNotificationActivity.class));
            } else {
                a(context);
            }
        }
    }

    public final void navigateToWatchfaceStudioActivity(@NotNull Activity context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (AppUtils.isNetConnected(context)) {
            Dashboard2PreferenceManager.Companion.getInstance(context).saveIsUserCheckedWatchfaceStudio(true);
            SessionManager.getInstance(context);
            String watchFaceDiyUrl = SessionManager.getInstance(context).getWatchFaceDiyUrl();
            if (watchFaceDiyUrl != null) {
                AppNavigator.Companion companion = com.coveiot.android.watchfaceui.utils.AppNavigator.Companion;
                String string = StringUtils.getString(R.string.create_watchface);
                Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an….string.create_watchface)");
                companion.navigateToWebViewer(context, string, watchFaceDiyUrl, true);
                return;
            }
            Toast.makeText(context, (int) R.string.coming_soon, 0).show();
            return;
        }
        Toast.makeText(context, context.getString(R.string.no_internet_connection), 0).show();
    }
}
