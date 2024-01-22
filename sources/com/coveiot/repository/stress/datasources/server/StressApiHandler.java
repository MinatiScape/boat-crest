package com.coveiot.repository.stress.datasources.server;

import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.PeriodicStressData;
import com.coveiot.coveaccess.stress.GetStressDataRes;
import com.coveiot.coveaccess.stress.StressApiManager;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.stress.datasources.server.FormatorServerToEntity;
import com.coveiot.repository.stress.datasources.server.StressApiHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class StressApiHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public interface StressApiResultListener {
        void onError(@NotNull String str);

        void onStressDataReceived(@NotNull DailyStress dailyStress, @NotNull ArrayList<HourlyStress> arrayList, boolean z);
    }

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void fetchDatafor(@NotNull Calendar calendar, @NotNull final StressApiResultListener stressDataApiResultListener) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(stressDataApiResultListener, "stressDataApiResultListener");
            StressApiManager.getStressDataFromServer(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetStressDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.stress.datasources.server.StressApiHandler$Companion$fetchDatafor$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    StressApiHandler.StressApiResultListener stressApiResultListener = StressApiHandler.StressApiResultListener.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    stressApiResultListener.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetStressDataRes getStressDataRes) {
                    if (getStressDataRes != null && !RepositoryUtils.isEmpty(getStressDataRes.getStressDataList())) {
                        StressApiHandler.StressApiResultListener stressApiResultListener = StressApiHandler.StressApiResultListener.this;
                        FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                        PeriodicStressData periodicStressData = getStressDataRes.getStressDataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(periodicStressData, "p0.stressDataList.get(0)");
                        DailyStress dailyStressData = companion.getDailyStressData(periodicStressData);
                        PeriodicStressData periodicStressData2 = getStressDataRes.getStressDataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(periodicStressData2, "p0.stressDataList.get(\n …                        )");
                        List<HourlyStress> hourlyStressData = companion.getHourlyStressData(periodicStressData2);
                        Intrinsics.checkNotNull(hourlyStressData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.stress.entity.HourlyStress>");
                        stressApiResultListener.onStressDataReceived(dailyStressData, (ArrayList) hourlyStressData, true);
                        return;
                    }
                    StressApiHandler.StressApiResultListener.this.onError("No data found");
                }
            });
        }

        public final void fetchDatafor(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final StressApiResultListener stressApiResultListener) {
            Intrinsics.checkNotNullParameter(fromDate, "fromDate");
            Intrinsics.checkNotNullParameter(toDate, "toDate");
            Intrinsics.checkNotNullParameter(stressApiResultListener, "stressApiResultListener");
            StressApiManager.getStressDataFromServer(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetStressDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.stress.datasources.server.StressApiHandler$Companion$fetchDatafor$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    StressApiHandler.StressApiResultListener stressApiResultListener2 = StressApiHandler.StressApiResultListener.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    stressApiResultListener2.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetStressDataRes getStressDataRes) {
                    if (getStressDataRes != null && !RepositoryUtils.isEmpty(getStressDataRes.getStressDataList())) {
                        int size = getStressDataRes.getStressDataList().size();
                        for (int i = 0; i < size; i++) {
                            if (i == getStressDataRes.getStressDataList().size() - 1) {
                                StressApiHandler.StressApiResultListener stressApiResultListener2 = StressApiHandler.StressApiResultListener.this;
                                FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                                PeriodicStressData periodicStressData = getStressDataRes.getStressDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(periodicStressData, "p0.stressDataList.get(\n …                        )");
                                DailyStress dailyStressData = companion.getDailyStressData(periodicStressData);
                                PeriodicStressData periodicStressData2 = getStressDataRes.getStressDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(periodicStressData2, "p0.stressDataList.get(\n …                        )");
                                List<HourlyStress> hourlyStressData = companion.getHourlyStressData(periodicStressData2);
                                Intrinsics.checkNotNull(hourlyStressData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.stress.entity.HourlyStress>");
                                stressApiResultListener2.onStressDataReceived(dailyStressData, (ArrayList) hourlyStressData, true);
                            } else {
                                StressApiHandler.StressApiResultListener stressApiResultListener3 = StressApiHandler.StressApiResultListener.this;
                                FormatorServerToEntity.Companion companion2 = FormatorServerToEntity.Companion;
                                PeriodicStressData periodicStressData3 = getStressDataRes.getStressDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(periodicStressData3, "p0.stressDataList.get(\n …                        )");
                                DailyStress dailyStressData2 = companion2.getDailyStressData(periodicStressData3);
                                PeriodicStressData periodicStressData4 = getStressDataRes.getStressDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(periodicStressData4, "p0.stressDataList.get(\n …                        )");
                                List<HourlyStress> hourlyStressData2 = companion2.getHourlyStressData(periodicStressData4);
                                Intrinsics.checkNotNull(hourlyStressData2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.stress.entity.HourlyStress>");
                                stressApiResultListener3.onStressDataReceived(dailyStressData2, (ArrayList) hourlyStressData2, false);
                            }
                        }
                        return;
                    }
                    StressApiHandler.StressApiResultListener.this.onError("No data found");
                }
            });
        }
    }
}
