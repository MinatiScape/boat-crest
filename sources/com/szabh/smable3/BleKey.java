package com.szabh.smable3;

import com.goodix.ble.libcomx.task.TaskPipe;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.image.constants.SubBinId;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.touchgui.sdk.TGEventListener;
import com.veryfit.multi.nativeprotocol.b;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.e;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public enum BleKey {
    OTA(257),
    XMODEM(258),
    TIME(513),
    TIME_ZONE(514),
    POWER(515),
    FIRMWARE_VERSION(516),
    BLE_ADDRESS(517),
    USER_PROFILE(518),
    STEP_GOAL(519),
    BACK_LIGHT(520),
    SEDENTARINESS(DfuConstants.PROGRESS_START_DFU_PROCESS),
    NO_DISTURB_RANGE(DfuConstants.PROGRESS_HAND_OVER_PROCESSING),
    VIBRATION(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE),
    GESTURE_WAKE(DfuConstants.PROGRESS_ACTIVE_IMAGE_AND_RESET),
    HR_ASSIST_SLEEP(DfuConstants.PROGRESS_ABORT_PROCESSING),
    HOUR_SYSTEM(DfuConstants.PROGRESS_DOWNLOAD_FIRMWARE),
    LANGUAGE(527),
    ALARM(528),
    UNIT_SET(529),
    COACHING(530),
    FIND_PHONE(531),
    NOTIFICATION_REMINDER(532),
    ANTI_LOST(533),
    HR_MONITORING(DfuAdapter.STATE_PREPARE_PAIRING_REQUEST),
    UI_PACK_VERSION(DfuAdapter.STATE_PREPARE_CONNECTING),
    LANGUAGE_PACK_VERSION(DfuAdapter.STATE_PENDDING_DISCOVERY_SERVICE),
    SLEEP_QUALITY(DfuAdapter.STATE_DISCOVERY_SERVICE),
    GIRL_CARE(538),
    TEMPERATURE_DETECTING(DfuAdapter.STATE_READ_DEVICE_INFO),
    AEROBIC_EXERCISE(DfuAdapter.STATE_READ_PROTOCOL_TYPE),
    TEMPERATURE_UNIT(DfuAdapter.STATE_READ_IMAGE_INFO),
    DATE_FORMAT(DfuAdapter.STATE_READ_BATTERY_INFO),
    WATCH_FACE_SWITCH(543),
    AGPS_PREREQUISITE(544),
    DRINK_WATER(545),
    SHUTDOWN(546),
    APP_SPORT_DATA(547),
    REAL_TIME_HEART_RATE(548),
    BLOOD_OXYGEN_SET(549),
    WASH_SET(b.U1),
    WATCHFACE_ID(b.V1),
    IBEACON_SET(b.W1),
    MAC_QRCODE(b.X1),
    REAL_TIME_TEMPERATURE(b.e2),
    REAL_TIME_BLOOD_PRESSURE(b.f2),
    TEMPERATURE_VALUE(b.g2),
    GAME_SET(b.h2),
    FIND_WATCH(b.i2),
    SET_WATCH_PASSWORD(b.j2),
    REALTIME_MEASUREMENT(566),
    POWER_SAVE_MODE(567),
    BAC_SET(568),
    CALORIES_GOAL(569),
    DISTANCE_GOAL(b.k2),
    SLEEP_GOAL(b.l2),
    LOVE_TAP_USER(b.m2),
    MEDICATION_REMINDER(b.n2),
    DEVICE_INFO(b.o2),
    HR_WARNING_SET(b.p2),
    SLEEP_MONITORING(b.q2),
    STANDBY_SET(b.r2),
    BT_NAME(578),
    TUYA_KEY_SET(579),
    BAC_RESULT(b.s2),
    BAC_RESULT_SET(b.t2),
    MEDICATION_ALARM(582),
    MATCH_SET(583),
    PACKAGE_STATUS(585),
    ALIPAY_SET(586),
    RECORD_PACKET(587),
    BLE_ADDRESS_SET(588),
    NAVI_INFO(589),
    LOCATION_GSV(759),
    HR_RAW(760),
    REALTIME_LOG(TaskPipe.EVT_BUSY),
    GSENSOR_OUTPUT(762),
    GSENSOR_RAW(763),
    MOTION_DETECT(764),
    LOCATION_GGA(765),
    RAW_SLEEP(DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED),
    NO_DISTURB_GLOBAL(DfuException.ERROR_NOTIFICATION_NO_RESPONSE),
    IDENTITY(TGEventListener.ALARM_UPDATED),
    SESSION(TGEventListener.REQUEST_UPDATE_WEATHER),
    NOTIFICATION(1025),
    MUSIC_CONTROL(1026),
    SCHEDULE(DfuException.ERROR_WRITE_NOT_PERMIT),
    WEATHER_REALTIME(DfuException.ERROR_GATT_INVALID_PDU),
    WEATHER_FORECAST(1029),
    HID(1030),
    WORLD_CLOCK(1031),
    STOCK(1032),
    SMS_QUICK_REPLY_CONTENT(1033),
    NOTIFICATION2(1034),
    NEWS_FEED(1035),
    WEATHER_REALTIME2(1036),
    WEATHER_FORECAST2(1037),
    LOGIN_DAYS(1038),
    TARGET_COMPLETION(1039),
    AUDIO_TEXT(SubBinId.Bbpro.DSP_UI_PARAMETER_FILE),
    DATA_ALL(1535),
    ACTIVITY_REALTIME(1281),
    ACTIVITY(1282),
    HEART_RATE(1283),
    BLOOD_PRESSURE(1284),
    SLEEP(1285),
    WORKOUT(1286),
    LOCATION(1287),
    TEMPERATURE(1288),
    BLOOD_OXYGEN(1289),
    HRV(1290),
    LOG(1291),
    SLEEP_RAW_DATA(1292),
    PRESSURE(1293),
    WORKOUT2(1294),
    MATCH_RECORD(1295),
    BLOOD_GLUCOSE(1296),
    BODY_STATUS(1297),
    MIND_STATUS(1298),
    CALORIE_INTAKE(1299),
    FOOD_BALANCE(1300),
    BAC(1301),
    MATCH_RECORD2(1302),
    AVG_HEART_RATE(1303),
    CAMERA(SubBinId.Bbpro.DSP_PATCH),
    REQUEST_LOCATION(SubBinId.Bbpro.DSP_APP_IMAGE),
    INCOMING_CALL(SubBinId.Bbpro.DSP_SCENARIO2),
    APP_SPORT_STATE(1540),
    CLASSIC_BLUETOOTH_STATE(1541),
    DEVICE_SMS_QUICK_REPLY(1543),
    LOVE_TAP(1544),
    WATCH_FACE(1793),
    AGPS_FILE(1794),
    FONT_FILE(1795),
    CONTACT(1796),
    UI_FILE(1797),
    DEVICE_FILE(1798),
    LANGUAGE_FILE(1799),
    BRAND_INFO_FILE(1800),
    QRCODE(1801),
    THIRD_PARTY_DATA(1802),
    NONE(65535);
    
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final int mKey;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final BleKey of(int i) {
            BleKey bleKey;
            BleKey[] values = BleKey.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    bleKey = null;
                    break;
                }
                bleKey = values[i2];
                if (bleKey.getMKey() == i) {
                    break;
                }
                i2++;
            }
            return bleKey == null ? BleKey.NONE : bleKey;
        }
    }

    /* loaded from: classes12.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BleKey.values().length];
            try {
                iArr[BleKey.OTA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BleKey.XMODEM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BleKey.POWER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[BleKey.FIRMWARE_VERSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[BleKey.BLE_ADDRESS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[BleKey.UI_PACK_VERSION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[BleKey.LANGUAGE_PACK_VERSION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[BleKey.DEVICE_FILE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[BleKey.RAW_SLEEP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[BleKey.DEVICE_INFO.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[BleKey.PACKAGE_STATUS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[BleKey.TIME.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[BleKey.NOTIFICATION_REMINDER.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[BleKey.SLEEP_QUALITY.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[BleKey.GIRL_CARE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[BleKey.TEMPERATURE_DETECTING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[BleKey.SHUTDOWN.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[BleKey.APP_SPORT_DATA.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[BleKey.REAL_TIME_HEART_RATE.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[BleKey.IBEACON_SET.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[BleKey.MAC_QRCODE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[BleKey.REAL_TIME_TEMPERATURE.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[BleKey.REAL_TIME_BLOOD_PRESSURE.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[BleKey.SET_WATCH_PASSWORD.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[BleKey.REALTIME_MEASUREMENT.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[BleKey.BAC_SET.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[BleKey.HR_WARNING_SET.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[BleKey.SLEEP_MONITORING.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[BleKey.BT_NAME.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr[BleKey.BAC_RESULT.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr[BleKey.BAC_RESULT_SET.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr[BleKey.MATCH_SET.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr[BleKey.BLE_ADDRESS_SET.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr[BleKey.NAVI_INFO.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr[BleKey.TIME_ZONE.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr[BleKey.USER_PROFILE.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                iArr[BleKey.STEP_GOAL.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                iArr[BleKey.BACK_LIGHT.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                iArr[BleKey.SEDENTARINESS.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                iArr[BleKey.NO_DISTURB_RANGE.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                iArr[BleKey.NO_DISTURB_GLOBAL.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                iArr[BleKey.VIBRATION.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                iArr[BleKey.GESTURE_WAKE.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                iArr[BleKey.HR_ASSIST_SLEEP.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                iArr[BleKey.HOUR_SYSTEM.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                iArr[BleKey.LANGUAGE.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                iArr[BleKey.ANTI_LOST.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                iArr[BleKey.HR_MONITORING.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                iArr[BleKey.AEROBIC_EXERCISE.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                iArr[BleKey.TEMPERATURE_UNIT.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                iArr[BleKey.DATE_FORMAT.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                iArr[BleKey.WATCH_FACE_SWITCH.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                iArr[BleKey.DRINK_WATER.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                iArr[BleKey.WATCHFACE_ID.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                iArr[BleKey.POWER_SAVE_MODE.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                iArr[BleKey.CALORIES_GOAL.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                iArr[BleKey.DISTANCE_GOAL.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                iArr[BleKey.SLEEP_GOAL.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                iArr[BleKey.UNIT_SET.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                iArr[BleKey.STANDBY_SET.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                iArr[BleKey.ALIPAY_SET.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                iArr[BleKey.ALARM.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                iArr[BleKey.LOVE_TAP_USER.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                iArr[BleKey.MEDICATION_REMINDER.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                iArr[BleKey.MEDICATION_ALARM.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                iArr[BleKey.COACHING.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                iArr[BleKey.TUYA_KEY_SET.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                iArr[BleKey.IDENTITY.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                iArr[BleKey.NOTIFICATION.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                iArr[BleKey.WEATHER_REALTIME.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                iArr[BleKey.WEATHER_FORECAST.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                iArr[BleKey.NOTIFICATION2.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                iArr[BleKey.NEWS_FEED.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                iArr[BleKey.WEATHER_REALTIME2.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                iArr[BleKey.WEATHER_FORECAST2.ordinal()] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                iArr[BleKey.LOGIN_DAYS.ordinal()] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                iArr[BleKey.TARGET_COMPLETION.ordinal()] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                iArr[BleKey.AUDIO_TEXT.ordinal()] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                iArr[BleKey.SCHEDULE.ordinal()] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                iArr[BleKey.SMS_QUICK_REPLY_CONTENT.ordinal()] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                iArr[BleKey.HID.ordinal()] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                iArr[BleKey.WORLD_CLOCK.ordinal()] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                iArr[BleKey.STOCK.ordinal()] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                iArr[BleKey.DATA_ALL.ordinal()] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                iArr[BleKey.ACTIVITY_REALTIME.ordinal()] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                iArr[BleKey.ACTIVITY.ordinal()] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                iArr[BleKey.HEART_RATE.ordinal()] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                iArr[BleKey.BLOOD_PRESSURE.ordinal()] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                iArr[BleKey.SLEEP.ordinal()] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                iArr[BleKey.WORKOUT.ordinal()] = 90;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                iArr[BleKey.LOCATION.ordinal()] = 91;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                iArr[BleKey.TEMPERATURE.ordinal()] = 92;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                iArr[BleKey.BLOOD_OXYGEN.ordinal()] = 93;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                iArr[BleKey.HRV.ordinal()] = 94;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                iArr[BleKey.LOG.ordinal()] = 95;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                iArr[BleKey.SLEEP_RAW_DATA.ordinal()] = 96;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                iArr[BleKey.PRESSURE.ordinal()] = 97;
            } catch (NoSuchFieldError unused97) {
            }
            try {
                iArr[BleKey.WORKOUT2.ordinal()] = 98;
            } catch (NoSuchFieldError unused98) {
            }
            try {
                iArr[BleKey.BLOOD_GLUCOSE.ordinal()] = 99;
            } catch (NoSuchFieldError unused99) {
            }
            try {
                iArr[BleKey.BODY_STATUS.ordinal()] = 100;
            } catch (NoSuchFieldError unused100) {
            }
            try {
                iArr[BleKey.MIND_STATUS.ordinal()] = 101;
            } catch (NoSuchFieldError unused101) {
            }
            try {
                iArr[BleKey.CALORIE_INTAKE.ordinal()] = 102;
            } catch (NoSuchFieldError unused102) {
            }
            try {
                iArr[BleKey.FOOD_BALANCE.ordinal()] = 103;
            } catch (NoSuchFieldError unused103) {
            }
            try {
                iArr[BleKey.BAC.ordinal()] = 104;
            } catch (NoSuchFieldError unused104) {
            }
            try {
                iArr[BleKey.MATCH_RECORD2.ordinal()] = 105;
            } catch (NoSuchFieldError unused105) {
            }
            try {
                iArr[BleKey.AVG_HEART_RATE.ordinal()] = 106;
            } catch (NoSuchFieldError unused106) {
            }
            try {
                iArr[BleKey.CAMERA.ordinal()] = 107;
            } catch (NoSuchFieldError unused107) {
            }
            try {
                iArr[BleKey.REQUEST_LOCATION.ordinal()] = 108;
            } catch (NoSuchFieldError unused108) {
            }
            try {
                iArr[BleKey.DEVICE_SMS_QUICK_REPLY.ordinal()] = 109;
            } catch (NoSuchFieldError unused109) {
            }
            try {
                iArr[BleKey.INCOMING_CALL.ordinal()] = 110;
            } catch (NoSuchFieldError unused110) {
            }
            try {
                iArr[BleKey.APP_SPORT_STATE.ordinal()] = 111;
            } catch (NoSuchFieldError unused111) {
            }
            try {
                iArr[BleKey.CLASSIC_BLUETOOTH_STATE.ordinal()] = 112;
            } catch (NoSuchFieldError unused112) {
            }
            try {
                iArr[BleKey.LOVE_TAP.ordinal()] = 113;
            } catch (NoSuchFieldError unused113) {
            }
            try {
                iArr[BleKey.WATCH_FACE.ordinal()] = 114;
            } catch (NoSuchFieldError unused114) {
            }
            try {
                iArr[BleKey.AGPS_FILE.ordinal()] = 115;
            } catch (NoSuchFieldError unused115) {
            }
            try {
                iArr[BleKey.FONT_FILE.ordinal()] = 116;
            } catch (NoSuchFieldError unused116) {
            }
            try {
                iArr[BleKey.CONTACT.ordinal()] = 117;
            } catch (NoSuchFieldError unused117) {
            }
            try {
                iArr[BleKey.UI_FILE.ordinal()] = 118;
            } catch (NoSuchFieldError unused118) {
            }
            try {
                iArr[BleKey.LANGUAGE_FILE.ordinal()] = 119;
            } catch (NoSuchFieldError unused119) {
            }
            try {
                iArr[BleKey.BRAND_INFO_FILE.ordinal()] = 120;
            } catch (NoSuchFieldError unused120) {
            }
            try {
                iArr[BleKey.QRCODE.ordinal()] = 121;
            } catch (NoSuchFieldError unused121) {
            }
            try {
                iArr[BleKey.THIRD_PARTY_DATA.ordinal()] = 122;
            } catch (NoSuchFieldError unused122) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    BleKey(int i) {
        this.mKey = i;
    }

    @NotNull
    public final List<BleKeyFlag> getBleKeyFlags() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
            case 2:
                return e.listOf(BleKeyFlag.UPDATE);
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return e.listOf(BleKeyFlag.READ);
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
                return e.listOf(BleKeyFlag.UPDATE);
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new BleKeyFlag[]{BleKeyFlag.UPDATE, BleKeyFlag.READ});
            case 62:
            case 63:
            case 64:
            case 65:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new BleKeyFlag[]{BleKeyFlag.CREATE, BleKeyFlag.DELETE, BleKeyFlag.UPDATE, BleKeyFlag.READ, BleKeyFlag.RESET});
            case 66:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new BleKeyFlag[]{BleKeyFlag.CREATE, BleKeyFlag.UPDATE, BleKeyFlag.READ});
            case 67:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new BleKeyFlag[]{BleKeyFlag.UPDATE, BleKeyFlag.READ, BleKeyFlag.DELETE});
            case 68:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new BleKeyFlag[]{BleKeyFlag.CREATE, BleKeyFlag.READ, BleKeyFlag.DELETE, BleKeyFlag.UPDATE});
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
                return e.listOf(BleKeyFlag.UPDATE);
            case 79:
            case 80:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new BleKeyFlag[]{BleKeyFlag.CREATE, BleKeyFlag.DELETE, BleKeyFlag.UPDATE});
            case 81:
                return e.listOf(BleKeyFlag.READ);
            case 82:
            case 83:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new BleKeyFlag[]{BleKeyFlag.CREATE, BleKeyFlag.DELETE, BleKeyFlag.UPDATE, BleKeyFlag.READ, BleKeyFlag.RESET});
            case 84:
            case 85:
                return e.listOf(BleKeyFlag.READ);
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new BleKeyFlag[]{BleKeyFlag.READ, BleKeyFlag.DELETE});
            case 107:
                return e.listOf(BleKeyFlag.UPDATE);
            case 108:
            case 109:
                return e.listOf(BleKeyFlag.CREATE);
            case 110:
            case 111:
            case 112:
            case 113:
                return e.listOf(BleKeyFlag.UPDATE);
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
                return e.listOf(BleKeyFlag.UPDATE);
            default:
                return CollectionsKt__CollectionsKt.emptyList();
        }
    }

    @NotNull
    public final BleCommand getMBleCommand() {
        BleCommand bleCommand;
        BleCommand[] values = BleCommand.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                bleCommand = null;
                break;
            }
            bleCommand = values[i];
            if (bleCommand.getMBleCommand() == (this.mKey >>> 8)) {
                break;
            }
            i++;
        }
        return bleCommand == null ? BleCommand.NONE : bleCommand;
    }

    public final byte getMCommandRawValue() {
        return (byte) (this.mKey >> 8);
    }

    public final int getMKey() {
        return this.mKey;
    }

    public final byte getMKeyRawValue() {
        return (byte) this.mKey;
    }

    public final boolean isIdObjectKey$AndroidSmaBle_debug() {
        return this == ALARM || this == SCHEDULE || this == COACHING || this == WORLD_CLOCK || this == STOCK || this == SMS_QUICK_REPLY_CONTENT || this == LOVE_TAP_USER || this == MEDICATION_REMINDER || this == MEDICATION_ALARM;
    }

    @Override // java.lang.Enum
    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("0x%04X_", Arrays.copyOf(new Object[]{Integer.valueOf(this.mKey)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        sb.append(format);
        sb.append(name());
        return sb.toString();
    }
}
