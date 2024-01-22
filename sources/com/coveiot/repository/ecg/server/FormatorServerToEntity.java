package com.coveiot.repository.ecg.server;

import android.util.Log;
import com.coveiot.coveaccess.ecgsession.model.EcgGraph;
import com.coveiot.coveaccess.ecgsession.model.FitnessSessionDatum;
import com.coveiot.coveaccess.mediauplaod.model.MediaClassType;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covedb.ecg.EntityECGSummaryData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.collections.CollectionsKt__CollectionsKt;
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
        public final String getFormattedDate(@NotNull SimpleDateFormat originalFormat, @NotNull SimpleDateFormat targetFormat, @NotNull String inputDate) {
            Intrinsics.checkNotNullParameter(originalFormat, "originalFormat");
            Intrinsics.checkNotNullParameter(targetFormat, "targetFormat");
            Intrinsics.checkNotNullParameter(inputDate, "inputDate");
            String format = targetFormat.format(originalFormat.parse(inputDate));
            Intrinsics.checkNotNullExpressionValue(format, "targetFormat.format(orig…lFormat.parse(inputDate))");
            return format;
        }

        @NotNull
        public final FitnessSessionDatum getSummaryEcgData(@NotNull EntityECGSummaryData ecgSessionData, int i) {
            Intrinsics.checkNotNullParameter(ecgSessionData, "ecgSessionData");
            FitnessSessionDatum fitnessSessionDatum = new FitnessSessionDatum();
            String valueOf = String.valueOf(PreferenceManager.getInstance().getUserId());
            String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
            Locale locale = Locale.ENGLISH;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT, locale);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT2, locale);
            String str = ecgSessionData.startDateTime;
            Intrinsics.checkNotNullExpressionValue(str, "ecgSessionData.startDateTime");
            String formattedDate = getFormattedDate(simpleDateFormat, simpleDateFormat2, str);
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT, locale);
            SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT2, locale);
            String str2 = ecgSessionData.endDateTime;
            Intrinsics.checkNotNullExpressionValue(str2, "ecgSessionData.endDateTime");
            String str3 = valueOf + ';' + userDeviceID + ";ecg;" + formattedDate + ';' + getFormattedDate(simpleDateFormat3, simpleDateFormat4, str2) + ';' + i;
            Log.d("FormatorServerToEntity", "clientRefString: " + str3);
            fitnessSessionDatum.setClientRefId(AppUtils.convertStringToMD5(str3));
            fitnessSessionDatum.setSessionStartDate(ecgSessionData.startDateTime);
            fitnessSessionDatum.setSessionEndDate(ecgSessionData.endDateTime);
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(ecgSessionData.heart_rate));
            fitnessSessionDatum.setHrValues(arrayList);
            fitnessSessionDatum.setHrv(Integer.valueOf(ecgSessionData.hrv_value));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(0, CollectionsKt__CollectionsKt.arrayListOf(Integer.valueOf(ecgSessionData.high_bp), Integer.valueOf(ecgSessionData.low_bp)));
            fitnessSessionDatum.setBpValues(arrayList2);
            fitnessSessionDatum.setStressLevel(Integer.valueOf(ecgSessionData.stress));
            fitnessSessionDatum.setBaseUnit("MILLIVOLTS");
            fitnessSessionDatum.setType("ECG");
            EcgGraph ecgGraph = new EcgGraph();
            ecgGraph.setMediaId(ecgSessionData.mediaID);
            ecgGraph.setMediaClass(MediaClassType.FIT_ECG_GRAPH_TYPE1.toString());
            fitnessSessionDatum.setEcgGraph(ecgGraph);
            fitnessSessionDatum.setTotalSampleCount(Integer.valueOf(i));
            return fitnessSessionDatum;
        }
    }
}
