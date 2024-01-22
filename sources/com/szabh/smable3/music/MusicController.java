package com.szabh.smable3.music;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import android.media.AudioManager;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.KeyEvent;
import com.bestmafen.baseble.util.BleLog;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import com.szabh.smable3.component.BleConnector;
import com.szabh.smable3.component.BleHandleCallback;
import com.szabh.smable3.entity.BleActivity;
import com.szabh.smable3.entity.BleAlarm;
import com.szabh.smable3.entity.BleAlipaySettings;
import com.szabh.smable3.entity.BleAppSportState;
import com.szabh.smable3.entity.BleAvgHeartRate;
import com.szabh.smable3.entity.BleBAC;
import com.szabh.smable3.entity.BleBloodGlucose;
import com.szabh.smable3.entity.BleBloodOxygen;
import com.szabh.smable3.entity.BleBloodPressure;
import com.szabh.smable3.entity.BleBodyStatus;
import com.szabh.smable3.entity.BleCalorieIntake;
import com.szabh.smable3.entity.BleCoachingIds;
import com.szabh.smable3.entity.BleDeviceFile;
import com.szabh.smable3.entity.BleDeviceInfo;
import com.szabh.smable3.entity.BleDeviceInfo2;
import com.szabh.smable3.entity.BleDrinkWaterSettings;
import com.szabh.smable3.entity.BleFoodBalance;
import com.szabh.smable3.entity.BleGSensorMotion;
import com.szabh.smable3.entity.BleGSensorRaw;
import com.szabh.smable3.entity.BleGestureWake;
import com.szabh.smable3.entity.BleHRRaw;
import com.szabh.smable3.entity.BleHeartRate;
import com.szabh.smable3.entity.BleHrMonitoringSettings;
import com.szabh.smable3.entity.BleHrv;
import com.szabh.smable3.entity.BleLanguagePackVersion;
import com.szabh.smable3.entity.BleLocation;
import com.szabh.smable3.entity.BleLocationGga;
import com.szabh.smable3.entity.BleLocationGsv;
import com.szabh.smable3.entity.BleLogText;
import com.szabh.smable3.entity.BleLoveTap;
import com.szabh.smable3.entity.BleLoveTapUser;
import com.szabh.smable3.entity.BleMatchRecord;
import com.szabh.smable3.entity.BleMatchRecord2;
import com.szabh.smable3.entity.BleMedicationAlarm;
import com.szabh.smable3.entity.BleMedicationReminder;
import com.szabh.smable3.entity.BleMindStatus;
import com.szabh.smable3.entity.BleMusicControl;
import com.szabh.smable3.entity.BleNaviInfo;
import com.szabh.smable3.entity.BleNoDisturbSettings;
import com.szabh.smable3.entity.BlePackageStatus;
import com.szabh.smable3.entity.BlePressure;
import com.szabh.smable3.entity.BleRealTimeMeasurement;
import com.szabh.smable3.entity.BleRealtimeLog;
import com.szabh.smable3.entity.BleRecordPacket;
import com.szabh.smable3.entity.BleSMSQuickReply;
import com.szabh.smable3.entity.BleSedentarinessSettings;
import com.szabh.smable3.entity.BleSleep;
import com.szabh.smable3.entity.BleSleepQuality;
import com.szabh.smable3.entity.BleStock;
import com.szabh.smable3.entity.BleTemperature;
import com.szabh.smable3.entity.BleThirdPartyData;
import com.szabh.smable3.entity.BleTuyaKey;
import com.szabh.smable3.entity.BleUserProfile;
import com.szabh.smable3.entity.BleWatchFaceId;
import com.szabh.smable3.entity.BleWorkout;
import com.szabh.smable3.entity.BleWorkout2;
import com.szabh.smable3.entity.BleWorldClock;
import com.szabh.smable3.entity.MusicAttr;
import com.szabh.smable3.entity.MusicCommand;
import com.szabh.smable3.entity.MusicEntity;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.f;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class MusicController extends MusicControllerCompat {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String LOG_HEADER = "MusicController";
    @Nullable
    private MediaControllerCompat mActiveMediaController;
    private long mLastVolumeTime;
    @Nullable
    private MediaSessionManager mMediaSessionManager;
    private ComponentName mNotificationListener;
    private int mLastVolume = -1;
    @NotNull
    private final MusicController$mCallback$1 mCallback = new MediaControllerCompat.Callback() { // from class: com.szabh.smable3.music.MusicController$mCallback$1
        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onMetadataChanged(@Nullable MediaMetadataCompat mediaMetadataCompat) {
            MusicController.this.updateMetadata(mediaMetadataCompat);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onPlaybackStateChanged(@Nullable PlaybackStateCompat playbackStateCompat) {
            MusicController.this.updatePlaybackState(playbackStateCompat);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionDestroyed() {
            BleLog.INSTANCE.i("MusicController onSessionDestroyed");
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionEvent(@Nullable String str, @Nullable Bundle bundle) {
            BleLog bleLog = BleLog.INSTANCE;
            bleLog.i("MusicController onSessionEvent -> event:" + str + ", extras:" + bundle);
        }
    };
    @NotNull
    private final MediaSessionManager.OnActiveSessionsChangedListener mOnActiveSessionsChangedListener = new MediaSessionManager.OnActiveSessionsChangedListener() { // from class: com.szabh.smable3.music.a
        @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
        public final void onActiveSessionsChanged(List list) {
            MusicController.mOnActiveSessionsChangedListener$lambda$1(MusicController.this, list);
        }
    };
    @NotNull
    private final MusicController$mBleHandleCallback$1 mBleHandleCallback = new BleHandleCallback() { // from class: com.szabh.smable3.music.MusicController$mBleHandleCallback$1

        /* loaded from: classes12.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[MusicCommand.values().length];
                try {
                    iArr[MusicCommand.PLAY.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[MusicCommand.PAUSE.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[MusicCommand.TOGGLE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[MusicCommand.NEXT.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[MusicCommand.PRE.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[MusicCommand.VOLUME_UP.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[MusicCommand.VOLUME_DOWN.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[MusicCommand.UNKNOWN.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onAlarmAdd(@NotNull BleAlarm bleAlarm) {
            BleHandleCallback.DefaultImpls.onAlarmAdd(this, bleAlarm);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onAlarmDelete(int i) {
            BleHandleCallback.DefaultImpls.onAlarmDelete(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onAlarmUpdate(@NotNull BleAlarm bleAlarm) {
            BleHandleCallback.DefaultImpls.onAlarmUpdate(this, bleAlarm);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onAppSportDataResponse(boolean z) {
            BleHandleCallback.DefaultImpls.onAppSportDataResponse(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onBacklightUpdate(int i) {
            BleHandleCallback.DefaultImpls.onBacklightUpdate(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onBleThirdPartyDataUpdate(@NotNull BleThirdPartyData bleThirdPartyData) {
            BleHandleCallback.DefaultImpls.onBleThirdPartyDataUpdate(this, bleThirdPartyData);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onCameraResponse(boolean z, int i) {
            BleHandleCallback.DefaultImpls.onCameraResponse(this, z, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onCameraStateChange(int i) {
            BleHandleCallback.DefaultImpls.onCameraStateChange(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onClassicBluetoothStateChange(int i) {
            BleHandleCallback.DefaultImpls.onClassicBluetoothStateChange(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onCommandReply(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, boolean z) {
            BleHandleCallback.DefaultImpls.onCommandReply(this, bleKey, bleKeyFlag, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onCommandSendTimeout(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag) {
            BleHandleCallback.DefaultImpls.onCommandSendTimeout(this, bleKey, bleKeyFlag);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onDeviceConnected(@NotNull BluetoothDevice bluetoothDevice) {
            BleHandleCallback.DefaultImpls.onDeviceConnected(this, bluetoothDevice);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onDeviceConnecting(boolean z) {
            BleHandleCallback.DefaultImpls.onDeviceConnecting(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onDeviceFileUpdate(@NotNull BleDeviceFile bleDeviceFile) {
            BleHandleCallback.DefaultImpls.onDeviceFileUpdate(this, bleDeviceFile);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onDeviceRequestAGpsFile(@NotNull String str) {
            BleHandleCallback.DefaultImpls.onDeviceRequestAGpsFile(this, str);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onDeviceSMSQuickReply(@NotNull BleSMSQuickReply bleSMSQuickReply) {
            BleHandleCallback.DefaultImpls.onDeviceSMSQuickReply(this, bleSMSQuickReply);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onFindPhone(boolean z) {
            BleHandleCallback.DefaultImpls.onFindPhone(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onFollowSystemLanguage(boolean z) {
            BleHandleCallback.DefaultImpls.onFollowSystemLanguage(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onGestureWakeUpdate(@NotNull BleGestureWake bleGestureWake) {
            BleHandleCallback.DefaultImpls.onGestureWakeUpdate(this, bleGestureWake);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onHIDState(int i) {
            BleHandleCallback.DefaultImpls.onHIDState(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onHIDValueChange(int i) {
            MediaControllerCompat mediaControllerCompat;
            MediaControllerCompat mediaControllerCompat2;
            MediaSessionManager mediaSessionManager;
            List<MediaController> controllers;
            ComponentName componentName;
            PlaybackStateCompat playbackState;
            if (i == 0 || i == 1 || i == 2) {
                BleLog bleLog = BleLog.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("MusicController onHIDValueChange ");
                sb.append(i);
                sb.append(" , current activeMediaController = ");
                mediaControllerCompat = MusicController.this.mActiveMediaController;
                ArrayList arrayList = null;
                sb.append(mediaControllerCompat != null ? mediaControllerCompat.getPackageName() : null);
                sb.append(" playbackState=");
                mediaControllerCompat2 = MusicController.this.mActiveMediaController;
                sb.append((mediaControllerCompat2 == null || (playbackState = mediaControllerCompat2.getPlaybackState()) == null) ? null : Integer.valueOf(playbackState.getState()));
                bleLog.i(sb.toString());
                mediaSessionManager = MusicController.this.mMediaSessionManager;
                if (mediaSessionManager != null) {
                    componentName = MusicController.this.mNotificationListener;
                    if (componentName == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mNotificationListener");
                        componentName = null;
                    }
                    controllers = mediaSessionManager.getActiveSessions(componentName);
                } else {
                    controllers = null;
                }
                MusicController musicController = MusicController.this;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("MusicController onHIDValueChange activeMediaControllers -> ");
                if (controllers != null) {
                    Intrinsics.checkNotNullExpressionValue(controllers, "controllers");
                    ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(controllers, 10));
                    for (MediaController mediaController : controllers) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(mediaController.getPackageName());
                        sb3.append(" playbackState=");
                        PlaybackState playbackState2 = mediaController.getPlaybackState();
                        sb3.append(playbackState2 != null ? Integer.valueOf(playbackState2.getState()) : null);
                        arrayList2.add(sb3.toString());
                    }
                    arrayList = arrayList2;
                }
                sb2.append(arrayList);
                bleLog.i(sb2.toString());
                musicController.autoSwitchActiveController(controllers, true);
            }
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onIdentityCreate(boolean z, @Nullable BleDeviceInfo bleDeviceInfo) {
            BleHandleCallback.DefaultImpls.onIdentityCreate(this, z, bleDeviceInfo);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onIdentityDelete(boolean z) {
            BleHandleCallback.DefaultImpls.onIdentityDelete(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onIdentityDeleteByDevice(boolean z) {
            BleHandleCallback.DefaultImpls.onIdentityDeleteByDevice(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onIncomingCallStatus(int i) {
            BleHandleCallback.DefaultImpls.onIncomingCallStatus(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onLoveTapUpdate(@NotNull BleLoveTap bleLoveTap) {
            BleHandleCallback.DefaultImpls.onLoveTapUpdate(this, bleLoveTap);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onLoveTapUserDelete(int i) {
            BleHandleCallback.DefaultImpls.onLoveTapUserDelete(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onLoveTapUserUpdate(@NotNull BleLoveTapUser bleLoveTapUser) {
            BleHandleCallback.DefaultImpls.onLoveTapUserUpdate(this, bleLoveTapUser);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onMedicationAlarmAdd(@NotNull BleMedicationAlarm bleMedicationAlarm) {
            BleHandleCallback.DefaultImpls.onMedicationAlarmAdd(this, bleMedicationAlarm);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onMedicationAlarmDelete(int i) {
            BleHandleCallback.DefaultImpls.onMedicationAlarmDelete(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onMedicationAlarmUpdate(@NotNull BleMedicationAlarm bleMedicationAlarm) {
            BleHandleCallback.DefaultImpls.onMedicationAlarmUpdate(this, bleMedicationAlarm);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onMedicationReminderDelete(int i) {
            BleHandleCallback.DefaultImpls.onMedicationReminderDelete(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onMedicationReminderUpdate(@NotNull BleMedicationReminder bleMedicationReminder) {
            BleHandleCallback.DefaultImpls.onMedicationReminderUpdate(this, bleMedicationReminder);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onNaviInfoUpdate(@NotNull BleNaviInfo bleNaviInfo) {
            BleHandleCallback.DefaultImpls.onNaviInfoUpdate(this, bleNaviInfo);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onNoDisturbUpdate(@NotNull BleNoDisturbSettings bleNoDisturbSettings) {
            BleHandleCallback.DefaultImpls.onNoDisturbUpdate(this, bleNoDisturbSettings);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onOTA(boolean z) {
            BleHandleCallback.DefaultImpls.onOTA(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onPowerSaveModeState(int i) {
            BleHandleCallback.DefaultImpls.onPowerSaveModeState(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onPowerSaveModeStateChange(int i) {
            BleHandleCallback.DefaultImpls.onPowerSaveModeStateChange(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadActivity(@NotNull List<BleActivity> list) {
            BleHandleCallback.DefaultImpls.onReadActivity(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadAlarm(@NotNull List<BleAlarm> list) {
            BleHandleCallback.DefaultImpls.onReadAlarm(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadAlipaySettings(@NotNull BleAlipaySettings bleAlipaySettings) {
            BleHandleCallback.DefaultImpls.onReadAlipaySettings(this, bleAlipaySettings);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadAvgHeartRate(@NotNull List<BleAvgHeartRate> list) {
            BleHandleCallback.DefaultImpls.onReadAvgHeartRate(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadBAC(@NotNull List<BleBAC> list) {
            BleHandleCallback.DefaultImpls.onReadBAC(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadBacklight(int i) {
            BleHandleCallback.DefaultImpls.onReadBacklight(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadBleAddress(@NotNull String str) {
            BleHandleCallback.DefaultImpls.onReadBleAddress(this, str);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadBleHrv(@NotNull List<BleHrv> list) {
            BleHandleCallback.DefaultImpls.onReadBleHrv(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadBleLogText(@NotNull List<BleLogText> list) {
            BleHandleCallback.DefaultImpls.onReadBleLogText(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadBloodGlucose(@NotNull List<BleBloodGlucose> list) {
            BleHandleCallback.DefaultImpls.onReadBloodGlucose(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadBloodOxygen(@NotNull List<BleBloodOxygen> list) {
            BleHandleCallback.DefaultImpls.onReadBloodOxygen(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadBloodPressure(@NotNull List<BleBloodPressure> list) {
            BleHandleCallback.DefaultImpls.onReadBloodPressure(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadBodyStatus(@NotNull List<BleBodyStatus> list) {
            BleHandleCallback.DefaultImpls.onReadBodyStatus(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadCalorieIntake(@NotNull List<BleCalorieIntake> list) {
            BleHandleCallback.DefaultImpls.onReadCalorieIntake(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadCaloriesGoal(int i) {
            BleHandleCallback.DefaultImpls.onReadCaloriesGoal(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadCoachingIds(@NotNull BleCoachingIds bleCoachingIds) {
            BleHandleCallback.DefaultImpls.onReadCoachingIds(this, bleCoachingIds);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadDateFormat(int i) {
            BleHandleCallback.DefaultImpls.onReadDateFormat(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadDeviceFile(@NotNull BleDeviceFile bleDeviceFile) {
            BleHandleCallback.DefaultImpls.onReadDeviceFile(this, bleDeviceFile);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadDeviceInfo(@NotNull BleDeviceInfo bleDeviceInfo) {
            BleHandleCallback.DefaultImpls.onReadDeviceInfo(this, bleDeviceInfo);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadDeviceInfo2(@NotNull BleDeviceInfo2 bleDeviceInfo2) {
            BleHandleCallback.DefaultImpls.onReadDeviceInfo2(this, bleDeviceInfo2);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadDistanceGoal(int i) {
            BleHandleCallback.DefaultImpls.onReadDistanceGoal(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadDrinkWater(@NotNull BleDrinkWaterSettings bleDrinkWaterSettings) {
            BleHandleCallback.DefaultImpls.onReadDrinkWater(this, bleDrinkWaterSettings);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadFirmwareVersion(@NotNull String str) {
            BleHandleCallback.DefaultImpls.onReadFirmwareVersion(this, str);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadFoodBalance(@NotNull List<BleFoodBalance> list) {
            BleHandleCallback.DefaultImpls.onReadFoodBalance(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadGestureWake(@NotNull BleGestureWake bleGestureWake) {
            BleHandleCallback.DefaultImpls.onReadGestureWake(this, bleGestureWake);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadHeartRate(@NotNull List<BleHeartRate> list) {
            BleHandleCallback.DefaultImpls.onReadHeartRate(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadHourSystem(int i) {
            BleHandleCallback.DefaultImpls.onReadHourSystem(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadHrMonitoringSettings(@NotNull BleHrMonitoringSettings bleHrMonitoringSettings) {
            BleHandleCallback.DefaultImpls.onReadHrMonitoringSettings(this, bleHrMonitoringSettings);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadLanguagePackVersion(@NotNull BleLanguagePackVersion bleLanguagePackVersion) {
            BleHandleCallback.DefaultImpls.onReadLanguagePackVersion(this, bleLanguagePackVersion);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadLocation(@NotNull List<BleLocation> list) {
            BleHandleCallback.DefaultImpls.onReadLocation(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadLoveTapUser(@NotNull List<BleLoveTapUser> list) {
            BleHandleCallback.DefaultImpls.onReadLoveTapUser(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadMatchRecord(@NotNull List<BleMatchRecord> list) {
            BleHandleCallback.DefaultImpls.onReadMatchRecord(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadMatchRecord2(@NotNull List<BleMatchRecord2> list) {
            BleHandleCallback.DefaultImpls.onReadMatchRecord2(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadMedicationAlarm(@NotNull List<BleMedicationAlarm> list) {
            BleHandleCallback.DefaultImpls.onReadMedicationAlarm(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadMedicationReminder(@NotNull List<BleMedicationReminder> list) {
            BleHandleCallback.DefaultImpls.onReadMedicationReminder(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadMindStatus(@NotNull List<BleMindStatus> list) {
            BleHandleCallback.DefaultImpls.onReadMindStatus(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadMtkOtaMeta() {
            BleHandleCallback.DefaultImpls.onReadMtkOtaMeta(this);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadNoDisturb(@NotNull BleNoDisturbSettings bleNoDisturbSettings) {
            BleHandleCallback.DefaultImpls.onReadNoDisturb(this, bleNoDisturbSettings);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadPackageStatus(@NotNull BlePackageStatus blePackageStatus) {
            BleHandleCallback.DefaultImpls.onReadPackageStatus(this, blePackageStatus);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadPower(int i) {
            BleHandleCallback.DefaultImpls.onReadPower(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadPressure(@NotNull List<BlePressure> list) {
            BleHandleCallback.DefaultImpls.onReadPressure(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadSedentariness(@NotNull BleSedentarinessSettings bleSedentarinessSettings) {
            BleHandleCallback.DefaultImpls.onReadSedentariness(this, bleSedentarinessSettings);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadSleep(@NotNull List<BleSleep> list) {
            BleHandleCallback.DefaultImpls.onReadSleep(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadSleepGoal(int i) {
            BleHandleCallback.DefaultImpls.onReadSleepGoal(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadSleepQuality(@NotNull BleSleepQuality bleSleepQuality) {
            BleHandleCallback.DefaultImpls.onReadSleepQuality(this, bleSleepQuality);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadSleepRaw(@NotNull byte[] bArr) {
            BleHandleCallback.DefaultImpls.onReadSleepRaw(this, bArr);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadStepGoal(int i) {
            BleHandleCallback.DefaultImpls.onReadStepGoal(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadStock(@NotNull List<BleStock> list) {
            BleHandleCallback.DefaultImpls.onReadStock(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadTemperature(@NotNull List<BleTemperature> list) {
            BleHandleCallback.DefaultImpls.onReadTemperature(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadTemperatureUnit(int i) {
            BleHandleCallback.DefaultImpls.onReadTemperatureUnit(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadTuyaKey(@NotNull BleTuyaKey bleTuyaKey) {
            BleHandleCallback.DefaultImpls.onReadTuyaKey(this, bleTuyaKey);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadUiPackVersion(@NotNull String str) {
            BleHandleCallback.DefaultImpls.onReadUiPackVersion(this, str);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadUnit(int i) {
            BleHandleCallback.DefaultImpls.onReadUnit(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadUserProfile(@NotNull BleUserProfile bleUserProfile) {
            BleHandleCallback.DefaultImpls.onReadUserProfile(this, bleUserProfile);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadWatchFaceId(@NotNull BleWatchFaceId bleWatchFaceId) {
            BleHandleCallback.DefaultImpls.onReadWatchFaceId(this, bleWatchFaceId);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadWatchFaceSwitch(int i) {
            BleHandleCallback.DefaultImpls.onReadWatchFaceSwitch(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadWeatherRealTime(boolean z) {
            BleHandleCallback.DefaultImpls.onReadWeatherRealTime(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadWorkout(@NotNull List<BleWorkout> list) {
            BleHandleCallback.DefaultImpls.onReadWorkout(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadWorkout2(@NotNull List<BleWorkout2> list) {
            BleHandleCallback.DefaultImpls.onReadWorkout2(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadWorldClock(@NotNull List<BleWorldClock> list) {
            BleHandleCallback.DefaultImpls.onReadWorldClock(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onRealTimeMeasurement(@NotNull BleRealTimeMeasurement bleRealTimeMeasurement) {
            BleHandleCallback.DefaultImpls.onRealTimeMeasurement(this, bleRealTimeMeasurement);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReceiveGSensorMotion(@NotNull List<BleGSensorMotion> list) {
            BleHandleCallback.DefaultImpls.onReceiveGSensorMotion(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReceiveGSensorRaw(@NotNull List<BleGSensorRaw> list) {
            BleHandleCallback.DefaultImpls.onReceiveGSensorRaw(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReceiveHRRaw(@NotNull List<BleHRRaw> list) {
            BleHandleCallback.DefaultImpls.onReceiveHRRaw(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReceiveLocationGga(@NotNull BleLocationGga bleLocationGga) {
            BleHandleCallback.DefaultImpls.onReceiveLocationGga(this, bleLocationGga);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReceiveLocationGsv(@NotNull List<BleLocationGsv> list) {
            BleHandleCallback.DefaultImpls.onReceiveLocationGsv(this, list);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReceiveMusicCommand(@NotNull MusicCommand musicCommand) {
            MediaControllerCompat mediaControllerCompat;
            MediaControllerCompat mediaControllerCompat2;
            MediaSessionManager mediaSessionManager;
            List<MediaController> controllers;
            ComponentName componentName;
            PlaybackStateCompat playbackState;
            Intrinsics.checkNotNullParameter(musicCommand, "musicCommand");
            int i = WhenMappings.$EnumSwitchMapping$0[musicCommand.ordinal()];
            if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
                BleLog bleLog = BleLog.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("MusicController onReceiveMusicCommand ");
                sb.append(musicCommand);
                sb.append(" , current activeMediaController = ");
                mediaControllerCompat = MusicController.this.mActiveMediaController;
                ArrayList arrayList = null;
                sb.append(mediaControllerCompat != null ? mediaControllerCompat.getPackageName() : null);
                sb.append(" playbackState=");
                mediaControllerCompat2 = MusicController.this.mActiveMediaController;
                sb.append((mediaControllerCompat2 == null || (playbackState = mediaControllerCompat2.getPlaybackState()) == null) ? null : Integer.valueOf(playbackState.getState()));
                bleLog.i(sb.toString());
                mediaSessionManager = MusicController.this.mMediaSessionManager;
                if (mediaSessionManager != null) {
                    componentName = MusicController.this.mNotificationListener;
                    if (componentName == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mNotificationListener");
                        componentName = null;
                    }
                    controllers = mediaSessionManager.getActiveSessions(componentName);
                } else {
                    controllers = null;
                }
                MusicController musicController = MusicController.this;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("MusicController onReceiveMusicCommand activeMediaControllers -> ");
                if (controllers != null) {
                    Intrinsics.checkNotNullExpressionValue(controllers, "controllers");
                    ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(controllers, 10));
                    for (MediaController mediaController : controllers) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(mediaController.getPackageName());
                        sb3.append(" playbackState=");
                        PlaybackState playbackState2 = mediaController.getPlaybackState();
                        sb3.append(playbackState2 != null ? Integer.valueOf(playbackState2.getState()) : null);
                        arrayList2.add(sb3.toString());
                    }
                    arrayList = arrayList2;
                }
                sb2.append(arrayList);
                bleLog.i(sb2.toString());
                musicController.autoSwitchActiveController(controllers, true);
            }
            switch (WhenMappings.$EnumSwitchMapping$0[musicCommand.ordinal()]) {
                case 1:
                    MusicController.this.sendMediaKeyEvent(126);
                    return;
                case 2:
                    MusicController.this.sendMediaKeyEvent(127);
                    return;
                case 3:
                    MusicController.this.sendMediaKeyEvent(85);
                    return;
                case 4:
                    MusicController.this.sendMediaKeyEvent(87);
                    return;
                case 5:
                    MusicController.this.sendMediaKeyEvent(88);
                    return;
                case 6:
                    AudioManager mAudioManager = MusicController.this.getMAudioManager();
                    if (mAudioManager != null) {
                        mAudioManager.adjustStreamVolume(3, 1, 5);
                        return;
                    }
                    return;
                case 7:
                    AudioManager mAudioManager2 = MusicController.this.getMAudioManager();
                    if (mAudioManager2 != null) {
                        mAudioManager2.adjustStreamVolume(3, -1, 5);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReceiveRealtimeLog(@NotNull BleRealtimeLog bleRealtimeLog) {
            BleHandleCallback.DefaultImpls.onReceiveRealtimeLog(this, bleRealtimeLog);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReceiveRecordPacket(@NotNull BleRecordPacket bleRecordPacket) {
            BleHandleCallback.DefaultImpls.onReceiveRecordPacket(this, bleRecordPacket);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onRequestAgpsPrerequisite() {
            BleHandleCallback.DefaultImpls.onRequestAgpsPrerequisite(this);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onRequestLocation(int i) {
            BleHandleCallback.DefaultImpls.onRequestLocation(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onSessionStateChange(boolean z) {
            BleHandleCallback.DefaultImpls.onSessionStateChange(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onStockDelete(int i) {
            BleHandleCallback.DefaultImpls.onStockDelete(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onStreamProgress(boolean z, int i, int i2, int i3) {
            BleHandleCallback.DefaultImpls.onStreamProgress(this, z, i, i2, i3);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onSyncData(int i, @NotNull BleKey bleKey) {
            BleHandleCallback.DefaultImpls.onSyncData(this, i, bleKey);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onUpdateAppSportState(@NotNull BleAppSportState bleAppSportState) {
            BleHandleCallback.DefaultImpls.onUpdateAppSportState(this, bleAppSportState);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onUpdateBAC(@NotNull BleBAC bleBAC) {
            BleHandleCallback.DefaultImpls.onUpdateBAC(this, bleBAC);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onUpdateBloodPressure(@NotNull BleBloodPressure bleBloodPressure) {
            BleHandleCallback.DefaultImpls.onUpdateBloodPressure(this, bleBloodPressure);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onUpdateHeartRate(@NotNull BleHeartRate bleHeartRate) {
            BleHandleCallback.DefaultImpls.onUpdateHeartRate(this, bleHeartRate);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onUpdateTemperature(@NotNull BleTemperature bleTemperature) {
            BleHandleCallback.DefaultImpls.onUpdateTemperature(this, bleTemperature);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onUpdateWatchFaceSwitch(boolean z) {
            BleHandleCallback.DefaultImpls.onUpdateWatchFaceSwitch(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onVibrationUpdate(int i) {
            BleHandleCallback.DefaultImpls.onVibrationUpdate(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onWatchFaceIdUpdate(boolean z) {
            BleHandleCallback.DefaultImpls.onWatchFaceIdUpdate(this, z);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onWorldClockDelete(int i) {
            BleHandleCallback.DefaultImpls.onWorldClockDelete(this, i);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onXModem(byte b) {
            BleHandleCallback.DefaultImpls.onXModem(this, b);
        }

        @Override // com.szabh.smable3.component.BleHandleCallback
        public void onReadStock(boolean z) {
            BleHandleCallback.DefaultImpls.onReadStock(this, z);
        }
    };

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getState(PlaybackStateCompat playbackStateCompat) {
            if (playbackStateCompat == null) {
                return "null";
            }
            int state = playbackStateCompat.getState();
            return state != 1 ? state != 2 ? state != 3 ? state != 4 ? state != 5 ? "OTHER" : "STATE_REWINDING" : "STATE_FAST_FORWARDING" : "STATE_PLAYING" : "STATE_PAUSED" : "STATE_STOPPED";
        }
    }

    private final void assignActiveController(MediaController mediaController) {
        try {
            releaseActiveController();
            BleLog bleLog = BleLog.INSTANCE;
            StringBuilder sb = new StringBuilder();
            sb.append("MusicController assignActiveController -> ");
            sb.append(mediaController != null ? mediaController.getPackageName() : null);
            bleLog.i(sb.toString());
            MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(getMContext(), MediaSessionCompat.Token.fromToken(mediaController != null ? mediaController.getSessionToken() : null));
            this.mActiveMediaController = mediaControllerCompat;
            mediaControllerCompat.registerCallback(this.mCallback);
            MediaControllerCompat mediaControllerCompat2 = this.mActiveMediaController;
            updatePlaybackState(mediaControllerCompat2 != null ? mediaControllerCompat2.getPlaybackState() : null);
            MediaControllerCompat mediaControllerCompat3 = this.mActiveMediaController;
            updateMetadata(mediaControllerCompat3 != null ? mediaControllerCompat3.getMetadata() : null);
        } catch (Exception e) {
            e.printStackTrace();
            BleLog bleLog2 = BleLog.INSTANCE;
            bleLog2.i("MusicController assignActiveController -> error:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void autoSwitchActiveController(List<MediaController> list, boolean z) {
        boolean z2;
        PlaybackStateCompat playbackState;
        if (list != null && !list.isEmpty()) {
            MediaControllerCompat mediaControllerCompat = this.mActiveMediaController;
            if (!Intrinsics.areEqual(mediaControllerCompat != null ? mediaControllerCompat.getPackageName() : null, list.get(0).getPackageName())) {
                PlaybackState playbackState2 = list.get(0).getPlaybackState();
                if ((playbackState2 != null ? Integer.valueOf(playbackState2.getState()) : null) != null) {
                    assignActiveController(list.get(0));
                    return;
                }
                return;
            }
            MediaControllerCompat mediaControllerCompat2 = this.mActiveMediaController;
            if ((mediaControllerCompat2 == null || (playbackState = mediaControllerCompat2.getPlaybackState()) == null || playbackState.getState() != 3) ? false : true) {
                return;
            }
            for (MediaController mediaController : list) {
                PlaybackState playbackState3 = mediaController.getPlaybackState();
                if (playbackState3 == null || playbackState3.getState() != 3) {
                    z2 = false;
                    continue;
                } else {
                    z2 = true;
                    continue;
                }
                if (z2) {
                    assignActiveController(mediaController);
                    return;
                }
            }
            return;
        }
        if (z) {
            releaseActiveController();
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(0.0f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        List listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{String.valueOf((int) com.szabh.smable3.entity.PlaybackState.PAUSED.getMState()), format, ",0"});
        BleConnector bleConnector = BleConnector.INSTANCE;
        bleConnector.updateMusic(new BleMusicControl(MusicEntity.PLAYER, MusicAttr.PLAYER_PLAYBACK_INFO, listOf));
        MusicEntity musicEntity = MusicEntity.TRACK;
        bleConnector.updateMusic(new BleMusicControl(musicEntity, MusicAttr.TRACK_ARTIST, HexStringBuilder.DEFAULT_SEPARATOR));
        bleConnector.updateMusic(new BleMusicControl(musicEntity, MusicAttr.TRACK_ALBUM, HexStringBuilder.DEFAULT_SEPARATOR));
        bleConnector.updateMusic(new BleMusicControl(musicEntity, MusicAttr.TRACK_TITLE, HexStringBuilder.DEFAULT_SEPARATOR));
        bleConnector.updateMusic(new BleMusicControl(musicEntity, MusicAttr.TRACK_DURATION, BleConst.GetDeviceTime));
        this.mLastVolume = -1;
    }

    public static /* synthetic */ void autoSwitchActiveController$default(MusicController musicController, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        musicController.autoSwitchActiveController(list, z);
    }

    private final ComponentName getNotificationListener(Context context) {
        if (!(context instanceof NotificationListenerService)) {
            ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services;
            ServiceInfo serviceInfo = null;
            if (serviceInfoArr != null) {
                int i = 0;
                int length = serviceInfoArr.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    ServiceInfo serviceInfo2 = serviceInfoArr[i];
                    if (Intrinsics.areEqual(serviceInfo2.permission, "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE")) {
                        serviceInfo = serviceInfo2;
                        break;
                    }
                    i++;
                }
            }
            if (serviceInfo != null) {
                BleLog bleLog = BleLog.INSTANCE;
                bleLog.d("NotificationListener -> " + serviceInfo.name);
                return new ComponentName(context, serviceInfo.name);
            }
        }
        return new ComponentName(context, context.getClass());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mOnActiveSessionsChangedListener$lambda$1(MusicController this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleLog bleLog = BleLog.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("MusicController onActiveSessionsChanged -> ");
        ArrayList arrayList = null;
        if (list != null) {
            ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                MediaController mediaController = (MediaController) it.next();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(mediaController.getPackageName());
                sb2.append(" playbackState=");
                PlaybackState playbackState = mediaController.getPlaybackState();
                sb2.append(playbackState != null ? Integer.valueOf(playbackState.getState()) : null);
                arrayList2.add(sb2.toString());
            }
            arrayList = arrayList2;
        }
        sb.append(arrayList);
        bleLog.i(sb.toString());
        this$0.autoSwitchActiveController(list, true);
    }

    private final void releaseActiveController() {
        BleLog bleLog = BleLog.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("MusicController releaseActiveController -> ");
        MediaControllerCompat mediaControllerCompat = this.mActiveMediaController;
        sb.append(mediaControllerCompat != null ? mediaControllerCompat.getPackageName() : null);
        bleLog.i(sb.toString());
        MediaControllerCompat mediaControllerCompat2 = this.mActiveMediaController;
        if (mediaControllerCompat2 != null) {
            if (mediaControllerCompat2 != null) {
                mediaControllerCompat2.unregisterCallback(this.mCallback);
            }
            this.mActiveMediaController = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMediaKeyEvent(int i) {
        MediaControllerCompat mediaControllerCompat = this.mActiveMediaController;
        if (mediaControllerCompat != null) {
            BleLog bleLog = BleLog.INSTANCE;
            bleLog.i("MusicController keyEvent " + i);
            mediaControllerCompat.dispatchMediaButtonEvent(new KeyEvent(0, i));
            mediaControllerCompat.dispatchMediaButtonEvent(new KeyEvent(1, i));
        }
    }

    @Override // com.szabh.smable3.music.MusicControllerCompat
    public void exit() {
        BleLog.INSTANCE.i("MusicController exit");
        BleConnector.INSTANCE.removeHandleCallback(this.mBleHandleCallback);
        releaseActiveController();
        super.exit();
    }

    @NotNull
    public final String keepTwoDecimal(float f) {
        if ((Float.isInfinite(f) || Float.isNaN(f)) ? false : true) {
            String format = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH)).format(new BigDecimal(String.valueOf(f)));
            Intrinsics.checkNotNullExpressionValue(format, "{\n            DecimalFor…toBigDecimal())\n        }");
            return format;
        }
        return "0.00";
    }

    @Override // com.szabh.smable3.music.MusicControllerCompat
    public void launch(@NotNull Context context) {
        List<MediaController> controllers;
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(context, "context");
        super.launch(context);
        this.mNotificationListener = getNotificationListener(context);
        BleLog bleLog = BleLog.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("MusicController launch notificationListener -> ");
        ComponentName componentName = this.mNotificationListener;
        ComponentName componentName2 = null;
        if (componentName == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mNotificationListener");
            componentName = null;
        }
        sb.append(componentName);
        bleLog.i(sb.toString());
        Object systemService = context.getSystemService("media_session");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.session.MediaSessionManager");
        MediaSessionManager mediaSessionManager = (MediaSessionManager) systemService;
        this.mMediaSessionManager = mediaSessionManager;
        if (mediaSessionManager != null) {
            ComponentName componentName3 = this.mNotificationListener;
            if (componentName3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mNotificationListener");
                componentName3 = null;
            }
            controllers = mediaSessionManager.getActiveSessions(componentName3);
        } else {
            controllers = null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("MusicController launch activeMediaControllers -> ");
        if (controllers != null) {
            Intrinsics.checkNotNullExpressionValue(controllers, "controllers");
            arrayList = new ArrayList(f.collectionSizeOrDefault(controllers, 10));
            for (MediaController mediaController : controllers) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(mediaController.getPackageName());
                sb3.append(" playbackState=");
                PlaybackState playbackState = mediaController.getPlaybackState();
                sb3.append(playbackState != null ? Integer.valueOf(playbackState.getState()) : null);
                arrayList.add(sb3.toString());
            }
        } else {
            arrayList = null;
        }
        sb2.append(arrayList);
        bleLog.i(sb2.toString());
        autoSwitchActiveController$default(this, controllers, false, 2, null);
        MediaSessionManager mediaSessionManager2 = this.mMediaSessionManager;
        if (mediaSessionManager2 != null) {
            MediaSessionManager.OnActiveSessionsChangedListener onActiveSessionsChangedListener = this.mOnActiveSessionsChangedListener;
            ComponentName componentName4 = this.mNotificationListener;
            if (componentName4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mNotificationListener");
            } else {
                componentName2 = componentName4;
            }
            mediaSessionManager2.addOnActiveSessionsChangedListener(onActiveSessionsChangedListener, componentName2);
        }
        BleConnector.INSTANCE.addHandleCallback(this.mBleHandleCallback);
    }

    public final void updateMetadata(@Nullable MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat == null) {
            return;
        }
        String string = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ARTIST);
        String str = HexStringBuilder.DEFAULT_SEPARATOR;
        if (string == null) {
            string = HexStringBuilder.DEFAULT_SEPARATOR;
        }
        String string2 = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ALBUM);
        if (string2 == null) {
            string2 = HexStringBuilder.DEFAULT_SEPARATOR;
        }
        String string3 = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_TITLE);
        if (string3 != null) {
            str = string3;
        }
        String valueOf = String.valueOf(mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_DURATION) / 1000);
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.i("MusicController updateMetadata -> METADATA_KEY_ARTIST=" + string + " , METADATA_KEY_ALBUM=" + string2 + ", METADATA_KEY_TITLE=" + str + ", METADATA_KEY_DURATION=" + valueOf);
        BleConnector bleConnector = BleConnector.INSTANCE;
        MusicEntity musicEntity = MusicEntity.TRACK;
        bleConnector.updateMusic(new BleMusicControl(musicEntity, MusicAttr.TRACK_ARTIST, string));
        bleConnector.updateMusic(new BleMusicControl(musicEntity, MusicAttr.TRACK_ALBUM, string2));
        bleConnector.updateMusic(new BleMusicControl(musicEntity, MusicAttr.TRACK_TITLE, str));
        bleConnector.updateMusic(new BleMusicControl(musicEntity, MusicAttr.TRACK_DURATION, valueOf));
        if (this.mLastVolume == -1) {
            updateVolume();
        }
    }

    public final void updatePlaybackState(@Nullable PlaybackStateCompat playbackStateCompat) {
        byte mState;
        List<MediaController> list;
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.i("MusicController updatePlaybackState -> state=" + Companion.getState(playbackStateCompat) + ' ' + playbackStateCompat);
        if (playbackStateCompat == null) {
            return;
        }
        int state = playbackStateCompat.getState();
        if (state == 1 || state == 2) {
            mState = com.szabh.smable3.entity.PlaybackState.PAUSED.getMState();
        } else if (state == 3) {
            mState = com.szabh.smable3.entity.PlaybackState.PLAYING.getMState();
        } else if (state == 4) {
            mState = com.szabh.smable3.entity.PlaybackState.FAST_FORWARDING.getMState();
        } else if (state != 5) {
            mState = com.szabh.smable3.entity.PlaybackState.UNKNOWN.getMState();
        } else {
            mState = com.szabh.smable3.entity.PlaybackState.REWINDING.getMState();
        }
        if (mState != com.szabh.smable3.entity.PlaybackState.UNKNOWN.getMState()) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(playbackStateCompat.getPlaybackSpeed())}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            StringBuilder sb = new StringBuilder();
            sb.append(',');
            sb.append(playbackStateCompat.getPosition() / 1000);
            List listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{String.valueOf((int) mState), format, sb.toString()});
            bleLog.i("MusicController updatePlaybackState -> contents=" + listOf);
            BleConnector.INSTANCE.updateMusic(new BleMusicControl(MusicEntity.PLAYER, MusicAttr.PLAYER_PLAYBACK_INFO, listOf));
            if (this.mLastVolume == -1) {
                updateVolume();
            }
        }
        if (mState != com.szabh.smable3.entity.PlaybackState.PAUSED.getMState() || getMContext() == null) {
            return;
        }
        MediaSessionManager mediaSessionManager = this.mMediaSessionManager;
        ArrayList arrayList = null;
        if (mediaSessionManager != null) {
            ComponentName componentName = this.mNotificationListener;
            if (componentName == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mNotificationListener");
                componentName = null;
            }
            list = mediaSessionManager.getActiveSessions(componentName);
        } else {
            list = null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("MusicController updatePlaybackState activeMediaControllers -> ");
        if (list != null) {
            ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(list, 10));
            for (MediaController mediaController : list) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(mediaController.getPackageName());
                sb3.append(" playbackState=");
                PlaybackState playbackState = mediaController.getPlaybackState();
                sb3.append(playbackState != null ? Integer.valueOf(playbackState.getState()) : null);
                arrayList2.add(sb3.toString());
            }
            arrayList = arrayList2;
        }
        sb2.append(arrayList);
        bleLog.i(sb2.toString());
        autoSwitchActiveController(list, true);
    }

    @Override // com.szabh.smable3.music.MusicControllerCompat
    public void updateVolume() {
        int streamVolume = getStreamVolume(3);
        int streamMaxVolume = getStreamMaxVolume(3);
        if (System.currentTimeMillis() - this.mLastVolumeTime > 150 || ((streamVolume == 0 || streamVolume == streamMaxVolume) && streamVolume != this.mLastVolume)) {
            this.mLastVolumeTime = System.currentTimeMillis();
            String keepTwoDecimal = keepTwoDecimal(streamVolume / streamMaxVolume);
            if (BleConnector.INSTANCE.updateMusic(new BleMusicControl(MusicEntity.PLAYER, MusicAttr.PLAYER_VOLUME, keepTwoDecimal))) {
                BleLog bleLog = BleLog.INSTANCE;
                bleLog.d("updateVolume -> mLastVolume=" + this.mLastVolume + " , curVolume=" + streamVolume + " , value=" + keepTwoDecimal);
                this.mLastVolume = streamVolume;
            }
        }
    }
}
