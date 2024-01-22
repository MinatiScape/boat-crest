package com.szabh.smable3.component;

import android.bluetooth.BluetoothAdapter;
import android.util.SparseArray;
import com.bestmafen.baseble.util.BleLog;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.szabh.smable3.BleCommand;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import com.szabh.smable3.BuildConfig;
import com.szabh.smable3.entity.BleAlarm;
import com.szabh.smable3.entity.BleCoaching;
import com.szabh.smable3.entity.BleDeviceInfo;
import com.szabh.smable3.entity.BleIdObject;
import com.szabh.smable3.entity.BleLoveTapUser;
import com.szabh.smable3.entity.BleMedicationAlarm;
import com.szabh.smable3.entity.BleMedicationReminder;
import com.szabh.smable3.entity.BleSMSQuickReplyContent;
import com.szabh.smable3.entity.BleSchedule;
import com.szabh.smable3.entity.BleStock;
import com.szabh.smable3.entity.BleStreamPacket;
import com.szabh.smable3.entity.BleWorldClock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt___StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleCache {
    @NotNull
    private static final String MTK_OTA_META = "mtk_ota_meta";
    @NotNull
    private static final String TAG = "BleCache";
    @NotNull
    private static final SparseArray<String> mAGpsFileUrls;
    @Nullable
    private static BleDeviceInfo mDeviceInfo;
    @NotNull
    public static final BleCache INSTANCE = new BleCache();
    @NotNull
    private static final Lazy mSpUtils$delegate = LazyKt__LazyJVMKt.lazy(new Function0<SPUtils>() { // from class: com.szabh.smable3.component.BleCache$mSpUtils$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SPUtils invoke() {
            return SPUtils.getInstance("sma_ble_sdk3");
        }
    });

    /* loaded from: classes12.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[BleCommand.values().length];
            try {
                iArr[BleCommand.SET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BleCommand.PUSH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[BleKey.values().length];
            try {
                iArr2[BleKey.ALARM.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[BleKey.SCHEDULE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[BleKey.COACHING.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[BleKey.WORLD_CLOCK.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[BleKey.STOCK.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[BleKey.SMS_QUICK_REPLY_CONTENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[BleKey.LOVE_TAP_USER.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[BleKey.MEDICATION_REMINDER.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[BleKey.MEDICATION_ALARM.ordinal()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.append(1, "http://wepodownload.mediatek.com/EPO_GR_3_1.DAT");
        sparseArray.append(2, "https://alp.u-blox.com/current_1d.alp");
        sparseArray.append(6, "https://api.smawatch.cn/epo/ble_epo_offline.bin");
        boolean z = BuildConfig.DEBUG;
        sparseArray.append(7, z ? "https://sma-test.oss-accelerate.aliyuncs.com/a-gps/file_info_3335_epo.DAT" : "https://sma-product.oss-accelerate.aliyuncs.com/a-gps/file_info_3335_epo.DAT");
        sparseArray.append(8, z ? "https://sma-test.oss-accelerate.aliyuncs.com/a-gps/file_info_7dv5_lto.brm" : "https://sma-product.oss-accelerate.aliyuncs.com/a-gps/file_info_7dv5_lto.brm");
        mAGpsFileUrls = sparseArray;
    }

    private BleCache() {
    }

    public static /* synthetic */ boolean getBoolean$default(BleCache bleCache, BleKey bleKey, boolean z, BleKeyFlag bleKeyFlag, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            bleKeyFlag = null;
        }
        return bleCache.getBoolean(bleKey, z, bleKeyFlag);
    }

    public static /* synthetic */ int getInt$default(BleCache bleCache, BleKey bleKey, int i, BleKeyFlag bleKeyFlag, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            bleKeyFlag = null;
        }
        return bleCache.getInt(bleKey, i, bleKeyFlag);
    }

    private final String getKey(BleKey bleKey, BleKeyFlag bleKeyFlag) {
        if (bleKeyFlag == null) {
            return String.valueOf(bleKey);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(bleKey);
        sb.append('_');
        sb.append(bleKeyFlag);
        return sb.toString();
    }

    public static /* synthetic */ List getList$default(BleCache bleCache, BleKey bleKey, Class cls, BleKeyFlag bleKeyFlag, int i, Object obj) {
        if ((i & 4) != 0) {
            bleKeyFlag = null;
        }
        return bleCache.getList(bleKey, cls, bleKeyFlag);
    }

    public static /* synthetic */ long getLong$default(BleCache bleCache, BleKey bleKey, long j, BleKeyFlag bleKeyFlag, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        if ((i & 4) != 0) {
            bleKeyFlag = null;
        }
        return bleCache.getLong(bleKey, j, bleKeyFlag);
    }

    private final SPUtils getMSpUtils() {
        Object value = mSpUtils$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mSpUtils>(...)");
        return (SPUtils) value;
    }

    public static /* synthetic */ Object getObject$default(BleCache bleCache, BleKey bleKey, Class cls, BleKeyFlag bleKeyFlag, int i, Object obj) {
        if ((i & 4) != 0) {
            bleKeyFlag = null;
        }
        return bleCache.getObject(bleKey, cls, bleKeyFlag);
    }

    public static /* synthetic */ Object getObjectNotNull$default(BleCache bleCache, BleKey bleKey, Class cls, Object obj, BleKeyFlag bleKeyFlag, int i, Object obj2) {
        if ((i & 4) != 0) {
            obj = null;
        }
        if ((i & 8) != 0) {
            bleKeyFlag = null;
        }
        return bleCache.getObjectNotNull(bleKey, cls, obj, bleKeyFlag);
    }

    public static /* synthetic */ String getString$default(BleCache bleCache, BleKey bleKey, String str, BleKeyFlag bleKeyFlag, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        if ((i & 4) != 0) {
            bleKeyFlag = null;
        }
        return bleCache.getString(bleKey, str, bleKeyFlag);
    }

    public static /* synthetic */ void putBoolean$default(BleCache bleCache, BleKey bleKey, boolean z, BleKeyFlag bleKeyFlag, int i, Object obj) {
        if ((i & 4) != 0) {
            bleKeyFlag = null;
        }
        bleCache.putBoolean(bleKey, z, bleKeyFlag);
    }

    public static /* synthetic */ void putInt$default(BleCache bleCache, BleKey bleKey, int i, BleKeyFlag bleKeyFlag, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            bleKeyFlag = null;
        }
        bleCache.putInt(bleKey, i, bleKeyFlag);
    }

    public static /* synthetic */ void putList$default(BleCache bleCache, BleKey bleKey, List list, BleKeyFlag bleKeyFlag, int i, Object obj) {
        if ((i & 4) != 0) {
            bleKeyFlag = null;
        }
        bleCache.putList(bleKey, list, bleKeyFlag);
    }

    public static /* synthetic */ void putLong$default(BleCache bleCache, BleKey bleKey, long j, BleKeyFlag bleKeyFlag, int i, Object obj) {
        if ((i & 4) != 0) {
            bleKeyFlag = null;
        }
        bleCache.putLong(bleKey, j, bleKeyFlag);
    }

    public static /* synthetic */ void putObject$default(BleCache bleCache, BleKey bleKey, Object obj, BleKeyFlag bleKeyFlag, int i, Object obj2) {
        if ((i & 4) != 0) {
            bleKeyFlag = null;
        }
        bleCache.putObject(bleKey, obj, bleKeyFlag);
    }

    public static /* synthetic */ void putString$default(BleCache bleCache, BleKey bleKey, String str, BleKeyFlag bleKeyFlag, int i, Object obj) {
        if ((i & 4) != 0) {
            bleKeyFlag = null;
        }
        bleCache.putString(bleKey, str, bleKeyFlag);
    }

    public static /* synthetic */ void remove$default(BleCache bleCache, BleKey bleKey, BleKeyFlag bleKeyFlag, int i, Object obj) {
        if ((i & 2) != 0) {
            bleKeyFlag = null;
        }
        bleCache.remove(bleKey, bleKeyFlag);
    }

    public final void clear() {
        BleLog.INSTANCE.v("BleCache clear");
        getMSpUtils().clear();
    }

    public final boolean getBoolean(@NotNull BleKey bleKey, boolean z, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        boolean z2 = getMSpUtils().getBoolean(getKey(bleKey, bleKeyFlag), z);
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache getBoolean " + INSTANCE.getKey(bleKey, bleKeyFlag) + " -> " + z2);
        return z2;
    }

    @NotNull
    public final List<BleIdObject> getIdObjects$AndroidSmaBle_debug(@NotNull BleKey bleKey) {
        List list$default;
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        switch (WhenMappings.$EnumSwitchMapping$1[bleKey.ordinal()]) {
            case 1:
                list$default = getList$default(this, bleKey, BleAlarm.class, null, 4, null);
                break;
            case 2:
                list$default = getList$default(this, bleKey, BleSchedule.class, null, 4, null);
                break;
            case 3:
                list$default = getList$default(this, bleKey, BleCoaching.class, null, 4, null);
                break;
            case 4:
                list$default = getList$default(this, bleKey, BleWorldClock.class, null, 4, null);
                break;
            case 5:
                list$default = getList$default(this, bleKey, BleStock.class, null, 4, null);
                break;
            case 6:
                list$default = getList$default(this, bleKey, BleSMSQuickReplyContent.class, null, 4, null);
                break;
            case 7:
                list$default = getList$default(this, bleKey, BleLoveTapUser.class, null, 4, null);
                break;
            case 8:
                list$default = getList$default(this, bleKey, BleMedicationReminder.class, null, 4, null);
                break;
            case 9:
                list$default = getList$default(this, bleKey, BleMedicationAlarm.class, null, 4, null);
                break;
            default:
                list$default = CollectionsKt__CollectionsKt.emptyList();
                break;
        }
        return CollectionsKt___CollectionsKt.toMutableList((Collection) list$default);
    }

    public final int getInt(@NotNull BleKey bleKey, int i, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        int i2 = getMSpUtils().getInt(getKey(bleKey, bleKeyFlag), i);
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache getInt " + INSTANCE.getKey(bleKey, bleKeyFlag) + " -> " + i2);
        return i2;
    }

    @NotNull
    public final <T> List<T> getList(@NotNull BleKey bleKey, @NotNull Class<T> clazz, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        ArrayList arrayList = new ArrayList();
        BleCache bleCache = INSTANCE;
        String string = bleCache.getMSpUtils().getString(bleCache.getKey(bleKey, bleKeyFlag));
        Intrinsics.checkNotNullExpressionValue(string, "mSpUtils.getString(getKey(bleKey, keyFlag))");
        if (!m.isBlank(string)) {
            try {
                JsonArray<JsonElement> asJsonArray = JsonParser.parseString(bleCache.getMSpUtils().getString(bleCache.getKey(bleKey, bleKeyFlag))).getAsJsonArray();
                if (asJsonArray != null) {
                    Intrinsics.checkNotNullExpressionValue(asJsonArray, "asJsonArray");
                    for (JsonElement jsonElement : asJsonArray) {
                        arrayList.add(new Gson().fromJson(jsonElement, (Class<Object>) clazz));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache getList " + INSTANCE.getKey(bleKey, bleKeyFlag) + " -> " + arrayList);
        return arrayList;
    }

    public final long getLong(@NotNull BleKey bleKey, long j, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        long j2 = getMSpUtils().getLong(getKey(bleKey, bleKeyFlag), j);
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache getLong " + INSTANCE.getKey(bleKey, bleKeyFlag) + " -> " + j2);
        return j2;
    }

    @NotNull
    public final String getMAGpsFileUrl() {
        String str = mAGpsFileUrls.get(getMAGpsType());
        return str == null ? "" : str;
    }

    public final int getMAGpsType() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMAGpsType();
        }
        return 0;
    }

    @NotNull
    public final String getMBleAddress() {
        String mBleAddress;
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        return (bleDeviceInfo == null || (mBleAddress = bleDeviceInfo.getMBleAddress()) == null) ? "" : mBleAddress;
    }

    @NotNull
    public final String getMBleCustomName() {
        String mBleCustomName;
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        return (bleDeviceInfo == null || (mBleCustomName = bleDeviceInfo.getMBleCustomName()) == null) ? "" : mBleCustomName;
    }

    @NotNull
    public final String getMBleName() {
        String mBleName;
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        return (bleDeviceInfo == null || (mBleName = bleDeviceInfo.getMBleName()) == null) ? "" : mBleName;
    }

    @NotNull
    public final String getMClassicAddress() {
        String mClassicAddress;
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        return (bleDeviceInfo == null || (mClassicAddress = bleDeviceInfo.getMClassicAddress()) == null) ? "" : mClassicAddress;
    }

    @NotNull
    public final List<Integer> getMDataKeys() {
        List<Integer> mDataKeys;
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        return (bleDeviceInfo == null || (mDataKeys = bleDeviceInfo.getMDataKeys()) == null) ? CollectionsKt__CollectionsKt.emptyList() : mDataKeys;
    }

    @Nullable
    public final BleDeviceInfo getMDeviceInfo() {
        return mDeviceInfo;
    }

    @NotNull
    public final String getMDfuAddress() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo == null || !BluetoothAdapter.checkBluetoothAddress(bleDeviceInfo.getMBleAddress())) {
            return "";
        }
        if (!Intrinsics.areEqual(bleDeviceInfo.getMPlatform(), BleDeviceInfo.PLATFORM_JL) && !Intrinsics.areEqual(bleDeviceInfo.getMPlatform(), BleDeviceInfo.PLATFORM_NORDIC) && (!Intrinsics.areEqual(bleDeviceInfo.getMPlatform(), BleDeviceInfo.PLATFORM_GOODIX) || !Intrinsics.areEqual(bleDeviceInfo.getMPrototype(), BleDeviceInfo.PROTOTYPE_R3Q))) {
            return bleDeviceInfo.getMBleAddress();
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%012X", Arrays.copyOf(new Object[]{Long.valueOf(Long.parseLong(m.replace$default(bleDeviceInfo.getMBleAddress(), ":", "", false, 4, (Object) null), kotlin.text.a.checkRadix(16)) + 1)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return CollectionsKt___CollectionsKt.joinToString$default(StringsKt___StringsKt.chunked(format, 2), ":", null, null, 0, null, null, 62, null);
    }

    @NotNull
    public final String getMFirmwareFlag() {
        String mFirmwareFlag;
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        return (bleDeviceInfo == null || (mFirmwareFlag = bleDeviceInfo.getMFirmwareFlag()) == null) ? "" : mFirmwareFlag;
    }

    public final int getMHideDigitalPower() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMHideDigitalPower();
        }
        return 0;
    }

    public final long getMIOBufferSize() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        Long valueOf = bleDeviceInfo != null ? Long.valueOf(bleDeviceInfo.getMIOBufferSize()) : null;
        if (valueOf == null || valueOf.longValue() == 0) {
            valueOf = Long.valueOf(BleStreamPacket.Companion.getBUFFER_MAX_SIZE());
        }
        return valueOf.longValue();
    }

    @NotNull
    public final String getMPlatform() {
        String mPlatform;
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        return (bleDeviceInfo == null || (mPlatform = bleDeviceInfo.getMPlatform()) == null) ? "" : mPlatform;
    }

    @NotNull
    public final String getMPrototype() {
        String mPrototype;
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        return (bleDeviceInfo == null || (mPrototype = bleDeviceInfo.getMPrototype()) == null) ? "" : mPrototype;
    }

    public final int getMShowAntiLostSwitch() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMShowAntiLostSwitch();
        }
        return 0;
    }

    public final int getMSleepAlgorithmType() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSleepAlgorithmType();
        }
        return 0;
    }

    public final int getMSupport2DAcceleration() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupport2DAcceleration();
        }
        return 0;
    }

    public final int getMSupportAlipay() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportAlipay();
        }
        return 0;
    }

    public final int getMSupportAppSport() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportAppSport();
        }
        return 0;
    }

    public final int getMSupportBloodOxyGenSet() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportBloodOxyGenSet();
        }
        return 0;
    }

    public final int getMSupportChangeClassicBluetoothState() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportChangeClassicBluetoothState();
        }
        return 0;
    }

    public final int getMSupportContactSize() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportContactSize();
        }
        return 0;
    }

    public final int getMSupportDateFormatSet() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportDateFormatSet();
        }
        return 0;
    }

    public final int getMSupportDrinkWaterSet() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportDrinkWaterSet();
        }
        return 0;
    }

    public final int getMSupportFindWatch() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportFindWatch();
        }
        return 0;
    }

    public final int getMSupportHID() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportHID();
        }
        return 0;
    }

    public final int getMSupportHrWarnSet() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportHrWarnSet();
        }
        return 0;
    }

    public final int getMSupportIBeaconSet() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportIBeaconSet();
        }
        return 0;
    }

    public final int getMSupportJLTransport() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportJLTransport();
        }
        return 0;
    }

    public final int getMSupportLoveTap() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportLoveTap();
        }
        return 0;
    }

    public final int getMSupportMedicationAlarm() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportMedicationAlarm();
        }
        return 0;
    }

    public final int getMSupportMedicationReminder() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportMedicationReminder();
        }
        return 0;
    }

    public final int getMSupportNavigation() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportNavigation();
        }
        return 0;
    }

    public final int getMSupportNewsfeed() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportNewsfeed();
        }
        return 0;
    }

    public final int getMSupportNoDisturbSet() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportNoDisturbSet();
        }
        return 0;
    }

    public final int getMSupportPowerSaveMode() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportPowerSaveMode();
        }
        return 0;
    }

    public final int getMSupportQrcode() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportQrcode();
        }
        return 0;
    }

    public final int getMSupportReadDeviceInfo() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportReadDeviceInfo();
        }
        return 0;
    }

    public final int getMSupportReadPackageStatus() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportReadPackageStatus();
        }
        return 0;
    }

    public final int getMSupportRealTimeMeasurement() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportRealTimeMeasurement();
        }
        return 0;
    }

    public final int getMSupportRequestRealtimeWeather() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportRequestRealtimeWeather();
        }
        return 0;
    }

    public final int getMSupportSMSQuickReply() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportSMSQuickReply();
        }
        return 0;
    }

    public final int getMSupportSetWatchPassword() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportSetWatchPassword();
        }
        return 0;
    }

    public final int getMSupportStandbySet() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportStandbySet();
        }
        return 0;
    }

    public final int getMSupportStock() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportStock();
        }
        return 0;
    }

    public final int getMSupportTemperatureUnitSet() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportTemperatureUnitSet();
        }
        return 0;
    }

    public final int getMSupportTuyaKey() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportTuyaKey();
        }
        return 0;
    }

    public final int getMSupportVoice() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportVoice();
        }
        return 0;
    }

    public final int getMSupportWashSet() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportWashSet();
        }
        return 0;
    }

    public final int getMSupportWatchFaceId() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportWatchFaceId();
        }
        return 0;
    }

    public final int getMSupportWeather2() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportWeather2();
        }
        return 0;
    }

    public final int getMSupportWorldClock() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMSupportWorldClock();
        }
        return 0;
    }

    public final int getMWatchFaceType() {
        BleDeviceInfo bleDeviceInfo = mDeviceInfo;
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getMWatchFaceType();
        }
        return 0;
    }

    @NotNull
    public final String getMtkOtaMeta() {
        String string = getMSpUtils().getString(MTK_OTA_META);
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache getMtkOtaMeta -> " + string);
        Intrinsics.checkNotNullExpressionValue(string, "mSpUtils.getString(MTK_O…taMeta -> $it\")\n        }");
        return string;
    }

    @Nullable
    public final <T> T getObject(@NotNull BleKey bleKey, @NotNull Class<T> clazz, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        T t = (T) new Gson().fromJson(getMSpUtils().getString(getKey(bleKey, bleKeyFlag)), (Class<Object>) clazz);
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache getObject " + INSTANCE.getKey(bleKey, bleKeyFlag) + " -> " + t);
        return t;
    }

    public final <T> T getObjectNotNull(@NotNull BleKey bleKey, @NotNull Class<T> clazz, @Nullable T t, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        T t2 = (T) getObject$default(this, bleKey, clazz, null, 4, null);
        if (t2 == null) {
            if (t == null) {
                t = clazz.newInstance();
            }
            BleLog bleLog = BleLog.INSTANCE;
            bleLog.v("BleCache getObjectNotNull " + INSTANCE.getKey(bleKey, bleKeyFlag) + " -> " + t);
            return t;
        }
        return t2;
    }

    @NotNull
    public final String getString(@NotNull BleKey bleKey, @NotNull String def, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(def, "def");
        String string = getMSpUtils().getString(getKey(bleKey, bleKeyFlag), def);
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache getString " + INSTANCE.getKey(bleKey, bleKeyFlag) + " -> " + string);
        Intrinsics.checkNotNullExpressionValue(string, "mSpUtils.getString(getKe…Flag)} -> $it\")\n        }");
        return string;
    }

    public final void putBoolean(@NotNull BleKey bleKey, boolean z, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache putBoolean " + getKey(bleKey, bleKeyFlag) + " -> " + z);
        getMSpUtils().put(getKey(bleKey, bleKeyFlag), z);
    }

    public final void putInt(@NotNull BleKey bleKey, int i, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache putInt " + getKey(bleKey, bleKeyFlag) + " -> " + i);
        getMSpUtils().put(getKey(bleKey, bleKeyFlag), i);
    }

    public final <T> void putList(@NotNull BleKey bleKey, @Nullable List<? extends T> list, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache putList " + getKey(bleKey, bleKeyFlag) + " -> " + list);
        if (list != null && !list.isEmpty()) {
            getMSpUtils().put(getKey(bleKey, bleKeyFlag), new Gson().toJson(list));
        } else {
            remove$default(this, bleKey, null, 2, null);
        }
    }

    public final void putLong(@NotNull BleKey bleKey, long j, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache putLong " + getKey(bleKey, bleKeyFlag) + " -> " + j);
        getMSpUtils().put(getKey(bleKey, bleKeyFlag), j);
    }

    public final void putMtkOtaMeta(@NotNull String meta) {
        Intrinsics.checkNotNullParameter(meta, "meta");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache putMtkOtaMeta  -> " + meta);
        getMSpUtils().put(MTK_OTA_META, meta);
    }

    public final <T> void putObject(@NotNull BleKey bleKey, @Nullable T t, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache putObject " + getKey(bleKey, bleKeyFlag) + " -> " + t);
        if (t == null) {
            remove$default(this, bleKey, null, 2, null);
        } else {
            getMSpUtils().put(getKey(bleKey, bleKeyFlag), new Gson().toJson(t));
        }
    }

    public final void putString(@NotNull BleKey bleKey, @NotNull String value, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(value, "value");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache putString " + getKey(bleKey, bleKeyFlag) + " -> " + value);
        getMSpUtils().put(getKey(bleKey, bleKeyFlag), value);
    }

    public final void remove(@NotNull BleKey bleKey, @Nullable BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleCache remove " + getKey(bleKey, bleKeyFlag));
        getMSpUtils().remove(getKey(bleKey, bleKeyFlag));
    }

    public final boolean requireCache$AndroidSmaBle_debug(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
        int i = WhenMappings.$EnumSwitchMapping$0[bleKey.getMBleCommand().ordinal()];
        if (i != 1) {
            if (i != 2) {
                return false;
            }
            if ((bleKey != BleKey.SCHEDULE || (bleKeyFlag != BleKeyFlag.CREATE && bleKeyFlag != BleKeyFlag.DELETE && bleKeyFlag != BleKeyFlag.UPDATE)) && ((bleKey != BleKey.SMS_QUICK_REPLY_CONTENT || (bleKeyFlag != BleKeyFlag.CREATE && bleKeyFlag != BleKeyFlag.DELETE && bleKeyFlag != BleKeyFlag.UPDATE && bleKeyFlag != BleKeyFlag.RESET)) && ((bleKey != BleKey.WEATHER_REALTIME || bleKeyFlag != BleKeyFlag.UPDATE) && ((bleKey != BleKey.WEATHER_FORECAST || bleKeyFlag != BleKeyFlag.UPDATE) && ((bleKey != BleKey.STOCK || (bleKeyFlag != BleKeyFlag.CREATE && bleKeyFlag != BleKeyFlag.DELETE && bleKeyFlag != BleKeyFlag.UPDATE && bleKeyFlag != BleKeyFlag.RESET)) && ((bleKey != BleKey.WORLD_CLOCK || (bleKeyFlag != BleKeyFlag.CREATE && bleKeyFlag != BleKeyFlag.DELETE && bleKeyFlag != BleKeyFlag.UPDATE && bleKeyFlag != BleKeyFlag.RESET)) && ((bleKey != BleKey.WEATHER_REALTIME2 || bleKeyFlag != BleKeyFlag.UPDATE) && ((bleKey != BleKey.WEATHER_FORECAST2 || bleKeyFlag != BleKeyFlag.UPDATE) && ((bleKey != BleKey.LOGIN_DAYS || bleKeyFlag != BleKeyFlag.UPDATE) && ((bleKey != BleKey.TARGET_COMPLETION || bleKeyFlag != BleKeyFlag.UPDATE) && (bleKey != BleKey.AUDIO_TEXT || bleKeyFlag != BleKeyFlag.UPDATE))))))))))) {
                return false;
            }
        } else if (bleKeyFlag != BleKeyFlag.CREATE && bleKeyFlag != BleKeyFlag.DELETE && bleKeyFlag != BleKeyFlag.UPDATE && bleKeyFlag != BleKeyFlag.RESET) {
            return false;
        }
        return true;
    }

    public final void setMDeviceInfo(@Nullable BleDeviceInfo bleDeviceInfo) {
        mDeviceInfo = bleDeviceInfo;
    }
}
