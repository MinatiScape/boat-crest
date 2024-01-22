package com.coveiot.android.leonardo.performance.custommessage;

import android.content.Context;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.performance.Constants;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.repository.RepositoryUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class Nudge5EnergyScoreManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "Nudge5EnergyScoreManager";

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(Context context) {
            int i;
            String str;
            ArrayList<EnergyData> arrayList;
            EnergyData energyData;
            if ((BleApiManager.getInstance(context).getBleApi() != null ? BleApiManager.getInstance(context).getBleApi().getMacAddress() : null) != null) {
                String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
                EnergyScoreDbData energyScoreData = EnergyScoreRepository.Companion.getInstance(context).getEnergyScoreData(formatDate, BleApiManager.getInstance(context).getBleApi().getMacAddress());
                if (energyScoreData == null || (arrayList = energyScoreData.data) == null || (energyData = arrayList.get(0)) == null) {
                    i = 0;
                } else {
                    Integer currentEnergyLevel = energyData.getCurrentEnergyLevel();
                    Intrinsics.checkNotNull(currentEnergyLevel);
                    i = currentEnergyLevel.intValue();
                }
                if (i == 0 || i == -1) {
                    return;
                }
                if (i < 25) {
                    str = context.getString(R.string.very_low_energy_score);
                    Intrinsics.checkNotNullExpressionValue(str, "context.getString(R.string.very_low_energy_score)");
                } else {
                    if (25 <= i && i < 50) {
                        str = context.getString(R.string.low_energy_score);
                        Intrinsics.checkNotNullExpressionValue(str, "context.getString(R.string.low_energy_score)");
                    } else {
                        if (50 <= i && i < 75) {
                            str = context.getString(R.string.pace_your_day);
                            Intrinsics.checkNotNullExpressionValue(str, "context.getString(R.string.pace_your_day)");
                        } else {
                            if (75 <= i && i < 100) {
                                str = context.getString(R.string.high_on_energy);
                                Intrinsics.checkNotNullExpressionValue(str, "context.getString(R.string.high_on_energy)");
                            } else {
                                str = "";
                            }
                        }
                    }
                }
                if (str.length() > 0) {
                    NudgeCommandManager.Companion.sendEnergyScoreMessageToBle(context, i, str, Constants.ACTIVITY_IMAGE_ID);
                    NotificationManager.getInstance().notifyEnergyScore(context, 2147483636, str);
                    Map<String, Long> notificationRecordMap = KHPerformancePreferenceManager.getInstance(context).getPerformanceNotificationSentRecords();
                    Intrinsics.checkNotNullExpressionValue(notificationRecordMap, "notificationRecordMap");
                    notificationRecordMap.put(Constants.LAST_SENT_INFO_NUDGE_5_ENERGY_SCORE, Long.valueOf(System.currentTimeMillis()));
                    KHPerformancePreferenceManager.getInstance(context).savePerformanceNotificationSentRecords(notificationRecordMap);
                }
            }
        }

        public final void calculateNudge5AndSendToBLE(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Long l = KHPerformancePreferenceManager.getInstance(context).getPerformanceNotificationSentRecords().get(Constants.LAST_SENT_INFO_NUDGE_5_ENERGY_SCORE);
            if (l == null || System.currentTimeMillis() > l.longValue() + ((long) TimeConstants.HOUR)) {
                Calendar calendar = Calendar.getInstance();
                if (calendar.get(11) > 14 || calendar.get(11) > 18) {
                    a(context);
                }
            }
        }
    }
}
