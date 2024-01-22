package com.coveiot.android.activitymodes.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.adapters.ActivityFeedbackAdapterNew;
import com.coveiot.android.activitymodes.database.entities.ActivityDataSample;
import com.coveiot.android.activitymodes.database.entities.BadmintonSample;
import com.coveiot.android.activitymodes.database.entities.BasketBallSample;
import com.coveiot.android.activitymodes.database.entities.ClimbingSample;
import com.coveiot.android.activitymodes.database.entities.CyclingSample;
import com.coveiot.android.activitymodes.database.entities.DanceSample;
import com.coveiot.android.activitymodes.database.entities.EllipticalSample;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.database.entities.FootballSample;
import com.coveiot.android.activitymodes.database.entities.FreeExerciseSample;
import com.coveiot.android.activitymodes.database.entities.HikingSample;
import com.coveiot.android.activitymodes.database.entities.MeditationSample;
import com.coveiot.android.activitymodes.database.entities.PhysicalActivitySample;
import com.coveiot.android.activitymodes.database.entities.RowingMachineSample;
import com.coveiot.android.activitymodes.database.entities.RunSample;
import com.coveiot.android.activitymodes.database.entities.SampleData;
import com.coveiot.android.activitymodes.database.entities.SkippingSample;
import com.coveiot.android.activitymodes.database.entities.TennisSample;
import com.coveiot.android.activitymodes.database.entities.TreadmillSample;
import com.coveiot.android.activitymodes.database.entities.WalkSample;
import com.coveiot.android.activitymodes.database.entities.WorkoutSample;
import com.coveiot.android.activitymodes.database.entities.YogaSample;
import com.coveiot.android.activitymodes.database.models.TimeSpentHeartRateZone;
import com.coveiot.android.activitymodes.feedback.AnswerModel;
import com.coveiot.android.activitymodes.feedback.FeedbackModel;
import com.coveiot.android.activitymodes.feedback.FeedbackQuestionnarieModel;
import com.coveiot.android.activitymodes.feedback.QuestionModel;
import com.coveiot.android.activitymodes.models.ActivitySampleData;
import com.coveiot.android.activitymodes.models.ActivityShareData;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.models.WorkoutUiBean;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.SMAWorkoutUtils;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.activitymodes.viewmodels.ViewModelAADWorkoutFeedback;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutDetails;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.utils.RrHrHelperKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityDataSummaryDetails extends BaseActivity implements OnMapReadyCallback, ActivityFeedbackAdapterNew.AnswerFeedbackInterface {
    @Nullable
    public EntityWorkoutSession A;
    @Nullable
    public ViewModelWorkoutDetails p;
    @Nullable
    public ViewModelAADWorkoutFeedback q;
    @Nullable
    public String r;
    @Nullable
    public FeedbackQuestionnarieModel t;
    public int u;
    public int v;
    public int w;
    @Nullable
    public GoogleMap x;
    public int z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ActivityMode s = ActivityMode.RUN;
    @NotNull
    public final String y = "ActivityDataSummaryDetails";
    @NotNull
    public final Observer<EntityWorkoutSession> B = new Observer() { // from class: com.coveiot.android.activitymodes.activities.h0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.R0(ActivityDataSummaryDetails.this, (EntityWorkoutSession) obj);
        }
    };
    @NotNull
    public final Observer<List<RunSample>> C = new Observer() { // from class: com.coveiot.android.activitymodes.activities.q0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.P0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<PhysicalActivitySample>> D = new Observer() { // from class: com.coveiot.android.activitymodes.activities.i
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.L0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<WalkSample>> E = new Observer() { // from class: com.coveiot.android.activitymodes.activities.g
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.a1(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<CyclingSample>> F = new Observer() { // from class: com.coveiot.android.activitymodes.activities.m
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.y0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<TreadmillSample>> G = new Observer() { // from class: com.coveiot.android.activitymodes.activities.b
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.Y0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<ClimbingSample>> H = new Observer() { // from class: com.coveiot.android.activitymodes.activities.p
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.w0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<MeditationSample>> I = new Observer() { // from class: com.coveiot.android.activitymodes.activities.r0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.n0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<BadmintonSample>> J = new Observer() { // from class: com.coveiot.android.activitymodes.activities.j
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.s0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<FreeExerciseSample>> K = new Observer() { // from class: com.coveiot.android.activitymodes.activities.o
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.H0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<BasketBallSample>> L = new Observer() { // from class: com.coveiot.android.activitymodes.activities.n
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.u0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<DanceSample>> M = new Observer() { // from class: com.coveiot.android.activitymodes.activities.s0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.A0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<FootballSample>> N = new Observer() { // from class: com.coveiot.android.activitymodes.activities.u0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.F0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<TennisSample>> O = new Observer() { // from class: com.coveiot.android.activitymodes.activities.e
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.V0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<HikingSample>> P = new Observer() { // from class: com.coveiot.android.activitymodes.activities.q
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.J0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<WorkoutSample>> Q = new Observer() { // from class: com.coveiot.android.activitymodes.activities.f
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.c1(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<YogaSample>> R = new Observer() { // from class: com.coveiot.android.activitymodes.activities.d
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.e1(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<SkippingSample>> S = new Observer() { // from class: com.coveiot.android.activitymodes.activities.k
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.T0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<EllipticalSample>> T = new Observer() { // from class: com.coveiot.android.activitymodes.activities.h
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.D0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<RowingMachineSample>> U = new Observer() { // from class: com.coveiot.android.activitymodes.activities.t0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.N0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<ActivityDataSample>> V = new Observer() { // from class: com.coveiot.android.activitymodes.activities.c
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityDataSummaryDetails.q0(ActivityDataSummaryDetails.this, (List) obj);
        }
    };

    /* loaded from: classes2.dex */
    public interface AutoDetectionFeedbackInterface {
        void onFeedbackClicked(@NotNull EntityWorkoutSession entityWorkoutSession);
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ActivityMode.values().length];
            try {
                iArr[ActivityMode.RUN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ActivityMode.PHYSICAL_ACTIVITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ActivityMode.WALK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ActivityMode.BADMINTON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ActivityMode.FREE_EXERCISE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ActivityMode.BASKETBALL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ActivityMode.CYCLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ActivityMode.MEDITATION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ActivityMode.TREADMILL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ActivityMode.CLIMBING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ActivityMode.DANCE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ActivityMode.FOOTBALL.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ActivityMode.HIKING.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[ActivityMode.TENNIS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[ActivityMode.WORKOUT.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[ActivityMode.YOGA.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[ActivityMode.SKIPPING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[ActivityMode.ROWING.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[ActivityMode.ELLIPTICAL.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[ActivityMode.CRICKET.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[ActivityMode.MOUNTAINEERING.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[ActivityMode.SWIM.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$initAutoActivityDetectionFeedback$1$1", f = "ActivityDataSummaryDetails.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ FeedbackQuestionnarieModel $questionnarie;
        public int label;
        public final /* synthetic */ ActivityDataSummaryDetails this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(FeedbackQuestionnarieModel feedbackQuestionnarieModel, ActivityDataSummaryDetails activityDataSummaryDetails, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$questionnarie = feedbackQuestionnarieModel;
            this.this$0 = activityDataSummaryDetails;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$questionnarie, this.this$0, continuation);
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
                FeedbackQuestionnarieModel feedbackQuestionnarieModel = this.$questionnarie;
                if (feedbackQuestionnarieModel != null) {
                    List<QuestionModel> questions = feedbackQuestionnarieModel.getQuestions();
                    if (!(questions == null || questions.isEmpty())) {
                        this.this$0.t = this.$questionnarie;
                        ActivityDataSummaryDetails activityDataSummaryDetails = this.this$0;
                        int i = R.id.rv_feedback;
                        ((RecyclerView) activityDataSummaryDetails._$_findCachedViewById(i)).setVisibility(0);
                        ((RecyclerView) this.this$0._$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(this.this$0, 0, true));
                        ActivityFeedbackAdapterNew activityFeedbackAdapterNew = new ActivityFeedbackAdapterNew(this.this$0);
                        activityFeedbackAdapterNew.setAnswerFeedbackListener(this.this$0);
                        FeedbackQuestionnarieModel feedbackQuestionnarieModel2 = this.this$0.t;
                        Intrinsics.checkNotNull(feedbackQuestionnarieModel2);
                        List<QuestionModel> questions2 = feedbackQuestionnarieModel2.getQuestions();
                        Intrinsics.checkNotNull(questions2);
                        activityFeedbackAdapterNew.setData(questions2);
                        ((RecyclerView) this.this$0._$_findCachedViewById(i)).setAdapter(activityFeedbackAdapterNew);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$onAnswer$1$2", f = "ActivityDataSummaryDetails.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

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
            ActivityDataSummaryDetails activityDataSummaryDetails;
            ViewModelAADWorkoutFeedback viewModelAADWorkoutFeedback;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                EntityWorkoutSession mEntityWorkoutSession = ActivityDataSummaryDetails.this.getMEntityWorkoutSession();
                if (mEntityWorkoutSession != null && (viewModelAADWorkoutFeedback = (activityDataSummaryDetails = ActivityDataSummaryDetails.this).q) != null) {
                    FeedbackQuestionnarieModel feedbackQuestionnarieModel = activityDataSummaryDetails.t;
                    Intrinsics.checkNotNull(feedbackQuestionnarieModel);
                    viewModelAADWorkoutFeedback.saveFeedbackAnswer(mEntityWorkoutSession, feedbackQuestionnarieModel);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.f0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.B0(list, this$0);
            }
        });
    }

    public static final void B0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getDanceSamplesObserver$lambda$63$lambda$62$lambda$61$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((DanceSample) t).getSampleData().getTimeStamp()), Long.valueOf(((DanceSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((DanceSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((DanceSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void D0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.a0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.E0(list, this$0);
            }
        });
    }

    public static final void E0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getEllipticalSamplesObserver$lambda$91$lambda$90$lambda$89$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((EllipticalSample) t).getSampleData().getTimeStamp()), Long.valueOf(((EllipticalSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((EllipticalSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((EllipticalSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void F0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.l0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.G0(list, this$0);
            }
        });
    }

    public static final void G0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getFootBallSamplesObserver$lambda$67$lambda$66$lambda$65$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((FootballSample) t).getSampleData().getTimeStamp()), Long.valueOf(((FootballSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((FootballSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((FootballSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void H0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.m0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.I0(list, this$0);
            }
        });
    }

    public static final void I0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getFreeExerciseSamplesObserver$lambda$55$lambda$54$lambda$53$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((FreeExerciseSample) t).getSampleData().getTimeStamp()), Long.valueOf(((FreeExerciseSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((FreeExerciseSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((FreeExerciseSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void J0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.e0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.K0(list, this$0);
            }
        });
    }

    public static final void K0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getHikingSamplesObserver$lambda$75$lambda$74$lambda$73$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((HikingSample) t).getSampleData().getTimeStamp()), Long.valueOf(((HikingSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((HikingSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((HikingSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void L0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.n0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.M0(list, this$0);
            }
        });
    }

    public static final void M0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getPhysicalActivitySamplesObserver$lambda$27$lambda$26$lambda$25$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((PhysicalActivitySample) t).getSampleData().getTimeStamp()), Long.valueOf(((PhysicalActivitySample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((PhysicalActivitySample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((PhysicalActivitySample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void N0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.z
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.O0(list, this$0);
            }
        });
    }

    public static final void O0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getRowingMachineSamplesObserver$lambda$95$lambda$94$lambda$93$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((RowingMachineSample) t).getSampleData().getTimeStamp()), Long.valueOf(((RowingMachineSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((RowingMachineSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((RowingMachineSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void P0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.y
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.Q0(list, this$0);
            }
        });
    }

    public static final void Q0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getRunSamplesObserver$lambda$23$lambda$22$lambda$21$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((RunSample) t).getSampleData().getTimeStamp()), Long.valueOf(((RunSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((RunSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((RunSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void R0(final ActivityDataSummaryDetails this$0, final EntityWorkoutSession entityWorkoutSession) {
        Integer fromHAR;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (entityWorkoutSession != null) {
            this$0.A = entityWorkoutSession;
            WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
            String activityModeNames = workoutUtils.getActivityModeNames(this$0.s, this$0, entityWorkoutSession);
            try {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string = this$0.getString(R.string.summary);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.summary)");
                String format = String.format(locale, string, Arrays.copyOf(new Object[]{workoutUtils.getActivityModeNames(this$0.s, this$0, entityWorkoutSession)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                ((TextView) this$0.findViewById(R.id.toolbar_title)).setText(format);
            } catch (StringIndexOutOfBoundsException e) {
                e.printStackTrace();
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Locale locale2 = Locale.ENGLISH;
                String upperCase = this$0.getString(R.string.summary).toString().toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
                String format2 = String.format(locale2, upperCase, Arrays.copyOf(new Object[]{this$0.s.toString()}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                ((TextView) this$0.findViewById(R.id.toolbar_title)).setText(format2);
            }
            int i = R.id.activityTypeText;
            WorkoutUtils workoutUtils2 = WorkoutUtils.INSTANCE;
            ((TextView) this$0._$_findCachedViewById(i)).setText(workoutUtils2.getActivityModeNames(this$0.s, this$0, entityWorkoutSession));
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if ((companion.isSmaDevice(this$0) || companion.isMatrixDevice(this$0)) && kotlin.text.m.equals(entityWorkoutSession.getActivity_type(), CoveApiConstants.CYCLE, true) && kotlin.text.m.equals(entityWorkoutSession.getIndoor_outdoor(), "INDOOR", true)) {
                activityModeNames = this$0.getString(R.string.spinning_title_case);
                Intrinsics.checkNotNullExpressionValue(activityModeNames, "getString(R.string.spinning_title_case)");
            }
            if (((TextView) this$0._$_findCachedViewById(i)) != null) {
                ((TextView) this$0._$_findCachedViewById(i)).setText(activityModeNames);
            }
            try {
                if (this$0.s != ActivityMode.PHYSICAL_ACTIVITY) {
                    StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                    Locale locale3 = Locale.ENGLISH;
                    String string2 = this$0.getString(R.string.summary);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.summary)");
                    String format3 = String.format(locale3, string2, Arrays.copyOf(new Object[]{workoutUtils2.convertStringToTitleCase(activityModeNames)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                    ((TextView) this$0.findViewById(R.id.toolbar_title)).setText(format3);
                } else {
                    StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                    Locale locale4 = Locale.ENGLISH;
                    String string3 = this$0.getString(R.string.summary);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.summary)");
                    String format4 = String.format(locale4, string3, Arrays.copyOf(new Object[]{activityModeNames}, 1));
                    Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                    ((TextView) this$0.findViewById(R.id.toolbar_title)).setText(format4);
                }
            } catch (StringIndexOutOfBoundsException e2) {
                e2.printStackTrace();
                StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                Locale locale5 = Locale.ENGLISH;
                String string4 = this$0.getString(R.string.summary);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.summary)");
                String format5 = String.format(locale5, string4, Arrays.copyOf(new Object[]{activityModeNames}, 1));
                Intrinsics.checkNotNullExpressionValue(format5, "format(locale, format, *args)");
                ((TextView) this$0.findViewById(R.id.toolbar_title)).setText(format5);
            }
            if (this$0.s1(entityWorkoutSession)) {
                this$0._$_findCachedViewById(R.id.activityWorkoutInsights).setVisibility(0);
                ((LinearLayout) this$0._$_findCachedViewById(R.id.cv_steps_graph_card)).setVisibility(0);
            } else {
                this$0._$_findCachedViewById(R.id.activityWorkoutInsights).setVisibility(8);
                ((LinearLayout) this$0._$_findCachedViewById(R.id.cv_steps_graph_card)).setVisibility(8);
            }
            if (this$0.r1()) {
                if (DeviceUtils.Companion.isEastApexDevice(this$0)) {
                    ((LinearLayout) this$0._$_findCachedViewById(R.id.hr_Zone_cardView)).setVisibility(0);
                    ((LinearLayout) this$0._$_findCachedViewById(R.id.cardView_hr_graph)).setVisibility(8);
                } else {
                    ((LinearLayout) this$0._$_findCachedViewById(R.id.hr_Zone_cardView)).setVisibility(0);
                    ((LinearLayout) this$0._$_findCachedViewById(R.id.cardView_hr_graph)).setVisibility(0);
                }
            } else {
                if (BleApiManager.getInstance(this$0) != null && BleApiManager.getInstance(this$0).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
                    this$0._$_findCachedViewById(R.id.view_hr_graph_border).setVisibility(8);
                }
                ((LinearLayout) this$0._$_findCachedViewById(R.id.hr_Zone_cardView)).setVisibility(8);
                ((LinearLayout) this$0._$_findCachedViewById(R.id.cardView_hr_graph)).setVisibility(8);
            }
            ((ImageView) this$0._$_findCachedViewById(R.id.share_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.w
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDataSummaryDetails.S0(ActivityDataSummaryDetails.this, entityWorkoutSession, view);
                }
            });
            WorkoutUtils workoutUtils3 = WorkoutUtils.INSTANCE;
            if (workoutUtils3.isBestActivityOfType(this$0, entityWorkoutSession)) {
                ((ImageView) this$0._$_findCachedViewById(R.id.bestActivityImg)).setVisibility(0);
            } else {
                ((ImageView) this$0._$_findCachedViewById(R.id.bestActivityImg)).setVisibility(8);
            }
            if (entityWorkoutSession.getFromHAR() != null && (fromHAR = entityWorkoutSession.getFromHAR()) != null && fromHAR.intValue() == 1 && !this$0.k1(entityWorkoutSession)) {
                this$0.g1();
            } else {
                ((RecyclerView) this$0._$_findCachedViewById(R.id.rv_feedback)).setVisibility(8);
            }
            ((TextView) this$0._$_findCachedViewById(R.id.activityDateTimeText)).setText(workoutUtils3.getTodayYesterdayStringWithTimeStamp(entityWorkoutSession.getStart_time(), this$0));
            entityWorkoutSession.getSteps_sampling_rate();
            this$0.v = entityWorkoutSession.getMin_hr();
            this$0.w = entityWorkoutSession.getMax_hr();
            new DecimalFormat("#00.00").setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
            this$0.u = entityWorkoutSession.getTotal_steps();
            List<WorkoutUiBean> workoutUiBeans = workoutUtils3.getWorkoutUiBeans(this$0, entityWorkoutSession, WorkoutUiBeanProvider.ScreenType.ACTIVITY_DETAIL);
            if (!workoutUiBeans.isEmpty()) {
                int i2 = 0;
                boolean z = false;
                for (WorkoutUiBean workoutUiBean : workoutUiBeans) {
                    int i3 = i2 + 1;
                    if (DeviceUtils.Companion.isMigratedDevice(this$0)) {
                        if (kotlin.text.m.equals(workoutUiBean.getValue(), this$0.getResources().getString(R.string.activity_no_data), true)) {
                            ((TextView) this$0._$_findCachedViewById(R.id.tv_activity_legend_info_summary_screen)).setVisibility(0);
                            if (kotlin.text.m.equals(workoutUiBean.getTitle(), this$0.getResources().getString(R.string.hr_new), true)) {
                                ((LinearLayout) this$0._$_findCachedViewById(R.id.hr_Zone_cardView)).setVisibility(8);
                            }
                            z = true;
                        } else if (!z) {
                            ((TextView) this$0._$_findCachedViewById(R.id.tv_activity_legend_info_summary_screen)).setVisibility(8);
                        }
                    }
                    switch (i2) {
                        case 0:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item1Cl)).setVisibility(0);
                            Integer image = workoutUiBean.getImage();
                            if (image != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item1TitleImg)).setImageResource(image.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item1TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item1ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 1:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item2Cl)).setVisibility(0);
                            Integer image2 = workoutUiBean.getImage();
                            if (image2 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item2TitleImg)).setImageResource(image2.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item2TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item2ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 2:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item3Cl)).setVisibility(0);
                            Integer image3 = workoutUiBean.getImage();
                            if (image3 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item3TitleImg)).setImageResource(image3.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item3TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item3ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 3:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item4Cl)).setVisibility(0);
                            Integer image4 = workoutUiBean.getImage();
                            if (image4 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item4TitleImg)).setImageResource(image4.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item4TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item4ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 4:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item5Cl)).setVisibility(0);
                            Integer image5 = workoutUiBean.getImage();
                            if (image5 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item5TitleImg)).setImageResource(image5.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item5TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item5ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 5:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item6Cl)).setVisibility(0);
                            Integer image6 = workoutUiBean.getImage();
                            if (image6 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item6TitleImg)).setImageResource(image6.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item6TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item6ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 6:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item7Cl)).setVisibility(0);
                            Integer image7 = workoutUiBean.getImage();
                            if (image7 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item7TitleImg)).setImageResource(image7.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item7TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item7ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 7:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item8Cl)).setVisibility(0);
                            Integer image8 = workoutUiBean.getImage();
                            if (image8 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item8TitleImg)).setImageResource(image8.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item8TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item8ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 8:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item9Cl)).setVisibility(0);
                            Integer image9 = workoutUiBean.getImage();
                            if (image9 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item9TitleImg)).setImageResource(image9.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item9TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item9ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 9:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item10Cl)).setVisibility(0);
                            Integer image10 = workoutUiBean.getImage();
                            if (image10 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item10TitleImg)).setImageResource(image10.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item10TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item10ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 10:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item11Cl)).setVisibility(0);
                            Integer image11 = workoutUiBean.getImage();
                            if (image11 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item11TitleImg)).setImageResource(image11.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item11TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item11ValText)).setText(workoutUiBean.getValue());
                            break;
                        case 11:
                            ((LinearLayout) this$0._$_findCachedViewById(R.id.item12Cl)).setVisibility(0);
                            Integer image12 = workoutUiBean.getImage();
                            if (image12 != null) {
                                ((ImageView) this$0._$_findCachedViewById(R.id.item12TitleImg)).setImageResource(image12.intValue());
                            }
                            ((TextView) this$0._$_findCachedViewById(R.id.item12TitleText)).setText(workoutUiBean.getTitle());
                            ((TextView) this$0._$_findCachedViewById(R.id.item12ValText)).setText(workoutUiBean.getValue());
                            break;
                    }
                    i2 = i3;
                }
            }
            this$0.l1(entityWorkoutSession, entityWorkoutSession);
            this$0.setHRZoneDataToUi(entityWorkoutSession);
        }
    }

    public static final void S0(ActivityDataSummaryDetails this$0, EntityWorkoutSession entityWorkoutSession, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(entityWorkoutSession, "entityWorkoutSession");
        this$0.q1(entityWorkoutSession);
    }

    public static final void T0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.x
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.U0(list, this$0);
            }
        });
    }

    public static final void U0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getSkippingSamplesObserver$lambda$87$lambda$86$lambda$85$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((SkippingSample) t).getSampleData().getTimeStamp()), Long.valueOf(((SkippingSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((SkippingSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((SkippingSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void V0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.i0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.W0(list, this$0);
            }
        });
    }

    public static final void W0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getTennisSamplesObserver$lambda$71$lambda$70$lambda$69$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((TennisSample) t).getSampleData().getTimeStamp()), Long.valueOf(((TennisSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((TennisSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((TennisSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void Y0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.v
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.Z0(list, this$0);
            }
        });
    }

    public static final void Z0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getTreadmillSamplesObserver$lambda$39$lambda$38$lambda$37$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((TreadmillSample) t).getSampleData().getTimeStamp()), Long.valueOf(((TreadmillSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((TreadmillSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((TreadmillSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void a1(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.b0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.b1(list, this$0);
            }
        });
    }

    public static final void b1(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getWalkSamplesObserver$lambda$31$lambda$30$lambda$29$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((WalkSample) t).getSampleData().getTimeStamp()), Long.valueOf(((WalkSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((WalkSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((WalkSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void c1(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.d0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.d1(list, this$0);
            }
        });
    }

    public static final void d1(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getWorkoutSamplesObserver$lambda$79$lambda$78$lambda$77$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((WorkoutSample) t).getSampleData().getTimeStamp()), Long.valueOf(((WorkoutSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((WorkoutSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((WorkoutSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void e1(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.j0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.f1(list, this$0);
            }
        });
    }

    public static final void f1(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getYogaSamplesObserver$lambda$83$lambda$82$lambda$81$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((YogaSample) t).getSampleData().getTimeStamp()), Long.valueOf(((YogaSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((YogaSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((YogaSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void h1(ActivityDataSummaryDetails this$0, FeedbackQuestionnarieModel feedbackQuestionnarieModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isFinishing()) {
            return;
        }
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getMain(), null, new a(feedbackQuestionnarieModel, this$0, null), 2, null);
    }

    public static final void i1(ActivityDataSummaryDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, HeartRateZoneInfoActivity.class));
    }

    public static final void j1(ActivityDataSummaryDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void l0(final ActivityDataSummaryDetails this$0, PolylineOptions polylineOptions, final LatLngBounds.Builder builder) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(polylineOptions, "$polylineOptions");
        Intrinsics.checkNotNullParameter(builder, "$builder");
        ((LinearLayout) this$0._$_findCachedViewById(R.id.map_fragment_details_layout)).setVisibility(0);
        GoogleMap googleMap = this$0.x;
        if (googleMap != null) {
            if (googleMap != null) {
                googleMap.clear();
            }
            GoogleMap googleMap2 = this$0.x;
            if (googleMap2 != null) {
                googleMap2.addPolyline(polylineOptions);
            }
            if (polylineOptions.getPoints().size() > 0) {
                GoogleMap googleMap3 = this$0.x;
                if (googleMap3 != null) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    List<LatLng> points = polylineOptions.getPoints();
                    Intrinsics.checkNotNullExpressionValue(points, "polylineOptions.points");
                    googleMap3.addMarker(markerOptions.position((LatLng) CollectionsKt___CollectionsKt.first((List<? extends Object>) points)).icon(BitmapDescriptorFactory.fromResource(R.drawable.start_location_marker)));
                }
                GoogleMap googleMap4 = this$0.x;
                if (googleMap4 != null) {
                    MarkerOptions markerOptions2 = new MarkerOptions();
                    List<LatLng> points2 = polylineOptions.getPoints();
                    Intrinsics.checkNotNullExpressionValue(points2, "polylineOptions.points");
                    googleMap4.addMarker(markerOptions2.position((LatLng) CollectionsKt___CollectionsKt.last((List<? extends Object>) points2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.end_location_marker)));
                }
                GoogleMap googleMap5 = this$0.x;
                if (googleMap5 != null) {
                    googleMap5.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() { // from class: com.coveiot.android.activitymodes.activities.r
                        @Override // com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
                        public final void onMapLoaded() {
                            ActivityDataSummaryDetails.m0(ActivityDataSummaryDetails.this, builder);
                        }
                    });
                }
            }
        }
    }

    public static final void m0(ActivityDataSummaryDetails this$0, LatLngBounds.Builder builder) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(builder, "$builder");
        GoogleMap googleMap = this$0.x;
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 200));
        }
    }

    public static final void n0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.k0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.o0(list, this$0);
            }
        });
    }

    public static final void o0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$geMeditationSamplesObserver$lambda$47$lambda$46$lambda$45$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((MeditationSample) t).getSampleData().getTimeStamp()), Long.valueOf(((MeditationSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((MeditationSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((MeditationSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void q0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.o0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.r0(list, this$0);
            }
        });
    }

    public static final void r0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getActivityDataSamplesObserver$lambda$99$lambda$98$lambda$97$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((ActivityDataSample) t).getTimeStamp()), Long.valueOf(((ActivityDataSample) t2).getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            SampleData sampleData = new SampleData();
            sampleData.setTimeStamp(((ActivityDataSample) sortedWith.get(i)).getTimeStamp());
            sampleData.setHr_value(((ActivityDataSample) sortedWith.get(i)).getHrValue());
            sampleData.setCalories(((ActivityDataSample) sortedWith.get(i)).getCalories());
            sampleData.setDistance(((ActivityDataSample) sortedWith.get(i)).getDistance());
            sampleData.setLatitude(((ActivityDataSample) sortedWith.get(i)).getLatitude());
            sampleData.setLongitude(((ActivityDataSample) sortedWith.get(i)).getLongitude());
            sampleData.setSpeed_value(((ActivityDataSample) sortedWith.get(i)).getSpeedValue());
            activitySampleData.setSampleData(sampleData);
            activitySampleData.setStepCount(((ActivityDataSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void s0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.u
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.t0(list, this$0);
            }
        });
    }

    public static final void t0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getBadmintonSamplesObserver$lambda$51$lambda$50$lambda$49$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((BadmintonSample) t).getSampleData().getTimeStamp()), Long.valueOf(((BadmintonSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((BadmintonSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((BadmintonSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void u0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.g0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.v0(list, this$0);
            }
        });
    }

    public static final void v0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getBasketBallSamplesObserver$lambda$59$lambda$58$lambda$57$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((BasketBallSample) t).getSampleData().getTimeStamp()), Long.valueOf(((BasketBallSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((BasketBallSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((BasketBallSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void w0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.c0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.x0(list, this$0);
            }
        });
    }

    public static final void x0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getClimbingSamplesObserver$lambda$43$lambda$42$lambda$41$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((ClimbingSample) t).getSampleData().getTimeStamp()), Long.valueOf(((ClimbingSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((ClimbingSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((ClimbingSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public static final void y0(final ActivityDataSummaryDetails this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.t
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.z0(list, this$0);
            }
        });
    }

    public static final void z0(List list, ActivityDataSummaryDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        List sortedWith = CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$getCycleSamplesObserver$lambda$35$lambda$34$lambda$33$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(Long.valueOf(((CyclingSample) t).getSampleData().getTimeStamp()), Long.valueOf(((CyclingSample) t2).getSampleData().getTimeStamp()));
            }
        });
        ArrayList<ActivitySampleData> arrayList = new ArrayList<>();
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            ActivitySampleData activitySampleData = new ActivitySampleData();
            activitySampleData.setSampleData(((CyclingSample) sortedWith.get(i)).getSampleData());
            activitySampleData.setStepCount(((CyclingSample) sortedWith.get(i)).getStepCount());
            arrayList.add(activitySampleData);
        }
        this$0.p0(arrayList);
    }

    public final void C0() {
        LiveData<EntityWorkoutSession> liveDataWorkoutSession;
        if (this.r != null) {
            if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isGenericActivityDataSampleSupported()) {
                ViewModelWorkoutDetails viewModelWorkoutDetails = this.p;
                if (viewModelWorkoutDetails != null) {
                    String str = this.r;
                    Intrinsics.checkNotNull(str);
                    viewModelWorkoutDetails.getWorkoutSessionAndSamplesFromGenericActivityData(str);
                }
            } else {
                ViewModelWorkoutDetails viewModelWorkoutDetails2 = this.p;
                if (viewModelWorkoutDetails2 != null) {
                    String str2 = this.r;
                    Intrinsics.checkNotNull(str2);
                    viewModelWorkoutDetails2.getWorkoutSessionAndSamplesFromDB(str2, this.s);
                }
            }
            ViewModelWorkoutDetails viewModelWorkoutDetails3 = this.p;
            if (viewModelWorkoutDetails3 == null || (liveDataWorkoutSession = viewModelWorkoutDetails3.getLiveDataWorkoutSession()) == null) {
                return;
            }
            liveDataWorkoutSession.observe(this, this.B);
        }
    }

    public final String X0(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        String time = new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(time, "time");
        return time;
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

    public final void drawPolyLineOnMap(final PolylineOptions polylineOptions, final LatLngBounds.Builder builder) {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.s
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDataSummaryDetails.l0(ActivityDataSummaryDetails.this, polylineOptions, builder);
            }
        });
    }

    public final void g1() {
        MutableLiveData<FeedbackQuestionnarieModel> getQuestionarieLiveData;
        ViewModelAADWorkoutFeedback viewModelAADWorkoutFeedback = this.q;
        if (viewModelAADWorkoutFeedback != null && (getQuestionarieLiveData = viewModelAADWorkoutFeedback.getGetQuestionarieLiveData()) != null) {
            getQuestionarieLiveData.observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.activities.p0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActivityDataSummaryDetails.h1(ActivityDataSummaryDetails.this, (FeedbackQuestionnarieModel) obj);
                }
            });
        }
        if (AppUtils.isNetConnected(this)) {
            ViewModelAADWorkoutFeedback viewModelAADWorkoutFeedback2 = this.q;
            if (viewModelAADWorkoutFeedback2 != null) {
                viewModelAADWorkoutFeedback2.getFeedbackQuestionnarieList();
                return;
            }
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
    }

    @Nullable
    public final GoogleMap getGoogleMap() {
        return this.x;
    }

    public final int getHrMax() {
        return this.w;
    }

    public final int getHrMin() {
        return this.v;
    }

    @Nullable
    public final EntityWorkoutSession getMEntityWorkoutSession() {
        return this.A;
    }

    public final int getSteps() {
        return this.u;
    }

    public final void initClickListeners() {
        ((ImageView) _$_findCachedViewById(R.id.iv_hr_zone_info)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDataSummaryDetails.i1(ActivityDataSummaryDetails.this, view);
            }
        });
        String str = this.r;
        Intrinsics.checkNotNull(str);
        WorkoutSessionRepository.Companion.getInstance(this).getSessionDetails(str);
        C0();
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.activity_details));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDataSummaryDetails.j1(ActivityDataSummaryDetails.this, view);
            }
        });
    }

    public final boolean k1(EntityWorkoutSession entityWorkoutSession) {
        FeedbackModel feedbackModel;
        String feedback = entityWorkoutSession.getFeedback();
        boolean z = false;
        if ((feedback == null || feedback.length() == 0) || (feedbackModel = (FeedbackModel) new Gson().fromJson(entityWorkoutSession.getFeedback(), (Class<Object>) FeedbackModel.class)) == null) {
            return false;
        }
        List<AnswerModel> questionsAndAnswers = feedbackModel.getQuestionsAndAnswers();
        if (questionsAndAnswers == null || questionsAndAnswers.isEmpty()) {
            z = true;
        }
        return !z;
    }

    public final void l1(EntityWorkoutSession entityWorkoutSession, EntityWorkoutSession entityWorkoutSession2) {
        String str;
        LiveData<List<RunSample>> liveDataRunSamples;
        LiveData<List<PhysicalActivitySample>> liveDataPhysicalSamples;
        LiveData<List<WalkSample>> liveDataWalkSamples;
        LiveData<List<BadmintonSample>> liveDataBadmintonSamples;
        LiveData<List<FreeExerciseSample>> liveDataFreeExerciseSamples;
        LiveData<List<BasketBallSample>> liveDataBasketBallSamples;
        LiveData<List<CyclingSample>> liveDataCyclingSamples;
        LiveData<List<MeditationSample>> liveDataMeditationSamples;
        LiveData<List<TreadmillSample>> liveDataTreadmillSamples;
        LiveData<List<ClimbingSample>> liveDataClimingSamples;
        LiveData<List<DanceSample>> liveDataDanceSamples;
        LiveData<List<FootballSample>> liveDataFootBallSamples;
        LiveData<List<HikingSample>> liveDataHikingSamples;
        LiveData<List<TennisSample>> liveDataTennisSamples;
        LiveData<List<WorkoutSample>> liveDataWorkoutSamples;
        LiveData<List<YogaSample>> liveDataYogaSamples;
        LiveData<List<SkippingSample>> liveDataSkippingSamples;
        LiveData<List<RowingMachineSample>> liveDataRowingMachineSamples;
        LiveData<List<EllipticalSample>> liveDataEllipticalSamples;
        LiveData<List<RunSample>> liveDataRunSamples2;
        LiveData<List<ActivityDataSample>> liveDataActivityDataSamples;
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        Intrinsics.checkNotNull(this);
        ActivityMode activityMode = this.s;
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            str = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(str);
        } else {
            str = null;
        }
        WorkoutImageBean imageUiBean = workoutUtils.getImageUiBean(this, activityMode, str, entityWorkoutSession.getActivityId(), entityWorkoutSession.getCategoryId());
        Object image = imageUiBean.getImage();
        if (image instanceof Integer) {
            ((ImageView) _$_findCachedViewById(R.id.activityImg)).setImageResource(((Number) imageUiBean.getImage()).intValue());
        } else if (image instanceof String) {
            RequestBuilder<Drawable> m30load = Glide.with((FragmentActivity) this).m30load((String) imageUiBean.getImage());
            ImageView imageView = (ImageView) _$_findCachedViewById(R.id.activityImg);
            Intrinsics.checkNotNull(imageView);
            m30load.into(imageView);
        } else {
            ((ImageView) _$_findCachedViewById(R.id.activityImg)).setImageResource(R.drawable.activity_running_icon);
        }
        if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isGenericActivityDataSampleSupported()) {
            ViewModelWorkoutDetails viewModelWorkoutDetails = this.p;
            if (viewModelWorkoutDetails != null && (liveDataActivityDataSamples = viewModelWorkoutDetails.getLiveDataActivityDataSamples()) != null) {
                liveDataActivityDataSamples.observe(this, this.V);
            }
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[this.s.ordinal()]) {
                case 1:
                    ViewModelWorkoutDetails viewModelWorkoutDetails2 = this.p;
                    if (viewModelWorkoutDetails2 != null && (liveDataRunSamples = viewModelWorkoutDetails2.getLiveDataRunSamples()) != null) {
                        liveDataRunSamples.observe(this, this.C);
                        break;
                    }
                    break;
                case 2:
                    ViewModelWorkoutDetails viewModelWorkoutDetails3 = this.p;
                    if (viewModelWorkoutDetails3 != null && (liveDataPhysicalSamples = viewModelWorkoutDetails3.getLiveDataPhysicalSamples()) != null) {
                        liveDataPhysicalSamples.observe(this, this.D);
                        break;
                    }
                    break;
                case 3:
                    ViewModelWorkoutDetails viewModelWorkoutDetails4 = this.p;
                    if (viewModelWorkoutDetails4 != null && (liveDataWalkSamples = viewModelWorkoutDetails4.getLiveDataWalkSamples()) != null) {
                        liveDataWalkSamples.observe(this, this.E);
                        break;
                    }
                    break;
                case 4:
                    ViewModelWorkoutDetails viewModelWorkoutDetails5 = this.p;
                    if (viewModelWorkoutDetails5 != null && (liveDataBadmintonSamples = viewModelWorkoutDetails5.getLiveDataBadmintonSamples()) != null) {
                        liveDataBadmintonSamples.observe(this, this.J);
                        break;
                    }
                    break;
                case 5:
                    ViewModelWorkoutDetails viewModelWorkoutDetails6 = this.p;
                    if (viewModelWorkoutDetails6 != null && (liveDataFreeExerciseSamples = viewModelWorkoutDetails6.getLiveDataFreeExerciseSamples()) != null) {
                        liveDataFreeExerciseSamples.observe(this, this.K);
                        break;
                    }
                    break;
                case 6:
                    ViewModelWorkoutDetails viewModelWorkoutDetails7 = this.p;
                    if (viewModelWorkoutDetails7 != null && (liveDataBasketBallSamples = viewModelWorkoutDetails7.getLiveDataBasketBallSamples()) != null) {
                        liveDataBasketBallSamples.observe(this, this.L);
                        break;
                    }
                    break;
                case 7:
                    ViewModelWorkoutDetails viewModelWorkoutDetails8 = this.p;
                    if (viewModelWorkoutDetails8 != null && (liveDataCyclingSamples = viewModelWorkoutDetails8.getLiveDataCyclingSamples()) != null) {
                        liveDataCyclingSamples.observe(this, this.F);
                        break;
                    }
                    break;
                case 8:
                    ViewModelWorkoutDetails viewModelWorkoutDetails9 = this.p;
                    if (viewModelWorkoutDetails9 != null && (liveDataMeditationSamples = viewModelWorkoutDetails9.getLiveDataMeditationSamples()) != null) {
                        liveDataMeditationSamples.observe(this, this.I);
                        break;
                    }
                    break;
                case 9:
                    ViewModelWorkoutDetails viewModelWorkoutDetails10 = this.p;
                    if (viewModelWorkoutDetails10 != null && (liveDataTreadmillSamples = viewModelWorkoutDetails10.getLiveDataTreadmillSamples()) != null) {
                        liveDataTreadmillSamples.observe(this, this.G);
                        break;
                    }
                    break;
                case 10:
                    ViewModelWorkoutDetails viewModelWorkoutDetails11 = this.p;
                    if (viewModelWorkoutDetails11 != null && (liveDataClimingSamples = viewModelWorkoutDetails11.getLiveDataClimingSamples()) != null) {
                        liveDataClimingSamples.observe(this, this.H);
                        break;
                    }
                    break;
                case 11:
                    ViewModelWorkoutDetails viewModelWorkoutDetails12 = this.p;
                    if (viewModelWorkoutDetails12 != null && (liveDataDanceSamples = viewModelWorkoutDetails12.getLiveDataDanceSamples()) != null) {
                        liveDataDanceSamples.observe(this, this.M);
                        break;
                    }
                    break;
                case 12:
                    ViewModelWorkoutDetails viewModelWorkoutDetails13 = this.p;
                    if (viewModelWorkoutDetails13 != null && (liveDataFootBallSamples = viewModelWorkoutDetails13.getLiveDataFootBallSamples()) != null) {
                        liveDataFootBallSamples.observe(this, this.N);
                        break;
                    }
                    break;
                case 13:
                    ViewModelWorkoutDetails viewModelWorkoutDetails14 = this.p;
                    if (viewModelWorkoutDetails14 != null && (liveDataHikingSamples = viewModelWorkoutDetails14.getLiveDataHikingSamples()) != null) {
                        liveDataHikingSamples.observe(this, this.P);
                        break;
                    }
                    break;
                case 14:
                    ViewModelWorkoutDetails viewModelWorkoutDetails15 = this.p;
                    if (viewModelWorkoutDetails15 != null && (liveDataTennisSamples = viewModelWorkoutDetails15.getLiveDataTennisSamples()) != null) {
                        liveDataTennisSamples.observe(this, this.O);
                        break;
                    }
                    break;
                case 15:
                    ViewModelWorkoutDetails viewModelWorkoutDetails16 = this.p;
                    if (viewModelWorkoutDetails16 != null && (liveDataWorkoutSamples = viewModelWorkoutDetails16.getLiveDataWorkoutSamples()) != null) {
                        liveDataWorkoutSamples.observe(this, this.Q);
                        break;
                    }
                    break;
                case 16:
                    ViewModelWorkoutDetails viewModelWorkoutDetails17 = this.p;
                    if (viewModelWorkoutDetails17 != null && (liveDataYogaSamples = viewModelWorkoutDetails17.getLiveDataYogaSamples()) != null) {
                        liveDataYogaSamples.observe(this, this.R);
                        break;
                    }
                    break;
                case 17:
                    ViewModelWorkoutDetails viewModelWorkoutDetails18 = this.p;
                    if (viewModelWorkoutDetails18 != null && (liveDataSkippingSamples = viewModelWorkoutDetails18.getLiveDataSkippingSamples()) != null) {
                        liveDataSkippingSamples.observe(this, this.S);
                        break;
                    }
                    break;
                case 18:
                    ViewModelWorkoutDetails viewModelWorkoutDetails19 = this.p;
                    if (viewModelWorkoutDetails19 != null && (liveDataRowingMachineSamples = viewModelWorkoutDetails19.getLiveDataRowingMachineSamples()) != null) {
                        liveDataRowingMachineSamples.observe(this, this.U);
                        break;
                    }
                    break;
                case 19:
                    ViewModelWorkoutDetails viewModelWorkoutDetails20 = this.p;
                    if (viewModelWorkoutDetails20 != null && (liveDataEllipticalSamples = viewModelWorkoutDetails20.getLiveDataEllipticalSamples()) != null) {
                        liveDataEllipticalSamples.observe(this, this.T);
                        break;
                    }
                    break;
                default:
                    ViewModelWorkoutDetails viewModelWorkoutDetails21 = this.p;
                    if (viewModelWorkoutDetails21 != null && (liveDataRunSamples2 = viewModelWorkoutDetails21.getLiveDataRunSamples()) != null) {
                        liveDataRunSamples2.observe(this, this.C);
                        break;
                    }
                    break;
            }
        }
        setHRZoneDataToUi(entityWorkoutSession2);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m1() {
        /*
            Method dump skipped, instructions count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails.m1():void");
    }

    public final void n1(BarChart barChart, List<BarEntry> list, ArrayList<String> arrayList) {
        float f;
        BarDataSet barDataSet = new BarDataSet(list, "");
        barDataSet.setColor(ResourcesCompat.getColor(getResources(), R.color.color_9bb6fc, null));
        Resources resources = getResources();
        int i = R.color.secondary_text_color;
        barDataSet.setValueTextColor(resources.getColor(i));
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);
        barData.setDrawValues(false);
        barData.setValueTextColor(getResources().getColor(i));
        if (list != null) {
            Iterator<T> it = list.iterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            f = ((BarEntry) it.next()).getY();
            while (it.hasNext()) {
                f = Math.max(f, ((BarEntry) it.next()).getY());
            }
        } else {
            f = 200.0f;
        }
        barChart.setData(barData);
        barChart.getDescription().setText("");
        int i2 = R.color.secondary_text_color;
        barChart.setGridBackgroundColor(i2);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBorders(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setAutoScaleMinMaxEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.getLegend().setEnabled(false);
        barChart.setScrollContainer(true);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList));
        barChart.getPaint(7).setColor(getResources().getColor(R.color.colorPrimary));
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(i2));
        xAxis.setGranularityEnabled(true);
        YAxis axisLeft = barChart.getAxisLeft();
        axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        axisLeft.setDrawZeroLine(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setTextColor(getResources().getColor(i2));
        if (this.u == 0) {
            axisLeft.setAxisMinimum(0.0f);
            axisLeft.setAxisMaximum(100.0f);
        } else {
            axisLeft.setAxisMaximum(f);
            axisLeft.setAxisMinimum(0.0f);
        }
        if (this.u != 0) {
            ((TextView) _$_findCachedViewById(R.id.steps_value)).setText(String.valueOf(this.u));
        }
        YAxis axisRight = barChart.getAxisRight();
        axisRight.setEnabled(false);
        axisRight.setTextColor(getResources().getColor(i2));
        barChart.setClickable(false);
        barChart.invalidate();
    }

    public final void o1(LineChart lineChart, List<Entry> list, ArrayList<String> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<Entry> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry next = it.next();
            if (next.getY() == 0.0f) {
                arrayList2.add(next);
            }
        }
        list.removeAll(arrayList2);
        LineDataSet lineDataSet = new LineDataSet(list, "");
        lineDataSet.setColor(ResourcesCompat.getColor(getResources(), R.color.remsleep_color, null));
        Resources resources = getResources();
        int i = R.color.secondary_text_color;
        lineDataSet.setValueTextColor(resources.getColor(i));
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        LineData lineData = new LineData(lineDataSet);
        int i2 = R.id.heartrate_chart;
        ((LineChart) _$_findCachedViewById(i2)).setData(lineData);
        lineChart.getDescription().setText("");
        lineChart.setGridBackgroundColor(i);
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setAutoScaleMinMaxEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.getLegend().setEnabled(false);
        lineChart.setScrollContainer(true);
        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList));
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis axisLeft = lineChart.getAxisLeft();
        xAxis.setTextColor(getResources().getColor(i));
        xAxis.setGranularityEnabled(true);
        axisLeft.setTextColor(getResources().getColor(i));
        axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        axisLeft.setDrawZeroLine(false);
        axisLeft.setDrawGridLines(false);
        if (lineChart.equals((LineChart) _$_findCachedViewById(i2))) {
            if (this.w == 0 && this.v == 0) {
                axisLeft.setAxisMinimum(0.0f);
                axisLeft.setAxisMaximum(100.0f);
            }
            if (this.w != 0 && this.v != 0) {
                ((TextView) _$_findCachedViewById(R.id.heartrate_value)).setText(this.v + " - " + this.w + ' ' + getResources().getString(R.string.bpm_small));
            }
            axisLeft.setGranularity(10.0f);
        }
        YAxis axisRight = lineChart.getAxisRight();
        axisRight.setEnabled(false);
        axisRight.setTextColor(getResources().getColor(i));
        lineChart.setClickable(false);
        lineChart.invalidate();
    }

    @Override // com.coveiot.android.activitymodes.adapters.ActivityFeedbackAdapterNew.AnswerFeedbackInterface
    public void onAnswer(@NotNull AnswerModel answerModel) {
        FeedbackQuestionnarieModel feedbackQuestionnarieModel;
        MutableLiveData<Boolean> postQuestionarieLiveData;
        Intrinsics.checkNotNullParameter(answerModel, "answerModel");
        if (isFinishing() || (feedbackQuestionnarieModel = this.t) == null) {
            return;
        }
        if (feedbackQuestionnarieModel.getAnswers() == null) {
            feedbackQuestionnarieModel.setAnswers(new ArrayList());
        }
        List<AnswerModel> answers = feedbackQuestionnarieModel.getAnswers();
        Intrinsics.checkNotNull(answers);
        Iterator<AnswerModel> it = answers.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AnswerModel next = it.next();
            if (Intrinsics.areEqual(next.getQuestionId(), answerModel.getQuestionId())) {
                List<AnswerModel> answers2 = feedbackQuestionnarieModel.getAnswers();
                Intrinsics.checkNotNull(answers2);
                answers2.remove(next);
                break;
            }
        }
        List<AnswerModel> answers3 = feedbackQuestionnarieModel.getAnswers();
        Intrinsics.checkNotNull(answers3);
        answers3.add(answerModel);
        boolean z = false;
        if (AppUtils.isNetConnected(this)) {
            FeedbackQuestionnarieModel feedbackQuestionnarieModel2 = this.t;
            Intrinsics.checkNotNull(feedbackQuestionnarieModel2);
            List<AnswerModel> answers4 = feedbackQuestionnarieModel2.getAnswers();
            if ((answers4 == null || answers4.isEmpty()) ? true : true) {
                return;
            }
            showProgress();
            ViewModelAADWorkoutFeedback viewModelAADWorkoutFeedback = this.q;
            if (viewModelAADWorkoutFeedback != null && (postQuestionarieLiveData = viewModelAADWorkoutFeedback.getPostQuestionarieLiveData()) != null) {
                postQuestionarieLiveData.observe(this, new Observer<Boolean>() { // from class: com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$onAnswer$1$1

                    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$onAnswer$1$1$onChanged$1", f = "ActivityDataSummaryDetails.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* loaded from: classes2.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public int label;
                        public final /* synthetic */ ActivityDataSummaryDetails this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(ActivityDataSummaryDetails activityDataSummaryDetails, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.this$0 = activityDataSummaryDetails;
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
                                this.this$0.dismissProgress();
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails$onAnswer$1$1$onChanged$2", f = "ActivityDataSummaryDetails.kt", i = {}, l = {1872}, m = "invokeSuspend", n = {}, s = {})
                    /* loaded from: classes2.dex */
                    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public int label;
                        public final /* synthetic */ ActivityDataSummaryDetails this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public b(ActivityDataSummaryDetails activityDataSummaryDetails, Continuation<? super b> continuation) {
                            super(2, continuation);
                            this.this$0 = activityDataSummaryDetails;
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
                                EntityWorkoutSession mEntityWorkoutSession = this.this$0.getMEntityWorkoutSession();
                                if (mEntityWorkoutSession != null) {
                                    ActivityDataSummaryDetails activityDataSummaryDetails = this.this$0;
                                    this.label = 1;
                                    if (WorkoutSessionRepository.Companion.getInstance(activityDataSummaryDetails).updateSession(mEntityWorkoutSession, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                }
                            } else if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            } else {
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // androidx.lifecycle.Observer
                    public void onChanged(@Nullable Boolean bool) {
                        if (ActivityDataSummaryDetails.this.isFinishing()) {
                            return;
                        }
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityDataSummaryDetails.this), Dispatchers.getMain(), null, new a(ActivityDataSummaryDetails.this, null), 2, null);
                        if (bool != null) {
                            if (bool.booleanValue()) {
                                ActivityDataSummaryDetails activityDataSummaryDetails = ActivityDataSummaryDetails.this;
                                Toast.makeText(activityDataSummaryDetails, activityDataSummaryDetails.getString(R.string.save_successfully), 0).show();
                                FeedbackModel feedbackModel = new FeedbackModel();
                                FeedbackQuestionnarieModel feedbackQuestionnarieModel3 = ActivityDataSummaryDetails.this.t;
                                Intrinsics.checkNotNull(feedbackQuestionnarieModel3);
                                feedbackModel.setQuestionnaireId(feedbackQuestionnarieModel3.getQuestionnaireId());
                                FeedbackQuestionnarieModel feedbackQuestionnarieModel4 = ActivityDataSummaryDetails.this.t;
                                Intrinsics.checkNotNull(feedbackQuestionnarieModel4);
                                feedbackModel.setQuestionsAndAnswers(feedbackQuestionnarieModel4.getAnswers());
                                EntityWorkoutSession mEntityWorkoutSession = ActivityDataSummaryDetails.this.getMEntityWorkoutSession();
                                if (mEntityWorkoutSession != null) {
                                    mEntityWorkoutSession.setFeedback(new Gson().toJson(feedbackModel));
                                }
                                ((RecyclerView) ActivityDataSummaryDetails.this._$_findCachedViewById(R.id.rv_feedback)).setVisibility(8);
                                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new b(ActivityDataSummaryDetails.this, null), 2, null);
                                return;
                            }
                            ActivityDataSummaryDetails activityDataSummaryDetails2 = ActivityDataSummaryDetails.this;
                            Toast.makeText(activityDataSummaryDetails2, activityDataSummaryDetails2.getString(R.string.failed_to_save), 0).show();
                        }
                    }
                });
            }
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new b(null), 2, null);
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 0).show();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_data_summary);
        this.p = (ViewModelWorkoutDetails) ViewModelProviders.of(this).get(ViewModelWorkoutDetails.class);
        this.q = (ViewModelAADWorkoutFeedback) ViewModelProviders.of(this).get(ViewModelAADWorkoutFeedback.class);
        Bundle extras = getIntent().getExtras();
        this.s = WorkoutUtils.INSTANCE.getActivityMode(extras != null ? extras.getString(WorkoutConstants.ACTIVITY_MODE) : null);
        Bundle extras2 = getIntent().getExtras();
        this.r = extras2 != null ? extras2.getString(WorkoutConstants.SESSION_ID) : null;
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.map_fragment_details);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.google.android.gms.maps.SupportMapFragment");
        ((SupportMapFragment) findFragmentById).getMapAsync(this);
        initToolbar();
        initClickListeners();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(@NotNull GoogleMap p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Log.d(this.y, "onMapReady: called");
        this.x = p0;
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            GoogleMap googleMap = this.x;
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(false);
            }
            GoogleMap googleMap2 = this.x;
            UiSettings uiSettings = googleMap2 != null ? googleMap2.getUiSettings() : null;
            if (uiSettings != null) {
                uiSettings.setMyLocationButtonEnabled(false);
            }
            GoogleMap googleMap3 = this.x;
            UiSettings uiSettings2 = googleMap3 != null ? googleMap3.getUiSettings() : null;
            if (uiSettings2 != null) {
                uiSettings2.setScrollGesturesEnabled(true);
            }
            GoogleMap googleMap4 = this.x;
            UiSettings uiSettings3 = googleMap4 != null ? googleMap4.getUiSettings() : null;
            if (uiSettings3 != null) {
                uiSettings3.setZoomGesturesEnabled(true);
            }
            GoogleMap googleMap5 = this.x;
            UiSettings uiSettings4 = googleMap5 != null ? googleMap5.getUiSettings() : null;
            if (uiSettings4 == null) {
                return;
            }
            uiSettings4.setZoomControlsEnabled(true);
        }
    }

    public final void p0(ArrayList<ActivitySampleData> arrayList) {
        String X0;
        ArrayList arrayList2 = new ArrayList();
        ArrayList<SampleData> arrayList3 = new ArrayList<>();
        ArrayList arrayList4 = new ArrayList();
        ArrayList<String> arrayList5 = new ArrayList<>();
        ArrayList<String> arrayList6 = new ArrayList<>();
        this.z = 0;
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            this.z += arrayList.get(i3).getStepCount();
            arrayList3.add(arrayList.get(i3).getSampleData());
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
                String X02 = X0(arrayList.get(i3).getSampleData().getTimeStamp());
                arrayList4.add(new BarEntry(i, arrayList.get(i3).getStepCount()));
                arrayList5.add(X02);
                i++;
                if (arrayList.get(i3).getSampleData().getHr_value() > 0) {
                    arrayList2.add(new Entry(i2, arrayList.get(i3).getSampleData().getHr_value()));
                    arrayList6.add(X0(arrayList.get(i3).getSampleData().getTimeStamp()));
                }
            } else {
                try {
                    X0 = X0(arrayList.get(i3).getSampleData().getTimeStamp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (arrayList5.contains(X0)) {
                    if (arrayList.get(i3).getStepCount() > 0) {
                        arrayList4.set(arrayList5.indexOf(X0), new BarEntry(arrayList5.indexOf(X0), arrayList.get(i3).getStepCount()));
                        arrayList5.set(arrayList5.indexOf(X0), X0);
                    }
                    if (!arrayList6.contains(X0) && arrayList.get(i3).getSampleData().getHr_value() > 0) {
                        arrayList2.add(new Entry(i2, arrayList.get(i3).getSampleData().getHr_value()));
                        arrayList6.add(X0);
                    }
                } else {
                    arrayList4.add(new BarEntry(i, arrayList.get(i3).getStepCount()));
                    arrayList5.add(X0);
                }
                i++;
                if (!arrayList6.contains(X0)) {
                    arrayList2.add(new Entry(i2, arrayList.get(i3).getSampleData().getHr_value()));
                    arrayList6.add(X0);
                }
            }
            i2++;
        }
        p1(arrayList3);
        BarChart steps_chart = (BarChart) _$_findCachedViewById(R.id.steps_chart);
        Intrinsics.checkNotNullExpressionValue(steps_chart, "steps_chart");
        n1(steps_chart, arrayList4, arrayList5);
        LineChart heartrate_chart = (LineChart) _$_findCachedViewById(R.id.heartrate_chart);
        Intrinsics.checkNotNullExpressionValue(heartrate_chart, "heartrate_chart");
        o1(heartrate_chart, arrayList2, arrayList6);
        m1();
    }

    public final void p1(ArrayList<SampleData> arrayList) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(ContextCompat.getColor(this, R.color.colorPrimary));
        Iterator<SampleData> it = arrayList.iterator();
        while (it.hasNext()) {
            SampleData next = it.next();
            if (!(next.getLatitude() == 0.0d)) {
                if (!(next.getLongitude() == 0.0d)) {
                    polylineOptions.add(new LatLng(next.getLatitude(), next.getLongitude()));
                    builder.include(new LatLng(next.getLatitude(), next.getLongitude()));
                }
            }
        }
        if (polylineOptions.getPoints() != null && polylineOptions.getPoints().size() > 0) {
            drawPolyLineOnMap(polylineOptions, builder);
        } else {
            ((LinearLayout) _$_findCachedViewById(R.id.map_fragment_details_layout)).setVisibility(8);
        }
    }

    public final void q1(EntityWorkoutSession entityWorkoutSession) {
        ActivityShareData activityShareData = new ActivityShareData();
        activityShareData.setDuration(entityWorkoutSession.getSession_duration());
        activityShareData.setDistance(entityWorkoutSession.getTotal_distance());
        activityShareData.setHeartRate(entityWorkoutSession.getAvg_hr());
        activityShareData.setMinHeartRate(entityWorkoutSession.getMin_hr());
        activityShareData.setMaxHeartRate(entityWorkoutSession.getMax_hr());
        activityShareData.setCal(entityWorkoutSession.getTotal_calories());
        activityShareData.setPace(entityWorkoutSession.getPace());
        activityShareData.setSteps(entityWorkoutSession.getTotal_steps());
        activityShareData.setDwmValue(String.valueOf(entityWorkoutSession.getStart_time()));
        activityShareData.setActivityType(entityWorkoutSession.getActivity_type());
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        Intrinsics.checkNotNull(entityWorkoutSession);
        activityShareData.setActivityTitleName(workoutUtils.getActivityModeNames(workoutUtils.getActivityMode(entityWorkoutSession.getActivity_type()), this, entityWorkoutSession));
        activityShareData.setActivityId(entityWorkoutSession.getActivityId());
        activityShareData.setCategoryId(entityWorkoutSession.getCategoryId());
        Float avgSpeed = entityWorkoutSession.getAvgSpeed();
        if (avgSpeed != null) {
            avgSpeed.floatValue();
            Float avgSpeed2 = entityWorkoutSession.getAvgSpeed();
            Intrinsics.checkNotNull(avgSpeed2);
            activityShareData.setAvgSpeed(avgSpeed2.floatValue());
        }
        Integer avgStrideLength = entityWorkoutSession.getAvgStrideLength();
        if (avgStrideLength != null) {
            avgStrideLength.intValue();
            Integer avgStrideLength2 = entityWorkoutSession.getAvgStrideLength();
            Intrinsics.checkNotNull(avgStrideLength2);
            activityShareData.setAvgStrideLength(avgStrideLength2.intValue());
        }
        if (entityWorkoutSession.getSwimmingStyle() != null) {
            String swimmingStyle = entityWorkoutSession.getSwimmingStyle();
            Intrinsics.checkNotNull(swimmingStyle);
            activityShareData.setSwimStroke(swimmingStyle);
        }
        Integer totalStrokes = entityWorkoutSession.getTotalStrokes();
        if (totalStrokes != null) {
            totalStrokes.intValue();
            Integer totalStrokes2 = entityWorkoutSession.getTotalStrokes();
            Intrinsics.checkNotNull(totalStrokes2);
            activityShareData.setTotalStrokes(totalStrokes2.intValue());
        }
        Integer totalLaps = entityWorkoutSession.getTotalLaps();
        if (totalLaps != null) {
            totalLaps.intValue();
            Integer totalLaps2 = entityWorkoutSession.getTotalLaps();
            Intrinsics.checkNotNull(totalLaps2);
            activityShareData.setTotalLaps(totalLaps2.intValue());
        }
        Integer avgSwolf = entityWorkoutSession.getAvgSwolf();
        if (avgSwolf != null) {
            avgSwolf.intValue();
            Integer avgSwolf2 = entityWorkoutSession.getAvgSwolf();
            Intrinsics.checkNotNull(avgSwolf2);
            activityShareData.setAvgSwolf(avgSwolf2.intValue());
        }
        if (entityWorkoutSession.getIndoor_outdoor() != null && kotlin.text.m.equals(entityWorkoutSession.getIndoor_outdoor(), "INDOOR", true)) {
            activityShareData.setIndoor(true);
        }
        Float maxPace = entityWorkoutSession.getMaxPace();
        if (maxPace != null) {
            maxPace.floatValue();
            Float maxPace2 = entityWorkoutSession.getMaxPace();
            Intrinsics.checkNotNull(maxPace2);
            activityShareData.setMaxPace(maxPace2.floatValue());
        }
        Float minPace = entityWorkoutSession.getMinPace();
        if (minPace != null) {
            minPace.floatValue();
            Float minPace2 = entityWorkoutSession.getMinPace();
            Intrinsics.checkNotNull(minPace2);
            activityShareData.setMinPace(minPace2.floatValue());
        }
        Integer maxStepFrequency = entityWorkoutSession.getMaxStepFrequency();
        if (maxStepFrequency != null) {
            maxStepFrequency.intValue();
            Integer maxStepFrequency2 = entityWorkoutSession.getMaxStepFrequency();
            Intrinsics.checkNotNull(maxStepFrequency2);
            activityShareData.setMaxSPM(maxStepFrequency2.intValue());
        }
        Integer avgStepFrequency = entityWorkoutSession.getAvgStepFrequency();
        if (avgStepFrequency != null) {
            avgStepFrequency.intValue();
            Integer avgStepFrequency2 = entityWorkoutSession.getAvgStepFrequency();
            Intrinsics.checkNotNull(avgStepFrequency2);
            activityShareData.setAvgStepFreq(avgStepFrequency2.intValue());
        }
        Integer avgStrokeFreq = entityWorkoutSession.getAvgStrokeFreq();
        if (avgStrokeFreq != null) {
            avgStrokeFreq.intValue();
            Integer avgStrokeFreq2 = entityWorkoutSession.getAvgStrokeFreq();
            Intrinsics.checkNotNull(avgStrokeFreq2);
            activityShareData.setAvgStrokeFreq(avgStrokeFreq2.intValue());
        }
        Float avgPace = entityWorkoutSession.getAvgPace();
        if (avgPace != null) {
            avgPace.floatValue();
            Float avgPace2 = entityWorkoutSession.getAvgPace();
            Intrinsics.checkNotNull(avgPace2);
            activityShareData.setAvgPace((int) avgPace2.floatValue());
        }
        Intent intent = new Intent(this, ActivityModeShareScreen.class);
        intent.putExtra("share_data", activityShareData);
        startActivity(intent);
    }

    public final boolean r1() {
        if (DeviceUtils.Companion.isIDODevice(this)) {
            if (WhenMappings.$EnumSwitchMapping$0[this.s.ordinal()] == 22) {
                return false;
            }
        } else if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getDeviceType() == DeviceType.TOUCH_WAVE_NEO) {
            return false;
        }
        return true;
    }

    public final boolean s1(EntityWorkoutSession entityWorkoutSession) {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isJstyleDevice(this)) {
            int i = WhenMappings.$EnumSwitchMapping$0[this.s.ordinal()];
            if (i != 7 && i != 8) {
                return true;
            }
        } else if (companion.isSmaDevice(this)) {
            return SMAWorkoutUtils.Companion.getInstance(this).isStepsApplicable(this, this.s.name(), Integer.valueOf(this.u), WorkoutUiBeanProvider.ScreenType.ACTIVITY_DETAIL, null, null);
        } else {
            if (!companion.isMoyangDevice(this)) {
                if (companion.isIDODevice(this)) {
                    int i2 = WhenMappings.$EnumSwitchMapping$0[this.s.ordinal()];
                    if (i2 == 1 || i2 == 3 || i2 == 13 || i2 == 19 || i2 == 20) {
                        return true;
                    }
                } else if (companion.isMatrixDevice(this)) {
                    int i3 = WhenMappings.$EnumSwitchMapping$0[this.s.ordinal()];
                    if (i3 == 1 || i3 == 3 || i3 == 10 || i3 == 21) {
                        return true;
                    }
                } else if (companion.isCZDevice(this)) {
                    Integer categoryId = entityWorkoutSession.getCategoryId();
                    if (categoryId != null && (categoryId.intValue() == 17 || categoryId.intValue() == 12)) {
                        return true;
                    }
                } else if (!companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
                    if (!companion.isTouchELXDevice(this)) {
                        companion.isEastApexDevice(this);
                    }
                } else {
                    Integer categoryId2 = entityWorkoutSession.getCategoryId();
                    if (categoryId2 != null && (categoryId2.intValue() == 17 || categoryId2.intValue() == 12)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final void setGoogleMap(@Nullable GoogleMap googleMap) {
        this.x = googleMap;
    }

    public final void setHRZoneDataToUi(EntityWorkoutSession entityWorkoutSession) {
        TimeSpentHeartRateZone timespent_per_heartratezone = entityWorkoutSession.getTimespent_per_heartratezone();
        if (timespent_per_heartratezone != null) {
            if (DeviceUtils.Companion.isEastApexDevice(this)) {
                int zone0Time = timespent_per_heartratezone.getZone0Time() + timespent_per_heartratezone.getZone1Time() + timespent_per_heartratezone.getZone2Time() + timespent_per_heartratezone.getZone3Time() + timespent_per_heartratezone.getZone4Time();
                int i = R.id.pb_warm;
                ViewGroup.LayoutParams layoutParams = ((ProgressBar) _$_findCachedViewById(i)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams).weight = 0.35f;
                ViewGroup.LayoutParams layoutParams2 = ((LinearLayout) _$_findCachedViewById(R.id.pb_warm_ll)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams2).weight = 0.65f;
                int i2 = R.id.pb_fat_burn;
                ViewGroup.LayoutParams layoutParams3 = ((ProgressBar) _$_findCachedViewById(i2)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams3).weight = 0.35f;
                ViewGroup.LayoutParams layoutParams4 = ((LinearLayout) _$_findCachedViewById(R.id.pb_fat_burn_ll)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams4, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams4).weight = 0.65f;
                int i3 = R.id.pb_cardio;
                ViewGroup.LayoutParams layoutParams5 = ((ProgressBar) _$_findCachedViewById(i3)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams5, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams5).weight = 0.35f;
                ViewGroup.LayoutParams layoutParams6 = ((LinearLayout) _$_findCachedViewById(R.id.pb_cardio_ll)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams6, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams6).weight = 0.65f;
                int i4 = R.id.pb_threshold;
                ViewGroup.LayoutParams layoutParams7 = ((ProgressBar) _$_findCachedViewById(i4)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams7, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams7).weight = 0.35f;
                ViewGroup.LayoutParams layoutParams8 = ((LinearLayout) _$_findCachedViewById(R.id.pb_threshold_ll)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams8, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams8).weight = 0.65f;
                int i5 = R.id.pb_peak;
                ViewGroup.LayoutParams layoutParams9 = ((ProgressBar) _$_findCachedViewById(i5)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams9, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams9).weight = 0.35f;
                ViewGroup.LayoutParams layoutParams10 = ((LinearLayout) _$_findCachedViewById(R.id.pb_peak_ll)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams10, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                ((LinearLayout.LayoutParams) layoutParams10).weight = 0.65f;
                ((TextView) _$_findCachedViewById(R.id.tv_warmlabel)).setText("Warm up");
                ((TextView) _$_findCachedViewById(R.id.tv_fat_burn_label)).setText("Fat burning");
                ((TextView) _$_findCachedViewById(R.id.tv_cardio_label)).setText("Aerobic");
                ((TextView) _$_findCachedViewById(R.id.tv_threshold_label)).setText("Anaerobic");
                ((TextView) _$_findCachedViewById(R.id.tv_peak_label)).setText(RrHrHelperKt.e);
                int zone0Time2 = timespent_per_heartratezone.getZone0Time();
                if ((1 <= zone0Time2 && zone0Time2 < 60) && timespent_per_heartratezone.getZone0Time() > 0) {
                    ((TextView) _$_findCachedViewById(R.id.tv_progress_warm)).setText(timespent_per_heartratezone.getZone0Time() + " Secs");
                } else {
                    ((TextView) _$_findCachedViewById(R.id.tv_progress_warm)).setText((timespent_per_heartratezone.getZone0Time() / 60) + " Min");
                }
                int zone1Time = timespent_per_heartratezone.getZone1Time();
                if ((1 <= zone1Time && zone1Time < 60) && timespent_per_heartratezone.getZone1Time() > 0) {
                    ((TextView) _$_findCachedViewById(R.id.tv_progress_fat_burn)).setText(timespent_per_heartratezone.getZone1Time() + " Secs");
                } else {
                    ((TextView) _$_findCachedViewById(R.id.tv_progress_fat_burn)).setText((timespent_per_heartratezone.getZone1Time() / 60) + " Min");
                }
                int zone2Time = timespent_per_heartratezone.getZone2Time();
                if ((1 <= zone2Time && zone2Time < 60) && timespent_per_heartratezone.getZone2Time() > 0) {
                    ((TextView) _$_findCachedViewById(R.id.tv_progress_cardio)).setText(timespent_per_heartratezone.getZone2Time() + " Secs");
                } else {
                    ((TextView) _$_findCachedViewById(R.id.tv_progress_cardio)).setText((timespent_per_heartratezone.getZone2Time() / 60) + " Min");
                }
                int zone3Time = timespent_per_heartratezone.getZone3Time();
                if ((1 <= zone3Time && zone3Time < 60) && timespent_per_heartratezone.getZone3Time() > 0) {
                    ((TextView) _$_findCachedViewById(R.id.tv_progress_threshold)).setText(timespent_per_heartratezone.getZone3Time() + " Secs");
                } else {
                    ((TextView) _$_findCachedViewById(R.id.tv_progress_threshold)).setText((timespent_per_heartratezone.getZone3Time() / 60) + " Min");
                }
                int zone4Time = timespent_per_heartratezone.getZone4Time();
                if ((1 <= zone4Time && zone4Time < 60) && timespent_per_heartratezone.getZone4Time() > 0) {
                    ((TextView) _$_findCachedViewById(R.id.tv_progress_peak)).setText(timespent_per_heartratezone.getZone4Time() + " Secs");
                } else {
                    ((TextView) _$_findCachedViewById(R.id.tv_progress_peak)).setText((timespent_per_heartratezone.getZone4Time() / 60) + " Min");
                }
                if (timespent_per_heartratezone.getZone0Time() > 0) {
                    ((ProgressBar) _$_findCachedViewById(i)).setProgress(kotlin.math.c.roundToInt((timespent_per_heartratezone.getZone0Time() / zone0Time) * 100));
                }
                if (timespent_per_heartratezone.getZone1Time() > 0) {
                    ((ProgressBar) _$_findCachedViewById(i2)).setProgress(kotlin.math.c.roundToInt((timespent_per_heartratezone.getZone1Time() / zone0Time) * 100));
                }
                if (timespent_per_heartratezone.getZone2Time() > 0) {
                    ((ProgressBar) _$_findCachedViewById(i3)).setProgress(kotlin.math.c.roundToInt((timespent_per_heartratezone.getZone2Time() / zone0Time) * 100));
                }
                if (timespent_per_heartratezone.getZone3Time() > 0) {
                    ((ProgressBar) _$_findCachedViewById(i4)).setProgress(kotlin.math.c.roundToInt((timespent_per_heartratezone.getZone3Time() / zone0Time) * 100));
                }
                if (timespent_per_heartratezone.getZone4Time() > 0) {
                    ((ProgressBar) _$_findCachedViewById(i5)).setProgress(kotlin.math.c.roundToInt((timespent_per_heartratezone.getZone4Time() / zone0Time) * 100));
                    return;
                }
                return;
            }
            int zone1Time2 = timespent_per_heartratezone.getZone1Time() + timespent_per_heartratezone.getZone2Time() + timespent_per_heartratezone.getZone3Time() + timespent_per_heartratezone.getZone4Time() + timespent_per_heartratezone.getZone5Time();
            if (zone1Time2 > 0) {
                StringBuilder sb = new StringBuilder();
                float f = zone1Time2;
                float f2 = 100;
                sb.append(kotlin.math.c.roundToInt((timespent_per_heartratezone.getZone1Time() / f) * f2));
                sb.append(" %");
                ((TextView) _$_findCachedViewById(R.id.tv_progress_warm)).setText(sb.toString());
                ((TextView) _$_findCachedViewById(R.id.tv_progress_fat_burn)).setText(kotlin.math.c.roundToInt((timespent_per_heartratezone.getZone2Time() / f) * f2) + " %");
                ((TextView) _$_findCachedViewById(R.id.tv_progress_cardio)).setText(kotlin.math.c.roundToInt((((float) timespent_per_heartratezone.getZone3Time()) / f) * f2) + " %");
                ((TextView) _$_findCachedViewById(R.id.tv_progress_threshold)).setText(kotlin.math.c.roundToInt((((float) timespent_per_heartratezone.getZone4Time()) / f) * f2) + " %");
                ((TextView) _$_findCachedViewById(R.id.tv_progress_peak)).setText(kotlin.math.c.roundToInt((((float) timespent_per_heartratezone.getZone5Time()) / f) * f2) + " %");
                ((ProgressBar) _$_findCachedViewById(R.id.pb_warm)).setProgress(kotlin.math.c.roundToInt((((float) timespent_per_heartratezone.getZone1Time()) / f) * f2));
                ((ProgressBar) _$_findCachedViewById(R.id.pb_fat_burn)).setProgress(kotlin.math.c.roundToInt((((float) timespent_per_heartratezone.getZone2Time()) / f) * f2));
                ((ProgressBar) _$_findCachedViewById(R.id.pb_cardio)).setProgress(kotlin.math.c.roundToInt((((float) timespent_per_heartratezone.getZone3Time()) / f) * f2));
                ((ProgressBar) _$_findCachedViewById(R.id.pb_threshold)).setProgress(kotlin.math.c.roundToInt((timespent_per_heartratezone.getZone4Time() / f) * f2));
                ((ProgressBar) _$_findCachedViewById(R.id.pb_peak)).setProgress(kotlin.math.c.roundToInt((timespent_per_heartratezone.getZone5Time() / f) * f2));
            }
        }
    }

    public final void setHrMax(int i) {
        this.w = i;
    }

    public final void setHrMin(int i) {
        this.v = i;
    }

    public final void setMEntityWorkoutSession(@Nullable EntityWorkoutSession entityWorkoutSession) {
        this.A = entityWorkoutSession;
    }

    public final void setSteps(int i) {
        this.u = i;
    }

    public final boolean shouldShowDistanceInfo(@NotNull ActivityMode activityMode) {
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isJstyleDevice(this)) {
            int i = WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()];
            if (i != 1 && i != 3 && i != 13) {
                return false;
            }
        } else if (companion.isSmaDevice(this)) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()];
            if (i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7 || i2 == 12 || i2 == 16) {
                return false;
            }
        } else if (companion.isMoyangDevice(this)) {
            int i3 = WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()];
            if (i3 == 4 || i3 == 12 || i3 == 16 || i3 == 6 || i3 == 7) {
                return false;
            }
        } else if (companion.isMatrixDevice(this)) {
            int i4 = WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()];
            if (i4 != 1 && i4 != 3 && i4 != 10) {
                return false;
            }
        } else if (companion.isIDODevice(this)) {
            int i5 = WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()];
            if (i5 != 1 && i5 != 3 && i5 != 13) {
                return false;
            }
        } else if (companion.isTouchELXDevice(this)) {
            int i6 = WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()];
            if (i6 != 1 && i6 != 3 && i6 != 7) {
                return false;
            }
        } else if (!companion.isEastApexDevice(this)) {
            return false;
        } else {
            int i7 = WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()];
            if (i7 != 1 && i7 != 3 && i7 != 7) {
                return false;
            }
        }
        return true;
    }
}
