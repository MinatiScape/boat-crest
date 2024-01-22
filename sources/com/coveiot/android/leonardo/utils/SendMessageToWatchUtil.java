package com.coveiot.android.leonardo.utils;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.DynamicSportFieldBBText;
import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.CustomMessageRequest;
import com.coveiot.android.bleabstract.request.NotificationType;
import com.coveiot.android.bleabstract.request.SetMessageContentRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.BuildConfig;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.sdk.ble.api.model.AlignmentEnum;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.task.ITask;
import java.util.ArrayList;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SendMessageToWatchUtil {
    @NotNull
    public static final SendMessageToWatchUtil INSTANCE = new SendMessageToWatchUtil();

    @JvmStatic
    public static final void sendMessageToBleForOTA(@NotNull Context context, @NotNull String message, int i, @Nullable String str) {
        DynamicSportsField dynamicSportFieldText;
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(message, "message");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        Boolean bool = null;
        if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
            BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
            if (bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null) {
                bool = Boolean.valueOf(deviceSupportedFeatures.isCustomMessageSupported());
            }
            Intrinsics.checkNotNull(bool);
            if (bool.booleanValue()) {
                int i2 = KHPerformancePreferenceManager.getInstance(context).isPerformanceBasedNotificationVibrationEnabled().booleanValue() ? 2000 : 100;
                ArrayList arrayList = new ArrayList();
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                if (companion.isCYDevice(context)) {
                    int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
                    BleUtils.convertHexTo565("#ffad02");
                    arrayList.add(new DynamicSportFieldImage(1, -1, 46, 0, 362, 200, i));
                    arrayList.add(new DynamicSportFieldBBText(2, convertHexTo565, 57, 185, 0, 0, 0, 0, str + "\n\n" + message, 28, 360, 200, AlignmentEnum.BOTTOM.getValue()));
                } else if (companion.isPS1Device(context)) {
                    int convertHexTo5652 = BleUtils.convertHexTo565("#ffffff");
                    arrayList.add(new DynamicSportFieldImage(1, -1, 46, 0, 362, 200, 45010));
                    arrayList.add(new DynamicSportFieldText(2, convertHexTo5652, 57, 185, 32, ITask.EVT_START, 28, 0, str + "\n\n" + message));
                } else {
                    String str2 = Typography.quote + str + "\" - " + message;
                    int convertHexTo5653 = BleUtils.convertHexTo565("#ffffff");
                    arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, i));
                    if (companion.isUltimaCallOrUltimaConnectDeviceForOTA(context)) {
                        dynamicSportFieldText = new DynamicSportFieldText(2, convertHexTo5653, 15, 140, 32, 32, 0, 0, str2);
                    } else if (companion.isOPP2Device(context)) {
                        dynamicSportFieldText = new DynamicSportFieldText(2, convertHexTo5653, 15, 130, 32, 32, 22, 0, str2);
                    } else if (companion.isOPP3Device(context)) {
                        dynamicSportFieldText = new DynamicSportFieldText(2, convertHexTo5653, 30, 225, 32, 32, 38, 0, str2);
                    } else if (companion.isOPP4Device(context)) {
                        dynamicSportFieldText = new DynamicSportFieldText(2, convertHexTo5653, 78, 200, 32, 32, 38, 0, str2);
                    } else if (!companion.isOPP1Device(context) && !companion.isOPP5Device(context)) {
                        if (!companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
                            if (companion.isCADevice(context)) {
                                dynamicSportFieldText = new DynamicSportFieldText(2, convertHexTo5653, 15, 170, 32, 32, 0, 0, str2);
                            } else {
                                dynamicSportFieldText = new DynamicSportFieldBBText(2, convertHexTo5653, 20, 50, 0, 0, 0, 0, str2, 24, 220, 200, AlignmentEnum.BOTTOM.getValue());
                            }
                        } else {
                            dynamicSportFieldText = new DynamicSportFieldText(2, convertHexTo5653, 15, 170, 32, 32, 0, 0, str2);
                        }
                    } else {
                        dynamicSportFieldText = new DynamicSportFieldText(2, convertHexTo5653, 15, 122, 32, 32, 22, 0, str2);
                    }
                    arrayList.add(dynamicSportFieldText);
                }
                BleApiManager.getInstance(context).getBleApi().getData(new CustomMessageRequest(arrayList, (short) 0, (short) i2, (short) 8000, (short) 2, (short) 1), new DataResultListener() { // from class: com.coveiot.android.leonardo.utils.SendMessageToWatchUtil$sendMessageToBleForOTA$1
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
            } else if (kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smaf2_device), false) || DeviceUtils.Companion.isSmaJieieDevice(context)) {
                SetMessageContentRequest setMessageContentReq = new SetMessageContentRequest.Builder().setNotificationType(NotificationType.CUSTOM_EVENT, String.valueOf(message), "Nudge").setCustomPackage(BuildConfig.APPLICATION_ID).build();
                BleApi bleApi3 = BleApiManager.getInstance(context).getBleApi();
                Intrinsics.checkNotNullExpressionValue(setMessageContentReq, "setMessageContentReq");
                bleApi3.setUserSettings(setMessageContentReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.utils.SendMessageToWatchUtil$sendMessageToBleForOTA$2
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        LogHelper.d("PerformanceWorker", "ble push failure");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        LogHelper.d("PerformanceWorker", "ble push success");
                    }
                });
            }
        }
    }

    public static /* synthetic */ void sendMessageToBleForOTA$default(Context context, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            str2 = null;
        }
        sendMessageToBleForOTA(context, str, i, str2);
    }
}
