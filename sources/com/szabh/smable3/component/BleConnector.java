package com.szabh.smable3.component;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.constant.BroadcastConsts;
import com.bestmafen.baseble.connector.AbsBleConnector;
import com.bestmafen.baseble.connector.BleGattCallback;
import com.bestmafen.baseble.data.BleBuffer;
import com.bestmafen.baseble.data.ByteArrayExtKt;
import com.bestmafen.baseble.messenger.BleMessage;
import com.bestmafen.baseble.messenger.ReadMessage;
import com.bestmafen.baseble.messenger.WriteMessage;
import com.bestmafen.baseble.util.BleLog;
import com.realsil.sdk.core.RtkConfigure;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.dfu.RtkDfu;
import com.szabh.androiddfu.BuildConfig;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import com.szabh.smable3.entity.BleCoaching;
import com.szabh.smable3.entity.BleCoachingIds;
import com.szabh.smable3.entity.BleDeviceInfo;
import com.szabh.smable3.entity.BleIdObject;
import com.szabh.smable3.entity.BleLoveTapUser;
import com.szabh.smable3.entity.BleMusicControl;
import com.szabh.smable3.entity.BleStream;
import com.szabh.smable3.entity.BleStreamPacket;
import com.szabh.smable3.entity.MusicAttr;
import com.szabh.smable3.entity.MusicEntity;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.f;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.h;
import kotlin.text.Charsets;
import kotlin.text.m;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SuppressLint({"StaticFieldLeak"})
/* loaded from: classes12.dex */
public final class BleConnector extends AbsBleConnector {
    @NotNull
    private static final String BLE_CH_NOTIFY = "6e400003-b5a3-f393-e0a9-e50e24dcca9e";
    @NotNull
    private static final String BLE_CH_WRITE = "6e400002-b5a3-f393-e0a9-e50e24dcca9e";
    @NotNull
    private static final String BLE_SERVICE = "6e400001-b5a3-f393-e0a9-e50e24dcca9e";
    @NotNull
    private static final String CH_MTK_OTA_DATA = "c6a22924-f821-18bf-9704-0266f20e80fd";
    @NotNull
    private static final String CH_MTK_OTA_FLAG = "c6a22922-f821-18bf-9704-0266f20e80fd";
    @NotNull
    private static final String CH_MTK_OTA_MD5 = "c6a22926-f821-18bf-9704-0266f20e80fd";
    @NotNull
    public static final String CH_MTK_OTA_META = "c6a22916-f821-18bf-9704-0266f20e80fd";
    @NotNull
    private static final String CH_MTK_OTA_SIZE = "c6a22920-f821-18bf-9704-0266f20e80fd";
    private static final int MTK_OTA_PACKET_SIZE = 180;
    @NotNull
    public static final String SERVICE_MTK = "c6a22905-f821-18bf-9704-0266f20e80fd";
    @NotNull
    private static final String SERVICE_MTK_OTA = "c6a2b98b-f821-18bf-9704-0266f20e80fd";
    @NotNull
    private static final String TAG = "BleConnector";
    private static boolean isConnecting;
    private static boolean isNordicOtaMode;
    @Nullable
    private static BleStream mBleStream;
    private static int mHidState;
    @NotNull
    public static final BleConnector INSTANCE = new BleConnector();
    @NotNull
    private static final Lazy mService$delegate = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.szabh.smable3.component.BleConnector$mService$2
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final String invoke() {
            return "6e400001-b5a3-f393-e0a9-e50e24dcca9e";
        }
    });
    @NotNull
    private static final Lazy mNotify$delegate = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.szabh.smable3.component.BleConnector$mNotify$2
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final String invoke() {
            return "6e400003-b5a3-f393-e0a9-e50e24dcca9e";
        }
    });
    @NotNull
    private static final Lazy mBleMessenger$delegate = LazyKt__LazyJVMKt.lazy(new Function0<BleMessenger>() { // from class: com.szabh.smable3.component.BleConnector$mBleMessenger$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final BleMessenger invoke() {
            BleMessenger bleMessenger = new BleMessenger();
            bleMessenger.setMMessengerCallback(new BleMessengerCallback() { // from class: com.szabh.smable3.component.BleConnector$mBleMessenger$2$1$1
                @Override // com.szabh.smable3.component.BleMessengerCallback
                public void onRetry() {
                    BleConnector.INSTANCE.getMBleParser().reset();
                }

                @Override // com.szabh.smable3.component.BleMessengerCallback
                public void onTimeout(@NotNull BleMessage message) {
                    BleStream bleStream;
                    Intrinsics.checkNotNullParameter(message, "message");
                    bleStream = BleConnector.mBleStream;
                    if (bleStream != null) {
                        BleConnector bleConnector = BleConnector.INSTANCE;
                        bleConnector.getMBleMessenger().reset();
                        bleConnector.setMBleStream(null);
                    }
                    if (message instanceof WriteMessage) {
                        WriteMessage writeMessage = (WriteMessage) message;
                        final BleKey of = BleKey.Companion.of(ByteArrayExtKt.getInt$default(writeMessage.getMData(), 6, 2, null, 4, null));
                        final BleKeyFlag of2 = BleKeyFlag.Companion.of(writeMessage.getMData()[8]);
                        BleLog bleLog = BleLog.INSTANCE;
                        bleLog.d("BleConnector onCommandSendTimeout -> key=" + of + ", keyFlag=" + of2);
                        BleConnector.INSTANCE.notifyHandlers(new Function1<BleHandleCallback, Unit>() { // from class: com.szabh.smable3.component.BleConnector$mBleMessenger$2$1$1$onTimeout$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(BleHandleCallback bleHandleCallback) {
                                invoke2(bleHandleCallback);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2(@NotNull BleHandleCallback it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                it.onCommandSendTimeout(BleKey.this, of2);
                            }
                        });
                    }
                }
            });
            return bleMessenger;
        }
    });
    @NotNull
    private static final Lazy mBleParser$delegate = LazyKt__LazyJVMKt.lazy(new Function0<BleParser>() { // from class: com.szabh.smable3.component.BleConnector$mBleParser$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final BleParser invoke() {
            return BleParser.INSTANCE;
        }
    });
    @NotNull
    private static final Lazy mBleHandleCallbacks$delegate = LazyKt__LazyJVMKt.lazy(new Function0<CopyOnWriteArrayList<BleHandleCallback>>() { // from class: com.szabh.smable3.component.BleConnector$mBleHandleCallbacks$2
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final CopyOnWriteArrayList<BleHandleCallback> invoke() {
            return new CopyOnWriteArrayList<>();
        }
    });
    private static int mBleState = -1;
    @NotNull
    private static List<BleKey> mDataKeys = new ArrayList();
    @NotNull
    private static final Runnable mSyncTimeout = new Runnable() { // from class: com.szabh.smable3.component.c
        @Override // java.lang.Runnable
        public final void run() {
            BleConnector.mSyncTimeout$lambda$0();
        }
    };
    @NotNull
    private static final Map<MusicEntity, List<MusicAttr>> mMusicSubscriptions = new EnumMap(MusicEntity.class);
    private static int mStreamProgressTotal = -1;
    private static int mStreamProgressCompleted = -1;
    private static boolean mSupportFilterEmpty = true;
    private static boolean isNeedBind = true;

    /* loaded from: classes12.dex */
    public static final class Builder {
        @NotNull
        private final Context context;
        private boolean supportFilterEmpty;
        private boolean supportLauncher;
        private boolean supportMtkOta;
        private boolean supportNordicOta;
        private boolean supportRealtekDfu;

        public Builder(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.supportLauncher = true;
            this.supportFilterEmpty = true;
        }

        @NotNull
        public final BleConnector build() {
            return BleConnector.INSTANCE.init(this.context, this.supportNordicOta, this.supportRealtekDfu, this.supportMtkOta, this.supportLauncher, this.supportFilterEmpty);
        }

        @NotNull
        public final Builder supportFilterEmpty(boolean z) {
            this.supportFilterEmpty = z;
            return this;
        }

        @NotNull
        public final Builder supportLauncher(boolean z) {
            this.supportLauncher = z;
            return this;
        }

        @NotNull
        public final Builder supportMtkOta(boolean z) {
            this.supportMtkOta = z;
            return this;
        }

        @NotNull
        public final Builder supportNordicOta(boolean z) {
            this.supportNordicOta = z;
            return this;
        }

        @NotNull
        public final Builder supportRealtekDfu(boolean z) {
            this.supportRealtekDfu = z;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BleKey.values().length];
            try {
                iArr[BleKey.FIRMWARE_VERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BleKey.UI_PACK_VERSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BleKey.OTA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[BleKey.XMODEM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[BleKey.POWER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[BleKey.LANGUAGE_PACK_VERSION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[BleKey.BLE_ADDRESS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[BleKey.USER_PROFILE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[BleKey.STEP_GOAL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[BleKey.SEDENTARINESS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[BleKey.NO_DISTURB_RANGE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[BleKey.ALARM.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[BleKey.UNIT_SET.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[BleKey.COACHING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[BleKey.FIND_PHONE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[BleKey.HR_MONITORING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[BleKey.SLEEP_QUALITY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[BleKey.REALTIME_LOG.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[BleKey.AGPS_PREREQUISITE.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[BleKey.GSENSOR_RAW.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[BleKey.HR_RAW.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[BleKey.MOTION_DETECT.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[BleKey.LOCATION_GGA.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[BleKey.LOCATION_GSV.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[BleKey.LANGUAGE.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[BleKey.TEMPERATURE_UNIT.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[BleKey.DATE_FORMAT.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[BleKey.WATCH_FACE_SWITCH.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[BleKey.DRINK_WATER.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr[BleKey.VIBRATION.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr[BleKey.BACK_LIGHT.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr[BleKey.GESTURE_WAKE.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr[BleKey.HOUR_SYSTEM.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr[BleKey.LOVE_TAP_USER.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr[BleKey.MEDICATION_REMINDER.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr[BleKey.DEVICE_INFO.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                iArr[BleKey.TUYA_KEY_SET.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                iArr[BleKey.BAC_RESULT.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                iArr[BleKey.MEDICATION_ALARM.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                iArr[BleKey.PACKAGE_STATUS.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                iArr[BleKey.ALIPAY_SET.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                iArr[BleKey.RECORD_PACKET.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                iArr[BleKey.NAVI_INFO.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                iArr[BleKey.IDENTITY.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                iArr[BleKey.SESSION.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                iArr[BleKey.MUSIC_CONTROL.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                iArr[BleKey.SCHEDULE.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                iArr[BleKey.ACTIVITY.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                iArr[BleKey.HEART_RATE.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                iArr[BleKey.BLOOD_PRESSURE.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                iArr[BleKey.SLEEP.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                iArr[BleKey.WORKOUT.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                iArr[BleKey.LOCATION.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                iArr[BleKey.TEMPERATURE.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                iArr[BleKey.BLOOD_OXYGEN.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                iArr[BleKey.HRV.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                iArr[BleKey.LOG.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                iArr[BleKey.SLEEP_RAW_DATA.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                iArr[BleKey.RAW_SLEEP.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                iArr[BleKey.PRESSURE.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                iArr[BleKey.WORKOUT2.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                iArr[BleKey.MATCH_RECORD.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                iArr[BleKey.BLOOD_GLUCOSE.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                iArr[BleKey.BODY_STATUS.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                iArr[BleKey.MIND_STATUS.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                iArr[BleKey.CALORIE_INTAKE.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                iArr[BleKey.FOOD_BALANCE.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                iArr[BleKey.BAC.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                iArr[BleKey.MATCH_RECORD2.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                iArr[BleKey.AVG_HEART_RATE.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                iArr[BleKey.APP_SPORT_DATA.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                iArr[BleKey.REAL_TIME_HEART_RATE.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                iArr[BleKey.WATCHFACE_ID.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                iArr[BleKey.REAL_TIME_TEMPERATURE.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                iArr[BleKey.REAL_TIME_BLOOD_PRESSURE.ordinal()] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                iArr[BleKey.REALTIME_MEASUREMENT.ordinal()] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                iArr[BleKey.POWER_SAVE_MODE.ordinal()] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                iArr[BleKey.CALORIES_GOAL.ordinal()] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                iArr[BleKey.DISTANCE_GOAL.ordinal()] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                iArr[BleKey.SLEEP_GOAL.ordinal()] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                iArr[BleKey.CAMERA.ordinal()] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                iArr[BleKey.REQUEST_LOCATION.ordinal()] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                iArr[BleKey.INCOMING_CALL.ordinal()] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                iArr[BleKey.APP_SPORT_STATE.ordinal()] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                iArr[BleKey.CLASSIC_BLUETOOTH_STATE.ordinal()] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                iArr[BleKey.WATCH_FACE.ordinal()] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                iArr[BleKey.AGPS_FILE.ordinal()] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                iArr[BleKey.FONT_FILE.ordinal()] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                iArr[BleKey.CONTACT.ordinal()] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                iArr[BleKey.UI_FILE.ordinal()] = 90;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                iArr[BleKey.LANGUAGE_FILE.ordinal()] = 91;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                iArr[BleKey.BRAND_INFO_FILE.ordinal()] = 92;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                iArr[BleKey.QRCODE.ordinal()] = 93;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                iArr[BleKey.THIRD_PARTY_DATA.ordinal()] = 94;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                iArr[BleKey.DEVICE_FILE.ordinal()] = 95;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                iArr[BleKey.WEATHER_REALTIME.ordinal()] = 96;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                iArr[BleKey.HID.ordinal()] = 97;
            } catch (NoSuchFieldError unused97) {
            }
            try {
                iArr[BleKey.WORLD_CLOCK.ordinal()] = 98;
            } catch (NoSuchFieldError unused98) {
            }
            try {
                iArr[BleKey.STOCK.ordinal()] = 99;
            } catch (NoSuchFieldError unused99) {
            }
            try {
                iArr[BleKey.DEVICE_SMS_QUICK_REPLY.ordinal()] = 100;
            } catch (NoSuchFieldError unused100) {
            }
            try {
                iArr[BleKey.LOVE_TAP.ordinal()] = 101;
            } catch (NoSuchFieldError unused101) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private BleConnector() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bind() {
        sendInt32$default(this, BleKey.IDENTITY, BleKeyFlag.CREATE, Random.Default.nextInt(), null, false, false, 56, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkStreamProgress() {
        int i;
        if (isAvailable()) {
            if (mStreamProgressTotal <= 0 || mStreamProgressCompleted <= 0) {
                return;
            }
            BleLog bleLog = BleLog.INSTANCE;
            bleLog.v("BleConnector onStreamProgress -> mStreamProgressTotal=" + mStreamProgressTotal + ", mStreamProgressCompleted=" + mStreamProgressCompleted);
            notifyHandlersThen(new Function1<BleHandleCallback, Unit>() { // from class: com.szabh.smable3.component.BleConnector$checkStreamProgress$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(BleHandleCallback bleHandleCallback) {
                    invoke2(bleHandleCallback);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull BleHandleCallback it) {
                    int i2;
                    int i3;
                    Intrinsics.checkNotNullParameter(it, "it");
                    i2 = BleConnector.mStreamProgressTotal;
                    i3 = BleConnector.mStreamProgressCompleted;
                    it.onStreamProgress(true, 0, i2, i3);
                }
            }, new Function0<Unit>() { // from class: com.szabh.smable3.component.BleConnector$checkStreamProgress$2
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    int i2;
                    int i3;
                    i2 = BleConnector.mStreamProgressTotal;
                    i3 = BleConnector.mStreamProgressCompleted;
                    if (i2 == i3) {
                        BleConnector bleConnector = BleConnector.INSTANCE;
                        BleConnector.mStreamProgressTotal = -1;
                        BleConnector.mStreamProgressCompleted = -1;
                    }
                }
            });
            return;
        }
        int i2 = mStreamProgressTotal;
        if (i2 <= 0 || (i = mStreamProgressCompleted) < 0 || i >= i2) {
            return;
        }
        notifyHandlersThen(new Function1<BleHandleCallback, Unit>() { // from class: com.szabh.smable3.component.BleConnector$checkStreamProgress$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BleHandleCallback bleHandleCallback) {
                invoke2(bleHandleCallback);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull BleHandleCallback it) {
                int i3;
                int i4;
                Intrinsics.checkNotNullParameter(it, "it");
                i3 = BleConnector.mStreamProgressTotal;
                i4 = BleConnector.mStreamProgressCompleted;
                it.onStreamProgress(false, -1, i3, i4);
            }
        }, new Function0<Unit>() { // from class: com.szabh.smable3.component.BleConnector$checkStreamProgress$4
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                int i3;
                int i4;
                i3 = BleConnector.mStreamProgressTotal;
                i4 = BleConnector.mStreamProgressCompleted;
                if (i3 == i4) {
                    BleConnector bleConnector = BleConnector.INSTANCE;
                    BleConnector.mStreamProgressTotal = -1;
                    BleConnector.mStreamProgressCompleted = -1;
                }
            }
        });
    }

    private final Boolean createBond(String str) {
        if (BluetoothAdapter.checkBluetoothAddress(str)) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice remoteDevice = defaultAdapter != null ? defaultAdapter.getRemoteDevice(str) : null;
            if (remoteDevice != null) {
                return Boolean.valueOf(remoteDevice.createBond());
            }
            return null;
        }
        return Boolean.FALSE;
    }

    private final CopyOnWriteArrayList<BleHandleCallback> getMBleHandleCallbacks() {
        return (CopyOnWriteArrayList) mBleHandleCallbacks$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:1275:0x3292 A[Catch: Exception -> 0x335d, TryCatch #0 {Exception -> 0x335d, blocks: (B:3:0x000a, B:5:0x0012, B:6:0x0019, B:9:0x0020, B:15:0x00a2, B:1272:0x328d, B:1275:0x3292, B:1277:0x329a, B:1279:0x329e, B:1281:0x32a5, B:1282:0x32b3, B:1287:0x32bc, B:1289:0x32c5, B:1290:0x32cb, B:1292:0x32d3, B:1293:0x32dc, B:1294:0x32f7, B:1296:0x3300, B:1297:0x331a, B:1298:0x332b, B:1300:0x3331, B:17:0x00a9, B:20:0x00ad, B:22:0x0110, B:25:0x0121, B:28:0x0125, B:29:0x0177, B:33:0x018e, B:35:0x0192, B:39:0x01a3, B:40:0x01d1, B:67:0x02bf, B:42:0x020c, B:44:0x0213, B:47:0x0217, B:49:0x0249, B:63:0x0275, B:50:0x024d, B:51:0x0252, B:53:0x0258, B:62:0x0272, B:59:0x026b, B:64:0x02a2, B:66:0x02a8, B:70:0x02d3, B:72:0x02d7, B:76:0x02ea, B:77:0x0318, B:101:0x03da, B:78:0x034a, B:80:0x034e, B:83:0x0352, B:85:0x0380, B:99:0x03ac, B:86:0x0384, B:87:0x0389, B:89:0x038f, B:98:0x03a9, B:95:0x03a2, B:102:0x03e0, B:105:0x03f0, B:107:0x0406, B:110:0x040a, B:119:0x047c, B:113:0x043c, B:115:0x0440, B:118:0x0452, B:122:0x048c, B:124:0x0490, B:125:0x04a8, B:130:0x04c3, B:132:0x04c7, B:134:0x04cb, B:139:0x057f, B:136:0x051c, B:138:0x0520, B:142:0x0593, B:144:0x0597, B:147:0x059b, B:149:0x05d4, B:151:0x05de, B:158:0x0618, B:152:0x05e5, B:154:0x05e9, B:156:0x05f9, B:157:0x0612, B:160:0x0625, B:162:0x0629, B:166:0x0632, B:180:0x071f, B:168:0x066c, B:170:0x0676, B:172:0x067a, B:173:0x06b0, B:175:0x06b6, B:177:0x06ba, B:183:0x0732, B:186:0x0736, B:188:0x0771, B:191:0x0788, B:194:0x078c, B:196:0x07de, B:199:0x07f0, B:202:0x07f4, B:203:0x0826, B:206:0x0838, B:209:0x083c, B:210:0x0874, B:213:0x0886, B:216:0x088a, B:220:0x0893, B:225:0x08fc, B:221:0x08c0, B:224:0x08c4, B:228:0x090d, B:230:0x0911, B:233:0x0915, B:234:0x0941, B:237:0x0952, B:239:0x0956, B:242:0x095a, B:243:0x0986, B:246:0x0997, B:248:0x099b, B:251:0x099f, B:252:0x09cb, B:253:0x09cf, B:256:0x09e1, B:257:0x09ef, B:260:0x09f3, B:268:0x0a7e, B:262:0x0a2f, B:264:0x0a33, B:267:0x0a45, B:269:0x0a82, B:272:0x0a95, B:273:0x0aa3, B:275:0x0ae3, B:276:0x0ae7, B:279:0x0afa, B:280:0x0b47, B:281:0x0b4b, B:284:0x0b5e, B:285:0x0bab, B:288:0x0bbe, B:290:0x0bc2, B:291:0x0c02, B:293:0x0c06, B:295:0x0c11, B:296:0x0c15, B:299:0x0c28, B:300:0x0c75, B:303:0x0c83, B:306:0x0c87, B:310:0x0c90, B:311:0x0c98, B:314:0x0ca4, B:317:0x0cb5, B:321:0x0cc6, B:322:0x0cf4, B:324:0x0d12, B:328:0x0d1c, B:329:0x0d26, B:331:0x0d2b, B:332:0x0d2f, B:335:0x0d41, B:339:0x0d54, B:340:0x0d82, B:342:0x0da0, B:345:0x0db1, B:347:0x0db5, B:344:0x0da7, B:348:0x0db9, B:351:0x0dcb, B:355:0x0ddd, B:356:0x0e0b, B:358:0x0e29, B:361:0x0e3a, B:363:0x0e3e, B:360:0x0e30, B:364:0x0e42, B:367:0x0e54, B:371:0x0e66, B:372:0x0e94, B:374:0x0eb2, B:377:0x0ec3, B:379:0x0ec7, B:376:0x0eb9, B:380:0x0ecb, B:383:0x0edd, B:387:0x0eef, B:388:0x0f1d, B:390:0x0f3b, B:393:0x0f4c, B:395:0x0f50, B:392:0x0f42, B:396:0x0f54, B:399:0x0f66, B:403:0x0f78, B:404:0x0fa6, B:406:0x0fc4, B:409:0x0fd5, B:411:0x0fd9, B:408:0x0fcb, B:412:0x0fdd, B:415:0x0fef, B:419:0x1001, B:420:0x102f, B:422:0x104d, B:425:0x105e, B:427:0x1062, B:424:0x1054, B:428:0x1066, B:431:0x1078, B:435:0x108a, B:436:0x10b8, B:438:0x10d6, B:441:0x10e7, B:443:0x10eb, B:440:0x10dd, B:444:0x10ef, B:447:0x1101, B:451:0x1114, B:452:0x1142, B:454:0x1160, B:457:0x1171, B:459:0x1175, B:456:0x1167, B:460:0x1179, B:463:0x118b, B:467:0x119e, B:468:0x11cc, B:470:0x11ea, B:473:0x11fb, B:475:0x11ff, B:472:0x11f1, B:476:0x1203, B:479:0x1215, B:483:0x1227, B:484:0x1255, B:486:0x1273, B:489:0x1284, B:491:0x1288, B:488:0x127a, B:492:0x128c, B:495:0x129d, B:498:0x12a1, B:499:0x12b0, B:500:0x12bd, B:503:0x12cf, B:507:0x12e2, B:508:0x1310, B:510:0x1331, B:511:0x133b, B:513:0x133f, B:514:0x1343, B:517:0x1355, B:521:0x1367, B:522:0x1395, B:524:0x13b3, B:527:0x13c4, B:529:0x13c8, B:526:0x13ba, B:530:0x13cc, B:533:0x13de, B:537:0x13f0, B:538:0x141e, B:540:0x143c, B:543:0x144d, B:545:0x1451, B:542:0x1443, B:546:0x1455, B:549:0x1467, B:553:0x1479, B:554:0x14a7, B:556:0x14c5, B:559:0x14d6, B:561:0x14da, B:558:0x14cc, B:562:0x14de, B:565:0x14f0, B:569:0x1502, B:570:0x1530, B:572:0x154e, B:575:0x155f, B:577:0x1563, B:574:0x1555, B:578:0x1567, B:581:0x1579, B:585:0x158c, B:586:0x15ba, B:588:0x15d8, B:591:0x15e9, B:593:0x15ed, B:590:0x15df, B:594:0x15f1, B:597:0x1603, B:601:0x1615, B:602:0x1645, B:604:0x1663, B:607:0x1674, B:609:0x1678, B:606:0x166a, B:610:0x167c, B:613:0x168e, B:617:0x16a0, B:618:0x16ce, B:620:0x16ec, B:623:0x16fd, B:625:0x1701, B:622:0x16f3, B:626:0x1705, B:629:0x1717, B:633:0x1729, B:634:0x1757, B:636:0x1775, B:639:0x1786, B:641:0x178a, B:638:0x177c, B:642:0x178e, B:645:0x17a0, B:649:0x17b2, B:650:0x17e0, B:652:0x17fe, B:655:0x1823, B:657:0x1827, B:654:0x1805, B:659:0x1835, B:663:0x1876, B:665:0x187a, B:668:0x187e, B:669:0x188b, B:671:0x188f, B:673:0x18b0, B:674:0x18c6, B:675:0x18de, B:678:0x1901, B:680:0x1905, B:683:0x190b, B:685:0x194f, B:688:0x195d, B:691:0x196b, B:696:0x1976, B:697:0x1980, B:698:0x1983, B:701:0x1994, B:705:0x199d, B:708:0x19a3, B:736:0x1ae9, B:712:0x19b4, B:714:0x19b8, B:716:0x19d2, B:717:0x1a27, B:719:0x1a35, B:721:0x1a3d, B:724:0x1a5d, B:725:0x1a60, B:726:0x1a69, B:727:0x1a83, B:729:0x1a88, B:732:0x1a8e, B:734:0x1acb, B:739:0x1b01, B:741:0x1b05, B:743:0x1b6d, B:746:0x1b88, B:748:0x1b8c, B:751:0x1ba3, B:753:0x1be5, B:756:0x1bf8, B:758:0x1bfc, B:759:0x1c4a, B:762:0x1c5c, B:764:0x1c60, B:765:0x1cae, B:769:0x1cc5, B:771:0x1cc9, B:775:0x1cdb, B:776:0x1d07, B:819:0x1ed8, B:778:0x1d3c, B:780:0x1d40, B:781:0x1d84, B:783:0x1d8a, B:792:0x1da6, B:793:0x1da9, B:789:0x1da0, B:794:0x1dd8, B:796:0x1ddf, B:799:0x1de5, B:801:0x1e13, B:815:0x1e3d, B:802:0x1e17, B:803:0x1e1c, B:805:0x1e22, B:814:0x1e3a, B:811:0x1e34, B:816:0x1e6b, B:818:0x1e6f, B:820:0x1edc, B:823:0x1ef1, B:824:0x1f3e, B:827:0x1f53, B:829:0x1f57, B:830:0x1fa5, B:833:0x1fba, B:835:0x1fbe, B:836:0x200c, B:839:0x2020, B:841:0x2024, B:845:0x2037, B:846:0x2065, B:885:0x21c1, B:847:0x2098, B:849:0x209c, B:850:0x20de, B:852:0x20e4, B:861:0x2100, B:862:0x2103, B:858:0x20fa, B:863:0x2132, B:865:0x2136, B:868:0x213c, B:870:0x216a, B:884:0x2194, B:871:0x216e, B:872:0x2173, B:874:0x2179, B:883:0x2191, B:880:0x218b, B:888:0x21d5, B:890:0x21d9, B:894:0x21ec, B:895:0x221d, B:934:0x2379, B:896:0x2250, B:898:0x2254, B:899:0x2296, B:901:0x229c, B:910:0x22b8, B:911:0x22bb, B:907:0x22b2, B:912:0x22ea, B:914:0x22ee, B:917:0x22f4, B:919:0x2322, B:933:0x234c, B:920:0x2326, B:921:0x232b, B:923:0x2331, B:932:0x2349, B:929:0x2343, B:937:0x238c, B:939:0x2390, B:940:0x23c7, B:943:0x23dc, B:945:0x23e0, B:950:0x2491, B:947:0x2431, B:949:0x2435, B:953:0x24a4, B:955:0x24a8, B:963:0x2529, B:957:0x24e2, B:959:0x24e6, B:962:0x24fa, B:966:0x253c, B:968:0x2540, B:971:0x2554, B:972:0x2583, B:975:0x2598, B:977:0x259c, B:978:0x25db, B:981:0x25ee, B:983:0x25f2, B:986:0x25f8, B:987:0x2628, B:989:0x262c, B:991:0x2637, B:994:0x264a, B:996:0x264e, B:999:0x2654, B:1000:0x2683, B:1003:0x2696, B:1005:0x269a, B:1008:0x26a0, B:1009:0x26cf, B:1012:0x26e2, B:1014:0x26e6, B:1016:0x26ea, B:1017:0x2702, B:1020:0x2715, B:1022:0x2719, B:1026:0x2739, B:1027:0x2767, B:1028:0x2789, B:1031:0x279e, B:1033:0x27a2, B:1034:0x27ef, B:1037:0x2802, B:1039:0x2806, B:1043:0x2826, B:1044:0x2856, B:1045:0x2878, B:1048:0x288b, B:1050:0x288f, B:1054:0x28af, B:1055:0x28dd, B:1056:0x28ff, B:1059:0x2912, B:1061:0x2916, B:1065:0x2936, B:1066:0x2964, B:1067:0x2986, B:1070:0x2999, B:1072:0x299d, B:1073:0x29b5, B:1076:0x29ca, B:1078:0x29ce, B:1079:0x2a1b, B:1082:0x2a30, B:1084:0x2a34, B:1087:0x2a3a, B:1089:0x2a7f, B:1092:0x2a93, B:1094:0x2a97, B:1095:0x2ae4, B:1098:0x2af6, B:1100:0x2afa, B:1103:0x2b00, B:1107:0x2b09, B:1111:0x2b27, B:1112:0x2b39, B:1115:0x2b4d, B:1117:0x2b51, B:1118:0x2b91, B:1121:0x2ba2, B:1123:0x2ba6, B:1126:0x2bac, B:1127:0x2bda, B:1132:0x2bfd, B:1134:0x2c01, B:1138:0x2c12, B:1139:0x2c3e, B:1184:0x2e67, B:1140:0x2c78, B:1142:0x2ca2, B:1144:0x2cac, B:1145:0x2cfd, B:1147:0x2d03, B:1156:0x2d21, B:1157:0x2d24, B:1153:0x2d1a, B:1158:0x2d55, B:1160:0x2d61, B:1163:0x2d67, B:1165:0x2d97, B:1179:0x2dc3, B:1166:0x2d9b, B:1167:0x2da0, B:1169:0x2da6, B:1178:0x2dc0, B:1175:0x2db9, B:1180:0x2df4, B:1182:0x2df8, B:1187:0x2e7e, B:1189:0x2e82, B:1191:0x2ebb, B:1192:0x2ed4, B:1197:0x2f37, B:1194:0x2ed9, B:1196:0x2edd, B:1200:0x2f4e, B:1202:0x2f52, B:1203:0x2f8f, B:1206:0x2fa2, B:1208:0x2fa6, B:1211:0x2faa, B:1212:0x2fd6, B:1215:0x2fed, B:1217:0x2ff1, B:1218:0x302e, B:1219:0x3032, B:1222:0x3099, B:1224:0x309d, B:1225:0x30e9, B:1228:0x30fe, B:1229:0x310c, B:1232:0x3112, B:1234:0x311d, B:1235:0x3120, B:1237:0x3126, B:1238:0x312a, B:1239:0x315c, B:1241:0x316c, B:1244:0x3181, B:1248:0x3188, B:1251:0x31bb, B:1253:0x31bf, B:1256:0x31eb, B:1269:0x3286, B:1271:0x328a, B:1257:0x31ef, B:1258:0x321d, B:1263:0x324d, B:1265:0x3253, B:1267:0x3259, B:1268:0x326c), top: B:1305:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:1359:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void handleData(byte[] r47) {
        /*
            Method dump skipped, instructions count: 13362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szabh.smable3.component.BleConnector.handleData(byte[]):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BleConnector init(Context context, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        mSupportFilterEmpty = z5;
        super.init(context, new BleGattCallback() { // from class: com.szabh.smable3.component.BleConnector$init$1
            @Override // com.bestmafen.baseble.connector.BleGattCallback
            public void onCharacteristicChanged(@NotNull byte[] value) {
                Intrinsics.checkNotNullParameter(value, "value");
                BleConnector.INSTANCE.handleData(value);
            }

            @Override // com.bestmafen.baseble.connector.BleGattCallback
            public void onCharacteristicRead(@NotNull String characteristicUuid, @NotNull byte[] value, @NotNull String text) {
                Intrinsics.checkNotNullParameter(characteristicUuid, "characteristicUuid");
                Intrinsics.checkNotNullParameter(value, "value");
                Intrinsics.checkNotNullParameter(text, "text");
                if (Intrinsics.areEqual(characteristicUuid, BleConnector.CH_MTK_OTA_META)) {
                    BleCache.INSTANCE.putMtkOtaMeta(text);
                    BleConnector.INSTANCE.notifyHandlers(new Function1<BleHandleCallback, Unit>() { // from class: com.szabh.smable3.component.BleConnector$init$1$onCharacteristicRead$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(BleHandleCallback bleHandleCallback) {
                            invoke2(bleHandleCallback);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2(@NotNull BleHandleCallback it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            it.onReadMtkOtaMeta();
                        }
                    });
                }
            }

            @Override // com.bestmafen.baseble.connector.BleGattCallback
            public void onCharacteristicWrite(@NotNull String characteristicUuid, @NotNull byte[] value) {
                int i;
                Intrinsics.checkNotNullParameter(characteristicUuid, "characteristicUuid");
                Intrinsics.checkNotNullParameter(value, "value");
                if (Intrinsics.areEqual(characteristicUuid, "c6a22924-f821-18bf-9704-0266f20e80fd")) {
                    BleConnector bleConnector = BleConnector.INSTANCE;
                    i = BleConnector.mStreamProgressCompleted;
                    BleConnector.mStreamProgressCompleted = i + 1;
                    bleConnector.checkStreamProgress();
                }
            }

            @Override // com.bestmafen.baseble.connector.BleGattCallback
            public void onConnectionStateChange(boolean z6) {
                List list;
                BleStream bleStream;
                List list2;
                List list3;
                BleConnector bleConnector = BleConnector.INSTANCE;
                BluetoothGatt mBluetoothGatt = bleConnector.getMBluetoothGatt();
                Intrinsics.checkNotNull(mBluetoothGatt);
                final BluetoothDevice device = mBluetoothGatt.getDevice();
                if (z6) {
                    BleLog bleLog = BleLog.INSTANCE;
                    bleLog.d("BleConnector onDeviceConnected -> " + device);
                    BleConnector.mBleState = 0;
                    bleConnector.notifyHandlers(new Function1<BleHandleCallback, Unit>() { // from class: com.szabh.smable3.component.BleConnector$init$1$onConnectionStateChange$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(BleHandleCallback bleHandleCallback) {
                            invoke2(bleHandleCallback);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2(@NotNull BleHandleCallback it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            BluetoothDevice device2 = device;
                            Intrinsics.checkNotNullExpressionValue(device2, "device");
                            it.onDeviceConnected(device2);
                        }
                    });
                    BleConnector.mStreamProgressTotal = -1;
                    BleConnector.mStreamProgressCompleted = -1;
                } else {
                    BleLog.INSTANCE.d("BleConnector onSessionStateChange -> false");
                    BleConnector.mBleState = -1;
                    bleConnector.notifyHandlers(new Function1<BleHandleCallback, Unit>() { // from class: com.szabh.smable3.component.BleConnector$init$1$onConnectionStateChange$2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(BleHandleCallback bleHandleCallback) {
                            invoke2(bleHandleCallback);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2(@NotNull BleHandleCallback it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            it.onSessionStateChange(false);
                        }
                    });
                    list = BleConnector.mDataKeys;
                    if (!list.isEmpty()) {
                        list2 = BleConnector.mDataKeys;
                        bleConnector.notifySyncState(-2, (BleKey) list2.get(0));
                        list3 = BleConnector.mDataKeys;
                        list3.clear();
                        bleConnector.removeSyncTimeout();
                    }
                    bleStream = BleConnector.mBleStream;
                    if (bleStream != null) {
                        bleConnector.notifyHandlers(new Function1<BleHandleCallback, Unit>() { // from class: com.szabh.smable3.component.BleConnector$init$1$onConnectionStateChange$3
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(BleHandleCallback bleHandleCallback) {
                                invoke2(bleHandleCallback);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2(@NotNull BleHandleCallback it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                it.onStreamProgress(false, -1, 0, 0);
                            }
                        });
                    }
                    bleConnector.checkStreamProgress();
                }
                bleConnector.setMBleStream(null);
            }

            @Override // com.bestmafen.baseble.connector.BleGattCallback
            public void onMtuChanged() {
                boolean z6;
                BleConnector bleConnector = BleConnector.INSTANCE;
                BleConnector.mBleState = 1;
                z6 = BleConnector.isNordicOtaMode;
                if (z6) {
                    if (bleConnector.isNeedBind()) {
                        bleConnector.sendOta();
                        return;
                    }
                    return;
                }
                BleDeviceInfo mDeviceInfo = BleCache.INSTANCE.getMDeviceInfo();
                if (mDeviceInfo != null) {
                    BleLog.INSTANCE.v("BleConnector DeviceInfo not null -> login");
                    bleConnector.login(mDeviceInfo.getMId());
                    return;
                }
                BleLog.INSTANCE.v("BleConnector DeviceInfo is null -> bind");
                if (bleConnector.isNeedBind()) {
                    bleConnector.bind();
                }
            }
        });
        BleCache bleCache = BleCache.INSTANCE;
        bleCache.setMDeviceInfo((BleDeviceInfo) BleCache.getObject$default(bleCache, BleKey.IDENTITY, BleDeviceInfo.class, null, 4, null));
        if (z4) {
            launch();
        }
        if (z && Build.VERSION.SDK_INT >= 26) {
            DfuServiceInitiator.createDfuNotificationChannel(getMContext());
        }
        if (z2) {
            RtkConfigure.Builder builder = new RtkConfigure.Builder();
            boolean z6 = BuildConfig.DEBUG;
            RtkCore.initialize(context, builder.debugEnabled(z6).printLog(z6).logTag("RealtekDfu").build());
            RtkDfu.initialize(context, z6);
        }
        if (z3) {
            BroadcastConsts.PACKAGE_FOTA_UPDATE = context.getPackageName();
            OtaAgentPolicy.init(context);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void login(int i) {
        sendInt32$default(this, BleKey.SESSION, BleKeyFlag.CREATE, i, null, false, false, 56, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mSyncTimeout$lambda$0() {
        if (!mDataKeys.isEmpty()) {
            INSTANCE.notifySyncState(-1, mDataKeys.get(0));
            mDataKeys.clear();
            return;
        }
        INSTANCE.notifySyncState(-1, BleKey.NONE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyHandlers(final Function1<? super BleHandleCallback, Unit> function1) {
        getMHandler().post(new Runnable() { // from class: com.szabh.smable3.component.b
            @Override // java.lang.Runnable
            public final void run() {
                BleConnector.notifyHandlers$lambda$110(Function1.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyHandlers$lambda$110(Function1 action) {
        Intrinsics.checkNotNullParameter(action, "$action");
        for (BleHandleCallback it : INSTANCE.getMBleHandleCallbacks()) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            action.invoke(it);
        }
    }

    private final void notifyHandlersThen(final Function1<? super BleHandleCallback, Unit> function1, final Function0<Unit> function0) {
        getMHandler().post(new Runnable() { // from class: com.szabh.smable3.component.a
            @Override // java.lang.Runnable
            public final void run() {
                BleConnector.notifyHandlersThen$lambda$112(Function0.this, function1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyHandlersThen$lambda$112(Function0 then, Function1 action) {
        Intrinsics.checkNotNullParameter(then, "$then");
        Intrinsics.checkNotNullParameter(action, "$action");
        for (BleHandleCallback it : INSTANCE.getMBleHandleCallbacks()) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            action.invoke(it);
        }
        then.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySyncState(final int i, final BleKey bleKey) {
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleConnector onSyncData -> " + SyncState.INSTANCE.getState(i) + ", " + bleKey);
        notifyHandlers(new Function1<BleHandleCallback, Unit>() { // from class: com.szabh.smable3.component.BleConnector$notifySyncState$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BleHandleCallback bleHandleCallback) {
                invoke2(bleHandleCallback);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull BleHandleCallback it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onSyncData(i, bleKey);
            }
        });
    }

    private final void postDelaySyncTimeout() {
        removeSyncTimeout();
        getMHandler().postDelayed(mSyncTimeout, BleMessenger.TIMEOUT);
    }

    private final void removeBond(String str) {
        if ((Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(getMContext(), "android.permission.BLUETOOTH_CONNECT") == 0) && BluetoothAdapter.checkBluetoothAddress(str)) {
            for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
                if (m.equals(bluetoothDevice.getAddress(), str, true)) {
                    try {
                        Object invoke = bluetoothDevice.getClass().getMethod("removeBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
                        BleLog bleLog = BleLog.INSTANCE;
                        bleLog.v("BleConnector removeBond -> " + invoke);
                        return;
                    } catch (Exception e) {
                        BleLog bleLog2 = BleLog.INSTANCE;
                        bleLog2.w("BleConnector removeBond -> " + e);
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSyncTimeout() {
        getMHandler().removeCallbacks(mSyncTimeout);
    }

    public static /* synthetic */ boolean sendData$default(BleConnector bleConnector, BleKey bleKey, BleKeyFlag bleKeyFlag, byte[] bArr, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            bArr = null;
        }
        return bleConnector.sendData(bleKey, bleKeyFlag, bArr, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2);
    }

    public static /* synthetic */ boolean sendInt16$default(BleConnector bleConnector, BleKey bleKey, BleKeyFlag bleKeyFlag, int i, ByteOrder BIG_ENDIAN, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
            Intrinsics.checkNotNullExpressionValue(BIG_ENDIAN, "BIG_ENDIAN");
        }
        return bleConnector.sendInt16(bleKey, bleKeyFlag, i, BIG_ENDIAN, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? false : z2);
    }

    public static /* synthetic */ boolean sendInt24$default(BleConnector bleConnector, BleKey bleKey, BleKeyFlag bleKeyFlag, int i, ByteOrder BIG_ENDIAN, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
            Intrinsics.checkNotNullExpressionValue(BIG_ENDIAN, "BIG_ENDIAN");
        }
        return bleConnector.sendInt24(bleKey, bleKeyFlag, i, BIG_ENDIAN, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? false : z2);
    }

    public static /* synthetic */ boolean sendInt32$default(BleConnector bleConnector, BleKey bleKey, BleKeyFlag bleKeyFlag, int i, ByteOrder BIG_ENDIAN, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
            Intrinsics.checkNotNullExpressionValue(BIG_ENDIAN, "BIG_ENDIAN");
        }
        return bleConnector.sendInt32(bleKey, bleKeyFlag, i, BIG_ENDIAN, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? false : z2);
    }

    public static /* synthetic */ boolean sendInt8$default(BleConnector bleConnector, BleKey bleKey, BleKeyFlag bleKeyFlag, int i, boolean z, boolean z2, int i2, Object obj) {
        return bleConnector.sendInt8(bleKey, bleKeyFlag, i, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2);
    }

    public static /* synthetic */ boolean sendObject$default(BleConnector bleConnector, BleKey bleKey, BleKeyFlag bleKeyFlag, BleBuffer bleBuffer, boolean z, boolean z2, int i, Object obj) {
        return bleConnector.sendObject(bleKey, bleKeyFlag, bleBuffer, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendOta() {
        sendData$default(this, BleKey.OTA, BleKeyFlag.UPDATE, null, false, false, 28, null);
    }

    public static /* synthetic */ boolean sendStream$default(BleConnector bleConnector, BleKey bleKey, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return bleConnector.sendStream(bleKey, bArr, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMBleStream(BleStream bleStream) {
        mBleStream = bleStream;
        setDisableStreamLog(bleStream != null);
    }

    private final boolean syncData() {
        List<Integer> mDataKeys2 = BleCache.INSTANCE.getMDataKeys();
        ArrayList<Number> arrayList = new ArrayList();
        Iterator<T> it = mDataKeys2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((Number) next).intValue() != BleKey.DATA_ALL.getMKey()) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(arrayList, 10));
        for (Number number : arrayList) {
            arrayList2.add(BleKey.Companion.of(number.intValue()));
        }
        List<BleKey> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList2);
        mDataKeys = mutableList;
        if (mutableList.isEmpty()) {
            notifySyncState(0, BleKey.NONE);
            return true;
        }
        postDelaySyncTimeout();
        return sendData$default(this, mDataKeys.get(0), BleKeyFlag.READ, null, false, false, 28, null);
    }

    private final void unbindClassic() {
        removeBond(BleCache.INSTANCE.getMClassicAddress());
    }

    private final void unbindHID() {
        removeBond(BleCache.INSTANCE.getMBleAddress());
    }

    public final void addHandleCallback(@NotNull BleHandleCallback bleHandleCallback) {
        Intrinsics.checkNotNullParameter(bleHandleCallback, "bleHandleCallback");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.d("BleConnector addHandleCallback -> " + bleHandleCallback);
        if (!getMBleHandleCallbacks().contains(bleHandleCallback)) {
            getMBleHandleCallbacks().add(bleHandleCallback);
            return;
        }
        throw new UnsupportedOperationException("bleHandleCallback already exists");
    }

    public final void connectClassic() {
        Boolean createBond;
        if (Build.VERSION.SDK_INT >= 23) {
            BleCache bleCache = BleCache.INSTANCE;
            if (Intrinsics.areEqual(bleCache.getMPlatform(), BleDeviceInfo.PLATFORM_JL)) {
                createBond = createBond(bleCache.getMClassicAddress(), 1);
                BleLog bleLog = BleLog.INSTANCE;
                bleLog.v("connectClassic -> " + createBond);
            }
        }
        createBond = createBond(BleCache.INSTANCE.getMClassicAddress());
        BleLog bleLog2 = BleLog.INSTANCE;
        bleLog2.v("connectClassic -> " + createBond);
    }

    public final void connectHID() {
        Boolean createBond = createBond(BleCache.INSTANCE.getMBleAddress());
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("connectHID -> " + createBond);
    }

    public final int getMHidState() {
        return mHidState;
    }

    @Override // com.bestmafen.baseble.connector.AbsBleConnector
    @NotNull
    public String getMNotify() {
        return (String) mNotify$delegate.getValue();
    }

    @Override // com.bestmafen.baseble.connector.AbsBleConnector
    @NotNull
    public String getMService() {
        return (String) mService$delegate.getValue();
    }

    public final boolean isAvailable() {
        return mBleState >= 1;
    }

    public final boolean isBound() {
        return BleCache.INSTANCE.getMDeviceInfo() != null;
    }

    @Override // com.bestmafen.baseble.connector.AbsBleConnector
    public boolean isConnecting() {
        return isConnecting;
    }

    public final boolean isHandlerCallbackExist(@NotNull BleHandleCallback bleHandleCallback) {
        Intrinsics.checkNotNullParameter(bleHandleCallback, "bleHandleCallback");
        return getMBleHandleCallbacks().contains(bleHandleCallback);
    }

    public final boolean isNeedBind() {
        return isNeedBind;
    }

    public final void launch() {
        BleCache bleCache = BleCache.INSTANCE;
        if (bleCache.getMDeviceInfo() != null) {
            if (Build.VERSION.SDK_INT >= 31 && (getMContext().checkSelfPermission("android.permission.BLUETOOTH_SCAN") != 0 || getMContext().checkSelfPermission("android.permission.BLUETOOTH_CONNECT") != 0)) {
                BleLog.INSTANCE.e("BleConnector launch -> Need new bluetooth permission!!!");
                return;
            }
            BleLog bleLog = BleLog.INSTANCE;
            bleLog.d("BleConnector launch -> deviceInfo=" + bleCache.getMDeviceInfo());
            BleDeviceInfo mDeviceInfo = bleCache.getMDeviceInfo();
            Intrinsics.checkNotNull(mDeviceInfo);
            setAddress(mDeviceInfo.getMBleAddress());
            connect(true);
            return;
        }
        BleLog.INSTANCE.d("BleConnector launch -> deviceInfo=null");
    }

    public final void mtkOta(@NotNull byte[] bytes) {
        int length;
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (isAvailable()) {
            if (!(bytes.length == 0)) {
                if (bytes.length % 180 == 0) {
                    length = bytes.length / 180;
                } else {
                    length = (bytes.length / 180) + 1;
                }
                mStreamProgressTotal = length;
                mStreamProgressCompleted = 0;
                BleMessenger mBleMessenger = getMBleMessenger();
                int length2 = bytes.length;
                ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
                Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
                mBleMessenger.enqueueWritePackets(new WriteMessage(SERVICE_MTK_OTA, CH_MTK_OTA_SIZE, ByteArrayExtKt.byteArrayOfInt32(length2, LITTLE_ENDIAN)));
                getMBleMessenger().enqueueWritePackets(new WriteMessage(SERVICE_MTK_OTA, CH_MTK_OTA_FLAG, new byte[]{1}));
                int i = mStreamProgressTotal;
                int i2 = 0;
                while (i2 < i) {
                    int i3 = i2 + 1;
                    getMBleMessenger().enqueueWritePackets(new WriteMessage(SERVICE_MTK_OTA, CH_MTK_OTA_DATA, ArraysKt___ArraysKt.sliceArray(bytes, h.until(i2 * 180, Math.min(i3 * 180, bytes.length)))));
                    i2 = i3;
                }
                getMBleMessenger().enqueueWritePackets(new WriteMessage(SERVICE_MTK_OTA, CH_MTK_OTA_FLAG, new byte[]{2}));
                BleMessenger mBleMessenger2 = getMBleMessenger();
                byte[] bytes2 = "b3b27696771768c6648f237a43c37a39".getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
                mBleMessenger2.enqueueWritePackets(new WriteMessage(SERVICE_MTK_OTA, CH_MTK_OTA_MD5, bytes2));
                return;
            }
        }
        notifyHandlers(new Function1<BleHandleCallback, Unit>() { // from class: com.szabh.smable3.component.BleConnector$mtkOta$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BleHandleCallback bleHandleCallback) {
                invoke2(bleHandleCallback);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull BleHandleCallback it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onStreamProgress(false, -1, 0, 0);
            }
        });
    }

    public final boolean read(@NotNull String service, @NotNull String characteristic) {
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(characteristic, "characteristic");
        if (isAvailable()) {
            getMBleMessenger().enqueueMessage(new ReadMessage(service, characteristic));
            return true;
        }
        return false;
    }

    public final void removeHandleCallback(@NotNull BleHandleCallback bleHandleCallback) {
        Intrinsics.checkNotNullParameter(bleHandleCallback, "bleHandleCallback");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.d("BleConnector removeHandleCallback -> " + bleHandleCallback);
        if (getMBleHandleCallbacks().contains(bleHandleCallback)) {
            getMBleHandleCallbacks().remove(bleHandleCallback);
            return;
        }
        throw new UnsupportedOperationException("bleHandleCallback dose not exist");
    }

    public final boolean sendBoolean(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        boolean sendData = sendData(bleKey, bleKeyFlag, ByteArrayExtKt.byteArrayOfBoolean(z), z2, z3);
        if (sendData) {
            BleCache bleCache = BleCache.INSTANCE;
            if (bleCache.requireCache$AndroidSmaBle_debug(bleKey, bleKeyFlag)) {
                BleCache.putBoolean$default(bleCache, bleKey, z, null, 4, null);
            }
        }
        return sendData;
    }

    public final boolean sendData(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, @Nullable byte[] bArr, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.d("BleConnector sendData -> " + bleKey + ", " + bleKeyFlag);
        if (isAvailable()) {
            if (bleKey == BleKey.DATA_ALL && bleKeyFlag == BleKeyFlag.READ) {
                return syncData();
            }
            int i = z ? 16 : 0;
            if (z2) {
                i |= 32;
            }
            WriteMessage writeMessage = new WriteMessage(BLE_SERVICE, BLE_CH_WRITE, MessageFactory.INSTANCE.create(i, bleKey, bleKeyFlag, bArr));
            if (z) {
                INSTANCE.getMBleMessenger().replyMessage(writeMessage);
                return true;
            }
            INSTANCE.getMBleMessenger().enqueueMessage(writeMessage);
            return true;
        }
        return false;
    }

    public final boolean sendInt16(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, int i, @NotNull ByteOrder order, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        Intrinsics.checkNotNullParameter(order, "order");
        boolean sendData = sendData(bleKey, bleKeyFlag, ByteArrayExtKt.byteArrayOfInt16(i, order), z, z2);
        if (sendData) {
            BleCache bleCache = BleCache.INSTANCE;
            if (bleCache.requireCache$AndroidSmaBle_debug(bleKey, bleKeyFlag)) {
                BleCache.putInt$default(bleCache, bleKey, i, null, 4, null);
            }
        }
        return sendData;
    }

    public final boolean sendInt24(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, int i, @NotNull ByteOrder order, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        Intrinsics.checkNotNullParameter(order, "order");
        boolean sendData = sendData(bleKey, bleKeyFlag, ByteArrayExtKt.byteArrayOfInt24(i, order), z, z2);
        if (sendData) {
            BleCache bleCache = BleCache.INSTANCE;
            if (bleCache.requireCache$AndroidSmaBle_debug(bleKey, bleKeyFlag)) {
                BleCache.putInt$default(bleCache, bleKey, i, null, 4, null);
            }
        }
        return sendData;
    }

    public final boolean sendInt32(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, int i, @NotNull ByteOrder order, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        Intrinsics.checkNotNullParameter(order, "order");
        boolean sendData = sendData(bleKey, bleKeyFlag, ByteArrayExtKt.byteArrayOfInt32(i, order), z, z2);
        if (sendData) {
            BleCache bleCache = BleCache.INSTANCE;
            if (bleCache.requireCache$AndroidSmaBle_debug(bleKey, bleKeyFlag)) {
                BleCache.putInt$default(bleCache, bleKey, i, null, 4, null);
            }
        }
        return sendData;
    }

    public final boolean sendInt8(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, int i, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        boolean sendData = sendData(bleKey, bleKeyFlag, ByteArrayExtKt.byteArrayOfInt8(i), z, z2);
        if (sendData) {
            BleCache bleCache = BleCache.INSTANCE;
            if (bleCache.requireCache$AndroidSmaBle_debug(bleKey, bleKeyFlag)) {
                if (bleKey.isIdObjectKey$AndroidSmaBle_debug()) {
                    List<BleIdObject> idObjects$AndroidSmaBle_debug = bleCache.getIdObjects$AndroidSmaBle_debug(bleKey);
                    if (bleKeyFlag == BleKeyFlag.DELETE) {
                        if (i == 255) {
                            idObjects$AndroidSmaBle_debug.clear();
                        } else {
                            Iterator<BleIdObject> it = idObjects$AndroidSmaBle_debug.iterator();
                            int i2 = 0;
                            while (true) {
                                if (!it.hasNext()) {
                                    i2 = -1;
                                    break;
                                }
                                if (it.next().getMId() == i) {
                                    break;
                                }
                                i2++;
                            }
                            if (i2 > -1) {
                                idObjects$AndroidSmaBle_debug.remove(i2);
                            }
                        }
                    }
                    BleCache.putList$default(BleCache.INSTANCE, bleKey, idObjects$AndroidSmaBle_debug, null, 4, null);
                } else {
                    BleCache.putInt$default(bleCache, bleKey, i, null, 4, null);
                }
            }
        }
        return sendData;
    }

    /* JADX WARN: Type inference failed for: r3v13, types: [com.bestmafen.baseble.data.BleBuffer, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v8, types: [com.bestmafen.baseble.data.BleBuffer, java.lang.Object] */
    public final boolean sendList(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, @Nullable List<? extends BleBuffer> list, boolean z, boolean z2) {
        byte[] bArr;
        List<BleIdObject> list2;
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        int i = 0;
        if (isAvailable()) {
            byte[] bArr2 = null;
            ArrayList arrayList = new ArrayList();
            if (bleKey.isIdObjectKey$AndroidSmaBle_debug()) {
                list2 = BleCache.INSTANCE.getIdObjects$AndroidSmaBle_debug(bleKey);
                if (bleKeyFlag == BleKeyFlag.CREATE) {
                    ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(list2, 10));
                    for (BleIdObject bleIdObject : list2) {
                        arrayList2.add(Integer.valueOf(bleIdObject.getMId()));
                    }
                    List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList2);
                    if (list != null) {
                        Iterator<T> it = list.iterator();
                        while (it.hasNext()) {
                            ?? r4 = (BleBuffer) it.next();
                            if (r4 instanceof BleIdObject) {
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= 255) {
                                        break;
                                    } else if (!mutableList.contains(Integer.valueOf(i2))) {
                                        ((BleIdObject) r4).setMId(i2);
                                        bArr2 = ByteArrayExtKt.append(bArr2, r4.toByteArray());
                                        list2.add(r4);
                                        mutableList.add(Integer.valueOf(i2));
                                        break;
                                    } else {
                                        i2++;
                                    }
                                }
                            }
                        }
                    }
                } else if (bleKeyFlag == BleKeyFlag.RESET) {
                    list2.clear();
                    if (list != null) {
                        for (Object obj : list) {
                            int i3 = i + 1;
                            if (i < 0) {
                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                            }
                            ?? r3 = (BleBuffer) obj;
                            if (r3 instanceof BleIdObject) {
                                ((BleIdObject) r3).setMId(i);
                                bArr2 = ByteArrayExtKt.append(bArr2, r3.toByteArray());
                                list2.add(r3);
                            }
                            i = i3;
                        }
                    }
                    sendInt8$default(this, bleKey, BleKeyFlag.DELETE, 255, false, false, 24, null);
                    bArr = bArr2;
                }
                bArr = bArr2;
            } else {
                if (bleKeyFlag == BleKeyFlag.CREATE && list != null) {
                    for (BleBuffer bleBuffer : list) {
                        bArr2 = ByteArrayExtKt.append(bArr2, bleBuffer.toByteArray());
                    }
                }
                bArr = bArr2;
                list2 = arrayList;
            }
            BleKeyFlag bleKeyFlag2 = BleKeyFlag.RESET;
            boolean sendData = sendData(bleKey, bleKeyFlag == bleKeyFlag2 ? BleKeyFlag.CREATE : bleKeyFlag, bArr, z, z2);
            if (sendData) {
                BleCache bleCache = BleCache.INSTANCE;
                if (bleCache.requireCache$AndroidSmaBle_debug(bleKey, bleKeyFlag)) {
                    if (bleKey.isIdObjectKey$AndroidSmaBle_debug()) {
                        if (bleKeyFlag == BleKeyFlag.CREATE || bleKeyFlag == bleKeyFlag2) {
                            BleCache.putList$default(bleCache, bleKey, list2, null, 4, null);
                        }
                    } else {
                        BleCache.putList$default(bleCache, bleKey, list, null, 4, null);
                    }
                }
            }
            return sendData;
        }
        return false;
    }

    public final boolean sendObject(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, @Nullable BleBuffer bleBuffer, boolean z, boolean z2) {
        BleCoachingIds bleCoachingIds;
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        int i = 0;
        if (isAvailable()) {
            List<BleIdObject> arrayList = new ArrayList<>();
            boolean z3 = bleBuffer instanceof BleIdObject;
            if (z3) {
                arrayList = BleCache.INSTANCE.getIdObjects$AndroidSmaBle_debug(bleKey);
                if (bleKeyFlag == BleKeyFlag.CREATE) {
                    ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(arrayList, 10));
                    for (BleIdObject bleIdObject : arrayList) {
                        arrayList2.add(Integer.valueOf(bleIdObject.getMId()));
                    }
                    List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList2);
                    if ((bleBuffer instanceof BleCoaching) && (bleCoachingIds = (BleCoachingIds) BleCache.INSTANCE.getObject(BleKey.COACHING, BleCoachingIds.class, BleKeyFlag.READ)) != null) {
                        mutableList.addAll(bleCoachingIds.getMIds());
                    }
                    if (bleBuffer instanceof BleLoveTapUser) {
                        Iterator<BleIdObject> it = arrayList.iterator();
                        int i2 = 0;
                        while (true) {
                            if (!it.hasNext()) {
                                i2 = -1;
                                break;
                            }
                            if (it.next().getMId() == ((BleIdObject) bleBuffer).getMId()) {
                                break;
                            }
                            i2++;
                        }
                        if (i2 > -1) {
                            arrayList.set(i2, bleBuffer);
                        } else {
                            arrayList.add(bleBuffer);
                        }
                    } else {
                        while (true) {
                            if (i >= 255) {
                                break;
                            } else if (!mutableList.contains(Integer.valueOf(i))) {
                                ((BleIdObject) bleBuffer).setMId(i);
                                break;
                            } else {
                                i++;
                            }
                        }
                        arrayList.add(bleBuffer);
                    }
                } else if (bleKeyFlag == BleKeyFlag.UPDATE) {
                    Iterator<BleIdObject> it2 = arrayList.iterator();
                    int i3 = 0;
                    while (true) {
                        if (!it2.hasNext()) {
                            i3 = -1;
                            break;
                        }
                        if (it2.next().getMId() == ((BleIdObject) bleBuffer).getMId()) {
                            break;
                        }
                        i3++;
                    }
                    if (i3 > -1) {
                        arrayList.set(i3, bleBuffer);
                    }
                }
            }
            List<BleIdObject> list = arrayList;
            boolean sendData = sendData(bleKey, bleKeyFlag, bleBuffer != null ? bleBuffer.toByteArray() : null, z, z2);
            if (sendData) {
                BleCache bleCache = BleCache.INSTANCE;
                if (bleCache.requireCache$AndroidSmaBle_debug(bleKey, bleKeyFlag)) {
                    if (z3) {
                        BleCache.putList$default(bleCache, bleKey, list, null, 4, null);
                    } else {
                        BleCache.putObject$default(bleCache, bleKey, bleBuffer, null, 4, null);
                    }
                }
            }
            return sendData;
        }
        return false;
    }

    public final boolean sendStream(@NotNull BleKey bleKey, @NotNull byte[] bytes, int i) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (bytes.length == 0) {
            return false;
        }
        setMBleStream(new BleStream(bleKey, i, bytes));
        BleStream bleStream = mBleStream;
        BleStreamPacket packet = bleStream != null ? bleStream.getPacket(0, BleCache.INSTANCE.getMIOBufferSize()) : null;
        if (packet != null) {
            BleStream bleStream2 = mBleStream;
            Intrinsics.checkNotNull(bleStream2);
            return sendObject$default(this, bleStream2.getMBleKey(), BleKeyFlag.UPDATE, packet, false, false, 24, null);
        }
        return false;
    }

    @Override // com.bestmafen.baseble.connector.AbsBleConnector
    public void setConnecting(final boolean z) {
        isConnecting = z;
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.d("BleConnector onDeviceConnecting -> " + z);
        notifyHandlers(new Function1<BleHandleCallback, Unit>() { // from class: com.szabh.smable3.component.BleConnector$isConnecting$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BleHandleCallback bleHandleCallback) {
                invoke2(bleHandleCallback);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull BleHandleCallback it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.onDeviceConnecting(z);
            }
        });
    }

    public final void setMHidState(int i) {
        mHidState = i;
    }

    public final void setNeedBind(boolean z) {
        isNeedBind = z;
    }

    public final void setOtaMode(boolean z) {
        isNordicOtaMode = z;
    }

    public final void unbind() {
        unbindClassic();
        BleCache bleCache = BleCache.INSTANCE;
        if (bleCache.getMSupportHID() == 1) {
            unbindHID();
        }
        bleCache.setMDeviceInfo(null);
        BleCache.remove$default(bleCache, BleKey.IDENTITY, null, 2, null);
        closeConnection(true);
    }

    public final boolean updateMusic(@NotNull BleMusicControl bleMusicControl) {
        Intrinsics.checkNotNullParameter(bleMusicControl, "bleMusicControl");
        Map<MusicEntity, List<MusicAttr>> map = mMusicSubscriptions;
        if (map.get(bleMusicControl.getMMusicEntity()) != null) {
            List<MusicAttr> list = map.get(bleMusicControl.getMMusicEntity());
            Intrinsics.checkNotNull(list);
            if (list.contains(bleMusicControl.getMMusicAttr())) {
                return sendObject$default(this, BleKey.MUSIC_CONTROL, BleKeyFlag.UPDATE, bleMusicControl, false, false, 24, null);
            }
            return true;
        }
        return true;
    }

    public static /* synthetic */ boolean sendStream$default(BleConnector bleConnector, BleKey bleKey, InputStream inputStream, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return bleConnector.sendStream(bleKey, inputStream, i);
    }

    @Override // com.bestmafen.baseble.connector.AbsBleConnector
    @NotNull
    public BleMessenger getMBleMessenger() {
        return (BleMessenger) mBleMessenger$delegate.getValue();
    }

    @Override // com.bestmafen.baseble.connector.AbsBleConnector
    @NotNull
    public BleParser getMBleParser() {
        return (BleParser) mBleParser$delegate.getValue();
    }

    public static /* synthetic */ boolean sendStream$default(BleConnector bleConnector, BleKey bleKey, File file, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return bleConnector.sendStream(bleKey, file, i);
    }

    private final Boolean createBond(String str, int i) {
        if (BluetoothAdapter.checkBluetoothAddress(str)) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice remoteDevice = defaultAdapter != null ? defaultAdapter.getRemoteDevice(str) : null;
            if (remoteDevice != null) {
                try {
                    Method method = remoteDevice.getClass().getMethod("createBond", Integer.TYPE);
                    method.setAccessible(true);
                    Object invoke = method.invoke(remoteDevice, Integer.valueOf(i));
                    BleLog bleLog = BleLog.INSTANCE;
                    bleLog.v("BleConnector invoke createBond -> " + invoke);
                    if (invoke instanceof Boolean) {
                        return (Boolean) invoke;
                    }
                } catch (Exception e) {
                    BleLog bleLog2 = BleLog.INSTANCE;
                    bleLog2.w("BleConnector invoke createBond error -> " + e);
                }
            }
            if (remoteDevice != null) {
                return Boolean.valueOf(remoteDevice.createBond());
            }
            return null;
        }
        return Boolean.FALSE;
    }

    public static /* synthetic */ boolean sendStream$default(BleConnector bleConnector, BleKey bleKey, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return bleConnector.sendStream(bleKey, str, i);
    }

    public static /* synthetic */ boolean sendStream$default(BleConnector bleConnector, BleKey bleKey, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return bleConnector.sendStream(bleKey, i, i2);
    }

    public final boolean sendStream(@NotNull BleKey bleKey, @NotNull InputStream inputStream, int i) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        try {
            byte[] readBytes = ByteStreamsKt.readBytes(inputStream);
            CloseableKt.closeFinally(inputStream, null);
            return sendStream(bleKey, readBytes, i);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean sendStream(@NotNull BleKey bleKey, @NotNull File file, int i) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(file, "file");
        return sendStream(bleKey, new FileInputStream(file), i);
    }

    public final boolean sendStream(@NotNull BleKey bleKey, @NotNull String path, int i) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(path, "path");
        return sendStream(bleKey, new FileInputStream(path), i);
    }

    public final boolean sendStream(@NotNull BleKey bleKey, int i, int i2) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        InputStream openRawResource = getMContext().getResources().openRawResource(i);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "mContext.resources.openRawResource(rawRes)");
        return sendStream(bleKey, openRawResource, i2);
    }

    public final void mtkOta(@NotNull InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        try {
            byte[] readBytes = ByteStreamsKt.readBytes(inputStream);
            CloseableKt.closeFinally(inputStream, null);
            mtkOta(readBytes);
        } finally {
        }
    }

    public final void mtkOta(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        mtkOta(new FileInputStream(file));
    }

    public final void mtkOta(@NotNull String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        mtkOta(new FileInputStream(path));
    }

    public final void mtkOta(int i) {
        InputStream openRawResource = getMContext().getResources().openRawResource(i);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "mContext.resources.openRawResource(rawRes)");
        mtkOta(openRawResource);
    }
}
