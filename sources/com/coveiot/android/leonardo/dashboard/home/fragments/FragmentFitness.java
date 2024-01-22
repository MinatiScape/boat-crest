package com.coveiot.android.leonardo.dashboard.home.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.activities.ActivityFitnessPlan;
import com.coveiot.android.activitymodes.activities.ActivityHistory;
import com.coveiot.android.activitymodes.activities.ActivityPreparationPlan;
import com.coveiot.android.activitymodes.adapters.ActivityHistoryAdapter;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.utils.PlanStatus;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutHistory;
import com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity;
import com.coveiot.android.activitymodes.workoutVideos.activities.YoutubePlayerNewActivity;
import com.coveiot.android.activitymodes.workoutVideos.models.WorkoutVideosBean;
import com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentFitnessBinding;
import com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface;
import com.coveiot.android.dashboard2.util.SleepDataHelper;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.fitnesschallenges.ActivityCreateChallenge;
import com.coveiot.android.fitnesschallenges.FitnessChallengeDetails;
import com.coveiot.android.fitnesschallenges.FitnessChallengesHome;
import com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeAdapter;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeCleverTapConstants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeDetailsViewModel;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractSleepDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.DailyStepsInsightsModel;
import com.coveiot.android.leonardo.dashboard.health.model.FitnessBlogsModel;
import com.coveiot.android.leonardo.dashboard.health.model.GoalInsightsModel;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.model.WalkData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel;
import com.coveiot.android.leonardo.dashboard.home.adapters.FitnessBlogsAdapter;
import com.coveiot.android.leonardo.dashboard.home.adapters.FitnessGoalAdapter;
import com.coveiot.android.leonardo.dashboard.home.adapters.FitnessGoalInsightsAdapter;
import com.coveiot.android.leonardo.dashboard.home.adapters.FitnessWorkoutAdapter;
import com.coveiot.android.leonardo.more.models.GoalsModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.BottomSheetThemeDialogOneButtonTitleMessage;
import com.coveiot.android.theme.CircularArcProgressBar;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.compundview.LinePagerIndicatorDecoration;
import com.coveiot.android.theme.utils.GridSpacingItemDecoration;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.GetChallengeStartNEndDateRes;
import com.coveiot.coveaccess.fitnesschallenge.model.JoinChallengeReq;
import com.coveiot.covedb.sleep.DailySleepDataAlias;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.LastNightSleepData;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.github.mikephil.charting.data.BarEntry;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentFitness extends BaseFragment implements FitnessGoalInsightsAdapter.OnItemClickListener, ContractFitnessDashBoard, ContractSleepDashboard, FitnessWorkoutAdapter.ItemClickListener, FitnessChallengeAdapter.ChallengeClickListener, SuccessResultListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FitnessGoalInsightsAdapter B;
    @NotNull
    public Calendar D;
    @NotNull
    public SimpleDateFormat E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public int J;
    public boolean K;
    public ViewModelWorkoutFeedback L;
    public FitnessChallengeAdapter M;
    public FitnessChallengeListViewModel N;
    public FitnessChallengeDetailsViewModel O;
    public Pair<? extends Calendar, ? extends Calendar> P;
    public boolean Q;
    public boolean R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public boolean X;
    public boolean Y;
    public boolean Z;
    @Nullable
    public WalkData a0;
    public final SimpleDateFormat b0;
    @Nullable
    public BottomSheetThemeDialogOneButtonTitleMessage c0;
    @Nullable
    public String d0;
    public DatePickerDialog datePickerDialog;
    @Nullable
    public String e0;
    public FragmentFitnessBinding n;
    public FragmentStepsViewModel o;
    public FragmentSleepViewModel p;
    public FragmentSleepHistoryViewModel q;
    @Nullable
    public FitnessBlogsAdapter s;
    public ViewModelWorkoutHistory t;
    @Nullable
    public ActivityHistoryAdapter u;
    public ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard;
    @Nullable
    public FitnessWorkoutAdapter w;
    public ViewModelWorkoutVideos y;
    @Nullable
    public FitnessGoalAdapter z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentFitness";
    @NotNull
    public ArrayList<FitnessBlogsModel> r = new ArrayList<>();
    @NotNull
    public List<EntityWorkoutSession> v = new ArrayList();
    @NotNull
    public List<WorkoutVideosBean> x = new ArrayList();
    @NotNull
    public ArrayList<GoalsModel> A = new ArrayList<>();
    @NotNull
    public ArrayList<GoalInsightsModel> C = new ArrayList<>();

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentFitness newInstance() {
            return new FragmentFitness();
        }
    }

    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlanStatus.values().length];
            try {
                iArr[PlanStatus.ONGOING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PlanStatus.UPCOMING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes3.dex */
    public static final class a extends Lambda implements Function1<Intent, Unit> {
        public final /* synthetic */ BuddiesChallengeRes.Item $challenge;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(BuddiesChallengeRes.Item item) {
            super(1);
            this.$challenge = item;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.putExtra(FitnessChallengeConstants.CHALLENGE_ID, this.$challenge.getChallengeId().toString());
            launchActivity.putExtra(FitnessChallengeConstants.CHALLENGE_TYPE, this.$challenge.getChallengeType());
        }
    }

    /* loaded from: classes3.dex */
    public static final class b extends Lambda implements Function1<Integer, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Integer it) {
            Object clone = FragmentFitness.this.D.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            calendar.add(6, -1);
            FragmentFitness fragmentFitness = FragmentFitness.this;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            fragmentFitness.setCurrentDaySleep(it.intValue());
            FragmentSleepViewModel fragmentSleepViewModel = FragmentFitness.this.p;
            if (fragmentSleepViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelSleep");
                fragmentSleepViewModel = null;
            }
            fragmentSleepViewModel.getDailySleepData(calendar, false);
        }
    }

    /* loaded from: classes3.dex */
    public static final class c extends Lambda implements Function1<Integer, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Integer it) {
            Drawable drawable;
            FragmentFitness.this.dismissProgress();
            FragmentFitness.this.setPreviousDaySleep(0);
            FragmentFitness fragmentFitness = FragmentFitness.this;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            fragmentFitness.setPreviousDaySleep(it.intValue());
            int abs = Math.abs(FragmentFitness.this.getCurrentDaySleep() - FragmentFitness.this.getPreviousDaySleep());
            StringBuilder sb = new StringBuilder();
            PayUtils payUtils = PayUtils.INSTANCE;
            sb.append(payUtils.getHourFormat(abs));
            sb.append(':');
            sb.append(payUtils.getSecondFormat(abs % 60));
            String sb2 = sb.toString();
            if (FragmentFitness.this.getCurrentDaySleep() > FragmentFitness.this.getPreviousDaySleep()) {
                drawable = ContextCompat.getDrawable(FragmentFitness.this.requireContext(), R.drawable.ic_up_arrow_green);
                FragmentFitness.this.X = true;
            } else if (FragmentFitness.this.getCurrentDaySleep() < FragmentFitness.this.getPreviousDaySleep()) {
                drawable = ContextCompat.getDrawable(FragmentFitness.this.requireContext(), R.drawable.ic_down_arrow_red);
                FragmentFitness.this.X = true;
            } else {
                if (FragmentFitness.this.getCurrentDaySleep() == 0 || FragmentFitness.this.getCurrentDaySleep() != FragmentFitness.this.getPreviousDaySleep()) {
                    FragmentFitness.this.X = false;
                } else {
                    FragmentFitness.this.X = true;
                    TextView textView = FragmentFitness.this.W().tvSleepInsights;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Locale locale = Locale.ENGLISH;
                    String string = FragmentFitness.this.requireContext().getString(R.string.you_slept_equal_to_previous_night);
                    Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…_equal_to_previous_night)");
                    String format = String.format(locale, string, Arrays.copyOf(new Object[]{payUtils.getHourFormat(FragmentFitness.this.getCurrentDaySleep()) + ':' + payUtils.getSecondFormat(FragmentFitness.this.getCurrentDaySleep() % 60)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    textView.setText(format);
                }
                drawable = null;
            }
            if (drawable != null) {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Locale locale2 = Locale.ENGLISH;
                String string2 = FragmentFitness.this.requireContext().getString(R.string.you_slept_compared_to_previous_night);
                Intrinsics.checkNotNullExpressionValue(string2, "requireContext().getStri…mpared_to_previous_night)");
                String format2 = String.format(locale2, string2, Arrays.copyOf(new Object[]{sb2}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                int length = ((String) StringsKt__StringsKt.split$default((CharSequence) format2, new String[]{Constants.KEY_ICON}, false, 0, 6, (Object) null).get(0)).length();
                SpannableString spannableString = new SpannableString(format2);
                int lineHeight = FragmentFitness.this.W().tvSleepInsights.getLineHeight();
                drawable.setBounds(0, 0, lineHeight, lineHeight);
                spannableString.setSpan(new ImageSpan(drawable), length, length + 4, 33);
                FragmentFitness.this.W().tvSleepInsights.setText(spannableString);
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class d extends Lambda implements Function1<DailyStepsInsightsModel, Unit> {
        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DailyStepsInsightsModel dailyStepsInsightsModel) {
            invoke2(dailyStepsInsightsModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(DailyStepsInsightsModel dailyStepsInsightsModel) {
            String str;
            Drawable drawable;
            String str2;
            int abs = Math.abs(dailyStepsInsightsModel.getCurrentDaySteps() - dailyStepsInsightsModel.getPreviousDaySteps());
            if (dailyStepsInsightsModel.getCurrentDaySteps() > dailyStepsInsightsModel.getPreviousDaySteps()) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string = FragmentFitness.this.requireContext().getString(R.string.keep_it_up_steps);
                Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri….string.keep_it_up_steps)");
                PayUtils payUtils = PayUtils.INSTANCE;
                Context requireContext = FragmentFitness.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                str = String.format(locale, string, Arrays.copyOf(new Object[]{Integer.valueOf(abs), payUtils.formatLastSyncTime(requireContext, "hh:mm a")}, 2));
                Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
                drawable = ContextCompat.getDrawable(FragmentFitness.this.requireContext(), R.drawable.ic_up_arrow_green);
                FragmentFitness.this.Y = true;
            } else if (dailyStepsInsightsModel.getCurrentDaySteps() < dailyStepsInsightsModel.getPreviousDaySteps()) {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Locale locale2 = Locale.ENGLISH;
                String string2 = FragmentFitness.this.requireContext().getString(R.string.push_harder_steps);
                Intrinsics.checkNotNullExpressionValue(string2, "requireContext().getStri…string.push_harder_steps)");
                PayUtils payUtils2 = PayUtils.INSTANCE;
                Context requireContext2 = FragmentFitness.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                str = String.format(locale2, string2, Arrays.copyOf(new Object[]{Integer.valueOf(abs), payUtils2.formatLastSyncTime(requireContext2, "hh:mm a")}, 2));
                Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
                drawable = ContextCompat.getDrawable(FragmentFitness.this.requireContext(), R.drawable.ic_up_arrow_green);
                FragmentFitness.this.Y = true;
            } else {
                if (dailyStepsInsightsModel.getCurrentDaySteps() != 0 && dailyStepsInsightsModel.getCurrentDaySteps() == dailyStepsInsightsModel.getPreviousDaySteps()) {
                    FragmentFitness.this.Y = true;
                    FragmentFitness.this.Z = true;
                } else if (dailyStepsInsightsModel.getCurrentDaySteps() == 0 && dailyStepsInsightsModel.getPreviousDaySteps() == 0) {
                    FragmentFitness.this.Y = false;
                }
                str = null;
                drawable = null;
            }
            if (str != null) {
                List split$default = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{Constants.KEY_ICON}, false, 0, 6, (Object) null);
                Integer valueOf = (split$default == null || (str2 = (String) split$default.get(0)) == null) ? null : Integer.valueOf(str2.length());
                Integer valueOf2 = valueOf != null ? Integer.valueOf(valueOf.intValue() + 4) : null;
                int lineHeight = FragmentFitness.this.W().tvStepsInsights.getLineHeight();
                SpannableString spannableString = new SpannableString(str);
                if (drawable != null) {
                    drawable.setBounds(0, 0, lineHeight, lineHeight);
                    if (valueOf != null && valueOf2 != null) {
                        spannableString.setSpan(new ImageSpan(drawable), valueOf.intValue(), valueOf2.intValue(), 33);
                    }
                }
                FragmentFitness.this.W().tvStepsInsights.setText(spannableString);
            }
            if (FragmentFitness.this.Z) {
                TextView textView = FragmentFitness.this.W().tvStepsInsights;
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                Locale locale3 = Locale.ENGLISH;
                String string3 = FragmentFitness.this.requireContext().getString(R.string.keep_it_up_equals_steps);
                Intrinsics.checkNotNullExpressionValue(string3, "requireContext().getStri….keep_it_up_equals_steps)");
                PayUtils payUtils3 = PayUtils.INSTANCE;
                Context requireContext3 = FragmentFitness.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                String format = String.format(locale3, string3, Arrays.copyOf(new Object[]{Integer.valueOf(dailyStepsInsightsModel.getCurrentDaySteps()), payUtils3.formatLastSyncTime(requireContext3, "hh:mm a")}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                textView.setText(format);
            }
            if (FragmentFitness.this.Y || FragmentFitness.this.X) {
                if (!FragmentFitness.this.Y || FragmentFitness.this.X) {
                    if (!FragmentFitness.this.Y && FragmentFitness.this.X) {
                        FragmentFitness.this.W().tvStepsInsights.setText(FragmentFitness.this.requireContext().getString(R.string.steps_no_data_found));
                        FragmentFitness fragmentFitness = FragmentFitness.this;
                        TextView textView2 = fragmentFitness.W().tvSleepInsights;
                        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvSleepInsights");
                        fragmentFitness.visible(textView2);
                    } else {
                        FragmentFitness fragmentFitness2 = FragmentFitness.this;
                        TextView textView3 = fragmentFitness2.W().tvStepsInsights;
                        Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvStepsInsights");
                        fragmentFitness2.visible(textView3);
                        FragmentFitness fragmentFitness3 = FragmentFitness.this;
                        TextView textView4 = fragmentFitness3.W().tvSleepInsights;
                        Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvSleepInsights");
                        fragmentFitness3.visible(textView4);
                    }
                } else {
                    FragmentFitness fragmentFitness4 = FragmentFitness.this;
                    TextView textView5 = fragmentFitness4.W().tvStepsInsights;
                    Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvStepsInsights");
                    fragmentFitness4.visible(textView5);
                    FragmentFitness fragmentFitness5 = FragmentFitness.this;
                    TextView textView6 = fragmentFitness5.W().tvSleepInsights;
                    Intrinsics.checkNotNullExpressionValue(textView6, "binding.tvSleepInsights");
                    fragmentFitness5.visible(textView6);
                    FragmentFitness.this.W().tvSleepInsights.setText(FragmentFitness.this.requireContext().getString(R.string.sleep_no_data_found));
                }
            } else {
                FragmentFitness.this.W().tvStepsInsights.setText(FragmentFitness.this.requireContext().getString(R.string.no_data_found));
                FragmentFitness fragmentFitness6 = FragmentFitness.this;
                TextView textView7 = fragmentFitness6.W().tvSleepInsights;
                Intrinsics.checkNotNullExpressionValue(textView7, "binding.tvSleepInsights");
                fragmentFitness6.inVisible(textView7);
            }
            FragmentFitness.this.dismissProgress();
        }
    }

    /* loaded from: classes3.dex */
    public static final class e extends Lambda implements Function1<Intent, Unit> {
        public static final e INSTANCE = new e();

        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.putExtra(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SOURCE.getValue(), CleverTapConstants.CustomEventValues.FITNESS_CHALLENGE_FITNESS_SCREEN.getValue());
        }
    }

    /* loaded from: classes3.dex */
    public static final class f extends Lambda implements Function1<Intent, Unit> {
        public static final f INSTANCE = new f();

        public f() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$onViewCreated$1$1", f = "FragmentFitness.kt", i = {}, l = {254}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes3.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* loaded from: classes3.dex */
        public static final class a extends Lambda implements Function1<Intent, Unit> {
            public static final a INSTANCE = new a();

            public a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
                invoke2(intent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Intent launchActivity) {
                Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            }
        }

        public g(Continuation<? super g> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentFitness.this.getContext() != null) {
                    PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                    Context requireContext = FragmentFitness.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    this.label = 1;
                    obj = companion.getInstance(requireContext).doesPlanExists(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                FragmentFitness.this.startActivity(new Intent(FragmentFitness.this.requireContext(), ActivityPreparationPlan.class));
                return Unit.INSTANCE;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            if (((Boolean) obj).booleanValue()) {
                Context requireContext2 = FragmentFitness.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                a aVar = a.INSTANCE;
                Intent intent = new Intent(requireContext2, ActivityFitnessPlan.class);
                aVar.invoke((a) intent);
                requireContext2.startActivity(intent, null);
                return Unit.INSTANCE;
            }
            FragmentFitness.this.startActivity(new Intent(FragmentFitness.this.requireContext(), ActivityPreparationPlan.class));
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes3.dex */
    public static final class h extends Lambda implements Function1<Intent, Unit> {
        public static final h INSTANCE = new h();

        public h() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.putExtra("isPlanHistory", true);
        }
    }

    /* loaded from: classes3.dex */
    public static final class i extends Lambda implements Function1<Intent, Unit> {
        public static final i INSTANCE = new i();

        public i() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
        }
    }

    /* loaded from: classes3.dex */
    public static final class j extends Lambda implements Function1<Pair<? extends Integer, ? extends Integer>, Unit> {
        public j() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Integer, ? extends Integer> pair) {
            invoke2((Pair<Integer, Integer>) pair);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Pair<Integer, Integer> pair) {
            FragmentFitness.this.s0(pair.getFirst().intValue(), pair.getSecond().intValue());
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$setOngoingCardData$1", f = "FragmentFitness.kt", i = {}, l = {389}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes3.dex */
    public static final class k extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
        public int label;

        public k(Continuation<? super k> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new k(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((k) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            FragmentFitness fragmentFitness;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentFitness.this.getActivity() != null) {
                    FragmentFitness fragmentFitness2 = FragmentFitness.this;
                    ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = fragmentFitness2.getViewModelCurrentPlanDashboard();
                    boolean z = fragmentFitness2.K;
                    this.L$0 = fragmentFitness2;
                    this.label = 1;
                    Object ongoingPlanData = viewModelCurrentPlanDashboard.getOngoingPlanData(z, this);
                    if (ongoingPlanData == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    fragmentFitness = fragmentFitness2;
                    obj = ongoingPlanData;
                }
                return Unit.INSTANCE;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                fragmentFitness = (FragmentFitness) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            Pair pair = (Pair) obj;
            EntityPreparationDay entityPreparationDay = (EntityPreparationDay) pair.component1();
            EntityPreparationWeek entityPreparationWeek = (EntityPreparationWeek) pair.component2();
            fragmentFitness.W().fitnessJourneyOngoing.tvTodayGoalName.setText(fragmentFitness.getViewModelCurrentPlanDashboard().getActivitiesString(entityPreparationDay));
            String lowerCase = fragmentFitness.getViewModelCurrentPlanDashboard().getActivitiesString(entityPreparationDay).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (lowerCase.equals("rest day")) {
                fragmentFitness.W().fitnessJourneyOngoing.ivTodayGoal.setImageResource(2131232378);
                ProgressBar progressBar = fragmentFitness.W().fitnessJourneyOngoing.todayGoalProgress;
                Intrinsics.checkNotNullExpressionValue(progressBar, "binding.fitnessJourneyOngoing.todayGoalProgress");
                fragmentFitness.gone(progressBar);
                TextView textView = fragmentFitness.W().fitnessJourneyOngoing.tvTodayGoalTotalValue;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.fitnessJourneyOn…ing.tvTodayGoalTotalValue");
                fragmentFitness.gone(textView);
            } else {
                fragmentFitness.W().fitnessJourneyOngoing.ivTodayGoal.setImageResource(R.drawable.ic_walk_circular_background_grey);
                ProgressBar progressBar2 = fragmentFitness.W().fitnessJourneyOngoing.todayGoalProgress;
                Intrinsics.checkNotNullExpressionValue(progressBar2, "binding.fitnessJourneyOngoing.todayGoalProgress");
                fragmentFitness.visible(progressBar2);
                TextView textView2 = fragmentFitness.W().fitnessJourneyOngoing.tvTodayGoalTotalValue;
                Intrinsics.checkNotNullExpressionValue(textView2, "binding.fitnessJourneyOn…ing.tvTodayGoalTotalValue");
                fragmentFitness.visible(textView2);
            }
            TextView textView3 = fragmentFitness.W().fitnessJourneyOngoing.tvWeekPlanName;
            textView3.setText(entityPreparationWeek.getName() + ' ' + fragmentFitness.getString(R.string.dash_day) + ' ' + entityPreparationDay.getDay_number());
            fragmentFitness.W().fitnessJourneyOngoing.clCompleted.setVisibility(8);
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes3.dex */
    public static final class l extends Lambda implements Function1<Integer, Unit> {
        public l() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Integer it) {
            FragmentFitness.this.U = 0;
            FragmentFitness fragmentFitness = FragmentFitness.this;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            fragmentFitness.U = it.intValue();
            FragmentStepsViewModel fragmentStepsViewModel = FragmentFitness.this.o;
            Pair pair = null;
            if (fragmentStepsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelSteps");
                fragmentStepsViewModel = null;
            }
            Pair pair2 = FragmentFitness.this.P;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            Calendar calendar = (Calendar) pair2.getFirst();
            Pair pair3 = FragmentFitness.this.P;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair3;
            }
            fragmentStepsViewModel.rangeStepsData(calendar, (Calendar) pair.getSecond());
        }
    }

    /* loaded from: classes3.dex */
    public static final class m extends Lambda implements Function1<List<? extends DailyWalkData>, Unit> {
        public m() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends DailyWalkData> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<? extends DailyWalkData> list) {
            FragmentFitness.this.V = 0;
            FragmentFitness.this.W = 0;
            if (list != null && (!list.isEmpty())) {
                for (DailyWalkData dailyWalkData : list) {
                    FragmentFitness.this.V += dailyWalkData.getValue();
                    if (dailyWalkData.getValue() >= dailyWalkData.getStepsTarget()) {
                        FragmentFitness.this.W++;
                    }
                }
            }
            FragmentFitness.this.W().tvGoalsAchievedValue.setText(FragmentFitness.this.W + ' ' + FragmentFitness.this.requireContext().getString(R.string.days));
            TextView textView = FragmentFitness.this.W().tvGoalsInsightsDesc;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = FragmentFitness.this.requireContext().getString(R.string.fitness_insights_range_desc);
            Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…ness_insights_range_desc)");
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(FragmentFitness.this.V);
            StringBuilder sb = new StringBuilder();
            sb.append(FragmentFitness.this.U / 60);
            sb.append(':');
            sb.append(FragmentFitness.this.U % 60);
            objArr[1] = sb.toString();
            objArr[2] = FragmentFitness.this.Q ? "Week" : "Month";
            String format = String.format(locale, string, Arrays.copyOf(objArr, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            textView.setText(format);
            FragmentFitness.this.dismissProgress();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$updatePlanProgressUi$1", f = "FragmentFitness.kt", i = {1}, l = {429, 443, 451}, m = "invokeSuspend", n = {"planStartDate"}, s = {"L$0"})
    /* loaded from: classes3.dex */
    public static final class n extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$updatePlanProgressUi$1$currentPlan$1", f = "FragmentFitness.kt", i = {}, l = {430}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes3.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
            public int label;
            public final /* synthetic */ FragmentFitness this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentFitness fragmentFitness, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentFitness;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationPlan> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = this.this$0.getViewModelCurrentPlanDashboard();
                    this.label = 1;
                    obj = viewModelCurrentPlanDashboard.getCurrentPlan(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$updatePlanProgressUi$1$dayData$1", f = "FragmentFitness.kt", i = {}, l = {452}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes3.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationDay>, Object> {
            public int label;
            public final /* synthetic */ FragmentFitness this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(FragmentFitness fragmentFitness, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = fragmentFitness;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationDay> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = this.this$0.getViewModelCurrentPlanDashboard();
                    boolean z = this.this$0.K;
                    this.label = 1;
                    obj = viewModelCurrentPlanDashboard.getCurrentDayPlanData(z, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }

        public n(Continuation<? super n> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new n(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((n) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x00b8 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00c9  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                java.lang.String r2 = "dd MMM yy"
                java.lang.String r3 = "yyyy-MM-dd"
                r4 = 3
                r5 = 2
                r6 = 1
                r7 = 0
                if (r1 == 0) goto L2f
                if (r1 == r6) goto L2b
                if (r1 == r5) goto L23
                if (r1 != r4) goto L1b
                kotlin.ResultKt.throwOnFailure(r11)
                goto Lb9
            L1b:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L23:
                java.lang.Object r1 = r10.L$0
                java.lang.String r1 = (java.lang.String) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L78
            L2b:
                kotlin.ResultKt.throwOnFailure(r11)
                goto L46
            L2f:
                kotlin.ResultKt.throwOnFailure(r11)
                kotlinx.coroutines.CoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.getIO()
                com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$n$a r1 = new com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$n$a
                com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness r8 = com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness.this
                r1.<init>(r8, r7)
                r10.label = r6
                java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r1, r10)
                if (r11 != r0) goto L46
                return r0
            L46:
                com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan r11 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan) r11
                if (r11 == 0) goto La3
                java.lang.String r1 = r11.getStartDate()
                java.util.Date r1 = com.coveiot.utils.utility.AppUtils.parseDate(r1, r3)
                java.lang.String r1 = com.coveiot.utils.utility.AppUtils.formatDate(r1, r2)
                com.coveiot.android.activitymodes.repository.PreparationPlanRepository$Companion r6 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.Companion
                com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness r8 = com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness.this
                android.content.Context r8 = r8.requireContext()
                java.lang.String r9 = "requireContext()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)
                java.lang.Object r6 = r6.getInstance(r8)
                com.coveiot.android.activitymodes.repository.PreparationPlanRepository r6 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository) r6
                java.lang.String r11 = r11.getPlanId()
                r10.L$0 = r1
                r10.label = r5
                java.lang.Object r11 = r6.getPlanEndDate(r11, r10)
                if (r11 != r0) goto L78
                return r0
            L78:
                java.lang.String r11 = (java.lang.String) r11
                java.util.Date r11 = com.coveiot.utils.utility.AppUtils.parseDate(r11, r3)
                java.lang.String r11 = com.coveiot.utils.utility.AppUtils.formatDate(r11, r2)
                com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness r2 = com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness.this
                com.coveiot.android.boat.databinding.FragmentFitnessBinding r2 = com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness.access$getBinding(r2)
                com.coveiot.android.theme.databinding.ListItemWeekPlanLayoutBinding r2 = r2.fitnessJourneyOngoing
                android.widget.TextView r2 = r2.textView
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                r3.append(r1)
                java.lang.String r1 = " - "
                r3.append(r1)
                r3.append(r11)
                java.lang.String r11 = r3.toString()
                r2.setText(r11)
            La3:
                kotlinx.coroutines.CoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.getIO()
                com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$n$b r1 = new com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$n$b
                com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness r2 = com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness.this
                r1.<init>(r2, r7)
                r10.L$0 = r7
                r10.label = r4
                java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r1, r10)
                if (r11 != r0) goto Lb9
                return r0
            Lb9:
                com.coveiot.android.activitymodes.database.entities.EntityPreparationDay r11 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationDay) r11
                com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness r0 = com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness.this
                com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r0 = com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness.access$getViewModelWorkoutFeedback$p(r0)
                if (r0 != 0) goto Lc9
                java.lang.String r0 = "viewModelWorkoutFeedback"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
                goto Lca
            Lc9:
                r7 = r0
            Lca:
                r7.dailyDistanceCalculation(r11)
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness.n.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public FragmentFitness() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.D = calendar;
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        this.E = simpleDateFormat;
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.i
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                FragmentFitness.o0(FragmentFitness.this, (ActivityResult) obj);
            }
        }), "registerForActivityResul…)\n            }\n        }");
        this.R = true;
        this.b0 = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
    }

    public static final void B0(FragmentFitness this$0, PlanStatus planStatus) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (planStatus != null) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[planStatus.ordinal()];
            if (i2 == 1) {
                this$0.K = false;
                ConstraintLayout constraintLayout = this$0.W().fitnessJourneyOngoing.clOngoing;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.fitnessJourneyOngoing.clOngoing");
                this$0.visible(constraintLayout);
                ConstraintLayout constraintLayout2 = this$0.W().fitnessJourneyOngoing.clUpcoming;
                Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.fitnessJourneyOngoing.clUpcoming");
                this$0.gone(constraintLayout2);
                this$0.z0();
                return;
            } else if (i2 != 2) {
                this$0.K = false;
                ConstraintLayout constraintLayout3 = this$0.W().fitnessJourneyOngoing.clMain;
                Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.fitnessJourneyOngoing.clMain");
                this$0.gone(constraintLayout3);
                ConstraintLayout constraintLayout4 = this$0.W().fitnessJourney.clMain;
                Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.fitnessJourney.clMain");
                this$0.visible(constraintLayout4);
                this$0.W().tvFitnessPlan.setVisibility(8);
                return;
            } else {
                this$0.K = true;
                ConstraintLayout constraintLayout5 = this$0.W().fitnessJourneyOngoing.clUpcoming;
                Intrinsics.checkNotNullExpressionValue(constraintLayout5, "binding.fitnessJourneyOngoing.clUpcoming");
                this$0.visible(constraintLayout5);
                ConstraintLayout constraintLayout6 = this$0.W().fitnessJourneyOngoing.clOngoing;
                Intrinsics.checkNotNullExpressionValue(constraintLayout6, "binding.fitnessJourneyOngoing.clOngoing");
                this$0.gone(constraintLayout6);
                this$0.z0();
                return;
            }
        }
        this$0.K = false;
        ConstraintLayout constraintLayout7 = this$0.W().fitnessJourneyOngoing.clMain;
        Intrinsics.checkNotNullExpressionValue(constraintLayout7, "binding.fitnessJourneyOngoing.clMain");
        this$0.gone(constraintLayout7);
        ConstraintLayout constraintLayout8 = this$0.W().fitnessJourney.clMain;
        Intrinsics.checkNotNullExpressionValue(constraintLayout8, "binding.fitnessJourney.clMain");
        this$0.visible(constraintLayout8);
        this$0.W().tvFitnessPlan.setVisibility(8);
    }

    public static final void D0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void F0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getActivity(), WorkoutVideosActivity.class));
    }

    public static final void G0(FragmentFitness this$0, List workoutVideos) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(workoutVideos, "workoutVideos");
        this$0.x = workoutVideos;
        if (!workoutVideos.isEmpty()) {
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            this$0.w = new FitnessWorkoutAdapter(requireContext, workoutVideos, this$0);
            this$0.W().rvCultVideos.setAdapter(this$0.w);
        }
    }

    public static final void O0(CommonMessageDialog commonMessageDialog, FragmentFitness this$0) {
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        commonMessageDialog.dismiss();
        if (this$0.d0 == null || this$0.e0 == null) {
            return;
        }
        Intent intent = new Intent(this$0.requireContext(), FitnessChallengeDetails.class);
        intent.putExtra(FitnessChallengeConstants.CHALLENGE_ID, this$0.d0);
        intent.putExtra(FitnessChallengeConstants.CHALLENGE_TYPE, this$0.e0);
        intent.putExtra(FitnessChallengeConstants.ISJOINEDFROMFROMFITNESSPAGE, true);
        this$0.startActivity(intent);
    }

    public static final void P(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = this$0.c0;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
        bottomSheetThemeDialogOneButtonTitleMessage.dismiss();
    }

    public static final void Q0(BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        guestOrPairDevicePopup.dismiss();
    }

    public static final void R0(FragmentFitness this$0, BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (themesUtils.isGuestUser(requireContext)) {
            if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                ((FragmentHomeCallBackInterface) activity).navigateToLogin();
            }
        } else if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity2 = this$0.getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity2).navigateToPairDevice();
        }
        guestOrPairDevicePopup.dismiss();
    }

    public static final void T(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void T0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void U(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void V(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void V0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static /* synthetic */ void X0(FragmentFitness fragmentFitness, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        fragmentFitness.W0(z);
    }

    public static final void Z(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                this$0.c0();
                return;
            }
        }
        this$0.P0();
    }

    public static final void a0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                this$0.c0();
                return;
            }
        }
        this$0.P0();
    }

    public static final void b0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (!themesUtils.isGuestUser(requireContext)) {
                Context requireContext2 = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                if (!themesUtils.isPairDeviceLater(requireContext2)) {
                    if (!AppUtils.isNetConnected(this$0.requireContext())) {
                        this$0.showNoInternetDialog();
                        return;
                    }
                    Context requireContext3 = this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    e eVar = e.INSTANCE;
                    Intent intent = new Intent(requireContext3, FitnessChallengesHome.class);
                    eVar.invoke((e) intent);
                    requireContext3.startActivity(intent, null);
                    return;
                }
            }
            this$0.P0();
        }
    }

    public static final void e0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.t0();
        this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
        this$0.getDatePickerDialog().show();
    }

    public static final void f0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.X(PayUtils.INSTANCE.getPreviousDayCalendar(this$0.D));
    }

    public static final void g0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (DateUtils.isToday(this$0.D.getTimeInMillis())) {
            return;
        }
        this$0.X(PayUtils.INSTANCE.getNextDayCalendar(this$0.D));
    }

    public static final void h0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isPairDeviceLater(requireContext)) {
            if (AppUtils.isNetConnected(this$0.requireContext())) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new g(null), 2, null);
                return;
            } else {
                this$0.showNoInternetDialog();
                return;
            }
        }
        this$0.P0();
    }

    public static final void i0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.requireContext())) {
            this$0.showNoInternetDialog();
            return;
        }
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        h hVar = h.INSTANCE;
        Intent intent = new Intent(requireContext, ActivityFitnessPlan.class);
        hVar.invoke((h) intent);
        requireContext.startActivity(intent, null);
    }

    public static final void j0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireContext(), ActivityHistory.class));
    }

    public static final void k0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.requireContext())) {
            this$0.showNoInternetDialog();
            return;
        }
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        i iVar = i.INSTANCE;
        Intent intent = new Intent(requireContext, ActivityFitnessPlan.class);
        iVar.invoke((i) intent);
        requireContext.startActivity(intent, null);
    }

    public static final void l0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void m0(FragmentFitness this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.P0();
    }

    public static final void o0(FragmentFitness this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            ConstraintLayout constraintLayout = this$0.W().fitnessJourney.clMain;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.fitnessJourney.clMain");
            this$0.visible(constraintLayout);
            ConstraintLayout constraintLayout2 = this$0.W().fitnessJourneyOngoing.clMain;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.fitnessJourneyOngoing.clMain");
            this$0.gone(constraintLayout2);
        }
    }

    public static final void q0(FragmentFitness this$0, List sessions) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(sessions, "sessions");
        this$0.v = sessions;
        if (!sessions.isEmpty()) {
            if (sessions.size() <= 1) {
                this$0.W().imageViewDot2.setVisibility(8);
            } else {
                ImageView imageView = this$0.W().imageViewDot2;
                Intrinsics.checkNotNullExpressionValue(imageView, "binding.imageViewDot2");
                this$0.visible(imageView);
            }
            this$0.W().clNoActivity.setVisibility(8);
            LinearLayout linearLayout = this$0.W().linearLayoutDots;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.linearLayoutDots");
            this$0.visible(linearLayout);
            ActivityHistoryAdapter activityHistoryAdapter = this$0.u;
            Intrinsics.checkNotNull(activityHistoryAdapter);
            activityHistoryAdapter.setData(sessions);
            return;
        }
        this$0.W().ivNoHistory.setBackgroundResource(R.drawable.no_activity);
        ConstraintLayout constraintLayout = this$0.W().clNoActivity;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clNoActivity");
        this$0.visible(constraintLayout);
        this$0.W().linearLayoutDots.setVisibility(8);
    }

    public static final void u0(FragmentFitness this$0, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i2, i3, i4);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.X(newDate);
    }

    public static final void x0(FragmentFitness this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            View root = this$0.W().noChallengeView.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.noChallengeView.root");
            this$0.visible(root);
            LinearLayout linearLayout = this$0.W().challengeLinearLayoutDots;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.challengeLinearLayoutDots");
            this$0.gone(linearLayout);
            RecyclerView recyclerView = this$0.W().rvFitnessChallenge;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvFitnessChallenge");
            this$0.gone(recyclerView);
        }
    }

    public static final void y0(FragmentFitness this$0, BuddiesChallengeRes buddiesChallengeRes) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded() && buddiesChallengeRes != null) {
            RecyclerView recyclerView = this$0.W().rvFitnessChallenge;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvFitnessChallenge");
            this$0.visible(recyclerView);
            LinearLayout linearLayout = this$0.W().challengeLinearLayoutDots;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.challengeLinearLayoutDots");
            this$0.visible(linearLayout);
            LogHelper.d("ItData", "it Data " + buddiesChallengeRes);
            ArrayList arrayList = new ArrayList();
            arrayList.add(buddiesChallengeRes.getItems().get(0));
            if (buddiesChallengeRes.getItems().size() > 1 && buddiesChallengeRes.getItems().get(1) != null) {
                arrayList.add(buddiesChallengeRes.getItems().get(1));
            }
            LinearLayout linearLayout2 = this$0.W().challengeLinearLayoutDots;
            Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.challengeLinearLayoutDots");
            this$0.visibleIf(linearLayout2, buddiesChallengeRes.getItems().size() > 1);
            this$0.Q(buddiesChallengeRes.getItems().size(), 0);
            View root = this$0.W().noChallengeView.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.noChallengeView.root");
            this$0.gone(root);
            FitnessChallengeAdapter fitnessChallengeAdapter = this$0.M;
            if (fitnessChallengeAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeAdapter");
                fitnessChallengeAdapter = null;
            }
            fitnessChallengeAdapter.setChallengesList(arrayList);
            return;
        }
        LogHelper.d("ItData", "it is null}");
        View root2 = this$0.W().noChallengeView.getRoot();
        Intrinsics.checkNotNullExpressionValue(root2, "binding.noChallengeView.root");
        this$0.visible(root2);
        RecyclerView recyclerView2 = this$0.W().rvFitnessChallenge;
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.rvFitnessChallenge");
        this$0.gone(recyclerView2);
    }

    public final void A0() {
        getViewModelCurrentPlanDashboard().checkIfPlanOngoingOrFinished();
        ConstraintLayout constraintLayout = W().fitnessJourney.clMain;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.fitnessJourney.clMain");
        visible(constraintLayout);
        getViewModelCurrentPlanDashboard().getPlanStatusLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.j
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitness.B0(FragmentFitness.this, (PlanStatus) obj);
            }
        });
    }

    public final void C0() {
        ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.L;
        if (viewModelWorkoutFeedback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
            viewModelWorkoutFeedback = null;
        }
        MutableLiveData<Pair<Integer, Integer>> dailyDistanceData = viewModelWorkoutFeedback.getDailyDistanceData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final j jVar = new j();
        dailyDistanceData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.p
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitness.D0(Function1.this, obj);
            }
        });
    }

    public final void E0() {
        if (AppUtils.isNetConnected(getContext())) {
            ConstraintLayout constraintLayout = W().clWorkoutVideosMain;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clWorkoutVideosMain");
            visible(constraintLayout);
            W().tvCultVideos.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentFitness.F0(FragmentFitness.this, view);
                }
            });
            W().rvCultVideos.setLayoutManager(new LinearLayoutManager(requireContext()));
            ViewModelWorkoutVideos viewModelWorkoutVideos = this.y;
            ViewModelWorkoutVideos viewModelWorkoutVideos2 = null;
            if (viewModelWorkoutVideos == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
                viewModelWorkoutVideos = null;
            }
            viewModelWorkoutVideos.getRecommendedWorkoutVideos();
            ViewModelWorkoutVideos viewModelWorkoutVideos3 = this.y;
            if (viewModelWorkoutVideos3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutVideos");
            } else {
                viewModelWorkoutVideos2 = viewModelWorkoutVideos3;
            }
            viewModelWorkoutVideos2.getGetRecommenderCultFitVideosLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.o
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentFitness.G0(FragmentFitness.this, (List) obj);
                }
            });
            return;
        }
        W().clWorkoutVideosMain.setVisibility(8);
    }

    public final void H0() {
        GoalInsightsModel goalInsightsModel = new GoalInsightsModel("Day");
        GoalInsightsModel goalInsightsModel2 = new GoalInsightsModel("Week");
        GoalInsightsModel goalInsightsModel3 = new GoalInsightsModel("Month");
        this.C.add(goalInsightsModel);
        this.C.add(goalInsightsModel2);
        this.C.add(goalInsightsModel3);
        W().rvCalendarGoalsInsights.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        W().rvCalendarGoalsInsights.addItemDecoration(new GridSpacingItemDecoration(3, 30, false));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.B = new FitnessGoalInsightsAdapter(requireContext, this.C, this);
        W().rvCalendarGoalsInsights.setAdapter(this.B);
        R();
    }

    public final void I0() {
        Integer num;
        int intValue;
        if (UserDataManager.getInstance(requireContext()).getLiveStepsData(Calendar.getInstance(), BleApiManager.getInstance(getContext()).getBleApi().getMacAddress()) != null) {
            StepsDataModel liveStepsData = UserDataManager.getInstance(requireContext()).getLiveStepsData(Calendar.getInstance(), BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
            Drawable drawable = requireContext().getDrawable(R.drawable.ic_steps_profile);
            Intrinsics.checkNotNull(drawable);
            int steps = liveStepsData.getSteps();
            String string = getString(R.string.steps);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.steps)");
            FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
            Integer num2 = fitnessGoalStepsData != null ? fitnessGoalStepsData.target : null;
            this.A.add(new GoalsModel(drawable, steps, string, num2 != null ? num2.intValue() : 10000));
            M0(liveStepsData.getSteps());
        } else {
            Drawable drawable2 = requireContext().getDrawable(R.drawable.ic_steps_profile);
            Intrinsics.checkNotNull(drawable2);
            String string2 = getString(R.string.steps);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.steps)");
            FitnessGoal fitnessGoalStepsData2 = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
            Integer num3 = fitnessGoalStepsData2 != null ? fitnessGoalStepsData2.target : null;
            this.A.add(new GoalsModel(drawable2, 0, string2, num3 != null ? num3.intValue() : 10000));
            M0(0);
        }
        LastNightSleepData lastNightSleepData = UserDataManager.getInstance(getContext()).getLastNightSleepData(BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
        if (lastNightSleepData != null) {
            Drawable drawable3 = requireContext().getDrawable(R.drawable.ic_sleep);
            Intrinsics.checkNotNull(drawable3);
            int duration = lastNightSleepData.getDuration();
            String string3 = getString(R.string.sleep);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.sleep)");
            FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData();
            Integer num4 = fitnessGoalSleepData != null ? fitnessGoalSleepData.target : null;
            if (num4 == null) {
                intValue = 480;
            } else {
                Intrinsics.checkNotNullExpressionValue(num4, "UserDataManager.getInsta…eepData?.target ?: 8 * 60");
                intValue = num4.intValue();
            }
            this.A.add(new GoalsModel(drawable3, duration, string3, intValue));
            int duration2 = lastNightSleepData.getDuration();
            FitnessGoal fitnessGoalSleepData2 = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData();
            num = fitnessGoalSleepData2 != null ? fitnessGoalSleepData2.target : null;
            L0(duration2, num != null ? num.intValue() : 480);
        } else {
            Drawable drawable4 = requireContext().getDrawable(R.drawable.ic_sleep);
            Intrinsics.checkNotNull(drawable4);
            String string4 = getString(R.string.sleep);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.sleep)");
            FitnessGoal fitnessGoalStepsData3 = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
            Integer num5 = fitnessGoalStepsData3 != null ? fitnessGoalStepsData3.target : null;
            this.A.add(new GoalsModel(drawable4, 0, string4, num5 == null ? 480 : num5.intValue()));
            FitnessGoal fitnessGoalStepsData4 = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
            num = fitnessGoalStepsData4 != null ? fitnessGoalStepsData4.target : null;
            L0(0, num != null ? num.intValue() : 480);
        }
        W().rvGoals.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.z = new FitnessGoalAdapter(requireContext, this.A);
        W().rvGoals.setAdapter(this.z);
    }

    public final void J0() {
        boolean z = this.F;
        if (z && this.G) {
            this.J = 2;
            TextView textView = W().tvGoalStatus;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvGoalStatus");
            visible(textView);
            TextView textView2 = W().tvGoalDesc;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvGoalDesc");
            visible(textView2);
            if (this.H && this.I) {
                W().tvGoalDesc.setText(requireContext().getString(R.string.champ_goal_acheived));
                W().tvGoalStatus.setText(requireContext().getString(R.string.you_are_champ));
            } else {
                W().tvGoalDesc.setText(requireContext().getString(R.string.congrats_goal_acheived));
                W().tvGoalStatus.setText(requireContext().getString(R.string.congrats));
            }
        } else if (z) {
            this.J = 1;
            TextView textView3 = W().tvGoalStatus;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvGoalStatus");
            visible(textView3);
            TextView textView4 = W().tvGoalDesc;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvGoalDesc");
            visible(textView4);
            if (this.H) {
                W().tvGoalDesc.setText(requireContext().getString(R.string.champ_steps_goal_acheived));
                W().tvGoalStatus.setText(requireContext().getString(R.string.you_are_champ));
            } else {
                W().tvGoalDesc.setText(requireContext().getString(R.string.congrats_steps_goal_acheived));
                W().tvGoalStatus.setText(requireContext().getString(R.string.congrats));
            }
        } else if (this.G) {
            this.J = 1;
            TextView textView5 = W().tvGoalStatus;
            Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvGoalStatus");
            visible(textView5);
            TextView textView6 = W().tvGoalDesc;
            Intrinsics.checkNotNullExpressionValue(textView6, "binding.tvGoalDesc");
            visible(textView6);
            if (this.I) {
                W().tvGoalDesc.setText(requireContext().getString(R.string.champ_sleep_goal_acheived));
                W().tvGoalStatus.setText(requireContext().getString(R.string.you_are_champ));
            } else {
                W().tvGoalDesc.setText(requireContext().getString(R.string.congrats_sleep_goal_acheived));
                W().tvGoalStatus.setText(requireContext().getString(R.string.congrats));
            }
        } else {
            this.J = 0;
            W().tvGoalStatus.setVisibility(8);
            W().tvGoalDesc.setVisibility(8);
        }
        TextView textView7 = W().tvProgressValue;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = getString(R.string.goal_achieved_count);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.goal_achieved_count)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{Integer.valueOf(this.J)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView7.setText(Html.fromHtml(format), TextView.BufferType.SPANNABLE);
    }

    public final void K0() {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new k(null), 2, null);
        }
    }

    public final void L0(int i2, int i3) {
        this.G = i2 >= i3;
        this.I = i2 > i3;
        J0();
        float f2 = i2 / i3;
        int i4 = 100;
        try {
            int roundToInt = kotlin.math.c.roundToInt(f2 * 100);
            if (roundToInt < 100) {
                i4 = roundToInt;
            }
            W().arcProgressBar.circularArcProgressBar.setProgress(i4);
        } catch (IllegalArgumentException unused) {
            W().arcProgressBar.circularArcProgressBar.setProgress(0);
        }
    }

    public final void M0(int i2) {
        Integer num;
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
        Integer num2 = fitnessGoalStepsData != null ? fitnessGoalStepsData.target : null;
        int i3 = 10000;
        this.F = i2 >= (num2 == null ? 10000 : num2.intValue());
        FitnessGoal fitnessGoalStepsData2 = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
        Integer num3 = fitnessGoalStepsData2 != null ? fitnessGoalStepsData2.target : null;
        this.H = i2 > (num3 == null ? 10000 : num3.intValue());
        J0();
        CircularArcProgressBar circularArcProgressBar = W().arcProgressBar.circularArcProgressBarTop;
        Intrinsics.checkNotNullExpressionValue(circularArcProgressBar, "binding.arcProgressBar.circularArcProgressBarTop");
        visible(circularArcProgressBar);
        float f2 = i2;
        try {
            FitnessGoal fitnessGoalStepsData3 = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
            if (fitnessGoalStepsData3 != null && (num = fitnessGoalStepsData3.target) != null) {
                i3 = num.intValue();
            }
            int i4 = 100;
            int roundToInt = kotlin.math.c.roundToInt((f2 / i3) * 100);
            if (roundToInt < 100) {
                i4 = roundToInt;
            }
            W().arcProgressBar.circularArcProgressBarTop.setProgress(i4);
        } catch (IllegalArgumentException unused) {
            W().arcProgressBar.circularArcProgressBarTop.setProgress(0);
        }
    }

    public final void N0(String str) {
        Window window;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(requireContext, str, false, true);
        commonMessageDialog.show(getChildFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.v
            @Override // java.lang.Runnable
            public final void run() {
                FragmentFitness.O0(CommonMessageDialog.this, this);
            }
        }, 1000L);
    }

    public final void O(FragmentFitnessBinding fragmentFitnessBinding) {
        W().arcProgressBar.circularArcProgressBarTop.setProgress(0);
        W().arcProgressBar.circularArcProgressBar.setProgress(0);
        fragmentFitnessBinding.clSelectedOptionValues.setEnabled(false);
        fragmentFitnessBinding.tvSelectedTypeValue.setText(getString(R.string.today));
        TextView textView = fragmentFitnessBinding.tvProgressValue;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = getString(R.string.goal_achieved_count);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.goal_achieved_count)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{Integer.valueOf(this.J)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView.setText(Html.fromHtml(format), TextView.BufferType.SPANNABLE);
        fragmentFitnessBinding.tvSelectedTypeValue.setEnabled(false);
        fragmentFitnessBinding.ibForward.setEnabled(false);
        fragmentFitnessBinding.ibPrevious.setEnabled(false);
        fragmentFitnessBinding.tvSelectedTypeValue.setTextColor(requireContext().getColor(R.color.color_666666));
        fragmentFitnessBinding.ibPrevious.setImageResource(R.drawable.ic_small_left_arrow_grey);
        fragmentFitnessBinding.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey1);
        TextView tvSelectedTypeValue = fragmentFitnessBinding.tvSelectedTypeValue;
        Intrinsics.checkNotNullExpressionValue(tvSelectedTypeValue, "tvSelectedTypeValue");
        drawableEnd(tvSelectedTypeValue, R.drawable.ic_small_down_arrow_grey);
        fragmentFitnessBinding.ivShare.setEnabled(false);
        fragmentFitnessBinding.tvActivityHistory.setEnabled(false);
        fragmentFitnessBinding.tvActivityHistory.setCompoundDrawables(null, null, null, null);
        fragmentFitnessBinding.ivNoHistory.setBackgroundResource(R.drawable.guest_no_activities_shoes);
        ConstraintLayout clGetStarted = fragmentFitnessBinding.clGetStarted;
        Intrinsics.checkNotNullExpressionValue(clGetStarted, "clGetStarted");
        visible(clGetStarted);
        ConstraintLayout clGuestSleep = fragmentFitnessBinding.clGuestSleep;
        Intrinsics.checkNotNullExpressionValue(clGuestSleep, "clGuestSleep");
        visible(clGuestSleep);
        ConstraintLayout clGuestSteps = fragmentFitnessBinding.clGuestSteps;
        Intrinsics.checkNotNullExpressionValue(clGuestSteps, "clGuestSteps");
        visible(clGuestSteps);
    }

    public final void P0() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String string = getString(themesUtils.isGuestUser(requireContext2) ? R.string.login : R.string.pair_device);
        Intrinsics.checkNotNullExpressionValue(string, "if (requireContext().isG…pair_device\n            )");
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        String string2 = getString(themesUtils.isGuestUser(requireContext3) ? R.string.please_login_to_use_this_feature : R.string.please_pair_with_your_bluetooth_device_to_use_this_feature);
        Intrinsics.checkNotNullExpressionValue(string2, "if (requireContext().isG…his_feature\n            )");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentFitness.Q0(BottomSheetDialogTwoButtons.this, view);
            }
        });
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        String string4 = getString(themesUtils.isGuestUser(requireContext4) ? R.string.login_now : R.string.pair_now);
        Intrinsics.checkNotNullExpressionValue(string4, "if (requireContext().isG…ng.pair_now\n            )");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentFitness.R0(FragmentFitness.this, bottomSheetDialogTwoButtons, view);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    public final void Q(int i2, int i3) {
        LinearLayout linearLayout = W().challengeLinearLayoutDots;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.challengeLinearLayoutDots");
        linearLayout.removeAllViews();
        for (int i4 = 0; i4 < i2; i4++) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen.margin_8dp), 0);
            imageView.setLayoutParams(layoutParams);
            if (i4 == i3) {
                imageView.setImageResource(R.drawable.viewpager_selected_indicator);
            } else {
                imageView.setImageResource(R.drawable.viewpager_unselected_indicator);
            }
            linearLayout.addView(imageView);
        }
    }

    public final void R() {
        showProgress(true);
        ConstraintLayout constraintLayout = W().clDailyInsights;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clDailyInsights");
        visible(constraintLayout);
        ConstraintLayout constraintLayout2 = W().clRangeInsights;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clRangeInsights");
        gone(constraintLayout2);
        FragmentSleepViewModel fragmentSleepViewModel = this.p;
        FragmentStepsViewModel fragmentStepsViewModel = null;
        if (fragmentSleepViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSleep");
            fragmentSleepViewModel = null;
        }
        fragmentSleepViewModel.getDailySleepData(this.D, true);
        FragmentStepsViewModel fragmentStepsViewModel2 = this.o;
        if (fragmentStepsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSteps");
        } else {
            fragmentStepsViewModel = fragmentStepsViewModel2;
        }
        fragmentStepsViewModel.loadHourlyDataForInsights(this.D);
    }

    public final void S() {
        FragmentSleepViewModel fragmentSleepViewModel = this.p;
        FragmentStepsViewModel fragmentStepsViewModel = null;
        if (fragmentSleepViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSleep");
            fragmentSleepViewModel = null;
        }
        MutableLiveData<Integer> currentDaySleepData = fragmentSleepViewModel.getCurrentDaySleepData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final b bVar = new b();
        currentDaySleepData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.r
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitness.T(Function1.this, obj);
            }
        });
        FragmentSleepViewModel fragmentSleepViewModel2 = this.p;
        if (fragmentSleepViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSleep");
            fragmentSleepViewModel2 = null;
        }
        MutableLiveData<Integer> previousDaySleepData = fragmentSleepViewModel2.getPreviousDaySleepData();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final c cVar = new c();
        previousDaySleepData.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.t
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitness.U(Function1.this, obj);
            }
        });
        FragmentStepsViewModel fragmentStepsViewModel2 = this.o;
        if (fragmentStepsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSteps");
        } else {
            fragmentStepsViewModel = fragmentStepsViewModel2;
        }
        MutableLiveData<DailyStepsInsightsModel> stepsGoalInsights = fragmentStepsViewModel.getStepsGoalInsights();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        final d dVar = new d();
        stepsGoalInsights.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.s
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitness.V(Function1.this, obj);
            }
        });
    }

    public final void S0() {
        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = this.q;
        if (fragmentSleepHistoryViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historySleepViewModel");
            fragmentSleepHistoryViewModel = null;
        }
        MutableLiveData<Integer> totalSleepData = fragmentSleepHistoryViewModel.getTotalSleepData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final l lVar = new l();
        totalSleepData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.u
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitness.T0(Function1.this, obj);
            }
        });
    }

    public final void U0() {
        FragmentStepsViewModel fragmentStepsViewModel = this.o;
        if (fragmentStepsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSteps");
            fragmentStepsViewModel = null;
        }
        MutableLiveData<List<DailyWalkData>> rangeDailyWalkData = fragmentStepsViewModel.getRangeDailyWalkData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final m mVar = new m();
        rangeDailyWalkData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.q
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitness.V0(Function1.this, obj);
            }
        });
    }

    public final FragmentFitnessBinding W() {
        FragmentFitnessBinding fragmentFitnessBinding = this.n;
        if (fragmentFitnessBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentFitnessBinding;
    }

    public final void W0(boolean z) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new n(null), 2, null);
        }
    }

    public final void X(Calendar calendar) {
        if (isAdded()) {
            TextView textView = W().tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(PayUtils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                W().ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                W().ibForward.setEnabled(false);
            } else {
                W().ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                W().ibForward.setEnabled(true);
            }
            FragmentStepsViewModel fragmentStepsViewModel = this.o;
            FragmentSleepViewModel fragmentSleepViewModel = null;
            if (fragmentStepsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelSteps");
                fragmentStepsViewModel = null;
            }
            fragmentStepsViewModel.loadDailData(calendar);
            FragmentSleepViewModel fragmentSleepViewModel2 = this.p;
            if (fragmentSleepViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelSleep");
            } else {
                fragmentSleepViewModel = fragmentSleepViewModel2;
            }
            fragmentSleepViewModel.loadHourlyData(calendar);
            this.D = calendar;
            if (this.R) {
                R();
            } else {
                n0();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0083, code lost:
        if (r0.booleanValue() != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00b7, code lost:
        if (r0.booleanValue() != false) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Y() {
        /*
            r7 = this;
            android.content.Context r0 = r7.requireContext()
            boolean r0 = com.coveiot.utils.utility.AppUtils.isNetConnected(r0)
            r1 = 1
            java.lang.String r2 = "fitnessChallengeConfig.f…ess_challenges.visibility"
            r3 = 0
            java.lang.String r4 = "binding.fitnessChallenge"
            if (r0 == 0) goto L8b
            com.coveiot.android.theme.utils.ThemesUtils r0 = com.coveiot.android.theme.utils.ThemesUtils.INSTANCE
            android.content.Context r5 = r7.requireContext()
            java.lang.String r6 = "requireContext()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            boolean r0 = r0.isGuestUser(r5)
            if (r0 != 0) goto L2f
            com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel r0 = r7.N
            if (r0 != 0) goto L2b
            java.lang.String r0 = "fitnessChallengeListViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = 0
        L2b:
            r0.getDashboardFirstTwoChallenges()
            goto L41
        L2f:
            com.coveiot.android.boat.databinding.FragmentFitnessBinding r0 = r7.W()
            com.coveiot.android.fitnesschallenges.databinding.NoChallengesBannerBinding r0 = r0.noChallengeView
            android.view.View r0 = r0.getRoot()
            java.lang.String r5 = "binding.noChallengeView.root"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r5)
            r7.visible(r0)
        L41:
            com.coveiot.android.boat.databinding.FragmentFitnessBinding r0 = r7.W()
            androidx.recyclerview.widget.RecyclerView r0 = r0.rvFitnessChallenge
            com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$initFittnessChallenge$1 r5 = new com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$initFittnessChallenge$1
            r5.<init>()
            r0.addOnScrollListener(r5)
            android.content.Context r0 = r7.getContext()
            com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager r0 = com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager.getInstance(r0)
            com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration r0 = r0.getFitnessChallengeRemoteConfig()
            com.coveiot.android.boat.databinding.FragmentFitnessBinding r5 = r7.W()
            androidx.constraintlayout.widget.ConstraintLayout r5 = r5.fitnessChallenge
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r4)
            com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration$FitnessChallenge r4 = r0.getFitness_challenges()
            if (r4 == 0) goto L86
            com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration$FitnessChallenge r4 = r0.getFitness_challenges()
            java.lang.Boolean r4 = r4.getVisibility()
            if (r4 == 0) goto L86
            com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration$FitnessChallenge r0 = r0.getFitness_challenges()
            java.lang.Boolean r0 = r0.getVisibility()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L86
            goto L87
        L86:
            r1 = r3
        L87:
            r7.visibleIf(r5, r1)
            goto Lbe
        L8b:
            android.content.Context r0 = r7.getContext()
            com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager r0 = com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager.getInstance(r0)
            com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration r0 = r0.getFitnessChallengeRemoteConfig()
            com.coveiot.android.boat.databinding.FragmentFitnessBinding r5 = r7.W()
            androidx.constraintlayout.widget.ConstraintLayout r5 = r5.fitnessChallenge
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r4)
            if (r0 == 0) goto Lba
            com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration$FitnessChallenge r4 = r0.getFitness_challenges()
            if (r4 == 0) goto Lba
            com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration$FitnessChallenge r0 = r0.getFitness_challenges()
            java.lang.Boolean r0 = r0.getVisibility()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto Lba
            goto Lbb
        Lba:
            r1 = r3
        Lbb:
            r7.visibleIf(r5, r1)
        Lbe:
            com.coveiot.android.boat.databinding.FragmentFitnessBinding r0 = r7.W()
            com.coveiot.android.fitnesschallenges.databinding.NoChallengesBannerBinding r0 = r0.noChallengeView
            android.view.View r0 = r0.getRoot()
            com.coveiot.android.leonardo.dashboard.home.fragments.y r1 = new com.coveiot.android.leonardo.dashboard.home.fragments.y
            r1.<init>()
            r0.setOnClickListener(r1)
            com.coveiot.android.boat.databinding.FragmentFitnessBinding r0 = r7.W()
            com.coveiot.android.theme.databinding.RoundedCardNavLayoutBinding r0 = r0.viewFitnessChallengeDashboardHeader
            r1 = 2131952420(0x7f130324, float:1.9541282E38)
            java.lang.String r1 = r7.getString(r1)
            r0.setTitle(r1)
            com.coveiot.android.boat.databinding.FragmentFitnessBinding r0 = r7.W()
            com.coveiot.android.theme.databinding.RoundedCardNavLayoutBinding r0 = r0.viewFitnessChallengeDashboardHeader
            android.view.View r0 = r0.getRoot()
            com.coveiot.android.leonardo.dashboard.home.fragments.w r1 = new com.coveiot.android.leonardo.dashboard.home.fragments.w
            r1.<init>()
            r0.setOnClickListener(r1)
            com.coveiot.android.boat.databinding.FragmentFitnessBinding r0 = r7.W()
            android.widget.TextView r0 = r0.tvFitnessChallengeViewMore
            com.coveiot.android.leonardo.dashboard.home.fragments.f r1 = new com.coveiot.android.leonardo.dashboard.home.fragments.f
            r1.<init>()
            r0.setOnClickListener(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness.Y():void");
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i2) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i2)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    public final void c0() {
        if (isAdded()) {
            GetChallengeStartNEndDateRes fitnessChallengeActiveDateRange = FitnessChallengeSessionManager.getInstance(requireContext()).getFitnessChallengeActiveDateRange();
            if (!AppUtils.isNetConnected(requireContext())) {
                showNoInternetDialog();
                return;
            }
            if (fitnessChallengeActiveDateRange != null && fitnessChallengeActiveDateRange.getActiveCreatedBuddiesChallenges() != null) {
                Integer activeCreatedBuddiesChallenges = fitnessChallengeActiveDateRange.getActiveCreatedBuddiesChallenges();
                Intrinsics.checkNotNullExpressionValue(activeCreatedBuddiesChallenges, "getChallengeActiveDates.…eCreatedBuddiesChallenges");
                int intValue = activeCreatedBuddiesChallenges.intValue();
                Integer maxAllowedBuddiesChallenges = fitnessChallengeActiveDateRange.getMaxAllowedBuddiesChallenges();
                Intrinsics.checkNotNullExpressionValue(maxAllowedBuddiesChallenges, "getChallengeActiveDates.…xAllowedBuddiesChallenges");
                if (intValue >= maxAllowedBuddiesChallenges.intValue()) {
                    createChallengeError();
                    return;
                }
            }
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            f fVar = f.INSTANCE;
            Intent intent = new Intent(requireContext, ActivityCreateChallenge.class);
            fVar.invoke((f) intent);
            requireContext.startActivity(intent, null);
        }
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeAdapter.ChallengeClickListener
    public void challengeClick(@NotNull BuddiesChallengeRes.Item challenge, boolean z) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        if (isAdded()) {
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (!themesUtils.isGuestUser(requireContext)) {
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                if (!themesUtils.isPairDeviceLater(requireContext2)) {
                    if (!AppUtils.isNetConnected(requireContext())) {
                        showNoInternetDialog();
                        return;
                    }
                    this.d0 = challenge.getChallengeId().toString();
                    this.e0 = challenge.getType();
                    FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = null;
                    if (z) {
                        showProgress(true);
                        FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel2 = this.O;
                        if (fitnessChallengeDetailsViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeDetailsViewModel");
                        } else {
                            fitnessChallengeDetailsViewModel = fitnessChallengeDetailsViewModel2;
                        }
                        fitnessChallengeDetailsViewModel.joinFitnessChallenge(new JoinChallengeReq(challenge.getChallengeId(), challenge.getType()));
                        return;
                    }
                    Context requireContext3 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    a aVar = new a(challenge);
                    Intent intent = new Intent(requireContext3, FitnessChallengeDetails.class);
                    aVar.invoke((a) intent);
                    requireContext3.startActivity(intent, null);
                    return;
                }
            }
            P0();
        }
    }

    public final void createChallengeError() {
        if (isAdded()) {
            if (this.c0 == null) {
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String string = getString(R.string.maximum_challenges_created);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.maximum_challenges_created)");
                String string2 = getString(R.string.please_note_that_a_maximum_of_two_challenges_can_be_created_simultaneously);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…e_created_simultaneously)");
                BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = new BottomSheetThemeDialogOneButtonTitleMessage(requireContext, string, string2);
                this.c0 = bottomSheetThemeDialogOneButtonTitleMessage;
                Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
                String string3 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.android.theme.R.string.ok)");
                bottomSheetThemeDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.e
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentFitness.P(FragmentFitness.this, view);
                    }
                });
            }
            BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage2 = this.c0;
            Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage2);
            if (bottomSheetThemeDialogOneButtonTitleMessage2.isShowing()) {
                return;
            }
            BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage3 = this.c0;
            Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage3);
            bottomSheetThemeDialogOneButtonTitleMessage3.show();
        }
    }

    public final void d0() {
        FragmentFitnessBinding W = W();
        W.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentFitness.e0(FragmentFitness.this, view);
            }
        });
        W.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentFitness.f0(FragmentFitness.this, view);
            }
        });
        W.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentFitness.g0(FragmentFitness.this, view);
            }
        });
    }

    public final int getCurrentDaySleep() {
        return this.S;
    }

    @NotNull
    public final DatePickerDialog getDatePickerDialog() {
        DatePickerDialog datePickerDialog = this.datePickerDialog;
        if (datePickerDialog != null) {
            return datePickerDialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("datePickerDialog");
        return null;
    }

    @NotNull
    public final List<EntityWorkoutSession> getEntityWorkoutSessions() {
        return this.v;
    }

    public final int getGoalAchievedCount() {
        return this.J;
    }

    public final int getPreviousDaySleep() {
        return this.T;
    }

    @NotNull
    public final String getTAG() {
        return this.m;
    }

    @NotNull
    public final ViewModelCurrentPlanDashboard getViewModelCurrentPlanDashboard() {
        ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = this.viewModelCurrentPlanDashboard;
        if (viewModelCurrentPlanDashboard != null) {
            return viewModelCurrentPlanDashboard;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
        return null;
    }

    @NotNull
    public final List<WorkoutVideosBean> getWorkoutVideos() {
        return this.x;
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard, com.coveiot.android.leonardo.dashboard.health.listeners.ContractSleepDashboard
    public boolean isSyncInProgress() {
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        return ((DataSyncViewModel) ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(DataSyncViewModel.class)).checkIsSyncing();
    }

    public final void n0() {
        showProgress(true);
        ConstraintLayout constraintLayout = W().clDailyInsights;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clDailyInsights");
        gone(constraintLayout);
        ConstraintLayout constraintLayout2 = W().clRangeInsights;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clRangeInsights");
        visible(constraintLayout2);
        Object clone = this.D.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        calendar.add(5, this.Q ? -6 : -30);
        this.P = new Pair<>(calendar, this.D);
        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = this.q;
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (fragmentSleepHistoryViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historySleepViewModel");
            fragmentSleepHistoryViewModel = null;
        }
        Pair<? extends Calendar, ? extends Calendar> pair2 = this.P;
        if (pair2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            pair2 = null;
        }
        Calendar first = pair2.getFirst();
        Pair<? extends Calendar, ? extends Calendar> pair3 = this.P;
        if (pair3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
        } else {
            pair = pair3;
        }
        fragmentSleepHistoryViewModel.queryLastNightSleepDataFor(first, pair.getSecond());
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentFitnessBinding inflate = FragmentFitnessBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.n = inflate;
        return W().getRoot();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard
    public void onDataLoaded(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2, @NotNull List<? extends DailyWalkData> dataList) {
        Intrinsics.checkNotNullParameter(dataList, "dataList");
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        if (isAdded()) {
            Toast.makeText(requireContext(), str, 0).show();
        }
    }

    @Override // com.coveiot.android.leonardo.dashboard.home.adapters.FitnessGoalInsightsAdapter.OnItemClickListener
    public void onItemClicked(@NotNull GoalInsightsModel goalInsightsModel) {
        Intrinsics.checkNotNullParameter(goalInsightsModel, "goalInsightsModel");
        if (!goalInsightsModel.getCalendarType().equals("Week") && !goalInsightsModel.getCalendarType().equals("Month")) {
            this.R = true;
            R();
            return;
        }
        this.Q = goalInsightsModel.getCalendarType().equals("Week");
        this.R = false;
        n0();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getViewModelCurrentPlanDashboard().checkIfPlanOngoingOrFinished();
        Y();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        if (isAdded()) {
            dismissProgress();
            String string = requireContext().getString(R.string.woah_challenge_joined);
            Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…ng.woah_challenge_joined)");
            N0(string);
        }
    }

    @Override // com.coveiot.android.leonardo.dashboard.home.adapters.FitnessWorkoutAdapter.ItemClickListener
    public void onVideosItemClick(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable List<String> list) {
        if (AppUtils.isNetConnected(requireContext())) {
            Intent intent = new Intent(requireContext(), YoutubePlayerNewActivity.class);
            intent.putExtra(WorkoutConstants.YOUTUBE_VIDEO_ID, str);
            intent.putExtra(WorkoutConstants.VIDEO_ID, str2);
            intent.putExtra(WorkoutConstants.VIDEO_NAME, str3);
            startActivityForResult(intent, 121);
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.CV_VIDEO_TAP.getValue());
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
            analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.WORKOUT_VIDEOS.getValue());
            HashMap<String, String> hashMap = new HashMap<>();
            String value = FirebaseEventParams.MetaData.CV_VIDEO_NAME.getValue();
            String lowerCase = String.valueOf(str3).toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            hashMap.put(value, lowerCase);
            String value2 = FirebaseEventParams.MetaData.CV_VIDEO_DURATION.getValue();
            WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
            Intrinsics.checkNotNull(num);
            hashMap.put(value2, workoutUtils.getFormattedDuration(num.intValue()));
            String value3 = FirebaseEventParams.MetaData.CV_VIDEO_ID.getValue();
            String lowerCase2 = String.valueOf(str2).toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
            hashMap.put(value3, lowerCase2);
            if (list != null) {
                int size = list.size();
                String str4 = "";
                for (int i2 = 0; i2 < size; i2++) {
                    String str5 = list.get(i2);
                    if (StringsKt__StringsKt.contains$default((CharSequence) str5, (CharSequence) "_", false, 2, (Object) null)) {
                        str5 = kotlin.text.m.replace$default(str5, "_", HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null);
                    }
                    if (list.size() > 1) {
                        str4 = str4.length() > 0 ? str4 + ',' + str5 : str4 + str5;
                    } else {
                        str4 = str4 + str5;
                    }
                }
                String value4 = FirebaseEventParams.MetaData.CV_VIDEO_CATEGORY.getValue();
                String lowerCase3 = str4.toString().toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                hashMap.put(value4, lowerCase3);
            }
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            return;
        }
        Toast.makeText(requireContext(), getString(R.string.no_internet_connection), 0).show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.o = (FragmentStepsViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentStepsViewModel.class);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.p = (FragmentSleepViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext2)).get(FragmentSleepViewModel.class);
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        this.q = (FragmentSleepHistoryViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext3)).get(FragmentSleepHistoryViewModel.class);
        this.L = (ViewModelWorkoutFeedback) new ViewModelProvider(this).get(ViewModelWorkoutFeedback.class);
        FragmentStepsViewModel fragmentStepsViewModel = this.o;
        FragmentSleepViewModel fragmentSleepViewModel = null;
        if (fragmentStepsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSteps");
            fragmentStepsViewModel = null;
        }
        fragmentStepsViewModel.setContractFitnessDashBoard(this);
        FragmentStepsViewModel fragmentStepsViewModel2 = this.o;
        if (fragmentStepsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSteps");
            fragmentStepsViewModel2 = null;
        }
        fragmentStepsViewModel2.setMLifecycleOwner(this);
        FragmentSleepViewModel fragmentSleepViewModel2 = this.p;
        if (fragmentSleepViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSleep");
            fragmentSleepViewModel2 = null;
        }
        fragmentSleepViewModel2.setContractSleepDashboard$app_prodRelease(this);
        FragmentSleepViewModel fragmentSleepViewModel3 = this.p;
        if (fragmentSleepViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelSleep");
            fragmentSleepViewModel3 = null;
        }
        fragmentSleepViewModel3.setMLifecycleOwner(this);
        this.t = (ViewModelWorkoutHistory) ViewModelProviders.of(this).get(ViewModelWorkoutHistory.class);
        this.y = (ViewModelWorkoutVideos) ViewModelProviders.of(this).get(ViewModelWorkoutVideos.class);
        setViewModelCurrentPlanDashboard((ViewModelCurrentPlanDashboard) ViewModelProviders.of(this).get(ViewModelCurrentPlanDashboard.class));
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        this.N = new FitnessChallengeListViewModel(requireContext4);
        Context requireContext5 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
        FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = new FitnessChallengeDetailsViewModel(requireContext5);
        this.O = fitnessChallengeDetailsViewModel;
        fitnessChallengeDetailsViewModel.setMListener(this);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext6 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
        if (themesUtils.isGuestUser(requireContext6)) {
            ConstraintLayout constraintLayout = W().clFitnessJourneyMain;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clFitnessJourneyMain");
            gone(constraintLayout);
            ConstraintLayout constraintLayout2 = W().clGoalInsightsMain;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clGoalInsightsMain");
            gone(constraintLayout2);
            O(W());
            W().tvInfo.setText(getString(R.string.pair_your_device_to_track_activities_guest));
        } else {
            Context requireContext7 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext7, "requireContext()");
            if (themesUtils.isPairDeviceLater(requireContext7)) {
                O(W());
                ConstraintLayout constraintLayout3 = W().clGoalInsightsMain;
                Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clGoalInsightsMain");
                gone(constraintLayout3);
                W().tvInfo.setText(getString(R.string.pair_your_device_to_track_activities));
            } else {
                TextView textView = W().tvSelectedTypeValue;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.tvSelectedTypeValue");
                drawableEnd(textView, R.drawable.ic_small_down_arrow_white);
                W().ibPrevious.setImageResource(R.drawable.ic_small_left_arrow_white);
                W().ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                H0();
                I0();
                p0();
                v0();
                S();
                U0();
                S0();
                C0();
                FragmentStepsViewModel fragmentStepsViewModel3 = this.o;
                if (fragmentStepsViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelSteps");
                    fragmentStepsViewModel3 = null;
                }
                Calendar calendar = Calendar.getInstance();
                Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                fragmentStepsViewModel3.loadDailData(calendar);
                FragmentSleepViewModel fragmentSleepViewModel4 = this.p;
                if (fragmentSleepViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelSleep");
                } else {
                    fragmentSleepViewModel = fragmentSleepViewModel4;
                }
                Calendar calendar2 = Calendar.getInstance();
                Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
                fragmentSleepViewModel.loadHourlyData(calendar2);
            }
        }
        r0();
        w0();
        d0();
        E0();
        W().fitnessJourney.ivIcon.setImageDrawable(requireContext().getDrawable(R.drawable.fitness_plan_bg));
        W().fitnessJourney.tvHeader.setText(requireContext().getString(R.string.build_fitness_plan));
        W().fitnessJourney.tvInfo.setText(requireContext().getString(R.string.fitness_plans_tailored));
        W().fitnessJourney.clMain.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitness.h0(FragmentFitness.this, view2);
            }
        });
        W().tvFitnessPlan.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitness.i0(FragmentFitness.this, view2);
            }
        });
        W().tvActivityHistory.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitness.j0(FragmentFitness.this, view2);
            }
        });
        W().fitnessJourneyOngoing.clMain.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitness.k0(FragmentFitness.this, view2);
            }
        });
        A0();
        W().ivShare.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitness.l0(FragmentFitness.this, view2);
            }
        });
        W().tvGetStarted.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitness.m0(FragmentFitness.this, view2);
            }
        });
    }

    public final void p0() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.u = new ActivityHistoryAdapter(requireContext, WorkoutUiBeanProvider.ScreenType.ACTIVITY_FITNESS);
        W().rvActivityHistory.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        W().rvActivityHistory.setAdapter(this.u);
        ViewModelWorkoutHistory viewModelWorkoutHistory = this.t;
        if (viewModelWorkoutHistory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutHistory");
            viewModelWorkoutHistory = null;
        }
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String connectedDeviceMacAddress = new PreferenceManager(requireContext2).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNull(connectedDeviceMacAddress);
        viewModelWorkoutHistory.getAllWorkoutSessionsFromDB(connectedDeviceMacAddress).observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.n
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitness.q0(FragmentFitness.this, (List) obj);
            }
        });
        W().rvActivityHistory.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitness$setActivityWorkoutHistory$2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int i2) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrollStateChanged(recyclerView, i2);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                if (((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() == 0) {
                    FragmentFitness.this.W().imageViewDot1.setImageDrawable(FragmentFitness.this.requireContext().getDrawable(R.drawable.viewpager_selected_indicator));
                    FragmentFitness.this.W().imageViewDot2.setImageDrawable(FragmentFitness.this.requireContext().getDrawable(R.drawable.viewpager_unselected_indicator));
                    return;
                }
                FragmentFitness.this.W().imageViewDot1.setImageDrawable(FragmentFitness.this.requireContext().getDrawable(R.drawable.viewpager_unselected_indicator));
                FragmentFitness.this.W().imageViewDot2.setImageDrawable(FragmentFitness.this.requireContext().getDrawable(R.drawable.viewpager_selected_indicator));
            }
        });
    }

    public final void r0() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.M = new FitnessChallengeAdapter(requireContext, this);
        RecyclerView recyclerView = W().rvFitnessChallenge;
        FitnessChallengeAdapter fitnessChallengeAdapter = this.M;
        if (fitnessChallengeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeAdapter");
            fitnessChallengeAdapter = null;
        }
        recyclerView.setAdapter(fitnessChallengeAdapter);
        W().rvFitnessChallenge.setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
    }

    @SuppressLint({"SetTextI18n"})
    public final void s0(int i2, int i3) {
        TextView textView = W().fitnessJourneyOngoing.tvTodayGoalTotalValue;
        StringBuilder sb = new StringBuilder();
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        double convertCmToMeters = themesUtils.convertCmToMeters(i2);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        sb.append(themesUtils.getStringFormattedValueTillNDecimal(Double.valueOf(convertCmToMeters / themesUtils.getDivisionValueAsPerUnit(requireContext)), 2));
        sb.append('/');
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(requireContext()).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(requireContext()).isDistanceUnitInMile");
        sb.append(themesUtils.getDistanceWithUnit(requireContext2, i3, isDistanceUnitInMile.booleanValue() ? 1 : 0));
        textView.setText(sb.toString());
        W().fitnessJourneyOngoing.todayGoalProgress.setMax(i3);
        W().fitnessJourneyOngoing.todayGoalProgress.setProgress((int) themesUtils.convertCmToMeters(i2));
    }

    public final void setCurrentDaySleep(int i2) {
        this.S = i2;
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setEntityWorkoutSessions(@NotNull List<EntityWorkoutSession> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.v = list;
    }

    public final void setGoalAchievedCount(int i2) {
        this.J = i2;
    }

    public final void setPreviousDaySleep(int i2) {
        this.T = i2;
    }

    public final void setViewModelCurrentPlanDashboard(@NotNull ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard) {
        Intrinsics.checkNotNullParameter(viewModelCurrentPlanDashboard, "<set-?>");
        this.viewModelCurrentPlanDashboard = viewModelCurrentPlanDashboard;
    }

    public final void setWorkoutVideos(@NotNull List<WorkoutVideosBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.x = list;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        shareData.setGraphType(getResources().getString(R.string.share_daily));
        shareData.setDwmValue(simpleDateFormat.format(this.D.getTime()));
        shareData.setData(String.valueOf(this.J));
        ArrayList<GoalsModel> arrayList = this.A;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        shareData.setGoalsModel(this.A);
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.fitness_tab);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.fitness_tab)");
        companion.navigateToShareScreen(requireContext, shareData, string);
    }

    public final void t0() {
        Calendar calendar = this.D;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.a
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                FragmentFitness.u0(FragmentFitness.this, datePicker, i2, i3, i4);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractSleepDashboard
    public void updateDailyLevelData(@Nullable DailySleepDataAlias dailySleepDataAlias) {
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard
    public void updateDailyLevelData(@Nullable DailyWalkData dailyWalkData) {
        if (dailyWalkData != null) {
            dailyWalkData.getValue();
            int value = dailyWalkData.getValue();
            String caloriesValue = AppUtils.getCaloriesValue(dailyWalkData.getCalories());
            Intrinsics.checkNotNullExpressionValue(caloriesValue, "getCaloriesValue(dailyWalkData.calories)");
            String valueOf = String.valueOf(dailyWalkData.getMeters());
            String str = dailyWalkData.getmDate();
            Intrinsics.checkNotNullExpressionValue(str, "dailyWalkData.getmDate()");
            this.a0 = new WalkData(value, caloriesValue, valueOf, str).setType("Day");
            TextView textView = W().tvSelectedTypeValue;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvSelectedTypeValue");
            visible(textView);
            if (this.a0 != null) {
                TextView textView2 = W().tvSelectedTypeValue;
                FragmentStepsViewModel fragmentStepsViewModel = this.o;
                if (fragmentStepsViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelSteps");
                    fragmentStepsViewModel = null;
                }
                WalkData walkData = this.a0;
                Intrinsics.checkNotNull(walkData);
                textView2.setText(fragmentStepsViewModel.loadSelectedDAteRange(walkData));
                if (PayUtils.currentDayString().equals(this.E.format(this.b0.parse(dailyWalkData.mDate)))) {
                    W().tvSelectedTypeValue.setText(getResources().getString(R.string.today));
                }
            } else {
                Date parse = this.b0.parse(dailyWalkData.mDate);
                if (PayUtils.currentDayString().equals(this.E.format(parse))) {
                    W().tvSelectedTypeValue.setText(getResources().getString(R.string.today));
                } else if (PayUtils.previousDayString().equals(this.E.format(parse))) {
                    W().tvSelectedTypeValue.setText(getResources().getString(R.string.yesterday));
                } else {
                    W().tvSelectedTypeValue.setText(this.E.format(parse));
                }
            }
            FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData();
            int i2 = 10000;
            if ((fitnessGoalStepsData != null ? fitnessGoalStepsData.target : null) != null) {
                Integer num = fitnessGoalStepsData.target;
                Intrinsics.checkNotNullExpressionValue(num, "fitnessStepGoalData.target");
                if (num.intValue() > 0) {
                    Integer num2 = fitnessGoalStepsData.target;
                    Intrinsics.checkNotNullExpressionValue(num2, "fitnessStepGoalData.target");
                    i2 = num2.intValue();
                }
            }
            ArrayList<GoalsModel> arrayList = this.A;
            Drawable drawable = requireContext().getDrawable(R.drawable.ic_steps_profile);
            Intrinsics.checkNotNull(drawable);
            int value2 = dailyWalkData.getValue();
            String string = requireContext().getString(R.string.steps);
            Intrinsics.checkNotNullExpressionValue(string, "requireContext().getString(R.string.steps)");
            arrayList.set(0, new GoalsModel(drawable, value2, string, i2));
            M0(dailyWalkData.getValue());
        } else {
            SimpleDateFormat simpleDateFormat = this.b0;
            PayUtils payUtils = PayUtils.INSTANCE;
            Calendar calendar = this.D;
            Intrinsics.checkNotNull(calendar);
            Date parse2 = simpleDateFormat.parse(payUtils.getDateFormatValue(calendar));
            if (PayUtils.currentDayString().equals(this.E.format(parse2))) {
                W().tvSelectedTypeValue.setText(getResources().getString(R.string.today));
            } else if (PayUtils.previousDayString().equals(this.E.format(parse2))) {
                W().tvSelectedTypeValue.setText(getResources().getString(R.string.yesterday));
            } else {
                W().tvSelectedTypeValue.setText(this.E.format(parse2));
            }
            ArrayList<GoalsModel> arrayList2 = this.A;
            Drawable drawable2 = requireContext().getDrawable(R.drawable.ic_steps_profile);
            Intrinsics.checkNotNull(drawable2);
            String string2 = requireContext().getString(R.string.steps);
            Intrinsics.checkNotNullExpressionValue(string2, "requireContext().getString(R.string.steps)");
            Integer num3 = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData().target;
            Intrinsics.checkNotNullExpressionValue(num3, "getInstance(requireConte…tnessGoalStepsData.target");
            arrayList2.set(0, new GoalsModel(drawable2, 0, string2, num3.intValue()));
            M0(0);
        }
        FitnessGoalAdapter fitnessGoalAdapter = this.z;
        if (fitnessGoalAdapter != null) {
            fitnessGoalAdapter.notifyItemChanged(0);
        }
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard
    public void updateHourlyLevelData(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2, boolean z) {
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractSleepDashboard
    public void updateHourlyLevelData(@Nullable List<? extends SleepDataModelForLastNight> list) {
        Integer num;
        int i2 = 480;
        if (UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData() != null) {
            FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData();
            if ((fitnessGoalSleepData != null ? fitnessGoalSleepData.target : null) == null || (num = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData().target) == null || num.intValue() != 0) {
                Integer num2 = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData().target;
                Intrinsics.checkNotNullExpressionValue(num2, "{\n                    Us….target\n                }");
                i2 = num2.intValue();
            }
        }
        if (list != null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            SleepDataModel sleepDataModel = SleepDataHelper.getSleepDataModel(requireContext, list);
            ArrayList<GoalsModel> arrayList = this.A;
            Drawable drawable = requireContext().getDrawable(R.drawable.ic_sleep);
            Intrinsics.checkNotNull(drawable);
            Integer valueOf = sleepDataModel != null ? Integer.valueOf(sleepDataModel.getCountTotalSleep()) : null;
            Intrinsics.checkNotNull(valueOf);
            int intValue = valueOf.intValue();
            String string = requireContext().getString(R.string.sleep);
            Intrinsics.checkNotNullExpressionValue(string, "requireContext().getString(R.string.sleep)");
            arrayList.set(1, new GoalsModel(drawable, intValue, string, i2));
            FitnessGoalAdapter fitnessGoalAdapter = this.z;
            if (fitnessGoalAdapter != null) {
                fitnessGoalAdapter.notifyItemChanged(1);
            }
            if (sleepDataModel != null) {
                sleepDataModel.getCountTotalSleep();
                L0(sleepDataModel.getCountTotalSleep(), i2);
                return;
            }
            L0(0, i2);
            return;
        }
        ArrayList<GoalsModel> arrayList2 = this.A;
        Drawable drawable2 = requireContext().getDrawable(R.drawable.ic_sleep);
        Intrinsics.checkNotNull(drawable2);
        String string2 = requireContext().getString(R.string.sleep);
        Intrinsics.checkNotNullExpressionValue(string2, "requireContext().getString(R.string.sleep)");
        arrayList2.set(1, new GoalsModel(drawable2, 0, string2, i2));
        FitnessGoalAdapter fitnessGoalAdapter2 = this.z;
        if (fitnessGoalAdapter2 != null) {
            fitnessGoalAdapter2.notifyItemChanged(1);
        }
        L0(0, i2);
    }

    public final void v0() {
        FitnessBlogsModel fitnessBlogsModel = new FitnessBlogsModel("Blogs", "Blogs", "Blogs", "Blogs", "Blogs");
        ArrayList<FitnessBlogsModel> arrayList = new ArrayList<>();
        this.r = arrayList;
        arrayList.add(fitnessBlogsModel);
        this.r.add(fitnessBlogsModel);
        this.r.add(fitnessBlogsModel);
        W().rvFitnessBlogs.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        W().rvFitnessBlogs.addItemDecoration(new LinePagerIndicatorDecoration());
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.s = new FitnessBlogsAdapter(requireContext, this.r);
        W().rvFitnessBlogs.setAdapter(this.s);
    }

    public final void w0() {
        FitnessChallengeListViewModel fitnessChallengeListViewModel = this.N;
        FitnessChallengeListViewModel fitnessChallengeListViewModel2 = null;
        if (fitnessChallengeListViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
            fitnessChallengeListViewModel = null;
        }
        fitnessChallengeListViewModel.getShowEmptyLayout().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.m
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitness.x0(FragmentFitness.this, (Boolean) obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel3 = this.N;
        if (fitnessChallengeListViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
        } else {
            fitnessChallengeListViewModel2 = fitnessChallengeListViewModel3;
        }
        fitnessChallengeListViewModel2.getGetBuddiesChallengeListLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.k
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitness.y0(FragmentFitness.this, (BuddiesChallengeRes) obj);
            }
        });
    }

    public final void z0() {
        W().fitnessJourneyOngoing.tvWeekPlanStatus.setText(PlanStatus.ONGOING.name());
        ConstraintLayout constraintLayout = W().fitnessJourneyOngoing.clMain;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.fitnessJourneyOngoing.clMain");
        visible(constraintLayout);
        ConstraintLayout constraintLayout2 = W().fitnessJourney.clMain;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.fitnessJourney.clMain");
        gone(constraintLayout2);
        TextView textView = W().tvFitnessPlan;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvFitnessPlan");
        visible(textView);
        W().fitnessJourneyOngoing.tvTodayGoal.setText(requireActivity().getString(R.string.todays_goal));
        if (isAdded()) {
            K0();
            X0(this, false, 1, null);
        }
    }
}
