package com.coveiot.android.leonardo.performance.custommessage;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
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
import com.coveiot.utils.utility.LogHelper;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class NudgeCommandManager {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void sendGenericMessageToBle$default(Companion companion, Context context, String str, int i, String str2, int i2, Object obj) {
            if ((i2 & 8) != 0) {
                str2 = null;
            }
            companion.sendGenericMessageToBle(context, str, i, str2);
        }

        public final void sendEnergyScoreMessageToBle(@NotNull Context context, int i, @NotNull String message, int i2) {
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
                    BleApiManager.getInstance(context).getBleApi().getData(new CustomMessageRequest(CustomMessageHelperFactory.Companion.getCustomMessageHelper(context).getEnergyScoreCustomMessageRequest(i, message, i2), (short) 0, (short) (KHPerformancePreferenceManager.getInstance(context).isPerformanceBasedNotificationVibrationEnabled().booleanValue() ? 2000 : 100), (short) 8000, (short) 2, (short) 1), new DataResultListener() { // from class: com.coveiot.android.leonardo.performance.custommessage.NudgeCommandManager$Companion$sendEnergyScoreMessageToBle$1
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
            }
        }

        public final void sendGenericMessageToBle(@NotNull Context context, @NotNull String message, int i, @Nullable String str) {
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
                    BleApiManager.getInstance(context).getBleApi().getData(new CustomMessageRequest(CustomMessageHelperFactory.Companion.getCustomMessageHelper(context).getGenericCustomMessageRequest(message, i, str), (short) 0, (short) (KHPerformancePreferenceManager.getInstance(context).isPerformanceBasedNotificationVibrationEnabled().booleanValue() ? 2000 : 100), (short) 8000, (short) 2, (short) 1), new DataResultListener() { // from class: com.coveiot.android.leonardo.performance.custommessage.NudgeCommandManager$Companion$sendGenericMessageToBle$1
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
                } else if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smaf2_device), false) || DeviceUtils.Companion.isSmaJieieDevice(context)) {
                    SetMessageContentRequest setMessageContentReq = new SetMessageContentRequest.Builder().setNotificationType(NotificationType.CUSTOM_EVENT, String.valueOf(message), "Nudge").setCustomPackage(BuildConfig.APPLICATION_ID).build();
                    BleApi bleApi3 = BleApiManager.getInstance(context).getBleApi();
                    Intrinsics.checkNotNullExpressionValue(setMessageContentReq, "setMessageContentReq");
                    bleApi3.setUserSettings(setMessageContentReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.performance.custommessage.NudgeCommandManager$Companion$sendGenericMessageToBle$2
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

        public final void sendStressMessageToBle(@NotNull Context context, @NotNull Pair<Integer, Long> stressValuePair, @NotNull String message, int i) {
            DeviceSupportedFeatures deviceSupportedFeatures;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(stressValuePair, "stressValuePair");
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
                    BleApiManager.getInstance(context).getBleApi().getData(new CustomMessageRequest(CustomMessageHelperFactory.Companion.getCustomMessageHelper(context).getStressCustomMessageRequest(stressValuePair, message, i), (short) 0, (short) (KHPerformancePreferenceManager.getInstance(context).isPerformanceBasedNotificationVibrationEnabled().booleanValue() ? 2000 : 100), (short) 8000, (short) 2, (short) 1), new DataResultListener() { // from class: com.coveiot.android.leonardo.performance.custommessage.NudgeCommandManager$Companion$sendStressMessageToBle$1
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
            }
        }
    }
}
