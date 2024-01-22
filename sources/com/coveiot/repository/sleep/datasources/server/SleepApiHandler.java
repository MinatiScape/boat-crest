package com.coveiot.repository.sleep.datasources.server;

import com.coveiot.coveaccess.CoveActivityTracker;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.model.ActivityData;
import com.coveiot.coveaccess.fitness.model.ActivityDataModel;
import com.coveiot.coveaccess.fitnessdataapi.FitnessDataApiManager;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetSleepData;
import com.coveiot.coveaccess.model.server.SleepDataBean;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.sleep.datasources.server.FormatorServerToEntity;
import com.coveiot.repository.sleep.datasources.server.SleepApiHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class SleepApiHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public interface SleepDataApiResultListner {
        void onError(@NotNull String str);

        void onSleepDataReceived(@NotNull DailySleepData dailySleepData, @NotNull ArrayList<HourlySleepData> arrayList, boolean z);
    }

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void fetchDatafor(@NotNull Calendar calendar, @NotNull final SleepDataApiResultListner walkDataApiResultListner) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(walkDataApiResultListner, "walkDataApiResultListner");
            CoveActivityTracker.getActivityDataFromServer(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), ActivityType.SLEEP, true, new CoveApiListener<ActivityData, CoveApiErrorModel>() { // from class: com.coveiot.repository.sleep.datasources.server.SleepApiHandler$Companion$fetchDatafor$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SleepApiHandler.SleepDataApiResultListner sleepDataApiResultListner = SleepApiHandler.SleepDataApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    sleepDataApiResultListner.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable ActivityData activityData) {
                    if (activityData != null && !RepositoryUtils.isEmpty(activityData.data)) {
                        SleepApiHandler.SleepDataApiResultListner sleepDataApiResultListner = SleepApiHandler.SleepDataApiResultListner.this;
                        FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                        ActivityDataModel activityDataModel = activityData.data.get(0);
                        Intrinsics.checkNotNullExpressionValue(activityDataModel, "p0.data.get(0)");
                        DailySleepData sleepDataFrom = companion.getSleepDataFrom(activityDataModel);
                        ActivityDataModel activityDataModel2 = activityData.data.get(0);
                        Intrinsics.checkNotNullExpressionValue(activityDataModel2, "p0.data.get(0)");
                        List<HourlySleepData> hourlySleepData = companion.getHourlySleepData(activityDataModel2);
                        Intrinsics.checkNotNull(hourlySleepData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.sleep.HourlySleepData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.sleep.HourlySleepData> }");
                        sleepDataApiResultListner.onSleepDataReceived(sleepDataFrom, (ArrayList) hourlySleepData, true);
                        return;
                    }
                    SleepApiHandler.SleepDataApiResultListner.this.onError("No data found");
                }
            });
        }

        public final void fetchDatafor(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final SleepDataApiResultListner walkDataApiResultListner) {
            Intrinsics.checkNotNullParameter(fromDate, "fromDate");
            Intrinsics.checkNotNullParameter(toDate, "toDate");
            Intrinsics.checkNotNullParameter(walkDataApiResultListner, "walkDataApiResultListner");
            FitnessDataApiManager.getSleepDataFromServer(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<SGetSleepData, CoveApiErrorModel>() { // from class: com.coveiot.repository.sleep.datasources.server.SleepApiHandler$Companion$fetchDatafor$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SleepApiHandler.SleepDataApiResultListner sleepDataApiResultListner = SleepApiHandler.SleepDataApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    sleepDataApiResultListner.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SGetSleepData sGetSleepData) {
                    if (sGetSleepData != null && sGetSleepData.getDataBean() != null && !RepositoryUtils.isEmpty(sGetSleepData.getDataBean().getFitnessData())) {
                        int size = sGetSleepData.getDataBean().getFitnessData().size();
                        for (int i = 0; i < size; i++) {
                            if (i == sGetSleepData.getDataBean().getFitnessData().size() - 1) {
                                SleepApiHandler.SleepDataApiResultListner sleepDataApiResultListner = SleepApiHandler.SleepDataApiResultListner.this;
                                FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                                SleepDataBean sleepDataBean = sGetSleepData.getDataBean().getFitnessData().get(i);
                                Intrinsics.checkNotNullExpressionValue(sleepDataBean, "p0.dataBean.fitnessData.get(i)");
                                DailySleepData sleepDataFrom = companion.getSleepDataFrom(sleepDataBean);
                                SleepDataBean sleepDataBean2 = sGetSleepData.getDataBean().getFitnessData().get(i);
                                Intrinsics.checkNotNullExpressionValue(sleepDataBean2, "p0.dataBean.fitnessData.…                        )");
                                List<HourlySleepData> hourlySleepData = companion.getHourlySleepData(sleepDataBean2);
                                Intrinsics.checkNotNull(hourlySleepData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.sleep.HourlySleepData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.sleep.HourlySleepData> }");
                                sleepDataApiResultListner.onSleepDataReceived(sleepDataFrom, (ArrayList) hourlySleepData, true);
                            } else {
                                SleepApiHandler.SleepDataApiResultListner sleepDataApiResultListner2 = SleepApiHandler.SleepDataApiResultListner.this;
                                FormatorServerToEntity.Companion companion2 = FormatorServerToEntity.Companion;
                                SleepDataBean sleepDataBean3 = sGetSleepData.getDataBean().getFitnessData().get(i);
                                Intrinsics.checkNotNullExpressionValue(sleepDataBean3, "p0.dataBean.fitnessData.get(i)");
                                DailySleepData sleepDataFrom2 = companion2.getSleepDataFrom(sleepDataBean3);
                                SleepDataBean sleepDataBean4 = sGetSleepData.getDataBean().getFitnessData().get(i);
                                Intrinsics.checkNotNullExpressionValue(sleepDataBean4, "p0.dataBean.fitnessData.…                        )");
                                List<HourlySleepData> hourlySleepData2 = companion2.getHourlySleepData(sleepDataBean4);
                                Intrinsics.checkNotNull(hourlySleepData2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.sleep.HourlySleepData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.sleep.HourlySleepData> }");
                                sleepDataApiResultListner2.onSleepDataReceived(sleepDataFrom2, (ArrayList) hourlySleepData2, false);
                            }
                        }
                        return;
                    }
                    SleepApiHandler.SleepDataApiResultListner.this.onError("No data found");
                }
            });
        }
    }
}
