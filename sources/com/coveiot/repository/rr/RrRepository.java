package com.coveiot.repository.rr;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.covedb.rr.entity.EntityDailyRrData;
import com.coveiot.covedb.rr.entity.EntityHourlyRrData;
import com.coveiot.covedb.rr.model.MonthlyRrData;
import com.coveiot.covedb.rr.model.WeeklyRrData;
import com.coveiot.repository.ActivityType;
import com.coveiot.repository.FlowValidator;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.ResponseResult;
import com.coveiot.repository.SingletonHolder;
import com.coveiot.repository.rr.datasources.db.read.RrDbRead;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class RrRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<ResponseResult> f7426a;
    public Context b;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<RrRepository, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, RrRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, RrRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final RrRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new RrRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RrRepository(Context context) {
        this.f7426a = new MutableLiveData<>();
        this.b = context;
    }

    public /* synthetic */ RrRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final LiveData<EntityDailyRrData> getDailyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.RR_HISTORY;
        MutableLiveData<ResponseResult> mutableLiveData = this.f7426a;
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
        LiveData<EntityDailyRrData> liveDailyRrData = RrDbRead.getInstance(context2).getLiveDailyRrData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyRrData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyRrData;
    }

    @NotNull
    public final LiveData<EntityDailyRrData> getDailyDataWithoutFlowValidator(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<EntityDailyRrData> liveDailyRrData = RrDbRead.getInstance(context).getLiveDailyRrData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyRrData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyRrData;
    }

    @NotNull
    public final LiveData<List<EntityDailyRrData>> getDayWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.RR_HISTORY;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7426a;
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
        LiveData<List<EntityDailyRrData>> dailyRrByMacAddress = RrDbRead.getInstance(context2).getDailyRrByMacAddress(serial_no);
        Intrinsics.checkNotNullExpressionValue(dailyRrByMacAddress, "getInstance(context)\n   …RrByMacAddress(serial_no)");
        return dailyRrByMacAddress;
    }

    @NotNull
    public final LiveData<List<EntityHourlyRrData>> getHourlyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<List<EntityHourlyRrData>> rrData = RrDbRead.getInstance(context).getRrData(formatDate, serial_no);
        Intrinsics.checkNotNullExpressionValue(rrData, "getInstance(context)\n   …tRrData(time1, serial_no)");
        return rrData;
    }

    @Nullable
    public final LiveData<List<MonthlyRrData>> getMonthWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.RR_HISTORY;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7426a;
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
        return RrDbRead.getInstance(context2).getMonthWiseRrHistory(serial_no);
    }

    @Nullable
    public final LiveData<List<WeeklyRrData>> getWeekWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.RR_HISTORY;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7426a;
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
        return RrDbRead.getInstance(context2).getWeekWiseRrHistory(serial_no);
    }

    public final void setResponseResult(@NotNull MutableLiveData<ResponseResult> responseResult) {
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
        this.f7426a = responseResult;
    }
}
