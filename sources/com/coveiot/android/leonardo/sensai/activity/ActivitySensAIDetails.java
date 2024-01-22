package com.coveiot.android.leonardo.sensai.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.activities.HeartRateZoneInfoActivity;
import com.coveiot.android.activitymodes.database.models.HeartRateZoneRanges;
import com.coveiot.android.activitymodes.database.models.TimeSpentHeartRateZone;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivitySensAiDetailsBinding;
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
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.ExtensionFuncsKt;
import com.coveiot.android.theme.compundview.CustomMarkerViewPieChart;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
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
import java.util.ArrayList;
import java.util.Arrays;
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
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySensAIDetails extends BaseActivity implements SensAIFeedbackAdapter.onItemClickListener {
    public SensAIFeedbackAdapter SensAIFeedbackAdapter;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivitySensAiDetailsBinding p;
    public SensAISummaryDetailsViewModel q;
    public SensAIActivitySummaryDetails r;
    public SensAIActivitySummary s;
    public int t;
    public boolean u;
    @Nullable
    public String v;
    public FeedbackQuestionnarieModel w;

    /* loaded from: classes5.dex */
    public final class BattingChartValueFormatter implements IValueFormatter {
        public BattingChartValueFormatter(ActivitySensAIDetails activitySensAIDetails) {
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

    public static final void A(ActivitySensAIDetails this$0, Ref.ObjectRef sessionID, SensAIActivitySummary sensAIActivitySummary) {
        String string;
        String str;
        SensAIActivitySummary sensAIActivitySummary2;
        String str2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sessionID, "$sessionID");
        if (sensAIActivitySummary != null) {
            this$0.s = sensAIActivitySummary;
            String clientRefID = sensAIActivitySummary.getClientRefID();
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
            if (sensAIActivitySummary4.getGoalCompletionPct() > 100) {
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding = this$0.p;
                if (activitySensAiDetailsBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding = null;
                }
                activitySensAiDetailsBinding.tvGoalAchieved.setTextColor(this$0.getColor(R.color.color_03c28a));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding2 = this$0.p;
                if (activitySensAiDetailsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding2 = null;
                }
                TextView textView = activitySensAiDetailsBinding2.tvGoalAchieved;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.tvGoalAchieved");
                this$0.drawableEnd(textView, R.drawable.ic_small_green_arrow_up_12_by_12);
            } else {
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding3 = this$0.p;
                if (activitySensAiDetailsBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding3 = null;
                }
                activitySensAiDetailsBinding3.tvGoalAchieved.setTextColor(this$0.getColor(R.color.main_text_color));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding4 = this$0.p;
                if (activitySensAiDetailsBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding4 = null;
                }
                TextView textView2 = activitySensAiDetailsBinding4.tvGoalAchieved;
                Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvGoalAchieved");
                ViewUtilsKt.emptyDrawable(textView2);
            }
            SensAIActivitySummary sensAIActivitySummary5 = this$0.s;
            if (sensAIActivitySummary5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary5 = null;
            }
            String str3 = "format(locale, format, *args)";
            if (sensAIActivitySummary5.getGoalCompletionPct() >= 100) {
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding5 = this$0.p;
                if (activitySensAiDetailsBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding5 = null;
                }
                activitySensAiDetailsBinding5.clGoalAcheived.setVisibility(0);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding6 = this$0.p;
                if (activitySensAiDetailsBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding6 = null;
                }
                activitySensAiDetailsBinding6.clGoalSummary.setVisibility(8);
            } else {
                SensAIActivitySummary sensAIActivitySummary6 = this$0.s;
                if (sensAIActivitySummary6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary6 = null;
                }
                if (sensAIActivitySummary6.getGoalCompletionPct() > 0) {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding7 = this$0.p;
                    if (activitySensAiDetailsBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding7 = null;
                    }
                    activitySensAiDetailsBinding7.clGoalSummary.setVisibility(0);
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding8 = this$0.p;
                    if (activitySensAiDetailsBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding8 = null;
                    }
                    TextView textView3 = activitySensAiDetailsBinding8.tvGoalCompletion;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Locale locale = Locale.ENGLISH;
                    String string2 = this$0.getString(R.string.sens_ai_goal_completion);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.sens_ai_goal_completion)");
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    SensAIActivitySummary sensAIActivitySummary7 = this$0.s;
                    if (sensAIActivitySummary7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary7 = null;
                    }
                    sb.append(sensAIActivitySummary7.getGoalCompletionPct());
                    sb.append(" % ");
                    objArr[0] = sb.toString();
                    String format = String.format(locale, string2, Arrays.copyOf(objArr, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    textView3.setText(format);
                } else {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding9 = this$0.p;
                    if (activitySensAiDetailsBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding9 = null;
                    }
                    activitySensAiDetailsBinding9.clGoalSummary.setVisibility(8);
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding10 = this$0.p;
                    if (activitySensAiDetailsBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding10 = null;
                    }
                    activitySensAiDetailsBinding10.clGoalAcheived.setVisibility(8);
                }
            }
            SensAIActivitySummary sensAIActivitySummary8 = this$0.s;
            if (sensAIActivitySummary8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary8 = null;
            }
            if (sensAIActivitySummary8.getGoalType() == 1) {
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding11 = this$0.p;
                if (activitySensAiDetailsBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding11 = null;
                }
                activitySensAiDetailsBinding11.ivGoalAcheived.setImageDrawable(this$0.getDrawable(R.drawable.goal_acheived_time));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding12 = this$0.p;
                if (activitySensAiDetailsBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding12 = null;
                }
                TextView textView4 = activitySensAiDetailsBinding12.tvGoalAchievedTarget;
                StringBuilder sb2 = new StringBuilder();
                sb2.append('/');
                SensAIActivitySummary sensAIActivitySummary9 = this$0.s;
                if (sensAIActivitySummary9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary9 = null;
                }
                sb2.append(sensAIActivitySummary9.getTargetGoalValue());
                sb2.append(' ');
                sb2.append(this$0.getString(R.string.mins));
                textView4.setText(sb2.toString());
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding13 = this$0.p;
                if (activitySensAiDetailsBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding13 = null;
                }
                TextView textView5 = activitySensAiDetailsBinding13.tvGoalAchieved;
                PayUtils payUtils = PayUtils.INSTANCE;
                SensAIActivitySummary sensAIActivitySummary10 = this$0.s;
                if (sensAIActivitySummary10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    str2 = "format(locale, format, *args)";
                    sensAIActivitySummary10 = null;
                } else {
                    str2 = "format(locale, format, *args)";
                }
                textView5.setText(payUtils.formatSeconds((int) sensAIActivitySummary10.getDurationSec()));
                SensAIActivitySummary sensAIActivitySummary11 = this$0.s;
                if (sensAIActivitySummary11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary11 = null;
                }
                if (sensAIActivitySummary11.getGoalCompletionPct() > 100) {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding14 = this$0.p;
                    if (activitySensAiDetailsBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding14 = null;
                    }
                    TextView textView6 = activitySensAiDetailsBinding14.tvGoalAchievedMsg;
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    Locale locale2 = Locale.ENGLISH;
                    String string3 = this$0.getString(R.string.kudos_goal_exceeded_message);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.kudos_goal_exceeded_message)");
                    Object[] objArr2 = new Object[2];
                    SensAIActivitySummary sensAIActivitySummary12 = this$0.s;
                    if (sensAIActivitySummary12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary12 = null;
                    }
                    objArr2[0] = String.valueOf(sensAIActivitySummary12.getTargetGoalValue());
                    objArr2[1] = this$0.getString(R.string.mins);
                    String format2 = String.format(locale2, string3, Arrays.copyOf(objArr2, 2));
                    str3 = str2;
                    Intrinsics.checkNotNullExpressionValue(format2, str3);
                    textView6.setText(format2);
                } else {
                    str3 = str2;
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding15 = this$0.p;
                    if (activitySensAiDetailsBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding15 = null;
                    }
                    TextView textView7 = activitySensAiDetailsBinding15.tvGoalAchievedMsg;
                    StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                    Locale locale3 = Locale.ENGLISH;
                    String string4 = this$0.getString(R.string.kudos_goal_achieved_message);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.kudos_goal_achieved_message)");
                    Object[] objArr3 = new Object[2];
                    SensAIActivitySummary sensAIActivitySummary13 = this$0.s;
                    if (sensAIActivitySummary13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary13 = null;
                    }
                    objArr3[0] = String.valueOf(sensAIActivitySummary13.getTargetGoalValue());
                    objArr3[1] = this$0.getString(R.string.mins);
                    String format3 = String.format(locale3, string4, Arrays.copyOf(objArr3, 2));
                    Intrinsics.checkNotNullExpressionValue(format3, str3);
                    textView7.setText(format3);
                }
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding16 = this$0.p;
                if (activitySensAiDetailsBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding16 = null;
                }
                TextView textView8 = activitySensAiDetailsBinding16.tvGoalCompletionValue;
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                Locale locale4 = Locale.ENGLISH;
                String string5 = this$0.getString(R.string.goal_achieved_value);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.goal_achieved_value)");
                Object[] objArr4 = new Object[3];
                SensAIActivitySummary sensAIActivitySummary14 = this$0.s;
                if (sensAIActivitySummary14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary14 = null;
                }
                objArr4[0] = String.valueOf(payUtils.formatSeconds((int) sensAIActivitySummary14.getDurationSec()));
                SensAIActivitySummary sensAIActivitySummary15 = this$0.s;
                if (sensAIActivitySummary15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary15 = null;
                }
                objArr4[1] = String.valueOf(sensAIActivitySummary15.getTargetGoalValue());
                objArr4[2] = this$0.getString(R.string.mins);
                String format4 = String.format(locale4, string5, Arrays.copyOf(objArr4, 3));
                Intrinsics.checkNotNullExpressionValue(format4, str3);
                textView8.setText(format4);
            } else {
                SensAIActivitySummary sensAIActivitySummary16 = this$0.s;
                if (sensAIActivitySummary16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary16 = null;
                }
                if (sensAIActivitySummary16.getGoalType() == 2) {
                    if (this$0.t == 1) {
                        ActivitySensAiDetailsBinding activitySensAiDetailsBinding17 = this$0.p;
                        if (activitySensAiDetailsBinding17 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySensAiDetailsBinding17 = null;
                        }
                        activitySensAiDetailsBinding17.ivGoalAcheived.setImageDrawable(this$0.getDrawable(R.drawable.goal_acheived_bat));
                        ActivitySensAiDetailsBinding activitySensAiDetailsBinding18 = this$0.p;
                        if (activitySensAiDetailsBinding18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySensAiDetailsBinding18 = null;
                        }
                        TextView textView9 = activitySensAiDetailsBinding18.tvGoalAchievedTarget;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append('/');
                        SensAIActivitySummary sensAIActivitySummary17 = this$0.s;
                        if (sensAIActivitySummary17 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary17 = null;
                        }
                        sb3.append(sensAIActivitySummary17.getTargetGoalValue());
                        textView9.setText(sb3.toString());
                        ActivitySensAiDetailsBinding activitySensAiDetailsBinding19 = this$0.p;
                        if (activitySensAiDetailsBinding19 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySensAiDetailsBinding19 = null;
                        }
                        TextView textView10 = activitySensAiDetailsBinding19.tvGoalAchieved;
                        SensAIActivitySummary sensAIActivitySummary18 = this$0.s;
                        if (sensAIActivitySummary18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary18 = null;
                        }
                        textView10.setText(String.valueOf(sensAIActivitySummary18.getTotalSwings()));
                        SensAIActivitySummary sensAIActivitySummary19 = this$0.s;
                        if (sensAIActivitySummary19 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary19 = null;
                        }
                        if (sensAIActivitySummary19.getGoalCompletionPct() > 100) {
                            ActivitySensAiDetailsBinding activitySensAiDetailsBinding20 = this$0.p;
                            if (activitySensAiDetailsBinding20 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activitySensAiDetailsBinding20 = null;
                            }
                            TextView textView11 = activitySensAiDetailsBinding20.tvGoalAchievedMsg;
                            StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                            Locale locale5 = Locale.ENGLISH;
                            String string6 = this$0.getString(R.string.kudos_goal_exceeded_message);
                            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.kudos_goal_exceeded_message)");
                            Object[] objArr5 = new Object[2];
                            SensAIActivitySummary sensAIActivitySummary20 = this$0.s;
                            if (sensAIActivitySummary20 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                                sensAIActivitySummary20 = null;
                            }
                            objArr5[0] = String.valueOf(sensAIActivitySummary20.getTargetGoalValue());
                            objArr5[1] = this$0.getString(R.string.shots);
                            String format5 = String.format(locale5, string6, Arrays.copyOf(objArr5, 2));
                            Intrinsics.checkNotNullExpressionValue(format5, "format(locale, format, *args)");
                            textView11.setText(format5);
                        } else {
                            ActivitySensAiDetailsBinding activitySensAiDetailsBinding21 = this$0.p;
                            if (activitySensAiDetailsBinding21 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activitySensAiDetailsBinding21 = null;
                            }
                            TextView textView12 = activitySensAiDetailsBinding21.tvGoalAchievedMsg;
                            StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                            Locale locale6 = Locale.ENGLISH;
                            String string7 = this$0.getString(R.string.kudos_goal_achieved_message);
                            Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.kudos_goal_achieved_message)");
                            Object[] objArr6 = new Object[2];
                            SensAIActivitySummary sensAIActivitySummary21 = this$0.s;
                            if (sensAIActivitySummary21 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                                sensAIActivitySummary21 = null;
                            }
                            objArr6[0] = String.valueOf(sensAIActivitySummary21.getTargetGoalValue());
                            objArr6[1] = this$0.getString(R.string.shots);
                            String format6 = String.format(locale6, string7, Arrays.copyOf(objArr6, 2));
                            Intrinsics.checkNotNullExpressionValue(format6, "format(locale, format, *args)");
                            textView12.setText(format6);
                        }
                        ActivitySensAiDetailsBinding activitySensAiDetailsBinding22 = this$0.p;
                        if (activitySensAiDetailsBinding22 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySensAiDetailsBinding22 = null;
                        }
                        TextView textView13 = activitySensAiDetailsBinding22.tvGoalCompletionValue;
                        StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                        Locale locale7 = Locale.ENGLISH;
                        String string8 = this$0.getString(R.string.goal_achieved_value);
                        Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.goal_achieved_value)");
                        Object[] objArr7 = new Object[3];
                        SensAIActivitySummary sensAIActivitySummary22 = this$0.s;
                        if (sensAIActivitySummary22 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary22 = null;
                        }
                        objArr7[0] = String.valueOf(sensAIActivitySummary22.getTotalSwings());
                        SensAIActivitySummary sensAIActivitySummary23 = this$0.s;
                        if (sensAIActivitySummary23 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary23 = null;
                        }
                        objArr7[1] = String.valueOf(sensAIActivitySummary23.getTargetGoalValue());
                        objArr7[2] = this$0.getString(R.string.shots);
                        String format7 = String.format(locale7, string8, Arrays.copyOf(objArr7, 3));
                        Intrinsics.checkNotNullExpressionValue(format7, "format(locale, format, *args)");
                        textView13.setText(format7);
                    } else {
                        ActivitySensAiDetailsBinding activitySensAiDetailsBinding23 = this$0.p;
                        if (activitySensAiDetailsBinding23 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySensAiDetailsBinding23 = null;
                        }
                        activitySensAiDetailsBinding23.ivGoalAcheived.setImageDrawable(this$0.getDrawable(2131231820));
                        ActivitySensAiDetailsBinding activitySensAiDetailsBinding24 = this$0.p;
                        if (activitySensAiDetailsBinding24 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySensAiDetailsBinding24 = null;
                        }
                        TextView textView14 = activitySensAiDetailsBinding24.tvGoalAchievedTarget;
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append('/');
                        SensAIActivitySummary sensAIActivitySummary24 = this$0.s;
                        if (sensAIActivitySummary24 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary24 = null;
                        }
                        sb4.append(sensAIActivitySummary24.getTargetGoalValue());
                        textView14.setText(sb4.toString());
                        ActivitySensAiDetailsBinding activitySensAiDetailsBinding25 = this$0.p;
                        if (activitySensAiDetailsBinding25 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySensAiDetailsBinding25 = null;
                        }
                        TextView textView15 = activitySensAiDetailsBinding25.tvGoalAchieved;
                        SensAIActivitySummary sensAIActivitySummary25 = this$0.s;
                        if (sensAIActivitySummary25 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary25 = null;
                        }
                        textView15.setText(String.valueOf(sensAIActivitySummary25.getTotalBallsBowled()));
                        SensAIActivitySummary sensAIActivitySummary26 = this$0.s;
                        if (sensAIActivitySummary26 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary26 = null;
                        }
                        if (sensAIActivitySummary26.getGoalCompletionPct() > 100) {
                            ActivitySensAiDetailsBinding activitySensAiDetailsBinding26 = this$0.p;
                            if (activitySensAiDetailsBinding26 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activitySensAiDetailsBinding26 = null;
                            }
                            TextView textView16 = activitySensAiDetailsBinding26.tvGoalAchievedMsg;
                            StringCompanionObject stringCompanionObject8 = StringCompanionObject.INSTANCE;
                            Locale locale8 = Locale.ENGLISH;
                            String string9 = this$0.getString(R.string.kudos_goal_exceeded_message);
                            Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.kudos_goal_exceeded_message)");
                            Object[] objArr8 = new Object[2];
                            SensAIActivitySummary sensAIActivitySummary27 = this$0.s;
                            if (sensAIActivitySummary27 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                                sensAIActivitySummary27 = null;
                            }
                            objArr8[0] = String.valueOf(sensAIActivitySummary27.getTargetGoalValue());
                            objArr8[1] = this$0.getString(R.string.balls);
                            String format8 = String.format(locale8, string9, Arrays.copyOf(objArr8, 2));
                            Intrinsics.checkNotNullExpressionValue(format8, "format(locale, format, *args)");
                            textView16.setText(format8);
                        } else {
                            ActivitySensAiDetailsBinding activitySensAiDetailsBinding27 = this$0.p;
                            if (activitySensAiDetailsBinding27 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activitySensAiDetailsBinding27 = null;
                            }
                            TextView textView17 = activitySensAiDetailsBinding27.tvGoalAchievedMsg;
                            StringCompanionObject stringCompanionObject9 = StringCompanionObject.INSTANCE;
                            Locale locale9 = Locale.ENGLISH;
                            String string10 = this$0.getString(R.string.kudos_goal_achieved_message);
                            Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.kudos_goal_achieved_message)");
                            Object[] objArr9 = new Object[2];
                            SensAIActivitySummary sensAIActivitySummary28 = this$0.s;
                            if (sensAIActivitySummary28 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                                sensAIActivitySummary28 = null;
                            }
                            objArr9[0] = String.valueOf(sensAIActivitySummary28.getTargetGoalValue());
                            objArr9[1] = this$0.getString(R.string.balls);
                            String format9 = String.format(locale9, string10, Arrays.copyOf(objArr9, 2));
                            Intrinsics.checkNotNullExpressionValue(format9, "format(locale, format, *args)");
                            textView17.setText(format9);
                        }
                        ActivitySensAiDetailsBinding activitySensAiDetailsBinding28 = this$0.p;
                        if (activitySensAiDetailsBinding28 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activitySensAiDetailsBinding28 = null;
                        }
                        TextView textView18 = activitySensAiDetailsBinding28.tvGoalCompletionValue;
                        StringCompanionObject stringCompanionObject10 = StringCompanionObject.INSTANCE;
                        Locale locale10 = Locale.ENGLISH;
                        String string11 = this$0.getString(R.string.goal_achieved_value);
                        Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.goal_achieved_value)");
                        Object[] objArr10 = new Object[3];
                        SensAIActivitySummary sensAIActivitySummary29 = this$0.s;
                        if (sensAIActivitySummary29 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary29 = null;
                        }
                        objArr10[0] = String.valueOf(sensAIActivitySummary29.getTotalBallsBowled());
                        SensAIActivitySummary sensAIActivitySummary30 = this$0.s;
                        if (sensAIActivitySummary30 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                            sensAIActivitySummary30 = null;
                        }
                        objArr10[1] = String.valueOf(sensAIActivitySummary30.getTargetGoalValue());
                        objArr10[2] = this$0.getString(R.string.balls);
                        String format10 = String.format(locale10, string11, Arrays.copyOf(objArr10, 3));
                        Intrinsics.checkNotNullExpressionValue(format10, "format(locale, format, *args)");
                        textView18.setText(format10);
                    }
                }
            }
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding29 = this$0.p;
            if (activitySensAiDetailsBinding29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding29 = null;
            }
            activitySensAiDetailsBinding29.sensAiDetails.clSessionDetails.setVisibility(0);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding30 = this$0.p;
            if (activitySensAiDetailsBinding30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding30 = null;
            }
            TextView textView19 = activitySensAiDetailsBinding30.sensAiDetails.tvSessionsDurationValue;
            StringBuilder sb5 = new StringBuilder();
            PayUtils payUtils2 = PayUtils.INSTANCE;
            SensAIActivitySummary sensAIActivitySummary31 = this$0.s;
            if (sensAIActivitySummary31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary31 = null;
            }
            sb5.append(payUtils2.formatSecondsInHHMMSS((int) sensAIActivitySummary31.getDurationSec()));
            sb5.append(' ');
            sb5.append(this$0.getString(R.string.hrs));
            textView19.setText(sb5.toString());
            if (this$0.t == 1) {
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding31 = this$0.p;
                if (activitySensAiDetailsBinding31 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding31 = null;
                }
                activitySensAiDetailsBinding31.ivSpeed.setImageDrawable(this$0.getDrawable(R.drawable.ic_batting_avg_armspeed));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding32 = this$0.p;
                if (activitySensAiDetailsBinding32 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding32 = null;
                }
                activitySensAiDetailsBinding32.ivMaxSpeed.setImageDrawable(this$0.getDrawable(R.drawable.ic_batting_max_armspeed));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding33 = this$0.p;
                if (activitySensAiDetailsBinding33 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding33 = null;
                }
                activitySensAiDetailsBinding33.tvSpeedtxt.setText(this$0.getString(R.string.avg_hand_speed));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding34 = this$0.p;
                if (activitySensAiDetailsBinding34 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding34 = null;
                }
                activitySensAiDetailsBinding34.tvMaxSpeedtxt.setText(this$0.getString(R.string.max_hand_speed));
                Boolean isDistanceUnitInMile = UserDataManager.getInstance(this$0).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this@Activit…ils).isDistanceUnitInMile");
                if (isDistanceUnitInMile.booleanValue()) {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding35 = this$0.p;
                    if (activitySensAiDetailsBinding35 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding35 = null;
                    }
                    TextView textView20 = activitySensAiDetailsBinding35.tvSpeed;
                    StringBuilder sb6 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary32 = this$0.s;
                    if (sensAIActivitySummary32 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary32 = null;
                    }
                    sb6.append(payUtils2.getSpeedValue(this$0, sensAIActivitySummary32.getAvgHandSpeed()));
                    sb6.append(' ');
                    sb6.append(this$0.getResources().getString(R.string.mil_per_hours));
                    sb6.append(' ');
                    textView20.setText(sb6.toString());
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding36 = this$0.p;
                    if (activitySensAiDetailsBinding36 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding36 = null;
                    }
                    TextView textView21 = activitySensAiDetailsBinding36.tvMaxSpeed;
                    StringBuilder sb7 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary33 = this$0.s;
                    if (sensAIActivitySummary33 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary33 = null;
                    }
                    sb7.append(payUtils2.getSpeedValue(this$0, sensAIActivitySummary33.getMaxHandSpeed()));
                    sb7.append(' ');
                    sb7.append(this$0.getResources().getString(R.string.mil_per_hours));
                    sb7.append(' ');
                    textView21.setText(sb7.toString());
                } else {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding37 = this$0.p;
                    if (activitySensAiDetailsBinding37 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding37 = null;
                    }
                    TextView textView22 = activitySensAiDetailsBinding37.tvSpeed;
                    StringBuilder sb8 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary34 = this$0.s;
                    if (sensAIActivitySummary34 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary34 = null;
                    }
                    sb8.append(sensAIActivitySummary34.getAvgHandSpeed());
                    sb8.append(' ');
                    sb8.append(this$0.getResources().getString(R.string.km_per_hours));
                    sb8.append(' ');
                    textView22.setText(sb8.toString());
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding38 = this$0.p;
                    if (activitySensAiDetailsBinding38 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding38 = null;
                    }
                    TextView textView23 = activitySensAiDetailsBinding38.tvMaxSpeed;
                    StringBuilder sb9 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary35 = this$0.s;
                    if (sensAIActivitySummary35 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary35 = null;
                    }
                    sb9.append(sensAIActivitySummary35.getMaxHandSpeed());
                    sb9.append(' ');
                    sb9.append(this$0.getResources().getString(R.string.km_per_hours));
                    sb9.append(' ');
                    textView23.setText(sb9.toString());
                }
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding39 = this$0.p;
                if (activitySensAiDetailsBinding39 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding39 = null;
                }
                TextView textView24 = activitySensAiDetailsBinding39.tvTotalBalls;
                StringCompanionObject stringCompanionObject11 = StringCompanionObject.INSTANCE;
                Locale locale11 = Locale.ENGLISH;
                String string12 = this$0.getString(R.string.total_balls_played);
                Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.total_balls_played)");
                Object[] objArr11 = new Object[1];
                SensAIActivitySummary sensAIActivitySummary36 = this$0.s;
                if (sensAIActivitySummary36 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary36 = null;
                }
                objArr11[0] = String.valueOf(sensAIActivitySummary36.getTotalSwings());
                String format11 = String.format(locale11, string12, Arrays.copyOf(objArr11, 1));
                Intrinsics.checkNotNullExpressionValue(format11, str3);
                textView24.setText(format11);
                SensAIActivitySummary sensAIActivitySummary37 = this$0.s;
                if (sensAIActivitySummary37 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary37 = null;
                }
                if (sensAIActivitySummary37.getHand() == 0) {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding40 = this$0.p;
                    if (activitySensAiDetailsBinding40 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding40 = null;
                    }
                    activitySensAiDetailsBinding40.sensAiDetails.ivCenterVitalBg.setImageDrawable(this$0.getDrawable(R.drawable.sens_ai_right_hand_batting));
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding41 = this$0.p;
                    if (activitySensAiDetailsBinding41 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding41 = null;
                    }
                    activitySensAiDetailsBinding41.sensAiDetails.tvTitle.setText(this$0.getString(R.string.right_hand_batting));
                } else {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding42 = this$0.p;
                    if (activitySensAiDetailsBinding42 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding42 = null;
                    }
                    activitySensAiDetailsBinding42.sensAiDetails.ivCenterVitalBg.setImageDrawable(this$0.getDrawable(R.drawable.sens_ai_left_hand_batting));
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding43 = this$0.p;
                    if (activitySensAiDetailsBinding43 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding43 = null;
                    }
                    activitySensAiDetailsBinding43.sensAiDetails.tvTitle.setText(this$0.getString(R.string.left_hand_batting));
                }
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding44 = this$0.p;
                if (activitySensAiDetailsBinding44 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding44 = null;
                }
                activitySensAiDetailsBinding44.speedTitle.setText(this$0.getString(R.string.hand_speed));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding45 = this$0.p;
                if (activitySensAiDetailsBinding45 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding45 = null;
                }
                activitySensAiDetailsBinding45.clBallsDetails.setVisibility(8);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding46 = this$0.p;
                if (activitySensAiDetailsBinding46 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding46 = null;
                }
                activitySensAiDetailsBinding46.clHitAnalysisDetails.setVisibility(0);
            } else {
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding47 = this$0.p;
                if (activitySensAiDetailsBinding47 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding47 = null;
                }
                activitySensAiDetailsBinding47.ivSpeed.setImageDrawable(this$0.getDrawable(R.drawable.ic_bowlling_avg_armspeed));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding48 = this$0.p;
                if (activitySensAiDetailsBinding48 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding48 = null;
                }
                activitySensAiDetailsBinding48.ivMaxSpeed.setImageDrawable(this$0.getDrawable(R.drawable.ic_bowlling_max_armspeed));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding49 = this$0.p;
                if (activitySensAiDetailsBinding49 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding49 = null;
                }
                activitySensAiDetailsBinding49.tvSpeedtxt.setText(this$0.getString(R.string.avg_arm_speed));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding50 = this$0.p;
                if (activitySensAiDetailsBinding50 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding50 = null;
                }
                activitySensAiDetailsBinding50.tvMaxSpeedtxt.setText(this$0.getString(R.string.max_arm_speed));
                Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(this$0).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(this@Activit…ils).isDistanceUnitInMile");
                if (isDistanceUnitInMile2.booleanValue()) {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding51 = this$0.p;
                    if (activitySensAiDetailsBinding51 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding51 = null;
                    }
                    TextView textView25 = activitySensAiDetailsBinding51.tvSpeed;
                    StringBuilder sb10 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary38 = this$0.s;
                    if (sensAIActivitySummary38 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary38 = null;
                    }
                    sb10.append(payUtils2.getSpeedValue(this$0, sensAIActivitySummary38.getAvgArmSpeed()));
                    sb10.append(' ');
                    sb10.append(this$0.getResources().getString(R.string.mil_per_hours));
                    sb10.append(' ');
                    textView25.setText(sb10.toString());
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding52 = this$0.p;
                    if (activitySensAiDetailsBinding52 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding52 = null;
                    }
                    TextView textView26 = activitySensAiDetailsBinding52.tvMaxSpeed;
                    StringBuilder sb11 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary39 = this$0.s;
                    if (sensAIActivitySummary39 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary39 = null;
                    }
                    sb11.append(payUtils2.getSpeedValue(this$0, sensAIActivitySummary39.getMaxArmSpeed()));
                    sb11.append(' ');
                    sb11.append(this$0.getResources().getString(R.string.mil_per_hours));
                    sb11.append(' ');
                    textView26.setText(sb11.toString());
                } else {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding53 = this$0.p;
                    if (activitySensAiDetailsBinding53 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding53 = null;
                    }
                    TextView textView27 = activitySensAiDetailsBinding53.tvSpeed;
                    StringBuilder sb12 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary40 = this$0.s;
                    if (sensAIActivitySummary40 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary40 = null;
                    }
                    sb12.append(sensAIActivitySummary40.getAvgArmSpeed());
                    sb12.append(' ');
                    sb12.append(this$0.getResources().getString(R.string.km_per_hours));
                    sb12.append(' ');
                    textView27.setText(sb12.toString());
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding54 = this$0.p;
                    if (activitySensAiDetailsBinding54 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding54 = null;
                    }
                    TextView textView28 = activitySensAiDetailsBinding54.tvMaxSpeed;
                    StringBuilder sb13 = new StringBuilder();
                    SensAIActivitySummary sensAIActivitySummary41 = this$0.s;
                    if (sensAIActivitySummary41 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary41 = null;
                    }
                    sb13.append(sensAIActivitySummary41.getMaxArmSpeed());
                    sb13.append(' ');
                    sb13.append(this$0.getResources().getString(R.string.km_per_hours));
                    sb13.append(' ');
                    textView28.setText(sb13.toString());
                }
                SensAIActivitySummary sensAIActivitySummary42 = this$0.s;
                if (sensAIActivitySummary42 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary42 = null;
                }
                if (sensAIActivitySummary42.getHand() == 0) {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding55 = this$0.p;
                    if (activitySensAiDetailsBinding55 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding55 = null;
                    }
                    activitySensAiDetailsBinding55.sensAiDetails.ivCenterVitalBg.setImageDrawable(this$0.getDrawable(R.drawable.sens_ai_right_hand_bowling));
                    string = this$0.getString(R.string.right_hand);
                    Intrinsics.checkNotNullExpressionValue(string, "{\n                      …                        }");
                } else {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding56 = this$0.p;
                    if (activitySensAiDetailsBinding56 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding56 = null;
                    }
                    activitySensAiDetailsBinding56.sensAiDetails.ivCenterVitalBg.setImageDrawable(this$0.getDrawable(R.drawable.sens_ai_left_hand_bowling));
                    string = this$0.getString(R.string.left_hand);
                    Intrinsics.checkNotNullExpressionValue(string, "{\n                      …                        }");
                }
                SensAIActivitySummary sensAIActivitySummary43 = this$0.s;
                if (sensAIActivitySummary43 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary43 = null;
                }
                int bowlingType = sensAIActivitySummary43.getBowlingType();
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
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding57 = this$0.p;
                if (activitySensAiDetailsBinding57 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding57 = null;
                }
                activitySensAiDetailsBinding57.sensAiDetails.tvTitle.setText(str);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding58 = this$0.p;
                if (activitySensAiDetailsBinding58 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding58 = null;
                }
                activitySensAiDetailsBinding58.clBallsDetails.setVisibility(0);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding59 = this$0.p;
                if (activitySensAiDetailsBinding59 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding59 = null;
                }
                activitySensAiDetailsBinding59.clHitAnalysis.setVisibility(8);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding60 = this$0.p;
                if (activitySensAiDetailsBinding60 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding60 = null;
                }
                TextView textView29 = activitySensAiDetailsBinding60.tvTotalBallsBowledValue;
                SensAIActivitySummary sensAIActivitySummary44 = this$0.s;
                if (sensAIActivitySummary44 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary44 = null;
                }
                textView29.setText(String.valueOf(sensAIActivitySummary44.getTotalBallsBowled()));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding61 = this$0.p;
                if (activitySensAiDetailsBinding61 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding61 = null;
                }
                TextView textView30 = activitySensAiDetailsBinding61.tvEquivalentOversValue;
                SensAIActivitySummary sensAIActivitySummary45 = this$0.s;
                if (sensAIActivitySummary45 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary45 = null;
                }
                textView30.setText(String.valueOf(payUtils2.getOversFromBalls(sensAIActivitySummary45.getTotalBallsBowled())));
                SensAIActivitySummary sensAIActivitySummary46 = this$0.s;
                if (sensAIActivitySummary46 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary46 = null;
                }
                if (sensAIActivitySummary46.getTotalBallsBowled() < 6) {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding62 = this$0.p;
                    if (activitySensAiDetailsBinding62 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding62 = null;
                    }
                    activitySensAiDetailsBinding62.tvAvgTimePerOverValue.setText(" - ");
                } else {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding63 = this$0.p;
                    if (activitySensAiDetailsBinding63 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activitySensAiDetailsBinding63 = null;
                    }
                    TextView textView31 = activitySensAiDetailsBinding63.tvAvgTimePerOverValue;
                    SensAIActivitySummary sensAIActivitySummary47 = this$0.s;
                    if (sensAIActivitySummary47 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary47 = null;
                    }
                    int durationSec = (int) sensAIActivitySummary47.getDurationSec();
                    SensAIActivitySummary sensAIActivitySummary48 = this$0.s;
                    if (sensAIActivitySummary48 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                        sensAIActivitySummary48 = null;
                    }
                    textView31.setText(payUtils2.formatSeconds((int) payUtils2.getAvgTimeForOvers(durationSec, sensAIActivitySummary48.getTotalBallsBowled())));
                }
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding64 = this$0.p;
                if (activitySensAiDetailsBinding64 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding64 = null;
                }
                activitySensAiDetailsBinding64.speedTitle.setText(this$0.getString(R.string.arm_speed));
            }
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding65 = this$0.p;
            if (activitySensAiDetailsBinding65 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding65 = null;
            }
            TextView textView32 = activitySensAiDetailsBinding65.tvCalories;
            SensAIActivitySummary sensAIActivitySummary49 = this$0.s;
            if (sensAIActivitySummary49 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary49 = null;
            }
            textView32.setText(String.valueOf(kotlin.math.c.roundToInt(sensAIActivitySummary49.getTotalCalories())));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding66 = this$0.p;
            if (activitySensAiDetailsBinding66 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding66 = null;
            }
            TextView textView33 = activitySensAiDetailsBinding66.tvHeartRate;
            SensAIActivitySummary sensAIActivitySummary50 = this$0.s;
            if (sensAIActivitySummary50 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary50 = null;
            }
            textView33.setText(String.valueOf(sensAIActivitySummary50.getAvgHR()));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding67 = this$0.p;
            if (activitySensAiDetailsBinding67 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding67 = null;
            }
            TextView textView34 = activitySensAiDetailsBinding67.tvSteps;
            SensAIActivitySummary sensAIActivitySummary51 = this$0.s;
            if (sensAIActivitySummary51 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary51 = null;
            }
            textView34.setText(String.valueOf(sensAIActivitySummary51.getTotalSteps()));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding68 = this$0.p;
            if (activitySensAiDetailsBinding68 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding68 = null;
            }
            ProgressBar progressBar = activitySensAiDetailsBinding68.goalProgressBar;
            SensAIActivitySummary sensAIActivitySummary52 = this$0.s;
            if (sensAIActivitySummary52 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary2 = null;
            } else {
                sensAIActivitySummary2 = sensAIActivitySummary52;
            }
            progressBar.setProgress(sensAIActivitySummary2.getGoalCompletionPct());
        }
    }

    public static final void B(ActivitySensAIDetails this$0, SensAIActivitySummaryDetails sensAIActivitySummaryDetails) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel = null;
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding = null;
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding2 = null;
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding3 = null;
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
                SensAIActivitySummary sensAIActivitySummary = this$0.s;
                if (sensAIActivitySummary == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary = null;
                }
                int totalSwings = sensAIActivitySummary.getTotalSwings();
                SensAIActivitySummary sensAIActivitySummary2 = this$0.s;
                if (sensAIActivitySummary2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary2 = null;
                }
                int played = totalSwings - sensAIActivitySummary2.getPlayed();
                SensAIActivitySummary sensAIActivitySummary3 = this$0.s;
                if (sensAIActivitySummary3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary3 = null;
                }
                int played2 = sensAIActivitySummary3.getPlayed();
                SensAIActivitySummary sensAIActivitySummary4 = this$0.s;
                if (sensAIActivitySummary4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                    sensAIActivitySummary4 = null;
                }
                this$0.I(played2, played, sensAIActivitySummary4.getTotalSwings());
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
            AppUtils.getSimpleDateFormat("dd MMM YYYY, hh:mm aa");
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding4 = this$0.p;
            if (activitySensAiDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding4 = null;
            }
            TextView textView = activitySensAiDetailsBinding4.sensAiDetails.tvSessionTimeValue;
            SensAIActivitySummaryDetails sensAIActivitySummaryDetails3 = this$0.r;
            if (sensAIActivitySummaryDetails3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryDetails");
                sensAIActivitySummaryDetails3 = null;
            }
            Long unixTimeStamp = sensAIActivitySummaryDetails3.getUnixTimeStamp();
            Long valueOf = unixTimeStamp != null ? Long.valueOf(unixTimeStamp.longValue()) : null;
            Intrinsics.checkNotNull(valueOf);
            textView.setText(String.valueOf(PayUtils.getTodayYesterdayStringWithTimeStampAtddMMMYYYY(valueOf.longValue(), this$0)));
            SensAIActivitySummaryDetails sensAIActivitySummaryDetails4 = this$0.r;
            if (sensAIActivitySummaryDetails4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryDetails");
                sensAIActivitySummaryDetails4 = null;
            }
            ArrayList<Integer> hr = sensAIActivitySummaryDetails4.getHr();
            Intrinsics.checkNotNullExpressionValue(hr, "activitySummaryDetails.hr");
            this$0.H(hr);
            SensAIActivitySummaryDetails sensAIActivitySummaryDetails5 = this$0.r;
            if (sensAIActivitySummaryDetails5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryDetails");
                sensAIActivitySummaryDetails5 = null;
            }
            ArrayList<Integer> hr2 = sensAIActivitySummaryDetails5.getHr();
            Intrinsics.checkNotNullExpressionValue(hr2, "activitySummaryDetails.hr");
            SensAIActivitySummary sensAIActivitySummary5 = this$0.s;
            if (sensAIActivitySummary5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
                sensAIActivitySummary5 = null;
            }
            this$0.G(hr2, (int) sensAIActivitySummary5.getDurationSec());
            if (AppUtils.isNetConnected(this$0)) {
                SensAIActivitySummaryDetails sensAIActivitySummaryDetails6 = this$0.r;
                if (sensAIActivitySummaryDetails6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activitySummaryDetails");
                    sensAIActivitySummaryDetails6 = null;
                }
                if (sensAIActivitySummaryDetails6.isFeedbackSaved()) {
                    ActivitySensAiDetailsBinding activitySensAiDetailsBinding5 = this$0.p;
                    if (activitySensAiDetailsBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activitySensAiDetailsBinding = activitySensAiDetailsBinding5;
                    }
                    activitySensAiDetailsBinding.clRateSesssion.setVisibility(8);
                    return;
                }
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding6 = this$0.p;
                if (activitySensAiDetailsBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activitySensAiDetailsBinding2 = activitySensAiDetailsBinding6;
                }
                activitySensAiDetailsBinding2.clRateSesssion.setVisibility(0);
                return;
            }
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding7 = this$0.p;
            if (activitySensAiDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySensAiDetailsBinding3 = activitySensAiDetailsBinding7;
            }
            activitySensAiDetailsBinding3.clRateSesssion.setVisibility(8);
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

    public static final void C(ActivitySensAIDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SensAIActivitySummary sensAIActivitySummary = this$0.s;
        if (sensAIActivitySummary == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activitySummaryData");
            sensAIActivitySummary = null;
        }
        this$0.J(sensAIActivitySummary);
    }

    public static final void D(ActivitySensAIDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, HeartRateZoneInfoActivity.class));
    }

    public static final void E(ActivitySensAIDetails this$0, Ref.ObjectRef sessionID, Boolean b) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sessionID, "$sessionID");
        Intrinsics.checkNotNullExpressionValue(b, "b");
        if (b.booleanValue()) {
            Toast.makeText(this$0, this$0.getString(R.string.thankyou_for_your_feedback), 0).show();
            SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel = this$0.q;
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding = null;
            if (sensAISummaryDetailsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
                sensAISummaryDetailsViewModel = null;
            }
            sensAISummaryDetailsViewModel.updateFeedbackToServer(true, (String) sessionID.element, BleApiManager.getInstance(this$0).getBleApi().getMacAddress());
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding2 = this$0.p;
            if (activitySensAiDetailsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySensAiDetailsBinding = activitySensAiDetailsBinding2;
            }
            activitySensAiDetailsBinding.clRateSesssion.setVisibility(8);
        } else {
            Toast.makeText(this$0, this$0.getString(R.string.failed_to_save), 0).show();
        }
        this$0.dismissProgress();
    }

    public static final void F(ActivitySensAIDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(ActivitySensAIDetails this$0, FeedbackQuestionnarieModel questionnarie) {
        QuestionModel questionModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(questionnarie, "questionnarie");
        this$0.w = questionnarie;
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding = null;
        if (questionnarie == null) {
            Intrinsics.throwUninitializedPropertyAccessException("feedbackQuestionnarieModel");
            questionnarie = null;
        }
        if (questionnarie.getQuestions() != null) {
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding2 = this$0.p;
            if (activitySensAiDetailsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding2 = null;
            }
            TextView textView = activitySensAiDetailsBinding2.rateSession;
            FeedbackQuestionnarieModel feedbackQuestionnarieModel = this$0.w;
            if (feedbackQuestionnarieModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("feedbackQuestionnarieModel");
                feedbackQuestionnarieModel = null;
            }
            List<QuestionModel> questions = feedbackQuestionnarieModel.getQuestions();
            Intrinsics.checkNotNull(questions);
            textView.setText(questions.get(0).getText());
            FeedbackQuestionnarieModel feedbackQuestionnarieModel2 = this$0.w;
            if (feedbackQuestionnarieModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("feedbackQuestionnarieModel");
                feedbackQuestionnarieModel2 = null;
            }
            List<QuestionModel> questions2 = feedbackQuestionnarieModel2.getQuestions();
            List<OptionModel> options = (questions2 == null || (questionModel = questions2.get(0)) == null) ? null : questionModel.getOptions();
            Intrinsics.checkNotNull(options);
            this$0.setSensAIFeedbackAdapter(new SensAIFeedbackAdapter(this$0, options, this$0));
            this$0.getSensAIFeedbackAdapter().setOnFeedBackClickListener(this$0);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding3 = this$0.p;
            if (activitySensAiDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySensAiDetailsBinding = activitySensAiDetailsBinding3;
            }
            activitySensAiDetailsBinding.rvFeedback.setAdapter(this$0.getSensAIFeedbackAdapter());
        }
    }

    public final void G(ArrayList<Integer> arrayList, int i) {
        TimeSpentHeartRateZone z = z(arrayList);
        int zone1Time = z.getZone1Time() + z.getZone2Time() + z.getZone3Time() + z.getZone4Time() + z.getZone5Time();
        if (zone1Time > 0) {
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding = this.p;
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding2 = null;
            if (activitySensAiDetailsBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding = null;
            }
            TextView textView = activitySensAiDetailsBinding.tvProgressWarm;
            StringBuilder sb = new StringBuilder();
            float f = zone1Time;
            float f2 = 100;
            sb.append(kotlin.math.c.roundToInt((z.getZone1Time() / f) * f2));
            sb.append(" %");
            textView.setText(sb.toString());
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding3 = this.p;
            if (activitySensAiDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding3 = null;
            }
            activitySensAiDetailsBinding3.tvProgressFatBurn.setText(kotlin.math.c.roundToInt((z.getZone2Time() / f) * f2) + " %");
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding4 = this.p;
            if (activitySensAiDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding4 = null;
            }
            activitySensAiDetailsBinding4.tvProgressCardio.setText(kotlin.math.c.roundToInt((z.getZone3Time() / f) * f2) + " %");
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding5 = this.p;
            if (activitySensAiDetailsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding5 = null;
            }
            activitySensAiDetailsBinding5.tvProgressThreshold.setText(kotlin.math.c.roundToInt((z.getZone4Time() / f) * f2) + " %");
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding6 = this.p;
            if (activitySensAiDetailsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding6 = null;
            }
            activitySensAiDetailsBinding6.tvProgressPeak.setText(kotlin.math.c.roundToInt((z.getZone5Time() / f) * f2) + " %");
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding7 = this.p;
            if (activitySensAiDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding7 = null;
            }
            activitySensAiDetailsBinding7.pbWarm.setProgress(kotlin.math.c.roundToInt((z.getZone1Time() / f) * f2));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding8 = this.p;
            if (activitySensAiDetailsBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding8 = null;
            }
            activitySensAiDetailsBinding8.pbFatBurn.setProgress(kotlin.math.c.roundToInt((z.getZone2Time() / f) * f2));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding9 = this.p;
            if (activitySensAiDetailsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding9 = null;
            }
            activitySensAiDetailsBinding9.pbCardio.setProgress(kotlin.math.c.roundToInt((z.getZone3Time() / f) * f2));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding10 = this.p;
            if (activitySensAiDetailsBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding10 = null;
            }
            activitySensAiDetailsBinding10.pbThreshold.setProgress(kotlin.math.c.roundToInt((z.getZone4Time() / f) * f2));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding11 = this.p;
            if (activitySensAiDetailsBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySensAiDetailsBinding2 = activitySensAiDetailsBinding11;
            }
            activitySensAiDetailsBinding2.pbPeak.setProgress(kotlin.math.c.roundToInt((z.getZone5Time() / f) * f2));
        }
    }

    public final void H(ArrayList<Integer> arrayList) {
        ArrayList<Entry> arrayList2 = new ArrayList();
        Iterator<Integer> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            arrayList2.add(new Entry(i, it.next().intValue()));
        }
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding = this.p;
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding2 = null;
        if (activitySensAiDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding = null;
        }
        if (activitySensAiDetailsBinding.heartrateChart != null) {
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding3 = this.p;
            if (activitySensAiDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding3 = null;
            }
            activitySensAiDetailsBinding3.heartrateChart.clear();
            ArrayList arrayList3 = new ArrayList();
            for (Entry entry : arrayList2) {
                if (entry.getY() == -1.0f) {
                    arrayList3.add(entry);
                }
            }
            arrayList2.removeAll(arrayList3);
            LineDataSet lineDataSet = new LineDataSet(arrayList2, "");
            lineDataSet.setCircleColor(getResources().getColor(R.color.mild_stress_color));
            lineDataSet.setCircleSize(0.5f);
            lineDataSet.setDrawValues(false);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
            lineDataSet.setColor(getResources().getColor(R.color.mild_stress_color));
            lineDataSet.setHighLightColor(getResources().getColor(17170445));
            lineDataSet.setFillColor(getResources().getColor(R.color.mild_stress_color));
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
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding4 = this.p;
            if (activitySensAiDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding4 = null;
            }
            YAxis axisLeft = activitySensAiDetailsBinding4.heartrateChart.getAxisLeft();
            Intrinsics.checkNotNull(axisLeft);
            axisLeft.setDrawAxisLine(true);
            axisLeft.setDrawGridLines(false);
            axisLeft.setAxisMinimum(0.0f);
            axisLeft.setEnabled(true);
            axisLeft.setAxisMaximum(240.0f);
            axisLeft.setAxisLineColor(getResources().getColor(R.color.color_757575));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding5 = this.p;
            if (activitySensAiDetailsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding5 = null;
            }
            YAxis axisRight = activitySensAiDetailsBinding5.heartrateChart.getAxisRight();
            Intrinsics.checkNotNull(axisRight);
            axisRight.setEnabled(false);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding6 = this.p;
            if (activitySensAiDetailsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding6 = null;
            }
            XAxis xAxis = activitySensAiDetailsBinding6.heartrateChart.getXAxis();
            Intrinsics.checkNotNull(xAxis);
            xAxis.setEnabled(true);
            xAxis.setGranularity(1.0f);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawLabels(true);
            xAxis.setAxisLineColor(getResources().getColor(R.color.color_757575));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding7 = this.p;
            if (activitySensAiDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding7 = null;
            }
            Legend legend = activitySensAiDetailsBinding7.heartrateChart.getLegend();
            Intrinsics.checkNotNull(legend);
            legend.setEnabled(false);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding8 = this.p;
            if (activitySensAiDetailsBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding8 = null;
            }
            LineChart lineChart = activitySensAiDetailsBinding8.heartrateChart;
            if (lineChart != null) {
                lineChart.setData(lineData);
            }
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding9 = this.p;
            if (activitySensAiDetailsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding9 = null;
            }
            activitySensAiDetailsBinding9.heartrateChart.setDrawGridBackground(false);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding10 = this.p;
            if (activitySensAiDetailsBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding10 = null;
            }
            Description description = activitySensAiDetailsBinding10.heartrateChart.getDescription();
            if (description != null) {
                description.setEnabled(false);
            }
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding11 = this.p;
            if (activitySensAiDetailsBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding11 = null;
            }
            activitySensAiDetailsBinding11.heartrateChart.setDrawBorders(false);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding12 = this.p;
            if (activitySensAiDetailsBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding12 = null;
            }
            LineChart lineChart2 = activitySensAiDetailsBinding12.heartrateChart;
            if (lineChart2 != null) {
                lineChart2.setAutoScaleMinMaxEnabled(true);
            }
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding13 = this.p;
            if (activitySensAiDetailsBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding13 = null;
            }
            activitySensAiDetailsBinding13.heartrateChart.setPinchZoom(false);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding14 = this.p;
            if (activitySensAiDetailsBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding14 = null;
            }
            activitySensAiDetailsBinding14.heartrateChart.setScaleEnabled(false);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding15 = this.p;
            if (activitySensAiDetailsBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding15 = null;
            }
            activitySensAiDetailsBinding15.heartrateChart.setVisibleXRangeMinimum(5.0f);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding16 = this.p;
            if (activitySensAiDetailsBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding16 = null;
            }
            YAxis axisLeft2 = activitySensAiDetailsBinding16.heartrateChart.getAxisLeft();
            Intrinsics.checkNotNull(axisLeft2);
            axisLeft2.setStartAtZero(true);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding17 = this.p;
            if (activitySensAiDetailsBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding17 = null;
            }
            YAxis axisRight2 = activitySensAiDetailsBinding17.heartrateChart.getAxisRight();
            Intrinsics.checkNotNull(axisRight2);
            axisRight2.setStartAtZero(true);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding18 = this.p;
            if (activitySensAiDetailsBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding18 = null;
            }
            YAxis axisLeft3 = activitySensAiDetailsBinding18.heartrateChart.getAxisLeft();
            Intrinsics.checkNotNull(axisLeft3);
            axisLeft3.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding19 = this.p;
            if (activitySensAiDetailsBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding19 = null;
            }
            XAxis xAxis2 = activitySensAiDetailsBinding19.heartrateChart.getXAxis();
            Intrinsics.checkNotNull(xAxis2);
            xAxis2.setTextColor(getResources().getColor(R.color.secondary_text_color));
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding20 = this.p;
            if (activitySensAiDetailsBinding20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding20 = null;
            }
            activitySensAiDetailsBinding20.heartrateChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.sensai.activity.ActivitySensAIDetails$setLineGraphValues$1
                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onNothingSelected() {
                }

                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    Intrinsics.checkNotNullParameter(h, "h");
                    if (((int) e.getY()) > 0) {
                        ActivitySensAIDetails activitySensAIDetails = ActivitySensAIDetails.this;
                        Toast.makeText(activitySensAIDetails, "Heart Rate: " + ((int) e.getY()), 0).show();
                    }
                }
            });
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding21 = this.p;
            if (activitySensAiDetailsBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySensAiDetailsBinding2 = activitySensAiDetailsBinding21;
            }
            activitySensAiDetailsBinding2.heartrateChart.invalidate();
        }
    }

    public final void I(int i, int i2, int i3) {
        if (this.p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding = this.p;
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding2 = null;
        if (activitySensAiDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding = null;
        }
        if (activitySensAiDetailsBinding.pieChart != null) {
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding3 = this.p;
            if (activitySensAiDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding3 = null;
            }
            activitySensAiDetailsBinding3.pieChart.clear();
            int roundToInt = kotlin.math.c.roundToInt((i / i3) * 100);
            int i4 = 100 - roundToInt;
            PieEntry pieEntry = new PieEntry(roundToInt, getString(R.string.hit), String.valueOf(i));
            PieEntry pieEntry2 = new PieEntry(i4, getString(R.string.missed), String.valueOf(i2));
            ArrayList arrayList = new ArrayList();
            arrayList.add(pieEntry);
            arrayList.add(pieEntry2);
            PieDataSet pieDataSet = new PieDataSet(arrayList, "");
            if (pieDataSet.getEntryCount() > 0) {
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding4 = this.p;
                if (activitySensAiDetailsBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding4 = null;
                }
                PieChart pieChart = activitySensAiDetailsBinding4.pieChart;
                Intrinsics.checkNotNullExpressionValue(pieChart, "binding.pieChart");
                visible(pieChart);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding5 = this.p;
                if (activitySensAiDetailsBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding5 = null;
                }
                RecyclerView recyclerView = activitySensAiDetailsBinding5.gridView;
                Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.gridView");
                visible(recyclerView);
                pieDataSet.setDrawIcons(false);
                pieDataSet.setSliceSpace(3.0f);
                pieDataSet.setIconsOffset(new MPPointF(0.0f, 40.0f));
                pieDataSet.setSelectionShift(10.0f);
                ArrayList arrayList2 = new ArrayList();
                Resources resources = getResources();
                Integer valueOf = resources != null ? Integer.valueOf(resources.getColor(R.color.color_7dcb88)) : null;
                Intrinsics.checkNotNull(valueOf);
                arrayList2.add(valueOf);
                Resources resources2 = getResources();
                Integer valueOf2 = resources2 != null ? Integer.valueOf(resources2.getColor(R.color.color_F9834E)) : null;
                Intrinsics.checkNotNull(valueOf2);
                arrayList2.add(valueOf2);
                pieDataSet.setColors(arrayList2);
                pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                pieDataSet.setValueLinePart1OffsetPercentage(10.0f);
                pieDataSet.setValueLinePart1Length(0.3f);
                pieDataSet.setValueLinePart2Length(0.1f);
                pieDataSet.setValueLineColor(getResources().getColor(R.color.main_text_color));
                pieDataSet.setValueTextColor(getResources().getColor(R.color.main_text_color));
                PieData pieData = new PieData(pieDataSet);
                pieData.setDrawValues(false);
                pieData.setValueFormatter(new PercentFormatter(new DecimalFormat("###,###,##")));
                pieData.setValueTextSize(10.0f);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding6 = this.p;
                if (activitySensAiDetailsBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding6 = null;
                }
                activitySensAiDetailsBinding6.pieChart.setData(pieData);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding7 = this.p;
                if (activitySensAiDetailsBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding7 = null;
                }
                activitySensAiDetailsBinding7.pieChart.setRotationEnabled(false);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding8 = this.p;
                if (activitySensAiDetailsBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding8 = null;
                }
                activitySensAiDetailsBinding8.pieChart.setHighlightPerTapEnabled(true);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding9 = this.p;
                if (activitySensAiDetailsBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding9 = null;
                }
                activitySensAiDetailsBinding9.pieChart.setTransparentCircleColor(0);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding10 = this.p;
                if (activitySensAiDetailsBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding10 = null;
                }
                activitySensAiDetailsBinding10.pieChart.setHoleRadius(70.0f);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding11 = this.p;
                if (activitySensAiDetailsBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding11 = null;
                }
                activitySensAiDetailsBinding11.pieChart.setHoleColor(0);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding12 = this.p;
                if (activitySensAiDetailsBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding12 = null;
                }
                activitySensAiDetailsBinding12.pieChart.setTransparentCircleRadius(0.0f);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding13 = this.p;
                if (activitySensAiDetailsBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding13 = null;
                }
                activitySensAiDetailsBinding13.pieChart.getLegend().setWordWrapEnabled(true);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding14 = this.p;
                if (activitySensAiDetailsBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding14 = null;
                }
                activitySensAiDetailsBinding14.pieChart.setDrawEntryLabels(false);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding15 = this.p;
                if (activitySensAiDetailsBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding15 = null;
                }
                activitySensAiDetailsBinding15.pieChart.setDrawHoleEnabled(true);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding16 = this.p;
                if (activitySensAiDetailsBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding16 = null;
                }
                activitySensAiDetailsBinding16.pieChart.setUsePercentValues(false);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding17 = this.p;
                if (activitySensAiDetailsBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding17 = null;
                }
                activitySensAiDetailsBinding17.pieChart.setEntryLabelTextSize(5.0f);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding18 = this.p;
                if (activitySensAiDetailsBinding18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding18 = null;
                }
                activitySensAiDetailsBinding18.pieChart.setEntryLabelColor(getResources().getColor(R.color.main_text_color));
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding19 = this.p;
                if (activitySensAiDetailsBinding19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding19 = null;
                }
                activitySensAiDetailsBinding19.pieChart.setCenterTextSize(24.0f);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string = getString(R.string.hit_percentage_value);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hit_percentage_value)");
                String format = String.format(locale, string, Arrays.copyOf(new Object[]{roundToInt + " % "}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                SpannableString spannableString = new SpannableString(format);
                spannableString.setSpan(new ForegroundColorSpan(getColor(R.color.white)), StringsKt__StringsKt.indexOf$default((CharSequence) format, roundToInt + " % ", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, roundToInt + " % ", 0, false, 6, (Object) null) + (roundToInt + " % ").length(), 33);
                spannableStringBuilder.append((CharSequence) spannableString);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding20 = this.p;
                if (activitySensAiDetailsBinding20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding20 = null;
                }
                activitySensAiDetailsBinding20.pieChart.setCenterText(spannableStringBuilder);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding21 = this.p;
                if (activitySensAiDetailsBinding21 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding21 = null;
                }
                activitySensAiDetailsBinding21.pieChart.setCenterTextColor(getColor(R.color.secondary_text_color));
                CustomMarkerViewPieChart customMarkerViewPieChart = new CustomMarkerViewPieChart(this, R.layout.custom_marker_view_pie_chart);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding22 = this.p;
                if (activitySensAiDetailsBinding22 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding22 = null;
                }
                customMarkerViewPieChart.setChartView(activitySensAiDetailsBinding22.pieChart);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding23 = this.p;
                if (activitySensAiDetailsBinding23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding23 = null;
                }
                activitySensAiDetailsBinding23.pieChart.setMarker(customMarkerViewPieChart);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding24 = this.p;
                if (activitySensAiDetailsBinding24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding24 = null;
                }
                activitySensAiDetailsBinding24.pieChart.getDescription().setEnabled(false);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding25 = this.p;
                if (activitySensAiDetailsBinding25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySensAiDetailsBinding25 = null;
                }
                activitySensAiDetailsBinding25.pieChart.getLegend().setEnabled(false);
                setCustomLegend(roundToInt, i4);
                ActivitySensAiDetailsBinding activitySensAiDetailsBinding26 = this.p;
                if (activitySensAiDetailsBinding26 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activitySensAiDetailsBinding2 = activitySensAiDetailsBinding26;
                }
                activitySensAiDetailsBinding2.pieChart.invalidate();
                return;
            }
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding27 = this.p;
            if (activitySensAiDetailsBinding27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding27 = null;
            }
            PieChart pieChart2 = activitySensAiDetailsBinding27.pieChart;
            Intrinsics.checkNotNullExpressionValue(pieChart2, "binding.pieChart");
            gone(pieChart2);
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding28 = this.p;
            if (activitySensAiDetailsBinding28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySensAiDetailsBinding2 = activitySensAiDetailsBinding28;
            }
            RecyclerView recyclerView2 = activitySensAiDetailsBinding2.gridView;
            Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.gridView");
            gone(recyclerView2);
        }
    }

    public final void J(SensAIActivitySummary sensAIActivitySummary) {
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
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding = this.p;
        if (activitySensAiDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding = null;
        }
        summaryShareData.setDate(activitySensAiDetailsBinding.sensAiDetails.tvSessionTimeValue.getText().toString());
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

    public final int getMaxHeartRate(int i) {
        if (i == 0) {
            i = 35;
        }
        return 220 - i;
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
        sensAISummaryDetailsViewModel4.getGetActivitySummaryDataLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.sensai.activity.h
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySensAIDetails.A(ActivitySensAIDetails.this, objectRef, (SensAIActivitySummary) obj);
            }
        });
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel5 = this.q;
        if (sensAISummaryDetailsViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
            sensAISummaryDetailsViewModel5 = null;
        }
        sensAISummaryDetailsViewModel5.getGetActivitySummaryDetailsLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.sensai.activity.g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySensAIDetails.B(ActivitySensAIDetails.this, (SensAIActivitySummaryDetails) obj);
            }
        });
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding = this.p;
        if (activitySensAiDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding = null;
        }
        activitySensAiDetailsBinding.sensAiDetails.ibShare.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIDetails.C(ActivitySensAIDetails.this, view);
            }
        });
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding2 = this.p;
        if (activitySensAiDetailsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding2 = null;
        }
        activitySensAiDetailsBinding2.ivHrZoneInfo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIDetails.D(ActivitySensAIDetails.this, view);
            }
        });
        x(this.t);
        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel6 = this.q;
        if (sensAISummaryDetailsViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSummaryDetails");
        } else {
            sensAISummaryDetailsViewModel = sensAISummaryDetailsViewModel6;
        }
        sensAISummaryDetailsViewModel.getPostQuestionarieLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.sensai.activity.i
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySensAIDetails.E(ActivitySensAIDetails.this, objectRef, (Boolean) obj);
            }
        });
    }

    public final void initToolbar() {
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding = this.p;
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding2 = null;
        if (activitySensAiDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding = null;
        }
        TextView textView = (TextView) activitySensAiDetailsBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding3 = this.p;
        if (activitySensAiDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySensAiDetailsBinding2 = activitySensAiDetailsBinding3;
        }
        textView.setText(getString(R.string.session_insights));
        ((TextView) activitySensAiDetailsBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIDetails.F(ActivitySensAIDetails.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySensAiDetailsBinding inflate = ActivitySensAiDetailsBinding.inflate(getLayoutInflater());
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
            FeedbackQuestionnarieModel feedbackQuestionnarieModel = this.w;
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
            FeedbackQuestionnarieModel feedbackQuestionnarieModel3 = this.w;
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
                FeedbackQuestionnarieModel feedbackQuestionnarieModel4 = this.w;
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

    public final void setArmSpeedGraphValues(@NotNull ArrayList<Integer> armSpeedList, int i) {
        int maximumYValue;
        LimitLine limitLine;
        LimitLine limitLine2;
        Intrinsics.checkNotNullParameter(armSpeedList, "armSpeedList");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
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
            StringBuilder sb = new StringBuilder();
            sb.append('B');
            sb.append(i2);
            arrayList2.add(sb.toString());
        }
        BarDataSet barDataSet = new BarDataSet(arrayList, AppConstants.EMPTY_STRING.getValue());
        barDataSet.setDrawValues(false);
        barDataSet.setBarShadowColor(getResources().getColor(R.color.mild_stress_color));
        barDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        barDataSet.setColor(getResources().getColor(R.color.mild_stress_color));
        if (barDataSet.getEntryCount() > 0) {
            int entryCount = barDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i3 = 0; i3 < entryCount; i3++) {
                iArr[i3] = getResources().getColor(R.color.mild_stress_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.mild_stress_color);
            barDataSet.setColors(Arrays.copyOf(iArr, entryCount));
        }
        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(false);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding = this.p;
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding2 = null;
        if (activitySensAiDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding = null;
        }
        activitySensAiDetailsBinding.speedChart.setData(barData);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding3 = this.p;
        if (activitySensAiDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding3 = null;
        }
        activitySensAiDetailsBinding3.speedChart.setFitBars(true);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding4 = this.p;
        if (activitySensAiDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding4 = null;
        }
        activitySensAiDetailsBinding4.speedChart.setDrawValueAboveBar(false);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding5 = this.p;
        if (activitySensAiDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding5 = null;
        }
        activitySensAiDetailsBinding5.speedChart.setDrawGridBackground(false);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding6 = this.p;
        if (activitySensAiDetailsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding6 = null;
        }
        activitySensAiDetailsBinding6.speedChart.setScaleEnabled(false);
        Description description = new Description();
        description.setText("");
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding7 = this.p;
        if (activitySensAiDetailsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding7 = null;
        }
        activitySensAiDetailsBinding7.speedChart.setDescription(description);
        barData.setValueTextColor(getResources().getColor(R.color.mild_stress_color));
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding8 = this.p;
        if (activitySensAiDetailsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding8 = null;
        }
        activitySensAiDetailsBinding8.speedChart.getPaint(7).setColor(getResources().getColor(R.color.mild_stress_color));
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding9 = this.p;
        if (activitySensAiDetailsBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding9 = null;
        }
        activitySensAiDetailsBinding9.speedChart.setDrawBorders(false);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding10 = this.p;
        if (activitySensAiDetailsBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding10 = null;
        }
        activitySensAiDetailsBinding10.speedChart.setAutoScaleMinMaxEnabled(false);
        String string = getString(R.string.sens_ai_details);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.sens_ai_details)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(this, R.layout.custom_marker_view_steps_hr, string, 6, arrayList2);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding11 = this.p;
        if (activitySensAiDetailsBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding11 = null;
        }
        customMarkerViewVitals.setChartView(activitySensAiDetailsBinding11.speedChart);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding12 = this.p;
        if (activitySensAiDetailsBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding12 = null;
        }
        activitySensAiDetailsBinding12.speedChart.setMarker(customMarkerViewVitals);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding13 = this.p;
        if (activitySensAiDetailsBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding13 = null;
        }
        YAxis axisLeft = activitySensAiDetailsBinding13.speedChart.getAxisLeft();
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
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding14 = this.p;
        if (activitySensAiDetailsBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding14 = null;
        }
        activitySensAiDetailsBinding14.speedChart.getAxisRight().setEnabled(false);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding15 = this.p;
        if (activitySensAiDetailsBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding15 = null;
        }
        XAxis xAxis = activitySensAiDetailsBinding15.speedChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.color_757575));
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding16 = this.p;
        if (activitySensAiDetailsBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding16 = null;
        }
        activitySensAiDetailsBinding16.speedChart.getAxisLeft().setStartAtZero(true);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding17 = this.p;
        if (activitySensAiDetailsBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding17 = null;
        }
        activitySensAiDetailsBinding17.speedChart.getAxisRight().setStartAtZero(true);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding18 = this.p;
        if (activitySensAiDetailsBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding18 = null;
        }
        activitySensAiDetailsBinding18.speedChart.getAxisLeft().setTextColor(getResources().getColor(R.color.color_757575));
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding19 = this.p;
        if (activitySensAiDetailsBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding19 = null;
        }
        activitySensAiDetailsBinding19.speedChart.getXAxis().setTextColor(getResources().getColor(R.color.color_757575));
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding20 = this.p;
        if (activitySensAiDetailsBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding20 = null;
        }
        activitySensAiDetailsBinding20.speedChart.getLegend().setTextColor(getResources().getColor(R.color.color_757575));
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
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding21 = this.p;
            if (activitySensAiDetailsBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding21 = null;
            }
            activitySensAiDetailsBinding21.speedChart.getAxisLeft().addLimitLine(limitLine2);
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
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding22 = this.p;
            if (activitySensAiDetailsBinding22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySensAiDetailsBinding22 = null;
            }
            activitySensAiDetailsBinding22.speedChart.getAxisLeft().addLimitLine(limitLine);
        }
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding23 = this.p;
        if (activitySensAiDetailsBinding23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding23 = null;
        }
        activitySensAiDetailsBinding23.speedChart.getLegend().setEnabled(false);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding24 = this.p;
        if (activitySensAiDetailsBinding24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding24 = null;
        }
        activitySensAiDetailsBinding24.speedChart.animateY(2000);
        ActivitySensAiDetailsBinding activitySensAiDetailsBinding25 = this.p;
        if (activitySensAiDetailsBinding25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiDetailsBinding25 = null;
        }
        activitySensAiDetailsBinding25.speedChart.invalidate();
        if (arrayList.size() > 6) {
            ActivitySensAiDetailsBinding activitySensAiDetailsBinding26 = this.p;
            if (activitySensAiDetailsBinding26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySensAiDetailsBinding2 = activitySensAiDetailsBinding26;
            }
            activitySensAiDetailsBinding2.speedChart.moveViewToX(6.0f);
        }
    }

    public final void setClientRefId(@Nullable String str) {
        this.v = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005f A[Catch: Exception -> 0x007c, TRY_LEAVE, TryCatch #0 {Exception -> 0x007c, blocks: (B:25:0x003f, B:27:0x004d, B:29:0x0053, B:35:0x005f), top: B:44:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0078 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setCustomLegend(int r16, int r17) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.sensai.activity.ActivitySensAIDetails.setCustomLegend(int, int):void");
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
        sensAISummaryDetailsViewModel.getGetQuestionarieLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.sensai.activity.f
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivitySensAIDetails.y(ActivitySensAIDetails.this, (FeedbackQuestionnarieModel) obj);
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
