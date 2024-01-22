package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.model.WatchFactoryReset;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.datasync.domainlogic.SyncSessionManager;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class FactoryResetReceiver extends BroadcastReceiver {
    public final void a(Context context) {
        String connectedDeviceBT3MacAddress;
        if (BleApiManager.getInstance(context).getBleApi() == null || (connectedDeviceBT3MacAddress = PreferenceManagerAbstract.getInstance(context).getConnectedDeviceBT3MacAddress()) == null) {
            return;
        }
        BT3Utils.unpairDevice(connectedDeviceBT3MacAddress);
    }

    public final void disconnect(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SessionManager.getInstance(context).saveSoftwareUpdateResponseString("");
        UserDataManager.getInstance(context).saveLastUpdateWeatherTimeStamp(0L);
        SessionManager sessionManager = SessionManager.getInstance(context);
        sessionManager.setIsDevicePaired(false);
        sessionManager.setOnBoardingComplete(false);
        UserDataManager.getInstance(context).remove(context, UserDataManager.IS_ONK_SUPPORTED);
        UserDataManager.getInstance(context).remove(context, UserDataManager.APP_NOTIFICATION_OBJECT);
        PreferenceManager.clearAppNotificationData(context);
        Dashboard2PreferenceManager.Companion.getInstance(context).clearPreferences();
        sessionManager.setExistingUserFirstTime(true);
        SyncSessionManager.getInstance(context).setExistingUserFirstTime(true);
        PreferenceManagerAbstract.getInstance(context).saveDeviceVersionNumber(null);
        SessionManager.getInstance(context).setAnalyticsUserPropertiesUpdated(false);
        CoveEventBusManager.getInstance().getEventBus().post(new WatchFactoryReset(true));
    }

    @NotNull
    public final String getTodayDate() {
        String format = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(cal.time)");
        return format;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (intent.hasExtra(Constants.FACTORY_RESET_BROADCAST_INTENT_EXTRA)) {
            LogHelper.d("FactoryResetReceiver", "Reset today data");
            UserDataManager.getInstance(context).saveLatestSpo2Value(new LatestHealthDataModel());
            UserDataManager.getInstance(context).saveLatestHRValue(new LatestHealthDataModel());
            UserDataManager.getInstance(context).saveLatestTemperatureValue(new LatestHealthDataModel());
            if (BleApiManager.getInstance(context) != null) {
                WalkDBRead.getInstance(context).deleteDailyWalkDataFor(getTodayDate(), BleApiManager.getInstance(context).getBleApi().getMacAddress());
                WalkDBRead.getInstance(context).deleteHourlyWalkDataFor(getTodayDate(), BleApiManager.getInstance(context).getBleApi().getMacAddress());
                UserDataManager.getInstance(context).saveLiveStepsData(new StepsDataModel(), Calendar.getInstance(), BleApiManager.getInstance(context).getBleApi().getMacAddress());
            }
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isSmaJieieDevice(context)) {
                Toast.makeText(context, context.getResources().getString(R.string.please_connect_again_factory_reset_found), 1).show();
                SyncManager.getInstance().clearBleSyncUtilsInstance();
                disconnect(context);
            } else if (companion.isTouchELXDevice(context)) {
                a(context);
            }
        }
    }
}
