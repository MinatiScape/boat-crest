package com.coveiot.repository.ambientsound;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData;
import com.coveiot.covedb.ambientsound.model.MonthlyAmbientSoundData;
import com.coveiot.covedb.ambientsound.model.WeeklyAmbientSoundData;
import com.coveiot.repository.ActivityType;
import com.coveiot.repository.FlowValidator;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.ResponseResult;
import com.coveiot.repository.SingletonHolder;
import com.coveiot.repository.ambientsound.datasources.db.read.AmbientSoundDBRead;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class AmbientSoundRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<ResponseResult> f7308a;
    public Context b;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<AmbientSoundRepository, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, AmbientSoundRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, AmbientSoundRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final AmbientSoundRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new AmbientSoundRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AmbientSoundRepository(Context context) {
        this.f7308a = new MutableLiveData<>();
        this.b = context;
    }

    public /* synthetic */ AmbientSoundRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final LiveData<EntityDailyAmbientSoundData> getDailyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        FlowValidator flowValidator = new FlowValidator(context);
        ActivityType activityType = ActivityType.AMBIENT_SOUND;
        MutableLiveData<ResponseResult> mutableLiveData = this.f7308a;
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
        LiveData<EntityDailyAmbientSoundData> liveDailyAmbientSoundData = AmbientSoundDBRead.getInstance(context2).getLiveDailyAmbientSoundData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyAmbientSoundData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyAmbientSoundData;
    }

    @NotNull
    public final LiveData<EntityDailyAmbientSoundData> getDailyDataWithoutFlowValidator(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<EntityDailyAmbientSoundData> liveDailyAmbientSoundData = AmbientSoundDBRead.getInstance(context).getLiveDailyAmbientSoundData(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), serial_no);
        Intrinsics.checkNotNullExpressionValue(liveDailyAmbientSoundData, "getInstance(context)\n   …  serial_no\n            )");
        return liveDailyAmbientSoundData;
    }

    @NotNull
    public final LiveData<List<EntityDailyAmbientSoundData>> getDayWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.AMBIENT_SOUND;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7308a;
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
        LiveData<List<EntityDailyAmbientSoundData>> dailyAmbientSoundDataByMacAddress = AmbientSoundDBRead.getInstance(context2).getDailyAmbientSoundDataByMacAddress(serial_no);
        Intrinsics.checkNotNullExpressionValue(dailyAmbientSoundDataByMacAddress, "getInstance(context)\n   …taByMacAddress(serial_no)");
        return dailyAmbientSoundDataByMacAddress;
    }

    @Nullable
    public final List<EntityHourlyAmbientSoundData> getHourlyAmbientSoundDataFrom(@NotNull String time, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return AmbientSoundDBRead.getInstance(context).getHourlyAmbientSoundDataFrom(time, serialNo);
    }

    @NotNull
    public final LiveData<List<EntityHourlyAmbientSoundData>> getHourlyData(@NotNull Calendar calendar, @NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        LiveData<List<EntityHourlyAmbientSoundData>> ambientSoundData = AmbientSoundDBRead.getInstance(context).getAmbientSoundData(formatDate, serial_no);
        Intrinsics.checkNotNullExpressionValue(ambientSoundData, "getInstance(context)\n   …undData(time1, serial_no)");
        return ambientSoundData;
    }

    @Nullable
    public final LiveData<EntityHourlyAmbientSoundData> getLastReadAmbientSound(@NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return AmbientSoundDBRead.getInstance(context).getLastReadAmbientSoundData(serial_no);
    }

    @Nullable
    public final EntityHourlyAmbientSoundData getLatestRecordHourly(@NotNull String serial_no) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return AmbientSoundDBRead.getInstance(context).getLatestRecordHourly(serial_no);
    }

    @Nullable
    public final LiveData<List<MonthlyAmbientSoundData>> getMonthWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.AMBIENT_SOUND;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7308a;
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
        return AmbientSoundDBRead.getInstance(context2).getMonthWiseAmbientSoundHistory(serial_no);
    }

    @Nullable
    public final LiveData<List<WeeklyAmbientSoundData>> getWeekWiseList(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull String serial_no) {
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
        ActivityType activityType = ActivityType.AMBIENT_SOUND;
        MutableLiveData<ResponseResult> mutableLiveData2 = this.f7308a;
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
        return AmbientSoundDBRead.getInstance(context2).getWeekWiseAmbientSoundHistory(serial_no);
    }

    public final void setResponseResult(@NotNull MutableLiveData<ResponseResult> responseResult) {
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
        this.f7308a = responseResult;
    }

    @Nullable
    public final EntityHourlyAmbientSoundData getLatestRecordHourly(@NotNull String serial_no, @NotNull String date) {
        Intrinsics.checkNotNullParameter(serial_no, "serial_no");
        Intrinsics.checkNotNullParameter(date, "date");
        Context context = this.b;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        return AmbientSoundDBRead.getInstance(context).getLatestRecordHourly(serial_no, date);
    }
}
