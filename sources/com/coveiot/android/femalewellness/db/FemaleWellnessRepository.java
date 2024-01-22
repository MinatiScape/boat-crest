package com.coveiot.android.femalewellness.db;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.android.femalewellness.SingletonHolder;
import com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao;
import com.coveiot.android.femalewellness.db.entities.EntityFemaleWellnessSymptoms;
import com.coveiot.android.femalewellness.wellnesscalendar.model.PhaseAndDateModel;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FemaleWellnessRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4392a;
    @NotNull
    public FemaleWellnessSymptomsDao b;

    /* loaded from: classes4.dex */
    public static final class Companion extends SingletonHolder<FemaleWellnessRepository, Context> {

        /* loaded from: classes4.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, FemaleWellnessRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, FemaleWellnessRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final FemaleWellnessRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new FemaleWellnessRepository(p0);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public FemaleWellnessRepository(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4392a = context;
        FemaleWellnessSymptomsDao symptomsDao = FemaleWellnessDatabase.getAppDatabase(context).symptomsDao();
        Intrinsics.checkNotNullExpressionValue(symptomsDao, "getAppDatabase(context).symptomsDao()");
        this.b = symptomsDao;
    }

    public final void deleteFutureRecordsIfAny(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.b.deleteFutureRecordsIfAny(date);
    }

    @Nullable
    public final PhaseAndDateModel fetchNextPeriodOvulationDate(@NotNull String selectedDate) {
        Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
        return this.b.fetchNextPeriodOvulationDate(selectedDate);
    }

    @Nullable
    public final PhaseAndDateModel fetchPreviousPeriodOvulationDate(@NotNull String selectedDate) {
        Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
        return this.b.fetchPreviousPeriodOvulationDate(selectedDate);
    }

    @Nullable
    public final List<String> fetchRecordedOvulationDates(@NotNull String dateStr, @NotNull String startDate) {
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        return this.b.fetchRecordedOvulationDates(dateStr, startDate);
    }

    @Nullable
    public final List<String> fetchRecordedPeriodDates(@NotNull String dateStr, @NotNull String startDate) {
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        return this.b.fetchRecordedPeriodDates(dateStr, startDate);
    }

    @NotNull
    public final LiveData<List<EntityFemaleWellnessSymptoms>> getLatestRecord(@NotNull String selectedDate) {
        Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
        LiveData<List<EntityFemaleWellnessSymptoms>> theRecordedSymptomsByDate = this.b.getTheRecordedSymptomsByDate(selectedDate);
        Intrinsics.checkNotNullExpressionValue(theRecordedSymptomsByDate, "symptomsFemaleWellnessDa…ptomsByDate(selectedDate)");
        return theRecordedSymptomsByDate;
    }

    @Nullable
    public final String getOldestLMD() {
        return this.b.getOldestLMD();
    }

    public final void insert(@NotNull EntityFemaleWellnessSymptoms symptomsRecordFemaleWellness) {
        Intrinsics.checkNotNullParameter(symptomsRecordFemaleWellness, "symptomsRecordFemaleWellness");
        this.b.insert(symptomsRecordFemaleWellness);
    }

    public final void insertOrUpdateWellnessData(@NotNull String date, @NotNull String cycleStartDate, int i, int i2, @Nullable String str, boolean z) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(cycleStartDate, "cycleStartDate");
        this.b.insertOrUpdateWellnessData(date, cycleStartDate, i, i2, str, Boolean.valueOf(z));
    }

    public final void update(@NotNull EntityFemaleWellnessSymptoms symptomsRecordFemaleWellness) {
        Intrinsics.checkNotNullParameter(symptomsRecordFemaleWellness, "symptomsRecordFemaleWellness");
        this.b.updateFemaleWellnessSymptoms(symptomsRecordFemaleWellness.date, symptomsRecordFemaleWellness.flow, symptomsRecordFemaleWellness.pain, symptomsRecordFemaleWellness.mood, symptomsRecordFemaleWellness.symptoms);
    }
}
