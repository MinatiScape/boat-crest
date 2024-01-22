package com.coveiot.android.activitymodes.repository;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import com.coveiot.android.activitymodes.SingletonHolder;
import com.coveiot.android.activitymodes.database.WorkoutSessionDatabase;
import com.coveiot.android.activitymodes.database.dao.PreparationPlanDao;
import com.coveiot.android.activitymodes.database.entities.EntityPlanSchedule;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CovePreparationPlanApi;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SCurrentPreparationPlanRes;
import com.google.mlkit.common.MlKitException;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PreparationPlanRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2858a;
    @NotNull
    public final String b;
    @NotNull
    public PreparationPlanDao c;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<PreparationPlanRepository, Context> {

        /* loaded from: classes2.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, PreparationPlanRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, PreparationPlanRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final PreparationPlanRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new PreparationPlanRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public interface OptoutListner {
        void onFailure(@NotNull String str);

        void onPlanOptout();
    }

    /* loaded from: classes2.dex */
    public interface PlanDetailsListner {
        void onFailure(@NotNull String str);

        void onPlanFetchedSuccessfully();
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$deletePlan$2", f = "PreparationPlanRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                PreparationPlanRepository.this.c.deletePlan();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$doesPlanExists$2", f = "PreparationPlanRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
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
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Boolean> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(PreparationPlanRepository.this.c.rowCountPlan() > 0);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository", f = "PreparationPlanRepository.kt", i = {}, l = {187}, m = "getCurrentPlan", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class c extends ContinuationImpl {
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
            return PreparationPlanRepository.this.getCurrentPlan(this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getCurrentPlan$entityPreparationPlan$1", f = "PreparationPlanRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
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
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationPlan> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return PreparationPlanRepository.this.c.getCurrentPlanInfo();
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getCurrentPlanInfo$2", f = "PreparationPlanRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
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
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationPlan> continuation) {
            return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return PreparationPlanRepository.this.c.getCurrentPlanInfo();
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository", f = "PreparationPlanRepository.kt", i = {0, 0}, l = {129}, m = "getDayLevelInfo", n = {"this", "weekNumber"}, s = {"L$0", "I$0"})
    /* loaded from: classes2.dex */
    public static final class f extends ContinuationImpl {
        public int I$0;
        public Object L$0;
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
            return PreparationPlanRepository.this.getDayLevelInfo(0, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getDayLevelInfo$planInfo$1", f = "PreparationPlanRepository.kt", i = {}, l = {127}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
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
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationPlan> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PreparationPlanRepository preparationPlanRepository = PreparationPlanRepository.this;
                this.label = 1;
                obj = preparationPlanRepository.a(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return (EntityPreparationPlan) obj;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getDayLevelInfoBasedOnDate$2", f = "PreparationPlanRepository.kt", i = {}, l = {MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationDay>, Object> {
        public final /* synthetic */ String $date;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getDayLevelInfoBasedOnDate$2$planInfo$1", f = "PreparationPlanRepository.kt", i = {}, l = {205}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
            public int label;
            public final /* synthetic */ PreparationPlanRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(PreparationPlanRepository preparationPlanRepository, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = preparationPlanRepository;
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
                    PreparationPlanRepository preparationPlanRepository = this.this$0;
                    this.label = 1;
                    obj = preparationPlanRepository.a(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return (EntityPreparationPlan) obj;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(String str, Continuation<? super h> continuation) {
            super(2, continuation);
            this.$date = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new h(this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationDay> continuation) {
            return ((h) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Deferred b;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                b = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new a(PreparationPlanRepository.this, null), 3, null);
                this.label = 1;
                obj = b.await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            EntityPreparationPlan entityPreparationPlan = (EntityPreparationPlan) obj;
            PreparationPlanDao preparationPlanDao = PreparationPlanRepository.this.c;
            String planId = entityPreparationPlan != null ? entityPreparationPlan.getPlanId() : null;
            Intrinsics.checkNotNull(planId);
            return preparationPlanDao.getDayLevelInfoBasedOnDate(planId, this.$date);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getDayLevelInfoWithOutLiveData$2", f = "PreparationPlanRepository.kt", i = {}, l = {138}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class i extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EntityPreparationDay>>, Object> {
        public final /* synthetic */ int $weekNumber;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getDayLevelInfoWithOutLiveData$2$planInfo$1", f = "PreparationPlanRepository.kt", i = {}, l = {136}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
            public int label;
            public final /* synthetic */ PreparationPlanRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(PreparationPlanRepository preparationPlanRepository, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = preparationPlanRepository;
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
                    PreparationPlanRepository preparationPlanRepository = this.this$0;
                    this.label = 1;
                    obj = preparationPlanRepository.a(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return (EntityPreparationPlan) obj;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(int i, Continuation<? super i> continuation) {
            super(2, continuation);
            this.$weekNumber = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new i(this.$weekNumber, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EntityPreparationDay>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EntityPreparationDay>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<EntityPreparationDay>> continuation) {
            return ((i) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Deferred b;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            List<EntityPreparationDay> list = null;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                b = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new a(PreparationPlanRepository.this, null), 3, null);
                this.label = 1;
                obj = b.await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            EntityPreparationPlan entityPreparationPlan = (EntityPreparationPlan) obj;
            if (entityPreparationPlan != null) {
                PreparationPlanRepository preparationPlanRepository = PreparationPlanRepository.this;
                int i2 = this.$weekNumber;
                PreparationPlanDao preparationPlanDao = preparationPlanRepository.c;
                String planId = entityPreparationPlan.getPlanId();
                Intrinsics.checkNotNull(planId);
                list = preparationPlanDao.getDayLevelInfoWithOutLiveData(planId, i2);
            }
            Intrinsics.checkNotNull(list);
            return list;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getDayLevelInfoWithinDateRange$2", f = "PreparationPlanRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class j extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EntityPreparationDay>>, Object> {
        public final /* synthetic */ String $fromDate;
        public final /* synthetic */ String $planId;
        public final /* synthetic */ String $untilDate;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j(String str, String str2, String str3, Continuation<? super j> continuation) {
            super(2, continuation);
            this.$planId = str;
            this.$fromDate = str2;
            this.$untilDate = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new j(this.$planId, this.$fromDate, this.$untilDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EntityPreparationDay>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EntityPreparationDay>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<EntityPreparationDay>> continuation) {
            return ((j) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return PreparationPlanRepository.this.c.getDayLevelInfoWithinDateRange(this.$planId, this.$fromDate, this.$untilDate);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getPlanEndDate$2", f = "PreparationPlanRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class k extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        public final /* synthetic */ String $planId;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k(String str, Continuation<? super k> continuation) {
            super(2, continuation);
            this.$planId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new k(this.$planId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super String> continuation) {
            return ((k) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return PreparationPlanRepository.this.c.getPlanEndDate(this.$planId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository", f = "PreparationPlanRepository.kt", i = {0}, l = {110}, m = "getWeekLevelInfo", n = {"this"}, s = {"L$0"})
    /* loaded from: classes2.dex */
    public static final class l extends ContinuationImpl {
        public Object L$0;
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
            return PreparationPlanRepository.this.getWeekLevelInfo(this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getWeekLevelInfo$3", f = "PreparationPlanRepository.kt", i = {}, l = {199}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class m extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationWeek>, Object> {
        public final /* synthetic */ int $weekNumber;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getWeekLevelInfo$3$planInfo$1", f = "PreparationPlanRepository.kt", i = {}, l = {CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
            public int label;
            public final /* synthetic */ PreparationPlanRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(PreparationPlanRepository preparationPlanRepository, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = preparationPlanRepository;
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
                    PreparationPlanRepository preparationPlanRepository = this.this$0;
                    this.label = 1;
                    obj = preparationPlanRepository.a(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return (EntityPreparationPlan) obj;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m(int i, Continuation<? super m> continuation) {
            super(2, continuation);
            this.$weekNumber = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new m(this.$weekNumber, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationWeek> continuation) {
            return ((m) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Deferred b;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                b = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new a(PreparationPlanRepository.this, null), 3, null);
                this.label = 1;
                obj = b.await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return PreparationPlanRepository.this.c.getWeekLevelInfo(((EntityPreparationPlan) obj).getPlanId(), this.$weekNumber);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getWeekLevelInfo$planInfo$1", f = "PreparationPlanRepository.kt", i = {}, l = {108}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class n extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
        public int label;

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
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationPlan> continuation) {
            return ((n) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PreparationPlanRepository preparationPlanRepository = PreparationPlanRepository.this;
                this.label = 1;
                obj = preparationPlanRepository.a(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return (EntityPreparationPlan) obj;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getWeekLevelInfoWithoutLiveData$2", f = "PreparationPlanRepository.kt", i = {}, l = {119}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class o extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EntityPreparationWeek>>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getWeekLevelInfoWithoutLiveData$2$planInfo$1", f = "PreparationPlanRepository.kt", i = {}, l = {117}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationPlan>, Object> {
            public int label;
            public final /* synthetic */ PreparationPlanRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(PreparationPlanRepository preparationPlanRepository, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = preparationPlanRepository;
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
                    PreparationPlanRepository preparationPlanRepository = this.this$0;
                    this.label = 1;
                    obj = preparationPlanRepository.a(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return (EntityPreparationPlan) obj;
            }
        }

        public o(Continuation<? super o> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new o(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EntityPreparationWeek>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EntityPreparationWeek>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<EntityPreparationWeek>> continuation) {
            return ((o) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Deferred b;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                b = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new a(PreparationPlanRepository.this, null), 3, null);
                this.label = 1;
                obj = b.await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            EntityPreparationPlan entityPreparationPlan = (EntityPreparationPlan) obj;
            if (entityPreparationPlan != null) {
                return PreparationPlanRepository.this.c.getWeekLevelInfoWithoutLiveData(entityPreparationPlan.getPlanId());
            }
            return null;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$insertDailyPlan$2", f = "PreparationPlanRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class p extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
        public final /* synthetic */ List<EntityPreparationDay> $entityPreparationDay;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p(List<EntityPreparationDay> list, Continuation<? super p> continuation) {
            super(2, continuation);
            this.$entityPreparationDay = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new p(this.$entityPreparationDay, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Object> continuation) {
            return invoke2(coroutineScope, (Continuation<Object>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<Object> continuation) {
            return ((p) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    PreparationPlanRepository.this.c.insertDailyPlan(this.$entityPreparationDay);
                    return Unit.INSTANCE;
                } catch (SQLiteConstraintException e) {
                    return Boxing.boxInt(Log.d(PreparationPlanRepository.this.getTAG(), e.toString()));
                }
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$insertPlan$2", f = "PreparationPlanRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class q extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EntityPreparationPlan $entityPreparationPlan;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q(EntityPreparationPlan entityPreparationPlan, Continuation<? super q> continuation) {
            super(2, continuation);
            this.$entityPreparationPlan = entityPreparationPlan;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new q(this.$entityPreparationPlan, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((q) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                PreparationPlanRepository.this.c.insertPlan(this.$entityPreparationPlan);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$insertPlanSchedule$2", f = "PreparationPlanRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class r extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EntityPlanSchedule $entityPlanSchedule;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public r(EntityPlanSchedule entityPlanSchedule, Continuation<? super r> continuation) {
            super(2, continuation);
            this.$entityPlanSchedule = entityPlanSchedule;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new r(this.$entityPlanSchedule, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((r) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                PreparationPlanRepository.this.c.insertPlanSchedule(this.$entityPlanSchedule);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$insertWeeklyPlan$2", f = "PreparationPlanRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class s extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
        public final /* synthetic */ List<EntityPreparationWeek> $entityPreparationWeek;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public s(List<EntityPreparationWeek> list, Continuation<? super s> continuation) {
            super(2, continuation);
            this.$entityPreparationWeek = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new s(this.$entityPreparationWeek, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Object> continuation) {
            return invoke2(coroutineScope, (Continuation<Object>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<Object> continuation) {
            return ((s) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    PreparationPlanRepository.this.c.insertWeeklyPlan(this.$entityPreparationWeek);
                    return Unit.INSTANCE;
                } catch (SQLiteConstraintException e) {
                    return Boxing.boxInt(Log.d(PreparationPlanRepository.this.getTAG(), e.toString()));
                }
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public PreparationPlanRepository(Context context) {
        this.f2858a = context;
        this.b = "PreparationPlanRepository";
        PreparationPlanDao preparationPlanDao = WorkoutSessionDatabase.getAppDatabase(context).preparationPlanDao();
        Intrinsics.checkNotNullExpressionValue(preparationPlanDao, "getAppDatabase(context).preparationPlanDao()");
        this.c = preparationPlanDao;
    }

    public /* synthetic */ PreparationPlanRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Object a(Continuation<? super EntityPreparationPlan> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new e(null), continuation);
    }

    public final Object b(List<EntityPreparationDay> list, Continuation<Object> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new p(list, null), continuation);
    }

    public final Object c(EntityPreparationPlan entityPreparationPlan, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new q(entityPreparationPlan, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final Object d(EntityPlanSchedule entityPlanSchedule, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new r(entityPlanSchedule, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object deletePlan(@NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new a(null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object doesPlanExists(@NotNull Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new b(null), continuation);
    }

    public final Object e(List<EntityPreparationWeek> list, Continuation<Object> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new s(list, null), continuation);
    }

    @NotNull
    public final Context getContext() {
        return this.f2858a;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCurrentPlan(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.coveiot.android.activitymodes.repository.PreparationPlanRepository.c
            if (r0 == 0) goto L13
            r0 = r11
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$c r0 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository.c) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$c r0 = new com.coveiot.android.activitymodes.repository.PreparationPlanRepository$c
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r11)
            goto L4d
        L29:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L31:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.GlobalScope r4 = kotlinx.coroutines.GlobalScope.INSTANCE
            r5 = 0
            r6 = 0
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$d r7 = new com.coveiot.android.activitymodes.repository.PreparationPlanRepository$d
            r11 = 0
            r7.<init>(r11)
            r8 = 3
            r9 = 0
            kotlinx.coroutines.Deferred r11 = kotlinx.coroutines.BuildersKt.async$default(r4, r5, r6, r7, r8, r9)
            r0.label = r3
            java.lang.Object r11 = r11.await(r0)
            if (r11 != r1) goto L4d
            return r1
        L4d:
            com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan r11 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan) r11
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.PreparationPlanRepository.getCurrentPlan(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void getCurrentPlanFromServer(@Nullable final PlanDetailsListner planDetailsListner) {
        CovePreparationPlanApi.getCurrentPreparationPlan(true, new CoveApiListener<SCurrentPreparationPlanRes, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getCurrentPlanFromServer$1

            @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getCurrentPlanFromServer$1$onSuccess$1", f = "PreparationPlanRepository.kt", i = {2, 3, 4}, l = {74, 76, 77, 78, 79, 80}, m = "invokeSuspend", n = {"planDataHolder", "planDataHolder", "planDataHolder"}, s = {"L$0", "L$0", "L$0"})
            /* loaded from: classes2.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ PreparationPlanRepository.PlanDetailsListner $listener;
                public final /* synthetic */ SCurrentPreparationPlanRes $p0;
                public Object L$0;
                public int label;
                public final /* synthetic */ PreparationPlanRepository this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(PreparationPlanRepository preparationPlanRepository, SCurrentPreparationPlanRes sCurrentPreparationPlanRes, PreparationPlanRepository.PlanDetailsListner planDetailsListner, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = preparationPlanRepository;
                    this.$p0 = sCurrentPreparationPlanRes;
                    this.$listener = planDetailsListner;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$p0, this.$listener, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:27:0x0054 A[Catch: Exception -> 0x0037, TryCatch #0 {Exception -> 0x0037, blocks: (B:6:0x0011, B:9:0x001a, B:39:0x009b, B:12:0x0023, B:36:0x0089, B:15:0x002b, B:33:0x0077, B:16:0x002f, B:30:0x0062, B:17:0x0033, B:25:0x0048, B:27:0x0054, B:22:0x003c), top: B:48:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:32:0x0076 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:35:0x0088 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:38:0x009a A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:41:0x00ad A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:45:0x00cc  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @org.jetbrains.annotations.Nullable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r5) {
                    /*
                        r4 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                        int r1 = r4.label
                        switch(r1) {
                            case 0: goto L39;
                            case 1: goto L33;
                            case 2: goto L2f;
                            case 3: goto L27;
                            case 4: goto L1f;
                            case 5: goto L16;
                            case 6: goto L11;
                            default: goto L9;
                        }
                    L9:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r0)
                        throw r5
                    L11:
                        kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Exception -> L37
                        goto Lb1
                    L16:
                        java.lang.Object r1 = r4.L$0
                        com.coveiot.android.activitymodes.repository.PlanDataHolder r1 = (com.coveiot.android.activitymodes.repository.PlanDataHolder) r1
                        kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Exception -> L37
                        goto L9b
                    L1f:
                        java.lang.Object r1 = r4.L$0
                        com.coveiot.android.activitymodes.repository.PlanDataHolder r1 = (com.coveiot.android.activitymodes.repository.PlanDataHolder) r1
                        kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Exception -> L37
                        goto L89
                    L27:
                        java.lang.Object r1 = r4.L$0
                        com.coveiot.android.activitymodes.repository.PlanDataHolder r1 = (com.coveiot.android.activitymodes.repository.PlanDataHolder) r1
                        kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Exception -> L37
                        goto L77
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Exception -> L37
                        goto L62
                    L33:
                        kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Exception -> L37
                        goto L48
                    L37:
                        r5 = move-exception
                        goto Lae
                    L39:
                        kotlin.ResultKt.throwOnFailure(r5)
                        com.coveiot.android.activitymodes.repository.PreparationPlanRepository r5 = r4.this$0     // Catch: java.lang.Exception -> L37
                        r1 = 1
                        r4.label = r1     // Catch: java.lang.Exception -> L37
                        java.lang.Object r5 = r5.deletePlan(r4)     // Catch: java.lang.Exception -> L37
                        if (r5 != r0) goto L48
                        return r0
                    L48:
                        com.coveiot.coveaccess.model.server.SCurrentPreparationPlanRes r5 = r4.$p0     // Catch: java.lang.Exception -> L37
                        com.coveiot.coveaccess.model.server.SCurrentPreparationPlanRes$DataBean r5 = r5.getData()     // Catch: java.lang.Exception -> L37
                        com.coveiot.coveaccess.model.server.SCurrentPreparationPlanRes$DataBean$ScheduleBean r5 = r5.getSchedule()     // Catch: java.lang.Exception -> L37
                        if (r5 == 0) goto Lb1
                        com.coveiot.android.activitymodes.repository.Formator$Companion r5 = com.coveiot.android.activitymodes.repository.Formator.Companion     // Catch: java.lang.Exception -> L37
                        com.coveiot.coveaccess.model.server.SCurrentPreparationPlanRes r1 = r4.$p0     // Catch: java.lang.Exception -> L37
                        r2 = 2
                        r4.label = r2     // Catch: java.lang.Exception -> L37
                        java.lang.Object r5 = r5.getPreparationPlan(r1, r4)     // Catch: java.lang.Exception -> L37
                        if (r5 != r0) goto L62
                        return r0
                    L62:
                        r1 = r5
                        com.coveiot.android.activitymodes.repository.PlanDataHolder r1 = (com.coveiot.android.activitymodes.repository.PlanDataHolder) r1     // Catch: java.lang.Exception -> L37
                        com.coveiot.android.activitymodes.repository.PreparationPlanRepository r5 = r4.this$0     // Catch: java.lang.Exception -> L37
                        com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan r2 = r1.getEntityPreparationPlan()     // Catch: java.lang.Exception -> L37
                        r4.L$0 = r1     // Catch: java.lang.Exception -> L37
                        r3 = 3
                        r4.label = r3     // Catch: java.lang.Exception -> L37
                        java.lang.Object r5 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.access$insertPlan(r5, r2, r4)     // Catch: java.lang.Exception -> L37
                        if (r5 != r0) goto L77
                        return r0
                    L77:
                        com.coveiot.android.activitymodes.repository.PreparationPlanRepository r5 = r4.this$0     // Catch: java.lang.Exception -> L37
                        com.coveiot.android.activitymodes.database.entities.EntityPlanSchedule r2 = r1.getPlanSchedule()     // Catch: java.lang.Exception -> L37
                        r4.L$0 = r1     // Catch: java.lang.Exception -> L37
                        r3 = 4
                        r4.label = r3     // Catch: java.lang.Exception -> L37
                        java.lang.Object r5 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.access$insertPlanSchedule(r5, r2, r4)     // Catch: java.lang.Exception -> L37
                        if (r5 != r0) goto L89
                        return r0
                    L89:
                        com.coveiot.android.activitymodes.repository.PreparationPlanRepository r5 = r4.this$0     // Catch: java.lang.Exception -> L37
                        java.util.List r2 = r1.getWeeks()     // Catch: java.lang.Exception -> L37
                        r4.L$0 = r1     // Catch: java.lang.Exception -> L37
                        r3 = 5
                        r4.label = r3     // Catch: java.lang.Exception -> L37
                        java.lang.Object r5 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.access$insertWeeklyPlan(r5, r2, r4)     // Catch: java.lang.Exception -> L37
                        if (r5 != r0) goto L9b
                        return r0
                    L9b:
                        com.coveiot.android.activitymodes.repository.PreparationPlanRepository r5 = r4.this$0     // Catch: java.lang.Exception -> L37
                        java.util.List r1 = r1.getDays()     // Catch: java.lang.Exception -> L37
                        r2 = 0
                        r4.L$0 = r2     // Catch: java.lang.Exception -> L37
                        r2 = 6
                        r4.label = r2     // Catch: java.lang.Exception -> L37
                        java.lang.Object r5 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.access$insertDailyPlan(r5, r1, r4)     // Catch: java.lang.Exception -> L37
                        if (r5 != r0) goto Lb1
                        return r0
                    Lae:
                        r5.printStackTrace()
                    Lb1:
                        com.coveiot.android.activitymodes.repository.PreparationPlanRepository r5 = r4.this$0
                        android.content.Context r5 = r5.getContext()
                        com.coveiot.covepreferences.SessionManager r5 = com.coveiot.covepreferences.SessionManager.getInstance(r5)
                        com.coveiot.coveaccess.model.server.SCurrentPreparationPlanRes r0 = r4.$p0
                        com.coveiot.coveaccess.model.server.SCurrentPreparationPlanRes$DataBean r0 = r0.getData()
                        int r0 = r0.getPreviousPlansSubscribed()
                        r5.savePlanHistoryCount(r0)
                        com.coveiot.android.activitymodes.repository.PreparationPlanRepository$PlanDetailsListner r5 = r4.$listener
                        if (r5 == 0) goto Lcf
                        r5.onPlanFetchedSuccessfully()
                    Lcf:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.PreparationPlanRepository$getCurrentPlanFromServer$1.a.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SCurrentPreparationPlanRes sCurrentPreparationPlanRes) {
                if (sCurrentPreparationPlanRes == null || sCurrentPreparationPlanRes.getData() == null) {
                    return;
                }
                e.e(GlobalScope.INSTANCE, null, null, new a(PreparationPlanRepository.this, sCurrentPreparationPlanRes, planDetailsListner, null), 3, null);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getDayLevelInfo(int r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.lifecycle.LiveData<java.util.List<com.coveiot.android.activitymodes.database.entities.EntityPreparationDay>>> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.coveiot.android.activitymodes.repository.PreparationPlanRepository.f
            if (r0 == 0) goto L13
            r0 = r12
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$f r0 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository.f) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$f r0 = new com.coveiot.android.activitymodes.repository.PreparationPlanRepository$f
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            int r11 = r0.I$0
            java.lang.Object r0 = r0.L$0
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository r0 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository) r0
            kotlin.ResultKt.throwOnFailure(r12)
            goto L58
        L2f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L37:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.GlobalScope r4 = kotlinx.coroutines.GlobalScope.INSTANCE
            r5 = 0
            r6 = 0
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$g r7 = new com.coveiot.android.activitymodes.repository.PreparationPlanRepository$g
            r12 = 0
            r7.<init>(r12)
            r8 = 3
            r9 = 0
            kotlinx.coroutines.Deferred r12 = kotlinx.coroutines.BuildersKt.async$default(r4, r5, r6, r7, r8, r9)
            r0.L$0 = r10
            r0.I$0 = r11
            r0.label = r3
            java.lang.Object r12 = r12.await(r0)
            if (r12 != r1) goto L57
            return r1
        L57:
            r0 = r10
        L58:
            com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan r12 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan) r12
            com.coveiot.android.activitymodes.database.dao.PreparationPlanDao r0 = r0.c
            java.lang.String r12 = r12.getPlanId()
            androidx.lifecycle.LiveData r11 = r0.getDayLevelInfo(r12, r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.PreparationPlanRepository.getDayLevelInfo(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object getDayLevelInfoBasedOnDate(@NotNull String str, @NotNull Continuation<? super EntityPreparationDay> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new h(str, null), continuation);
    }

    @Nullable
    public final Object getDayLevelInfoWithOutLiveData(int i2, @NotNull Continuation<? super List<EntityPreparationDay>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new i(i2, null), continuation);
    }

    @Nullable
    public final Object getDayLevelInfoWithinDateRange(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Continuation<? super List<EntityPreparationDay>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new j(str, str2, str3, null), continuation);
    }

    @Nullable
    public final Object getPlanEndDate(@NotNull String str, @NotNull Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new k(str, null), continuation);
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getWeekLevelInfo(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.lifecycle.LiveData<java.util.List<com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek>>> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.coveiot.android.activitymodes.repository.PreparationPlanRepository.l
            if (r0 == 0) goto L13
            r0 = r11
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$l r0 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository.l) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$l r0 = new com.coveiot.android.activitymodes.repository.PreparationPlanRepository$l
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository r0 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L54
        L2d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L35:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.GlobalScope r4 = kotlinx.coroutines.GlobalScope.INSTANCE
            r5 = 0
            r6 = 0
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$n r7 = new com.coveiot.android.activitymodes.repository.PreparationPlanRepository$n
            r11 = 0
            r7.<init>(r11)
            r8 = 3
            r9 = 0
            kotlinx.coroutines.Deferred r11 = kotlinx.coroutines.BuildersKt.async$default(r4, r5, r6, r7, r8, r9)
            r0.L$0 = r10
            r0.label = r3
            java.lang.Object r11 = r11.await(r0)
            if (r11 != r1) goto L53
            return r1
        L53:
            r0 = r10
        L54:
            com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan r11 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan) r11
            com.coveiot.android.activitymodes.database.dao.PreparationPlanDao r0 = r0.c
            java.lang.String r11 = r11.getPlanId()
            androidx.lifecycle.LiveData r11 = r0.getWeekLevelInfo(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.PreparationPlanRepository.getWeekLevelInfo(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object getWeekLevelInfoWithoutLiveData(@NotNull Continuation<? super List<EntityPreparationWeek>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new o(null), continuation);
    }

    public final void optOutCurrentPlan(@NotNull OptoutListner optoutListener) {
        Intrinsics.checkNotNullParameter(optoutListener, "optoutListener");
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new PreparationPlanRepository$optOutCurrentPlan$1(this, optoutListener, null), 3, null);
    }

    @Nullable
    public final Object getWeekLevelInfo(int i2, @NotNull Continuation<? super EntityPreparationWeek> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new m(i2, null), continuation);
    }
}
