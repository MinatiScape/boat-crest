package com.coveiot.android.leonardo.performance.custommessage;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.LastDataHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.firebaseservices.utils.NotificationManager;
import com.coveiot.android.leonardo.performance.Constants;
import java.util.Calendar;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class Nudge6StressManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "Nudge6StressManager";

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(Context context) {
            Integer first;
            String str;
            if ((BleApiManager.getInstance(context).getBleApi() != null ? BleApiManager.getInstance(context).getBleApi().getMacAddress() : null) != null) {
                Pair<Integer, Long> highPeriodicStress = LastDataHelper.Companion.getInstance(context).getHighPeriodicStress();
                if (highPeriodicStress.getFirst() == null || (first = highPeriodicStress.getFirst()) == null || first.intValue() == 0 || first.intValue() == -1) {
                    return;
                }
                if (first.intValue() < 30) {
                    str = context.getString(R.string.low_stress_nudge);
                    Intrinsics.checkNotNullExpressionValue(str, "context.getString(R.string.low_stress_nudge)");
                } else if (new IntRange(31, 50).contains(first.intValue())) {
                    str = context.getString(R.string.mild_stress_nudge);
                    Intrinsics.checkNotNullExpressionValue(str, "context.getString(R.string.mild_stress_nudge)");
                } else if (new IntRange(51, 80).contains(first.intValue())) {
                    str = context.getString(R.string.moderate_stress_nudge);
                    Intrinsics.checkNotNullExpressionValue(str, "context.getString(R.string.moderate_stress_nudge)");
                } else if (new IntRange(81, 100).contains(first.intValue())) {
                    str = context.getString(R.string.hight_stress_nudge);
                    Intrinsics.checkNotNullExpressionValue(str, "context.getString(R.string.hight_stress_nudge)");
                } else {
                    str = "";
                }
                if (str.length() > 0) {
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    if (!companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
                        NudgeCommandManager.Companion.sendStressMessageToBle(context, highPeriodicStress, str, Constants.GENERAL_NUDGE_IMAGE_ID);
                    } else {
                        NudgeCommandManager.Companion.sendStressMessageToBle(context, highPeriodicStress, str, Constants.STRESS_NUDGE_IMAGE_ID);
                    }
                    NotificationManager.getInstance().notifyEnergyScore(context, Constants.STRESS_NOTIFICATION_ID, str);
                    Map<String, Long> notificationRecordMap = KHPerformancePreferenceManager.getInstance(context).getPerformanceNotificationSentRecords();
                    Intrinsics.checkNotNullExpressionValue(notificationRecordMap, "notificationRecordMap");
                    notificationRecordMap.put(Constants.LAST_SENT_INFO_NUDGE_6_STRESS, Long.valueOf(System.currentTimeMillis()));
                    KHPerformancePreferenceManager.getInstance(context).savePerformanceNotificationSentRecords(notificationRecordMap);
                }
            }
        }

        public final void calculateNudge6AndSendToBLE(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Long l = KHPerformancePreferenceManager.getInstance(context).getPerformanceNotificationSentRecords().get(Constants.LAST_SENT_INFO_NUDGE_6_STRESS);
            if ((l == null || System.currentTimeMillis() > l.longValue() + 43200000) && Calendar.getInstance().get(11) >= 19) {
                a(context);
            }
        }
    }
}
