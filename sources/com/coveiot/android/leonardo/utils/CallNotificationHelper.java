package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.CallStatusType;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.SendCallStatusRequest;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.request.StopMessageNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.GenericBandApplication;
import com.coveiot.android.dashboard2.util.BT3CallConstants;
import com.coveiot.android.dashboard2.util.BT3CallUtils;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.NotificationSettings;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class CallNotificationHelper {

    /* renamed from: a  reason: collision with root package name */
    public static int f5418a;
    public static boolean d;
    @NotNull
    public static final CallNotificationHelper INSTANCE = new CallNotificationHelper();
    public static int b = -1;
    public static int c = -1;
    @NotNull
    public static String e = "";
    @NotNull
    public static String f = "SocialNotificationListenerService";
    @NotNull
    public static Handler g = new Handler(Looper.getMainLooper());

    public static final void b(String str, Context context, GenericBandApplication application) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        DeviceSupportedFeatures deviceSupportedFeatures3;
        BleApi bleApi;
        int i;
        DeviceSupportedFeatures deviceSupportedFeatures4;
        String str2 = str;
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(application, "$application");
        String str3 = f;
        StringBuilder sb = new StringBuilder();
        sb.append("MyPhoneStateListener incomingNumber :====== ");
        sb.append(str2);
        sb.append("currentCallState :");
        CallNotificationHelper callNotificationHelper = INSTANCE;
        sb.append(b);
        LogHelper.d(str3, sb.toString());
        LogHelper.d(f, "previousCallState====== " + f5418a);
        boolean z = false;
        d = false;
        if (str2 != null) {
            e = str2;
        }
        int i2 = f5418a;
        int i3 = b;
        if (i2 != i3 && c != i3) {
            c = i3;
            NotificationSettings notificationsData = UserDataManager.getInstance(context).getNotificationsData();
            if (notificationsData != null && notificationsData.isCall_notifications()) {
                if (str2 != null) {
                    boolean z2 = true;
                    if (str.length() > 0) {
                        int i4 = f5418a;
                        if ((i4 == 0 && b == 2) || (i4 == 2 && b == 0)) {
                            if (i4 == b) {
                                LogHelper.d(f, "previousCallState and currentCallState are same ignored.");
                                return;
                            }
                            BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
                            if (bleApi2 != null && (deviceSupportedFeatures4 = bleApi2.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures4.isKahaBTCallSupported()) {
                                z = true;
                            }
                            if (z) {
                                int i5 = f5418a;
                                if (i5 == 0 && b == 2) {
                                    BT3CallUtils.INSTANCE.sendInfoToBT3Watch(context, BT3CallConstants.INSTANCE.getOUTGOING_CALL(), kotlin.text.m.replace$default(StringsKt__StringsKt.trim(str).toString(), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null), PayUtils.INSTANCE.getContactName(context, str2));
                                } else if (i5 == 2 && b == 0) {
                                    BT3CallUtils.INSTANCE.sendInfoToBT3Watch(context, BT3CallConstants.INSTANCE.getHANG_UP(), kotlin.text.m.replace$default(StringsKt__StringsKt.trim(str).toString(), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null), PayUtils.INSTANCE.getContactName(context, str2));
                                }
                            } else {
                                int i6 = f5418a;
                                if (i6 == 1 && (i = b) == 0) {
                                    if (i6 == i) {
                                        LogHelper.d(f, "previousCallState and currentCallState are same ignored.");
                                        return;
                                    } else if (!DeviceUtils.Companion.isJstyleDevice(context)) {
                                        callNotificationHelper.stopNotificationRequest(context);
                                    }
                                }
                            }
                        } else {
                            int i7 = b;
                            if (i7 == 1) {
                                if (i4 == i7) {
                                    LogHelper.d(f, "previousCallState and currentCallState are same ignored.");
                                    return;
                                }
                                UserDataManager.getInstance(context).saveLastIncomingNumberToQuickReply(str2);
                                BleApiManager bleApiManager = BleApiManager.getInstance(context);
                                if (((bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null) ? null : bleApi.getConnectionStatus()) == ConnectionStatus.CONNECTED) {
                                    PayUtils payUtils = PayUtils.INSTANCE;
                                    String contactName = payUtils.getContactName(context, str2);
                                    BleApi bleApi3 = BleApiManager.getInstance(context).getBleApi();
                                    if ((bleApi3 == null || (deviceSupportedFeatures3 = bleApi3.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures3.isKahaBTCallSupported()) ? false : true) {
                                        BT3CallUtils.INSTANCE.sendInfoToBT3Watch(context, BT3CallConstants.INSTANCE.getINCOMING_CALL(), kotlin.text.m.replace$default(StringsKt__StringsKt.trim(str).toString(), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null), payUtils.getContactName(context, str2));
                                    } else {
                                        SetMessageContentRequest setMessageContentReq = new SetMessageContentRequest.Builder().setNotificationType(NotificationType.CALL, contactName == null || contactName.length() == 0 ? str2 : contactName).build();
                                        if (contactName == null || contactName.length() == 0) {
                                            z = true;
                                        }
                                        if (!z) {
                                            str2 = contactName;
                                        }
                                        setMessageContentReq.title = str2;
                                        BleApi bleApi4 = BleApiManager.getInstance(context).getBleApi();
                                        Intrinsics.checkNotNullExpressionValue(setMessageContentReq, "setMessageContentReq");
                                        bleApi4.setUserSettings(setMessageContentReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.utils.CallNotificationHelper$onCallStateChangedL$1$2
                                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                            public void onSettingsError(@NotNull BleBaseError error) {
                                                Intrinsics.checkNotNullParameter(error, "error");
                                            }

                                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                                String str4;
                                                Intrinsics.checkNotNullParameter(response, "response");
                                                str4 = CallNotificationHelper.f;
                                                LogHelper.d(str4, "Call notification turned on");
                                            }
                                        });
                                    }
                                }
                            } else if (i4 == 1 && i7 == 2) {
                                if (i4 == i7) {
                                    LogHelper.d(f, "previousCallState and currentCallState are same ignored.");
                                    return;
                                }
                                BleApi bleApi5 = BleApiManager.getInstance(context).getBleApi();
                                if (bleApi5 == null || (deviceSupportedFeatures2 = bleApi5.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures2.isKahaBTCallSupported()) {
                                    z2 = false;
                                }
                                if (z2) {
                                    BT3CallUtils.INSTANCE.sendInfoToBT3Watch(context, BT3CallConstants.INSTANCE.getANSWER(), kotlin.text.m.replace$default(StringsKt__StringsKt.trim(str).toString(), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null), PayUtils.INSTANCE.getContactName(context, str2));
                                } else {
                                    if (!DeviceUtils.Companion.isJstyleDevice(context)) {
                                        callNotificationHelper.stopNotificationRequest(context);
                                    }
                                    callNotificationHelper.c(context, CallStatusType.ACCEPT);
                                }
                                if (application.isMuteCommandReceived()) {
                                    Object systemService = context.getSystemService("audio");
                                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
                                    ((AudioManager) systemService).adjustStreamVolume(2, 100, 0);
                                    application.setMuteCommandReceived(false);
                                }
                            } else if (i7 == 0 && i4 == 1) {
                                if (i4 == i7) {
                                    LogHelper.d(f, "previousCallState and currentCallState are same ignored.");
                                    return;
                                }
                                d = true;
                                BleApi bleApi6 = BleApiManager.getInstance(context).getBleApi();
                                if (bleApi6 == null || (deviceSupportedFeatures = bleApi6.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) {
                                    z2 = false;
                                }
                                if (z2) {
                                    BT3CallUtils.INSTANCE.sendInfoToBT3Watch(context, BT3CallConstants.INSTANCE.getHANG_UP(), kotlin.text.m.replace$default(StringsKt__StringsKt.trim(str).toString(), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null), PayUtils.INSTANCE.getContactName(context, str2));
                                } else {
                                    if (!DeviceUtils.Companion.isJstyleDevice(context)) {
                                        callNotificationHelper.stopNotificationRequest(context);
                                    }
                                    callNotificationHelper.c(context, CallStatusType.REJECT);
                                }
                                if (application.isMuteCommandReceived()) {
                                    Object systemService2 = context.getSystemService("audio");
                                    Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.media.AudioManager");
                                    ((AudioManager) systemService2).adjustStreamVolume(2, 100, 0);
                                    application.setMuteCommandReceived(false);
                                }
                            }
                        }
                    }
                }
                f5418a = b;
                c = -1;
                return;
            }
            f5418a = b;
            return;
        }
        LogHelper.d(f, "previousCallState and currentCallState are same ignored.");
    }

    public final void c(Context context, CallStatusType callStatusType) {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if ((!companion.isTouchELXDevice(context) && !companion.isEastApexDevice(context)) || BleApiManager.getInstance(context) == null || BleApiManager.getInstance(context).getBleApi() == null) {
            return;
        }
        BleApiManager.getInstance(context).getBleApi().setUserSettings(new SendCallStatusRequest(callStatusType), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.utils.CallNotificationHelper$sendCallSyncStatus$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d("sendCallSyncStatus", "Success");
            }
        });
    }

    public final boolean getCallMissedOrRejected() {
        return d;
    }

    public final int getCurrentCallState() {
        return b;
    }

    @NotNull
    public final String getIncomingCallNumber() {
        return e;
    }

    public final int getPreviousCallState() {
        return f5418a;
    }

    public final int getTempCurrentCallState() {
        return c;
    }

    public final void onCallStateChangedL(@NotNull final Context context, @NotNull final GenericBandApplication application, int i, @Nullable final String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(application, "application");
        b = i;
        g.removeCallbacksAndMessages(null);
        g.post(new Runnable() { // from class: com.coveiot.android.leonardo.utils.d
            @Override // java.lang.Runnable
            public final void run() {
                CallNotificationHelper.b(str, context, application);
            }
        });
    }

    public final void setCallMissedOrRejected(boolean z) {
        d = z;
    }

    public final void setCurrentCallState(int i) {
        b = i;
    }

    public final void setIncomingCallNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        e = str;
    }

    public final void setPreviousCallState(int i) {
        f5418a = i;
    }

    public final void setTempCurrentCallState(int i) {
        c = i;
    }

    public final void stopNotificationRequest(@NotNull Context context) {
        BleApi bleApi;
        Intrinsics.checkNotNullParameter(context, "context");
        BleApiManager bleApiManager = BleApiManager.getInstance(context);
        if (((bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null) ? null : bleApi.getConnectionStatus()) == ConnectionStatus.CONNECTED) {
            StopMessageNotificationRequest setMessageContentReq = new StopMessageNotificationRequest.Builder().setNotificationType(NotificationType.CALL).build();
            BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
            Intrinsics.checkNotNullExpressionValue(setMessageContentReq, "setMessageContentReq");
            bleApi2.setUserSettings(setMessageContentReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.utils.CallNotificationHelper$stopNotificationRequest$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    Intrinsics.checkNotNullParameter(response, "response");
                    str = CallNotificationHelper.f;
                    LogHelper.d(str, "Call notification turned off");
                }
            });
        }
    }
}
