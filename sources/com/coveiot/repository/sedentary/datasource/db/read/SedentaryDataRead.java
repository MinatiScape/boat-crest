package com.coveiot.repository.sedentary.datasource.db.read;

import com.coveiot.covedb.sedentary.SedentaryDataDao;
import com.coveiot.covedb.sedentary.entities.EntitySedentary;
import com.coveiot.repository.RepositoryUtils;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class SedentaryDataRead {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final Long getLastSedentaryAlertTimeStampFor(@NotNull SedentaryDataDao sedentaryDataDao, @NotNull Calendar calendar, @NotNull String address) {
            Intrinsics.checkNotNullParameter(sedentaryDataDao, "sedentaryDataDao");
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(address, "address");
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            return Long.valueOf(sedentaryDataDao.getLastSedentaryAlertTimeStampFor(calendar.getTimeInMillis(), address));
        }

        @Nullable
        public final List<EntitySedentary> getUnSyncedSedentaryAlertsList(@NotNull SedentaryDataDao sedentaryDataDao, @NotNull String address) {
            Intrinsics.checkNotNullParameter(sedentaryDataDao, "sedentaryDataDao");
            Intrinsics.checkNotNullParameter(address, "address");
            return sedentaryDataDao.getUnSyncedSedentaryAlertsList(address);
        }

        @Nullable
        public final Integer readCountOfSedentaryAlertsFor(@NotNull SedentaryDataDao sedentaryDataDao, @NotNull Calendar calendar, @NotNull String address) {
            Intrinsics.checkNotNullParameter(sedentaryDataDao, "sedentaryDataDao");
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(address, "address");
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            return Integer.valueOf(sedentaryDataDao.countOfSedentaryAlertsFrom(calendar.getTimeInMillis(), address));
        }

        @Nullable
        public final Integer readCountOfSedentaryAlertsForDate(@NotNull SedentaryDataDao sedentaryDataDao, @NotNull Calendar calendar, @NotNull String address) {
            Intrinsics.checkNotNullParameter(sedentaryDataDao, "sedentaryDataDao");
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(address, "address");
            return Integer.valueOf(sedentaryDataDao.countOfSedentaryAlertsForDate(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), address));
        }
    }
}
