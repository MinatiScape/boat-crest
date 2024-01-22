package com.coveiot.repository.sedentary.datasource.server;

import com.coveiot.coveaccess.sedentaryalerts.model.SedentaryAlertsDataBean;
import com.coveiot.covedb.sedentary.entities.EntitySedentary;
import com.coveiot.repository.RepositoryUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class FormatorServerToEntity {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final EntitySedentary getSedentaryAlertsData(@NotNull SedentaryAlertsDataBean.TimeLog.Log timelog, @NotNull SedentaryAlertsDataBean sedentaryAlertsData, @NotNull String macAddress, int i) {
            Intrinsics.checkNotNullParameter(timelog, "timelog");
            Intrinsics.checkNotNullParameter(sedentaryAlertsData, "sedentaryAlertsData");
            Intrinsics.checkNotNullParameter(macAddress, "macAddress");
            EntitySedentary entitySedentary = new EntitySedentary();
            entitySedentary.setTimestamp(RepositoryUtils.getTimestampFromDate(sedentaryAlertsData.getDate() + ' ' + timelog.getTime()));
            entitySedentary.setDate(sedentaryAlertsData.getDate());
            entitySedentary.setSerial_number(macAddress);
            entitySedentary.setSavedServer(RepositoryUtils.isDateToday(sedentaryAlertsData.getDate()) ^ true);
            entitySedentary.setRecordNumber(i);
            return entitySedentary;
        }
    }
}
