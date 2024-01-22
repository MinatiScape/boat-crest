package com.coveiot.android.bleabstract;

import android.app.ActivityManager;
import android.app.ForegroundServiceStartNotAllowedException;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.bleabstract.exceptions.SetupException;
import com.coveiot.android.bleabstract.models.AppCapabilityData;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.FirmwareCapabilityData;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BleApiUtils {
    @NotNull
    public static final BleApiUtils INSTANCE = new BleApiUtils();

    /* renamed from: a  reason: collision with root package name */
    public static int f2909a = 0;
    public static int b = 0;
    public static boolean c = true;
    public static boolean d = true;
    @NotNull
    public static String e = "app_desc";
    @NotNull
    public static String f = "app_name";
    @Nullable
    public static String g = null;
    @NotNull
    public static String h = "Meta data to be added in AndroidManifest.xml. Refer to the documents.";
    @NotNull
    public static String i = "App icon to be added in AndroidManifest.xml.";
    @NotNull
    public static String j = "App icon in AndroidManifest.xml is malformed.";
    @NotNull
    public static String k = "App Name to be added in AndroidManifest.xml.";
    @NotNull
    public static String l = "App Name in AndroidManifest.xml is malformed.";
    @NotNull
    public static String m = "App desc to be added in AndroidManifest.xml.";
    @NotNull
    public static String n = "App desc in AndroidManifest.xml is malformed.";

    /* loaded from: classes2.dex */
    public enum ActivityMode {
        WALK,
        RUN,
        CYCLE,
        STRENGTH_TRAINING,
        BADMINTON,
        YOGA,
        ELLIPTICAL,
        FREE_EXERCISE,
        ROWING,
        CRICKET,
        TENNIS,
        HIKING,
        FOLK_DANCE,
        HAND_CYCLING,
        KENDO,
        PING_PONG,
        FOOTBALL,
        MEDITATION,
        WRESTLING,
        STEPPER,
        TAI_CHI,
        GYMNASTICS,
        TRACK_FIELD,
        SKIPPING,
        MARTIAL_ARTS,
        WARM_UP,
        SNOW_SPORTS,
        LACROSSE,
        HORIZONTAL_BAR,
        PARALLEL_BARS,
        HULA_HOOP,
        DARTS,
        ARCHERY,
        HORSE_RIDING,
        SHUTTLECOCK,
        ICE_HOCKEY,
        SIT_UPS,
        WAIST_TRAINING,
        PUSH_UPS,
        TREADMILL,
        BATTLE_ROPE,
        SMITH_MACHINE,
        PULL_UPS,
        ZUMBA,
        PLANK,
        KABADDI,
        SHOT_PUT,
        SOLID_BALL,
        JAVELIN,
        LONG_JUMP,
        HIGH_JUMP,
        ROCK_CLIMBING,
        SQUARE_DANCE,
        DUMBBELLS,
        KARTING,
        DODGEBALL,
        YOYO,
        LOCKING,
        HIIT,
        BURPEES,
        BELLY_DANCE,
        SKATEBOARDING,
        PARKOUR,
        JAZZ_DANCE,
        MODERN_DANCE,
        AEROBICS,
        PILATES,
        CROSS_FIT,
        LATIN_DANCE,
        POPPING,
        BALLET,
        BASEBALL,
        BOWLING,
        RACQUETBALL,
        CURLING,
        HUNTING,
        SNOWBOARDING,
        BASKETBALL,
        FISHING,
        DISC_SPORTS,
        RUGBY,
        GOLF,
        CORE_TRAINING,
        SKI,
        FITNESS_GAMING,
        AEROBICS_GYMS,
        GROUP_TRAINING,
        CARDIO_BOXING,
        MOUNTAINEERING,
        FENCING,
        SOFTBALL,
        CLIMB_STAIRS,
        AMERICAN_FOOTBALL,
        VOLLEYBALL,
        ROLLING,
        PICKLEBALL,
        HOCKEY,
        BOXING,
        TAEKWONDO,
        KARATE,
        FLEXIBILITY,
        HANDBALL,
        TRAIL_RUN,
        SWIM,
        PARACHUTE,
        WEIGHTLIFTING,
        JUMPING_JACKS,
        STAIR_MACHINE,
        STRETCHING,
        CROSS_TRAINING,
        DEADLIFT,
        FUNCTIONAL_STRENGTH_TRAINING,
        UPPER_BODY_TRAINING,
        LOWER_BODY_TRAINING,
        ABS_TRAINING,
        BACK_TRAINING,
        SAILBOATING,
        SUP,
        WATER_POLO,
        THRASH,
        KAYAKING,
        DRIFTING,
        BOATING,
        DIVING,
        KITESURFING,
        ATV,
        BEACH_SOCCER,
        HIP_HOP,
        MUAY_THAI,
        JUDO,
        FREE_SPARRING,
        SOCCER,
        SQUASH,
        RAGA,
        SKIS,
        PUCK,
        SNOWMOBILE,
        SLEDDING,
        BILLIARDS,
        RACING,
        EQUESTRIAN,
        FRISBEE,
        KITE,
        PUSH_PULL,
        CUSTOM_SPORT,
        WALKING_MACHINE,
        TRAMPOLINE,
        ROLLER_SKATING,
        SKATING,
        CLIMBING_MACHINE,
        WORKOUT,
        FIN_SWIM,
        ARTISTIC_SWIM,
        SNORKEL,
        DANCE,
        STREET_DANCE,
        WUSHU,
        CLIMBING,
        SPINNING,
        OTHER,
        ROWING_MACHINE,
        COOLDOWN,
        TRADITIONAL_STRENGTH_TRAINING,
        VO2MAX,
        HOVERBOARDING,
        BLADING,
        SURFING,
        BUNGEE_JUMPING,
        SHOOTING,
        MARATHON,
        TABLE_TENNIS,
        AIR_WALKER,
        MIXED_CARDIO,
        KICKBOXING,
        BARRE,
        AUSTRALIAN_FOOTBALL,
        DOWNHILL_SKIING,
        ATHLETICS,
        TREKKING,
        POLE_DANCE,
        DISCO,
        TAP_DANCE,
        SCOOTER,
        HAMMER,
        SQUAT,
        LEG_PRESS,
        OFF_ROAD_BIKE,
        MOTOCROSS,
        CROQUET,
        FLOORBALL,
        JAI_ALAI,
        TENNIS_DOUBLES,
        BODY_COMBAT,
        BODY_BALANCING,
        TRX,
        TAE_BO,
        HORSE_RACING,
        DISC_THROW
    }

    /* loaded from: classes2.dex */
    public enum IndoorOutdoor {
        OUTDOOR,
        INDOOR
    }

    /* loaded from: classes2.dex */
    public static final class LogShareRunnable implements Runnable {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final Context f2910a;
        @NotNull
        public final File b;

        public LogShareRunnable(@NotNull Context context, @NotNull File fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            this.f2910a = context;
            this.b = fileName;
        }

        @NotNull
        public final Context getContext() {
            return this.f2910a;
        }

        @NotNull
        public final File getFileName() {
            return this.b;
        }

        @Override // java.lang.Runnable
        public void run() {
            Intent intent = new Intent("android.intent.action.SEND");
            Context context = this.f2910a;
            Uri uriForFile = FileProvider.getUriForFile(context, this.f2910a.getApplicationContext().getPackageName() + ".provider", this.b);
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            intent.addFlags(1);
            intent.setDataAndType(uriForFile, this.f2910a.getContentResolver().getType(uriForFile));
            this.f2910a.startActivity(Intent.createChooser(intent, ""));
        }
    }

    @JvmStatic
    @NotNull
    public static final String byte2Hex(@Nullable byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return "no data";
        }
        StringBuilder sb = new StringBuilder(bArr.length);
        for (byte b2 : bArr) {
            String format = String.format(Locale.ENGLISH, "%02X ", Arrays.copyOf(new Object[]{Byte.valueOf(b2)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
        return sb2;
    }

    @JvmStatic
    public static final void checkExceptionAndShowNotification(@NotNull Exception e2, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(e2, "e");
        Intrinsics.checkNotNullParameter(context, "context");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 31 || !(e2 instanceof ForegroundServiceStartNotAllowedException)) {
            return;
        }
        Object systemService = context.getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        if (((PowerManager) systemService).isIgnoringBatteryOptimizations(context.getPackageName())) {
            return;
        }
        BleApiUtils bleApiUtils = INSTANCE;
        if (getData().getAppIcon() == 0) {
            getMetadata(context);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(getData().getAppLauncherActivity()), 67108864);
        String string = context.getString(R.string.battery_optimization_title);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ttery_optimization_title)");
        String string2 = context.getString(R.string.battery_optimization_content);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…ery_optimization_content)");
        NotificationInfo data = getData();
        Intrinsics.checkNotNullExpressionValue(pendingIntent, "pendingIntent");
        bleApiUtils.getClass();
        NotificationCompat.Builder style = new NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID).setSmallIcon(data.getAppIcon()).setContentTitle(string).setContentText(string2).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(string2));
        int i3 = R.color.white;
        NotificationCompat.Builder contentIntent = style.setColor(ContextCompat.getColor(context, i3)).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(pendingIntent);
        Intrinsics.checkNotNullExpressionValue(contentIntent, "Builder(context, channel…tentIntent(pendingIntent)");
        contentIntent.setSmallIcon(data.getAppIcon());
        contentIntent.setContentTitle(string);
        contentIntent.setColor(ContextCompat.getColor(context, i3));
        contentIntent.setContentText(string2);
        contentIntent.setSound(RingtoneManager.getDefaultUri(2));
        contentIntent.setStyle(new NotificationCompat.BigTextStyle().bigText(string2));
        contentIntent.setAutoCancel(true);
        Object systemService2 = context.getSystemService("notification");
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService2;
        if (i2 >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(Constants.NOTIFICATION_CHANNEL_ID, data.getAppName(), 3));
        }
        notificationManager.notify(2147483613, contentIntent.build());
    }

    @JvmStatic
    public static final int findDateDifference(@Nullable Date date, @Nullable Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        return (int) ((calendar2.getTimeInMillis() - calendar.getTimeInMillis()) / ((long) TimeConstants.DAY));
    }

    @JvmStatic
    public static final boolean getBleEnableActivitydetails_CZ2() {
        return d;
    }

    @JvmStatic
    public static final boolean getBleEnableBpV7() {
        return c;
    }

    @NotNull
    public static final NotificationInfo getData() {
        NotificationInfo notificationInfo = new NotificationInfo();
        notificationInfo.setAppName(f);
        notificationInfo.setAppDesc(e);
        notificationInfo.setAppIcon(f2909a);
        notificationInfo.setAppColor(b);
        notificationInfo.setAppLauncherActivity(g);
        return notificationInfo;
    }

    @JvmStatic
    public static /* synthetic */ void getData$annotations() {
    }

    @NotNull
    public static final ArrayList<Integer> getEmptyDaySleepCodedValuesList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < 1440; i2++) {
            arrayList.add(-1);
        }
        return arrayList;
    }

    @JvmStatic
    public static /* synthetic */ void getEmptyDaySleepCodedValuesList$annotations() {
    }

    @JvmStatic
    public static final void getMetadata(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "context.packageManager.g…T_META_DATA\n            )");
            Bundle bundle = applicationInfo.metaData;
            if (bundle != null && !bundle.isEmpty()) {
                BleApiUtils bleApiUtils = INSTANCE;
                f = bleApiUtils.a(bundle, "com.coveiot.bleabstract.AppName");
                e = bleApiUtils.a(bundle, "com.coveiot.bleabstract.AppDesc");
                g = bleApiUtils.a(bundle, "com.coveiot.bleabstract.AppLauncherActivity");
                if (bundle.containsKey("com.coveiot.bleabstract.AppNotificationColor")) {
                    b = bundle.getInt("com.coveiot.bleabstract.AppNotificationColor");
                } else {
                    b = R.color.color_app_notification;
                }
                int i2 = bundle.getInt("com.coveiot.bleabstract.AppIcon");
                if (i2 != 0) {
                    f2909a = i2;
                    if (bundle.containsKey("com.coveiot.bleabstract.EnableBPV7")) {
                        c = bundle.getBoolean("com.coveiot.bleabstract.EnableBPV7");
                    }
                    if (bundle.containsKey("com.coveiot.bleabstract.EnableActivitydetails_CZ2")) {
                        d = bundle.getBoolean("com.coveiot.bleabstract.EnableActivitydetails_CZ2");
                        return;
                    }
                    return;
                }
                throw new SetupException(bleApiUtils.a("com.coveiot.bleabstract.AppIcon"));
            }
            throw new SetupException(h);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            throw new SetupException("setup_err_generic");
        } catch (NullPointerException e3) {
            e3.printStackTrace();
            throw new SetupException("setup_err_generic");
        }
    }

    @JvmStatic
    public static final boolean isServiceRunning(@NotNull String name, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(name, runningServiceInfo.service.getClassName()) && runningServiceInfo.started) {
                z = true;
            }
        }
        return z;
    }

    @JvmStatic
    public static final void saveLogFile(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = context.getFilesDir().getAbsolutePath().toString();
        File file = new File(str, "Log_" + AppUtils.formatDate(new Date(), "yyyy_MM_dd_HH_mm") + ".txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        Runtime.getRuntime().exec("logcat -d -f" + file.getAbsolutePath());
        new Handler(Looper.getMainLooper()).postDelayed(new LogShareRunnable(context, file), 1000L);
    }

    @JvmStatic
    @NotNull
    public static final DeviceSupportedFeatures updateDeviceSupportedFeatureBasedOnFWAndAppCapability(@NotNull Context context, @NotNull String macAddress, @NotNull DeviceSupportedFeatures features) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(features, "features");
        FirmwareCapabilityData firmwareCapability = PreferenceManagerAbstract.getInstance(context).getFirmwareCapability(macAddress);
        if (firmwareCapability != null && firmwareCapability.getCapabilities() != null) {
            byte[] capabilities = firmwareCapability.getCapabilities();
            Intrinsics.checkNotNullExpressionValue(capabilities, "firmwareCapability.capabilities");
            if (!(capabilities.length == 0)) {
                AppCapabilityData appCapability = PreferenceManagerAbstract.getInstance(context).getAppCapability(macAddress);
                if (firmwareCapability.getCapabilities()[0] == 1 && appCapability.isAADSupported()) {
                    features.setActivityAutoRecognitionSupported(true);
                }
                if (firmwareCapability.getCapabilities()[1] == 1 && appCapability.isBatterySaverConfigSupported()) {
                    features.setBatterySaverConfigSupported(true);
                }
                if (firmwareCapability.getCapabilities()[2] == 1 && appCapability.isSilentModeSupported()) {
                    features.setSilentModeConfigSupported(true);
                }
                if (firmwareCapability.getCapabilities()[3] == 1 && appCapability.isSensAISupported()) {
                    features.setSensAISupported(true);
                }
                if (firmwareCapability.getCapabilities()[4] == 1 && appCapability.isRespiratoryRateSupported()) {
                    features.setRespiratoryRateByPPGSupported(true);
                }
                if (firmwareCapability.getCapabilities()[5] == 1 && appCapability.isScheduledLiftWristToViewSupported()) {
                    features.setScheduledLiftWristViewSettingsSupported(true);
                }
                if (firmwareCapability.getCapabilities()[6] == 1 && appCapability.isExtendedAppNotificationSupported()) {
                    features.setExtendedNotificationsSupported(true);
                }
                if (firmwareCapability.getCapabilities()[7] == 1 && appCapability.isDistanceAndCalorieDataFromBandSupported()) {
                    features.setDistanceAndCalorieDataFromBandSupported(true);
                }
                if (firmwareCapability.getCapabilities()[8] == 1 && appCapability.isFindWatchSupported()) {
                    features.setNewCommandSupportedForFindMyWatch(true);
                }
                if (firmwareCapability.getCapabilities()[9] == 1 && appCapability.isPersonalizedSupported()) {
                    features.setPersonalizedMessageSupported(true);
                }
                if (firmwareCapability.getCapabilities()[10] == 1 && appCapability.isNavigationSupported()) {
                    features.setTurnByTurnNavigationSupported(true);
                }
                if (firmwareCapability.getCapabilities()[11] == 1 && appCapability.isSmartAlertSupported()) {
                    features.setSmartAlertsSupported(true);
                }
                if (firmwareCapability.getCapabilities()[12] == 1 && appCapability.isQRCodeSupported()) {
                    features.setQRCodeSupported(true);
                }
                if (firmwareCapability.getCapabilities()[13] == 1 && appCapability.isSOSSupported()) {
                    features.setSosSupported(true);
                }
            }
        }
        return features;
    }

    public final String a(Bundle bundle, String str) {
        String str2;
        if (bundle.containsKey(str)) {
            String string = bundle.getString(str);
            if (string == null || string.length() <= 0) {
                throw new SetupException(a(str));
            }
            return string;
        }
        int hashCode = str.hashCode();
        if (hashCode == -399305095) {
            if (str.equals("com.coveiot.bleabstract.AppDesc")) {
                str2 = m;
            }
            str2 = h;
        } else if (hashCode == -399158175) {
            if (str.equals("com.coveiot.bleabstract.AppIcon")) {
                str2 = i;
            }
            str2 = h;
        } else {
            if (hashCode == -399011213 && str.equals("com.coveiot.bleabstract.AppName")) {
                str2 = k;
            }
            str2 = h;
        }
        throw new SetupException(str2);
    }

    @Nullable
    public final String addSecondsToDate(int i2, @NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
        Date parse = simpleDateFormat.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        calendar.add(13, i2);
        return simpleDateFormat.format(calendar.getTime());
    }

    @NotNull
    public final String getAPP_NAME() {
        return f;
    }

    public final int getAverageValue(@NotNull List<Integer> bpHrMinData) {
        Intrinsics.checkNotNullParameter(bpHrMinData, "bpHrMinData");
        if (bpHrMinData.size() > 0) {
            int size = bpHrMinData.size();
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                i3 += bpHrMinData.get(i4).intValue();
                if (bpHrMinData.get(i4).intValue() != 0) {
                    i2++;
                }
            }
            if (i2 > 0) {
                return i3 / i2;
            }
            return 0;
        }
        return 0;
    }

    @Nullable
    public final Integer getAvgValueFromList(@Nullable List<Integer> list) {
        ArrayList<Integer> nonZeroList = getNonZeroList(list);
        int i2 = 0;
        if (nonZeroList != null && nonZeroList.size() != 0) {
            int size = nonZeroList.size();
            int i3 = 0;
            while (i2 < size) {
                Integer num = nonZeroList.get(i2);
                Intrinsics.checkNotNullExpressionValue(num, "nonZeroList[i]");
                i3 += num.intValue();
                i2++;
            }
            i2 = i3 / nonZeroList.size();
        }
        return Integer.valueOf(i2);
    }

    @NotNull
    public final Calendar getCalendarFromTimeStamp(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @NotNull
    public final Date getDateFromString(int i2, int i3, int i4) {
        StringBuilder sb = new StringBuilder();
        sb.append(i2);
        sb.append('/');
        sb.append(i3);
        sb.append('/');
        sb.append(i4);
        Date parse = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(sb.toString());
        Intrinsics.checkNotNull(parse);
        return parse;
    }

    @Nullable
    public final String getDateFromTimeStamp(long j2) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date(j2));
    }

    @NotNull
    public final List<Integer> getEachBit(int i2) {
        ArrayList arrayList = new ArrayList();
        if (i2 > 0) {
            while (i2 > 0) {
                arrayList.add(Integer.valueOf(i2 & 1));
                i2 >>= 1;
            }
            while (arrayList.size() < 8) {
                arrayList.add(0);
            }
        } else {
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
        }
        return arrayList;
    }

    @NotNull
    public final ArrayList<Integer> getEmptyHourCodedValuesList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < 60; i2++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    public final int getMaxValue(@NotNull List<Integer> bpHrMinData) {
        Intrinsics.checkNotNullParameter(bpHrMinData, "bpHrMinData");
        int intValue = bpHrMinData.get(0).intValue();
        if (bpHrMinData.size() > 0) {
            int size = bpHrMinData.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (intValue < bpHrMinData.get(i2).intValue()) {
                    intValue = bpHrMinData.get(i2).intValue();
                }
            }
        }
        return intValue;
    }

    @Nullable
    public final Integer getMaxValueFromList(@Nullable List<Integer> list) {
        if (list != null && !list.isEmpty()) {
            return (Integer) Collections.max(list);
        }
        return 0;
    }

    public final int getMinValue(@NotNull List<Integer> bpHrMinData) {
        Intrinsics.checkNotNullParameter(bpHrMinData, "bpHrMinData");
        if (bpHrMinData.size() > 0) {
            int size = bpHrMinData.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                } else if (bpHrMinData.get(i2).intValue() > 0) {
                    bpHrMinData.get(i2).intValue();
                    break;
                } else {
                    i2++;
                }
            }
            int size2 = bpHrMinData.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size2; i4++) {
                if (i3 > bpHrMinData.get(i4).intValue() && bpHrMinData.get(i4).intValue() != 0) {
                    i3 = bpHrMinData.get(i4).intValue();
                }
            }
            return i3;
        }
        return 0;
    }

    @Nullable
    public final Integer getMinValueFromList(@Nullable List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        ArrayList<Integer> nonZeroList = getNonZeroList(list);
        if (nonZeroList.size() <= 0) {
            return 0;
        }
        return (Integer) Collections.min(nonZeroList);
    }

    @NotNull
    public final ArrayList<Integer> getNonZeroList(@Nullable List<Integer> list) {
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

    @NotNull
    public final String getSETUP_ERR_MALFORMED_APP_DESC() {
        return n;
    }

    @NotNull
    public final String getSETUP_ERR_MALFORMED_APP_ICON() {
        return j;
    }

    @NotNull
    public final String getSETUP_ERR_MALFORMED_APP_NAME() {
        return l;
    }

    @NotNull
    public final String getSETUP_ERR_MISSING_APP_DESC() {
        return m;
    }

    @NotNull
    public final String getSETUP_ERR_MISSING_APP_ICON() {
        return i;
    }

    @NotNull
    public final String getSETUP_ERR_MISSING_APP_NAME() {
        return k;
    }

    @NotNull
    public final String getSETUP_ERR_MISSING_DATA() {
        return h;
    }

    @NotNull
    public final String getTodayDate() {
        String format = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(cal.time)");
        return format;
    }

    @NotNull
    public final Calendar getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @Nullable
    public final String removeChars(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return m.replace$default(m.replace$default(m.replace$default(value, "-", "", false, 4, (Object) null), ":", "", false, 4, (Object) null), ExifInterface.GPS_DIRECTION_TRUE, "", false, 4, (Object) null);
    }

    public final void setAPP_NAME(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        f = str;
    }

    public final void setSETUP_ERR_MALFORMED_APP_DESC(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        n = str;
    }

    public final void setSETUP_ERR_MALFORMED_APP_ICON(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        j = str;
    }

    public final void setSETUP_ERR_MALFORMED_APP_NAME(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        l = str;
    }

    public final void setSETUP_ERR_MISSING_APP_DESC(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        m = str;
    }

    public final void setSETUP_ERR_MISSING_APP_ICON(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        i = str;
    }

    public final void setSETUP_ERR_MISSING_APP_NAME(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        k = str;
    }

    public final void setSETUP_ERR_MISSING_DATA(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        h = str;
    }

    public final String a(String str) {
        int hashCode = str.hashCode();
        if (hashCode != -399305095) {
            if (hashCode != -399158175) {
                if (hashCode == -399011213 && str.equals("com.coveiot.bleabstract.AppName")) {
                    return l;
                }
            } else if (str.equals("com.coveiot.bleabstract.AppIcon")) {
                return j;
            }
        } else if (str.equals("com.coveiot.bleabstract.AppDesc")) {
            return n;
        }
        return h;
    }
}
