package com.coveiot.repository.sedentary.datasource.db.write;

import com.coveiot.covedb.sedentary.SedentaryDataDao;
import com.coveiot.covedb.sedentary.entities.EntitySedentary;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class SedentaryDataWrite {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void insert(@NotNull SedentaryDataDao sedentaryDataDao, @NotNull EntitySedentary entitySedentary) {
            Intrinsics.checkNotNullParameter(sedentaryDataDao, "sedentaryDataDao");
            Intrinsics.checkNotNullParameter(entitySedentary, "entitySedentary");
            sedentaryDataDao.insert(entitySedentary);
        }

        public final void updateSyncState(@NotNull SedentaryDataDao sedentaryDataDao, @NotNull String date) {
            Intrinsics.checkNotNullParameter(sedentaryDataDao, "sedentaryDataDao");
            Intrinsics.checkNotNullParameter(date, "date");
            sedentaryDataDao.updateSyncState(date);
        }
    }
}
