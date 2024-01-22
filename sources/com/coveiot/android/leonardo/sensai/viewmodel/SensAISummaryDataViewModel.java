package com.coveiot.android.leonardo.sensai.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.sensai.model.AddToCompareData;
import com.coveiot.android.leonardo.sensai.model.SensAICompareData;
import com.coveiot.android.leonardo.sensai.model.SensAICompareListItem;
import com.coveiot.android.leonardo.sensai.model.SensAICompareTitle;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.activitysession.GetActivitySessionHeaderResponse;
import com.coveiot.coveaccess.activitysession.PostActivitySessionDataRequest;
import com.coveiot.coveaccess.activitysession.PostActivitySessionHeaderResponse;
import com.coveiot.coveaccess.activitysession.Target;
import com.coveiot.coveaccess.activitysession.TraqConfigApi;
import com.coveiot.coveaccess.activitysession.fitnessActivitySessions;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.sensai.datasource.db.read.SensAIBeamDBRead;
import com.coveiot.repository.sensai.datasource.db.write.SensAIBeamDBWrite;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.c;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SensAISummaryDataViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5394a;
    @NotNull
    public final String b;

    public SensAISummaryDataViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5394a = context;
        this.b = "ViewModelSensAISummaryData";
    }

    @NotNull
    public final ArrayList<SensAICompareData> getCompareDataList(@NotNull List<? extends SensAIActivitySummary> activitySummaryList) {
        Intrinsics.checkNotNullParameter(activitySummaryList, "activitySummaryList");
        ArrayList arrayList = new ArrayList();
        ArrayList<SensAICompareData> arrayList2 = new ArrayList<>();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        for (SensAIActivitySummary sensAIActivitySummary : activitySummaryList) {
            if (sensAIActivitySummary.isAddToCompare()) {
                arrayList.add(sensAIActivitySummary);
            }
        }
        arrayList3.add(new SensAICompareListItem(this.f5394a.getString(R.string.duration), Integer.valueOf((int) R.drawable.ic_time_blue), Integer.valueOf((int) ((SensAIActivitySummary) arrayList.get(0)).getDurationSec()), Integer.valueOf((int) ((SensAIActivitySummary) arrayList.get(1)).getDurationSec())));
        arrayList3.add(new SensAICompareListItem(this.f5394a.getString(R.string.goals_achieved), Integer.valueOf((int) R.drawable.ic_compare_goal_acheived), Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getGoalCompletionPct()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getGoalCompletionPct())));
        if (((SensAIActivitySummary) arrayList.get(0)).getActivityType() == 1) {
            arrayList3.add(new SensAICompareListItem(this.f5394a.getString(R.string.balls_played), Integer.valueOf((int) R.drawable.ic_compare_ball), Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getTotalSwings()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getTotalSwings())));
            arrayList3.add(new SensAICompareListItem(this.f5394a.getString(R.string.hit_percentage), Integer.valueOf((int) R.drawable.ic_compare_bat), Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getHitPct()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getHitPct())));
        } else {
            arrayList3.add(new SensAICompareListItem(this.f5394a.getString(R.string.balls_bowled), Integer.valueOf((int) R.drawable.ic_compare_ball), Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getTotalBallsBowled()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getTotalBallsBowled())));
        }
        arrayList2.add(new SensAICompareData(this.f5394a.getString(R.string.activity), arrayList3));
        if (((SensAIActivitySummary) arrayList.get(0)).getActivityType() == 1) {
            arrayList4.add(new SensAICompareListItem(this.f5394a.getString(R.string.max_hand_speed), Integer.valueOf((int) R.drawable.ic_batting_max_armspeed), Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getMaxHandSpeed()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getMaxHandSpeed())));
            arrayList4.add(new SensAICompareListItem(this.f5394a.getString(R.string.avg_hand_speed), Integer.valueOf((int) R.drawable.ic_batting_avg_armspeed), Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getAvgHandSpeed()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getAvgHandSpeed())));
            arrayList2.add(new SensAICompareData(this.f5394a.getString(R.string.hand_speed), arrayList4));
        } else {
            arrayList4.add(new SensAICompareListItem(this.f5394a.getString(R.string.max_arm_speed), Integer.valueOf((int) R.drawable.ic_bowlling_max_armspeed), Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getMaxArmSpeed()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getMaxArmSpeed())));
            arrayList4.add(new SensAICompareListItem(this.f5394a.getString(R.string.min_arm_speed), Integer.valueOf((int) R.drawable.ic_bowlling_min_armspeed), Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getMinArmSpeed()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getMinArmSpeed())));
            arrayList4.add(new SensAICompareListItem(this.f5394a.getString(R.string.avg_arm_speed), Integer.valueOf((int) R.drawable.ic_bowlling_avg_armspeed), Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getAvgArmSpeed()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getAvgArmSpeed())));
            arrayList2.add(new SensAICompareData(this.f5394a.getString(R.string.arm_speed), arrayList4));
        }
        arrayList5.add(new SensAICompareListItem(this.f5394a.getString(R.string.steps), 2131232553, Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getTotalSteps()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getTotalSteps())));
        arrayList5.add(new SensAICompareListItem(this.f5394a.getString(R.string.calories), Integer.valueOf((int) R.drawable.ic_calories), Integer.valueOf((int) ((SensAIActivitySummary) arrayList.get(0)).getTotalCalories()), Integer.valueOf((int) ((SensAIActivitySummary) arrayList.get(1)).getTotalCalories())));
        arrayList5.add(new SensAICompareListItem(this.f5394a.getString(R.string.avg_hr), Integer.valueOf((int) R.drawable.ic_heart), Integer.valueOf(((SensAIActivitySummary) arrayList.get(0)).getAvgHR()), Integer.valueOf(((SensAIActivitySummary) arrayList.get(1)).getAvgHR())));
        arrayList2.add(new SensAICompareData(this.f5394a.getString(R.string.health), arrayList5));
        return arrayList2;
    }

    @NotNull
    public final ArrayList<AddToCompareData> getCompareList(@NotNull List<? extends SensAIActivitySummary> activitySummaryList) {
        String string;
        int i;
        String str;
        String string2;
        int i2;
        String str2;
        String string3;
        int i3;
        String string4;
        int i4;
        String str3;
        Date date;
        String str4;
        Date date2;
        String str5;
        Intrinsics.checkNotNullParameter(activitySummaryList, "activitySummaryList");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList<AddToCompareData> arrayList3 = new ArrayList<>();
        for (SensAIActivitySummary sensAIActivitySummary : activitySummaryList) {
            if (sensAIActivitySummary.getActivityType() == 1 && sensAIActivitySummary.isAddToCompare()) {
                arrayList.add(sensAIActivitySummary);
            }
        }
        for (SensAIActivitySummary sensAIActivitySummary2 : activitySummaryList) {
            if (sensAIActivitySummary2.getActivityType() == 2 && sensAIActivitySummary2.isAddToCompare()) {
                arrayList2.add(sensAIActivitySummary2);
            }
        }
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(this.f5394a).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
        String str6 = isDistanceUnitInMile.booleanValue() ? this.f5394a.getResources().getString(R.string.mil_per_hours) + ' ' : this.f5394a.getResources().getString(R.string.km_per_hours) + ' ';
        if (arrayList.size() == 2 && arrayList2.size() == 0) {
            if (((SensAIActivitySummary) arrayList.get(0)).getHand() == 0) {
                string3 = this.f5394a.getString(R.string.right_hand_batting);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.right_hand_batting)");
                i3 = R.drawable.right_hand_batting;
            } else {
                string3 = this.f5394a.getString(R.string.left_hand_batting);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.left_hand_batting)");
                i3 = R.drawable.left_hand_batting;
            }
            String str7 = string3;
            if (((SensAIActivitySummary) arrayList.get(1)).getHand() == 0) {
                string4 = this.f5394a.getString(R.string.right_hand_batting);
                Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.right_hand_batting)");
                i4 = R.drawable.right_hand_batting;
            } else {
                string4 = this.f5394a.getString(R.string.left_hand_batting);
                Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.left_hand_batting)");
                i4 = R.drawable.left_hand_batting;
            }
            String str8 = string4;
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM YYYY, hh:mm aa");
            Long timestamp = ((SensAIActivitySummary) arrayList.get(0)).getTimestamp();
            if (timestamp != null) {
                str3 = " bpm";
                date = new Date(timestamp.longValue());
            } else {
                str3 = " bpm";
                date = null;
            }
            String format = simpleDateFormat.format(date);
            Long timestamp2 = ((SensAIActivitySummary) arrayList.get(1)).getTimestamp();
            if (timestamp2 != null) {
                str4 = " %";
                date2 = new Date(timestamp2.longValue());
            } else {
                str4 = " %";
                date2 = null;
            }
            String format2 = simpleDateFormat.format(date2);
            Integer valueOf = Integer.valueOf(i3);
            Boolean bool = Boolean.TRUE;
            arrayList3.add(new AddToCompareData(str7, valueOf, format, bool, str8, Integer.valueOf(i4), format2, bool));
            if (((SensAIActivitySummary) arrayList.get(0)).getDurationSec() > ((SensAIActivitySummary) arrayList.get(1)).getDurationSec()) {
                String string5 = this.f5394a.getString(R.string.duration);
                Integer valueOf2 = Integer.valueOf((int) R.drawable.duration);
                PayUtils payUtils = PayUtils.INSTANCE;
                arrayList3.add(new AddToCompareData(string5, valueOf2, String.valueOf(payUtils.formatSecondsInHHMMSS((int) ((SensAIActivitySummary) arrayList.get(0)).getDurationSec())), bool, this.f5394a.getString(R.string.duration), Integer.valueOf((int) R.drawable.duration), String.valueOf(payUtils.formatSecondsInHHMMSS((int) ((SensAIActivitySummary) arrayList.get(1)).getDurationSec())), Boolean.FALSE));
            } else {
                String string6 = this.f5394a.getString(R.string.duration);
                Integer valueOf3 = Integer.valueOf((int) R.drawable.duration);
                PayUtils payUtils2 = PayUtils.INSTANCE;
                arrayList3.add(new AddToCompareData(string6, valueOf3, String.valueOf(payUtils2.formatSecondsInHHMMSS((int) ((SensAIActivitySummary) arrayList.get(0)).getDurationSec())), Boolean.FALSE, this.f5394a.getString(R.string.duration), Integer.valueOf((int) R.drawable.duration), String.valueOf(payUtils2.formatSecondsInHHMMSS((int) ((SensAIActivitySummary) arrayList.get(1)).getDurationSec())), bool));
            }
            if (((SensAIActivitySummary) arrayList.get(0)).getAvgHandSpeed() > ((SensAIActivitySummary) arrayList.get(1)).getAvgHandSpeed()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.avg_hand_speed), Integer.valueOf((int) R.drawable.avg_hand_speed), ((SensAIActivitySummary) arrayList.get(0)).getAvgHandSpeed() + ' ' + str6, bool, this.f5394a.getString(R.string.avg_hand_speed), Integer.valueOf((int) R.drawable.avg_hand_speed), ((SensAIActivitySummary) arrayList.get(1)).getAvgHandSpeed() + ' ' + str6, Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.avg_hand_speed), Integer.valueOf((int) R.drawable.avg_hand_speed), ((SensAIActivitySummary) arrayList.get(0)).getAvgHandSpeed() + ' ' + str6, Boolean.FALSE, this.f5394a.getString(R.string.avg_hand_speed), Integer.valueOf((int) R.drawable.avg_hand_speed), ((SensAIActivitySummary) arrayList.get(1)).getAvgHandSpeed() + ' ' + str6, bool));
            }
            if (((SensAIActivitySummary) arrayList.get(0)).getMaxHandSpeed() > ((SensAIActivitySummary) arrayList.get(1)).getMaxHandSpeed()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.max_hand_speed), Integer.valueOf((int) R.drawable.max_hand_speed), ((SensAIActivitySummary) arrayList.get(0)).getMaxHandSpeed() + ' ' + str6, bool, this.f5394a.getString(R.string.max_hand_speed), Integer.valueOf((int) R.drawable.max_hand_speed), ((SensAIActivitySummary) arrayList.get(1)).getMaxHandSpeed() + ' ' + str6, Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.max_hand_speed), Integer.valueOf((int) R.drawable.max_hand_speed), ((SensAIActivitySummary) arrayList.get(0)).getMaxHandSpeed() + ' ' + str6, Boolean.FALSE, this.f5394a.getString(R.string.max_hand_speed), Integer.valueOf((int) R.drawable.max_hand_speed), ((SensAIActivitySummary) arrayList.get(1)).getMaxHandSpeed() + ' ' + str6, bool));
            }
            if (((SensAIActivitySummary) arrayList.get(0)).getTotalCalories() > ((SensAIActivitySummary) arrayList.get(1)).getTotalCalories()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.calories), Integer.valueOf((int) R.drawable.ic_calories), String.valueOf(c.roundToInt(((SensAIActivitySummary) arrayList.get(0)).getTotalCalories())), bool, this.f5394a.getString(R.string.calories), Integer.valueOf((int) R.drawable.ic_calories), String.valueOf(c.roundToInt(((SensAIActivitySummary) arrayList.get(1)).getTotalCalories())), Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.calories), Integer.valueOf((int) R.drawable.ic_calories), String.valueOf(c.roundToInt(((SensAIActivitySummary) arrayList.get(0)).getTotalCalories())), Boolean.FALSE, this.f5394a.getString(R.string.calories), Integer.valueOf((int) R.drawable.ic_calories), String.valueOf(c.roundToInt(((SensAIActivitySummary) arrayList.get(1)).getTotalCalories())), bool));
            }
            if (((SensAIActivitySummary) arrayList.get(0)).getTotalSteps() > ((SensAIActivitySummary) arrayList.get(1)).getTotalSteps()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.steps), 2131232553, String.valueOf(((SensAIActivitySummary) arrayList.get(0)).getTotalSteps()), bool, this.f5394a.getString(R.string.steps), 2131232553, String.valueOf(((SensAIActivitySummary) arrayList.get(1)).getTotalSteps()), Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.steps), 2131232553, String.valueOf(((SensAIActivitySummary) arrayList.get(0)).getTotalSteps()), Boolean.FALSE, this.f5394a.getString(R.string.steps), 2131232553, String.valueOf(((SensAIActivitySummary) arrayList.get(1)).getTotalSteps()), bool));
            }
            if (((SensAIActivitySummary) arrayList.get(0)).getGoalCompletionPct() > ((SensAIActivitySummary) arrayList.get(1)).getGoalCompletionPct()) {
                String string7 = this.f5394a.getString(R.string.goals_achieved);
                Integer valueOf4 = Integer.valueOf((int) R.drawable.compare_award);
                StringBuilder sb = new StringBuilder();
                sb.append(((SensAIActivitySummary) arrayList.get(0)).getGoalCompletionPct());
                str5 = str4;
                sb.append(str5);
                arrayList3.add(new AddToCompareData(string7, valueOf4, sb.toString(), bool, this.f5394a.getString(R.string.goals_achieved), Integer.valueOf((int) R.drawable.compare_award), ((SensAIActivitySummary) arrayList.get(1)).getGoalCompletionPct() + str5, Boolean.FALSE));
            } else {
                str5 = str4;
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.goals_achieved), Integer.valueOf((int) R.drawable.compare_award), ((SensAIActivitySummary) arrayList.get(0)).getGoalCompletionPct() + str5, Boolean.FALSE, this.f5394a.getString(R.string.goals_achieved), Integer.valueOf((int) R.drawable.compare_award), ((SensAIActivitySummary) arrayList.get(1)).getGoalCompletionPct() + str5, bool));
            }
            if (((SensAIActivitySummary) arrayList.get(0)).getTotalSwings() > ((SensAIActivitySummary) arrayList.get(1)).getTotalSwings()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.total_shots), Integer.valueOf((int) R.drawable.compare_total_shots), String.valueOf(((SensAIActivitySummary) arrayList.get(0)).getTotalSwings()), bool, this.f5394a.getString(R.string.total_shots), Integer.valueOf((int) R.drawable.compare_total_shots), String.valueOf(((SensAIActivitySummary) arrayList.get(1)).getTotalSwings()), Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.total_shots), Integer.valueOf((int) R.drawable.compare_total_shots), String.valueOf(((SensAIActivitySummary) arrayList.get(0)).getTotalSwings()), Boolean.FALSE, this.f5394a.getString(R.string.total_shots), Integer.valueOf((int) R.drawable.compare_total_shots), String.valueOf(((SensAIActivitySummary) arrayList.get(1)).getTotalSwings()), bool));
            }
            if (((SensAIActivitySummary) arrayList.get(0)).getHitPct() > ((SensAIActivitySummary) arrayList.get(1)).getHitPct()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.hit_perc), Integer.valueOf((int) R.drawable.compare_hit), ((SensAIActivitySummary) arrayList.get(0)).getHitPct() + str5, bool, this.f5394a.getString(R.string.hit_perc), Integer.valueOf((int) R.drawable.compare_hit), ((SensAIActivitySummary) arrayList.get(1)).getHitPct() + str5, Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.hit_perc), Integer.valueOf((int) R.drawable.compare_hit), ((SensAIActivitySummary) arrayList.get(0)).getHitPct() + str5, Boolean.FALSE, this.f5394a.getString(R.string.hit_perc), Integer.valueOf((int) R.drawable.compare_hit), ((SensAIActivitySummary) arrayList.get(1)).getHitPct() + str5, bool));
            }
            if (((SensAIActivitySummary) arrayList.get(0)).getAvgHR() > ((SensAIActivitySummary) arrayList.get(1)).getAvgHR()) {
                String string8 = this.f5394a.getString(R.string.avg_hr);
                Integer valueOf5 = Integer.valueOf((int) R.drawable.hr_health);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(((SensAIActivitySummary) arrayList.get(0)).getAvgHR());
                String str9 = str3;
                sb2.append(str9);
                arrayList3.add(new AddToCompareData(string8, valueOf5, sb2.toString(), bool, this.f5394a.getString(R.string.avg_hr), Integer.valueOf((int) R.drawable.hr_health), ((SensAIActivitySummary) arrayList.get(1)).getAvgHR() + str9, Boolean.FALSE));
            } else {
                String str10 = str3;
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.avg_hr), Integer.valueOf((int) R.drawable.hr_health), ((SensAIActivitySummary) arrayList.get(0)).getAvgHR() + str10, Boolean.FALSE, this.f5394a.getString(R.string.avg_hr), Integer.valueOf((int) R.drawable.hr_health), ((SensAIActivitySummary) arrayList.get(1)).getAvgHR() + str10, bool));
            }
        } else if (arrayList2.size() == 2 && arrayList.size() == 0) {
            if (((SensAIActivitySummary) arrayList2.get(0)).getHand() == 0) {
                string = this.f5394a.getString(R.string.right_hand);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.right_hand)");
                i = R.drawable.right_hand_bowling;
            } else {
                string = this.f5394a.getString(R.string.left_hand);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.left_hand)");
                i = R.drawable.left_hand_bowling;
            }
            if (((SensAIActivitySummary) arrayList2.get(0)).getBowlingType() == 0) {
                str = string + ' ' + AppConstants.BOWLING_FAST.getValue();
            } else if (((SensAIActivitySummary) arrayList2.get(0)).getBowlingType() == 1) {
                str = string + ' ' + AppConstants.BOWLING_MEDIUM_PACE.getValue();
            } else {
                str = string + ' ' + AppConstants.BOWLING_SPIN.getValue();
            }
            String str11 = str;
            if (((SensAIActivitySummary) arrayList2.get(1)).getHand() == 0) {
                string2 = this.f5394a.getString(R.string.right_hand);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.right_hand)");
                i2 = R.drawable.right_hand_bowling;
            } else {
                string2 = this.f5394a.getString(R.string.left_hand);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.left_hand)");
                i2 = R.drawable.left_hand_bowling;
            }
            if (((SensAIActivitySummary) arrayList2.get(1)).getBowlingType() == 0) {
                str2 = string2 + " Fast";
            } else if (((SensAIActivitySummary) arrayList2.get(1)).getBowlingType() == 1) {
                str2 = string2 + " Medium Pace";
            } else {
                str2 = string2 + " Spin";
            }
            String str12 = str2;
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd MMM YYYY, hh:mm aa");
            Long timestamp3 = ((SensAIActivitySummary) arrayList2.get(0)).getTimestamp();
            String format3 = simpleDateFormat2.format(timestamp3 != null ? new Date(timestamp3.longValue()) : null);
            Long timestamp4 = ((SensAIActivitySummary) arrayList2.get(1)).getTimestamp();
            String format4 = simpleDateFormat2.format(timestamp4 != null ? new Date(timestamp4.longValue()) : null);
            Integer valueOf6 = Integer.valueOf(i);
            Boolean bool2 = Boolean.TRUE;
            arrayList3.add(new AddToCompareData(str11, valueOf6, format3, bool2, str12, Integer.valueOf(i2), format4, bool2));
            if (((SensAIActivitySummary) arrayList2.get(0)).getDurationSec() > ((SensAIActivitySummary) arrayList2.get(1)).getDurationSec()) {
                String string9 = this.f5394a.getString(R.string.duration);
                Integer valueOf7 = Integer.valueOf((int) R.drawable.duration);
                PayUtils payUtils3 = PayUtils.INSTANCE;
                arrayList3.add(new AddToCompareData(string9, valueOf7, payUtils3.formatSecondsInHHMMSS((int) ((SensAIActivitySummary) arrayList2.get(0)).getDurationSec()), bool2, this.f5394a.getString(R.string.duration), Integer.valueOf((int) R.drawable.duration), payUtils3.formatSecondsInHHMMSS((int) ((SensAIActivitySummary) arrayList2.get(1)).getDurationSec()), Boolean.FALSE));
            } else {
                String string10 = this.f5394a.getString(R.string.duration);
                Integer valueOf8 = Integer.valueOf((int) R.drawable.duration);
                PayUtils payUtils4 = PayUtils.INSTANCE;
                arrayList3.add(new AddToCompareData(string10, valueOf8, payUtils4.formatSecondsInHHMMSS((int) ((SensAIActivitySummary) arrayList2.get(0)).getDurationSec()), Boolean.FALSE, this.f5394a.getString(R.string.duration), Integer.valueOf((int) R.drawable.duration), payUtils4.formatSecondsInHHMMSS((int) ((SensAIActivitySummary) arrayList2.get(1)).getDurationSec()), bool2));
            }
            if (((SensAIActivitySummary) arrayList2.get(0)).getAvgArmSpeed() > ((SensAIActivitySummary) arrayList2.get(1)).getAvgArmSpeed()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.avg_arm_speed), Integer.valueOf((int) R.drawable.avg_arm_speed), String.valueOf(((SensAIActivitySummary) arrayList2.get(0)).getAvgArmSpeed()), bool2, this.f5394a.getString(R.string.avg_arm_speed), Integer.valueOf((int) R.drawable.avg_arm_speed), String.valueOf(((SensAIActivitySummary) arrayList2.get(1)).getAvgArmSpeed()), Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.avg_arm_speed), Integer.valueOf((int) R.drawable.avg_arm_speed), String.valueOf(((SensAIActivitySummary) arrayList2.get(0)).getAvgArmSpeed()), Boolean.FALSE, this.f5394a.getString(R.string.avg_arm_speed), Integer.valueOf((int) R.drawable.avg_arm_speed), String.valueOf(((SensAIActivitySummary) arrayList2.get(1)).getAvgArmSpeed()), bool2));
            }
            if (((SensAIActivitySummary) arrayList2.get(0)).getMaxArmSpeed() > ((SensAIActivitySummary) arrayList2.get(1)).getMaxArmSpeed()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.max_arm_speed), Integer.valueOf((int) R.drawable.fastest_ball), ((SensAIActivitySummary) arrayList2.get(0)).getMaxArmSpeed() + ' ' + str6, bool2, this.f5394a.getString(R.string.max_arm_speed), Integer.valueOf((int) R.drawable.fastest_ball), ((SensAIActivitySummary) arrayList2.get(1)).getMaxArmSpeed() + ' ' + str6, Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.max_arm_speed), Integer.valueOf((int) R.drawable.fastest_ball), ((SensAIActivitySummary) arrayList2.get(0)).getMaxArmSpeed() + ' ' + str6, Boolean.FALSE, this.f5394a.getString(R.string.max_arm_speed), Integer.valueOf((int) R.drawable.fastest_ball), ((SensAIActivitySummary) arrayList2.get(1)).getMaxArmSpeed() + ' ' + str6, bool2));
            }
            if (((SensAIActivitySummary) arrayList2.get(0)).getTotalCalories() > ((SensAIActivitySummary) arrayList2.get(1)).getTotalCalories()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.calories), Integer.valueOf((int) R.drawable.ic_calories), String.valueOf(c.roundToInt(((SensAIActivitySummary) arrayList2.get(0)).getTotalCalories())), bool2, this.f5394a.getString(R.string.calories), Integer.valueOf((int) R.drawable.ic_calories), String.valueOf(c.roundToInt(((SensAIActivitySummary) arrayList2.get(1)).getTotalCalories())), Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.calories), Integer.valueOf((int) R.drawable.ic_calories), String.valueOf(c.roundToInt(((SensAIActivitySummary) arrayList2.get(0)).getTotalCalories())), Boolean.FALSE, this.f5394a.getString(R.string.calories), Integer.valueOf((int) R.drawable.ic_calories), String.valueOf(c.roundToInt(((SensAIActivitySummary) arrayList2.get(1)).getTotalCalories())), bool2));
            }
            if (((SensAIActivitySummary) arrayList2.get(0)).getTotalSteps() > ((SensAIActivitySummary) arrayList2.get(1)).getTotalSteps()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.steps), Integer.valueOf((int) R.drawable.ic_steps_sensai), String.valueOf(((SensAIActivitySummary) arrayList2.get(0)).getTotalSteps()), bool2, this.f5394a.getString(R.string.steps), Integer.valueOf((int) R.drawable.ic_steps_sensai), String.valueOf(((SensAIActivitySummary) arrayList2.get(1)).getTotalSteps()), Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.steps), Integer.valueOf((int) R.drawable.ic_steps_sensai), String.valueOf(((SensAIActivitySummary) arrayList2.get(0)).getTotalSteps()), Boolean.FALSE, this.f5394a.getString(R.string.steps), Integer.valueOf((int) R.drawable.ic_steps_sensai), String.valueOf(((SensAIActivitySummary) arrayList2.get(1)).getTotalSteps()), bool2));
            }
            if (((SensAIActivitySummary) arrayList2.get(0)).getGoalCompletionPct() > ((SensAIActivitySummary) arrayList2.get(1)).getGoalCompletionPct()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.goals_achieved), Integer.valueOf((int) R.drawable.compare_award), ((SensAIActivitySummary) arrayList2.get(0)).getGoalCompletionPct() + " %", bool2, this.f5394a.getString(R.string.goals_achieved), Integer.valueOf((int) R.drawable.compare_award), ((SensAIActivitySummary) arrayList2.get(1)).getGoalCompletionPct() + " %", Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.goals_achieved), Integer.valueOf((int) R.drawable.compare_award), ((SensAIActivitySummary) arrayList2.get(0)).getGoalCompletionPct() + " %", Boolean.FALSE, this.f5394a.getString(R.string.goals_achieved), Integer.valueOf((int) R.drawable.compare_award), ((SensAIActivitySummary) arrayList2.get(1)).getGoalCompletionPct() + " %", bool2));
            }
            if (((SensAIActivitySummary) arrayList2.get(0)).getTotalBallsBowled() > ((SensAIActivitySummary) arrayList2.get(1)).getTotalBallsBowled()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.total_balls), Integer.valueOf((int) R.drawable.compare_total_balls), String.valueOf(((SensAIActivitySummary) arrayList2.get(0)).getTotalBallsBowled()), bool2, this.f5394a.getString(R.string.total_balls), Integer.valueOf((int) R.drawable.compare_total_balls), String.valueOf(((SensAIActivitySummary) arrayList2.get(1)).getTotalBallsBowled()), Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.total_balls), Integer.valueOf((int) R.drawable.compare_total_balls), String.valueOf(((SensAIActivitySummary) arrayList2.get(0)).getTotalBallsBowled()), Boolean.FALSE, this.f5394a.getString(R.string.total_balls), Integer.valueOf((int) R.drawable.compare_total_balls), String.valueOf(((SensAIActivitySummary) arrayList2.get(1)).getTotalBallsBowled()), bool2));
            }
            if (((SensAIActivitySummary) arrayList2.get(0)).getTotalBallsBowled() > ((SensAIActivitySummary) arrayList2.get(1)).getTotalBallsBowled()) {
                String string11 = this.f5394a.getString(R.string.overs_bowled);
                Integer valueOf9 = Integer.valueOf((int) R.drawable.compare_overs_bowled);
                PayUtils payUtils5 = PayUtils.INSTANCE;
                arrayList3.add(new AddToCompareData(string11, valueOf9, String.valueOf(payUtils5.getOversFromBalls(((SensAIActivitySummary) arrayList2.get(0)).getTotalBallsBowled())), bool2, this.f5394a.getString(R.string.overs_bowled), Integer.valueOf((int) R.drawable.compare_overs_bowled), String.valueOf(payUtils5.getOversFromBalls(((SensAIActivitySummary) arrayList2.get(1)).getTotalBallsBowled())), Boolean.FALSE));
            } else {
                String string12 = this.f5394a.getString(R.string.overs_bowled);
                Integer valueOf10 = Integer.valueOf((int) R.drawable.compare_overs_bowled);
                PayUtils payUtils6 = PayUtils.INSTANCE;
                arrayList3.add(new AddToCompareData(string12, valueOf10, String.valueOf(payUtils6.getOversFromBalls(((SensAIActivitySummary) arrayList2.get(0)).getTotalBallsBowled())), Boolean.FALSE, this.f5394a.getString(R.string.overs_bowled), Integer.valueOf((int) R.drawable.compare_overs_bowled), String.valueOf(payUtils6.getOversFromBalls(((SensAIActivitySummary) arrayList2.get(1)).getTotalBallsBowled())), bool2));
            }
            if (((SensAIActivitySummary) arrayList2.get(0)).getAvgHR() > ((SensAIActivitySummary) arrayList2.get(1)).getAvgHR()) {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.avg_hr), Integer.valueOf((int) R.drawable.hr_health), ((SensAIActivitySummary) arrayList2.get(0)).getAvgHR() + " bpm", bool2, this.f5394a.getString(R.string.avg_hr), Integer.valueOf((int) R.drawable.hr_health), ((SensAIActivitySummary) arrayList2.get(1)).getAvgHR() + " bpm", Boolean.FALSE));
            } else {
                arrayList3.add(new AddToCompareData(this.f5394a.getString(R.string.avg_hr), Integer.valueOf((int) R.drawable.hr_health), ((SensAIActivitySummary) arrayList2.get(0)).getAvgHR() + " bpm", Boolean.FALSE, this.f5394a.getString(R.string.avg_hr), Integer.valueOf((int) R.drawable.hr_health), ((SensAIActivitySummary) arrayList2.get(1)).getAvgHR() + " bpm", bool2));
            }
        }
        return arrayList3;
    }

    @NotNull
    public final ArrayList<SensAICompareTitle> getCompareTitleList(@NotNull List<? extends SensAIActivitySummary> activitySummaryList) {
        String string;
        int i;
        String str;
        String string2;
        String str2;
        Intrinsics.checkNotNullParameter(activitySummaryList, "activitySummaryList");
        ArrayList arrayList = new ArrayList();
        ArrayList<SensAICompareTitle> arrayList2 = new ArrayList<>();
        for (SensAIActivitySummary sensAIActivitySummary : activitySummaryList) {
            if (sensAIActivitySummary.isAddToCompare()) {
                arrayList.add(sensAIActivitySummary);
            }
        }
        int activityType = ((SensAIActivitySummary) arrayList.get(0)).getActivityType();
        int i2 = R.drawable.ic_right_batting;
        if (activityType == 1) {
            if (((SensAIActivitySummary) arrayList.get(0)).getHand() == 0) {
                str = this.f5394a.getString(R.string.right_hand_batting);
                Intrinsics.checkNotNullExpressionValue(str, "context.getString(R.string.right_hand_batting)");
                i = 2131232387;
            } else {
                str = this.f5394a.getString(R.string.left_hand_batting);
                Intrinsics.checkNotNullExpressionValue(str, "context.getString(R.string.left_hand_batting)");
                i = 2131232164;
            }
            if (((SensAIActivitySummary) arrayList.get(1)).getHand() == 0) {
                str2 = this.f5394a.getString(R.string.right_hand_batting);
                Intrinsics.checkNotNullExpressionValue(str2, "context.getString(R.string.right_hand_batting)");
            } else {
                String string3 = this.f5394a.getString(R.string.left_hand_batting);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.left_hand_batting)");
                str2 = string3;
                i2 = 2131232164;
            }
        } else {
            int hand = ((SensAIActivitySummary) arrayList.get(0)).getHand();
            i2 = R.drawable.ic_right_bowling;
            if (hand == 0) {
                string = this.f5394a.getString(R.string.right_hand);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.right_hand)");
                i = 2131232389;
            } else {
                string = this.f5394a.getString(R.string.left_hand);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.left_hand)");
                i = 2131232166;
            }
            if (((SensAIActivitySummary) arrayList.get(0)).getBowlingType() == 0) {
                str = string + ' ' + AppConstants.BOWLING_FAST.getValue();
            } else if (((SensAIActivitySummary) arrayList.get(0)).getBowlingType() == 1) {
                str = string + ' ' + AppConstants.BOWLING_MEDIUM_PACE.getValue();
            } else {
                str = string + ' ' + AppConstants.BOWLING_SPIN.getValue();
            }
            if (((SensAIActivitySummary) arrayList.get(1)).getHand() == 0) {
                string2 = this.f5394a.getString(R.string.right_hand);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.right_hand)");
            } else {
                string2 = this.f5394a.getString(R.string.left_hand);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.left_hand)");
                i2 = 2131232166;
            }
            if (((SensAIActivitySummary) arrayList.get(1)).getBowlingType() == 0) {
                str2 = string2 + ' ' + AppConstants.BOWLING_FAST.getValue();
            } else if (((SensAIActivitySummary) arrayList.get(1)).getBowlingType() == 1) {
                str2 = string2 + ' ' + AppConstants.BOWLING_MEDIUM_PACE.getValue();
            } else {
                str2 = string2 + ' ' + AppConstants.BOWLING_SPIN.getValue();
            }
        }
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM YYYY, hh:mm aa");
        Long timestamp = ((SensAIActivitySummary) arrayList.get(0)).getTimestamp();
        String format = simpleDateFormat.format(timestamp != null ? new Date(timestamp.longValue()) : null);
        Long timestamp2 = ((SensAIActivitySummary) arrayList.get(1)).getTimestamp();
        String format2 = simpleDateFormat.format(timestamp2 != null ? new Date(timestamp2.longValue()) : null);
        arrayList2.add(new SensAICompareTitle(str, Integer.valueOf(i), format));
        arrayList2.add(new SensAICompareTitle(str2, Integer.valueOf(i2), format2));
        return arrayList2;
    }

    @NotNull
    public final Context getContext() {
        return this.f5394a;
    }

    @NotNull
    public final LiveData<List<SensAIActivitySummary>> getSensAIActivitySummaryDataFromDB(@NotNull String serialNo, @Nullable ArrayList<String> arrayList, int i) {
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        LiveData<List<SensAIActivitySummary>> activitySummaryLiveData = SensAIBeamDBRead.getInstance(this.f5394a).getActivitySummaryLiveData(serialNo, arrayList, i);
        Intrinsics.checkNotNullExpressionValue(activitySummaryLiveData, "getInstance(context)\n   …ectedFilters, sortByItem)");
        return activitySummaryLiveData;
    }

    public final void getSessionsListFromServer(@NotNull Calendar fromDate, @NotNull Calendar toDate, @NotNull final String macAddress) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        TraqConfigApi.getSessionHeaderListFromServer(0, AppUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), AppUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), PreferenceManager.getInstance().getUserDeviceID(), new CoveApiListener<GetActivitySessionHeaderResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDataViewModel$getSessionsListFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetActivitySessionHeaderResponse getActivitySessionHeaderResponse) {
                String str;
                if (getActivitySessionHeaderResponse != null) {
                    fitnessActivitySessions fitnessactivitysessions = getActivitySessionHeaderResponse.requestData;
                    List<PostActivitySessionDataRequest> fitnessActivitySessions = fitnessactivitysessions != null ? fitnessactivitysessions.getFitnessActivitySessions() : null;
                    if (fitnessActivitySessions != null) {
                        for (final PostActivitySessionDataRequest postActivitySessionDataRequest : fitnessActivitySessions) {
                            try {
                                if (m.equals(postActivitySessionDataRequest.getSessionType(), SensAISummaryDataViewModel.this.getContext().getString(R.string.cricket__batting), true)) {
                                    if (SensAIBeamDBRead.getInstance(SensAISummaryDataViewModel.this.getContext()).isSessionIdExists(postActivitySessionDataRequest.getFwSessionId(), macAddress) != 1) {
                                        final ArrayList arrayList = new ArrayList();
                                        String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
                                        Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
                                        int parseInt = Integer.parseInt(userDeviceID);
                                        String clientRefId = postActivitySessionDataRequest.getClientRefId();
                                        ActivityType activityType = ActivityType.CRICKET_BATTING;
                                        final String str2 = macAddress;
                                        final SensAISummaryDataViewModel sensAISummaryDataViewModel = SensAISummaryDataViewModel.this;
                                        TraqConfigApi.getSessionOverallDataFromServer(0, parseInt, clientRefId, activityType, new CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDataViewModel$getSessionsListFromServer$1$onSuccess$1

                                            @DebugMetadata(c = "com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDataViewModel$getSessionsListFromServer$1$onSuccess$1$onSuccess$1", f = "SensAISummaryDataViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                            /* loaded from: classes5.dex */
                                            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                public final /* synthetic */ List<SensAIActivitySummary> $battingList;
                                                public final /* synthetic */ PostActivitySessionDataRequest $dataItem;
                                                public final /* synthetic */ String $macAddress;
                                                public final /* synthetic */ PostActivitySessionHeaderResponse $p0;
                                                public int label;
                                                public final /* synthetic */ SensAISummaryDataViewModel this$0;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                public a(PostActivitySessionHeaderResponse postActivitySessionHeaderResponse, String str, SensAISummaryDataViewModel sensAISummaryDataViewModel, PostActivitySessionDataRequest postActivitySessionDataRequest, List<SensAIActivitySummary> list, Continuation<? super a> continuation) {
                                                    super(2, continuation);
                                                    this.$p0 = postActivitySessionHeaderResponse;
                                                    this.$macAddress = str;
                                                    this.this$0 = sensAISummaryDataViewModel;
                                                    this.$dataItem = postActivitySessionDataRequest;
                                                    this.$battingList = list;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                @NotNull
                                                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                                    return new a(this.$p0, this.$macAddress, this.this$0, this.$dataItem, this.$battingList, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                @Nullable
                                                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                                                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                @Nullable
                                                public final Object invokeSuspend(@NotNull Object obj) {
                                                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                                    if (this.label == 0) {
                                                        ResultKt.throwOnFailure(obj);
                                                        SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                                                        SensAIActivitySummaryDetails sensAIActivitySummaryDetails = new SensAIActivitySummaryDetails();
                                                        sensAIActivitySummary.setSessionId(this.$p0.requestData.getFwSessionId());
                                                        sensAIActivitySummary.setMacAddress(this.$macAddress);
                                                        sensAIActivitySummary.setClientRefID(this.$p0.requestData.getClientRefId());
                                                        PayUtils payUtils = PayUtils.INSTANCE;
                                                        String sessionStartDate = this.$p0.requestData.getSessionStartDate();
                                                        Intrinsics.checkNotNullExpressionValue(sessionStartDate, "p0.requestData.sessionStartDate");
                                                        Long timeStampFromDate = payUtils.getTimeStampFromDate(sessionStartDate);
                                                        Intrinsics.checkNotNull(timeStampFromDate);
                                                        sensAIActivitySummary.setTimestamp(timeStampFromDate);
                                                        sensAIActivitySummary.setActivityType(1);
                                                        sensAIActivitySummary.setDurationSec(this.$p0.requestData.getTotalActivityDuration());
                                                        Integer totalSteps = this.$p0.requestData.getActivityData().getTotalSteps();
                                                        Intrinsics.checkNotNullExpressionValue(totalSteps, "p0.requestData.activityData.totalSteps");
                                                        sensAIActivitySummary.setTotalSteps(totalSteps.intValue());
                                                        sensAIActivitySummary.setTotalCalories(this.$p0.requestData.getActivityData().getTotalCalories().floatValue());
                                                        if (m.equals(this.$p0.requestData.getActivityData().getPlayingHand(), this.this$0.getContext().getString(R.string.right), true)) {
                                                            sensAIActivitySummary.setHand(0);
                                                        } else {
                                                            sensAIActivitySummary.setHand(1);
                                                        }
                                                        if (this.$p0.requestData.getActivityData().getTargets() != null) {
                                                            for (Target target : this.$p0.requestData.getActivityData().getTargets()) {
                                                                if (target.getBaseUnit().equals("MINUTES")) {
                                                                    sensAIActivitySummary.setGoalType(1);
                                                                    Integer value = target.getValue();
                                                                    Intrinsics.checkNotNullExpressionValue(value, "targetItem.value");
                                                                    sensAIActivitySummary.setTargetGoalValue(value.intValue());
                                                                } else {
                                                                    sensAIActivitySummary.setGoalType(2);
                                                                    Integer value2 = target.getValue();
                                                                    Intrinsics.checkNotNullExpressionValue(value2, "targetItem.value");
                                                                    sensAIActivitySummary.setTargetGoalValue(value2.intValue());
                                                                }
                                                            }
                                                        }
                                                        Integer targetAchievedPct = this.$p0.requestData.getActivityData().getTargetAchievedPct();
                                                        Intrinsics.checkNotNullExpressionValue(targetAchievedPct, "p0.requestData.activityData.targetAchievedPct");
                                                        sensAIActivitySummary.setGoalCompletionPct(targetAchievedPct.intValue());
                                                        Integer maxHr = this.$p0.requestData.getActivityData().getMaxHr();
                                                        Intrinsics.checkNotNullExpressionValue(maxHr, "p0.requestData.activityData.maxHr");
                                                        sensAIActivitySummary.setMaxHR(maxHr.intValue());
                                                        Integer avgHr = this.$p0.requestData.getActivityData().getAvgHr();
                                                        Intrinsics.checkNotNullExpressionValue(avgHr, "p0.requestData.activityData.avgHr");
                                                        sensAIActivitySummary.setAvgHR(avgHr.intValue());
                                                        Integer totalSwings = this.$p0.requestData.getActivityData().getTotalSwings();
                                                        Intrinsics.checkNotNullExpressionValue(totalSwings, "p0.requestData.activityData.totalSwings");
                                                        sensAIActivitySummary.setTotalSwings(totalSwings.intValue());
                                                        Integer totalHits = this.$p0.requestData.getActivityData().getTotalHits();
                                                        Intrinsics.checkNotNullExpressionValue(totalHits, "p0.requestData.activityData.totalHits");
                                                        sensAIActivitySummary.setPlayed(totalHits.intValue());
                                                        Integer hitPercentage = this.$p0.requestData.getActivityData().getHitPercentage();
                                                        Intrinsics.checkNotNullExpressionValue(hitPercentage, "p0.requestData.activityData.hitPercentage");
                                                        sensAIActivitySummary.setHitPct(hitPercentage.intValue());
                                                        Double maxHandSpeed = this.$p0.requestData.getActivityData().getMaxHandSpeed();
                                                        Intrinsics.checkNotNullExpressionValue(maxHandSpeed, "p0.requestData.activityData.maxHandSpeed");
                                                        sensAIActivitySummary.setMaxHandSpeed(c.roundToInt(maxHandSpeed.doubleValue()));
                                                        Double avgHandSpeed = this.$p0.requestData.getActivityData().getAvgHandSpeed();
                                                        Intrinsics.checkNotNullExpressionValue(avgHandSpeed, "p0.requestData.activityData.avgHandSpeed");
                                                        sensAIActivitySummary.setAvgHandSpeed(c.roundToInt(avgHandSpeed.doubleValue()));
                                                        sensAIActivitySummary.setSavedServer(true);
                                                        sensAIActivitySummary.setDataAggregateSaved(!RepositoryUtils.isDateToday(this.$dataItem.getSessionStartDate()));
                                                        sensAIActivitySummary.setAddToCompare(false);
                                                        sensAIActivitySummaryDetails.setSessionId(this.$p0.requestData.getFwSessionId());
                                                        sensAIActivitySummaryDetails.setMacAddress(this.$macAddress);
                                                        sensAIActivitySummaryDetails.setActivityType(1);
                                                        PayUtils payUtils2 = PayUtils.INSTANCE;
                                                        String sessionStartDate2 = this.$p0.requestData.getSessionStartDate();
                                                        Intrinsics.checkNotNullExpressionValue(sessionStartDate2, "p0.requestData.sessionStartDate");
                                                        Long timeStampFromDate2 = payUtils2.getTimeStampFromDate(sessionStartDate2);
                                                        Intrinsics.checkNotNull(timeStampFromDate2);
                                                        sensAIActivitySummaryDetails.setUnixTimeStamp(timeStampFromDate2);
                                                        if (this.$p0.requestData.getFeedback() != null) {
                                                            sensAIActivitySummaryDetails.setFeedbackSaved(true);
                                                        }
                                                        if (this.$p0.requestData.getActivityData().getTraqActivityLogs() != null) {
                                                            sensAIActivitySummaryDetails.setDistance((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getDistanceValues());
                                                            sensAIActivitySummaryDetails.setHr((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHrValues());
                                                            sensAIActivitySummaryDetails.setHandSpeed((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHandSpeedValues());
                                                            sensAIActivitySummaryDetails.setSteps((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getStepValues());
                                                            ArrayList<Integer> arrayList = new ArrayList<>();
                                                            for (Boolean hitItem : this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHitValues()) {
                                                                Intrinsics.checkNotNullExpressionValue(hitItem, "hitItem");
                                                                if (hitItem.booleanValue()) {
                                                                    arrayList.add(Boxing.boxInt(1));
                                                                } else {
                                                                    arrayList.add(Boxing.boxInt(0));
                                                                }
                                                            }
                                                            sensAIActivitySummaryDetails.setHitOrMiss(arrayList);
                                                            ArrayList<Integer> arrayList2 = new ArrayList<>();
                                                            for (Float calorieItem : this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getCalorieValues()) {
                                                                Intrinsics.checkNotNullExpressionValue(calorieItem, "calorieItem");
                                                                arrayList2.add(Boxing.boxInt(c.roundToInt(calorieItem.floatValue())));
                                                            }
                                                            sensAIActivitySummaryDetails.setCalories(arrayList2);
                                                        }
                                                        sensAIActivitySummaryDetails.setDetailsDataNum(sensAIActivitySummaryDetails.getCalories().size());
                                                        this.$battingList.add(sensAIActivitySummary);
                                                        SensAIBeamDBWrite.getInstance(this.this$0.getContext()).insertActivitySummaryList(this.$battingList);
                                                        SensAIBeamDBWrite.getInstance(this.this$0.getContext()).insertActivitySummaryDetails(sensAIActivitySummaryDetails);
                                                        return Unit.INSTANCE;
                                                    }
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                            }

                                            /* loaded from: classes5.dex */
                                            public static final class b extends Lambda implements Function1<Throwable, Unit> {
                                                public final /* synthetic */ SensAISummaryDataViewModel this$0;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                public b(SensAISummaryDataViewModel sensAISummaryDataViewModel) {
                                                    super(1);
                                                    this.this$0 = sensAISummaryDataViewModel;
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                                    invoke2(th);
                                                    return Unit.INSTANCE;
                                                }

                                                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                                                public final void invoke2(@Nullable Throwable th) {
                                                    String str;
                                                    str = this.this$0.b;
                                                    LogHelper.d(str, "Inserted Successfully");
                                                }
                                            }

                                            @Override // com.coveiot.coveaccess.CoveApiListener
                                            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                                            }

                                            @Override // com.coveiot.coveaccess.CoveApiListener
                                            public void onSuccess(@Nullable PostActivitySessionHeaderResponse postActivitySessionHeaderResponse) {
                                                Job e;
                                                if ((postActivitySessionHeaderResponse != null ? postActivitySessionHeaderResponse.requestData : null) != null) {
                                                    e = e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(postActivitySessionHeaderResponse, str2, sensAISummaryDataViewModel, postActivitySessionDataRequest, arrayList, null), 2, null);
                                                    e.invokeOnCompletion(new b(sensAISummaryDataViewModel));
                                                }
                                            }
                                        });
                                    }
                                } else if (m.equals(postActivitySessionDataRequest.getSessionType(), SensAISummaryDataViewModel.this.getContext().getString(R.string.cricket__bowling), true) && SensAIBeamDBRead.getInstance(SensAISummaryDataViewModel.this.getContext()).isSessionIdExists(postActivitySessionDataRequest.getFwSessionId(), macAddress) != 1) {
                                    final ArrayList arrayList2 = new ArrayList();
                                    String userDeviceID2 = PreferenceManager.getInstance().getUserDeviceID();
                                    Intrinsics.checkNotNullExpressionValue(userDeviceID2, "getInstance().userDeviceID");
                                    int parseInt2 = Integer.parseInt(userDeviceID2);
                                    String clientRefId2 = postActivitySessionDataRequest.getClientRefId();
                                    ActivityType activityType2 = ActivityType.CRICKET_BOWLING;
                                    final String str3 = macAddress;
                                    final SensAISummaryDataViewModel sensAISummaryDataViewModel2 = SensAISummaryDataViewModel.this;
                                    TraqConfigApi.getSessionOverallDataFromServer(0, parseInt2, clientRefId2, activityType2, new CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDataViewModel$getSessionsListFromServer$1$onSuccess$2

                                        @DebugMetadata(c = "com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDataViewModel$getSessionsListFromServer$1$onSuccess$2$onSuccess$1", f = "SensAISummaryDataViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                        /* loaded from: classes5.dex */
                                        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            public final /* synthetic */ List<SensAIActivitySummary> $bowlingList;
                                            public final /* synthetic */ PostActivitySessionDataRequest $dataItem;
                                            public final /* synthetic */ String $macAddress;
                                            public final /* synthetic */ PostActivitySessionHeaderResponse $p0;
                                            public int label;
                                            public final /* synthetic */ SensAISummaryDataViewModel this$0;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            public a(PostActivitySessionHeaderResponse postActivitySessionHeaderResponse, String str, SensAISummaryDataViewModel sensAISummaryDataViewModel, PostActivitySessionDataRequest postActivitySessionDataRequest, List<SensAIActivitySummary> list, Continuation<? super a> continuation) {
                                                super(2, continuation);
                                                this.$p0 = postActivitySessionHeaderResponse;
                                                this.$macAddress = str;
                                                this.this$0 = sensAISummaryDataViewModel;
                                                this.$dataItem = postActivitySessionDataRequest;
                                                this.$bowlingList = list;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            @NotNull
                                            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                                return new a(this.$p0, this.$macAddress, this.this$0, this.$dataItem, this.$bowlingList, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            @Nullable
                                            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                                                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            @Nullable
                                            public final Object invokeSuspend(@NotNull Object obj) {
                                                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                                if (this.label == 0) {
                                                    ResultKt.throwOnFailure(obj);
                                                    SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                                                    SensAIActivitySummaryDetails sensAIActivitySummaryDetails = new SensAIActivitySummaryDetails();
                                                    sensAIActivitySummary.setSessionId(this.$p0.requestData.getFwSessionId());
                                                    sensAIActivitySummary.setMacAddress(this.$macAddress);
                                                    sensAIActivitySummary.setClientRefID(this.$p0.requestData.getClientRefId());
                                                    PayUtils payUtils = PayUtils.INSTANCE;
                                                    String sessionStartDate = this.$p0.requestData.getSessionStartDate();
                                                    Intrinsics.checkNotNullExpressionValue(sessionStartDate, "p0.requestData.sessionStartDate");
                                                    Long timeStampFromDate = payUtils.getTimeStampFromDate(sessionStartDate);
                                                    Intrinsics.checkNotNull(timeStampFromDate);
                                                    sensAIActivitySummary.setTimestamp(timeStampFromDate);
                                                    sensAIActivitySummary.setActivityType(2);
                                                    sensAIActivitySummary.setDurationSec(this.$p0.requestData.getTotalActivityDuration());
                                                    Integer totalSteps = this.$p0.requestData.getActivityData().getTotalSteps();
                                                    Intrinsics.checkNotNullExpressionValue(totalSteps, "p0.requestData.activityData.totalSteps");
                                                    sensAIActivitySummary.setTotalSteps(totalSteps.intValue());
                                                    sensAIActivitySummary.setTotalCalories(this.$p0.requestData.getActivityData().getTotalCalories().floatValue());
                                                    if (m.equals(this.$p0.requestData.getActivityData().getPlayingHand(), this.this$0.getContext().getString(R.string.right), true)) {
                                                        sensAIActivitySummary.setHand(0);
                                                    } else {
                                                        sensAIActivitySummary.setHand(1);
                                                    }
                                                    if (this.$p0.requestData.getActivityData().getTargets() != null) {
                                                        for (Target target : this.$p0.requestData.getActivityData().getTargets()) {
                                                            if (target.getBaseUnit().equals("MINUTES")) {
                                                                sensAIActivitySummary.setGoalType(1);
                                                                Integer value = target.getValue();
                                                                Intrinsics.checkNotNullExpressionValue(value, "targetItem.value");
                                                                sensAIActivitySummary.setTargetGoalValue(value.intValue());
                                                            } else {
                                                                sensAIActivitySummary.setGoalType(2);
                                                                Integer value2 = target.getValue();
                                                                Intrinsics.checkNotNullExpressionValue(value2, "targetItem.value");
                                                                sensAIActivitySummary.setTargetGoalValue(value2.intValue());
                                                            }
                                                        }
                                                    }
                                                    Integer targetAchievedPct = this.$p0.requestData.getActivityData().getTargetAchievedPct();
                                                    Intrinsics.checkNotNullExpressionValue(targetAchievedPct, "p0.requestData.activityData.targetAchievedPct");
                                                    sensAIActivitySummary.setGoalCompletionPct(targetAchievedPct.intValue());
                                                    Integer maxHr = this.$p0.requestData.getActivityData().getMaxHr();
                                                    Intrinsics.checkNotNullExpressionValue(maxHr, "p0.requestData.activityData.maxHr");
                                                    sensAIActivitySummary.setMaxHR(maxHr.intValue());
                                                    Integer avgHr = this.$p0.requestData.getActivityData().getAvgHr();
                                                    Intrinsics.checkNotNullExpressionValue(avgHr, "p0.requestData.activityData.avgHr");
                                                    sensAIActivitySummary.setAvgHR(avgHr.intValue());
                                                    Integer totalBallsBowled = this.$p0.requestData.getActivityData().getTotalBallsBowled();
                                                    Intrinsics.checkNotNullExpressionValue(totalBallsBowled, "p0.requestData.activityData.totalBallsBowled");
                                                    sensAIActivitySummary.setTotalBallsBowled(totalBallsBowled.intValue());
                                                    if (this.$p0.requestData.getActivityData().getBowlingType().equals(AppConstants.BOWLING_FAST.getValue())) {
                                                        sensAIActivitySummary.setBowlingType(0);
                                                    } else if (this.$p0.requestData.getActivityData().getBowlingType().equals(AppConstants.BOWLING_MEDIUM.getValue())) {
                                                        sensAIActivitySummary.setBowlingType(1);
                                                    } else if (this.$p0.requestData.getActivityData().getBowlingType().equals(AppConstants.BOWLING_SPIN.getValue())) {
                                                        sensAIActivitySummary.setBowlingType(2);
                                                    }
                                                    Double maxHandSpeed = this.$p0.requestData.getActivityData().getMaxHandSpeed();
                                                    Intrinsics.checkNotNullExpressionValue(maxHandSpeed, "p0.requestData.activityData.maxHandSpeed");
                                                    sensAIActivitySummary.setMaxArmSpeed(c.roundToInt(maxHandSpeed.doubleValue()));
                                                    Double avgHandSpeed = this.$p0.requestData.getActivityData().getAvgHandSpeed();
                                                    Intrinsics.checkNotNullExpressionValue(avgHandSpeed, "p0.requestData.activityData.avgHandSpeed");
                                                    sensAIActivitySummary.setAvgArmSpeed(c.roundToInt(avgHandSpeed.doubleValue()));
                                                    Double minHandSpeed = this.$p0.requestData.getActivityData().getMinHandSpeed();
                                                    Intrinsics.checkNotNullExpressionValue(minHandSpeed, "p0.requestData.activityData.minHandSpeed");
                                                    sensAIActivitySummary.setMinArmSpeed(c.roundToInt(minHandSpeed.doubleValue()));
                                                    sensAIActivitySummary.setSavedServer(true);
                                                    sensAIActivitySummary.setDataAggregateSaved(!RepositoryUtils.isDateToday(this.$dataItem.getSessionStartDate()));
                                                    sensAIActivitySummary.setAddToCompare(false);
                                                    sensAIActivitySummaryDetails.setSessionId(this.$p0.requestData.getFwSessionId());
                                                    sensAIActivitySummaryDetails.setMacAddress(this.$macAddress);
                                                    sensAIActivitySummaryDetails.setActivityType(2);
                                                    PayUtils payUtils2 = PayUtils.INSTANCE;
                                                    String sessionStartDate2 = this.$p0.requestData.getSessionStartDate();
                                                    Intrinsics.checkNotNullExpressionValue(sessionStartDate2, "p0.requestData.sessionStartDate");
                                                    Long timeStampFromDate2 = payUtils2.getTimeStampFromDate(sessionStartDate2);
                                                    Intrinsics.checkNotNull(timeStampFromDate2);
                                                    sensAIActivitySummaryDetails.setUnixTimeStamp(timeStampFromDate2);
                                                    if (this.$p0.requestData.getFeedback() != null) {
                                                        sensAIActivitySummaryDetails.setFeedbackSaved(true);
                                                    }
                                                    if (this.$p0.requestData.getActivityData().getTraqActivityLogs() != null) {
                                                        sensAIActivitySummaryDetails.setDistance((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getDistanceValues());
                                                        sensAIActivitySummaryDetails.setHr((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHrValues());
                                                        sensAIActivitySummaryDetails.setHandSpeed((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHandSpeedValues());
                                                        sensAIActivitySummaryDetails.setSteps((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getStepValues());
                                                        ArrayList<Integer> arrayList = new ArrayList<>();
                                                        for (Float calorieItem : this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getCalorieValues()) {
                                                            Intrinsics.checkNotNullExpressionValue(calorieItem, "calorieItem");
                                                            arrayList.add(Boxing.boxInt(c.roundToInt(calorieItem.floatValue())));
                                                        }
                                                        sensAIActivitySummaryDetails.setCalories(arrayList);
                                                        sensAIActivitySummaryDetails.setDetailsDataNum(sensAIActivitySummaryDetails.getCalories().size());
                                                    }
                                                    this.$bowlingList.add(sensAIActivitySummary);
                                                    SensAIBeamDBWrite.getInstance(this.this$0.getContext()).insertActivitySummaryList(this.$bowlingList);
                                                    SensAIBeamDBWrite.getInstance(this.this$0.getContext()).insertActivitySummaryDetails(sensAIActivitySummaryDetails);
                                                    return Unit.INSTANCE;
                                                }
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                        }

                                        /* loaded from: classes5.dex */
                                        public static final class b extends Lambda implements Function1<Throwable, Unit> {
                                            public final /* synthetic */ SensAISummaryDataViewModel this$0;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            public b(SensAISummaryDataViewModel sensAISummaryDataViewModel) {
                                                super(1);
                                                this.this$0 = sensAISummaryDataViewModel;
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                                                invoke2(th);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                                            public final void invoke2(@Nullable Throwable th) {
                                                String str;
                                                str = this.this$0.b;
                                                LogHelper.d(str, "Inserted Successfully");
                                            }
                                        }

                                        @Override // com.coveiot.coveaccess.CoveApiListener
                                        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                                        }

                                        @Override // com.coveiot.coveaccess.CoveApiListener
                                        public void onSuccess(@Nullable PostActivitySessionHeaderResponse postActivitySessionHeaderResponse) {
                                            Job e;
                                            if ((postActivitySessionHeaderResponse != null ? postActivitySessionHeaderResponse.requestData : null) != null) {
                                                e = e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(postActivitySessionHeaderResponse, str3, sensAISummaryDataViewModel2, postActivitySessionDataRequest, arrayList2, null), 2, null);
                                                e.invokeOnCompletion(new b(sensAISummaryDataViewModel2));
                                            }
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                str = SensAISummaryDataViewModel.this.b;
                                LogHelper.d(str, e.toString());
                            }
                        }
                    }
                }
            }
        });
    }
}
