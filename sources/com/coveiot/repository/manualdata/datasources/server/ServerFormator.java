package com.coveiot.repository.manualdata.datasources.server;

import android.util.Log;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordType;
import com.coveiot.coveaccess.manualdata.SaveManualSPO2DataReq;
import com.coveiot.coveaccess.manualdata.model.BaseUnitsManual;
import com.coveiot.coveaccess.manualdata.model.ManualBpData;
import com.coveiot.coveaccess.manualdata.model.ManualMixedData;
import com.coveiot.coveaccess.model.server.SPO2FitnessSessionDataBean;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.manualdata.entities.Source;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ServerFormator {
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
        public final EntityManualData getEntityFromServerObject(@NotNull ManualBpData manualBpData, @NotNull String serialNo, @NotNull String userDeviceId) {
            Intrinsics.checkNotNullParameter(manualBpData, "manualBpData");
            Intrinsics.checkNotNullParameter(serialNo, "serialNo");
            Intrinsics.checkNotNullParameter(userDeviceId, "userDeviceId");
            long j = 1000;
            EntityManualData entityManualData = new EntityManualData((AppUtils.parseDate(manualBpData.getSessionStartDate(), UtilConstants.SERVER_TIME_FORMAT).getTime() / j) * j, Source.FROM_DEVICE.name());
            Integer num = manualBpData.getValue().get(0);
            Intrinsics.checkNotNullExpressionValue(num, "manualBpData.value[0]");
            entityManualData.setSystolicBp(num.intValue());
            Integer num2 = manualBpData.getValue().get(1);
            Intrinsics.checkNotNullExpressionValue(num2, "manualBpData.value[1]");
            entityManualData.setDiastolicBp(num2.intValue());
            entityManualData.setSerialNo(serialNo);
            entityManualData.setUserDeviceId(userDeviceId);
            entityManualData.setSyncedWithServer(true);
            return entityManualData;
        }

        @NotNull
        public final ManualMixedData getHRVStressServerObjectEntity(@NotNull EntityManualData manualData) {
            Intrinsics.checkNotNullParameter(manualData, "manualData");
            ManualMixedData manualMixedData = new ManualMixedData();
            BaseUnitsManual baseUnitsManual = new BaseUnitsManual();
            baseUnitsManual.setHr("BPM");
            baseUnitsManual.setHrv("VARIANCE");
            baseUnitsManual.setStress("PERCENTAGE");
            baseUnitsManual.setVascAging("YEARS");
            manualMixedData.setBaseUnits(baseUnitsManual);
            String str = String.valueOf(PreferenceManager.getInstance().getUserId()) + ';' + PreferenceManager.getInstance().getUserDeviceID() + ";mixed;" + AppUtils.formatDate(new Date(manualData.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT2) + ';' + AppUtils.formatDate(new Date(manualData.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT2) + ";1";
            Log.d("ClientRefIdForMixedData", "clientRefString: " + str);
            manualMixedData.setClientRefId(AppUtils.convertStringToMD5(str));
            manualMixedData.setTzOffset(RepositoryUtils.getTimeZoneOffset());
            manualMixedData.setTotalSampleCount(1);
            manualMixedData.setSessionStartDate(AppUtils.formatDate(new Date(manualData.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT));
            manualMixedData.setSessionEndDate(AppUtils.formatDate(new Date(manualData.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT));
            manualMixedData.setHr(Integer.valueOf(manualData.getHr()));
            manualMixedData.setHrv(Integer.valueOf(manualData.getHrv()));
            manualMixedData.setStressLevel(Integer.valueOf(manualData.getStress()));
            manualMixedData.setVascAging(Integer.valueOf(manualData.getVascularAging()));
            return manualMixedData;
        }

        @NotNull
        public final EntityManualData getSPO2EntityFromServerObject(@NotNull SPO2FitnessSessionDataBean spO2FitnessSessionDataBean, @NotNull String serialNo, @NotNull String userDeviceId) {
            Intrinsics.checkNotNullParameter(spO2FitnessSessionDataBean, "spO2FitnessSessionDataBean");
            Intrinsics.checkNotNullParameter(serialNo, "serialNo");
            Intrinsics.checkNotNullParameter(userDeviceId, "userDeviceId");
            long j = 1000;
            EntityManualData entityManualData = new EntityManualData((AppUtils.parseDate(spO2FitnessSessionDataBean.getSessionStartDate(), UtilConstants.SERVER_TIME_FORMAT).getTime() / j) * j, Source.MANUAL.name());
            entityManualData.setSpo2(spO2FitnessSessionDataBean.getValue());
            if (spO2FitnessSessionDataBean.getBaseUnit() != null) {
                entityManualData.setLevelInterpretation(m.equals(spO2FitnessSessionDataBean.getBaseUnit(), "ORDINAL", true));
            }
            entityManualData.setSerialNo(serialNo);
            entityManualData.setUserDeviceId(userDeviceId);
            entityManualData.setSyncedWithServer(true);
            return entityManualData;
        }

        @NotNull
        public final SPO2FitnessSessionDataBean getSPO2ServerObjectFromEntity(@NotNull EntityManualData manualSPO2) {
            Intrinsics.checkNotNullParameter(manualSPO2, "manualSPO2");
            SPO2FitnessSessionDataBean sPO2FitnessSessionDataBean = new SPO2FitnessSessionDataBean();
            String str = String.valueOf(PreferenceManager.getInstance().getUserId()) + ';' + PreferenceManager.getInstance().getUserDeviceID() + ";spo2;" + AppUtils.formatDate(new Date(manualSPO2.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT2) + ';' + AppUtils.formatDate(new Date(manualSPO2.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT2) + ";1";
            Log.d("FormatorServerToEntity", "getManualSPO2ClientRefId: " + str);
            sPO2FitnessSessionDataBean.setClientRefId(AppUtils.convertStringToMD5(str));
            sPO2FitnessSessionDataBean.setBaseUnit(manualSPO2.isLevelInterpretation() ? "ORDINAL" : "PERCENTAGE");
            sPO2FitnessSessionDataBean.setTzOffset(RepositoryUtils.getTimeZoneOffset());
            sPO2FitnessSessionDataBean.setTotalSampleCount(1);
            sPO2FitnessSessionDataBean.setValue(manualSPO2.getSpo2());
            sPO2FitnessSessionDataBean.setSessionStartDate(AppUtils.formatDate(new Date(manualSPO2.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT));
            sPO2FitnessSessionDataBean.setSessionEndDate(AppUtils.formatDate(new Date(manualSPO2.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT));
            sPO2FitnessSessionDataBean.setType(FitnessRecordType.SPO2.name());
            return sPO2FitnessSessionDataBean;
        }

        @NotNull
        public final SaveManualSPO2DataReq getSaveManualSPO2DataReq(@Nullable List<? extends EntityManualData> list) {
            ArrayList arrayList = new ArrayList();
            if (list != null && (!list.isEmpty())) {
                for (EntityManualData entityManualData : list) {
                    arrayList.add(getSPO2ServerObjectFromEntity(entityManualData));
                }
            }
            return new SaveManualSPO2DataReq(arrayList);
        }

        @NotNull
        public final ManualBpData getServerObjectEntity(@NotNull EntityManualData manualBp) {
            Intrinsics.checkNotNullParameter(manualBp, "manualBp");
            ManualBpData manualBpData = new ManualBpData();
            String str = String.valueOf(PreferenceManager.getInstance().getUserId()) + ';' + PreferenceManager.getInstance().getUserDeviceID() + ";bp;" + AppUtils.formatDate(new Date(manualBp.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT2) + ';' + AppUtils.formatDate(new Date(manualBp.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT2) + ";1";
            Log.d("FormatorServerToEntity", "clientRefString: " + str);
            manualBpData.setClientRefId(AppUtils.convertStringToMD5(str));
            manualBpData.setBaseUnit("MMHG");
            manualBpData.setTzOffset(RepositoryUtils.getTimeZoneOffset());
            manualBpData.setTotalSampleCount(1);
            manualBpData.setValue(CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(manualBp.getSystolicBp()), Integer.valueOf(manualBp.getDiastolicBp())}));
            manualBpData.setSessionStartDate(AppUtils.formatDate(new Date(manualBp.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT));
            manualBpData.setSessionEndDate(AppUtils.formatDate(new Date(manualBp.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT));
            return manualBpData;
        }
    }
}
