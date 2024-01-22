package com.coveiot.android.sleepenergyscore.energymeter.database;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.android.sleepenergyscore.energymeter.database.dao.EnergyScoreDao;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.QuestionAnswerData;
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
public final class EnergyScoreRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5704a;
    @NotNull
    public EnergyScoreDao b;

    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<EnergyScoreRepository, Context> {

        /* loaded from: classes6.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, EnergyScoreRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, EnergyScoreRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final EnergyScoreRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EnergyScoreRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EnergyScoreRepository(Context context) {
        this.f5704a = context;
        EnergyScoreDao energyScoreDao = EnergyScoreDatabase.getAppDatabase(context).energyScoreDao();
        Intrinsics.checkNotNullExpressionValue(energyScoreDao, "getAppDatabase(context).energyScoreDao()");
        this.b = energyScoreDao;
    }

    public /* synthetic */ EnergyScoreRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final EnergyScoreDbData getEnergyScoreData(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getEnergyScoreData(date, macAddress);
    }

    @Nullable
    public final Long getLastSyncedDate(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return Long.valueOf(this.b.getLastSyncedDate(macAddress));
    }

    @NotNull
    public final LiveData<EnergyScoreDbData> getLastSyncedEnergyScoreLiveData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        LiveData<EnergyScoreDbData> lastSyncedScoreLiveData = this.b.getLastSyncedScoreLiveData(macAddress);
        Intrinsics.checkNotNullExpressionValue(lastSyncedScoreLiveData, "energyScoreDao.getLastSy…ScoreLiveData(macAddress)");
        return lastSyncedScoreLiveData;
    }

    @NotNull
    public final List<EnergyScoreDbData> getListOfEnergyScoreData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        List<EnergyScoreDbData> listOfEnergyScoreData = this.b.getListOfEnergyScoreData(macAddress);
        Intrinsics.checkNotNullExpressionValue(listOfEnergyScoreData, "energyScoreDao.getListOf…ergyScoreData(macAddress)");
        return listOfEnergyScoreData;
    }

    @NotNull
    public final List<EnergyScoreDbData> getListOfUnsyncedEnergyScoreData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        List<EnergyScoreDbData> listOfUnSyncedEnergyScoreData = this.b.getListOfUnSyncedEnergyScoreData(macAddress);
        Intrinsics.checkNotNullExpressionValue(listOfUnSyncedEnergyScoreData, "energyScoreDao.getListOf…ergyScoreData(macAddress)");
        return listOfUnSyncedEnergyScoreData;
    }

    @Nullable
    public final Long insert(@NotNull EnergyScoreDbData energyScoreData) {
        Intrinsics.checkNotNullParameter(energyScoreData, "energyScoreData");
        return Long.valueOf(this.b.insert(energyScoreData));
    }

    public final void updateFeedbackList(@NotNull ArrayList<QuestionAnswerData> listFeedback, @NotNull String questionnarieid, @NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(listFeedback, "listFeedback");
        Intrinsics.checkNotNullParameter(questionnarieid, "questionnarieid");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        this.b.updateFeedbackList(listFeedback, questionnarieid, macAddress, date);
    }

    public final void updateSyncedtToServer(@NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        this.b.updateSyncedDataToServer(macAddress, date);
    }
}
