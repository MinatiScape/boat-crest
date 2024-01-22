package com.coveiot.repository.heartrate.datasources.server;

import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.heartrate.GetHeartRateDataRes;
import com.coveiot.coveaccess.heartrate.HearRateApiManager;
import com.coveiot.coveaccess.heartrate.HeartRateData;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.heartrate.datasources.server.FormatorServerToEntity;
import com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class HeartRateApiHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public interface HeartRateDataApiResultListner {
        void onError(@NotNull String str);

        void onHeartRateDataReceived(@NotNull EntityDailyHeartRateData entityDailyHeartRateData, @NotNull ArrayList<EntityHourlyHeartRateData> arrayList, boolean z);
    }

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void fetchDatafor(@NotNull Calendar calendar, @NotNull final HeartRateDataApiResultListner walkDataApiResultListner) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(walkDataApiResultListner, "walkDataApiResultListner");
            HearRateApiManager.getHeartRateDataFromServer(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetHeartRateDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler$Companion$fetchDatafor$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    HeartRateApiHandler.HeartRateDataApiResultListner heartRateDataApiResultListner = HeartRateApiHandler.HeartRateDataApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    heartRateDataApiResultListner.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetHeartRateDataRes getHeartRateDataRes) {
                    if (getHeartRateDataRes != null && !RepositoryUtils.isEmpty(getHeartRateDataRes.getHeartRateDataList())) {
                        HeartRateApiHandler.HeartRateDataApiResultListner heartRateDataApiResultListner = HeartRateApiHandler.HeartRateDataApiResultListner.this;
                        FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                        HeartRateData heartRateData = getHeartRateDataRes.getHeartRateDataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(heartRateData, "p0.heartRateDataList.get(0)");
                        EntityDailyHeartRateData dailyHeartRateData = companion.getDailyHeartRateData(heartRateData);
                        HeartRateData heartRateData2 = getHeartRateDataRes.getHeartRateDataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(heartRateData2, "p0.heartRateDataList.get…                        )");
                        List<EntityHourlyHeartRateData> hourlyHeartRateData = companion.getHourlyHeartRateData(heartRateData2);
                        Intrinsics.checkNotNull(hourlyHeartRateData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.heartrate.EntityHourlyHeartRateData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.heartrate.EntityHourlyHeartRateData> }");
                        heartRateDataApiResultListner.onHeartRateDataReceived(dailyHeartRateData, (ArrayList) hourlyHeartRateData, true);
                        return;
                    }
                    HeartRateApiHandler.HeartRateDataApiResultListner.this.onError("No data found");
                }
            });
        }

        public final void fetchDatafor(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final HeartRateDataApiResultListner walkDataApiResultListner) {
            Intrinsics.checkNotNullParameter(fromDate, "fromDate");
            Intrinsics.checkNotNullParameter(toDate, "toDate");
            Intrinsics.checkNotNullParameter(walkDataApiResultListner, "walkDataApiResultListner");
            HearRateApiManager.getHeartRateDataFromServer(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetHeartRateDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.heartrate.datasources.server.HeartRateApiHandler$Companion$fetchDatafor$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    HeartRateApiHandler.HeartRateDataApiResultListner heartRateDataApiResultListner = HeartRateApiHandler.HeartRateDataApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    heartRateDataApiResultListner.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetHeartRateDataRes getHeartRateDataRes) {
                    if (getHeartRateDataRes != null && !RepositoryUtils.isEmpty(getHeartRateDataRes.getHeartRateDataList())) {
                        int size = getHeartRateDataRes.getHeartRateDataList().size();
                        for (int i = 0; i < size; i++) {
                            if (i == getHeartRateDataRes.getHeartRateDataList().size() - 1) {
                                HeartRateApiHandler.HeartRateDataApiResultListner heartRateDataApiResultListner = HeartRateApiHandler.HeartRateDataApiResultListner.this;
                                FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                                HeartRateData heartRateData = getHeartRateDataRes.getHeartRateDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(heartRateData, "p0.heartRateDataList.get…                        )");
                                EntityDailyHeartRateData dailyHeartRateData = companion.getDailyHeartRateData(heartRateData);
                                HeartRateData heartRateData2 = getHeartRateDataRes.getHeartRateDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(heartRateData2, "p0.heartRateDataList.get…                        )");
                                List<EntityHourlyHeartRateData> hourlyHeartRateData = companion.getHourlyHeartRateData(heartRateData2);
                                Intrinsics.checkNotNull(hourlyHeartRateData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.heartrate.EntityHourlyHeartRateData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.heartrate.EntityHourlyHeartRateData> }");
                                heartRateDataApiResultListner.onHeartRateDataReceived(dailyHeartRateData, (ArrayList) hourlyHeartRateData, true);
                            } else {
                                HeartRateApiHandler.HeartRateDataApiResultListner heartRateDataApiResultListner2 = HeartRateApiHandler.HeartRateDataApiResultListner.this;
                                FormatorServerToEntity.Companion companion2 = FormatorServerToEntity.Companion;
                                HeartRateData heartRateData3 = getHeartRateDataRes.getHeartRateDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(heartRateData3, "p0.heartRateDataList.get…                        )");
                                EntityDailyHeartRateData dailyHeartRateData2 = companion2.getDailyHeartRateData(heartRateData3);
                                HeartRateData heartRateData4 = getHeartRateDataRes.getHeartRateDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(heartRateData4, "p0.heartRateDataList.get…                        )");
                                List<EntityHourlyHeartRateData> hourlyHeartRateData2 = companion2.getHourlyHeartRateData(heartRateData4);
                                Intrinsics.checkNotNull(hourlyHeartRateData2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.heartrate.EntityHourlyHeartRateData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.heartrate.EntityHourlyHeartRateData> }");
                                heartRateDataApiResultListner2.onHeartRateDataReceived(dailyHeartRateData2, (ArrayList) hourlyHeartRateData2, false);
                            }
                        }
                        return;
                    }
                    HeartRateApiHandler.HeartRateDataApiResultListner.this.onError("No data found");
                }
            });
        }
    }
}
