package com.coveiot.repository.periodicspo2.server;

import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.spo2.GetSpo2DataRes;
import com.coveiot.coveaccess.spo2.Spo2ApiManager;
import com.coveiot.coveaccess.spo2.Spo2Data;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.periodicspo2.server.FormatorServerToEntity;
import com.coveiot.repository.periodicspo2.server.PeriodicSpo2ApiHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class PeriodicSpo2ApiHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public interface PeriodicSpo2ApiResultListner {
        void onError(@NotNull String str);

        void onPeriodicSpo2DataReceived(@NotNull DailyPeriodicSpo2 dailyPeriodicSpo2, @NotNull ArrayList<EntityHourlySpo2> arrayList, boolean z);
    }

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void fetchDatafor(@NotNull Calendar calendar, @NotNull final PeriodicSpo2ApiResultListner periodicSpo2DataApiResultListner) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(periodicSpo2DataApiResultListner, "periodicSpo2DataApiResultListner");
            Spo2ApiManager.getSpo2FromServer(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetSpo2DataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.periodicspo2.server.PeriodicSpo2ApiHandler$Companion$fetchDatafor$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner periodicSpo2ApiResultListner = PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    periodicSpo2ApiResultListner.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetSpo2DataRes getSpo2DataRes) {
                    if (getSpo2DataRes != null && !RepositoryUtils.isEmpty(getSpo2DataRes.getSpo2DataList())) {
                        PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner periodicSpo2ApiResultListner = PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner.this;
                        FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                        Spo2Data spo2Data = getSpo2DataRes.getSpo2DataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(spo2Data, "p0.spo2DataList.get(0)");
                        DailyPeriodicSpo2 dailyPeriodicSpo2Data = companion.getDailyPeriodicSpo2Data(spo2Data);
                        Spo2Data spo2Data2 = getSpo2DataRes.getSpo2DataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(spo2Data2, "p0.spo2DataList.get(\n   …                        )");
                        List<EntityHourlySpo2> hourlyPeriodicSpo2Data = companion.getHourlyPeriodicSpo2Data(spo2Data2);
                        Intrinsics.checkNotNull(hourlyPeriodicSpo2Data, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.spo2.entity.EntityHourlySpo2>");
                        periodicSpo2ApiResultListner.onPeriodicSpo2DataReceived(dailyPeriodicSpo2Data, (ArrayList) hourlyPeriodicSpo2Data, true);
                        return;
                    }
                    PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner.this.onError("No data found");
                }
            });
        }

        public final void fetchDatafor(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final PeriodicSpo2ApiResultListner periodicSpo2ApiResultListner) {
            Intrinsics.checkNotNullParameter(fromDate, "fromDate");
            Intrinsics.checkNotNullParameter(toDate, "toDate");
            Intrinsics.checkNotNullParameter(periodicSpo2ApiResultListner, "periodicSpo2ApiResultListner");
            Spo2ApiManager.getSpo2FromServer(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetSpo2DataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.periodicspo2.server.PeriodicSpo2ApiHandler$Companion$fetchDatafor$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner periodicSpo2ApiResultListner2 = PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    periodicSpo2ApiResultListner2.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetSpo2DataRes getSpo2DataRes) {
                    if (getSpo2DataRes != null && !RepositoryUtils.isEmpty(getSpo2DataRes.getSpo2DataList())) {
                        int size = getSpo2DataRes.getSpo2DataList().size();
                        for (int i = 0; i < size; i++) {
                            if (i == getSpo2DataRes.getSpo2DataList().size() - 1) {
                                PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner periodicSpo2ApiResultListner2 = PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner.this;
                                FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                                Spo2Data spo2Data = getSpo2DataRes.getSpo2DataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(spo2Data, "p0.spo2DataList.get(\n   …                        )");
                                DailyPeriodicSpo2 dailyPeriodicSpo2Data = companion.getDailyPeriodicSpo2Data(spo2Data);
                                Spo2Data spo2Data2 = getSpo2DataRes.getSpo2DataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(spo2Data2, "p0.spo2DataList.get(\n   …                        )");
                                List<EntityHourlySpo2> hourlyPeriodicSpo2Data = companion.getHourlyPeriodicSpo2Data(spo2Data2);
                                Intrinsics.checkNotNull(hourlyPeriodicSpo2Data, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.spo2.entity.EntityHourlySpo2>");
                                periodicSpo2ApiResultListner2.onPeriodicSpo2DataReceived(dailyPeriodicSpo2Data, (ArrayList) hourlyPeriodicSpo2Data, true);
                            } else {
                                PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner periodicSpo2ApiResultListner3 = PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner.this;
                                FormatorServerToEntity.Companion companion2 = FormatorServerToEntity.Companion;
                                Spo2Data spo2Data3 = getSpo2DataRes.getSpo2DataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(spo2Data3, "p0.spo2DataList.get(\n   …                        )");
                                DailyPeriodicSpo2 dailyPeriodicSpo2Data2 = companion2.getDailyPeriodicSpo2Data(spo2Data3);
                                Spo2Data spo2Data4 = getSpo2DataRes.getSpo2DataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(spo2Data4, "p0.spo2DataList.get(\n   …                        )");
                                List<EntityHourlySpo2> hourlyPeriodicSpo2Data2 = companion2.getHourlyPeriodicSpo2Data(spo2Data4);
                                Intrinsics.checkNotNull(hourlyPeriodicSpo2Data2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.spo2.entity.EntityHourlySpo2>");
                                periodicSpo2ApiResultListner3.onPeriodicSpo2DataReceived(dailyPeriodicSpo2Data2, (ArrayList) hourlyPeriodicSpo2Data2, false);
                            }
                        }
                        return;
                    }
                    PeriodicSpo2ApiHandler.PeriodicSpo2ApiResultListner.this.onError("No data found");
                }
            });
        }
    }
}
