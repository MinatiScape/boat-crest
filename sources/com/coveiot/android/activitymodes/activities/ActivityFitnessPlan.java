package com.coveiot.android.activitymodes.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory;
import com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo;
import com.coveiot.android.activitymodes.models.CurrentWeekAndDay;
import com.coveiot.android.activitymodes.models.FitnessPlanData;
import com.coveiot.android.activitymodes.models.PlanHistory;
import com.coveiot.android.activitymodes.models.WeekInfo;
import com.coveiot.android.activitymodes.utils.SingleLiveEvent;
import com.coveiot.android.activitymodes.utils.ViewUtilsKt;
import com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityFitnessPlan extends BaseActivity {
    public PlanDetailsViewModel q;
    public ViewModelCurrentPlanDashboard r;
    public ViewModelWorkoutFeedback s;
    public boolean t;
    @Nullable
    public String v;
    @Nullable
    public EntityPreparationPlan y;
    @Nullable
    public FitnessPlanTemplateRes.FitnessPlanTemplateData z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String p = ActivityFitnessPlan.class.getSimpleName();
    @NotNull
    public String u = "";
    @NotNull
    public String w = "";
    @NotNull
    public String x = "";

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$getOngoingPlanCardDetail$1", f = "ActivityFitnessPlan.kt", i = {1, 2, 2}, l = {226, 227, 237}, m = "invokeSuspend", n = {"totalActivitiesDistance", "totalActivitiesDistance", "activityCompletedData"}, s = {"L$0", "L$0", "L$1"})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function1<FitnessPlanData, Unit> $fitnessDataCallback;
        public final /* synthetic */ Ref.ObjectRef<FitnessPlanData> $ongoingPlanMainDetails;
        public final /* synthetic */ ArrayList<WeekInfo> $weekList;
        public Object L$0;
        public Object L$1;
        public int label;

        /* renamed from: com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C0231a extends Lambda implements Function1<Pair<? extends Integer, ? extends Integer>, Unit> {
            public final /* synthetic */ Ref.ObjectRef<FitnessPlanData> $ongoingPlanMainDetails;
            public final /* synthetic */ ActivityFitnessPlan this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0231a(ActivityFitnessPlan activityFitnessPlan, Ref.ObjectRef<FitnessPlanData> objectRef) {
                super(1);
                this.this$0 = activityFitnessPlan;
                this.$ongoingPlanMainDetails = objectRef;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Integer, ? extends Integer> pair) {
                invoke2((Pair<Integer, Integer>) pair);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Pair<Integer, Integer> activityData) {
                Intrinsics.checkNotNullParameter(activityData, "activityData");
                if (this.this$0.isFinishing()) {
                    return;
                }
                FitnessPlanData fitnessPlanData = this.$ongoingPlanMainDetails.element;
                if (fitnessPlanData != null) {
                    fitnessPlanData.setTotalActivities(activityData.getFirst().intValue());
                }
                FitnessPlanData fitnessPlanData2 = this.$ongoingPlanMainDetails.element;
                if (fitnessPlanData2 == null) {
                    return;
                }
                fitnessPlanData2.setCompletedActivities(activityData.getSecond().intValue());
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$getOngoingPlanCardDetail$1$3", f = "ActivityFitnessPlan.kt", i = {0, 0}, l = {com.veryfit.multi.nativeprotocol.b.j1}, m = "invokeSuspend", n = {"finalCoveredDistance", "distanceValueJob"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Function1<FitnessPlanData, Unit> $fitnessDataCallback;
            public final /* synthetic */ Ref.ObjectRef<FitnessPlanData> $ongoingPlanMainDetails;
            public final /* synthetic */ ArrayList<WeekInfo> $weekList;
            public Object L$0;
            public Object L$1;
            public int label;
            public final /* synthetic */ ActivityFitnessPlan this$0;

            @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$getOngoingPlanCardDetail$1$3$distanceValueJob$1", f = "ActivityFitnessPlan.kt", i = {0, 0, 1, 1, 2}, l = {274, 286, 304}, m = "invokeSuspend", n = {"dayData", "activity", "dayData", "activity", "dayData"}, s = {"L$6", "L$8", "L$6", "L$8", "L$6"})
            /* renamed from: com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$a$b$a  reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public static final class C0232a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ Ref.IntRef $finalCoveredDistance;
                public final /* synthetic */ Ref.ObjectRef<FitnessPlanData> $ongoingPlanMainDetails;
                public final /* synthetic */ Ref.IntRef $totalDistance;
                public final /* synthetic */ ArrayList<WeekInfo> $weekList;
                public Object L$0;
                public Object L$1;
                public Object L$10;
                public Object L$2;
                public Object L$3;
                public Object L$4;
                public Object L$5;
                public Object L$6;
                public Object L$7;
                public Object L$8;
                public Object L$9;
                public int label;
                public final /* synthetic */ ActivityFitnessPlan this$0;

                /* renamed from: com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$a$b$a$a  reason: collision with other inner class name */
                /* loaded from: classes2.dex */
                public static final class C0233a extends Lambda implements Function1<Integer, Unit> {
                    public final /* synthetic */ Ref.IntRef $finalCoveredDistance;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0233a(Ref.IntRef intRef) {
                        super(1);
                        this.$finalCoveredDistance = intRef;
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        this.$finalCoveredDistance.element += i;
                    }
                }

                /* renamed from: com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$a$b$a$b  reason: collision with other inner class name */
                /* loaded from: classes2.dex */
                public static final class C0234b extends Lambda implements Function1<Integer, Unit> {
                    public final /* synthetic */ Ref.IntRef $finalCoveredDistance;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0234b(Ref.IntRef intRef) {
                        super(1);
                        this.$finalCoveredDistance = intRef;
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        this.$finalCoveredDistance.element += i;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0232a(ArrayList<WeekInfo> arrayList, Ref.IntRef intRef, ActivityFitnessPlan activityFitnessPlan, Ref.ObjectRef<FitnessPlanData> objectRef, Ref.IntRef intRef2, Continuation<? super C0232a> continuation) {
                    super(2, continuation);
                    this.$weekList = arrayList;
                    this.$totalDistance = intRef;
                    this.this$0 = activityFitnessPlan;
                    this.$ongoingPlanMainDetails = objectRef;
                    this.$finalCoveredDistance = intRef2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0232a(this.$weekList, this.$totalDistance, this.this$0, this.$ongoingPlanMainDetails, this.$finalCoveredDistance, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0232a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x00e2  */
                /* JADX WARN: Removed duplicated region for block: B:18:0x00f6  */
                /* JADX WARN: Removed duplicated region for block: B:23:0x011f  */
                /* JADX WARN: Removed duplicated region for block: B:42:0x017a  */
                /* JADX WARN: Removed duplicated region for block: B:49:0x01b1  */
                /* JADX WARN: Removed duplicated region for block: B:58:0x0207  */
                /* JADX WARN: Removed duplicated region for block: B:64:0x0245  */
                /* JADX WARN: Removed duplicated region for block: B:75:0x02a5  */
                /* JADX WARN: Removed duplicated region for block: B:87:0x02ef  */
                /* JADX WARN: Removed duplicated region for block: B:95:0x0326  */
                /* JADX WARN: Removed duplicated region for block: B:98:0x0341  */
                /* JADX WARN: Removed duplicated region for block: B:99:0x034a  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x00e2 -> B:16:0x00f0). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0102 -> B:21:0x0119). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x0201 -> B:47:0x01ab). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0207 -> B:40:0x0174). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x0295 -> B:40:0x0174). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:76:0x02a7 -> B:77:0x02a9). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:83:0x02e8 -> B:85:0x02eb). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:96:0x0333 -> B:97:0x0339). Please submit an issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r21) {
                    /*
                        Method dump skipped, instructions count: 845
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activities.ActivityFitnessPlan.a.b.C0232a.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public b(ActivityFitnessPlan activityFitnessPlan, Ref.ObjectRef<FitnessPlanData> objectRef, Function1<? super FitnessPlanData, Unit> function1, ArrayList<WeekInfo> arrayList, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = activityFitnessPlan;
                this.$ongoingPlanMainDetails = objectRef;
                this.$fitnessDataCallback = function1;
                this.$weekList = arrayList;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, this.$ongoingPlanMainDetails, this.$fitnessDataCallback, this.$weekList, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Ref.IntRef intRef;
                Deferred b;
                Deferred deferred;
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    intRef = new Ref.IntRef();
                    b = kotlinx.coroutines.e.b(LifecycleOwnerKt.getLifecycleScope(this.this$0), null, null, new C0232a(this.$weekList, new Ref.IntRef(), this.this$0, this.$ongoingPlanMainDetails, intRef, null), 3, null);
                    Deferred[] deferredArr = {b};
                    this.L$0 = intRef;
                    this.L$1 = b;
                    this.label = 1;
                    if (AwaitKt.awaitAll(deferredArr, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    deferred = b;
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    deferred = (Deferred) this.L$1;
                    intRef = (Ref.IntRef) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                if (deferred.isCompleted() && !this.this$0.isFinishing()) {
                    FitnessPlanData fitnessPlanData = this.$ongoingPlanMainDetails.element;
                    if (fitnessPlanData != null) {
                        fitnessPlanData.setCompletedDistance(intRef.element);
                    }
                    this.$fitnessDataCallback.invoke(this.$ongoingPlanMainDetails.element);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(Ref.ObjectRef<FitnessPlanData> objectRef, ArrayList<WeekInfo> arrayList, Function1<? super FitnessPlanData, Unit> function1, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$ongoingPlanMainDetails = objectRef;
            this.$weekList = arrayList;
            this.$fitnessDataCallback = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$ongoingPlanMainDetails, this.$weekList, this.$fitnessDataCallback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x00df  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x011f  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0142  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0175  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x018b  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x01a4  */
        /* JADX WARN: Type inference failed for: r9v0, types: [T, com.coveiot.android.activitymodes.models.FitnessPlanData] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r27) {
            /*
                Method dump skipped, instructions count: 480
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activities.ActivityFitnessPlan.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityFitnessPlan", f = "ActivityFitnessPlan.kt", i = {0, 0, 0, 0, 0}, l = {393}, m = "getTotalNCompletedActivities", n = {"this", "activityCallback", "totalActivityCount", "completedActivityCount", "activityDeferred"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    /* loaded from: classes2.dex */
    public static final class b extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public int label;
        public /* synthetic */ Object result;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ActivityFitnessPlan.this.w(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$getTotalNCompletedActivities$activityDeferred$1", f = "ActivityFitnessPlan.kt", i = {0, 0, 1, 1}, l = {364, 375}, m = "invokeSuspend", n = {WeatherCriteria.UNIT_TYPE_DAY, "destination$iv$iv", WeatherCriteria.UNIT_TYPE_DAY, "destination$iv$iv"}, s = {"L$6", "L$7", "L$6", "L$7"})
    /* loaded from: classes2.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Ref.IntRef $completedActivityCount;
        public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData $fitnessPlanTemplateData;
        public final /* synthetic */ Ref.IntRef $totalActivityCount;
        public final /* synthetic */ ArrayList<WeekInfo> $weekList;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public Object L$6;
        public Object L$7;
        public Object L$8;
        public Object L$9;
        public int label;
        public final /* synthetic */ ActivityFitnessPlan this$0;

        /* loaded from: classes2.dex */
        public static final class a extends Lambda implements Function1<Integer, Unit> {
            public final /* synthetic */ com.coveiot.android.activitymodes.models.ActivityInfo $activityInfo;
            public final /* synthetic */ Ref.IntRef $completedActivityCount;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(com.coveiot.android.activitymodes.models.ActivityInfo activityInfo, Ref.IntRef intRef) {
                super(1);
                this.$activityInfo = activityInfo;
                this.$completedActivityCount = intRef;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                int convertCmToMeters = (int) ThemesUtils.INSTANCE.convertCmToMeters(i);
                String target = this.$activityInfo.getTarget();
                Intrinsics.checkNotNull(target);
                if (convertCmToMeters >= Integer.parseInt(target)) {
                    this.$completedActivityCount.element++;
                }
            }
        }

        /* loaded from: classes2.dex */
        public static final class b extends Lambda implements Function1<Integer, Unit> {
            public final /* synthetic */ com.coveiot.android.activitymodes.models.ActivityInfo $activityInfo;
            public final /* synthetic */ Ref.IntRef $completedActivityCount;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(com.coveiot.android.activitymodes.models.ActivityInfo activityInfo, Ref.IntRef intRef) {
                super(1);
                this.$activityInfo = activityInfo;
                this.$completedActivityCount = intRef;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                int convertCmToMeters = (int) ThemesUtils.INSTANCE.convertCmToMeters(i);
                String target = this.$activityInfo.getTarget();
                Intrinsics.checkNotNull(target);
                if (convertCmToMeters >= Integer.parseInt(target)) {
                    this.$completedActivityCount.element++;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(ArrayList<WeekInfo> arrayList, Ref.IntRef intRef, FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData, ActivityFitnessPlan activityFitnessPlan, Ref.IntRef intRef2, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$weekList = arrayList;
            this.$totalActivityCount = intRef;
            this.$fitnessPlanTemplateData = fitnessPlanTemplateData;
            this.this$0 = activityFitnessPlan;
            this.$completedActivityCount = intRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$weekList, this.$totalActivityCount, this.$fitnessPlanTemplateData, this.this$0, this.$completedActivityCount, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:13:0x0092  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x00a6  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00de  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0237  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x0256  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x025a  */
        /* JADX WARN: Type inference failed for: r2v18, types: [java.util.Collection] */
        /* JADX WARN: Type inference failed for: r2v21, types: [java.util.Collection] */
        /* JADX WARN: Type inference failed for: r6v11 */
        /* JADX WARN: Type inference failed for: r6v14 */
        /* JADX WARN: Type inference failed for: r6v16, types: [java.util.Collection] */
        /* JADX WARN: Type inference failed for: r6v19, types: [java.util.Collection] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0092 -> B:14:0x00a0). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00b2 -> B:19:0x00d8). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x01a3 -> B:83:0x022b). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:77:0x01fc -> B:78:0x0205). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:82:0x0222 -> B:83:0x022b). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:85:0x024e -> B:86:0x0251). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r24) {
            /*
                Method dump skipped, instructions count: 605
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activities.ActivityFitnessPlan.c.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$initData$1", f = "ActivityFitnessPlan.kt", i = {}, l = {86}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
        public int label;

        /* loaded from: classes2.dex */
        public static final class a extends Lambda implements Function1<FitnessPlanTemplateRes.FitnessPlanTemplateData, Unit> {
            public final /* synthetic */ ActivityFitnessPlan this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityFitnessPlan activityFitnessPlan) {
                super(1);
                this.this$0 = activityFitnessPlan;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
                invoke2(fitnessPlanTemplateData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
                if (fitnessPlanTemplateData != null) {
                    this.this$0.setFitnessPlanTemplateData(fitnessPlanTemplateData);
                } else if (!this.this$0.isFinishing()) {
                    this.this$0.dismissProgress();
                }
                PlanDetailsViewModel planDetailsViewModel = this.this$0.q;
                if (planDetailsViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("planDetailsViewModel");
                    planDetailsViewModel = null;
                }
                planDetailsViewModel.getWeeksInFo(this.this$0);
            }
        }

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
            ActivityFitnessPlan activityFitnessPlan;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            PlanDetailsViewModel planDetailsViewModel = null;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (ActivityFitnessPlan.this.t) {
                    PlanDetailsViewModel planDetailsViewModel2 = ActivityFitnessPlan.this.q;
                    if (planDetailsViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("planDetailsViewModel");
                    } else {
                        planDetailsViewModel = planDetailsViewModel2;
                    }
                    planDetailsViewModel.getFitnessHistoryPlan();
                } else {
                    if (ActivityFitnessPlan.this.u.length() > 0) {
                        PlanDetailsViewModel planDetailsViewModel3 = ActivityFitnessPlan.this.q;
                        if (planDetailsViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("planDetailsViewModel");
                        } else {
                            planDetailsViewModel = planDetailsViewModel3;
                        }
                        planDetailsViewModel.getUserFitnessPlanInfo(ActivityFitnessPlan.this.u);
                    } else {
                        ActivityFitnessPlan activityFitnessPlan2 = ActivityFitnessPlan.this;
                        ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = activityFitnessPlan2.r;
                        if (viewModelCurrentPlanDashboard == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
                            viewModelCurrentPlanDashboard = null;
                        }
                        this.L$0 = activityFitnessPlan2;
                        this.label = 1;
                        Object currentPlan = viewModelCurrentPlanDashboard.getCurrentPlan(this);
                        if (currentPlan == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        activityFitnessPlan = activityFitnessPlan2;
                        obj = currentPlan;
                    }
                }
                return Unit.INSTANCE;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                activityFitnessPlan = (ActivityFitnessPlan) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            activityFitnessPlan.setCurrentPlan((EntityPreparationPlan) obj);
            if (ActivityFitnessPlan.this.getCurrentPlan() != null) {
                ActivityFitnessPlan.this.C();
                PlanDetailsViewModel planDetailsViewModel4 = ActivityFitnessPlan.this.q;
                if (planDetailsViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("planDetailsViewModel");
                } else {
                    planDetailsViewModel = planDetailsViewModel4;
                }
                EntityPreparationPlan currentPlan2 = ActivityFitnessPlan.this.getCurrentPlan();
                Intrinsics.checkNotNull(currentPlan2);
                planDetailsViewModel.getPlanTemplate(currentPlan2.getPlanId(), new a(ActivityFitnessPlan.this));
            } else if (!ActivityFitnessPlan.this.isFinishing()) {
                ActivityFitnessPlan.this.dismissProgress();
            }
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes2.dex */
    public static final class e extends Lambda implements Function1<FitnessPlanData, Unit> {
        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FitnessPlanData fitnessPlanData) {
            invoke2(fitnessPlanData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable FitnessPlanData fitnessPlanData) {
            if (fitnessPlanData != null) {
                if (ActivityFitnessPlan.this.v != null) {
                    String str = ActivityFitnessPlan.this.v;
                    Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
                    fitnessPlanData.setBackgroundImage(str);
                } else {
                    if (ActivityFitnessPlan.this.x.length() > 0) {
                        fitnessPlanData.setBackgroundImage(ActivityFitnessPlan.this.x);
                    }
                }
                if (ActivityFitnessPlan.this.w.length() > 0) {
                    fitnessPlanData.setTitle(ActivityFitnessPlan.this.w);
                }
                ActivityFitnessPlan.this.getSupportFragmentManager().beginTransaction().replace(R.id.fitnessContainer, FragmentFitnessPlanWeekAndDayInfo.Companion.newInstance(fitnessPlanData, new CurrentWeekAndDay(0, 0, ""), fitnessPlanData.getBackgroundImage(), true)).commitAllowingStateLoss();
                ActivityFitnessPlan.this.dismissProgress();
                return;
            }
            ActivityFitnessPlan activityFitnessPlan = ActivityFitnessPlan.this;
            String string = activityFitnessPlan.getString(R.string.please_try_again);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_try_again)");
            ViewUtilsKt.toast(activityFitnessPlan, string);
            ActivityFitnessPlan.this.finish();
        }
    }

    /* loaded from: classes2.dex */
    public static final class f extends Lambda implements Function1<List<? extends PlanHistory>, Unit> {
        public f() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends PlanHistory> list) {
            invoke2((List<PlanHistory>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<PlanHistory> list) {
            if (ActivityFitnessPlan.this.isFinishing()) {
                return;
            }
            ActivityFitnessPlan.this.dismissProgress();
            FragmentTransaction beginTransaction = ActivityFitnessPlan.this.getSupportFragmentManager().beginTransaction();
            int i = R.id.fitnessContainer;
            FragmentFitnessPlanHistory.Companion companion = FragmentFitnessPlanHistory.Companion;
            if (list == null) {
                list = CollectionsKt__CollectionsKt.emptyList();
            }
            beginTransaction.replace(i, companion.newInstance(list, "")).commit();
        }
    }

    /* loaded from: classes2.dex */
    public static final class g extends Lambda implements Function1<CurrentWeekAndDay, Unit> {
        public final /* synthetic */ Ref.ObjectRef<CurrentWeekAndDay> $currentWeekAndDay;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(Ref.ObjectRef<CurrentWeekAndDay> objectRef) {
            super(1);
            this.$currentWeekAndDay = objectRef;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CurrentWeekAndDay currentWeekAndDay) {
            invoke2(currentWeekAndDay);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(CurrentWeekAndDay it) {
            Ref.ObjectRef<CurrentWeekAndDay> objectRef = this.$currentWeekAndDay;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            objectRef.element = it;
        }
    }

    /* loaded from: classes2.dex */
    public static final class h extends Lambda implements Function1<List<? extends WeekInfo>, Unit> {
        public final /* synthetic */ Ref.ObjectRef<CurrentWeekAndDay> $currentWeekAndDay;

        /* loaded from: classes2.dex */
        public static final class a extends Lambda implements Function1<FitnessPlanData, Unit> {
            public final /* synthetic */ Ref.ObjectRef<CurrentWeekAndDay> $currentWeekAndDay;
            public final /* synthetic */ FitnessPlanData $finalFitnessPlanData;
            public final /* synthetic */ List<WeekInfo> $it;
            public final /* synthetic */ ActivityFitnessPlan this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityFitnessPlan activityFitnessPlan, FitnessPlanData fitnessPlanData, List<WeekInfo> list, Ref.ObjectRef<CurrentWeekAndDay> objectRef) {
                super(1);
                this.this$0 = activityFitnessPlan;
                this.$finalFitnessPlanData = fitnessPlanData;
                this.$it = list;
                this.$currentWeekAndDay = objectRef;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FitnessPlanData fitnessPlanData) {
                invoke2(fitnessPlanData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable FitnessPlanData fitnessPlanData) {
                ActivityFitnessPlan activityFitnessPlan;
                int i;
                LogHelper.d(this.this$0.p, "getOngoingPlanCardDetail called");
                if (this.this$0.isFinishing() || fitnessPlanData == null || this.this$0.getCurrentPlan() == null) {
                    return;
                }
                FitnessPlanData fitnessPlanData2 = this.$finalFitnessPlanData;
                StringBuilder sb = new StringBuilder();
                sb.append(this.$it.size());
                sb.append(' ');
                if (this.$it.size() >= 2) {
                    activityFitnessPlan = this.this$0;
                    i = R.string.weeks;
                } else {
                    activityFitnessPlan = this.this$0;
                    i = R.string.week;
                }
                sb.append(activityFitnessPlan.getString(i));
                fitnessPlanData2.setTitle(sb.toString());
                this.$finalFitnessPlanData.setProgress(fitnessPlanData.getProgress());
                this.$finalFitnessPlanData.setActivitiesMapped(fitnessPlanData.getActivitiesMapped());
                this.$finalFitnessPlanData.setMaxProgress(fitnessPlanData.getMaxProgress());
                this.$finalFitnessPlanData.setTotalCalories(fitnessPlanData.getTotalCalories());
                this.$finalFitnessPlanData.setTotalDistance(fitnessPlanData.getTotalDistance());
                this.$finalFitnessPlanData.setTotalActivities(fitnessPlanData.getTotalActivities());
                this.$finalFitnessPlanData.setCompletedDistance(fitnessPlanData.getCompletedDistance());
                this.$finalFitnessPlanData.setCompletedActivities(fitnessPlanData.getCompletedActivities());
                this.$finalFitnessPlanData.setTotalSteps(fitnessPlanData.getTotalSteps());
                this.$finalFitnessPlanData.setCategoryChosen(fitnessPlanData.getCategoryChosen());
                this.$finalFitnessPlanData.setPlanCompleted(false);
                this.$finalFitnessPlanData.setPlanTemplateId(fitnessPlanData.getPlanTemplateId());
                this.$finalFitnessPlanData.setBackgroundImage(fitnessPlanData.getBackgroundImage());
                FitnessPlanData fitnessPlanData3 = this.$finalFitnessPlanData;
                List<WeekInfo> it = this.$it;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                fitnessPlanData3.setWeekList((ArrayList) it);
                FragmentTransaction beginTransaction = this.this$0.getSupportFragmentManager().beginTransaction();
                int i2 = R.id.fitnessContainer;
                FragmentFitnessPlanWeekAndDayInfo.Companion companion = FragmentFitnessPlanWeekAndDayInfo.Companion;
                FitnessPlanData fitnessPlanData4 = this.$finalFitnessPlanData;
                Intrinsics.checkNotNull(fitnessPlanData4);
                beginTransaction.replace(i2, companion.newInstance(fitnessPlanData4, this.$currentWeekAndDay.element, this.$finalFitnessPlanData.getBackgroundImage(), false)).commitAllowingStateLoss();
                this.this$0.dismissProgress();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(Ref.ObjectRef<CurrentWeekAndDay> objectRef) {
            super(1);
            this.$currentWeekAndDay = objectRef;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends WeekInfo> list) {
            invoke2((List<WeekInfo>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<WeekInfo> list) {
            if (ActivityFitnessPlan.this.isFinishing() || list == null || ActivityFitnessPlan.this.getCurrentPlan() == null) {
                return;
            }
            ArrayList arrayList = (ArrayList) list;
            FitnessPlanData fitnessPlanData = new FitnessPlanData("", false, 0, 0, 0.0d, 0, 0, 0.0d, 0, 0, "", false, "", "", arrayList);
            ActivityFitnessPlan activityFitnessPlan = ActivityFitnessPlan.this;
            activityFitnessPlan.v(arrayList, new a(activityFitnessPlan, fitnessPlanData, list, this.$currentWeekAndDay));
        }
    }

    public static final void A(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void B(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void D(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void E(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void y(ActivityFitnessPlan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.coveiot.android.activitymodes.models.CurrentWeekAndDay] */
    public final void C() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new CurrentWeekAndDay(0, 0, "");
        PlanDetailsViewModel planDetailsViewModel = this.q;
        PlanDetailsViewModel planDetailsViewModel2 = null;
        if (planDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planDetailsViewModel");
            planDetailsViewModel = null;
        }
        SingleLiveEvent<CurrentWeekAndDay> currentWeekAndDayValue = planDetailsViewModel.getCurrentWeekAndDayValue();
        final g gVar = new g(objectRef);
        currentWeekAndDayValue.observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.activities.w0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityFitnessPlan.D(Function1.this, obj);
            }
        });
        PlanDetailsViewModel planDetailsViewModel3 = this.q;
        if (planDetailsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planDetailsViewModel");
        } else {
            planDetailsViewModel2 = planDetailsViewModel3;
        }
        SingleLiveEvent<List<WeekInfo>> weekListMutableList = planDetailsViewModel2.getWeekListMutableList();
        final h hVar = new h(objectRef);
        weekListMutableList.observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.activities.z0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityFitnessPlan.E(Function1.this, obj);
            }
        });
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

    @Nullable
    public final EntityPreparationPlan getCurrentPlan() {
        return this.y;
    }

    @Nullable
    public final FitnessPlanTemplateRes.FitnessPlanTemplateData getFitnessPlanTemplateData() {
        return this.z;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(this.t ? getString(R.string.fitness_history) : getString(R.string.fitness_plan));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.v0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFitnessPlan.y(ActivityFitnessPlan.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fitness_plan);
        ViewModel viewModel = new ViewModelProvider(this).get(PlanDetailsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this)[…ilsViewModel::class.java]");
        this.q = (PlanDetailsViewModel) viewModel;
        ViewModel viewModel2 = new ViewModelProvider(this).get(ViewModelCurrentPlanDashboard.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProvider(this)[…lanDashboard::class.java]");
        this.r = (ViewModelCurrentPlanDashboard) viewModel2;
        ViewModel viewModel3 = new ViewModelProvider(this).get(ViewModelWorkoutFeedback.class);
        Intrinsics.checkNotNullExpressionValue(viewModel3, "ViewModelProvider(this)[…koutFeedback::class.java]");
        this.s = (ViewModelWorkoutFeedback) viewModel3;
        if (getIntent().getExtras() != null) {
            this.t = getIntent().getBooleanExtra("isPlanHistory", false);
            this.w = String.valueOf(getIntent().getStringExtra("planTitleHistory"));
            this.x = String.valueOf(getIntent().getStringExtra("planHistoryBackground"));
            String stringExtra = getIntent().getStringExtra("userPlanId");
            if (stringExtra != null) {
                stringExtra.length();
            }
        }
        if (SessionManager.getInstance(this).getUserPlanId() != null) {
            String userPlanId = SessionManager.getInstance(this).getUserPlanId();
            Intrinsics.checkNotNullExpressionValue(userPlanId, "getInstance(this@ActivityFitnessPlan).userPlanId");
            this.u = userPlanId;
        }
        if (SessionManager.getInstance(this).getUserPlanBackground() != null) {
            this.v = SessionManager.getInstance(this).getUserPlanBackground();
        }
        initToolbar();
        showProgress();
        z();
        x();
    }

    public final void setCurrentPlan(@Nullable EntityPreparationPlan entityPreparationPlan) {
        this.y = entityPreparationPlan;
    }

    public final void setFitnessPlanTemplateData(@Nullable FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
        this.z = fitnessPlanTemplateData;
    }

    public final void v(ArrayList<WeekInfo> arrayList, Function1<? super FitnessPlanData, Unit> function1) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new a(new Ref.ObjectRef(), arrayList, function1, null), 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object w(java.util.ArrayList<com.coveiot.android.activitymodes.models.WeekInfo> r18, com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes.FitnessPlanTemplateData r19, kotlin.jvm.functions.Function1<? super kotlin.Pair<java.lang.Integer, java.lang.Integer>, kotlin.Unit> r20, kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            r17 = this;
            r7 = r17
            r0 = r21
            boolean r1 = r0 instanceof com.coveiot.android.activitymodes.activities.ActivityFitnessPlan.b
            if (r1 == 0) goto L17
            r1 = r0
            com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$b r1 = (com.coveiot.android.activitymodes.activities.ActivityFitnessPlan.b) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L17
            int r2 = r2 - r3
            r1.label = r2
            goto L1c
        L17:
            com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$b r1 = new com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$b
            r1.<init>(r0)
        L1c:
            r8 = r1
            java.lang.Object r0 = r8.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r10 = 1
            if (r1 == 0) goto L4b
            if (r1 != r10) goto L43
            java.lang.Object r1 = r8.L$4
            kotlinx.coroutines.Deferred r1 = (kotlinx.coroutines.Deferred) r1
            java.lang.Object r2 = r8.L$3
            kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
            java.lang.Object r3 = r8.L$2
            kotlin.jvm.internal.Ref$IntRef r3 = (kotlin.jvm.internal.Ref.IntRef) r3
            java.lang.Object r4 = r8.L$1
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = r8.L$0
            com.coveiot.android.activitymodes.activities.ActivityFitnessPlan r5 = (com.coveiot.android.activitymodes.activities.ActivityFitnessPlan) r5
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r4
            goto L91
        L43:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L4b:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r11 = new kotlin.jvm.internal.Ref$IntRef
            r11.<init>()
            kotlin.jvm.internal.Ref$IntRef r12 = new kotlin.jvm.internal.Ref$IntRef
            r12.<init>()
            androidx.lifecycle.LifecycleCoroutineScope r13 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r17)
            r14 = 0
            r15 = 0
            com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$c r16 = new com.coveiot.android.activitymodes.activities.ActivityFitnessPlan$c
            r6 = 0
            r0 = r16
            r1 = r18
            r2 = r11
            r3 = r19
            r4 = r17
            r5 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r4 = 3
            r5 = 0
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            kotlinx.coroutines.Deferred r1 = kotlinx.coroutines.BuildersKt.async$default(r0, r1, r2, r3, r4, r5)
            r8.L$0 = r7
            r0 = r20
            r8.L$1 = r0
            r8.L$2 = r11
            r8.L$3 = r12
            r8.L$4 = r1
            r8.label = r10
            java.lang.Object r2 = r1.await(r8)
            if (r2 != r9) goto L8e
            return r9
        L8e:
            r5 = r7
            r3 = r11
            r2 = r12
        L91:
            boolean r1 = r1.isCompleted()
            if (r1 == 0) goto Lb1
            boolean r1 = r5.isFinishing()
            if (r1 != 0) goto Lb1
            kotlin.Pair r1 = new kotlin.Pair
            int r3 = r3.element
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            int r2 = r2.element
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            r1.<init>(r3, r2)
            r0.invoke(r1)
        Lb1:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activities.ActivityFitnessPlan.w(java.util.ArrayList, com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes$FitnessPlanTemplateData, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void x() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
    }

    public final void z() {
        PlanDetailsViewModel planDetailsViewModel = this.q;
        PlanDetailsViewModel planDetailsViewModel2 = null;
        if (planDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planDetailsViewModel");
            planDetailsViewModel = null;
        }
        SingleLiveEvent<FitnessPlanData> fitnessPlanData = planDetailsViewModel.getFitnessPlanData();
        final e eVar = new e();
        fitnessPlanData.observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.activities.x0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityFitnessPlan.A(Function1.this, obj);
            }
        });
        PlanDetailsViewModel planDetailsViewModel3 = this.q;
        if (planDetailsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planDetailsViewModel");
        } else {
            planDetailsViewModel2 = planDetailsViewModel3;
        }
        SingleLiveEvent<List<PlanHistory>> fitnessPlanHistory = planDetailsViewModel2.getFitnessPlanHistory();
        final f fVar = new f();
        fitnessPlanHistory.observe(this, new Observer() { // from class: com.coveiot.android.activitymodes.activities.y0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityFitnessPlan.B(Function1.this, obj);
            }
        });
    }
}
