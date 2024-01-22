package com.coveiot.android.activitymodes.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.ActivityModesNavigator;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.ActivityInfo;
import com.coveiot.android.activitymodes.activities.ActivityPlanDetails;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.eventmodels.ShowRunSession;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.utils.PlanStatus;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentCurrentPlanDashboard extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int m = 1;
    public ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentCurrentPlanDashboard newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard = new FragmentCurrentPlanDashboard();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            bundle.putString("param2", param2);
            fragmentCurrentPlanDashboard.setArguments(bundle);
            return fragmentCurrentPlanDashboard;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlanStatus.values().length];
            try {
                iArr[PlanStatus.UPCOMING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PlanStatus.ONGOING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PlanStatus.ENDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$onViewCreated$2$1", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {153}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

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
                ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = FragmentCurrentPlanDashboard.this.getViewModelCurrentPlanDashboard();
                this.label = 1;
                obj = viewModelCurrentPlanDashboard.getCurrentDayPlanData(false, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            EntityPreparationDay entityPreparationDay = (EntityPreparationDay) obj;
            if (entityPreparationDay == null || entityPreparationDay.getTarget() <= 0) {
                FragmentCurrentPlanDashboard.this.M();
            } else {
                CoveEventBusManager.getInstance().getEventBus().post(new ShowRunSession(entityPreparationDay));
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$setOngoingCardData$1", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.r1}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
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
            FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentCurrentPlanDashboard.this.getActivity() != null) {
                    FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard2 = FragmentCurrentPlanDashboard.this;
                    CardView cardView = (CardView) fragmentCurrentPlanDashboard2._$_findCachedViewById(R.id.cv_status_started_finished);
                    if (cardView != null) {
                        cardView.setVisibility(8);
                    }
                    ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = fragmentCurrentPlanDashboard2.getViewModelCurrentPlanDashboard();
                    this.L$0 = fragmentCurrentPlanDashboard2;
                    this.label = 1;
                    Object ongoingPlanData = viewModelCurrentPlanDashboard.getOngoingPlanData(false, this);
                    if (ongoingPlanData == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    fragmentCurrentPlanDashboard = fragmentCurrentPlanDashboard2;
                    obj = ongoingPlanData;
                }
                return Unit.INSTANCE;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                fragmentCurrentPlanDashboard = (FragmentCurrentPlanDashboard) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            Pair pair = (Pair) obj;
            EntityPreparationDay entityPreparationDay = (EntityPreparationDay) pair.component1();
            EntityPreparationWeek entityPreparationWeek = (EntityPreparationWeek) pair.component2();
            TextView textView = (TextView) fragmentCurrentPlanDashboard._$_findCachedViewById(R.id.tv_week_day);
            if (textView != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(entityPreparationWeek.getName());
                sb.append(' ');
                sb.append(fragmentCurrentPlanDashboard.getString(R.string.dash_day));
                sb.append(' ');
                sb.append(entityPreparationDay != null ? Boxing.boxInt(entityPreparationDay.getDay_number()) : null);
                textView.setText(sb.toString());
            }
            TextView textView2 = (TextView) fragmentCurrentPlanDashboard._$_findCachedViewById(R.id.tv_activities);
            if (textView2 != null) {
                ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard2 = fragmentCurrentPlanDashboard.getViewModelCurrentPlanDashboard();
                Intrinsics.checkNotNull(entityPreparationDay);
                textView2.setText(viewModelCurrentPlanDashboard2.getActivitiesString(entityPreparationDay));
            }
            TextView textView3 = (TextView) fragmentCurrentPlanDashboard._$_findCachedViewById(R.id.tv_date);
            if (textView3 != null) {
                textView3.setText(WorkoutUtils.INSTANCE.getFormattedDate(entityPreparationDay != null ? entityPreparationDay.getDate() : null, "yyyy-MM-dd", "dd MMM yy"));
            }
            CardView cardView2 = (CardView) fragmentCurrentPlanDashboard._$_findCachedViewById(R.id.cv_status_not_started);
            if (cardView2 != null) {
                cardView2.setVisibility(0);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$updatePlanProgressUi$1", f = "FragmentCurrentPlanDashboard.kt", i = {5}, l = {358, 366, 375, 378, 392, 395}, m = "invokeSuspend", n = {"totalActivitiesAndDistance"}, s = {"L$0"})
    /* loaded from: classes2.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isPlanEnded;
        public Object L$0;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$updatePlanProgressUi$1$1", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {367}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
            public int label;
            public final /* synthetic */ FragmentCurrentPlanDashboard this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentCurrentPlanDashboard;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super String> continuation) {
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
                    obj = viewModelCurrentPlanDashboard.getPlanDurationInDays(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return String.valueOf(((Number) obj).intValue());
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$updatePlanProgressUi$1$3", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {376}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            public int label;
            public final /* synthetic */ FragmentCurrentPlanDashboard this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = fragmentCurrentPlanDashboard;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
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
                    obj = viewModelCurrentPlanDashboard.getDayProgress(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return Boxing.boxInt((int) ((Number) obj).floatValue());
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$updatePlanProgressUi$1$4", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {379}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$c$c  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C0238c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
            public int label;
            public final /* synthetic */ FragmentCurrentPlanDashboard this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0238c(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, Continuation<? super C0238c> continuation) {
                super(2, continuation);
                this.this$0 = fragmentCurrentPlanDashboard;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0238c(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super String> continuation) {
                return ((C0238c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                    obj = viewModelCurrentPlanDashboard.getDayNumber(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return String.valueOf(((Number) obj).intValue());
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$updatePlanProgressUi$1$activityCompletedData$1", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {396}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Integer, ? extends Integer>>, Object> {
            public int label;
            public final /* synthetic */ FragmentCurrentPlanDashboard this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, Continuation<? super d> continuation) {
                super(2, continuation);
                this.this$0 = fragmentCurrentPlanDashboard;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new d(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Integer, ? extends Integer>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super Pair<Integer, Integer>>) continuation);
            }

            @Nullable
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Pair<Integer, Integer>> continuation) {
                return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                    obj = viewModelCurrentPlanDashboard.getActivitiesCompletedCount(this);
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

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$updatePlanProgressUi$1$currentPlan$1", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {359}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
            public int label;
            public final /* synthetic */ FragmentCurrentPlanDashboard this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public e(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, Continuation<? super e> continuation) {
                super(2, continuation);
                this.this$0 = fragmentCurrentPlanDashboard;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new e(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationPlan> continuation) {
                return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$updatePlanProgressUi$1$totalActivitiesAndDistance$1", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {393}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Integer, ? extends Integer>>, Object> {
            public int label;
            public final /* synthetic */ FragmentCurrentPlanDashboard this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public f(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, Continuation<? super f> continuation) {
                super(2, continuation);
                this.this$0 = fragmentCurrentPlanDashboard;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new f(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Integer, ? extends Integer>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super Pair<Integer, Integer>>) continuation);
            }

            @Nullable
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Pair<Integer, Integer>> continuation) {
                return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(boolean z, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$isPlanEnded = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, View view) {
            FragmentActivity activity = fragmentCurrentPlanDashboard.getActivity();
            Intrinsics.checkNotNull(activity);
            String string = activity.getString(R.string.plan_completed);
            Intrinsics.checkNotNullExpressionValue(string, "activity!!.getString(R.string.plan_completed)");
            FragmentActivity activity2 = fragmentCurrentPlanDashboard.getActivity();
            Intrinsics.checkNotNull(activity2);
            String string2 = activity2.getString(R.string.your_plan_has_ended);
            Intrinsics.checkNotNullExpressionValue(string2, "activity!!.getString(R.string.your_plan_has_ended)");
            fragmentCurrentPlanDashboard.G(string, string2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_MODES_DASHBOARD.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_CURRENT_PLAN_CARD.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.MODES_VIEW_CURRENT_PLAN_CARD.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            fragmentCurrentPlanDashboard.startActivityForResult(new Intent(fragmentCurrentPlanDashboard.getContext(), ActivityPlanDetails.class), fragmentCurrentPlanDashboard.getREQUEST_CODE_PLAN_DETAILS());
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$isPlanEnded, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0079  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00c1  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00cc  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0130  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0150 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0169 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x016a  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x017d  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x01ae  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
            /*
                Method dump skipped, instructions count: 636
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard.c.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final void A(FragmentCurrentPlanDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(null), 2, null);
    }

    public static final void B(FragmentCurrentPlanDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        if (!new PreferenceManager(activity).isModeFromApp()) {
            this$0.startActivity(new Intent(this$0.getContext(), ActivityInfo.class));
        } else {
            this$0.startActivityForResult(new Intent(this$0.getContext(), ActivityPlanDetails.class), this$0.m);
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_MODES_DASHBOARD.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_CURRENT_DAY_ACTIVITY.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.MODE_START_ACTIVITY_CARD.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public static final void C(FragmentCurrentPlanDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        if (!AppUtils.isNetConnected(context)) {
            this$0.showNoInternetMessage();
            return;
        }
        ActivityModesNavigator.Companion companion = ActivityModesNavigator.Companion;
        Context context2 = this$0.getContext();
        Intrinsics.checkNotNull(context2);
        String string = this$0.getString(R.string.my_plans);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.my_plans)");
        Context context3 = this$0.getContext();
        Intrinsics.checkNotNull(context3);
        String browsePlan = UserDataManager.getInstance(context3).getPlanConfigUrlsFromPref().getBrowsePlan();
        Intrinsics.checkNotNullExpressionValue(browsePlan, "getInstance(context!!).p…igUrlsFromPref.browsePlan");
        companion.navigateToWorkoutWebViewer(context2, string, browsePlan);
    }

    public static final void D(FragmentCurrentPlanDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_MODES_DASHBOARD.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_MY_PLAN_HISTORY.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.MODES_PLAN_HISTORY_CARD.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        if (!AppUtils.isNetConnected(context)) {
            this$0.showNoInternetMessage();
            return;
        }
        ActivityModesNavigator.Companion companion = ActivityModesNavigator.Companion;
        Context context2 = this$0.getContext();
        Intrinsics.checkNotNull(context2);
        String string = this$0.getString(R.string.my_plans);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.my_plans)");
        Context context3 = this$0.getContext();
        Intrinsics.checkNotNull(context3);
        String userPlanHistory = UserDataManager.getInstance(context3).getPlanConfigUrlsFromPref().getUserPlanHistory();
        Intrinsics.checkNotNullExpressionValue(userPlanHistory, "getInstance(context!!).p…sFromPref.userPlanHistory");
        companion.navigateToWorkoutWebViewer(context2, string, userPlanHistory);
    }

    public static final void E(FragmentCurrentPlanDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_MODES_DASHBOARD.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_REBUILD_NEW_PLAN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.MODES_REBUILD_NEW_PLAN_CARD.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        String url = UserDataManager.getInstance(context).getPlanConfigUrlsFromPref().getOnboarding();
        if (AppUtils.isValidUrl(url)) {
            Context context2 = this$0.getContext();
            Intrinsics.checkNotNull(context2);
            if (!AppUtils.isNetConnected(context2)) {
                this$0.showNoInternetMessage();
                return;
            }
            ActivityModesNavigator.Companion companion = ActivityModesNavigator.Companion;
            Context context3 = this$0.getContext();
            Intrinsics.checkNotNull(context3);
            String string = this$0.getString(R.string.build_plan);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.build_plan)");
            Intrinsics.checkNotNullExpressionValue(url, "url");
            companion.navigateToWorkoutWebViewer(context3, string, url);
        }
    }

    public static final void H(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void J(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void K(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void L(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, FragmentCurrentPlanDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogTwoButtons.dismiss();
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1(this$0, null), 2, null);
    }

    public static final void N(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static /* synthetic */ void P(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        fragmentCurrentPlanDashboard.O(z);
    }

    @JvmStatic
    @NotNull
    public static final FragmentCurrentPlanDashboard newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
    }

    public static final void x(final FragmentCurrentPlanDashboard this$0, final PlanStatus planStatus) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.fragments.w
            @Override // java.lang.Runnable
            public final void run() {
                FragmentCurrentPlanDashboard.y(PlanStatus.this, this$0);
            }
        });
    }

    public static final void y(PlanStatus planStatus, final FragmentCurrentPlanDashboard this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (planStatus != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[planStatus.ordinal()];
            if (i == 1) {
                ((TextView) this$0._$_findCachedViewById(R.id.tv_plan_status_title)).setText(this$0.getString(R.string.your_plan_starts_tomorrow));
                ((TextView) this$0._$_findCachedViewById(R.id.tv_plan_status_subtitle)).setText(this$0.getString(R.string.all_the_best));
                ((CardView) this$0._$_findCachedViewById(R.id.cv_status_started_finished)).setVisibility(0);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_status_not_started)).setVisibility(8);
                P(this$0, false, 1, null);
                ((ImageView) this$0._$_findCachedViewById(R.id.time_finished_flag)).setVisibility(8);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_view_more_plans)).setVisibility(0);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_my_history)).setVisibility(0);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_rebuild_plan)).setVisibility(0);
                ((Button) this$0._$_findCachedViewById(R.id.btn_build_newPlan)).setVisibility(8);
            } else if (i == 2) {
                this$0.F();
                P(this$0, false, 1, null);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_view_more_plans)).setVisibility(0);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_my_history)).setVisibility(0);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_rebuild_plan)).setVisibility(0);
                ((Button) this$0._$_findCachedViewById(R.id.btn_build_newPlan)).setVisibility(8);
            } else if (i != 3) {
            } else {
                ((TextView) this$0._$_findCachedViewById(R.id.tv_plan_status_title)).setText(this$0.getString(R.string.your_plan_finished));
                ((TextView) this$0._$_findCachedViewById(R.id.tv_plan_status_subtitle)).setText(this$0.getString(R.string.time_to_look_for_new_plans));
                ((CardView) this$0._$_findCachedViewById(R.id.cv_status_started_finished)).setVisibility(0);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_status_not_started)).setVisibility(8);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_view_more_plans)).setVisibility(8);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_my_history)).setVisibility(8);
                ((CardView) this$0._$_findCachedViewById(R.id.cv_rebuild_plan)).setVisibility(8);
                int i2 = R.id.btn_build_newPlan;
                ((Button) this$0._$_findCachedViewById(i2)).setVisibility(0);
                ((ImageView) this$0._$_findCachedViewById(R.id.time_finished_flag)).setVisibility(0);
                ((Button) this$0._$_findCachedViewById(i2)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.b0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentCurrentPlanDashboard.z(FragmentCurrentPlanDashboard.this, view);
                    }
                });
                this$0.O(true);
            }
        }
    }

    public static final void z(FragmentCurrentPlanDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    public final void F() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new b(null), 2, null);
    }

    public final void G(String str, String str2) {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activity, str, str2);
        bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(false);
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        String string = activity2.getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string, "activity!!.getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCurrentPlanDashboard.H(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void I() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        if (AppUtils.isNetConnected(context)) {
            Context context2 = getContext();
            Intrinsics.checkNotNull(context2);
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.do_you_want_continue);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.do_you_want_continue)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(context2, string, string2, false, 8, null);
            String string3 = getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.u
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentCurrentPlanDashboard.L(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            String string4 = getString(R.string.f2703no);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.f0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentCurrentPlanDashboard.J(BottomSheetDialogTwoButtons.this, view);
                }
            });
            if (bottomSheetDialogTwoButtons.isShowing()) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        Context context3 = getContext();
        Intrinsics.checkNotNull(context3);
        String string5 = getString(R.string.no_internet_connection);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.no_internet_connection)");
        String string6 = getString(R.string.please_check_network);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.please_check_network)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(context3, string5, string6);
        bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(false);
        String string7 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string7, "this.getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCurrentPlanDashboard.K(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void M() {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        String string = activity.getString(R.string.resting_day);
        Intrinsics.checkNotNullExpressionValue(string, "activity!!.getString(R.string.resting_day)");
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        String string2 = activity2.getString(R.string.resting_day_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "activity!!.getString(R.string.resting_day_msg)");
        FragmentActivity activity3 = getActivity();
        Intrinsics.checkNotNull(activity3);
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activity3, string, string2);
        bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(false);
        FragmentActivity activity4 = getActivity();
        Intrinsics.checkNotNull(activity4);
        String string3 = activity4.getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "activity!!.getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCurrentPlanDashboard.N(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void O(boolean z) {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new c(z, null), 2, null);
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final int getREQUEST_CODE_PLAN_DETAILS() {
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

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == this.m) {
            Serializable serializableExtra = intent != null ? intent.getSerializableExtra("data") : null;
            if (serializableExtra != null) {
                CoveEventBusManager.getInstance().getEventBus().post(serializableExtra);
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("param1");
            arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_current_plan_dashboard, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getViewModelCurrentPlanDashboard().checkIfPlanOngoingOrFinished();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ViewModel viewModel = ViewModelProviders.of(this).get(ViewModelCurrentPlanDashboard.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this).get(ViewModelCu…lanDashboard::class.java)");
        setViewModelCurrentPlanDashboard((ViewModelCurrentPlanDashboard) viewModel);
        getViewModelCurrentPlanDashboard().getPlanStatusLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.activitymodes.fragments.v
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentCurrentPlanDashboard.x(FragmentCurrentPlanDashboard.this, (PlanStatus) obj);
            }
        });
        ((CardView) _$_findCachedViewById(R.id.cv_status_not_started)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCurrentPlanDashboard.A(FragmentCurrentPlanDashboard.this, view2);
            }
        });
        ((CardView) _$_findCachedViewById(R.id.cv_current_activity)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCurrentPlanDashboard.B(FragmentCurrentPlanDashboard.this, view2);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cl_view_more_plans)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCurrentPlanDashboard.C(FragmentCurrentPlanDashboard.this, view2);
            }
        });
        ((CardView) _$_findCachedViewById(R.id.cv_my_history)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCurrentPlanDashboard.D(FragmentCurrentPlanDashboard.this, view2);
            }
        });
        ((CardView) _$_findCachedViewById(R.id.cv_rebuild_plan)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCurrentPlanDashboard.E(FragmentCurrentPlanDashboard.this, view2);
            }
        });
    }

    public final void setViewModelCurrentPlanDashboard(@NotNull ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard) {
        Intrinsics.checkNotNullParameter(viewModelCurrentPlanDashboard, "<set-?>");
        this.viewModelCurrentPlanDashboard = viewModelCurrentPlanDashboard;
    }
}
