package com.coveiot.repository.stress;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.covedb.stress.model.MonthlyStressData;
import com.coveiot.covedb.stress.model.WeeklyStressData;
import com.coveiot.repository.ActivityType;
import com.coveiot.repository.FlowValidator;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.ResponseResult;
import com.coveiot.repository.SingletonHolder;
import com.coveiot.repository.stress.datasources.db.read.StressDBRead;
import com.coveiot.repository.stress.datasources.db.write.StressDBWrite;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class StressRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<ResponseResult> f7443a;
    public Context b;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<StressRepository, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, StressRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, StressRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final StressRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new StressRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public StressRepository(Context context) {
        this.f7443a = new MutableLiveData<>();
        this.b = context;
    }

    public /* synthetic */ StressRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final LiveData<DailyStress> getDailyData(@NotNull Calendar calendar, @NotNull String serial_no) {
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
        MutableLiveData<ResponseResult> mutableLiveData = this.f7443a;
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
        LiveData<DailyStress> liveDailyStressData = StressDBRead.getInstance(context2).getLiveDailyStressData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyStressData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyStressData;
    }

    @NotNull
    public final LiveData<DailyStress> getDailyDataWithoutFlowValidator(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<DailyStress> liveDailyStressData = StressDBRead.getInstance(context).getLiveDailyStressData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyStressData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyStressData;
    }

    @Nullable
    public final DailyStress getDailyStressData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return StressDBRead.getInstance(context).getDailyStressData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
    }

    @NotNull
    public final LiveData<List<DailyStress>> getDayWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7443a;
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
        LiveData<List<DailyStress>> dailyStressDataByMacAddress = StressDBRead.getInstance(context2).getDailyStressDataByMacAddress(serial_no);
        Intrinsics.checkNotNullExpressionValue(dailyStressDataByMacAddress, "getInstance(context)\n   …taByMacAddress(serial_no)");
        return dailyStressDataByMacAddress;
    }

    @NotNull
    public final LiveData<List<HourlyStress>> getHourlyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<List<HourlyStress>> hourlyStressDataLiveData = StressDBRead.getInstance(context).getHourlyStressDataLiveData(formatDate, serial_no);
        Intrinsics.checkNotNullExpressionValue(hourlyStressDataLiveData, "getInstance(context)\n   …iveData(time1, serial_no)");
        return hourlyStressDataLiveData;
    }

    @Nullable
    public final List<HourlyStress> getHourlyStressDataFrom(@NotNull String time, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return StressDBRead.getInstance(context).getHourlyStressDataFrom(time, serialNo);
    }

    @Nullable
    public final HourlyStress getLatestHighStressRecordHourly(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return StressDBRead.getInstance(context).getLatestHighStressRecordHourly(formatDate, serial_no);
    }

    @Nullable
    public final HourlyStress getLatestRecordHourly(@NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return StressDBRead.getInstance(context).getLatestRecordHourly(serial_no);
    }

    @NotNull
    public final LiveData<HourlyStress> getLatestRecordHourlyLiveData(@NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<HourlyStress> latestRecordHourlyLiveData = StressDBRead.getInstance(context).getLatestRecordHourlyLiveData(serial_no);
        Intrinsics.checkNotNullExpressionValue(latestRecordHourlyLiveData, "getInstance(context).get…HourlyLiveData(serial_no)");
        return latestRecordHourlyLiveData;
    }

    @Nullable
    public final HourlyStress getLatestStressRecordHourly(@NotNull Calendar calendar, @NotNull String serial_no, float f, float f2) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return StressDBRead.getInstance(context).getLatestStressRecordHourly(formatDate, serial_no, f, f2);
    }

    @Nullable
    public final LiveData<List<MonthlyStressData>> getMonthWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7443a;
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
        return StressDBRead.getInstance(context2).getMonthWiseStressHistory(serial_no);
    }

    @Nullable
    public final LiveData<List<WeeklyStressData>> getWeekWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7443a;
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
        return StressDBRead.getInstance(context2).getWeekWiseStressHistory(serial_no);
    }

    public final void setResponseResult(@NotNull MutableLiveData<ResponseResult> responseResult) {
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
        this.f7443a = responseResult;
    }

    public final void updateAnsweredQuestionsList(@NotNull Calendar calendar, @NotNull String serial_no, @NotNull ArrayList<String> questionsIDList) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Intrinsics.checkNotNullParameter(questionsIDList, "questionsIDList");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        DailyStress dailyStressData = StressDBRead.getInstance(context).getDailyStressData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        if (dailyStressData != null) {
            dailyStressData.AnsweredQuestions_FeedBack = questionsIDList;
        }
        Context context3 = this.b;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context3;
        }
        StressDBWrite.getInstance(context2).insertStress(dailyStressData);
    }

    @Nullable
    public final HourlyStress getLatestRecordHourly(@NotNull String serial_no, float f, float f2) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return StressDBRead.getInstance(context).getLatestRecordHourly(serial_no, f, f2);
    }

    @Nullable
    public final HourlyStress getLatestStressRecordHourly(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return StressDBRead.getInstance(context).getLatestStressRecordHourly(formatDate, serial_no);
    }
}
