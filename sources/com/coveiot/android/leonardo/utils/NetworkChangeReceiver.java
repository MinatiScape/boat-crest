package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.SportsNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsUtils;
import com.coveiot.android.sportsnotification.model.AppDynamicSportsField;
import com.coveiot.android.sportsnotification.model.WatchCricketUIModel;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class NetworkChangeReceiver extends BroadcastReceiver {
    public final void a(Context context) {
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        WatchCricketUIModel watchCricketUIPreference = sportsUtils.getWatchCricketUIPreference(context);
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, sportsUtils.getSportsCricketImageID(context)));
        arrayList.add(new DynamicSportFieldText(2, sportsUtils.getGameStatusRGBColor(context), watchCricketUIPreference.getGameStatusXPosition() - 64, watchCricketUIPreference.getGameStatusYPosition(), sportsUtils.getGameStatusLength(context), sportsUtils.getGameStatusWidth(context), sportsUtils.getGameStatusFont(context), 0, "Internet offline!"));
        Intrinsics.checkNotNull(context);
        c(context, arrayList);
    }

    public final void b(Context context) {
        AppDynamicSportsField appDynamicSportsField = AppDynamicSportsField.INSTANCE;
        if (appDynamicSportsField.getMDynamicSportsFieldList() != null) {
            ArrayList<DynamicSportsField> mDynamicSportsFieldList = appDynamicSportsField.getMDynamicSportsFieldList();
            Intrinsics.checkNotNull(mDynamicSportsFieldList);
            c(context, mDynamicSportsFieldList);
        }
    }

    public final void c(Context context, ArrayList<DynamicSportsField> arrayList) {
        if (BleApiManager.getInstance(context).getBleApi() == null || BleApiManager.getInstance(context).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        SportsNotificationRequest sportsNotificationRequest = new SportsNotificationRequest(arrayList);
        LogHelper.d("NetworkChangeReceiver", "sendSportDataToTheWatch");
        BleApiManager.getInstance(context).getBleApi().getData(sportsNotificationRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.utils.NetworkChangeReceiver$sendSportDataToTheWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        if (BleApiManager.getInstance(context).getBleApi() == null || BleApiManager.getInstance(context).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Intrinsics.checkNotNull(context);
        if (companion.isCZDevice(context)) {
            if (AppUtils.isNetConnected(context)) {
                LogHelper.d("NetworkChangeReceiver", "Online");
                if (SportsPreference.Companion.isNotificationEnabled(context)) {
                    b(context);
                    return;
                }
                return;
            }
            LogHelper.d("NetworkChangeReceiver", "Offline");
            a(context);
        }
    }
}
