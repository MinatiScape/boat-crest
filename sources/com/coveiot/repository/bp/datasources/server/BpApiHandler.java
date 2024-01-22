package com.coveiot.repository.bp.datasources.server;

import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.bp.BpApiManager;
import com.coveiot.coveaccess.bp.GetBpRateDataRes;
import com.coveiot.coveaccess.bp.model.BpData;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.bp.datasources.server.BpApiHandler;
import com.coveiot.repository.bp.datasources.server.FormatorServerToEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class BpApiHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public interface BPDataApiResultListner {
        void onBPDataReceived(@NotNull EntityDailyBp entityDailyBp, @NotNull ArrayList<EntityHourlyBp> arrayList, boolean z);

        void onError(@NotNull String str);
    }

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void fetchDatafor(@NotNull Calendar calendar, @NotNull final BPDataApiResultListner bpDataApiResultListner) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(bpDataApiResultListner, "bpDataApiResultListner");
            BpApiManager.getBpDataFromServer(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetBpRateDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.bp.datasources.server.BpApiHandler$Companion$fetchDatafor$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    BpApiHandler.BPDataApiResultListner bPDataApiResultListner = BpApiHandler.BPDataApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    bPDataApiResultListner.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetBpRateDataRes getBpRateDataRes) {
                    if (getBpRateDataRes != null && !RepositoryUtils.isEmpty(getBpRateDataRes.getBpDataList())) {
                        BpApiHandler.BPDataApiResultListner bPDataApiResultListner = BpApiHandler.BPDataApiResultListner.this;
                        FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                        BpData bpData = getBpRateDataRes.getBpDataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(bpData, "p0.bpDataList.get(0)");
                        EntityDailyBp dailyBpData = companion.getDailyBpData(bpData);
                        BpData bpData2 = getBpRateDataRes.getBpDataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(bpData2, "p0.bpDataList.get(0)");
                        List<EntityHourlyBp> hourlyBPData = companion.getHourlyBPData(bpData2);
                        Intrinsics.checkNotNull(hourlyBPData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.bp.entities.EntityHourlyBp>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.bp.entities.EntityHourlyBp> }");
                        bPDataApiResultListner.onBPDataReceived(dailyBpData, (ArrayList) hourlyBPData, true);
                        return;
                    }
                    BpApiHandler.BPDataApiResultListner.this.onError("No data found");
                }
            });
        }

        public final void fetchDatafor(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final BPDataApiResultListner bpDataApiResultListner) {
            Intrinsics.checkNotNullParameter(fromDate, "fromDate");
            Intrinsics.checkNotNullParameter(toDate, "toDate");
            Intrinsics.checkNotNullParameter(bpDataApiResultListner, "bpDataApiResultListner");
            BpApiManager.getBpDataFromServer(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetBpRateDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.bp.datasources.server.BpApiHandler$Companion$fetchDatafor$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    BpApiHandler.BPDataApiResultListner bPDataApiResultListner = BpApiHandler.BPDataApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    bPDataApiResultListner.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetBpRateDataRes getBpRateDataRes) {
                    if (getBpRateDataRes != null && !RepositoryUtils.isEmpty(getBpRateDataRes.getBpDataList())) {
                        int size = getBpRateDataRes.getBpDataList().size();
                        for (int i = 0; i < size; i++) {
                            if (i == getBpRateDataRes.getBpDataList().size() - 1) {
                                BpApiHandler.BPDataApiResultListner bPDataApiResultListner = BpApiHandler.BPDataApiResultListner.this;
                                FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                                BpData bpData = getBpRateDataRes.getBpDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(bpData, "p0.bpDataList.get(i)");
                                EntityDailyBp dailyBpData = companion.getDailyBpData(bpData);
                                BpData bpData2 = getBpRateDataRes.getBpDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(bpData2, "p0.bpDataList.get(i)");
                                List<EntityHourlyBp> hourlyBPData = companion.getHourlyBPData(bpData2);
                                Intrinsics.checkNotNull(hourlyBPData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.bp.entities.EntityHourlyBp>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.bp.entities.EntityHourlyBp> }");
                                bPDataApiResultListner.onBPDataReceived(dailyBpData, (ArrayList) hourlyBPData, true);
                            } else {
                                BpApiHandler.BPDataApiResultListner bPDataApiResultListner2 = BpApiHandler.BPDataApiResultListner.this;
                                FormatorServerToEntity.Companion companion2 = FormatorServerToEntity.Companion;
                                BpData bpData3 = getBpRateDataRes.getBpDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(bpData3, "p0.bpDataList.get(i)");
                                EntityDailyBp dailyBpData2 = companion2.getDailyBpData(bpData3);
                                BpData bpData4 = getBpRateDataRes.getBpDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(bpData4, "p0.bpDataList.get(i)");
                                List<EntityHourlyBp> hourlyBPData2 = companion2.getHourlyBPData(bpData4);
                                Intrinsics.checkNotNull(hourlyBPData2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.bp.entities.EntityHourlyBp>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.bp.entities.EntityHourlyBp> }");
                                bPDataApiResultListner2.onBPDataReceived(dailyBpData2, (ArrayList) hourlyBPData2, false);
                            }
                        }
                        return;
                    }
                    BpApiHandler.BPDataApiResultListner.this.onError("No data found");
                }
            });
        }
    }
}
