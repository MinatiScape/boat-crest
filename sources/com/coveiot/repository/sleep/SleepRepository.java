package com.coveiot.repository.sleep;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.DailySleepDataAlias;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covedb.sleep.model.SleepDataModelMonthWiseCommon;
import com.coveiot.covedb.sleep.model.SleepDataModelWeekWiseCommon;
import com.coveiot.repository.ActivityType;
import com.coveiot.repository.FlowValidator;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.ResponseResult;
import com.coveiot.repository.SingletonHolder;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class SleepRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<ResponseResult> f7436a;
    public Context b;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<SleepRepository, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, SleepRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, SleepRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final SleepRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SleepRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SleepRepository(Context context) {
        this.f7436a = new MutableLiveData<>();
        this.b = context;
    }

    public /* synthetic */ SleepRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final LiveData<DailySleepDataAlias> getDailyLastNightData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.SLEEP;
        MutableLiveData<ResponseResult> mutableLiveData = this.f7436a;
        if (mutableLiveData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("responseResult");
            mutableLiveData = null;
        }
        flowValidator.validateAndProceed(calendar, serial_no, activityType, mutableLiveData);
        Context context3 = this.b;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context3;
        }
        LiveData<DailySleepDataAlias> liveLastNightSleepData = SleepDBRead.getInstance(context2).getLiveLastNightSleepData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveLastNightSleepData, "getInstance(context)\n   …  serial_no\n            )");
        return liveLastNightSleepData;
    }

    @NotNull
    public final LiveData<List<DailySleepDataAlias>> getDayWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
        MutableLiveData<ResponseResult> mutableLiveData;
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.SLEEP;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7436a;
        if (mutableLiveData2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("responseResult");
            mutableLiveData = null;
        } else {
            mutableLiveData = mutableLiveData2;
        }
        flowValidator.validateAndProceed(fromDate, toDate, serial_no, activityType, mutableLiveData);
        Context context3 = this.b;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context3;
        }
        LiveData<List<DailySleepDataAlias>> liveDailyLastNightData = SleepDBRead.getInstance(context2).getLiveDailyLastNightData(serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyLastNightData, "getInstance(context)\n   …yLastNightData(serial_no)");
        return liveDailyLastNightData;
    }

    @NotNull
    public final LiveData<List<DailySleepDataAlias>> getDayWiseListWithOutFlowValidator(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<List<DailySleepDataAlias>> liveDailyLastNightData = SleepDBRead.getInstance(context).getLiveDailyLastNightData(serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyLastNightData, "getInstance(context)\n   …yLastNightData(serial_no)");
        return liveDailyLastNightData;
    }

    @NotNull
    public final LiveData<List<SleepDataModelForLastNight>> getHourlyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Calendar calendar2;
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.SLEEP;
        MutableLiveData<ResponseResult> mutableLiveData = this.f7436a;
        if (mutableLiveData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("responseResult");
            mutableLiveData = null;
        }
        flowValidator.validateAndProceed(calendar, serial_no, activityType, mutableLiveData);
        Object clone = calendar.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        ((Calendar) clone).add(6, -1);
        String str = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd") + " 11:00:00";
        String str2 = RepositoryUtils.formatDate(calendar2.getTime(), "yyyy-MM-dd") + " 12:00:00";
        Context context3 = this.b;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context3;
        }
        LiveData<List<SleepDataModelForLastNight>> liveLastNightDataHourly = SleepDBRead.getInstance(context2).getLiveLastNightDataHourly(str2, str, serial_no);
        Intrinsics.checkNotNullExpressionValue(liveLastNightDataHourly, "getInstance(context)\n   …(time2, time1, serial_no)");
        return liveLastNightDataHourly;
    }

    @NotNull
    public final LiveData<List<SleepDataModelForLastNight>> getHourlyDataWithoutFlowValidator(@NotNull Calendar calendar, @NotNull String serial_no) {
        Calendar calendar2;
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Object clone = calendar.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        ((Calendar) clone).add(6, -1);
        String str = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd") + " 11:00:00";
        String str2 = RepositoryUtils.formatDate(calendar2.getTime(), "yyyy-MM-dd") + " 12:00:00";
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<List<SleepDataModelForLastNight>> liveLastNightDataHourly = SleepDBRead.getInstance(context).getLiveLastNightDataHourly(str2, str, serial_no);
        Intrinsics.checkNotNullExpressionValue(liveLastNightDataHourly, "getInstance(context)\n   …(time2, time1, serial_no)");
        return liveLastNightDataHourly;
    }

    @Nullable
    public final List<SleepDataModelForLastNight> getLastNignthSleepDataWithOutLiveData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Calendar calendar2;
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Object clone = calendar.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        ((Calendar) clone).add(6, -1);
        String str = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd") + " 11:00:00";
        String str2 = RepositoryUtils.formatDate(calendar2.getTime(), "yyyy-MM-dd") + " 12:00:00";
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return SleepDBRead.getInstance(context).getLastNightDataHourly(str2, str, serial_no);
    }

    @Nullable
    public final HourlySleepData getLatestRecordHourly(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return SleepDBRead.getInstance(context).getLatestRecordHourly(macAddress);
    }

    @Nullable
    public final LiveData<List<SleepDataModelMonthWiseCommon>> getMonthWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
        MutableLiveData<ResponseResult> mutableLiveData;
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.SLEEP;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7436a;
        if (mutableLiveData2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("responseResult");
            mutableLiveData = null;
        } else {
            mutableLiveData = mutableLiveData2;
        }
        flowValidator.validateAndProceed(fromDate, toDate, serial_no, activityType, mutableLiveData);
        Context context3 = this.b;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context3;
        }
        return SleepDBRead.getInstance(context2).getLiveMonthWiseData(serial_no);
    }

    public final List<HourlySleepData> getRemSleepListBetweenDates(@NotNull String startDate, @NotNull String endDate, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return SleepDBRead.getInstance(context).getSleepDataBetweenDates(startDate, endDate, serialNo);
    }

    @NotNull
    public final List<DailySleepData> getTotalSleepData(@NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        List<DailySleepData> totalData = SleepDBRead.getInstance(context).getTotalData(serialNo);
        Intrinsics.checkNotNullExpressionValue(totalData, "getInstance(context).getTotalData(serialNo)");
        return totalData;
    }

    @Nullable
    public final LiveData<List<SleepDataModelWeekWiseCommon>> getWeekWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
        MutableLiveData<ResponseResult> mutableLiveData;
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.SLEEP;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7436a;
        if (mutableLiveData2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("responseResult");
            mutableLiveData = null;
        } else {
            mutableLiveData = mutableLiveData2;
        }
        flowValidator.validateAndProceed(fromDate, toDate, serial_no, activityType, mutableLiveData);
        Context context3 = this.b;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context3;
        }
        return SleepDBRead.getInstance(context2).getLiveWeekWiseData(serial_no);
    }

    public final void setResponseResult(@NotNull MutableLiveData<ResponseResult> responseResult) {
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
        this.f7436a = responseResult;
    }
}
