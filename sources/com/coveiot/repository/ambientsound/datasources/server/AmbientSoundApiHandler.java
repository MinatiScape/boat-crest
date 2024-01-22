package com.coveiot.repository.ambientsound.datasources.server;

import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.ambientsound.AmbientSoundApiManager;
import com.coveiot.coveaccess.ambientsound.AmbientSoundData;
import com.coveiot.coveaccess.ambientsound.GetAmbientSoundDataRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.ambientsound.datasources.server.AmbientSoundApiHandler;
import com.coveiot.repository.ambientsound.datasources.server.FormatterServerToEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class AmbientSoundApiHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes9.dex */
    public interface AmbientSoundDataApiResultListener {
        void onAmbientSoundDataReceived(@NotNull EntityDailyAmbientSoundData entityDailyAmbientSoundData, @NotNull ArrayList<EntityHourlyAmbientSoundData> arrayList, boolean z);

        void onError(@NotNull String str);
    }

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void fetchDataFor(@NotNull Calendar calendar, @NotNull final AmbientSoundDataApiResultListener ambientSoundDataApiResultListener) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            Intrinsics.checkNotNullParameter(ambientSoundDataApiResultListener, "ambientSoundDataApiResultListener");
            AmbientSoundApiManager.getAmbientSoundDataFromServer(RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetAmbientSoundDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.ambientsound.datasources.server.AmbientSoundApiHandler$Companion$fetchDataFor$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    AmbientSoundApiHandler.AmbientSoundDataApiResultListener ambientSoundDataApiResultListener2 = AmbientSoundApiHandler.AmbientSoundDataApiResultListener.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    ambientSoundDataApiResultListener2.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetAmbientSoundDataRes getAmbientSoundDataRes) {
                    if (getAmbientSoundDataRes != null && !RepositoryUtils.isEmpty(getAmbientSoundDataRes.getAmbientSoundDataList())) {
                        AmbientSoundApiHandler.AmbientSoundDataApiResultListener ambientSoundDataApiResultListener2 = AmbientSoundApiHandler.AmbientSoundDataApiResultListener.this;
                        FormatterServerToEntity.Companion companion = FormatterServerToEntity.Companion;
                        AmbientSoundData ambientSoundData = getAmbientSoundDataRes.getAmbientSoundDataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(ambientSoundData, "p0.ambientSoundDataList.…                        )");
                        EntityDailyAmbientSoundData dailyAmbientSoundData = companion.getDailyAmbientSoundData(ambientSoundData);
                        AmbientSoundData ambientSoundData2 = getAmbientSoundDataRes.getAmbientSoundDataList().get(0);
                        Intrinsics.checkNotNullExpressionValue(ambientSoundData2, "p0.ambientSoundDataList.…                        )");
                        List<EntityHourlyAmbientSoundData> hourlyAmbientSoundData = companion.getHourlyAmbientSoundData(ambientSoundData2);
                        Intrinsics.checkNotNull(hourlyAmbientSoundData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData>");
                        ambientSoundDataApiResultListener2.onAmbientSoundDataReceived(dailyAmbientSoundData, (ArrayList) hourlyAmbientSoundData, true);
                        return;
                    }
                    AmbientSoundApiHandler.AmbientSoundDataApiResultListener.this.onError("No data found");
                }
            });
        }

        public final void fetchDataFor(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final AmbientSoundDataApiResultListener walkDataApiResultListner) {
            Intrinsics.checkNotNullParameter(fromDate, "fromDate");
            Intrinsics.checkNotNullParameter(toDate, "toDate");
            Intrinsics.checkNotNullParameter(walkDataApiResultListner, "walkDataApiResultListner");
            AmbientSoundApiManager.getAmbientSoundDataFromServer(RepositoryUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), true, new CoveApiListener<GetAmbientSoundDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.ambientsound.datasources.server.AmbientSoundApiHandler$Companion$fetchDataFor$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    AmbientSoundApiHandler.AmbientSoundDataApiResultListener ambientSoundDataApiResultListener = AmbientSoundApiHandler.AmbientSoundDataApiResultListener.this;
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    ambientSoundDataApiResultListener.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetAmbientSoundDataRes getAmbientSoundDataRes) {
                    if (getAmbientSoundDataRes != null && !RepositoryUtils.isEmpty(getAmbientSoundDataRes.getAmbientSoundDataList())) {
                        int size = getAmbientSoundDataRes.getAmbientSoundDataList().size();
                        for (int i = 0; i < size; i++) {
                            if (i == getAmbientSoundDataRes.getAmbientSoundDataList().size() - 1) {
                                AmbientSoundApiHandler.AmbientSoundDataApiResultListener ambientSoundDataApiResultListener = AmbientSoundApiHandler.AmbientSoundDataApiResultListener.this;
                                FormatterServerToEntity.Companion companion = FormatterServerToEntity.Companion;
                                AmbientSoundData ambientSoundData = getAmbientSoundDataRes.getAmbientSoundDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(ambientSoundData, "p0.ambientSoundDataList[i]");
                                EntityDailyAmbientSoundData dailyAmbientSoundData = companion.getDailyAmbientSoundData(ambientSoundData);
                                AmbientSoundData ambientSoundData2 = getAmbientSoundDataRes.getAmbientSoundDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(ambientSoundData2, "p0.ambientSoundDataList[i]");
                                List<EntityHourlyAmbientSoundData> hourlyAmbientSoundData = companion.getHourlyAmbientSoundData(ambientSoundData2);
                                Intrinsics.checkNotNull(hourlyAmbientSoundData, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData>");
                                ambientSoundDataApiResultListener.onAmbientSoundDataReceived(dailyAmbientSoundData, (ArrayList) hourlyAmbientSoundData, true);
                            } else {
                                AmbientSoundApiHandler.AmbientSoundDataApiResultListener ambientSoundDataApiResultListener2 = AmbientSoundApiHandler.AmbientSoundDataApiResultListener.this;
                                FormatterServerToEntity.Companion companion2 = FormatterServerToEntity.Companion;
                                AmbientSoundData ambientSoundData3 = getAmbientSoundDataRes.getAmbientSoundDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(ambientSoundData3, "p0.ambientSoundDataList[i]");
                                EntityDailyAmbientSoundData dailyAmbientSoundData2 = companion2.getDailyAmbientSoundData(ambientSoundData3);
                                AmbientSoundData ambientSoundData4 = getAmbientSoundDataRes.getAmbientSoundDataList().get(i);
                                Intrinsics.checkNotNullExpressionValue(ambientSoundData4, "p0.ambientSoundDataList[i]");
                                List<EntityHourlyAmbientSoundData> hourlyAmbientSoundData2 = companion2.getHourlyAmbientSoundData(ambientSoundData4);
                                Intrinsics.checkNotNull(hourlyAmbientSoundData2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData>");
                                ambientSoundDataApiResultListener2.onAmbientSoundDataReceived(dailyAmbientSoundData2, (ArrayList) hourlyAmbientSoundData2, false);
                            }
                        }
                        return;
                    }
                    AmbientSoundApiHandler.AmbientSoundDataApiResultListener.this.onError("No data found");
                }
            });
        }
    }
}
