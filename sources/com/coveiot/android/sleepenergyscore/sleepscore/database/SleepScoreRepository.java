package com.coveiot.android.sleepenergyscore.sleepscore.database;

import android.content.Context;
import com.coveiot.android.sleepenergyscore.sleepscore.database.dao.SleepScoreDao;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.QuestionAnswerSleepData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.repository.SingletonHolder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SleepScoreRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5742a;
    @NotNull
    public SleepScoreDao b;

    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<SleepScoreRepository, Context> {

        /* loaded from: classes6.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, SleepScoreRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, SleepScoreRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final SleepScoreRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SleepScoreRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SleepScoreRepository(Context context) {
        this.f5742a = context;
        SleepScoreDao sleepScoreDao = SleepScoreDatabase.getAppDatabase(context).sleepScoreDao();
        Intrinsics.checkNotNullExpressionValue(sleepScoreDao, "getAppDatabase(context).sleepScoreDao()");
        this.b = sleepScoreDao;
    }

    public /* synthetic */ SleepScoreRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final Long getLastSyncedDate(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return Long.valueOf(this.b.getLastSyncedDate(macAddress));
    }

    @Nullable
    public final Integer getSleepScore(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return Integer.valueOf(this.b.getSleepScore(date, macAddress));
    }

    @Nullable
    public final SleepScoreData getSleepScoreData(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getSleepScoreData(date, macAddress);
    }

    @Nullable
    public final List<SleepScoreData> getSleepScoreDataList(@NotNull String startDate, @NotNull String endDate, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getSleepScoreDataListBetweenDates(startDate, endDate, macAddress);
    }

    @Nullable
    public final Long insert(@NotNull SleepScoreData sleepScoreData) {
        Intrinsics.checkNotNullParameter(sleepScoreData, "sleepScoreData");
        return Long.valueOf(this.b.insert(sleepScoreData));
    }

    public final void updateFeedbackList(@NotNull ArrayList<QuestionAnswerSleepData> listFeedback, @NotNull String questionnarieid, @NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(listFeedback, "listFeedback");
        Intrinsics.checkNotNullParameter(questionnarieid, "questionnarieid");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        this.b.updateFeedbackList(listFeedback, questionnarieid, macAddress, date);
    }
}
