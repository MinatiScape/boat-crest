package com.coveiot.android.remotecommandframework.alexa.response;

import android.annotation.SuppressLint;
import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.request.SendSleepDataRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.SingletonHolder;
import com.coveiot.android.remotecommandframework.alexa.model.LiveHealthDataModel;
import com.coveiot.android.remotecommandframework.alexa.utils.Constants;
import com.coveiot.android.sleepalgorithm.filtering.CZ0ParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.MatrixParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.MoyangParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepalgorithm.filtering.SmaParsedSleepDataF2;
import com.coveiot.android.sleepalgorithm.filtering.StrideParsedSleepDataV2NoAlgo;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepAlgoWithREM;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepData;
import com.coveiot.android.watchfaceui.constants.WatchfaceConstants;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.bp.BPRepository;
import com.coveiot.repository.heartrate.HeartRateRepository;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.repository.periodicspo2.PeriodicSpo2Repository;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.repository.stress.StressRepository;
import com.coveiot.repository.temperature.TemperatureRepository;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.UtilConstants;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class LastDataHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5633a;
    @NotNull
    public final String b;
    @NotNull
    public final Gson c;

    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<LastDataHelper, Context> {

        /* loaded from: classes6.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, LastDataHelper> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, LastDataHelper.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final LastDataHelper invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new LastDataHelper(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes6.dex */
    public interface UploadCompletionListner {
        void onDataUploadeComplete();

        void onUploadFailed();
    }

    @DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.response.LastDataHelper$getLastMeasuredData$2", f = "LastDataHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LiveHealthDataModel>, Object> {
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
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super LiveHealthDataModel> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            double d;
            int i;
            int i2;
            EntityHourlyHeartRateData entityHourlyHeartRateData;
            EntityHourlyBp entityHourlyBp;
            HourlyTemperature hourlyTemperature;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AppUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
                String macAddress = BleApiManager.getInstance(LastDataHelper.this.getContext()).getBleApi().getMacAddress();
                DailyWalkData lastDayData = WalkDBRead.getInstance(LastDataHelper.this.getContext()).getLastDayData(macAddress);
                if (lastDayData != null) {
                    i2 = lastDayData.getValue();
                    d = lastDayData.getCalories();
                    i = lastDayData.getMeters();
                } else {
                    d = 0.0d;
                    i = 0;
                    i2 = 0;
                }
                LiveHealthDataModel liveHealthDataModel = new LiveHealthDataModel();
                liveHealthDataModel.totalSteps = i2;
                liveHealthDataModel.totalCalorie = d;
                liveHealthDataModel.totalDistance = i;
                liveHealthDataModel.stepsRecordedDate = LastDataHelper.this.getSeverTime(System.currentTimeMillis());
                if (BleApiManager.getInstance(LastDataHelper.this.getContext()).getBleApi().getDeviceSupportedFeatures().isSleepSupported()) {
                    LastDataHelper lastDataHelper = LastDataHelper.this;
                    Calendar calendar = Calendar.getInstance();
                    Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                    SleepDataModel lastNightSleepData = lastDataHelper.getLastNightSleepData(SleepRepository.Companion.getInstance(lastDataHelper.getContext()).getLastNignthSleepDataWithOutLiveData(calendar, macAddress));
                    if (lastNightSleepData != null) {
                        LiveHealthDataModel.SleepDetailsBean c = LastDataHelper.this.c(lastNightSleepData);
                        liveHealthDataModel.totalSleep = lastNightSleepData.getCountTotalSleep();
                        if (c != null) {
                            liveHealthDataModel.sleepRecordedDate = c.endDate;
                            liveHealthDataModel.sleepDetails = c;
                        }
                    }
                }
                EntityHourlySpo2 entityHourlySpo2 = null;
                if (BleApiManager.getInstance(LastDataHelper.this.getContext()).getBleApi().getDeviceSupportedFeatures().isHeartRateSupported()) {
                    try {
                        entityHourlyHeartRateData = HeartRateRepository.Companion.getInstance(LastDataHelper.this.getContext()).getLatestRecordHourly(macAddress);
                    } catch (Exception e) {
                        e.printStackTrace();
                        entityHourlyHeartRateData = null;
                    }
                    if (entityHourlyHeartRateData != null) {
                        int size = 60 / entityHourlyHeartRateData.getCodedValues().size();
                        int size2 = entityHourlyHeartRateData.getCodedValues().size();
                        int i3 = 0;
                        int i4 = 0;
                        for (int i5 = 0; i5 < size2; i5++) {
                            Integer num = entityHourlyHeartRateData.getCodedValues().get(i5);
                            Intrinsics.checkNotNullExpressionValue(num, "hourlyHeartRate.codedValues.get(i)");
                            if (num.intValue() > 0) {
                                Integer num2 = entityHourlyHeartRateData.getCodedValues().get(i5);
                                Intrinsics.checkNotNullExpressionValue(num2, "hourlyHeartRate.codedValues.get(i)");
                                i4 = num2.intValue();
                                i3 = i5;
                            }
                        }
                        Date parseDate = AppUtils.parseDate(entityHourlyHeartRateData.getDate() + ' ' + entityHourlyHeartRateData.getStartTime(), "yyyy-MM-dd HH:mm:ss");
                        Calendar calendar2 = Calendar.getInstance();
                        calendar2.setTime(parseDate);
                        calendar2.add(12, size * i3);
                        liveHealthDataModel.hr = i4;
                        liveHealthDataModel.hrRecordedDate = LastDataHelper.this.getSeverTime(calendar2.getTimeInMillis());
                    }
                }
                if (BleApiManager.getInstance(LastDataHelper.this.getContext()).getBleApi().getDeviceSupportedFeatures().isBpSupported()) {
                    try {
                        entityHourlyBp = BPRepository.Companion.getInstance(LastDataHelper.this.getContext()).getLatestRecordHourly(macAddress);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        entityHourlyBp = null;
                    }
                    if (entityHourlyBp != null) {
                        int size3 = 60 / entityHourlyBp.codevalues.size();
                        int size4 = entityHourlyBp.codevalues.size();
                        int i6 = 0;
                        int i7 = 0;
                        int i8 = 0;
                        for (int i9 = 0; i9 < size4; i9++) {
                            if (entityHourlyBp.getCodevalues().get(i9).getDiastolicBp() > 0 && entityHourlyBp.getCodevalues().get(i9).getSystolicBp() > 0) {
                                int systolicBp = entityHourlyBp.getCodevalues().get(i9).getSystolicBp();
                                i7 = entityHourlyBp.getCodevalues().get(i9).getDiastolicBp();
                                i8 = systolicBp;
                                i6 = i9;
                            }
                        }
                        Date parseDate2 = AppUtils.parseDate(entityHourlyBp.date + ' ' + entityHourlyBp.getStartHour(), "yyyy-MM-dd HH:mm:ss");
                        Calendar calendar3 = Calendar.getInstance();
                        calendar3.setTime(parseDate2);
                        calendar3.add(12, size3 * i6);
                        liveHealthDataModel.bpDiastolic = i7;
                        liveHealthDataModel.bpSystolic = i8;
                        liveHealthDataModel.bpRecordedDate = LastDataHelper.this.getSeverTime(calendar3.getTimeInMillis());
                    }
                }
                if (BleApiManager.getInstance(LastDataHelper.this.getContext()).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                    try {
                        hourlyTemperature = TemperatureRepository.Companion.getInstance(LastDataHelper.this.getContext()).getLatestRecordHourly(macAddress, Float.parseFloat(Constants.MIN_FILTER_VALUE.getValue()), Float.parseFloat(Constants.MAX_FILTER_VALUE.getValue()));
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        hourlyTemperature = null;
                    }
                    if (hourlyTemperature != null) {
                        Pair b = LastDataHelper.this.b(hourlyTemperature);
                        double doubleValue = ((Number) b.getFirst()).doubleValue();
                        LiveHealthDataModel.BodyTemperatureBean bodyTemperatureBean = new LiveHealthDataModel.BodyTemperatureBean();
                        bodyTemperatureBean.value = doubleValue;
                        bodyTemperatureBean.baseUnit = WatchfaceConstants.CELSIUS;
                        bodyTemperatureBean.recordedDate = LastDataHelper.this.getSeverTime(((Calendar) b.getSecond()).getTimeInMillis());
                        liveHealthDataModel.bodyTemperature = bodyTemperatureBean;
                    }
                }
                if (BleApiManager.getInstance(LastDataHelper.this.getContext()).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                    try {
                        entityHourlySpo2 = PeriodicSpo2Repository.Companion.getInstance(LastDataHelper.this.getContext()).getLatestRecordHourly(macAddress);
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                    if (entityHourlySpo2 != null) {
                        Pair a2 = LastDataHelper.this.a(entityHourlySpo2);
                        liveHealthDataModel.spo2 = ((Number) a2.getFirst()).doubleValue();
                        liveHealthDataModel.spo2BaseUnit = "PERCENTAGE";
                        liveHealthDataModel.spo2RecordedDate = LastDataHelper.this.getSeverTime(((Calendar) a2.getSecond()).getTimeInMillis());
                    }
                } else {
                    EntityManualData lastMeasuredSpo2ByMacAddress = ManualDataRepository.Companion.getInstance(LastDataHelper.this.getContext()).getLastMeasuredSpo2ByMacAddress(BleApiManager.getInstance(LastDataHelper.this.getContext()).getBleApi().getMacAddress());
                    if (lastMeasuredSpo2ByMacAddress != null) {
                        liveHealthDataModel.spo2 = lastMeasuredSpo2ByMacAddress.getSpo2();
                        liveHealthDataModel.spo2BaseUnit = lastMeasuredSpo2ByMacAddress.isLevelInterpretation() ? "ORDINAL" : "PERCENTAGE";
                        liveHealthDataModel.spo2RecordedDate = LastDataHelper.this.getSeverTime(lastMeasuredSpo2ByMacAddress.getTimeStamp());
                    }
                }
                return liveHealthDataModel;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.response.LastDataHelper$isLevelInterpretation$2", f = "LastDataHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        public final /* synthetic */ String $date;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$date = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Boolean> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            boolean z;
            Exception e;
            List<EntityManualData> spo2DataList;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                boolean z2 = false;
                try {
                    ManualDataRepository.Companion companion = ManualDataRepository.Companion;
                    Context context = LastDataHelper.this.getContext();
                    Intrinsics.checkNotNull(context);
                    spo2DataList = companion.getInstance(context).getSpo2DataList(this.$date, null, null);
                } catch (Exception e2) {
                    z = false;
                    e = e2;
                }
                if (spo2DataList != null) {
                    if (spo2DataList.size() > 0) {
                        z = true;
                        try {
                            for (EntityManualData entityManualData : spo2DataList) {
                                if (!entityManualData.isLevelInterpretation()) {
                                    z = false;
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                            e.printStackTrace();
                            z2 = z;
                            return Boxing.boxBoolean(z2);
                        }
                        z2 = z;
                    }
                }
                return Boxing.boxBoolean(z2);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public LastDataHelper(Context context) {
        this.f5633a = context;
        String name = LastDataHelper.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "javaClass.name");
        this.b = name;
        this.c = new Gson();
    }

    public /* synthetic */ LastDataHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Pair<Double, Calendar> a(EntityHourlySpo2 entityHourlySpo2) {
        int size = 60 / entityHourlySpo2.codevalue.size();
        int size2 = entityHourlySpo2.codevalue.size();
        double d = 0.0d;
        int i = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            Integer num = entityHourlySpo2.codevalue.get(i2);
            Intrinsics.checkNotNullExpressionValue(num, "hourlySPO2.codevalue.get(i)");
            if (num.intValue() > 0) {
                d = entityHourlySpo2.codevalue.get(i2).intValue();
                i = i2;
            }
        }
        Date parseDate = AppUtils.parseDate(entityHourlySpo2.getmDate() + ' ' + entityHourlySpo2.getStartTime(), "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        calendar.add(12, size * i);
        return new Pair<>(Double.valueOf(d), calendar);
    }

    public final Pair<Double, Calendar> b(HourlyTemperature hourlyTemperature) {
        int size = 60 / hourlyTemperature.getCodevalue().size();
        int size2 = hourlyTemperature.getCodevalue().size();
        double d = 0.0d;
        int i = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            Double d2 = hourlyTemperature.getCodevalue().get(i2);
            Intrinsics.checkNotNullExpressionValue(d2, "hourlyTemperature.codevalue.get(i)");
            if (d2.doubleValue() > 0.0d) {
                Double d3 = hourlyTemperature.getCodevalue().get(i2);
                Intrinsics.checkNotNullExpressionValue(d3, "hourlyTemperature.codevalue.get(i)");
                d = d3.doubleValue();
                i = i2;
            }
        }
        Date parseDate = AppUtils.parseDate(hourlyTemperature.getmDate() + ' ' + hourlyTemperature.getStartTime(), "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        calendar.add(12, size * i);
        return new Pair<>(Double.valueOf(d), calendar);
    }

    public final LiveHealthDataModel.SleepDetailsBean c(SleepDataModel sleepDataModel) {
        if (sleepDataModel != null) {
            LiveHealthDataModel.SleepDetailsBean sleepDetailsBean = new LiveHealthDataModel.SleepDetailsBean();
            Calendar calendar = Calendar.getInstance();
            if (sleepDataModel.getSleepStartHour() != -1) {
                calendar.set(11, sleepDataModel.getSleepStartHour());
                calendar.set(12, sleepDataModel.getSleepStartMinute());
                if (sleepDataModel.getSleepStartHour() >= 12) {
                    calendar.add(6, -1);
                }
            }
            sleepDetailsBean.startDate = AppUtils.formatDateUTC(calendar.getTime(), UtilConstants.SERVER_TIME_FORMAT);
            Calendar calendar2 = Calendar.getInstance();
            if (sleepDataModel.getSleepEndHour() != -1) {
                calendar2.set(11, sleepDataModel.getSleepEndHour());
                calendar2.set(12, sleepDataModel.getSleepEndMinute());
                if (sleepDataModel.getSleepEndHour() >= 12) {
                    calendar2.add(6, -1);
                }
            }
            sleepDetailsBean.endDate = AppUtils.formatDateUTC(calendar2.getTime(), UtilConstants.SERVER_TIME_FORMAT);
            sleepDetailsBean.deepSleep = sleepDataModel.getCountOfDeepSleepMinutes();
            sleepDetailsBean.lightSleep = sleepDataModel.getCountOfLightSleepMinutes();
            sleepDetailsBean.awake = sleepDataModel.getCountOfAwakeMinutes();
            return sleepDetailsBean;
        }
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f5633a;
    }

    @NotNull
    public final Gson getGson() {
        return this.c;
    }

    @Nullable
    public final Object getLastMeasuredData(@NotNull Continuation<? super LiveHealthDataModel> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new a(null), continuation);
    }

    @NotNull
    public final Pair<Integer, Long> getLastMeasuredPeriodicStress() {
        String macAddress = BleApiManager.getInstance(this.f5633a).getBleApi().getMacAddress();
        StressRepository.Companion companion = StressRepository.Companion;
        Context context = this.f5633a;
        Intrinsics.checkNotNull(context);
        HourlyStress latestRecordHourly = companion.getInstance(context).getLatestRecordHourly(macAddress);
        if (latestRecordHourly != null) {
            int size = 60 / latestRecordHourly.getCodevalue().size();
            int size2 = latestRecordHourly.getCodevalue().size();
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < size2; i3++) {
                Integer num = latestRecordHourly.getCodevalue().get(i3);
                Intrinsics.checkNotNullExpressionValue(num, "hourlyStress.codevalue.get(i)");
                if (num.intValue() > 0) {
                    Integer num2 = latestRecordHourly.getCodevalue().get(i3);
                    Intrinsics.checkNotNullExpressionValue(num2, "hourlyStress.codevalue.get(i)");
                    i2 = num2.intValue();
                    i = i3;
                }
            }
            Date parseDate = AppUtils.parseDate(latestRecordHourly.getmDate() + ' ' + latestRecordHourly.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate);
            calendar.add(12, size * i);
            return new Pair<>(Integer.valueOf(i2), Long.valueOf(calendar.getTimeInMillis()));
        }
        return new Pair<>(null, null);
    }

    @Nullable
    public final Pair<Double, Calendar> getLastMeasuredTemperature() {
        try {
            HourlyTemperature latestRecordHourly = TemperatureRepository.Companion.getInstance(this.f5633a).getLatestRecordHourly(BleApiManager.getInstance(this.f5633a).getBleApi().getMacAddress(), Float.parseFloat(Constants.MIN_FILTER_VALUE.getValue()), Float.parseFloat(Constants.MAX_FILTER_VALUE.getValue()));
            if (latestRecordHourly != null) {
                return b(latestRecordHourly);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public final SleepDataModel getLastNightSleepData(@Nullable List<? extends SleepDataModelForLastNight> list) {
        SmaParsedSleepDataF2 smaParsedSleepDataF2;
        MoyangParsedSleepData moyangParsedSleepData;
        MatrixParsedSleepData matrixParsedSleepData;
        StrideParsedSleepDataV2NoAlgo strideParsedSleepDataV2NoAlgo;
        JStyleParsedSleepData jStyleParsedSleepData;
        JStyleSleepAlgoWithREM jStyleSleepAlgoWithREM;
        CZ0ParsedSleepData cZ0ParsedSleepData = null;
        if (AppUtils.isEmpty(list)) {
            return null;
        }
        byte[] bArr = new byte[1440];
        Arrays.fill(bArr, 0, 1440, (byte) -1);
        Intrinsics.checkNotNull(list);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int size2 = list.get(i).getCodevalue().size();
            for (int i2 = 0; i2 < size2; i2++) {
                int timeIndex = getTimeIndex(list.get(i), i2);
                if (timeIndex < 1440) {
                    Integer num = list.get(i).getCodevalue().get(i2);
                    if (num != null) {
                        Context context = this.f5633a;
                        Intrinsics.checkNotNull(context);
                        if (!RepositoryUtils.isJStyleDevice(context) && !RepositoryUtils.isSmaDevice(this.f5633a) && !RepositoryUtils.isMoyangDevice(this.f5633a) && !RepositoryUtils.isMatrixDevice(this.f5633a)) {
                            if (num.intValue() != 3) {
                                bArr[timeIndex] = (byte) num.intValue();
                            } else {
                                bArr[timeIndex] = -1;
                            }
                        } else {
                            bArr[timeIndex] = (byte) num.intValue();
                        }
                    } else {
                        bArr[timeIndex] = -1;
                    }
                }
            }
        }
        Context context2 = this.f5633a;
        Intrinsics.checkNotNull(context2);
        if (RepositoryUtils.isJStyleDevice(context2)) {
            if (BleApiManager.getInstance(this.f5633a).getBleApi().getDeviceSupportedFeatures().isREMSupportedInSleep()) {
                try {
                    jStyleSleepAlgoWithREM = new JStyleSleepAlgoWithREM(bArr, JStyleSleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                } catch (Exception e) {
                    e.printStackTrace();
                    jStyleSleepAlgoWithREM = null;
                }
                if (jStyleSleepAlgoWithREM == null || jStyleSleepAlgoWithREM.getSleepDataModel() == null) {
                    return null;
                }
                return jStyleSleepAlgoWithREM.getSleepDataModel();
            }
            try {
                jStyleParsedSleepData = new JStyleParsedSleepData(bArr, JStyleSleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e2) {
                e2.printStackTrace();
                jStyleParsedSleepData = null;
            }
            if (jStyleParsedSleepData == null || jStyleParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return jStyleParsedSleepData.getSleepDataModel();
        } else if (RepositoryUtils.isSmaDevice(this.f5633a)) {
            try {
                smaParsedSleepDataF2 = new SmaParsedSleepDataF2(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e3) {
                e3.printStackTrace();
                smaParsedSleepDataF2 = null;
            }
            if (smaParsedSleepDataF2 == null || smaParsedSleepDataF2.getSleepDataModel() == null) {
                return null;
            }
            return smaParsedSleepDataF2.getSleepDataModel();
        } else if (RepositoryUtils.isMoyangDevice(this.f5633a)) {
            try {
                moyangParsedSleepData = new MoyangParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e4) {
                e4.printStackTrace();
                moyangParsedSleepData = null;
            }
            if (moyangParsedSleepData == null || moyangParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return moyangParsedSleepData.getSleepDataModel();
        } else if (RepositoryUtils.isMatrixDevice(this.f5633a)) {
            try {
                matrixParsedSleepData = new MatrixParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e5) {
                e5.printStackTrace();
                matrixParsedSleepData = null;
            }
            if (matrixParsedSleepData == null || matrixParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return matrixParsedSleepData.getSleepDataModel();
        } else if (RepositoryUtils.isKaHaDeviceREM(this.f5633a)) {
            try {
                cZ0ParsedSleepData = new CZ0ParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e6) {
                e6.printStackTrace();
            }
            Intrinsics.checkNotNull(cZ0ParsedSleepData);
            return cZ0ParsedSleepData.getSleepDataModel();
        } else {
            try {
                strideParsedSleepDataV2NoAlgo = new StrideParsedSleepDataV2NoAlgo(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e7) {
                e7.printStackTrace();
                strideParsedSleepDataV2NoAlgo = null;
            }
            if (strideParsedSleepDataV2NoAlgo == null || strideParsedSleepDataV2NoAlgo.getSleepDataModel() == null) {
                return null;
            }
            return strideParsedSleepDataV2NoAlgo.getSleepDataModel();
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    @NotNull
    public final String getSeverTime(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = simpleDateFormat.format(new Date(j));
        Intrinsics.checkNotNullExpressionValue(format, "sdf.format(date)");
        return format;
    }

    public final int getTimeIndex(@NotNull SleepDataModelForLastNight sleepDataModelForLastNight, int i) {
        int parseInt;
        int parseInt2;
        Intrinsics.checkNotNullParameter(sleepDataModelForLastNight, "sleepDataModelForLastNight");
        String startTime = sleepDataModelForLastNight.getStartTime();
        Intrinsics.checkNotNullExpressionValue(startTime, "sleepDataModelForLastNight.startTime");
        String[] strArr = (String[]) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
        if (strArr.length > 1) {
            if (Integer.parseInt(strArr[0]) < 12) {
                parseInt = (Integer.parseInt(strArr[0]) + 12) * 60;
                parseInt2 = Integer.parseInt(strArr[1]);
            } else {
                parseInt = (Integer.parseInt(strArr[0]) - 12) * 60;
                parseInt2 = Integer.parseInt(strArr[1]);
            }
            return parseInt + parseInt2 + i;
        }
        return -1;
    }

    @Nullable
    public final Object isLevelInterpretation(@NotNull String str, @NotNull Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new b(str, null), continuation);
    }

    public final void sendSleepDataToBand(@NotNull final UploadCompletionListner listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        String macAddress = BleApiManager.getInstance(this.f5633a).getBleApi().getMacAddress();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        SleepDataModel lastNightSleepData = getLastNightSleepData(SleepRepository.Companion.getInstance(this.f5633a).getLastNignthSleepDataWithOutLiveData(calendar, macAddress));
        if (lastNightSleepData != null) {
            SendSleepDataRequest sendSleepDataRequest = new SendSleepDataRequest(lastNightSleepData.getCountTotalSleep(), lastNightSleepData.getCountOfDeepSleepMinutes(), lastNightSleepData.getCountOfLightSleepMinutes());
            String str = this.b;
            LogHelper.d(str, "SendSleepDataRequest{totalSleep=" + sendSleepDataRequest.getTotalSleep() + ", totalDeepSleep=" + sendSleepDataRequest.getTotalDeepSleep() + ", totalLightSleep=" + sendSleepDataRequest.getTotalLightSleep() + '}');
            if (BleApiManager.getInstance(this.f5633a).getDeviceType() != DeviceType.CZ0) {
                BleApiManager.getInstance(this.f5633a).getBleApi().setUserSettings(sendSleepDataRequest, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.response.LastDataHelper$sendSleepDataToBand$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        String str2;
                        Intrinsics.checkNotNullParameter(error, "error");
                        str2 = LastDataHelper.this.b;
                        LogHelper.e(str2, error.getErrorMsg());
                        listner.onUploadFailed();
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        String str2;
                        Intrinsics.checkNotNullParameter(response, "response");
                        str2 = LastDataHelper.this.b;
                        LogHelper.d(str2, "Sleep data sent back to watch");
                        listner.onDataUploadeComplete();
                    }
                });
                return;
            }
            return;
        }
        LogHelper.d(this.b, "No Sleep data to upload to watch");
        listner.onDataUploadeComplete();
    }
}
