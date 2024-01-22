package com.coveiot.repository.hrv.datasource.server;

import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.hrvdata.HRVDataNew;
import com.coveiot.coveaccess.hrvdata.NewHRVAPIManager;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SNewGetHrvDataRes;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.hrv.datasource.server.FormatorServerToEntity;
import com.coveiot.repository.hrv.datasource.server.HRVApiHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class HRVApiHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void fetchDatafor(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final StressApiResultListener stressApiResultListener) {
            Intrinsics.checkNotNullParameter(fromDate, "fromDate");
            Intrinsics.checkNotNullParameter(toDate, "toDate");
            Intrinsics.checkNotNullParameter(stressApiResultListener, "stressApiResultListener");
            NewHRVAPIManager.getHrvDataFromServer(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<SNewGetHrvDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.hrv.datasource.server.HRVApiHandler$Companion$fetchDatafor$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    HRVApiHandler.StressApiResultListener stressApiResultListener2 = HRVApiHandler.StressApiResultListener.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    stressApiResultListener2.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SNewGetHrvDataRes sNewGetHrvDataRes) {
                    if (sNewGetHrvDataRes != null && sNewGetHrvDataRes.getData() != null && !RepositoryUtils.isEmpty(sNewGetHrvDataRes.getData().getFitnessData())) {
                        int size = sNewGetHrvDataRes.getData().getFitnessData().size();
                        for (int i = 0; i < size; i++) {
                            if (i == sNewGetHrvDataRes.getData().getFitnessData().size() - 1) {
                                HRVApiHandler.StressApiResultListener stressApiResultListener2 = HRVApiHandler.StressApiResultListener.this;
                                FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                                HRVDataNew hRVDataNew = sNewGetHrvDataRes.getData().getFitnessData().get(i);
                                Intrinsics.checkNotNullExpressionValue(hRVDataNew, "p0.data.fitnessData.get(…                        )");
                                DailyHRV dailyStressData = companion.getDailyStressData(hRVDataNew);
                                HRVDataNew hRVDataNew2 = sNewGetHrvDataRes.getData().getFitnessData().get(i);
                                Intrinsics.checkNotNullExpressionValue(hRVDataNew2, "p0.data.fitnessData.get(…                        )");
                                List<HourlyHRV> hourlyStressData = companion.getHourlyStressData(hRVDataNew2);
                                Intrinsics.checkNotNull(hourlyStressData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.hrv.entity.HourlyHRV>");
                                stressApiResultListener2.onStressDataReceived(dailyStressData, (ArrayList) hourlyStressData, true);
                            } else {
                                HRVApiHandler.StressApiResultListener stressApiResultListener3 = HRVApiHandler.StressApiResultListener.this;
                                FormatorServerToEntity.Companion companion2 = FormatorServerToEntity.Companion;
                                HRVDataNew hRVDataNew3 = sNewGetHrvDataRes.getData().getFitnessData().get(i);
                                Intrinsics.checkNotNullExpressionValue(hRVDataNew3, "p0.data.fitnessData.get(…                        )");
                                DailyHRV dailyStressData2 = companion2.getDailyStressData(hRVDataNew3);
                                HRVDataNew hRVDataNew4 = sNewGetHrvDataRes.getData().getFitnessData().get(i);
                                Intrinsics.checkNotNullExpressionValue(hRVDataNew4, "p0.data.fitnessData.get(…                        )");
                                List<HourlyHRV> hourlyStressData2 = companion2.getHourlyStressData(hRVDataNew4);
                                Intrinsics.checkNotNull(hourlyStressData2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.hrv.entity.HourlyHRV>");
                                stressApiResultListener3.onStressDataReceived(dailyStressData2, (ArrayList) hourlyStressData2, false);
                            }
                        }
                        return;
                    }
                    HRVApiHandler.StressApiResultListener.this.onError("No data found");
                }
            });
        }
    }

    /* loaded from: classes9.dex */
    public interface StressApiResultListener {
        void onError(@NotNull String str);

        void onStressDataReceived(@NotNull DailyHRV dailyHRV, @NotNull ArrayList<HourlyHRV> arrayList, boolean z);
    }
}
