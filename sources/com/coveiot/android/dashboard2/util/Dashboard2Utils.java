package com.coveiot.android.dashboard2.util;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.BatteryManager;
import android.os.PersistableBundle;
import android.text.format.DateUtils;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.preferences.PreferenceManager1860;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.service.ProbeService;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreApiCall;
import com.coveiot.android.sleepenergyscore.sleepscore.SleepScoreApiCall;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.coveaccess.model.server.SwimmingStyleEnum;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ScanOnDisconnectCriteria;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class Dashboard2Utils {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f4252a = "Dashboard2Utils";
    public static final int b = 2;
    public static final long c = 3600000;

    /* loaded from: classes4.dex */
    public static final class Companion {

        /* loaded from: classes4.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[DeviceType.values().length];
                try {
                    iArr[DeviceType.crpGPF5.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @DebugMetadata(c = "com.coveiot.android.dashboard2.util.Dashboard2Utils$Companion$resetBluetoothServicesAndConfigurations$1", f = "Dashboard2Utils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Context $context;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Context context, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    Companion companion = Dashboard2Utils.Companion;
                    LogHelper.d(companion.getTAG(), "resetBluetoothServicesAndConfigurations");
                    if (AppUtils.isBluetoothEnabled(this.$context)) {
                        BleApiManager.getInstance(this.$context).getBleApi().restartService();
                        companion.scheduleJob(this.$context);
                        SyncManager.getInstance().resetSyncProgress();
                    } else {
                        LogHelper.d(companion.getTAG(), "resetBluetoothServicesAndConfigurations - Bluetooth disabled");
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.dashboard2.util.Dashboard2Utils$Companion$sleepAndEnergyMeterSync$1", f = "Dashboard2Utils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Context $context;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(Context context, Continuation<? super b> continuation) {
                super(2, continuation);
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (UserDataManager.getInstance(this.$context).isEnableSleepEnergyScoreFeature(this.$context)) {
                        Thread.sleep(5000L);
                        if (AppUtils.isNetConnected(this.$context)) {
                            Calendar calendar = Calendar.getInstance();
                            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                            calendar.add(6, -4);
                            String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
                            Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
                            SleepScoreData sleepScoreData = SleepScoreRepository.Companion.getInstance(this.$context).getSleepScoreData(formatDate, BleApiManager.getInstance(this.$context).getBleApi().getMacAddress());
                            if ((sleepScoreData != null ? sleepScoreData.getSleepScore() : null) == null) {
                                SleepScoreApiCall sleepScoreApiCall = SleepScoreApiCall.INSTANCE;
                                Calendar calendar2 = Calendar.getInstance();
                                Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
                                Context context = this.$context;
                                String string = context.getResources().getString(R.string.home);
                                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…                        )");
                                sleepScoreApiCall.getSleepScoreBatchApiCall(calendar, calendar2, context, string);
                            } else {
                                EnergyScoreApiCall energyScoreApiCall = EnergyScoreApiCall.INSTANCE;
                                Calendar calendar3 = Calendar.getInstance();
                                Intrinsics.checkNotNullExpressionValue(calendar3, "getInstance()");
                                Context context2 = this.$context;
                                String string2 = context2.getResources().getString(com.coveiot.android.sleepenergyscore.R.string.home);
                                Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…nergyscore.R.string.home)");
                                energyScoreApiCall.energyScoreBatchApiCall(calendar, calendar3, context2, string2);
                            }
                        }
                    } else {
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (companion.isIDODevice(this.$context) || companion.isTouchELXDevice(this.$context)) {
                            SleepScoreData sleepScoreData2 = new SleepScoreData();
                            DailySleepData dailySleepDatafortheDate = SleepDBRead.getInstance(this.$context).getDailySleepDatafortheDate(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"), BleApiManager.getInstance(this.$context).getBleApi().getMacAddress());
                            if (dailySleepDatafortheDate != null) {
                                sleepScoreData2.setSleepScore(Boxing.boxInt(dailySleepDatafortheDate.getSleepScore()));
                                sleepScoreData2.setMacAddress(BleApiManager.getInstance(this.$context).getBleApi().getMacAddress());
                                String date = dailySleepDatafortheDate.getDate();
                                Intrinsics.checkNotNullExpressionValue(date, "dailySleepData.date");
                                sleepScoreData2.setDate(date);
                                SleepScoreRepository.Companion.getInstance(this.$context).insert(sleepScoreData2);
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final void d(Exception it) {
            Intrinsics.checkNotNullParameter(it, "it");
        }

        public static final void e(final FirebaseRemoteConfig remoteConfig, final Context context, Task task) {
            Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(task, "task");
            if (task.isSuccessful()) {
                Void r3 = (Void) task.getResult();
                remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.dashboard2.util.a
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task2) {
                        Dashboard2Utils.Companion.f(FirebaseRemoteConfig.this, context, task2);
                    }
                });
            }
        }

        public static final void f(FirebaseRemoteConfig remoteConfig, Context context, Task it) {
            Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(it, "it");
            String string = remoteConfig.getString(ThemeConstants.REMOTE_CONFIG_SCAN_ON_DISCONNECT_CRITERIA.getValue());
            Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…ISCONNECT_CRITERIA.value)");
            ScanOnDisconnectCriteria scanOnDisconnectCriteria = (ScanOnDisconnectCriteria) new Gson().fromJson(string, (Class<Object>) ScanOnDisconnectCriteria.class);
            if (scanOnDisconnectCriteria != null) {
                if (scanOnDisconnectCriteria.getShouldDoBackgroundScan() != null) {
                    PreferenceManagerAbstract preferenceManagerAbstract = PreferenceManagerAbstract.getInstance(context);
                    Boolean shouldDoBackgroundScan = scanOnDisconnectCriteria.getShouldDoBackgroundScan();
                    Intrinsics.checkNotNullExpressionValue(shouldDoBackgroundScan, "scanOnDisconnectCriteria.shouldDoBackgroundScan");
                    preferenceManagerAbstract.saveShouldDoScanConnect(shouldDoBackgroundScan.booleanValue());
                }
                SessionManager.getInstance(context).setScanOnDisconnectCriteria(scanOnDisconnectCriteria);
            }
        }

        public final boolean boolUpdateAgpsFile(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (PreferenceManager1860.getInstance(context).getAgpsFileLastUpdatedDate() != null) {
                String agpsFileLastUpdatedDate = PreferenceManager1860.getInstance(context).getAgpsFileLastUpdatedDate();
                Intrinsics.checkNotNullExpressionValue(agpsFileLastUpdatedDate, "getInstance(context).agpsFileLastUpdatedDate");
                int findDateDifference = AppUtils.findDateDifference(AppUtils.parseDate(agpsFileLastUpdatedDate, "yyyy-MM-dd"), AppUtils.parseDate(getDate(), "yyyy-MM-dd"));
                r1 = findDateDifference >= 1;
                LogHelper.d(getTAG(), "boolFileUpdate:", String.valueOf(findDateDifference));
            }
            PreferenceManager1860.getInstance(context).saveAgpsFileUpdated(PreferenceManager1860.getInstance(context).getConnectedDeviceMacAddress(), !r1);
            return r1;
        }

        public final double calculateCaloriesForCZ(int i, int i2, int i3, int i4) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.ENGLISH, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(((float) ((i * i3) / (160934.0d / (i2 * 0.413d)))) * 0.9f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            return Double.parseDouble(format);
        }

        public final boolean checkIfBluetoothPermissionExists(@Nullable Context context) {
            String[] unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(context, new String[]{"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"});
            Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
            return unGrantedPermissions.length == 0;
        }

        public final long convertIntervalToMilliSeconds(@NotNull String interval) {
            Intrinsics.checkNotNullParameter(interval, "interval");
            return AppUtils.parseDate(interval, "HH:mm:ss").getTime() - AppUtils.parseDate("00:00:00", "HH:mm:ss").getTime();
        }

        public final double convertKMToMiles(double d) {
            return d * 0.62137d;
        }

        @NotNull
        public final String getCaloriesValue(double d) {
            return String.valueOf((int) d);
        }

        @JvmStatic
        @NotNull
        public final String getDate() {
            String format = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(cal.time)");
            return format;
        }

        @NotNull
        public final String getDistanceValue(@NotNull Context context, int i) {
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
                String format = decimalFormat.format(convertKMToMiles(i / 1000.0d));
                Intrinsics.checkNotNullExpressionValue(format, "{\n                    de…le()))\n\n                }");
                return format;
            }
            String format2 = decimalFormat.format(i / 1000.0d);
            Intrinsics.checkNotNullExpressionValue(format2, "{\n                    de…uble())\n                }");
            return format2;
        }

        @NotNull
        public final String getLastUpdatedTimeToShow(@NotNull Context context, long j) {
            Intrinsics.checkNotNullParameter(context, "context");
            long currentTimeMillis = (System.currentTimeMillis() - j) / 1000;
            long j2 = 60;
            long j3 = currentTimeMillis / j2;
            long j4 = j3 / j2;
            long j5 = 24;
            int i = (int) (j4 / j5);
            int i2 = (int) (j4 % j5);
            int i3 = (int) (j3 % j2);
            if (i != 0) {
                if (i != 1) {
                    String string = context.getString(R.string.updated_x_days_ago, Integer.valueOf(i));
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ated_x_days_ago, dayDiff)");
                    return string;
                }
                String string2 = context.getString(R.string.updated_1_day_ago);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.updated_1_day_ago)");
                return string2;
            } else if (i2 == 0) {
                if (i3 == 0) {
                    String string3 = context.getString(R.string.updated_just_now);
                    Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.updated_just_now)");
                    return string3;
                } else if (i3 != 1) {
                    String string4 = context.getString(R.string.updated_x_mins_ago, Integer.valueOf(i3));
                    Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.stri…ated_x_mins_ago, minDiff)");
                    return string4;
                } else {
                    String string5 = context.getString(R.string.updated_x_min_ago, Integer.valueOf(i3));
                    Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.stri…dated_x_min_ago, minDiff)");
                    return string5;
                }
            } else if (i2 != 1) {
                if (i3 == 0) {
                    String string6 = context.getString(R.string.updated_x_hrs_ago, Integer.valueOf(i2));
                    Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.stri…ated_x_hrs_ago, hourDiff)");
                    return string6;
                } else if (i3 != 1) {
                    String string7 = context.getString(R.string.updated_x_hrs_x_mins_ago, Integer.valueOf(i2), Integer.valueOf(i3));
                    Intrinsics.checkNotNullExpressionValue(string7, "context.getString(\n     …                        )");
                    return string7;
                } else {
                    String string8 = context.getString(R.string.updated_x_hrs_x_min_ago, Integer.valueOf(i2), Integer.valueOf(i3));
                    Intrinsics.checkNotNullExpressionValue(string8, "context.getString(\n     …                        )");
                    return string8;
                }
            } else if (i3 == 0) {
                String string9 = context.getString(R.string.updated_x_hr_ago, Integer.valueOf(i2));
                Intrinsics.checkNotNullExpressionValue(string9, "context.getString(R.stri…dated_x_hr_ago, hourDiff)");
                return string9;
            } else if (i3 != 1) {
                String string10 = context.getString(R.string.updated_x_hr_x_mins_ago, Integer.valueOf(i2), Integer.valueOf(i3));
                Intrinsics.checkNotNullExpressionValue(string10, "context.getString(\n     …                        )");
                return string10;
            } else {
                String string11 = context.getString(R.string.updated_x_hr_x_min_ago, Integer.valueOf(i2), Integer.valueOf(i3));
                Intrinsics.checkNotNullExpressionValue(string11, "context.getString(\n     …                        )");
                return string11;
            }
        }

        @NotNull
        public final String getPhoneBatteryLevel(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("batterymanager");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.BatteryManager");
            return String.valueOf(((BatteryManager) systemService).getIntProperty(4));
        }

        public final int getSampleRateForSessionInSecs(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return DeviceUtils.Companion.isJstyleDevice(context) ? 60 : 5;
        }

        public final void getSubscribeStatus(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new Dashboard2Utils$Companion$getSubscribeStatus$1(context, null), 2, null);
        }

        @Nullable
        public final String getSwimStyleByValue(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
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

        @NotNull
        public final String getTAG() {
            return Dashboard2Utils.f4252a;
        }

        @JvmStatic
        @NotNull
        public final String getTodayYesterdayStringFromTimeStamp(long j, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
            if (j != 0) {
                if (DateUtils.isToday(j)) {
                    return String.valueOf(context.getResources().getString(R.string.today));
                }
                if (isYesterday(j)) {
                    return String.valueOf(context.getResources().getString(R.string.yesterday));
                }
                return String.valueOf(simpleDateFormat.format(new Date(j)));
            }
            return "";
        }

        public final void getUserProfile(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new Dashboard2Utils$Companion$getUserProfile$1(context, null), 2, null);
        }

        public final boolean isFutureDate(@NotNull Date date) {
            Intrinsics.checkNotNullParameter(date, "date");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            try {
                return simpleDateFormat.parse(simpleDateFormat.format(new Date())).before(simpleDateFormat.parse(simpleDateFormat.format(date)));
            } catch (ParseException e) {
                e.printStackTrace();
                return true;
            }
        }

        public final boolean isSameDate(@NotNull Calendar calender1, @NotNull Calendar calender2) {
            Intrinsics.checkNotNullParameter(calender1, "calender1");
            Intrinsics.checkNotNullParameter(calender2, "calender2");
            return calender1.get(6) == calender2.get(6) && calender1.get(1) == calender2.get(1);
        }

        public final boolean isTBTNavigationSupported(@NotNull Context context) {
            DeviceSupportedFeatures deviceSupportedFeatures;
            Intrinsics.checkNotNullParameter(context, "context");
            BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
            Boolean valueOf = (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures.isTurnByTurnNavigationSupported());
            Intrinsics.checkNotNull(valueOf);
            return valueOf.booleanValue();
        }

        public final boolean isWeatherFeatureSupported(@Nullable Context context) {
            DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
            return (deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()]) == 1;
        }

        public final boolean isYesterday(long j) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(j));
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(6, -1);
            return calendar.get(6) == calendar2.get(6);
        }

        public final void loadScanDeviceOnDisconnectConfiguration(@NotNull final Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
                remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.dashboard2.util.c
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        Dashboard2Utils.Companion.d(exc);
                    }
                }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.dashboard2.util.b
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        Dashboard2Utils.Companion.e(FirebaseRemoteConfig.this, context, task);
                    }
                });
            } catch (Exception unused) {
            } catch (Throwable th) {
                PreferenceManagerAbstract.getInstance(context).saveShouldDoScanConnect(true);
                throw th;
            }
            PreferenceManagerAbstract.getInstance(context).saveShouldDoScanConnect(true);
        }

        public final void resetBluetoothServicesAndConfigurations(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(context, null), 2, null);
        }

        public final void scheduleJob(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            LogHelper.d(getTAG(), "PeriodicSyncJobService initiated");
            ComponentName componentName = new ComponentName(context, ProbeService.class);
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putBoolean(Dashboard2Constants.IS_FROM_SCHEDULER, true);
            JobInfo.Builder builder = new JobInfo.Builder(Dashboard2Utils.b, componentName);
            builder.setExtras(persistableBundle);
            builder.setMinimumLatency(Dashboard2Utils.c);
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
            try {
                jobScheduler.cancel(Dashboard2Utils.b);
            } catch (Exception unused) {
            }
            jobScheduler.schedule(builder.build());
        }

        public final void sleepAndEnergyMeterSync(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new b(context, null), 2, null);
        }
    }

    @JvmStatic
    @NotNull
    public static final String getDate() {
        return Companion.getDate();
    }

    @JvmStatic
    @NotNull
    public static final String getTodayYesterdayStringFromTimeStamp(long j, @NotNull Context context) {
        return Companion.getTodayYesterdayStringFromTimeStamp(j, context);
    }
}
