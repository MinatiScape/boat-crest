package com.coveiot.repository.sedentary.datasource.db;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.sedentary.SedentaryDataDao;
import com.coveiot.covedb.sedentary.entities.EntitySedentary;
import com.coveiot.repository.sedentary.datasource.db.read.SedentaryDataRead;
import com.coveiot.repository.sedentary.datasource.db.write.SedentaryDataWrite;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class SedentaryDBRepo {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public static SedentaryDBRepo c;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Context f7432a;
    @NotNull
    public final SedentaryDataDao b;

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final SedentaryDBRepo getInstance(@Nullable Context context) {
            if (SedentaryDBRepo.c == null) {
                SedentaryDBRepo.c = new SedentaryDBRepo(context);
            }
            return SedentaryDBRepo.c;
        }
    }

    public SedentaryDBRepo(@Nullable Context context) {
        this.f7432a = context;
        SedentaryDataDao sedentaryDataDao = CoveAppDatabase.getAppDatabase(context).sedentaryDataDao();
        Intrinsics.checkNotNullExpressionValue(sedentaryDataDao, "appDatabase.sedentaryDataDao()");
        this.b = sedentaryDataDao;
    }

    @Nullable
    public final Context getApplication() {
        return this.f7432a;
    }

    @Nullable
    public final Long getLastSedentaryAlertTimeStampFor(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        return SedentaryDataRead.Companion.getLastSedentaryAlertTimeStampFor(this.b, calendar, BleApiManager.getInstance(this.f7432a).getBleApi().getMacAddress());
    }

    @NotNull
    public final SedentaryDataDao getSedentaryDataDao() {
        return this.b;
    }

    @Nullable
    public final List<EntitySedentary> getUnSyncedSedentaryAlertsList() {
        return SedentaryDataRead.Companion.getUnSyncedSedentaryAlertsList(this.b, BleApiManager.getInstance(this.f7432a).getBleApi().getMacAddress());
    }

    public final void insert(@NotNull EntitySedentary entitySedentary) {
        Intrinsics.checkNotNullParameter(entitySedentary, "entitySedentary");
        SedentaryDataWrite.Companion.insert(this.b, entitySedentary);
    }

    @Nullable
    public final Integer readCountOfSedentaryAlertsFor(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        return SedentaryDataRead.Companion.readCountOfSedentaryAlertsFor(this.b, calendar, BleApiManager.getInstance(this.f7432a).getBleApi().getMacAddress());
    }

    @Nullable
    public final Integer readCountOfSedentaryAlertsForDate(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        return SedentaryDataRead.Companion.readCountOfSedentaryAlertsForDate(this.b, calendar, BleApiManager.getInstance(this.f7432a).getBleApi().getMacAddress());
    }

    public final void updateSyncState(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        SedentaryDataWrite.Companion.updateSyncState(this.b, date);
    }
}
