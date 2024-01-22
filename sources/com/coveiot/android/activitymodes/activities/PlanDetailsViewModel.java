package com.coveiot.android.activitymodes.activities;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.models.CurrentWeekAndDay;
import com.coveiot.android.activitymodes.models.DayInfo;
import com.coveiot.android.activitymodes.models.FitnessPlanData;
import com.coveiot.android.activitymodes.models.Images;
import com.coveiot.android.activitymodes.models.PlanHistory;
import com.coveiot.android.activitymodes.models.PlanTemplate;
import com.coveiot.android.activitymodes.models.WeekInfo;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.SingleLiveEvent;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CovePreparationPlanApi;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.FitnessPlanHistoryRes;
import com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes;
import com.coveiot.coveaccess.model.server.UserPlanInfoRes;
import com.coveiot.utils.utility.AppUtils;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PlanDetailsViewModel extends AndroidViewModel {
    @NotNull
    public final SingleLiveEvent<List<WeekInfo>> d;
    @NotNull
    public final SingleLiveEvent<FitnessPlanData> e;
    @NotNull
    public final SingleLiveEvent<CurrentWeekAndDay> f;
    @NotNull
    public final SingleLiveEvent<List<PlanHistory>> g;
    @NotNull
    public Context h;

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$getCurrentPlan$2", f = "PlanDetailsViewModel.kt", i = {}, l = {65}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
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
                PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                Application application = PlanDetailsViewModel.this.getApplication();
                Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
                this.label = 1;
                obj = companion.getInstance(application).getCurrentPlan(this);
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

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$getDayLevelInfo$2", f = "PlanDetailsViewModel.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LiveData<List<? extends EntityPreparationDay>>>, Object> {
        public final /* synthetic */ int $weekNumber;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(int i, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$weekNumber = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$weekNumber, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super LiveData<List<? extends EntityPreparationDay>>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super LiveData<List<EntityPreparationDay>>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super LiveData<List<EntityPreparationDay>>> continuation) {
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
                Application application = PlanDetailsViewModel.this.getApplication();
                Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
                int i2 = this.$weekNumber;
                this.label = 1;
                obj = companion.getInstance(application).getDayLevelInfo(i2, this);
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

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.PlanDetailsViewModel", f = "PlanDetailsViewModel.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}, l = {402, com.veryfit.multi.nativeprotocol.b.B1, 409, 415}, m = "getDistanceValueByOneKActivityCheck", n = {"this", WeatherCriteria.UNIT_TYPE_DAY, "activityType", "distanceCoveredCallback", "distance", "this", WeatherCriteria.UNIT_TYPE_DAY, "activityType", "distanceCoveredCallback", "it", "distance", "this", WeatherCriteria.UNIT_TYPE_DAY, "activityType", "distanceCoveredCallback", "it", "distance", "this", WeatherCriteria.UNIT_TYPE_DAY, "activityType", "distanceCoveredCallback", "it", "distance"}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6"})
    /* loaded from: classes2.dex */
    public static final class c extends ContinuationImpl {
        public int I$0;
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
        public /* synthetic */ Object result;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlanDetailsViewModel.this.getDistanceValueByOneKActivityCheck(null, null, null, null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.PlanDetailsViewModel", f = "PlanDetailsViewModel.kt", i = {0}, l = {435}, m = "getDistanceValueWithoutMapping", n = {"distanceCoveredCallback"}, s = {"L$0"})
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
            return PlanDetailsViewModel.this.getDistanceValueWithoutMapping(null, null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$getPlanEndDate$2", f = "PlanDetailsViewModel.kt", i = {}, l = {69, 74}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        public int label;

        public e(Continuation<? super e> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super String> continuation) {
            return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                Application application = PlanDetailsViewModel.this.getApplication();
                Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
                this.label = 1;
                obj = companion.getInstance(application).getCurrentPlan(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return AppUtils.formatDate(AppUtils.parseDate((String) obj, "yyyy-MM-dd"), "dd MMM yyyy");
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            EntityPreparationPlan entityPreparationPlan = (EntityPreparationPlan) obj;
            if (entityPreparationPlan != null) {
                PreparationPlanRepository.Companion companion2 = PreparationPlanRepository.Companion;
                Application application2 = PlanDetailsViewModel.this.getApplication();
                Intrinsics.checkNotNullExpressionValue(application2, "getApplication()");
                String planId = entityPreparationPlan.getPlanId();
                this.label = 2;
                obj = companion2.getInstance(application2).getPlanEndDate(planId, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return AppUtils.formatDate(AppUtils.parseDate((String) obj, "yyyy-MM-dd"), "dd MMM yyyy");
            }
            return null;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$getWeekPlanInfo$2", f = "PlanDetailsViewModel.kt", i = {}, l = {44}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LiveData<List<? extends EntityPreparationWeek>>>, Object> {
        public int label;

        public f(Continuation<? super f> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super LiveData<List<? extends EntityPreparationWeek>>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super LiveData<List<EntityPreparationWeek>>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super LiveData<List<EntityPreparationWeek>>> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                Application application = PlanDetailsViewModel.this.getApplication();
                Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
                this.label = 1;
                obj = companion.getInstance(application).getWeekLevelInfo(this);
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

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$getWeeksInFo$1", f = "PlanDetailsViewModel.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ LifecycleOwner $lifecycleOwner;
        public int label;

        /* loaded from: classes2.dex */
        public static final class a extends Lambda implements Function1<List<? extends EntityPreparationWeek>, Unit> {
            public final /* synthetic */ PlanDetailsViewModel this$0;

            @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$getWeeksInFo$1$1$1", f = "PlanDetailsViewModel.kt", i = {0, 0, 0, 0}, l = {102}, m = "invokeSuspend", n = {"weekList", "wInfo", "finalDayList", "isCurrentWeekAndDayFoundAndPosted"}, s = {"L$0", "L$2", "L$3", "I$0"})
            /* renamed from: com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$g$a$a  reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public static final class C0236a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ CurrentWeekAndDay $currentWeekAndDay;
                public final /* synthetic */ List<EntityPreparationWeek> $weekInfo;
                public int I$0;
                public Object L$0;
                public Object L$1;
                public Object L$2;
                public Object L$3;
                public int label;
                public final /* synthetic */ PlanDetailsViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0236a(List<EntityPreparationWeek> list, PlanDetailsViewModel planDetailsViewModel, CurrentWeekAndDay currentWeekAndDay, Continuation<? super C0236a> continuation) {
                    super(2, continuation);
                    this.$weekInfo = list;
                    this.this$0 = planDetailsViewModel;
                    this.$currentWeekAndDay = currentWeekAndDay;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0236a(this.$weekInfo, this.this$0, this.$currentWeekAndDay, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0236a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:13:0x004d  */
                /* JADX WARN: Removed duplicated region for block: B:45:0x016c  */
                /* JADX WARN: Removed duplicated region for block: B:49:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0071 -> B:17:0x0079). Please submit an issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r25) {
                    /*
                        Method dump skipped, instructions count: 379
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activities.PlanDetailsViewModel.g.a.C0236a.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(PlanDetailsViewModel planDetailsViewModel) {
                super(1);
                this.this$0 = planDetailsViewModel;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends EntityPreparationWeek> list) {
                invoke2((List<EntityPreparationWeek>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<EntityPreparationWeek> list) {
                kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this.this$0), Dispatchers.getMain(), null, new C0236a(list, this.this$0, new CurrentWeekAndDay(0, 0, ""), null), 2, null);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(LifecycleOwner lifecycleOwner, Continuation<? super g> continuation) {
            super(2, continuation);
            this.$lifecycleOwner = lifecycleOwner;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g(this.$lifecycleOwner, continuation);
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
                PlanDetailsViewModel planDetailsViewModel = PlanDetailsViewModel.this;
                this.label = 1;
                obj = planDetailsViewModel.getWeekFitnessPlanInfo(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            LifecycleOwner lifecycleOwner = this.$lifecycleOwner;
            final a aVar = new a(PlanDetailsViewModel.this);
            ((LiveData) obj).observe(lifecycleOwner, new Observer() { // from class: com.coveiot.android.activitymodes.activities.v2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj2) {
                    Function1.this.invoke(obj2);
                }
            });
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlanDetailsViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = new SingleLiveEvent<>();
        this.e = new SingleLiveEvent<>();
        this.f = new SingleLiveEvent<>();
        this.g = new SingleLiveEvent<>();
        this.h = application;
    }

    public final Object a(int i, Continuation<? super List<EntityPreparationDay>> continuation) {
        PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
        return companion.getInstance(application).getDayLevelInfoWithOutLiveData(i, continuation);
    }

    @Nullable
    public final Object getCurrentDayPlanData(@NotNull Continuation<? super EntityPreparationDay> continuation) {
        return PreparationPlanRepository.Companion.getInstance(this.h).getDayLevelInfoBasedOnDate(WorkoutUtils.INSTANCE.getCurrentDate(), continuation);
    }

    @Nullable
    public final Object getCurrentPlan(@NotNull Continuation<? super EntityPreparationPlan> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain(), new a(null), continuation);
    }

    @NotNull
    public final SingleLiveEvent<CurrentWeekAndDay> getCurrentWeekAndDayValue() {
        return this.f;
    }

    @Nullable
    public final Object getDayFitnessPlanInfo(int i, @NotNull Continuation<? super LiveData<List<EntityPreparationDay>>> continuation) {
        PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
        return companion.getInstance(application).getDayLevelInfo(i, continuation);
    }

    @Nullable
    public final Object getDayLevelInfo(int i, @NotNull Continuation<? super LiveData<List<EntityPreparationDay>>> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain(), new b(i, null), continuation);
    }

    @Nullable
    public final Object getDistanceByActivityNCategoryIDs(@NotNull String str, int i, int i2, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.h).getDistanceByActivityNCategoryIDs(str, i, i2, continuation);
    }

    @Nullable
    public final Object getDistanceByActivityType(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.h).getDistanceByType(str, str2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02bf  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x019e -> B:33:0x0140). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x01ba -> B:72:0x02c8). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x0212 -> B:55:0x0223). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x023f -> B:60:0x0253). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x0286 -> B:66:0x028d). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:68:0x02b0 -> B:69:0x02b1). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x02bf -> B:72:0x02c8). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getDistanceValueByOneKActivityCheck(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes.FitnessPlanTemplateData r25, @org.jetbrains.annotations.NotNull java.lang.String r26, @org.jetbrains.annotations.NotNull java.lang.String r27, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r28, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            Method dump skipped, instructions count: 718
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activities.PlanDetailsViewModel.getDistanceValueByOneKActivityCheck(com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes$FitnessPlanTemplateData, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0071  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getDistanceValueWithoutMapping(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.coveiot.android.activitymodes.activities.PlanDetailsViewModel.d
            if (r0 == 0) goto L13
            r0 = r7
            com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$d r0 = (com.coveiot.android.activitymodes.activities.PlanDetailsViewModel.d) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$d r0 = new com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$d
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            r6 = r5
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4e
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r7)
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$Companion r7 = com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.Companion
            android.content.Context r2 = r4.h
            java.lang.Object r7 = r7.getInstance(r2)
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r7 = (com.coveiot.android.activitymodes.repository.WorkoutSessionRepository) r7
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = r7.getSessionsOfParticularDay(r5, r0)
            if (r7 != r1) goto L4e
            return r1
        L4e:
            java.util.List r7 = (java.util.List) r7
            r5 = 0
            if (r7 == 0) goto L71
            java.util.Iterator r7 = r7.iterator()
        L57:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L69
            java.lang.Object r0 = r7.next()
            com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r0 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r0
            int r0 = r0.getTotal_distance()
            int r5 = r5 + r0
            goto L57
        L69:
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            r6.invoke(r5)
            goto L78
        L71:
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            r6.invoke(r5)
        L78:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activities.PlanDetailsViewModel.getDistanceValueWithoutMapping(java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void getFitnessHistoryPlan() {
        CovePreparationPlanApi.getFitnessPlanHistory(new CoveApiListener<FitnessPlanHistoryRes.FitnessPlanHistoryData, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$getFitnessHistoryPlan$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                PlanDetailsViewModel.this.getFitnessPlanHistory().postValue(null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable FitnessPlanHistoryRes.FitnessPlanHistoryData fitnessPlanHistoryData) {
                ArrayList arrayList = new ArrayList();
                if (fitnessPlanHistoryData != null) {
                    for (FitnessPlanHistoryRes.FitnessPlanHistoryData.PlansHistory plansHistory : fitnessPlanHistoryData.getPlansHistory()) {
                        PlanHistory planHistory = new PlanHistory("", "", "", "", "", "", 0, "", 0, 0, 0, "", "", null);
                        String userPlanId = plansHistory.getUserPlanId();
                        Intrinsics.checkNotNullExpressionValue(userPlanId, "item.userPlanId");
                        planHistory.setUserPlanId(userPlanId);
                        String activationDate = plansHistory.getActivationDate();
                        Intrinsics.checkNotNullExpressionValue(activationDate, "item.activationDate");
                        planHistory.setActivationDate(activationDate);
                        if (plansHistory.getDeactivationDate() != null) {
                            String deactivationDate = plansHistory.getDeactivationDate();
                            Intrinsics.checkNotNullExpressionValue(deactivationDate, "item.deactivationDate");
                            planHistory.setDeactivationDate(deactivationDate);
                        }
                        String endDate = plansHistory.getEndDate();
                        Intrinsics.checkNotNullExpressionValue(endDate, "item.endDate");
                        planHistory.setEndDate(endDate);
                        String formatDate = AppUtils.formatDate(AppUtils.parseDate(plansHistory.getActivationDate(), "yyyy-MM-dd"), "dd MMM");
                        String formatDate2 = AppUtils.formatDate(AppUtils.parseDate(plansHistory.getEndDate(), "yyyy-MM-dd"), "dd MMM yyyy");
                        planHistory.setStartAndEndDate(formatDate + " - " + formatDate2);
                        String categoryChosen = plansHistory.getCategoryChosen();
                        Intrinsics.checkNotNullExpressionValue(categoryChosen, "item.categoryChosen");
                        planHistory.setCategoryChosen(categoryChosen);
                        Integer progress = plansHistory.getProgress();
                        Intrinsics.checkNotNullExpressionValue(progress, "item.progress");
                        planHistory.setProgress(progress.intValue());
                        if (plansHistory.getProgressStatus() != null) {
                            String progressStatus = plansHistory.getProgressStatus();
                            Intrinsics.checkNotNullExpressionValue(progressStatus, "item.progressStatus");
                            planHistory.setProgressStatus(progressStatus);
                        }
                        Integer totalCalories = plansHistory.getTotalCalories();
                        Intrinsics.checkNotNullExpressionValue(totalCalories, "item.totalCalories");
                        planHistory.setTotalCalories(totalCalories.intValue());
                        Integer totalDistance = plansHistory.getTotalDistance();
                        Intrinsics.checkNotNullExpressionValue(totalDistance, "item.totalDistance");
                        planHistory.setTotalDistance(totalDistance.intValue());
                        Integer totalSteps = plansHistory.getTotalSteps();
                        Intrinsics.checkNotNullExpressionValue(totalSteps, "item.totalSteps");
                        planHistory.setTotalSteps(totalSteps.intValue());
                        String createdDate = plansHistory.getCreatedDate();
                        Intrinsics.checkNotNullExpressionValue(createdDate, "item.createdDate");
                        planHistory.setCreatedDate(createdDate);
                        String lastModifiedDate = plansHistory.getLastModifiedDate();
                        Intrinsics.checkNotNullExpressionValue(lastModifiedDate, "item.lastModifiedDate");
                        planHistory.setLastModifiedDate(lastModifiedDate);
                        PlanTemplate planTemplate = new PlanTemplate("", 0, "", "", "", "", "", null, "");
                        String planTemplateId = plansHistory.getPlanTemplate().getPlanTemplateId();
                        Intrinsics.checkNotNullExpressionValue(planTemplateId, "item.planTemplate.planTemplateId");
                        planTemplate.setPlanTemplateId(planTemplateId);
                        Integer sortIndex = plansHistory.getPlanTemplate().getSortIndex();
                        Intrinsics.checkNotNullExpressionValue(sortIndex, "item.planTemplate.sortIndex");
                        planTemplate.setSortIndex(sortIndex.intValue());
                        String categoryId = plansHistory.getPlanTemplate().getCategoryId();
                        Intrinsics.checkNotNullExpressionValue(categoryId, "item.planTemplate.categoryId");
                        planTemplate.setCategoryId(categoryId);
                        String shortTitle = plansHistory.getPlanTemplate().getShortTitle();
                        Intrinsics.checkNotNullExpressionValue(shortTitle, "item.planTemplate.shortTitle");
                        planTemplate.setShortTitle(shortTitle);
                        String fullTitle = plansHistory.getPlanTemplate().getFullTitle();
                        Intrinsics.checkNotNullExpressionValue(fullTitle, "item.planTemplate.fullTitle");
                        planTemplate.setFullTitle(fullTitle);
                        String subTitle = plansHistory.getPlanTemplate().getSubTitle();
                        Intrinsics.checkNotNullExpressionValue(subTitle, "item.planTemplate.subTitle");
                        planTemplate.setSubTitle(subTitle);
                        String shortDesc = plansHistory.getPlanTemplate().getShortDesc();
                        Intrinsics.checkNotNullExpressionValue(shortDesc, "item.planTemplate.shortDesc");
                        planTemplate.setShortDesc(shortDesc);
                        String categoryName = plansHistory.getPlanTemplate().getCategoryName();
                        Intrinsics.checkNotNullExpressionValue(categoryName, "item.planTemplate.categoryName");
                        planTemplate.setCategoryName(categoryName);
                        Images images = new Images("", "");
                        if (plansHistory.getPlanTemplate().getImages().getBackground1() != null) {
                            images.setBackground1(plansHistory.getPlanTemplate().getImages().getBackground1());
                        }
                        if (plansHistory.getPlanTemplate().getImages().getThumbnail1() != null) {
                            images.setThumbnail1(plansHistory.getPlanTemplate().getImages().getThumbnail1());
                        }
                        planTemplate.setImages(images);
                        planHistory.setPlanTemplate(planTemplate);
                        arrayList.add(planHistory);
                    }
                    PlanDetailsViewModel.this.getFitnessPlanHistory().postValue(arrayList);
                    return;
                }
                PlanDetailsViewModel.this.getFitnessPlanHistory().postValue(null);
            }
        });
    }

    @NotNull
    public final SingleLiveEvent<FitnessPlanData> getFitnessPlanData() {
        return this.e;
    }

    @NotNull
    public final SingleLiveEvent<List<PlanHistory>> getFitnessPlanHistory() {
        return this.g;
    }

    @Nullable
    public final Object getPlanEndDate(@NotNull Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain(), new e(null), continuation);
    }

    public final void getPlanTemplate(@NotNull String planTemplateId, @NotNull final Function1<? super FitnessPlanTemplateRes.FitnessPlanTemplateData, Unit> templateCallback) {
        Intrinsics.checkNotNullParameter(planTemplateId, "planTemplateId");
        Intrinsics.checkNotNullParameter(templateCallback, "templateCallback");
        CovePreparationPlanApi.getUserFitnessPlanTemplate(planTemplateId, new CoveApiListener<FitnessPlanTemplateRes.FitnessPlanTemplateData, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$getPlanTemplate$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                templateCallback.invoke(null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
                if (fitnessPlanTemplateData != null) {
                    templateCallback.invoke(fitnessPlanTemplateData);
                }
            }
        });
    }

    public final void getUserFitnessPlanInfo(@NotNull String userPlanId) {
        Intrinsics.checkNotNullParameter(userPlanId, "userPlanId");
        CovePreparationPlanApi.getUserFitnessPlanInfo(userPlanId, new CoveApiListener<UserPlanInfoRes.UserFitnessPlanData, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.activities.PlanDetailsViewModel$getUserFitnessPlanInfo$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                PlanDetailsViewModel.this.getFitnessPlanData().postValue(null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable UserPlanInfoRes.UserFitnessPlanData userFitnessPlanData) {
                int i;
                PlanDetailsViewModel$getUserFitnessPlanInfo$1 planDetailsViewModel$getUserFitnessPlanInfo$1;
                int i2;
                int i3;
                int i4;
                int i5;
                int i6;
                Iterator<UserPlanInfoRes.UserFitnessPlanData.Schedule.Week> it;
                ArrayList arrayList;
                Integer num;
                Iterator<UserPlanInfoRes.UserFitnessPlanData.Schedule.Week> it2;
                ArrayList arrayList2;
                Iterator<UserPlanInfoRes.UserFitnessPlanData.Schedule.Week.Day.Activity> it3;
                ArrayList arrayList3 = new ArrayList();
                if (userFitnessPlanData != null) {
                    if (userFitnessPlanData.getSchedule() != null) {
                        Iterator<UserPlanInfoRes.UserFitnessPlanData.Schedule.Week> it4 = userFitnessPlanData.getSchedule().getWeeks().iterator();
                        i2 = 0;
                        i3 = 0;
                        int i7 = 0;
                        int i8 = 0;
                        int i9 = 0;
                        while (it4.hasNext()) {
                            UserPlanInfoRes.UserFitnessPlanData.Schedule.Week next = it4.next();
                            ArrayList arrayList4 = new ArrayList();
                            for (UserPlanInfoRes.UserFitnessPlanData.Schedule.Week.Day day : next.getDays()) {
                                ArrayList arrayList5 = new ArrayList();
                                if (day.getActivities() != null) {
                                    Iterator<UserPlanInfoRes.UserFitnessPlanData.Schedule.Week.Day.Activity> it5 = day.getActivities().iterator();
                                    while (it5.hasNext()) {
                                        UserPlanInfoRes.UserFitnessPlanData.Schedule.Week.Day.Activity next2 = it5.next();
                                        if (next2.getTarget() != null) {
                                            String target = next2.getTarget();
                                            it2 = it4;
                                            Intrinsics.checkNotNullExpressionValue(target, "activity.target");
                                            if (Integer.parseInt(target) != 0) {
                                                i7++;
                                            }
                                            String targetAchieved = next2.getTargetAchieved();
                                            if (targetAchieved == null || targetAchieved.length() == 0) {
                                                arrayList2 = arrayList3;
                                                it3 = it5;
                                            } else {
                                                arrayList2 = arrayList3;
                                                it3 = it5;
                                                int parseDouble = (int) Double.parseDouble(next2.getTargetAchieved().toString());
                                                i9 += parseDouble;
                                                if (parseDouble != 0) {
                                                    String target2 = next2.getTarget();
                                                    Intrinsics.checkNotNullExpressionValue(target2, "activity.target");
                                                    if (parseDouble >= Integer.parseInt(target2)) {
                                                        i8++;
                                                    }
                                                }
                                            }
                                            String target3 = next2.getTarget();
                                            Intrinsics.checkNotNullExpressionValue(target3, "activity.target");
                                            i3 += Integer.parseInt(target3);
                                        } else {
                                            it2 = it4;
                                            arrayList2 = arrayList3;
                                            it3 = it5;
                                        }
                                        arrayList5.add(new com.coveiot.android.activitymodes.models.ActivityInfo(next2.getActivityType(), next2.getTitle(), next2.getActivityCode(), next2.getTarget(), next2.getTargetAchieved(), next2.getActivityBaseUnit()));
                                        it5 = it3;
                                        it4 = it2;
                                        arrayList3 = arrayList2;
                                    }
                                    it = it4;
                                    arrayList = arrayList3;
                                    Integer dayNumber = day.getDayNumber();
                                    Intrinsics.checkNotNullExpressionValue(dayNumber, "day.dayNumber");
                                    arrayList4.add(new DayInfo(dayNumber.intValue(), day.getDate(), "", arrayList5));
                                    if (userFitnessPlanData.getActivitiesMapped() != null && !userFitnessPlanData.getActivitiesMapped().booleanValue()) {
                                        if (day.getTotalDistance() != null) {
                                            num = day.getTotalDistance();
                                        } else {
                                            num = 0;
                                        }
                                        Intrinsics.checkNotNullExpressionValue(num, "if (day.totalDistance !=…                        }");
                                        i2 += num.intValue();
                                    }
                                } else {
                                    it = it4;
                                    arrayList = arrayList3;
                                    arrayList5.add(new com.coveiot.android.activitymodes.models.ActivityInfo("", "", "", BleConst.GetDeviceTime, BleConst.GetDeviceTime, ""));
                                    Integer dayNumber2 = day.getDayNumber();
                                    Intrinsics.checkNotNullExpressionValue(dayNumber2, "day.dayNumber");
                                    arrayList4.add(new DayInfo(dayNumber2.intValue(), day.getDate(), "", arrayList5));
                                }
                                it4 = it;
                                arrayList3 = arrayList;
                            }
                            Integer weekNumber = next.getWeekNumber();
                            Intrinsics.checkNotNullExpressionValue(weekNumber, "week.weekNumber");
                            arrayList3.add(new WeekInfo(weekNumber.intValue(), "", "", null, arrayList4));
                            it4 = it4;
                        }
                        i = 0;
                        planDetailsViewModel$getUserFitnessPlanInfo$1 = this;
                        i4 = i7;
                        i5 = i8;
                        i6 = i9;
                    } else {
                        i = 0;
                        planDetailsViewModel$getUserFitnessPlanInfo$1 = this;
                        i2 = 0;
                        i3 = 0;
                        i4 = 0;
                        i5 = 0;
                        i6 = 0;
                    }
                    SingleLiveEvent<FitnessPlanData> fitnessPlanData = PlanDetailsViewModel.this.getFitnessPlanData();
                    Boolean activitiesMapped = userFitnessPlanData.getActivitiesMapped();
                    Intrinsics.checkNotNullExpressionValue(activitiesMapped, "planData.activitiesMapped");
                    boolean booleanValue = activitiesMapped.booleanValue();
                    Integer progress = userFitnessPlanData.getProgress();
                    int intValue = progress == null ? i : progress.intValue();
                    Integer progress2 = userFitnessPlanData.getProgress();
                    if (progress2 != null) {
                        i = progress2.intValue();
                    }
                    int i10 = i;
                    Double totalCalories = userFitnessPlanData.getTotalCalories();
                    Intrinsics.checkNotNullExpressionValue(totalCalories, "planData.totalCalories");
                    double doubleValue = totalCalories.doubleValue();
                    Boolean activitiesMapped2 = userFitnessPlanData.getActivitiesMapped();
                    Intrinsics.checkNotNullExpressionValue(activitiesMapped2, "planData.activitiesMapped");
                    int i11 = activitiesMapped2.booleanValue() ? i3 : i2;
                    Double totalDistance = userFitnessPlanData.getTotalDistance();
                    Intrinsics.checkNotNullExpressionValue(totalDistance, "planData.totalDistance");
                    double doubleValue2 = totalDistance.doubleValue();
                    String categoryChosen = userFitnessPlanData.getCategoryChosen();
                    Intrinsics.checkNotNullExpressionValue(categoryChosen, "planData.categoryChosen");
                    String planTemplateId = userFitnessPlanData.getPlanTemplateId();
                    Intrinsics.checkNotNullExpressionValue(planTemplateId, "planData.planTemplateId");
                    fitnessPlanData.postValue(new FitnessPlanData("", booleanValue, intValue, i10, doubleValue, i11, i4, doubleValue2, i5, i6, categoryChosen, true, "", planTemplateId, arrayList3));
                }
            }
        });
    }

    @Nullable
    public final Object getWeekFitnessPlanInfo(@NotNull Continuation<? super LiveData<List<EntityPreparationWeek>>> continuation) {
        PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
        return companion.getInstance(application).getWeekLevelInfo(continuation);
    }

    @NotNull
    public final SingleLiveEvent<List<WeekInfo>> getWeekListMutableList() {
        return this.d;
    }

    @Nullable
    public final Object getWeekPlanInfo(@NotNull Continuation<? super LiveData<List<EntityPreparationWeek>>> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain(), new f(null), continuation);
    }

    public final void getWeeksInFo(@NotNull LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new g(lifecycleOwner, null), 2, null);
    }
}
