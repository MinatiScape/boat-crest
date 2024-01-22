package com.coveiot.android.activitymodes.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.android.activitymodes.SingletonHolder;
import com.coveiot.android.activitymodes.database.WorkoutSessionDatabase;
import com.coveiot.android.activitymodes.database.dao.SessionDAO;
import com.coveiot.android.activitymodes.database.entities.ActivityDataSample;
import com.coveiot.android.activitymodes.database.entities.BadmintonSample;
import com.coveiot.android.activitymodes.database.entities.BasketBallSample;
import com.coveiot.android.activitymodes.database.entities.ClimbingSample;
import com.coveiot.android.activitymodes.database.entities.CyclingSample;
import com.coveiot.android.activitymodes.database.entities.DanceSample;
import com.coveiot.android.activitymodes.database.entities.EllipticalSample;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment;
import com.coveiot.android.activitymodes.database.entities.FootballSample;
import com.coveiot.android.activitymodes.database.entities.FreeExerciseSample;
import com.coveiot.android.activitymodes.database.entities.HikingSample;
import com.coveiot.android.activitymodes.database.entities.MeditationSample;
import com.coveiot.android.activitymodes.database.entities.PhysicalActivitySample;
import com.coveiot.android.activitymodes.database.entities.RowingMachineSample;
import com.coveiot.android.activitymodes.database.entities.RunSample;
import com.coveiot.android.activitymodes.database.entities.SkippingSample;
import com.coveiot.android.activitymodes.database.entities.TennisSample;
import com.coveiot.android.activitymodes.database.entities.TreadmillSample;
import com.coveiot.android.activitymodes.database.entities.WalkSample;
import com.coveiot.android.activitymodes.database.entities.WorkoutSample;
import com.coveiot.android.activitymodes.database.entities.YogaSample;
import com.coveiot.android.activitymodes.models.FitnessChallengeStatsData;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.activitysession.GetActivitySessionHeaderResponse;
import com.coveiot.coveaccess.activitysession.PostActivitySessionDataRequest;
import com.coveiot.coveaccess.activitysession.PostActivitySessionHeaderResponse;
import com.coveiot.coveaccess.activitysession.TraqConfigApi;
import com.coveiot.coveaccess.activitysession.fitnessActivitySessions;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.gr.libdfu.task.sub.ResultCode;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.veryfit.multi.nativeprotocol.b;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorkoutSessionRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2861a;
    @NotNull
    public SessionDAO b;
    public long c;
    @Nullable
    public ActivityDataSyncListner d;

    /* loaded from: classes2.dex */
    public interface ActivityDataSyncListner {
        void onActivityDataSyncComplete();
    }

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<WorkoutSessionRepository, Context> {

        /* loaded from: classes2.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, WorkoutSessionRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, WorkoutSessionRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final WorkoutSessionRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new WorkoutSessionRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$deleteSession$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(EntityWorkoutSession entityWorkoutSession, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$entityWorkoutSession = entityWorkoutSession;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$entityWorkoutSession, continuation);
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
                WorkoutSessionRepository.this.b.deleteSession(this.$entityWorkoutSession);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getDanceSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends DanceSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a0(String str, Continuation<? super a0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends DanceSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<DanceSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<DanceSample>> continuation) {
            return ((a0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getDanceSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSessionSegmentsList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EntityWorkoutSessionSegment>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a1(String str, Continuation<? super a1> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a1(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EntityWorkoutSessionSegment>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EntityWorkoutSessionSegment>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<EntityWorkoutSessionSegment>> continuation) {
            return ((a1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getSessionSegmentListBy(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertClimbingSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<ClimbingSample> $climbingSampleList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a2(ArrayList<ClimbingSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super a2> continuation) {
            super(2, continuation);
            this.$climbingSampleList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a2(this.$climbingSampleList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$climbingSampleList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$climbingSampleList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertClimbingSamples(this.$climbingSampleList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$deleteWorkoutSession$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                WorkoutSessionRepository.this.b.deleteWorkOutSession();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getDistanceByActivityNCategoryIDs$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ int $activityId;
        public final /* synthetic */ int $categoryId;
        public final /* synthetic */ String $date;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b0(String str, int i, int i2, Continuation<? super b0> continuation) {
            super(2, continuation);
            this.$date = str;
            this.$categoryId = i;
            this.$activityId = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b0(this.$date, this.$categoryId, this.$activityId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((b0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getDistanceByActivityNCategoryIDs(this.$date, this.$categoryId, this.$activityId));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSessionsList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EntityWorkoutSession>>, Object> {
        public int label;

        public b1(Continuation<? super b1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EntityWorkoutSession>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EntityWorkoutSession>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<EntityWorkoutSession>> continuation) {
            return ((b1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getSessionsList();
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertCyclingSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<CyclingSample> $cycleSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b2(ArrayList<CyclingSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super b2> continuation) {
            super(2, continuation);
            this.$cycleSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b2(this.$cycleSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$cycleSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$cycleSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertCyclingSamples(this.$cycleSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$fetchSessionEntitiesAndInsert$2", f = "WorkoutSessionRepository.kt", i = {}, l = {643, 644}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ List<PostActivitySessionDataRequest> $postActivitySessionDataRequestList;
        public Object L$0;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public c(List<? extends PostActivitySessionDataRequest> list, WorkoutSessionRepository workoutSessionRepository, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$postActivitySessionDataRequestList = list;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$postActivitySessionDataRequestList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x009b A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x009c  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x009c -> B:12:0x0033). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L29
                if (r1 == r3) goto L1f
                if (r1 != r2) goto L17
                java.lang.Object r1 = r9.L$0
                java.util.Iterator r1 = (java.util.Iterator) r1
                kotlin.ResultKt.throwOnFailure(r10)
                r10 = r1
                goto L32
            L17:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L1f:
                java.lang.Object r1 = r9.L$0
                java.util.Iterator r1 = (java.util.Iterator) r1
                kotlin.ResultKt.throwOnFailure(r10)
                r4 = r1
                r1 = r9
                goto L8d
            L29:
                kotlin.ResultKt.throwOnFailure(r10)
                java.util.List<com.coveiot.coveaccess.activitysession.PostActivitySessionDataRequest> r10 = r9.$postActivitySessionDataRequestList
                java.util.Iterator r10 = r10.iterator()
            L32:
                r1 = r9
            L33:
                boolean r4 = r10.hasNext()
                if (r4 == 0) goto L9e
                java.lang.Object r4 = r10.next()
                com.coveiot.coveaccess.activitysession.PostActivitySessionDataRequest r4 = (com.coveiot.coveaccess.activitysession.PostActivitySessionDataRequest) r4
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r5 = r1.this$0
                java.lang.String r6 = r4.getClientRefId()
                java.lang.String r7 = "postActivitySession.clientRefId"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
                boolean r5 = com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.access$isClientRefIdExists(r5, r6)
                if (r5 != 0) goto L33
                java.lang.String r5 = r4.getSessionType()
                java.lang.String r6 = "CRICKET_BATTING"
                boolean r5 = r5.equals(r6)
                if (r5 != 0) goto L33
                java.lang.String r5 = r4.getSessionType()
                java.lang.String r6 = "CRICKET_BOWLING"
                boolean r5 = r5.equals(r6)
                if (r5 != 0) goto L33
                com.coveiot.android.activitymodes.preference.PreferenceManager r5 = new com.coveiot.android.activitymodes.preference.PreferenceManager
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r6 = r1.this$0
                android.content.Context r6 = r6.getContext()
                r5.<init>(r6)
                java.lang.String r5 = r5.getConnectedDeviceMacAddress()
                com.coveiot.android.activitymodes.repository.Formator$Companion r6 = com.coveiot.android.activitymodes.repository.Formator.Companion
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r7 = r1.this$0
                android.content.Context r7 = r7.getContext()
                r1.L$0 = r10
                r1.label = r3
                java.lang.Object r4 = r6.getWorkoutSessionEntity(r7, r4, r5, r1)
                if (r4 != r0) goto L8a
                return r0
            L8a:
                r8 = r4
                r4 = r10
                r10 = r8
            L8d:
                com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r10
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r5 = r1.this$0
                r1.L$0 = r4
                r1.label = r2
                java.lang.Object r10 = r5.insertSession(r10, r1)
                if (r10 != r0) goto L9c
                return r0
            L9c:
                r10 = r4
                goto L33
            L9e:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.c.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getDistanceByType$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class c0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $date;
        public final /* synthetic */ String $type;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c0(String str, String str2, Continuation<? super c0> continuation) {
            super(2, continuation);
            this.$date = str;
            this.$type = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c0(this.$date, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((c0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getDistanceByType(this.$date, this.$type));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSessionsOfParticularDay$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class c1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EntityWorkoutSession>>, Object> {
        public final /* synthetic */ String $date;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c1(String str, Continuation<? super c1> continuation) {
            super(2, continuation);
            this.$date = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c1(this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EntityWorkoutSession>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EntityWorkoutSession>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<EntityWorkoutSession>> continuation) {
            return ((c1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getSessionsOfParticularDay(this.$date);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertDanceSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class c2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<DanceSample> $danceSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c2(ArrayList<DanceSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super c2> continuation) {
            super(2, continuation);
            this.$danceSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c2(this.$danceSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$danceSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$danceSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertDanceSamples(this.$danceSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getActivitiesByActivityAndCategoryID$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EntityWorkoutSession>>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EntityWorkoutSession>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EntityWorkoutSession>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<EntityWorkoutSession>> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getActivitiesByActivityAndCategoryID();
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getDistanceForWeekByActivityNCategoryIDs$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ int $activityId;
        public final /* synthetic */ int $categoryId;
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d0(int i, int i2, String str, String str2, Continuation<? super d0> continuation) {
            super(2, continuation);
            this.$categoryId = i;
            this.$activityId = i2;
            this.$startDate = str;
            this.$endDate = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d0(this.$categoryId, this.$activityId, this.$startDate, this.$endDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((d0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getDistanceForWeekByActivityNCategoryIDs(this.$categoryId, this.$activityId, this.$startDate, this.$endDate));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSingleDayCalorieWithoutActivityMapping$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $date;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d1(String str, Continuation<? super d1> continuation) {
            super(2, continuation);
            this.$date = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d1(this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((d1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getSingleDayCalorieWithoutActivityMapping(this.$date));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertEllipticalSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<EllipticalSample> $ellipticalSampleList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d2(ArrayList<EllipticalSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super d2> continuation) {
            super(2, continuation);
            this.$ellipticalSampleList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d2(this.$ellipticalSampleList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$ellipticalSampleList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$ellipticalSampleList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertEllipticalSamples(this.$ellipticalSampleList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getActivitiesByCategoryID$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EntityWorkoutSession>>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EntityWorkoutSession>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EntityWorkoutSession>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<EntityWorkoutSession>> continuation) {
            return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getActivitiesByCategoryID();
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getDistanceForWeekByType$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class e0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public final /* synthetic */ String $type;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e0(String str, String str2, String str3, Continuation<? super e0> continuation) {
            super(2, continuation);
            this.$startDate = str;
            this.$endDate = str2;
            this.$type = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e0(this.$startDate, this.$endDate, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((e0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getDistanceForWeekByType(this.$startDate, this.$endDate, this.$type));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSingleDayDistanceWithoutActivityMapping$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class e1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $date;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e1(String str, Continuation<? super e1> continuation) {
            super(2, continuation);
            this.$date = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e1(this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((e1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getSingleDayDistanceWithoutActivityMapping(this.$date));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertFootBallSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class e2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<FootballSample> $footBallSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e2(ArrayList<FootballSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super e2> continuation) {
            super(2, continuation);
            this.$footBallSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e2(this.$footBallSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((e2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$footBallSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$footBallSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertFootballSamples(this.$footBallSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getActivityDataSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<ActivityDataSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(String str, String str2, Continuation<? super f> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<ActivityDataSample>> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<ActivityDataSample> activityDataSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getActivityDataSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(activityDataSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.ActivityDataSample>");
                return (ArrayList) activityDataSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getDistanceWithoutActivityMapping$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class f0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f0(String str, String str2, Continuation<? super f0> continuation) {
            super(2, continuation);
            this.$startDate = str;
            this.$endDate = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f0(this.$startDate, this.$endDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((f0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getDistanceWithoutActivityMapping(this.$startDate, this.$endDate));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSingleDayStepWithoutActivityMapping$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class f1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $date;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f1(String str, Continuation<? super f1> continuation) {
            super(2, continuation);
            this.$date = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f1(this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((f1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getSingleDayStepWithoutActivityMapping(this.$date));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertFreeExerciseSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class f2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<FreeExerciseSample> $freeExerciseSampleList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f2(ArrayList<FreeExerciseSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super f2> continuation) {
            super(2, continuation);
            this.$freeExerciseSampleList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f2(this.$freeExerciseSampleList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((f2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$freeExerciseSampleList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$freeExerciseSampleList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertFreeExerciseSamples(this.$freeExerciseSampleList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getActivityDataSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends ActivityDataSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String str, Continuation<? super g> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends ActivityDataSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<ActivityDataSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<ActivityDataSample>> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getActivityDataSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getEllipticalSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class g0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<EllipticalSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g0(String str, String str2, Continuation<? super g0> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g0(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<EllipticalSample>> continuation) {
            return ((g0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<EllipticalSample> ellipticalSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getEllipticalSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(ellipticalSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.EllipticalSample>");
                return (ArrayList) ellipticalSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSkippingSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class g1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<SkippingSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g1(String str, String str2, Continuation<? super g1> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g1(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<SkippingSample>> continuation) {
            return ((g1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<SkippingSample> skippingSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getSkippingSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(skippingSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.SkippingSample>");
                return (ArrayList) skippingSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertGenericActivitySampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class g2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<ActivityDataSample> $genericActivitySample;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g2(ArrayList<ActivityDataSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super g2> continuation) {
            super(2, continuation);
            this.$genericActivitySample = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g2(this.$genericActivitySample, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((g2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$genericActivitySample.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sessionID = this.$genericActivitySample.get(0).getSessionID();
                    Intrinsics.checkNotNull(sessionID);
                    if (sessionDAO.isSessionIdExists(sessionID) != 0) {
                        this.this$0.b.insertActivityDataSamples(this.$genericActivitySample);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getActivitySamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends ActivityDataSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(String str, Continuation<? super h> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new h(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends ActivityDataSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<ActivityDataSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<ActivityDataSample>> continuation) {
            return ((h) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getActivityDataSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getEllipticalSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class h0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends EllipticalSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h0(String str, Continuation<? super h0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new h0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends EllipticalSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<EllipticalSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<EllipticalSample>> continuation) {
            return ((h0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getEllipticalSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSkippingSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class h1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends SkippingSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h1(String str, Continuation<? super h1> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new h1(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends SkippingSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<SkippingSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<SkippingSample>> continuation) {
            return ((h1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getSkippingSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertHikingSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class h2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<HikingSample> $hikingSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h2(ArrayList<HikingSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super h2> continuation) {
            super(2, continuation);
            this.$hikingSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new h2(this.$hikingSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((h2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$hikingSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$hikingSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertHikingSamples(this.$hikingSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getBadmintonSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class i extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<BadmintonSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(String str, String str2, Continuation<? super i> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new i(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<BadmintonSample>> continuation) {
            return ((i) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<BadmintonSample> badmintonSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getBadmintonSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(badmintonSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.BadmintonSample>");
                return (ArrayList) badmintonSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getFootBallSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class i0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<FootballSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i0(String str, String str2, Continuation<? super i0> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new i0(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<FootballSample>> continuation) {
            return ((i0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<FootballSample> footBallSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getFootBallSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(footBallSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.FootballSample>");
                return (ArrayList) footBallSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getStepsByActivityNCategoryIDs$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class i1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ int $activityId;
        public final /* synthetic */ int $categoryId;
        public final /* synthetic */ String $date;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i1(String str, int i, int i2, Continuation<? super i1> continuation) {
            super(2, continuation);
            this.$date = str;
            this.$categoryId = i;
            this.$activityId = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new i1(this.$date, this.$categoryId, this.$activityId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((i1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getStepsByActivityNCategoryIDs(this.$date, this.$categoryId, this.$activityId));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertMeditationSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class i2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<MeditationSample> $meditationSampleList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i2(ArrayList<MeditationSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super i2> continuation) {
            super(2, continuation);
            this.$meditationSampleList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new i2(this.$meditationSampleList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((i2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$meditationSampleList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$meditationSampleList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertMeditationSamples(this.$meditationSampleList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getBadmintonSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class j extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends BadmintonSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j(String str, Continuation<? super j> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new j(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends BadmintonSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<BadmintonSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<BadmintonSample>> continuation) {
            return ((j) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getBadmintonSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getFootBallSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class j0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends FootballSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j0(String str, Continuation<? super j0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new j0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends FootballSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<FootballSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<FootballSample>> continuation) {
            return ((j0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getFootballSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getStepsByActivityType$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class j1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $date;
        public final /* synthetic */ String $type;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j1(String str, String str2, Continuation<? super j1> continuation) {
            super(2, continuation);
            this.$date = str;
            this.$type = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new j1(this.$date, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((j1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getStepsByActivityType(this.$date, this.$type));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertPhysicalActivitySamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class j2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<PhysicalActivitySample> $walkSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j2(ArrayList<PhysicalActivitySample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super j2> continuation) {
            super(2, continuation);
            this.$walkSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new j2(this.$walkSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((j2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$walkSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$walkSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertPhysicalActivitySamples(this.$walkSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getBasketBallSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class k extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<BasketBallSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k(String str, String str2, Continuation<? super k> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new k(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<BasketBallSample>> continuation) {
            return ((k) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<BasketBallSample> basketBallSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getBasketBallSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(basketBallSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.BasketBallSample>");
                return (ArrayList) basketBallSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getFreeExerciseSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class k0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<FreeExerciseSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k0(String str, String str2, Continuation<? super k0> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new k0(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<FreeExerciseSample>> continuation) {
            return ((k0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<FreeExerciseSample> freeExerciseSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getFreeExerciseSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(freeExerciseSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.FreeExerciseSample>");
                return (ArrayList) freeExerciseSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getStepsForWeekByActivityNCategoryIDs$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class k1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ int $activityId;
        public final /* synthetic */ int $categoryId;
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k1(int i, int i2, String str, String str2, Continuation<? super k1> continuation) {
            super(2, continuation);
            this.$categoryId = i;
            this.$activityId = i2;
            this.$startDate = str;
            this.$endDate = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new k1(this.$categoryId, this.$activityId, this.$startDate, this.$endDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((k1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getStepsForWeekByActivityNCategoryIDs(this.$categoryId, this.$activityId, this.$startDate, this.$endDate));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertRowingMachineSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class k2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<RowingMachineSample> $rowingMachineSampleList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k2(ArrayList<RowingMachineSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super k2> continuation) {
            super(2, continuation);
            this.$rowingMachineSampleList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new k2(this.$rowingMachineSampleList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((k2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$rowingMachineSampleList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$rowingMachineSampleList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertRowingMachineSamples(this.$rowingMachineSampleList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getBasketBallSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class l extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends BasketBallSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public l(String str, Continuation<? super l> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new l(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends BasketBallSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<BasketBallSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<BasketBallSample>> continuation) {
            return ((l) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getBasketBallSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getFreeExerciseSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class l0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends FreeExerciseSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public l0(String str, Continuation<? super l0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new l0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends FreeExerciseSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<FreeExerciseSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<FreeExerciseSample>> continuation) {
            return ((l0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getFreeExerciseSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getStepsForWeekByType$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class l1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public final /* synthetic */ String $type;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public l1(String str, String str2, String str3, Continuation<? super l1> continuation) {
            super(2, continuation);
            this.$startDate = str;
            this.$endDate = str2;
            this.$type = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new l1(this.$startDate, this.$endDate, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((l1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getStepsForWeekByType(this.$startDate, this.$endDate, this.$type));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertRunSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class l2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<RunSample> $runSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public l2(ArrayList<RunSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super l2> continuation) {
            super(2, continuation);
            this.$runSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new l2(this.$runSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((l2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$runSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$runSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertRunSamples(this.$runSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getBestSessionByActivityAndCategoryID$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class m extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityWorkoutSession>, Object> {
        public final /* synthetic */ int $activityId;
        public final /* synthetic */ int $categoryID;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m(int i, int i2, Continuation<? super m> continuation) {
            super(2, continuation);
            this.$activityId = i;
            this.$categoryID = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new m(this.$activityId, this.$categoryID, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityWorkoutSession> continuation) {
            return ((m) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getBestSessionByActivityAndCategoryID(this.$activityId, this.$categoryID);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getHikingSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class m0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<HikingSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m0(String str, String str2, Continuation<? super m0> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new m0(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<HikingSample>> continuation) {
            return ((m0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<HikingSample> hikingSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getHikingSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(hikingSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.HikingSample>");
                return (ArrayList) hikingSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getStepsWithoutActivityMapping$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class m1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m1(String str, String str2, Continuation<? super m1> continuation) {
            super(2, continuation);
            this.$startDate = str;
            this.$endDate = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new m1(this.$startDate, this.$endDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((m1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getStepsWithoutActivityMapping(this.$startDate, this.$endDate));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertSession$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class m2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m2(EntityWorkoutSession entityWorkoutSession, Continuation<? super m2> continuation) {
            super(2, continuation);
            this.$entityWorkoutSession = entityWorkoutSession;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new m2(this.$entityWorkoutSession, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((m2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                SessionDAO sessionDAO = WorkoutSessionRepository.this.b;
                String serialNo = this.$entityWorkoutSession.getSerialNo();
                Intrinsics.checkNotNull(serialNo);
                String activity_type = this.$entityWorkoutSession.getActivity_type();
                Intrinsics.checkNotNull(activity_type);
                if (sessionDAO.isDataPresentInDb(serialNo, activity_type, this.$entityWorkoutSession.getStart_time()) <= 0) {
                    WorkoutSessionRepository.this.b.insertSession(this.$entityWorkoutSession);
                    LogHelper.d("Workoutsession", "start time and end time  " + this.$entityWorkoutSession.getStart_time() + " -- " + this.$entityWorkoutSession.getEnd_time());
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getBestSessionByActivityType$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class n extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityWorkoutSession>, Object> {
        public final /* synthetic */ String $activityType;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public n(String str, Continuation<? super n> continuation) {
            super(2, continuation);
            this.$activityType = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new n(this.$activityType, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityWorkoutSession> continuation) {
            return ((n) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getBestSessionByActivityType(this.$activityType);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getHikingSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class n0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends HikingSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public n0(String str, Continuation<? super n0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new n0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends HikingSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<HikingSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<HikingSample>> continuation) {
            return ((n0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getHikingSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getTennisSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class n1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<TennisSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public n1(String str, String str2, Continuation<? super n1> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new n1(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<TennisSample>> continuation) {
            return ((n1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<TennisSample> tennisSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getTennisSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(tennisSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.TennisSample>");
                return (ArrayList) tennisSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertSessionSegment$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class n2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EntityWorkoutSessionSegment $entityWorkoutSessionSegment;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public n2(EntityWorkoutSessionSegment entityWorkoutSessionSegment, WorkoutSessionRepository workoutSessionRepository, Continuation<? super n2> continuation) {
            super(2, continuation);
            this.$entityWorkoutSessionSegment = entityWorkoutSessionSegment;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new n2(this.$entityWorkoutSessionSegment, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((n2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                LogHelper.d("Workoutsessionsegment***", "start time and end time ** " + this.$entityWorkoutSessionSegment.getStart_time() + " -- " + this.$entityWorkoutSessionSegment.getEnd_time());
                SessionDAO sessionDAO = this.this$0.b;
                String sess_id = this.$entityWorkoutSessionSegment.getSess_id();
                Intrinsics.checkNotNull(sess_id);
                if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                    this.this$0.b.insertSessionSegment(this.$entityWorkoutSessionSegment);
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getBestSessionByActivityType$4", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class o extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityWorkoutSession>, Object> {
        public final /* synthetic */ String $activityType;
        public final /* synthetic */ String $indoorOutdoorType;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public o(String str, String str2, Continuation<? super o> continuation) {
            super(2, continuation);
            this.$activityType = str;
            this.$indoorOutdoorType = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new o(this.$activityType, this.$indoorOutdoorType, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityWorkoutSession> continuation) {
            return ((o) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getBestSessionByActivityType(this.$activityType, this.$indoorOutdoorType);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getLatestSession$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class o0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityWorkoutSession>, Object> {
        public int label;

        public o0(Continuation<? super o0> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new o0(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityWorkoutSession> continuation) {
            return ((o0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getLatestSession();
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getTennisSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class o1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends TennisSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public o1(String str, Continuation<? super o1> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new o1(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends TennisSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<TennisSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<TennisSample>> continuation) {
            return ((o1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getTennisSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertSessionSegmentList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class o2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<EntityWorkoutSessionSegment> $entityWorkoutSessionSegmentList;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public o2(ArrayList<EntityWorkoutSessionSegment> arrayList, Continuation<? super o2> continuation) {
            super(2, continuation);
            this.$entityWorkoutSessionSegmentList = arrayList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new o2(this.$entityWorkoutSessionSegmentList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((o2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                WorkoutSessionRepository.this.b.insertSessionSegments(this.$entityWorkoutSessionSegmentList);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getBestSessionByCategoryID$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class p extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityWorkoutSession>, Object> {
        public final /* synthetic */ int $categoryID;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p(int i, Continuation<? super p> continuation) {
            super(2, continuation);
            this.$categoryID = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new p(this.$categoryID, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityWorkoutSession> continuation) {
            return ((p) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getBestSessionByCategoryID(this.$categoryID);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getLatestSessionByActivityType$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class p0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityWorkoutSession>, Object> {
        public final /* synthetic */ String $activityType;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p0(String str, Continuation<? super p0> continuation) {
            super(2, continuation);
            this.$activityType = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new p0(this.$activityType, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityWorkoutSession> continuation) {
            return ((p0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getLatestSessionByActivityType(this.$activityType);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getTreadmillSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class p1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<TreadmillSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p1(String str, String str2, Continuation<? super p1> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new p1(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<TreadmillSample>> continuation) {
            return ((p1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<TreadmillSample> treadMillSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getTreadMillSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(treadMillSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.TreadmillSample>");
                return (ArrayList) treadMillSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertSkippingSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class p2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<SkippingSample> $skippingSamples;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p2(ArrayList<SkippingSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super p2> continuation) {
            super(2, continuation);
            this.$skippingSamples = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new p2(this.$skippingSamples, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((p2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$skippingSamples.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$skippingSamples.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertSkippingSamples(this.$skippingSamples);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getCaloriesByActivityNCategoryIDs$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class q extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ int $activityId;
        public final /* synthetic */ int $categoryId;
        public final /* synthetic */ String $date;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q(String str, int i, int i2, Continuation<? super q> continuation) {
            super(2, continuation);
            this.$date = str;
            this.$categoryId = i;
            this.$activityId = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new q(this.$date, this.$categoryId, this.$activityId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((q) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getCaloriesByActivityNCategoryIDs(this.$date, this.$categoryId, this.$activityId));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getMeditationSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class q0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<MeditationSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q0(String str, String str2, Continuation<? super q0> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new q0(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<MeditationSample>> continuation) {
            return ((q0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<MeditationSample> meditationSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getMeditationSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(meditationSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.MeditationSample>");
                return (ArrayList) meditationSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getTreadmillSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class q1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends TreadmillSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q1(String str, Continuation<? super q1> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new q1(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends TreadmillSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<TreadmillSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<TreadmillSample>> continuation) {
            return ((q1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getTreadmillSamplesBy(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertTennisSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class q2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<TennisSample> $tennisSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q2(ArrayList<TennisSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super q2> continuation) {
            super(2, continuation);
            this.$tennisSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new q2(this.$tennisSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((q2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$tennisSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$tennisSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertTennisSamples(this.$tennisSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getCaloriesByActivityType$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class r extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $date;
        public final /* synthetic */ String $type;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public r(String str, String str2, Continuation<? super r> continuation) {
            super(2, continuation);
            this.$date = str;
            this.$type = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new r(this.$date, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((r) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getCaloriesByActivityType(this.$date, this.$type));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getMeditationSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class r0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends MeditationSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public r0(String str, Continuation<? super r0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new r0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends MeditationSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<MeditationSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<MeditationSample>> continuation) {
            return ((r0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getMeditationSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getWalkSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class r1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<WalkSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public r1(String str, String str2, Continuation<? super r1> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new r1(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<WalkSample>> continuation) {
            return ((r1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<WalkSample> walkSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getWalkSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(walkSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.WalkSample>");
                return (ArrayList) walkSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertTreadmillSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class r2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<TreadmillSample> $treadmillSamples;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public r2(ArrayList<TreadmillSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super r2> continuation) {
            super(2, continuation);
            this.$treadmillSamples = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new r2(this.$treadmillSamples, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((r2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$treadmillSamples.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$treadmillSamples.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertTreadmillSamples(this.$treadmillSamples);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getCaloriesForWeekByActivityNCategoryIDs$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class s extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ int $activityId;
        public final /* synthetic */ int $categoryId;
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public s(int i, int i2, String str, String str2, Continuation<? super s> continuation) {
            super(2, continuation);
            this.$categoryId = i;
            this.$activityId = i2;
            this.$startDate = str;
            this.$endDate = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new s(this.$categoryId, this.$activityId, this.$startDate, this.$endDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((s) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getCaloriesForWeekByActivityNCategoryIDs(this.$categoryId, this.$activityId, this.$startDate, this.$endDate));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getPhysicalActivitySamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class s0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<PhysicalActivitySample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public s0(String str, String str2, Continuation<? super s0> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new s0(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<PhysicalActivitySample>> continuation) {
            return ((s0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<PhysicalActivitySample> physicalActivitySamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getPhysicalActivitySamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(physicalActivitySamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.PhysicalActivitySample>");
                return (ArrayList) physicalActivitySamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getWalkSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class s1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends WalkSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public s1(String str, Continuation<? super s1> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new s1(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends WalkSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<WalkSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<WalkSample>> continuation) {
            return ((s1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getWalkSamplesBy(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertWalkSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class s2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<WalkSample> $walkSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public s2(ArrayList<WalkSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super s2> continuation) {
            super(2, continuation);
            this.$walkSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new s2(this.$walkSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((s2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$walkSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$walkSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertWalkSamples(this.$walkSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getCaloriesForWeekByType$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class t extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public final /* synthetic */ String $type;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public t(String str, String str2, String str3, Continuation<? super t> continuation) {
            super(2, continuation);
            this.$startDate = str;
            this.$endDate = str2;
            this.$type = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new t(this.$startDate, this.$endDate, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((t) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getCaloriesForWeekByType(this.$startDate, this.$endDate, this.$type));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getPhysicalActivitySamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class t0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PhysicalActivitySample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public t0(String str, Continuation<? super t0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new t0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends PhysicalActivitySample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<PhysicalActivitySample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<PhysicalActivitySample>> continuation) {
            return ((t0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getPhysicalActivitySamplesBy(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getWorkoutSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class t1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<WorkoutSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public t1(String str, String str2, Continuation<? super t1> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new t1(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<WorkoutSample>> continuation) {
            return ((t1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<WorkoutSample> workoutSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getWorkoutSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(workoutSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.WorkoutSample>");
                return (ArrayList) workoutSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertWorkoutSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class t2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<WorkoutSample> $workoutSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public t2(ArrayList<WorkoutSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super t2> continuation) {
            super(2, continuation);
            this.$workoutSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new t2(this.$workoutSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((t2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$workoutSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$workoutSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertWorkoutSamples(this.$workoutSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getCaloriesWithoutActivityMapping$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class u extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        public final /* synthetic */ String $endDate;
        public final /* synthetic */ String $startDate;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public u(String str, String str2, Continuation<? super u> continuation) {
            super(2, continuation);
            this.$startDate = str;
            this.$endDate = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new u(this.$startDate, this.$endDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Integer> continuation) {
            return ((u) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(WorkoutSessionRepository.this.b.getCaloriesWithoutActivityMapping(this.$startDate, this.$endDate));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getRowingMachineSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class u0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<RowingMachineSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public u0(String str, String str2, Continuation<? super u0> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new u0(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<RowingMachineSample>> continuation) {
            return ((u0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<RowingMachineSample> rowingMachineSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getRowingMachineSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(rowingMachineSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.RowingMachineSample>");
                return (ArrayList) rowingMachineSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getWorkoutSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class u1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends WorkoutSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public u1(String str, Continuation<? super u1> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new u1(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends WorkoutSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<WorkoutSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<WorkoutSample>> continuation) {
            return ((u1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getWorkoutSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertYogaSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class u2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<YogaSample> $yogaSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public u2(ArrayList<YogaSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super u2> continuation) {
            super(2, continuation);
            this.$yogaSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new u2(this.$yogaSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((u2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$yogaSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$yogaSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertYogaSamples(this.$yogaSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getClimbingSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class v extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<ClimbingSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public v(String str, String str2, Continuation<? super v> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new v(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<ClimbingSample>> continuation) {
            return ((v) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<ClimbingSample> climbingSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getClimbingSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(climbingSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.ClimbingSample>");
                return (ArrayList) climbingSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getRowingMachineSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class v0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends RowingMachineSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public v0(String str, Continuation<? super v0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new v0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends RowingMachineSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<RowingMachineSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<RowingMachineSample>> continuation) {
            return ((v0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getRowingMachineSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getYogaSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class v1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<YogaSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public v1(String str, String str2, Continuation<? super v1> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new v1(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<YogaSample>> continuation) {
            return ((v1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<YogaSample> yogaSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getYogaSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(yogaSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.YogaSample>");
                return (ArrayList) yogaSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository", f = "WorkoutSessionRepository.kt", i = {0, 0, 0}, l = {com.veryfit.multi.nativeprotocol.b.n1}, m = "postActivitySessionDataRequestInRecursion", n = {"this", "entityWorkoutSessions", DeviceKey.position}, s = {"L$0", "L$1", "I$0"})
    /* loaded from: classes2.dex */
    public static final class v2 extends ContinuationImpl {
        public int I$0;
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public v2(Continuation<? super v2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WorkoutSessionRepository.this.g(null, 0, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getClimbingSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class w extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends ClimbingSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public w(String str, Continuation<? super w> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new w(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends ClimbingSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<ClimbingSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<ClimbingSample>> continuation) {
            return ((w) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getClimbingSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getRunSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class w0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<RunSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public w0(String str, String str2, Continuation<? super w0> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new w0(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<RunSample>> continuation) {
            return ((w0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<RunSample> runSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getRunSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(runSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.RunSample>");
                return (ArrayList) runSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getYogaSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class w1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends YogaSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public w1(String str, Continuation<? super w1> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new w1(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends YogaSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<YogaSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<YogaSample>> continuation) {
            return ((w1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getYogaSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$saveUnSyncedSessionsToServer$2", f = "WorkoutSessionRepository.kt", i = {}, l = {315}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class w2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ long $timeMillis;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public w2(long j, Continuation<? super w2> continuation) {
            super(2, continuation);
            this.$timeMillis = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new w2(this.$timeMillis, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((w2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    List<EntityWorkoutSession> unSyncedSessions = WorkoutSessionRepository.this.b.getUnSyncedSessions();
                    WorkoutSessionRepository.this.c = this.$timeMillis;
                    if (!unSyncedSessions.isEmpty()) {
                        WorkoutSessionRepository workoutSessionRepository = WorkoutSessionRepository.this;
                        this.label = 1;
                        if (workoutSessionRepository.g(unSyncedSessions, 0, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getCycleSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class x extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<CyclingSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public x(String str, String str2, Continuation<? super x> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new x(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<CyclingSample>> continuation) {
            return ((x) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<CyclingSample> cyclingSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getCyclingSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(cyclingSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.CyclingSample>");
                return (ArrayList) cyclingSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getRunSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class x0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends RunSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public x0(String str, Continuation<? super x0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new x0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends RunSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<RunSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<RunSample>> continuation) {
            return ((x0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getRunSamplesBy(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertActivitySamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class x1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<ActivityDataSample> $samplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public x1(ArrayList<ActivityDataSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super x1> continuation) {
            super(2, continuation);
            this.$samplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new x1(this.$samplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((x1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$samplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sessionID = this.$samplesList.get(0).getSessionID();
                    Intrinsics.checkNotNull(sessionID);
                    if (sessionDAO.isSessionIdExists(sessionID) != 0) {
                        this.this$0.b.insertActivityDataSamples(this.$samplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$updateSession$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class x2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public x2(EntityWorkoutSession entityWorkoutSession, Continuation<? super x2> continuation) {
            super(2, continuation);
            this.$entityWorkoutSession = entityWorkoutSession;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new x2(this.$entityWorkoutSession, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((x2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                WorkoutSessionRepository.this.b.updateSession(this.$entityWorkoutSession);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getCyclingSamplesListBy$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class y extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends CyclingSample>>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public y(String str, Continuation<? super y> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new y(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends CyclingSample>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<CyclingSample>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super List<CyclingSample>> continuation) {
            return ((y) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getCyclingSamplesBySessionId(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSession$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class y0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityWorkoutSession>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public y0(String str, Continuation<? super y0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new y0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityWorkoutSession> continuation) {
            return ((y0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return WorkoutSessionRepository.this.b.getSessionBy(this.$session_id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertBadmintonSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class y1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<BadmintonSample> $badmintonSampleList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public y1(ArrayList<BadmintonSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super y1> continuation) {
            super(2, continuation);
            this.$badmintonSampleList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new y1(this.$badmintonSampleList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((y1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$badmintonSampleList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$badmintonSampleList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertBadmintonSamples(this.$badmintonSampleList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getDanceSamples$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class z extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<DanceSample>>, Object> {
        public final /* synthetic */ String $segment_id;
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public z(String str, String str2, Continuation<? super z> continuation) {
            super(2, continuation);
            this.$session_id = str;
            this.$segment_id = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new z(this.$session_id, this.$segment_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<DanceSample>> continuation) {
            return ((z) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<DanceSample> danceSamplesBySessionIdAndSegmentId = WorkoutSessionRepository.this.b.getDanceSamplesBySessionIdAndSegmentId(this.$session_id, this.$segment_id);
                Intrinsics.checkNotNull(danceSamplesBySessionIdAndSegmentId, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.database.entities.DanceSample>");
                return (ArrayList) danceSamplesBySessionIdAndSegmentId;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSessionDetails$1", f = "WorkoutSessionRepository.kt", i = {}, l = {ResultCode.SEND_CMD_FAILD}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class z0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $session_id;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public z0(String str, Continuation<? super z0> continuation) {
            super(2, continuation);
            this.$session_id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new z0(this.$session_id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((z0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (!WorkoutSessionRepository.this.e(this.$session_id)) {
                    WorkoutSessionRepository workoutSessionRepository = WorkoutSessionRepository.this;
                    String str = this.$session_id;
                    this.label = 1;
                    obj = workoutSessionRepository.getSession(str, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            EntityWorkoutSession entityWorkoutSession = (EntityWorkoutSession) obj;
            WorkoutSessionRepository workoutSessionRepository2 = WorkoutSessionRepository.this;
            String str2 = this.$session_id;
            String client_ref_id = entityWorkoutSession.getClient_ref_id();
            Intrinsics.checkNotNull(client_ref_id);
            workoutSessionRepository2.c(str2, client_ref_id, WorkoutSessionRepository.this.b(entityWorkoutSession.getActivity_type()));
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$insertBasketBallSampleList$2", f = "WorkoutSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class z1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<BasketBallSample> $basketBallSamplesList;
        public int label;
        public final /* synthetic */ WorkoutSessionRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public z1(ArrayList<BasketBallSample> arrayList, WorkoutSessionRepository workoutSessionRepository, Continuation<? super z1> continuation) {
            super(2, continuation);
            this.$basketBallSamplesList = arrayList;
            this.this$0 = workoutSessionRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new z1(this.$basketBallSamplesList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((z1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$basketBallSamplesList.size() > 0) {
                    SessionDAO sessionDAO = this.this$0.b;
                    String sess_id = this.$basketBallSamplesList.get(0).getSess_id();
                    Intrinsics.checkNotNull(sess_id);
                    if (sessionDAO.isSessionIdExists(sess_id) != 0) {
                        this.this$0.b.insertBasketBallSamples(this.$basketBallSamplesList);
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public WorkoutSessionRepository(Context context) {
        this.f2861a = context;
        SessionDAO sessionDAO = WorkoutSessionDatabase.getAppDatabase(context).sessionDAO();
        Intrinsics.checkNotNullExpressionValue(sessionDAO, "getAppDatabase(context).sessionDAO()");
        this.b = sessionDAO;
    }

    public /* synthetic */ WorkoutSessionRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static /* synthetic */ void getSessionsListFromServer$default(WorkoutSessionRepository workoutSessionRepository, Calendar calendar, Calendar calendar2, SuccessResultListener successResultListener, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            successResultListener = null;
        }
        workoutSessionRepository.getSessionsListFromServer(calendar, calendar2, successResultListener);
    }

    public final Object a(List<? extends PostActivitySessionDataRequest> list, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new c(list, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final ActivityType b(String str) {
        Intrinsics.checkNotNull(str);
        return ActivityType.valueOf(str);
    }

    public final void c(final String str, String str2, final ActivityType activityType) {
        int i3 = (BleApiManager.getInstance(this.f2861a) == null || BleApiManager.getInstance(this.f2861a).getBleApi() == null || !BleApiManager.getInstance(this.f2861a).getBleApi().getDeviceSupportedFeatures().isGenericActivityDataSampleSupported()) ? 0 : 1;
        String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
        Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
        TraqConfigApi.getSessionOverallDataFromServer(i3, Integer.parseInt(userDeviceID), str2, activityType, new CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSampleDetailsForServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable PostActivitySessionHeaderResponse postActivitySessionHeaderResponse) {
                if (postActivitySessionHeaderResponse == null || postActivitySessionHeaderResponse.requestData == null) {
                    return;
                }
                e.e(GlobalScope.INSTANCE, null, null, new WorkoutSessionRepository$getSampleDetailsForServer$1$onSuccess$1(WorkoutSessionRepository.this, postActivitySessionHeaderResponse, str, activityType, null), 3, null);
            }
        });
    }

    public final boolean d(String str) {
        return this.b.isClientRefIdExists(str) > 0;
    }

    @Nullable
    public final Object deleteSession(@NotNull EntityWorkoutSession entityWorkoutSession, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new a(entityWorkoutSession, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object deleteWorkoutSession(@NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new b(null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final boolean e(String str) {
        return this.b.isSampleDataExists(str) > 0;
    }

    public final void f() {
        long currentTimeMillis = System.currentTimeMillis();
        int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(currentTimeMillis - this.c);
        LogHelper.d("timedifference", "starttime**  " + this.c + "endtime**  " + currentTimeMillis + "minutes*** " + minutes);
        if (minutes < 5) {
            ActivityDataSyncListner activityDataSyncListner = this.d;
            Intrinsics.checkNotNull(activityDataSyncListner);
            activityDataSyncListner.onActivityDataSyncComplete();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object g(final java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession> r7, final int r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.v2
            if (r0 == 0) goto L13
            r0 = r9
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$v2 r0 = (com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.v2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$v2 r0 = new com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$v2
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            int r8 = r0.I$0
            java.lang.Object r7 = r0.L$1
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r0 = r0.L$0
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r0 = (com.coveiot.android.activitymodes.repository.WorkoutSessionRepository) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L9c
        L33:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3b:
            kotlin.ResultKt.throwOnFailure(r9)
            int r9 = r7.size()
            if (r8 >= r9) goto La7
            java.lang.Object r9 = r7.get(r8)
            com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r9 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r9
            int r9 = r9.getSession_duration()
            if (r9 <= 0) goto Laa
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r2 = "entitysession "
            r9.append(r2)
            java.lang.Object r2 = r7.get(r8)
            com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r2 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r2
            long r4 = r2.getStart_time()
            r9.append(r4)
            java.lang.String r2 = "endtime "
            r9.append(r2)
            java.lang.Object r2 = r7.get(r8)
            com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r2 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r2
            long r4 = r2.getEnd_time()
            r9.append(r4)
            java.lang.String r9 = r9.toString()
            java.lang.String r2 = "WorkoutRepo"
            com.coveiot.utils.utility.LogHelper.d(r2, r9)
            com.coveiot.android.activitymodes.repository.Formator$Companion r9 = com.coveiot.android.activitymodes.repository.Formator.Companion
            java.lang.Object r2 = r7.get(r8)
            com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r2 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r2
            android.content.Context r4 = r6.f2861a
            r0.L$0 = r6
            r0.L$1 = r7
            r0.I$0 = r8
            r0.label = r3
            java.lang.Object r9 = r9.getPostActivitySessionDataRequest(r2, r4, r0)
            if (r9 != r1) goto L9b
            return r1
        L9b:
            r0 = r6
        L9c:
            com.coveiot.coveaccess.activitysession.PostActivitySessionDataRequest r9 = (com.coveiot.coveaccess.activitysession.PostActivitySessionDataRequest) r9
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$postActivitySessionDataRequestInRecursion$2 r1 = new com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$postActivitySessionDataRequestInRecursion$2
            r1.<init>()
            com.coveiot.coveaccess.activitysession.TraqConfigApi.sendSessionHeaderDataToServer(r9, r1)
            goto Laa
        La7:
            r6.f()
        Laa:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.g(java.util.List, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object getActivitiesByActivityAndCategoryID(@NotNull Continuation<? super List<EntityWorkoutSession>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new d(null), continuation);
    }

    @Nullable
    public final Object getActivitiesByCategoryID(@NotNull Continuation<? super List<EntityWorkoutSession>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new e(null), continuation);
    }

    @Nullable
    public final Object getActivityDataSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<ActivityDataSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new f(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getActivityDataSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<ActivityDataSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new g(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<ActivityDataSample>> getActivityDataSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getActivityDataSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final ActivityDataSyncListner getActivityDataSyncListner() {
        return this.d;
    }

    @Nullable
    public final Object getActivitySamplesListBy(@NotNull String str, @NotNull Continuation<? super List<ActivityDataSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new h(str, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final FitnessChallengeStatsData getAllSessionsByDate(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllSessionDataByDate(date, macAddress);
    }

    @Nullable
    public final Object getBadmintonSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<BadmintonSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new i(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getBadmintonSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<BadmintonSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new j(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<BadmintonSample>> getBadmintonSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getBadmintonSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getBasketBallSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<BasketBallSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new k(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getBasketBallSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<BasketBallSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new l(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<BasketBallSample>> getBasketBallSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getBasketBallSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getBestSessionByActivityAndCategoryID(int i3, int i4, @NotNull Continuation<? super EntityWorkoutSession> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new m(i3, i4, null), continuation);
    }

    @Nullable
    public final Object getBestSessionByActivityType(@NotNull String str, @NotNull Continuation<? super EntityWorkoutSession> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new n(str, null), continuation);
    }

    @Nullable
    public final Object getBestSessionByCategoryID(int i3, @NotNull Continuation<? super EntityWorkoutSession> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new p(i3, null), continuation);
    }

    @Nullable
    public final Object getCaloriesByActivityNCategoryIDs(@NotNull String str, int i3, int i4, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new q(str, i3, i4, null), continuation);
    }

    @Nullable
    public final Object getCaloriesByActivityType(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new r(str, str2, null), continuation);
    }

    @Nullable
    public final Object getCaloriesForWeekByActivityNCategoryIDs(int i3, int i4, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new s(i3, i4, str, str2, null), continuation);
    }

    @Nullable
    public final Object getCaloriesForWeekByType(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new t(str, str2, str3, null), continuation);
    }

    @Nullable
    public final Object getCaloriesWithoutActivityMapping(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new u(str, str2, null), continuation);
    }

    @Nullable
    public final Object getClimbingSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<ClimbingSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new v(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getClimbingSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<ClimbingSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new w(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<ClimbingSample>> getClimbingSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getClimbingSamplesLiveDataBy(session_id);
    }

    @NotNull
    public final Context getContext() {
        return this.f2861a;
    }

    @Nullable
    public final Object getCycleSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<CyclingSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new x(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getCyclingSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<CyclingSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new y(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<CyclingSample>> getCyclingSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getCyclingSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getDanceSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<DanceSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new z(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getDanceSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<DanceSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new a0(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<DanceSample>> getDanceSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getDanceSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getDistanceByActivityNCategoryIDs(@NotNull String str, int i3, int i4, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new b0(str, i3, i4, null), continuation);
    }

    @Nullable
    public final Object getDistanceByType(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new c0(str, str2, null), continuation);
    }

    @Nullable
    public final Object getDistanceForWeekByActivityNCategoryIDs(int i3, int i4, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new d0(i3, i4, str, str2, null), continuation);
    }

    @Nullable
    public final Object getDistanceForWeekByType(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new e0(str, str2, str3, null), continuation);
    }

    @Nullable
    public final Object getDistanceWithoutActivityMapping(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new f0(str, str2, null), continuation);
    }

    @Nullable
    public final Object getEllipticalSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<EllipticalSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new g0(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getEllipticalSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<EllipticalSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new h0(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<EllipticalSample>> getEllipticalSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getEllipticalSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getFootBallSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<FootballSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new i0(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getFootBallSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<FootballSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new j0(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<FootballSample>> getFootBallSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getFootballSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getFreeExerciseSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<FreeExerciseSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new k0(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getFreeExerciseSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<FreeExerciseSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new l0(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<FreeExerciseSample>> getFreeExerciseSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getFreeExerciseSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getHikingSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<HikingSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new m0(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getHikingSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<HikingSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new n0(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<HikingSample>> getHikingSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getHikingSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final EntityWorkoutSession getLastSessionData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getLastSessionData(macAddress);
    }

    @Nullable
    public final EntityWorkoutSession getLastSessionDataByDate(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getLastSessionDataByDate(date, macAddress);
    }

    @NotNull
    public final LiveData<EntityWorkoutSession> getLastSessionLiveData() {
        return this.b.getLastSessionLiveData();
    }

    @Nullable
    public final Object getLatestSession(@NotNull Continuation<? super EntityWorkoutSession> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new o0(null), continuation);
    }

    @Nullable
    public final Object getLatestSessionByActivityType(@NotNull String str, @NotNull Continuation<? super EntityWorkoutSession> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new p0(str, null), continuation);
    }

    @Nullable
    public final Object getMeditationSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<MeditationSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new q0(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getMeditationSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<MeditationSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new r0(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<MeditationSample>> getMeditationSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getMeditationSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getPhysicalActivitySamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<PhysicalActivitySample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new s0(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getPhysicalActivitySamplesListBy(@NotNull String str, @NotNull Continuation<? super List<PhysicalActivitySample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new t0(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<PhysicalActivitySample>> getPhysicalActivitySamplesLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getPhysicalActivitySamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getRowingMachineSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<RowingMachineSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new u0(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getRowingMachineSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<RowingMachineSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new v0(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<RowingMachineSample>> getRowingMachineSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getRowingMachineSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getRunSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<RunSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new w0(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getRunSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<RunSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new x0(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<RunSample>> getRunSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getRunSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getSession(@NotNull String str, @NotNull Continuation<? super EntityWorkoutSession> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new y0(str, null), 3, null);
        return b3.await(continuation);
    }

    public final void getSessionDetails(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new z0(session_id, null), 3, null);
    }

    @NotNull
    public final LiveData<EntityWorkoutSession> getSessionLiveData(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getSessionLiveDataBy(session_id);
    }

    @Nullable
    public final Object getSessionSegmentsList(@NotNull String str, @NotNull Continuation<? super List<EntityWorkoutSessionSegment>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new a1(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<EntityWorkoutSessionSegment>> getSessionSegmentsListLiveData(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getSessionSegmentListLiveDataBy(session_id);
    }

    @Nullable
    public final Object getSessionsList(@NotNull Continuation<? super List<EntityWorkoutSession>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new b1(null), 3, null);
        return b3.await(continuation);
    }

    public final void getSessionsListFromServer(@NotNull Calendar fromDate, @NotNull Calendar toDate, @Nullable final SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        TraqConfigApi.getSessionHeaderListFromServer((BleApiManager.getInstance(this.f2861a) == null || BleApiManager.getInstance(this.f2861a).getBleApi() == null || !BleApiManager.getInstance(this.f2861a).getBleApi().getDeviceSupportedFeatures().isGenericActivityDataSampleSupported()) ? 0 : 1, AppUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), AppUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), PreferenceManager.getInstance().getUserDeviceID(), new CoveApiListener<GetActivitySessionHeaderResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSessionsListFromServer$1

            @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSessionsListFromServer$1$onSuccess$1", f = "WorkoutSessionRepository.kt", i = {}, l = {b.M2}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes2.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ List<PostActivitySessionDataRequest> $postActivitySessionDataRequestList;
                public int label;
                public final /* synthetic */ WorkoutSessionRepository this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(WorkoutSessionRepository workoutSessionRepository, List<PostActivitySessionDataRequest> list, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = workoutSessionRepository;
                    this.$postActivitySessionDataRequestList = list;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$postActivitySessionDataRequestList, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object a2;
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    try {
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            WorkoutSessionRepository workoutSessionRepository = this.this$0;
                            List<PostActivitySessionDataRequest> list = this.$postActivitySessionDataRequestList;
                            Intrinsics.checkNotNull(list);
                            this.label = 1;
                            a2 = workoutSessionRepository.a(list, this);
                            if (a2 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            ResultKt.throwOnFailure(obj);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                SuccessResultListener successResultListener2 = SuccessResultListener.this;
                if (successResultListener2 != null) {
                    successResultListener2.onError(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetActivitySessionHeaderResponse getActivitySessionHeaderResponse) {
                if (getActivitySessionHeaderResponse != null) {
                    fitnessActivitySessions fitnessactivitysessions = getActivitySessionHeaderResponse.requestData;
                    e.e(GlobalScope.INSTANCE, null, null, new a(this, fitnessactivitysessions != null ? fitnessactivitysessions.getFitnessActivitySessions() : null, null), 3, null);
                }
                SuccessResultListener successResultListener2 = SuccessResultListener.this;
                if (successResultListener2 != null) {
                    successResultListener2.onSuccess();
                }
            }
        });
    }

    @NotNull
    public final LiveData<List<EntityWorkoutSession>> getSessionsListLiveData() {
        return this.b.getSessionsListLiveData();
    }

    @NotNull
    public final LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataByDescStartTime() {
        return this.b.getSessionsListLiveDataByDescStartTime();
    }

    @NotNull
    public final LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataFilterBy(@NotNull String activityType) {
        Intrinsics.checkNotNullParameter(activityType, "activityType");
        return this.b.getSessionsListLiveDataFilterBy(activityType);
    }

    @Nullable
    public final Object getSessionsOfParticularDay(@NotNull String str, @NotNull Continuation<? super List<EntityWorkoutSession>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new c1(str, null), continuation);
    }

    @Nullable
    public final Object getSingleDayCalorieWithoutActivityMapping(@NotNull String str, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new d1(str, null), continuation);
    }

    @Nullable
    public final Object getSingleDayDistanceWithoutActivityMapping(@NotNull String str, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new e1(str, null), continuation);
    }

    @Nullable
    public final Object getSingleDayStepWithoutActivityMapping(@NotNull String str, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new f1(str, null), continuation);
    }

    @Nullable
    public final Object getSkippingSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<SkippingSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new g1(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getSkippingSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<SkippingSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new h1(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<SkippingSample>> getSkippingSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getSkippingSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getStepsByActivityNCategoryIDs(@NotNull String str, int i3, int i4, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new i1(str, i3, i4, null), continuation);
    }

    @Nullable
    public final Object getStepsByActivityType(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new j1(str, str2, null), continuation);
    }

    @Nullable
    public final Object getStepsForWeekByActivityNCategoryIDs(int i3, int i4, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new k1(i3, i4, str, str2, null), continuation);
    }

    @Nullable
    public final Object getStepsForWeekByType(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new l1(str, str2, str3, null), continuation);
    }

    @Nullable
    public final Object getStepsWithoutActivityMapping(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new m1(str, str2, null), continuation);
    }

    @Nullable
    public final Object getTennisSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<TennisSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new n1(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getTennisSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<TennisSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new o1(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<TennisSample>> getTennisSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getTennisSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getTreadmillSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<TreadmillSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new p1(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getTreadmillSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<TreadmillSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new q1(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<TreadmillSample>> getTreadmillSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getTreadmillSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getWalkSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<WalkSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new r1(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getWalkSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<WalkSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new s1(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<WalkSample>> getWalkSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getWalkSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getWorkoutSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<WorkoutSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new t1(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getWorkoutSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<WorkoutSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new u1(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<WorkoutSample>> getWorkoutSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getWorkoutSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object getYogaSamples(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super ArrayList<YogaSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new v1(str, str2, null), 3, null);
        return b3.await(continuation);
    }

    @Nullable
    public final Object getYogaSamplesListBy(@NotNull String str, @NotNull Continuation<? super List<YogaSample>> continuation) {
        Deferred b3;
        b3 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new w1(str, null), 3, null);
        return b3.await(continuation);
    }

    @NotNull
    public final LiveData<List<YogaSample>> getYogaSamplesListLiveDataBy(@NotNull String session_id) {
        Intrinsics.checkNotNullParameter(session_id, "session_id");
        return this.b.getYogaSamplesLiveDataBy(session_id);
    }

    @Nullable
    public final Object insertActivitySamples(@NotNull ArrayList<ActivityDataSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new x1(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertBadmintonSampleList(@NotNull ArrayList<BadmintonSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new y1(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertBasketBallSampleList(@NotNull ArrayList<BasketBallSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new z1(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertClimbingSampleList(@NotNull ArrayList<ClimbingSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new a2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertCyclingSampleList(@NotNull ArrayList<CyclingSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new b2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertDanceSampleList(@NotNull ArrayList<DanceSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new c2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertEllipticalSampleList(@NotNull ArrayList<EllipticalSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new d2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertFootBallSampleList(@NotNull ArrayList<FootballSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new e2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertFreeExerciseSampleList(@NotNull ArrayList<FreeExerciseSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new f2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertGenericActivitySampleList(@NotNull ArrayList<ActivityDataSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new g2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertHikingSampleList(@NotNull ArrayList<HikingSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new h2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertMeditationSampleList(@NotNull ArrayList<MeditationSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new i2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertPhysicalActivitySamples(@NotNull ArrayList<PhysicalActivitySample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new j2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertRowingMachineSampleList(@NotNull ArrayList<RowingMachineSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new k2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertRunSampleList(@NotNull ArrayList<RunSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new l2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertSession(@NotNull EntityWorkoutSession entityWorkoutSession, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new m2(entityWorkoutSession, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertSessionSegment(@NotNull EntityWorkoutSessionSegment entityWorkoutSessionSegment, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new n2(entityWorkoutSessionSegment, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertSessionSegmentList(@NotNull ArrayList<EntityWorkoutSessionSegment> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new o2(arrayList, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertSkippingSampleList(@NotNull ArrayList<SkippingSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new p2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertTennisSampleList(@NotNull ArrayList<TennisSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new q2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertTreadmillSampleList(@NotNull ArrayList<TreadmillSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new r2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertWalkSampleList(@NotNull ArrayList<WalkSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new s2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertWorkoutSampleList(@NotNull ArrayList<WorkoutSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new t2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertYogaSampleList(@NotNull ArrayList<YogaSample> arrayList, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new u2(arrayList, this, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object saveUnSyncedSessionsToServer(long j3, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new w2(j3, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void setActivityDataSyncListner(@Nullable ActivityDataSyncListner activityDataSyncListner) {
        this.d = activityDataSyncListner;
    }

    @Nullable
    public final Object updateSession(@NotNull EntityWorkoutSession entityWorkoutSession, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new x2(entityWorkoutSession, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object getBestSessionByActivityType(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super EntityWorkoutSession> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new o(str, str2, null), continuation);
    }

    @NotNull
    public final LiveData<EntityWorkoutSession> getLastSessionLiveData(@NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        return this.b.getLastSessionLiveDataForConnectedDevice(serialNo);
    }

    @NotNull
    public final LiveData<List<EntityWorkoutSession>> getSessionsListLiveData(@NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        return this.b.getSessionsListLiveDataForConnectedDevice(serialNo);
    }

    @NotNull
    public final LiveData<List<EntityWorkoutSession>> getSessionsListLiveDataFilterBy(@NotNull String activityType, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(activityType, "activityType");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        return this.b.getSessionsListLiveDataFilterBy(activityType, serialNo);
    }
}
