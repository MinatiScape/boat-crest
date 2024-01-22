package com.coveiot.android.respiratoryrate.database;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao;
import com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRawPPGHistoryEntity;
import com.coveiot.android.respiratoryrate.database.entities.HourlyRespiratoryRawPPGDataEntity;
import com.coveiot.android.respiratoryrate.model.MonthlyRespiratoryRateData;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.coveiot.android.respiratoryrate.model.WeeklyRespiratoryRateData;
import com.coveiot.repository.SingletonHolder;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class RespiratoryRateRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5671a;
    @NotNull
    public RespiratoryRateDao b;
    @NotNull
    public RespiratoryRawPPGHistoryDao c;

    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<RespiratoryRateRepository, Context> {

        /* loaded from: classes6.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, RespiratoryRateRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, RespiratoryRateRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final RespiratoryRateRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new RespiratoryRateRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RespiratoryRateRepository(Context context) {
        this.f5671a = context;
        RespiratoryRateDao respiratoryRateDao = RespiratoryRateDatabase.getAppDatabase(context).respiratoryRateDao();
        Intrinsics.checkNotNullExpressionValue(respiratoryRateDao, "getAppDatabase(context).respiratoryRateDao()");
        this.b = respiratoryRateDao;
        RespiratoryRawPPGHistoryDao respiratoryRawPPGHistoryDao = RespiratoryRateDatabase.getAppDatabase(context).respiratoryRawPPGHistoryDao();
        Intrinsics.checkNotNullExpressionValue(respiratoryRawPPGHistoryDao, "getAppDatabase(context).…iratoryRawPPGHistoryDao()");
        this.c = respiratoryRawPPGHistoryDao;
    }

    public /* synthetic */ RespiratoryRateRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void deleteAllDailyRawPPGData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.c.deleteAllDailyRawPPGData(macAddress);
    }

    public final void deleteAllHourlyRawPPGData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.c.deleteAllHourlyRawPPGData(macAddress);
    }

    public final void deleteDailyRawPPGData(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.c.deleteDailyRawPPGData(date, macAddress);
    }

    public final void deleteHourlyRawPPGData(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.c.deleteHourlyRawPPGData(date, macAddress);
    }

    @Nullable
    public final List<DailyRespiratoryRawPPGHistoryEntity> getAllDailyRawPPGData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.c.getAllDailyRawPPGData(macAddress);
    }

    @Nullable
    public final DailyRespiratoryRawPPGHistoryEntity getDailyRawPPGData(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.c.getDailyRawPPGData(date, macAddress);
    }

    public final int getDailyRawPPGDataRowCount(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.c.getDailyDataCount(macAddress);
    }

    @Nullable
    public final List<RespiratoryRateListBean> getDailyRespiratoryRateDateBetweenDates(@NotNull String startDate, @NotNull String endDate, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getDailyRespiratoryRateDateBetweenDates(startDate, endDate, macAddress);
    }

    @Nullable
    public final List<RespiratoryRateListBean> getDailyRespiratoryRateRangeBetweenDates(@NotNull String startDate, @NotNull String endDate, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getDailyRespiratoryRateRangeBetweenDates(startDate, endDate, macAddress);
    }

    @Nullable
    public final List<HourlyRespiratoryRawPPGDataEntity> getHourlyRawPPGData(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.c.getHourlyRawPPGData(date, macAddress);
    }

    @Nullable
    public final String getLastSyncedDate(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getLastSyncedDate(macAddress);
    }

    @NotNull
    public final LiveData<DailyRespiratoryRateEntity> getLatestRespiratoryRateData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        LiveData<DailyRespiratoryRateEntity> latestRespiratoryRateData = this.b.getLatestRespiratoryRateData(macAddress);
        Intrinsics.checkNotNullExpressionValue(latestRespiratoryRateData, "respiratoryRateDao.getLa…atoryRateData(macAddress)");
        return latestRespiratoryRateData;
    }

    @NotNull
    public final List<DailyRespiratoryRateEntity> getListOfUnsyncedRespiratoryRateData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        List<DailyRespiratoryRateEntity> listOfUnSyncedRespiratoryRate = this.b.getListOfUnSyncedRespiratoryRate(macAddress);
        Intrinsics.checkNotNullExpressionValue(listOfUnSyncedRespiratoryRate, "respiratoryRateDao.getLi…spiratoryRate(macAddress)");
        return listOfUnSyncedRespiratoryRate;
    }

    @NotNull
    public final LiveData<List<DailyRespiratoryRateEntity>> getLiveDayWiseRespiratoryRateData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        LiveData<List<DailyRespiratoryRateEntity>> liveDayWiseRespiratoryRateData = this.b.getLiveDayWiseRespiratoryRateData(macAddress);
        Intrinsics.checkNotNullExpressionValue(liveDayWiseRespiratoryRateData, "respiratoryRateDao.getLi…atoryRateData(macAddress)");
        return liveDayWiseRespiratoryRateData;
    }

    @NotNull
    public final LiveData<List<MonthlyRespiratoryRateData>> getLiveMonthWiseRespiratoryRateData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        LiveData<List<MonthlyRespiratoryRateData>> liveMonthWiseRespiratoryRateData = this.b.getLiveMonthWiseRespiratoryRateData(macAddress);
        Intrinsics.checkNotNullExpressionValue(liveMonthWiseRespiratoryRateData, "respiratoryRateDao.getLi…atoryRateData(macAddress)");
        return liveMonthWiseRespiratoryRateData;
    }

    @NotNull
    public final LiveData<List<WeeklyRespiratoryRateData>> getLiveWeekWiseRespiratoryRateData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        LiveData<List<WeeklyRespiratoryRateData>> liveWeekWiseRespiratoryRateData = this.b.getLiveWeekWiseRespiratoryRateData(macAddress);
        Intrinsics.checkNotNullExpressionValue(liveWeekWiseRespiratoryRateData, "respiratoryRateDao.getLi…atoryRateData(macAddress)");
        return liveWeekWiseRespiratoryRateData;
    }

    @Nullable
    public final DailyRespiratoryRawPPGHistoryEntity getOldestDailyRawPPGData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.c.getOldestDailyRawPPGData(macAddress);
    }

    @Nullable
    public final DailyRespiratoryRateEntity getRespiratoryRateData(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getRespiratoryRate(date, macAddress);
    }

    @Nullable
    public final LiveData<DailyRespiratoryRateEntity> getRespiratoryRateLiveData(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getRespiratoryRateLiveData(date, macAddress);
    }

    public final int getRowCount(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getRowCount(macAddress);
    }

    @Nullable
    public final Long insert(@NotNull DailyRespiratoryRateEntity respiratoryRateData) {
        Intrinsics.checkNotNullParameter(respiratoryRateData, "respiratoryRateData");
        return Long.valueOf(this.b.insert(respiratoryRateData));
    }

    public final void insertAllHourlyRawPPGData(@NotNull List<HourlyRespiratoryRawPPGDataEntity> hourlyRespiratoryRawPPGDataEntities) {
        Intrinsics.checkNotNullParameter(hourlyRespiratoryRawPPGDataEntities, "hourlyRespiratoryRawPPGDataEntities");
        this.c.insertAll(hourlyRespiratoryRawPPGDataEntities);
    }

    public final void insertDailyRawPPGData(@NotNull DailyRespiratoryRawPPGHistoryEntity dailyRespiratoryRawPPGHistoryEntity) {
        Intrinsics.checkNotNullParameter(dailyRespiratoryRawPPGHistoryEntity, "dailyRespiratoryRawPPGHistoryEntity");
        this.c.insert(dailyRespiratoryRawPPGHistoryEntity);
    }

    public final void updateSyncedToServer(@NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        this.b.updateRespiratoryRateToServer(macAddress, date);
    }
}
