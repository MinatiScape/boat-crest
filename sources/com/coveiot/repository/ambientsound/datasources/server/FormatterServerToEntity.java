package com.coveiot.repository.ambientsound.datasources.server;

import com.coveiot.coveaccess.ambientsound.AmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData;
import com.coveiot.repository.RepositoryUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class FormatterServerToEntity {
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
        public final AmbientSoundData getAmbientSoundDataModel(@NotNull EntityDailyAmbientSoundData ambientSound, @NotNull List<? extends EntityHourlyAmbientSoundData> ambientSoundHourlyData) {
            Intrinsics.checkNotNullParameter(ambientSound, "ambientSound");
            Intrinsics.checkNotNullParameter(ambientSoundHourlyData, "ambientSoundHourlyData");
            AmbientSoundData ambientSoundData = new AmbientSoundData();
            ambientSoundData.setDate(ambientSound.getDate());
            ambientSoundData.setType("AMBIENT_SOUND");
            ambientSoundData.setBaseUnit("DECIBEL");
            ambientSoundData.setMax(ambientSound.getMaxAmbientSound());
            ambientSoundData.setMin(ambientSound.getMinAmbientSound());
            ambientSoundData.setAvg((int) ambientSound.getAvgAmbientSound());
            ambientSoundData.setTotalDuration(ambientSound.getTotalTime());
            ambientSoundData.setTzOffset(RepositoryUtils.getTimeZoneOffset());
            AmbientSoundData.TimeLogBean timeLogBean = new AmbientSoundData.TimeLogBean();
            ArrayList arrayList = new ArrayList();
            for (EntityHourlyAmbientSoundData entityHourlyAmbientSoundData : ambientSoundHourlyData) {
                AmbientSoundData.TimeLogBean.LogsBean logsBean = new AmbientSoundData.TimeLogBean.LogsBean();
                logsBean.setStartTime(entityHourlyAmbientSoundData.getStartTime());
                logsBean.setEndTime(entityHourlyAmbientSoundData.getEndTime());
                logsBean.setMax(entityHourlyAmbientSoundData.getMaxAmbientSound());
                logsBean.setMin(entityHourlyAmbientSoundData.getMinAmbientSound());
                logsBean.setAvg((int) entityHourlyAmbientSoundData.getAvgAmbientSound());
                logsBean.setCodedValues(entityHourlyAmbientSoundData.getCodedValues());
                arrayList.add(logsBean);
            }
            timeLogBean.setLogs(arrayList);
            ambientSoundData.setTimeLog(timeLogBean);
            AmbientSoundData.BaseUnits baseUnits = new AmbientSoundData.BaseUnits();
            baseUnits.setTotalDuration("MINUTES");
            ambientSoundData.setBaseUnits(baseUnits);
            return ambientSoundData;
        }

        @NotNull
        public final EntityDailyAmbientSoundData getDailyAmbientSoundData(@NotNull AmbientSoundData ambientSoundData) {
            Intrinsics.checkNotNullParameter(ambientSoundData, "ambientSoundData");
            EntityDailyAmbientSoundData entityDailyAmbientSoundData = new EntityDailyAmbientSoundData();
            entityDailyAmbientSoundData.setDate(ambientSoundData.getDate());
            entityDailyAmbientSoundData.setMaxAmbientSound(ambientSoundData.getMax());
            entityDailyAmbientSoundData.setMinAmbientSound(ambientSoundData.getMin());
            entityDailyAmbientSoundData.setAvgAmbientSound(ambientSoundData.getAvg());
            entityDailyAmbientSoundData.setTotalTime(ambientSoundData.getTotalDuration());
            entityDailyAmbientSoundData.setIsSyncServer(true);
            return entityDailyAmbientSoundData;
        }

        @NotNull
        public final List<EntityHourlyAmbientSoundData> getHourlyAmbientSoundData(@NotNull AmbientSoundData ambientSoundData) {
            Intrinsics.checkNotNullParameter(ambientSoundData, "ambientSoundData");
            ArrayList arrayList = new ArrayList();
            if (ambientSoundData.getTimeLog() != null && !RepositoryUtils.isEmpty(ambientSoundData.getTimeLog().getLogs())) {
                for (AmbientSoundData.TimeLogBean.LogsBean logsBean : ambientSoundData.getTimeLog().getLogs()) {
                    EntityHourlyAmbientSoundData entityHourlyAmbientSoundData = new EntityHourlyAmbientSoundData();
                    entityHourlyAmbientSoundData.setStartTime(logsBean.getStartTime());
                    entityHourlyAmbientSoundData.setEndTime(logsBean.getEndTime());
                    entityHourlyAmbientSoundData.setMaxAmbientSound(logsBean.getMax());
                    entityHourlyAmbientSoundData.setMinAmbientSound(logsBean.getMin());
                    entityHourlyAmbientSoundData.setDate(ambientSoundData.getDate());
                    if (!RepositoryUtils.isEmpty(logsBean.getCodedValues())) {
                        int i = 0;
                        for (Integer element : logsBean.getCodedValues()) {
                            Intrinsics.checkNotNullExpressionValue(element, "element");
                            i += element.intValue();
                        }
                        entityHourlyAmbientSoundData.setAvgAmbientSound(i / logsBean.getCodedValues().size());
                        entityHourlyAmbientSoundData.setCodedValues((ArrayList) logsBean.getCodedValues());
                    }
                    arrayList.add(entityHourlyAmbientSoundData);
                }
            }
            return arrayList;
        }
    }
}
