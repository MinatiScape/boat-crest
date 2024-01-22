package com.coveiot.android.leonardo.performance.custommessage;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.performance.Constants;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class Nudge4SleepScoreManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "Nudge4SleepScoreManager";

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(Context context) {
            String str;
            String str2 = null;
            String macAddress = BleApiManager.getInstance(context).getBleApi() != null ? BleApiManager.getInstance(context).getBleApi().getMacAddress() : null;
            if (macAddress != null) {
                String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(Calendar.getI…nce().time, \"yyyy-MM-dd\")");
                Integer sleepScore = SleepScoreRepository.Companion.getInstance(context).getSleepScore(formatDate, macAddress);
                if (sleepScore == null || sleepScore.intValue() == 0 || sleepScore.intValue() == -1) {
                    return;
                }
                if (sleepScore.intValue() < 60) {
                    str = context.getResources().getString(R.string.get_enough_sleep_score_msg);
                    Intrinsics.checkNotNullExpressionValue(str, "context.resources.getStr…t_enough_sleep_score_msg)");
                    str2 = "Poor Sleep !";
                } else if (sleepScore.intValue() > 90) {
                    str = context.getResources().getString(R.string.excellent_sleep_score_msg);
                    Intrinsics.checkNotNullExpressionValue(str, "context.resources.getStr…xcellent_sleep_score_msg)");
                    str2 = "Great going!";
                } else {
                    str = "";
                }
                if (str.length() > 0) {
                    NudgeCommandManager.Companion.sendGenericMessageToBle(context, str, Constants.SLEEP_IMAGE_ID, str2);
                    NotificationManager.getInstance().notifySleepScore(context, 2147483636, str);
                    Map<String, Long> notificationRecordMap = KHPerformancePreferenceManager.getInstance(context).getPerformanceNotificationSentRecords();
                    Intrinsics.checkNotNullExpressionValue(notificationRecordMap, "notificationRecordMap");
                    notificationRecordMap.put(Constants.LAST_SENT_INFO_NUDGE_4_SLEEP_SCORE, Long.valueOf(System.currentTimeMillis()));
                    KHPerformancePreferenceManager.getInstance(context).savePerformanceNotificationSentRecords(notificationRecordMap);
                }
            }
        }

        public final void calculateNudge4AndSendToBLE(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Map<String, Long> performanceNotificationSentRecords = KHPerformancePreferenceManager.getInstance(context).getPerformanceNotificationSentRecords();
            if (performanceNotificationSentRecords.get(Constants.LAST_SENT_INFO_NUDGE_4_SLEEP_SCORE) != null) {
                Long l = performanceNotificationSentRecords.get(Constants.LAST_SENT_INFO_NUDGE_4_SLEEP_SCORE);
                Intrinsics.checkNotNull(l);
                if (AppUtils.findDateDifference(new Date(l.longValue()), new Date()) < 1) {
                    return;
                }
            }
            a(context);
        }
    }
}
