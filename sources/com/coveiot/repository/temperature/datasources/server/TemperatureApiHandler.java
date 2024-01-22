package com.coveiot.repository.temperature.datasources.server;

import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.temperature.GetTemperatureDataRes;
import com.coveiot.coveaccess.temperature.TemperatureApiManager;
import com.coveiot.coveaccess.temperature.model.TemperatureData;
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.temperature.datasources.server.FormatorServerToEntity;
import com.coveiot.repository.temperature.datasources.server.TemperatureApiHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class TemperatureApiHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public interface TemperatureApiResultListner {
        void onError(@NotNull String str);

        void onTemperatureDataReceived(@NotNull DailyTemperature dailyTemperature, @NotNull ArrayList<HourlyTemperature> arrayList, boolean z);
    }

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void fetchDatafor(@NotNull Calendar calendar, @NotNull final TemperatureApiResultListner temperatureDataApiResultListner) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(temperatureDataApiResultListner, "temperatureDataApiResultListner");
            TemperatureApiManager.getTemperatureFromServer(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetTemperatureDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.temperature.datasources.server.TemperatureApiHandler$Companion$fetchDatafor$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    TemperatureApiHandler.TemperatureApiResultListner temperatureApiResultListner = TemperatureApiHandler.TemperatureApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    temperatureApiResultListner.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetTemperatureDataRes getTemperatureDataRes) {
                    if (getTemperatureDataRes != null && !RepositoryUtils.isEmpty(getTemperatureDataRes.getTemperatureDataList())) {
                        TemperatureApiHandler.TemperatureApiResultListner temperatureApiResultListner = TemperatureApiHandler.TemperatureApiResultListner.this;
                        FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                        TemperatureData temperatureData = getTemperatureDataRes.getTemperatureDataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(temperatureData, "p0.temperatureDataList.get(0)");
                        DailyTemperature dailyTemperatureData = companion.getDailyTemperatureData(temperatureData);
                        TemperatureData temperatureData2 = getTemperatureDataRes.getTemperatureDataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(temperatureData2, "p0.temperatureDataList.g…                        )");
                        List<HourlyTemperature> hourlyTemperatureData = companion.getHourlyTemperatureData(temperatureData2);
                        Intrinsics.checkNotNull(hourlyTemperatureData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.temperature.entity.HourlyTemperature>");
                        temperatureApiResultListner.onTemperatureDataReceived(dailyTemperatureData, (ArrayList) hourlyTemperatureData, true);
                        return;
                    }
                    TemperatureApiHandler.TemperatureApiResultListner.this.onError("No data found");
                }
            });
        }

        public final void fetchDatafor(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final TemperatureApiResultListner temperatureApiResultListner) {
            Intrinsics.checkNotNullParameter(fromDate, "fromDate");
            Intrinsics.checkNotNullParameter(toDate, "toDate");
            Intrinsics.checkNotNullParameter(temperatureApiResultListner, "temperatureApiResultListner");
            TemperatureApiManager.getTemperatureFromServer(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetTemperatureDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.temperature.datasources.server.TemperatureApiHandler$Companion$fetchDatafor$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    TemperatureApiHandler.TemperatureApiResultListner temperatureApiResultListner2 = TemperatureApiHandler.TemperatureApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    temperatureApiResultListner2.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetTemperatureDataRes getTemperatureDataRes) {
                    if (getTemperatureDataRes != null && !RepositoryUtils.isEmpty(getTemperatureDataRes.getTemperatureDataList())) {
                        int size = getTemperatureDataRes.getTemperatureDataList().size();
                        for (int i = 0; i < size; i++) {
                            if (i == getTemperatureDataRes.getTemperatureDataList().size() - 1) {
                                TemperatureApiHandler.TemperatureApiResultListner temperatureApiResultListner2 = TemperatureApiHandler.TemperatureApiResultListner.this;
                                FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                                TemperatureData temperatureData = getTemperatureDataRes.getTemperatureDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(temperatureData, "p0.temperatureDataList.g…                        )");
                                DailyTemperature dailyTemperatureData = companion.getDailyTemperatureData(temperatureData);
                                TemperatureData temperatureData2 = getTemperatureDataRes.getTemperatureDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(temperatureData2, "p0.temperatureDataList.g…                        )");
                                List<HourlyTemperature> hourlyTemperatureData = companion.getHourlyTemperatureData(temperatureData2);
                                Intrinsics.checkNotNull(hourlyTemperatureData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.temperature.entity.HourlyTemperature>");
                                temperatureApiResultListner2.onTemperatureDataReceived(dailyTemperatureData, (ArrayList) hourlyTemperatureData, true);
                            } else {
                                TemperatureApiHandler.TemperatureApiResultListner temperatureApiResultListner3 = TemperatureApiHandler.TemperatureApiResultListner.this;
                                FormatorServerToEntity.Companion companion2 = FormatorServerToEntity.Companion;
                                TemperatureData temperatureData3 = getTemperatureDataRes.getTemperatureDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(temperatureData3, "p0.temperatureDataList.g…                        )");
                                DailyTemperature dailyTemperatureData2 = companion2.getDailyTemperatureData(temperatureData3);
                                TemperatureData temperatureData4 = getTemperatureDataRes.getTemperatureDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(temperatureData4, "p0.temperatureDataList.g…                        )");
                                List<HourlyTemperature> hourlyTemperatureData2 = companion2.getHourlyTemperatureData(temperatureData4);
                                Intrinsics.checkNotNull(hourlyTemperatureData2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.temperature.entity.HourlyTemperature>");
                                temperatureApiResultListner3.onTemperatureDataReceived(dailyTemperatureData2, (ArrayList) hourlyTemperatureData2, false);
                            }
                        }
                        return;
                    }
                    TemperatureApiHandler.TemperatureApiResultListner.this.onError("No data found");
                }
            });
        }
    }
}
