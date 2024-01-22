package com.coveiot.repository.walk.datasources.server;

import com.coveiot.coveaccess.CoveActivityTracker;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.model.ActivityData;
import com.coveiot.coveaccess.fitness.model.ActivityDataModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.walk.datasources.server.FormatorServerToEntity;
import com.coveiot.repository.walk.datasources.server.WalkApiHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class WalkApiHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public interface WalkDataApiResultListner {
        void onError(@NotNull String str);

        void onWalkDataReceived(@NotNull DailyWalkData dailyWalkData, @NotNull ArrayList<HourlyWalkData> arrayList, boolean z);
    }

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void fetchDatafor(@NotNull Calendar calendar, @NotNull final WalkDataApiResultListner walkDataApiResultListner) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(walkDataApiResultListner, "walkDataApiResultListner");
            CoveActivityTracker.getActivityDataFromServer(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), ActivityType.WALK, true, new CoveApiListener<ActivityData, CoveApiErrorModel>() { // from class: com.coveiot.repository.walk.datasources.server.WalkApiHandler$Companion$fetchDatafor$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    WalkApiHandler.WalkDataApiResultListner walkDataApiResultListner2 = WalkApiHandler.WalkDataApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    walkDataApiResultListner2.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable ActivityData activityData) {
                    if (activityData != null && !RepositoryUtils.isEmpty(activityData.data)) {
                        WalkApiHandler.WalkDataApiResultListner walkDataApiResultListner2 = WalkApiHandler.WalkDataApiResultListner.this;
                        FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                        ActivityDataModel activityDataModel = activityData.data.get(0);
                        Intrinsics.checkNotNullExpressionValue(activityDataModel, "p0.data.get(0)");
                        DailyWalkData dailyWalkDataFrom = companion.getDailyWalkDataFrom(activityDataModel);
                        ActivityDataModel activityDataModel2 = activityData.data.get(0);
                        Intrinsics.checkNotNullExpressionValue(activityDataModel2, "p0.data.get(0)");
                        List<HourlyWalkData> hourlyWalkWalkData = companion.getHourlyWalkWalkData(activityDataModel2);
                        Intrinsics.checkNotNull(hourlyWalkWalkData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.walk.entities.HourlyWalkData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.walk.entities.HourlyWalkData> }");
                        walkDataApiResultListner2.onWalkDataReceived(dailyWalkDataFrom, (ArrayList) hourlyWalkWalkData, true);
                        return;
                    }
                    WalkApiHandler.WalkDataApiResultListner.this.onError("No data found");
                }
            });
        }

        public final void fetchDatafor(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final WalkDataApiResultListner walkDataApiResultListner) {
            Intrinsics.checkNotNullParameter(fromDate, "fromDate");
            Intrinsics.checkNotNullParameter(toDate, "toDate");
            Intrinsics.checkNotNullParameter(walkDataApiResultListner, "walkDataApiResultListner");
            CoveActivityTracker.getActivityDataFromServer(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), ActivityType.WALK, true, new CoveApiListener<ActivityData, CoveApiErrorModel>() { // from class: com.coveiot.repository.walk.datasources.server.WalkApiHandler$Companion$fetchDatafor$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    WalkApiHandler.WalkDataApiResultListner walkDataApiResultListner2 = WalkApiHandler.WalkDataApiResultListner.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    walkDataApiResultListner2.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable ActivityData activityData) {
                    if (activityData != null && !RepositoryUtils.isEmpty(activityData.data)) {
                        int size = activityData.data.size();
                        for (int i = 0; i < size; i++) {
                            if (i == activityData.data.size() - 1) {
                                WalkApiHandler.WalkDataApiResultListner walkDataApiResultListner2 = WalkApiHandler.WalkDataApiResultListner.this;
                                FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                                ActivityDataModel activityDataModel = activityData.data.get(i);
                                Intrinsics.checkNotNullExpressionValue(activityDataModel, "p0.data.get(i)");
                                DailyWalkData dailyWalkDataFrom = companion.getDailyWalkDataFrom(activityDataModel);
                                ActivityDataModel activityDataModel2 = activityData.data.get(i);
                                Intrinsics.checkNotNullExpressionValue(activityDataModel2, "p0.data.get(i)");
                                List<HourlyWalkData> hourlyWalkWalkData = companion.getHourlyWalkWalkData(activityDataModel2);
                                Intrinsics.checkNotNull(hourlyWalkWalkData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.walk.entities.HourlyWalkData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.walk.entities.HourlyWalkData> }");
                                walkDataApiResultListner2.onWalkDataReceived(dailyWalkDataFrom, (ArrayList) hourlyWalkWalkData, true);
                            } else {
                                WalkApiHandler.WalkDataApiResultListner walkDataApiResultListner3 = WalkApiHandler.WalkDataApiResultListner.this;
                                FormatorServerToEntity.Companion companion2 = FormatorServerToEntity.Companion;
                                ActivityDataModel activityDataModel3 = activityData.data.get(i);
                                Intrinsics.checkNotNullExpressionValue(activityDataModel3, "p0.data.get(i)");
                                DailyWalkData dailyWalkDataFrom2 = companion2.getDailyWalkDataFrom(activityDataModel3);
                                ActivityDataModel activityDataModel4 = activityData.data.get(i);
                                Intrinsics.checkNotNullExpressionValue(activityDataModel4, "p0.data.get(i)");
                                List<HourlyWalkData> hourlyWalkWalkData2 = companion2.getHourlyWalkWalkData(activityDataModel4);
                                Intrinsics.checkNotNull(hourlyWalkWalkData2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.walk.entities.HourlyWalkData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.covedb.walk.entities.HourlyWalkData> }");
                                walkDataApiResultListner3.onWalkDataReceived(dailyWalkDataFrom2, (ArrayList) hourlyWalkWalkData2, false);
                            }
                        }
                        return;
                    }
                    WalkApiHandler.WalkDataApiResultListner.this.onError("No data found");
                }
            });
        }
    }
}
