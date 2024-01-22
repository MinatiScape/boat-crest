package com.coveiot.android.activitymodes.viewmodels;

import android.app.Application;
import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.activitymodes.MoodAfterTheSession;
import com.coveiot.android.activitymodes.SessionPlaceType;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationActivity;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CovePreparationPlanApi;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.FitnessPlanProgressReq;
import com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes;
import com.coveiot.coveaccess.preparationplan.requestmodel.PlanProgressUpdateReq;
import com.coveiot.coveaccess.preparationplan.requestmodel.PlanProgressUpdateRes;
import com.google.mlkit.common.MlKitException;
import com.jieli.jl_rcsp.constant.Command;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import com.realsil.sdk.dfu.DfuException;
import com.touchgui.sdk.TGEventListener;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewModelWorkoutFeedback extends AndroidViewModel {
    @NotNull
    public Context d;
    @Nullable
    public String e;
    @NotNull
    public String f;
    @NotNull
    public String g;
    @NotNull
    public MutableLiveData<Boolean> h;
    @NotNull
    public MutableLiveData<Pair<Integer, Integer>> i;

    /* loaded from: classes2.dex */
    public static final class a extends Lambda implements Function1<FitnessPlanTemplateRes.FitnessPlanTemplateData, Unit> {
        public final /* synthetic */ EntityPreparationDay $dayData;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$dailyDistanceCalculation$1$1", f = "ViewModelWorkoutFeedback.kt", i = {0, 0, 0}, l = {MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD}, m = "invokeSuspend", n = {"finalCoveredDistance", "totalDistance", "distanceValueJob"}, s = {"L$0", "L$1", "L$2"})
        /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C0243a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ EntityPreparationDay $dayData;
            public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData $template;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public int label;
            public final /* synthetic */ ViewModelWorkoutFeedback this$0;

            @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$dailyDistanceCalculation$1$1$distanceValueJob$1", f = "ViewModelWorkoutFeedback.kt", i = {0, 0, 1, 1, 2}, l = {171, 183, 195}, m = "invokeSuspend", n = {"destination$iv$iv", "activity", "destination$iv$iv", "activity", "destination$iv$iv"}, s = {"L$5", "L$7", "L$5", "L$7", "L$5"})
            /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public static final class C0244a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Unit>>, Object> {
                public final /* synthetic */ EntityPreparationDay $dayData;
                public final /* synthetic */ Ref.IntRef $finalCoveredDistance;
                public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData $template;
                public final /* synthetic */ Ref.IntRef $totalDistance;
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
                public final /* synthetic */ ViewModelWorkoutFeedback this$0;

                /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$a$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes2.dex */
                public static final class C0245a extends Lambda implements Function1<Integer, Unit> {
                    public final /* synthetic */ Ref.IntRef $finalCoveredDistance;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0245a(Ref.IntRef intRef) {
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

                /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$a$a$a$b */
                /* loaded from: classes2.dex */
                public static final class b extends Lambda implements Function1<Integer, Unit> {
                    public final /* synthetic */ Ref.IntRef $finalCoveredDistance;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(Ref.IntRef intRef) {
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
                public C0244a(EntityPreparationDay entityPreparationDay, Ref.IntRef intRef, FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData, ViewModelWorkoutFeedback viewModelWorkoutFeedback, Ref.IntRef intRef2, Continuation<? super C0244a> continuation) {
                    super(2, continuation);
                    this.$dayData = entityPreparationDay;
                    this.$totalDistance = intRef;
                    this.$template = fitnessPlanTemplateData;
                    this.this$0 = viewModelWorkoutFeedback;
                    this.$finalCoveredDistance = intRef2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0244a(this.$dayData, this.$totalDistance, this.$template, this.this$0, this.$finalCoveredDistance, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Unit>> continuation) {
                    return invoke2(coroutineScope, (Continuation<? super List<Unit>>) continuation);
                }

                @Nullable
                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<Unit>> continuation) {
                    return ((C0244a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:15:0x00ea  */
                /* JADX WARN: Removed duplicated region for block: B:28:0x0130  */
                /* JADX WARN: Removed duplicated region for block: B:35:0x0169  */
                /* JADX WARN: Removed duplicated region for block: B:39:0x01a5  */
                /* JADX WARN: Removed duplicated region for block: B:46:0x01df  */
                /* JADX WARN: Removed duplicated region for block: B:54:0x0229  */
                /* JADX WARN: Removed duplicated region for block: B:61:0x026d  */
                /* JADX WARN: Removed duplicated region for block: B:71:0x02b5  */
                /* JADX WARN: Type inference failed for: r10v13, types: [java.util.Collection] */
                /* JADX WARN: Type inference failed for: r12v17, types: [java.util.Collection] */
                /* JADX WARN: Type inference failed for: r2v17, types: [java.util.Collection] */
                /* JADX WARN: Type inference failed for: r2v19, types: [java.util.Collection] */
                /* JADX WARN: Type inference failed for: r2v22, types: [java.util.Collection] */
                /* JADX WARN: Type inference failed for: r9v13 */
                /* JADX WARN: Type inference failed for: r9v19 */
                /* JADX WARN: Type inference failed for: r9v26, types: [java.util.Collection] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x019f -> B:33:0x0163). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x01ac -> B:26:0x012a). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x0229 -> B:70:0x02a8). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0268 -> B:59:0x0269). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x02a3 -> B:70:0x02a8). Please submit an issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r20) {
                    /*
                        Method dump skipped, instructions count: 696
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.a.C0243a.C0244a.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0243a(EntityPreparationDay entityPreparationDay, ViewModelWorkoutFeedback viewModelWorkoutFeedback, FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData, Continuation<? super C0243a> continuation) {
                super(2, continuation);
                this.$dayData = entityPreparationDay;
                this.this$0 = viewModelWorkoutFeedback;
                this.$template = fitnessPlanTemplateData;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0243a(this.$dayData, this.this$0, this.$template, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0243a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Ref.IntRef intRef;
                Deferred b;
                Ref.IntRef intRef2;
                Deferred deferred;
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.IntRef intRef3 = new Ref.IntRef();
                    intRef = new Ref.IntRef();
                    if (this.$dayData.getActivities() != null) {
                        b = kotlinx.coroutines.e.b(ViewModelKt.getViewModelScope(this.this$0), null, null, new C0244a(this.$dayData, intRef, this.$template, this.this$0, intRef3, null), 3, null);
                        Deferred[] deferredArr = {b};
                        this.L$0 = intRef3;
                        this.L$1 = intRef;
                        this.L$2 = b;
                        this.label = 1;
                        if (AwaitKt.awaitAll(deferredArr, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        intRef2 = intRef3;
                        deferred = b;
                    }
                    return Unit.INSTANCE;
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    deferred = (Deferred) this.L$2;
                    intRef = (Ref.IntRef) this.L$1;
                    intRef2 = (Ref.IntRef) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                if (deferred.isCompleted()) {
                    this.this$0.getDailyDistanceData().postValue(new Pair<>(Boxing.boxInt(intRef2.element), Boxing.boxInt(intRef.element)));
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(EntityPreparationDay entityPreparationDay) {
            super(1);
            this.$dayData = entityPreparationDay;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
            invoke2(fitnessPlanTemplateData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull FitnessPlanTemplateRes.FitnessPlanTemplateData template) {
            Intrinsics.checkNotNullParameter(template, "template");
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(ViewModelWorkoutFeedback.this), Dispatchers.getIO(), null, new C0243a(this.$dayData, ViewModelWorkoutFeedback.this, template, null), 2, null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$getAllWeekDistanceValueByActivityNCategoryIDs$1", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {675}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function1<Integer, Unit> $distanceCoveredCallback;
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $idString;
        public final /* synthetic */ String $startDate;
        public Object L$0;
        public int label;
        public final /* synthetic */ ViewModelWorkoutFeedback this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public b(String str, Function1<? super Integer, Unit> function1, ViewModelWorkoutFeedback viewModelWorkoutFeedback, String str2, String str3, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$idString = str;
            this.$distanceCoveredCallback = function1;
            this.this$0 = viewModelWorkoutFeedback;
            this.$startDate = str2;
            this.$endDate = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$idString, this.$distanceCoveredCallback, this.this$0, this.$startDate, this.$endDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.functions.Function1] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Function1<Integer, Unit> function1;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                List split$default = StringsKt__StringsKt.split$default((CharSequence) this.$idString, new String[]{"-"}, false, 0, 6, (Object) null);
                Function1<Integer, Unit> function12 = this.$distanceCoveredCallback;
                ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.this$0;
                int parseInt = Integer.parseInt((String) split$default.get(0));
                int parseInt2 = Integer.parseInt((String) split$default.get(1));
                String str = this.$startDate;
                String str2 = this.$endDate;
                this.L$0 = function12;
                this.label = 1;
                obj = viewModelWorkoutFeedback.getDistanceForWeekByActivityNCategoryIDs(parseInt, parseInt2, str, str2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                function1 = function12;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                function1 = (Function1) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            function1.invoke(obj);
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$getAllWeeksDistanceValueByActivityType$1", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.I2}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $activityType;
        public final /* synthetic */ Function1<Integer, Unit> $distanceCoveredCallback;
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public Object L$0;
        public int label;
        public final /* synthetic */ ViewModelWorkoutFeedback this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public c(Function1<? super Integer, Unit> function1, ViewModelWorkoutFeedback viewModelWorkoutFeedback, String str, String str2, String str3, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$distanceCoveredCallback = function1;
            this.this$0 = viewModelWorkoutFeedback;
            this.$startDate = str;
            this.$endDate = str2;
            this.$activityType = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$distanceCoveredCallback, this.this$0, this.$startDate, this.$endDate, this.$activityType, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.functions.Function1] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Function1<Integer, Unit> function1;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function1<Integer, Unit> function12 = this.$distanceCoveredCallback;
                ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.this$0;
                String str = this.$startDate;
                String str2 = this.$endDate;
                String str3 = this.$activityType;
                this.L$0 = function12;
                this.label = 1;
                Object distanceForWeekByActivityType = viewModelWorkoutFeedback.getDistanceForWeekByActivityType(str, str2, str3, this);
                if (distanceForWeekByActivityType == coroutine_suspended) {
                    return coroutine_suspended;
                }
                function1 = function12;
                obj = distanceForWeekByActivityType;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                function1 = (Function1) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            function1.invoke(obj);
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.V2}, m = "getCaloriesTotalValueByActivityType", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public d(Continuation<? super d> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ViewModelWorkoutFeedback.this.b(null, null, null, this);
        }
    }

    /* loaded from: classes2.dex */
    public static final class e extends Lambda implements Function1<FitnessPlanTemplateRes.FitnessPlanTemplateData, Unit> {
        public final /* synthetic */ Function1<Pair<Integer, Double>, Unit> $completedDistanceCallback;
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$getCompletedDistanceCalorie$1$1", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {692, 693}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Function1<Pair<Integer, Double>, Unit> $completedDistanceCallback;
            public final /* synthetic */ String $endDate;
            public final /* synthetic */ String $startDate;
            public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData $template;
            public Object L$0;
            public Object L$1;
            public int label;
            public final /* synthetic */ ViewModelWorkoutFeedback this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a(Function1<? super Pair<Integer, Double>, Unit> function1, ViewModelWorkoutFeedback viewModelWorkoutFeedback, FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData, String str, String str2, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$completedDistanceCallback = function1;
                this.this$0 = viewModelWorkoutFeedback;
                this.$template = fitnessPlanTemplateData;
                this.$startDate = str;
                this.$endDate = str2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$completedDistanceCallback, this.this$0, this.$template, this.$startDate, this.$endDate, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Function1<Pair<Integer, Double>, Unit> function1;
                Object obj2;
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Function1<Pair<Integer, Double>, Unit> function12 = this.$completedDistanceCallback;
                    ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.this$0;
                    FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData = this.$template;
                    String str = this.$startDate;
                    String str2 = this.$endDate;
                    this.L$0 = function12;
                    this.label = 1;
                    Object h = viewModelWorkoutFeedback.h(fitnessPlanTemplateData, str, str2, this);
                    if (h == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    function1 = function12;
                    obj = h;
                } else if (i != 1) {
                    if (i == 2) {
                        obj2 = this.L$1;
                        function1 = (Function1) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        function1.invoke(new Pair<>(obj2, obj));
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    function1 = (Function1) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                ViewModelWorkoutFeedback viewModelWorkoutFeedback2 = this.this$0;
                FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData2 = this.$template;
                String str3 = this.$startDate;
                String str4 = this.$endDate;
                this.L$0 = function1;
                this.L$1 = obj;
                this.label = 2;
                Object g = viewModelWorkoutFeedback2.g(fitnessPlanTemplateData2, str3, str4, this);
                if (g == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj2 = obj;
                obj = g;
                function1.invoke(new Pair<>(obj2, obj));
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public e(Function1<? super Pair<Integer, Double>, Unit> function1, String str, String str2) {
            super(1);
            this.$completedDistanceCallback = function1;
            this.$startDate = str;
            this.$endDate = str2;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
            invoke2(fitnessPlanTemplateData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull FitnessPlanTemplateRes.FitnessPlanTemplateData template) {
            Intrinsics.checkNotNullParameter(template, "template");
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(ViewModelWorkoutFeedback.this), Dispatchers.getIO(), null, new a(this.$completedDistanceCallback, ViewModelWorkoutFeedback.this, template, this.$startDate, this.$endDate, null), 2, null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback", f = "ViewModelWorkoutFeedback.kt", i = {0, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3}, l = {TGEventListener.AWAKE_SCREEN_UPDATED, 774, 776, 782}, m = "getCurrentPlanAndTotalProgress", n = {"this", "this", "plan", "planStartDate", "this", "plan", "this", "plan", "sessionAllDayDistance", "totalDistance", WeatherCriteria.UNIT_TYPE_DAY}, s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$5"})
    /* loaded from: classes2.dex */
    public static final class f extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public int label;
        public /* synthetic */ Object result;

        public f(Continuation<? super f> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ViewModelWorkoutFeedback.this.getCurrentPlanAndTotalProgress(this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {643}, m = "getDistanceValueByActivityNCategoryIDs", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class g extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public g(Continuation<? super g> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ViewModelWorkoutFeedback.this.getDistanceValueByActivityNCategoryIDs(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.P2}, m = "getDistanceValueByActivityType", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class h extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public h(Continuation<? super h> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ViewModelWorkoutFeedback.this.getDistanceValueByActivityType(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {663}, m = "getStepsTotalByActivityNCategoryIDs", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class i extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public i(Continuation<? super i> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ViewModelWorkoutFeedback.this.getStepsTotalByActivityNCategoryIDs(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {635}, m = "getStepsTotalByActivityType", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class j extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public j(Continuation<? super j> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ViewModelWorkoutFeedback.this.f(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.Z2}, m = "getTotalCaloriesByActivityNCategoryIDs", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class k extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public k(Continuation<? super k> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ViewModelWorkoutFeedback.this.getTotalCaloriesByActivityNCategoryIDs(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback", f = "ViewModelWorkoutFeedback.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {709, 715}, m = "getTotalCompletedCalories", n = {"this", "startDate", "endDate", "completedCalories", "this", "startDate", "endDate", "completedCalories"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    /* loaded from: classes2.dex */
    public static final class l extends ContinuationImpl {
        public double D$0;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public Object L$6;
        public int label;
        public /* synthetic */ Object result;

        public l(Continuation<? super l> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ViewModelWorkoutFeedback.this.g(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$getTotalCompletedCalories$3", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {724}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class m extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Ref.DoubleRef $completedCalories;
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public Object L$0;
        public int label;
        public final /* synthetic */ ViewModelWorkoutFeedback this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m(Ref.DoubleRef doubleRef, ViewModelWorkoutFeedback viewModelWorkoutFeedback, String str, String str2, Continuation<? super m> continuation) {
            super(2, continuation);
            this.$completedCalories = doubleRef;
            this.this$0 = viewModelWorkoutFeedback;
            this.$startDate = str;
            this.$endDate = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new m(this.$completedCalories, this.this$0, this.$startDate, this.$endDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((m) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Ref.DoubleRef doubleRef;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.DoubleRef doubleRef2 = this.$completedCalories;
                String str = this.$startDate;
                String str2 = this.$endDate;
                this.L$0 = doubleRef2;
                this.label = 1;
                Object caloriesWithoutActivityMapping = WorkoutSessionRepository.Companion.getInstance(this.this$0.d).getCaloriesWithoutActivityMapping(str, str2, this);
                if (caloriesWithoutActivityMapping == coroutine_suspended) {
                    return coroutine_suspended;
                }
                doubleRef = doubleRef2;
                obj = caloriesWithoutActivityMapping;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                doubleRef = (Ref.DoubleRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            doubleRef.element = ((Number) obj).intValue();
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback", f = "ViewModelWorkoutFeedback.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {741, 747}, m = "getTotalCompletedDistance", n = {"this", "startDate", "endDate", "completedDistance", "this", "startDate", "endDate", "completedDistance"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    /* loaded from: classes2.dex */
    public static final class n extends ContinuationImpl {
        public int I$0;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public Object L$6;
        public int label;
        public /* synthetic */ Object result;

        public n(Continuation<? super n> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ViewModelWorkoutFeedback.this.h(null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$getTotalCompletedDistance$3", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {756}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class o extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Ref.IntRef $completedDistance;
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public Object L$0;
        public int label;
        public final /* synthetic */ ViewModelWorkoutFeedback this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public o(Ref.IntRef intRef, ViewModelWorkoutFeedback viewModelWorkoutFeedback, String str, String str2, Continuation<? super o> continuation) {
            super(2, continuation);
            this.$completedDistance = intRef;
            this.this$0 = viewModelWorkoutFeedback;
            this.$startDate = str;
            this.$endDate = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new o(this.$completedDistance, this.this$0, this.$startDate, this.$endDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((o) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Ref.IntRef intRef;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.IntRef intRef2 = this.$completedDistance;
                String str = this.$startDate;
                String str2 = this.$endDate;
                this.L$0 = intRef2;
                this.label = 1;
                Object distanceWithoutActivityMapping = WorkoutSessionRepository.Companion.getInstance(this.this$0.d).getDistanceWithoutActivityMapping(str, str2, this);
                if (distanceWithoutActivityMapping == coroutine_suspended) {
                    return coroutine_suspended;
                }
                intRef = intRef2;
                obj = distanceWithoutActivityMapping;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                intRef = (Ref.IntRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            intRef.element = ((Number) obj).intValue();
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$sendWorkoutProgressToServerAfterPlanFinished$1", f = "ViewModelWorkoutFeedback.kt", i = {0}, l = {107}, m = "invokeSuspend", n = {"planProgressUpdateReq"}, s = {"L$0"})
    /* loaded from: classes2.dex */
    public static final class p extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ CoveApiListener<PlanProgressUpdateRes, CoveApiErrorModel> $listner;
        public Object L$0;
        public int label;
        public final /* synthetic */ ViewModelWorkoutFeedback this$0;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$sendWorkoutProgressToServerAfterPlanFinished$1$currentPlanAndProgress$1", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {108}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends EntityPreparationPlan, ? extends Float>>, Object> {
            public int label;
            public final /* synthetic */ ViewModelWorkoutFeedback this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ViewModelWorkoutFeedback viewModelWorkoutFeedback, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = viewModelWorkoutFeedback;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends EntityPreparationPlan, ? extends Float>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super Pair<EntityPreparationPlan, Float>>) continuation);
            }

            @Nullable
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Pair<EntityPreparationPlan, Float>> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.this$0;
                    this.label = 1;
                    obj = viewModelWorkoutFeedback.getCurrentPlanAndTotalProgress(this);
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
        public p(CoveApiListener<PlanProgressUpdateRes, CoveApiErrorModel> coveApiListener, ViewModelWorkoutFeedback viewModelWorkoutFeedback, Continuation<? super p> continuation) {
            super(2, continuation);
            this.$listner = coveApiListener;
            this.this$0 = viewModelWorkoutFeedback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new p(this.$listner, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((p) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            PlanProgressUpdateReq planProgressUpdateReq;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    new FitnessPlanProgressReq("", Boxing.boxInt(0), "", Boxing.boxInt(0), Boxing.boxInt(0), Boxing.boxInt(0), null);
                    PlanProgressUpdateReq planProgressUpdateReq2 = new PlanProgressUpdateReq();
                    CoroutineDispatcher io2 = Dispatchers.getIO();
                    a aVar = new a(this.this$0, null);
                    this.L$0 = planProgressUpdateReq2;
                    this.label = 1;
                    Object withContext = BuildersKt.withContext(io2, aVar, this);
                    if (withContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    planProgressUpdateReq = planProgressUpdateReq2;
                    obj = withContext;
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    planProgressUpdateReq = (PlanProgressUpdateReq) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                Pair pair = (Pair) obj;
                planProgressUpdateReq.setUserPlanId(((EntityPreparationPlan) pair.getFirst()).getUserPlanId());
                int floatValue = (int) ((Number) pair.getSecond()).floatValue();
                planProgressUpdateReq.setPercentage(floatValue);
                if (floatValue >= 50) {
                    planProgressUpdateReq.setProgressStatus("FINISHED");
                    CovePreparationPlanApi.updatePlanProgress(planProgressUpdateReq, this.$listner);
                } else {
                    planProgressUpdateReq.setProgressStatus("INCOMPLETE");
                    CovePreparationPlanApi.updatePlanProgress(planProgressUpdateReq, this.$listner);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$submit$1$1", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {53, 56, 58}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class q extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public q(Continuation<? super q> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new q(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((q) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x008e A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L26
                if (r1 == r4) goto L22
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                kotlin.ResultKt.throwOnFailure(r6)
                goto L8f
            L16:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L1e:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L74
            L22:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L49
            L26:
                kotlin.ResultKt.throwOnFailure(r6)
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$Companion r6 = com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.Companion
                com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r1 = com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.this
                android.content.Context r1 = com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.access$getMContext$p(r1)
                java.lang.Object r6 = r6.getInstance(r1)
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r6 = (com.coveiot.android.activitymodes.repository.WorkoutSessionRepository) r6
                com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r1 = com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.this
                java.lang.String r1 = r1.getSessionId()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                r5.label = r4
                java.lang.Object r6 = r6.getSession(r1, r5)
                if (r6 != r0) goto L49
                return r0
            L49:
                com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r6 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r6
                com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r1 = com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.this
                java.lang.String r1 = r1.getSessionMood()
                r6.setMoodaftersession(r1)
                com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r1 = com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.this
                java.lang.String r1 = r1.getSessionPlace()
                r6.setSession_place(r1)
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$Companion r1 = com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.Companion
                com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r4 = com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.this
                android.content.Context r4 = com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.access$getMContext$p(r4)
                java.lang.Object r1 = r1.getInstance(r4)
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r1 = (com.coveiot.android.activitymodes.repository.WorkoutSessionRepository) r1
                r5.label = r3
                java.lang.Object r6 = r1.updateSession(r6, r5)
                if (r6 != r0) goto L74
                return r0
            L74:
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$Companion r6 = com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.Companion
                com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r1 = com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.this
                android.content.Context r1 = com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.access$getMContext$p(r1)
                java.lang.Object r6 = r6.getInstance(r1)
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r6 = (com.coveiot.android.activitymodes.repository.WorkoutSessionRepository) r6
                long r3 = java.lang.System.currentTimeMillis()
                r5.label = r2
                java.lang.Object r6 = r6.saveUnSyncedSessionsToServer(r3, r5)
                if (r6 != r0) goto L8f
                return r0
            L8f:
                com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r6 = com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.this
                r6.sendWorkoutProgressToServer()
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.q.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$updateFitnessPlanProgress$1", f = "ViewModelWorkoutFeedback.kt", i = {0, 0}, l = {Command.CMD_PHONE_NUMBER_PLAY_MODE}, m = "invokeSuspend", n = {"fitnessPlanProgressReq", "scheduleData"}, s = {"L$0", "L$1"})
    /* loaded from: classes2.dex */
    public static final class r extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isProgressUpdate;
        public final /* synthetic */ CoveApiListener<CommonResponseClass, CoveApiErrorModel> $listener;
        public final /* synthetic */ String $planTemplateId;
        public final /* synthetic */ double $totalProgress;
        public Object L$0;
        public Object L$1;
        public int label;

        /* loaded from: classes2.dex */
        public static final class a extends Lambda implements Function1<FitnessPlanTemplateRes.FitnessPlanTemplateData, Unit> {
            public final /* synthetic */ Pair<EntityPreparationPlan, Float> $currentPlanAndProgress;
            public final /* synthetic */ FitnessPlanProgressReq $fitnessPlanProgressReq;
            public final /* synthetic */ boolean $isProgressUpdate;
            public final /* synthetic */ CoveApiListener<CommonResponseClass, CoveApiErrorModel> $listener;
            public final /* synthetic */ FitnessPlanProgressReq.Schedule $scheduleData;
            public final /* synthetic */ double $totalProgress;
            public final /* synthetic */ ViewModelWorkoutFeedback this$0;

            @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$updateFitnessPlanProgress$1$1$1", f = "ViewModelWorkoutFeedback.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, l = {260, DfuException.ERROR_READ_DEVICE_INFO_ERROR, 286, TGEventListener.SLEEP_STOP, TGEventListener.REQUEST_UPDATE_STOCK, TypedValues.CycleType.TYPE_WAVE_PERIOD}, m = "invokeSuspend", n = {"totalCalories", "totalSteps", "distanceCalculated", "totalCalories", "totalSteps", "distanceCalculated", "fitnessPlanDataList", "weekItem", "finalDayList", "totalCalories", "totalSteps", "distanceCalculated", "fitnessPlanDataList", "weekItem", "finalDayList", "dayItem", "distance", "isDistanceConsidered", "activityList", "totalCalories", "totalSteps", "distanceCalculated", "fitnessPlanDataList", "weekItem", "finalDayList", "dayItem", "distance", "isDistanceConsidered", "activityList", "totalCalories", "totalSteps", "distanceCalculated", "fitnessPlanDataList", "weekItem", "finalDayList", "dayItem", "distance", "isDistanceConsidered", "activityList", "calories", "totalCalories", "totalSteps", "distanceCalculated", "fitnessPlanDataList", "weekItem", "finalDayList", "dayItem", "activityList", "activity", "targetAchieved", "distanceValueJob"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$8", "L$9", "L$10", "L$11", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$8", "L$9", "L$10", "L$11", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$8", "L$9", "L$10", "L$11", "I$0", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$8", "L$9", "L$11", "L$12", "L$13"})
            /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$r$a$a  reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public static final class C0246a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ Pair<EntityPreparationPlan, Float> $currentPlanAndProgress;
                public final /* synthetic */ FitnessPlanProgressReq $fitnessPlanProgressReq;
                public final /* synthetic */ boolean $isProgressUpdate;
                public final /* synthetic */ CoveApiListener<CommonResponseClass, CoveApiErrorModel> $listener;
                public final /* synthetic */ FitnessPlanProgressReq.Schedule $scheduleData;
                public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData $template;
                public final /* synthetic */ double $totalProgress;
                public int I$0;
                public Object L$0;
                public Object L$1;
                public Object L$10;
                public Object L$11;
                public Object L$12;
                public Object L$13;
                public Object L$2;
                public Object L$3;
                public Object L$4;
                public Object L$5;
                public Object L$6;
                public Object L$7;
                public Object L$8;
                public Object L$9;
                public int label;
                public final /* synthetic */ ViewModelWorkoutFeedback this$0;

                @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$updateFitnessPlanProgress$1$1$1$distanceValueJob$1", f = "ViewModelWorkoutFeedback.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5}, l = {366, 374, 381, 394, 402, 409}, m = "invokeSuspend", n = {"tempItem", "destination$iv$iv", "code", "tempItem", "destination$iv$iv", "code", "tempItem", "destination$iv$iv", "tempItem", "destination$iv$iv", "sessionType", "tempItem", "destination$iv$iv", "sessionType", "tempItem", "destination$iv$iv"}, s = {"L$1", "L$9", "L$12", "L$1", "L$9", "L$12", "L$1", "L$9", "L$1", "L$9", "L$12", "L$1", "L$9", "L$12", "L$1", "L$9"})
                /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$r$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes2.dex */
                public static final class C0247a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ EntityPreparationActivity $activity;
                    public final /* synthetic */ EntityPreparationDay $dayItem;
                    public final /* synthetic */ Ref.IntRef $distanceCalculated;
                    public final /* synthetic */ Ref.IntRef $targetAchieved;
                    public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData $template;
                    public final /* synthetic */ Ref.IntRef $totalCalories;
                    public final /* synthetic */ Ref.IntRef $totalSteps;
                    public Object L$0;
                    public Object L$1;
                    public Object L$10;
                    public Object L$11;
                    public Object L$12;
                    public Object L$13;
                    public Object L$2;
                    public Object L$3;
                    public Object L$4;
                    public Object L$5;
                    public Object L$6;
                    public Object L$7;
                    public Object L$8;
                    public Object L$9;
                    public int label;
                    public final /* synthetic */ ViewModelWorkoutFeedback this$0;

                    /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$r$a$a$a$a  reason: collision with other inner class name */
                    /* loaded from: classes2.dex */
                    public static final class C0248a extends Lambda implements Function1<Integer, Unit> {
                        public final /* synthetic */ Ref.IntRef $distanceCalculated;
                        public final /* synthetic */ Ref.IntRef $targetAchieved;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C0248a(Ref.IntRef intRef, Ref.IntRef intRef2) {
                            super(1);
                            this.$targetAchieved = intRef;
                            this.$distanceCalculated = intRef2;
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i) {
                            this.$targetAchieved.element += i;
                            this.$distanceCalculated.element += i;
                        }
                    }

                    /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$r$a$a$a$b */
                    /* loaded from: classes2.dex */
                    public static final class b extends Lambda implements Function1<Integer, Unit> {
                        public final /* synthetic */ Ref.IntRef $totalCalories;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public b(Ref.IntRef intRef) {
                            super(1);
                            this.$totalCalories = intRef;
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i) {
                            this.$totalCalories.element += i;
                        }
                    }

                    /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$r$a$a$a$c */
                    /* loaded from: classes2.dex */
                    public static final class c extends Lambda implements Function1<Integer, Unit> {
                        public final /* synthetic */ Ref.IntRef $totalSteps;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public c(Ref.IntRef intRef) {
                            super(1);
                            this.$totalSteps = intRef;
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i) {
                            this.$totalSteps.element += i;
                        }
                    }

                    /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$r$a$a$a$d */
                    /* loaded from: classes2.dex */
                    public static final class d extends Lambda implements Function1<Integer, Unit> {
                        public final /* synthetic */ Ref.IntRef $distanceCalculated;
                        public final /* synthetic */ Ref.IntRef $targetAchieved;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public d(Ref.IntRef intRef, Ref.IntRef intRef2) {
                            super(1);
                            this.$targetAchieved = intRef;
                            this.$distanceCalculated = intRef2;
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i) {
                            this.$targetAchieved.element += i;
                            this.$distanceCalculated.element += i;
                        }
                    }

                    /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$r$a$a$a$e */
                    /* loaded from: classes2.dex */
                    public static final class e extends Lambda implements Function1<Integer, Unit> {
                        public final /* synthetic */ Ref.IntRef $totalCalories;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public e(Ref.IntRef intRef) {
                            super(1);
                            this.$totalCalories = intRef;
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i) {
                            this.$totalCalories.element += i;
                        }
                    }

                    /* renamed from: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$r$a$a$a$f */
                    /* loaded from: classes2.dex */
                    public static final class f extends Lambda implements Function1<Integer, Unit> {
                        public final /* synthetic */ Ref.IntRef $totalSteps;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public f(Ref.IntRef intRef) {
                            super(1);
                            this.$totalSteps = intRef;
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i) {
                            this.$totalSteps.element += i;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0247a(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData, EntityPreparationActivity entityPreparationActivity, ViewModelWorkoutFeedback viewModelWorkoutFeedback, EntityPreparationDay entityPreparationDay, Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Continuation<? super C0247a> continuation) {
                        super(2, continuation);
                        this.$template = fitnessPlanTemplateData;
                        this.$activity = entityPreparationActivity;
                        this.this$0 = viewModelWorkoutFeedback;
                        this.$dayItem = entityPreparationDay;
                        this.$targetAchieved = intRef;
                        this.$distanceCalculated = intRef2;
                        this.$totalCalories = intRef3;
                        this.$totalSteps = intRef4;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new C0247a(this.$template, this.$activity, this.this$0, this.$dayItem, this.$targetAchieved, this.$distanceCalculated, this.$totalCalories, this.$totalSteps, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((C0247a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:20:0x0242  */
                    /* JADX WARN: Removed duplicated region for block: B:23:0x0272  */
                    /* JADX WARN: Removed duplicated region for block: B:30:0x02c0  */
                    /* JADX WARN: Removed duplicated region for block: B:36:0x0349 A[RETURN] */
                    /* JADX WARN: Removed duplicated region for block: B:37:0x034a  */
                    /* JADX WARN: Removed duplicated region for block: B:40:0x0399 A[RETURN] */
                    /* JADX WARN: Removed duplicated region for block: B:41:0x039a  */
                    /* JADX WARN: Removed duplicated region for block: B:43:0x03b0  */
                    /* JADX WARN: Removed duplicated region for block: B:50:0x0407  */
                    /* JADX WARN: Removed duplicated region for block: B:56:0x0493 A[RETURN] */
                    /* JADX WARN: Removed duplicated region for block: B:57:0x0494  */
                    /* JADX WARN: Removed duplicated region for block: B:60:0x04e0 A[RETURN] */
                    /* JADX WARN: Removed duplicated region for block: B:61:0x04e1  */
                    /* JADX WARN: Removed duplicated region for block: B:63:0x04f1  */
                    /* JADX WARN: Removed duplicated region for block: B:67:0x050e  */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0242 -> B:21:0x026c). Please submit an issue!!! */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x03ac -> B:28:0x02ba). Please submit an issue!!! */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x03b0 -> B:44:0x03b4). Please submit an issue!!! */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x03df -> B:48:0x0401). Please submit an issue!!! */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:61:0x04e1 -> B:62:0x04ed). Please submit an issue!!! */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x04fd -> B:66:0x0500). Please submit an issue!!! */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r25) {
                        /*
                            Method dump skipped, instructions count: 1328
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.r.a.C0246a.C0247a.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0246a(ViewModelWorkoutFeedback viewModelWorkoutFeedback, FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData, FitnessPlanProgressReq.Schedule schedule, FitnessPlanProgressReq fitnessPlanProgressReq, double d, Pair<EntityPreparationPlan, Float> pair, boolean z, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener, Continuation<? super C0246a> continuation) {
                    super(2, continuation);
                    this.this$0 = viewModelWorkoutFeedback;
                    this.$template = fitnessPlanTemplateData;
                    this.$scheduleData = schedule;
                    this.$fitnessPlanProgressReq = fitnessPlanProgressReq;
                    this.$totalProgress = d;
                    this.$currentPlanAndProgress = pair;
                    this.$isProgressUpdate = z;
                    this.$listener = coveApiListener;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0246a(this.this$0, this.$template, this.$scheduleData, this.$fitnessPlanProgressReq, this.$totalProgress, this.$currentPlanAndProgress, this.$isProgressUpdate, this.$listener, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0246a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Can't wrap try/catch for region: R(10:27|28|29|30|31|32|33|34|35|(1:37)(12:38|11|12|(5:257|258|259|260|261)(1:14)|15|16|17|18|19|20|21|(8:250|72|73|74|75|58|59|(0)(0))(0))) */
                /* JADX WARN: Can't wrap try/catch for region: R(10:92|93|94|95|96|97|98|99|100|(1:102)(8:103|104|105|106|107|108|109|(1:111)(10:112|113|114|(6:116|(9:119|(3:152|153|(6:155|122|(7:124|125|126|127|128|129|(2:142|143))(1:151)|131|(2:133|134)(1:136)|135))|121|122|(0)(0)|131|(0)(0)|135|117)|160|161|(5:163|164|165|166|167)(1:174)|168)(1:175)|169|170|75|58|59|(7:210|211|212|213|45|46|(8:222|(1:224)|225|(1:227)|228|(1:230)(2:233|(1:235)(1:236))|231|232)(0))(0)))) */
                /* JADX WARN: Can't wrap try/catch for region: R(12:38|11|12|(5:257|258|259|260|261)(1:14)|15|16|17|18|19|20|21|(8:250|72|73|74|75|58|59|(0)(0))(0)) */
                /* JADX WARN: Can't wrap try/catch for region: R(13:55|56|57|58|59|(3:61|(2:205|206)(1:63)|(6:85|86|87|88|89|(1:91)(10:92|93|94|95|96|97|98|99|100|(1:102)(8:103|104|105|106|107|108|109|(1:111)(10:112|113|114|(6:116|(9:119|(3:152|153|(6:155|122|(7:124|125|126|127|128|129|(2:142|143))(1:151)|131|(2:133|134)(1:136)|135))|121|122|(0)(0)|131|(0)(0)|135|117)|160|161|(5:163|164|165|166|167)(1:174)|168)(1:175)|169|170|75|58|59|(7:210|211|212|213|45|46|(8:222|(1:224)|225|(1:227)|228|(1:230)(2:233|(1:235)(1:236))|231|232)(0))(0)))))(4:65|66|67|(5:69|70|20|21|(4:23|(3:242|243|(2:245|(10:27|28|29|30|31|32|33|34|35|(1:37)(12:38|11|12|(5:257|258|259|260|261)(1:14)|15|16|17|18|19|20|21|(8:250|72|73|74|75|58|59|(0)(0))(0)))(5:241|19|20|21|(0)(0))))|25|(0)(0))(0))(8:71|72|73|74|75|58|59|(0)(0))))(0)|82|83|84|44|45|46|(0)(0)) */
                /* JADX WARN: Can't wrap try/catch for region: R(5:(1:257)|258|259|260|261) */
                /* JADX WARN: Can't wrap try/catch for region: R(5:163|164|165|166|167) */
                /* JADX WARN: Can't wrap try/catch for region: R(5:69|70|20|21|(4:23|(3:242|243|(2:245|(10:27|28|29|30|31|32|33|34|35|(1:37)(12:38|11|12|(5:257|258|259|260|261)(1:14)|15|16|17|18|19|20|21|(8:250|72|73|74|75|58|59|(0)(0))(0)))(5:241|19|20|21|(0)(0))))|25|(0)(0))(0)) */
                /* JADX WARN: Can't wrap try/catch for region: R(6:(1:85)|86|87|88|89|(1:91)(10:92|93|94|95|96|97|98|99|100|(1:102)(8:103|104|105|106|107|108|109|(1:111)(10:112|113|114|(6:116|(9:119|(3:152|153|(6:155|122|(7:124|125|126|127|128|129|(2:142|143))(1:151)|131|(2:133|134)(1:136)|135))|121|122|(0)(0)|131|(0)(0)|135|117)|160|161|(5:163|164|165|166|167)(1:174)|168)(1:175)|169|170|75|58|59|(7:210|211|212|213|45|46|(8:222|(1:224)|225|(1:227)|228|(1:230)(2:233|(1:235)(1:236))|231|232)(0))(0))))) */
                /* JADX WARN: Can't wrap try/catch for region: R(8:103|104|105|106|107|108|109|(1:111)(10:112|113|114|(6:116|(9:119|(3:152|153|(6:155|122|(7:124|125|126|127|128|129|(2:142|143))(1:151)|131|(2:133|134)(1:136)|135))|121|122|(0)(0)|131|(0)(0)|135|117)|160|161|(5:163|164|165|166|167)(1:174)|168)(1:175)|169|170|75|58|59|(7:210|211|212|213|45|46|(8:222|(1:224)|225|(1:227)|228|(1:230)(2:233|(1:235)(1:236))|231|232)(0))(0))) */
                /* JADX WARN: Code restructure failed: missing block: B:113:0x03dd, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:114:0x03de, code lost:
                    r3 = r7;
                    r6 = r10;
                    r5 = r17;
                    r7 = r22;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:128:0x0443, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:129:0x0444, code lost:
                    r6 = r36;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:132:0x0449, code lost:
                    r23 = r6;
                    r6 = r5;
                    r5 = r15;
                    r33 = r9;
                    r1 = r0;
                    r0 = r3;
                    r3 = r7;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:133:0x0456, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:134:0x0457, code lost:
                    r3 = r2;
                    r1 = r9;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:135:0x045a, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:136:0x045b, code lost:
                    r1 = r9;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:139:0x0462, code lost:
                    r3 = r2;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:140:0x0463, code lost:
                    r23 = r3;
                    r6 = r5;
                    r3 = r7;
                    r5 = r15;
                    r7 = r1;
                    r1 = r0;
                    r0 = r36;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:141:0x046f, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:142:0x0470, code lost:
                    r23 = r2;
                    r22 = r9;
                    r3 = r36;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:145:0x047e, code lost:
                    r1 = r0;
                    r0 = r6;
                    r6 = r7;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:179:0x0578, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:183:0x057d, code lost:
                    r1 = r0;
                    r0 = r3;
                    r3 = r6;
                    r7 = r8;
                    r23 = r25;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:189:0x0596, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:193:0x059c, code lost:
                    r1 = r0;
                    r0 = r3;
                    r3 = r6;
                    r7 = r8;
                    r23 = r25;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:194:0x05a4, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:195:0x05a5, code lost:
                    r9 = r36;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:196:0x05aa, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:197:0x05ab, code lost:
                    r9 = r36;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:200:0x05c3, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:201:0x05c4, code lost:
                    r9 = r1;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:203:0x05c6, code lost:
                    r1 = r0;
                    r0 = r3;
                    r3 = r6;
                    r23 = r9;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:216:0x0617, code lost:
                    r0 = e;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:217:0x0618, code lost:
                    r23 = r2;
                    r22 = r9;
                 */
                /* JADX WARN: Not initialized variable reg: 10, insn: 0x014e: MOVE  (r6 I:??[OBJECT, ARRAY]) = (r10 I:??[OBJECT, ARRAY]), block:B:25:0x014b */
                /* JADX WARN: Not initialized variable reg: 12, insn: 0x014f: MOVE  (r3 I:??[OBJECT, ARRAY]) = (r12 I:??[OBJECT, ARRAY]), block:B:25:0x014b */
                /* JADX WARN: Not initialized variable reg: 13, insn: 0x0150: MOVE  (r4 I:??[OBJECT, ARRAY]) = (r13 I:??[OBJECT, ARRAY]), block:B:25:0x014b */
                /* JADX WARN: Not initialized variable reg: 15, insn: 0x0151: MOVE  (r5 I:??[OBJECT, ARRAY]) = (r15 I:??[OBJECT, ARRAY]), block:B:25:0x014b */
                /* JADX WARN: Removed duplicated region for block: B:102:0x037e  */
                /* JADX WARN: Removed duplicated region for block: B:105:0x039a A[Catch: Exception -> 0x042a, TryCatch #3 {Exception -> 0x042a, blocks: (B:97:0x0360, B:103:0x0388, B:105:0x039a, B:106:0x03a6, B:107:0x03c3, B:109:0x03d3, B:116:0x03e9, B:118:0x0406), top: B:250:0x0360 }] */
                /* JADX WARN: Removed duplicated region for block: B:117:0x03fc  */
                /* JADX WARN: Removed duplicated region for block: B:153:0x04ac A[Catch: Exception -> 0x05c3, TRY_LEAVE, TryCatch #29 {Exception -> 0x05c3, blocks: (B:151:0x04a6, B:153:0x04ac, B:163:0x04d0), top: B:301:0x04a6 }] */
                /* JADX WARN: Removed duplicated region for block: B:163:0x04d0 A[Catch: Exception -> 0x05c3, TRY_ENTER, TRY_LEAVE, TryCatch #29 {Exception -> 0x05c3, blocks: (B:151:0x04a6, B:153:0x04ac, B:163:0x04d0), top: B:301:0x04a6 }] */
                /* JADX WARN: Removed duplicated region for block: B:184:0x0586  */
                /* JADX WARN: Removed duplicated region for block: B:198:0x05ae  */
                /* JADX WARN: Removed duplicated region for block: B:199:0x05b2  */
                /* JADX WARN: Removed duplicated region for block: B:211:0x05f3  */
                /* JADX WARN: Removed duplicated region for block: B:225:0x0657  */
                /* JADX WARN: Removed duplicated region for block: B:226:0x0660  */
                /* JADX WARN: Removed duplicated region for block: B:234:0x0673  */
                /* JADX WARN: Removed duplicated region for block: B:237:0x06be  */
                /* JADX WARN: Removed duplicated region for block: B:238:0x06cd  */
                /* JADX WARN: Removed duplicated region for block: B:270:0x0210 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:293:0x054d A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:305:0x03a6 A[SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:34:0x01c2  */
                /* JADX WARN: Removed duplicated region for block: B:37:0x01cd  */
                /* JADX WARN: Removed duplicated region for block: B:47:0x021f A[Catch: Exception -> 0x0617, TRY_LEAVE, TryCatch #17 {Exception -> 0x0617, blocks: (B:45:0x0219, B:47:0x021f), top: B:277:0x0219 }] */
                /* JADX WARN: Removed duplicated region for block: B:67:0x02c0 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:68:0x02c1  */
                /* JADX WARN: Removed duplicated region for block: B:74:0x02f8 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:75:0x02f9  */
                /* JADX WARN: Removed duplicated region for block: B:78:0x0311 A[Catch: Exception -> 0x0432, TryCatch #7 {Exception -> 0x0432, blocks: (B:76:0x0300, B:78:0x0311, B:79:0x031c, B:81:0x0322), top: B:258:0x0300 }] */
                /* JADX WARN: Removed duplicated region for block: B:92:0x034b  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:120:0x0426 -> B:277:0x0219). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:127:0x0440 -> B:223:0x0632). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:140:0x0463 -> B:223:0x0632). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:150:0x0498 -> B:301:0x04a6). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:172:0x053e -> B:275:0x0547). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:198:0x05ae -> B:188:0x0592). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:208:0x05eb -> B:223:0x0632). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:213:0x060a -> B:224:0x0653). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:219:0x0622 -> B:223:0x0632). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:222:0x0630 -> B:223:0x0632). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:225:0x0657 -> B:35:0x01c7). Please submit an issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0153 -> B:223:0x0632). Please submit an issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r36) {
                    /*
                        Method dump skipped, instructions count: 1834
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.r.a.C0246a.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ViewModelWorkoutFeedback viewModelWorkoutFeedback, FitnessPlanProgressReq.Schedule schedule, FitnessPlanProgressReq fitnessPlanProgressReq, double d, Pair<EntityPreparationPlan, Float> pair, boolean z, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
                super(1);
                this.this$0 = viewModelWorkoutFeedback;
                this.$scheduleData = schedule;
                this.$fitnessPlanProgressReq = fitnessPlanProgressReq;
                this.$totalProgress = d;
                this.$currentPlanAndProgress = pair;
                this.$isProgressUpdate = z;
                this.$listener = coveApiListener;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
                invoke2(fitnessPlanTemplateData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull FitnessPlanTemplateRes.FitnessPlanTemplateData template) {
                Intrinsics.checkNotNullParameter(template, "template");
                kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this.this$0), Dispatchers.getIO(), null, new C0246a(this.this$0, template, this.$scheduleData, this.$fitnessPlanProgressReq, this.$totalProgress, this.$currentPlanAndProgress, this.$isProgressUpdate, this.$listener, null), 2, null);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$updateFitnessPlanProgress$1$currentPlanAndProgress$1", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {242}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends EntityPreparationPlan, ? extends Float>>, Object> {
            public int label;
            public final /* synthetic */ ViewModelWorkoutFeedback this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(ViewModelWorkoutFeedback viewModelWorkoutFeedback, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = viewModelWorkoutFeedback;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends EntityPreparationPlan, ? extends Float>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super Pair<EntityPreparationPlan, Float>>) continuation);
            }

            @Nullable
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Pair<EntityPreparationPlan, Float>> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.this$0;
                    this.label = 1;
                    obj = viewModelWorkoutFeedback.getCurrentPlanAndTotalProgress(this);
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
        public r(String str, double d, boolean z, CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener, Continuation<? super r> continuation) {
            super(2, continuation);
            this.$planTemplateId = str;
            this.$totalProgress = d;
            this.$isProgressUpdate = z;
            this.$listener = coveApiListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new r(this.$planTemplateId, this.$totalProgress, this.$isProgressUpdate, this.$listener, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((r) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            FitnessPlanProgressReq fitnessPlanProgressReq;
            FitnessPlanProgressReq.Schedule schedule;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FitnessPlanProgressReq fitnessPlanProgressReq2 = new FitnessPlanProgressReq("", Boxing.boxInt(0), "", Boxing.boxInt(0), Boxing.boxInt(0), Boxing.boxInt(0), null);
                    FitnessPlanProgressReq.Schedule schedule2 = new FitnessPlanProgressReq.Schedule(null);
                    CoroutineDispatcher io2 = Dispatchers.getIO();
                    b bVar = new b(ViewModelWorkoutFeedback.this, null);
                    this.L$0 = fitnessPlanProgressReq2;
                    this.L$1 = schedule2;
                    this.label = 1;
                    Object withContext = BuildersKt.withContext(io2, bVar, this);
                    if (withContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    fitnessPlanProgressReq = fitnessPlanProgressReq2;
                    schedule = schedule2;
                    obj = withContext;
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    FitnessPlanProgressReq.Schedule schedule3 = (FitnessPlanProgressReq.Schedule) this.L$1;
                    FitnessPlanProgressReq fitnessPlanProgressReq3 = (FitnessPlanProgressReq) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    schedule = schedule3;
                    fitnessPlanProgressReq = fitnessPlanProgressReq3;
                }
                ViewModelWorkoutFeedback viewModelWorkoutFeedback = ViewModelWorkoutFeedback.this;
                viewModelWorkoutFeedback.getPlanTemplate(this.$planTemplateId, new a(viewModelWorkoutFeedback, schedule, fitnessPlanProgressReq, this.$totalProgress, (Pair) obj, this.$isProgressUpdate, this.$listener));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewModelWorkoutFeedback(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.h = new MutableLiveData<>();
        this.i = new MutableLiveData<>();
        this.d = application;
        this.f = MoodAfterTheSession.TIRED.toString();
        this.g = SessionPlaceType.ROAD.toString();
    }

    public final Object a(String str, String str2, String str3, Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getCaloriesForWeekByType(str, str2, str3, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object b(java.lang.String r5, java.lang.String r6, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.d
            if (r0 == 0) goto L13
            r0 = r8
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$d r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.d) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$d r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$d
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            r7 = r5
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L44
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r4.getTotalCaloriesByActivityType(r5, r6, r0)
            if (r8 != r1) goto L44
            return r1
        L44:
            r7.invoke(r8)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.b(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object c(int i2, Continuation<? super List<EntityPreparationDay>> continuation) {
        PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
        return companion.getInstance(application).getDayLevelInfoWithOutLiveData(i2, continuation);
    }

    public final Object d(String str, Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getSingleDayCalorieWithoutActivityMapping(str, continuation);
    }

    public final void dailyDistanceCalculation(@NotNull EntityPreparationDay dayData) {
        Intrinsics.checkNotNullParameter(dayData, "dayData");
        getPlanTemplate(dayData.getPlan_id(), new a(dayData));
    }

    public final Object e(String str, Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getSingleDayStepWithoutActivityMapping(str, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object f(java.lang.String r5, java.lang.String r6, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.j
            if (r0 == 0) goto L13
            r0 = r8
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$j r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.j) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$j r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$j
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            r7 = r5
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L44
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r4.getTotalStepsByActivityType(r5, r6, r0)
            if (r8 != r1) goto L44
            return r1
        L44:
            r7.invoke(r8)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.f(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01a0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0130 -> B:26:0x00d0). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x013e -> B:21:0x009d). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x014a -> B:35:0x015f). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x018b -> B:41:0x018c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object g(com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes.FitnessPlanTemplateData r22, java.lang.String r23, java.lang.String r24, kotlin.coroutines.Continuation<? super java.lang.Double> r25) {
        /*
            Method dump skipped, instructions count: 453
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.g(com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes$FitnessPlanTemplateData, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void getAllWeekDistanceValueByActivityNCategoryIDs(@NotNull String idString, @NotNull String startDate, @NotNull String endDate, @NotNull Function1<? super Integer, Unit> distanceCoveredCallback) {
        Intrinsics.checkNotNullParameter(idString, "idString");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(distanceCoveredCallback, "distanceCoveredCallback");
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new b(idString, distanceCoveredCallback, this, startDate, endDate, null), 2, null);
    }

    public final void getAllWeeksDistanceValueByActivityType(@NotNull String startDate, @NotNull String endDate, @NotNull String activityType, @NotNull Function1<? super Integer, Unit> distanceCoveredCallback) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(activityType, "activityType");
        Intrinsics.checkNotNullParameter(distanceCoveredCallback, "distanceCoveredCallback");
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new c(distanceCoveredCallback, this, startDate, endDate, activityType, null), 2, null);
    }

    @Nullable
    public final Object getCaloriesForWeekByActivityNCategoryIDs(int i2, int i3, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getCaloriesForWeekByActivityNCategoryIDs(i2, i3, str, str2, continuation);
    }

    public final void getCompletedDistanceCalorie(@NotNull String planTemplateId, @NotNull String startDate, @NotNull String endDate, @NotNull Function1<? super Pair<Integer, Double>, Unit> completedDistanceCallback) {
        Intrinsics.checkNotNullParameter(planTemplateId, "planTemplateId");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(completedDistanceCallback, "completedDistanceCallback");
        getPlanTemplate(planTemplateId, new e(completedDistanceCallback, startDate, endDate));
    }

    @Nullable
    public final Object getCurrentDayPlanData(@NotNull Continuation<? super EntityPreparationDay> continuation) {
        return PreparationPlanRepository.Companion.getInstance(this.d).getDayLevelInfoBasedOnDate(WorkoutUtils.INSTANCE.getCurrentDate(), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00db A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x012f A[LOOP:0: B:40:0x0129->B:42:0x012f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0149  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0120 -> B:39:0x0123). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCurrentPlanAndTotalProgress(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan, java.lang.Float>> r13) {
        /*
            Method dump skipped, instructions count: 349
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.getCurrentPlanAndTotalProgress(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final MutableLiveData<Pair<Integer, Integer>> getDailyDistanceData() {
        return this.i;
    }

    @Nullable
    public final Object getDistanceByActivityNCategoryIDs(@NotNull String str, int i2, int i3, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getDistanceByActivityNCategoryIDs(str, i2, i3, continuation);
    }

    @Nullable
    public final Object getDistanceByActivityType(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getDistanceByType(str, str2, continuation);
    }

    public final Object getDistanceForWeekByActivityNCategoryIDs(int i2, int i3, String str, String str2, Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getDistanceForWeekByActivityNCategoryIDs(i2, i3, str, str2, continuation);
    }

    public final Object getDistanceForWeekByActivityType(String str, String str2, String str3, Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getDistanceForWeekByType(str, str2, str3, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getDistanceValueByActivityNCategoryIDs(@org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.g
            if (r0 == 0) goto L13
            r0 = r14
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$g r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.g) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$g r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$g
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r11 = r0.L$0
            r13 = r11
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto L68
        L2e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L36:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.String r14 = "-"
            java.lang.String[] r5 = new java.lang.String[]{r14}
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            r4 = r11
            java.util.List r11 = kotlin.text.StringsKt__StringsKt.split$default(r4, r5, r6, r7, r8, r9)
            r14 = 0
            java.lang.Object r14 = r11.get(r14)
            java.lang.String r14 = (java.lang.String) r14
            int r14 = java.lang.Integer.parseInt(r14)
            java.lang.Object r11 = r11.get(r3)
            java.lang.String r11 = (java.lang.String) r11
            int r11 = java.lang.Integer.parseInt(r11)
            r0.L$0 = r13
            r0.label = r3
            java.lang.Object r14 = r10.getDistanceByActivityNCategoryIDs(r12, r14, r11, r0)
            if (r14 != r1) goto L68
            return r1
        L68:
            r13.invoke(r14)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.getDistanceValueByActivityNCategoryIDs(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getDistanceValueByActivityType(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.h
            if (r0 == 0) goto L13
            r0 = r8
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$h r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.h) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$h r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$h
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            r7 = r5
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L44
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r4.getDistanceByActivityType(r5, r6, r0)
            if (r8 != r1) goto L44
            return r1
        L44:
            r7.invoke(r8)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.getDistanceValueByActivityType(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final MutableLiveData<Boolean> getFinishActivity() {
        return this.h;
    }

    public final void getPlanTemplate(@NotNull String planTemplateId, @NotNull final Function1<? super FitnessPlanTemplateRes.FitnessPlanTemplateData, Unit> templateCallback) {
        Intrinsics.checkNotNullParameter(planTemplateId, "planTemplateId");
        Intrinsics.checkNotNullParameter(templateCallback, "templateCallback");
        CovePreparationPlanApi.getUserFitnessPlanTemplate(planTemplateId, new CoveApiListener<FitnessPlanTemplateRes.FitnessPlanTemplateData, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$getPlanTemplate$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
                if (fitnessPlanTemplateData != null) {
                    templateCallback.invoke(fitnessPlanTemplateData);
                }
            }
        });
    }

    @Nullable
    public final String getSessionId() {
        return this.e;
    }

    @NotNull
    public final String getSessionMood() {
        return this.f;
    }

    @NotNull
    public final String getSessionPlace() {
        return this.g;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getStepsTotalByActivityNCategoryIDs(@org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.i
            if (r0 == 0) goto L13
            r0 = r14
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$i r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.i) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$i r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$i
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r11 = r0.L$0
            r13 = r11
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto L68
        L2e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L36:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.String r14 = "-"
            java.lang.String[] r5 = new java.lang.String[]{r14}
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            r4 = r11
            java.util.List r11 = kotlin.text.StringsKt__StringsKt.split$default(r4, r5, r6, r7, r8, r9)
            r14 = 0
            java.lang.Object r14 = r11.get(r14)
            java.lang.String r14 = (java.lang.String) r14
            int r14 = java.lang.Integer.parseInt(r14)
            java.lang.Object r11 = r11.get(r3)
            java.lang.String r11 = (java.lang.String) r11
            int r11 = java.lang.Integer.parseInt(r11)
            r0.L$0 = r13
            r0.label = r3
            java.lang.Object r14 = r10.getTotalStepsByActivityNCategoryIDs(r12, r14, r11, r0)
            if (r14 != r1) goto L68
            return r1
        L68:
            r13.invoke(r14)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.getStepsTotalByActivityNCategoryIDs(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object getTotalCalorieByActivityNCategoryIDs(@NotNull String str, int i2, int i3, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getCaloriesByActivityNCategoryIDs(str, i2, i3, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getTotalCaloriesByActivityNCategoryIDs(@org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.k
            if (r0 == 0) goto L13
            r0 = r14
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$k r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.k) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$k r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$k
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r11 = r0.L$0
            r13 = r11
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto L68
        L2e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L36:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.String r14 = "-"
            java.lang.String[] r5 = new java.lang.String[]{r14}
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            r4 = r11
            java.util.List r11 = kotlin.text.StringsKt__StringsKt.split$default(r4, r5, r6, r7, r8, r9)
            r14 = 0
            java.lang.Object r14 = r11.get(r14)
            java.lang.String r14 = (java.lang.String) r14
            int r14 = java.lang.Integer.parseInt(r14)
            java.lang.Object r11 = r11.get(r3)
            java.lang.String r11 = (java.lang.String) r11
            int r11 = java.lang.Integer.parseInt(r11)
            r0.L$0 = r13
            r0.label = r3
            java.lang.Object r14 = r10.getTotalCalorieByActivityNCategoryIDs(r12, r14, r11, r0)
            if (r14 != r1) goto L68
            return r1
        L68:
            r13.invoke(r14)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.getTotalCaloriesByActivityNCategoryIDs(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object getTotalCaloriesByActivityType(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getCaloriesByActivityType(str, str2, continuation);
    }

    @Nullable
    public final Object getTotalStepsByActivityNCategoryIDs(@NotNull String str, int i2, int i3, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getStepsByActivityNCategoryIDs(str, i2, i3, continuation);
    }

    @Nullable
    public final Object getTotalStepsByActivityType(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getStepsByActivityType(str, str2, continuation);
    }

    @Nullable
    public final Object getWeekFitnessPlanInfo(@NotNull Continuation<? super List<EntityPreparationWeek>> continuation) {
        PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
        return companion.getInstance(application).getWeekLevelInfoWithoutLiveData(continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01af  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0136 -> B:26:0x00d3). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0144 -> B:21:0x009d). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0153 -> B:35:0x016b). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0197 -> B:41:0x0198). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object h(com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes.FitnessPlanTemplateData r22, java.lang.String r23, java.lang.String r24, kotlin.coroutines.Continuation<? super java.lang.Integer> r25) {
        /*
            Method dump skipped, instructions count: 468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback.h(com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes$FitnessPlanTemplateData, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void sendWorkoutProgressToServer() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new ViewModelWorkoutFeedback$sendWorkoutProgressToServer$1(this, null), 2, null);
    }

    public final void sendWorkoutProgressToServerAfterPlanFinished(@NotNull CoveApiListener<PlanProgressUpdateRes, CoveApiErrorModel> listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new p(listner, this, null), 2, null);
    }

    public final void setDailyDistanceData(@NotNull MutableLiveData<Pair<Integer, Integer>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.i = mutableLiveData;
    }

    public final void setFinishActivity(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }

    public final void setSessionId(@Nullable String str) {
        this.e = str;
    }

    public final void setSessionMood(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setSessionPlace(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.g = str;
    }

    public final void submit() {
        if (this.e != null) {
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new q(null), 3, null);
            this.h.setValue(Boolean.TRUE);
        }
    }

    public final void updateFitnessPlanProgress(boolean z, double d2, @NotNull ArrayList<String> dayRange, @NotNull String planTemplateId, @NotNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> listener) {
        Intrinsics.checkNotNullParameter(dayRange, "dayRange");
        Intrinsics.checkNotNullParameter(planTemplateId, "planTemplateId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new r(planTemplateId, d2, z, listener, null), 2, null);
    }
}
