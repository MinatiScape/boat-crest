package com.coveiot.android.dashboard2.fragment;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.PointerIconCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails;
import com.coveiot.android.activitymodes.activities.ActivityFitnessPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.PlanStatus;
import com.coveiot.android.activitymodes.utils.SingleLiveEvent;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback;
import com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity;
import com.coveiot.android.activitymodes.workoutVideos.fragments.CultFitVideoFragment;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.ViewModelFactory;
import com.coveiot.android.dashboard2.activity.ActivityEditHomeDashboardVitals;
import com.coveiot.android.dashboard2.adapter.BestOffersAdapter;
import com.coveiot.android.dashboard2.adapter.BuddiesDashboardAdapter;
import com.coveiot.android.dashboard2.adapter.DashboardTopFeaturesViewAdapter;
import com.coveiot.android.dashboard2.adapter.DashboardVitalsGridviewAdapter;
import com.coveiot.android.dashboard2.adapter.SetupYourWatchPagerAdapter;
import com.coveiot.android.dashboard2.customui.DashboardFitnessVitalsItemDecorator;
import com.coveiot.android.dashboard2.customui.DividerItemDecoration;
import com.coveiot.android.dashboard2.customui.LinearGradientForegroundSpan;
import com.coveiot.android.dashboard2.customui.PagerContainer;
import com.coveiot.android.dashboard2.databinding.FragmentHomeBinding;
import com.coveiot.android.dashboard2.fragment.FragmentHome;
import com.coveiot.android.dashboard2.fragment.SetupYourWatchItemFragment;
import com.coveiot.android.dashboard2.listener.ConnectionStatusUpdateListener;
import com.coveiot.android.dashboard2.listener.DoMoreWithYourWatchItemClickListener;
import com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface;
import com.coveiot.android.dashboard2.listener.SyncInterruptionListener;
import com.coveiot.android.dashboard2.model.BestOffers;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchDataModel;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchFeatureType;
import com.coveiot.android.dashboard2.model.EnergyMeterData;
import com.coveiot.android.dashboard2.model.FitnessVitalData;
import com.coveiot.android.dashboard2.model.FitnessVitalsDataModel;
import com.coveiot.android.dashboard2.model.HRVData;
import com.coveiot.android.dashboard2.model.HeartRateData;
import com.coveiot.android.dashboard2.model.RespiratoryRateData;
import com.coveiot.android.dashboard2.model.SPO2Data;
import com.coveiot.android.dashboard2.model.SelectedFitnessVitalsDataModel;
import com.coveiot.android.dashboard2.model.SetupYourWatchDataModel;
import com.coveiot.android.dashboard2.model.SetupYourWatchOption;
import com.coveiot.android.dashboard2.model.State;
import com.coveiot.android.dashboard2.model.StressData;
import com.coveiot.android.dashboard2.model.SyncInterruptionType;
import com.coveiot.android.dashboard2.model.TemperatureData;
import com.coveiot.android.dashboard2.model.state.SyncState;
import com.coveiot.android.dashboard2.uihelper.FragmentHomeHelper;
import com.coveiot.android.dashboard2.util.BT3CallUtils;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.dashboard2.util.FitnessVitalsHelper;
import com.coveiot.android.dashboard2.util.SetupYourWatchDataHelper;
import com.coveiot.android.dashboard2.util.SleepDataHelper;
import com.coveiot.android.dashboard2.util.SmartGridEnums;
import com.coveiot.android.dashboard2.util.SmartGridUtilsKt;
import com.coveiot.android.dashboard2.util.StepsDataHelper;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.dashboard2.viewmodel.EnergyMeterDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel;
import com.coveiot.android.dashboard2.viewmodel.HRVDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.HeartRateDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.PromotionsViewModel;
import com.coveiot.android.dashboard2.viewmodel.RespiratoryRateDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.SPO2DataViewModel;
import com.coveiot.android.dashboard2.viewmodel.SleepDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.SmartGridViewModel;
import com.coveiot.android.dashboard2.viewmodel.StepsDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.StressDataViewModel;
import com.coveiot.android.dashboard2.viewmodel.TemperatureDataViewModel;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.dynamictab.adapters.DynamicTabAdapter;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor;
import com.coveiot.android.fitnessbuddies.fragments.viewmodels.NotificationsViewModel;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.android.fitnesschallenges.ActivityCreateChallenge;
import com.coveiot.android.fitnesschallenges.FitnessChallengeDetails;
import com.coveiot.android.fitnesschallenges.FitnessChallengesHome;
import com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeAdapter;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeRemoteConfiguration;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeCleverTapConstants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeDetailsViewModel;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel;
import com.coveiot.android.idoSdk.IDOConstants;
import com.coveiot.android.sos.utils.SOSCleverTapConstants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoVerticalBtns;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.BottomSheetThemeDialogOneButtonTitleMessage;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.databinding.ConnectedDeviceInfoCardDashboardBinding;
import com.coveiot.android.theme.databinding.ExclusiveCardBoatCoinsBinding;
import com.coveiot.android.theme.databinding.ListItemWeekPlanLayoutBinding;
import com.coveiot.android.theme.model.BindingDataModel1;
import com.coveiot.android.theme.model.BoatCoinsScreenCaller;
import com.coveiot.android.theme.model.OnWatchBT3EnabledEvent;
import com.coveiot.android.theme.model.WatchfaceScreenCaller;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.android.theme.utils.GridSpacingItemDecoration;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.weather.weather.WeatherActivity;
import com.coveiot.android.weather.weather.WeatherAppPreferenceManager;
import com.coveiot.android.weathersdk.WeatherPreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.boatcoins.model.BoatCoinsDetailsResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddiesGoal;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.GetChallengeStartNEndDateRes;
import com.coveiot.coveaccess.fitnesschallenge.model.JoinChallengeReq;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes;
import com.coveiot.coveaccess.smartgrid.model.GetSmartGridRes;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.BatteryLevelData;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.covepreferences.listener.FirebaseRemoteConfigChangeListener;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FragmentHome extends BaseFragment implements SetupYourWatchItemFragment.SetupYourWatchItemSelectedListener, FirebaseRemoteConfigChangeListener, DoMoreWithYourWatchItemClickListener, NotificationsContarctor, DashboardVitalsGridviewAdapter.ItemClickListener, SyncInterruptionListener, ConnectionStatusUpdateListener, FitnessChallengeAdapter.ChallengeClickListener, SuccessResultListener, DashboardTopFeaturesViewAdapter.TopFeatureClickListener {
    public NotificationsViewModel A;
    public boolean B;
    @NotNull
    public State G;
    @NotNull
    public State H;
    public Calendar I;
    @NotNull
    public List<FitnessVitalsDataModel> J;
    @NotNull
    public List<FitnessVitalsDataModel> K;
    @NotNull
    public List<DoMoreWithYourWatchDataModel> L;
    @Nullable
    public DashboardVitalsGridviewAdapter M;
    @NotNull
    public final FragmentHomeHelper N;
    @Nullable
    public Animation O;
    @Nullable
    public DynamicTabAdapter P;
    public boolean Q;
    public ViewModelWorkoutFeedback R;
    public boolean S;
    public FitnessChallengeAdapter T;
    public FitnessChallengeListViewModel U;
    public FitnessChallengeDetailsViewModel V;
    public boolean W;
    @Nullable
    public DashboardTopFeaturesViewAdapter X;
    public int Y;
    @Nullable
    public BottomSheetDialogTwoButtons Z;
    @Nullable
    public BottomSheetDialogImageTitleMessageTwoVerticalBtns a0;
    @NotNull
    public final SuccessResultListener b0;
    public BluetoothAdapter bluetoothAdapter;
    @Nullable
    public BottomSheetThemeDialogOneButtonTitleMessage c0;
    @Nullable
    public String d0;
    public DatePickerDialog datePickerDialog;
    @Nullable
    public String e0;
    @Nullable
    public FragmentHomeBinding n;
    public FragmentHomeViewModel o;
    public DataSyncViewModel p;
    public StepsDataViewModel q;
    public SleepDataViewModel r;
    public HeartRateDataViewModel s;
    public SmartGridViewModel smartGridViewModel;
    public SPO2DataViewModel t;
    public StressDataViewModel u;
    public HRVDataViewModel v;
    public ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard;
    public EnergyMeterDataViewModel w;
    public RespiratoryRateDataViewModel x;
    public TemperatureDataViewModel y;
    public PromotionsViewModel z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentHome";
    public final int C = 1001;
    public final int D = PointerIconCompat.TYPE_ALIAS;
    public final int E = 1011;
    public final int F = PointerIconCompat.TYPE_GRAB;

    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SetupYourWatchOption.values().length];
            try {
                iArr[SetupYourWatchOption.WATCH_FACE_STUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SetupYourWatchOption.WATCH_SETTINGS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SetupYourWatchOption.WATCH_FACE_CHANGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[SetupYourWatchOption.SPORT_LIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes4.dex */
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

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$setOngoingFitnessPlanData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Pair<EntityPreparationDay, EntityPreparationWeek> $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a0(Pair<EntityPreparationDay, EntityPreparationWeek> pair, FragmentHome fragmentHome, Continuation<? super a0> continuation) {
            super(2, continuation);
            this.$it = pair;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a0(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$it != null) {
                    this.this$0.q0().setIsFitnessPlanOngoing(Boxing.boxBoolean(true));
                    this.this$0.q0().doMoreWithYourWatchCardContainer.setShowFitnessPlan(Boxing.boxBoolean(false));
                    this.this$0.q0().fitnessJourneyOngoing.tvTodayGoalName.setText(this.this$0.getViewModelCurrentPlanDashboard().getActivitiesString(this.$it.getFirst()));
                    String lowerCase = this.this$0.getViewModelCurrentPlanDashboard().getActivitiesString(this.$it.getFirst()).toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                    if (lowerCase.equals("rest day")) {
                        this.this$0.q0().fitnessJourneyOngoing.ivTodayGoal.setImageResource(R.drawable.ic_rest_icon);
                        FragmentHome fragmentHome = this.this$0;
                        ProgressBar progressBar = fragmentHome.q0().fitnessJourneyOngoing.todayGoalProgress;
                        Intrinsics.checkNotNullExpressionValue(progressBar, "binding.fitnessJourneyOngoing.todayGoalProgress");
                        fragmentHome.gone(progressBar);
                        FragmentHome fragmentHome2 = this.this$0;
                        TextView textView = fragmentHome2.q0().fitnessJourneyOngoing.tvTodayGoalTotalValue;
                        Intrinsics.checkNotNullExpressionValue(textView, "binding.fitnessJourneyOn…ing.tvTodayGoalTotalValue");
                        fragmentHome2.gone(textView);
                    } else {
                        this.this$0.q0().fitnessJourneyOngoing.ivTodayGoal.setImageResource(R.drawable.ic_walk_circular_background_grey);
                        FragmentHome fragmentHome3 = this.this$0;
                        ProgressBar progressBar2 = fragmentHome3.q0().fitnessJourneyOngoing.todayGoalProgress;
                        Intrinsics.checkNotNullExpressionValue(progressBar2, "binding.fitnessJourneyOngoing.todayGoalProgress");
                        fragmentHome3.visible(progressBar2);
                        FragmentHome fragmentHome4 = this.this$0;
                        TextView textView2 = fragmentHome4.q0().fitnessJourneyOngoing.tvTodayGoalTotalValue;
                        Intrinsics.checkNotNullExpressionValue(textView2, "binding.fitnessJourneyOn…ing.tvTodayGoalTotalValue");
                        fragmentHome4.visible(textView2);
                    }
                    this.this$0.q0().fitnessJourneyOngoing.tvWeekPlanStatus.setText(PlanStatus.ONGOING.name());
                    TextView textView3 = this.this$0.q0().fitnessJourneyOngoing.tvWeekPlanName;
                    textView3.setText(this.$it.getSecond().getName() + ' ' + this.this$0.getString(com.coveiot.android.activitymodes.R.string.dash_day) + ' ' + this.$it.getFirst().getDay_number());
                    this.this$0.q0().fitnessJourneyOngoing.clCompleted.setVisibility(8);
                    FragmentHome.V2(this.this$0, false, 1, null);
                } else {
                    this.this$0.q0().setIsFitnessPlanOngoing(Boxing.boxBoolean(false));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$doMoreWithYourWatchItemClicked$1", f = "FragmentHome.kt", i = {}, l = {3068}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* loaded from: classes4.dex */
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

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentHome.this.getContext() != null) {
                    PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                    Context requireContext = FragmentHome.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    this.label = 1;
                    obj = companion.getInstance(requireContext).doesPlanExists(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                FragmentHome.this.G1();
                return Unit.INSTANCE;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            if (((Boolean) obj).booleanValue()) {
                Context requireContext2 = FragmentHome.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                a aVar = a.INSTANCE;
                Intent intent = new Intent(requireContext2, ActivityFitnessPlan.class);
                aVar.invoke((a) intent);
                requireContext2.startActivity(intent, null);
                return Unit.INSTANCE;
            }
            FragmentHome.this.G1();
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$setOngoingFitnessPlanData$2", f = "FragmentHome.kt", i = {}, l = {1528}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class b0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b0(Continuation<? super b0> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b0(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = FragmentHome.this.getViewModelCurrentPlanDashboard();
                boolean z = FragmentHome.this.S;
                this.label = 1;
                if (viewModelCurrentPlanDashboard.getOngoingPlanData(z, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$endCurrentPlan$1", f = "FragmentHome.kt", i = {1, 2, 2, 2}, l = {1350, 1354, 1358}, m = "invokeSuspend", n = {"currentPlan", "currentPlan", "totalActivitiesDistance", "dayRange"}, s = {"L$0", "L$0", "L$1", "L$2"})
    /* loaded from: classes4.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* loaded from: classes4.dex */
        public static final class a extends Lambda implements Function1<FitnessPlanTemplateRes.FitnessPlanTemplateData, Unit> {
            public final /* synthetic */ EntityPreparationPlan $currentPlan;
            public final /* synthetic */ ArrayList<String> $dayRange;
            public final /* synthetic */ Pair<Integer, Integer> $totalActivitiesDistance;
            public final /* synthetic */ FragmentHome this$0;

            @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$endCurrentPlan$1$1$1", f = "FragmentHome.kt", i = {0, 0, 1}, l = {1400, 1412}, m = "invokeSuspend", n = {"weekCoveredDistance", "distanceValueJob", "weekCoveredDistance"}, s = {"L$0", "L$1", "L$0"})
            /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes4.dex */
            public static final class C0254a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ EntityPreparationPlan $currentPlan;
                public final /* synthetic */ ArrayList<String> $dayRange;
                public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData $template;
                public final /* synthetic */ Pair<Integer, Integer> $totalActivitiesDistance;
                public Object L$0;
                public Object L$1;
                public int label;
                public final /* synthetic */ FragmentHome this$0;

                @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$endCurrentPlan$1$1$1$distanceValueJob$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$c$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes4.dex */
                public static final class C0255a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ArrayList<String> $dayRange;
                    public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData $template;
                    public final /* synthetic */ Ref.IntRef $weekCoveredDistance;
                    public int label;
                    public final /* synthetic */ FragmentHome this$0;

                    /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$c$a$a$a$a  reason: collision with other inner class name */
                    /* loaded from: classes4.dex */
                    public static final class C0256a extends Lambda implements Function1<Integer, Unit> {
                        public final /* synthetic */ Ref.IntRef $weekCoveredDistance;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C0256a(Ref.IntRef intRef) {
                            super(1);
                            this.$weekCoveredDistance = intRef;
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i) {
                            this.$weekCoveredDistance.element += i;
                        }
                    }

                    /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$c$a$a$a$b */
                    /* loaded from: classes4.dex */
                    public static final class b extends Lambda implements Function1<Integer, Unit> {
                        public final /* synthetic */ Ref.IntRef $weekCoveredDistance;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public b(Ref.IntRef intRef) {
                            super(1);
                            this.$weekCoveredDistance = intRef;
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i) {
                            this.$weekCoveredDistance.element += i;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0255a(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData, FragmentHome fragmentHome, ArrayList<String> arrayList, Ref.IntRef intRef, Continuation<? super C0255a> continuation) {
                        super(2, continuation);
                        this.$template = fitnessPlanTemplateData;
                        this.this$0 = fragmentHome;
                        this.$dayRange = arrayList;
                        this.$weekCoveredDistance = intRef;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new C0255a(this.$template, this.this$0, this.$dayRange, this.$weekCoveredDistance, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((C0255a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        ArrayList<String> arrayList;
                        ArrayList<String> arrayList2;
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            for (FitnessPlanTemplateRes.FitnessPlanTemplateData.ActivityMapping activityMapping : this.$template.getActivityMappings()) {
                                if (UserDataManager.getInstance(this.this$0.requireContext()).isOneKSupported()) {
                                    for (String code : activityMapping.getPhysicalActivityCodes()) {
                                        ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.this$0.R;
                                        if (viewModelWorkoutFeedback == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
                                            viewModelWorkoutFeedback = null;
                                        }
                                        Intrinsics.checkNotNullExpressionValue(code, "code");
                                        String str = this.$dayRange.get(0);
                                        Intrinsics.checkNotNullExpressionValue(str, "dayRange[0]");
                                        String str2 = this.$dayRange.get(arrayList.size() - 1);
                                        Intrinsics.checkNotNullExpressionValue(str2, "dayRange[dayRange.size - 1]");
                                        viewModelWorkoutFeedback.getAllWeekDistanceValueByActivityNCategoryIDs(code, str, str2, new C0256a(this.$weekCoveredDistance));
                                    }
                                } else {
                                    for (String type : activityMapping.getSessionTypes()) {
                                        ViewModelWorkoutFeedback viewModelWorkoutFeedback2 = this.this$0.R;
                                        if (viewModelWorkoutFeedback2 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
                                            viewModelWorkoutFeedback2 = null;
                                        }
                                        String str3 = this.$dayRange.get(0);
                                        Intrinsics.checkNotNullExpressionValue(str3, "dayRange[0]");
                                        String str4 = this.$dayRange.get(arrayList2.size() - 1);
                                        Intrinsics.checkNotNullExpressionValue(str4, "dayRange[dayRange.size - 1]");
                                        Intrinsics.checkNotNullExpressionValue(type, "type");
                                        viewModelWorkoutFeedback2.getAllWeeksDistanceValueByActivityType(str3, str4, type, new b(this.$weekCoveredDistance));
                                    }
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0254a(FragmentHome fragmentHome, FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData, Pair<Integer, Integer> pair, ArrayList<String> arrayList, EntityPreparationPlan entityPreparationPlan, Continuation<? super C0254a> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentHome;
                    this.$template = fitnessPlanTemplateData;
                    this.$totalActivitiesDistance = pair;
                    this.$dayRange = arrayList;
                    this.$currentPlan = entityPreparationPlan;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0254a(this.this$0, this.$template, this.$totalActivitiesDistance, this.$dayRange, this.$currentPlan, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0254a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:20:0x007e  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r15) {
                    /*
                        Method dump skipped, instructions count: 271
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.fragment.FragmentHome.c.a.C0254a.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentHome fragmentHome, Pair<Integer, Integer> pair, ArrayList<String> arrayList, EntityPreparationPlan entityPreparationPlan) {
                super(1);
                this.this$0 = fragmentHome;
                this.$totalActivitiesDistance = pair;
                this.$dayRange = arrayList;
                this.$currentPlan = entityPreparationPlan;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
                invoke2(fitnessPlanTemplateData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull FitnessPlanTemplateRes.FitnessPlanTemplateData template) {
                Intrinsics.checkNotNullParameter(template, "template");
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new C0254a(this.this$0, template, this.$totalActivitiesDistance, this.$dayRange, this.$currentPlan, null), 2, null);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$endCurrentPlan$1$currentPlan$1", f = "FragmentHome.kt", i = {}, l = {1351}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
            public int label;
            public final /* synthetic */ FragmentHome this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(FragmentHome fragmentHome, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = fragmentHome;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationPlan> continuation) {
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

        @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$endCurrentPlan$1$totalActivitiesDistance$1", f = "FragmentHome.kt", i = {}, l = {1355}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$c$c  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public static final class C0257c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Integer, ? extends Integer>>, Object> {
            public int label;
            public final /* synthetic */ FragmentHome this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0257c(FragmentHome fragmentHome, Continuation<? super C0257c> continuation) {
                super(2, continuation);
                this.this$0 = fragmentHome;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0257c(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Integer, ? extends Integer>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super Pair<Integer, Integer>>) continuation);
            }

            @Nullable
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Pair<Integer, Integer>> continuation) {
                return ((C0257c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                    obj = viewModelCurrentPlanDashboard.getTotalActivitiesAndDistance(this);
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

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x008c A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x008d  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0096  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00c8  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
            /*
                Method dump skipped, instructions count: 230
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.fragment.FragmentHome.c.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$updateBT3ConnectionState$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class c0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isEnabled;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c0(boolean z, Continuation<? super c0> continuation) {
            super(2, continuation);
            this.$isEnabled = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c0(this.$isEnabled, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentHome.this.B) {
                    BindingDataModel1 bt3ConnectionState = FragmentHome.this.q0().getBt3ConnectionState();
                    if (bt3ConnectionState != null) {
                        bt3ConnectionState.setVisible(true);
                    }
                    if (this.$isEnabled) {
                        BleApi bleApi = BleApiManager.getInstance(FragmentHome.this.requireActivity()).getBleApi();
                        if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                            FragmentHome.this.W = true;
                            FragmentHome fragmentHome = FragmentHome.this;
                            fragmentHome.T1(fragmentHome.W);
                            FragmentHome.this.q0().setBt3ConnectionState(new BindingDataModel1(FragmentHome.this.B, FragmentHome.this.getString(R.string.bt_calling), FragmentHome.this.getString(R.string.connected), FragmentHome.this.requireActivity().getDrawable(R.drawable.ic_bt3_connected)));
                        }
                    }
                    FragmentHome.this.W = false;
                    FragmentHome fragmentHome2 = FragmentHome.this;
                    fragmentHome2.T1(fragmentHome2.W);
                    FragmentHome.this.q0().setBt3ConnectionState(new BindingDataModel1(FragmentHome.this.B, FragmentHome.this.getString(R.string.bt_calling), FragmentHome.this.getString(R.string.disconnected), FragmentHome.this.requireActivity().getDrawable(R.drawable.ic_bt3_disconnected)));
                } else {
                    BindingDataModel1 bt3ConnectionState2 = FragmentHome.this.q0().getBt3ConnectionState();
                    if (bt3ConnectionState2 != null) {
                        bt3ConnectionState2.setVisible(false);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$initBoatCoinsData$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            String coinsCardImage;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentHome.this.isAdded() && (coinsCardImage = SessionManager.getInstance(FragmentHome.this.requireContext()).getCoinsCardImage()) != null) {
                    Glide.with(FragmentHome.this.requireContext()).m30load(coinsCardImage).into(FragmentHome.this.q0().boatCoinsLayout.ivIcon);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$updateFitnessOverviewUI$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class d0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ StepsDataModel $stepsDataModel;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d0(StepsDataModel stepsDataModel, Continuation<? super d0> continuation) {
            super(2, continuation);
            this.$stepsDataModel = stepsDataModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d0(this.$stepsDataModel, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentHome.this.q0().setStepsDataModel(this.$stepsDataModel);
                FragmentHomeBinding q0 = FragmentHome.this.q0();
                StepsDataHelper stepsDataHelper = StepsDataHelper.INSTANCE;
                FragmentActivity requireActivity = FragmentHome.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                q0.setDistanceData(stepsDataHelper.getDistanceSmallHealthCardInfo(requireActivity, this.$stepsDataModel.getDistance()));
                FragmentHomeBinding q02 = FragmentHome.this.q0();
                FragmentActivity requireActivity2 = FragmentHome.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                Double calories = this.$stepsDataModel.getCalories();
                Intrinsics.checkNotNullExpressionValue(calories, "stepsDataModel.calories");
                q02.setCalorieData(stepsDataHelper.getCalorieSmallHealthCardInfo(requireActivity2, calories.doubleValue()));
                FragmentHome.this.Y2(Boxing.boxInt(this.$stepsDataModel.getSteps()));
                FragmentHome.this.Z2(this.$stepsDataModel.getSteps());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$initBoatCoinsData$2", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$initBoatCoinsData$2$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ BoatCoinsDetailsResponse $it;
            public int label;
            public final /* synthetic */ FragmentHome this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentHome fragmentHome, BoatCoinsDetailsResponse boatCoinsDetailsResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentHome;
                this.$it = boatCoinsDetailsResponse;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$it, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                BoatCoinsDetailsResponse.DataBean.CoinsProfile coinsProfile;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextView textView = this.this$0.q0().toolbar.boatCoinsValue;
                    BoatCoinsDetailsResponse.DataBean data = this.$it.getData();
                    Integer coinsBalance = (data == null || (coinsProfile = data.getCoinsProfile()) == null) ? null : coinsProfile.getCoinsBalance();
                    textView.setText(String.valueOf(coinsBalance == null ? 0 : coinsBalance.intValue()));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public e(Continuation<? super e> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(FragmentHome fragmentHome, BoatCoinsDetailsResponse boatCoinsDetailsResponse) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentHome), Dispatchers.getMain(), null, new a(fragmentHome, boatCoinsDetailsResponse, null), 2, null);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentHome.this.isAdded()) {
                    FragmentHomeViewModel fragmentHomeViewModel = FragmentHome.this.o;
                    if (fragmentHomeViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                        fragmentHomeViewModel = null;
                    }
                    MutableLiveData<BoatCoinsDetailsResponse> getBoatCoinsDetails = fragmentHomeViewModel.getGetBoatCoinsDetails();
                    LifecycleOwner viewLifecycleOwner = FragmentHome.this.getViewLifecycleOwner();
                    final FragmentHome fragmentHome = FragmentHome.this;
                    getBoatCoinsDetails.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.dashboard2.fragment.c1
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj2) {
                            FragmentHome.e.invokeSuspend$lambda$0(FragmentHome.this, (BoatCoinsDetailsResponse) obj2);
                        }
                    });
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$updatePlanProgressUi$1$1", f = "FragmentHome.kt", i = {0}, l = {1546}, m = "invokeSuspend", n = {"planStartDate"}, s = {"L$0"})
    /* loaded from: classes4.dex */
    public static final class e0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EntityPreparationPlan $it;
        public Object L$0;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e0(EntityPreparationPlan entityPreparationPlan, FragmentHome fragmentHome, Continuation<? super e0> continuation) {
            super(2, continuation);
            this.$it = entityPreparationPlan;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e0(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((e0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            String str;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String formatDate = AppUtils.formatDate(AppUtils.parseDate(this.$it.getStartDate(), "yyyy-MM-dd"), "dd MMM yy");
                PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                Context requireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String planId = this.$it.getPlanId();
                this.L$0 = formatDate;
                this.label = 1;
                Object planEndDate = companion.getInstance(requireContext).getPlanEndDate(planId, this);
                if (planEndDate == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str = formatDate;
                obj = planEndDate;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                str = (String) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            String str2 = (String) obj;
            if (str2 != null) {
                String formatDate2 = AppUtils.formatDate(AppUtils.parseDate(str2, "yyyy-MM-dd"), "dd MMM yy");
                TextView textView = this.this$0.q0().fitnessJourneyOngoing.textView;
                textView.setText(str + " - " + formatDate2);
            }
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes4.dex */
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
            launchActivity.putExtra(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SOURCE.getValue(), CleverTapConstants.CustomEventValues.HP_BANNER.getValue());
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$updatePlanProgressUi$2", f = "FragmentHome.kt", i = {}, l = {1567, 1571}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class f0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$updatePlanProgressUi$2$dayData$1", f = "FragmentHome.kt", i = {}, l = {1568}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationDay>, Object> {
            public int label;
            public final /* synthetic */ FragmentHome this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentHome fragmentHome, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentHome;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationDay> continuation) {
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
                    boolean z = this.this$0.S;
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

        public f0(Continuation<? super f0> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f0(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((f0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            ViewModelWorkoutFeedback viewModelWorkoutFeedback = null;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                a aVar = new a(FragmentHome.this, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, aVar, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            EntityPreparationDay entityPreparationDay = (EntityPreparationDay) obj;
            ViewModelWorkoutFeedback viewModelWorkoutFeedback2 = FragmentHome.this.R;
            if (viewModelWorkoutFeedback2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
            } else {
                viewModelWorkoutFeedback = viewModelWorkoutFeedback2;
            }
            viewModelWorkoutFeedback.dailyDistanceCalculation(entityPreparationDay);
            ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = FragmentHome.this.getViewModelCurrentPlanDashboard();
            this.label = 2;
            if (viewModelCurrentPlanDashboard.getCurrentPlan(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$initOneK$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentHome.this.isAdded()) {
                    FragmentHome.this.q0().setShow1kFtu(Boxing.boxBoolean(UserDataManager.getInstance(FragmentHome.this.requireActivity()).isOneKSupported()));
                    if (UserDataManager.getInstance(FragmentHome.this.requireActivity()).isOneKSupported()) {
                        FragmentHome fragmentHome = FragmentHome.this;
                        TextView textView = fragmentHome.q0().tvboatExclusiveHeader;
                        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvboatExclusiveHeader");
                        fragmentHome.visible(textView);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$updateSleepDataUI$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class g0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $duration;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g0(int i, Continuation<? super g0> continuation) {
            super(2, continuation);
            this.$duration = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g0(this.$duration, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((g0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentHomeBinding q0 = FragmentHome.this.q0();
                SleepDataHelper sleepDataHelper = SleepDataHelper.INSTANCE;
                FragmentActivity requireActivity = FragmentHome.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                q0.setSleepData(sleepDataHelper.getSleepSmallHealthCardInfo(requireActivity, this.$duration));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$initSetupYourWatchData$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ List<SetupYourWatchDataModel> $it;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(List<SetupYourWatchDataModel> list, Continuation<? super h> continuation) {
            super(2, continuation);
            this.$it = list;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$lambda$0(FragmentHome fragmentHome, View view, MotionEvent motionEvent) {
            fragmentHome.q0().swipeContainer.setEnabled(false);
            if (motionEvent.getAction() == 1) {
                fragmentHome.q0().swipeContainer.setEnabled(true);
            }
            return false;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new h(this.$it, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((h) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                SetupYourWatchDataHelper setupYourWatchDataHelper = SetupYourWatchDataHelper.INSTANCE;
                Context requireContext = FragmentHome.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                boolean isDiySupported = setupYourWatchDataHelper.isDiySupported(requireContext);
                FragmentHome fragmentHome = FragmentHome.this;
                TextView textView = fragmentHome.q0().tvSetupYourWatchHeader;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.tvSetupYourWatchHeader");
                fragmentHome.gone(textView);
                List<SetupYourWatchDataModel> list = this.$it;
                if (list == null || list.isEmpty()) {
                    FragmentHome.this.q0().setSetupYourWatchItemCount(Boxing.boxInt(0));
                    FragmentHome.this.q0().setIsSetupYourWatchSettingsCompleted(Boxing.boxBoolean(true));
                    FragmentHome.this.q0().setShowWatchfaceBigLayout(Boxing.boxBoolean(false));
                    if (!isDiySupported) {
                        FragmentHome.this.q0().setShowWatchfaceStudioBigLayout(Boxing.boxBoolean(false));
                        FragmentHome.this.q0().setShowWatchfaceStudioBigLayoutTop(Boxing.boxBoolean(false));
                    } else {
                        Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
                        FragmentActivity requireActivity = FragmentHome.this.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        if (companion.getInstance(requireActivity).isUserTappedWatchfaceStudioInDashboard()) {
                            FragmentHome.this.q0().setShowWatchfaceStudioBigLayoutTop(Boxing.boxBoolean(false));
                            FragmentHome.this.q0().setShowWatchfaceStudioBigLayout(Boxing.boxBoolean(true));
                            FragmentHome fragmentHome2 = FragmentHome.this;
                            TextView textView2 = fragmentHome2.q0().tvboatExclusiveHeader;
                            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvboatExclusiveHeader");
                            fragmentHome2.visible(textView2);
                        } else {
                            FragmentHome.this.q0().setShowWatchfaceStudioBigLayoutTop(Boxing.boxBoolean(true));
                            FragmentHome.this.q0().setShowWatchfaceStudioBigLayout(Boxing.boxBoolean(false));
                            FragmentHome fragmentHome3 = FragmentHome.this;
                            TextView textView3 = fragmentHome3.q0().tvSetupYourWatchHeader;
                            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvSetupYourWatchHeader");
                            fragmentHome3.visible(textView3);
                        }
                    }
                    FragmentHome.this.q0().setShowWatchSettingsBigLayout(Boxing.boxBoolean(false));
                } else {
                    FragmentHome fragmentHome4 = FragmentHome.this;
                    TextView textView4 = fragmentHome4.q0().tvSetupYourWatchHeader;
                    Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvSetupYourWatchHeader");
                    fragmentHome4.visible(textView4);
                    boolean z = false;
                    boolean z2 = false;
                    boolean z3 = false;
                    for (SetupYourWatchDataModel setupYourWatchDataModel : this.$it) {
                        if (setupYourWatchDataModel.getOption() != null && Intrinsics.areEqual(setupYourWatchDataModel.getOption(), "WATCH_FACE_STUDIO")) {
                            z2 = true;
                        } else if (setupYourWatchDataModel.getOption() != null && Intrinsics.areEqual(setupYourWatchDataModel.getOption(), "WATCH_SETTINGS")) {
                            z = true;
                        } else if (setupYourWatchDataModel.getOption() != null && Intrinsics.areEqual(setupYourWatchDataModel.getOption(), "WATCH_FACE_CHANGE")) {
                            z3 = true;
                        }
                    }
                    if (this.$it.size() == 1) {
                        FragmentHome.this.q0().setIsSetupYourWatchSettingsCompleted(Boxing.boxBoolean(true));
                        FragmentHome.this.q0().setSetupYourWatchItemCount(Boxing.boxInt(0));
                        if (z) {
                            FragmentHome.this.q0().setShowWatchSettingsBigLayout(Boxing.boxBoolean(true));
                            FragmentHome.this.q0().setShowWatchfaceStudioBigLayoutTop(Boxing.boxBoolean(false));
                            FragmentHome.this.q0().setShowWatchfaceStudioBigLayout(Boxing.boxBoolean(isDiySupported));
                            if (isDiySupported) {
                                FragmentHome fragmentHome5 = FragmentHome.this;
                                TextView textView5 = fragmentHome5.q0().tvboatExclusiveHeader;
                                Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvboatExclusiveHeader");
                                fragmentHome5.visible(textView5);
                            }
                            FragmentHome.this.q0().setShowWatchfaceBigLayout(Boxing.boxBoolean(false));
                        } else if (z2) {
                            FragmentHome.this.q0().setShowWatchSettingsBigLayout(Boxing.boxBoolean(false));
                            FragmentHome.this.q0().setShowWatchfaceBigLayout(Boxing.boxBoolean(false));
                            Dashboard2PreferenceManager.Companion companion2 = Dashboard2PreferenceManager.Companion;
                            FragmentActivity requireActivity2 = FragmentHome.this.requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                            if (companion2.getInstance(requireActivity2).isUserTappedWatchfaceStudioInDashboard()) {
                                FragmentHome.this.q0().setShowWatchfaceStudioBigLayoutTop(Boxing.boxBoolean(false));
                                FragmentHome.this.q0().setShowWatchfaceStudioBigLayout(Boxing.boxBoolean(true));
                                FragmentHome fragmentHome6 = FragmentHome.this;
                                TextView textView6 = fragmentHome6.q0().tvboatExclusiveHeader;
                                Intrinsics.checkNotNullExpressionValue(textView6, "binding.tvboatExclusiveHeader");
                                fragmentHome6.visible(textView6);
                            } else {
                                FragmentHome.this.q0().setShowWatchfaceStudioBigLayoutTop(Boxing.boxBoolean(true));
                                FragmentHome.this.q0().setShowWatchfaceStudioBigLayout(Boxing.boxBoolean(false));
                            }
                        } else if (z3) {
                            FragmentHome.this.q0().setShowWatchSettingsBigLayout(Boxing.boxBoolean(false));
                            FragmentHome.this.q0().setShowWatchfaceStudioBigLayoutTop(Boxing.boxBoolean(false));
                            FragmentHome.this.q0().setShowWatchfaceBigLayout(Boxing.boxBoolean(true));
                            FragmentHome.this.q0().setShowWatchfaceStudioBigLayout(Boxing.boxBoolean(false));
                        }
                    } else {
                        FragmentHome.this.q0().setShowWatchSettingsBigLayout(Boxing.boxBoolean(false));
                        FragmentHome.this.q0().setShowWatchfaceStudioBigLayout(Boxing.boxBoolean(false));
                        FragmentHome.this.q0().setShowWatchfaceStudioBigLayoutTop(Boxing.boxBoolean(false));
                        FragmentHome.this.q0().setShowWatchfaceBigLayout(Boxing.boxBoolean(false));
                        FragmentHome.this.q0().setSetupYourWatchItemCount(Boxing.boxInt(this.$it.size()));
                        FragmentHome.this.q0().setIsSetupYourWatchSettingsCompleted(Boxing.boxBoolean(false));
                        PagerContainer pagerContainer = FragmentHome.this.q0().setYourWatchPager;
                        Intrinsics.checkNotNullExpressionValue(pagerContainer, "binding.setYourWatchPager");
                        final FragmentHome fragmentHome7 = FragmentHome.this;
                        pagerContainer.setOnTouchListener(new View.OnTouchListener() { // from class: com.coveiot.android.dashboard2.fragment.d1
                            @Override // android.view.View.OnTouchListener
                            public final boolean onTouch(View view, MotionEvent motionEvent) {
                                boolean invokeSuspend$lambda$0;
                                invokeSuspend$lambda$0 = FragmentHome.h.invokeSuspend$lambda$0(FragmentHome.this, view, motionEvent);
                                return invokeSuspend$lambda$0;
                            }
                        });
                        FragmentActivity requireActivity3 = FragmentHome.this.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
                        FragmentManager childFragmentManager = FragmentHome.this.getChildFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                        pagerContainer.setAdapter(new SetupYourWatchPagerAdapter(requireActivity3, childFragmentManager, this.$it, FragmentHome.this));
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes4.dex */
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
            launchActivity.putExtra("isPlanHistory", true);
        }
    }

    /* loaded from: classes4.dex */
    public static final class j extends Lambda implements Function1<Intent, Unit> {
        public static final j INSTANCE = new j();

        public j() {
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

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$initiateNetworkCalls$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class k extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                SessionManager.getInstance(FragmentHome.this.requireContext()).registerFirebaseRemoteConfigChangeListener(FragmentHome.this);
                FragmentHome.this.o0();
                FragmentHome.this.h0();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes4.dex */
    public static final class l extends Lambda implements Function1<Intent, Unit> {
        public static final l INSTANCE = new l();

        public l() {
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

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$navigateToFitnessVitals$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class m extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $fitnessVitalsType;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m(String str, Continuation<? super m> continuation) {
            super(2, continuation);
            this.$fitnessVitalsType = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new m(this.$fitnessVitalsType, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((m) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentHome.this.requireActivity() instanceof FragmentHomeCallBackInterface) {
                    FragmentActivity requireActivity = FragmentHome.this.requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                    ((FragmentHomeCallBackInterface) requireActivity).navigateToFitnessVital(this.$fitnessVitalsType);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerBatteryLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class n extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Integer $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public n(Integer num, FragmentHome fragmentHome, Continuation<? super n> continuation) {
            super(2, continuation);
            this.$it = num;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new n(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((n) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Integer it = this.$it;
                if (it != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    if (it.intValue() > 0) {
                        Integer it2 = this.$it;
                        Intrinsics.checkNotNullExpressionValue(it2, "it");
                        if (it2.intValue() <= 100) {
                            TextView textView = this.this$0.q0().syncStatusLayout.tvBatteryLevel;
                            StringBuilder sb = new StringBuilder();
                            sb.append(this.$it);
                            sb.append('%');
                            textView.setText(sb.toString());
                            FragmentHome fragmentHome = this.this$0;
                            Integer it3 = this.$it;
                            Intrinsics.checkNotNullExpressionValue(it3, "it");
                            fragmentHome.p2(it3.intValue());
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerDoMoreWithYourWatchLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class o extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ List<DoMoreWithYourWatchDataModel> $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public o(List<DoMoreWithYourWatchDataModel> list, FragmentHome fragmentHome, Continuation<? super o> continuation) {
            super(2, continuation);
            this.$it = list;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new o(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((o) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<DoMoreWithYourWatchDataModel> list = this.$it;
                boolean z = false;
                if (list != null) {
                    if (!(list == null || list.isEmpty())) {
                        this.this$0.getDoMoreWithYourWatchGridDataModels().clear();
                        List<DoMoreWithYourWatchDataModel> doMoreWithYourWatchGridDataModels = this.this$0.getDoMoreWithYourWatchGridDataModels();
                        if (doMoreWithYourWatchGridDataModels == null || doMoreWithYourWatchGridDataModels.isEmpty()) {
                            z = true;
                        }
                        if (z) {
                            this.this$0.q0().setDoMoreWithYourWatchItemCount(Boxing.boxInt(this.$it.size()));
                            FragmentHome fragmentHome = this.this$0;
                            List<DoMoreWithYourWatchDataModel> it = this.$it;
                            Intrinsics.checkNotNullExpressionValue(it, "it");
                            fragmentHome.setDoMoreWithYourWatchGridDataModels(CollectionsKt___CollectionsKt.toMutableList((Collection) it));
                            FragmentHomeHelper fragmentHomeHelper = this.this$0.getFragmentHomeHelper();
                            if (fragmentHomeHelper != null) {
                                List<DoMoreWithYourWatchDataModel> doMoreWithYourWatchGridDataModels2 = this.this$0.getDoMoreWithYourWatchGridDataModels();
                                FragmentHomeBinding q0 = this.this$0.q0();
                                FragmentActivity requireActivity = this.this$0.requireActivity();
                                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                                fragmentHomeHelper.populateDoMoreWithYourWatchData(doMoreWithYourWatchGridDataModels2, q0, requireActivity, this.this$0);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }
                this.this$0.q0().setDoMoreWithYourWatchItemCount(Boxing.boxInt(0));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerEnergyMeterLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class p extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EnergyMeterData $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p(EnergyMeterData energyMeterData, FragmentHome fragmentHome, Continuation<? super p> continuation) {
            super(2, continuation);
            this.$it = energyMeterData;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new p(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((p) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [com.coveiot.android.dashboard2.model.EnergyMeterData, T] */
        /* JADX WARN: Type inference failed for: r1v26, types: [com.coveiot.android.dashboard2.model.EnergyMeterData, T] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = this.$it;
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                if (objectRef.element == 0) {
                    objectRef.element = new EnergyMeterData(0, 0L, 3, null);
                }
                boolean z = true;
                if (((EnergyMeterData) objectRef.element).getValue() > 0) {
                    booleanRef.element = true;
                }
                int i = 0;
                if (this.this$0.q0().healthVitalsCardContainer.clFirstFitnessVitalsCard.getVisibility() == 0) {
                    String firstCardType = this.this$0.q0().healthVitalsCardContainer.getFirstCardType();
                    if (firstCardType != null && firstCardType.length() != 0) {
                        z = false;
                    }
                    if (!z && Intrinsics.areEqual(this.this$0.q0().healthVitalsCardContainer.getFirstCardType(), "ENERGY_METER")) {
                        FragmentHomeHelper fragmentHomeHelper = this.this$0.getFragmentHomeHelper();
                        FragmentHomeBinding q0 = this.this$0.q0();
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        fragmentHomeHelper.updateFirstVitalCard(q0, requireActivity, booleanRef.element, (FitnessVitalData) objectRef.element, this.this$0);
                        return Unit.INSTANCE;
                    }
                }
                List<FitnessVitalsDataModel> selectedFitnessVitalsGridDataModels = this.this$0.getSelectedFitnessVitalsGridDataModels();
                FragmentHome fragmentHome = this.this$0;
                for (Object obj2 : selectedFitnessVitalsGridDataModels) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    FitnessVitalsDataModel fitnessVitalsDataModel = (FitnessVitalsDataModel) obj2;
                    if (Intrinsics.areEqual(fitnessVitalsDataModel.getFitnessVitalType(), "ENERGY_METER")) {
                        fitnessVitalsDataModel.setFitnessData((FitnessVitalData) objectRef.element);
                        fitnessVitalsDataModel.setDataAvailable(booleanRef.element);
                        RecyclerView.Adapter adapter = fragmentHome.q0().healthVitalsCardContainer.rvVitals.getAdapter();
                        if (adapter != null) {
                            adapter.notifyItemChanged(i);
                        }
                    }
                    i = i2;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerHRVLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class q extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ HRVData $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q(HRVData hRVData, FragmentHome fragmentHome, Continuation<? super q> continuation) {
            super(2, continuation);
            this.$it = hRVData;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new q(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((q) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [com.coveiot.android.dashboard2.model.HRVData, T] */
        /* JADX WARN: Type inference failed for: r1v27, types: [com.coveiot.android.dashboard2.model.HRVData, T] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = this.$it;
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                if (objectRef.element == 0) {
                    objectRef.element = new HRVData(0.0d, 0L, 3, null);
                }
                boolean z = true;
                if (((HRVData) objectRef.element).getValue() > 0.0d) {
                    booleanRef.element = true;
                }
                int i = 0;
                if (this.this$0.q0().healthVitalsCardContainer.clFirstFitnessVitalsCard.getVisibility() == 0) {
                    String firstCardType = this.this$0.q0().healthVitalsCardContainer.getFirstCardType();
                    if (firstCardType != null && firstCardType.length() != 0) {
                        z = false;
                    }
                    if (!z && Intrinsics.areEqual(this.this$0.q0().healthVitalsCardContainer.getFirstCardType(), "HRV")) {
                        FragmentHomeHelper fragmentHomeHelper = this.this$0.getFragmentHomeHelper();
                        FragmentHomeBinding q0 = this.this$0.q0();
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        fragmentHomeHelper.updateFirstVitalCard(q0, requireActivity, booleanRef.element, (FitnessVitalData) objectRef.element, this.this$0);
                        return Unit.INSTANCE;
                    }
                }
                List<FitnessVitalsDataModel> selectedFitnessVitalsGridDataModels = this.this$0.getSelectedFitnessVitalsGridDataModels();
                FragmentHome fragmentHome = this.this$0;
                for (Object obj2 : selectedFitnessVitalsGridDataModels) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    FitnessVitalsDataModel fitnessVitalsDataModel = (FitnessVitalsDataModel) obj2;
                    if (Intrinsics.areEqual(fitnessVitalsDataModel.getFitnessVitalType(), "HRV")) {
                        fitnessVitalsDataModel.setFitnessData((FitnessVitalData) objectRef.element);
                        fitnessVitalsDataModel.setDataAvailable(booleanRef.element);
                        RecyclerView.Adapter adapter = fragmentHome.q0().healthVitalsCardContainer.rvVitals.getAdapter();
                        if (adapter != null) {
                            adapter.notifyItemChanged(i);
                        }
                    }
                    i = i2;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerHeartRateLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class r extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ HeartRateData $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public r(HeartRateData heartRateData, FragmentHome fragmentHome, Continuation<? super r> continuation) {
            super(2, continuation);
            this.$it = heartRateData;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new r(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((r) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [T, com.coveiot.android.dashboard2.model.HeartRateData] */
        /* JADX WARN: Type inference failed for: r1v26, types: [T, com.coveiot.android.dashboard2.model.HeartRateData] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = this.$it;
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                if (objectRef.element == 0) {
                    objectRef.element = new HeartRateData(0, 0L, 3, null);
                }
                boolean z = true;
                if (((HeartRateData) objectRef.element).getValue() > 0) {
                    booleanRef.element = true;
                }
                int i = 0;
                if (this.this$0.q0().healthVitalsCardContainer.clFirstFitnessVitalsCard.getVisibility() == 0) {
                    String firstCardType = this.this$0.q0().healthVitalsCardContainer.getFirstCardType();
                    if (firstCardType != null && firstCardType.length() != 0) {
                        z = false;
                    }
                    if (!z && Intrinsics.areEqual(this.this$0.q0().healthVitalsCardContainer.getFirstCardType(), IDOConstants.DATA_TYPE_HEART_RATE)) {
                        FragmentHomeHelper fragmentHomeHelper = this.this$0.getFragmentHomeHelper();
                        FragmentHomeBinding q0 = this.this$0.q0();
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        fragmentHomeHelper.updateFirstVitalCard(q0, requireActivity, booleanRef.element, (FitnessVitalData) objectRef.element, this.this$0);
                        return Unit.INSTANCE;
                    }
                }
                List<FitnessVitalsDataModel> selectedFitnessVitalsGridDataModels = this.this$0.getSelectedFitnessVitalsGridDataModels();
                FragmentHome fragmentHome = this.this$0;
                for (Object obj2 : selectedFitnessVitalsGridDataModels) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    FitnessVitalsDataModel fitnessVitalsDataModel = (FitnessVitalsDataModel) obj2;
                    if (Intrinsics.areEqual(fitnessVitalsDataModel.getFitnessVitalType(), IDOConstants.DATA_TYPE_HEART_RATE)) {
                        fitnessVitalsDataModel.setFitnessData((FitnessVitalData) objectRef.element);
                        fitnessVitalsDataModel.setDataAvailable(booleanRef.element);
                        RecyclerView.Adapter adapter = fragmentHome.q0().healthVitalsCardContainer.rvVitals.getAdapter();
                        if (adapter != null) {
                            adapter.notifyItemChanged(i);
                        }
                    }
                    i = i2;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerRespiratoryRateLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class s extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ RespiratoryRateData $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public s(RespiratoryRateData respiratoryRateData, FragmentHome fragmentHome, Continuation<? super s> continuation) {
            super(2, continuation);
            this.$it = respiratoryRateData;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new s(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((s) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [T, com.coveiot.android.dashboard2.model.RespiratoryRateData] */
        /* JADX WARN: Type inference failed for: r1v26, types: [T, com.coveiot.android.dashboard2.model.RespiratoryRateData] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = this.$it;
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                if (objectRef.element == 0) {
                    objectRef.element = new RespiratoryRateData(0, 0L, 3, null);
                }
                boolean z = true;
                if (((RespiratoryRateData) objectRef.element).getValue() > 0) {
                    booleanRef.element = true;
                }
                int i = 0;
                if (this.this$0.q0().healthVitalsCardContainer.clFirstFitnessVitalsCard.getVisibility() == 0) {
                    String firstCardType = this.this$0.q0().healthVitalsCardContainer.getFirstCardType();
                    if (firstCardType != null && firstCardType.length() != 0) {
                        z = false;
                    }
                    if (!z && Intrinsics.areEqual(this.this$0.q0().healthVitalsCardContainer.getFirstCardType(), "NBR")) {
                        FragmentHomeHelper fragmentHomeHelper = this.this$0.getFragmentHomeHelper();
                        FragmentHomeBinding q0 = this.this$0.q0();
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        fragmentHomeHelper.updateFirstVitalCard(q0, requireActivity, booleanRef.element, (FitnessVitalData) objectRef.element, this.this$0);
                        return Unit.INSTANCE;
                    }
                }
                List<FitnessVitalsDataModel> selectedFitnessVitalsGridDataModels = this.this$0.getSelectedFitnessVitalsGridDataModels();
                FragmentHome fragmentHome = this.this$0;
                for (Object obj2 : selectedFitnessVitalsGridDataModels) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    FitnessVitalsDataModel fitnessVitalsDataModel = (FitnessVitalsDataModel) obj2;
                    if (Intrinsics.areEqual(fitnessVitalsDataModel.getFitnessVitalType(), "NBR")) {
                        fitnessVitalsDataModel.setFitnessData((FitnessVitalData) objectRef.element);
                        fitnessVitalsDataModel.setDataAvailable(booleanRef.element);
                        RecyclerView.Adapter adapter = fragmentHome.q0().healthVitalsCardContainer.rvVitals.getAdapter();
                        if (adapter != null) {
                            adapter.notifyItemChanged(i);
                        }
                    }
                    i = i2;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerSPO2LiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class t extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ SPO2Data $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public t(SPO2Data sPO2Data, FragmentHome fragmentHome, Continuation<? super t> continuation) {
            super(2, continuation);
            this.$it = sPO2Data;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new t(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((t) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [com.coveiot.android.dashboard2.model.SPO2Data, T] */
        /* JADX WARN: Type inference failed for: r1v27, types: [com.coveiot.android.dashboard2.model.SPO2Data, T] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = this.$it;
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                if (objectRef.element == 0) {
                    objectRef.element = new SPO2Data(0.0d, 0L, null, 7, null);
                }
                boolean z = true;
                if (((SPO2Data) objectRef.element).getValue() > 0.0d) {
                    booleanRef.element = true;
                }
                int i = 0;
                if (this.this$0.q0().healthVitalsCardContainer.clFirstFitnessVitalsCard.getVisibility() == 0) {
                    String firstCardType = this.this$0.q0().healthVitalsCardContainer.getFirstCardType();
                    if (firstCardType != null && firstCardType.length() != 0) {
                        z = false;
                    }
                    if (!z && Intrinsics.areEqual(this.this$0.q0().healthVitalsCardContainer.getFirstCardType(), "SPO2")) {
                        FragmentHomeHelper fragmentHomeHelper = this.this$0.getFragmentHomeHelper();
                        FragmentHomeBinding q0 = this.this$0.q0();
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        fragmentHomeHelper.updateFirstVitalCard(q0, requireActivity, booleanRef.element, (FitnessVitalData) objectRef.element, this.this$0);
                        return Unit.INSTANCE;
                    }
                }
                List<FitnessVitalsDataModel> selectedFitnessVitalsGridDataModels = this.this$0.getSelectedFitnessVitalsGridDataModels();
                FragmentHome fragmentHome = this.this$0;
                for (Object obj2 : selectedFitnessVitalsGridDataModels) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    FitnessVitalsDataModel fitnessVitalsDataModel = (FitnessVitalsDataModel) obj2;
                    if (Intrinsics.areEqual(fitnessVitalsDataModel.getFitnessVitalType(), "SPO2")) {
                        fitnessVitalsDataModel.setFitnessData((FitnessVitalData) objectRef.element);
                        fitnessVitalsDataModel.setDataAvailable(booleanRef.element);
                        RecyclerView.Adapter adapter = fragmentHome.q0().healthVitalsCardContainer.rvVitals.getAdapter();
                        if (adapter != null) {
                            adapter.notifyItemChanged(i);
                        }
                    }
                    i = i2;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerSelectedFitnessVitalsLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class u extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ SelectedFitnessVitalsDataModel $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerSelectedFitnessVitalsLiveData$1$1$2", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentHome this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentHome fragmentHome, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentHome;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, continuation);
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
                    if (this.this$0.isAdded()) {
                        FitnessVitalsHelper fitnessVitalsHelper = FitnessVitalsHelper.INSTANCE;
                        Context requireContext = this.this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        TemperatureDataViewModel temperatureDataViewModel = null;
                        if (fitnessVitalsHelper.isHeartRateSupported(requireContext)) {
                            HeartRateDataViewModel heartRateDataViewModel = this.this$0.s;
                            if (heartRateDataViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mHeartRateDataViewModel");
                                heartRateDataViewModel = null;
                            }
                            heartRateDataViewModel.getLastReadHeartRate();
                        }
                        Context requireContext2 = this.this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        if (fitnessVitalsHelper.isSPO2Supported(requireContext2)) {
                            SPO2DataViewModel sPO2DataViewModel = this.this$0.t;
                            if (sPO2DataViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mSPO2DataViewModel");
                                sPO2DataViewModel = null;
                            }
                            sPO2DataViewModel.getLastReadSPO2();
                        }
                        Context requireContext3 = this.this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                        if (fitnessVitalsHelper.isStressSupported(requireContext3)) {
                            StressDataViewModel stressDataViewModel = this.this$0.u;
                            if (stressDataViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mStressDataViewModel");
                                stressDataViewModel = null;
                            }
                            stressDataViewModel.getStressData();
                        }
                        Context requireContext4 = this.this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                        if (fitnessVitalsHelper.isHRVSupported(requireContext4)) {
                            HRVDataViewModel hRVDataViewModel = this.this$0.v;
                            if (hRVDataViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mHRVDataViewModel");
                                hRVDataViewModel = null;
                            }
                            hRVDataViewModel.getLastReadHrv();
                        }
                        Context requireContext5 = this.this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                        if (fitnessVitalsHelper.isEnergyMeterSupported(requireContext5)) {
                            EnergyMeterDataViewModel energyMeterDataViewModel = this.this$0.w;
                            if (energyMeterDataViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mEnergyMeterViewModel");
                                energyMeterDataViewModel = null;
                            }
                            energyMeterDataViewModel.getEnergyMeterData();
                        }
                        Context requireContext6 = this.this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
                        if (fitnessVitalsHelper.isRespiratoryRateSupported(requireContext6)) {
                            RespiratoryRateDataViewModel respiratoryRateDataViewModel = this.this$0.x;
                            if (respiratoryRateDataViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mRespiratoryRateViewModel");
                                respiratoryRateDataViewModel = null;
                            }
                            respiratoryRateDataViewModel.getRespiratoryRateData();
                        }
                        Context requireContext7 = this.this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext7, "requireContext()");
                        if (fitnessVitalsHelper.isTemperatureSupported(requireContext7)) {
                            TemperatureDataViewModel temperatureDataViewModel2 = this.this$0.y;
                            if (temperatureDataViewModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mTemperatureDataViewModel");
                            } else {
                                temperatureDataViewModel = temperatureDataViewModel2;
                            }
                            temperatureDataViewModel.getLastReadTemperature();
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public u(SelectedFitnessVitalsDataModel selectedFitnessVitalsDataModel, FragmentHome fragmentHome, Continuation<? super u> continuation) {
            super(2, continuation);
            this.$it = selectedFitnessVitalsDataModel;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new u(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((u) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                SelectedFitnessVitalsDataModel selectedFitnessVitalsDataModel = this.$it;
                int i = 0;
                if (selectedFitnessVitalsDataModel != null) {
                    List<FitnessVitalsDataModel> fitnessDataModels = selectedFitnessVitalsDataModel.getFitnessDataModels();
                    if (!(fitnessDataModels == null || fitnessDataModels.isEmpty())) {
                        this.this$0.q0().setSelectedVitalsCount(Boxing.boxInt(this.$it.getFitnessDataModels().size()));
                        this.this$0.setSelectedFitnessVitalsDataModels(this.$it.getFitnessDataModels());
                        this.this$0.getSelectedFitnessVitalsGridDataModels().clear();
                        if (this.$it.getFitnessDataModels().size() % 2 == 0) {
                            this.this$0.q0().healthVitalsCardContainer.clFirstFitnessVitalsCard.setVisibility(8);
                            this.this$0.getSelectedFitnessVitalsGridDataModels().addAll(CollectionsKt___CollectionsKt.toMutableList((Collection) this.$it.getFitnessDataModels()));
                        } else {
                            this.this$0.q0().healthVitalsCardContainer.clFirstFitnessVitalsCard.setVisibility(0);
                            List<FitnessVitalsDataModel> fitnessDataModels2 = this.$it.getFitnessDataModels();
                            FragmentHome fragmentHome = this.this$0;
                            for (Object obj2 : fitnessDataModels2) {
                                int i2 = i + 1;
                                if (i < 0) {
                                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                                }
                                FitnessVitalsDataModel fitnessVitalsDataModel = (FitnessVitalsDataModel) obj2;
                                if (i != 0) {
                                    fragmentHome.getSelectedFitnessVitalsGridDataModels().add(fitnessVitalsDataModel);
                                } else {
                                    fragmentHome.q0().healthVitalsCardContainer.setFirstCardType(fitnessVitalsDataModel.getFitnessVitalType());
                                }
                                i = i2;
                            }
                        }
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new a(this.this$0, null), 2, null);
                        FragmentHome fragmentHome2 = this.this$0;
                        List<FitnessVitalsDataModel> selectedFitnessVitalsGridDataModels = fragmentHome2.getSelectedFitnessVitalsGridDataModels();
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        fragmentHome2.setVitalsGridAdapter(new DashboardVitalsGridviewAdapter(selectedFitnessVitalsGridDataModels, requireActivity));
                        DashboardVitalsGridviewAdapter vitalsGridAdapter = this.this$0.getVitalsGridAdapter();
                        if (vitalsGridAdapter != null) {
                            vitalsGridAdapter.setFitnessVitalClickListener(this.this$0);
                        }
                        this.this$0.q0().healthVitalsCardContainer.rvVitals.setAdapter(this.this$0.getVitalsGridAdapter());
                        return Unit.INSTANCE;
                    }
                }
                this.this$0.q0().setSelectedVitalsCount(Boxing.boxInt(0));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerStepsLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class v extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ StepsDataModel $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public v(StepsDataModel stepsDataModel, FragmentHome fragmentHome, Continuation<? super v> continuation) {
            super(2, continuation);
            this.$it = stepsDataModel;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new v(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((v) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                StepsDataModel stepsDataModel = this.$it;
                if (stepsDataModel != null) {
                    stepsDataModel.getSteps();
                    if (this.$it.getSteps() >= 0 && DateUtils.isToday(this.this$0.getSelectedCalendar().getTimeInMillis())) {
                        this.this$0.q0().setStepsDataModel(this.$it);
                        FragmentHomeBinding q0 = this.this$0.q0();
                        StepsDataHelper stepsDataHelper = StepsDataHelper.INSTANCE;
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        q0.setDistanceData(stepsDataHelper.getDistanceSmallHealthCardInfo(requireActivity, this.$it.getDistance()));
                        FragmentHomeBinding q02 = this.this$0.q0();
                        FragmentActivity requireActivity2 = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                        Double calories = this.$it.getCalories();
                        Intrinsics.checkNotNullExpressionValue(calories, "it.calories");
                        q02.setCalorieData(stepsDataHelper.getCalorieSmallHealthCardInfo(requireActivity2, calories.doubleValue()));
                        this.this$0.Y2(Boxing.boxInt(this.$it.getSteps()));
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerStressLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class w extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ StressData $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public w(StressData stressData, FragmentHome fragmentHome, Continuation<? super w> continuation) {
            super(2, continuation);
            this.$it = stressData;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new w(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((w) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [T, com.coveiot.android.dashboard2.model.StressData] */
        /* JADX WARN: Type inference failed for: r1v26, types: [T, com.coveiot.android.dashboard2.model.StressData] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = this.$it;
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                if (objectRef.element == 0) {
                    objectRef.element = new StressData(0, null, 0L, 7, null);
                }
                boolean z = true;
                if (((StressData) objectRef.element).getValue() > 0) {
                    booleanRef.element = true;
                }
                int i = 0;
                if (this.this$0.q0().healthVitalsCardContainer.clFirstFitnessVitalsCard.getVisibility() == 0) {
                    String firstCardType = this.this$0.q0().healthVitalsCardContainer.getFirstCardType();
                    if (firstCardType != null && firstCardType.length() != 0) {
                        z = false;
                    }
                    if (!z && Intrinsics.areEqual(this.this$0.q0().healthVitalsCardContainer.getFirstCardType(), "STRESS")) {
                        FragmentHomeHelper fragmentHomeHelper = this.this$0.getFragmentHomeHelper();
                        FragmentHomeBinding q0 = this.this$0.q0();
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        fragmentHomeHelper.updateFirstVitalCard(q0, requireActivity, booleanRef.element, (FitnessVitalData) objectRef.element, this.this$0);
                        return Unit.INSTANCE;
                    }
                }
                List<FitnessVitalsDataModel> selectedFitnessVitalsGridDataModels = this.this$0.getSelectedFitnessVitalsGridDataModels();
                FragmentHome fragmentHome = this.this$0;
                for (Object obj2 : selectedFitnessVitalsGridDataModels) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    FitnessVitalsDataModel fitnessVitalsDataModel = (FitnessVitalsDataModel) obj2;
                    if (Intrinsics.areEqual(fitnessVitalsDataModel.getFitnessVitalType(), "STRESS")) {
                        fitnessVitalsDataModel.setFitnessData((FitnessVitalData) objectRef.element);
                        fitnessVitalsDataModel.setDataAvailable(booleanRef.element);
                        RecyclerView.Adapter adapter = fragmentHome.q0().healthVitalsCardContainer.rvVitals.getAdapter();
                        if (adapter != null) {
                            adapter.notifyItemChanged(i);
                        }
                    }
                    i = i2;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerSyncStateLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class x extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ SyncState $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* loaded from: classes4.dex */
        public static final class a implements Runnable {
            public final /* synthetic */ FragmentHome h;

            public a(FragmentHome fragmentHome) {
                this.h = fragmentHome;
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (this.h.isAdded()) {
                    DataSyncViewModel dataSyncViewModel = this.h.p;
                    if (dataSyncViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                        dataSyncViewModel = null;
                    }
                    if (dataSyncViewModel.getSyncState().isSyncing()) {
                        return;
                    }
                    FragmentHome fragmentHome = this.h;
                    String string = fragmentHome.getString(R.string.tap_to_sync);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.tap_to_sync)");
                    fragmentHome.C1(false, string);
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public x(SyncState syncState, FragmentHome fragmentHome, Continuation<? super x> continuation) {
            super(2, continuation);
            this.$it = syncState;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new x(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((x) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding;
            ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding2;
            TextView textView;
            ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding3;
            TextView textView2;
            ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding4;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$it.isSyncing()) {
                    this.this$0.C1(true, this.$it.getMessage());
                } else if (Intrinsics.areEqual(this.$it.getMessage(), this.this$0.getString(R.string.sync_falied))) {
                    if (this.this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                        FragmentActivity activity = this.this$0.getActivity();
                        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                        ((FragmentHomeCallBackInterface) activity).syncFailed(this.$it.getMessage());
                    }
                    DataSyncViewModel dataSyncViewModel = this.this$0.p;
                    if (dataSyncViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                        dataSyncViewModel = null;
                    }
                    MutableLiveData<SyncState> syncStateLiveData = dataSyncViewModel.getSyncStateLiveData();
                    if (syncStateLiveData != null) {
                        syncStateLiveData.postValue(null);
                    }
                } else {
                    FragmentHome fragmentHome = this.this$0;
                    String string = fragmentHome.getString(R.string.tap_to_sync);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.tap_to_sync)");
                    fragmentHome.C1(false, string);
                    this.$it.getLastDataSyncTimestamp();
                    if (this.$it.getLastDataSyncTimestamp() > 0) {
                        FragmentHomeBinding q0 = this.this$0.q0();
                        TextView textView3 = (q0 == null || (connectedDeviceInfoCardDashboardBinding4 = q0.syncStatusLayout) == null) ? null : connectedDeviceInfoCardDashboardBinding4.tvLastSyncTime;
                        if (textView3 != null) {
                            textView3.setText(AppUtils.formatDate(new Date(this.$it.getLastDataSyncTimestamp()), "hh:mm aa"));
                        }
                        if (DateUtils.isToday(this.$it.getLastDataSyncTimestamp())) {
                            FragmentHomeBinding q02 = this.this$0.q0();
                            if (q02 != null && (connectedDeviceInfoCardDashboardBinding3 = q02.syncStatusLayout) != null && (textView2 = connectedDeviceInfoCardDashboardBinding3.tvLastSyncDate) != null) {
                                this.this$0.gone(textView2);
                            }
                        } else {
                            FragmentHomeBinding q03 = this.this$0.q0();
                            if (q03 != null && (connectedDeviceInfoCardDashboardBinding2 = q03.syncStatusLayout) != null && (textView = connectedDeviceInfoCardDashboardBinding2.tvLastSyncDate) != null) {
                                this.this$0.visible(textView);
                            }
                            FragmentHomeBinding q04 = this.this$0.q0();
                            TextView textView4 = (q04 == null || (connectedDeviceInfoCardDashboardBinding = q04.syncStatusLayout) == null) ? null : connectedDeviceInfoCardDashboardBinding.tvLastSyncDate;
                            if (textView4 != null) {
                                textView4.setText(AppUtils.formatDate(new Date(this.$it.getLastDataSyncTimestamp()), "dd MMM yy"));
                            }
                        }
                    }
                    if (Intrinsics.areEqual(this.$it.getMessage(), this.this$0.getString(R.string.synced_successfully))) {
                        if (this.this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                            FragmentActivity activity2 = this.this$0.getActivity();
                            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                            ((FragmentHomeCallBackInterface) activity2).syncSuccess();
                        }
                        FragmentHome fragmentHome2 = this.this$0;
                        String string2 = fragmentHome2.getString(R.string.sync_completed);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.sync_completed)");
                        fragmentHome2.C1(true, string2);
                        if (this.this$0.isAdded()) {
                            new Handler(Looper.getMainLooper()).postDelayed(new a(this.this$0), 5000L);
                        }
                        DataSyncViewModel dataSyncViewModel2 = this.this$0.p;
                        if (dataSyncViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                            dataSyncViewModel2 = null;
                        }
                        MutableLiveData<SyncState> syncStateLiveData2 = dataSyncViewModel2.getSyncStateLiveData();
                        if (syncStateLiveData2 != null) {
                            syncStateLiveData2.postValue(null);
                        }
                        this.this$0.a2();
                        this.this$0.i0();
                        FragmentHome fragmentHome3 = this.this$0;
                        Calendar selectedCalendar = fragmentHome3.getSelectedCalendar();
                        Intrinsics.checkNotNullExpressionValue(selectedCalendar, "selectedCalendar");
                        fragmentHome3.v0(selectedCalendar);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerTemperatureLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class y extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ TemperatureData $it;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public y(TemperatureData temperatureData, FragmentHome fragmentHome, Continuation<? super y> continuation) {
            super(2, continuation);
            this.$it = temperatureData;
            this.this$0 = fragmentHome;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new y(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((y) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [T, com.coveiot.android.dashboard2.model.TemperatureData] */
        /* JADX WARN: Type inference failed for: r1v27, types: [T, com.coveiot.android.dashboard2.model.TemperatureData] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = this.$it;
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                if (objectRef.element == 0) {
                    objectRef.element = new TemperatureData(0.0d, 0L, 3, null);
                }
                boolean z = true;
                if (((TemperatureData) objectRef.element).getValue() > 0.0d) {
                    booleanRef.element = true;
                }
                int i = 0;
                if (this.this$0.q0().healthVitalsCardContainer.clFirstFitnessVitalsCard.getVisibility() == 0) {
                    String firstCardType = this.this$0.q0().healthVitalsCardContainer.getFirstCardType();
                    if (firstCardType != null && firstCardType.length() != 0) {
                        z = false;
                    }
                    if (!z && Intrinsics.areEqual(this.this$0.q0().healthVitalsCardContainer.getFirstCardType(), "TEMPERATURE")) {
                        FragmentHomeHelper fragmentHomeHelper = this.this$0.getFragmentHomeHelper();
                        FragmentHomeBinding q0 = this.this$0.q0();
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        fragmentHomeHelper.updateFirstVitalCard(q0, requireActivity, booleanRef.element, (FitnessVitalData) objectRef.element, this.this$0);
                        return Unit.INSTANCE;
                    }
                }
                List<FitnessVitalsDataModel> selectedFitnessVitalsGridDataModels = this.this$0.getSelectedFitnessVitalsGridDataModels();
                FragmentHome fragmentHome = this.this$0;
                for (Object obj2 : selectedFitnessVitalsGridDataModels) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    FitnessVitalsDataModel fitnessVitalsDataModel = (FitnessVitalsDataModel) obj2;
                    if (Intrinsics.areEqual(fitnessVitalsDataModel.getFitnessVitalType(), "TEMPERATURE")) {
                        fitnessVitalsDataModel.setFitnessData((FitnessVitalData) objectRef.element);
                        fitnessVitalsDataModel.setDataAvailable(booleanRef.element);
                        RecyclerView.Adapter adapter = fragmentHome.q0().healthVitalsCardContainer.rvVitals.getAdapter();
                        if (adapter != null) {
                            adapter.notifyItemChanged(i);
                        }
                    }
                    i = i2;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$sendProgressOfOngoingPlanData$1", f = "FragmentHome.kt", i = {1, 2, 2, 2}, l = {1219, 1223, 1228}, m = "invokeSuspend", n = {"currentPlan", "currentPlan", "totalActivitiesDistance", "dayRange"}, s = {"L$0", "L$0", "L$1", "L$2"})
    /* loaded from: classes4.dex */
    public static final class z extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* loaded from: classes4.dex */
        public static final class a extends Lambda implements Function1<Pair<? extends Integer, ? extends Double>, Unit> {
            public final /* synthetic */ EntityPreparationPlan $currentPlan;
            public final /* synthetic */ ArrayList<String> $dayRange;
            public final /* synthetic */ Pair<Integer, Integer> $totalActivitiesDistance;
            public final /* synthetic */ FragmentHome this$0;

            /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$z$a$a  reason: collision with other inner class name */
            /* loaded from: classes4.dex */
            public static final class C0259a extends Lambda implements Function1<FitnessPlanTemplateRes.FitnessPlanTemplateData, Unit> {
                public final /* synthetic */ EntityPreparationPlan $currentPlan;
                public final /* synthetic */ ArrayList<String> $dayRange;
                public final /* synthetic */ Pair<Integer, Integer> $totalActivitiesDistance;
                public final /* synthetic */ FragmentHome this$0;

                @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$sendProgressOfOngoingPlanData$1$1$1$1", f = "FragmentHome.kt", i = {0, 0, 1}, l = {1290, 1304}, m = "invokeSuspend", n = {"weekCoveredDistance", "distanceValueJob", "weekCoveredDistance"}, s = {"L$0", "L$1", "L$0"})
                /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$z$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes4.dex */
                public static final class C0260a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ EntityPreparationPlan $currentPlan;
                    public final /* synthetic */ ArrayList<String> $dayRange;
                    public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData $template;
                    public final /* synthetic */ Pair<Integer, Integer> $totalActivitiesDistance;
                    public Object L$0;
                    public Object L$1;
                    public int label;
                    public final /* synthetic */ FragmentHome this$0;

                    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$sendProgressOfOngoingPlanData$1$1$1$1$distanceValueJob$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$z$a$a$a$a  reason: collision with other inner class name */
                    /* loaded from: classes4.dex */
                    public static final class C0261a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public final /* synthetic */ ArrayList<String> $dayRange;
                        public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData $template;
                        public final /* synthetic */ Ref.IntRef $weekCoveredDistance;
                        public int label;
                        public final /* synthetic */ FragmentHome this$0;

                        /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$z$a$a$a$a$a  reason: collision with other inner class name */
                        /* loaded from: classes4.dex */
                        public static final class C0262a extends Lambda implements Function1<Integer, Unit> {
                            public final /* synthetic */ Ref.IntRef $weekCoveredDistance;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public C0262a(Ref.IntRef intRef) {
                                super(1);
                                this.$weekCoveredDistance = intRef;
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                invoke(num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i) {
                                this.$weekCoveredDistance.element += i;
                            }
                        }

                        /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$z$a$a$a$a$b */
                        /* loaded from: classes4.dex */
                        public static final class b extends Lambda implements Function1<Integer, Unit> {
                            public final /* synthetic */ Ref.IntRef $weekCoveredDistance;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public b(Ref.IntRef intRef) {
                                super(1);
                                this.$weekCoveredDistance = intRef;
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                                invoke(num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(int i) {
                                this.$weekCoveredDistance.element += i;
                            }
                        }

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C0261a(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData, FragmentHome fragmentHome, ArrayList<String> arrayList, Ref.IntRef intRef, Continuation<? super C0261a> continuation) {
                            super(2, continuation);
                            this.$template = fitnessPlanTemplateData;
                            this.this$0 = fragmentHome;
                            this.$dayRange = arrayList;
                            this.$weekCoveredDistance = intRef;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new C0261a(this.$template, this.this$0, this.$dayRange, this.$weekCoveredDistance, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                            return ((C0261a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            ArrayList<String> arrayList;
                            ArrayList<String> arrayList2;
                            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                            if (this.label == 0) {
                                ResultKt.throwOnFailure(obj);
                                for (FitnessPlanTemplateRes.FitnessPlanTemplateData.ActivityMapping activityMapping : this.$template.getActivityMappings()) {
                                    if (UserDataManager.getInstance(this.this$0.requireContext()).isOneKSupported()) {
                                        for (String code : activityMapping.getPhysicalActivityCodes()) {
                                            ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.this$0.R;
                                            if (viewModelWorkoutFeedback == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
                                                viewModelWorkoutFeedback = null;
                                            }
                                            Intrinsics.checkNotNullExpressionValue(code, "code");
                                            String str = this.$dayRange.get(0);
                                            Intrinsics.checkNotNullExpressionValue(str, "dayRange[0]");
                                            String str2 = this.$dayRange.get(arrayList.size() - 1);
                                            Intrinsics.checkNotNullExpressionValue(str2, "dayRange[dayRange.size - 1]");
                                            viewModelWorkoutFeedback.getAllWeekDistanceValueByActivityNCategoryIDs(code, str, str2, new C0262a(this.$weekCoveredDistance));
                                        }
                                    } else {
                                        for (String type : activityMapping.getSessionTypes()) {
                                            ViewModelWorkoutFeedback viewModelWorkoutFeedback2 = this.this$0.R;
                                            if (viewModelWorkoutFeedback2 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
                                                viewModelWorkoutFeedback2 = null;
                                            }
                                            String str3 = this.$dayRange.get(0);
                                            Intrinsics.checkNotNullExpressionValue(str3, "dayRange[0]");
                                            String str4 = this.$dayRange.get(arrayList2.size() - 1);
                                            Intrinsics.checkNotNullExpressionValue(str4, "dayRange[dayRange.size - 1]");
                                            Intrinsics.checkNotNullExpressionValue(type, "type");
                                            viewModelWorkoutFeedback2.getAllWeeksDistanceValueByActivityType(str3, str4, type, new b(this.$weekCoveredDistance));
                                        }
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0260a(FragmentHome fragmentHome, FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData, Pair<Integer, Integer> pair, ArrayList<String> arrayList, EntityPreparationPlan entityPreparationPlan, Continuation<? super C0260a> continuation) {
                        super(2, continuation);
                        this.this$0 = fragmentHome;
                        this.$template = fitnessPlanTemplateData;
                        this.$totalActivitiesDistance = pair;
                        this.$dayRange = arrayList;
                        this.$currentPlan = entityPreparationPlan;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new C0260a(this.this$0, this.$template, this.$totalActivitiesDistance, this.$dayRange, this.$currentPlan, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((C0260a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:20:0x007e  */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r15) {
                        /*
                            Method dump skipped, instructions count: 271
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.fragment.FragmentHome.z.a.C0259a.C0260a.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0259a(FragmentHome fragmentHome, Pair<Integer, Integer> pair, ArrayList<String> arrayList, EntityPreparationPlan entityPreparationPlan) {
                    super(1);
                    this.this$0 = fragmentHome;
                    this.$totalActivitiesDistance = pair;
                    this.$dayRange = arrayList;
                    this.$currentPlan = entityPreparationPlan;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
                    invoke2(fitnessPlanTemplateData);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull FitnessPlanTemplateRes.FitnessPlanTemplateData template) {
                    Intrinsics.checkNotNullParameter(template, "template");
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new C0260a(this.this$0, template, this.$totalActivitiesDistance, this.$dayRange, this.$currentPlan, null), 2, null);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentHome fragmentHome, EntityPreparationPlan entityPreparationPlan, Pair<Integer, Integer> pair, ArrayList<String> arrayList) {
                super(1);
                this.this$0 = fragmentHome;
                this.$currentPlan = entityPreparationPlan;
                this.$totalActivitiesDistance = pair;
                this.$dayRange = arrayList;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Integer, ? extends Double> pair) {
                invoke2((Pair<Integer, Double>) pair);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Pair<Integer, Double> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ThemesUtils themesUtils = ThemesUtils.INSTANCE;
                Context requireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                float parseFloat = Float.parseFloat(themesUtils.getDistanceWithoutUnit(requireContext, (int) themesUtils.convertCmToMeters(it.getFirst().intValue()), 2));
                float parseFloat2 = Float.parseFloat(themesUtils.getStringFormattedValueTillNDecimal(it.getSecond(), 2));
                Float lastCalorieBurnedFitness = SessionManager.getInstance(this.this$0.requireContext()).getLastCalorieBurnedFitness();
                Intrinsics.checkNotNullExpressionValue(lastCalorieBurnedFitness, "getInstance(requireConte….lastCalorieBurnedFitness");
                if (lastCalorieBurnedFitness.floatValue() >= parseFloat2) {
                    Float lastDistanceCoveredFitness = SessionManager.getInstance(this.this$0.requireContext()).getLastDistanceCoveredFitness();
                    Intrinsics.checkNotNullExpressionValue(lastDistanceCoveredFitness, "getInstance(\n           …astDistanceCoveredFitness");
                    if (lastDistanceCoveredFitness.floatValue() >= parseFloat) {
                        return;
                    }
                }
                SessionManager.getInstance(this.this$0.requireContext()).setLastCalorieBurnedFitness(Float.valueOf(parseFloat2));
                SessionManager.getInstance(this.this$0.requireContext()).setLastDistanceCoveredFitness(Float.valueOf(parseFloat));
                ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.this$0.R;
                if (viewModelWorkoutFeedback == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
                    viewModelWorkoutFeedback = null;
                }
                viewModelWorkoutFeedback.getPlanTemplate(this.$currentPlan.getPlanId(), new C0259a(this.this$0, this.$totalActivitiesDistance, this.$dayRange, this.$currentPlan));
            }
        }

        @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$sendProgressOfOngoingPlanData$1$currentPlan$1", f = "FragmentHome.kt", i = {}, l = {1220}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
            public int label;
            public final /* synthetic */ FragmentHome this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(FragmentHome fragmentHome, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = fragmentHome;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationPlan> continuation) {
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

        @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$sendProgressOfOngoingPlanData$1$totalActivitiesDistance$1", f = "FragmentHome.kt", i = {}, l = {1224}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Integer, ? extends Integer>>, Object> {
            public int label;
            public final /* synthetic */ FragmentHome this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(FragmentHome fragmentHome, Continuation<? super c> continuation) {
                super(2, continuation);
                this.this$0 = fragmentHome;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new c(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Integer, ? extends Integer>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super Pair<Integer, Integer>>) continuation);
            }

            @Nullable
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Pair<Integer, Integer>> continuation) {
                return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                    obj = viewModelCurrentPlanDashboard.getTotalActivitiesAndDistance(this);
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

        public z(Continuation<? super z> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new z(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((z) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x008c A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x008d  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0097  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00c8  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r14) {
            /*
                Method dump skipped, instructions count: 257
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.fragment.FragmentHome.z.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public FragmentHome() {
        State state = State.STOPPED;
        this.G = state;
        this.H = state;
        this.I = Calendar.getInstance();
        this.J = new ArrayList();
        this.K = new ArrayList();
        this.L = new ArrayList();
        this.N = new FragmentHomeHelper();
        this.Q = true;
        this.b0 = new SuccessResultListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$stepsLiveDataListener$1

            @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$stepsLiveDataListener$1$onSuccess$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes4.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ FragmentHome this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(FragmentHome fragmentHome, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentHome;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
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
                        DataSyncViewModel dataSyncViewModel = this.this$0.p;
                        if (dataSyncViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                            dataSyncViewModel = null;
                        }
                        MutableLiveData<StepsDataModel> stepsLiveDataOnPreference = dataSyncViewModel.getStepsLiveDataOnPreference();
                        UserDataManager userDataManager = UserDataManager.getInstance(this.this$0.getContext());
                        Calendar calendar = Calendar.getInstance();
                        BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
                        stepsLiveDataOnPreference.postValue(userDataManager.getLiveStepsData(calendar, bleApi != null ? bleApi.getMacAddress() : null));
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onError(@Nullable String str) {
            }

            @Override // com.coveiot.android.theme.SuccessResultListener
            public void onSuccess() {
                if (FragmentHome.this.isAdded()) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new a(FragmentHome.this, null), 2, null);
                }
            }
        };
    }

    public static final void B0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_CULTBANNER_TAPPED.getValue(), null);
        this$0.startActivity(new Intent(this$0.requireContext(), WorkoutVideosActivity.class));
    }

    public static final void C0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireContext(), WorkoutVideosActivity.class));
    }

    public static final void C2(FragmentHome this$0, Boolean isSmartGridVisible) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            ConstraintLayout constraintLayout = this$0.q0().clTopFeature;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clTopFeature");
            Intrinsics.checkNotNullExpressionValue(isSmartGridVisible, "isSmartGridVisible");
            this$0.visibleIf(constraintLayout, isSmartGridVisible.booleanValue());
            if (isSmartGridVisible.booleanValue()) {
                this$0.getSmartGridViewModel().m104getTopFeatureList();
            }
        }
    }

    public static final void D2(FragmentHome this$0, GetSmartGridRes getSmartGridRes) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            if (getSmartGridRes != null) {
                List<GetSmartGridRes.GridItem> gridItems = getSmartGridRes.getGridItems();
                if (!(gridItems == null || gridItems.isEmpty())) {
                    Context requireContext = this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    this$0.X = new DashboardTopFeaturesViewAdapter(requireContext, this$0);
                    this$0.q0().rvTopFeatures.setAdapter(this$0.X);
                    Context requireContext2 = this$0.requireContext();
                    Context requireContext3 = this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    List<GetSmartGridRes.GridItem> gridItems2 = getSmartGridRes.getGridItems();
                    Intrinsics.checkNotNullExpressionValue(gridItems2, "it.gridItems");
                    this$0.q0().rvTopFeatures.setLayoutManager(new GridLayoutManager(requireContext2, SmartGridUtilsKt.getFilteredSmartGridList(requireContext3, gridItems2).size() == 4 ? 2 : 3));
                    this$0.q0().rvTopFeatures.addItemDecoration(new DividerItemDecoration(this$0.requireContext(), this$0.getResources().getDimensionPixelSize(R.dimen.margin_0_5dp), this$0.getResources().getDimensionPixelSize(R.dimen.margin_30dp), this$0.getResources().getDimensionPixelSize(R.dimen.margin_15dp), R.drawable.rounded_gradient_grey_center_vertical_background, this$0.getResources().getDimensionPixelSize(R.dimen.margin_20dp)));
                    DashboardTopFeaturesViewAdapter dashboardTopFeaturesViewAdapter = this$0.X;
                    if (dashboardTopFeaturesViewAdapter != null) {
                        Context requireContext4 = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                        List<GetSmartGridRes.GridItem> gridItems3 = getSmartGridRes.getGridItems();
                        Intrinsics.checkNotNullExpressionValue(gridItems3, "it.gridItems");
                        dashboardTopFeaturesViewAdapter.setTopFeatureList(SmartGridUtilsKt.getFilteredSmartGridList(requireContext4, gridItems3));
                        return;
                    }
                    return;
                }
            }
            ConstraintLayout constraintLayout = this$0.q0().clTopFeature;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clTopFeature");
            this$0.gone(constraintLayout);
        }
    }

    public static final void G0(FragmentHome this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.q0().setShowFitnessChallenge(bool);
    }

    public static final void G2(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        AppUtils.openAppSettings(this$0.requireActivity(), 121);
    }

    public static final void H0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.A1();
    }

    public static final void I0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_CREATECHALLENGE_TAPPED.getValue(), null);
        this$0.A1();
    }

    public static final void I2(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DataSyncViewModel dataSyncViewModel = this$0.p;
        if (dataSyncViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
            dataSyncViewModel = null;
        }
        if (!dataSyncViewModel.getSyncState().isSyncing()) {
            if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                ((FragmentHomeCallBackInterface) activity).navigateToNotificationActivity();
            }
            BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns = this$0.a0;
            if (bottomSheetDialogImageTitleMessageTwoVerticalBtns != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns.dismiss();
            }
        } else if (this$0.isAdded()) {
            Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.syncing_please_wait), 0).show();
        }
    }

    public static final void J0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_CHALLENGEVIEWMORE_TAPPED.getValue(), null);
        if (this$0.isAdded()) {
            DataSyncViewModel dataSyncViewModel = this$0.p;
            if (dataSyncViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                dataSyncViewModel = null;
            }
            if (!dataSyncViewModel.getSyncState().isSyncing()) {
                if (!AppUtils.isNetConnected(this$0.requireContext())) {
                    this$0.showNoInternetDialog();
                    return;
                }
                Context requireContext = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                f fVar = f.INSTANCE;
                Intent intent = new Intent(requireContext, FitnessChallengesHome.class);
                fVar.invoke((f) intent);
                requireContext.startActivity(intent, null);
                return;
            }
            Toast.makeText(this$0.requireContext(), this$0.requireContext().getString(R.string.syncing_please_wait), 0).show();
        }
    }

    public static final void J2(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns = this$0.a0;
        if (bottomSheetDialogImageTitleMessageTwoVerticalBtns != null) {
            bottomSheetDialogImageTitleMessageTwoVerticalBtns.dismiss();
        }
    }

    public static final void L2(CommonMessageDialog commonMessageDialog, FragmentHome this$0) {
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        commonMessageDialog.dismiss();
        DataSyncViewModel dataSyncViewModel = this$0.p;
        if (dataSyncViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
            dataSyncViewModel = null;
        }
        if (!dataSyncViewModel.getSyncState().isSyncing()) {
            if (this$0.d0 == null || this$0.e0 == null) {
                return;
            }
            Intent intent = new Intent(this$0.requireContext(), FitnessChallengeDetails.class);
            intent.putExtra(FitnessChallengeConstants.CHALLENGE_ID, this$0.d0);
            intent.putExtra(FitnessChallengeConstants.CHALLENGE_TYPE, this$0.e0);
            intent.putExtra(FitnessChallengeConstants.ISJOINEDFROMHP, true);
            this$0.startActivity(intent);
            return;
        }
        Toast.makeText(this$0.requireContext(), this$0.requireContext().getString(R.string.syncing_please_wait), 0).show();
    }

    public static final void M2(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtils.openBluetoothSettings(this$0.requireActivity());
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.Z;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void N2(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.Z;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void R2(FragmentHome this$0) {
        SwipeRefreshLayout swipeRefreshLayout;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getActivity() == null || this$0.q0().swipeContainer == null || (swipeRefreshLayout = this$0.q0().swipeContainer) == null) {
            return;
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    public static final void T0(FragmentHome this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SwipeRefreshLayout swipeRefreshLayout = this$0.q0().swipeContainer;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        this$0.Q2();
    }

    public static final void U0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.s2();
        this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
        this$0.getDatePickerDialog().show();
    }

    public static final void V0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object clone = this$0.I.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        calendar.add(6, 1);
        this$0.v0(calendar);
    }

    public static /* synthetic */ void V2(FragmentHome fragmentHome, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        fragmentHome.U2(z2);
    }

    public static final void W0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object clone = this$0.I.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        calendar.add(6, -1);
        this$0.v0(calendar);
    }

    public static final void X0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_BTCALLING_CONNECT_RQST.getValue(), null);
        this$0.E1();
    }

    public static final void Y0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.L1();
    }

    public static final void Z0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_PROFILEICON_TAPPED.getValue(), null);
        this$0.L1();
    }

    public static final void a1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.ONEK_BANNER_CLICK.getValue());
        analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.ACTIVITY_TAB_ON_HOME_DASH.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_ACTIVITIES_TAPPED.getValue(), null);
        this$0.K1();
    }

    public static final void b1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_EDIT_VITALS_CARD_TAPPED.getValue(), null);
        this$0.startActivityForResult(new Intent(this$0.requireActivity(), ActivityEditHomeDashboardVitals.class), this$0.E);
    }

    public static final void c1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_TRACK_MORE_VITALS_TAPPED.getValue(), null);
        this$0.startActivityForResult(new Intent(this$0.requireActivity(), ActivityEditHomeDashboardVitals.class), this$0.E);
    }

    public static final void d1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.S1();
    }

    public static final void e1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_BUDDIESBANNER_TAPPED.getValue(), null);
        this$0.H1();
    }

    public static final void f1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.H1();
    }

    public static final void g1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.U1();
    }

    public static final void h1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_VIEW_FITNESSDASH_TAPPED.getValue(), null);
        if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToFitnessOverview();
        }
    }

    public static final void i1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.getActivity())) {
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

    public static final void j1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_WFBANNER_TAPPED.getValue(), null);
        this$0.P1();
    }

    public static final void k0(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = this$0.c0;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
        bottomSheetThemeDialogOneButtonTitleMessage.dismiss();
    }

    public static final void k1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.Q2();
    }

    public static final void l1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_SETTINGBANNER_TAPPED.getValue(), null);
        this$0.Q1();
    }

    public static final void m1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_STUDIOBANNER_TAPPED.getValue(), null);
        this$0.R1();
    }

    public static final void n1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.J1();
    }

    public static final void o1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_STUDIOBANNER_TAPPED.getValue(), null);
        if (!AppUtils.isNetConnected(this$0.requireActivity())) {
            Toast.makeText(this$0.requireActivity(), R.string.check_internet, 1).show();
            return;
        }
        if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToWatchfaceStudioActivity();
        }
        Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        companion.getInstance(requireActivity).saveIsUserCheckedWatchfaceStudio(true);
        FragmentActivity requireActivity2 = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        companion.getInstance(requireActivity2).saveIsUserTappedWatchfaceStudioInDashboard(true);
        this$0.N0();
    }

    public static final void p1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I1("STEPS");
    }

    public static final void q1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I1("SLEEP");
    }

    public static final void r1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I1("STEPS");
    }

    public static final void s1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I1("STEPS");
    }

    public static final void t1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.getActivity())) {
            this$0.showNoInternetDialog();
            return;
        }
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        j jVar = j.INSTANCE;
        Intent intent = new Intent(requireContext, ActivityFitnessPlan.class);
        jVar.invoke((j) intent);
        requireContext.startActivity(intent, null);
    }

    public static final void t2(FragmentHome this$0, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i2, i3, i4);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.v0(newDate);
    }

    public static final void u1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_BCBANNER_TAPPED.getValue(), null);
        this$0.F1(BoatCoinsScreenCaller.HP_BANNER);
    }

    public static final void v1(FragmentHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_BOATCOINICON_TAPPED.getValue(), null);
        this$0.F1(BoatCoinsScreenCaller.HP_TOP_ICON);
    }

    public static final void v2(FragmentHome this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            View root = this$0.q0().noChallengeView.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.noChallengeView.root");
            this$0.visible(root);
            LinearLayout linearLayout = this$0.q0().challengeLinearLayoutDots;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.challengeLinearLayoutDots");
            this$0.gone(linearLayout);
            RecyclerView recyclerView = this$0.q0().rvFitnessChallenge;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvFitnessChallenge");
            this$0.gone(recyclerView);
        }
    }

    public static final void w2(FragmentHome this$0, BuddiesChallengeRes buddiesChallengeRes) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.isAdded() || buddiesChallengeRes == null) {
            return;
        }
        RecyclerView recyclerView = this$0.q0().rvFitnessChallenge;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvFitnessChallenge");
        this$0.visible(recyclerView);
        LinearLayout linearLayout = this$0.q0().challengeLinearLayoutDots;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.challengeLinearLayoutDots");
        this$0.visible(linearLayout);
        ArrayList arrayList = new ArrayList();
        arrayList.add(buddiesChallengeRes.getItems().get(0));
        if (buddiesChallengeRes.getItems().size() > 1 && buddiesChallengeRes.getItems().get(1) != null) {
            arrayList.add(buddiesChallengeRes.getItems().get(1));
        }
        LinearLayout linearLayout2 = this$0.q0().challengeLinearLayoutDots;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.challengeLinearLayoutDots");
        this$0.visibleIf(linearLayout2, buddiesChallengeRes.getItems().size() > 1);
        this$0.l0(buddiesChallengeRes.getItems().size(), 0);
        View root = this$0.q0().noChallengeView.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.noChallengeView.root");
        this$0.gone(root);
        FitnessChallengeAdapter fitnessChallengeAdapter = this$0.T;
        if (fitnessChallengeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeAdapter");
            fitnessChallengeAdapter = null;
        }
        fitnessChallengeAdapter.setChallengesList(arrayList);
    }

    public static final void z2(FragmentHome this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            this$0.D0();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (it.booleanValue()) {
                View root = this$0.q0().indusIndWellnessCrew.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "binding.indusIndWellnessCrew.root");
                this$0.visible(root);
                if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                    FragmentActivity activity = this$0.getActivity();
                    Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                    ((FragmentHomeCallBackInterface) activity).shouldShowIndusIndLogo(true);
                    return;
                }
                return;
            }
            View root2 = this$0.q0().indusIndWellnessCrew.getRoot();
            Intrinsics.checkNotNullExpressionValue(root2, "binding.indusIndWellnessCrew.root");
            this$0.gone(root2);
            if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                FragmentActivity activity2 = this$0.getActivity();
                Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                ((FragmentHomeCallBackInterface) activity2).shouldShowIndusIndLogo(false);
            }
        }
    }

    public final void A0() {
        if (AppUtils.isNetConnected(requireActivity())) {
            q0().cultFitLayout.setVisibility(0);
            String lastVideoId = SessionManager.getInstance(requireContext()).getLastVideoId();
            SessionManager.getInstance(requireContext()).getLastVideoName();
            SessionManager.getInstance(requireContext()).getLastVideoPosition();
            if (lastVideoId == null) {
                q0().setShowCultFitFTU(Boolean.TRUE);
                q0().cultFitLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.v0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentHome.B0(FragmentHome.this, view);
                    }
                });
                return;
            }
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_cult_fit, CultFitVideoFragment.Companion.newInstance(true)).commit();
            q0().viewMoreCultFit.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.t
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentHome.C0(FragmentHome.this, view);
                }
            });
            return;
        }
        q0().cultFitLayout.setVisibility(8);
    }

    public final void A1() {
        if (isAdded()) {
            DataSyncViewModel dataSyncViewModel = this.p;
            if (dataSyncViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                dataSyncViewModel = null;
            }
            if (!dataSyncViewModel.getSyncState().isSyncing()) {
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
                l lVar = l.INSTANCE;
                Intent intent = new Intent(requireContext, ActivityCreateChallenge.class);
                lVar.invoke((l) intent);
                requireContext.startActivity(intent, null);
                return;
            }
            Toast.makeText(requireContext(), requireContext().getString(R.string.syncing_please_wait), 0).show();
        }
    }

    public final void A2() {
        MutableLiveData<Pair<EntityPreparationDay, EntityPreparationWeek>> ongoingPlanLiveData;
        if (isAdded()) {
            ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = getViewModelCurrentPlanDashboard();
            if (viewModelCurrentPlanDashboard != null && (ongoingPlanLiveData = viewModelCurrentPlanDashboard.getOngoingPlanLiveData()) != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                ongoingPlanLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$setOngoingFitnessPlanData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        Pair pair = (Pair) t2;
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.a0(pair, FragmentHome.this, null), 2, null);
                        }
                    }
                });
            }
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new b0(null), 2, null);
        }
    }

    public final void B1(EntityWorkoutSession entityWorkoutSession) {
        LogHelper.d("FragmentActivityDashboard", "loadDetailScreen");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        if (!new PreferenceManager(requireActivity).isSampleDataSupported()) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (!companion.isEastApexDevice(requireContext)) {
                return;
            }
        }
        Intent intent = new Intent(requireActivity(), ActivityDataSummaryDetails.class);
        intent.putExtra(WorkoutConstants.ACTIVITY_MODE, entityWorkoutSession.getActivity_type());
        intent.putExtra(WorkoutConstants.SESSION_ID, entityWorkoutSession.getSession_id());
        requireActivity().startActivity(intent);
    }

    public final void B2() {
        getSmartGridViewModel().isSmartGridVisible().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.dashboard2.fragment.q0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentHome.C2(FragmentHome.this, (Boolean) obj);
            }
        });
        SingleLiveEvent<GetSmartGridRes> topFeatureList = getSmartGridViewModel().getTopFeatureList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        topFeatureList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.dashboard2.fragment.m0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentHome.D2(FragmentHome.this, (GetSmartGridRes) obj);
            }
        });
    }

    public final void C1(boolean z2, String str) {
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding;
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding2;
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding3;
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding4;
        TextView textView;
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding5;
        if (isAdded()) {
            x0();
            TextView textView2 = null;
            if (z2) {
                FragmentHomeBinding q0 = q0();
                ConstraintLayout constraintLayout = (q0 == null || (connectedDeviceInfoCardDashboardBinding5 = q0.syncStatusLayout) == null) ? null : connectedDeviceInfoCardDashboardBinding5.clManualSync;
                if (constraintLayout != null) {
                    constraintLayout.setEnabled(false);
                }
                FragmentHomeBinding q02 = q0();
                if (q02 != null && (connectedDeviceInfoCardDashboardBinding4 = q02.syncStatusLayout) != null && (textView = connectedDeviceInfoCardDashboardBinding4.tvSyncingStatus) != null) {
                    emptyDrawable(textView);
                }
                FragmentHomeBinding q03 = q0();
                if (q03 != null && (connectedDeviceInfoCardDashboardBinding3 = q03.syncStatusLayout) != null) {
                    textView2 = connectedDeviceInfoCardDashboardBinding3.tvSyncingStatus;
                }
                if (textView2 == null) {
                    return;
                }
                textView2.setText(str);
                return;
            }
            FragmentHomeBinding q04 = q0();
            ConstraintLayout constraintLayout2 = (q04 == null || (connectedDeviceInfoCardDashboardBinding2 = q04.syncStatusLayout) == null) ? null : connectedDeviceInfoCardDashboardBinding2.clManualSync;
            if (constraintLayout2 != null) {
                constraintLayout2.setEnabled(true);
            }
            TextView textView3 = q0().syncStatusLayout.tvSyncingStatus;
            if (textView3 != null) {
                drawableStart(textView3, R.drawable.iv_manual_sync);
            }
            FragmentHomeBinding q05 = q0();
            if (q05 != null && (connectedDeviceInfoCardDashboardBinding = q05.syncStatusLayout) != null) {
                textView2 = connectedDeviceInfoCardDashboardBinding.tvSyncingStatus;
            }
            if (textView2 == null) {
                return;
            }
            textView2.setText(str);
        }
    }

    public final void D0() {
        FragmentHomeViewModel fragmentHomeViewModel = this.o;
        if (fragmentHomeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            fragmentHomeViewModel = null;
        }
        fragmentHomeViewModel.getDoMoreWithYourWatchData();
    }

    public final void D1() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToAlexaConnect();
        }
    }

    public final void E0() {
        ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = getViewModelCurrentPlanDashboard();
        if (viewModelCurrentPlanDashboard != null) {
            viewModelCurrentPlanDashboard.checkIfPlanOngoingOrFinished();
        }
    }

    public final void E1() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToBluetoothCallingActivity();
        }
    }

    public final void E2() {
        q0().setYourWatchPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$setupYourWatchPagerListener$1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f2, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                FragmentHome.this.q0().setSetupYourWatchItemSelectedPosition(Integer.valueOf(i2));
            }
        });
    }

    public final void F0() {
        if (AppUtils.isNetConnected(requireContext())) {
            FragmentHomeViewModel fragmentHomeViewModel = this.o;
            FitnessChallengeListViewModel fitnessChallengeListViewModel = null;
            if (fragmentHomeViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                fragmentHomeViewModel = null;
            }
            fragmentHomeViewModel.getFitnessChallengeVisibility().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.dashboard2.fragment.o0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentHome.G0(FragmentHome.this, (Boolean) obj);
                }
            });
            FitnessChallengeListViewModel fitnessChallengeListViewModel2 = this.U;
            if (fitnessChallengeListViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
            } else {
                fitnessChallengeListViewModel = fitnessChallengeListViewModel2;
            }
            fitnessChallengeListViewModel.getDashboardFirstTwoChallenges();
        } else {
            FitnessChallengeRemoteConfiguration fitnessChallengeRemoteConfig = FitnessChallengeSessionManager.getInstance(getContext()).getFitnessChallengeRemoteConfig();
            if (fitnessChallengeRemoteConfig != null && fitnessChallengeRemoteConfig.getFitness_challenges() != null) {
                q0().setShowFitnessChallenge(fitnessChallengeRemoteConfig.getFitness_challenges().getVisibility());
            }
        }
        q0().viewFitnessChallengeDashboardHeader.setTitle(getString(R.string.create_challenge));
        q0().noChallengeView.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.H0(FragmentHome.this, view);
            }
        });
        q0().viewFitnessChallengeDashboardHeader.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.I0(FragmentHome.this, view);
            }
        });
        q0().tvFitnessChallengeViewMore.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.J0(FragmentHome.this, view);
            }
        });
    }

    public final void F1(BoatCoinsScreenCaller boatCoinsScreenCaller) {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            FragmentHomeCallBackInterface.DefaultImpls.navigateToBoatCoinsWebViewer$default((FragmentHomeCallBackInterface) activity, boatCoinsScreenCaller, null, 2, null);
        }
    }

    public final void F2() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.bluetooth_permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_permission_required)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.G2(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void G1() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToBuildFitnessPlan();
        }
    }

    public final void H1() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToFitnessBuddies();
        }
    }

    public final void H2() {
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns;
        SessionManager.getInstance(requireActivity()).setBT3CallFTUShown(Long.valueOf(System.currentTimeMillis()));
        n2();
        boolean z2 = false;
        if (this.a0 == null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_bt3_connected);
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            Intrinsics.checkNotNullExpressionValue(drawable, "drawable");
            String string = getString(R.string.action_required);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.action_required)");
            String string2 = getString(R.string.to_start_using_bluetooth_calling);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.to_st…_using_bluetooth_calling)");
            BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns2 = new BottomSheetDialogImageTitleMessageTwoVerticalBtns(requireActivity, drawable, string, string2);
            this.a0 = bottomSheetDialogImageTitleMessageTwoVerticalBtns2;
            String string3 = getString(R.string.allow);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.allow)");
            bottomSheetDialogImageTitleMessageTwoVerticalBtns2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.r
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentHome.I2(FragmentHome.this, view);
                }
            });
            BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns3 = this.a0;
            if (bottomSheetDialogImageTitleMessageTwoVerticalBtns3 != null) {
                String string4 = getString(R.string.skip);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.skip)");
                bottomSheetDialogImageTitleMessageTwoVerticalBtns3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.i
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentHome.J2(FragmentHome.this, view);
                    }
                });
            }
            BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns4 = this.a0;
            if (bottomSheetDialogImageTitleMessageTwoVerticalBtns4 != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns4.setCancelable(false);
            }
        }
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns5 = this.a0;
        if (bottomSheetDialogImageTitleMessageTwoVerticalBtns5 != null && !bottomSheetDialogImageTitleMessageTwoVerticalBtns5.isShowing()) {
            z2 = true;
        }
        if (!z2 || (bottomSheetDialogImageTitleMessageTwoVerticalBtns = this.a0) == null) {
            return;
        }
        bottomSheetDialogImageTitleMessageTwoVerticalBtns.show();
    }

    public final void I1(String str) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new m(str, null), 2, null);
        }
    }

    public final void J1() {
        if (!AppUtils.isNetConnected(requireActivity())) {
            Toast.makeText(requireActivity(), R.string.check_internet, 1).show();
        } else if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToNavigationScreen();
        }
    }

    public final void K0() {
        Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isTBTNavigationSupported(requireContext)) {
            ImageView imageView = q0().navigationBanner;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.navigationBanner");
            visible(imageView);
            return;
        }
        ImageView imageView2 = q0().navigationBanner;
        Intrinsics.checkNotNullExpressionValue(imageView2, "binding.navigationBanner");
        gone(imageView2);
    }

    public final void K1() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToOneKActivity();
        }
    }

    public final void K2(String str) {
        Window window;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(requireContext, str, false, true);
        commonMessageDialog.show(getChildFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.dashboard2.fragment.t0
            @Override // java.lang.Runnable
            public final void run() {
                FragmentHome.L2(CommonMessageDialog.this, this);
            }
        }, 1000L);
    }

    public final void L0() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new g(null), 2, null);
    }

    public final void L1() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToProfileActivity();
        }
    }

    public final void M0() {
        GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$initProfileData$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                if (FragmentHome.this.q0().toolbar.imgVProfilePic != null) {
                    Bitmap circleBitmap = AppUtils.getCircleBitmap(resource);
                    if (circleBitmap != null) {
                        FragmentHome.this.q0().toolbar.imgVProfilePic.setImageBitmap(circleBitmap);
                    } else {
                        FragmentHome.this.q0().toolbar.imgVProfilePic.setImageResource(R.drawable.default_avatar);
                    }
                }
            }
        });
        ProfileData userDetails = SessionManager.getInstance(getActivity()).getUserDetails();
        if ((userDetails != null ? userDetails.getName() : null) != null) {
            q0().syncStatusLayout.tvUserName.setText(SessionManager.getInstance(getActivity()).getUserDetails().getName());
        } else {
            q0().syncStatusLayout.tvUserName.setText(getString(R.string.boathead));
        }
        if (Intrinsics.areEqual(q0().syncStatusLayout.tvUserName.getText(), getString(R.string.boathead))) {
            q0().setIsUserProfileCompleted(Boolean.FALSE);
            q0().profileLayout.progressBar.setProgress(90);
            TextView textView = q0().profileLayout.tvProgressValue;
            StringBuilder sb = new StringBuilder();
            sb.append(q0().profileLayout.progressBar.getProgress());
            sb.append('%');
            textView.setText(sb.toString());
            return;
        }
        q0().setIsUserProfileCompleted(Boolean.TRUE);
        q0().profileLayout.progressBar.setProgress(100);
        TextView textView2 = q0().profileLayout.tvProgressValue;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(q0().profileLayout.progressBar.getProgress());
        sb2.append('%');
        textView2.setText(sb2.toString());
    }

    public final void M1(String str) {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToSOSSettings(str);
        }
    }

    public final void N0() {
        SetupYourWatchDataHelper setupYourWatchDataHelper = SetupYourWatchDataHelper.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        O0(setupYourWatchDataHelper.getSetupYourWatchData(requireActivity));
    }

    public final void N1() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToSportsActivity();
        }
    }

    public final void O0(List<SetupYourWatchDataModel> list) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new h(list, null), 2, null);
        }
    }

    public final void O1() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToTapAndPay();
        }
    }

    public final void O2() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        if (this.H == State.STOPPED) {
            BleApi bleApi = BleApiManager.getInstance(requireActivity()).getBleApi();
            boolean z2 = false;
            if (bleApi != null && (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures.isLiveStepsSupported()) {
                z2 = true;
            }
            if (z2) {
                BleApi bleApi2 = BleApiManager.getInstance(requireActivity()).getBleApi();
                StepsDataViewModel stepsDataViewModel = null;
                if ((bleApi2 != null ? bleApi2.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                    DataSyncViewModel dataSyncViewModel = this.p;
                    if (dataSyncViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                        dataSyncViewModel = null;
                    }
                    if (!dataSyncViewModel.checkIsSyncing()) {
                        this.G = this.H;
                        this.H = State.INPROGRESS;
                        StepsDataViewModel stepsDataViewModel2 = this.q;
                        if (stepsDataViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mStepsDataViewModel");
                        } else {
                            stepsDataViewModel = stepsDataViewModel2;
                        }
                        stepsDataViewModel.sendLiveStepsControlRequest(true);
                    }
                }
            }
        }
    }

    public final void P0() {
        q0().calSelectionView.imgArrowRight.setEnabled(false);
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        t0(calendar);
    }

    public final void P1() {
        if (BleApiManager.getInstance(getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            DataSyncViewModel dataSyncViewModel = this.p;
            if (dataSyncViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                dataSyncViewModel = null;
            }
            if (!dataSyncViewModel.checkIsSyncing()) {
                if (getActivity() instanceof FragmentHomeCallBackInterface) {
                    FragmentActivity activity = getActivity();
                    Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                    ((FragmentHomeCallBackInterface) activity).navigateToWatchfaceScreen(WatchfaceScreenCaller.WATCH_FEATURES);
                }
                Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                companion.getInstance(requireActivity).saveIsUserCheckedWatchface(true);
                N0();
                return;
            }
            Toast.makeText(requireActivity(), getString(R.string.syncing_please_wait), 0).show();
            return;
        }
        Toast.makeText(requireActivity(), getString(R.string.band_not_connected), 0).show();
    }

    public final void P2() {
        if (this.H == State.STARTED) {
            BleApi bleApi = BleApiManager.getInstance(requireActivity()).getBleApi();
            StepsDataViewModel stepsDataViewModel = null;
            if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                DataSyncViewModel dataSyncViewModel = this.p;
                if (dataSyncViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                    dataSyncViewModel = null;
                }
                if (!dataSyncViewModel.checkIsSyncing()) {
                    this.G = this.H;
                    this.H = State.INPROGRESS;
                    StepsDataViewModel stepsDataViewModel2 = this.q;
                    if (stepsDataViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mStepsDataViewModel");
                    } else {
                        stepsDataViewModel = stepsDataViewModel2;
                    }
                    stepsDataViewModel.sendLiveStepsControlRequest(false);
                }
            }
        }
    }

    public final void Q0() {
        Calendar selectedCalendar = this.I;
        Intrinsics.checkNotNullExpressionValue(selectedCalendar, "selectedCalendar");
        v0(selectedCalendar);
    }

    public final void Q1() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToWatchSettingsActivity(false);
        }
        Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        companion.getInstance(requireActivity).saveIsUserCheckedWatchSettings(true);
        N0();
    }

    public final void Q2() {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        DataSyncViewModel dataSyncViewModel = null;
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_WATCHSYNC_USER_RQST.getValue(), null);
        DataSyncViewModel dataSyncViewModel2 = this.p;
        if (dataSyncViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
            dataSyncViewModel2 = null;
        }
        if (dataSyncViewModel2.getSyncState().isSyncing()) {
            return;
        }
        if (BleApiManager.getInstance(getActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (getActivity() instanceof FragmentHomeCallBackInterface) {
                DataSyncViewModel dataSyncViewModel3 = this.p;
                if (dataSyncViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                } else {
                    dataSyncViewModel = dataSyncViewModel3;
                }
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                dataSyncViewModel.syncData(true, ((FragmentHomeCallBackInterface) activity).getServerSyncConfig());
                return;
            }
            DataSyncViewModel dataSyncViewModel4 = this.p;
            if (dataSyncViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                dataSyncViewModel4 = null;
            }
            dataSyncViewModel4.syncData(true, null);
            return;
        }
        if (getBluetoothAdapter() != null) {
            if (getBluetoothAdapter().isEnabled()) {
                if (Build.VERSION.SDK_INT >= 31) {
                    Dashboard2Utils.Companion companion2 = Dashboard2Utils.Companion;
                    if (companion2.checkIfBluetoothPermissionExists(requireContext())) {
                        Context requireContext = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        companion2.resetBluetoothServicesAndConfigurations(requireContext);
                    } else if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), "android.permission.BLUETOOTH_CONNECT")) {
                        F2();
                    } else {
                        j0();
                    }
                } else {
                    Dashboard2Utils.Companion companion3 = Dashboard2Utils.Companion;
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    companion3.resetBluetoothServicesAndConfigurations(requireContext2);
                }
            } else {
                showOpenBluetoothSettingsDialog();
            }
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        if (companion.isMoyangDevice(requireActivity)) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.dashboard2.fragment.s0
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentHome.R2(FragmentHome.this);
                }
            }, 15000L);
            return;
        }
        SwipeRefreshLayout swipeRefreshLayout = q0().swipeContainer;
        if (swipeRefreshLayout == null) {
            return;
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    public final void R0() {
        FragmentHomeBinding q0;
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding;
        ImageView imageView;
        int i2;
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding2;
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding3;
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding4;
        TextView textView;
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding5;
        TextView textView2;
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding6;
        Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        long lastCompleteDataSyncTimestamp = companion.getInstance(requireActivity).getLastCompleteDataSyncTimestamp();
        if (lastCompleteDataSyncTimestamp > 0) {
            FragmentHomeBinding q02 = q0();
            TextView textView3 = (q02 == null || (connectedDeviceInfoCardDashboardBinding6 = q02.syncStatusLayout) == null) ? null : connectedDeviceInfoCardDashboardBinding6.tvLastSyncTime;
            if (textView3 != null) {
                textView3.setText(AppUtils.formatDate(new Date(lastCompleteDataSyncTimestamp), "hh:mm aa"));
            }
            if (DateUtils.isToday(lastCompleteDataSyncTimestamp)) {
                FragmentHomeBinding q03 = q0();
                if (q03 != null && (connectedDeviceInfoCardDashboardBinding5 = q03.syncStatusLayout) != null && (textView2 = connectedDeviceInfoCardDashboardBinding5.tvLastSyncDate) != null) {
                    gone(textView2);
                }
            } else {
                FragmentHomeBinding q04 = q0();
                if (q04 != null && (connectedDeviceInfoCardDashboardBinding4 = q04.syncStatusLayout) != null && (textView = connectedDeviceInfoCardDashboardBinding4.tvLastSyncDate) != null) {
                    visible(textView);
                }
                FragmentHomeBinding q05 = q0();
                TextView textView4 = (q05 == null || (connectedDeviceInfoCardDashboardBinding3 = q05.syncStatusLayout) == null) ? null : connectedDeviceInfoCardDashboardBinding3.tvLastSyncDate;
                if (textView4 != null) {
                    textView4.setText(AppUtils.formatDate(new Date(lastCompleteDataSyncTimestamp), "dd MMM yy"));
                }
            }
        }
        BatteryLevelData batteryLevelData = UserDataManager.getInstance(requireContext()).getBatteryLevelData();
        if (batteryLevelData != null && (i2 = batteryLevelData.batteryLevel) > 0 && i2 <= 100) {
            FragmentHomeBinding q06 = q0();
            TextView textView5 = (q06 == null || (connectedDeviceInfoCardDashboardBinding2 = q06.syncStatusLayout) == null) ? null : connectedDeviceInfoCardDashboardBinding2.tvBatteryLevel;
            if (textView5 != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(batteryLevelData.batteryLevel);
                sb.append('%');
                textView5.setText(sb.toString());
            }
            p2(batteryLevelData.batteryLevel);
        }
        FragmentHomeBinding q07 = q0();
        ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding7 = q07 != null ? q07.syncStatusLayout : null;
        if (connectedDeviceInfoCardDashboardBinding7 != null) {
            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            connectedDeviceInfoCardDashboardBinding7.setWatchName(companion2.getWatchName(requireActivity2));
        }
        DeviceUtils.Companion companion3 = DeviceUtils.Companion;
        DeviceType deviceType = BleApiManager.getInstance(requireContext()).getDeviceType();
        Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(requireContext()).deviceType");
        DeviceRemoteConfig.DeviceModelsBean deviceModelFromDeviceType = companion3.getDeviceModelFromDeviceType(deviceType);
        if (deviceModelFromDeviceType == null || (q0 = q0()) == null || (connectedDeviceInfoCardDashboardBinding = q0.syncStatusLayout) == null || (imageView = connectedDeviceInfoCardDashboardBinding.ivWatch) == null) {
            return;
        }
        imageView.setImageDrawable(getResources().getDrawable(companion3.getWatchModelImageStraight(deviceModelFromDeviceType)));
    }

    public final void R1() {
        if (!AppUtils.isNetConnected(requireActivity())) {
            Toast.makeText(requireActivity(), R.string.check_internet, 1).show();
            return;
        }
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToWatchfaceStudioActivity();
        }
        Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        companion.getInstance(requireActivity).saveIsUserCheckedWatchfaceStudio(true);
        N0();
    }

    public final void S0() {
        View root;
        View root2;
        SwipeRefreshLayout swipeRefreshLayout = q0().swipeContainer;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.coveiot.android.dashboard2.fragment.r0
                @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
                public final void onRefresh() {
                    FragmentHome.T0(FragmentHome.this);
                }
            });
        }
        q0().calSelectionView.tvDate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.U0(FragmentHome.this, view);
            }
        });
        q0().calSelectionView.imgArrowRight.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.V0(FragmentHome.this, view);
            }
        });
        q0().calSelectionView.imgArrowLeft.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.x0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.W0(FragmentHome.this, view);
            }
        });
        q0().lBt3State.clMain.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.X0(FragmentHome.this, view);
            }
        });
        q0().profileLayout.clMain.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.Y0(FragmentHome.this, view);
            }
        });
        q0().toolbar.imgVProfilePic.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.Z0(FragmentHome.this, view);
            }
        });
        q0().activities700plus.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.a1(FragmentHome.this, view);
            }
        });
        q0().tvEditVitalCards.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.b1(FragmentHome.this, view);
            }
        });
        q0().trackMoreVitals.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.c1(FragmentHome.this, view);
            }
        });
        q0().indusIndWellnessCrew.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.d1(FragmentHome.this, view);
            }
        });
        q0().myBuddiesFtu.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.u0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.e1(FragmentHome.this, view);
            }
        });
        q0().viewMoreBuddies.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.f1(FragmentHome.this, view);
            }
        });
        E2();
        q0().toolbar.imgWeatherDash.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.g1(FragmentHome.this, view);
            }
        });
        q0().viewFitnessDashboardHeader.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.w0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.h1(FragmentHome.this, view);
            }
        });
        q0().tvFitnessJourneyViewMore.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.z0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.i1(FragmentHome.this, view);
            }
        });
        q0().personalizedWatchfaceContainer.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.j1(FragmentHome.this, view);
            }
        });
        q0().syncStatusLayout.clManualSync.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.k1(FragmentHome.this, view);
            }
        });
        q0().watchSettingsBig.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.l1(FragmentHome.this, view);
            }
        });
        q0().watchfaceStudioBig.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.m1(FragmentHome.this, view);
            }
        });
        q0().navigationBanner.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.n1(FragmentHome.this, view);
            }
        });
        q0().watchfaceStudioBigTop.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.o1(FragmentHome.this, view);
            }
        });
        q0().fitnessOverviewCl.tvStepsCount.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.p1(FragmentHome.this, view);
            }
        });
        q0().fitnessSleep.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.q1(FragmentHome.this, view);
            }
        });
        q0().fitnessCalorie.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.r1(FragmentHome.this, view);
            }
        });
        q0().fitnessDistance.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.y0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentHome.s1(FragmentHome.this, view);
            }
        });
        ListItemWeekPlanLayoutBinding listItemWeekPlanLayoutBinding = q0().fitnessJourneyOngoing;
        if (listItemWeekPlanLayoutBinding != null && (root2 = listItemWeekPlanLayoutBinding.getRoot()) != null) {
            root2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.y
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentHome.t1(FragmentHome.this, view);
                }
            });
        }
        ExclusiveCardBoatCoinsBinding exclusiveCardBoatCoinsBinding = q0().boatCoinsLayout;
        if (exclusiveCardBoatCoinsBinding != null && (root = exclusiveCardBoatCoinsBinding.getRoot()) != null) {
            root.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.g0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentHome.u1(FragmentHome.this, view);
                }
            });
        }
        ConstraintLayout constraintLayout = q0().toolbar.clboAtCoins;
        if (constraintLayout != null) {
            constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentHome.v1(FragmentHome.this, view);
                }
            });
        }
    }

    public final void S1() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).navigateToWellnessCrew();
        }
    }

    public final void S2(boolean z2) {
        LifecycleCoroutineScope lifecycleScope;
        if (!isAdded() || (lifecycleScope = LifecycleOwnerKt.getLifecycleScope(this)) == null) {
            return;
        }
        kotlinx.coroutines.e.e(lifecycleScope, Dispatchers.getMain(), null, new c0(z2, null), 2, null);
    }

    public final void T1(boolean z2) {
        if (!SessionManager.getInstance(requireContext()).getLastBt3Status() && z2 != SessionManager.getInstance(requireContext()).getLastBt3Status()) {
            DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_BTCALLING_CONNECT_SUCCESS.getValue(), null);
        }
        SessionManager.getInstance(requireContext()).setLastBt3Status(z2);
    }

    public final void T2(StepsDataModel stepsDataModel) {
        if (stepsDataModel == null || !isAdded()) {
            return;
        }
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d0(stepsDataModel, null), 2, null);
    }

    public final void U1() {
        String lastLocationLatLng;
        if (!AppUtils.isNetConnected(requireActivity())) {
            Toast.makeText(requireActivity(), R.string.check_internet, 1).show();
        } else if (ContextCompat.checkSelfPermission(requireActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(requireActivity(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            WeatherAppPreferenceManager companion2 = companion.getInstance(requireActivity);
            Intrinsics.checkNotNull(companion2);
            Boolean isWeatherEnabled = companion2.isWeatherEnabled();
            Intrinsics.checkNotNull(isWeatherEnabled);
            if (isWeatherEnabled.booleanValue()) {
                WeatherPreferenceManager.Companion companion3 = WeatherPreferenceManager.Companion;
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                WeatherPreferenceManager companion4 = companion3.getInstance(requireActivity2);
                List split$default = (companion4 == null || (lastLocationLatLng = companion4.getLastLocationLatLng()) == null) ? null : StringsKt__StringsKt.split$default((CharSequence) lastLocationLatLng, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null);
                if (split$default != null) {
                    if (split$default.size() <= 1) {
                        Toast.makeText(requireActivity(), requireActivity().getString(R.string.unable_to_fetch_location), 0).show();
                        return;
                    }
                } else {
                    Toast.makeText(requireActivity(), requireActivity().getString(R.string.unable_to_fetch_location), 0).show();
                    return;
                }
            }
            Intent intent = new Intent(requireActivity(), WeatherActivity.class);
            intent.putExtra(ThemeConstants.IS_WEATHER_FROM_SETTING_SCREEN.getValue(), false);
            startActivity(intent);
        } else {
            requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, this.C);
        }
    }

    public final void U2(boolean z2) {
        if (isAdded()) {
            MutableLiveData<EntityPreparationPlan> currentPlanLiveData = getViewModelCurrentPlanDashboard().getCurrentPlanLiveData();
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
            currentPlanLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$updatePlanProgressUi$$inlined$observe$1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(T t2) {
                    EntityPreparationPlan entityPreparationPlan = (EntityPreparationPlan) t2;
                    if (!FragmentHome.this.isAdded() || entityPreparationPlan == null) {
                        return;
                    }
                    try {
                        if (entityPreparationPlan.getStartDate() != null) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.e0(entityPreparationPlan, FragmentHome.this, null), 2, null);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new f0(null), 2, null);
        }
    }

    public final void V1() {
        PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.getInstance(requireContext).optOutCurrentPlan(new PreparationPlanRepository.OptoutListner() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$optOutCurrentPlan$1
            @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.OptoutListner
            public void onFailure(@NotNull String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                Toast.makeText(FragmentHome.this.requireContext(), message, 1).show();
            }

            @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.OptoutListner
            public void onPlanOptout() {
                PreparationPlanRepository.Companion companion2 = PreparationPlanRepository.Companion;
                Context requireContext2 = FragmentHome.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                companion2.getInstance(requireContext2).getCurrentPlanFromServer(new FragmentHome$optOutCurrentPlan$1$onPlanOptout$1(FragmentHome.this));
            }
        });
    }

    public final void W1() {
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isKahaBTCallSupported() && isAdded()) {
            FragmentHomeViewModel fragmentHomeViewModel = this.o;
            if (fragmentHomeViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                fragmentHomeViewModel = null;
            }
            LiveData<Integer> bT3ConnectionStatusLiveData = fragmentHomeViewModel.getBT3ConnectionStatusLiveData();
            if (bT3ConnectionStatusLiveData != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                bT3ConnectionStatusLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerBTConnectionLiveData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        int intValue = ((Number) t2).intValue();
                        String tag = FragmentHome.this.getTAG();
                        LogHelper.i(tag, "BT3 bonding state " + intValue);
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        Context requireContext = FragmentHome.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        if (!companion.isOPPDevice(requireContext)) {
                            FragmentHome.this.S2(intValue == 12);
                        } else if (intValue != 12) {
                            FragmentHome.this.S2(false);
                        } else if (SessionManager.getInstance(FragmentHome.this.requireContext()).getAclBt3LastReceivedConnectionStatus() == null || (SessionManager.getInstance(FragmentHome.this.requireContext()).getAclBt3LastReceivedConnectionStatus().getConnectionStatus() == ConnectionStatus.CONNECTED && Intrinsics.areEqual(SessionManager.getInstance(FragmentHome.this.requireContext()).getAclBt3LastReceivedConnectionStatus().getMacAddress(), UserDataManager.getInstance(FragmentHome.this.requireContext()).getConnectedBTCallDeviceMac()))) {
                            FragmentHome.this.S2(true);
                        } else {
                            FragmentHome.this.S2(false);
                        }
                    }
                });
            }
        }
    }

    public final void W2(double d2, ArrayList<String> arrayList, String str) {
        ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.R;
        if (viewModelWorkoutFeedback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
            viewModelWorkoutFeedback = null;
        }
        viewModelWorkoutFeedback.updateFitnessPlanProgress(true, d2, arrayList, str, new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$updateProgressWhenPlanOngoing$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseClass commonResponseClass) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (coveApiErrorModel != null) {
                    coveApiErrorModel.getCode();
                }
            }
        });
    }

    public final void X1() {
        DataSyncViewModel dataSyncViewModel = this.p;
        if (dataSyncViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
            dataSyncViewModel = null;
        }
        MutableLiveData<Integer> batteryLiveData = dataSyncViewModel.getBatteryLiveData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        batteryLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerBatteryLiveData$$inlined$observe$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t2) {
                Integer num = (Integer) t2;
                if (FragmentHome.this.isAdded()) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.n(num, FragmentHome.this, null), 2, null);
                }
            }
        });
    }

    public final void X2(int i2) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new g0(i2, null), 2, null);
        }
    }

    public final void Y1() {
        if (isAdded()) {
            FragmentHomeViewModel fragmentHomeViewModel = this.o;
            if (fragmentHomeViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                fragmentHomeViewModel = null;
            }
            MutableLiveData<List<DoMoreWithYourWatchDataModel>> doMoreWithYourWatchLiveData = fragmentHomeViewModel.getDoMoreWithYourWatchLiveData();
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
            doMoreWithYourWatchLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerDoMoreWithYourWatchLiveData$$inlined$observe$1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(T t2) {
                    List list = (List) t2;
                    if (FragmentHome.this.isAdded()) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.o(list, FragmentHome.this, null), 2, null);
                    }
                }
            });
        }
    }

    public final void Y2(Integer num) {
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(requireActivity()).getFitnessGoalStepsData();
        Integer num2 = fitnessGoalStepsData != null ? fitnessGoalStepsData.target : null;
        if (num2 == null || num2.intValue() <= 0) {
            return;
        }
        q0().fitnessOverviewCl.setStepsGoal(num2);
        if (num != null) {
            int intValue = (num.intValue() * 100) / num2.intValue();
            int i2 = intValue <= 100 ? intValue : 100;
            q0().fitnessOverviewCl.progressBar.setProgress(i2);
            if (i2 > this.Y) {
                Z2(num.intValue());
            }
            this.Y = i2;
        }
    }

    public final void Z1() {
        if (isAdded()) {
            EnergyMeterDataViewModel energyMeterDataViewModel = this.w;
            if (energyMeterDataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEnergyMeterViewModel");
                energyMeterDataViewModel = null;
            }
            MutableLiveData<EnergyMeterData> energyMeterLiveData = energyMeterDataViewModel.getEnergyMeterLiveData();
            if (energyMeterLiveData != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                energyMeterLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerEnergyMeterLiveData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        EnergyMeterData energyMeterData = (EnergyMeterData) t2;
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.p(energyMeterData, FragmentHome.this, null), 2, null);
                        }
                    }
                });
            }
        }
    }

    public final void Z2(int i2) {
        if (i2 > 0 && this.Q) {
            View root = q0().progressMotivationalMessage.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.progressMotivationalMessage.root");
            visible(root);
        } else {
            View root2 = q0().progressMotivationalMessage.getRoot();
            Intrinsics.checkNotNullExpressionValue(root2, "binding.progressMotivationalMessage.root");
            gone(root2);
        }
        SpannableString spannableString = new SpannableString(getString(R.string.keep_going));
        spannableString.setSpan(new LinearGradientForegroundSpan(Color.parseColor("#f9834e"), Color.parseColor("#fdb343"), q0().progressMotivationalMessage.tvHeader.getLineHeight()), 0, spannableString.length(), 33);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) spannableString);
        q0().progressMotivationalMessage.tvHeader.setText(spannableStringBuilder);
        TextView textView = q0().progressMotivationalMessage.tvStatus;
        StepsDataHelper stepsDataHelper = StepsDataHelper.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        textView.setText(stepsDataHelper.getMotivationalInfoBasedOnProgress(i2, requireContext));
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

    public final void a2() {
        MutableLiveData<PlanStatus> planStatusLiveData;
        if (!isAdded() || (planStatusLiveData = getViewModelCurrentPlanDashboard().getPlanStatusLiveData()) == null) {
            return;
        }
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        planStatusLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerFitnessPlanLiveData$$inlined$observe$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t2) {
                PlanStatus planStatus = (PlanStatus) t2;
                if (FragmentHome.this.isAdded()) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome$registerFitnessPlanLiveData$1$1(planStatus, FragmentHome.this, null), 2, null);
                }
            }
        });
    }

    public final void b2() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).registerForConnectionStatusUpdate(this);
        }
    }

    public final void c2() {
        if (isAdded()) {
            HRVDataViewModel hRVDataViewModel = this.v;
            if (hRVDataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHRVDataViewModel");
                hRVDataViewModel = null;
            }
            MutableLiveData<HRVData> hRVLiveData = hRVDataViewModel.getHRVLiveData();
            if (hRVLiveData != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                hRVLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerHRVLiveData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        HRVData hRVData = (HRVData) t2;
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.q(hRVData, FragmentHome.this, null), 2, null);
                        }
                    }
                });
            }
        }
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeAdapter.ChallengeClickListener
    public void challengeClick(@NotNull BuddiesChallengeRes.Item challenge, boolean z2) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        if (isAdded()) {
            if (!AppUtils.isNetConnected(requireContext())) {
                showNoInternetDialog();
                return;
            }
            DataSyncViewModel dataSyncViewModel = this.p;
            FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = null;
            if (dataSyncViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                dataSyncViewModel = null;
            }
            if (!dataSyncViewModel.getSyncState().isSyncing()) {
                this.d0 = challenge.getChallengeId().toString();
                this.e0 = challenge.getType();
                if (z2) {
                    showProgress(true);
                    FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel2 = this.V;
                    if (fitnessChallengeDetailsViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeDetailsViewModel");
                    } else {
                        fitnessChallengeDetailsViewModel = fitnessChallengeDetailsViewModel2;
                    }
                    fitnessChallengeDetailsViewModel.joinFitnessChallenge(new JoinChallengeReq(challenge.getChallengeId(), challenge.getType()));
                    return;
                }
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                a aVar = new a(challenge);
                Intent intent = new Intent(requireContext, FitnessChallengeDetails.class);
                aVar.invoke((a) intent);
                requireContext.startActivity(intent, null);
                return;
            }
            Toast.makeText(requireContext(), requireContext().getString(R.string.syncing_please_wait), 0).show();
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
                String string3 = getString(com.coveiot.android.theme.R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.android.theme.R.string.ok)");
                bottomSheetThemeDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.b0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentHome.k0(FragmentHome.this, view);
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

    public final void d2() {
        if (isAdded()) {
            HeartRateDataViewModel heartRateDataViewModel = this.s;
            if (heartRateDataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHeartRateDataViewModel");
                heartRateDataViewModel = null;
            }
            MutableLiveData<HeartRateData> heartRateLiveData = heartRateDataViewModel.getHeartRateLiveData();
            if (heartRateLiveData != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                heartRateLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerHeartRateLiveData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        HeartRateData heartRateData = (HeartRateData) t2;
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.r(heartRateData, FragmentHome.this, null), 2, null);
                        }
                    }
                });
            }
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.DoMoreWithYourWatchItemClickListener
    public void doMoreWithYourWatchItemClicked(@Nullable DoMoreWithYourWatchFeatureType doMoreWithYourWatchFeatureType) {
        if (!isAdded() || doMoreWithYourWatchFeatureType == null) {
            return;
        }
        String name = doMoreWithYourWatchFeatureType.name();
        switch (name.hashCode()) {
            case -1789021807:
                if (name.equals("BUILD_FITNESS_PLAN")) {
                    if (AppUtils.isNetConnected(requireContext())) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new b(null), 2, null);
                        return;
                    } else {
                        showNoInternetDialog();
                        return;
                    }
                }
                return;
            case -1498347065:
                if (name.equals("SPORT_SCORE")) {
                    N1();
                    return;
                }
                return;
            case -1155766972:
                if (name.equals("TAP_AND_PAY")) {
                    O1();
                    return;
                }
                return;
            case 761971289:
                if (name.equals("WELLNESS_CREW_SETUP")) {
                    S1();
                    return;
                }
                return;
            case 780751339:
                if (name.equals("SOS_SETTINGS")) {
                    M1(SOSCleverTapConstants.SOS_HP_BANNER.getValue());
                    return;
                }
                return;
            case 1961809932:
                if (name.equals("SETUP_ALEXA_CONNECT")) {
                    D1();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final void e2() {
        b2();
        W1();
        X1();
        l2();
        j2();
        i2();
        d2();
        g2();
        k2();
        c2();
        Z1();
        f2();
        m2();
        h2();
        Y1();
        p0();
        r0();
        a2();
        s0();
    }

    public final void f2() {
        if (isAdded()) {
            RespiratoryRateDataViewModel respiratoryRateDataViewModel = this.x;
            if (respiratoryRateDataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRespiratoryRateViewModel");
                respiratoryRateDataViewModel = null;
            }
            MutableLiveData<RespiratoryRateData> respiratoryLiveData = respiratoryRateDataViewModel.getRespiratoryLiveData();
            if (respiratoryLiveData != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                respiratoryLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerRespiratoryRateLiveData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        RespiratoryRateData respiratoryRateData = (RespiratoryRateData) t2;
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.s(respiratoryRateData, FragmentHome.this, null), 2, null);
                        }
                    }
                });
            }
        }
    }

    public final void g2() {
        if (isAdded()) {
            SPO2DataViewModel sPO2DataViewModel = this.t;
            if (sPO2DataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSPO2DataViewModel");
                sPO2DataViewModel = null;
            }
            LiveData<SPO2Data> sPO2LiveData = sPO2DataViewModel.getSPO2LiveData();
            if (sPO2LiveData != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                sPO2LiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerSPO2LiveData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        SPO2Data sPO2Data = (SPO2Data) t2;
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.t(sPO2Data, FragmentHome.this, null), 2, null);
                        }
                    }
                });
            }
        }
    }

    @NotNull
    public final BluetoothAdapter getBluetoothAdapter() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bluetoothAdapter");
        return null;
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
    public final List<DoMoreWithYourWatchDataModel> getDoMoreWithYourWatchGridDataModels() {
        return this.L;
    }

    @Nullable
    public final DynamicTabAdapter getDynamicTabAdapter() {
        return this.P;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getEnableBluetoothDialog() {
        return this.Z;
    }

    @NotNull
    public final FragmentHomeHelper getFragmentHomeHelper() {
        return this.N;
    }

    public final int getLastStepsGoalAcheivedPercentage() {
        return this.Y;
    }

    @Nullable
    public final Animation getRotateAnimation() {
        return this.O;
    }

    public final Calendar getSelectedCalendar() {
        return this.I;
    }

    @NotNull
    public final List<FitnessVitalsDataModel> getSelectedFitnessVitalsDataModels() {
        return this.K;
    }

    @NotNull
    public final List<FitnessVitalsDataModel> getSelectedFitnessVitalsGridDataModels() {
        return this.J;
    }

    @NotNull
    public final SmartGridViewModel getSmartGridViewModel() {
        SmartGridViewModel smartGridViewModel = this.smartGridViewModel;
        if (smartGridViewModel != null) {
            return smartGridViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("smartGridViewModel");
        return null;
    }

    @NotNull
    public final SuccessResultListener getStepsLiveDataListener() {
        return this.b0;
    }

    @NotNull
    public final String getTAG() {
        return this.m;
    }

    @Nullable
    public final DashboardTopFeaturesViewAdapter getTopFeaturesGridAdapter() {
        return this.X;
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

    @Nullable
    public final DashboardVitalsGridviewAdapter getVitalsGridAdapter() {
        return this.M;
    }

    public final void h0() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new FragmentHome$callRemoteConfigAPIWithWeather$1(this, null), 2, null);
    }

    public final void h2() {
        if (isAdded()) {
            FragmentHomeViewModel fragmentHomeViewModel = this.o;
            if (fragmentHomeViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                fragmentHomeViewModel = null;
            }
            MutableLiveData<SelectedFitnessVitalsDataModel> selectedFitnessVitalsLiveData = fragmentHomeViewModel.getSelectedFitnessVitalsLiveData();
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
            selectedFitnessVitalsLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerSelectedFitnessVitalsLiveData$$inlined$observe$1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(T t2) {
                    SelectedFitnessVitalsDataModel selectedFitnessVitalsDataModel = (SelectedFitnessVitalsDataModel) t2;
                    if (FragmentHome.this.isAdded()) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.u(selectedFitnessVitalsDataModel, FragmentHome.this, null), 2, null);
                    }
                }
            });
        }
    }

    public final void i0() {
        Integer bTDeviceBondedState;
        DataSyncViewModel dataSyncViewModel = this.p;
        if (dataSyncViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
            dataSyncViewModel = null;
        }
        if (dataSyncViewModel.checkIsSyncing() || (bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(UserDataManager.getInstance(requireActivity()).getConnectedBTCallDeviceMac(), requireActivity())) == null || bTDeviceBondedState.intValue() != 12) {
            return;
        }
        BT3CallUtils bT3CallUtils = BT3CallUtils.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        if (bT3CallUtils.isCallNotificationFeatureEnabled(requireActivity)) {
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            if (bT3CallUtils.isCallPermissionAvailable(requireActivity2)) {
                return;
            }
        }
        if (SessionManager.getInstance(requireActivity()).getBT3CallFTUShown() <= 0 || !DateUtils.isToday(SessionManager.getInstance(requireActivity()).getBT3CallFTUShown())) {
            H2();
        }
    }

    public final void i2() {
        if (isAdded()) {
            SleepDataViewModel sleepDataViewModel = this.r;
            if (sleepDataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSleepDataViewModel");
                sleepDataViewModel = null;
            }
            LiveData<List<SleepDataModelForLastNight>> lastNightSleepLiveData = sleepDataViewModel.getLastNightSleepLiveData();
            if (lastNightSleepLiveData != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                lastNightSleepLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerSleepLiveData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        List list = (List) t2;
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getIO(), null, new FragmentHome$registerSleepLiveData$1$1(list, FragmentHome.this, null), 2, null);
                        }
                    }
                });
            }
        }
    }

    public final void initData() {
        q0().setLifecycleOwner(this);
        y0();
        x0();
        R0();
        if (!AppUtils.isNetConnected(requireActivity())) {
            N0();
        }
        Q0();
        P0();
        x1();
        L0();
        D0();
        A0();
        y1();
        z0();
    }

    public final boolean isBTCallingSupported() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        BleApi bleApi = BleApiManager.getInstance(requireActivity()).getBleApi();
        Boolean valueOf = (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures.isKahaBTCallSupported());
        Intrinsics.checkNotNull(valueOf);
        if (!valueOf.booleanValue()) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (!companion.isTouchELXDevice(requireContext) || BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                if (!companion.isEastApexDevice(requireContext2)) {
                    Context requireContext3 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    if (!companion.isSmaJieieDevice(requireContext3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final void j0() {
        if (Build.VERSION.SDK_INT >= 31) {
            String[] permissions = PermissionUtils.checkPermissionsHasGranted(requireContext(), new String[]{"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"});
            Intrinsics.checkNotNullExpressionValue(permissions, "permissions");
            if (!(permissions.length == 0)) {
                ActivityCompat.requestPermissions(requireActivity(), permissions, this.D);
            }
        }
    }

    public final void j2() {
        BleApi bleApi;
        LiveData<LiveStepsData> registerLiveStepsData;
        StepsDataViewModel stepsDataViewModel = null;
        if (isAdded()) {
            DataSyncViewModel dataSyncViewModel = this.p;
            if (dataSyncViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                dataSyncViewModel = null;
            }
            MutableLiveData<StepsDataModel> stepsLiveDataOnPreference = dataSyncViewModel.getStepsLiveDataOnPreference();
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
            stepsLiveDataOnPreference.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerStepsLiveData$$inlined$observe$1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(T t2) {
                    StepsDataModel stepsDataModel = (StepsDataModel) t2;
                    if (FragmentHome.this.isAdded()) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.v(stepsDataModel, FragmentHome.this, null), 2, null);
                    }
                }
            });
        }
        if (isAdded() && (bleApi = BleApiManager.getInstance(requireActivity()).getBleApi()) != null && (registerLiveStepsData = bleApi.registerLiveStepsData()) != null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            registerLiveStepsData.observe(requireActivity, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerStepsLiveData$$inlined$observe$2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(T t2) {
                    LiveStepsData liveStepsData = (LiveStepsData) t2;
                    if (FragmentHome.this.isAdded()) {
                        StepsDataHelper stepsDataHelper = StepsDataHelper.INSTANCE;
                        Context applicationContext = FragmentHome.this.requireActivity().getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "requireActivity().applicationContext");
                        stepsDataHelper.saveLiveStepsDataToPreferenceFromWatch(applicationContext, liveStepsData, FragmentHome.this.getStepsLiveDataListener());
                    }
                }
            });
        }
        if (isAdded()) {
            StepsDataViewModel stepsDataViewModel2 = this.q;
            if (stepsDataViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mStepsDataViewModel");
            } else {
                stepsDataViewModel = stepsDataViewModel2;
            }
            MutableLiveData<Boolean> stepsControlCommandLiveData = stepsDataViewModel.getStepsControlCommandLiveData();
            if (stepsControlCommandLiveData != null) {
                LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "viewLifecycleOwner");
                stepsControlCommandLiveData.observe(viewLifecycleOwner2, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerStepsLiveData$$inlined$observe$3
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        State state;
                        State state2;
                        State state3;
                        Boolean it = (Boolean) t2;
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        if (it.booleanValue()) {
                            state = FragmentHome.this.H;
                            if (state == State.INPROGRESS) {
                                state2 = FragmentHome.this.G;
                                State state4 = State.STOPPED;
                                if (state2 == state4) {
                                    FragmentHome.this.H = State.STARTED;
                                    return;
                                }
                                state3 = FragmentHome.this.G;
                                if (state3 == State.STARTED) {
                                    FragmentHome.this.H = state4;
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public final void k2() {
        if (isAdded()) {
            StressDataViewModel stressDataViewModel = this.u;
            if (stressDataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mStressDataViewModel");
                stressDataViewModel = null;
            }
            MutableLiveData<StressData> stressLiveData = stressDataViewModel.getStressLiveData();
            if (stressLiveData != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                stressLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerStressLiveData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        StressData stressData = (StressData) t2;
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.w(stressData, FragmentHome.this, null), 2, null);
                        }
                    }
                });
            }
        }
    }

    public final void l0(int i2, int i3) {
        LinearLayout linearLayout = q0().challengeLinearLayoutDots;
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

    public final void l2() {
        if (isAdded()) {
            DataSyncViewModel dataSyncViewModel = this.p;
            if (dataSyncViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDataSyncViewModel");
                dataSyncViewModel = null;
            }
            MutableLiveData<SyncState> syncStateLiveData = dataSyncViewModel.getSyncStateLiveData();
            if (syncStateLiveData != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                syncStateLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerSyncStateLiveData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        SyncState syncState = (SyncState) t2;
                        if (!FragmentHome.this.isAdded() || syncState == null) {
                            return;
                        }
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.x(syncState, FragmentHome.this, null), 2, null);
                    }
                });
            }
        }
    }

    public final void m0() {
        if (getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity).deRegisterForConnectionStatusUpdate(this);
        }
    }

    public final void m2() {
        if (isAdded()) {
            TemperatureDataViewModel temperatureDataViewModel = this.y;
            if (temperatureDataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTemperatureDataViewModel");
                temperatureDataViewModel = null;
            }
            MutableLiveData<TemperatureData> temperatureLiveData = temperatureDataViewModel.getTemperatureLiveData();
            if (temperatureLiveData != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                temperatureLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerTemperatureLiveData$$inlined$observe$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t2) {
                        TemperatureData temperatureData = (TemperatureData) t2;
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new FragmentHome.y(temperatureData, FragmentHome.this, null), 2, null);
                        }
                    }
                });
            }
        }
    }

    public final void n0() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
    }

    public final void n2() {
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns;
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns2 = this.a0;
        if (bottomSheetDialogImageTitleMessageTwoVerticalBtns2 != null) {
            boolean z2 = true;
            if (bottomSheetDialogImageTitleMessageTwoVerticalBtns2 == null || !bottomSheetDialogImageTitleMessageTwoVerticalBtns2.isShowing()) {
                z2 = false;
            }
            if (z2 && (bottomSheetDialogImageTitleMessageTwoVerticalBtns = this.a0) != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns.dismiss();
            }
            this.a0 = null;
        }
    }

    public final void o0() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new FragmentHome$fetchRemoteConfig$1(this, null), 2, null);
    }

    public final void o2() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new z(null), 2, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        if (i2 == this.E) {
            if (i3 == -1 && isAdded()) {
                FragmentHomeViewModel fragmentHomeViewModel = this.o;
                if (fragmentHomeViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    fragmentHomeViewModel = null;
                }
                fragmentHomeViewModel.getSelectedFitnessVitals();
            }
        } else if (i2 == this.F && intent != null) {
            if (i3 == -1 && isAdded()) {
                q0().setIsFitnessPlanOngoing(Boolean.FALSE);
            }
        } else {
            super.onActivityResult(i2, i3, intent);
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.ConnectionStatusUpdateListener
    public void onConnectionUpdated(@Nullable ConnectionStatus connectionStatus) {
        String str = this.m;
        StringBuilder sb = new StringBuilder();
        sb.append("Ble connection sate ");
        sb.append(connectionStatus != null ? connectionStatus.name() : null);
        LogHelper.i(str, sb.toString());
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new FragmentHome$onConnectionUpdated$1(connectionStatus, this, null), 2, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.n = FragmentHomeBinding.inflate(inflater, viewGroup, false);
        return q0().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        m0();
        SessionManager.getInstance(requireActivity()).removeFirebaseRemoteConfigChangeListener(this);
        P2();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        if (isAdded()) {
            Toast.makeText(requireContext(), str, 1).show();
        }
    }

    @Override // com.coveiot.covepreferences.listener.FirebaseRemoteConfigChangeListener
    public void onFirebaseConfigChanged() {
        if (!isAdded() || getView() == null) {
            return;
        }
        N0();
        L0();
        D0();
        y1();
        z0();
    }

    @Override // com.coveiot.android.dashboard2.adapter.DashboardVitalsGridviewAdapter.ItemClickListener
    public void onFitnessVitalItemClicked(@NotNull String fitnessVitalsType) {
        Intrinsics.checkNotNullParameter(fitnessVitalsType, "fitnessVitalsType");
        I1(fitnessVitalsType);
    }

    @Override // com.coveiot.android.dashboard2.listener.SyncInterruptionListener
    public void onInterrupt(@NotNull SyncInterruptionType type) {
        Intrinsics.checkNotNullParameter(type, "type");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i2, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if (i2 == this.D) {
            Integer firstOrNull = ArraysKt___ArraysKt.firstOrNull(grantResults);
            if (firstOrNull == null || firstOrNull.intValue() != 0) {
                Context requireContext = requireContext();
                Intrinsics.checkNotNull(requireContext);
                Toast.makeText(requireContext, com.coveiot.android.weather.R.string.please_enable_bluetooth_permission, 1).show();
                return;
            }
            BleApiManager.getInstance(requireContext()).getBleApi().restartService();
        } else if (i2 == this.C) {
            Integer firstOrNull2 = ArraysKt___ArraysKt.firstOrNull(grantResults);
            if (firstOrNull2 == null || firstOrNull2.intValue() != 0) {
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNull(requireContext2);
                Toast.makeText(requireContext2, com.coveiot.android.weather.R.string.please_enable_location_permission, 1).show();
                return;
            }
            ImageView imageView = q0().toolbar.imgWeatherDash;
            if (imageView != null) {
                imageView.performClick();
            }
        } else {
            super.onRequestPermissionsResult(i2, permissions, grantResults);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        CoveEventBusManager.getInstance().getEventBus().register(this);
        y0();
        x0();
        M0();
        Q0();
        P0();
        L0();
        D0();
        E0();
        z0();
        F0();
        K0();
    }

    @Override // com.coveiot.android.dashboard2.fragment.SetupYourWatchItemFragment.SetupYourWatchItemSelectedListener
    public void onSetupYourWatchItemSelected(@NotNull SetupYourWatchOption setupYourWatchOption) {
        Intrinsics.checkNotNullParameter(setupYourWatchOption, "setupYourWatchOption");
        if (isAdded()) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[setupYourWatchOption.ordinal()];
            if (i2 == 1) {
                if (!AppUtils.isNetConnected(requireActivity())) {
                    Toast.makeText(requireActivity(), R.string.check_internet, 1).show();
                    return;
                }
                Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                companion.getInstance(requireActivity).saveIsUserTappedWatchfaceStudioInDashboard(true);
                DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_STUDIOBANNER_TAPPED.getValue(), null);
                R1();
            } else if (i2 == 2) {
                DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_SETTINGBANNER_TAPPED.getValue(), null);
                Q1();
            } else if (i2 == 3) {
                DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_WFBANNER_TAPPED.getValue(), null);
                P1();
            } else if (i2 != 4) {
            } else {
                N1();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        if (isAdded()) {
            dismissProgress();
            String string = requireContext().getString(R.string.woah_challenge_joined);
            Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…ng.woah_challenge_joined)");
            K2(string);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        w0();
        w1();
        e2();
        y2();
        initData();
        O2();
        S0();
        x2();
        u2();
        B2();
        FragmentHomeViewModel fragmentHomeViewModel = this.o;
        FragmentHomeViewModel fragmentHomeViewModel2 = null;
        if (fragmentHomeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            fragmentHomeViewModel = null;
        }
        fragmentHomeViewModel.getFitnessChallengeFirebaseConfig();
        FragmentHomeViewModel fragmentHomeViewModel3 = this.o;
        if (fragmentHomeViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            fragmentHomeViewModel2 = fragmentHomeViewModel3;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        fragmentHomeViewModel2.getFeatureMappingRemoteConfig(requireContext);
        getSmartGridViewModel().getSmartGridVisibilityConfig();
    }

    @Subscribe
    public final void onWatchBTEnableEventReceived(@NotNull OnWatchBT3EnabledEvent onWatchBT3EnabledEvent) {
        Intrinsics.checkNotNullParameter(onWatchBT3EnabledEvent, "onWatchBT3EnabledEvent");
        String str = this.m;
        LogHelper.i(str, "BT3 connection state " + Boolean.valueOf(onWatchBT3EnabledEvent.isEnabled()));
        S2(onWatchBT3EnabledEvent.isEnabled());
    }

    public final void p0() {
        PromotionsViewModel promotionsViewModel = this.z;
        if (promotionsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPromotionsViewModel");
            promotionsViewModel = null;
        }
        promotionsViewModel.getBestOffersFromServer(new PromotionsViewModel.BestOffersListeners() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$getBestOffersData$1
            @Override // com.coveiot.android.dashboard2.viewmodel.PromotionsViewModel.BestOffersListeners
            public void onFailure(@Nullable String str) {
            }

            @Override // com.coveiot.android.dashboard2.viewmodel.PromotionsViewModel.BestOffersListeners
            public void onOffersLoaded(@Nullable List<BestOffers> list) {
                if (list != null) {
                    if (FragmentHome.this.isAdded()) {
                        FragmentHome.this.q0().setBestOffers(Integer.valueOf(list.size()));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FragmentHome.this.requireContext());
                        linearLayoutManager.setOrientation(0);
                        linearLayoutManager.setReverseLayout(false);
                        FragmentHome.this.q0().tvBestOffersCardContainer.rcvBestOffers.setLayoutManager(linearLayoutManager);
                        FragmentHome.this.q0().tvBestOffersCardContainer.rcvBestOffers.setHasFixedSize(false);
                        FragmentHome.this.q0().tvBestOffersCardContainer.rcvBestOffers.setAdapter(new BestOffersAdapter(list));
                        FragmentHome.this.q0().tvBestOffersCardContainer.rcvBestOffers.scrollToPosition(0);
                        return;
                    }
                    return;
                }
                FragmentHome.this.q0().setBestOffers(0);
            }
        });
    }

    public final void p2(int i2) {
        int i3;
        Drawable drawable;
        if (i2 >= 90) {
            i3 = 5;
        } else {
            boolean z2 = false;
            if (70 <= i2 && i2 < 90) {
                i3 = 4;
            } else {
                if (50 <= i2 && i2 < 70) {
                    i3 = 3;
                } else {
                    if (20 <= i2 && i2 < 50) {
                        z2 = true;
                    }
                    i3 = z2 ? 2 : 1;
                }
            }
        }
        q0().syncStatusLayout.batteryProgressBar.setProgress(i3);
        ProgressBar progressBar = q0().syncStatusLayout.batteryProgressBar;
        if (i3 == 1) {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_red);
        } else if (i3 == 2) {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_orange);
        } else if (i3 == 3) {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_yellow);
        } else if (i3 == 4) {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_green);
        } else if (i3 != 5) {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_green);
        } else {
            drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.progressbar_battery_green);
        }
        progressBar.setProgressDrawable(drawable);
    }

    public final FragmentHomeBinding q0() {
        FragmentHomeBinding fragmentHomeBinding = this.n;
        Intrinsics.checkNotNull(fragmentHomeBinding);
        return fragmentHomeBinding;
    }

    public final void q2() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.T = new FitnessChallengeAdapter(requireContext, this);
        RecyclerView recyclerView = q0().rvFitnessChallenge;
        FitnessChallengeAdapter fitnessChallengeAdapter = this.T;
        if (fitnessChallengeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeAdapter");
            fitnessChallengeAdapter = null;
        }
        recyclerView.setAdapter(fitnessChallengeAdapter);
        q0().rvFitnessChallenge.setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
        q0().rvFitnessChallenge.addItemDecoration(new GridSpacingItemDecoration(2, 30, false));
        q0().rvFitnessChallenge.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$setChallengesRecyclerview$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView2, int i2) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, i2);
                RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
                Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                FragmentHome.this.l0(2, ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition());
            }
        });
    }

    public final void r0() {
        NotificationsViewModel notificationsViewModel = this.A;
        if (notificationsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBuddiesNotificationViewModel");
            notificationsViewModel = null;
        }
        notificationsViewModel.loadBuddiesDetailsInformation();
    }

    @SuppressLint({"SetTextI18n"})
    public final void r2(int i2, int i3) {
        TextView textView = q0().fitnessJourneyOngoing.tvTodayGoalTotalValue;
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
        q0().fitnessJourneyOngoing.todayGoalProgress.setMax(i3);
        q0().fitnessJourneyOngoing.todayGoalProgress.setProgress((int) themesUtils.convertCmToMeters(i2));
    }

    public final void s0() {
        WorkoutSessionRepository.Companion companion = WorkoutSessionRepository.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String connectedDeviceMacAddress = new PreferenceManager(requireContext2).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNull(connectedDeviceMacAddress);
        companion.getInstance(requireContext).getLastSessionLiveData(connectedDeviceMacAddress).observe(getViewLifecycleOwner(), new FragmentHome$getLastActivityData$1(this));
    }

    public final void s2() {
        Calendar calendar = this.I;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.dashboard2.fragment.d
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                FragmentHome.t2(FragmentHome.this, datePicker, i2, i3, i4);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void setBluetoothAdapter(@NotNull BluetoothAdapter bluetoothAdapter) {
        Intrinsics.checkNotNullParameter(bluetoothAdapter, "<set-?>");
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setDoMoreWithYourWatchGridDataModels(@NotNull List<DoMoreWithYourWatchDataModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.L = list;
    }

    public final void setDynamicTabAdapter(@Nullable DynamicTabAdapter dynamicTabAdapter) {
        this.P = dynamicTabAdapter;
    }

    public final void setEnableBluetoothDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.Z = bottomSheetDialogTwoButtons;
    }

    public final void setLastStepsGoalAcheivedPercentage(int i2) {
        this.Y = i2;
    }

    public final void setRotateAnimation(@Nullable Animation animation) {
        this.O = animation;
    }

    public final void setSelectedCalendar(Calendar calendar) {
        this.I = calendar;
    }

    public final void setSelectedFitnessVitalsDataModels(@NotNull List<FitnessVitalsDataModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.K = list;
    }

    public final void setSelectedFitnessVitalsGridDataModels(@NotNull List<FitnessVitalsDataModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.J = list;
    }

    public final void setSmartGridViewModel(@NotNull SmartGridViewModel smartGridViewModel) {
        Intrinsics.checkNotNullParameter(smartGridViewModel, "<set-?>");
        this.smartGridViewModel = smartGridViewModel;
    }

    public final void setTopFeaturesGridAdapter(@Nullable DashboardTopFeaturesViewAdapter dashboardTopFeaturesViewAdapter) {
        this.X = dashboardTopFeaturesViewAdapter;
    }

    public final void setViewModelCurrentPlanDashboard(@NotNull ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard) {
        Intrinsics.checkNotNullParameter(viewModelCurrentPlanDashboard, "<set-?>");
        this.viewModelCurrentPlanDashboard = viewModelCurrentPlanDashboard;
    }

    public final void setVitalsGridAdapter(@Nullable DashboardVitalsGridviewAdapter dashboardVitalsGridviewAdapter) {
        this.M = dashboardVitalsGridviewAdapter;
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showBuddyContents(@NotNull List<GetBuddyItems> buddiesGoals) {
        Intrinsics.checkNotNullParameter(buddiesGoals, "buddiesGoals");
        if (isAdded()) {
            q0().setShowMyBuddies(Boolean.FALSE);
            q0().setShowMyBuddiesList(Boolean.TRUE);
            List<GetBuddyItems> arrayList = new ArrayList<>();
            if (buddiesGoals.size() > 2) {
                arrayList.add(buddiesGoals.get(0));
                arrayList.add(buddiesGoals.get(1));
            } else {
                arrayList = CollectionsKt___CollectionsKt.toMutableList((Collection) buddiesGoals);
            }
            BuddiesDashboardAdapter buddiesDashboardAdapter = new BuddiesDashboardAdapter();
            buddiesDashboardAdapter.setBuddiesGoalList(arrayList);
            if (getContext() != null) {
                PreferenceManager.Companion companion = com.coveiot.android.fitnessbuddies.utils.PreferenceManager.Companion;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                buddiesDashboardAdapter.setBuddiesReminders(companion.getFitnessBuddiesReminder(requireActivity));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
                linearLayoutManager.setOrientation(0);
                linearLayoutManager.setReverseLayout(false);
                q0().rcvBuddiesList.setLayoutManager(linearLayoutManager);
                q0().rcvBuddiesList.setAdapter(buddiesDashboardAdapter);
                q0().rcvBuddiesList.scrollToPosition(0);
            }
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showContents(@NotNull List<? extends BuddiesGoal> buddiesGoals) {
        Intrinsics.checkNotNullParameter(buddiesGoals, "buddiesGoals");
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showEmptyLayout() {
        q0().setShowMyBuddies(Boolean.TRUE);
        q0().setShowMyBuddiesList(Boolean.FALSE);
    }

    public final void showOpenBluetoothSettingsDialog() {
        if (this.Z == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            String string = getString(R.string.bluetooth_off);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off)");
            String string2 = getString(R.string.bluetooth_off_message);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_off_message)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireActivity, string, string2, false, 8, null);
            this.Z = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.proceed);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.proceed)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentHome.M2(FragmentHome.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.Z;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.a0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentHome.N2(FragmentHome.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.Z;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.Z;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        bottomSheetDialogTwoButtons4.show();
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor
    public void showProgress() {
    }

    public final void t0(Calendar calendar) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new FragmentHome$getSleepDataByDate$1(this, calendar, null), 2, null);
    }

    @Override // com.coveiot.android.dashboard2.adapter.DashboardTopFeaturesViewAdapter.TopFeatureClickListener
    @RequiresApi(26)
    public void topFeatureClicked(@NotNull GetSmartGridRes.GridItem item) {
        BleApi bleApi;
        Intrinsics.checkNotNullParameter(item, "item");
        if (isAdded()) {
            if (AppUtils.isNetConnected(requireContext())) {
                String featureId = item.getFeatureId();
                if (Intrinsics.areEqual(featureId, SmartGridEnums.WATCH_FACE_STUDIO.getValue()) ? true : Intrinsics.areEqual(featureId, SmartGridEnums.QR_TRAY.getValue()) ? true : Intrinsics.areEqual(featureId, SmartGridEnums.NAVIGATION.getValue()) ? true : Intrinsics.areEqual(featureId, SmartGridEnums.SMART_ALERTS.getValue()) ? true : Intrinsics.areEqual(featureId, SmartGridEnums.SOS.getValue())) {
                    BleApiManager bleApiManager = BleApiManager.getInstance(requireContext());
                    if (((bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null) ? null : bleApi.getConnectionStatus()) == ConnectionStatus.CONNECTED) {
                        Context requireContext = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        SmartGridUtilsKt.callCTEventSmartGrid(requireContext, item);
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        Context requireContext2 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        SmartGridUtilsKt.navigateSmartGridItems(requireActivity, requireContext2, item);
                        return;
                    }
                    Context requireContext3 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    String string = getString(R.string.please_connect_the_device);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_connect_the_device)");
                    toast(requireContext3, string);
                    return;
                }
                Context requireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                SmartGridUtilsKt.callCTEventSmartGrid(requireContext4, item);
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                Context requireContext5 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                SmartGridUtilsKt.navigateSmartGridItems(requireActivity2, requireContext5, item);
                return;
            }
            Context requireContext6 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
            String string2 = getString(R.string.no_internet_connection);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.no_internet_connection)");
            toast(requireContext6, string2);
        }
    }

    public final void u0() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new FragmentHome$getTodayStepData$1(this, null), 2, null);
    }

    public final void u2() {
        FitnessChallengeListViewModel fitnessChallengeListViewModel = this.U;
        FitnessChallengeListViewModel fitnessChallengeListViewModel2 = null;
        if (fitnessChallengeListViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
            fitnessChallengeListViewModel = null;
        }
        fitnessChallengeListViewModel.getShowEmptyLayout().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.dashboard2.fragment.p0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentHome.v2(FragmentHome.this, (Boolean) obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel3 = this.U;
        if (fitnessChallengeListViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeListViewModel");
        } else {
            fitnessChallengeListViewModel2 = fitnessChallengeListViewModel3;
        }
        fitnessChallengeListViewModel2.getGetBuddiesChallengeListLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.dashboard2.fragment.l0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentHome.w2(FragmentHome.this, (BuddiesChallengeRes) obj);
            }
        });
    }

    public final void updateProgressWhenPlanEnds(double d2, @NotNull ArrayList<String> dayRange, @NotNull String planId) {
        Intrinsics.checkNotNullParameter(dayRange, "dayRange");
        Intrinsics.checkNotNullParameter(planId, "planId");
        ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.R;
        if (viewModelWorkoutFeedback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
            viewModelWorkoutFeedback = null;
        }
        viewModelWorkoutFeedback.updateFitnessPlanProgress(false, d2, dayRange, planId, new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$updateProgressWhenPlanEnds$1

            @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$updateProgressWhenPlanEnds$1$onError$1", f = "FragmentHome.kt", i = {}, l = {1451}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes4.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ FragmentHome this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(FragmentHome fragmentHome, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentHome;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                        Context context = this.this$0.getContext();
                        Intrinsics.checkNotNull(context);
                        this.label = 1;
                        if (companion.getInstance(context).deletePlan(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$updateProgressWhenPlanEnds$1$onSuccess$1", f = "FragmentHome.kt", i = {}, l = {1441}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes4.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ FragmentHome this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(FragmentHome fragmentHome, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentHome;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new b(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                        Context context = this.this$0.getContext();
                        Intrinsics.checkNotNull(context);
                        this.label = 1;
                        if (companion.getInstance(context).deletePlan(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (coveApiErrorModel == null || coveApiErrorModel.getCode() != 200) {
                    return;
                }
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getIO(), null, new a(FragmentHome.this, null), 2, null);
                FragmentHome.this.V1();
                SessionManager.getInstance(FragmentHome.this.requireContext()).setLastCalorieBurnedFitness(Float.valueOf(0.0f));
                SessionManager.getInstance(FragmentHome.this.requireContext()).setLastDistanceCoveredFitness(Float.valueOf(0.0f));
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseClass commonResponseClass) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getIO(), null, new b(FragmentHome.this, null), 2, null);
                FragmentHome.this.V1();
                SessionManager sessionManager = SessionManager.getInstance(FragmentHome.this.requireContext());
                Float valueOf = Float.valueOf(0.0f);
                sessionManager.setLastCalorieBurnedFitness(valueOf);
                SessionManager.getInstance(FragmentHome.this.requireContext()).setLastDistanceCoveredFitness(valueOf);
            }
        });
    }

    public final void v0(Calendar calendar) {
        this.I = calendar;
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new FragmentHome$handleSelectedDate$1(this, calendar, null), 2, null);
        }
    }

    public final void w0() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImageView imageView = q0().ivPoweredBy;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivPoweredBy");
        ThemesUtils.setPoweredByLogoIcon(requireContext, imageView, false);
        z1();
        Object systemService = requireActivity().getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothAdapter adapter = ((BluetoothManager) systemService).getAdapter();
        Intrinsics.checkNotNullExpressionValue(adapter, "requireActivity().getSys…BluetoothManager).adapter");
        setBluetoothAdapter(adapter);
        q0().viewFitnessDashboardHeader.setTitle(getString(R.string.view_fitness_dashboard));
        if (Dashboard2Utils.Companion.isWeatherFeatureSupported(requireActivity())) {
            q0().toolbar.imgWeatherDash.setVisibility(0);
            q0().toolbar.clWeatherDataDash.setVisibility(0);
        } else {
            q0().toolbar.imgWeatherDash.setVisibility(8);
            q0().toolbar.clWeatherDataDash.setVisibility(8);
        }
        q0().setDoMoreWithYourWatchItemCount(Integer.valueOf(this.L.size()));
        q0().setSetupYourWatchItemCount(0);
        FragmentHomeBinding q0 = q0();
        Boolean bool = Boolean.TRUE;
        q0.setIsSetupYourWatchSettingsCompleted(bool);
        q0().setShowMyBuddies(bool);
        q0().setShowDynamicTab(Boolean.FALSE);
        TextView textView = q0().tvboatExclusiveHeader;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvboatExclusiveHeader");
        gone(textView);
        this.O = AnimationUtils.loadAnimation(requireContext(), R.anim.rotation_anim);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.P = new DynamicTabAdapter(requireActivity);
        q0().dynamicTabRecy.setLayoutManager(new LinearLayoutManager(requireActivity()));
        q0().dynamicTabRecy.setAdapter(this.P);
        q2();
    }

    public final void w1() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.U = new FitnessChallengeListViewModel(requireContext);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = new FitnessChallengeDetailsViewModel(requireContext2);
        this.V = fitnessChallengeDetailsViewModel;
        fitnessChallengeDetailsViewModel.setMListener(this);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.o = (FragmentHomeViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity)).get(FragmentHomeViewModel.class);
        FragmentActivity requireActivity2 = requireActivity();
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
        this.p = (DataSyncViewModel) ViewModelProviders.of(requireActivity2, new ViewModelFactory(requireActivity3)).get(DataSyncViewModel.class);
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        this.q = (StepsDataViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity4)).get(StepsDataViewModel.class);
        FragmentActivity requireActivity5 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
        this.r = (SleepDataViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity5)).get(SleepDataViewModel.class);
        FragmentActivity requireActivity6 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity6, "requireActivity()");
        this.s = (HeartRateDataViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity6)).get(HeartRateDataViewModel.class);
        FragmentActivity requireActivity7 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity7, "requireActivity()");
        this.t = (SPO2DataViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity7)).get(SPO2DataViewModel.class);
        FragmentActivity requireActivity8 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity8, "requireActivity()");
        this.u = (StressDataViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity8)).get(StressDataViewModel.class);
        FragmentActivity requireActivity9 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity9, "requireActivity()");
        this.v = (HRVDataViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity9)).get(HRVDataViewModel.class);
        FragmentActivity requireActivity10 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity10, "requireActivity()");
        this.w = (EnergyMeterDataViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity10)).get(EnergyMeterDataViewModel.class);
        FragmentActivity requireActivity11 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity11, "requireActivity()");
        this.x = (RespiratoryRateDataViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity11)).get(RespiratoryRateDataViewModel.class);
        FragmentActivity requireActivity12 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity12, "requireActivity()");
        this.y = (TemperatureDataViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity12)).get(TemperatureDataViewModel.class);
        FragmentActivity requireActivity13 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity13, "requireActivity()");
        this.z = (PromotionsViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity13)).get(PromotionsViewModel.class);
        Application application = requireActivity().getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "requireActivity().application");
        this.A = new NotificationsViewModel(application, this);
        setViewModelCurrentPlanDashboard((ViewModelCurrentPlanDashboard) ViewModelProviders.of(this).get(ViewModelCurrentPlanDashboard.class));
        this.R = (ViewModelWorkoutFeedback) new ViewModelProvider(this).get(ViewModelWorkoutFeedback.class);
        setSmartGridViewModel((SmartGridViewModel) new ViewModelProvider(this).get(SmartGridViewModel.class));
    }

    public final void x0() {
        Drawable drawable;
        String string;
        Drawable drawable2;
        String string2;
        DeviceSupportedFeatures deviceSupportedFeatures;
        boolean isBTCallingSupported = isBTCallingSupported();
        this.B = isBTCallingSupported;
        if (isBTCallingSupported) {
            ConnectionStatus connectionStatus = BleApiManager.getInstance(getContext()).getBleApi().getConnectionStatus();
            ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
            if (connectionStatus == connectionStatus2) {
                String macAddress = BleApiManager.getInstance(getContext()).getBleApi().getMacAddress();
                BleApi bleApi = BleApiManager.getInstance(requireActivity()).getBleApi();
                Boolean valueOf = (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures.isKahaBTCallSupported());
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue()) {
                    macAddress = UserDataManager.getInstance(requireActivity()).getConnectedBTCallDeviceMac();
                } else {
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    if (companion.isTouchELXDevice(requireContext)) {
                        macAddress = PreferenceManagerAbstract.getInstance(requireContext()).getConnectedDeviceBT3MacAddress();
                    } else {
                        Context requireContext2 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        if (companion.isEastApexDevice(requireContext2)) {
                            macAddress = BleApiManager.getInstance(getContext()).getBleApi().getMacAddress();
                        }
                    }
                }
                if (macAddress == null) {
                    macAddress = BleApiManager.getInstance(getContext()).getBleApi().getMacAddress();
                }
                if (macAddress == null || macAddress.length() == 0) {
                    return;
                }
                Integer bTDeviceBondedState = BT3Utils.getBTDeviceBondedState(macAddress, requireActivity());
                DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                if (companion2.isOPPDevice(requireContext3)) {
                    FragmentHomeBinding q0 = q0();
                    boolean z2 = this.B;
                    String string3 = getString(R.string.bt_calling);
                    if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 12 && (SessionManager.getInstance(getContext()).getAclBt3LastReceivedConnectionStatus() == null || (SessionManager.getInstance(getContext()).getAclBt3LastReceivedConnectionStatus().getConnectionStatus() == connectionStatus2 && Intrinsics.areEqual(SessionManager.getInstance(getContext()).getAclBt3LastReceivedConnectionStatus().getMacAddress(), macAddress)))) {
                        drawable2 = requireActivity().getDrawable(R.drawable.ic_bt3_connected);
                    } else {
                        drawable2 = requireActivity().getDrawable(R.drawable.ic_bt3_disconnected);
                    }
                    if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 12 && (SessionManager.getInstance(getContext()).getAclBt3LastReceivedConnectionStatus() == null || (SessionManager.getInstance(getContext()).getAclBt3LastReceivedConnectionStatus().getConnectionStatus() == connectionStatus2 && Intrinsics.areEqual(SessionManager.getInstance(getContext()).getAclBt3LastReceivedConnectionStatus().getMacAddress(), macAddress)))) {
                        this.W = true;
                        T1(true);
                        string2 = getString(R.string.connected);
                    } else {
                        this.W = false;
                        T1(false);
                        string2 = getString(R.string.disconnected);
                    }
                    q0.setBt3ConnectionState(new BindingDataModel1(z2, string3, string2, drawable2));
                    return;
                }
                FragmentHomeBinding q02 = q0();
                boolean z3 = this.B;
                String string4 = getString(R.string.bt_calling);
                if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 12) {
                    drawable = requireActivity().getDrawable(R.drawable.ic_bt3_connected);
                } else {
                    drawable = requireActivity().getDrawable(R.drawable.ic_bt3_disconnected);
                }
                if (bTDeviceBondedState != null && bTDeviceBondedState.intValue() == 12) {
                    this.W = true;
                    T1(true);
                    string = getString(R.string.connected);
                } else {
                    this.W = false;
                    T1(false);
                    string = getString(R.string.disconnected);
                }
                q02.setBt3ConnectionState(new BindingDataModel1(z3, string4, string, drawable));
                return;
            }
        }
        q0().setBt3ConnectionState(new BindingDataModel1(this.B, getString(R.string.bt_calling), getString(R.string.disconnected), requireActivity().getDrawable(R.drawable.ic_bt3_disconnected)));
    }

    public final void x1() {
        q0().healthVitalsCardContainer.rvVitals.addItemDecoration(new DashboardFitnessVitalsItemDecorator(requireActivity().getResources().getDimensionPixelSize(R.dimen.margin_8dp), 0, 0, 6, null));
        q0().healthVitalsCardContainer.rvVitals.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        FragmentHomeViewModel fragmentHomeViewModel = null;
        q0().healthVitalsCardContainer.rvVitals.setItemAnimator(null);
        FragmentHomeViewModel fragmentHomeViewModel2 = this.o;
        if (fragmentHomeViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            fragmentHomeViewModel = fragmentHomeViewModel2;
        }
        fragmentHomeViewModel.getSelectedFitnessVitals();
    }

    public final void x2() {
        ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.R;
        if (viewModelWorkoutFeedback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
            viewModelWorkoutFeedback = null;
        }
        MutableLiveData<Pair<Integer, Integer>> dailyDistanceData = viewModelWorkoutFeedback.getDailyDistanceData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        dailyDistanceData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$setFitnessPlanObserver$$inlined$observe$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t2) {
                Pair pair = (Pair) t2;
                FragmentHome.this.r2(((Number) pair.getFirst()).intValue(), ((Number) pair.getSecond()).intValue());
            }
        });
    }

    public final void y0() {
        if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            q0().setBleConnectionState(new BindingDataModel1(true, getString(R.string.bluetooth), getString(R.string.connected), requireActivity().getDrawable(R.drawable.ic_ble_connected)));
            return;
        }
        q0().setBleConnectionState(new BindingDataModel1(true, getString(R.string.bluetooth), getString(R.string.disconnected), requireActivity().getDrawable(R.drawable.ic_ble_disconnected)));
    }

    public final void y1() {
    }

    public final void y2() {
        FragmentHomeViewModel fragmentHomeViewModel = this.o;
        if (fragmentHomeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            fragmentHomeViewModel = null;
        }
        fragmentHomeViewModel.getShouldShowIndusIndLogo().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.dashboard2.fragment.n0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentHome.z2(FragmentHome.this, (Boolean) obj);
            }
        });
    }

    public final void z0() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
        FragmentHomeViewModel fragmentHomeViewModel = this.o;
        if (fragmentHomeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            fragmentHomeViewModel = null;
        }
        fragmentHomeViewModel.getBoatCoinsDetails();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new e(null), 2, null);
    }

    public final void z1() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new k(null), 2, null);
    }
}
