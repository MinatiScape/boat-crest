package com.coveiot.android.sportsnotification;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.utils.apihelper.SoccerApiHelper;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Calendar;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class MatchStartJobService extends JobService {
    public final void a(long j) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.SYSTEM_INTERRUPTS_TIMER.getValue());
        analyticsLog.setCTX("entitysport");
        MatchListModel selectedSoccerMatch = SportsPreference.Companion.getSelectedSoccerMatch(this);
        analyticsLog.setMatchID(String.valueOf(selectedSoccerMatch != null ? selectedSoccerMatch.getMatchId() : null));
        String lowerCase = String.valueOf(selectedSoccerMatch != null ? selectedSoccerMatch.getDate() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        analyticsLog.setMatchDate((String) StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.trim(lowerCase).toString(), new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0));
        String lowerCase2 = String.valueOf(selectedSoccerMatch != null ? selectedSoccerMatch.getTeamA() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
        analyticsLog.setCricketTeam1(lowerCase2);
        String lowerCase3 = String.valueOf(selectedSoccerMatch != null ? selectedSoccerMatch.getTeamB() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
        analyticsLog.setCricketTeam2(lowerCase3);
        StringBuilder sb = new StringBuilder();
        String lowerCase4 = String.valueOf(selectedSoccerMatch != null ? selectedSoccerMatch.getFormattedTime() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
        sb.append(StringsKt__StringsKt.trim((String) StringsKt__StringsKt.split$default((CharSequence) lowerCase4, new String[]{com.clevertap.android.sdk.Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).get(0)).toString());
        sb.append(":00");
        analyticsLog.setMatchStartTime(sb.toString());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("delay_in_milliseconds", String.valueOf(j));
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(@Nullable JobParameters jobParameters) {
        LogHelper.d("SoccerSportsServiceNew", "MatchStartJobService Started");
        SoccerApiHelper.Companion.getInstance(this).checkConditionsAndCallApi();
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        MatchListModel selectedSoccerMatch = SportsPreference.Companion.getSelectedSoccerMatch(this);
        Intrinsics.checkNotNull(selectedSoccerMatch);
        String date = selectedSoccerMatch.getDate();
        Intrinsics.checkNotNull(date);
        Calendar startTime = sportsUtils.getStartTime(date);
        startTime.add(12, 1);
        if (System.currentTimeMillis() - startTime.getTimeInMillis() > 30000) {
            a(System.currentTimeMillis() - startTime.getTimeInMillis());
        }
        Intent intent = new Intent("ACTION_MATCH_UPDATE");
        intent.putExtra(com.clevertap.android.sdk.Constants.KEY_ACTION, "schedule_periodic_job_again");
        intent.putExtra(MqttServiceConstants.SUBSCRIBE_ACTION, true);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(@Nullable JobParameters jobParameters) {
        return true;
    }
}
