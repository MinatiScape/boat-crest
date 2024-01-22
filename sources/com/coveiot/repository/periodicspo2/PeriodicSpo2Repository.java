package com.coveiot.repository.periodicspo2;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covedb.spo2.model.MonthlySpo2Data;
import com.coveiot.covedb.spo2.model.WeeklySpo2Data;
import com.coveiot.repository.ActivityType;
import com.coveiot.repository.FlowValidator;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.ResponseResult;
import com.coveiot.repository.SingletonHolder;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class PeriodicSpo2Repository {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<ResponseResult> f7418a;
    public Context b;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<PeriodicSpo2Repository, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, PeriodicSpo2Repository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, PeriodicSpo2Repository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final PeriodicSpo2Repository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new PeriodicSpo2Repository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PeriodicSpo2Repository(Context context) {
        this.f7418a = new MutableLiveData<>();
        this.b = context;
    }

    public /* synthetic */ PeriodicSpo2Repository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final LiveData<DailyPeriodicSpo2> getDailyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.SPO2;
        MutableLiveData<ResponseResult> mutableLiveData = this.f7418a;
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
        LiveData<DailyPeriodicSpo2> liveDailyPeriodicSpo2Data = PeriodicSpo2DBRead.getInstance(context2).getLiveDailyPeriodicSpo2Data(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyPeriodicSpo2Data, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyPeriodicSpo2Data;
    }

    @NotNull
    public final LiveData<DailyPeriodicSpo2> getDailyDataWithoutFlowValidator(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<DailyPeriodicSpo2> liveDailyPeriodicSpo2Data = PeriodicSpo2DBRead.getInstance(context).getLiveDailyPeriodicSpo2Data(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyPeriodicSpo2Data, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyPeriodicSpo2Data;
    }

    @Nullable
    public final DailyPeriodicSpo2 getDailySpo2Data(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return PeriodicSpo2DBRead.getInstance(context).getDailyPeriodicSpo2Data(formatDate, serial_no);
    }

    @NotNull
    public final LiveData<List<DailyPeriodicSpo2>> getDayWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.SPO2;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7418a;
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
        LiveData<List<DailyPeriodicSpo2>> dailySpo2DataByMacAddress = PeriodicSpo2DBRead.getInstance(context2).getDailySpo2DataByMacAddress(serial_no);
        Intrinsics.checkNotNullExpressionValue(dailySpo2DataByMacAddress, "getInstance(context)\n   …taByMacAddress(serial_no)");
        return dailySpo2DataByMacAddress;
    }

    @NotNull
    public final LiveData<List<EntityHourlySpo2>> getHourlyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<List<EntityHourlySpo2>> hourlySpo2DataLiveData = PeriodicSpo2DBRead.getInstance(context).getHourlySpo2DataLiveData(formatDate, serial_no);
        Intrinsics.checkNotNullExpressionValue(hourlySpo2DataLiveData, "getInstance(context)\n   …iveData(time1, serial_no)");
        return hourlySpo2DataLiveData;
    }

    @Nullable
    public final List<EntityHourlySpo2> getHourlySpo2DataFrom(@NotNull String time, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return PeriodicSpo2DBRead.getInstance(context).getHourlySpo2DataFrom(time, serialNo);
    }

    @Nullable
    public final EntityHourlySpo2 getLatestHighSpo2RecordHourly(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return PeriodicSpo2DBRead.getInstance(context).getLatestHighSpo2RecordHourly(formatDate, serial_no);
    }

    @Nullable
    public final EntityHourlySpo2 getLatestRecordHourly(@NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return PeriodicSpo2DBRead.getInstance(context).getLatestRecordHourly(serial_no);
    }

    @Nullable
    public final EntityHourlySpo2 getLatestSpo2RecordHourly(@NotNull Calendar calendar, @NotNull String serial_no, float f, float f2) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return PeriodicSpo2DBRead.getInstance(context).getLatestSpo2RecordHourly(formatDate, serial_no, f, f2);
    }

    @Nullable
    public final LiveData<List<MonthlySpo2Data>> getMonthWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.SPO2;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7418a;
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
        return PeriodicSpo2DBRead.getInstance(context2).getMonthWiseSpo2History(serial_no);
    }

    @Nullable
    public final LiveData<List<WeeklySpo2Data>> getWeekWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.SPO2;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7418a;
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
        return PeriodicSpo2DBRead.getInstance(context2).getWeekWiseSpo2History(serial_no);
    }

    public final void setResponseResult(@NotNull MutableLiveData<ResponseResult> responseResult) {
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
        this.f7418a = responseResult;
    }

    @Nullable
    public final EntityHourlySpo2 getLatestRecordHourly(@NotNull String serial_no, float f, float f2) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return PeriodicSpo2DBRead.getInstance(context).getLatestRecordHourly(serial_no, f, f2);
    }

    @Nullable
    public final EntityHourlySpo2 getLatestSpo2RecordHourly(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return PeriodicSpo2DBRead.getInstance(context).getLatestSpo2RecordHourly(formatDate, serial_no);
    }
}
