package com.coveiot.repository.heartrate;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.heartrate.model.MonthlyHeartRateData;
import com.coveiot.covedb.heartrate.model.WeeklyHeartRateData;
import com.coveiot.repository.ActivityType;
import com.coveiot.repository.FlowValidator;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.ResponseResult;
import com.coveiot.repository.SingletonHolder;
import com.coveiot.repository.heartrate.datasources.db.read.HeartRateDBRead;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class HeartRateRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<ResponseResult> f7394a;
    public Context b;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<HeartRateRepository, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, HeartRateRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, HeartRateRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final HeartRateRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new HeartRateRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public HeartRateRepository(Context context) {
        this.f7394a = new MutableLiveData<>();
        this.b = context;
    }

    public /* synthetic */ HeartRateRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final LiveData<EntityDailyHeartRateData> getDailyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.HEARTRATE;
        MutableLiveData<ResponseResult> mutableLiveData = this.f7394a;
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
        LiveData<EntityDailyHeartRateData> liveDailyHeartRateData = HeartRateDBRead.getInstance(context2).getLiveDailyHeartRateData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyHeartRateData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyHeartRateData;
    }

    @NotNull
    public final LiveData<EntityDailyHeartRateData> getDailyDataWithoutFlowValidator(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<EntityDailyHeartRateData> liveDailyHeartRateData = HeartRateDBRead.getInstance(context).getLiveDailyHeartRateData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyHeartRateData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyHeartRateData;
    }

    @Nullable
    public final EntityDailyHeartRateData getDailyHeartRateData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HeartRateDBRead.getInstance(context).getDailyHeartRateData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
    }

    @NotNull
    public final LiveData<List<EntityDailyHeartRateData>> getDayWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.HEARTRATE;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7394a;
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
        LiveData<List<EntityDailyHeartRateData>> dailyHearRateDataByMacAddress = HeartRateDBRead.getInstance(context2).getDailyHearRateDataByMacAddress(serial_no);
        Intrinsics.checkNotNullExpressionValue(dailyHearRateDataByMacAddress, "getInstance(context)\n   …taByMacAddress(serial_no)");
        return dailyHearRateDataByMacAddress;
    }

    @NotNull
    public final LiveData<List<EntityHourlyHeartRateData>> getHourlyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<List<EntityHourlyHeartRateData>> hearRateData = HeartRateDBRead.getInstance(context).getHearRateData(formatDate, serial_no);
        Intrinsics.checkNotNullExpressionValue(hearRateData, "getInstance(context)\n   …ateData(time1, serial_no)");
        return hearRateData;
    }

    @Nullable
    public final List<EntityHourlyHeartRateData> getHourlyHeartRateDataFrom(@NotNull String time, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HeartRateDBRead.getInstance(context).getHourlyHeartRateDataFrom(time, serialNo);
    }

    @Nullable
    public final LiveData<EntityHourlyHeartRateData> getLastReadHeartRate(@NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HeartRateDBRead.getInstance(context).getLastReadHeartRateData(serial_no);
    }

    @Nullable
    public final EntityHourlyHeartRateData getLatestRecordHourly(@NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HeartRateDBRead.getInstance(context).getLatestRecordHourly(serial_no);
    }

    @Nullable
    public final LiveData<List<MonthlyHeartRateData>> getMonthWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.HEARTRATE;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7394a;
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
        return HeartRateDBRead.getInstance(context2).getMonthWiseHeartRateHistory(serial_no);
    }

    @Nullable
    public final LiveData<List<WeeklyHeartRateData>> getWeekWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.HEARTRATE;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7394a;
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
        return HeartRateDBRead.getInstance(context2).getWeekWiseHeartRateHistory(serial_no);
    }

    public final void setResponseResult(@NotNull MutableLiveData<ResponseResult> responseResult) {
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
        this.f7394a = responseResult;
    }

    @Nullable
    public final EntityHourlyHeartRateData getLatestRecordHourly(@NotNull String serial_no, @NotNull String date) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Intrinsics.checkNotNullParameter(date, "date");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HeartRateDBRead.getInstance(context).getLatestRecordHourly(serial_no, date);
    }
}
