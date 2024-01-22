package com.coveiot.android.leonardo.googlefit;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.util.SleepDataHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.googlefit.model.DailyHeartRateData;
import com.coveiot.android.leonardo.googlefit.model.DailySPO2Data;
import com.coveiot.android.leonardo.googlefit.model.DailySleepData;
import com.coveiot.android.leonardo.googlefit.model.DailyTemperatureData;
import com.coveiot.android.leonardo.googlefit.model.DailyWalkData;
import com.coveiot.android.leonardo.googlefit.model.TodaysData;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.repository.heartrate.HeartRateRepository;
import com.coveiot.repository.periodicspo2.PeriodicSpo2Repository;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.repository.temperature.TemperatureRepository;
import com.coveiot.repository.walk.WalkRepository;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.HealthDataTypes;
import com.google.android.gms.fitness.data.HealthFields;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GoogleFitDataManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static String l = GoogleFitDataManager.class.getSimpleName();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4831a;
    public byte b;
    public byte c;
    public byte d;
    public Observer<List<EntityHourlySpo2>> dailySPO2DataObserver;
    public Observer<List<HourlyTemperature>> dailyTemperatureDataObserver;
    public byte e;
    public byte f;
    @Nullable
    public Calendar g;
    @Nullable
    public Calendar h;
    public Observer<List<EntityHourlyHeartRateData>> hourlyHeartRateObserver;
    public Observer<List<HourlyWalkData>> hourlyWalkDataObserver;
    @Nullable
    public Calendar i;
    @Nullable
    public Calendar j;
    @Nullable
    public Calendar k;
    public Observer<List<SleepDataModelForLastNight>> sleepDataObserver;
    public TodaysData todaysData;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(DataSet dataSet) {
            String tAG$app_prodRelease = getTAG$app_prodRelease();
            LogHelper.i(tAG$app_prodRelease, "Data returned for Data type: " + dataSet.getDataType().getName());
            for (DataPoint dataPoint : dataSet.getDataPoints()) {
                LogHelper.i(getTAG$app_prodRelease(), "Read Data point:");
                AppUtils.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").setTimeZone(TimeZone.getTimeZone("UTC"));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                String tAG$app_prodRelease2 = getTAG$app_prodRelease();
                LogHelper.i(tAG$app_prodRelease2, "\tType: " + dataPoint.getDataType().getName());
                String tAG$app_prodRelease3 = getTAG$app_prodRelease();
                StringBuilder sb = new StringBuilder();
                sb.append("\tStart: ");
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                sb.append(simpleDateFormat.format(new Date(dataPoint.getStartTime(timeUnit))));
                LogHelper.i(tAG$app_prodRelease3, sb.toString());
                String tAG$app_prodRelease4 = getTAG$app_prodRelease();
                LogHelper.i(tAG$app_prodRelease4, "\tEnd: " + simpleDateFormat.format(new Date(dataPoint.getEndTime(timeUnit))));
                for (Field field : dataPoint.getDataType().getFields()) {
                    String tAG$app_prodRelease5 = getTAG$app_prodRelease();
                    LogHelper.i(tAG$app_prodRelease5, "\tField: " + field.getName() + " Value: " + dataPoint.getValue(field));
                }
            }
        }

        public final String getTAG$app_prodRelease() {
            return GoogleFitDataManager.l;
        }

        public final void printData(@NotNull DataReadResponse dataReadResult) {
            Intrinsics.checkNotNullParameter(dataReadResult, "dataReadResult");
            if (dataReadResult.getBuckets().size() > 0) {
                String tAG$app_prodRelease = getTAG$app_prodRelease();
                LogHelper.i(tAG$app_prodRelease, "Number of returned buckets of DataSets is: " + dataReadResult.getBuckets().size());
                for (Bucket bucket : dataReadResult.getBuckets()) {
                    List<DataSet> dataSets = bucket.getDataSets();
                    Intrinsics.checkNotNullExpressionValue(dataSets, "bucket.dataSets");
                    for (DataSet dataSet : dataSets) {
                        Intrinsics.checkNotNullExpressionValue(dataSet, "dataSet");
                        a(dataSet);
                    }
                }
            } else if (dataReadResult.getDataSets().size() > 0) {
                String tAG$app_prodRelease2 = getTAG$app_prodRelease();
                LogHelper.i(tAG$app_prodRelease2, "Number of returned DataSets is: " + dataReadResult.getDataSets().size());
                for (DataSet dataSet2 : dataReadResult.getDataSets()) {
                    Intrinsics.checkNotNullExpressionValue(dataSet2, "dataSet");
                    a(dataSet2);
                }
            }
        }

        public final void setTAG$app_prodRelease(String str) {
            GoogleFitDataManager.l = str;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.googlefit.GoogleFitDataManager$updateToGoogleFit$1", f = "GoogleFitDataManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                try {
                    Boolean isGoogleFitHrSPO2TempSleepSupportAvailable = UserDataManager.getInstance(GoogleFitDataManager.this.getContext()).isGoogleFitHrSPO2TempSleepSupportAvailable();
                    Intrinsics.checkNotNullExpressionValue(isGoogleFitHrSPO2TempSleepSupportAvailable, "getInstance(context).isG…TempSleepSupportAvailable");
                    if (isGoogleFitHrSPO2TempSleepSupportAvailable.booleanValue()) {
                        GoogleFitDataManager.this.y();
                    }
                    GoogleFitDataManager.this.E();
                    GoogleFitDataManager.this.u();
                    GoogleFitDataManager.this.w();
                    Boolean isGoogleFitHrSPO2TempSleepSupportAvailable2 = UserDataManager.getInstance(GoogleFitDataManager.this.getContext()).isGoogleFitHrSPO2TempSleepSupportAvailable();
                    Intrinsics.checkNotNullExpressionValue(isGoogleFitHrSPO2TempSleepSupportAvailable2, "getInstance(context).isG…TempSleepSupportAvailable");
                    if (isGoogleFitHrSPO2TempSleepSupportAvailable2.booleanValue()) {
                        GoogleFitDataManager.this.C();
                        GoogleFitDataManager.this.A();
                        GoogleFitDataManager.this.G();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public GoogleFitDataManager(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4831a = context;
        this.c = (byte) 1;
        this.d = (byte) 2;
        this.e = (byte) 3;
        this.f = (byte) -1;
        setTodaysData(new TodaysData(null, null, null, null, null, 16, null));
    }

    public static final void B(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            LogHelper.i(l, "SPO2 data insert was successful!");
        } else {
            LogHelper.e(l, "There was a problem inserting the SPO2 dataset.", task.getException());
        }
    }

    public static final void D(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            LogHelper.i(l, "Sleep data insert was successful!");
        } else {
            LogHelper.e(l, "There was a problem inserting the sleep dataset.", task.getException());
        }
    }

    public static final void F(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            LogHelper.i(l, "Step data insert was successful!");
        } else {
            LogHelper.e(l, "There was a problem inserting the step dataset.", task.getException());
        }
    }

    public static final void H(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            LogHelper.i(l, "Temperature data insert was successful!");
        } else {
            LogHelper.e(l, "There was a problem inserting the Temperature dataset.", task.getException());
        }
    }

    public static final void s(GoogleFitDataManager this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = l;
        StringBuilder sb = new StringBuilder();
        sb.append("SPO2 onChanged ");
        Calendar calendar = this$0.i;
        Intrinsics.checkNotNull(calendar);
        sb.append(calendar.getTime());
        LogHelper.d(str, sb.toString());
        GoogleFitPreferenceManager googleFitPreferenceManager = GoogleFitPreferenceManager.getInstance(this$0.f4831a);
        Calendar calendar2 = this$0.i;
        Intrinsics.checkNotNull(calendar2);
        googleFitPreferenceManager.saveLastSPO2SyncTimeToFit(Long.valueOf(calendar2.getTimeInMillis()));
        PeriodicSpo2Repository.Companion companion = PeriodicSpo2Repository.Companion;
        Calendar calendar3 = this$0.i;
        Intrinsics.checkNotNull(calendar3);
        LiveData<List<EntityHourlySpo2>> hourlyData = companion.getInstance(this$0.f4831a).getHourlyData(calendar3, BleApiManager.getInstance(this$0.f4831a).getBleApi().getMacAddress());
        Observer<List<EntityHourlySpo2>> dailySPO2DataObserver = this$0.getDailySPO2DataObserver();
        Intrinsics.checkNotNull(dailySPO2DataObserver);
        hourlyData.removeObserver(dailySPO2DataObserver);
        if (list != null && (!list.isEmpty())) {
            if (this$0.getTodaysData() != null) {
                List<DailySPO2Data> dailySPO2DataList = this$0.getTodaysData().getDailySPO2DataList();
                Intrinsics.checkNotNull(dailySPO2DataList);
                List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) list);
                Calendar calendar4 = this$0.i;
                Intrinsics.checkNotNull(calendar4);
                Object clone = calendar4.clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                dailySPO2DataList.add(new DailySPO2Data(mutableList, (Calendar) clone));
                Calendar calendar5 = this$0.i;
                Intrinsics.checkNotNull(calendar5);
                if (DateUtils.isToday(calendar5.getTimeInMillis())) {
                    this$0.M();
                    Calendar calendar6 = this$0.j;
                    Intrinsics.checkNotNull(calendar6);
                    TemperatureRepository.Companion.getInstance(this$0.f4831a).getHourlyData(calendar6, BleApiManager.getInstance(this$0.f4831a).getBleApi().getMacAddress()).observeForever(this$0.getDailyTemperatureDataObserver());
                    return;
                }
                Calendar calendar7 = this$0.i;
                Intrinsics.checkNotNull(calendar7);
                calendar7.add(6, 1);
                Calendar calendar8 = this$0.i;
                Intrinsics.checkNotNull(calendar8);
                companion.getInstance(this$0.f4831a).getHourlyData(calendar8, BleApiManager.getInstance(this$0.f4831a).getBleApi().getMacAddress()).observeForever(this$0.getDailySPO2DataObserver());
                return;
            }
            return;
        }
        Calendar calendar9 = this$0.i;
        Intrinsics.checkNotNull(calendar9);
        if (DateUtils.isToday(calendar9.getTimeInMillis())) {
            this$0.M();
            Calendar calendar10 = this$0.j;
            Intrinsics.checkNotNull(calendar10);
            TemperatureRepository.Companion.getInstance(this$0.f4831a).getHourlyData(calendar10, BleApiManager.getInstance(this$0.f4831a).getBleApi().getMacAddress()).observeForever(this$0.getDailyTemperatureDataObserver());
            return;
        }
        Calendar calendar11 = this$0.i;
        Intrinsics.checkNotNull(calendar11);
        calendar11.add(6, 1);
        Calendar calendar12 = this$0.i;
        Intrinsics.checkNotNull(calendar12);
        companion.getInstance(this$0.f4831a).getHourlyData(calendar12, BleApiManager.getInstance(this$0.f4831a).getBleApi().getMacAddress()).observeForever(this$0.getDailySPO2DataObserver());
    }

    public static final void t(GoogleFitDataManager this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = l;
        StringBuilder sb = new StringBuilder();
        sb.append("Temperature onChanged ");
        Calendar calendar = this$0.j;
        Intrinsics.checkNotNull(calendar);
        sb.append(calendar.getTime());
        LogHelper.d(str, sb.toString());
        GoogleFitPreferenceManager googleFitPreferenceManager = GoogleFitPreferenceManager.getInstance(this$0.f4831a);
        Calendar calendar2 = this$0.j;
        Intrinsics.checkNotNull(calendar2);
        googleFitPreferenceManager.saveLastTemperatureSyncTimeToFit(Long.valueOf(calendar2.getTimeInMillis()));
        TemperatureRepository.Companion companion = TemperatureRepository.Companion;
        Calendar calendar3 = this$0.j;
        Intrinsics.checkNotNull(calendar3);
        LiveData<List<HourlyTemperature>> hourlyData = companion.getInstance(this$0.f4831a).getHourlyData(calendar3, BleApiManager.getInstance(this$0.f4831a).getBleApi().getMacAddress());
        Observer<List<HourlyTemperature>> dailyTemperatureDataObserver = this$0.getDailyTemperatureDataObserver();
        Intrinsics.checkNotNull(dailyTemperatureDataObserver);
        hourlyData.removeObserver(dailyTemperatureDataObserver);
        if (list != null && (!list.isEmpty())) {
            if (this$0.getTodaysData() != null) {
                List<DailyTemperatureData> dailyTemperatureDataList = this$0.getTodaysData().getDailyTemperatureDataList();
                Intrinsics.checkNotNull(dailyTemperatureDataList);
                List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) list);
                Calendar calendar4 = this$0.j;
                Intrinsics.checkNotNull(calendar4);
                Object clone = calendar4.clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                dailyTemperatureDataList.add(new DailyTemperatureData(mutableList, (Calendar) clone));
                Calendar calendar5 = this$0.j;
                Intrinsics.checkNotNull(calendar5);
                if (DateUtils.isToday(calendar5.getTimeInMillis())) {
                    this$0.N();
                    return;
                }
                Calendar calendar6 = this$0.j;
                Intrinsics.checkNotNull(calendar6);
                calendar6.add(6, 1);
                Calendar calendar7 = this$0.j;
                Intrinsics.checkNotNull(calendar7);
                companion.getInstance(this$0.f4831a).getHourlyData(calendar7, BleApiManager.getInstance(this$0.f4831a).getBleApi().getMacAddress()).observeForever(this$0.getDailyTemperatureDataObserver());
                return;
            }
            return;
        }
        Calendar calendar8 = this$0.j;
        Intrinsics.checkNotNull(calendar8);
        if (DateUtils.isToday(calendar8.getTimeInMillis())) {
            this$0.N();
            return;
        }
        Calendar calendar9 = this$0.j;
        Intrinsics.checkNotNull(calendar9);
        calendar9.add(6, 1);
        Calendar calendar10 = this$0.j;
        Intrinsics.checkNotNull(calendar10);
        companion.getInstance(this$0.f4831a).getHourlyData(calendar10, BleApiManager.getInstance(this$0.f4831a).getBleApi().getMacAddress()).observeForever(this$0.getDailyTemperatureDataObserver());
    }

    public static final void v(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            LogHelper.i(l, "Calories Data insert was successful!");
        } else {
            LogHelper.e(l, "There was a problem inserting the calories dataset.", task.getException());
        }
    }

    public static final void x(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            LogHelper.i(l, "Distance Data insert was successful!");
        } else {
            LogHelper.e(l, "There was a problem inserting the Distance dataset.", task.getException());
        }
    }

    public static final void z(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            LogHelper.i(l, "Heart Rate data insert was successful!");
        } else {
            LogHelper.e(l, "There was a problem inserting the dataset.", task.getException());
        }
    }

    public final Task<Void> A() {
        DataSource.Builder dataType = new DataSource.Builder().setAppPackageName(this.f4831a).setDataType(HealthDataTypes.TYPE_OXYGEN_SATURATION);
        DataSource build = dataType.setStreamName(l + AppConstants.SPO2_FIT.getValue()).setType(1).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…VED)\n            .build()");
        DataSet create = DataSet.create(build);
        Intrinsics.checkNotNullExpressionValue(create, "create(dataSource)");
        DataSet o = o(create);
        if (o.isEmpty()) {
            return null;
        }
        LogHelper.i(l, "Inserting the dataset SPO2 in the History API.");
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.f4831a);
        if (lastSignedInAccount != null) {
            return Fitness.getHistoryClient(this.f4831a, lastSignedInAccount).insertData(o).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.googlefit.m
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    GoogleFitDataManager.B(task);
                }
            });
        }
        return null;
    }

    public final Task<Void> C() {
        DataSource.Builder dataType = new DataSource.Builder().setAppPackageName(this.f4831a).setDataType(DataType.TYPE_SLEEP_SEGMENT);
        DataSource build = dataType.setStreamName(l + AppConstants.SLEEP_FIT.getValue()).setType(0).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…RAW)\n            .build()");
        List<DataSet> n = DeviceUtils.Companion.isJstyleDevice(this.f4831a) ? n(build) : p(build);
        if (n == null || n.isEmpty()) {
            return null;
        }
        for (DataSet dataSet : n) {
            if (!dataSet.isEmpty()) {
                Session.Builder builder = new Session.Builder();
                StringBuilder sb = new StringBuilder();
                sb.append("Sleep_");
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                sb.append(dataSet.getDataPoints().get(0).getStartTime(timeUnit));
                Session build2 = builder.setName(sb.toString()).setIdentifier(String.valueOf(dataSet.getDataPoints().get(0).getStartTime(timeUnit))).setDescription("Last Night Sleep Data").setStartTime(dataSet.getDataPoints().get(0).getStartTime(timeUnit), timeUnit).setEndTime(dataSet.getDataPoints().get(dataSet.getDataPoints().size() - 1).getEndTime(timeUnit), timeUnit).setActivity(FitnessActivities.SLEEP).build();
                Intrinsics.checkNotNullExpressionValue(build2, "Builder()\n              …\n                .build()");
                SessionInsertRequest build3 = new SessionInsertRequest.Builder().setSession(build2).addDataSet(dataSet).build();
                Intrinsics.checkNotNullExpressionValue(build3, "Builder()\n              …\n                .build()");
                LogHelper.i(l, "Inserting the dataset sleep in the History API.");
                GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.f4831a);
                if (lastSignedInAccount != null) {
                    Fitness.getSessionsClient(this.f4831a, lastSignedInAccount).insertSession(build3).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.googlefit.k
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public final void onComplete(Task task) {
                            GoogleFitDataManager.D(task);
                        }
                    });
                }
            }
        }
        return null;
    }

    public final Task<Void> E() {
        DataSource.Builder dataType = new DataSource.Builder().setAppPackageName(this.f4831a).setDataType(DataType.TYPE_STEP_COUNT_DELTA);
        DataSource build = dataType.setStreamName(l + AppConstants.STEPSCOUNT_FIT.getValue()).setType(0).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…RAW)\n            .build()");
        DataSet create = DataSet.create(build);
        Intrinsics.checkNotNullExpressionValue(create, "create(dataSource)");
        DataSet q = q(create);
        if (q.isEmpty()) {
            return null;
        }
        LogHelper.i(l, "Inserting the dataset in the steps History API.");
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.f4831a);
        if (lastSignedInAccount != null) {
            return Fitness.getHistoryClient(this.f4831a, lastSignedInAccount).insertData(q).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.googlefit.i
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    GoogleFitDataManager.F(task);
                }
            });
        }
        return null;
    }

    public final Task<Void> G() {
        DataSource.Builder dataType = new DataSource.Builder().setAppPackageName(this.f4831a).setDataType(HealthDataTypes.TYPE_BODY_TEMPERATURE);
        DataSource build = dataType.setStreamName(l + AppConstants.BODY_TEMPERATURE_FIT.getValue()).setType(1).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…VED)\n            .build()");
        DataSet create = DataSet.create(build);
        Intrinsics.checkNotNullExpressionValue(create, "create(dataSource)");
        DataSet j = j(create);
        if (j.isEmpty()) {
            return null;
        }
        LogHelper.i(l, "Inserting the dataset temperature in the History API.");
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.f4831a);
        if (lastSignedInAccount != null) {
            return Fitness.getHistoryClient(this.f4831a, lastSignedInAccount).insertData(j).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.googlefit.j
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    GoogleFitDataManager.H(task);
                }
            });
        }
        return null;
    }

    public final void I() {
        Long lastHeartRateSyncTimeToFit = GoogleFitPreferenceManager.getInstance(this.f4831a).getLastHeartRateSyncTimeToFit();
        this.g = Calendar.getInstance();
        if (lastHeartRateSyncTimeToFit != null && lastHeartRateSyncTimeToFit.longValue() != -1) {
            Calendar calendar = this.g;
            Intrinsics.checkNotNull(calendar);
            calendar.setTimeInMillis(lastHeartRateSyncTimeToFit.longValue());
            return;
        }
        Calendar calendar2 = this.g;
        Intrinsics.checkNotNull(calendar2);
        calendar2.add(6, -1);
    }

    public final void J() {
        Long lastSPO2SyncTimeToFit = GoogleFitPreferenceManager.getInstance(this.f4831a).getLastSPO2SyncTimeToFit();
        this.i = Calendar.getInstance();
        if (lastSPO2SyncTimeToFit != null && lastSPO2SyncTimeToFit.longValue() != -1) {
            Calendar calendar = this.i;
            Intrinsics.checkNotNull(calendar);
            calendar.setTimeInMillis(lastSPO2SyncTimeToFit.longValue());
            return;
        }
        Calendar calendar2 = this.i;
        Intrinsics.checkNotNull(calendar2);
        calendar2.add(6, -1);
    }

    public final void K() {
        Long lastSleepSyncTimeToFit = GoogleFitPreferenceManager.getInstance(this.f4831a).getLastSleepSyncTimeToFit();
        this.h = Calendar.getInstance();
        if (lastSleepSyncTimeToFit != null && lastSleepSyncTimeToFit.longValue() != -1) {
            Calendar calendar = this.h;
            Intrinsics.checkNotNull(calendar);
            calendar.setTimeInMillis(lastSleepSyncTimeToFit.longValue());
            return;
        }
        Calendar calendar2 = this.h;
        Intrinsics.checkNotNull(calendar2);
        calendar2.add(6, -1);
    }

    public final void L() {
        Long lastStepsSyncTimeToFit = GoogleFitPreferenceManager.getInstance(this.f4831a).getLastStepsSyncTimeToFit();
        this.k = Calendar.getInstance();
        if (lastStepsSyncTimeToFit != null && lastStepsSyncTimeToFit.longValue() != -1) {
            Calendar calendar = this.k;
            Intrinsics.checkNotNull(calendar);
            calendar.setTimeInMillis(lastStepsSyncTimeToFit.longValue());
            return;
        }
        Calendar calendar2 = this.k;
        Intrinsics.checkNotNull(calendar2);
        calendar2.add(6, -1);
    }

    public final void M() {
        Long lastTemperatureSyncTimeToFit = GoogleFitPreferenceManager.getInstance(this.f4831a).getLastTemperatureSyncTimeToFit();
        this.j = Calendar.getInstance();
        if (lastTemperatureSyncTimeToFit != null && lastTemperatureSyncTimeToFit.longValue() != -1) {
            Calendar calendar = this.j;
            Intrinsics.checkNotNull(calendar);
            calendar.setTimeInMillis(lastTemperatureSyncTimeToFit.longValue());
            return;
        }
        Calendar calendar2 = this.j;
        Intrinsics.checkNotNull(calendar2);
        calendar2.add(6, -1);
    }

    public final void N() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
    }

    @NotNull
    public final Context getContext() {
        return this.f4831a;
    }

    @NotNull
    public final Observer<List<EntityHourlySpo2>> getDailySPO2DataObserver() {
        Observer<List<EntityHourlySpo2>> observer = this.dailySPO2DataObserver;
        if (observer != null) {
            return observer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dailySPO2DataObserver");
        return null;
    }

    @NotNull
    public final Observer<List<HourlyTemperature>> getDailyTemperatureDataObserver() {
        Observer<List<HourlyTemperature>> observer = this.dailyTemperatureDataObserver;
        if (observer != null) {
            return observer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dailyTemperatureDataObserver");
        return null;
    }

    @NotNull
    public final Observer<List<EntityHourlyHeartRateData>> getHourlyHeartRateObserver() {
        Observer<List<EntityHourlyHeartRateData>> observer = this.hourlyHeartRateObserver;
        if (observer != null) {
            return observer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hourlyHeartRateObserver");
        return null;
    }

    @NotNull
    public final Observer<List<HourlyWalkData>> getHourlyWalkDataObserver() {
        Observer<List<HourlyWalkData>> observer = this.hourlyWalkDataObserver;
        if (observer != null) {
            return observer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hourlyWalkDataObserver");
        return null;
    }

    @NotNull
    public final Observer<List<SleepDataModelForLastNight>> getSleepDataObserver() {
        Observer<List<SleepDataModelForLastNight>> observer = this.sleepDataObserver;
        if (observer != null) {
            return observer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sleepDataObserver");
        return null;
    }

    @NotNull
    public final TodaysData getTodaysData() {
        TodaysData todaysData = this.todaysData;
        if (todaysData != null) {
            return todaysData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("todaysData");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void insertAndReadData() {
        try {
            LogHelper.d("GoogleFitDataManager", "time zone name " + TimeZone.getDefault().getDisplayName());
            final String macAddress = BleApiManager.getInstance(this.f4831a).getBleApi().getMacAddress();
            if (macAddress != null && !TextUtils.isEmpty(macAddress)) {
                getTodaysData().setDailyHeartRateDataList(new ArrayList());
                getTodaysData().setDailySleepDataList(new ArrayList());
                getTodaysData().setDailySPO2DataList(new ArrayList());
                getTodaysData().setDailyTemperatureDataList(new ArrayList());
                getTodaysData().setDailyWalkDataList(new ArrayList());
                setHourlyHeartRateObserver(new Observer<List<? extends EntityHourlyHeartRateData>>() { // from class: com.coveiot.android.leonardo.googlefit.GoogleFitDataManager$insertAndReadData$1
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(@NotNull List<? extends EntityHourlyHeartRateData> entityHourlyHeartRateData) {
                        Calendar calendar;
                        Calendar calendar2;
                        Calendar calendar3;
                        Calendar calendar4;
                        Calendar calendar5;
                        Calendar calendar6;
                        Calendar calendar7;
                        Calendar calendar8;
                        Calendar calendar9;
                        Calendar calendar10;
                        Calendar calendar11;
                        Calendar calendar12;
                        Intrinsics.checkNotNullParameter(entityHourlyHeartRateData, "entityHourlyHeartRateData");
                        String tAG$app_prodRelease = GoogleFitDataManager.Companion.getTAG$app_prodRelease();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Heart Rate onChanged ");
                        calendar = GoogleFitDataManager.this.g;
                        Intrinsics.checkNotNull(calendar);
                        sb.append(calendar.getTime());
                        LogHelper.d(tAG$app_prodRelease, sb.toString());
                        GoogleFitPreferenceManager googleFitPreferenceManager = GoogleFitPreferenceManager.getInstance(GoogleFitDataManager.this.getContext());
                        calendar2 = GoogleFitDataManager.this.g;
                        Intrinsics.checkNotNull(calendar2);
                        googleFitPreferenceManager.saveLastHeartRateSyncTimeToFit(Long.valueOf(calendar2.getTimeInMillis()));
                        HeartRateRepository.Companion companion = HeartRateRepository.Companion;
                        calendar3 = GoogleFitDataManager.this.g;
                        Intrinsics.checkNotNull(calendar3);
                        companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyData(calendar3, macAddress).removeObserver(GoogleFitDataManager.this.getHourlyHeartRateObserver());
                        if (!(!entityHourlyHeartRateData.isEmpty())) {
                            calendar4 = GoogleFitDataManager.this.g;
                            Intrinsics.checkNotNull(calendar4);
                            if (DateUtils.isToday(calendar4.getTimeInMillis())) {
                                GoogleFitDataManager.this.L();
                                calendar7 = GoogleFitDataManager.this.k;
                                Intrinsics.checkNotNull(calendar7);
                                WalkRepository.Companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyData(calendar7, macAddress).observeForever(GoogleFitDataManager.this.getHourlyWalkDataObserver());
                                return;
                            }
                            calendar5 = GoogleFitDataManager.this.g;
                            Intrinsics.checkNotNull(calendar5);
                            calendar5.add(6, 1);
                            calendar6 = GoogleFitDataManager.this.g;
                            Intrinsics.checkNotNull(calendar6);
                            companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyData(calendar6, macAddress).observeForever(GoogleFitDataManager.this.getHourlyHeartRateObserver());
                        } else if (GoogleFitDataManager.this.getTodaysData() != null) {
                            List<DailyHeartRateData> dailyHeartRateDataList = GoogleFitDataManager.this.getTodaysData().getDailyHeartRateDataList();
                            if (dailyHeartRateDataList != null) {
                                List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) entityHourlyHeartRateData);
                                calendar12 = GoogleFitDataManager.this.g;
                                Intrinsics.checkNotNull(calendar12);
                                Object clone = calendar12.clone();
                                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                                dailyHeartRateDataList.add(new DailyHeartRateData(mutableList, (Calendar) clone));
                            }
                            calendar8 = GoogleFitDataManager.this.g;
                            Intrinsics.checkNotNull(calendar8);
                            if (DateUtils.isToday(calendar8.getTimeInMillis())) {
                                GoogleFitDataManager.this.L();
                                calendar11 = GoogleFitDataManager.this.k;
                                Intrinsics.checkNotNull(calendar11);
                                WalkRepository.Companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyData(calendar11, macAddress).observeForever(GoogleFitDataManager.this.getHourlyWalkDataObserver());
                                return;
                            }
                            calendar9 = GoogleFitDataManager.this.g;
                            Intrinsics.checkNotNull(calendar9);
                            calendar9.add(6, 1);
                            calendar10 = GoogleFitDataManager.this.g;
                            Intrinsics.checkNotNull(calendar10);
                            companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyData(calendar10, macAddress).observeForever(GoogleFitDataManager.this.getHourlyHeartRateObserver());
                        }
                    }
                });
                setHourlyWalkDataObserver(new Observer<List<? extends HourlyWalkData>>() { // from class: com.coveiot.android.leonardo.googlefit.GoogleFitDataManager$insertAndReadData$2
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(@NotNull List<? extends HourlyWalkData> entityHourlyData) {
                        Calendar calendar;
                        Calendar calendar2;
                        Calendar calendar3;
                        Calendar calendar4;
                        Calendar calendar5;
                        Calendar calendar6;
                        Calendar calendar7;
                        Calendar calendar8;
                        Calendar calendar9;
                        Calendar calendar10;
                        Calendar calendar11;
                        Intrinsics.checkNotNullParameter(entityHourlyData, "entityHourlyData");
                        LogHelper.d(GoogleFitDataManager.Companion.getTAG$app_prodRelease(), "Steps onChanged");
                        GoogleFitPreferenceManager googleFitPreferenceManager = GoogleFitPreferenceManager.getInstance(GoogleFitDataManager.this.getContext());
                        calendar = GoogleFitDataManager.this.k;
                        Intrinsics.checkNotNull(calendar);
                        googleFitPreferenceManager.saveLastStepsSyncTimeToFit(Long.valueOf(calendar.getTimeInMillis()));
                        WalkRepository.Companion companion = WalkRepository.Companion;
                        calendar2 = GoogleFitDataManager.this.k;
                        Intrinsics.checkNotNull(calendar2);
                        companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyData(calendar2, macAddress).removeObserver(GoogleFitDataManager.this.getHourlyWalkDataObserver());
                        if (!(!entityHourlyData.isEmpty())) {
                            calendar3 = GoogleFitDataManager.this.k;
                            Intrinsics.checkNotNull(calendar3);
                            if (!DateUtils.isToday(calendar3.getTimeInMillis())) {
                                calendar4 = GoogleFitDataManager.this.k;
                                Intrinsics.checkNotNull(calendar4);
                                calendar4.add(6, 1);
                                calendar5 = GoogleFitDataManager.this.k;
                                Intrinsics.checkNotNull(calendar5);
                                companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyData(calendar5, macAddress).observeForever(GoogleFitDataManager.this.getHourlyWalkDataObserver());
                                return;
                            }
                            Boolean isGoogleFitHrSPO2TempSleepSupportAvailable = UserDataManager.getInstance(GoogleFitDataManager.this.getContext()).isGoogleFitHrSPO2TempSleepSupportAvailable();
                            Intrinsics.checkNotNullExpressionValue(isGoogleFitHrSPO2TempSleepSupportAvailable, "getInstance(context).isG…TempSleepSupportAvailable");
                            if (isGoogleFitHrSPO2TempSleepSupportAvailable.booleanValue()) {
                                GoogleFitDataManager.this.K();
                                calendar6 = GoogleFitDataManager.this.h;
                                Intrinsics.checkNotNull(calendar6);
                                SleepRepository.Companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyDataWithoutFlowValidator(calendar6, macAddress).observeForever(GoogleFitDataManager.this.getSleepDataObserver());
                                return;
                            }
                            GoogleFitDataManager.this.N();
                        } else if (GoogleFitDataManager.this.getTodaysData() != null) {
                            List<DailyWalkData> dailyWalkDataList = GoogleFitDataManager.this.getTodaysData().getDailyWalkDataList();
                            Intrinsics.checkNotNull(dailyWalkDataList);
                            List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) entityHourlyData);
                            calendar7 = GoogleFitDataManager.this.k;
                            Intrinsics.checkNotNull(calendar7);
                            Object clone = calendar7.clone();
                            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                            dailyWalkDataList.add(new DailyWalkData(mutableList, (Calendar) clone));
                            calendar8 = GoogleFitDataManager.this.k;
                            Intrinsics.checkNotNull(calendar8);
                            if (!DateUtils.isToday(calendar8.getTimeInMillis())) {
                                calendar9 = GoogleFitDataManager.this.k;
                                Intrinsics.checkNotNull(calendar9);
                                calendar9.add(6, 1);
                                calendar10 = GoogleFitDataManager.this.k;
                                Intrinsics.checkNotNull(calendar10);
                                companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyData(calendar10, macAddress).observeForever(GoogleFitDataManager.this.getHourlyWalkDataObserver());
                                return;
                            }
                            Boolean isGoogleFitHrSPO2TempSleepSupportAvailable2 = UserDataManager.getInstance(GoogleFitDataManager.this.getContext()).isGoogleFitHrSPO2TempSleepSupportAvailable();
                            Intrinsics.checkNotNullExpressionValue(isGoogleFitHrSPO2TempSleepSupportAvailable2, "getInstance(context).isG…TempSleepSupportAvailable");
                            if (isGoogleFitHrSPO2TempSleepSupportAvailable2.booleanValue()) {
                                GoogleFitDataManager.this.K();
                                calendar11 = GoogleFitDataManager.this.h;
                                Intrinsics.checkNotNull(calendar11);
                                SleepRepository.Companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyDataWithoutFlowValidator(calendar11, macAddress).observeForever(GoogleFitDataManager.this.getSleepDataObserver());
                                return;
                            }
                            GoogleFitDataManager.this.N();
                        }
                    }
                });
                setSleepDataObserver(new Observer<List<? extends SleepDataModelForLastNight>>() { // from class: com.coveiot.android.leonardo.googlefit.GoogleFitDataManager$insertAndReadData$3
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(@NotNull List<? extends SleepDataModelForLastNight> sleepDataModels) {
                        Calendar calendar;
                        Calendar calendar2;
                        Calendar calendar3;
                        Calendar calendar4;
                        Calendar calendar5;
                        Calendar calendar6;
                        Calendar calendar7;
                        Calendar calendar8;
                        Calendar calendar9;
                        Calendar calendar10;
                        Calendar calendar11;
                        Calendar calendar12;
                        Intrinsics.checkNotNullParameter(sleepDataModels, "sleepDataModels");
                        String tAG$app_prodRelease = GoogleFitDataManager.Companion.getTAG$app_prodRelease();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Sleep onChanged ");
                        calendar = GoogleFitDataManager.this.h;
                        Intrinsics.checkNotNull(calendar);
                        sb.append(calendar.getTime());
                        LogHelper.d(tAG$app_prodRelease, sb.toString());
                        GoogleFitPreferenceManager googleFitPreferenceManager = GoogleFitPreferenceManager.getInstance(GoogleFitDataManager.this.getContext());
                        calendar2 = GoogleFitDataManager.this.h;
                        Intrinsics.checkNotNull(calendar2);
                        googleFitPreferenceManager.saveLastSleepSyncTimeToFit(Long.valueOf(calendar2.getTimeInMillis()));
                        SleepRepository.Companion companion = SleepRepository.Companion;
                        calendar3 = GoogleFitDataManager.this.h;
                        Intrinsics.checkNotNull(calendar3);
                        companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyDataWithoutFlowValidator(calendar3, macAddress).removeObserver(GoogleFitDataManager.this.getSleepDataObserver());
                        if (!(!sleepDataModels.isEmpty())) {
                            calendar4 = GoogleFitDataManager.this.h;
                            Intrinsics.checkNotNull(calendar4);
                            if (DateUtils.isToday(calendar4.getTimeInMillis())) {
                                GoogleFitDataManager.this.J();
                                calendar7 = GoogleFitDataManager.this.i;
                                Intrinsics.checkNotNull(calendar7);
                                PeriodicSpo2Repository.Companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyData(calendar7, BleApiManager.getInstance(GoogleFitDataManager.this.getContext()).getBleApi().getMacAddress()).observeForever(GoogleFitDataManager.this.getDailySPO2DataObserver());
                                return;
                            }
                            calendar5 = GoogleFitDataManager.this.h;
                            Intrinsics.checkNotNull(calendar5);
                            calendar5.add(6, 1);
                            calendar6 = GoogleFitDataManager.this.h;
                            Intrinsics.checkNotNull(calendar6);
                            companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyDataWithoutFlowValidator(calendar6, macAddress).observeForever(GoogleFitDataManager.this.getSleepDataObserver());
                        } else if (GoogleFitDataManager.this.getTodaysData() != null) {
                            SleepDataModel sleepDataModel = SleepDataHelper.getSleepDataModel(GoogleFitDataManager.this.getContext(), sleepDataModels);
                            if (sleepDataModel != null) {
                                GoogleFitDataManager googleFitDataManager = GoogleFitDataManager.this;
                                List<DailySleepData> dailySleepDataList = googleFitDataManager.getTodaysData().getDailySleepDataList();
                                if (dailySleepDataList != null) {
                                    calendar12 = googleFitDataManager.h;
                                    Intrinsics.checkNotNull(calendar12);
                                    Object clone = calendar12.clone();
                                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                                    dailySleepDataList.add(new DailySleepData(sleepDataModel, (Calendar) clone));
                                }
                            }
                            calendar8 = GoogleFitDataManager.this.h;
                            Intrinsics.checkNotNull(calendar8);
                            if (DateUtils.isToday(calendar8.getTimeInMillis())) {
                                GoogleFitDataManager.this.J();
                                calendar11 = GoogleFitDataManager.this.i;
                                Intrinsics.checkNotNull(calendar11);
                                PeriodicSpo2Repository.Companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyData(calendar11, BleApiManager.getInstance(GoogleFitDataManager.this.getContext()).getBleApi().getMacAddress()).observeForever(GoogleFitDataManager.this.getDailySPO2DataObserver());
                                return;
                            }
                            calendar9 = GoogleFitDataManager.this.h;
                            Intrinsics.checkNotNull(calendar9);
                            calendar9.add(6, 1);
                            calendar10 = GoogleFitDataManager.this.h;
                            Intrinsics.checkNotNull(calendar10);
                            companion.getInstance(GoogleFitDataManager.this.getContext()).getHourlyDataWithoutFlowValidator(calendar10, macAddress).observeForever(GoogleFitDataManager.this.getSleepDataObserver());
                        }
                    }
                });
                setDailySPO2DataObserver(new Observer() { // from class: com.coveiot.android.leonardo.googlefit.e
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        GoogleFitDataManager.s(GoogleFitDataManager.this, (List) obj);
                    }
                });
                setDailyTemperatureDataObserver(new Observer() { // from class: com.coveiot.android.leonardo.googlefit.f
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        GoogleFitDataManager.t(GoogleFitDataManager.this, (List) obj);
                    }
                });
                Boolean isGoogleFitHrSPO2TempSleepSupportAvailable = UserDataManager.getInstance(this.f4831a).isGoogleFitHrSPO2TempSleepSupportAvailable();
                Intrinsics.checkNotNullExpressionValue(isGoogleFitHrSPO2TempSleepSupportAvailable, "getInstance(context).isG…TempSleepSupportAvailable");
                if (isGoogleFitHrSPO2TempSleepSupportAvailable.booleanValue()) {
                    if (ContextCompat.checkSelfPermission(this.f4831a, "android.permission.BODY_SENSORS") == 0) {
                        I();
                        Calendar calendar = this.g;
                        Intrinsics.checkNotNull(calendar);
                        HeartRateRepository.Companion.getInstance(this.f4831a).getHourlyData(calendar, macAddress).observeForever(getHourlyHeartRateObserver());
                    } else {
                        L();
                        Calendar calendar2 = this.k;
                        Intrinsics.checkNotNull(calendar2);
                        WalkRepository.Companion.getInstance(this.f4831a).getHourlyData(calendar2, macAddress).observeForever(getHourlyWalkDataObserver());
                    }
                } else {
                    L();
                    Calendar calendar3 = this.k;
                    Intrinsics.checkNotNull(calendar3);
                    WalkRepository.Companion.getInstance(this.f4831a).getHourlyData(calendar3, macAddress).observeForever(getHourlyWalkDataObserver());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final DataSet j(DataSet dataSet) {
        String valueOf;
        if (getTodaysData() != null) {
            List<DailyTemperatureData> dailyTemperatureDataList = getTodaysData().getDailyTemperatureDataList();
            if (!(dailyTemperatureDataList == null || dailyTemperatureDataList.isEmpty())) {
                List<DailyTemperatureData> dailyTemperatureDataList2 = getTodaysData().getDailyTemperatureDataList();
                Intrinsics.checkNotNull(dailyTemperatureDataList2);
                for (DailyTemperatureData dailyTemperatureData : dailyTemperatureDataList2) {
                    List<HourlyTemperature> hourlyTemperatureDataList = dailyTemperatureData.getHourlyTemperatureDataList();
                    if (!(hourlyTemperatureDataList == null || hourlyTemperatureDataList.isEmpty())) {
                        List<HourlyTemperature> hourlyTemperatureDataList2 = dailyTemperatureData.getHourlyTemperatureDataList();
                        Intrinsics.checkNotNull(hourlyTemperatureDataList2);
                        int size = hourlyTemperatureDataList2.size();
                        for (int i = 0; i < size; i++) {
                            List<HourlyTemperature> hourlyTemperatureDataList3 = dailyTemperatureData.getHourlyTemperatureDataList();
                            Intrinsics.checkNotNull(hourlyTemperatureDataList3);
                            HourlyTemperature hourlyTemperature = hourlyTemperatureDataList3.get(i);
                            List<Double> codevalue = hourlyTemperature.getCodevalue();
                            if (!(codevalue == null || codevalue.isEmpty())) {
                                int size2 = 60 / hourlyTemperature.getCodevalue().size();
                                int size3 = hourlyTemperature.getCodevalue().size();
                                for (int i2 = 0; i2 < size3; i2++) {
                                    Double temperature = hourlyTemperature.getCodevalue().get(i2);
                                    Intrinsics.checkNotNullExpressionValue(temperature, "temperature");
                                    if (temperature.doubleValue() > 0.0d) {
                                        LogHelper.d(l, "Temperature Val " + temperature);
                                        int i3 = i2 * size2;
                                        if (i3 < 10) {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append('0');
                                            sb.append(i3);
                                            valueOf = sb.toString();
                                        } else {
                                            valueOf = String.valueOf(i3);
                                        }
                                        String startTime = hourlyTemperature.getStartTime();
                                        Intrinsics.checkNotNullExpressionValue(startTime, "bean.startTime");
                                        String substring = startTime.substring(0, 2);
                                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                                        StringBuilder sb2 = new StringBuilder();
                                        Intrinsics.checkNotNull(dailyTemperatureData);
                                        Calendar date = dailyTemperatureData.getDate();
                                        Intrinsics.checkNotNull(date);
                                        sb2.append(AppUtils.formatDate(date.getTime(), "yyyy-MM-dd"));
                                        sb2.append(' ');
                                        sb2.append(substring);
                                        sb2.append(':');
                                        sb2.append(valueOf);
                                        sb2.append(":00");
                                        String sb3 = sb2.toString();
                                        LogHelper.d(l, "Start temperaturetime " + sb3);
                                        LogHelper.d(l, "End temperaturetime " + sb3);
                                        DataPoint timeInterval = dataSet.createDataPoint().setTimeInterval(PayUtils.convertStringDateTimeToMilliSeconds2(sb3), PayUtils.convertStringDateTimeToMilliSeconds2(sb3), TimeUnit.MILLISECONDS);
                                        Intrinsics.checkNotNullExpressionValue(timeInterval, "dataSet.createDataPoint(…                        )");
                                        timeInterval.getValue(HealthFields.FIELD_BODY_TEMPERATURE).setFloat((float) temperature.doubleValue());
                                        dataSet.add(timeInterval);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return dataSet;
    }

    public final DataSet k(DataSet dataSet) {
        List<DailyWalkData> dailyWalkDataList = getTodaysData().getDailyWalkDataList();
        if (!(dailyWalkDataList == null || dailyWalkDataList.isEmpty())) {
            List<DailyWalkData> dailyWalkDataList2 = getTodaysData().getDailyWalkDataList();
            Intrinsics.checkNotNull(dailyWalkDataList2);
            for (DailyWalkData dailyWalkData : dailyWalkDataList2) {
                List<HourlyWalkData> hourlyWalkDataList = dailyWalkData.getHourlyWalkDataList();
                if (!(hourlyWalkDataList == null || hourlyWalkDataList.isEmpty())) {
                    Intrinsics.checkNotNull(hourlyWalkDataList);
                    int size = hourlyWalkDataList.size();
                    for (int i = 0; i < size; i++) {
                        HourlyWalkData hourlyWalkData = hourlyWalkDataList.get(i);
                        if (hourlyWalkData.getCalories() > 0) {
                            String str = hourlyWalkData.getmDate() + ' ' + hourlyWalkData.getStartTime();
                            StringBuilder sb = new StringBuilder();
                            sb.append(hourlyWalkData.getmDate());
                            sb.append(' ');
                            sb.append(Intrinsics.areEqual(hourlyWalkData.getEndTime(), "00:00:00") ? "24:00:00" : hourlyWalkData.getEndTime());
                            String sb2 = sb.toString();
                            LogHelper.d(l, "Start calorietime " + str);
                            LogHelper.d(l, "end calorietime " + sb2);
                            LogHelper.d(l, "calorie " + hourlyWalkData.getCalories());
                            DataPoint timeInterval = dataSet.createDataPoint().setTimeInterval(PayUtils.convertStringDateTimeToMilliSeconds2(str), PayUtils.convertStringDateTimeToMilliSeconds2(sb2), TimeUnit.MILLISECONDS);
                            Intrinsics.checkNotNullExpressionValue(timeInterval, "dataSet.createDataPoint(…                        )");
                            timeInterval.getValue(Field.FIELD_CALORIES).setFloat((float) hourlyWalkData.getCalories());
                            dataSet.add(timeInterval);
                        }
                    }
                }
            }
        }
        return dataSet;
    }

    public final DataSet l(DataSet dataSet) {
        List<DailyWalkData> dailyWalkDataList = getTodaysData().getDailyWalkDataList();
        if (!(dailyWalkDataList == null || dailyWalkDataList.isEmpty())) {
            List<DailyWalkData> dailyWalkDataList2 = getTodaysData().getDailyWalkDataList();
            Intrinsics.checkNotNull(dailyWalkDataList2);
            for (DailyWalkData dailyWalkData : dailyWalkDataList2) {
                List<HourlyWalkData> hourlyWalkDataList = dailyWalkData.getHourlyWalkDataList();
                if (!(hourlyWalkDataList == null || hourlyWalkDataList.isEmpty())) {
                    Intrinsics.checkNotNull(hourlyWalkDataList);
                    int size = hourlyWalkDataList.size();
                    for (int i = 0; i < size; i++) {
                        HourlyWalkData hourlyWalkData = hourlyWalkDataList.get(i);
                        if (hourlyWalkData.getIntervelValue() > 0) {
                            String str = hourlyWalkData.getmDate() + ' ' + hourlyWalkData.getStartTime();
                            StringBuilder sb = new StringBuilder();
                            sb.append(hourlyWalkData.getmDate());
                            sb.append(' ');
                            sb.append(Intrinsics.areEqual(hourlyWalkData.getEndTime(), "00:00:00") ? "24:00:00" : hourlyWalkData.getEndTime());
                            String sb2 = sb.toString();
                            float intervelValue = hourlyWalkData.getIntervelValue();
                            float f = 70.0f;
                            if (ProfileData.getInstance().getStride_length() != null) {
                                String stride_length = ProfileData.getInstance().getStride_length();
                                Intrinsics.checkNotNull(stride_length);
                                if (Float.parseFloat(stride_length) > 0.0f) {
                                    String stride_length2 = ProfileData.getInstance().getStride_length();
                                    Intrinsics.checkNotNullExpressionValue(stride_length2, "getInstance().stride_length");
                                    f = Float.parseFloat(stride_length2);
                                }
                            }
                            float f2 = intervelValue * f;
                            float f3 = f2 / 100;
                            if (f3 > 0.0f) {
                                LogHelper.d("Start distancetime", str);
                                LogHelper.d("end distancetime", sb2);
                                DataPoint timeInterval = dataSet.createDataPoint().setTimeInterval(PayUtils.convertStringDateTimeToMilliSeconds2(str), PayUtils.convertStringDateTimeToMilliSeconds2(sb2), TimeUnit.MILLISECONDS);
                                Intrinsics.checkNotNullExpressionValue(timeInterval, "dataSet.createDataPoint(…                        )");
                                LogHelper.d(l, "distance and length" + f2);
                                LogHelper.d(l, "interval value " + intervelValue);
                                LogHelper.d(l, "stride Lenght " + f);
                                LogHelper.d(l, "Distance = " + f3);
                                timeInterval.getValue(Field.FIELD_DISTANCE).setFloat(f3);
                                dataSet.add(timeInterval);
                            }
                        }
                    }
                }
            }
        }
        return dataSet;
    }

    public final DataSet m(DataSet dataSet) {
        String valueOf;
        if (getTodaysData() != null) {
            List<DailyHeartRateData> dailyHeartRateDataList = getTodaysData().getDailyHeartRateDataList();
            int i = 0;
            if (!(dailyHeartRateDataList == null || dailyHeartRateDataList.isEmpty())) {
                List<DailyHeartRateData> dailyHeartRateDataList2 = getTodaysData().getDailyHeartRateDataList();
                Intrinsics.checkNotNull(dailyHeartRateDataList2);
                for (DailyHeartRateData dailyHeartRateData : dailyHeartRateDataList2) {
                    List<EntityHourlyHeartRateData> hourlyHeartRateDataList = dailyHeartRateData.getHourlyHeartRateDataList();
                    Intrinsics.checkNotNull(hourlyHeartRateDataList);
                    if (!hourlyHeartRateDataList.isEmpty()) {
                        List<EntityHourlyHeartRateData> hourlyHeartRateDataList2 = dailyHeartRateData.getHourlyHeartRateDataList();
                        Intrinsics.checkNotNull(hourlyHeartRateDataList2);
                        int size = hourlyHeartRateDataList2.size();
                        int i2 = i;
                        while (i2 < size) {
                            List<EntityHourlyHeartRateData> hourlyHeartRateDataList3 = dailyHeartRateData.getHourlyHeartRateDataList();
                            Intrinsics.checkNotNull(hourlyHeartRateDataList3);
                            EntityHourlyHeartRateData entityHourlyHeartRateData = hourlyHeartRateDataList3.get(i2);
                            ArrayList<Integer> codedValues = entityHourlyHeartRateData.getCodedValues();
                            if (((codedValues == null || codedValues.isEmpty()) ? 1 : i) == 0) {
                                int size2 = 60 / entityHourlyHeartRateData.getCodedValues().size();
                                int size3 = entityHourlyHeartRateData.getCodedValues().size();
                                int i3 = i;
                                while (i3 < size3) {
                                    Integer num = entityHourlyHeartRateData.getCodedValues().get(i3);
                                    if ((num != null ? Float.valueOf(num.intValue()) : null).floatValue() > 0.0f) {
                                        String str = l;
                                        LogHelper.d(str, "Hr Val " + num);
                                        int i4 = i3 * size2;
                                        if (i4 < 10) {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append('0');
                                            sb.append(i4);
                                            valueOf = sb.toString();
                                        } else {
                                            valueOf = String.valueOf(i4);
                                        }
                                        String startTime = entityHourlyHeartRateData.getStartTime();
                                        Intrinsics.checkNotNullExpressionValue(startTime, "bean.startTime");
                                        String substring = startTime.substring(i, 2);
                                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                                        StringBuilder sb2 = new StringBuilder();
                                        Intrinsics.checkNotNull(dailyHeartRateData);
                                        Calendar date = dailyHeartRateData.getDate();
                                        Intrinsics.checkNotNull(date);
                                        sb2.append(AppUtils.formatDate(date.getTime(), "yyyy-MM-dd"));
                                        sb2.append(' ');
                                        sb2.append(substring);
                                        sb2.append(':');
                                        sb2.append(valueOf);
                                        sb2.append(":00");
                                        String sb3 = sb2.toString();
                                        String str2 = l;
                                        LogHelper.d(str2, "Start hrtime " + sb3);
                                        String str3 = l;
                                        LogHelper.d(str3, "end hrtime " + sb3);
                                        DataPoint timeInterval = dataSet.createDataPoint().setTimeInterval(PayUtils.convertStringDateTimeToMilliSeconds2(sb3), PayUtils.convertStringDateTimeToMilliSeconds2(sb3), TimeUnit.MILLISECONDS);
                                        Intrinsics.checkNotNullExpressionValue(timeInterval, "dataSet.createDataPoint(…                        )");
                                        timeInterval.getValue(Field.FIELD_BPM).setFloat((float) num.intValue());
                                        dataSet.add(timeInterval);
                                    }
                                    i3++;
                                    i = 0;
                                }
                            }
                            i2++;
                            i = 0;
                        }
                    }
                }
            }
        }
        return dataSet;
    }

    public final List<DataSet> n(DataSource dataSource) {
        DataSet create = DataSet.create(dataSource);
        Intrinsics.checkNotNullExpressionValue(create, "create(dataSource)");
        return CollectionsKt__CollectionsKt.mutableListOf(create);
    }

    public final DataSet o(DataSet dataSet) {
        String valueOf;
        if (getTodaysData() != null) {
            List<DailySPO2Data> dailySPO2DataList = getTodaysData().getDailySPO2DataList();
            int i = 0;
            if (!(dailySPO2DataList == null || dailySPO2DataList.isEmpty())) {
                List<DailySPO2Data> dailySPO2DataList2 = getTodaysData().getDailySPO2DataList();
                Intrinsics.checkNotNull(dailySPO2DataList2);
                for (DailySPO2Data dailySPO2Data : dailySPO2DataList2) {
                    List<EntityHourlySpo2> hourlySPO2DataList = dailySPO2Data.getHourlySPO2DataList();
                    if (((hourlySPO2DataList == null || hourlySPO2DataList.isEmpty()) ? 1 : i) == 0) {
                        List<EntityHourlySpo2> hourlySPO2DataList2 = dailySPO2Data.getHourlySPO2DataList();
                        Intrinsics.checkNotNull(hourlySPO2DataList2);
                        int size = hourlySPO2DataList2.size();
                        int i2 = i;
                        while (i2 < size) {
                            List<EntityHourlySpo2> hourlySPO2DataList3 = dailySPO2Data.getHourlySPO2DataList();
                            Intrinsics.checkNotNull(hourlySPO2DataList3);
                            EntityHourlySpo2 entityHourlySpo2 = hourlySPO2DataList3.get(i2);
                            List<Integer> list = entityHourlySpo2.codevalue;
                            if (((list == null || list.isEmpty()) ? 1 : i) == 0) {
                                int size2 = 60 / entityHourlySpo2.codevalue.size();
                                int size3 = entityHourlySpo2.codevalue.size();
                                int i3 = i;
                                while (i3 < size3) {
                                    Integer num = entityHourlySpo2.codevalue.get(i3);
                                    if ((num != null ? Float.valueOf(num.intValue()) : null).floatValue() > 0.0f) {
                                        String str = l;
                                        LogHelper.d(str, "SPO2 Val " + num);
                                        int i4 = i3 * size2;
                                        if (i4 < 10) {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append('0');
                                            sb.append(i4);
                                            valueOf = sb.toString();
                                        } else {
                                            valueOf = String.valueOf(i4);
                                        }
                                        String startTime = entityHourlySpo2.getStartTime();
                                        Intrinsics.checkNotNullExpressionValue(startTime, "bean.startTime");
                                        String substring = startTime.substring(i, 2);
                                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                                        StringBuilder sb2 = new StringBuilder();
                                        Intrinsics.checkNotNull(dailySPO2Data);
                                        Calendar date = dailySPO2Data.getDate();
                                        Intrinsics.checkNotNull(date);
                                        sb2.append(AppUtils.formatDate(date.getTime(), "yyyy-MM-dd"));
                                        sb2.append(' ');
                                        sb2.append(substring);
                                        sb2.append(':');
                                        sb2.append(valueOf);
                                        sb2.append(":00");
                                        String sb3 = sb2.toString();
                                        String str2 = l;
                                        LogHelper.d(str2, "Start spo2time " + sb3);
                                        String str3 = l;
                                        LogHelper.d(str3, "End spo2time " + sb3);
                                        DataPoint timeInterval = dataSet.createDataPoint().setTimeInterval(PayUtils.convertStringDateTimeToMilliSeconds2(sb3), PayUtils.convertStringDateTimeToMilliSeconds2(sb3), TimeUnit.MILLISECONDS);
                                        Intrinsics.checkNotNullExpressionValue(timeInterval, "dataSet.createDataPoint(…                        )");
                                        timeInterval.getValue(HealthFields.FIELD_OXYGEN_SATURATION).setFloat((float) num.intValue());
                                        timeInterval.getValue(HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE).setFloat(0.0f);
                                        dataSet.add(timeInterval);
                                    }
                                    i3++;
                                    i = 0;
                                }
                            }
                            i2++;
                            i = 0;
                        }
                    }
                }
            }
        }
        return dataSet;
    }

    public final List<DataSet> p(DataSource dataSource) {
        Iterator<DailySleepData> it;
        byte b;
        DailySleepData dailySleepData;
        String valueOf;
        String valueOf2;
        char c;
        String valueOf3;
        String valueOf4;
        int i;
        SleepDataModel sleepDataModel;
        SleepDataModel sleepDataModel2;
        SleepDataModel sleepDataModel3;
        SleepDataModel sleepDataModel4;
        SleepDataModel sleepDataModel5;
        SleepDataModel sleepDataModel6;
        SleepDataModel sleepDataModel7;
        ArrayList arrayList = new ArrayList();
        if (getTodaysData() != null) {
            List<DailySleepData> dailySleepDataList = getTodaysData().getDailySleepDataList();
            byte b2 = 1;
            if (!(dailySleepDataList == null || dailySleepDataList.isEmpty())) {
                List<DailySleepData> dailySleepDataList2 = getTodaysData().getDailySleepDataList();
                Intrinsics.checkNotNull(dailySleepDataList2);
                Iterator<DailySleepData> it2 = dailySleepDataList2.iterator();
                while (it2.hasNext()) {
                    DailySleepData next = it2.next();
                    DataSet create = DataSet.create(dataSource);
                    Intrinsics.checkNotNullExpressionValue(create, "create(dataSource)");
                    if (((next == null || (sleepDataModel7 = next.getSleepDataModel()) == null) ? null : sleepDataModel7.getFilteredSleepData()) != null) {
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (companion.isSmaDevice(this.f4831a)) {
                            this.c = (byte) 2;
                            this.d = b2;
                            this.e = (byte) 3;
                        } else if (companion.isIDODevice(this.f4831a) || companion.isTouchELXDevice(this.f4831a)) {
                            this.b = b2;
                            this.c = (byte) 2;
                            this.d = (byte) 3;
                            this.e = (byte) 4;
                        }
                        int i2 = -1;
                        if (((next == null || (sleepDataModel6 = next.getSleepDataModel()) == null || sleepDataModel6.getSleepStartHour() != -1) ? (byte) 0 : b2) == 0) {
                            if (((next == null || (sleepDataModel5 = next.getSleepDataModel()) == null || sleepDataModel5.getSleepEndHour() != -1) ? (byte) 0 : b2) == 0) {
                                Integer valueOf5 = (next == null || (sleepDataModel4 = next.getSleepDataModel()) == null) ? null : Integer.valueOf(sleepDataModel4.getSleepStartHour());
                                Intrinsics.checkNotNull(valueOf5);
                                int r = r(valueOf5.intValue());
                                Integer valueOf6 = (next == null || (sleepDataModel3 = next.getSleepDataModel()) == null) ? null : Integer.valueOf(sleepDataModel3.getSleepEndHour());
                                Intrinsics.checkNotNull(valueOf6);
                                int r2 = r(valueOf6.intValue());
                                int i3 = r * 60;
                                Integer valueOf7 = (next == null || (sleepDataModel2 = next.getSleepDataModel()) == null) ? null : Integer.valueOf(sleepDataModel2.getSleepStartMinute());
                                Intrinsics.checkNotNull(valueOf7);
                                int intValue = i3 + valueOf7.intValue();
                                int i4 = r2 * 60;
                                Integer valueOf8 = (next == null || (sleepDataModel = next.getSleepDataModel()) == null) ? null : Integer.valueOf(sleepDataModel.getSleepEndMinute());
                                Intrinsics.checkNotNull(valueOf8);
                                int intValue2 = i4 + valueOf8.intValue();
                                byte b3 = this.f;
                                byte b4 = b2;
                                int i5 = -1;
                                int i6 = 0;
                                while (b4 != 0) {
                                    if (intValue == 1440) {
                                        intValue = 0;
                                    }
                                    SleepDataModel sleepDataModel8 = next != null ? next.getSleepDataModel() : null;
                                    Intrinsics.checkNotNull(sleepDataModel8);
                                    byte b5 = sleepDataModel8.getFilteredSleepData()[intValue];
                                    if (intValue == intValue2) {
                                        i6 = b3 == b5 ? i6 + 1 : 1;
                                        LogHelper.d(l, "endIndex " + intValue2);
                                        b3 = b5;
                                        b4 = 0;
                                    }
                                    if (b5 != b3 || b4 == 0) {
                                        if (i6 > 0) {
                                            Intrinsics.checkNotNull(next);
                                            Calendar date = next.getDate();
                                            Intrinsics.checkNotNull(date);
                                            Object clone = date.clone();
                                            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                                            Calendar date2 = next.getDate();
                                            Intrinsics.checkNotNull(date2);
                                            Object clone2 = date2.clone();
                                            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
                                            Calendar calendar = (Calendar) clone2;
                                            calendar.add(6, i2);
                                            String today = AppUtils.formatDate(((Calendar) clone).getTime(), "yyyy-MM-dd");
                                            String yesterday = AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
                                            String str = i5 < 720 ? yesterday : today;
                                            int r3 = r(i5 / 60);
                                            it = it2;
                                            if (r3 < 10) {
                                                StringBuilder sb = new StringBuilder();
                                                b = b5;
                                                sb.append('0');
                                                sb.append(r3);
                                                valueOf = sb.toString();
                                            } else {
                                                b = b5;
                                                valueOf = String.valueOf(r3);
                                            }
                                            int i7 = i5 % 60;
                                            if (i7 < 10) {
                                                StringBuilder sb2 = new StringBuilder();
                                                dailySleepData = next;
                                                sb2.append('0');
                                                sb2.append(i7);
                                                valueOf2 = sb2.toString();
                                            } else {
                                                dailySleepData = next;
                                                valueOf2 = String.valueOf(i7);
                                            }
                                            int i8 = i5 + (i6 - 1);
                                            if (i8 < 720) {
                                                Intrinsics.checkNotNullExpressionValue(yesterday, "yesterday");
                                                today = yesterday;
                                            } else {
                                                Intrinsics.checkNotNullExpressionValue(today, "today");
                                            }
                                            int r4 = r(i8 / 60);
                                            if (r4 < 10) {
                                                StringBuilder sb3 = new StringBuilder();
                                                c = '0';
                                                sb3.append('0');
                                                sb3.append(r4);
                                                valueOf3 = sb3.toString();
                                            } else {
                                                c = '0';
                                                valueOf3 = String.valueOf(r4);
                                            }
                                            int i9 = i8 % 60;
                                            if (i9 < 10) {
                                                StringBuilder sb4 = new StringBuilder();
                                                sb4.append(c);
                                                sb4.append(i9);
                                                valueOf4 = sb4.toString();
                                            } else {
                                                valueOf4 = String.valueOf(i9);
                                            }
                                            String str2 = str + ' ' + valueOf + ':' + valueOf2 + ":00";
                                            String str3 = today + ' ' + valueOf3 + ':' + valueOf4 + ":00";
                                            DataPoint timeInterval = create.createDataPoint().setTimeInterval(PayUtils.convertStringDateTimeToMilliSeconds2(str2), PayUtils.convertStringDateTimeToMilliSeconds2(str3), TimeUnit.MILLISECONDS);
                                            Intrinsics.checkNotNullExpressionValue(timeInterval, "dataSet.createDataPoint(…                        )");
                                            if (b3 == this.b) {
                                                i = 1;
                                            } else if (b3 == this.c) {
                                                i = 4;
                                            } else if (b3 == this.d) {
                                                i = 5;
                                            } else {
                                                i = b3 == this.e ? 6 : 3;
                                            }
                                            timeInterval.getValue(Field.FIELD_SLEEP_SEGMENT_TYPE).setInt(i);
                                            create.add(timeInterval);
                                            LogHelper.d(l, "Sleep Type Start " + str2);
                                            LogHelper.d(l, "Sleep Type End  " + str3);
                                            LogHelper.d(l, "Sleep Type " + i);
                                            LogHelper.d(l, "Sleep Type Duration " + i6);
                                            i6 = 1;
                                        } else {
                                            it = it2;
                                            b = b5;
                                            dailySleepData = next;
                                            i6++;
                                        }
                                        i5 = intValue;
                                    } else {
                                        i6++;
                                        it = it2;
                                        b = b5;
                                        dailySleepData = next;
                                    }
                                    intValue++;
                                    it2 = it;
                                    b3 = b;
                                    next = dailySleepData;
                                    i2 = -1;
                                }
                            }
                        }
                    }
                    arrayList.add(create);
                    it2 = it2;
                    b2 = 1;
                }
            }
        }
        return arrayList;
    }

    public final DataSet q(DataSet dataSet) {
        List<DailyWalkData> dailyWalkDataList;
        if (getTodaysData().getDailyWalkDataList() != null) {
            Intrinsics.checkNotNull(getTodaysData().getDailyWalkDataList());
            if (!dailyWalkDataList.isEmpty()) {
                List<DailyWalkData> dailyWalkDataList2 = getTodaysData().getDailyWalkDataList();
                Intrinsics.checkNotNull(dailyWalkDataList2);
                for (DailyWalkData dailyWalkData : dailyWalkDataList2) {
                    List<HourlyWalkData> hourlyWalkDataList = dailyWalkData.getHourlyWalkDataList();
                    if (!(hourlyWalkDataList == null || hourlyWalkDataList.isEmpty())) {
                        Intrinsics.checkNotNull(hourlyWalkDataList);
                        int size = hourlyWalkDataList.size();
                        for (int i = 0; i < size; i++) {
                            HourlyWalkData hourlyWalkData = hourlyWalkDataList.get(i);
                            if (hourlyWalkData.getIntervelValue() > 0) {
                                String str = hourlyWalkData.getmDate() + ' ' + hourlyWalkData.getStartTime();
                                StringBuilder sb = new StringBuilder();
                                sb.append(hourlyWalkData.getmDate());
                                sb.append(' ');
                                sb.append(Intrinsics.areEqual(hourlyWalkData.getEndTime(), "00:00:00") ? "24:00:00" : hourlyWalkData.getEndTime());
                                String sb2 = sb.toString();
                                LogHelper.d("Start steptime", str);
                                LogHelper.d("end steptime", sb2);
                                DataPoint timeInterval = dataSet.createDataPoint().setTimeInterval(PayUtils.convertStringDateTimeToMilliSeconds2(str), PayUtils.convertStringDateTimeToMilliSeconds2(sb2), TimeUnit.MILLISECONDS);
                                Intrinsics.checkNotNullExpressionValue(timeInterval, "dataSet.createDataPoint(…                        )");
                                LogHelper.d(l, "steps value " + hourlyWalkData.getIntervelValue());
                                timeInterval.getValue(Field.FIELD_STEPS).setInt(hourlyWalkData.getIntervelValue());
                                dataSet.add(timeInterval);
                            }
                        }
                    }
                }
            }
        }
        return dataSet;
    }

    @NotNull
    public final DataReadRequest queryDistanceData() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        long timeInMillis = calendar.getTimeInMillis();
        calendar.add(6, -1);
        long timeInMillis2 = calendar.getTimeInMillis();
        DataSource.Builder appPackageName = new DataSource.Builder().setAppPackageName(this.f4831a);
        DataType dataType = DataType.TYPE_DISTANCE_DELTA;
        DataSource.Builder dataType2 = appPackageName.setDataType(dataType);
        DataSource build = dataType2.setStreamName(l + AppConstants.DISTANCE_FIT.getValue()).setType(0).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…RAW)\n            .build()");
        DataReadRequest build2 = new DataReadRequest.Builder().enableServerQueries().aggregate(build, dataType).bucketByTime(1, TimeUnit.HOURS).setTimeRange(timeInMillis2, timeInMillis, TimeUnit.MILLISECONDS).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder()\n            .e…NDS)\n            .build()");
        return build2;
    }

    public final int r(int i) {
        if (i > 12) {
            return Math.abs(12 - i);
        }
        if (i < 12) {
            return i + 12;
        }
        return 0;
    }

    public final void setDailySPO2DataObserver(@NotNull Observer<List<EntityHourlySpo2>> observer) {
        Intrinsics.checkNotNullParameter(observer, "<set-?>");
        this.dailySPO2DataObserver = observer;
    }

    public final void setDailyTemperatureDataObserver(@NotNull Observer<List<HourlyTemperature>> observer) {
        Intrinsics.checkNotNullParameter(observer, "<set-?>");
        this.dailyTemperatureDataObserver = observer;
    }

    public final void setHourlyHeartRateObserver(@NotNull Observer<List<EntityHourlyHeartRateData>> observer) {
        Intrinsics.checkNotNullParameter(observer, "<set-?>");
        this.hourlyHeartRateObserver = observer;
    }

    public final void setHourlyWalkDataObserver(@NotNull Observer<List<HourlyWalkData>> observer) {
        Intrinsics.checkNotNullParameter(observer, "<set-?>");
        this.hourlyWalkDataObserver = observer;
    }

    public final void setSleepDataObserver(@NotNull Observer<List<SleepDataModelForLastNight>> observer) {
        Intrinsics.checkNotNullParameter(observer, "<set-?>");
        this.sleepDataObserver = observer;
    }

    public final void setTodaysData(@NotNull TodaysData todaysData) {
        Intrinsics.checkNotNullParameter(todaysData, "<set-?>");
        this.todaysData = todaysData;
    }

    public final Task<Void> u() {
        DataSource.Builder dataType = new DataSource.Builder().setAppPackageName(this.f4831a).setDataType(DataType.TYPE_CALORIES_EXPENDED);
        DataSource build = dataType.setStreamName(l + AppConstants.CALORIES_FIT.getValue()).setType(0).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…RAW)\n            .build()");
        DataSet create = DataSet.create(build);
        Intrinsics.checkNotNullExpressionValue(create, "create(dataSource)");
        DataSet k = k(create);
        if (k.isEmpty()) {
            return null;
        }
        LogHelper.i(l, "Inserting the calories dataset in the History API.");
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.f4831a);
        if (lastSignedInAccount != null) {
            return Fitness.getHistoryClient(this.f4831a, lastSignedInAccount).insertData(k).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.googlefit.g
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    GoogleFitDataManager.v(task);
                }
            });
        }
        return null;
    }

    public final Task<Void> w() {
        GoogleSignInAccount lastSignedInAccount;
        LogHelper.i(l, "Inserting the distance dataset in the History API.");
        DataSource.Builder dataType = new DataSource.Builder().setAppPackageName(this.f4831a).setDataType(DataType.TYPE_DISTANCE_DELTA);
        DataSource build = dataType.setStreamName(l + AppConstants.DISTANCE_FIT.getValue()).setType(0).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…RAW)\n            .build()");
        DataSet create = DataSet.create(build);
        Intrinsics.checkNotNullExpressionValue(create, "create(dataSource)");
        DataSet l2 = l(create);
        if (l2.isEmpty() || (lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.f4831a)) == null) {
            return null;
        }
        return Fitness.getHistoryClient(this.f4831a, lastSignedInAccount).insertData(l2).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.googlefit.l
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                GoogleFitDataManager.x(task);
            }
        });
    }

    public final Task<Void> y() {
        DataSource.Builder dataType = new DataSource.Builder().setAppPackageName(this.f4831a).setDataType(DataType.TYPE_HEART_RATE_BPM);
        DataSource build = dataType.setStreamName(l + AppConstants.HEART_RATE_FIT.getValue()).setType(1).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…VED)\n            .build()");
        DataSet create = DataSet.create(build);
        Intrinsics.checkNotNullExpressionValue(create, "create(dataSource)");
        DataSet m = m(create);
        if (m.isEmpty()) {
            return null;
        }
        LogHelper.i(l, "Inserting the dataset hr in the History API.");
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.f4831a);
        if (lastSignedInAccount != null) {
            return Fitness.getHistoryClient(this.f4831a, lastSignedInAccount).insertData(m).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.googlefit.h
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    GoogleFitDataManager.z(task);
                }
            });
        }
        return null;
    }
}
