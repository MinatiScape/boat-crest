package com.coveiot.repository.bp.datasources.server;

import com.coveiot.coveaccess.bp.model.BpData;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.covedb.bp.model.BpCodedValueData;
import com.coveiot.repository.RepositoryUtils;
import java.util.ArrayList;
import java.util.List;
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
        public final EntityDailyBp getDailyBpData(@NotNull BpData bpData) {
            Intrinsics.checkNotNullParameter(bpData, "bpData");
            EntityDailyBp entityDailyBp = new EntityDailyBp();
            entityDailyBp.date = bpData.getDate();
            entityDailyBp.systolic_bp = bpData.getAvgSystolic();
            entityDailyBp.diastolic_bp = bpData.getAvgDiastolic();
            entityDailyBp.setIsSyncServer(true);
            return entityDailyBp;
        }

        @NotNull
        public final List<EntityHourlyBp> getHourlyBPData(@NotNull BpData bpData) {
            Intrinsics.checkNotNullParameter(bpData, "bpData");
            ArrayList arrayList = new ArrayList();
            if (bpData.getTimeLog() != null && !RepositoryUtils.isEmpty(bpData.getTimeLog().getLogs())) {
                for (BpData.TimeLogBean.LogsBean logsBean : bpData.getTimeLog().getLogs()) {
                    EntityHourlyBp entityHourlyBp = new EntityHourlyBp();
                    entityHourlyBp.start_hour = logsBean.getStartTime();
                    entityHourlyBp.end_hour = logsBean.getEndTime();
                    entityHourlyBp.date = bpData.getDate();
                    if (!RepositoryUtils.isEmpty(logsBean.getCodedValues())) {
                        ArrayList<BpCodedValueData> arrayList2 = new ArrayList<>();
                        int i = 0;
                        int i2 = 0;
                        int i3 = 0;
                        for (List<Integer> list : logsBean.getCodedValues()) {
                            BpCodedValueData bpCodedValueData = new BpCodedValueData();
                            Integer num = list.get(0);
                            Intrinsics.checkNotNullExpressionValue(num, "value.get(0)");
                            bpCodedValueData.setSystolicBp(num.intValue());
                            Integer num2 = list.get(1);
                            Intrinsics.checkNotNullExpressionValue(num2, "value.get(1)");
                            bpCodedValueData.setDiastolicBp(num2.intValue());
                            arrayList2.add(bpCodedValueData);
                            Integer num3 = list.get(0);
                            Intrinsics.checkNotNullExpressionValue(num3, "value.get(0)");
                            i3 += num3.intValue();
                            Integer num4 = list.get(1);
                            Intrinsics.checkNotNullExpressionValue(num4, "value.get(1)");
                            i2 += num4.intValue();
                            Integer num5 = list.get(0);
                            Intrinsics.checkNotNullExpressionValue(num5, "value.get(0)");
                            if (num5.intValue() > 0) {
                                Integer num6 = list.get(1);
                                Intrinsics.checkNotNullExpressionValue(num6, "value.get(1)");
                                if (num6.intValue() > 0) {
                                    i++;
                                }
                            }
                        }
                        if (i > 0) {
                            entityHourlyBp.setSystolicBp(i3 / i);
                            entityHourlyBp.setDiastolicBp(i2 / i);
                        }
                        entityHourlyBp.codevalues = arrayList2;
                    }
                    arrayList.add(entityHourlyBp);
                }
            }
            return arrayList;
        }
    }
}
