package com.coveiot.android.leonardo.sensai.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.activitymodes.activities.HeartRateZoneInfoActivity;
import com.coveiot.android.activitymodes.database.models.HeartRateZoneRanges;
import com.coveiot.android.activitymodes.database.models.TimeSpentHeartRateZone;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivitySessionInsightsDetailsBinding;
import com.coveiot.android.leonardo.sensai.adapter.SensAIFeedbackAdapter;
import com.coveiot.android.leonardo.sensai.model.SummaryShareData;
import com.coveiot.android.leonardo.sensai.model.feedback.AnswerModel;
import com.coveiot.android.leonardo.sensai.model.feedback.FeedbackQuestionnarieModel;
import com.coveiot.android.leonardo.sensai.model.feedback.OptionModel;
import com.coveiot.android.leonardo.sensai.model.feedback.QuestionModel;
import com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDetailsViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.ExtensionFuncsKt;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySessionInsightsDetails extends BaseActivity implements SensAIFeedbackAdapter.onItemClickListener {
    public SensAIFeedbackAdapter SensAIFeedbackAdapter;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivitySessionInsightsDetailsBinding p;
    public SensAISummaryDetailsViewModel q;
    public SensAIActivitySummaryDetails r;
    public SensAIActivitySummary s;
    public int t;
    public boolean u;
    @Nullable
    public String v;
    public int w;
    public int x;
    public FeedbackQuestionnarieModel y;

    /* loaded from: classes5.dex */
    public final class BattingChartValueFormatter implements IValueFormatter {
        public BattingChartValueFormatter(ActivitySessionInsightsDetails activitySessionInsightsDetails) {
        }

        @Override // com.github.mikephil.charting.formatter.IValueFormatter
        @NotNull
        public String getFormattedValue(float f, @Nullable Entry entry, int i, @Nullable ViewPortHandler viewPortHandler) {
            if (f == 0.0f) {
                return "";
            }
            return ((int) f) + " %";
        }
    }

    public static final void B(ActivitySessionInsightsDetails this$0, Ref.ObjectRef sessionID, SensAIActivitySummary sensAIActivitySummary) {
        String string;
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sessionID, "$sessionID");
        if (sensAIActivitySummary != null) {
            this$0.s = sensAIActivitySummary;
            String clientRefID = sensAIActivitySummary.getClientRefID();
            SensAIActivitySummary sensAIActivitySummary2 = null;
            if (clientRefID == null || clientRefID.length() == 0) {
                SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel = this$0.q;
                if (sensAISummaryDetailsViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
                    sensAISummaryDetailsViewModel = null;
                }
                sensAISummaryDetailsViewModel.syncSensAI((String) sessionID.element, BleApiManager.getInstance(this$0).getBleApi().getMacAddress());
            } else {
                SensAIActivitySummary sensAIActivitySummary3 = this$0.s;
                if (sensAIActivitySummary3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary3 = null;
                }
                this$0.v = sensAIActivitySummary3.getClientRefID();
            }
            SensAIActivitySummary sensAIActivitySummary4 = this$0.s;
            if (sensAIActivitySummary4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary4 = null;
            }
            if (sensAIActivitySummary4.getGoalCompletionPct() >= 100) {
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding = this$0.p;
                if (activitySessionInsightsDetailsBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding = null;
                }
                activitySessionInsightsDetailsBinding.ivGoalAchieved.setVisibility(0);
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding2 = this$0.p;
                if (activitySessionInsightsDetailsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding2 = null;
                }
                TextView textView = activitySessionInsightsDetailsBinding2.tvGoalTxt;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string2 = this$0.getString(R.string.congratulations_sens_ai);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.congratulations_sens_ai)");
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("");
                SensAIActivitySummary sensAIActivitySummary5 = this$0.s;
                if (sensAIActivitySummary5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary5 = null;
                }
                sb.append(sensAIActivitySummary5.getGoalCompletionPct());
                sb.append(" % ");
                objArr[0] = sb.toString();
                String format = String.format(locale, string2, Arrays.copyOf(objArr, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                textView.setText(format);
            } else {
                SensAIActivitySummary sensAIActivitySummary6 = this$0.s;
                if (sensAIActivitySummary6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary6 = null;
                }
                if (sensAIActivitySummary6.getGoalCompletionPct() > 0) {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding3 = this$0.p;
                    if (activitySessionInsightsDetailsBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding3 = null;
                    }
                    activitySessionInsightsDetailsBinding3.ivGoalAchieved.setVisibility(8);
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding4 = this$0.p;
                    if (activitySessionInsightsDetailsBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding4 = null;
                    }
                    TextView textView2 = activitySessionInsightsDetailsBinding4.tvGoalTxt;
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    Locale locale2 = Locale.ENGLISH;
                    String string3 = this$0.getString(R.string.train_harder);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.train_harder)");
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("");
                    SensAIActivitySummary sensAIActivitySummary7 = this$0.s;
                    if (sensAIActivitySummary7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary7 = null;
                    }
                    sb2.append(sensAIActivitySummary7.getGoalCompletionPct());
                    sb2.append(" % ");
                    objArr2[0] = sb2.toString();
                    String format2 = String.format(locale2, string3, Arrays.copyOf(objArr2, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                    textView2.setText(format2);
                } else {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding5 = this$0.p;
                    if (activitySessionInsightsDetailsBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding5 = null;
                    }
                    activitySessionInsightsDetailsBinding5.ivGoalAchieved.setVisibility(8);
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding6 = this$0.p;
                    if (activitySessionInsightsDetailsBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding6 = null;
                    }
                    activitySessionInsightsDetailsBinding6.tvGoalSummaryTxt.setVisibility(8);
                    this$0._$_findCachedViewById(R.id.view_goal_summary).setVisibility(8);
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding7 = this$0.p;
                    if (activitySessionInsightsDetailsBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding7 = null;
                    }
                    activitySessionInsightsDetailsBinding7.clGoalSummary.setVisibility(8);
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding8 = this$0.p;
                    if (activitySessionInsightsDetailsBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding8 = null;
                    }
                    activitySessionInsightsDetailsBinding8.clGoalSummary2.setVisibility(8);
                }
            }
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding9 = this$0.p;
            if (activitySessionInsightsDetailsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding9 = null;
            }
            TextView textView3 = activitySessionInsightsDetailsBinding9.tvDurationTxt;
            PayUtils payUtils = PayUtils.INSTANCE;
            SensAIActivitySummary sensAIActivitySummary8 = this$0.s;
            if (sensAIActivitySummary8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary8 = null;
            }
            textView3.setText(payUtils.formatSecondsInHHMMSS((int) sensAIActivitySummary8.getDurationSec()));
            if (this$0.t == 1) {
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding10 = this$0.p;
                if (activitySessionInsightsDetailsBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding10 = null;
                }
                activitySessionInsightsDetailsBinding10.ivSpeed.setImageDrawable(this$0.getDrawable(R.drawable.avg_hand_speed));
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding11 = this$0.p;
                if (activitySessionInsightsDetailsBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding11 = null;
                }
                activitySessionInsightsDetailsBinding11.ivMaxSpeed.setImageDrawable(this$0.getDrawable(R.drawable.max_hand_speed));
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding12 = this$0.p;
                if (activitySessionInsightsDetailsBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding12 = null;
                }
                activitySessionInsightsDetailsBinding12.tvSpeedtxt.setText(this$0.getString(R.string.avg_hand_speed));
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding13 = this$0.p;
                if (activitySessionInsightsDetailsBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding13 = null;
                }
                activitySessionInsightsDetailsBinding13.tvMaxSpeedtxt.setText(this$0.getString(R.string.max_hand_speed));
                Boolean isDistanceUnitInMile = UserDataManager.getInstance(this$0).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this@Activit…ils).isDistanceUnitInMile");
                if (isDistanceUnitInMile.booleanValue()) {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding14 = this$0.p;
                    if (activitySessionInsightsDetailsBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding14 = null;
                    }
                    TextView textView4 = activitySessionInsightsDetailsBinding14.tvSpeed;
                    StringBuilder sb3 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary9 = this$0.s;
                    if (sensAIActivitySummary9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary9 = null;
                    }
                    sb3.append(payUtils.getSpeedValue(this$0, sensAIActivitySummary9.getAvgHandSpeed()));
                    sb3.append(' ');
                    sb3.append(this$0.getResources().getString(R.string.mil_per_hours));
                    sb3.append(' ');
                    textView4.setText(sb3.toString());
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding15 = this$0.p;
                    if (activitySessionInsightsDetailsBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding15 = null;
                    }
                    TextView textView5 = activitySessionInsightsDetailsBinding15.tvMaxSpeed;
                    StringBuilder sb4 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary10 = this$0.s;
                    if (sensAIActivitySummary10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary10 = null;
                    }
                    sb4.append(payUtils.getSpeedValue(this$0, sensAIActivitySummary10.getMaxHandSpeed()));
                    sb4.append(' ');
                    sb4.append(this$0.getResources().getString(R.string.mil_per_hours));
                    sb4.append(' ');
                    textView5.setText(sb4.toString());
                } else {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding16 = this$0.p;
                    if (activitySessionInsightsDetailsBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding16 = null;
                    }
                    TextView textView6 = activitySessionInsightsDetailsBinding16.tvSpeed;
                    StringBuilder sb5 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary11 = this$0.s;
                    if (sensAIActivitySummary11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary11 = null;
                    }
                    sb5.append(sensAIActivitySummary11.getAvgHandSpeed());
                    sb5.append(' ');
                    sb5.append(this$0.getResources().getString(R.string.km_per_hours));
                    sb5.append(' ');
                    textView6.setText(sb5.toString());
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding17 = this$0.p;
                    if (activitySessionInsightsDetailsBinding17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding17 = null;
                    }
                    TextView textView7 = activitySessionInsightsDetailsBinding17.tvMaxSpeed;
                    StringBuilder sb6 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary12 = this$0.s;
                    if (sensAIActivitySummary12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary12 = null;
                    }
                    sb6.append(sensAIActivitySummary12.getMaxHandSpeed());
                    sb6.append(' ');
                    sb6.append(this$0.getResources().getString(R.string.km_per_hours));
                    sb6.append(' ');
                    textView7.setText(sb6.toString());
                }
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding18 = this$0.p;
                if (activitySessionInsightsDetailsBinding18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding18 = null;
                }
                TextView textView8 = activitySessionInsightsDetailsBinding18.totalSwingsCount;
                SensAIActivitySummary sensAIActivitySummary13 = this$0.s;
                if (sensAIActivitySummary13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary13 = null;
                }
                textView8.setText(String.valueOf(sensAIActivitySummary13.getTotalSwings()));
                SensAIActivitySummary sensAIActivitySummary14 = this$0.s;
                if (sensAIActivitySummary14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary14 = null;
                }
                if (sensAIActivitySummary14.getHand() == 0) {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding19 = this$0.p;
                    if (activitySessionInsightsDetailsBinding19 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding19 = null;
                    }
                    activitySessionInsightsDetailsBinding19.ivSessionType.setImageDrawable(this$0.getDrawable(R.drawable.right_hand_batting));
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding20 = this$0.p;
                    if (activitySessionInsightsDetailsBinding20 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding20 = null;
                    }
                    activitySessionInsightsDetailsBinding20.tvSessionType.setText(this$0.getString(R.string.right_hand_batting));
                } else {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding21 = this$0.p;
                    if (activitySessionInsightsDetailsBinding21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding21 = null;
                    }
                    activitySessionInsightsDetailsBinding21.ivSessionType.setImageDrawable(this$0.getDrawable(R.drawable.left_hand_batting));
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding22 = this$0.p;
                    if (activitySessionInsightsDetailsBinding22 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding22 = null;
                    }
                    activitySessionInsightsDetailsBinding22.tvSessionType.setText(this$0.getString(R.string.left_hand_batting));
                }
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding23 = this$0.p;
                if (activitySessionInsightsDetailsBinding23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding23 = null;
                }
                activitySessionInsightsDetailsBinding23.speedTitle.setText(this$0.getString(R.string.hand_speed));
            } else {
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding24 = this$0.p;
                if (activitySessionInsightsDetailsBinding24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding24 = null;
                }
                activitySessionInsightsDetailsBinding24.ivSpeed.setImageDrawable(this$0.getDrawable(R.drawable.avg_arm_speed));
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding25 = this$0.p;
                if (activitySessionInsightsDetailsBinding25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding25 = null;
                }
                activitySessionInsightsDetailsBinding25.ivMaxSpeed.setImageDrawable(this$0.getDrawable(R.drawable.fastest_ball));
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding26 = this$0.p;
                if (activitySessionInsightsDetailsBinding26 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding26 = null;
                }
                activitySessionInsightsDetailsBinding26.tvSpeedtxt.setText(this$0.getString(R.string.avg_arm_speed));
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding27 = this$0.p;
                if (activitySessionInsightsDetailsBinding27 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding27 = null;
                }
                activitySessionInsightsDetailsBinding27.tvMaxSpeedtxt.setText(this$0.getString(R.string.max_arm_speed));
                Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(this$0).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(this@Activit…ils).isDistanceUnitInMile");
                if (isDistanceUnitInMile2.booleanValue()) {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding28 = this$0.p;
                    if (activitySessionInsightsDetailsBinding28 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding28 = null;
                    }
                    TextView textView9 = activitySessionInsightsDetailsBinding28.tvSpeed;
                    StringBuilder sb7 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary15 = this$0.s;
                    if (sensAIActivitySummary15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary15 = null;
                    }
                    sb7.append(payUtils.getSpeedValue(this$0, sensAIActivitySummary15.getAvgArmSpeed()));
                    sb7.append(' ');
                    sb7.append(this$0.getResources().getString(R.string.mil_per_hours));
                    sb7.append(' ');
                    textView9.setText(sb7.toString());
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding29 = this$0.p;
                    if (activitySessionInsightsDetailsBinding29 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding29 = null;
                    }
                    TextView textView10 = activitySessionInsightsDetailsBinding29.tvMaxSpeed;
                    StringBuilder sb8 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary16 = this$0.s;
                    if (sensAIActivitySummary16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary16 = null;
                    }
                    sb8.append(payUtils.getSpeedValue(this$0, sensAIActivitySummary16.getMaxArmSpeed()));
                    sb8.append(' ');
                    sb8.append(this$0.getResources().getString(R.string.mil_per_hours));
                    sb8.append(' ');
                    textView10.setText(sb8.toString());
                } else {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding30 = this$0.p;
                    if (activitySessionInsightsDetailsBinding30 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding30 = null;
                    }
                    TextView textView11 = activitySessionInsightsDetailsBinding30.tvSpeed;
                    StringBuilder sb9 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary17 = this$0.s;
                    if (sensAIActivitySummary17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary17 = null;
                    }
                    sb9.append(sensAIActivitySummary17.getAvgArmSpeed());
                    sb9.append(' ');
                    sb9.append(this$0.getResources().getString(R.string.km_per_hours));
                    sb9.append(' ');
                    textView11.setText(sb9.toString());
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding31 = this$0.p;
                    if (activitySessionInsightsDetailsBinding31 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding31 = null;
                    }
                    TextView textView12 = activitySessionInsightsDetailsBinding31.tvMaxSpeed;
                    StringBuilder sb10 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary18 = this$0.s;
                    if (sensAIActivitySummary18 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary18 = null;
                    }
                    sb10.append(sensAIActivitySummary18.getMaxArmSpeed());
                    sb10.append(' ');
                    sb10.append(this$0.getResources().getString(R.string.km_per_hours));
                    sb10.append(' ');
                    textView12.setText(sb10.toString());
                }
                SensAIActivitySummary sensAIActivitySummary19 = this$0.s;
                if (sensAIActivitySummary19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary19 = null;
                }
                if (sensAIActivitySummary19.getHand() == 0) {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding32 = this$0.p;
                    if (activitySessionInsightsDetailsBinding32 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding32 = null;
                    }
                    activitySessionInsightsDetailsBinding32.ivSessionType.setImageDrawable(this$0.getDrawable(R.drawable.right_hand_bowling));
                    string = this$0.getString(R.string.right_hand);
                    Intrinsics.checkNotNullExpressionValue(string, "{\n                      …                        }");
                } else {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding33 = this$0.p;
                    if (activitySessionInsightsDetailsBinding33 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding33 = null;
                    }
                    activitySessionInsightsDetailsBinding33.ivSessionType.setImageDrawable(this$0.getDrawable(R.drawable.left_hand_bowling));
                    string = this$0.getString(R.string.left_hand);
                    Intrinsics.checkNotNullExpressionValue(string, "{\n                      …                        }");
                }
                SensAIActivitySummary sensAIActivitySummary20 = this$0.s;
                if (sensAIActivitySummary20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary20 = null;
                }
                int bowlingType = sensAIActivitySummary20.getBowlingType();
                if (bowlingType == 0) {
                    str = string + ' ' + AppConstants.BOWLING_FAST.getValue();
                    Unit unit = Unit.INSTANCE;
                } else if (bowlingType != 1) {
                    str = string + ' ' + AppConstants.BOWLING_SPIN.getValue();
                    Unit unit2 = Unit.INSTANCE;
                } else {
                    str = string + ' ' + AppConstants.BOWLING_MEDIUM_PACE.getValue();
                    Unit unit3 = Unit.INSTANCE;
                }
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding34 = this$0.p;
                if (activitySessionInsightsDetailsBinding34 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding34 = null;
                }
                activitySessionInsightsDetailsBinding34.tvSessionType.setText(str);
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding35 = this$0.p;
                if (activitySessionInsightsDetailsBinding35 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding35 = null;
                }
                activitySessionInsightsDetailsBinding35.cvBowlingAnalysis.setVisibility(0);
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding36 = this$0.p;
                if (activitySessionInsightsDetailsBinding36 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding36 = null;
                }
                activitySessionInsightsDetailsBinding36.tvBowlingAnalysisTxt.setVisibility(0);
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding37 = this$0.p;
                if (activitySessionInsightsDetailsBinding37 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding37 = null;
                }
                TextView textView13 = activitySessionInsightsDetailsBinding37.totalBallsCount;
                SensAIActivitySummary sensAIActivitySummary21 = this$0.s;
                if (sensAIActivitySummary21 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary21 = null;
                }
                textView13.setText(String.valueOf(sensAIActivitySummary21.getTotalBallsBowled()));
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding38 = this$0.p;
                if (activitySessionInsightsDetailsBinding38 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding38 = null;
                }
                TextView textView14 = activitySessionInsightsDetailsBinding38.equivalentOverCount;
                SensAIActivitySummary sensAIActivitySummary22 = this$0.s;
                if (sensAIActivitySummary22 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary22 = null;
                }
                textView14.setText(String.valueOf(payUtils.getOversFromBalls(sensAIActivitySummary22.getTotalBallsBowled())));
                SensAIActivitySummary sensAIActivitySummary23 = this$0.s;
                if (sensAIActivitySummary23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary23 = null;
                }
                if (sensAIActivitySummary23.getTotalBallsBowled() < 6) {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding39 = this$0.p;
                    if (activitySessionInsightsDetailsBinding39 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding39 = null;
                    }
                    activitySessionInsightsDetailsBinding39.avgTimeOverCount.setText(" - ");
                } else {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding40 = this$0.p;
                    if (activitySessionInsightsDetailsBinding40 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySessionInsightsDetailsBinding40 = null;
                    }
                    TextView textView15 = activitySessionInsightsDetailsBinding40.avgTimeOverCount;
                    SensAIActivitySummary sensAIActivitySummary24 = this$0.s;
                    if (sensAIActivitySummary24 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary24 = null;
                    }
                    int durationSec = (int) sensAIActivitySummary24.getDurationSec();
                    SensAIActivitySummary sensAIActivitySummary25 = this$0.s;
                    if (sensAIActivitySummary25 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary25 = null;
                    }
                    textView15.setText(payUtils.formatSeconds((int) payUtils.getAvgTimeForOvers(durationSec, sensAIActivitySummary25.getTotalBallsBowled())));
                }
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding41 = this$0.p;
                if (activitySessionInsightsDetailsBinding41 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding41 = null;
                }
                activitySessionInsightsDetailsBinding41.tvShotAnalysisTxt.setVisibility(8);
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding42 = this$0.p;
                if (activitySessionInsightsDetailsBinding42 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding42 = null;
                }
                activitySessionInsightsDetailsBinding42.cvTotalSwings.setVisibility(8);
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding43 = this$0.p;
                if (activitySessionInsightsDetailsBinding43 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding43 = null;
                }
                activitySessionInsightsDetailsBinding43.speedTitle.setText(this$0.getString(R.string.arm_speed));
            }
            SensAIActivitySummary sensAIActivitySummary26 = this$0.s;
            if (sensAIActivitySummary26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary26 = null;
            }
            if (sensAIActivitySummary26.getGoalType() == 1) {
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding44 = this$0.p;
                if (activitySessionInsightsDetailsBinding44 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding44 = null;
                }
                activitySessionInsightsDetailsBinding44.tvTargetShotsTxt.setText(this$0.getString(R.string.target_duration));
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding45 = this$0.p;
                if (activitySessionInsightsDetailsBinding45 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding45 = null;
                }
                activitySessionInsightsDetailsBinding45.tvActualShotsTxt.setText(this$0.getString(R.string.actual_duration));
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding46 = this$0.p;
                if (activitySessionInsightsDetailsBinding46 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding46 = null;
                }
                TextView textView16 = activitySessionInsightsDetailsBinding46.tvTargetShots;
                StringBuilder sb11 = new StringBuilder();
                SensAIActivitySummary sensAIActivitySummary27 = this$0.s;
                if (sensAIActivitySummary27 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary27 = null;
                }
                sb11.append(sensAIActivitySummary27.getTargetGoalValue());
                sb11.append(' ');
                sb11.append(this$0.getString(R.string.mins));
                textView16.setText(sb11.toString());
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding47 = this$0.p;
                if (activitySessionInsightsDetailsBinding47 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySessionInsightsDetailsBinding47 = null;
                }
                TextView textView17 = activitySessionInsightsDetailsBinding47.tvActualShots;
                SensAIActivitySummary sensAIActivitySummary28 = this$0.s;
                if (sensAIActivitySummary28 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary28 = null;
                }
                textView17.setText(payUtils.formatSeconds((int) sensAIActivitySummary28.getDurationSec()));
            } else {
                SensAIActivitySummary sensAIActivitySummary29 = this$0.s;
                if (sensAIActivitySummary29 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary29 = null;
                }
                if (sensAIActivitySummary29.getGoalType() == 2) {
                    if (this$0.t == 1) {
                        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding48 = this$0.p;
                        if (activitySessionInsightsDetailsBinding48 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySessionInsightsDetailsBinding48 = null;
                        }
                        activitySessionInsightsDetailsBinding48.tvTargetShotsTxt.setText(this$0.getString(R.string.target_shots));
                        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding49 = this$0.p;
                        if (activitySessionInsightsDetailsBinding49 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySessionInsightsDetailsBinding49 = null;
                        }
                        activitySessionInsightsDetailsBinding49.tvActualShotsTxt.setText(this$0.getString(R.string.actual_shots));
                        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding50 = this$0.p;
                        if (activitySessionInsightsDetailsBinding50 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySessionInsightsDetailsBinding50 = null;
                        }
                        TextView textView18 = activitySessionInsightsDetailsBinding50.tvTargetShots;
                        SensAIActivitySummary sensAIActivitySummary30 = this$0.s;
                        if (sensAIActivitySummary30 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary30 = null;
                        }
                        textView18.setText(String.valueOf(sensAIActivitySummary30.getTargetGoalValue()));
                        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding51 = this$0.p;
                        if (activitySessionInsightsDetailsBinding51 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySessionInsightsDetailsBinding51 = null;
                        }
                        TextView textView19 = activitySessionInsightsDetailsBinding51.tvActualShots;
                        SensAIActivitySummary sensAIActivitySummary31 = this$0.s;
                        if (sensAIActivitySummary31 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary31 = null;
                        }
                        textView19.setText(String.valueOf(sensAIActivitySummary31.getTotalSwings()));
                    } else {
                        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding52 = this$0.p;
                        if (activitySessionInsightsDetailsBinding52 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySessionInsightsDetailsBinding52 = null;
                        }
                        activitySessionInsightsDetailsBinding52.tvTargetShotsTxt.setText(this$0.getString(R.string.target_balls));
                        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding53 = this$0.p;
                        if (activitySessionInsightsDetailsBinding53 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySessionInsightsDetailsBinding53 = null;
                        }
                        activitySessionInsightsDetailsBinding53.tvActualShotsTxt.setText(this$0.getString(R.string.actual_balls));
                        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding54 = this$0.p;
                        if (activitySessionInsightsDetailsBinding54 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySessionInsightsDetailsBinding54 = null;
                        }
                        TextView textView20 = activitySessionInsightsDetailsBinding54.tvTargetShots;
                        SensAIActivitySummary sensAIActivitySummary32 = this$0.s;
                        if (sensAIActivitySummary32 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary32 = null;
                        }
                        textView20.setText(String.valueOf(sensAIActivitySummary32.getTargetGoalValue()));
                        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding55 = this$0.p;
                        if (activitySessionInsightsDetailsBinding55 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySessionInsightsDetailsBinding55 = null;
                        }
                        TextView textView21 = activitySessionInsightsDetailsBinding55.tvActualShots;
                        SensAIActivitySummary sensAIActivitySummary33 = this$0.s;
                        if (sensAIActivitySummary33 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary33 = null;
                        }
                        textView21.setText(String.valueOf(sensAIActivitySummary33.getTotalBallsBowled()));
                    }
                }
            }
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding56 = this$0.p;
            if (activitySessionInsightsDetailsBinding56 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding56 = null;
            }
            TextView textView22 = activitySessionInsightsDetailsBinding56.tvCalories;
            SensAIActivitySummary sensAIActivitySummary34 = this$0.s;
            if (sensAIActivitySummary34 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary34 = null;
            }
            textView22.setText(String.valueOf(kotlin.math.c.roundToInt(sensAIActivitySummary34.getTotalCalories())));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding57 = this$0.p;
            if (activitySessionInsightsDetailsBinding57 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding57 = null;
            }
            TextView textView23 = activitySessionInsightsDetailsBinding57.tvHeartRate;
            SensAIActivitySummary sensAIActivitySummary35 = this$0.s;
            if (sensAIActivitySummary35 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary35 = null;
            }
            textView23.setText(String.valueOf(sensAIActivitySummary35.getAvgHR()));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding58 = this$0.p;
            if (activitySessionInsightsDetailsBinding58 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding58 = null;
            }
            TextView textView24 = activitySessionInsightsDetailsBinding58.tvSteps;
            SensAIActivitySummary sensAIActivitySummary36 = this$0.s;
            if (sensAIActivitySummary36 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary36 = null;
            }
            textView24.setText(String.valueOf(sensAIActivitySummary36.getTotalSteps()));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding59 = this$0.p;
            if (activitySessionInsightsDetailsBinding59 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding59 = null;
            }
            ProgressBar progressBar = activitySessionInsightsDetailsBinding59.goalProgressBar;
            SensAIActivitySummary sensAIActivitySummary37 = this$0.s;
            if (sensAIActivitySummary37 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
            } else {
                sensAIActivitySummary2 = sensAIActivitySummary37;
            }
            progressBar.setProgress(sensAIActivitySummary2.getGoalCompletionPct());
        }
    }

    public static final void C(ActivitySessionInsightsDetails this$0, SensAIActivitySummaryDetails sensAIActivitySummaryDetails) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel = null;
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding = null;
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding2 = null;
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding3 = null;
        if (sensAIActivitySummaryDetails != null) {
            if (!sensAIActivitySummaryDetails.isFeedbackSaved() && AppUtils.isNetConnected(this$0) && !this$0.u && this$0.v != null) {
                SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel2 = this$0.q;
                if (sensAISummaryDetailsViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
                    sensAISummaryDetailsViewModel2 = null;
                }
                int i = this$0.t;
                String str = this$0.v;
                Intrinsics.checkNotNull(str);
                sensAISummaryDetailsViewModel2.getSummaryDetailsFromServer(i, str);
                this$0.u = true;
            }
            this$0.dismissProgress();
            this$0.r = sensAIActivitySummaryDetails;
            if (this$0.t == 1) {
                ArrayList<Integer> hitOrMiss = sensAIActivitySummaryDetails.getHitOrMiss();
                Intrinsics.checkNotNullExpressionValue(hitOrMiss, "activitySummaryDetails.hitOrMiss");
                this$0.A(hitOrMiss);
                this$0.J(this$0.w, this$0.x);
                SensAIActivitySummaryDetails sensAIActivitySummaryDetails2 = this$0.r;
                if (sensAIActivitySummaryDetails2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryDetails");
                    sensAIActivitySummaryDetails2 = null;
                }
                ArrayList<Integer> handSpeed = sensAIActivitySummaryDetails2.getHandSpeed();
                Intrinsics.checkNotNullExpressionValue(handSpeed, "activitySummaryDetails.handSpeed");
                this$0.setArmSpeedGraphValues(handSpeed, 1);
            } else {
                ArrayList<Integer> handSpeed2 = sensAIActivitySummaryDetails.getHandSpeed();
                Intrinsics.checkNotNullExpressionValue(handSpeed2, "activitySummaryDetails.handSpeed");
                this$0.setArmSpeedGraphValues(handSpeed2, 2);
            }
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM YYYY, hh:mm aa");
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding4 = this$0.p;
            if (activitySessionInsightsDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding4 = null;
            }
            TextView textView = activitySessionInsightsDetailsBinding4.tvTime;
            SensAIActivitySummaryDetails sensAIActivitySummaryDetails3 = this$0.r;
            if (sensAIActivitySummaryDetails3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryDetails");
                sensAIActivitySummaryDetails3 = null;
            }
            Long unixTimeStamp = sensAIActivitySummaryDetails3.getUnixTimeStamp();
            Long valueOf = unixTimeStamp != null ? Long.valueOf(unixTimeStamp.longValue()) : null;
            Intrinsics.checkNotNull(valueOf);
            textView.setText(simpleDateFormat.format(new Date(valueOf.longValue())));
            SensAIActivitySummaryDetails sensAIActivitySummaryDetails4 = this$0.r;
            if (sensAIActivitySummaryDetails4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryDetails");
                sensAIActivitySummaryDetails4 = null;
            }
            ArrayList<Integer> hr = sensAIActivitySummaryDetails4.getHr();
            Intrinsics.checkNotNullExpressionValue(hr, "activitySummaryDetails.hr");
            this$0.I(hr);
            SensAIActivitySummaryDetails sensAIActivitySummaryDetails5 = this$0.r;
            if (sensAIActivitySummaryDetails5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryDetails");
                sensAIActivitySummaryDetails5 = null;
            }
            ArrayList<Integer> hr2 = sensAIActivitySummaryDetails5.getHr();
            Intrinsics.checkNotNullExpressionValue(hr2, "activitySummaryDetails.hr");
            SensAIActivitySummary sensAIActivitySummary = this$0.s;
            if (sensAIActivitySummary == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary = null;
            }
            this$0.H(hr2, (int) sensAIActivitySummary.getDurationSec());
            if (AppUtils.isNetConnected(this$0)) {
                SensAIActivitySummaryDetails sensAIActivitySummaryDetails6 = this$0.r;
                if (sensAIActivitySummaryDetails6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryDetails");
                    sensAIActivitySummaryDetails6 = null;
                }
                if (sensAIActivitySummaryDetails6.isFeedbackSaved()) {
                    ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding5 = this$0.p;
                    if (activitySessionInsightsDetailsBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activitySessionInsightsDetailsBinding = activitySessionInsightsDetailsBinding5;
                    }
                    activitySessionInsightsDetailsBinding.cvRateSession.setVisibility(8);
                    return;
                }
                ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding6 = this$0.p;
                if (activitySessionInsightsDetailsBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activitySessionInsightsDetailsBinding2 = activitySessionInsightsDetailsBinding6;
                }
                activitySessionInsightsDetailsBinding2.cvRateSession.setVisibility(0);
                return;
            }
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding7 = this$0.p;
            if (activitySessionInsightsDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySessionInsightsDetailsBinding3 = activitySessionInsightsDetailsBinding7;
            }
            activitySessionInsightsDetailsBinding3.cvRateSession.setVisibility(8);
        } else if (AppUtils.isNetConnected(this$0)) {
            String str2 = this$0.v;
            if (str2 != null) {
                SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel3 = this$0.q;
                if (sensAISummaryDetailsViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
                } else {
                    sensAISummaryDetailsViewModel = sensAISummaryDetailsViewModel3;
                }
                sensAISummaryDetailsViewModel.getSummaryDetailsFromServer(this$0.t, str2);
                this$0.u = true;
            }
        } else {
            this$0.dismissProgress();
            Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
        }
    }

    public static final void D(ActivitySessionInsightsDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SensAIActivitySummary sensAIActivitySummary = this$0.s;
        if (sensAIActivitySummary == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
            sensAIActivitySummary = null;
        }
        this$0.K(sensAIActivitySummary);
    }

    public static final void E(ActivitySessionInsightsDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, HeartRateZoneInfoActivity.class));
    }

    public static final void F(ActivitySessionInsightsDetails this$0, Ref.ObjectRef sessionID, Boolean b) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sessionID, "$sessionID");
        Intrinsics.checkNotNullExpressionValue(b, "b");
        if (b.booleanValue()) {
            Toast.makeText(this$0, this$0.getString(R.string.thankyou_for_your_feedback), 0).show();
            SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel = this$0.q;
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding = null;
            if (sensAISummaryDetailsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
                sensAISummaryDetailsViewModel = null;
            }
            sensAISummaryDetailsViewModel.updateFeedbackToServer(true, (String) sessionID.element, BleApiManager.getInstance(this$0).getBleApi().getMacAddress());
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding2 = this$0.p;
            if (activitySessionInsightsDetailsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySessionInsightsDetailsBinding = activitySessionInsightsDetailsBinding2;
            }
            activitySessionInsightsDetailsBinding.cvRateSession.setVisibility(8);
        } else {
            Toast.makeText(this$0, this$0.getString(R.string.failed_to_save), 0).show();
        }
        this$0.dismissProgress();
    }

    public static final void G(ActivitySessionInsightsDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(ActivitySessionInsightsDetails this$0, FeedbackQuestionnarieModel questionnarie) {
        QuestionModel questionModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(questionnarie, "questionnarie");
        this$0.y = questionnarie;
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding = null;
        if (questionnarie == null) {
            Intrinsics.throwUninitializedPropertyAccessException("feedbackQuestionnarieModel");
            questionnarie = null;
        }
        if (questionnarie.getQuestions() != null) {
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding2 = this$0.p;
            if (activitySessionInsightsDetailsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding2 = null;
            }
            TextView textView = activitySessionInsightsDetailsBinding2.rateSession;
            FeedbackQuestionnarieModel feedbackQuestionnarieModel = this$0.y;
            if (feedbackQuestionnarieModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("feedbackQuestionnarieModel");
                feedbackQuestionnarieModel = null;
            }
            List<QuestionModel> questions = feedbackQuestionnarieModel.getQuestions();
            Intrinsics.checkNotNull(questions);
            textView.setText(questions.get(0).getText());
            FeedbackQuestionnarieModel feedbackQuestionnarieModel2 = this$0.y;
            if (feedbackQuestionnarieModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("feedbackQuestionnarieModel");
                feedbackQuestionnarieModel2 = null;
            }
            List<QuestionModel> questions2 = feedbackQuestionnarieModel2.getQuestions();
            List<OptionModel> options = (questions2 == null || (questionModel = questions2.get(0)) == null) ? null : questionModel.getOptions();
            Intrinsics.checkNotNull(options);
            this$0.setSensAIFeedbackAdapter(new SensAIFeedbackAdapter(this$0, options, this$0));
            this$0.getSensAIFeedbackAdapter().setOnFeedBackClickListener(this$0);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding3 = this$0.p;
            if (activitySessionInsightsDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySessionInsightsDetailsBinding = activitySessionInsightsDetailsBinding3;
            }
            activitySessionInsightsDetailsBinding.rvFeedback.setAdapter(this$0.getSensAIFeedbackAdapter());
        }
    }

    public final int A(List<Integer> list) {
        int i = 0;
        for (Integer num : list) {
            if (num.intValue() == 1) {
                i++;
                this.w++;
            } else {
                this.x++;
            }
        }
        return i;
    }

    public final void H(ArrayList<Integer> arrayList, int i) {
        TimeSpentHeartRateZone z = z(arrayList);
        int zone1Time = z.getZone1Time() + z.getZone2Time() + z.getZone3Time() + z.getZone4Time() + z.getZone5Time();
        if (zone1Time > 0) {
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding = this.p;
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding2 = null;
            if (activitySessionInsightsDetailsBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding = null;
            }
            TextView textView = activitySessionInsightsDetailsBinding.tvProgressWarm;
            StringBuilder sb = new StringBuilder();
            float f = zone1Time;
            float f2 = 100;
            sb.append(kotlin.math.c.roundToInt((z.getZone1Time() / f) * f2));
            sb.append(" %");
            textView.setText(sb.toString());
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding3 = this.p;
            if (activitySessionInsightsDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding3 = null;
            }
            activitySessionInsightsDetailsBinding3.tvProgressFatBurn.setText(kotlin.math.c.roundToInt((z.getZone2Time() / f) * f2) + " %");
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding4 = this.p;
            if (activitySessionInsightsDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding4 = null;
            }
            activitySessionInsightsDetailsBinding4.tvProgressCardio.setText(kotlin.math.c.roundToInt((z.getZone3Time() / f) * f2) + " %");
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding5 = this.p;
            if (activitySessionInsightsDetailsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding5 = null;
            }
            activitySessionInsightsDetailsBinding5.tvProgressThreshold.setText(kotlin.math.c.roundToInt((z.getZone4Time() / f) * f2) + " %");
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding6 = this.p;
            if (activitySessionInsightsDetailsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding6 = null;
            }
            activitySessionInsightsDetailsBinding6.tvProgressPeak.setText(kotlin.math.c.roundToInt((z.getZone5Time() / f) * f2) + " %");
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding7 = this.p;
            if (activitySessionInsightsDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding7 = null;
            }
            activitySessionInsightsDetailsBinding7.pbWarm.setProgress(kotlin.math.c.roundToInt((z.getZone1Time() / f) * f2));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding8 = this.p;
            if (activitySessionInsightsDetailsBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding8 = null;
            }
            activitySessionInsightsDetailsBinding8.pbFatBurn.setProgress(kotlin.math.c.roundToInt((z.getZone2Time() / f) * f2));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding9 = this.p;
            if (activitySessionInsightsDetailsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding9 = null;
            }
            activitySessionInsightsDetailsBinding9.pbCardio.setProgress(kotlin.math.c.roundToInt((z.getZone3Time() / f) * f2));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding10 = this.p;
            if (activitySessionInsightsDetailsBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding10 = null;
            }
            activitySessionInsightsDetailsBinding10.pbThreshold.setProgress(kotlin.math.c.roundToInt((z.getZone4Time() / f) * f2));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding11 = this.p;
            if (activitySessionInsightsDetailsBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySessionInsightsDetailsBinding2 = activitySessionInsightsDetailsBinding11;
            }
            activitySessionInsightsDetailsBinding2.pbPeak.setProgress(kotlin.math.c.roundToInt((z.getZone5Time() / f) * f2));
        }
    }

    public final void I(ArrayList<Integer> arrayList) {
        ArrayList<Entry> arrayList2 = new ArrayList();
        Iterator<Integer> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            arrayList2.add(new Entry(i, it.next().intValue()));
        }
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding = this.p;
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding2 = null;
        if (activitySessionInsightsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding = null;
        }
        if (activitySessionInsightsDetailsBinding.heartrateChart != null) {
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding3 = this.p;
            if (activitySessionInsightsDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding3 = null;
            }
            activitySessionInsightsDetailsBinding3.heartrateChart.clear();
            ArrayList arrayList3 = new ArrayList();
            for (Entry entry : arrayList2) {
                if (entry.getY() == -1.0f) {
                    arrayList3.add(entry);
                }
            }
            arrayList2.removeAll(arrayList3);
            LineDataSet lineDataSet = new LineDataSet(arrayList2, "");
            lineDataSet.setCircleColor(getResources().getColor(R.color.colorPrimary));
            lineDataSet.setCircleSize(0.5f);
            lineDataSet.setDrawValues(false);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
            lineDataSet.setColor(getResources().getColor(R.color.colorPrimary));
            lineDataSet.setHighLightColor(getResources().getColor(17170445));
            lineDataSet.setFillColor(getResources().getColor(R.color.color_33E51C23));
            LineDataSet lineDataSet2 = new LineDataSet(arrayList3, "");
            lineDataSet2.setCircleColor(getResources().getColor(R.color.transparent));
            lineDataSet2.setDrawCircleHole(false);
            lineDataSet2.setDrawValues(false);
            lineDataSet2.setValueTextColor(getResources().getColor(17170445));
            lineDataSet2.setColor(getResources().getColor(17170445));
            lineDataSet2.setHighLightColor(getResources().getColor(17170445));
            LineData lineData = new LineData(lineDataSet, lineDataSet2);
            lineData.setDrawValues(false);
            lineData.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding4 = this.p;
            if (activitySessionInsightsDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding4 = null;
            }
            YAxis axisLeft = activitySessionInsightsDetailsBinding4.heartrateChart.getAxisLeft();
            Intrinsics.checkNotNull(axisLeft);
            axisLeft.setDrawAxisLine(true);
            axisLeft.setDrawGridLines(false);
            axisLeft.setAxisMinimum(0.0f);
            axisLeft.setEnabled(true);
            axisLeft.setAxisMaximum(240.0f);
            axisLeft.setAxisLineColor(getResources().getColor(R.color.color_757575));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding5 = this.p;
            if (activitySessionInsightsDetailsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding5 = null;
            }
            YAxis axisRight = activitySessionInsightsDetailsBinding5.heartrateChart.getAxisRight();
            Intrinsics.checkNotNull(axisRight);
            axisRight.setEnabled(false);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding6 = this.p;
            if (activitySessionInsightsDetailsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding6 = null;
            }
            XAxis xAxis = activitySessionInsightsDetailsBinding6.heartrateChart.getXAxis();
            Intrinsics.checkNotNull(xAxis);
            xAxis.setEnabled(true);
            xAxis.setGranularity(1.0f);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawLabels(true);
            xAxis.setAxisLineColor(getResources().getColor(R.color.color_757575));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding7 = this.p;
            if (activitySessionInsightsDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding7 = null;
            }
            Legend legend = activitySessionInsightsDetailsBinding7.heartrateChart.getLegend();
            Intrinsics.checkNotNull(legend);
            legend.setEnabled(false);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding8 = this.p;
            if (activitySessionInsightsDetailsBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding8 = null;
            }
            LineChart lineChart = activitySessionInsightsDetailsBinding8.heartrateChart;
            if (lineChart != null) {
                lineChart.setData(lineData);
            }
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding9 = this.p;
            if (activitySessionInsightsDetailsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding9 = null;
            }
            activitySessionInsightsDetailsBinding9.heartrateChart.setDrawGridBackground(false);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding10 = this.p;
            if (activitySessionInsightsDetailsBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding10 = null;
            }
            Description description = activitySessionInsightsDetailsBinding10.heartrateChart.getDescription();
            if (description != null) {
                description.setEnabled(false);
            }
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding11 = this.p;
            if (activitySessionInsightsDetailsBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding11 = null;
            }
            activitySessionInsightsDetailsBinding11.heartrateChart.setDrawBorders(false);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding12 = this.p;
            if (activitySessionInsightsDetailsBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding12 = null;
            }
            LineChart lineChart2 = activitySessionInsightsDetailsBinding12.heartrateChart;
            if (lineChart2 != null) {
                lineChart2.setAutoScaleMinMaxEnabled(true);
            }
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding13 = this.p;
            if (activitySessionInsightsDetailsBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding13 = null;
            }
            activitySessionInsightsDetailsBinding13.heartrateChart.setPinchZoom(false);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding14 = this.p;
            if (activitySessionInsightsDetailsBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding14 = null;
            }
            activitySessionInsightsDetailsBinding14.heartrateChart.setScaleEnabled(false);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding15 = this.p;
            if (activitySessionInsightsDetailsBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding15 = null;
            }
            activitySessionInsightsDetailsBinding15.heartrateChart.setVisibleXRangeMinimum(5.0f);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding16 = this.p;
            if (activitySessionInsightsDetailsBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding16 = null;
            }
            YAxis axisLeft2 = activitySessionInsightsDetailsBinding16.heartrateChart.getAxisLeft();
            Intrinsics.checkNotNull(axisLeft2);
            axisLeft2.setStartAtZero(true);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding17 = this.p;
            if (activitySessionInsightsDetailsBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding17 = null;
            }
            YAxis axisRight2 = activitySessionInsightsDetailsBinding17.heartrateChart.getAxisRight();
            Intrinsics.checkNotNull(axisRight2);
            axisRight2.setStartAtZero(true);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding18 = this.p;
            if (activitySessionInsightsDetailsBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding18 = null;
            }
            YAxis axisLeft3 = activitySessionInsightsDetailsBinding18.heartrateChart.getAxisLeft();
            Intrinsics.checkNotNull(axisLeft3);
            axisLeft3.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding19 = this.p;
            if (activitySessionInsightsDetailsBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding19 = null;
            }
            XAxis xAxis2 = activitySessionInsightsDetailsBinding19.heartrateChart.getXAxis();
            Intrinsics.checkNotNull(xAxis2);
            xAxis2.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding20 = this.p;
            if (activitySessionInsightsDetailsBinding20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding20 = null;
            }
            activitySessionInsightsDetailsBinding20.heartrateChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.sensai.activity.ActivitySessionInsightsDetails$setLineGraphValues$1
                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onNothingSelected() {
                }

                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    Intrinsics.checkNotNullParameter(h, "h");
                    if (((int) e.getY()) > 0) {
                        ActivitySessionInsightsDetails activitySessionInsightsDetails = ActivitySessionInsightsDetails.this;
                        Toast.makeText(activitySessionInsightsDetails, "Heart Rate: " + ((int) e.getY()), 0).show();
                    }
                }
            });
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding21 = this.p;
            if (activitySessionInsightsDetailsBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySessionInsightsDetailsBinding2 = activitySessionInsightsDetailsBinding21;
            }
            activitySessionInsightsDetailsBinding2.heartrateChart.invalidate();
        }
    }

    public final void J(int i, int i2) {
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding = this.p;
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding2 = null;
        if (activitySessionInsightsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding = null;
        }
        activitySessionInsightsDetailsBinding.pieChart.setUsePercentValues(true);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding3 = this.p;
        if (activitySessionInsightsDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding3 = null;
        }
        activitySessionInsightsDetailsBinding3.pieChart.getDescription().setEnabled(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding4 = this.p;
        if (activitySessionInsightsDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding4 = null;
        }
        activitySessionInsightsDetailsBinding4.pieChart.setExtraOffsets(5.0f, 10.0f, 5.0f, 5.0f);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding5 = this.p;
        if (activitySessionInsightsDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding5 = null;
        }
        activitySessionInsightsDetailsBinding5.pieChart.setDragDecelerationFrictionCoef(0.95f);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding6 = this.p;
        if (activitySessionInsightsDetailsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding6 = null;
        }
        activitySessionInsightsDetailsBinding6.pieChart.setDrawHoleEnabled(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding7 = this.p;
        if (activitySessionInsightsDetailsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding7 = null;
        }
        activitySessionInsightsDetailsBinding7.pieChart.setRotationEnabled(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding8 = this.p;
        if (activitySessionInsightsDetailsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding8 = null;
        }
        activitySessionInsightsDetailsBinding8.pieChart.setHighlightPerTapEnabled(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding9 = this.p;
        if (activitySessionInsightsDetailsBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding9 = null;
        }
        activitySessionInsightsDetailsBinding9.pieChart.setDrawEntryLabels(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding10 = this.p;
        if (activitySessionInsightsDetailsBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding10 = null;
        }
        activitySessionInsightsDetailsBinding10.pieChart.getLegend().setEnabled(false);
        PieEntry pieEntry = new PieEntry(i, "hit");
        PieEntry pieEntry2 = new PieEntry(i2, "missed");
        ArrayList arrayList = new ArrayList();
        arrayList.add(pieEntry);
        arrayList.add(pieEntry2);
        PieDataSet pieDataSet = new PieDataSet(arrayList, "Cricket");
        pieDataSet.setDrawIcons(false);
        pieDataSet.setSliceSpace(0.0f);
        pieDataSet.setIconsOffset(new MPPointF(0.0f, 40.0f));
        pieDataSet.setSelectionShift(5.0f);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(getColor(R.color.color_3c70ff)));
        arrayList2.add(Integer.valueOf(getColor(R.color.color_e76867)));
        pieDataSet.setColors(arrayList2);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter(new DecimalFormat("####,###,###")));
        Resources resources = getResources();
        Integer valueOf = resources != null ? Integer.valueOf(resources.getColor(R.color.main_text_color)) : null;
        Intrinsics.checkNotNull(valueOf);
        pieData.setValueTextColor(valueOf.intValue());
        pieDataSet.setDrawValues(true);
        pieDataSet.setValueFormatter(new BattingChartValueFormatter(this));
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding11 = this.p;
        if (activitySessionInsightsDetailsBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding11 = null;
        }
        activitySessionInsightsDetailsBinding11.pieChart.setData(pieData);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding12 = this.p;
        if (activitySessionInsightsDetailsBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding12 = null;
        }
        activitySessionInsightsDetailsBinding12.pieChart.highlightValues(null);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding13 = this.p;
        if (activitySessionInsightsDetailsBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySessionInsightsDetailsBinding2 = activitySessionInsightsDetailsBinding13;
        }
        activitySessionInsightsDetailsBinding2.pieChart.invalidate();
    }

    public final void K(SensAIActivitySummary sensAIActivitySummary) {
        SummaryShareData summaryShareData = new SummaryShareData();
        summaryShareData.setDuration((int) sensAIActivitySummary.getDurationSec());
        summaryShareData.setAvgHeartRate(sensAIActivitySummary.getAvgHR());
        Long timestamp = sensAIActivitySummary.getTimestamp();
        Intrinsics.checkNotNullExpressionValue(timestamp, "entitySummaryData.timestamp");
        summaryShareData.setTimeStamp(timestamp.longValue());
        summaryShareData.setCalories((float) sensAIActivitySummary.getTotalCalories());
        summaryShareData.setSteps(sensAIActivitySummary.getTotalSteps());
        summaryShareData.setActivityType(sensAIActivitySummary.getActivityType());
        summaryShareData.setHandType(sensAIActivitySummary.getHand());
        if (sensAIActivitySummary.getActivityType() == 1) {
            summaryShareData.setTotalShots(sensAIActivitySummary.getTotalSwings());
            summaryShareData.setAvgHandSpeed(sensAIActivitySummary.getAvgHandSpeed());
            summaryShareData.setMaxHandSpeed(sensAIActivitySummary.getMaxHandSpeed());
            summaryShareData.setHitPercentage(sensAIActivitySummary.getHitPct());
            summaryShareData.setPlayed(sensAIActivitySummary.getPlayed());
        } else {
            summaryShareData.setTotalBallsBowled(sensAIActivitySummary.getTotalBallsBowled());
            summaryShareData.setAvgArmSpeed(sensAIActivitySummary.getAvgArmSpeed());
            summaryShareData.setMaxArmSpeed(sensAIActivitySummary.getMaxArmSpeed());
            summaryShareData.setMinArmSpeed(sensAIActivitySummary.getMinArmSpeed());
            summaryShareData.setBowlingType(sensAIActivitySummary.getBowlingType());
        }
        Intent intent = new Intent(this, ActivitySensAIShareScreen.class);
        intent.putExtra("share_data", summaryShareData);
        startActivity(intent);
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @NotNull
    public final HeartRateZoneRanges geHeartRateZoneRanges(int i) {
        int maxHeartRate = getMaxHeartRate(i);
        int i2 = maxHeartRate - 50;
        Log.d("Session: ", String.valueOf(i2));
        return new HeartRateZoneRanges(0, ExtensionFuncsKt.percentage(i2, 50.0f), ExtensionFuncsKt.percentage(i2, 70.0f), ExtensionFuncsKt.percentage(i2, 80.0f), ExtensionFuncsKt.percentage(i2, 90.0f), maxHeartRate);
    }

    @Nullable
    public final String getClientRefId() {
        return this.v;
    }

    public final int getHit() {
        return this.w;
    }

    public final int getMaxHeartRate(int i) {
        if (i == 0) {
            i = 35;
        }
        return 220 - i;
    }

    public final int getMissed() {
        return this.x;
    }

    @NotNull
    public final SensAIFeedbackAdapter getSensAIFeedbackAdapter() {
        SensAIFeedbackAdapter sensAIFeedbackAdapter = this.SensAIFeedbackAdapter;
        if (sensAIFeedbackAdapter != null) {
            return sensAIFeedbackAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SensAIFeedbackAdapter");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void init() {
        this.q = (SensAISummaryDetailsViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(SensAISummaryDetailsViewModel.class);
        Intent intent = getIntent();
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel = null;
        Integer valueOf = intent != null ? Integer.valueOf(intent.getIntExtra("ACTIVITY_TYPE", 0)) : null;
        Intrinsics.checkNotNull(valueOf);
        this.t = valueOf.intValue();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Intent intent2 = getIntent();
        objectRef.element = intent2 != null ? intent2.getStringExtra("SESSION_ID") : 0;
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel2 = this.q;
        if (sensAISummaryDetailsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
            sensAISummaryDetailsViewModel2 = null;
        }
        T t = objectRef.element;
        Intrinsics.checkNotNull(t);
        sensAISummaryDetailsViewModel2.getSummaryDataItemFromDB((String) t, BleApiManager.getInstance(this).getBleApi().getMacAddress());
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel3 = this.q;
        if (sensAISummaryDetailsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
            sensAISummaryDetailsViewModel3 = null;
        }
        sensAISummaryDetailsViewModel3.getSummaryDetailsDataFromDB((String) objectRef.element, BleApiManager.getInstance(this).getBleApi().getMacAddress());
        showProgress();
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel4 = this.q;
        if (sensAISummaryDetailsViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
            sensAISummaryDetailsViewModel4 = null;
        }
        sensAISummaryDetailsViewModel4.getGetActivitySummaryDataLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.sensai.activity.j0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySessionInsightsDetails.B(ActivitySessionInsightsDetails.this, objectRef, (SensAIActivitySummary) obj);
            }
        });
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel5 = this.q;
        if (sensAISummaryDetailsViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
            sensAISummaryDetailsViewModel5 = null;
        }
        sensAISummaryDetailsViewModel5.getGetActivitySummaryDetailsLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.sensai.activity.i0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySessionInsightsDetails.C(ActivitySessionInsightsDetails.this, (SensAIActivitySummaryDetails) obj);
            }
        });
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding = this.p;
        if (activitySessionInsightsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding = null;
        }
        activitySessionInsightsDetailsBinding.shareButton.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySessionInsightsDetails.D(ActivitySessionInsightsDetails.this, view);
            }
        });
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding2 = this.p;
        if (activitySessionInsightsDetailsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding2 = null;
        }
        activitySessionInsightsDetailsBinding2.ivHrZoneInfo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySessionInsightsDetails.E(ActivitySessionInsightsDetails.this, view);
            }
        });
        x(this.t);
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel6 = this.q;
        if (sensAISummaryDetailsViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
        } else {
            sensAISummaryDetailsViewModel = sensAISummaryDetailsViewModel6;
        }
        sensAISummaryDetailsViewModel.getPostQuestionarieLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.sensai.activity.k0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySessionInsightsDetails.F(ActivitySessionInsightsDetails.this, objectRef, (Boolean) obj);
            }
        });
    }

    public final void initToolbar() {
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding = this.p;
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding2 = null;
        if (activitySessionInsightsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding = null;
        }
        TextView textView = (TextView) activitySessionInsightsDetailsBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding3 = this.p;
        if (activitySessionInsightsDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySessionInsightsDetailsBinding2 = activitySessionInsightsDetailsBinding3;
        }
        textView.setText(getString(R.string.session_insights));
        ((TextView) activitySessionInsightsDetailsBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySessionInsightsDetails.G(ActivitySessionInsightsDetails.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySessionInsightsDetailsBinding inflate = ActivitySessionInsightsDetailsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
        init();
    }

    @Override // com.coveiot.android.leonardo.sensai.adapter.SensAIFeedbackAdapter.onItemClickListener
    public void onItemClick(int i, @NotNull String optionId) {
        QuestionModel questionModel;
        Intrinsics.checkNotNullParameter(optionId, "optionId");
        if (AppUtils.isNetConnected(this)) {
            ArrayList arrayList = new ArrayList();
            AnswerModel answerModel = new AnswerModel();
            FeedbackQuestionnarieModel feedbackQuestionnarieModel = this.y;
            FeedbackQuestionnarieModel feedbackQuestionnarieModel2 = null;
            if (feedbackQuestionnarieModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("feedbackQuestionnarieModel");
                feedbackQuestionnarieModel = null;
            }
            List<QuestionModel> questions = feedbackQuestionnarieModel.getQuestions();
            answerModel.setQuestionId((questions == null || (questionModel = questions.get(0)) == null) ? null : questionModel.getQuestionId());
            if (!(optionId.length() == 0)) {
                answerModel.setAnswerIds(CollectionsKt___CollectionsKt.toList(CollectionsKt__CollectionsKt.mutableListOf(optionId)));
            }
            arrayList.add(answerModel);
            FeedbackQuestionnarieModel feedbackQuestionnarieModel3 = this.y;
            if (feedbackQuestionnarieModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("feedbackQuestionnarieModel");
                feedbackQuestionnarieModel3 = null;
            }
            feedbackQuestionnarieModel3.setAnswers(arrayList);
            String str = this.v;
            if (str != null) {
                SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel = this.q;
                if (sensAISummaryDetailsViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
                    sensAISummaryDetailsViewModel = null;
                }
                FeedbackQuestionnarieModel feedbackQuestionnarieModel4 = this.y;
                if (feedbackQuestionnarieModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("feedbackQuestionnarieModel");
                } else {
                    feedbackQuestionnarieModel2 = feedbackQuestionnarieModel4;
                }
                sensAISummaryDetailsViewModel.saveFeedbackAnswer(str, feedbackQuestionnarieModel2);
            }
            showProgress();
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
    }

    public final void setArmSpeedGraphValues(@NotNull ArrayList<Integer> armSpeedList, final int i) {
        int maximumYValue;
        LimitLine limitLine;
        LimitLine limitLine2;
        Intrinsics.checkNotNullParameter(armSpeedList, "armSpeedList");
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = armSpeedList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2++;
            int intValue = it.next().intValue();
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(this).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this@Activit…ils).isDistanceUnitInMile");
            if (isDistanceUnitInMile.booleanValue()) {
                arrayList.add(new BarEntry(i2, (float) PayUtils.INSTANCE.getSpeedValue(this, intValue)));
            } else {
                arrayList.add(new BarEntry(i2, intValue));
            }
        }
        BarDataSet barDataSet = new BarDataSet(arrayList, AppConstants.EMPTY_STRING.getValue());
        barDataSet.setDrawValues(false);
        barDataSet.setBarShadowColor(getResources().getColor(R.color.colorPrimary));
        barDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        barDataSet.setColor(getResources().getColor(R.color.colorPrimary));
        if (barDataSet.getEntryCount() > 0) {
            int entryCount = barDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i3 = 0; i3 < entryCount; i3++) {
                iArr[i3] = getResources().getColor(R.color.color_E51C23);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.color_E51C23);
            barDataSet.setColors(Arrays.copyOf(iArr, entryCount));
        }
        BarData barData = new BarData(barDataSet);
        if (barDataSet.getEntryCount() < 8) {
            barData.setBarWidth(0.06f);
        } else {
            barData.setBarWidth(0.1f);
        }
        barData.setDrawValues(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding = this.p;
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding2 = null;
        if (activitySessionInsightsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding = null;
        }
        activitySessionInsightsDetailsBinding.speedChart.setData(barData);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding3 = this.p;
        if (activitySessionInsightsDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding3 = null;
        }
        activitySessionInsightsDetailsBinding3.speedChart.setFitBars(true);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding4 = this.p;
        if (activitySessionInsightsDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding4 = null;
        }
        activitySessionInsightsDetailsBinding4.speedChart.setDrawValueAboveBar(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding5 = this.p;
        if (activitySessionInsightsDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding5 = null;
        }
        activitySessionInsightsDetailsBinding5.speedChart.setDrawGridBackground(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding6 = this.p;
        if (activitySessionInsightsDetailsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding6 = null;
        }
        activitySessionInsightsDetailsBinding6.speedChart.setScaleEnabled(false);
        Description description = new Description();
        description.setText("");
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding7 = this.p;
        if (activitySessionInsightsDetailsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding7 = null;
        }
        activitySessionInsightsDetailsBinding7.speedChart.setDescription(description);
        barData.setValueTextColor(getResources().getColor(R.color.color_E51C23));
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding8 = this.p;
        if (activitySessionInsightsDetailsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding8 = null;
        }
        activitySessionInsightsDetailsBinding8.speedChart.getPaint(7).setColor(getResources().getColor(R.color.color_E51C23));
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding9 = this.p;
        if (activitySessionInsightsDetailsBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding9 = null;
        }
        activitySessionInsightsDetailsBinding9.speedChart.setDrawBorders(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding10 = this.p;
        if (activitySessionInsightsDetailsBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding10 = null;
        }
        activitySessionInsightsDetailsBinding10.speedChart.setAutoScaleMinMaxEnabled(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding11 = this.p;
        if (activitySessionInsightsDetailsBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding11 = null;
        }
        YAxis axisLeft = activitySessionInsightsDetailsBinding11.speedChart.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.setYOffset(0.0f);
        axisLeft.setXOffset(15.0f);
        PayUtils payUtils = PayUtils.INSTANCE;
        if (payUtils.getMaximumYValue(arrayList) == 0) {
            axisLeft.mAxisMaximum = 1000.0f;
        } else {
            axisLeft.mAxisMaximum = (maximumYValue * 150) / 100.0f;
        }
        axisLeft.setAxisLineColor(getResources().getColor(R.color.color_757575));
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding12 = this.p;
        if (activitySessionInsightsDetailsBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding12 = null;
        }
        activitySessionInsightsDetailsBinding12.speedChart.getAxisRight().setEnabled(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding13 = this.p;
        if (activitySessionInsightsDetailsBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding13 = null;
        }
        XAxis xAxis = activitySessionInsightsDetailsBinding13.speedChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.color_757575));
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding14 = this.p;
        if (activitySessionInsightsDetailsBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding14 = null;
        }
        activitySessionInsightsDetailsBinding14.speedChart.getAxisLeft().setStartAtZero(true);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding15 = this.p;
        if (activitySessionInsightsDetailsBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding15 = null;
        }
        activitySessionInsightsDetailsBinding15.speedChart.getAxisRight().setStartAtZero(true);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding16 = this.p;
        if (activitySessionInsightsDetailsBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding16 = null;
        }
        activitySessionInsightsDetailsBinding16.speedChart.getAxisLeft().setTextColor(getResources().getColor(R.color.color_757575));
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding17 = this.p;
        if (activitySessionInsightsDetailsBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding17 = null;
        }
        activitySessionInsightsDetailsBinding17.speedChart.getXAxis().setTextColor(getResources().getColor(R.color.color_757575));
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding18 = this.p;
        if (activitySessionInsightsDetailsBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding18 = null;
        }
        activitySessionInsightsDetailsBinding18.speedChart.getLegend().setTextColor(getResources().getColor(R.color.color_757575));
        if (this.t == 1) {
            Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(this).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(this@Activit…ils).isDistanceUnitInMile");
            if (isDistanceUnitInMile2.booleanValue()) {
                SensAIActivitySummary sensAIActivitySummary = this.s;
                if (sensAIActivitySummary == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary = null;
                }
                limitLine2 = new LimitLine((float) payUtils.getSpeedValue(this, sensAIActivitySummary.getAvgHandSpeed()), "Avg");
            } else {
                SensAIActivitySummary sensAIActivitySummary2 = this.s;
                if (sensAIActivitySummary2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary2 = null;
                }
                limitLine2 = new LimitLine(sensAIActivitySummary2.getAvgHandSpeed(), "Avg");
            }
            limitLine2.setLineColor(Color.parseColor("#29ffffff"));
            limitLine2.setTextColor(getColor(R.color.secondary_text_color));
            limitLine2.enableDashedLine(33.0f, 2.0f, 2.0f);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding19 = this.p;
            if (activitySessionInsightsDetailsBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding19 = null;
            }
            activitySessionInsightsDetailsBinding19.speedChart.getAxisLeft().addLimitLine(limitLine2);
        } else {
            Boolean isDistanceUnitInMile3 = UserDataManager.getInstance(this).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile3, "getInstance(this@Activit…ils).isDistanceUnitInMile");
            if (isDistanceUnitInMile3.booleanValue()) {
                SensAIActivitySummary sensAIActivitySummary3 = this.s;
                if (sensAIActivitySummary3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary3 = null;
                }
                limitLine = new LimitLine((float) payUtils.getSpeedValue(this, sensAIActivitySummary3.getAvgArmSpeed()), "Avg");
            } else {
                SensAIActivitySummary sensAIActivitySummary4 = this.s;
                if (sensAIActivitySummary4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary4 = null;
                }
                limitLine = new LimitLine(sensAIActivitySummary4.getAvgArmSpeed(), "Avg");
            }
            limitLine.setLineColor(Color.parseColor("#29ffffff"));
            limitLine.setTextColor(getColor(R.color.secondary_text_color));
            limitLine.enableDashedLine(33.0f, 2.0f, 2.0f);
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding20 = this.p;
            if (activitySessionInsightsDetailsBinding20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySessionInsightsDetailsBinding20 = null;
            }
            activitySessionInsightsDetailsBinding20.speedChart.getAxisLeft().addLimitLine(limitLine);
        }
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding21 = this.p;
        if (activitySessionInsightsDetailsBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding21 = null;
        }
        activitySessionInsightsDetailsBinding21.speedChart.getLegend().setEnabled(false);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding22 = this.p;
        if (activitySessionInsightsDetailsBinding22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding22 = null;
        }
        activitySessionInsightsDetailsBinding22.speedChart.animateY(2000);
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding23 = this.p;
        if (activitySessionInsightsDetailsBinding23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding23 = null;
        }
        activitySessionInsightsDetailsBinding23.speedChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.sensai.activity.ActivitySessionInsightsDetails$setArmSpeedGraphValues$1
            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onNothingSelected() {
            }

            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                Intrinsics.checkNotNullParameter(e, "e");
                Intrinsics.checkNotNullParameter(h, "h");
                if (((int) e.getY()) > 0) {
                    if (i == 1) {
                        ActivitySessionInsightsDetails activitySessionInsightsDetails = this;
                        Toast.makeText(activitySessionInsightsDetails, "Hand Speed: " + ((int) e.getY()), 0).show();
                        return;
                    }
                    ActivitySessionInsightsDetails activitySessionInsightsDetails2 = this;
                    Toast.makeText(activitySessionInsightsDetails2, "Arm Speed: " + ((int) e.getY()), 0).show();
                }
            }
        });
        ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding24 = this.p;
        if (activitySessionInsightsDetailsBinding24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySessionInsightsDetailsBinding24 = null;
        }
        activitySessionInsightsDetailsBinding24.speedChart.invalidate();
        if (arrayList.size() > 6) {
            ActivitySessionInsightsDetailsBinding activitySessionInsightsDetailsBinding25 = this.p;
            if (activitySessionInsightsDetailsBinding25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySessionInsightsDetailsBinding2 = activitySessionInsightsDetailsBinding25;
            }
            activitySessionInsightsDetailsBinding2.speedChart.moveViewToX(6.0f);
        }
    }

    public final void setClientRefId(@Nullable String str) {
        this.v = str;
    }

    public final void setHit(int i) {
        this.w = i;
    }

    public final void setMissed(int i) {
        this.x = i;
    }

    public final void setSensAIFeedbackAdapter(@NotNull SensAIFeedbackAdapter sensAIFeedbackAdapter) {
        Intrinsics.checkNotNullParameter(sensAIFeedbackAdapter, "<set-?>");
        this.SensAIFeedbackAdapter = sensAIFeedbackAdapter;
    }

    public final void x(int i) {
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel = this.q;
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel2 = null;
        if (sensAISummaryDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
            sensAISummaryDetailsViewModel = null;
        }
        sensAISummaryDetailsViewModel.getGetQuestionarieLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.sensai.activity.h0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySessionInsightsDetails.y(ActivitySessionInsightsDetails.this, (FeedbackQuestionnarieModel) obj);
            }
        });
        if (AppUtils.isNetConnected(this)) {
            SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel3 = this.q;
            if (sensAISummaryDetailsViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
            } else {
                sensAISummaryDetailsViewModel2 = sensAISummaryDetailsViewModel3;
            }
            sensAISummaryDetailsViewModel2.getFeedbackQuestionnaireList(i);
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
    }

    public final TimeSpentHeartRateZone z(List<Integer> list) {
        TimeSpentHeartRateZone timeSpentHeartRateZone = new TimeSpentHeartRateZone();
        HeartRateZoneRanges geHeartRateZoneRanges = geHeartRateZoneRanges(new PreferenceManager(this).getAge());
        for (Number number : list) {
            int intValue = number.intValue();
            boolean z = true;
            if (intValue <= geHeartRateZoneRanges.getZone2LowLimit() && geHeartRateZoneRanges.getZone1LowLimit() <= intValue) {
                timeSpentHeartRateZone.setZone1Time(timeSpentHeartRateZone.getZone1Time() + 5);
            } else if (intValue <= geHeartRateZoneRanges.getZone3LowLimit() && geHeartRateZoneRanges.getZone2LowLimit() <= intValue) {
                timeSpentHeartRateZone.setZone2Time(timeSpentHeartRateZone.getZone2Time() + 5);
            } else if (intValue <= geHeartRateZoneRanges.getZone4LowLimit() && geHeartRateZoneRanges.getZone3LowLimit() <= intValue) {
                timeSpentHeartRateZone.setZone3Time(timeSpentHeartRateZone.getZone3Time() + 5);
            } else if (intValue <= geHeartRateZoneRanges.getZone5LowLimit() && geHeartRateZoneRanges.getZone4LowLimit() <= intValue) {
                timeSpentHeartRateZone.setZone4Time(timeSpentHeartRateZone.getZone4Time() + 5);
            } else {
                if ((intValue > geHeartRateZoneRanges.getZone5HighLimit() || geHeartRateZoneRanges.getZone5LowLimit() > intValue) ? false : false) {
                    timeSpentHeartRateZone.setZone5Time(timeSpentHeartRateZone.getZone5Time() + 5);
                }
            }
        }
        return timeSpentHeartRateZone;
    }
}
