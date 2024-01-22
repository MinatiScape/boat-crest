package com.coveiot.android.dashboard2.util;

import android.content.Context;
import android.os.Build;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SendBTCallInfoRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.NotificationSettings;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class BT3CallUtils {
    @NotNull
    public static final BT3CallUtils INSTANCE;

    /* renamed from: a  reason: collision with root package name */
    public static final String f4250a;

    static {
        BT3CallUtils bT3CallUtils = new BT3CallUtils();
        INSTANCE = bT3CallUtils;
        f4250a = bT3CallUtils.getClass().getSimpleName();
    }

    public final String getTAG() {
        return f4250a;
    }

    public final boolean isCallNotificationFeatureEnabled(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        NotificationSettings notificationsData = UserDataManager.getInstance(context).getNotificationsData();
        return notificationsData != null && notificationsData.isCall_notifications();
    }

    public final boolean isCallPermissionAvailable(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String[] permissionNotGiven = PermissionUtils.checkPermissionsHasGranted(context, new String[]{"android.permission.READ_CALL_LOG", "android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE"});
        if (Build.VERSION.SDK_INT >= 28) {
            permissionNotGiven = PermissionUtils.checkPermissionsHasGranted(context, new String[]{"android.permission.READ_CALL_LOG", "android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE", "android.permission.ANSWER_PHONE_CALLS"});
        }
        Intrinsics.checkNotNullExpressionValue(permissionNotGiven, "permissionNotGiven");
        return permissionNotGiven.length == 0;
    }

    public final void sendInfoToBT3Watch(@NotNull Context context, final short s, @NotNull String number, @Nullable String str) {
        String str2;
        BleApi bleApi;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(number, "number");
        BT3CallConstants bT3CallConstants = BT3CallConstants.INSTANCE;
        ConnectionStatus connectionStatus = null;
        if (s == bT3CallConstants.getINCOMING_CALL()) {
            str2 = "INCOMING CALL";
        } else if (s == bT3CallConstants.getANSWER()) {
            str2 = "ANSWER";
        } else if (s == bT3CallConstants.getOUTGOING_CALL()) {
            str2 = "OUTGOING CALL";
        } else {
            str2 = s == bT3CallConstants.getHANG_UP() ? "HANG UP" : null;
        }
        String str3 = f4250a;
        LogHelper.d(str3, "send to watch -> " + str2);
        BleApiManager bleApiManager = BleApiManager.getInstance(context);
        if (bleApiManager != null && (bleApi = bleApiManager.getBleApi()) != null) {
            connectionStatus = bleApi.getConnectionStatus();
        }
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(context).getBleApi().setUserSettings(new SendBTCallInfoRequest(s, number, str), new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.util.BT3CallUtils$sendInfoToBT3Watch$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    String tag = BT3CallUtils.INSTANCE.getTAG();
                    LogHelper.e(tag, "BT3 send info error " + error.getErrorMsg());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    String tag = BT3CallUtils.INSTANCE.getTAG();
                    LogHelper.d(tag, "BT3 -> Status: " + ((int) s) + " sent!");
                }
            });
        } else {
            LogHelper.d(str3, "BLE Not Connected!");
        }
    }
}
