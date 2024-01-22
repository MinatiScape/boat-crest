package com.coveiot.android.leonardo.utils;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.work.PeriodicWorkRequest;
import com.blankj.utilcode.constant.CacheConstants;
import com.blankj.utilcode.constant.TimeConstants;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.PushPermissionManager;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.coveiot.android.activitymodes.models.CustomMessageConfiguration;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.models.ActivityTypeModel;
import com.coveiot.android.bleabstract.models.ActivityTypes;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.service.ProbeService;
import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.devicemodels.RSAKeysRemoteConfig;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.leonardo.listener.DeviceCapabilityConfigFetchListener;
import com.coveiot.android.leonardo.listener.DeviceRemoteConfigFetchListener;
import com.coveiot.android.leonardo.listener.DeviceRsaKeysFetchListener;
import com.coveiot.android.leonardo.more.models.AutoRecognitonModel;
import com.coveiot.android.leonardo.more.models.SportsType;
import com.coveiot.android.leonardo.probe.ReminderJobServiceJobService;
import com.coveiot.android.leonardo.sp02.preference.Spo2DataManager;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.model.server.ActiveDisplaysEnum;
import com.coveiot.coveaccess.model.server.SwimmingStyleEnum;
import com.coveiot.covedb.deviceinfo.DeviceInfoRepository;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.BatteryOptimizationConfig;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import com.coveiot.covepreferences.data.ScanOnDisconnectCriteria;
import com.coveiot.leaderboard.services.LeaderBoardApiIntentService;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.UtilConstants;
import com.github.mikephil.charting.data.BarEntry;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import com.jstyle.blesdk1860.constant.BleConst;
import com.szabh.smable3.entity.BleNotification;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class PayUtils {
    @NotNull
    public static final PayUtils INSTANCE = new PayUtils();

    /* renamed from: a  reason: collision with root package name */
    public static final int f5431a = 2;
    public static final int b = 1;
    public static final long c = PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;
    public static final String d = PayUtils.class.getSimpleName();
    @NotNull
    public static final String[] e = {AppConstants.SCAN_FILTER_DEVICE_1790.getValue(), AppConstants.SCAN_FILTER_DEVICE_ARM.getValue(), AppConstants.SCAN_FILTER_DEVICE_COVE_BA1009.getValue()};
    @NotNull
    public static final String[] f;
    @NotNull
    public static final String[] g;
    @NotNull
    public static final String[] h;
    @NotNull
    public static final String[] i;
    @NotNull
    public static final String[] j;
    @NotNull
    public static final String[] k;
    @NotNull
    public static final String[] l;
    @NotNull
    public static final String[] m;
    @NotNull
    public static final String[] n;
    @NotNull
    public static final String[] o;
    @NotNull
    public static final String[] p;
    @NotNull
    public static final String[] q;
    @NotNull
    public static final String[] r;
    @NotNull
    public static final String[] s;
    @NotNull
    public static final String[] t;
    @NotNull
    public static final String[] u;
    @NotNull
    public static final String[] v;
    @NotNull
    public static final String[] w;
    @NotNull
    public static final String[] x;

    /* loaded from: classes5.dex */
    public static final class LogShareRunnable implements Runnable {
        @NotNull
        public final Context h;
        @NotNull
        public final File i;

        public LogShareRunnable(@NotNull Context context, @NotNull File fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            this.h = context;
            this.i = fileName;
        }

        @NotNull
        public final Context getContext() {
            return this.h;
        }

        @NotNull
        public final File getFileName() {
            return this.i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Intent intent = new Intent("android.intent.action.SEND");
            Context context = this.h;
            Uri uriForFile = FileProvider.getUriForFile(context, this.h.getApplicationContext().getPackageName() + ".provider", this.i);
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            intent.addFlags(1);
            intent.setDataAndType(uriForFile, this.h.getContentResolver().getType(uriForFile));
            this.h.startActivity(Intent.createChooser(intent, ""));
        }
    }

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DeviceType.values().length];
            try {
                iArr[DeviceType.crpGPF5.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_CALL_PLUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_CONNECT_PLUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ActivityTypes.values().length];
            try {
                iArr2[ActivityTypes.RUN.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ActivityTypes.WALK.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        AppConstants appConstants = AppConstants.SCAN_FILTER_DEVICE_COVE_;
        f = new String[]{AppConstants.SCAN_FILTER_DEVICE_1810G.getValue(), AppConstants.SCAN_FILTER_DEVICE_COVE_BA.getValue(), AppConstants.SCAN_FILTER_DEVICE_C_BA1010V11.getValue(), AppConstants.SCAN_FILTER_DEVICE_COVE9.getValue(), appConstants.getValue(), AppConstants.SCAN_FILTER_DEVICE_CO19.getValue()};
        g = new String[]{AppConstants.SCAN_FILTER_DEVICE_1963D.getValue(), AppConstants.SCAN_FILTER_DEVICE_F_WA1002V11.getValue()};
        h = new String[]{AppConstants.SCAN_FILTER_DEVICE_TX_WA1V1.getValue()};
        AppConstants appConstants2 = AppConstants.SCAN_FILTER_DEVICE;
        i = new String[]{appConstants2.getValue(), AppConstants.SCAN_FILTER_IDOC.getValue(), appConstants.getValue(), AppConstants.SCAN_FILTER_DEVICE_C_.getValue()};
        j = new String[]{appConstants2.getValue(), AppConstants.SCAN_FILTER_v7.getValue(), AppConstants.SCAN_FILTER_v7_BLUERIDGE.getValue(), AppConstants.SCAN_FILTER_v7_OPENAMERICA.getValue(), AppConstants.SCAN_FILTER_v7_COVE.getValue(), AppConstants.SCAN_FILTER_DEVICE_O8_BA1V1.getValue()};
        k = new String[]{appConstants2.getValue(), AppConstants.SCAN_FILTER_DEVICE_SMART_T_TEE.getValue()};
        l = new String[]{AppConstants.SCAN_FILTER_DEVICE_1860.getValue(), AppConstants.SCAN_FILTER_DEVICE_WANDERER.getValue()};
        m = new String[]{AppConstants.SCAN_FILTER_DEVICE_SMA_F2.getValue(), AppConstants.SCAN_FILTER_DEVICE_SMA_MERCURY.getValue()};
        n = new String[]{AppConstants.SCAN_FILTER_DEVICE_SMA_S10.getValue()};
        o = new String[]{AppConstants.SCAN_FILTER_CZ1_COVE.getValue(), AppConstants.SCAN_FILTER_WAVEPRO.getValue()};
        p = new String[]{AppConstants.SCAN_FILTER_WAVEPLUS.getValue(), AppConstants.SCAN_FILTER_XTENDSPORT.getValue()};
        q = new String[]{AppConstants.SCAN_FILTER_WAVEPRIME.getValue()};
        r = new String[]{AppConstants.SCAN_FILTER_DEVICE_MOYANG_VERTEX.getValue()};
        s = new String[]{AppConstants.SCAN_FILTER_DEVICE_MOYANG_WAVEFIT.getValue()};
        t = new String[]{AppConstants.SCAN_FILTER_DEVICE_SMA_S12.getValue(), AppConstants.SCAN_FILTER_DEVICE_SMA_S12_2.getValue()};
        u = new String[]{AppConstants.SCAN_FILTER_DEVICE_MATRIX_LA07.getValue(), AppConstants.SCAN_FILTER_DEVICE_MATRIX.getValue()};
        v = new String[]{AppConstants.SCAN_FILTER_CA_0.getValue()};
        AppConstants appConstants3 = AppConstants.SCAN_FILTER_CA_3;
        w = new String[]{appConstants3.getValue(), AppConstants.SCAN_FILTER_STORMPRO.getValue()};
        x = new String[]{appConstants3.getValue(), AppConstants.SCAN_FILTER_XTENDPRO.getValue()};
    }

    public static final void A(FirebaseRemoteConfig remoteConfig, DeviceCapabilityConfigFetchListener deviceCapabilityConfigFetchListener, Void r3, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(ThemeConstants.REMOTE_CONFIG_DEVICE_CAPABILITY_CONFIG.getValue());
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…_CAPABILITY_CONFIG.value)");
        DeviceRemoteConfig deviceRemoteConfig = (DeviceRemoteConfig) new Gson().fromJson(string, (Class<Object>) DeviceRemoteConfig.class);
        if (deviceCapabilityConfigFetchListener != null) {
            deviceCapabilityConfigFetchListener.onSuccess(deviceRemoteConfig);
        }
        String str = d;
        LogHelper.d(str, "Capability Config params updated: " + r3 + string);
    }

    public static final void B(DeviceRemoteConfigFetchListener deviceRemoteConfigFetchListener, Context context, Exception it) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(it, "it");
        if (deviceRemoteConfigFetchListener != null) {
            deviceRemoteConfigFetchListener.onFailure(context.getString(R.string.some_thing_went_wrong));
        }
    }

    public static final void C(final FirebaseRemoteConfig remoteConfig, final DeviceRemoteConfigFetchListener deviceRemoteConfigFetchListener, final String key, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r5 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.f
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    PayUtils.D(FirebaseRemoteConfig.this, key, deviceRemoteConfigFetchListener, r5, task2);
                }
            });
            return;
        }
        LogHelper.e(d, "Remote Config Failed");
        if (deviceRemoteConfigFetchListener != null) {
            deviceRemoteConfigFetchListener.onSuccess(null);
        }
    }

    public static final void D(FirebaseRemoteConfig remoteConfig, String key, DeviceRemoteConfigFetchListener deviceRemoteConfigFetchListener, Void r4, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(key);
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(key)");
        DeviceRemoteConfig deviceRemoteConfig = (DeviceRemoteConfig) new Gson().fromJson(string, (Class<Object>) DeviceRemoteConfig.class);
        if (deviceRemoteConfigFetchListener != null) {
            deviceRemoteConfigFetchListener.onSuccess(deviceRemoteConfig);
        }
        String str = d;
        LogHelper.d(str, "Config params updated: " + r4 + ' ' + string);
    }

    public static final void E(Ref.ObjectRef mFirebaseRemoteConfig, Context context, Task task) {
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Void r3 = (Void) task.getResult();
            ((FirebaseRemoteConfig) mFirebaseRemoteConfig.element).getBoolean("isGoogleFitHrSPO2TempSleepSupportAvailable");
            UserDataManager.getInstance(context).saveIsGoogleFitHrSPO2TempSleepSupportAvailable(Boolean.valueOf(((FirebaseRemoteConfig) mFirebaseRemoteConfig.element).getBoolean("isGoogleFitHrSPO2TempSleepSupportAvailable")));
        }
    }

    public static final void F(final FirebaseRemoteConfig remoteConfig, final DeviceRsaKeysFetchListener deviceRsaKeysFetchListener, final String key, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r5 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.g
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    PayUtils.G(FirebaseRemoteConfig.this, key, deviceRsaKeysFetchListener, r5, task2);
                }
            });
            return;
        }
        LogHelper.e(d, "Remote Config Failed");
        if (deviceRsaKeysFetchListener != null) {
            deviceRsaKeysFetchListener.onSuccess(null);
        }
    }

    public static final void G(FirebaseRemoteConfig remoteConfig, String key, DeviceRsaKeysFetchListener deviceRsaKeysFetchListener, Void r4, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(key);
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(key)");
        RSAKeysRemoteConfig rSAKeysRemoteConfig = (RSAKeysRemoteConfig) new Gson().fromJson(string, (Class<Object>) RSAKeysRemoteConfig.class);
        if (deviceRsaKeysFetchListener != null) {
            deviceRsaKeysFetchListener.onSuccess(rSAKeysRemoteConfig);
        }
        String str = d;
        LogHelper.d(str, "Config params updated: " + r4 + ' ' + string);
    }

    public static final void H(DeviceRsaKeysFetchListener deviceRsaKeysFetchListener, Context context, Exception it) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(it, "it");
        if (deviceRsaKeysFetchListener != null) {
            deviceRsaKeysFetchListener.onFailure(context.getString(R.string.some_thing_went_wrong));
        }
    }

    public static final void I(Exception it) {
        Intrinsics.checkNotNullParameter(it, "it");
    }

    public static final void J(final FirebaseRemoteConfig remoteConfig, final Context mContext, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(mContext, "$mContext");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r4 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.r
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    PayUtils.K(FirebaseRemoteConfig.this, mContext, r4, task2);
                }
            });
            return;
        }
        LogHelper.e(d, "Remote Config Failed");
    }

    public static final void K(FirebaseRemoteConfig remoteConfig, Context mContext, Void r3, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(mContext, "$mContext");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(ThemeConstants.BATTERY_OPTIMIZATION_CONFIG.getValue());
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…PTIMIZATION_CONFIG.value)");
        BatteryOptimizationConfig batteryOptimizationConfig = (BatteryOptimizationConfig) new Gson().fromJson(string, (Class<Object>) BatteryOptimizationConfig.class);
        if (batteryOptimizationConfig != null) {
            UserDataManager.getInstance(mContext).saveBatteryOptimizationConfig(batteryOptimizationConfig);
        }
        String str = d;
        LogHelper.d(str, "Config params updated: " + r3 + ' ' + string);
    }

    public static final void L(Exception it) {
        Intrinsics.checkNotNullParameter(it, "it");
    }

    public static final void M(final FirebaseRemoteConfig remoteConfig, final Context mContext, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(mContext, "$mContext");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r4 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.s
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    PayUtils.N(FirebaseRemoteConfig.this, mContext, r4, task2);
                }
            });
            return;
        }
        LogHelper.e(d, "Remote Config Failed");
    }

    public static final void N(FirebaseRemoteConfig remoteConfig, Context mContext, Void r3, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(mContext, "$mContext");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(ThemeConstants.FITNESS_CHALLENGES.getValue());
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…FITNESS_CHALLENGES.value)");
        FitnessChallengeRemoteConfiguration fitnessChallengeRemoteConfiguration = (FitnessChallengeRemoteConfiguration) new Gson().fromJson(string, (Class<Object>) FitnessChallengeRemoteConfiguration.class);
        if (fitnessChallengeRemoteConfiguration != null) {
            FitnessChallengeSessionManager.getInstance(mContext).saveFitnessChallengeRemoteConfig(fitnessChallengeRemoteConfiguration);
        }
        String str = d;
        LogHelper.d(str, "Config params updated: " + r3 + ' ' + string);
    }

    public static final void O(BoatCoinsFirebaseConfigResultListener listener, Exception it) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(it, "it");
        listener.onFailure(null);
    }

    public static final void P(final FirebaseRemoteConfig remoteConfig, final BoatCoinsFirebaseConfigResultListener listener, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Void r3 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.x
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    PayUtils.Q(FirebaseRemoteConfig.this, listener, task2);
                }
            });
        }
    }

    public static final void Q(FirebaseRemoteConfig remoteConfig, BoatCoinsFirebaseConfigResultListener listener, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(it, "it");
        listener.onResult(remoteConfig.getBoolean(ThemeConstants.BOAT_COINS_VISIBILITY.getValue()));
    }

    @JvmStatic
    public static final long convertStringDateTimeToMilliSeconds(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        TimeZone.getDefault().getRawOffset();
        try {
            Date parse = simpleDateFormat.parse(date);
            Intrinsics.checkNotNull(parse, "null cannot be cast to non-null type java.util.Date");
            return parse.getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    @JvmStatic
    public static final long convertStringDateTimeToMilliSeconds2(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        TimeZone.getDefault().getRawOffset();
        try {
            Date parse = simpleDateFormat.parse(date);
            Intrinsics.checkNotNull(parse, "null cannot be cast to non-null type java.util.Date");
            return parse.getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    @JvmStatic
    @NotNull
    public static final String currentDayString() {
        String str;
        Date time = Calendar.getInstance().getTime();
        Intrinsics.checkNotNullExpressionValue(time, "getInstance().time");
        try {
            str = AppUtils.getSimpleDateFormat("dd MMMM yyyy").format(time);
        } catch (ParseException e2) {
            e2.printStackTrace();
            str = null;
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    @JvmStatic
    public static final void fetchBadgesInBackground(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        new Thread(new Runnable() { // from class: com.coveiot.android.leonardo.utils.q
            @Override // java.lang.Runnable
            public final void run() {
                PayUtils.x(context);
            }
        }).start();
    }

    @JvmStatic
    @NotNull
    public static final String getAmPmHourValue(@NotNull String hourValue) {
        int parseInt;
        List emptyList;
        boolean z;
        Intrinsics.checkNotNullParameter(hourValue, "hourValue");
        if (StringsKt__StringsKt.contains$default((CharSequence) hourValue, (CharSequence) ":", false, 2, (Object) null)) {
            List<String> split = new Regex(":").split(hourValue, 0);
            if (!split.isEmpty()) {
                ListIterator<String> listIterator = split.listIterator(split.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() == 0) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (!z) {
                        emptyList = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            parseInt = Integer.parseInt(((String[]) emptyList.toArray(new String[0]))[0]);
        } else {
            parseInt = Integer.parseInt(hourValue);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, parseInt);
        calendar.get(10);
        String valueOf = String.valueOf(calendar.get(10));
        if (valueOf.equals(BleConst.GetDeviceTime)) {
            valueOf = BleConst.CMD_Reset;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(valueOf);
        sb.append(calendar.get(9) == 0 ? " AM" : " PM");
        return sb.toString();
    }

    @JvmStatic
    @NotNull
    public static final String getAmbientSoundInfo(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        boolean z = false;
        if (i2 >= 0 && i2 < 81) {
            z = true;
        }
        if (z) {
            String string = context.getResources().getString(R.string.normal);
            Intrinsics.checkNotNullExpressionValue(string, "{\n                contex…ing.normal)\n            }");
            return string;
        }
        String string2 = context.getResources().getString(R.string.high);
        Intrinsics.checkNotNullExpressionValue(string2, "{\n                contex…tring.high)\n            }");
        return string2;
    }

    @JvmStatic
    @NotNull
    public static final String getDate() {
        String format = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(cal.time)");
        return format;
    }

    @JvmStatic
    @NotNull
    public static final String getDateDailyFormat(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        try {
            String format = AppUtils.getSimpleDateFormat("dd MMMM yyyy").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(date));
            Intrinsics.checkNotNullExpressionValue(format, "spf.format(newDate)");
            return format;
        } catch (ParseException unused) {
            return date;
        }
    }

    @JvmStatic
    @NotNull
    public static final String getDateWeeklyFormatDefault() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.set(7, 2);
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM");
        String format = simpleDateFormat.format(calendar.getTime());
        calendar.add(5, 6);
        calendar.set(7, 1);
        String format2 = simpleDateFormat.format(calendar.getTime());
        return format + " - " + format2;
    }

    @JvmStatic
    @NotNull
    public static final String getDayMonthFormatDate(@Nullable String str) {
        String format = AppUtils.getSimpleDateFormat("dd/MM").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format…ateFormat.parse(strDate))");
        return format;
    }

    @JvmStatic
    @NotNull
    public static final String getHRVRange(int i2) {
        boolean z = true;
        if (i2 >= 0 && i2 < 30) {
            return RrHrHelperKt.f5433a;
        }
        if (30 <= i2 && i2 < 60) {
            return RrHrHelperKt.b;
        }
        if (60 <= i2 && i2 < 100) {
            return "Good";
        }
        if (100 > i2 || i2 >= 301) {
            z = false;
        }
        return z ? "Excellent" : RrHrHelperKt.b;
    }

    @JvmStatic
    @NotNull
    public static final String getMonthFormatDate(@Nullable String str) {
        String format = AppUtils.getSimpleDateFormat("MMM").format(AppUtils.getSimpleDateFormat("MM").parse(str));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format…ateFormat.parse(strDate))");
        return format;
    }

    @JvmStatic
    @NotNull
    public static final ArrayList<Double> getNonZeroDoubleList(@Nullable List<Double> list) {
        ArrayList<Double> arrayList = new ArrayList<>();
        if (list != null && (!list.isEmpty())) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (list.get(i2) != null) {
                    if (!(list.get(i2).doubleValue() == 0.0d)) {
                        arrayList.add(list.get(i2));
                    }
                }
            }
        }
        return arrayList;
    }

    @JvmStatic
    @NotNull
    public static final ArrayList<Integer> getNonZeroIntList(@Nullable List<Integer> list) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (list != null && (!list.isEmpty())) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (list.get(i2) != null && list.get(i2).intValue() != 0) {
                    arrayList.add(list.get(i2));
                }
            }
        }
        return arrayList;
    }

    @JvmStatic
    public static final int getPixelsFromDp(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (int) TypedValue.applyDimension(1, i2, context.getResources().getDisplayMetrics());
    }

    @JvmStatic
    @NotNull
    public static final String getSleepScoreInfo(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        boolean z = true;
        if (DeviceUtils.Companion.isIDODevice(context)) {
            if (i2 >= 0 && i2 < 50) {
                String string = context.getResources().getString(R.string.bad);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.bad)");
                return string;
            }
            if (50 <= i2 && i2 < 70) {
                String string2 = context.getResources().getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.ok)");
                return string2;
            }
            if (70 <= i2 && i2 < 85) {
                String string3 = context.getResources().getString(R.string.good);
                Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getString(R.string.good)");
                return string3;
            }
            if (85 > i2 || i2 >= 101) {
                z = false;
            }
            if (z) {
                String string4 = context.getResources().getString(R.string.perfect);
                Intrinsics.checkNotNullExpressionValue(string4, "context.resources.getString(R.string.perfect)");
                return string4;
            }
        } else {
            if (i2 >= 0 && i2 < 61) {
                String string5 = context.getResources().getString(R.string.sleep_score_dynamic_info1);
                Intrinsics.checkNotNullExpressionValue(string5, "context.resources.getStr…leep_score_dynamic_info1)");
                return string5;
            }
            if (61 <= i2 && i2 < 71) {
                String string6 = context.getResources().getString(R.string.sleep_score_dynamic_info2);
                Intrinsics.checkNotNullExpressionValue(string6, "context.resources.getStr…leep_score_dynamic_info2)");
                return string6;
            }
            if (71 <= i2 && i2 < 81) {
                String string7 = context.getResources().getString(R.string.sleep_score_dynamic_info3);
                Intrinsics.checkNotNullExpressionValue(string7, "context.resources.getStr…leep_score_dynamic_info3)");
                return string7;
            }
            if (81 <= i2 && i2 < 91) {
                String string8 = context.getResources().getString(R.string.sleep_score_dynamic_info4);
                Intrinsics.checkNotNullExpressionValue(string8, "context.resources.getStr…leep_score_dynamic_info4)");
                return string8;
            }
            if (90 > i2 || i2 >= 101) {
                z = false;
            }
            if (z) {
                String string9 = context.getResources().getString(R.string.sleep_score_dynamic_info5);
                Intrinsics.checkNotNullExpressionValue(string9, "context.resources.getStr…leep_score_dynamic_info5)");
                return string9;
            }
        }
        return "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001a, code lost:
        if (r2.equals("Relax") == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
        if (r2.equals("Mild") == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002c, code lost:
        if (r2.equals(com.coveiot.android.leonardo.utils.RrHrHelperKt.c) == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0034, code lost:
        return 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003b, code lost:
        if (r2.equals("Moderate") == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0044, code lost:
        if (r2.equals("Relaxed") == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004c, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0053, code lost:
        if (r2.equals(com.coveiot.android.leonardo.utils.RrHrHelperKt.b) == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005b, code lost:
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0062, code lost:
        if (r2.equals("Medium") == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006a, code lost:
        return 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0011, code lost:
        if (r2.equals("Overstress") != false) goto L7;
     */
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Integer getStressIndexExcludingZero(@org.jetbrains.annotations.Nullable java.lang.String r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L6b
            int r1 = r2.hashCode()
            switch(r1) {
                case -1994163307: goto L5c;
                case -1955878649: goto L4d;
                case -1539816689: goto L3e;
                case -554213085: goto L35;
                case 2249154: goto L26;
                case 2398260: goto L1d;
                case 78844528: goto L14;
                case 258440104: goto Lb;
                default: goto La;
            }
        La:
            goto L6b
        Lb:
            java.lang.String r1 = "Overstress"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L6b
            goto L2f
        L14:
            java.lang.String r1 = "Relax"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L47
            goto L6b
        L1d:
            java.lang.String r1 = "Mild"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L56
            goto L6b
        L26:
            java.lang.String r1 = "High"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L2f
            goto L6b
        L2f:
            r2 = 3
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            return r2
        L35:
            java.lang.String r1 = "Moderate"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L65
            goto L6b
        L3e:
            java.lang.String r1 = "Relaxed"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L47
            goto L6b
        L47:
            r2 = 0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            return r2
        L4d:
            java.lang.String r1 = "Normal"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L56
            goto L6b
        L56:
            r2 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            return r2
        L5c:
            java.lang.String r1 = "Medium"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L65
            goto L6b
        L65:
            r2 = 2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            return r2
        L6b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.utils.PayUtils.getStressIndexExcludingZero(java.lang.String):java.lang.Integer");
    }

    @JvmStatic
    @NotNull
    public static final String getStressRange(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        boolean z = true;
        if (companion.isSmaDevice(context)) {
            if (companion.isSmaJieieDevice(context)) {
                if (!(1 <= i2 && i2 < 26)) {
                    if (!(26 <= i2 && i2 < 51)) {
                        if (!(51 <= i2 && i2 < 76)) {
                            if (76 > i2 || i2 >= 101) {
                                z = false;
                            }
                            if (z) {
                                return "Overstress";
                            }
                        }
                        return "Moderate";
                    }
                    return RrHrHelperKt.b;
                }
                return "Relax";
            }
            if (!(1 <= i2 && i2 < 26)) {
                if (!(26 <= i2 && i2 < 51)) {
                    if (!(51 <= i2 && i2 < 76)) {
                        if (76 > i2 || i2 >= 101) {
                            z = false;
                        }
                        if (z) {
                            return RrHrHelperKt.c;
                        }
                    }
                    return "Moderate";
                }
                return RrHrHelperKt.b;
            }
            return "Relax";
        } else if (!companion.isCADevice(context) && !companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
            if (1 <= i2 && i2 < 30) {
                return "Relax";
            }
            if (30 <= i2 && i2 < 60) {
                return RrHrHelperKt.b;
            }
            if (60 <= i2 && i2 < 80) {
                return "Medium";
            }
            if (80 > i2 || i2 >= 100) {
                z = false;
            }
            return z ? RrHrHelperKt.c : RrHrHelperKt.f5433a;
        } else {
            if (!(1 <= i2 && i2 < 26)) {
                if (26 <= i2 && i2 < 51) {
                    return "Mild";
                }
                if (51 <= i2 && i2 < 76) {
                    return "Moderate";
                }
                if (76 > i2 || i2 >= 101) {
                    z = false;
                }
                if (z) {
                    return RrHrHelperKt.c;
                }
            }
            return "Relaxed";
        }
    }

    @JvmStatic
    public static final int getStressRangeColor(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        boolean z = true;
        if (!companion.isSmaDevice(context) && !companion.isCADevice(context) && !companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
            int color = context.getColor(R.color.relaxed_stress_color);
            if (1 <= i2 && i2 < 30) {
                return context.getColor(R.color.relaxed_stress_color);
            }
            if (30 <= i2 && i2 < 60) {
                return context.getColor(R.color.mild_stress_color);
            }
            if (60 <= i2 && i2 < 80) {
                return context.getColor(R.color.moderate_stress_color);
            }
            if (80 > i2 || i2 >= 100) {
                z = false;
            }
            return z ? context.getColor(R.color.high_stress_color) : color;
        }
        int color2 = context.getColor(R.color.relaxed_stress_color);
        if (1 <= i2 && i2 < 26) {
            return context.getColor(R.color.relaxed_stress_color);
        }
        if (26 <= i2 && i2 < 51) {
            return context.getColor(R.color.mild_stress_color);
        }
        if (51 <= i2 && i2 < 76) {
            return context.getColor(R.color.moderate_stress_color);
        }
        if (76 > i2 || i2 >= 101) {
            z = false;
        }
        return z ? context.getColor(R.color.high_stress_color) : color2;
    }

    @JvmStatic
    @Nullable
    public static final String getStressRangeExcludingZero(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        boolean z = true;
        if (companion.isSmaDevice(context)) {
            if (companion.isSmaJieieDevice(context)) {
                if (1 <= i2 && i2 < 26) {
                    return "Relax";
                }
                if (!(26 <= i2 && i2 < 51)) {
                    if (!(51 <= i2 && i2 < 76)) {
                        if (76 > i2 || i2 >= 101) {
                            z = false;
                        }
                        if (z) {
                            return "Overstress";
                        }
                        return null;
                    }
                    return "Moderate";
                }
                return RrHrHelperKt.b;
            }
            if (1 <= i2 && i2 < 26) {
                return "Relaxed";
            }
            if (!(26 <= i2 && i2 < 51)) {
                if (!(51 <= i2 && i2 < 76)) {
                    if (76 > i2 || i2 >= 101) {
                        z = false;
                    }
                    if (z) {
                        return RrHrHelperKt.c;
                    }
                    return null;
                }
                return "Moderate";
            }
            return RrHrHelperKt.b;
        } else if (companion.isCADevice(context) || companion.isCYDevice(context) || companion.isPS1Device(context) || companion.isBESDevice(context)) {
            if (1 <= i2 && i2 < 26) {
                return "Relaxed";
            }
            if (26 <= i2 && i2 < 51) {
                return "Mild";
            }
            if (51 <= i2 && i2 < 76) {
                return "Moderate";
            }
            if (76 > i2 || i2 >= 101) {
                z = false;
            }
            if (z) {
                return RrHrHelperKt.c;
            }
            return null;
        } else {
            if (1 <= i2 && i2 < 30) {
                return "Relax";
            }
            if (30 <= i2 && i2 < 60) {
                return RrHrHelperKt.b;
            }
            if (60 <= i2 && i2 < 80) {
                return "Medium";
            }
            if (80 > i2 || i2 >= 100) {
                z = false;
            }
            if (z) {
                return RrHrHelperKt.c;
            }
            return null;
        }
    }

    @JvmStatic
    public static final double getTemperatureInCelsius(double d2) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        String format = decimalFormat.format(((d2 - 32) * 5) / 9);
        Intrinsics.checkNotNullExpressionValue(format, "df2.format((temperature - 32) * 5 / 9)");
        return Double.parseDouble(format);
    }

    @JvmStatic
    public static final double getTemperatureInFahrenheit(double d2) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        String format = decimalFormat.format((d2 * 1.8d) + 32);
        Intrinsics.checkNotNullExpressionValue(format, "df2.format(temperature * 1.8 + 32)");
        return Double.parseDouble(format);
    }

    @JvmStatic
    @Nullable
    public static final String getTodayYesterdayDate(@Nullable Context context, @NotNull String strType, @NotNull String strDate, int i2) {
        Resources resources;
        Resources resources2;
        Resources resources3;
        Intrinsics.checkNotNullParameter(strType, "strType");
        Intrinsics.checkNotNullParameter(strDate, "strDate");
        String str = null;
        String str2 = "";
        if (Intrinsics.areEqual(strType, AppConstants.DAY.getValue())) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
            Calendar calendar = Calendar.getInstance();
            try {
                str2 = simpleDateFormat2.format(simpleDateFormat.parse(strDate));
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            if (kotlin.text.m.equals(simpleDateFormat.format(calendar.getTime()), strDate, true)) {
                str2 = (context == null || (resources3 = context.getResources()) == null) ? null : resources3.getString(R.string.today);
            }
            calendar.add(5, -1);
            if (kotlin.text.m.equals(simpleDateFormat.format(calendar.getTime()), strDate, true)) {
                if (context != null && (resources2 = context.getResources()) != null) {
                    str = resources2.getString(R.string.yesterday);
                }
                return str;
            }
            return str2;
        } else if (Intrinsics.areEqual(strType, AppConstants.WEEK.getValue())) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setFirstDayOfWeek(2);
            calendar2.set(1, i2);
            calendar2.set(3, Integer.parseInt(strDate));
            calendar2.add(5, -(calendar2.get(7) - 2));
            Date time = calendar2.getTime();
            calendar2.add(5, 6);
            Date time2 = calendar2.getTime();
            SimpleDateFormat simpleDateFormat3 = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
            StringBuilder sb = new StringBuilder();
            if (context != null && (resources = context.getResources()) != null) {
                str = resources.getString(R.string.week);
            }
            sb.append(str);
            sb.append(AppConstants.EMPTY_SPACE.getValue());
            sb.append(strDate);
            String sb2 = sb.toString();
            return sb2 + '\n' + simpleDateFormat3.format(time) + " - " + simpleDateFormat3.format(time2);
        } else if (Intrinsics.areEqual(strType, AppConstants.MONTH.getValue())) {
            try {
                return AppUtils.getSimpleDateFormat("MMMM yyyy").format(AppUtils.getSimpleDateFormat("yyyy-MM").parse(strDate));
            } catch (ParseException e3) {
                e3.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    public static /* synthetic */ String getTodayYesterdayDate$default(Context context, String str, String str2, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return getTodayYesterdayDate(context, str, str2, i2);
    }

    @JvmStatic
    @NotNull
    public static final String getTodayYesterdayStringFromTimeStamp(long j2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        if (j2 != 0) {
            if (DateUtils.isToday(j2)) {
                return String.valueOf(context.getResources().getString(R.string.today));
            }
            if (INSTANCE.S(j2)) {
                return String.valueOf(context.getResources().getString(R.string.yesterday));
            }
            return String.valueOf(simpleDateFormat.format(new Date(j2)));
        }
        return "";
    }

    @JvmStatic
    @NotNull
    public static final String getTodayYesterdayStringWithTimeStamp(long j2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (j2 != 0) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("hh:mm a");
            if (DateUtils.isToday(j2)) {
                return context.getResources().getString(R.string.today_small) + ", " + simpleDateFormat2.format(new Date(j2));
            } else if (INSTANCE.S(j2)) {
                return context.getResources().getString(R.string.yesterday_small) + ", " + simpleDateFormat2.format(new Date(j2));
            } else {
                return simpleDateFormat.format(new Date(j2)) + ' ' + simpleDateFormat2.format(new Date(j2));
            }
        }
        return "";
    }

    @JvmStatic
    @NotNull
    public static final String getTodayYesterdayStringWithTimeStampAt(long j2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (j2 != 0) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("hh:mm a");
            if (DateUtils.isToday(j2)) {
                return context.getResources().getString(R.string.today) + " at " + simpleDateFormat2.format(new Date(j2));
            } else if (INSTANCE.S(j2)) {
                return context.getResources().getString(R.string.yesterday) + " at " + simpleDateFormat2.format(new Date(j2));
            } else {
                return simpleDateFormat.format(new Date(j2)) + " at " + simpleDateFormat2.format(new Date(j2));
            }
        }
        return "";
    }

    @JvmStatic
    @NotNull
    public static final String getTodayYesterdayStringWithTimeStampAtddMMMYYYY(long j2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (j2 != 0) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM YYYY, hh:mm aa");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("hh:mm aa");
            if (DateUtils.isToday(j2)) {
                return context.getResources().getString(R.string.today) + ", " + simpleDateFormat2.format(new Date(j2));
            } else if (INSTANCE.S(j2)) {
                return context.getResources().getString(R.string.yesterday) + ", " + simpleDateFormat2.format(new Date(j2));
            } else {
                return String.valueOf(simpleDateFormat.format(new Date(j2)));
            }
        }
        return "";
    }

    @JvmStatic
    @NotNull
    public static final String getWeeklyFormatGraph(int i2, @NotNull String year) {
        Intrinsics.checkNotNullParameter(year, "year");
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1, Integer.parseInt(year));
        calendar.set(3, i2);
        calendar.set(7, 2);
        String format = AppUtils.getSimpleDateFormat("dd").format(calendar.getTime());
        calendar.add(5, 6);
        calendar.set(7, 1);
        String format2 = AppUtils.getSimpleDateFormat("dd MMM").format(calendar.getTime());
        return format + " - " + format2;
    }

    @JvmStatic
    public static final void isBoatCoinsEnabled(@NotNull Context context, @NotNull final BoatCoinsFirebaseConfigResultListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        try {
            final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
            remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.utils.m
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    PayUtils.O(BoatCoinsFirebaseConfigResultListener.this, exc);
                }
            }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.y
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    PayUtils.P(FirebaseRemoteConfig.this, listener, task);
                }
            });
        } catch (Exception unused) {
            listener.onFailure(null);
        }
    }

    @JvmStatic
    public static final boolean isEmpty(@Nullable List<?> list) {
        return list == null || list.size() == 0;
    }

    @JvmStatic
    public static final boolean isIDODeviceSupportsBTCall(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return kotlin.text.m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_connect), false);
    }

    @JvmStatic
    @Nullable
    public static final Boolean parseBoolean(@Nullable String str, @Nullable Boolean bool) {
        if (str == null) {
            return bool;
        }
        try {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        } catch (NumberFormatException unused) {
            return bool;
        }
    }

    @JvmStatic
    public static final float parseFloat(@Nullable String str, float f2) {
        if (str == null) {
            return f2;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f2;
        }
    }

    @JvmStatic
    public static final int parseInt(@Nullable String str, int i2) {
        if (str == null) {
            return i2;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i2;
        }
    }

    @JvmStatic
    public static final long parseLong(@Nullable String str, long j2) {
        if (str == null) {
            return j2;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j2;
        }
    }

    @JvmStatic
    @NotNull
    public static final String previousDayString() {
        String str;
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -1);
        try {
            str = AppUtils.getSimpleDateFormat("dd MMMM yyyy").format(calendar.getTime());
        } catch (ParseException e2) {
            e2.printStackTrace();
            str = null;
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v4, types: [T, java.lang.Object] */
    public static final void v(FirebaseRemoteConfig remoteConfig, Ref.ObjectRef customMessageConfiguration, Context context, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(customMessageConfiguration, "$customMessageConfiguration");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            String string = remoteConfig.getString(WorkoutConstants.CUSTOM_MESSAGE_CONFIGURATION);
            Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(W…OM_MESSAGE_CONFIGURATION)");
            ?? fromJson = new Gson().fromJson(string, (Class<??>) CustomMessageConfiguration.class);
            customMessageConfiguration.element = fromJson;
            PreferenceManager.saveCustomMessageConfiguration(context, (CustomMessageConfiguration) fromJson);
            String str = d;
            LogHelper.d(str, "Config params updated:  " + string);
            return;
        }
        String string2 = remoteConfig.getString(WorkoutConstants.CUSTOM_MESSAGE_CONFIGURATION);
        Intrinsics.checkNotNullExpressionValue(string2, "remoteConfig.getString(W…OM_MESSAGE_CONFIGURATION)");
        ?? fromJson2 = new Gson().fromJson(string2, (Class<??>) CustomMessageConfiguration.class);
        customMessageConfiguration.element = fromJson2;
        PreferenceManager.saveCustomMessageConfiguration(context, (CustomMessageConfiguration) fromJson2);
    }

    public static final void x(Context context) {
        Intrinsics.checkNotNullParameter(context, "$context");
        try {
            Intent intent = new Intent(context, LeaderBoardApiIntentService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e2, context);
        }
    }

    public static final void y(DeviceCapabilityConfigFetchListener deviceCapabilityConfigFetchListener, Context context, Exception it) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(it, "it");
        if (deviceCapabilityConfigFetchListener != null) {
            deviceCapabilityConfigFetchListener.onFailure(context.getString(R.string.some_thing_went_wrong));
        }
    }

    public static final void z(final FirebaseRemoteConfig remoteConfig, final DeviceCapabilityConfigFetchListener deviceCapabilityConfigFetchListener, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r4 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.u
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    PayUtils.A(FirebaseRemoteConfig.this, deviceCapabilityConfigFetchListener, r4, task2);
                }
            });
            return;
        }
        LogHelper.e(d, "Capability Config Failed");
        if (deviceCapabilityConfigFetchListener != null) {
            deviceCapabilityConfigFetchListener.onSuccess(null);
        }
    }

    public final boolean R(Context context) {
        DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
        int i2 = deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()];
        return i2 == 2 || i2 == 3;
    }

    public final boolean S(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j2));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(6, -1);
        return calendar.get(6) == calendar2.get(6);
    }

    public final double calculateCaloriesForCZ(int i2, int i3, int i4, int i5) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(((float) ((i2 * i4) / (160934.0d / (i3 * 0.413d)))) * 0.9f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return Double.parseDouble(format);
    }

    public final int calculateDistance(int i2, int i3, int i4) {
        if (i4 <= 0) {
            i4 = (int) (i3 * 0.413d);
        }
        return (int) (((i4 * i2) * 1.0f) / 100);
    }

    public final double calculationFormulaHRV(double d2) {
        if (d2 > 0.0d) {
            return Math.exp((((d2 - 3.4646797d) * 0.8099999999999996d) / 2.6128658999999996d) + 2.95d);
        }
        return 1.0d;
    }

    public final boolean canDeviceHandleGalleryExternal(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI).resolveActivity(context.getPackageManager()) != null;
    }

    public final boolean canDeviceHandleGalleryInternal(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI).resolveActivity(context.getPackageManager()) != null;
    }

    public final void cancelReminderJob(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            ((JobScheduler) context.getSystemService(JobScheduler.class)).cancel(b);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @NotNull
    public final String capatalizeFirstLetter(@NotNull String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        StringBuilder sb = new StringBuilder();
        String substring = text.substring(0, 1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        String upperCase = substring.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
        sb.append(upperCase);
        String substring2 = text.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
        String lowerCase = substring2.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        sb.append(lowerCase);
        return sb.toString();
    }

    public final void checkCustomMessageConfiguration(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseRemoteConfig, "getInstance()");
        FirebaseRemoteConfigSettings build = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0L).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…s(0)\n            .build()");
        firebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_details);
        firebaseRemoteConfig.setConfigSettingsAsync(build);
        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.h
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                PayUtils.v(FirebaseRemoteConfig.this, objectRef, context, task);
            }
        });
    }

    public final boolean checkIfBluetoothPermissionExists(@Nullable Context context) {
        String[] unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(context, new String[]{"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"});
        Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
        return unGrantedPermissions.length == 0;
    }

    public final boolean checkIfNotificationPermissionExists(@Nullable Context context) {
        if (Build.VERSION.SDK_INT >= 33) {
            String[] unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(context, new String[]{PushPermissionManager.ANDROID_PERMISSION_STRING});
            Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
            return unGrantedPermissions.length == 0;
        }
        return true;
    }

    public final boolean checkIfScanOnDisConnectIsNeeded(@NotNull Context context) {
        String model;
        String str;
        int i2;
        ScanOnDisconnectCriteria scanOnDisconnectCriteria;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            model = Build.MODEL;
            str = Build.MANUFACTURER;
            i2 = Build.VERSION.SDK_INT;
            scanOnDisconnectCriteria = SessionManager.getInstance(context).getScanOnDisconnectCriteria();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (scanOnDisconnectCriteria == null) {
            return true;
        }
        Boolean scanAllDevices = scanOnDisconnectCriteria.getScanAllDevices();
        Intrinsics.checkNotNullExpressionValue(scanAllDevices, "scanOnDisconnectCriteria.scanAllDevices");
        if (scanAllDevices.booleanValue()) {
            return true;
        }
        for (ScanOnDisconnectCriteria.PhoneModelsBean phoneModelsBean : scanOnDisconnectCriteria.getPhoneModels()) {
            if (kotlin.text.m.equals(phoneModelsBean.getManufacturer(), str, true)) {
                if (phoneModelsBean.getExcludeModels() != null) {
                    String excludeModels = phoneModelsBean.getExcludeModels();
                    Intrinsics.checkNotNullExpressionValue(excludeModels, "data.excludeModels");
                    if (!StringsKt__StringsKt.split$default((CharSequence) excludeModels, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).isEmpty()) {
                        String excludeModels2 = phoneModelsBean.getExcludeModels();
                        Intrinsics.checkNotNullExpressionValue(excludeModels2, "data.excludeModels");
                        List<String> split$default = StringsKt__StringsKt.split$default((CharSequence) excludeModels2, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null);
                        Intrinsics.checkNotNullExpressionValue(model, "model");
                        if (!w(split$default, model)) {
                            return true;
                        }
                    }
                }
                if (phoneModelsBean.getExcludeModels() == null) {
                    if (phoneModelsBean.getModels() != null) {
                        String models = phoneModelsBean.getModels();
                        Intrinsics.checkNotNullExpressionValue(models, "data.models");
                        if (!StringsKt__StringsKt.split$default((CharSequence) models, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).isEmpty()) {
                            String models2 = phoneModelsBean.getModels();
                            Intrinsics.checkNotNullExpressionValue(models2, "data.models");
                            if (!StringsKt__StringsKt.split$default((CharSequence) models2, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).contains("ALL")) {
                                String models3 = phoneModelsBean.getModels();
                                Intrinsics.checkNotNullExpressionValue(models3, "data.models");
                                List<String> split$default2 = StringsKt__StringsKt.split$default((CharSequence) models3, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null);
                                Intrinsics.checkNotNullExpressionValue(model, "model");
                                if (!w(split$default2, model)) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (phoneModelsBean.getOsVersions() != null) {
                        String osVersions = phoneModelsBean.getOsVersions();
                        Intrinsics.checkNotNullExpressionValue(osVersions, "data.osVersions");
                        if (StringsKt__StringsKt.split$default((CharSequence) osVersions, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).isEmpty()) {
                            return true;
                        }
                        String osVersions2 = phoneModelsBean.getOsVersions();
                        Intrinsics.checkNotNullExpressionValue(osVersions2, "data.osVersions");
                        if (StringsKt__StringsKt.split$default((CharSequence) osVersions2, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).contains("ALL")) {
                            return true;
                        }
                        String osVersions3 = phoneModelsBean.getOsVersions();
                        Intrinsics.checkNotNullExpressionValue(osVersions3, "data.osVersions");
                        return CollectionsKt___CollectionsKt.contains(StringsKt__StringsKt.split$default((CharSequence) osVersions3, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null), Integer.valueOf(i2));
                    }
                    return true;
                }
            }
        }
        return true;
    }

    public final long convertIntervalToMilliSeconds(@NotNull String interval) {
        Intrinsics.checkNotNullParameter(interval, "interval");
        return AppUtils.parseDate(interval, "HH:mm:ss").getTime() - AppUtils.parseDate("00:00:00", "HH:mm:ss").getTime();
    }

    public final double convertKMToMiles(double d2) {
        return d2 * 0.62137d;
    }

    public final void fetchDeviceCapabilityConfig(@NotNull final Context context, @Nullable final DeviceCapabilityConfigFetchListener deviceCapabilityConfigFetchListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.utils.j
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                PayUtils.y(DeviceCapabilityConfigFetchListener.this, context, exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.t
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                PayUtils.z(FirebaseRemoteConfig.this, deviceCapabilityConfigFetchListener, task);
            }
        });
    }

    public final void fetchDeviceRemoteConfig(@NotNull final String key, @NotNull final Context context, @Nullable final DeviceRemoteConfigFetchListener deviceRemoteConfigFetchListener) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(context, "context");
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.setDefaultsAsync(R.xml.remote_config_details);
        remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.utils.k
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                PayUtils.B(DeviceRemoteConfigFetchListener.this, context, exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.v
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                PayUtils.C(FirebaseRemoteConfig.this, deviceRemoteConfigFetchListener, key, task);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.google.firebase.remoteconfig.FirebaseRemoteConfig, java.lang.Object] */
    public final void fetchGooglefitConfiguration(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseRemoteConfig, "getInstance()");
        objectRef.element = firebaseRemoteConfig;
        FirebaseRemoteConfigSettings build = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0L).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…s(0)\n            .build()");
        ((FirebaseRemoteConfig) objectRef.element).setConfigSettingsAsync(build);
        ((FirebaseRemoteConfig) objectRef.element).fetch(10L).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.i
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                PayUtils.E(Ref.ObjectRef.this, context, task);
            }
        });
    }

    public final void fetchRsaKeysConfig(@NotNull final String key, @NotNull final Context context, @Nullable final DeviceRsaKeysFetchListener deviceRsaKeysFetchListener) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(context, "context");
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.setDefaultsAsync(R.xml.remote_config_details);
        remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.utils.l
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                PayUtils.H(DeviceRsaKeysFetchListener.this, context, exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.w
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                PayUtils.F(FirebaseRemoteConfig.this, deviceRsaKeysFetchListener, key, task);
            }
        });
    }

    @NotNull
    public final String formatLastSyncTime(@NotNull Context context, @NotNull String format) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(format, "format");
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context).getDeviceInfoBy(BleApiManager.getInstance(context).getBleApi().getMacAddress());
        Calendar calendar = Calendar.getInstance();
        if ((deviceInfoBy != null ? Long.valueOf(deviceInfoBy.getLasySyncTime()) : null) != null) {
            calendar.setTime(deviceInfoBy != null ? new Date(deviceInfoBy.getLasySyncTime()) : null);
        }
        String formatDate = AppUtils.formatDate(calendar.getTime(), format);
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(calendar.time, format)");
        return formatDate;
    }

    @Nullable
    public final String formatSeconds(int i2) {
        int i3 = i2 % CacheConstants.HOUR;
        int i4 = i3 % 60;
        int floor = (int) Math.floor(i3 / 60);
        int floor2 = (int) Math.floor(i2 / CacheConstants.HOUR);
        if (floor2 == 0 && floor == 0) {
            return i4 + " secs";
        } else if (floor2 == 0) {
            return floor + " mins " + i4 + " secs";
        } else {
            return floor2 + " hrs " + floor + " mins " + i4 + " secs";
        }
    }

    @Nullable
    public final String formatSecondsInHHMMSS(int i2) {
        int i3 = i2 % CacheConstants.HOUR;
        int i4 = i3 % 60;
        int floor = (int) Math.floor(i3 / 60);
        int floor2 = (int) Math.floor(i2 / CacheConstants.HOUR);
        StringBuilder sb = new StringBuilder();
        String str = BleConst.GetDeviceTime;
        sb.append(floor < 10 ? BleConst.GetDeviceTime : "");
        sb.append(floor);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        if (i4 >= 10) {
            str = "";
        }
        sb3.append(str);
        sb3.append(i4);
        String sb4 = sb3.toString();
        return floor2 + ':' + sb2 + ':' + sb4;
    }

    @NotNull
    public final String formattedRankGraphDate(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        try {
            String format = AppUtils.getSimpleDateFormat("dd/MM").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(date));
            Intrinsics.checkNotNullExpressionValue(format, "spf.format(newDate)");
            try {
                System.out.println((Object) format);
                return format;
            } catch (ParseException unused) {
                date = format;
                return date;
            }
        } catch (ParseException unused2) {
        }
    }

    @Nullable
    public final Integer geAADImage(@Nullable ActivityTypes activityTypes) {
        if (activityTypes != null) {
            int i2 = WhenMappings.$EnumSwitchMapping$1[activityTypes.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    return null;
                }
                return Integer.valueOf((int) R.drawable.walk_aad_icon);
            }
            return Integer.valueOf((int) R.drawable.run_aad_icon);
        }
        return null;
    }

    @Nullable
    public final String getActivityNameListIDO(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (i2 != 24) {
            if (i2 != 25) {
                if (i2 != 133) {
                    if (i2 != 134) {
                        if (i2 != 170) {
                            if (i2 != 171) {
                                switch (i2) {
                                    case 4:
                                        return SportsType.SPORT_TYPE_ONFOOT.getType();
                                    case 11:
                                        return SportsType.SPORT_TYPE_ELLIPTICAL.getType();
                                    case 27:
                                        return SportsType.SPORT_TYPE_SKATING.getType();
                                    case 29:
                                        return SportsType.SPORT_TYPE_DANCING.getType();
                                    case 32:
                                        return SportsType.SPORT_TYPE_PILATES.getType();
                                    case 35:
                                        return SportsType.SPORT_TYPE_ZUMBA.getType();
                                    case 37:
                                        return SportsType.SPORT_TYPE_PLANK.getType();
                                    case 48:
                                        return SportsType.SPORT_TYPE_OUTDOOR_RUN.getType();
                                    case 49:
                                        return SportsType.SPORT_TYPE_INDOOR_RUN.getType();
                                    case 50:
                                        return SportsType.SPORT_TYPE_OUTDOOR_CYCLE.getType();
                                    case 51:
                                        return SportsType.SPORT_TYPE_INDOOR_CYCLE.getType();
                                    case 52:
                                        return SportsType.SPORT_TYPE_OUTDOOR_WALK.getType();
                                    case 53:
                                        return SportsType.SPORT_TYPE_INDOOR_WALK.getType();
                                    case 54:
                                        return SportsType.SPORT_TYPE_POOL_SWIM.getType();
                                    case 55:
                                        return SportsType.SPORT_TYPE_WATER_SWIM.getType();
                                    case 56:
                                        return SportsType.SPORT_TYPE_ELLIPTICAL.getType();
                                    case 57:
                                        return SportsType.SPORT_TYPE_ROWING_MACHINE.getType();
                                    case 58:
                                        return SportsType.SPORT_TYPE_HIIT.getType();
                                    case 75:
                                        return SportsType.SPORT_TYPE_CRICKET.getType();
                                    case 110:
                                        return SportsType.SPORT_TYPE_TRADITIONAL_STRENGTH_TRAINING.getType();
                                    case 112:
                                        return SportsType.SPORT_TYPE_PULL_UP.getType();
                                    case 123:
                                        return SportsType.SPORT_TYPE_FREE_SPARRING.getType();
                                    case 137:
                                        return SportsType.SPORT_TYPE_HANDBALL.getType();
                                    case 152:
                                        return SportsType.SPORT_TYPE_STREET_DANCE.getType();
                                    case 158:
                                        return SportsType.SPORT_TYPE_STAIR_CLIMBING.getType();
                                    case 161:
                                        return SportsType.SPORT_TYPE_SLEDDING.getType();
                                    case 180:
                                        return SportsType.SPORT_TYPE_SKATEBOARDING.getType();
                                    case 181:
                                        return SportsType.SPORT_TYPE_ROCK_CLIMBING.getType();
                                    default:
                                        switch (i2) {
                                            case 6:
                                                return SportsType.SPORT_TYPE_CLIMBING.getType();
                                            case 7:
                                                return SportsType.SPORT_TYPE_BADMINTON.getType();
                                            case 8:
                                                return SportsType.SPORT_TYPE_OTHER.getType();
                                            case 9:
                                                return SportsType.SPORT_TYPE_FITNESS.getType();
                                            default:
                                                switch (i2) {
                                                    case 13:
                                                        return SportsType.SPORT_TYPE_SIT_UPS.getType();
                                                    case 14:
                                                        return SportsType.SPORT_TYPE_PUSH_UPS.getType();
                                                    case 15:
                                                        return SportsType.SPORT_TYPE_DUMBBELLS.getType();
                                                    default:
                                                        switch (i2) {
                                                            case 17:
                                                                return SportsType.SPORT_TYPE_AEROBICS.getType();
                                                            case 18:
                                                                return SportsType.SPORT_TYPE_YOGA.getType();
                                                            case 19:
                                                                return SportsType.SPORT_TYPE_ROPE_SKIPPING.getType();
                                                            case 20:
                                                                return SportsType.SPORT_TYPE_TABLE_TENNIS.getType();
                                                            case 21:
                                                                return SportsType.SPORT_TYPE_BASKETBALL.getType();
                                                            case 22:
                                                                return SportsType.SPORT_TYPE_SOCKER.getType();
                                                            default:
                                                                switch (i2) {
                                                                    case 101:
                                                                        return SportsType.SPORT_TYPE_FUNCTIONAL_STRENGTH_TRAINING.getType();
                                                                    case 102:
                                                                        return SportsType.SPORT_TYPE_CORE_TRAINING.getType();
                                                                    case 103:
                                                                        return SportsType.SPORT_TYPE_STEPPER.getType();
                                                                    case 104:
                                                                        return SportsType.SPORT_TYPE_ORGANIZE_AND_RELAX.getType();
                                                                    default:
                                                                        switch (i2) {
                                                                            case 114:
                                                                                return SportsType.SPORT_TYPE_JUMPING_JACKS.getType();
                                                                            case 115:
                                                                                return SportsType.SPORT_TYPE_SQUAT.getType();
                                                                            case 116:
                                                                                return SportsType.SPORT_TYPE_HIGH_KNEE_LIFT.getType();
                                                                            case 117:
                                                                                return SportsType.SPORT_TYPE_BOXING.getType();
                                                                            case 118:
                                                                                return SportsType.SPORT_TYPE_BARBELL.getType();
                                                                            default:
                                                                                switch (i2) {
                                                                                    case 127:
                                                                                        return SportsType.SPORT_TYPE_HORIZONTAL_BAR.getType();
                                                                                    case 128:
                                                                                        return SportsType.SPORT_TYPE_PARALLEL_BARS.getType();
                                                                                    case 129:
                                                                                        return SportsType.SPORT_TYPE_CLIMBING_MACHINE.getType();
                                                                                    case 130:
                                                                                        return SportsType.SPORT_TYPE_CARDIO_CRUISER.getType();
                                                                                    case 131:
                                                                                        return SportsType.SPORT_TYPE_BOWLING.getType();
                                                                                    default:
                                                                                        return null;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                            }
                            return SportsType.SPORT_TYPE_SAIL_BOATING.getType();
                        }
                        return SportsType.SPORT_TYPE_SURFING.getType();
                    }
                    return SportsType.SPORT_TYPE_AMERICAN_FOOTBALL.getType();
                }
                return SportsType.SPORT_TYPE_HOCKEY.getType();
            }
            return SportsType.SPORT_TYPE_GOLF.getType();
        }
        return SportsType.SPORT_TYPE_TENNIS.getType();
    }

    @Nullable
    public final String getActivityNameListTouch(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        switch (i2) {
            case 4:
                return SportsType.SPORT_TYPE_HIKING.getType();
            case 5:
            case 10:
            case 12:
            case 13:
            case 16:
            case 19:
            case 23:
            case 24:
            case 25:
            case 28:
            case 30:
            case 41:
            case 51:
            case 54:
            case 55:
            case 90:
            case 113:
            default:
                return null;
            case 6:
                return SportsType.SPORT_TYPE_MOUNTAINEERING.getType();
            case 7:
                return SportsType.SPORT_TYPE_BADMINTON.getType();
            case 8:
                return SportsType.SPORT_TYPE_STRENGTH_TRAINING.getType();
            case 9:
                return SportsType.SPORT_TYPE_TABLE_TENNIS.getType();
            case 11:
                return SportsType.SPORT_TYPE_INDOOR_CYCLE.getType();
            case 14:
                return SportsType.SPORT_TYPE_TENNIS.getType();
            case 15:
                return SportsType.SPORT_TYPE_FOLK_DANCE.getType();
            case 17:
                return SportsType.SPORT_TYPE_PILATES.getType();
            case 18:
                return SportsType.SPORT_TYPE_YOGA.getType();
            case 20:
                return SportsType.SPORT_TYPE_CROSS_TRAINING.getType();
            case 21:
                return SportsType.SPORT_TYPE_BASKETBALL.getType();
            case 22:
                return SportsType.SPORT_TYPE_CROSS_FITTING.getType();
            case 26:
                return SportsType.SPORT_TYPE_LATIN_DANCE.getType();
            case 27:
                return SportsType.SPORT_TYPE_POPING.getType();
            case 29:
                return SportsType.SPORT_TYPE_BALLET.getType();
            case 31:
                return SportsType.SPORT_TYPE_BASEBALL.getType();
            case 32:
                return SportsType.SPORT_TYPE_BOWLING.getType();
            case 33:
                return SportsType.SPORT_TYPE_RACQUETBALL.getType();
            case 34:
                return SportsType.SPORT_TYPE_CURLING.getType();
            case 35:
                return SportsType.SPORT_TYPE_HUNTING.getType();
            case 36:
                return SportsType.SPORT_TYPE_SNOWBOARDING.getType();
            case 37:
                return SportsType.SPORT_TYPE_FISHING.getType();
            case 38:
                return SportsType.SPORT_TYPE_DISC_SPORTS.getType();
            case 39:
                return SportsType.SPORT_TYPE_RUGBY.getType();
            case 40:
                return SportsType.SPORT_TYPE_GOLF.getType();
            case 42:
                return SportsType.SPORT_TYPE_CORE_TRAINING.getType();
            case 43:
                return SportsType.SPORT_TYPE_SKATING.getType();
            case 44:
                return SportsType.SPORT_TYPE_FITNESS_GAME.getType();
            case 45:
                return SportsType.SPORT_TYPE_AEROBICS.getType();
            case 46:
                return SportsType.SPORT_TYPE_GROUP_TRAINING.getType();
            case 47:
                return SportsType.SPORT_TYPE_CARDIO_BOXING.getType();
            case 48:
                return SportsType.SPORT_TYPE_OUTDOOR_RUN.getType();
            case 49:
                return SportsType.SPORT_TYPE_INDOOR_RUN.getType();
            case 50:
                return SportsType.SPORT_TYPE_INDOOR_CYCLE.getType();
            case 52:
                return SportsType.SPORT_TYPE_OUTDOOR_WALK.getType();
            case 53:
                return SportsType.SPORT_TYPE_INDOOR_WALK.getType();
            case 56:
                return SportsType.SPORT_TYPE_ELLIPTICAL.getType();
            case 57:
                return SportsType.SPORT_TYPE_ROWER.getType();
            case 58:
                return SportsType.SPORT_TYPE_FREE_WORKOUT.getType();
            case 59:
                return SportsType.SPORT_TYPE_FENCING.getType();
            case 60:
                return SportsType.SPORT_TYPE_SOFTBALL.getType();
            case 61:
                return SportsType.SPORT_TYPE_STAIRS.getType();
            case 62:
                return SportsType.SPORT_TYPE_AMERICAN_FOOTBALL.getType();
            case 63:
                return SportsType.SPORT_TYPE_VOLLEYBALL.getType();
            case 64:
                return SportsType.SPORT_TYPE_ROLLING.getType();
            case 65:
                return SportsType.SPORT_TYPE_PICKLE_BALL.getType();
            case 66:
                return SportsType.SPORT_TYPE_HOCKEY.getType();
            case 67:
                return SportsType.SPORT_TYPE_BOXING.getType();
            case 68:
                return SportsType.SPORT_TYPE_TAEKWONDO.getType();
            case 69:
                return SportsType.SPORT_TYPE_KARATE.getType();
            case 70:
                return SportsType.SPORT_TYPE_FLEXIBILITY.getType();
            case 71:
                return SportsType.SPORT_TYPE_HANDBALL.getType();
            case 72:
                return SportsType.SPORT_TYPE_HANDCAR.getType();
            case 73:
                return SportsType.SPORT_TYPE_KENDO.getType();
            case 74:
                return SportsType.SPORT_TYPE_HIIT.getType();
            case 75:
                return SportsType.SPORT_TYPE_CRICKET.getType();
            case 76:
                return SportsType.SPORT_TYPE_SOCKER.getType();
            case 77:
                return SportsType.SPORT_TYPE_MEDITATION.getType();
            case 78:
                return SportsType.SPORT_TYPE_WRESTLING.getType();
            case 79:
                return SportsType.SPORT_TYPE_STEPPER.getType();
            case 80:
                return SportsType.SPORT_TYPE_TAI_CHI.getType();
            case 81:
                return SportsType.SPORT_TYPE_GYMNASTICS.getType();
            case 82:
                return SportsType.SPORT_TYPE_TRACK_FIELD.getType();
            case 83:
                return SportsType.SPORT_TYPE_JUMP_ROPE.getType();
            case 84:
                return SportsType.SPORT_TYPE_MARTIAL_ARTS.getType();
            case 85:
                return SportsType.SPORT_TYPE_PLAY.getType();
            case 86:
                return SportsType.SPORT_TYPE_SNOW_SPORTS.getType();
            case 87:
                return SportsType.SPORT_TYPE_LACROSSE.getType();
            case 88:
                return SportsType.SPORT_TYPE_SINGLE_BAR.getType();
            case 89:
                return SportsType.SPORT_TYPE_PARALLEL_BARS.getType();
            case 91:
                return SportsType.SPORT_TYPE_HULA_HOPE.getType();
            case 92:
                return SportsType.SPORT_TYPE_DARTS.getType();
            case 93:
                return SportsType.SPORT_TYPE_ARCHERY.getType();
            case 94:
                return SportsType.SPORT_TYPE_HORSE_RIDING.getType();
            case 95:
                return SportsType.SPORT_TYPE_SHUTTLECOCK.getType();
            case 96:
                return SportsType.SPORT_TYPE_ICE_HOCKEY.getType();
            case 97:
                return SportsType.SPORT_TYPE_WAIST_TRAINING.getType();
            case 98:
                return SportsType.SPORT_TYPE_SIT_UPS.getType();
            case 99:
                return SportsType.SPORT_TYPE_PUSH_UPS.getType();
            case 100:
                return SportsType.SPORT_TYPE_TREADMILL.getType();
            case 101:
                return SportsType.SPORT_TYPE_BATTLE_ROPE.getType();
            case 102:
                return SportsType.SPORT_TYPE_SMITH_MACHINE.getType();
            case 103:
                return SportsType.SPORT_TYPE_PULL_UP.getType();
            case 104:
                return SportsType.SPORT_TYPE_ZUMBA.getType();
            case 105:
                return SportsType.SPORT_TYPE_PLANK.getType();
            case 106:
                return SportsType.SPORT_TYPE_KABADDI.getType();
            case 107:
                return SportsType.SPORT_TYPE_SHOT_PUT.getType();
            case 108:
                return SportsType.SPORT_TYPE_SOLID_BALL.getType();
            case 109:
                return SportsType.SPORT_TYPE_JAVELIN.getType();
            case 110:
                return SportsType.SPORT_TYPE_LONG_JUMP.getType();
            case 111:
                return SportsType.SPORT_TYPE_HIGH_JUMP.getType();
            case 112:
                return SportsType.SPORT_TYPE_ROCK_CLIMBING.getType();
            case 114:
                return SportsType.SPORT_TYPE_SQUARE_DANCE.getType();
            case 115:
                return SportsType.SPORT_TYPE_DUMBBELLS.getType();
            case 116:
                return SportsType.SPORT_TYPE_KARTING.getType();
            case 117:
                return SportsType.SPORT_TYPE_DODGE_BALL.getType();
            case 118:
                return SportsType.SPORT_TYPE_YOYO.getType();
            case 119:
                return SportsType.SPORT_TYPE_LOCKING.getType();
            case 120:
                return SportsType.SPORT_TYPE_HIIT.getType();
            case 121:
                return SportsType.SPORT_TYPE_BURPEES.getType();
            case 122:
                return SportsType.SPORT_TYPE_BELLY_DANCE.getType();
            case 123:
                return SportsType.SPORT_TYPE_SKATEBOARDING.getType();
            case 124:
                return SportsType.SPORT_TYPE_PARKOUR.getType();
            case 125:
                return SportsType.SPORT_TYPE_JAZZ_DANCE.getType();
            case 126:
                return SportsType.SPORT_TYPE_MODERN_DANCE.getType();
            case 127:
                return SportsType.SPORT_TYPE_AEROBICS_GYMS.getType();
        }
    }

    @Nullable
    public final ActivityTypes getActivityTypes(@Nullable String str) {
        ActivityTypes activityTypes = ActivityTypes.WALK;
        if (Intrinsics.areEqual(str, kotlin.text.m.capitalize(activityTypes.name()))) {
            return activityTypes;
        }
        ActivityTypes activityTypes2 = ActivityTypes.RUN;
        if (Intrinsics.areEqual(str, kotlin.text.m.capitalize(activityTypes2.name()))) {
            return activityTypes2;
        }
        return null;
    }

    @Nullable
    public final ActivityTypes getActivityTypesIDO(@Nullable String str) {
        ActivityTypes activityTypes = ActivityTypes.WALK;
        if (Intrinsics.areEqual(str, kotlin.text.m.capitalize(activityTypes.name()))) {
            return activityTypes;
        }
        ActivityTypes activityTypes2 = ActivityTypes.RUN;
        if (Intrinsics.areEqual(str, kotlin.text.m.capitalize(activityTypes2.name()))) {
            return activityTypes2;
        }
        return null;
    }

    @NotNull
    public final String getAge(int i2, int i3, int i4) {
        Calendar calendar = Calendar.getInstance();
        long j2 = calendar.get(1);
        calendar.add(1, -3);
        int i5 = calendar.get(1);
        int i6 = calendar.get(2);
        int i7 = calendar.get(5);
        if (i2 > i5) {
            return "1";
        }
        if (i2 == i5) {
            return (i3 >= i6 && (i3 != i6 || i4 >= i7)) ? "3" : "2";
        }
        return String.valueOf(Math.abs(j2 - i2));
    }

    @NotNull
    public final String getAvgDistanceValueDouble(@NotNull Context context, double d2) {
        Intrinsics.checkNotNullParameter(context, "context");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isMatrixDevice(context) && !companion.isMoyangDevice(context)) {
            if (companion.isSmaDevice(context)) {
                if (companion.isPrimiaDevice(context)) {
                    decimalFormat = new DecimalFormat("#.#");
                    decimalFormat.setRoundingMode(RoundingMode.FLOOR);
                } else {
                    decimalFormat = new DecimalFormat("#.#");
                    decimalFormat.setRoundingMode(RoundingMode.FLOOR);
                }
            } else if (companion.isCZDevice(context) || companion.isCADevice(context) || companion.isCYDevice(context) || companion.isPS1Device(context) || companion.isBESDevice(context)) {
                decimalFormat = new DecimalFormat("#.#");
                decimalFormat.setRoundingMode(RoundingMode.FLOOR);
            }
        } else {
            decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        }
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        String format = decimalFormat.format(Math.abs(d2));
        Intrinsics.checkNotNullExpressionValue(format, "decimalFormat.format(kotlin.math.abs(distance))");
        return format;
    }

    public final double getAvgTimeForOvers(int i2, int i3) {
        return Math.round((i2 * 6) / i3);
    }

    public final void getBatteryOptimizationConfig(@NotNull final Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.utils.n
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                PayUtils.I(exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.e
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                PayUtils.J(FirebaseRemoteConfig.this, mContext, task);
            }
        });
    }

    @Nullable
    public final String getCalculatedDate(@Nullable String str, int i2) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        calendar.add(6, i2);
        return simpleDateFormat.format(new Date(calendar.getTimeInMillis()));
    }

    @NotNull
    public final String getCaloriesValue(double d2) {
        return String.valueOf((int) Math.abs(d2));
    }

    @NotNull
    public final String getContactName(@NotNull Context context, @NotNull String phoneNumber) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_CONTACTS") != 0) {
            return phoneNumber;
        }
        String str = "";
        try {
            Cursor query = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber)), new String[]{"display_name"}, null, null, null);
            if (query != null) {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(0)");
                    str = string;
                }
                query.close();
            }
            if (StringsKt__StringsKt.trim(str).toString().length() > 0) {
                phoneNumber = str;
            }
        } catch (Exception e2) {
            phoneNumber = str;
            e2.printStackTrace();
        }
        return removeEmojisAndDifferentLanguage(phoneNumber);
    }

    @NotNull
    public final String getConvertedInterval(int i2) {
        return i2 != 15 ? i2 != 30 ? i2 != 45 ? i2 != 60 ? i2 != 90 ? i2 != 120 ? i2 != 150 ? "01:00:00" : "02:30:00" : "02:00:00" : "01:30:00" : "01:00:00" : "00:45:00" : "00:30:00" : "00:15:00";
    }

    public final int getCurrentTimeInMinutes(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Calendar calendar = Calendar.getInstance();
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context).getDeviceInfoBy(BleApiManager.getInstance(context).getBleApi().getMacAddress());
        if ((deviceInfoBy != null ? Long.valueOf(deviceInfoBy.getLasySyncTime()) : null) != null) {
            calendar.setTime(deviceInfoBy != null ? new Date(deviceInfoBy.getLasySyncTime()) : null);
        }
        return calendar.get(12);
    }

    public final int getCurrentTimezone() {
        return TimeZone.getDefault().getRawOffset();
    }

    public final int getDateDifference(@Nullable String str, @Nullable String str2) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date2 = null;
        try {
            date = simpleDateFormat.parse(str);
            try {
                date2 = simpleDateFormat.parse(str2);
            } catch (ParseException e2) {
                e = e2;
                e.printStackTrace();
                Intrinsics.checkNotNull(date2);
                long time = date2.getTime();
                Intrinsics.checkNotNull(date);
                long time2 = time - date.getTime();
                long j2 = 60;
                long j3 = (time2 / 1000) % j2;
                long j4 = (time2 / 60000) % j2;
                long j5 = (time2 / ((long) TimeConstants.HOUR)) % 24;
                return (int) (time2 / ((long) TimeConstants.DAY));
            }
        } catch (ParseException e3) {
            e = e3;
            date = null;
        }
        Intrinsics.checkNotNull(date2);
        long time3 = date2.getTime();
        Intrinsics.checkNotNull(date);
        long time22 = time3 - date.getTime();
        long j22 = 60;
        long j32 = (time22 / 1000) % j22;
        long j42 = (time22 / 60000) % j22;
        long j52 = (time22 / ((long) TimeConstants.HOUR)) % 24;
        return (int) (time22 / ((long) TimeConstants.DAY));
    }

    @NotNull
    public final String getDateFormatValue(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%04d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(1))}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(Soundex.SILENT_MARKER);
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(2) + 1)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(Soundex.SILENT_MARKER);
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb.append(format3);
        return sb.toString();
    }

    @NotNull
    public final String getDateMonthlyFormat(int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2, i2 - 1);
        String format = AppUtils.getSimpleDateFormat("MMMM").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "spf.format(instance.time)");
        return format;
    }

    @NotNull
    public final String getDateMonthlyGraph(int i2) {
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("MMM");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2, i2 - 1);
        String format = simpleDateFormat.format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(cal.time)");
        return format;
    }

    @NotNull
    public final String getDateWeeklyFormat(int i2, @NotNull String year) {
        Intrinsics.checkNotNullParameter(year, "year");
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1, Integer.parseInt(year));
        calendar.set(3, i2);
        calendar.set(7, 2);
        String format = AppUtils.getSimpleDateFormat("dd MMM").format(calendar.getTime());
        calendar.add(5, 6);
        calendar.set(7, 1);
        String format2 = AppUtils.getSimpleDateFormat("dd MMM").format(calendar.getTime());
        return format + " - " + format2;
    }

    @NotNull
    public final ArrayList<AutoRecognitonModel> getDefaultAutoRecogActivities() {
        ArrayList<AutoRecognitonModel> arrayList = new ArrayList<>();
        arrayList.add(new AutoRecognitonModel(ActivityTypes.RUN, false, null, 4, null));
        arrayList.add(new AutoRecognitonModel(ActivityTypes.WALK, false, null, 4, null));
        return arrayList;
    }

    @NotNull
    public final String getDefaultMonth() {
        String format = AppUtils.getSimpleDateFormat("MMMM yyyy").format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(format, "spf.format(instance.time)");
        return format;
    }

    @NotNull
    public final String getDeviceNameForWatchRecovery(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BleApiManager.getInstance(context).getDeviceType() == DeviceType.CZ0 ? "boAt Wave Pro" : BleApiManager.getInstance(context).getDeviceType() == DeviceType.wavePrime ? "boAt Wave Prime" : BleApiManager.getInstance(context).getDeviceType() == DeviceType.WAVE_ELITE ? "boAt Wave Elite" : BleApiManager.getInstance(context).getDeviceType() == DeviceType.CZ3 ? "boAt Xtend Sport" : "boAt Watch";
    }

    public final double getDistanceDifference(@NotNull Context context, double d2, double d3) {
        Intrinsics.checkNotNullParameter(context, "context");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(Double.parseDouble(getDistanceValueDouble(context, d2)) - Double.parseDouble(getDistanceValueDouble(context, d3)))}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return Double.parseDouble(format);
    }

    @NotNull
    public final String getDistanceValue(@NotNull Context context, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isMatrixDevice(context) && !companion.isMoyangDevice(context)) {
            if (companion.isSmaDevice(context)) {
                if (companion.isPrimiaDevice(context)) {
                    decimalFormat = new DecimalFormat("#.#");
                    decimalFormat.setRoundingMode(RoundingMode.FLOOR);
                } else {
                    decimalFormat = new DecimalFormat("#.#");
                    decimalFormat.setRoundingMode(RoundingMode.FLOOR);
                }
            } else if (companion.isCZDevice(context) || companion.isCADevice(context) || companion.isCYDevice(context) || companion.isPS1Device(context) || companion.isBESDevice(context)) {
                decimalFormat = new DecimalFormat("#.#");
                decimalFormat.setRoundingMode(RoundingMode.FLOOR);
            }
        } else {
            decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        }
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        Boolean isUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isUnitInMile, "isUnitInMile");
        if (isUnitInMile.booleanValue() && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isDistanceUnitSettingsSupported()) {
            String format = decimalFormat.format(convertKMToMiles(Math.abs(i2) / 1000.0d));
            Intrinsics.checkNotNullExpressionValue(format, "{\n                decima…Double()))\n\n            }");
            return format;
        }
        String format2 = decimalFormat.format(Math.abs(i2) / 1000.0d);
        Intrinsics.checkNotNullExpressionValue(format2, "{\n                decima…toDouble())\n            }");
        return format2;
    }

    public final double getDistanceValueAsPerDevice(@NotNull Context context, double d2) {
        Intrinsics.checkNotNullParameter(context, "context");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isMatrixDevice(context) && !companion.isMoyangDevice(context)) {
            if (companion.isSmaDevice(context)) {
                if (companion.isPrimiaDevice(context)) {
                    decimalFormat = new DecimalFormat("#.#");
                    decimalFormat.setRoundingMode(RoundingMode.FLOOR);
                } else {
                    decimalFormat = new DecimalFormat("#.#");
                    decimalFormat.setRoundingMode(RoundingMode.FLOOR);
                }
            } else if (companion.isCZDevice(context) || companion.isCADevice(context) || companion.isCYDevice(context) || companion.isPS1Device(context) || companion.isBESDevice(context)) {
                decimalFormat = new DecimalFormat("#.#");
                decimalFormat.setRoundingMode(RoundingMode.FLOOR);
            }
        } else {
            decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        }
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        String format = decimalFormat.format(d2);
        Intrinsics.checkNotNullExpressionValue(format, "decimalFormat.format(distance)");
        return Double.parseDouble(format);
    }

    @NotNull
    public final String getDistanceValueDouble(@NotNull Context context, double d2) {
        Intrinsics.checkNotNullParameter(context, "context");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isMatrixDevice(context) && !companion.isMoyangDevice(context)) {
            if (companion.isSmaDevice(context)) {
                if (companion.isPrimiaDevice(context)) {
                    decimalFormat = new DecimalFormat("#.#");
                    decimalFormat.setRoundingMode(RoundingMode.FLOOR);
                } else {
                    decimalFormat = new DecimalFormat("#.#");
                    decimalFormat.setRoundingMode(RoundingMode.FLOOR);
                }
            } else if (companion.isCZDevice(context) || companion.isCADevice(context) || companion.isCYDevice(context) || companion.isPS1Device(context) || companion.isBESDevice(context)) {
                decimalFormat = new DecimalFormat("#.#");
                decimalFormat.setRoundingMode(RoundingMode.FLOOR);
            }
        } else {
            decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        }
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        Boolean isUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isUnitInMile, "isUnitInMile");
        if (isUnitInMile.booleanValue() && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isDistanceUnitSettingsSupported()) {
            String format = decimalFormat.format(convertKMToMiles(Math.abs(d2) / 1000.0d));
            Intrinsics.checkNotNullExpressionValue(format, "{\n                decima…Double()))\n\n            }");
            return format;
        }
        String format2 = decimalFormat.format(Math.abs(d2) / 1000.0d);
        Intrinsics.checkNotNullExpressionValue(format2, "{\n                decima…toDouble())\n            }");
        return format2;
    }

    @NotNull
    public final String getDistanceValueDoubleCheck(double d2) {
        return String.valueOf(Math.abs(d2) / 1000.0d);
    }

    @Nullable
    public final String getFirmwareVersionString(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
        if (bleDeviceInfo != null) {
            return bleDeviceInfo.getFirmwareRevision();
        }
        return null;
    }

    public final void getFitnessChallengesConfig(@NotNull final Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.utils.o
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                PayUtils.L(exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.utils.p
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                PayUtils.M(FirebaseRemoteConfig.this, mContext, task);
            }
        });
    }

    @NotNull
    public final Intent getGalleryIntent() {
        Intent intent = new Intent();
        intent.setType(com.crrepa.r.a.d);
        intent.setAction("android.intent.action.GET_CONTENT");
        return intent;
    }

    @NotNull
    public final String getHourFormat(int i2) {
        int i3 = i2 / 60;
        if (i3 < 10) {
            return String.valueOf(i3);
        }
        return String.valueOf(i3);
    }

    @NotNull
    public final String getHourMinuteFormatFromMinutes(int i2) {
        if (i2 != 0) {
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i2 / 60), Integer.valueOf(i2 % 60)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            sb.append(format);
            sb.append(" Hr");
            return sb.toString();
        }
        return "";
    }

    @Nullable
    public final Integer getInsightArrowDrawable(@NotNull Context context, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (i2 < 0) {
            return Integer.valueOf((int) R.drawable.ic_down_arrow_red);
        }
        if (i2 > 0) {
            return Integer.valueOf((int) R.drawable.ic_up_arrow_green);
        }
        return null;
    }

    public final long getJOB_INTERVAL() {
        return c;
    }

    @NotNull
    public final String getLastSyncCurrentHour(@NotNull Context context, @Nullable EntityDeviceInfo entityDeviceInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Calendar calendar = Calendar.getInstance();
        if ((entityDeviceInfo != null ? Long.valueOf(entityDeviceInfo.getLasySyncTime()) : null) != null) {
            calendar.setTime(entityDeviceInfo != null ? new Date(entityDeviceInfo.getLasySyncTime()) : null);
        }
        String formatDate = AppUtils.formatDate(calendar.getTime(), "HH:00:00");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(calendar.time, \"HH:00:00\")");
        return formatDate;
    }

    @NotNull
    public final String getLastSyncPreviousHour(@NotNull Context context, @Nullable EntityDeviceInfo entityDeviceInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Calendar calendar = Calendar.getInstance();
        if ((entityDeviceInfo != null ? Long.valueOf(entityDeviceInfo.getLasySyncTime()) : null) != null) {
            calendar.setTime(entityDeviceInfo != null ? new Date(entityDeviceInfo.getLasySyncTime()) : null);
        }
        calendar.add(10, 1);
        String formatDate = AppUtils.formatDate(calendar.getTime(), "HH:00:00");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(calendar.time, \"HH:00:00\")");
        return formatDate;
    }

    @NotNull
    public final String getLastSyncTime(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (BleApiManager.getInstance(context).getBleApi() != null) {
            EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context).getDeviceInfoBy(BleApiManager.getInstance(context).getBleApi().getMacAddress());
            if (deviceInfoBy != null) {
                deviceInfoBy.getLasySyncTime();
                return context.getString(R.string.last_sync) + ' ' + getTodayYesterdayStringWithTimeStamp(deviceInfoBy.getLasySyncTime(), context);
            }
        }
        return "";
    }

    @Nullable
    public final String getLastUpdatedDateFormatFrom(@NotNull String timestamp, @NotNull Context context) {
        String str;
        String format;
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Date date = new Date(Long.parseLong(timestamp));
            Locale locale = Locale.ENGLISH;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
            String format2 = simpleDateFormat.format(date);
            String format3 = simpleDateFormat.format(new Date());
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MMM-yyyy hh:mm a", locale);
            if (getDateDifference(format2, format3) == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(context.getResources().getString(R.string.today));
                sb.append(' ');
                String format4 = simpleDateFormat2.format(date);
                Intrinsics.checkNotNullExpressionValue(format4, "simpleDateFormat2.format(dt)");
                sb.append(((String[]) StringsKt__StringsKt.split$default((CharSequence) format4, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]))[1]);
                sb.append(' ');
                String format5 = simpleDateFormat2.format(date);
                Intrinsics.checkNotNullExpressionValue(format5, "simpleDateFormat2.format… dt\n                    )");
                sb.append(((String[]) StringsKt__StringsKt.split$default((CharSequence) format5, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]))[2]);
                format = sb.toString();
            } else if (getDateDifference(format2, format3) == 1) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(context.getResources().getString(R.string.yesterday));
                sb2.append(' ');
                String format6 = simpleDateFormat2.format(date);
                Intrinsics.checkNotNullExpressionValue(format6, "simpleDateFormat2.format… dt\n                    )");
                sb2.append(((String[]) StringsKt__StringsKt.split$default((CharSequence) format6, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]))[1]);
                sb2.append(' ');
                String format7 = simpleDateFormat2.format(date);
                Intrinsics.checkNotNullExpressionValue(format7, "simpleDateFormat2.format… dt\n                    )");
                sb2.append(((String[]) StringsKt__StringsKt.split$default((CharSequence) format7, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0]))[2]);
                format = sb2.toString();
            } else {
                format = simpleDateFormat2.format(date);
                Intrinsics.checkNotNullExpressionValue(format, "{\n                    si…mat(dt)\n                }");
            }
            simpleDateFormat2.format(date);
            return format;
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            String format8 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
            if (getDateDifference(timestamp, format8) == 0) {
                str = context.getResources().getString(R.string.today);
                Intrinsics.checkNotNullExpressionValue(str, "{\n                contex…ring.today)\n            }");
            } else if (getDateDifference(timestamp, format8) == 1) {
                str = context.getResources().getString(R.string.yesterday);
                Intrinsics.checkNotNullExpressionValue(str, "{\n                contex….yesterday)\n            }");
            } else {
                str = timestamp;
            }
            return str;
        }
    }

    public final int getLeastStepForSedentary(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DeviceUtils.Companion.isIDODevice(context) ? 100 : 50;
    }

    public final int getMaximumAlarmCount(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DeviceUtils.Companion.isMoyangDevice(context) ? 3 : 5;
    }

    public final int getMaximumWorldClockCount(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DeviceUtils.Companion.isSmaDevice(context) ? 8 : 10;
    }

    public final int getMaximumYValue(@NotNull List<? extends BarEntry> barEntries) {
        Intrinsics.checkNotNullParameter(barEntries, "barEntries");
        int i2 = 0;
        if (!barEntries.isEmpty()) {
            for (BarEntry barEntry : barEntries) {
                if (i2 < ((int) barEntry.getY())) {
                    i2 = (int) barEntry.getY();
                }
            }
        }
        return i2;
    }

    @NotNull
    public final String getMonth(@NotNull String month) {
        Intrinsics.checkNotNullParameter(month, "month");
        Integer.parseInt(month);
        String value = AppConstants.EMPTY_STRING.getValue();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("MM");
        try {
            String format = AppUtils.getSimpleDateFormat("MMM").format(simpleDateFormat.parse(month));
            Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(…utFormatter.parse(month))");
            return format;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return value;
        }
    }

    public final int getMonthFromStringMMM(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!AppUtils.isEmpty(value) && value.length() >= 3) {
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("MMM");
            Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"MMM\")");
            try {
                calendar.setTime(simpleDateFormat.parse(value));
                return calendar.get(2);
            } catch (ParseException | Exception unused) {
            }
        }
        return 0;
    }

    public final int getMonthFromStringMMMM(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!AppUtils.isEmpty(value) && value.length() >= 3) {
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("MMMM");
            Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"MMMM\")");
            try {
                calendar.setTime(simpleDateFormat.parse(value));
                return calendar.get(2);
            } catch (ParseException | Exception unused) {
            }
        }
        return 0;
    }

    @NotNull
    public final Calendar getNextDayCalendar(@NotNull Calendar selectedCalendar) {
        Intrinsics.checkNotNullParameter(selectedCalendar, "selectedCalendar");
        selectedCalendar.add(6, 1);
        return selectedCalendar;
    }

    public final double getOversFromBalls(int i2) {
        String valueOf = String.valueOf(Math.round(i2 / 6));
        String valueOf2 = String.valueOf(i2 % 6);
        Double numberOfOvers = Double.valueOf(valueOf + '.' + valueOf2);
        Intrinsics.checkNotNullExpressionValue(numberOfOvers, "numberOfOvers");
        return numberOfOvers.doubleValue();
    }

    @Nullable
    public final String getPackageNameFromServerAppId(@NotNull Context context, @NotNull String appId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appId, "appId");
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_wechat))) {
            return BleNotification.WE_CHAT;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_facebook))) {
            return BleNotification.FACEBOOK;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_whatsapp_bussiness))) {
            return "com.whatsapp.w4b";
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_whatsapp))) {
            return BleNotification.WHATS_APP;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_twitter))) {
            return BleNotification.TWITTER;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_qq_messenger))) {
            return BleNotification.QQ;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_qzone))) {
            return "com.o2m.gsk";
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_snapchat))) {
            return BleNotification.SNAPCHAT;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_skype))) {
            return BleNotification.SKYPE;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_fb_messenger))) {
            return BleNotification.FACEBOOK_MESSENGER;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_gmail))) {
            return BleNotification.GMAIL;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_instagram))) {
            return BleNotification.INSTAGRAM;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_telegram))) {
            return BleNotification.TELEGRAM;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_linkedin))) {
            return BleNotification.LINKED_IN;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_vkclient))) {
            return "com.vkontakte.android";
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_line_messenger))) {
            return BleNotification.LINE;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_calendar))) {
            return "com.google.android.calendar";
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_youtube))) {
            return BleNotification.YOUTUBE;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_outlook))) {
            return BleNotification.OUT_LOOK;
        }
        if (Intrinsics.areEqual(appId, context.getResources().getString(R.string.notify_yahoo_mail))) {
            return BleNotification.YAHOO_MAIL;
        }
        return null;
    }

    @NotNull
    public final String getPhoneBatteryLevel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("batterymanager");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.BatteryManager");
        return String.valueOf(((BatteryManager) systemService).getIntProperty(4));
    }

    @NotNull
    public final Calendar getPreviousDayCalendar(@NotNull Calendar selectedCalendar) {
        Intrinsics.checkNotNullParameter(selectedCalendar, "selectedCalendar");
        selectedCalendar.add(6, -1);
        return selectedCalendar;
    }

    @NotNull
    public final String[] getSCAN_FILTER_CA0() {
        return v;
    }

    @NotNull
    public final String[] getSCAN_FILTER_CA3() {
        return w;
    }

    @NotNull
    public final String[] getSCAN_FILTER_CA3_BT() {
        return x;
    }

    @NotNull
    public final String[] getSCAN_FILTER_CZ1() {
        return o;
    }

    @NotNull
    public final String[] getSCAN_FILTER_CZ3() {
        return p;
    }

    @NotNull
    public final String[] getSCAN_FILTER_JSTYLE1790() {
        return e;
    }

    @NotNull
    public final String[] getSCAN_FILTER_JSTYLE1810G() {
        return f;
    }

    @NotNull
    public final String[] getSCAN_FILTER_JSTYLE1860() {
        return l;
    }

    @NotNull
    public final String[] getSCAN_FILTER_JSTYLE1963D() {
        return g;
    }

    @NotNull
    public final String[] getSCAN_FILTER_JSTYLE1963YH() {
        return h;
    }

    @NotNull
    public final String[] getSCAN_FILTER_MATRIX() {
        return u;
    }

    @NotNull
    public final String[] getSCAN_FILTER_MOYANG() {
        return r;
    }

    @NotNull
    public final String[] getSCAN_FILTER_MOYANG_WAVEFIT() {
        return s;
    }

    @NotNull
    public final String[] getSCAN_FILTER_SMART_T() {
        return k;
    }

    @NotNull
    public final String[] getSCAN_FILTER_SMA_F2() {
        return m;
    }

    @NotNull
    public final String[] getSCAN_FILTER_SMA_S10() {
        return n;
    }

    @NotNull
    public final String[] getSCAN_FILTER_SMA_S12() {
        return t;
    }

    @NotNull
    public final String[] getSCAN_FILTER_V2() {
        return i;
    }

    @NotNull
    public final String[] getSCAN_FILTER_V7() {
        return j;
    }

    @NotNull
    public final String[] getSCAN_FILTER_WAVE_PRIME() {
        return q;
    }

    public final long getSampleRateForSessionInMillisSecs(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DeviceUtils.Companion.isJstyleDevice(context)) {
            return Constants.ONE_MIN_IN_MILLIS;
        }
        return 5000L;
    }

    @NotNull
    public final String getSecondFormat(int i2) {
        int i3 = i2 % 60;
        if (i3 < 10) {
            StringBuilder sb = new StringBuilder();
            sb.append('0');
            sb.append(i3);
            return sb.toString();
        }
        return String.valueOf(i3);
    }

    @Nullable
    public final String getSessionType(@NotNull String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!Intrinsics.areEqual(type, SportsType.SPORT_TYPE_INDOOR_WALK.getType()) && !Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OUTDOOR_WALK.getType())) {
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_INDOOR_CYCLE.getType())) {
                return ActivityMode.CYCLE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_INDOOR_RUN.getType())) {
                return ActivityMode.RUN.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OUTDOOR_CYCLE.getType())) {
                return ActivityMode.CYCLE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OUTDOOR_RUN.getType())) {
                return ActivityMode.RUN.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HIIT.getType())) {
                return ActivityMode.HIIT.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CRICKET.getType())) {
                return ActivityMode.CRICKET.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DANCING.getType())) {
                return ActivityMode.DANCE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_POOL_SWIM.getType())) {
                return ActivityMode.SWIM.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ELLIPTICAL.getType())) {
                return ActivityMode.ELLIPTICAL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_WATER_SWIM.getType())) {
                return ActivityMode.SWIM.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OTHER.getType())) {
                return ActivityMode.OTHER.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PILATES.getType())) {
                return ActivityMode.PILATES.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ZUMBA.getType())) {
                return ActivityMode.ZUMBA.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_YOGA.getType())) {
                return ActivityMode.YOGA.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROWER.getType())) {
                return ActivityMode.ROWING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ONFOOT.getType())) {
                return ActivityMode.HIKING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SOCKER.getType())) {
                return ActivityMode.FOOTBALL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FITNESS.getType())) {
                return ActivityMode.WORKOUT.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CORE_TRAINING.getType())) {
                return ActivityMode.CORE_TRAINING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ORGANIZE_AND_RELAX.getType())) {
                return ActivityMode.COOLDOWN.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FUNCTIONAL_STRENGTH_TRAINING.getType())) {
                return ActivityMode.FUNCTIONAL_STRENGTH_TRAINING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TRADITIONAL_STRENGTH_TRAINING.getType())) {
                return ActivityMode.TRADITIONAL_STRENGTH_TRAINING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_STRENGTH_TRAINING.getType())) {
                return ActivityMode.STRENGTH_TRAINING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BADMINTON.getType())) {
                return ActivityMode.BADMINTON.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FREE_WORKOUT.getType())) {
                return ActivityMode.FREE_EXERCISE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TENNIS.getType())) {
                return ActivityMode.TENNIS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HIKING.getType())) {
                return ActivityMode.HIKING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FOLK_DANCE.getType())) {
                return ActivityMode.FOLK_DANCE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HANDCAR.getType())) {
                return ActivityMode.HAND_CYCLING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_KENDO.getType())) {
                return ActivityMode.KENDO.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TABLE_TENNIS.getType())) {
                return ActivityMode.PING_PONG.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_MEDITATION.getType())) {
                return ActivityMode.MEDITATION.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_WRESTLING.getType())) {
                return ActivityMode.WRESTLING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_STEPPER.getType())) {
                return ActivityMode.STEPPER.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TAI_CHI.getType())) {
                return ActivityMode.TAI_CHI.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_GYMNASTICS.getType())) {
                return ActivityMode.GYMNASTICS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TRACK_FIELD.getType())) {
                return ActivityMode.TRACK_FIELD.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROPE_SKIPPING.getType())) {
                return ActivityMode.SKIPPING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_MARTIAL_ARTS.getType())) {
                return ActivityMode.MARTIAL_ARTS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PLAY.getType())) {
                return ActivityMode.WARM_UP.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SNOW_SPORTS.getType())) {
                return ActivityMode.SNOW_SPORTS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_LACROSSE.getType())) {
                return ActivityMode.LACROSSE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SINGLE_BAR.getType())) {
                return ActivityMode.HORIZONTAL_BAR.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PARALLEL_BARS.getType())) {
                return ActivityMode.PARALLEL_BARS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HULA_HOPE.getType())) {
                return ActivityMode.HULA_HOOP.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DARTS.getType())) {
                return ActivityMode.DARTS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ARCHERY.getType())) {
                return ActivityMode.ARCHERY.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HORSE_RIDING.getType())) {
                return ActivityMode.HORSE_RIDING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SHUTTLECOCK.getType())) {
                return ActivityMode.SHUTTLECOCK.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ICE_HOCKEY.getType())) {
                return ActivityMode.ICE_HOCKEY.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_WAIST_TRAINING.getType())) {
                return ActivityMode.SIT_UPS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SIT_UPS.getType())) {
                return ActivityMode.WAIST_TRAINING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PUSH_UPS.getType())) {
                return ActivityMode.PUSH_UPS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TREADMILL.getType())) {
                return ActivityMode.TREADMILL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BATTLE_ROPE.getType())) {
                return ActivityMode.BATTLE_ROPE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SMITH_MACHINE.getType())) {
                return ActivityMode.SMITH_MACHINE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PULL_UP.getType())) {
                return ActivityMode.PULL_UPS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PLANK.getType())) {
                return ActivityMode.PLANK.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_KABADDI.getType())) {
                return ActivityMode.KABADDI.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SHOT_PUT.getType())) {
                return ActivityMode.SHOT_PUT.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SOLID_BALL.getType())) {
                return ActivityMode.SOLID_BALL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_JAVELIN.getType())) {
                return ActivityMode.JAVELIN.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_LONG_JUMP.getType())) {
                return ActivityMode.LONG_JUMP.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HIGH_JUMP.getType())) {
                return ActivityMode.HIGH_JUMP.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROCK_CLIMBING.getType())) {
                return ActivityMode.ROCK_CLIMBING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SQUARE_DANCE.getType())) {
                return ActivityMode.SQUARE_DANCE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DUMBBELLS.getType())) {
                return ActivityMode.DUMBBELLS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_KARTING.getType())) {
                return ActivityMode.KARTING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DODGE_BALL.getType())) {
                return ActivityMode.DODGEBALL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_YOYO.getType())) {
                return ActivityMode.YOYO.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_LOCKING.getType())) {
                return ActivityMode.LOCKING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BURPEES.getType())) {
                return ActivityMode.BURPEES.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BELLY_DANCE.getType())) {
                return ActivityMode.BELLY_DANCE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SKATEBOARDING.getType())) {
                return ActivityMode.SKATEBOARDING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PARKOUR.getType())) {
                return ActivityMode.PARKOUR.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_JAZZ_DANCE.getType())) {
                return ActivityMode.JAZZ_DANCE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_MODERN_DANCE.getType())) {
                return ActivityMode.MODERN_DANCE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_AEROBICS.getType())) {
                return ActivityMode.AEROBICS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CROSS_FITTING.getType())) {
                return ActivityMode.CROSS_FIT.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_LATIN_DANCE.getType())) {
                return ActivityMode.LATIN_DANCE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_POPING.getType())) {
                return ActivityMode.POPPING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BALLET.getType())) {
                return ActivityMode.BALLET.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BASEBALL.getType())) {
                return ActivityMode.BASEBALL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BOWLING.getType())) {
                return ActivityMode.BOWLING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_RACQUETBALL.getType())) {
                return ActivityMode.RACQUETBALL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CURLING.getType())) {
                return ActivityMode.CURLING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HUNTING.getType())) {
                return ActivityMode.HUNTING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SNOWBOARDING.getType())) {
                return ActivityMode.SNOWBOARDING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BASKETBALL.getType())) {
                return ActivityMode.BASKETBALL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FISHING.getType())) {
                return ActivityMode.FISHING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DISC_SPORTS.getType())) {
                return ActivityMode.DISC_SPORTS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_RUGBY.getType())) {
                return ActivityMode.RUGBY.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_GOLF.getType())) {
                return ActivityMode.GOLF.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SKI.getType())) {
                return ActivityMode.SKI.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FITNESS_GAME.getType())) {
                return ActivityMode.FITNESS_GAMING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_AEROBICS_GYMS.getType())) {
                return ActivityMode.AEROBICS_GYMS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_GROUP_TRAINING.getType())) {
                return ActivityMode.GROUP_TRAINING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CARDIO_BOXING.getType())) {
                return ActivityMode.CARDIO_BOXING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_MOUNTAINEERING.getType())) {
                return ActivityMode.MOUNTAINEERING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FENCING.getType())) {
                return ActivityMode.FENCING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SOFTBALL.getType())) {
                return ActivityMode.SOFTBALL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_STAIR_CLIMBING.getType())) {
                return ActivityMode.CLIMB_STAIRS.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_AMERICAN_FOOTBALL.getType())) {
                return ActivityMode.AMERICAN_FOOTBALL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_VOLLEYBALL.getType())) {
                return ActivityMode.VOLLEYBALL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROLLING.getType())) {
                return ActivityMode.ROLLING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PICKLE_BALL.getType())) {
                return ActivityMode.PICKLEBALL.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HOCKEY.getType())) {
                return ActivityMode.HOCKEY.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BOXING.getType())) {
                return ActivityMode.BOXING.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TAEKWONDO.getType())) {
                return ActivityMode.TAEKWONDO.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_KARATE.getType())) {
                return ActivityMode.KARATE.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FLEXIBILITY.getType())) {
                return ActivityMode.FLEXIBILITY.name();
            }
            if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HANDBALL.getType())) {
                return ActivityMode.HANDBALL.name();
            }
            if (!Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROWING_MACHINE.getType()) && !Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROWER.getType())) {
                if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_JUMP_ROPE.getType())) {
                    return ActivityMode.SKIPPING.name();
                }
                if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_STAIRS.getType())) {
                    return ActivityMode.CLIMB_STAIRS.name();
                }
                if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CROSS_TRAINING.getType())) {
                    return ActivityMode.CROSS_FIT.name();
                }
                return null;
            }
            return ActivityMode.ROWING.name();
        }
        return ActivityMode.WALK.name();
    }

    public final int getShortcutMenuValueIDO(@NotNull String type, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.steps))) {
            return 17;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.sleep))) {
            return 3;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.heart_rate))) {
            return 2;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.spo2))) {
            return 18;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.stress))) {
            return 13;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.sports))) {
            return 16;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.weather))) {
            return 10;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.music))) {
            return 6;
        }
        return Intrinsics.areEqual(type, context.getResources().getString(R.string.alexa)) ? 20 : 0;
    }

    public final int getShortcutMenuValueTouch(@NotNull String type, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.steps))) {
            return 1;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.sleep))) {
            return 2;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.heart_rate))) {
            return 3;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.spo2))) {
            return 8;
        }
        if (Intrinsics.areEqual(type, context.getResources().getString(R.string.weather))) {
            return 4;
        }
        return Intrinsics.areEqual(type, context.getResources().getString(R.string.music)) ? 5 : 0;
    }

    @Nullable
    public final String getShortcutTypeIDO(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 6) {
                        if (i2 != 10) {
                            if (i2 != 13) {
                                if (i2 != 20) {
                                    switch (i2) {
                                        case 16:
                                            return context.getResources().getString(R.string.sports);
                                        case 17:
                                            return context.getResources().getString(R.string.steps);
                                        case 18:
                                            return context.getResources().getString(R.string.spo2);
                                        default:
                                            return null;
                                    }
                                }
                                return context.getResources().getString(R.string.alexa);
                            }
                            return context.getResources().getString(R.string.stress);
                        }
                        return context.getResources().getString(R.string.weather);
                    }
                    return context.getResources().getString(R.string.music);
                }
                return context.getResources().getString(R.string.sleep);
            }
            return context.getResources().getString(R.string.heart_rate);
        }
        return context.getResources().getString(R.string.steps);
    }

    @Nullable
    public final String getShortcutTypeTouch(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            if (i2 != 8) {
                                return null;
                            }
                            return context.getResources().getString(R.string.spo2);
                        }
                        return context.getResources().getString(R.string.music);
                    }
                    return context.getResources().getString(R.string.weather);
                }
                return context.getResources().getString(R.string.heart_rate);
            }
            return context.getResources().getString(R.string.sleep);
        }
        return context.getResources().getString(R.string.steps);
    }

    @Nullable
    public final String getShortcutsActiveDisplayEnumIDO(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 6) {
                        if (i2 != 10) {
                            if (i2 != 13) {
                                switch (i2) {
                                    case 16:
                                        return ActiveDisplaysEnum.ACTIVITIES.name();
                                    case 17:
                                        return ActiveDisplaysEnum.STEPS.name();
                                    case 18:
                                        return ActiveDisplaysEnum.SPO2.name();
                                    default:
                                        return null;
                                }
                            }
                            return ActiveDisplaysEnum.STRESS.name();
                        }
                        return ActiveDisplaysEnum.WEATHER.name();
                    }
                    return ActiveDisplaysEnum.MUSIC.name();
                }
                return ActiveDisplaysEnum.SLEEP.name();
            }
            return ActiveDisplaysEnum.HR.name();
        }
        return ActiveDisplaysEnum.STEPS.name();
    }

    @Nullable
    public final String getShortcutsActiveDisplayEnumTouch(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            if (i2 != 8) {
                                return null;
                            }
                            return ActiveDisplaysEnum.SPO2.name();
                        }
                        return ActiveDisplaysEnum.MUSIC.name();
                    }
                    return ActiveDisplaysEnum.WEATHER.name();
                }
                return ActiveDisplaysEnum.HR.name();
            }
            return ActiveDisplaysEnum.SLEEP.name();
        }
        return ActiveDisplaysEnum.STEPS.name();
    }

    public final int getShortcutsMenuValueByEnumIDO(@NotNull String value, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(context, "context");
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.STEPS.name())) {
            return 17;
        }
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.SLEEP.name())) {
            return 3;
        }
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.HR.name())) {
            return 2;
        }
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.SPO2.name())) {
            return 18;
        }
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.STRESS.name())) {
            return 13;
        }
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.ACTIVITIES.name())) {
            return 16;
        }
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.WEATHER.name())) {
            return 10;
        }
        return Intrinsics.areEqual(value, ActiveDisplaysEnum.MUSIC.name()) ? 6 : 0;
    }

    public final int getShortcutsMenuValueByEnumTouch(@NotNull String value, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(context, "context");
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.STEPS.name())) {
            return 1;
        }
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.SLEEP.name())) {
            return 2;
        }
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.HR.name())) {
            return 3;
        }
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.SPO2.name())) {
            return 8;
        }
        if (Intrinsics.areEqual(value, ActiveDisplaysEnum.WEATHER.name())) {
            return 4;
        }
        return Intrinsics.areEqual(value, ActiveDisplaysEnum.MUSIC.name()) ? 5 : 0;
    }

    @Nullable
    public final SleepScoreData getSleepScoreFromDbWithDate(@NotNull Context context, @NotNull String date) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(date, "date");
        return SleepScoreRepository.Companion.getInstance(context).getSleepScoreData(date, BleApiManager.getInstance(context).getBleApi().getMacAddress());
    }

    @NotNull
    public final Drawable getSleepScoreRange(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Drawable sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_good_smiley);
        boolean z = true;
        if (i2 >= 0 && i2 < 61) {
            sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_poor_smiley);
        } else {
            if (61 <= i2 && i2 < 71) {
                sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_fair_smiley);
            } else {
                if (71 <= i2 && i2 < 81) {
                    sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_average_smiley);
                } else {
                    if (81 <= i2 && i2 < 91) {
                        sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_good_smiley);
                    } else {
                        if (91 > i2 || i2 >= 201) {
                            z = false;
                        }
                        if (z) {
                            sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_excellent_smiley);
                        }
                    }
                }
            }
        }
        Intrinsics.checkNotNullExpressionValue(sleepScoreImage, "sleepScoreImage");
        return sleepScoreImage;
    }

    @NotNull
    public final Drawable getSleepScoreRangeIDo(int i2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Drawable sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_poor_ido_smiley);
        boolean z = true;
        if (i2 >= 0 && i2 < 50) {
            sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_verypoor_ido_smiley);
        } else {
            if (50 <= i2 && i2 < 70) {
                sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_poor_ido_smiley);
            } else {
                if (70 <= i2 && i2 < 85) {
                    sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_fair_ido_smiley);
                } else {
                    if (85 > i2 || i2 >= 101) {
                        z = false;
                    }
                    if (z) {
                        sleepScoreImage = context.getResources().getDrawable(R.drawable.ic_good_ido_smiley);
                    }
                }
            }
        }
        Intrinsics.checkNotNullExpressionValue(sleepScoreImage, "sleepScoreImage");
        return sleepScoreImage;
    }

    public final double getSpeedValue(@NotNull Context context, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        String format = decimalFormat.format(convertKMToMiles(i2));
        Intrinsics.checkNotNullExpressionValue(format, "decimalFormat.format(con…oMiles(speed.toDouble()))");
        return Double.parseDouble(format);
    }

    @NotNull
    public final ActivityTypeModel getSportsTypeValueIDO(@NotNull String type, @NotNull Context context) {
        int i2;
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_INDOOR_WALK.getType())) {
            i2 = 53;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OUTDOOR_WALK.getType())) {
            i2 = 52;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_INDOOR_CYCLE.getType())) {
            i2 = 51;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_INDOOR_RUN.getType())) {
            i2 = 49;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OUTDOOR_CYCLE.getType())) {
            i2 = 50;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OUTDOOR_RUN.getType())) {
            i2 = 48;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HIIT.getType())) {
            i2 = 58;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CRICKET.getType())) {
            i2 = 75;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DANCING.getType())) {
            i2 = 29;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_POOL_SWIM.getType())) {
            i2 = 54;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ELLIPTICAL.getType())) {
            i2 = 56;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_WATER_SWIM.getType())) {
            i2 = 55;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OTHER.getType())) {
            i2 = 8;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PILATES.getType())) {
            i2 = 32;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ZUMBA.getType())) {
            i2 = 35;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_YOGA.getType())) {
            i2 = 18;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROWING_MACHINE.getType())) {
            i2 = 57;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ONFOOT.getType())) {
            i2 = 4;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SOCKER.getType())) {
            i2 = 22;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FITNESS.getType())) {
            i2 = 9;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CORE_TRAINING.getType())) {
            i2 = 102;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ORGANIZE_AND_RELAX.getType())) {
            i2 = 104;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FUNCTIONAL_STRENGTH_TRAINING.getType())) {
            i2 = 101;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TRADITIONAL_STRENGTH_TRAINING.getType())) {
            i2 = 110;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_STEPPER.getType())) {
            i2 = 103;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_AEROBICS.getType())) {
            i2 = 17;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SIT_UPS.getType())) {
            i2 = 13;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PLANK.getType())) {
            i2 = 37;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_JUMPING_JACKS.getType())) {
            i2 = 114;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PULL_UP.getType())) {
            i2 = 112;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PUSH_UPS.getType())) {
            i2 = 14;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SQUAT.getType())) {
            i2 = 115;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HIGH_KNEE_LIFT.getType())) {
            i2 = 116;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DUMBBELLS.getType())) {
            i2 = 15;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BARBELL.getType())) {
            i2 = 118;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BOXING.getType())) {
            i2 = 117;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FREE_SPARRING.getType())) {
            i2 = 123;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HORIZONTAL_BAR.getType())) {
            i2 = 127;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PARALLEL_BARS.getType())) {
            i2 = 128;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CARDIO_CRUISER.getType())) {
            i2 = 130;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CLIMBING_MACHINE.getType())) {
            i2 = 129;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BOWLING.getType())) {
            i2 = 131;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TENNIS.getType())) {
            i2 = 24;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TABLE_TENNIS.getType())) {
            i2 = 20;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_GOLF.getType())) {
            i2 = 25;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BASKETBALL.getType())) {
            i2 = 21;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BADMINTON.getType())) {
            i2 = 7;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HOCKEY.getType())) {
            i2 = 133;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_AMERICAN_FOOTBALL.getType())) {
            i2 = 134;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HANDBALL.getType())) {
            i2 = 137;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_STREET_DANCE.getType())) {
            i2 = 152;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CLIMBING.getType())) {
            i2 = 6;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROPE_SKIPPING.getType())) {
            i2 = 19;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_STAIR_CLIMBING.getType())) {
            i2 = 158;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SLEDDING.getType())) {
            i2 = 161;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SKATING.getType())) {
            i2 = 27;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SURFING.getType())) {
            i2 = 170;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SAIL_BOATING.getType())) {
            i2 = 171;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SKATEBOARDING.getType())) {
            i2 = 180;
        } else {
            i2 = Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROCK_CLIMBING.getType()) ? 181 : 0;
        }
        return new ActivityTypeModel(i2);
    }

    @NotNull
    public final ActivityTypeModel getSportsTypeValueTouch(@NotNull String type, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        int i2 = 22;
        if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_INDOOR_WALK.getType())) {
            i2 = 53;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OUTDOOR_WALK.getType())) {
            i2 = 52;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_INDOOR_CYCLE.getType())) {
            i2 = 11;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_INDOOR_RUN.getType())) {
            i2 = 49;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OUTDOOR_CYCLE.getType())) {
            i2 = 50;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_OUTDOOR_RUN.getType())) {
            i2 = 48;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_STRENGTH_TRAINING.getType())) {
            i2 = 8;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BADMINTON.getType())) {
            i2 = 7;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_YOGA.getType())) {
            i2 = 18;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ELLIPTICAL.getType())) {
            i2 = 56;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FREE_WORKOUT.getType())) {
            i2 = 58;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROWER.getType())) {
            i2 = 57;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CRICKET.getType())) {
            i2 = 75;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TENNIS.getType())) {
            i2 = 14;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HIKING.getType())) {
            i2 = 4;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FOLK_DANCE.getType())) {
            i2 = 15;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HANDCAR.getType())) {
            i2 = 72;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_KENDO.getType())) {
            i2 = 73;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TABLE_TENNIS.getType())) {
            i2 = 9;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SOCKER.getType())) {
            i2 = 76;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_MEDITATION.getType())) {
            i2 = 77;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_WRESTLING.getType())) {
            i2 = 78;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_STEPPER.getType())) {
            i2 = 79;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TAI_CHI.getType())) {
            i2 = 80;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_GYMNASTICS.getType())) {
            i2 = 81;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TRACK_FIELD.getType())) {
            i2 = 82;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_JUMP_ROPE.getType())) {
            i2 = 83;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_MARTIAL_ARTS.getType())) {
            i2 = 84;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PLAY.getType())) {
            i2 = 85;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SNOW_SPORTS.getType())) {
            i2 = 86;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_LACROSSE.getType())) {
            i2 = 87;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SINGLE_BAR.getType())) {
            i2 = 88;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PARALLEL_BARS.getType())) {
            i2 = 89;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HULA_HOPE.getType())) {
            i2 = 91;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DARTS.getType())) {
            i2 = 92;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ARCHERY.getType())) {
            i2 = 93;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HORSE_RIDING.getType())) {
            i2 = 94;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SHUTTLECOCK.getType())) {
            i2 = 95;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ICE_HOCKEY.getType())) {
            i2 = 96;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_WAIST_TRAINING.getType())) {
            i2 = 98;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SIT_UPS.getType())) {
            i2 = 97;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PUSH_UPS.getType())) {
            i2 = 99;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TREADMILL.getType())) {
            i2 = 100;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BATTLE_ROPE.getType())) {
            i2 = 101;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SMITH_MACHINE.getType())) {
            i2 = 102;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PULL_UP.getType())) {
            i2 = 103;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ZUMBA.getType())) {
            i2 = 104;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PLANK.getType())) {
            i2 = 105;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_KABADDI.getType())) {
            i2 = 106;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SHOT_PUT.getType())) {
            i2 = 107;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SOLID_BALL.getType())) {
            i2 = 108;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_JAVELIN.getType())) {
            i2 = 109;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_LONG_JUMP.getType())) {
            i2 = 110;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HIGH_JUMP.getType())) {
            i2 = 111;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROCK_CLIMBING.getType())) {
            i2 = 112;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SQUARE_DANCE.getType())) {
            i2 = 114;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DUMBBELLS.getType())) {
            i2 = 115;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_KARTING.getType())) {
            i2 = 116;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DODGE_BALL.getType())) {
            i2 = 117;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_YOYO.getType())) {
            i2 = 118;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_LOCKING.getType())) {
            i2 = 119;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BURPEES.getType())) {
            i2 = 121;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BELLY_DANCE.getType())) {
            i2 = 122;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SKATEBOARDING.getType())) {
            i2 = 123;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PARKOUR.getType())) {
            i2 = 124;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_JAZZ_DANCE.getType())) {
            i2 = 125;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_MODERN_DANCE.getType())) {
            i2 = 126;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_AEROBICS.getType())) {
            i2 = 45;
        } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PILATES.getType())) {
            i2 = 17;
        } else {
            SportsType sportsType = SportsType.SPORT_TYPE_CROSS_FITTING;
            if (!Intrinsics.areEqual(type, sportsType.getType())) {
                if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_LATIN_DANCE.getType())) {
                    i2 = 26;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_POPING.getType())) {
                    i2 = 27;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BALLET.getType())) {
                    i2 = 29;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BASEBALL.getType())) {
                    i2 = 31;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BOWLING.getType())) {
                    i2 = 32;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_RACQUETBALL.getType())) {
                    i2 = 33;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CURLING.getType())) {
                    i2 = 34;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HUNTING.getType())) {
                    i2 = 35;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SNOWBOARDING.getType())) {
                    i2 = 36;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BASKETBALL.getType())) {
                    i2 = 21;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FISHING.getType())) {
                    i2 = 37;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_DISC_SPORTS.getType())) {
                    i2 = 38;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_RUGBY.getType())) {
                    i2 = 39;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_GOLF.getType())) {
                    i2 = 40;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CORE_TRAINING.getType())) {
                    i2 = 42;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SKATING.getType())) {
                    i2 = 43;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FITNESS_GAME.getType())) {
                    i2 = 44;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_AEROBICS_GYMS.getType())) {
                    i2 = 127;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_GROUP_TRAINING.getType())) {
                    i2 = 46;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CARDIO_BOXING.getType())) {
                    i2 = 47;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_MOUNTAINEERING.getType())) {
                    i2 = 6;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FENCING.getType())) {
                    i2 = 59;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_SOFTBALL.getType())) {
                    i2 = 60;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_STAIRS.getType())) {
                    i2 = 61;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_AMERICAN_FOOTBALL.getType())) {
                    i2 = 62;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_VOLLEYBALL.getType())) {
                    i2 = 63;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_ROLLING.getType())) {
                    i2 = 64;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_PICKLE_BALL.getType())) {
                    i2 = 65;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HOCKEY.getType())) {
                    i2 = 66;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_BOXING.getType())) {
                    i2 = 67;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_TAEKWONDO.getType())) {
                    i2 = 68;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_KARATE.getType())) {
                    i2 = 69;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_FLEXIBILITY.getType())) {
                    i2 = 70;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HANDBALL.getType())) {
                    i2 = 71;
                } else if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_CROSS_TRAINING.getType())) {
                    i2 = 20;
                } else if (!Intrinsics.areEqual(type, sportsType.getType())) {
                    if (R(context)) {
                        if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HIIT.getType())) {
                            i2 = 74;
                        }
                        i2 = 0;
                    } else {
                        if (Intrinsics.areEqual(type, SportsType.SPORT_TYPE_HIIT.getType())) {
                            i2 = 120;
                        }
                        i2 = 0;
                    }
                }
            }
        }
        return new ActivityTypeModel(i2);
    }

    @Nullable
    public final String getSwimStyleByValue(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            return null;
                        }
                        return SwimmingStyleEnum.BUTTERFLY.getSwimStyle();
                    }
                    return SwimmingStyleEnum.BACK_STROKE.getSwimStyle();
                }
                return SwimmingStyleEnum.BREAST_STROKE.getSwimStyle();
            }
            return SwimmingStyleEnum.FREE_STYLE.getSwimStyle();
        }
        return SwimmingStyleEnum.MEDLEY.getSwimStyle();
    }

    @Nullable
    public final String getSyncErrorType(@Nullable Integer num) {
        if (num != null) {
            int intValue = num.intValue();
            if (intValue == CommandError.COMMAND_ERROR_TIMEOUT.value) {
                return "cmd_timeout";
            }
            if (intValue == CommandError.COMMAND_FAILED.value) {
                return "cmd_failed";
            }
            if (intValue == CommandError.WATCH_BUSY.value || intValue == CommandError.SERVICE_BUSY.value) {
                return "dvc_busy";
            }
        }
        return null;
    }

    public final double getTimeDifferenceWorldClock(@NotNull String dateStart, @NotNull String dateStop, @NotNull String formatter) {
        Intrinsics.checkNotNullParameter(dateStart, "dateStart");
        Intrinsics.checkNotNullParameter(dateStop, "dateStop");
        Intrinsics.checkNotNullParameter(formatter, "formatter");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter, Locale.ENGLISH);
        double d2 = 0.0d;
        try {
            Date parse = simpleDateFormat.parse(dateStart);
            Date parse2 = simpleDateFormat.parse(dateStop);
            Intrinsics.checkNotNull(parse2);
            long time = parse2.getTime();
            Intrinsics.checkNotNull(parse);
            d2 = TimeUnit.MILLISECONDS.toMinutes(time - parse.getTime()) / 60.0d;
            return StringsKt__StringsKt.contains$default((CharSequence) String.valueOf(d2), (CharSequence) "-", false, 2, (Object) null) ? Double.parseDouble(kotlin.text.m.replace$default(String.valueOf(d2), "-", "+", false, 4, (Object) null)) : -d2;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return d2;
        }
    }

    @Nullable
    public final Long getTimeStampFromDate(@NotNull String dateValue) {
        Intrinsics.checkNotNullParameter(dateValue, "dateValue");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parse = simpleDateFormat.parse(dateValue);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        Date parse2 = simpleDateFormat.parse(simpleDateFormat.format(parse));
        Intrinsics.checkNotNull(parse2, "null cannot be cast to non-null type java.util.Date");
        return Long.valueOf(parse2.getTime());
    }

    @Nullable
    public final String getTimeZoneOffset() {
        String format = new SimpleDateFormat("Z").format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime());
        StringBuilder sb = new StringBuilder(format);
        sb.insert(format.length() - 2, ":");
        return sb.toString();
    }

    @NotNull
    public final String getUserAgent(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            try {
                String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                Intrinsics.checkNotNullExpressionValue(str, "info.versionName");
                StringBuilder sb = new StringBuilder();
                sb.append(context.getResources().getString(R.string.cove_app_name));
                sb.append('/');
                sb.append(str);
                sb.append(" (android/");
                sb.append(Build.VERSION.RELEASE);
                sb.append(';');
                String MANUFACTURER = Build.MANUFACTURER;
                Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
                sb.append(kotlin.text.m.replace$default(MANUFACTURER, ";", HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null));
                sb.append('/');
                String MODEL = Build.MODEL;
                Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
                sb.append(kotlin.text.m.replace$default(MODEL, ";", HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null));
                sb.append(HexStringBuilder.COMMENT_END_CHAR);
                return sb.toString();
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                return "";
            }
        } catch (Exception unused) {
            return "";
        }
    }

    @Nullable
    public final Integer getVersionCodeFromFirmware(@NotNull String firmwareVersion) {
        Intrinsics.checkNotNullParameter(firmwareVersion, "firmwareVersion");
        try {
            return Integer.valueOf(Integer.parseInt(kotlin.text.m.replace$default(kotlin.text.m.replace$default(kotlin.text.m.replace$default(firmwareVersion, CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "", false, 4, (Object) null), ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", false, 4, (Object) null), ".", "", false, 4, (Object) null)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @NotNull
    public final String getWithDecimalPointAfterTwoDigit(@NotNull String num) {
        Intrinsics.checkNotNullParameter(num, "num");
        if (num.length() <= 2) {
            return num;
        }
        StringBuilder sb = new StringBuilder();
        String substring = num.substring(0, 2);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        sb.append(substring);
        sb.append('.');
        String substring2 = num.substring(2);
        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
        sb.append(substring2);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0067, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isCurrentFirmwareHasIssueWithBatteryPercentage(@org.jetbrains.annotations.NotNull android.content.Context r6) {
        /*
            r5 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            com.google.gson.Gson r0 = new com.google.gson.Gson
            r0.<init>()
            com.coveiot.covepreferences.SessionManager r1 = com.coveiot.covepreferences.SessionManager.getInstance(r6)
            java.lang.String r1 = r1.getBleDeviceInfo()
            java.lang.Class<com.coveiot.sdk.ble.model.BleDeviceInfo> r2 = com.coveiot.sdk.ble.model.BleDeviceInfo.class
            java.lang.Object r0 = r0.fromJson(r1, r2)
            com.coveiot.sdk.ble.model.BleDeviceInfo r0 = (com.coveiot.sdk.ble.model.BleDeviceInfo) r0
            if (r0 == 0) goto L2c
            java.lang.String r0 = r0.getFirmwareRevision()
            if (r0 == 0) goto L2c
            java.lang.String r0 = r0.toLowerCase()
            java.lang.String r1 = "this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            goto L2d
        L2c:
            r0 = 0
        L2d:
            com.coveiot.android.devicemodels.DeviceUtils$Companion r1 = com.coveiot.android.devicemodels.DeviceUtils.Companion
            boolean r2 = r1.isOPP1Device(r6)
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L69
            if (r0 == 0) goto L68
            int r6 = r0.hashCode()
            r1 = 506438816(0x1e2fa4a0, float:9.2984665E-21)
            if (r6 == r1) goto L5f
            r1 = 506438840(0x1e2fa4b8, float:9.298486E-21)
            if (r6 == r1) goto L56
            r1 = 506438842(0x1e2fa4ba, float:9.2984875E-21)
            if (r6 == r1) goto L4d
            goto L68
        L4d:
            java.lang.String r6 = "v0.00.44"
            boolean r6 = r0.equals(r6)
            if (r6 != 0) goto L67
            goto L68
        L56:
            java.lang.String r6 = "v0.00.42"
            boolean r6 = r0.equals(r6)
            if (r6 != 0) goto L67
            goto L68
        L5f:
            java.lang.String r6 = "v0.00.39"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L68
        L67:
            return r3
        L68:
            return r4
        L69:
            boolean r6 = r1.isOPP5Device(r6)
            if (r6 == 0) goto L78
            java.lang.String r6 = "v0.00.16"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r6)
            if (r6 == 0) goto L78
            return r3
        L78:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.utils.PayUtils.isCurrentFirmwareHasIssueWithBatteryPercentage(android.content.Context):boolean");
    }

    public final boolean isDNDEnabled(@Nullable DoNotDisturbData doNotDisturbData) {
        if (doNotDisturbData != null) {
            return doNotDisturbData.isDnd_on_off() || doNotDisturbData.isSchedule_on_off();
        }
        return false;
    }

    public final boolean isEnableSleepScore(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return UserDataManager.getInstance(context).isEnableSleepEnergyScoreFeature(context) || BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isSleepScoreSupportsFromBand();
    }

    public final boolean isFirmwareSupportsDND(@NotNull String firmwareVersion) {
        Intrinsics.checkNotNullParameter(firmwareVersion, "firmwareVersion");
        if (!(firmwareVersion.length() == 0)) {
            Locale ENGLISH = Locale.ENGLISH;
            Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
            String lowerCase = firmwareVersion.toLowerCase(ENGLISH);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, false, 2, (Object) null)) {
                Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
                String lowerCase2 = firmwareVersion.toLowerCase(ENGLISH);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                String[] strArr = (String[]) new Regex("\\.").split(kotlin.text.m.replace$default(lowerCase2, CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "", false, 4, (Object) null), 0).toArray(new String[0]);
                if (strArr.length > 2) {
                    try {
                        int parseInt = Integer.parseInt(strArr[0]);
                        double parseDouble = Double.parseDouble(strArr[1] + '.' + strArr[2]);
                        if (parseInt == Integer.parseInt(AppConstants.DND_SUPPORTED_FW_HIGHER_VERSION.getValue())) {
                            if (parseDouble >= Double.parseDouble(AppConstants.DND_SUPPORTED_FW_LOWER_VERSION.getValue())) {
                                return true;
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    public final boolean isFutureDate(@NotNull Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            return simpleDateFormat.parse(simpleDateFormat.format(new Date())).before(simpleDateFormat.parse(simpleDateFormat.format(date)));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public final boolean isJobServiceOn(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("jobscheduler");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.job.JobScheduler");
        for (JobInfo jobInfo : ((JobScheduler) systemService).getAllPendingJobs()) {
            if (jobInfo.getId() == f5431a) {
                return true;
            }
        }
        return false;
    }

    public final boolean isManufacturerNeedBatteryOptimizationDelay(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BatteryOptimizationConfig batteryOptimizationConfig = UserDataManager.getInstance(context).getBatteryOptimizationConfig();
        boolean z = false;
        if (batteryOptimizationConfig != null && !AppUtils.isEmpty(batteryOptimizationConfig.getConfigs())) {
            for (BatteryOptimizationConfig.Configs configs : batteryOptimizationConfig.getConfigs()) {
                if (kotlin.text.m.equals(configs.getManufacturer(), Build.MANUFACTURER, true)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final boolean isSameDate(@NotNull Calendar calender1, @NotNull Calendar calender2) {
        Intrinsics.checkNotNullParameter(calender1, "calender1");
        Intrinsics.checkNotNullParameter(calender2, "calender2");
        return calender1.get(6) == calender2.get(6) && calender1.get(1) == calender2.get(1);
    }

    public final boolean isSleepScoreFromBand(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isSleepScoreSupportsFromBand();
    }

    public final boolean isWeatherFeatureSupported(@Nullable Context context) {
        DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
        return (deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()]) == 1;
    }

    @Nullable
    public final Spannable linkifyHtml(@NotNull String html, int i2) {
        URLSpan[] uRLSpanArr;
        Intrinsics.checkNotNullParameter(html, "html");
        Spanned fromHtml = Html.fromHtml(html);
        Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(html)");
        Object[] spans = fromHtml.getSpans(0, fromHtml.length(), URLSpan.class);
        Intrinsics.checkNotNullExpressionValue(spans, "text.getSpans(0, text.length, URLSpan::class.java)");
        SpannableString spannableString = new SpannableString(fromHtml);
        Linkify.addLinks(spannableString, i2);
        for (URLSpan uRLSpan : (URLSpan[]) spans) {
            spannableString.setSpan(uRLSpan, fromHtml.getSpanStart(uRLSpan), fromHtml.getSpanEnd(uRLSpan), 0);
        }
        return spannableString;
    }

    @NotNull
    public final Intent openFile(@NotNull Context context, @NotNull File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        Intent intent = new Intent("android.intent.action.VIEW", FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file));
        intent.addFlags(1);
        return intent;
    }

    public final void redirectToPlaystore(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.coveiot.android.boat"));
        context.startActivity(intent);
    }

    @NotNull
    public final String removeEmojisAndDifferentLanguage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new Regex("[^ A-Za-z0-9_@./#&+-=|\\\\;:‘“<>,(){}?*%$₹!]").replace(message, HexStringBuilder.DEFAULT_SEPARATOR);
    }

    public final float roundOffDecimalTillOnePlace(float f2) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String format = decimalFormat.format(Float.valueOf(f2));
        Intrinsics.checkNotNullExpressionValue(format, "df.format(number)");
        return Float.parseFloat(format);
    }

    public final void saveFile(@NotNull Context context, @NotNull InputStream inputStream, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), str);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read >= 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.flush();
                        context.startActivity(INSTANCE.openFile(context, file));
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream, null);
                        CloseableKt.closeFinally(inputStream, null);
                        return;
                    }
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileOutputStream, th);
                    throw th2;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            LogHelper.d(d, "Exception while saving legal doc file");
        }
    }

    @RequiresApi(29)
    public final void saveFileUsingMediaStore(@NotNull Context context, @NotNull InputStream inputStream, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", "application/pdf");
        contentValues.put("relative_path", Environment.DIRECTORY_DOWNLOADS);
        ContentResolver contentResolver = context.getContentResolver();
        Uri insert = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
        if (insert != null) {
            OutputStream openOutputStream = contentResolver.openOutputStream(insert);
            try {
                Intrinsics.checkNotNull(openOutputStream);
                ByteStreamsKt.copyTo(inputStream, openOutputStream, 8192);
                Intent intent = new Intent("android.intent.action.VIEW", insert);
                intent.addFlags(1);
                context.startActivity(intent);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(openOutputStream, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(openOutputStream, th);
                    throw th2;
                }
            }
        }
    }

    public final void saveLogFile(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = context.getFilesDir().getAbsolutePath().toString();
        File file = new File(str, "Log_" + AppUtils.formatDate(new Date(), "yyyy_MM_dd_HH_mm") + ".txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        Runtime.getRuntime().exec("logcat -d -f" + file.getAbsolutePath());
        new Handler(Looper.getMainLooper()).postDelayed(new LogShareRunnable(context, file), 1000L);
    }

    public final void scheduleJobImmediatly(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        LogHelper.d("PayUtils", "PeriodicSyncJobService initiated");
        ComponentName componentName = new ComponentName(context, ProbeService.class);
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putBoolean(Dashboard2Constants.IS_FROM_SCHEDULER, false);
        int i2 = f5431a;
        JobInfo.Builder builder = new JobInfo.Builder(i2, componentName);
        builder.setExtras(persistableBundle);
        builder.setMinimumLatency(0L);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        try {
            jobScheduler.cancel(i2);
        } catch (Exception unused) {
        }
        jobScheduler.schedule(builder.build());
    }

    public final void scheduleReminderJob(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        LogHelper.d("PayUtils", "Reminder Job  initiated");
        ComponentName componentName = new ComponentName(context, ReminderJobServiceJobService.class);
        int i2 = b;
        JobInfo.Builder builder = new JobInfo.Builder(i2, componentName);
        builder.setMinimumLatency(Spo2DataManager.getInstance(context).getReminderInterval() * 60 * 1000);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        try {
            jobScheduler.cancel(i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        jobScheduler.schedule(builder.build());
    }

    public final void setMarginTop(@NotNull View v2, int i2) {
        Intrinsics.checkNotNullParameter(v2, "v");
        ViewGroup.LayoutParams layoutParams = v2.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMargins(marginLayoutParams.leftMargin, i2, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
    }

    public final boolean shouldDisableAutoHR(@NotNull Context context) {
        com.coveiot.sdk.ble.model.BleDeviceInfo bleDeviceInfo;
        String firmwareRevision;
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceModelBean deviceModelBean = SessionManager.getInstance(context).getDeviceModelBean();
        String str = null;
        if ((deviceModelBean != null ? deviceModelBean.getDisableAutoHRFor() : null) != null) {
            DeviceModelBean deviceModelBean2 = SessionManager.getInstance(context).getDeviceModelBean();
            List<String> disableAutoHRFor = deviceModelBean2 != null ? deviceModelBean2.getDisableAutoHRFor() : null;
            if (disableAutoHRFor != null) {
                String bleDeviceInfo2 = SessionManager.getInstance(context).getBleDeviceInfo();
                if (bleDeviceInfo2 != null && (bleDeviceInfo = (com.coveiot.sdk.ble.model.BleDeviceInfo) new Gson().fromJson(bleDeviceInfo2, (Class<Object>) com.coveiot.sdk.ble.model.BleDeviceInfo.class)) != null && (firmwareRevision = bleDeviceInfo.getFirmwareRevision()) != null) {
                    str = firmwareRevision.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
                }
                if (disableAutoHRFor.contains("ALL")) {
                    return true;
                }
                return str != null && disableAutoHRFor.contains(str);
            }
            return false;
        }
        return false;
    }

    public final boolean shouldShowSleepEnergyScoreFeature(@NotNull DeviceType deviceType, @NotNull List<? extends DeviceRemoteConfig.DeviceModelsBean> deviceModelList) {
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceModelList, "deviceModelList");
        int size = deviceModelList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (Intrinsics.areEqual(deviceModelList.get(i2).getType(), DeviceUtils.Companion.getDeviceModelFromDeviceType(deviceType).getType())) {
                return true;
            }
        }
        return false;
    }

    public final void startUCrop(@NotNull Activity activity, @Nullable Fragment fragment, @Nullable Uri uri, int i2) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Uri fromFile = Uri.fromFile(new File(activity.getCacheDir(), "watch_face.jpg"));
        UCrop.Options options = new UCrop.Options();
        options.setCircleDimmedLayer(i2 == 0);
        options.setShowCropGrid(false);
        options.setShowCropFrame(i2 != 0);
        options.setHideBottomControls(true);
        options.setCropGridColumnCount(0);
        options.setCropGridRowCount(0);
        if (1 == i2) {
            options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        }
        options.setMaxBitmapSize(1024);
        if (fragment == null) {
            Intrinsics.checkNotNull(uri);
            UCrop.of(uri, fromFile).withOptions(options).withAspectRatio(1.0f, 1.0f).start(activity);
            return;
        }
        Intrinsics.checkNotNull(uri);
        UCrop.of(uri, fromFile).withOptions(options).withAspectRatio(1.0f, 1.0f).start(activity, fragment);
    }

    @NotNull
    public final List<String> toLowerCaseList(@NotNull List<String> originalList) {
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        ArrayList arrayList = new ArrayList();
        for (String str : originalList) {
            String lowerCase = str.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            arrayList.add(lowerCase);
        }
        return CollectionsKt___CollectionsKt.toList(arrayList);
    }

    public final boolean w(List<String> list, String str) {
        for (String str2 : list) {
            if (kotlin.text.m.equals(str, StringsKt__StringsKt.trim(str2).toString(), true)) {
                return true;
            }
        }
        return false;
    }

    @JvmStatic
    @NotNull
    public static final String getDayMonthFormatDate(long j2) {
        String format = AppUtils.getSimpleDateFormat("dd/MM").format(new Date(j2));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format(Date(timestamp))");
        return format;
    }

    public final int getAge(@NotNull Calendar dob) {
        Intrinsics.checkNotNullParameter(dob, "dob");
        Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(1) - dob.get(1);
        int i3 = calendar.get(2);
        int i4 = dob.get(2);
        if (i4 <= i3) {
            if (i4 != i3) {
                return i2;
            }
            if (dob.get(5) <= calendar.get(5)) {
                return i2;
            }
        }
        return i2 - 1;
    }

    public final int getAge(@NotNull String dobStr) {
        Intrinsics.checkNotNullParameter(dobStr, "dobStr");
        Date parseDate = AppUtils.parseDate(dobStr, "yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        Calendar calendar2 = Calendar.getInstance();
        int i2 = calendar2.get(1) - calendar.get(1);
        int i3 = calendar2.get(2);
        int i4 = calendar.get(2);
        if (i4 <= i3) {
            if (i4 != i3) {
                return i2;
            }
            if (calendar.get(5) <= calendar2.get(5)) {
                return i2;
            }
        }
        return i2 - 1;
    }
}
