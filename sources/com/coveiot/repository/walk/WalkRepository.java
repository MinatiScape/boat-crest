package com.coveiot.repository.walk;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.covedb.walk.model.StepsDataModelMonthWiseCommon;
import com.coveiot.covedb.walk.model.StepsDataModelWeekWiseCommon;
import com.coveiot.repository.ActivityType;
import com.coveiot.repository.FlowValidator;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.ResponseResult;
import com.coveiot.repository.SingletonHolder;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class WalkRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<ResponseResult> f7455a;
    public Context b;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<WalkRepository, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, WalkRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, WalkRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final WalkRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new WalkRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public WalkRepository(Context context) {
        this.f7455a = new MutableLiveData<>();
        this.b = context;
    }

    public /* synthetic */ WalkRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final LiveData<DailyWalkData> getDailyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.WALK;
        MutableLiveData<ResponseResult> mutableLiveData = this.f7455a;
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
        LiveData<DailyWalkData> liveDailyWalkData = WalkDBRead.getInstance(context2).getLiveDailyWalkData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyWalkData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyWalkData;
    }

    @NotNull
    public final LiveData<DailyWalkData> getDailyDataWithoutFlowValidator(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<DailyWalkData> liveDailyWalkData = WalkDBRead.getInstance(context).getLiveDailyWalkData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyWalkData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyWalkData;
    }

    @Nullable
    public final DailyWalkData getDailyWalkData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return WalkDBRead.getInstance(context).getDailyWalkData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
    }

    @NotNull
    public final LiveData<List<DailyWalkData>> getDayWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.WALK;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7455a;
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
        LiveData<List<DailyWalkData>> liveTotalDailyWalkDataLive = WalkDBRead.getInstance(context2).getLiveTotalDailyWalkDataLive(serial_no);
        Intrinsics.checkNotNullExpressionValue(liveTotalDailyWalkDataLive, "getInstance(context)\n   …lyWalkDataLive(serial_no)");
        return liveTotalDailyWalkDataLive;
    }

    @NotNull
    public final LiveData<List<HourlyWalkData>> getHourlyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<List<HourlyWalkData>> liveHourlyWalkDataLive = WalkDBRead.getInstance(context).getLiveHourlyWalkDataLive(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveHourlyWalkDataLive, "getInstance(context)\n   …  serial_no\n            )");
        return liveHourlyWalkDataLive;
    }

    @NotNull
    public final LiveData<List<HourlyWalkData>> getHourlyDataBetweenDates(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<List<HourlyWalkData>> walkDataBetweenDates = WalkDBRead.getInstance(context).getWalkDataBetweenDates(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(walkDataBetweenDates, "getInstance(context)\n   …  serial_no\n            )");
        return walkDataBetweenDates;
    }

    @Nullable
    public final HourlyWalkData getHourlyLastRecordFor(@NotNull String date, @NotNull String serail_no) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(serail_no, "serail_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return WalkDBRead.getInstance(context).getHourlyLastRecordFor(date, serail_no);
    }

    @Nullable
    public final DailyWalkData getLastDayData(@NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return WalkDBRead.getInstance(context).getLastDayData(serialNo);
    }

    @Nullable
    public final DailyWalkData getLastDayDataWithValue(@NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return WalkDBRead.getInstance(context).getLastDayDataWithValue(serial_no);
    }

    @Nullable
    public final HourlyWalkData getLatestRecordHourly(@NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return WalkDBRead.getInstance(context).getLatestRecordHourly(serial_no);
    }

    @Nullable
    public final LiveData<List<StepsDataModelMonthWiseCommon>> getMonthWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.WALK;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7455a;
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
        return WalkDBRead.getInstance(context2).getLiveStepsDataMonthWiseCommon(serial_no);
    }

    @Nullable
    public final LiveData<List<StepsDataModelWeekWiseCommon>> getWeekWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.WALK;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7455a;
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
        return WalkDBRead.getInstance(context2).getLiveStepsDataWeekWiseCommon(serial_no);
    }

    public final void setResponseResult(@NotNull MutableLiveData<ResponseResult> responseResult) {
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
        this.f7455a = responseResult;
    }
}
