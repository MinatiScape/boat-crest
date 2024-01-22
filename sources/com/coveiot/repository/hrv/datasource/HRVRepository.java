package com.coveiot.repository.hrv.datasource;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.covedb.hrv.model.MonthlyHRVData;
import com.coveiot.covedb.hrv.model.WeeklyHRVData;
import com.coveiot.repository.ActivityType;
import com.coveiot.repository.FlowValidator;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.ResponseResult;
import com.coveiot.repository.SingletonHolder;
import com.coveiot.repository.hrv.datasource.db.read.HRVDBRead;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class HRVRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<ResponseResult> f7400a;
    public Context b;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<HRVRepository, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, HRVRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, HRVRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final HRVRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new HRVRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public HRVRepository(Context context) {
        this.f7400a = new MutableLiveData<>();
        this.b = context;
    }

    public /* synthetic */ HRVRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final LiveData<DailyHRV> getDailyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.TEMPERATURE;
        MutableLiveData<ResponseResult> mutableLiveData = this.f7400a;
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
        LiveData<DailyHRV> liveDailyHRVData = HRVDBRead.getInstance(context2).getLiveDailyHRVData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyHRVData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyHRVData;
    }

    @NotNull
    public final LiveData<DailyHRV> getDailyDataWithoutFlowValidator(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<DailyHRV> liveDailyHRVData = HRVDBRead.getInstance(context).getLiveDailyHRVData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyHRVData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyHRVData;
    }

    @Nullable
    public final DailyHRV getDailyHrvData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HRVDBRead.getInstance(context).getDailyHRVData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
    }

    @NotNull
    public final LiveData<List<DailyHRV>> getDayWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.TEMPERATURE;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7400a;
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
        LiveData<List<DailyHRV>> dailyHRVDataByMacAddress = HRVDBRead.getInstance(context2).getDailyHRVDataByMacAddress(serial_no);
        Intrinsics.checkNotNullExpressionValue(dailyHRVDataByMacAddress, "getInstance(context)\n   …taByMacAddress(serial_no)");
        return dailyHRVDataByMacAddress;
    }

    @NotNull
    public final LiveData<List<HourlyHRV>> getHourlyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<List<HourlyHRV>> hourlyHRVDataLiveData = HRVDBRead.getInstance(context).getHourlyHRVDataLiveData(formatDate, serial_no);
        Intrinsics.checkNotNullExpressionValue(hourlyHRVDataLiveData, "getInstance(context)\n   …iveData(time1, serial_no)");
        return hourlyHRVDataLiveData;
    }

    @Nullable
    public final List<HourlyHRV> getHourlyHRVDataFrom(@NotNull String time, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HRVDBRead.getInstance(context).getHourlyHRVDataFrom(time, serialNo);
    }

    @Nullable
    public final HourlyHRV getLatestHRVRecordHourly(@NotNull Calendar calendar, @NotNull String serial_no, float f, float f2) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HRVDBRead.getInstance(context).getLatestHRVRecordHourly(formatDate, serial_no, f, f2);
    }

    @Nullable
    public final HourlyHRV getLatestHighHRVRecordHourly(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HRVDBRead.getInstance(context).getLatestHighHRVRecordHourly(formatDate, serial_no);
    }

    @Nullable
    public final HourlyHRV getLatestRecordHourly(@NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HRVDBRead.getInstance(context).getLatestRecordHourly(serial_no);
    }

    @Nullable
    public final LiveData<List<MonthlyHRVData>> getMonthWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.TEMPERATURE;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7400a;
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
        return HRVDBRead.getInstance(context2).getMonthWiseHRVHistory(serial_no);
    }

    @Nullable
    public final LiveData<List<WeeklyHRVData>> getWeekWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.TEMPERATURE;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7400a;
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
        return HRVDBRead.getInstance(context2).getWeekWiseHRVHistory(serial_no);
    }

    public final void setResponseResult(@NotNull MutableLiveData<ResponseResult> responseResult) {
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
        this.f7400a = responseResult;
    }

    @Nullable
    public final HourlyHRV getLatestRecordHourly(@NotNull String serial_no, float f, float f2) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HRVDBRead.getInstance(context).getLatestRecordHourly(serial_no, f, f2);
    }

    @Nullable
    public final HourlyHRV getLatestHRVRecordHourly(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return HRVDBRead.getInstance(context).getLatestHRVRecordHourly(formatDate, serial_no);
    }
}
