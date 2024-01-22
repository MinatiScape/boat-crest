package com.szabh.smable3.entity;

import android.text.TextUtils;
import com.bestmafen.baseble.data.BleReadable;
import com.szabh.smable3.BleKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.f;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleDeviceInfo extends BleReadable {
    public static final int AGPS_AGNSS = 6;
    public static final int AGPS_EPO = 1;
    public static final int AGPS_EPO2 = 7;
    public static final int AGPS_LTO = 8;
    public static final int AGPS_NONE = 0;
    public static final int AGPS_UBLOX = 2;
    public static final int ANTI_LOST_HIDE = 0;
    public static final int ANTI_LOST_VISIBLE = 1;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int DIGITAL_POWER_HIDE = 1;
    public static final int DIGITAL_POWER_VISIBLE = 0;
    @NotNull
    public static final String PLATFORM_GOODIX = "Goodix";
    @NotNull
    public static final String PLATFORM_JL = "JL";
    @NotNull
    public static final String PLATFORM_MTK = "MTK";
    @NotNull
    public static final String PLATFORM_NORDIC = "Nordic";
    @NotNull
    public static final String PLATFORM_REALTEK = "Realtek";
    @NotNull
    public static final String PROTOTYPE_10G = "SMA-10G";
    @NotNull
    public static final String PROTOTYPE_A7 = "A7";
    @NotNull
    public static final String PROTOTYPE_A8 = "A8";
    @NotNull
    public static final String PROTOTYPE_A8_ULTRA_PRO = "A8_Ultra_Pro";
    @NotNull
    public static final String PROTOTYPE_AM01 = "AM01";
    @NotNull
    public static final String PROTOTYPE_AM01J = "AM01J";
    @NotNull
    public static final String PROTOTYPE_AM02J = "AM02J";
    @NotNull
    public static final String PROTOTYPE_AM05 = "AM05";
    @NotNull
    public static final String PROTOTYPE_AM08 = "AM08";
    @NotNull
    public static final String PROTOTYPE_AM11 = "AM11";
    @NotNull
    public static final String PROTOTYPE_AW37 = "AW37";
    @NotNull
    public static final String PROTOTYPE_B5CRT = "SMA-B5CRT";
    @NotNull
    public static final String PROTOTYPE_B9 = "B9";
    @NotNull
    public static final String PROTOTYPE_EXPLORER = "Explorer";
    @NotNull
    public static final String PROTOTYPE_F1 = "SMA-F1";
    @NotNull
    public static final String PROTOTYPE_F11 = "F11";
    @NotNull
    public static final String PROTOTYPE_F12 = "F12";
    @NotNull
    public static final String PROTOTYPE_F12PRO = "F12Pro";
    @NotNull
    public static final String PROTOTYPE_F13 = "SMA-F13";
    @NotNull
    public static final String PROTOTYPE_F13A = "F13A";
    @NotNull
    public static final String PROTOTYPE_F13B = "F13B";
    @NotNull
    public static final String PROTOTYPE_F13J = "F13J";
    @NotNull
    public static final String PROTOTYPE_F17 = "F17";
    @NotNull
    public static final String PROTOTYPE_F1N = "SMA-F1N";
    @NotNull
    public static final String PROTOTYPE_F1RT = "SMA-F1RT";
    @NotNull
    public static final String PROTOTYPE_F1_DK = "F1";
    @NotNull
    public static final String PROTOTYPE_F2 = "SMA-F2";
    @NotNull
    public static final String PROTOTYPE_F2D = "SMA-F2D";
    @NotNull
    public static final String PROTOTYPE_F2K = "F2K";
    @NotNull
    public static final String PROTOTYPE_F2PRO = "F2Pro";
    @NotNull
    public static final String PROTOTYPE_F2R = "SMA-F2R";
    @NotNull
    public static final String PROTOTYPE_F2R_DK = "F2R";
    @NotNull
    public static final String PROTOTYPE_F3 = "F3";
    @NotNull
    public static final String PROTOTYPE_F3C = "SMA-F3C";
    @NotNull
    public static final String PROTOTYPE_F3R = "SMA-F3R";
    @NotNull
    public static final String PROTOTYPE_F3_LH = "F3-LH";
    @NotNull
    public static final String PROTOTYPE_F5 = "F5";
    @NotNull
    public static final String PROTOTYPE_F6 = "F6";
    @NotNull
    public static final String PROTOTYPE_F7 = "F7";
    @NotNull
    public static final String PROTOTYPE_FA86 = "SMA_FA_86";
    @NotNull
    public static final String PROTOTYPE_FC1 = "FC1";
    @NotNull
    public static final String PROTOTYPE_FC2 = "FC2";
    @NotNull
    public static final String PROTOTYPE_FT5 = "FT5";
    @NotNull
    public static final String PROTOTYPE_GB1 = "GB1";
    @NotNull
    public static final String PROTOTYPE_GTM5 = "SMA-GTM5";
    @NotNull
    public static final String PROTOTYPE_HW01 = "HW01";
    @NotNull
    public static final String PROTOTYPE_JX621D = "JX621D";
    @NotNull
    public static final String PROTOTYPE_K18 = "K18";
    @NotNull
    public static final String PROTOTYPE_K30 = "K30";
    @NotNull
    public static final String PROTOTYPE_LG19T = "SMA-LG19T";
    @NotNull
    public static final String PROTOTYPE_M3 = "M3";
    @NotNull
    public static final String PROTOTYPE_M4 = "M4";
    @NotNull
    public static final String PROTOTYPE_M4C = "M4C";
    @NotNull
    public static final String PROTOTYPE_M4S = "M4S";
    @NotNull
    public static final String PROTOTYPE_M5C = "M5C";
    @NotNull
    public static final String PROTOTYPE_M6 = "M6";
    @NotNull
    public static final String PROTOTYPE_M6C = "M6C";
    @NotNull
    public static final String PROTOTYPE_M7 = "M7";
    @NotNull
    public static final String PROTOTYPE_M7C = "M7C";
    @NotNull
    public static final String PROTOTYPE_M7S = "M7S";
    @NotNull
    public static final String PROTOTYPE_MATCH_S1 = "Match_S1";
    @NotNull
    public static final String PROTOTYPE_MC11 = "SMA_MC_11";
    @NotNull
    public static final String PROTOTYPE_ND08 = "SMA-ND08";
    @NotNull
    public static final String PROTOTYPE_ND09 = "SMA-ND09";
    @NotNull
    public static final String PROTOTYPE_NY58 = "NY58";
    @NotNull
    public static final String PROTOTYPE_R10 = "R10";
    @NotNull
    public static final String PROTOTYPE_R10PRO = "R10Pro";
    @NotNull
    public static final String PROTOTYPE_R11 = "R11";
    @NotNull
    public static final String PROTOTYPE_R11S = "R11S";
    @NotNull
    public static final String PROTOTYPE_R16 = "R16";
    @NotNull
    public static final String PROTOTYPE_R2 = "R2";
    @NotNull
    public static final String PROTOTYPE_R3H = "R3H";
    @NotNull
    public static final String PROTOTYPE_R3PRO = "R3Pro";
    @NotNull
    public static final String PROTOTYPE_R3Q = "R3Q";
    @NotNull
    public static final String PROTOTYPE_R4 = "SMA-R4";
    @NotNull
    public static final String PROTOTYPE_R5 = "SMA-R5";
    @NotNull
    public static final String PROTOTYPE_R6_PRO_DK = "R6_PRO_DK";
    @NotNull
    public static final String PROTOTYPE_R7 = "SMA-R7";
    @NotNull
    public static final String PROTOTYPE_R9 = "R9";
    @NotNull
    public static final String PROTOTYPE_R9J = "R9J";
    @NotNull
    public static final String PROTOTYPE_REALTEK_GTM5 = "REALTEK_GTM5";
    @NotNull
    public static final String PROTOTYPE_S03 = "SMA_S03";
    @NotNull
    public static final String PROTOTYPE_S2 = "S2";
    @NotNull
    public static final String PROTOTYPE_S4 = "S4";
    @NotNull
    public static final String PROTOTYPE_SPORT4 = "Sport4";
    @NotNull
    public static final String PROTOTYPE_SW01 = "SMA-SW01";
    @NotNull
    public static final String PROTOTYPE_T78 = "T78";
    @NotNull
    public static final String PROTOTYPE_V1 = "SMA-V1";
    @NotNull
    public static final String PROTOTYPE_V2 = "V2";
    @NotNull
    public static final String PROTOTYPE_V3 = "V3";
    @NotNull
    public static final String PROTOTYPE_V5 = "V5";
    @NotNull
    public static final String PROTOTYPE_V61 = "V61";
    @NotNull
    public static final String PROTOTYPE_W9 = "W9";
    @NotNull
    public static final String PROTOTYPE_Y1 = "Y1";
    @NotNull
    public static final String PROTOTYPE_Y2 = "Y2";
    @NotNull
    public static final String PROTOTYPE_Y3 = "Y3";
    @NotNull
    public static final String RAW_NAME_SEPARATOR = "<>";
    public static final int SUPPORT_2D_ACCELERATION_0 = 0;
    public static final int SUPPORT_2D_ACCELERATION_1 = 1;
    public static final int SUPPORT_ALIPAY_0 = 0;
    public static final int SUPPORT_ALIPAY_1 = 1;
    public static final int SUPPORT_APP_SPORT_0 = 0;
    public static final int SUPPORT_APP_SPORT_1 = 1;
    public static final int SUPPORT_BLOOD_OXYGEN_0 = 0;
    public static final int SUPPORT_BLOOD_OXYGEN_1 = 1;
    public static final int SUPPORT_CHANGE_CLASSIC_BLUETOOTH_STATE_0 = 0;
    public static final int SUPPORT_CHANGE_CLASSIC_BLUETOOTH_STATE_1 = 1;
    public static final int SUPPORT_DATE_FORMAT_0 = 0;
    public static final int SUPPORT_DATE_FORMAT_1 = 1;
    public static final int SUPPORT_DRINK_WATER_0 = 0;
    public static final int SUPPORT_DRINK_WATER_1 = 1;
    public static final int SUPPORT_FIND_WATCH_0 = 0;
    public static final int SUPPORT_FIND_WATCH_1 = 1;
    public static final int SUPPORT_HID_0 = 0;
    public static final int SUPPORT_HID_1 = 1;
    public static final int SUPPORT_HR_WARN_SET_0 = 0;
    public static final int SUPPORT_HR_WARN_SET_1 = 1;
    public static final int SUPPORT_IBEACON_SET_0 = 0;
    public static final int SUPPORT_IBEACON_SET_1 = 1;
    public static final int SUPPORT_JL_TRANSPORT_0 = 0;
    public static final int SUPPORT_JL_TRANSPORT_1 = 1;
    public static final int SUPPORT_LOVE_TAP_0 = 0;
    public static final int SUPPORT_LOVE_TAP_1 = 1;
    public static final int SUPPORT_MEDICATION_ALARM_0 = 0;
    public static final int SUPPORT_MEDICATION_ALARM_1 = 1;
    public static final int SUPPORT_MEDICATION_REMINDER_0 = 0;
    public static final int SUPPORT_MEDICATION_REMINDER_1 = 1;
    public static final int SUPPORT_NAVIGATION_0 = 0;
    public static final int SUPPORT_NAVIGATION_1 = 1;
    public static final int SUPPORT_NEWS_FEED_0 = 0;
    public static final int SUPPORT_NEWS_FEED_1 = 1;
    public static final int SUPPORT_NEW_SLEEP_ALGORITHM_0 = 0;
    public static final int SUPPORT_NEW_SLEEP_ALGORITHM_1 = 1;
    public static final int SUPPORT_NO_DISTURB_0 = 0;
    public static final int SUPPORT_NO_DISTURB_1 = 1;
    public static final int SUPPORT_POWER_SAVE_MODE_0 = 0;
    public static final int SUPPORT_POWER_SAVE_MODE_1 = 1;
    public static final int SUPPORT_QRCODE_0 = 0;
    public static final int SUPPORT_QRCODE_1 = 1;
    public static final int SUPPORT_READ_DEVICE_INFO_0 = 0;
    public static final int SUPPORT_READ_DEVICE_INFO_1 = 1;
    public static final int SUPPORT_READ_PACKAGE_STATUS_0 = 0;
    public static final int SUPPORT_READ_PACKAGE_STATUS_1 = 1;
    public static final int SUPPORT_REALTIME_MEASUREMENT_0 = 0;
    public static final int SUPPORT_REALTIME_MEASUREMENT_1 = 1;
    public static final int SUPPORT_REQUEST_REALTIME_WEATHER_0 = 0;
    public static final int SUPPORT_REQUEST_REALTIME_WEATHER_1 = 1;
    public static final int SUPPORT_SET_WATCH_PASSWORD_0 = 0;
    public static final int SUPPORT_SET_WATCH_PASSWORD_1 = 1;
    public static final int SUPPORT_SMS_QUICK_REPLY_0 = 0;
    public static final int SUPPORT_SMS_QUICK_REPLY_1 = 1;
    public static final int SUPPORT_STANDBY_SET_0 = 0;
    public static final int SUPPORT_STANDBY_SET_1 = 1;
    public static final int SUPPORT_STOCK_0 = 0;
    public static final int SUPPORT_STOCK_1 = 1;
    public static final int SUPPORT_TEMPERATURE_UNIT_0 = 0;
    public static final int SUPPORT_TEMPERATURE_UNIT_1 = 1;
    public static final int SUPPORT_TUYA_KEY_0 = 0;
    public static final int SUPPORT_TUYA_KEY_1 = 1;
    public static final int SUPPORT_VOICE_0 = 0;
    public static final int SUPPORT_VOICE_1 = 1;
    public static final int SUPPORT_WASH_0 = 0;
    public static final int SUPPORT_WASH_1 = 1;
    public static final int SUPPORT_WATCHFACE_ID_0 = 0;
    public static final int SUPPORT_WATCHFACE_ID_1 = 1;
    public static final int SUPPORT_WEATHER2_0 = 0;
    public static final int SUPPORT_WEATHER2_1 = 1;
    public static final int SUPPORT_WORLD_CLOCK_0 = 0;
    public static final int SUPPORT_WORLD_CLOCK_1 = 1;
    public static final int WATCH_0 = 0;
    public static final int WATCH_1 = 1;
    public static final int WATCH_10 = 10;
    public static final int WATCH_11 = 11;
    public static final int WATCH_12 = 12;
    public static final int WATCH_13 = 13;
    public static final int WATCH_14 = 14;
    public static final int WATCH_15 = 15;
    public static final int WATCH_16 = 16;
    public static final int WATCH_17 = 17;
    public static final int WATCH_18 = 18;
    public static final int WATCH_19 = 19;
    public static final int WATCH_2 = 2;
    public static final int WATCH_20 = 20;
    public static final int WATCH_21 = 21;
    public static final int WATCH_3 = 3;
    public static final int WATCH_4 = 4;
    public static final int WATCH_5 = 5;
    public static final int WATCH_6 = 6;
    public static final int WATCH_7 = 7;
    public static final int WATCH_8 = 8;
    public static final int WATCH_9 = 9;
    public static final int WATCH_99 = 99;
    private int mAGpsType;
    @NotNull
    private String mBleAddress;
    @NotNull
    private String mBleCustomName;
    @NotNull
    private String mBleDefaultName;
    @NotNull
    private String mBleName;
    @NotNull
    private String mClassicAddress;
    @NotNull
    private List<Integer> mDataKeys;
    @NotNull
    private String mFirmwareFlag;
    private int mHideDigitalPower;
    private long mIOBufferSize;
    private int mId;
    @NotNull
    private String mPlatform;
    @NotNull
    private String mPrototype;
    private int mShowAntiLostSwitch;
    private int mSleepAlgorithmType;
    private int mSupport2DAcceleration;
    private int mSupportAlipay;
    private int mSupportAppSport;
    private int mSupportBloodOxyGenSet;
    private int mSupportChangeClassicBluetoothState;
    private int mSupportContactSize;
    private int mSupportDateFormatSet;
    private int mSupportDrinkWaterSet;
    private int mSupportFindWatch;
    private int mSupportHID;
    private int mSupportHrWarnSet;
    private int mSupportIBeaconSet;
    private int mSupportJLTransport;
    private int mSupportLoveTap;
    private int mSupportMedicationAlarm;
    private int mSupportMedicationReminder;
    private int mSupportNavigation;
    private int mSupportNewTransportMode;
    private int mSupportNewsfeed;
    private int mSupportNoDisturbSet;
    private int mSupportPowerSaveMode;
    private int mSupportQrcode;
    private int mSupportReadDeviceInfo;
    private int mSupportReadPackageStatus;
    private int mSupportRealTimeMeasurement;
    private int mSupportRequestRealtimeWeather;
    private int mSupportSMSQuickReply;
    private int mSupportSetWatchPassword;
    private int mSupportStandbySet;
    private int mSupportStock;
    private int mSupportTemperatureUnitSet;
    private int mSupportTuyaKey;
    private int mSupportVoice;
    private int mSupportWashSet;
    private int mSupportWatchFaceId;
    private int mSupportWeather2;
    private int mSupportWorldClock;
    private int mWatchFaceType;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleDeviceInfo() {
        this(0, null, null, null, null, null, null, null, 0, 0L, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, -1, 2097151, null);
    }

    public /* synthetic */ BleDeviceInfo(int i, List list, String str, String str2, String str3, String str4, String str5, String str6, int i2, long j, int i3, String str7, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, int i31, int i32, int i33, int i34, int i35, int i36, int i37, int i38, int i39, int i40, int i41, int i42, int i43, String str8, int i44, int i45, DefaultConstructorMarker defaultConstructorMarker) {
        this((i44 & 1) != 0 ? 0 : i, (i44 & 2) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i44 & 4) != 0 ? "" : str, (i44 & 8) != 0 ? "" : str2, (i44 & 16) != 0 ? "" : str3, (i44 & 32) != 0 ? "" : str4, (i44 & 64) != 0 ? "" : str5, (i44 & 128) != 0 ? "" : str6, (i44 & 256) != 0 ? 0 : i2, (i44 & 512) != 0 ? 0L : j, (i44 & 1024) != 0 ? 0 : i3, (i44 & 2048) != 0 ? "" : str7, (i44 & 4096) != 0 ? 0 : i4, (i44 & 8192) != 0 ? 0 : i5, (i44 & 16384) != 0 ? 0 : i6, (i44 & 32768) != 0 ? 0 : i7, (i44 & 65536) != 0 ? 0 : i8, (i44 & 131072) != 0 ? 0 : i9, (i44 & 262144) != 0 ? 0 : i10, (i44 & 524288) != 0 ? 0 : i11, (i44 & 1048576) != 0 ? 0 : i12, (i44 & 2097152) != 0 ? 0 : i13, (i44 & 4194304) != 0 ? 0 : i14, (i44 & 8388608) != 0 ? 0 : i15, (i44 & 16777216) != 0 ? 0 : i16, (i44 & 33554432) != 0 ? 0 : i17, (i44 & 67108864) != 0 ? 0 : i18, (i44 & 134217728) != 0 ? 0 : i19, (i44 & 268435456) != 0 ? 0 : i20, (i44 & PKIFailureInfo.duplicateCertReq) != 0 ? 0 : i21, (i44 & 1073741824) != 0 ? 0 : i22, (i44 & Integer.MIN_VALUE) != 0 ? 0 : i23, (i45 & 1) != 0 ? 0 : i24, (i45 & 2) != 0 ? 0 : i25, (i45 & 4) != 0 ? 0 : i26, (i45 & 8) != 0 ? 0 : i27, (i45 & 16) != 0 ? 0 : i28, (i45 & 32) != 0 ? 0 : i29, (i45 & 64) != 0 ? 0 : i30, (i45 & 128) != 0 ? 0 : i31, (i45 & 256) != 0 ? 0 : i32, (i45 & 512) != 0 ? 0 : i33, (i45 & 1024) != 0 ? 0 : i34, (i45 & 2048) != 0 ? 0 : i35, (i45 & 4096) != 0 ? 0 : i36, (i45 & 8192) != 0 ? 0 : i37, (i45 & 16384) != 0 ? 0 : i38, (i45 & 32768) != 0 ? 0 : i39, (i45 & 65536) != 0 ? 0 : i40, (i45 & 131072) != 0 ? 0 : i41, (i45 & 262144) != 0 ? 0 : i42, (i45 & 524288) != 0 ? 0 : i43, (i45 & 1048576) != 0 ? "" : str8);
    }

    private final String component53() {
        return this.mBleDefaultName;
    }

    public final int component1() {
        return this.mId;
    }

    public final long component10() {
        return this.mIOBufferSize;
    }

    public final int component11() {
        return this.mWatchFaceType;
    }

    @NotNull
    public final String component12() {
        return this.mClassicAddress;
    }

    public final int component13() {
        return this.mHideDigitalPower;
    }

    public final int component14() {
        return this.mShowAntiLostSwitch;
    }

    public final int component15() {
        return this.mSleepAlgorithmType;
    }

    public final int component16() {
        return this.mSupportDateFormatSet;
    }

    public final int component17() {
        return this.mSupportReadDeviceInfo;
    }

    public final int component18() {
        return this.mSupportTemperatureUnitSet;
    }

    public final int component19() {
        return this.mSupportDrinkWaterSet;
    }

    @NotNull
    public final List<Integer> component2() {
        return this.mDataKeys;
    }

    public final int component20() {
        return this.mSupportChangeClassicBluetoothState;
    }

    public final int component21() {
        return this.mSupportAppSport;
    }

    public final int component22() {
        return this.mSupportBloodOxyGenSet;
    }

    public final int component23() {
        return this.mSupportWashSet;
    }

    public final int component24() {
        return this.mSupportRequestRealtimeWeather;
    }

    public final int component25() {
        return this.mSupportHID;
    }

    public final int component26() {
        return this.mSupportIBeaconSet;
    }

    public final int component27() {
        return this.mSupportWatchFaceId;
    }

    public final int component28() {
        return this.mSupportNewTransportMode;
    }

    public final int component29() {
        return this.mSupportJLTransport;
    }

    @NotNull
    public final String component3() {
        return this.mBleName;
    }

    public final int component30() {
        return this.mSupportFindWatch;
    }

    public final int component31() {
        return this.mSupportWorldClock;
    }

    public final int component32() {
        return this.mSupportStock;
    }

    public final int component33() {
        return this.mSupportSMSQuickReply;
    }

    public final int component34() {
        return this.mSupportNoDisturbSet;
    }

    public final int component35() {
        return this.mSupportSetWatchPassword;
    }

    public final int component36() {
        return this.mSupportRealTimeMeasurement;
    }

    public final int component37() {
        return this.mSupportPowerSaveMode;
    }

    public final int component38() {
        return this.mSupportLoveTap;
    }

    public final int component39() {
        return this.mSupportNewsfeed;
    }

    @NotNull
    public final String component4() {
        return this.mBleCustomName;
    }

    public final int component40() {
        return this.mSupportMedicationReminder;
    }

    public final int component41() {
        return this.mSupportQrcode;
    }

    public final int component42() {
        return this.mSupportWeather2;
    }

    public final int component43() {
        return this.mSupportAlipay;
    }

    public final int component44() {
        return this.mSupportStandbySet;
    }

    public final int component45() {
        return this.mSupport2DAcceleration;
    }

    public final int component46() {
        return this.mSupportTuyaKey;
    }

    public final int component47() {
        return this.mSupportMedicationAlarm;
    }

    public final int component48() {
        return this.mSupportReadPackageStatus;
    }

    public final int component49() {
        return this.mSupportContactSize;
    }

    @NotNull
    public final String component5() {
        return this.mBleAddress;
    }

    public final int component50() {
        return this.mSupportVoice;
    }

    public final int component51() {
        return this.mSupportNavigation;
    }

    public final int component52() {
        return this.mSupportHrWarnSet;
    }

    @NotNull
    public final String component6() {
        return this.mPlatform;
    }

    @NotNull
    public final String component7() {
        return this.mPrototype;
    }

    @NotNull
    public final String component8() {
        return this.mFirmwareFlag;
    }

    public final int component9() {
        return this.mAGpsType;
    }

    @NotNull
    public final BleDeviceInfo copy(int i, @NotNull List<Integer> mDataKeys, @NotNull String mBleName, @NotNull String mBleCustomName, @NotNull String mBleAddress, @NotNull String mPlatform, @NotNull String mPrototype, @NotNull String mFirmwareFlag, int i2, long j, int i3, @NotNull String mClassicAddress, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, int i31, int i32, int i33, int i34, int i35, int i36, int i37, int i38, int i39, int i40, int i41, int i42, int i43, @NotNull String mBleDefaultName) {
        Intrinsics.checkNotNullParameter(mDataKeys, "mDataKeys");
        Intrinsics.checkNotNullParameter(mBleName, "mBleName");
        Intrinsics.checkNotNullParameter(mBleCustomName, "mBleCustomName");
        Intrinsics.checkNotNullParameter(mBleAddress, "mBleAddress");
        Intrinsics.checkNotNullParameter(mPlatform, "mPlatform");
        Intrinsics.checkNotNullParameter(mPrototype, "mPrototype");
        Intrinsics.checkNotNullParameter(mFirmwareFlag, "mFirmwareFlag");
        Intrinsics.checkNotNullParameter(mClassicAddress, "mClassicAddress");
        Intrinsics.checkNotNullParameter(mBleDefaultName, "mBleDefaultName");
        return new BleDeviceInfo(i, mDataKeys, mBleName, mBleCustomName, mBleAddress, mPlatform, mPrototype, mFirmwareFlag, i2, j, i3, mClassicAddress, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, i28, i29, i30, i31, i32, i33, i34, i35, i36, i37, i38, i39, i40, i41, i42, i43, mBleDefaultName);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mId = BleReadable.readInt32$default(this, null, 1, null);
        List<List> chunked = CollectionsKt___CollectionsKt.chunked(ArraysKt___ArraysKt.toList(readBytesUtil((byte) 0)), 2);
        ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(chunked, 10));
        for (List list : chunked) {
            arrayList.add(Integer.valueOf((((Number) list.get(1)).byteValue() & 255) | ((((Number) list.get(0)).byteValue() & 255) << 8)));
        }
        this.mDataKeys = arrayList;
        this.mBleName = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        String readStringUtil$default = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String upperCase = readStringUtil$default.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        this.mBleAddress = upperCase;
        this.mPlatform = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        this.mPrototype = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        this.mFirmwareFlag = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        this.mAGpsType = readInt8();
        this.mIOBufferSize = BleReadable.readUInt16$default(this, null, 1, null);
        this.mWatchFaceType = readInt8();
        String readStringUtil$default2 = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        Locale locale2 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
        String upperCase2 = readStringUtil$default2.toUpperCase(locale2);
        Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(locale)");
        this.mClassicAddress = upperCase2;
        this.mHideDigitalPower = readInt8();
        this.mShowAntiLostSwitch = readInt8();
        this.mSleepAlgorithmType = readInt8();
        this.mSupportDateFormatSet = readInt8();
        this.mSupportReadDeviceInfo = readInt8();
        this.mSupportTemperatureUnitSet = readInt8();
        this.mSupportDrinkWaterSet = readInt8();
        this.mSupportChangeClassicBluetoothState = readInt8();
        this.mSupportAppSport = readInt8();
        this.mSupportBloodOxyGenSet = readInt8();
        this.mSupportWashSet = readInt8();
        this.mSupportRequestRealtimeWeather = readInt8();
        this.mSupportHID = readInt8();
        this.mSupportIBeaconSet = readInt8();
        this.mSupportWatchFaceId = readInt8();
        this.mSupportNewTransportMode = readInt8();
        this.mSupportJLTransport = readInt8();
        this.mSupportFindWatch = readInt8();
        this.mSupportWorldClock = readInt8();
        this.mSupportStock = readInt8();
        this.mSupportSMSQuickReply = readInt8();
        this.mSupportNoDisturbSet = readInt8();
        this.mSupportSetWatchPassword = readInt8();
        this.mSupportRealTimeMeasurement = readInt8();
        this.mSupportPowerSaveMode = readInt8();
        this.mSupportLoveTap = readInt8();
        this.mSupportNewsfeed = readInt8();
        this.mSupportMedicationReminder = readInt8();
        this.mSupportQrcode = readInt8();
        this.mSupportWeather2 = readInt8();
        this.mSupportAlipay = readInt8();
        this.mSupportStandbySet = readInt8();
        this.mSupport2DAcceleration = readInt8();
        this.mSupportTuyaKey = readInt8();
        this.mSupportMedicationAlarm = readInt8();
        this.mSupportReadPackageStatus = readInt8();
        this.mSupportContactSize = readInt8() * 10;
        this.mSupportVoice = readInt8();
        this.mSupportNavigation = readInt8();
        this.mSupportHrWarnSet = readInt8();
        if (StringsKt__StringsKt.contains$default((CharSequence) this.mFirmwareFlag, (CharSequence) RAW_NAME_SEPARATOR, false, 2, (Object) null)) {
            String str = (String) StringsKt__StringsKt.split$default((CharSequence) this.mFirmwareFlag, new String[]{RAW_NAME_SEPARATOR}, false, 0, 6, (Object) null).get(1);
            if (!TextUtils.isEmpty(str) && !Intrinsics.areEqual(str, this.mBleName)) {
                this.mBleCustomName = this.mBleName;
                this.mBleName = str;
            }
        }
        String readStringUtil$default3 = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        this.mBleDefaultName = readStringUtil$default3;
        if (TextUtils.isEmpty(readStringUtil$default3)) {
            return;
        }
        this.mBleCustomName = this.mBleName;
        this.mBleName = this.mBleDefaultName;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleDeviceInfo) {
            BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) obj;
            return this.mId == bleDeviceInfo.mId && Intrinsics.areEqual(this.mDataKeys, bleDeviceInfo.mDataKeys) && Intrinsics.areEqual(this.mBleName, bleDeviceInfo.mBleName) && Intrinsics.areEqual(this.mBleCustomName, bleDeviceInfo.mBleCustomName) && Intrinsics.areEqual(this.mBleAddress, bleDeviceInfo.mBleAddress) && Intrinsics.areEqual(this.mPlatform, bleDeviceInfo.mPlatform) && Intrinsics.areEqual(this.mPrototype, bleDeviceInfo.mPrototype) && Intrinsics.areEqual(this.mFirmwareFlag, bleDeviceInfo.mFirmwareFlag) && this.mAGpsType == bleDeviceInfo.mAGpsType && this.mIOBufferSize == bleDeviceInfo.mIOBufferSize && this.mWatchFaceType == bleDeviceInfo.mWatchFaceType && Intrinsics.areEqual(this.mClassicAddress, bleDeviceInfo.mClassicAddress) && this.mHideDigitalPower == bleDeviceInfo.mHideDigitalPower && this.mShowAntiLostSwitch == bleDeviceInfo.mShowAntiLostSwitch && this.mSleepAlgorithmType == bleDeviceInfo.mSleepAlgorithmType && this.mSupportDateFormatSet == bleDeviceInfo.mSupportDateFormatSet && this.mSupportReadDeviceInfo == bleDeviceInfo.mSupportReadDeviceInfo && this.mSupportTemperatureUnitSet == bleDeviceInfo.mSupportTemperatureUnitSet && this.mSupportDrinkWaterSet == bleDeviceInfo.mSupportDrinkWaterSet && this.mSupportChangeClassicBluetoothState == bleDeviceInfo.mSupportChangeClassicBluetoothState && this.mSupportAppSport == bleDeviceInfo.mSupportAppSport && this.mSupportBloodOxyGenSet == bleDeviceInfo.mSupportBloodOxyGenSet && this.mSupportWashSet == bleDeviceInfo.mSupportWashSet && this.mSupportRequestRealtimeWeather == bleDeviceInfo.mSupportRequestRealtimeWeather && this.mSupportHID == bleDeviceInfo.mSupportHID && this.mSupportIBeaconSet == bleDeviceInfo.mSupportIBeaconSet && this.mSupportWatchFaceId == bleDeviceInfo.mSupportWatchFaceId && this.mSupportNewTransportMode == bleDeviceInfo.mSupportNewTransportMode && this.mSupportJLTransport == bleDeviceInfo.mSupportJLTransport && this.mSupportFindWatch == bleDeviceInfo.mSupportFindWatch && this.mSupportWorldClock == bleDeviceInfo.mSupportWorldClock && this.mSupportStock == bleDeviceInfo.mSupportStock && this.mSupportSMSQuickReply == bleDeviceInfo.mSupportSMSQuickReply && this.mSupportNoDisturbSet == bleDeviceInfo.mSupportNoDisturbSet && this.mSupportSetWatchPassword == bleDeviceInfo.mSupportSetWatchPassword && this.mSupportRealTimeMeasurement == bleDeviceInfo.mSupportRealTimeMeasurement && this.mSupportPowerSaveMode == bleDeviceInfo.mSupportPowerSaveMode && this.mSupportLoveTap == bleDeviceInfo.mSupportLoveTap && this.mSupportNewsfeed == bleDeviceInfo.mSupportNewsfeed && this.mSupportMedicationReminder == bleDeviceInfo.mSupportMedicationReminder && this.mSupportQrcode == bleDeviceInfo.mSupportQrcode && this.mSupportWeather2 == bleDeviceInfo.mSupportWeather2 && this.mSupportAlipay == bleDeviceInfo.mSupportAlipay && this.mSupportStandbySet == bleDeviceInfo.mSupportStandbySet && this.mSupport2DAcceleration == bleDeviceInfo.mSupport2DAcceleration && this.mSupportTuyaKey == bleDeviceInfo.mSupportTuyaKey && this.mSupportMedicationAlarm == bleDeviceInfo.mSupportMedicationAlarm && this.mSupportReadPackageStatus == bleDeviceInfo.mSupportReadPackageStatus && this.mSupportContactSize == bleDeviceInfo.mSupportContactSize && this.mSupportVoice == bleDeviceInfo.mSupportVoice && this.mSupportNavigation == bleDeviceInfo.mSupportNavigation && this.mSupportHrWarnSet == bleDeviceInfo.mSupportHrWarnSet && Intrinsics.areEqual(this.mBleDefaultName, bleDeviceInfo.mBleDefaultName);
        }
        return false;
    }

    public final int getMAGpsType() {
        return this.mAGpsType;
    }

    @NotNull
    public final String getMBleAddress() {
        return this.mBleAddress;
    }

    @NotNull
    public final String getMBleCustomName() {
        return this.mBleCustomName;
    }

    @NotNull
    public final String getMBleName() {
        return this.mBleName;
    }

    @NotNull
    public final String getMClassicAddress() {
        return this.mClassicAddress;
    }

    @NotNull
    public final List<Integer> getMDataKeys() {
        return this.mDataKeys;
    }

    @NotNull
    public final String getMFirmwareFlag() {
        return this.mFirmwareFlag;
    }

    public final int getMHideDigitalPower() {
        return this.mHideDigitalPower;
    }

    public final long getMIOBufferSize() {
        return this.mIOBufferSize;
    }

    public final int getMId() {
        return this.mId;
    }

    @NotNull
    public final String getMPlatform() {
        return this.mPlatform;
    }

    @NotNull
    public final String getMPrototype() {
        return this.mPrototype;
    }

    public final int getMShowAntiLostSwitch() {
        return this.mShowAntiLostSwitch;
    }

    public final int getMSleepAlgorithmType() {
        return this.mSleepAlgorithmType;
    }

    public final int getMSupport2DAcceleration() {
        return this.mSupport2DAcceleration;
    }

    public final int getMSupportAlipay() {
        return this.mSupportAlipay;
    }

    public final int getMSupportAppSport() {
        return this.mSupportAppSport;
    }

    public final int getMSupportBloodOxyGenSet() {
        return this.mSupportBloodOxyGenSet;
    }

    public final int getMSupportChangeClassicBluetoothState() {
        return this.mSupportChangeClassicBluetoothState;
    }

    public final int getMSupportContactSize() {
        return this.mSupportContactSize;
    }

    public final int getMSupportDateFormatSet() {
        return this.mSupportDateFormatSet;
    }

    public final int getMSupportDrinkWaterSet() {
        return this.mSupportDrinkWaterSet;
    }

    public final int getMSupportFindWatch() {
        return this.mSupportFindWatch;
    }

    public final int getMSupportHID() {
        return this.mSupportHID;
    }

    public final int getMSupportHrWarnSet() {
        return this.mSupportHrWarnSet;
    }

    public final int getMSupportIBeaconSet() {
        return this.mSupportIBeaconSet;
    }

    public final int getMSupportJLTransport() {
        return this.mSupportJLTransport;
    }

    public final int getMSupportLoveTap() {
        return this.mSupportLoveTap;
    }

    public final int getMSupportMedicationAlarm() {
        return this.mSupportMedicationAlarm;
    }

    public final int getMSupportMedicationReminder() {
        return this.mSupportMedicationReminder;
    }

    public final int getMSupportNavigation() {
        return this.mSupportNavigation;
    }

    public final int getMSupportNewTransportMode() {
        return this.mSupportNewTransportMode;
    }

    public final int getMSupportNewsfeed() {
        return this.mSupportNewsfeed;
    }

    public final int getMSupportNoDisturbSet() {
        return this.mSupportNoDisturbSet;
    }

    public final int getMSupportPowerSaveMode() {
        return this.mSupportPowerSaveMode;
    }

    public final int getMSupportQrcode() {
        return this.mSupportQrcode;
    }

    public final int getMSupportReadDeviceInfo() {
        return this.mSupportReadDeviceInfo;
    }

    public final int getMSupportReadPackageStatus() {
        return this.mSupportReadPackageStatus;
    }

    public final int getMSupportRealTimeMeasurement() {
        return this.mSupportRealTimeMeasurement;
    }

    public final int getMSupportRequestRealtimeWeather() {
        return this.mSupportRequestRealtimeWeather;
    }

    public final int getMSupportSMSQuickReply() {
        return this.mSupportSMSQuickReply;
    }

    public final int getMSupportSetWatchPassword() {
        return this.mSupportSetWatchPassword;
    }

    public final int getMSupportStandbySet() {
        return this.mSupportStandbySet;
    }

    public final int getMSupportStock() {
        return this.mSupportStock;
    }

    public final int getMSupportTemperatureUnitSet() {
        return this.mSupportTemperatureUnitSet;
    }

    public final int getMSupportTuyaKey() {
        return this.mSupportTuyaKey;
    }

    public final int getMSupportVoice() {
        return this.mSupportVoice;
    }

    public final int getMSupportWashSet() {
        return this.mSupportWashSet;
    }

    public final int getMSupportWatchFaceId() {
        return this.mSupportWatchFaceId;
    }

    public final int getMSupportWeather2() {
        return this.mSupportWeather2;
    }

    public final int getMSupportWorldClock() {
        return this.mSupportWorldClock;
    }

    public final int getMWatchFaceType() {
        return this.mWatchFaceType;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((Integer.hashCode(this.mId) * 31) + this.mDataKeys.hashCode()) * 31) + this.mBleName.hashCode()) * 31) + this.mBleCustomName.hashCode()) * 31) + this.mBleAddress.hashCode()) * 31) + this.mPlatform.hashCode()) * 31) + this.mPrototype.hashCode()) * 31) + this.mFirmwareFlag.hashCode()) * 31) + Integer.hashCode(this.mAGpsType)) * 31) + Long.hashCode(this.mIOBufferSize)) * 31) + Integer.hashCode(this.mWatchFaceType)) * 31) + this.mClassicAddress.hashCode()) * 31) + Integer.hashCode(this.mHideDigitalPower)) * 31) + Integer.hashCode(this.mShowAntiLostSwitch)) * 31) + Integer.hashCode(this.mSleepAlgorithmType)) * 31) + Integer.hashCode(this.mSupportDateFormatSet)) * 31) + Integer.hashCode(this.mSupportReadDeviceInfo)) * 31) + Integer.hashCode(this.mSupportTemperatureUnitSet)) * 31) + Integer.hashCode(this.mSupportDrinkWaterSet)) * 31) + Integer.hashCode(this.mSupportChangeClassicBluetoothState)) * 31) + Integer.hashCode(this.mSupportAppSport)) * 31) + Integer.hashCode(this.mSupportBloodOxyGenSet)) * 31) + Integer.hashCode(this.mSupportWashSet)) * 31) + Integer.hashCode(this.mSupportRequestRealtimeWeather)) * 31) + Integer.hashCode(this.mSupportHID)) * 31) + Integer.hashCode(this.mSupportIBeaconSet)) * 31) + Integer.hashCode(this.mSupportWatchFaceId)) * 31) + Integer.hashCode(this.mSupportNewTransportMode)) * 31) + Integer.hashCode(this.mSupportJLTransport)) * 31) + Integer.hashCode(this.mSupportFindWatch)) * 31) + Integer.hashCode(this.mSupportWorldClock)) * 31) + Integer.hashCode(this.mSupportStock)) * 31) + Integer.hashCode(this.mSupportSMSQuickReply)) * 31) + Integer.hashCode(this.mSupportNoDisturbSet)) * 31) + Integer.hashCode(this.mSupportSetWatchPassword)) * 31) + Integer.hashCode(this.mSupportRealTimeMeasurement)) * 31) + Integer.hashCode(this.mSupportPowerSaveMode)) * 31) + Integer.hashCode(this.mSupportLoveTap)) * 31) + Integer.hashCode(this.mSupportNewsfeed)) * 31) + Integer.hashCode(this.mSupportMedicationReminder)) * 31) + Integer.hashCode(this.mSupportQrcode)) * 31) + Integer.hashCode(this.mSupportWeather2)) * 31) + Integer.hashCode(this.mSupportAlipay)) * 31) + Integer.hashCode(this.mSupportStandbySet)) * 31) + Integer.hashCode(this.mSupport2DAcceleration)) * 31) + Integer.hashCode(this.mSupportTuyaKey)) * 31) + Integer.hashCode(this.mSupportMedicationAlarm)) * 31) + Integer.hashCode(this.mSupportReadPackageStatus)) * 31) + Integer.hashCode(this.mSupportContactSize)) * 31) + Integer.hashCode(this.mSupportVoice)) * 31) + Integer.hashCode(this.mSupportNavigation)) * 31) + Integer.hashCode(this.mSupportHrWarnSet)) * 31) + this.mBleDefaultName.hashCode();
    }

    public final void setMAGpsType(int i) {
        this.mAGpsType = i;
    }

    public final void setMBleAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mBleAddress = str;
    }

    public final void setMBleCustomName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mBleCustomName = str;
    }

    public final void setMBleName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mBleName = str;
    }

    public final void setMClassicAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mClassicAddress = str;
    }

    public final void setMDataKeys(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mDataKeys = list;
    }

    public final void setMFirmwareFlag(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mFirmwareFlag = str;
    }

    public final void setMHideDigitalPower(int i) {
        this.mHideDigitalPower = i;
    }

    public final void setMIOBufferSize(long j) {
        this.mIOBufferSize = j;
    }

    public final void setMId(int i) {
        this.mId = i;
    }

    public final void setMPlatform(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPlatform = str;
    }

    public final void setMPrototype(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPrototype = str;
    }

    public final void setMShowAntiLostSwitch(int i) {
        this.mShowAntiLostSwitch = i;
    }

    public final void setMSleepAlgorithmType(int i) {
        this.mSleepAlgorithmType = i;
    }

    public final void setMSupport2DAcceleration(int i) {
        this.mSupport2DAcceleration = i;
    }

    public final void setMSupportAlipay(int i) {
        this.mSupportAlipay = i;
    }

    public final void setMSupportAppSport(int i) {
        this.mSupportAppSport = i;
    }

    public final void setMSupportBloodOxyGenSet(int i) {
        this.mSupportBloodOxyGenSet = i;
    }

    public final void setMSupportChangeClassicBluetoothState(int i) {
        this.mSupportChangeClassicBluetoothState = i;
    }

    public final void setMSupportContactSize(int i) {
        this.mSupportContactSize = i;
    }

    public final void setMSupportDateFormatSet(int i) {
        this.mSupportDateFormatSet = i;
    }

    public final void setMSupportDrinkWaterSet(int i) {
        this.mSupportDrinkWaterSet = i;
    }

    public final void setMSupportFindWatch(int i) {
        this.mSupportFindWatch = i;
    }

    public final void setMSupportHID(int i) {
        this.mSupportHID = i;
    }

    public final void setMSupportHrWarnSet(int i) {
        this.mSupportHrWarnSet = i;
    }

    public final void setMSupportIBeaconSet(int i) {
        this.mSupportIBeaconSet = i;
    }

    public final void setMSupportJLTransport(int i) {
        this.mSupportJLTransport = i;
    }

    public final void setMSupportLoveTap(int i) {
        this.mSupportLoveTap = i;
    }

    public final void setMSupportMedicationAlarm(int i) {
        this.mSupportMedicationAlarm = i;
    }

    public final void setMSupportMedicationReminder(int i) {
        this.mSupportMedicationReminder = i;
    }

    public final void setMSupportNavigation(int i) {
        this.mSupportNavigation = i;
    }

    public final void setMSupportNewTransportMode(int i) {
        this.mSupportNewTransportMode = i;
    }

    public final void setMSupportNewsfeed(int i) {
        this.mSupportNewsfeed = i;
    }

    public final void setMSupportNoDisturbSet(int i) {
        this.mSupportNoDisturbSet = i;
    }

    public final void setMSupportPowerSaveMode(int i) {
        this.mSupportPowerSaveMode = i;
    }

    public final void setMSupportQrcode(int i) {
        this.mSupportQrcode = i;
    }

    public final void setMSupportReadDeviceInfo(int i) {
        this.mSupportReadDeviceInfo = i;
    }

    public final void setMSupportReadPackageStatus(int i) {
        this.mSupportReadPackageStatus = i;
    }

    public final void setMSupportRealTimeMeasurement(int i) {
        this.mSupportRealTimeMeasurement = i;
    }

    public final void setMSupportRequestRealtimeWeather(int i) {
        this.mSupportRequestRealtimeWeather = i;
    }

    public final void setMSupportSMSQuickReply(int i) {
        this.mSupportSMSQuickReply = i;
    }

    public final void setMSupportSetWatchPassword(int i) {
        this.mSupportSetWatchPassword = i;
    }

    public final void setMSupportStandbySet(int i) {
        this.mSupportStandbySet = i;
    }

    public final void setMSupportStock(int i) {
        this.mSupportStock = i;
    }

    public final void setMSupportTemperatureUnitSet(int i) {
        this.mSupportTemperatureUnitSet = i;
    }

    public final void setMSupportTuyaKey(int i) {
        this.mSupportTuyaKey = i;
    }

    public final void setMSupportVoice(int i) {
        this.mSupportVoice = i;
    }

    public final void setMSupportWashSet(int i) {
        this.mSupportWashSet = i;
    }

    public final void setMSupportWatchFaceId(int i) {
        this.mSupportWatchFaceId = i;
    }

    public final void setMSupportWeather2(int i) {
        this.mSupportWeather2 = i;
    }

    public final void setMSupportWorldClock(int i) {
        this.mSupportWorldClock = i;
    }

    public final void setMWatchFaceType(int i) {
        this.mWatchFaceType = i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BleDeviceInfo(mId=");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("0x%08X", Arrays.copyOf(new Object[]{Integer.valueOf(this.mId)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        sb.append(format);
        sb.append(", mDataKeys=");
        List<Integer> list = this.mDataKeys;
        ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
        for (Number number : list) {
            arrayList.add(BleKey.Companion.of(number.intValue()));
        }
        sb.append(arrayList);
        sb.append(", mBleName='");
        sb.append(this.mBleName);
        sb.append("', mBleCustomName='");
        sb.append(this.mBleCustomName);
        sb.append("', mBleAddress='");
        sb.append(this.mBleAddress);
        sb.append("', mPlatform='");
        sb.append(this.mPlatform);
        sb.append("', mPrototype='");
        sb.append(this.mPrototype);
        sb.append("', mFirmwareFlag='");
        sb.append(this.mFirmwareFlag);
        sb.append("', mAGpsType=");
        sb.append(this.mAGpsType);
        sb.append(", mIOBufferSize=");
        sb.append(this.mIOBufferSize);
        sb.append(", mWatchFaceType=");
        sb.append(this.mWatchFaceType);
        sb.append(", mClassicAddress='");
        sb.append(this.mClassicAddress);
        sb.append("', mHideDigitalPower=");
        sb.append(this.mHideDigitalPower);
        sb.append(", mShowAntiLostSwitch=");
        sb.append(this.mShowAntiLostSwitch);
        sb.append(", mSleepAlgorithmType=");
        sb.append(this.mSleepAlgorithmType);
        sb.append(", mSupportDateFormatSet=");
        sb.append(this.mSupportDateFormatSet);
        sb.append(", mSupportReadDeviceInfo=");
        sb.append(this.mSupportReadDeviceInfo);
        sb.append(", mSupportTemperatureUnitSet=");
        sb.append(this.mSupportTemperatureUnitSet);
        sb.append(", mSupportDrinkWaterSet=");
        sb.append(this.mSupportDrinkWaterSet);
        sb.append(", mSupportChangeClassicBluetoothState=");
        sb.append(this.mSupportChangeClassicBluetoothState);
        sb.append(", mSupportAppSport=");
        sb.append(this.mSupportAppSport);
        sb.append(", mSupportBloodOxyGenSet=");
        sb.append(this.mSupportBloodOxyGenSet);
        sb.append(", mSupportWashSet=");
        sb.append(this.mSupportWashSet);
        sb.append(", mSupportRequestRealtimeWeather=");
        sb.append(this.mSupportRequestRealtimeWeather);
        sb.append(", mSupportHID=");
        sb.append(this.mSupportHID);
        sb.append(", mSupportIBeaconSet=");
        sb.append(this.mSupportIBeaconSet);
        sb.append(", mSupportWatchFaceId=");
        sb.append(this.mSupportWatchFaceId);
        sb.append(", mSupportNewTransportMode=");
        sb.append(this.mSupportNewTransportMode);
        sb.append(", mSupportJLTransport=");
        sb.append(this.mSupportJLTransport);
        sb.append(", mSupportFindWatch=");
        sb.append(this.mSupportFindWatch);
        sb.append(", mSupportWorldClock=");
        sb.append(this.mSupportWorldClock);
        sb.append(", mSupportStock=");
        sb.append(this.mSupportStock);
        sb.append(", mSupportSMSQuickReply=");
        sb.append(this.mSupportSMSQuickReply);
        sb.append(", mSupportNoDisturbSet=");
        sb.append(this.mSupportNoDisturbSet);
        sb.append(", mSupportSetWatchPassword=");
        sb.append(this.mSupportSetWatchPassword);
        sb.append(", mSupportRealTimeMeasurement=");
        sb.append(this.mSupportRealTimeMeasurement);
        sb.append(", mSupportPowerSaveMode=");
        sb.append(this.mSupportPowerSaveMode);
        sb.append(", mSupportLoveTap=");
        sb.append(this.mSupportLoveTap);
        sb.append(", mSupportNewsfeed=");
        sb.append(this.mSupportNewsfeed);
        sb.append(", mSupportMedicationReminder=");
        sb.append(this.mSupportMedicationReminder);
        sb.append(", mSupportQrcode=");
        sb.append(this.mSupportQrcode);
        sb.append(", mSupportWeather2=");
        sb.append(this.mSupportWeather2);
        sb.append(", mSupportAlipay=");
        sb.append(this.mSupportAlipay);
        sb.append(", mSupportStandbySet=");
        sb.append(this.mSupportStandbySet);
        sb.append(", mSupport2DAcceleration=");
        sb.append(this.mSupport2DAcceleration);
        sb.append(", mSupportTuyaKey=");
        sb.append(this.mSupportTuyaKey);
        sb.append(", mSupportMedicationAlarm=");
        sb.append(this.mSupportMedicationAlarm);
        sb.append(", mSupportReadPackageStatus=");
        sb.append(this.mSupportReadPackageStatus);
        sb.append(", mSupportContactSize=");
        sb.append(this.mSupportContactSize);
        sb.append(", mSupportVoice=");
        sb.append(this.mSupportVoice);
        sb.append(", mSupportNavigation=");
        sb.append(this.mSupportNavigation);
        sb.append(", mSupportHrWarnSet=");
        sb.append(this.mSupportHrWarnSet);
        sb.append(", mBleDefaultName='");
        sb.append(this.mBleDefaultName);
        sb.append("')");
        return sb.toString();
    }

    public BleDeviceInfo(int i, @NotNull List<Integer> mDataKeys, @NotNull String mBleName, @NotNull String mBleCustomName, @NotNull String mBleAddress, @NotNull String mPlatform, @NotNull String mPrototype, @NotNull String mFirmwareFlag, int i2, long j, int i3, @NotNull String mClassicAddress, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, int i31, int i32, int i33, int i34, int i35, int i36, int i37, int i38, int i39, int i40, int i41, int i42, int i43, @NotNull String mBleDefaultName) {
        Intrinsics.checkNotNullParameter(mDataKeys, "mDataKeys");
        Intrinsics.checkNotNullParameter(mBleName, "mBleName");
        Intrinsics.checkNotNullParameter(mBleCustomName, "mBleCustomName");
        Intrinsics.checkNotNullParameter(mBleAddress, "mBleAddress");
        Intrinsics.checkNotNullParameter(mPlatform, "mPlatform");
        Intrinsics.checkNotNullParameter(mPrototype, "mPrototype");
        Intrinsics.checkNotNullParameter(mFirmwareFlag, "mFirmwareFlag");
        Intrinsics.checkNotNullParameter(mClassicAddress, "mClassicAddress");
        Intrinsics.checkNotNullParameter(mBleDefaultName, "mBleDefaultName");
        this.mId = i;
        this.mDataKeys = mDataKeys;
        this.mBleName = mBleName;
        this.mBleCustomName = mBleCustomName;
        this.mBleAddress = mBleAddress;
        this.mPlatform = mPlatform;
        this.mPrototype = mPrototype;
        this.mFirmwareFlag = mFirmwareFlag;
        this.mAGpsType = i2;
        this.mIOBufferSize = j;
        this.mWatchFaceType = i3;
        this.mClassicAddress = mClassicAddress;
        this.mHideDigitalPower = i4;
        this.mShowAntiLostSwitch = i5;
        this.mSleepAlgorithmType = i6;
        this.mSupportDateFormatSet = i7;
        this.mSupportReadDeviceInfo = i8;
        this.mSupportTemperatureUnitSet = i9;
        this.mSupportDrinkWaterSet = i10;
        this.mSupportChangeClassicBluetoothState = i11;
        this.mSupportAppSport = i12;
        this.mSupportBloodOxyGenSet = i13;
        this.mSupportWashSet = i14;
        this.mSupportRequestRealtimeWeather = i15;
        this.mSupportHID = i16;
        this.mSupportIBeaconSet = i17;
        this.mSupportWatchFaceId = i18;
        this.mSupportNewTransportMode = i19;
        this.mSupportJLTransport = i20;
        this.mSupportFindWatch = i21;
        this.mSupportWorldClock = i22;
        this.mSupportStock = i23;
        this.mSupportSMSQuickReply = i24;
        this.mSupportNoDisturbSet = i25;
        this.mSupportSetWatchPassword = i26;
        this.mSupportRealTimeMeasurement = i27;
        this.mSupportPowerSaveMode = i28;
        this.mSupportLoveTap = i29;
        this.mSupportNewsfeed = i30;
        this.mSupportMedicationReminder = i31;
        this.mSupportQrcode = i32;
        this.mSupportWeather2 = i33;
        this.mSupportAlipay = i34;
        this.mSupportStandbySet = i35;
        this.mSupport2DAcceleration = i36;
        this.mSupportTuyaKey = i37;
        this.mSupportMedicationAlarm = i38;
        this.mSupportReadPackageStatus = i39;
        this.mSupportContactSize = i40;
        this.mSupportVoice = i41;
        this.mSupportNavigation = i42;
        this.mSupportHrWarnSet = i43;
        this.mBleDefaultName = mBleDefaultName;
    }
}
