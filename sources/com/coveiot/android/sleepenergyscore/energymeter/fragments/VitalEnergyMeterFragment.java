package com.coveiot.android.sleepenergyscore.energymeter.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.databinding.FragmentVitalEnergyMeterBinding;
import com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreEventData;
import com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreFeedbackEventData;
import com.coveiot.android.sleepenergyscore.energymeter.activities.ActivityShare;
import com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterInsightModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterLineChartModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterPieChartBean;
import com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterPieChartModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.ShareEnergyMeterData;
import com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModelNew;
import com.coveiot.android.sleepenergyscore.feedback.ContractFeedBackQuestionsList;
import com.coveiot.android.sleepenergyscore.feedback.DismissLoader;
import com.coveiot.android.sleepenergyscore.feedback.PagerAdapterFeedback;
import com.coveiot.android.sleepenergyscore.sleepscore.SleepScoreApiCall;
import com.coveiot.android.sleepenergyscore.utils.Constants;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import com.coveiot.android.sleepenergyscore.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.adapter.TipsAdapter;
import com.coveiot.android.theme.compundview.CustomMarkerViewPieChart;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.android.theme.model.TipsModel;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.squareup.otto.Subscribe;
import com.viewpagerindicator.CirclePageIndicator;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class VitalEnergyMeterFragment extends BaseFragment implements View.OnClickListener, ContractFeedBackQuestionsList {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalEnergyMeterBinding m;
    public FragmentEnergyMeterViewModelNew n;
    @NotNull
    public Calendar o;
    public boolean p;
    public boolean q;
    @NotNull
    public final int[] r;
    @NotNull
    public final int[] s;
    public SimpleDateFormat simpleDateFormat;
    @NotNull
    public final ArrayList<PieEntry> t;
    @NotNull
    public int[] u;
    @NotNull
    public int[] v;
    @NotNull
    public int[] w;
    @NotNull
    public int[] x;

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VitalEnergyMeterFragment newInstance() {
            return new VitalEnergyMeterFragment();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$handleSelectedDate$1", f = "VitalEnergyMeterFragment.kt", i = {}, l = {396, 404, com.veryfit.multi.nativeprotocol.b.C1}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$handleSelectedDate$1$1", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$a$a  reason: collision with other inner class name */
        /* loaded from: classes6.dex */
        public static final class C0303a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ VitalEnergyMeterFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0303a(VitalEnergyMeterFragment vitalEnergyMeterFragment, Continuation<? super C0303a> continuation) {
                super(2, continuation);
                this.this$0 = vitalEnergyMeterFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0303a(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0303a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ImageButton imageButton = this.this$0.z().vitalsMainData.ibShareVitals;
                    if (imageButton != null) {
                        imageButton.setVisibility(0);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$handleSelectedDate$1$2", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes6.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ VitalEnergyMeterFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(VitalEnergyMeterFragment vitalEnergyMeterFragment, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = vitalEnergyMeterFragment;
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
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew = this.this$0.n;
                    if (fragmentEnergyMeterViewModelNew == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentEnergyMeterViewModelNew = null;
                    }
                    fragmentEnergyMeterViewModelNew.getFeedbackQuestionnarieList();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$handleSelectedDate$1$3", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes6.dex */
        public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ VitalEnergyMeterFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(VitalEnergyMeterFragment vitalEnergyMeterFragment, Continuation<? super c> continuation) {
                super(2, continuation);
                this.this$0 = vitalEnergyMeterFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new c(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    LinearLayout linearLayout = this.this$0.z().viewPagerLayout;
                    if (linearLayout != null) {
                        linearLayout.setVisibility(8);
                    }
                    ImageButton imageButton = this.this$0.z().vitalsMainData.ibShareVitals;
                    if (imageButton != null) {
                        imageButton.setVisibility(4);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$handleSelectedDate$1$4", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes6.dex */
        public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ VitalEnergyMeterFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d(VitalEnergyMeterFragment vitalEnergyMeterFragment, Continuation<? super d> continuation) {
                super(2, continuation);
                this.this$0 = vitalEnergyMeterFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new d(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    Toast.makeText(this.this$0.requireContext(), this.this$0.getResources().getString(R.string.please_check_network), 0).show();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew = VitalEnergyMeterFragment.this.n;
                if (fragmentEnergyMeterViewModelNew == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentEnergyMeterViewModelNew = null;
                }
                fragmentEnergyMeterViewModelNew.loadEnergyData(VitalEnergyMeterFragment.this.o);
                Calendar calendar = VitalEnergyMeterFragment.this.o;
                Calendar endCal = Calendar.getInstance();
                endCal.setTimeInMillis(VitalEnergyMeterFragment.this.o.getTimeInMillis());
                endCal.add(6, -5);
                if (AppUtils.isNetConnected(VitalEnergyMeterFragment.this.getContext())) {
                    SleepScoreApiCall sleepScoreApiCall = SleepScoreApiCall.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(endCal, "endCal");
                    Context requireContext = VitalEnergyMeterFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    String string = VitalEnergyMeterFragment.this.getResources().getString(R.string.energy_meter);
                    Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.energy_meter)");
                    sleepScoreApiCall.getSleepScoreBatchApiCall(endCal, calendar, requireContext, string);
                    Calendar calendar2 = VitalEnergyMeterFragment.this.o;
                    if (Intrinsics.areEqual(RepositoryUtils.formatDate(calendar2 != null ? calendar2.getTime() : null, "yyyy-MM-dd"), RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"))) {
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        C0303a c0303a = new C0303a(VitalEnergyMeterFragment.this, null);
                        this.label = 1;
                        if (BuildersKt.withContext(main, c0303a, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(VitalEnergyMeterFragment.this), Dispatchers.getIO(), null, new b(VitalEnergyMeterFragment.this, null), 2, null);
                    } else {
                        MainCoroutineDispatcher main2 = Dispatchers.getMain();
                        c cVar = new c(VitalEnergyMeterFragment.this, null);
                        this.label = 2;
                        if (BuildersKt.withContext(main2, cVar, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    MainCoroutineDispatcher main3 = Dispatchers.getMain();
                    d dVar = new d(VitalEnergyMeterFragment.this, null);
                    this.label = 3;
                    if (BuildersKt.withContext(main3, dVar, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(VitalEnergyMeterFragment.this), Dispatchers.getIO(), null, new b(VitalEnergyMeterFragment.this, null), 2, null);
            } else if (i != 2 && i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$onEnergyScoreCalculated$1", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Calendar calendar = VitalEnergyMeterFragment.this.o;
                if (calendar != null) {
                    FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew = VitalEnergyMeterFragment.this.n;
                    if (fragmentEnergyMeterViewModelNew == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentEnergyMeterViewModelNew = null;
                    }
                    fragmentEnergyMeterViewModelNew.loadEnergyData(calendar);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$onEnergyScoreFeedbackUiUpdate$1", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoveEventBusManager.getInstance().getEventBus().post(new DismissLoader());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$onEnergyScoreFeedbackUiUpdate$2", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew = VitalEnergyMeterFragment.this.n;
                if (fragmentEnergyMeterViewModelNew == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentEnergyMeterViewModelNew = null;
                }
                fragmentEnergyMeterViewModelNew.getFeedbackQuestionnarieList();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$onReceiveQuestionList$1", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<FeedbackQuetionnarieModel> $questionList;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(ArrayList<FeedbackQuetionnarieModel> arrayList, Continuation<? super e> continuation) {
            super(2, continuation);
            this.$questionList = arrayList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e(this.$questionList, continuation);
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
                FragmentVitalEnergyMeterBinding z = VitalEnergyMeterFragment.this.z();
                if (z != null) {
                    ArrayList<FeedbackQuetionnarieModel> arrayList = this.$questionList;
                    VitalEnergyMeterFragment vitalEnergyMeterFragment = VitalEnergyMeterFragment.this;
                    if (arrayList != null && arrayList.size() > 0) {
                        LinearLayout linearLayout = z.viewPagerLayout;
                        if (linearLayout != null) {
                            linearLayout.setVisibility(0);
                        }
                        ViewPager viewPager = z.energyFeedbackPager;
                        if (viewPager != null) {
                            Context requireContext = vitalEnergyMeterFragment.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            FragmentManager childFragmentManager = vitalEnergyMeterFragment.getChildFragmentManager();
                            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                            viewPager.setAdapter(new PagerAdapterFeedback(requireContext, childFragmentManager, arrayList));
                        }
                        CirclePageIndicator circlePageIndicator = z.circlePageIndicatorFeedback;
                        if (circlePageIndicator != null) {
                            circlePageIndicator.setViewPager(z.energyFeedbackPager);
                        }
                        if (arrayList.size() > 1) {
                            CirclePageIndicator circlePageIndicator2 = z.circlePageIndicatorFeedback;
                            if (circlePageIndicator2 != null) {
                                circlePageIndicator2.setVisibility(0);
                            }
                        } else {
                            CirclePageIndicator circlePageIndicator3 = z.circlePageIndicatorFeedback;
                            if (circlePageIndicator3 != null) {
                                circlePageIndicator3.setVisibility(8);
                            }
                        }
                    } else {
                        LinearLayout linearLayout2 = z.viewPagerLayout;
                        if (linearLayout2 != null) {
                            linearLayout2.setVisibility(8);
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes6.dex */
    public static final class f extends Lambda implements Function1<Integer, Unit> {

        @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$registerLiveData$1$1", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes6.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Integer $it;
            public int label;
            public final /* synthetic */ VitalEnergyMeterFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(VitalEnergyMeterFragment vitalEnergyMeterFragment, Integer num, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = vitalEnergyMeterFragment;
                this.$it = num;
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
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    FragmentVitalEnergyMeterBinding z = this.this$0.z();
                    if (z != null) {
                        Integer it = this.$it;
                        VitalEnergyMeterFragment vitalEnergyMeterFragment = this.this$0;
                        z.vitalsMainData.tvAvgStepsSleepValue.setText(String.valueOf(it));
                        TextView textView = z.vitalsMainData.tvLastSyncTime;
                        Utils utils = Utils.INSTANCE;
                        Context requireContext = vitalEnergyMeterFragment.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        textView.setText(utils.getLastSyncTime(requireContext));
                        TextView textView2 = z.vitalsMainData.tvVitalInfo;
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        int intValue = it.intValue();
                        Context requireContext2 = vitalEnergyMeterFragment.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        textView2.setText(Utils.getEnergyInfo(intValue, requireContext2));
                        if (it.intValue() > 0) {
                            vitalEnergyMeterFragment.z().tvEndScore.setText(String.valueOf(it));
                        } else {
                            vitalEnergyMeterFragment.z().tvEndScore.setText("--");
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public f() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Integer num) {
            if (VitalEnergyMeterFragment.this.isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(VitalEnergyMeterFragment.this), Dispatchers.getMain(), null, new a(VitalEnergyMeterFragment.this, num, null), 2, null);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class g extends Lambda implements Function1<EnergyMeterLineChartModel, Unit> {

        @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$registerLiveData$2$1", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes6.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ EnergyMeterLineChartModel $it;
            public int label;
            public final /* synthetic */ VitalEnergyMeterFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(EnergyMeterLineChartModel energyMeterLineChartModel, VitalEnergyMeterFragment vitalEnergyMeterFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$it = energyMeterLineChartModel;
                this.this$0 = vitalEnergyMeterFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$it, this.this$0, continuation);
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
                    EnergyMeterLineChartModel energyMeterLineChartModel = this.$it;
                    if (energyMeterLineChartModel == null) {
                        this.this$0.z().tvStartScore.setText("--");
                        this.this$0.z().tvEndScore.setText("--");
                    } else {
                        this.this$0.updateEnergyMeterLineChart(energyMeterLineChartModel.getBarEntries(), this.$it.getLables());
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public g() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(EnergyMeterLineChartModel energyMeterLineChartModel) {
            invoke2(energyMeterLineChartModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable EnergyMeterLineChartModel energyMeterLineChartModel) {
            if (VitalEnergyMeterFragment.this.isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(VitalEnergyMeterFragment.this), Dispatchers.getMain(), null, new a(energyMeterLineChartModel, VitalEnergyMeterFragment.this, null), 2, null);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class h extends Lambda implements Function1<EnergyMeterPieChartModel, Unit> {

        @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$registerLiveData$3$1", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes6.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ EnergyMeterPieChartModel $it;
            public int label;
            public final /* synthetic */ VitalEnergyMeterFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(EnergyMeterPieChartModel energyMeterPieChartModel, VitalEnergyMeterFragment vitalEnergyMeterFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$it = energyMeterPieChartModel;
                this.this$0 = vitalEnergyMeterFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$it, this.this$0, continuation);
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
                    EnergyMeterPieChartModel energyMeterPieChartModel = this.$it;
                    if (energyMeterPieChartModel != null) {
                        this.this$0.e0(energyMeterPieChartModel);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public h() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(EnergyMeterPieChartModel energyMeterPieChartModel) {
            invoke2(energyMeterPieChartModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable EnergyMeterPieChartModel energyMeterPieChartModel) {
            if (VitalEnergyMeterFragment.this.isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(VitalEnergyMeterFragment.this), Dispatchers.getMain(), null, new a(energyMeterPieChartModel, VitalEnergyMeterFragment.this, null), 2, null);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class i extends Lambda implements Function1<Integer, Unit> {

        @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$registerLiveData$4$1", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes6.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Integer $it;
            public int label;
            public final /* synthetic */ VitalEnergyMeterFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Integer num, VitalEnergyMeterFragment vitalEnergyMeterFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$it = num;
                this.this$0 = vitalEnergyMeterFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$it, this.this$0, continuation);
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
                    Integer it = this.$it;
                    if (it != null) {
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        if (it.intValue() > 0) {
                            this.this$0.z().tvEndScore.setText(String.valueOf(this.$it));
                            return Unit.INSTANCE;
                        }
                    }
                    this.this$0.z().tvEndScore.setText("--");
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public i() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Integer num) {
            if (VitalEnergyMeterFragment.this.isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(VitalEnergyMeterFragment.this), Dispatchers.getMain(), null, new a(num, VitalEnergyMeterFragment.this, null), 2, null);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class j extends Lambda implements Function1<Integer, Unit> {
        public j() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Integer num) {
            if (num == null || num.intValue() <= 0) {
                VitalEnergyMeterFragment.this.z().tvStartScore.setText("--");
            } else {
                VitalEnergyMeterFragment.this.z().tvStartScore.setText(String.valueOf(num));
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class k extends Lambda implements Function1<EnergyMeterInsightModel, Unit> {
        public k() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(EnergyMeterInsightModel energyMeterInsightModel) {
            invoke2(energyMeterInsightModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(EnergyMeterInsightModel it) {
            VitalEnergyMeterFragment vitalEnergyMeterFragment = VitalEnergyMeterFragment.this;
            FragmentVitalEnergyMeterBinding z = vitalEnergyMeterFragment.z();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            vitalEnergyMeterFragment.d0(z, it);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$updateEnergyMeterLineChart$1", f = "VitalEnergyMeterFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class l extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<Entry> $entries;
        public final /* synthetic */ ArrayList<String> $labels;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public l(ArrayList<Entry> arrayList, ArrayList<String> arrayList2, Continuation<? super l> continuation) {
            super(2, continuation);
            this.$entries = arrayList;
            this.$labels = arrayList2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new l(this.$entries, this.$labels, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((l) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentVitalEnergyMeterBinding z = VitalEnergyMeterFragment.this.z();
                if (z != null) {
                    ArrayList<Entry> arrayList = this.$entries;
                    ArrayList<String> arrayList2 = this.$labels;
                    VitalEnergyMeterFragment vitalEnergyMeterFragment = VitalEnergyMeterFragment.this;
                    if (!(arrayList == null || arrayList.isEmpty())) {
                        LineChart lineChart = z.lineChartEnergyMeter;
                        if (lineChart != null) {
                            lineChart.setVisibility(0);
                        }
                        TextView textView = z.tvNoDataFound;
                        if (textView != null) {
                            textView.setVisibility(8);
                        }
                        if (arrayList2 != null) {
                            vitalEnergyMeterFragment.b0(arrayList, arrayList2);
                        }
                    } else {
                        LineChart lineChart2 = z.lineChartEnergyMeter;
                        if (lineChart2 != null) {
                            lineChart2.setVisibility(8);
                        }
                        TextView textView2 = z.tvNoDataFound;
                        if (textView2 != null) {
                            textView2.setVisibility(0);
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public VitalEnergyMeterFragment() {
        AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.o = calendar;
        this.p = true;
        this.q = true;
        this.r = new int[]{R.drawable.stress_tip_1, R.drawable.stress_tip_2, R.drawable.stress_tip_3, R.drawable.stress_tip_4};
        this.s = new int[]{R.string.energy_meter_tip_1, R.string.energy_meter_tip_3, R.string.energy_meter_tip_2, R.string.energy_meter_tip_4};
        this.t = new ArrayList<>();
        this.u = new int[]{Color.parseColor("#ffcb3f"), Color.parseColor("#e51c23"), Color.parseColor("#ffa26e"), Color.parseColor("#3e93ff"), Color.parseColor("#7c42ff"), Color.parseColor("#68bb49")};
        this.v = new int[]{Color.parseColor("#ffcb3f"), Color.parseColor("#e51c23"), Color.parseColor("#ffa26e"), Color.parseColor("#3e93ff"), Color.parseColor("#7c42ff"), Color.parseColor("#68bb49")};
        this.w = new int[]{Color.parseColor("#ffcb3f"), Color.parseColor("#e51c23"), Color.parseColor("#ffa26e"), Color.parseColor("#3e93ff"), Color.parseColor("#7c42ff"), Color.parseColor("#68bb49")};
        this.x = new int[]{Color.parseColor("#ffcb3f"), Color.parseColor("#e51c23"), Color.parseColor("#ffa26e"), Color.parseColor("#3e93ff"), Color.parseColor("#7c42ff"), Color.parseColor("#68bb49")};
    }

    public static final void G(VitalEnergyMeterFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void H(VitalEnergyMeterFragment this$0, FragmentVitalEnergyMeterBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        if (this$0.p) {
            if (!this$0.q) {
                this$0.q = true;
                this_apply.tvGainTab.setBackground(this$0.getResources().getDrawable(R.drawable.tab_selected_bg_drain_gain));
            }
            this$0.p = false;
            this_apply.tvDrainTab.setBackground(this$0.getResources().getDrawable(R.drawable.tab_unselected_bg_drain_gain));
        } else {
            this$0.p = true;
            this_apply.tvDrainTab.setBackground(this$0.getResources().getDrawable(R.drawable.tab_selected_bg_drain_gain));
        }
        this$0.c0();
    }

    public static final void I(VitalEnergyMeterFragment this$0, FragmentVitalEnergyMeterBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        if (this$0.q) {
            if (!this$0.p) {
                this$0.p = true;
                this_apply.tvDrainTab.setBackground(this$0.getResources().getDrawable(R.drawable.tab_selected_bg_drain_gain));
            }
            this$0.q = false;
            this_apply.tvGainTab.setBackground(this$0.getResources().getDrawable(R.drawable.tab_unselected_bg_drain_gain));
        } else {
            this$0.q = true;
            this_apply.tvGainTab.setBackground(this$0.getResources().getDrawable(R.drawable.tab_selected_bg_drain_gain));
        }
        this$0.c0();
    }

    public static final void J(final FragmentVitalEnergyMeterBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollEnergyMeter.post(new Runnable() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.o
            @Override // java.lang.Runnable
            public final void run() {
                VitalEnergyMeterFragment.K(FragmentVitalEnergyMeterBinding.this);
            }
        });
    }

    public static final void K(FragmentVitalEnergyMeterBinding this_apply) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollEnergyMeter.fullScroll(130);
    }

    public static final void L(VitalEnergyMeterFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.X();
        this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
        this$0.getDatePickerDialog().show();
    }

    public static final void M(VitalEnergyMeterFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.D(Utils.INSTANCE.getPreviousDayCalendar(this$0.o));
    }

    public static final void N(VitalEnergyMeterFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (DateUtils.isToday(this$0.o.getTimeInMillis())) {
            return;
        }
        this$0.D(Utils.INSTANCE.getNextDayCalendar(this$0.o));
    }

    public static final void S(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void T(Function1 tmp0, Object obj) {
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

    public static final void W(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void Y(VitalEnergyMeterFragment this$0, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i2, i3, i4);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.D(newDate);
    }

    public static final void a0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final ArrayList<PieEntry> A(ArrayList<PieEntry> arrayList) {
        ArrayList<PieEntry> arrayList2 = new ArrayList<>();
        Iterator<PieEntry> it = arrayList.iterator();
        while (it.hasNext()) {
            PieEntry next = it.next();
            if (this.q && Intrinsics.areEqual(next.getLabel(), getString(R.string.sleep_score))) {
                arrayList2.add(next.copy());
            }
            if (this.p && !Intrinsics.areEqual(next.getLabel(), getString(R.string.sleep_score))) {
                arrayList2.add(next.copy());
            }
        }
        return arrayList2;
    }

    public final EnergyMeterPieChartBean B(List<EnergyMeterPieChartBean> list) {
        for (EnergyMeterPieChartBean energyMeterPieChartBean : list) {
            if (Intrinsics.areEqual(energyMeterPieChartBean.getName(), getString(R.string.sleep_score))) {
                return energyMeterPieChartBean;
            }
        }
        return null;
    }

    public final List<TipsModel> C() {
        ArrayList arrayList = new ArrayList();
        int length = this.s.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = this.r[i2];
            String string = getString(this.s[i2]);
            Intrinsics.checkNotNullExpressionValue(string, "getString(description[i])");
            arrayList.add(new TipsModel(i3, string));
        }
        return arrayList;
    }

    public final void D(Calendar calendar) {
        if (isAdded()) {
            this.o = calendar;
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                z().clMinMax.setVisibility(8);
            } else {
                z().clMinMax.setVisibility(0);
            }
            TextView textView = z().vitalsMainData.tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(Utils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                z().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                z().vitalsMainData.ibForward.setEnabled(false);
            } else {
                z().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                z().vitalsMainData.ibForward.setEnabled(true);
            }
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
        }
    }

    public final void E() {
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat);
    }

    public final void F() {
        final FragmentVitalEnergyMeterBinding z = z();
        z.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalEnergyMeterFragment.L(VitalEnergyMeterFragment.this, view);
            }
        });
        z.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalEnergyMeterFragment.M(VitalEnergyMeterFragment.this, view);
            }
        });
        z.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalEnergyMeterFragment.N(VitalEnergyMeterFragment.this, view);
            }
        });
        z.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalEnergyMeterFragment.G(VitalEnergyMeterFragment.this, view);
            }
        });
        z.tvDrainTab.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalEnergyMeterFragment.H(VitalEnergyMeterFragment.this, z, view);
            }
        });
        z.tvGainTab.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalEnergyMeterFragment.I(VitalEnergyMeterFragment.this, z, view);
            }
        });
        z.vitalsMainData.tvVitalName.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalEnergyMeterFragment.J(FragmentVitalEnergyMeterBinding.this, view);
            }
        });
    }

    public final void O() {
    }

    public final void P() {
        FragmentVitalEnergyMeterBinding z = z();
        ConstraintLayout constraintLayout = z.vitalsMainData.clTopSelector;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clTopSelector");
        gone(constraintLayout);
        ConstraintLayout constraintLayout2 = z.vitalsMainData.clMinData;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "vitalsMainData.clMinData");
        inVisible(constraintLayout2);
        ConstraintLayout constraintLayout3 = z.vitalsMainData.clMaxData;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "vitalsMainData.clMaxData");
        inVisible(constraintLayout3);
        ImageView imageView = z.vitalsMainData.ivMin;
        Intrinsics.checkNotNullExpressionValue(imageView, "vitalsMainData.ivMin");
        inVisible(imageView);
        ImageView imageView2 = z.vitalsMainData.ivMax;
        Intrinsics.checkNotNullExpressionValue(imageView2, "vitalsMainData.ivMax");
        inVisible(imageView2);
        ImageView imageView3 = z.vitalsMainData.ivMinBg;
        Intrinsics.checkNotNullExpressionValue(imageView3, "vitalsMainData.ivMinBg");
        inVisible(imageView3);
        ImageView imageView4 = z.vitalsMainData.ivMaxBg;
        Intrinsics.checkNotNullExpressionValue(imageView4, "vitalsMainData.ivMaxBg");
        inVisible(imageView4);
        ConstraintLayout constraintLayout4 = z.vitalsMainData.clCenterDataEnergyAndStress;
        Intrinsics.checkNotNullExpressionValue(constraintLayout4, "vitalsMainData.clCenterDataEnergyAndStress");
        gone(constraintLayout4);
        ConstraintLayout constraintLayout5 = z.vitalsMainData.clCenterDataHRVSpo2;
        Intrinsics.checkNotNullExpressionValue(constraintLayout5, "vitalsMainData.clCenterDataHRVSpo2");
        gone(constraintLayout5);
        ConstraintLayout constraintLayout6 = z.vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout6, "vitalsMainData.clCenterDataStepsSleep");
        visible(constraintLayout6);
        z.vitalsMainData.ivCenterVital.setImageResource(R.drawable.ic_energy_level);
        z.vitalsMainData.tvVitalName.setText(getString(R.string.energy_meter));
        TextView textView = z.vitalsMainData.tvOutOfStepsSleepValue;
        Intrinsics.checkNotNullExpressionValue(textView, "vitalsMainData.tvOutOfStepsSleepValue");
        inVisible(textView);
        z.vitalsMainData.tvAvgType.setText("Avg.Energy Meter");
        z.vitalsMainData.tvOutOfStepsSleepValue.setText("Out of 100");
        TextView textView2 = z.vitalsMainData.tvAvgType;
        Intrinsics.checkNotNullExpressionValue(textView2, "vitalsMainData.tvAvgType");
        inVisible(textView2);
        z.emTipsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        TipsAdapter tipsAdapter = new TipsAdapter();
        tipsAdapter.setTipsList(C());
        z.emTipsRecycler.setAdapter(tipsAdapter);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        TextView tvEmDescription = z.tvEmDescription;
        Intrinsics.checkNotNullExpressionValue(tvEmDescription, "tvEmDescription");
        themesUtils.makeTextViewResizable(tvEmDescription, 3, "... Read More", true);
        O();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        D(calendar);
    }

    public final void Q() {
        FragmentActivity requireActivity = requireActivity();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new ViewModelFactory(requireContext)).get(FragmentEnergyMeterViewModelNew.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(requireActivity(), Vi…:class.java\n            )");
        FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew = (FragmentEnergyMeterViewModelNew) viewModel;
        this.n = fragmentEnergyMeterViewModelNew;
        FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew2 = null;
        if (fragmentEnergyMeterViewModelNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModelNew = null;
        }
        fragmentEnergyMeterViewModelNew.setQuestionsList$sleepenergyscore_prodRelease(this);
        FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew3 = this.n;
        if (fragmentEnergyMeterViewModelNew3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentEnergyMeterViewModelNew2 = fragmentEnergyMeterViewModelNew3;
        }
        fragmentEnergyMeterViewModelNew2.setMLifecycleOwner(this);
    }

    public final void R() {
        FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew = this.n;
        FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew2 = null;
        if (fragmentEnergyMeterViewModelNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModelNew = null;
        }
        MutableLiveData<Integer> energyScoreLiveData = fragmentEnergyMeterViewModelNew.getEnergyScoreLiveData();
        if (energyScoreLiveData != null) {
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            final f fVar = new f();
            energyScoreLiveData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.x
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    VitalEnergyMeterFragment.S(Function1.this, obj);
                }
            });
        }
        FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew3 = this.n;
        if (fragmentEnergyMeterViewModelNew3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModelNew3 = null;
        }
        MutableLiveData<EnergyMeterLineChartModel> energyMeterLineChartLiveData = fragmentEnergyMeterViewModelNew3.getEnergyMeterLineChartLiveData();
        if (energyMeterLineChartLiveData != null) {
            LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
            final g gVar = new g();
            energyMeterLineChartLiveData.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.k
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    VitalEnergyMeterFragment.T(Function1.this, obj);
                }
            });
        }
        FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew4 = this.n;
        if (fragmentEnergyMeterViewModelNew4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModelNew4 = null;
        }
        MutableLiveData<EnergyMeterPieChartModel> energyMeterPieChartLiveData = fragmentEnergyMeterViewModelNew4.getEnergyMeterPieChartLiveData();
        if (energyMeterPieChartLiveData != null) {
            LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
            final h hVar = new h();
            energyMeterPieChartLiveData.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.w
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    VitalEnergyMeterFragment.U(Function1.this, obj);
                }
            });
        }
        FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew5 = this.n;
        if (fragmentEnergyMeterViewModelNew5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModelNew5 = null;
        }
        MutableLiveData<Integer> energyScoreLiveData2 = fragmentEnergyMeterViewModelNew5.getEnergyScoreLiveData();
        if (energyScoreLiveData2 != null) {
            LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
            final i iVar = new i();
            energyScoreLiveData2.observe(viewLifecycleOwner4, new Observer() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.l
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    VitalEnergyMeterFragment.V(Function1.this, obj);
                }
            });
        }
        FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew6 = this.n;
        if (fragmentEnergyMeterViewModelNew6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentEnergyMeterViewModelNew2 = fragmentEnergyMeterViewModelNew6;
        }
        MutableLiveData<Integer> lastScoreLiveData = fragmentEnergyMeterViewModelNew2.getLastScoreLiveData();
        if (lastScoreLiveData != null) {
            LifecycleOwner viewLifecycleOwner5 = getViewLifecycleOwner();
            final j jVar = new j();
            lastScoreLiveData.observe(viewLifecycleOwner5, new Observer() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.n
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    VitalEnergyMeterFragment.W(Function1.this, obj);
                }
            });
        }
    }

    public final void X() {
        Calendar calendar = this.o;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.j
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                VitalEnergyMeterFragment.Y(VitalEnergyMeterFragment.this, datePicker, i2, i3, i4);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void Z() {
        FragmentEnergyMeterViewModelNew fragmentEnergyMeterViewModelNew = this.n;
        if (fragmentEnergyMeterViewModelNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentEnergyMeterViewModelNew = null;
        }
        MutableLiveData<EnergyMeterInsightModel> energyInsightData = fragmentEnergyMeterViewModelNew.getEnergyInsightData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final k kVar = new k();
        energyInsightData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.m
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalEnergyMeterFragment.a0(Function1.this, obj);
            }
        });
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

    public final void b0(ArrayList<Entry> arrayList, final ArrayList<String> arrayList2) {
        FragmentVitalEnergyMeterBinding z = z();
        if (z != null) {
            LineChart lineChart = z.lineChartEnergyMeter;
            if (lineChart != null) {
                lineChart.clear();
            }
            ArrayList arrayList3 = new ArrayList();
            Intrinsics.checkNotNull(arrayList);
            Iterator<Entry> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Entry next = it.next();
                if (next.getY() == -1.0f) {
                    arrayList3.add(next);
                }
            }
            arrayList.removeAll(arrayList3);
            LineDataSet lineDataSet = new LineDataSet(arrayList, "");
            lineDataSet.setCircleColor(getResources().getColor(R.color.white));
            lineDataSet.setCircleSize(0.5f);
            lineDataSet.setDrawValues(false);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setDrawFilled(true);
            Resources resources = getResources();
            int i2 = R.color.secondary_text_color;
            lineDataSet.setValueTextColor(resources.getColor(i2));
            lineDataSet.setColor(getResources().getColor(R.color.steps_graph_color));
            lineDataSet.setHighLightColor(getResources().getColor(17170445));
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(getResources().getDrawable(R.drawable.line_chart_background_gradiant_bg));
            LineDataSet lineDataSet2 = new LineDataSet(arrayList3, "");
            lineDataSet2.setCircleColor(getResources().getColor(R.color.transparent));
            lineDataSet2.setDrawCircleHole(false);
            lineDataSet2.setDrawValues(false);
            lineDataSet2.setValueTextColor(getResources().getColor(17170445));
            lineDataSet2.setColor(getResources().getColor(17170445));
            lineDataSet2.setHighLightColor(getResources().getColor(17170445));
            LineData lineData = new LineData(lineDataSet, lineDataSet2);
            lineData.setDrawValues(false);
            lineData.setValueTextColor(getResources().getColor(i2));
            z.lineChartEnergyMeter.getXAxis().setDrawGridLines(false);
            LineChart lineChart2 = z.lineChartEnergyMeter;
            YAxis axisLeft = lineChart2 != null ? lineChart2.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft);
            axisLeft.setCenterAxisLabels(true);
            axisLeft.setDrawAxisLine(true);
            axisLeft.setDrawGridLines(false);
            axisLeft.setAxisMinimum(0.0f);
            axisLeft.setEnabled(true);
            axisLeft.setAxisMaximum(100.0f);
            Resources resources2 = getResources();
            int i3 = R.color.color_757575;
            axisLeft.setAxisLineColor(resources2.getColor(i3));
            LineChart lineChart3 = z.lineChartEnergyMeter;
            YAxis axisRight = lineChart3 != null ? lineChart3.getAxisRight() : null;
            Intrinsics.checkNotNull(axisRight);
            axisRight.setEnabled(false);
            LineChart lineChart4 = z.lineChartEnergyMeter;
            XAxis xAxis = lineChart4 != null ? lineChart4.getXAxis() : null;
            Intrinsics.checkNotNull(xAxis);
            xAxis.setEnabled(true);
            xAxis.setGranularity(1.0f);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGridColor(getResources().getColor(i3));
            xAxis.setDrawLabels(true);
            xAxis.setAxisLineColor(getResources().getColor(i3));
            xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment$setLineGrapValues$1$1
                @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
                @NotNull
                public String getFormattedValue(float f2, @Nullable AxisBase axisBase) {
                    int roundToInt;
                    if (f2 >= 0.0f && (roundToInt = kotlin.math.c.roundToInt(f2)) >= 0 && roundToInt < arrayList2.size() && roundToInt == ((int) f2)) {
                        String str = arrayList2.get(roundToInt);
                        Intrinsics.checkNotNullExpressionValue(str, "labels[index]");
                        return str;
                    }
                    return "";
                }
            });
            LineChart lineChart5 = z.lineChartEnergyMeter;
            Legend legend = lineChart5 != null ? lineChart5.getLegend() : null;
            Intrinsics.checkNotNull(legend);
            legend.setEnabled(false);
            z.lineChartEnergyMeter.setData(lineData);
            z.lineChartEnergyMeter.setDrawGridBackground(false);
            z.lineChartEnergyMeter.getDescription().setEnabled(false);
            z.lineChartEnergyMeter.setDrawBorders(false);
            z.lineChartEnergyMeter.setAutoScaleMinMaxEnabled(true);
            z.lineChartEnergyMeter.setPinchZoom(false);
            z.lineChartEnergyMeter.setVisibleXRangeMinimum(5.0f);
            Context context = getContext();
            int i4 = R.layout.custom_marker_view_steps_hr;
            String string = getString(R.string.energy_meter);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.energy_meter)");
            CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, i4, string, 0, arrayList2);
            customMarkerViewVitals.setChartView(z.lineChartEnergyMeter);
            z.lineChartEnergyMeter.setMarker(customMarkerViewVitals);
            LineChart lineChart6 = z.lineChartEnergyMeter;
            YAxis axisLeft2 = lineChart6 != null ? lineChart6.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft2);
            axisLeft2.setStartAtZero(true);
            LineChart lineChart7 = z.lineChartEnergyMeter;
            YAxis axisRight2 = lineChart7 != null ? lineChart7.getAxisRight() : null;
            Intrinsics.checkNotNull(axisRight2);
            axisRight2.setStartAtZero(true);
            LineChart lineChart8 = z.lineChartEnergyMeter;
            YAxis axisLeft3 = lineChart8 != null ? lineChart8.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft3);
            axisLeft3.setTextColor(getResources().getColor(i2));
            LineChart lineChart9 = z.lineChartEnergyMeter;
            XAxis xAxis2 = lineChart9 != null ? lineChart9.getXAxis() : null;
            Intrinsics.checkNotNull(xAxis2);
            xAxis2.setTextColor(getResources().getColor(i2));
            z.lineChartEnergyMeter.setDoubleTapToZoomEnabled(false);
            z.lineChartEnergyMeter.invalidate();
        }
    }

    public final void c0() {
        PieChart pieChart;
        FragmentVitalEnergyMeterBinding z = z();
        if (z == null || (pieChart = z.pieChart) == null) {
            return;
        }
        pieChart.clear();
        PieDataSet pieDataSet = new PieDataSet(A(this.t), "");
        if (pieDataSet.getEntryCount() > 0) {
            PieChart pieChart2 = z.pieChart;
            Intrinsics.checkNotNullExpressionValue(pieChart2, "pieChart");
            visible(pieChart2);
            RecyclerView gridView = z.gridView;
            Intrinsics.checkNotNullExpressionValue(gridView, "gridView");
            visible(gridView);
            TextView pieChartNoDataTv = z.pieChartNoDataTv;
            Intrinsics.checkNotNullExpressionValue(pieChartNoDataTv, "pieChartNoDataTv");
            gone(pieChartNoDataTv);
            pieDataSet.setDrawIcons(false);
            pieDataSet.setSliceSpace(3.0f);
            pieDataSet.setIconsOffset(new MPPointF(0.0f, 40.0f));
            pieDataSet.setSelectionShift(10.0f);
            ArrayList arrayList = new ArrayList();
            int[] VORDIPLOM_COLORS = ColorTemplate.VORDIPLOM_COLORS;
            Intrinsics.checkNotNullExpressionValue(VORDIPLOM_COLORS, "VORDIPLOM_COLORS");
            for (int i2 : VORDIPLOM_COLORS) {
                arrayList.add(Integer.valueOf(i2));
            }
            int[] JOYFUL_COLORS = ColorTemplate.JOYFUL_COLORS;
            Intrinsics.checkNotNullExpressionValue(JOYFUL_COLORS, "JOYFUL_COLORS");
            for (int i3 : JOYFUL_COLORS) {
                arrayList.add(Integer.valueOf(i3));
            }
            int[] COLORFUL_COLORS = ColorTemplate.COLORFUL_COLORS;
            Intrinsics.checkNotNullExpressionValue(COLORFUL_COLORS, "COLORFUL_COLORS");
            for (int i4 : COLORFUL_COLORS) {
                arrayList.add(Integer.valueOf(i4));
            }
            int[] LIBERTY_COLORS = ColorTemplate.LIBERTY_COLORS;
            Intrinsics.checkNotNullExpressionValue(LIBERTY_COLORS, "LIBERTY_COLORS");
            for (int i5 : LIBERTY_COLORS) {
                arrayList.add(Integer.valueOf(i5));
            }
            int[] PASTEL_COLORS = ColorTemplate.PASTEL_COLORS;
            Intrinsics.checkNotNullExpressionValue(PASTEL_COLORS, "PASTEL_COLORS");
            for (int i6 : PASTEL_COLORS) {
                arrayList.add(Integer.valueOf(i6));
            }
            arrayList.add(Integer.valueOf(ColorTemplate.getHoloBlue()));
            pieDataSet.setColors(arrayList);
            pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
            pieDataSet.setValueLinePart1OffsetPercentage(10.0f);
            pieDataSet.setValueLinePart1Length(0.3f);
            pieDataSet.setValueLinePart2Length(0.1f);
            Resources resources = getResources();
            int i7 = R.color.main_text_color;
            pieDataSet.setValueLineColor(resources.getColor(i7));
            pieDataSet.setValueTextColor(getResources().getColor(i7));
            PieData pieData = new PieData(pieDataSet);
            pieData.setDrawValues(false);
            pieData.setValueFormatter(new PercentFormatter(new DecimalFormat("###,###,##")));
            pieData.setValueTextSize(10.0f);
            z.pieChart.setData(pieData);
            z.pieChart.setRotationEnabled(false);
            z.pieChart.setHighlightPerTapEnabled(true);
            z.pieChart.setTransparentCircleColor(0);
            z.pieChart.setHoleRadius(70.0f);
            z.pieChart.setHoleColor(0);
            z.pieChart.setTransparentCircleRadius(0.0f);
            z.pieChart.getLegend().setWordWrapEnabled(true);
            z.pieChart.setDrawEntryLabels(false);
            z.pieChart.setDrawHoleEnabled(true);
            z.pieChart.setUsePercentValues(false);
            z.pieChart.setEntryLabelTextSize(5.0f);
            z.pieChart.setEntryLabelColor(getResources().getColor(i7));
            z.pieChart.setCenterTextSize(24.0f);
            CustomMarkerViewPieChart customMarkerViewPieChart = new CustomMarkerViewPieChart(getContext(), R.layout.custom_marker_view_pie_chart);
            customMarkerViewPieChart.setChartView(z.pieChart);
            z.pieChart.setMarker(customMarkerViewPieChart);
            z.pieChart.getDescription().setEnabled(false);
            setCustomLegend();
            z.pieChart.invalidate();
            return;
        }
        PieChart pieChart3 = z.pieChart;
        Intrinsics.checkNotNullExpressionValue(pieChart3, "pieChart");
        gone(pieChart3);
        RecyclerView gridView2 = z.gridView;
        Intrinsics.checkNotNullExpressionValue(gridView2, "gridView");
        gone(gridView2);
        TextView pieChartNoDataTv2 = z.pieChartNoDataTv;
        Intrinsics.checkNotNullExpressionValue(pieChartNoDataTv2, "pieChartNoDataTv");
        visible(pieChartNoDataTv2);
    }

    public final void d0(FragmentVitalEnergyMeterBinding fragmentVitalEnergyMeterBinding, EnergyMeterInsightModel energyMeterInsightModel) {
        String string;
        if (energyMeterInsightModel.getEnergyDifference() > 0) {
            fragmentVitalEnergyMeterBinding.tvEnergyPercentage.setTextAppearance(R.style.bold);
            fragmentVitalEnergyMeterBinding.tvEnergyPercentage.setTextSize(2, 24.0f);
            fragmentVitalEnergyMeterBinding.tvEnergyPercentage.setText(String.valueOf(energyMeterInsightModel.getEnergyDifference()));
            TextView tvEnergyIncreasedDecreased = fragmentVitalEnergyMeterBinding.tvEnergyIncreasedDecreased;
            Intrinsics.checkNotNullExpressionValue(tvEnergyIncreasedDecreased, "tvEnergyIncreasedDecreased");
            visible(tvEnergyIncreasedDecreased);
            ImageView ivEnergyIncreaseDecrease = fragmentVitalEnergyMeterBinding.ivEnergyIncreaseDecrease;
            Intrinsics.checkNotNullExpressionValue(ivEnergyIncreaseDecrease, "ivEnergyIncreaseDecrease");
            visible(ivEnergyIncreaseDecrease);
            TextView textView = fragmentVitalEnergyMeterBinding.tvEnergyIncreasedDecreased;
            if (energyMeterInsightModel.isEnergyIncreased()) {
                string = getString(R.string.increased);
            } else {
                string = getString(R.string.decreased);
            }
            textView.setText(string);
            if (energyMeterInsightModel.isEnergyIncreased()) {
                fragmentVitalEnergyMeterBinding.ivEnergyIncreaseDecrease.setBackgroundResource(R.drawable.ic_small_green_arrow_up);
                return;
            } else {
                fragmentVitalEnergyMeterBinding.ivEnergyIncreaseDecrease.setBackgroundResource(R.drawable.ic_small_red_arrow_down);
                return;
            }
        }
        fragmentVitalEnergyMeterBinding.tvEnergyPercentage.setTextAppearance(R.style.semi_bold);
        fragmentVitalEnergyMeterBinding.tvEnergyPercentage.setTextSize(2, 16.0f);
        fragmentVitalEnergyMeterBinding.tvEnergyPercentage.setText(getString(R.string.no_change));
        TextView tvEnergyIncreasedDecreased2 = fragmentVitalEnergyMeterBinding.tvEnergyIncreasedDecreased;
        Intrinsics.checkNotNullExpressionValue(tvEnergyIncreasedDecreased2, "tvEnergyIncreasedDecreased");
        gone(tvEnergyIncreasedDecreased2);
        ImageView ivEnergyIncreaseDecrease2 = fragmentVitalEnergyMeterBinding.ivEnergyIncreaseDecrease;
        Intrinsics.checkNotNullExpressionValue(ivEnergyIncreaseDecrease2, "ivEnergyIncreaseDecrease");
        gone(ivEnergyIncreaseDecrease2);
    }

    public final void e0(EnergyMeterPieChartModel energyMeterPieChartModel) {
        int contribution;
        int contribution2;
        this.t.clear();
        List<EnergyMeterPieChartBean> pieChartModels = energyMeterPieChartModel.getPieChartModels();
        if (!(pieChartModels == null || pieChartModels.isEmpty())) {
            z().pieChart.setVisibility(0);
            z().gridView.setVisibility(0);
            List<EnergyMeterPieChartBean> pieChartModels2 = energyMeterPieChartModel.getPieChartModels();
            Intrinsics.checkNotNull(pieChartModels2);
            EnergyMeterPieChartBean B = B(pieChartModels2);
            List<EnergyMeterPieChartBean> pieChartModels3 = energyMeterPieChartModel.getPieChartModels();
            Intrinsics.checkNotNull(pieChartModels3);
            for (EnergyMeterPieChartBean energyMeterPieChartBean : pieChartModels3) {
                if (energyMeterPieChartBean.getContribution() > 0) {
                    if (Intrinsics.areEqual(energyMeterPieChartBean.getName(), getString(R.string.sleep_score))) {
                        List<EnergyMeterPieChartBean> pieChartModels4 = energyMeterPieChartModel.getPieChartModels();
                        Intrinsics.checkNotNull(pieChartModels4);
                        if (pieChartModels4.size() > 1) {
                            contribution = (energyMeterPieChartBean.getContribution() * 100) / 200;
                        } else {
                            contribution = energyMeterPieChartBean.getContribution();
                        }
                        this.t.add(new PieEntry(contribution, energyMeterPieChartBean.getName(), energyMeterPieChartBean.getValue()));
                    } else {
                        if (B != null && B.getContribution() > 0) {
                            contribution2 = (energyMeterPieChartBean.getContribution() * 100) / 200;
                        } else {
                            contribution2 = energyMeterPieChartBean.getContribution();
                        }
                        this.t.add(new PieEntry(contribution2, energyMeterPieChartBean.getName(), energyMeterPieChartBean.getValue()));
                    }
                }
            }
            c0();
            piechartColor();
            return;
        }
        z().pieChart.setVisibility(8);
        z().gridView.setVisibility(8);
    }

    @NotNull
    public final int[] getColor() {
        return this.u;
    }

    @NotNull
    public final int[] getColor1() {
        return this.v;
    }

    @NotNull
    public final int[] getColor2() {
        return this.w;
    }

    @NotNull
    public final int[] getColorDefault() {
        return this.x;
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
    public final ArrayList<PieEntry> getPieEntries() {
        return this.t;
    }

    @NotNull
    public final SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = this.simpleDateFormat;
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("simpleDateFormat");
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentVitalEnergyMeterBinding.inflate(inflater, viewGroup, false);
        View root = z().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe
    public final void onEnergyScoreCalculated(@NotNull EnergyScoreEventData energyScoreEventData) {
        Intrinsics.checkNotNullParameter(energyScoreEventData, "energyScoreEventData");
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new b(null), 2, null);
        }
    }

    @Subscribe
    public final void onEnergyScoreFeedbackUiUpdate(@NotNull EnergyScoreFeedbackEventData energyScoreFeedbackEventData) {
        Intrinsics.checkNotNullParameter(energyScoreFeedbackEventData, "energyScoreFeedbackEventData");
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new d(null), 2, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Override // com.coveiot.android.sleepenergyscore.feedback.ContractFeedBackQuestionsList
    public void onReceiveQuestionList(@Nullable ArrayList<FeedbackQuetionnarieModel> arrayList) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new e(arrayList, null), 2, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        E();
        Q();
        P();
        R();
        F();
        Z();
    }

    public final void piechartColor() {
        int i2 = R.id.pieChart;
        if (((PieChart) _$_findCachedViewById(i2)) != null) {
            Legend legend = ((PieChart) _$_findCachedViewById(i2)).getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            legend.setDrawInside(false);
            legend.setEnabled(true);
            legend.setForm(Legend.LegendForm.NONE);
            legend.setTextColor(0);
            legend.setTextSize(0.0f);
            legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
            String[] labels = legend.getLabels();
            Intrinsics.checkNotNullExpressionValue(labels, "legend.labels");
            if (ArraysKt___ArraysKt.contains(labels, getResources().getString(R.string.sleep_score_gain))) {
                String[] labels2 = legend.getLabels();
                Intrinsics.checkNotNull(labels2);
                int length = labels2.length - 1;
                for (int i3 = 0; i3 < length; i3++) {
                    System.out.println((Object) ("labels index*** " + i3));
                    if (kotlin.text.m.equals(legend.getLabels()[i3], getResources().getString(R.string.sleep_score_gain), true)) {
                        String format = String.format("#%06X", Integer.valueOf(this.x[i3] & 16777215));
                        Intrinsics.checkNotNullExpressionValue(format, "format(\"#%06X\", 0xFFFFFF and color)");
                        String replace$default = kotlin.text.m.replace$default(format, MqttTopic.MULTI_LEVEL_WILDCARD, "", false, 4, (Object) null);
                        this.w[i3] = Color.parseColor("#99" + replace$default);
                    } else {
                        String format2 = String.format("#%06X", Integer.valueOf(this.x[i3] & 16777215));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(\"#%06X\", 0xFFFFFF and color)");
                        String replace$default2 = kotlin.text.m.replace$default(format2, MqttTopic.MULTI_LEVEL_WILDCARD, "", false, 4, (Object) null);
                        this.v[i3] = Color.parseColor("#99" + replace$default2);
                    }
                }
                return;
            }
            int[] iArr = this.x;
            int[] copyOf = Arrays.copyOf(iArr, iArr.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
            this.u = copyOf;
        }
    }

    public final void setColor(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.u = iArr;
    }

    public final void setColor1(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.v = iArr;
    }

    public final void setColor2(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.w = iArr;
    }

    public final void setColorDefault(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.x = iArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004e A[Catch: Exception -> 0x006b, TRY_LEAVE, TryCatch #0 {Exception -> 0x006b, blocks: (B:20:0x002e, B:22:0x003c, B:24:0x0042, B:30:0x004e), top: B:36:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setCustomLegend() {
        /*
            r14 = this;
            com.coveiot.android.sleepenergyscore.databinding.FragmentVitalEnergyMeterBinding r0 = r14.z()
            if (r0 == 0) goto L90
            com.github.mikephil.charting.charts.PieChart r1 = r0.pieChart
            if (r1 == 0) goto L90
            androidx.recyclerview.widget.RecyclerView r2 = r0.gridView
            if (r2 == 0) goto L90
            com.github.mikephil.charting.components.Legend r1 = r1.getLegend()
            java.lang.String[] r2 = r1.getLabels()
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L25
            int r2 = r2.length
            if (r2 != 0) goto L1f
            r2 = r4
            goto L20
        L1f:
            r2 = r3
        L20:
            if (r2 == 0) goto L23
            goto L25
        L23:
            r2 = r3
            goto L26
        L25:
            r2 = r4
        L26:
            if (r2 != 0) goto L90
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r5 = 3
            java.lang.String[] r6 = r1.getLabels()     // Catch: java.lang.Exception -> L6b
            java.lang.String r7 = "legend.labels"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch: java.lang.Exception -> L6b
            int r7 = r6.length     // Catch: java.lang.Exception -> L6b
            r8 = r3
            r9 = r8
        L3a:
            if (r8 >= r7) goto L6f
            r10 = r6[r8]     // Catch: java.lang.Exception -> L6b
            int r11 = r9 + 1
            if (r10 == 0) goto L4b
            int r12 = r10.length()     // Catch: java.lang.Exception -> L6b
            if (r12 != 0) goto L49
            goto L4b
        L49:
            r12 = r3
            goto L4c
        L4b:
            r12 = r4
        L4c:
            if (r12 != 0) goto L67
            com.coveiot.android.sleepenergyscore.energymeter.model.DrainGainGridItem r12 = new com.coveiot.android.sleepenergyscore.energymeter.model.DrainGainGridItem     // Catch: java.lang.Exception -> L6b
            r13 = 0
            r12.<init>(r13, r13, r5, r13)     // Catch: java.lang.Exception -> L6b
            r12.setName(r10)     // Catch: java.lang.Exception -> L6b
            int[] r10 = r1.getColors()     // Catch: java.lang.Exception -> L6b
            r9 = r10[r9]     // Catch: java.lang.Exception -> L6b
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Exception -> L6b
            r12.setColor(r9)     // Catch: java.lang.Exception -> L6b
            r2.add(r12)     // Catch: java.lang.Exception -> L6b
        L67:
            int r8 = r8 + 1
            r9 = r11
            goto L3a
        L6b:
            r1 = move-exception
            r1.printStackTrace()
        L6f:
            androidx.recyclerview.widget.RecyclerView r1 = r0.gridView
            androidx.recyclerview.widget.GridLayoutManager r3 = new androidx.recyclerview.widget.GridLayoutManager
            android.content.Context r4 = r14.requireContext()
            r3.<init>(r4, r5)
            r1.setLayoutManager(r3)
            androidx.recyclerview.widget.RecyclerView r0 = r0.gridView
            com.coveiot.android.sleepenergyscore.energymeter.adapters.EnergyScoreLegendGridAdapter1 r1 = new com.coveiot.android.sleepenergyscore.energymeter.adapters.EnergyScoreLegendGridAdapter1
            androidx.fragment.app.FragmentActivity r3 = r14.requireActivity()
            java.lang.String r4 = "requireActivity()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            r1.<init>(r3, r2)
            r0.setAdapter(r1)
        L90:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment.setCustomLegend():void");
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        ShareEnergyMeterData shareEnergyMeterData = new ShareEnergyMeterData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        shareEnergyMeterData.setStartEnergyScore(Integer.parseInt(z().vitalsMainData.tvAvgStepsSleepValue.getText().toString()));
        shareEnergyMeterData.setEndEnergyScore(0);
        shareEnergyMeterData.setGraphType(getResources().getString(R.string.share_daily));
        shareEnergyMeterData.setDwmValue(simpleDateFormat.format(this.o.getTime()));
        Intent intent = new Intent(getContext(), ActivityShare.class);
        intent.putExtra(Constants.SHARE_DATA.getValue(), shareEnergyMeterData);
        Context context = getContext();
        if (context != null) {
            context.startActivity(intent);
        }
    }

    public final void updateEnergyMeterLineChart(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new l(arrayList, arrayList2, null), 2, null);
        }
    }

    public final FragmentVitalEnergyMeterBinding z() {
        FragmentVitalEnergyMeterBinding fragmentVitalEnergyMeterBinding = this.m;
        Intrinsics.checkNotNull(fragmentVitalEnergyMeterBinding);
        return fragmentVitalEnergyMeterBinding;
    }
}
