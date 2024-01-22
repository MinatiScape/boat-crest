package com.coveiot.android.activitymodes.services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.ActivityWorkoutFeedback;
import com.coveiot.android.activitymodes.activities.ActivityWorkoutSession;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment;
import com.coveiot.android.activitymodes.database.entities.RunSample;
import com.coveiot.android.activitymodes.database.entities.SampleData;
import com.coveiot.android.activitymodes.database.entities.WalkSample;
import com.coveiot.android.activitymodes.models.HeartRateSample;
import com.coveiot.android.activitymodes.models.LocationSample;
import com.coveiot.android.activitymodes.models.StepsSample;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.IndoorOutdoor;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.eventmodels.LiveHeartRate;
import com.coveiot.utils.eventmodels.LiveSteps;
import com.coveiot.utils.eventmodels.OnDeviceConnected;
import com.coveiot.utils.eventmodels.OnDeviceDisconnected;
import com.coveiot.utils.eventmodels.OnRunEnd;
import com.coveiot.utils.eventmodels.OnRunPaused;
import com.coveiot.utils.eventmodels.OnRunResumed;
import com.coveiot.utils.eventmodels.OnRunStarted;
import com.coveiot.utils.eventmodels.OnStopSession;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.jieli.jl_rcsp.constant.Command;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorkoutSessionService extends Service {
    public int A;
    public float B;
    public int D;
    public int E;
    public long F;
    public long G;
    public long H;
    public long I;
    @Nullable
    public Timer L;
    @Nullable
    public CountDownTimer M;
    public int Q;
    public int R;
    @Nullable
    public LocationSample S;
    public int T;
    public boolean U;
    public boolean V;
    public int i;
    public int k;
    public Handler l;
    public boolean m;
    public FusedLocationProviderClient p;
    public LocationCallback q;
    @Nullable
    public RunSessionCallbacks t;
    public int w;
    public WorkoutSessionRepository workoutSessionRepository;
    public int x;
    public int y;
    public int z;
    public long h = -1;
    @NotNull
    public final String j = "WorkoutSessionService";
    @NotNull
    public final String n = "leonardo_run_session_tracking_service";
    public final int o = 2;
    public final Bus r = CoveEventBusManager.getInstance().getEventBus();
    @NotNull
    public IBinder s = new TrackSessionServiceBinder();
    @NotNull
    public ActivityMode u = ActivityMode.WALK;
    @NotNull
    public IndoorOutdoor v = IndoorOutdoor.OUTDOOR;
    @NotNull
    public String C = "";
    @NotNull
    public String J = "";
    @NotNull
    public String K = "";
    @NotNull
    public final List<HeartRateSample> N = new ArrayList();
    @NotNull
    public final ArrayList<StepsSample> O = new ArrayList<>();
    @NotNull
    public final ArrayList<LocationSample> P = new ArrayList<>();
    public int W = -1;
    public int X = -1;
    public int Y = -1;

    /* loaded from: classes2.dex */
    public interface RunSessionCallbacks {
        void showFeedbackScreen(@NotNull String str);

        void updateDuration(int i);

        void updateHeartRateInfo(int i, int i2, int i3);

        void updatePace(@NotNull String str);

        void updateStepsInfo(int i, int i2, float f);
    }

    /* loaded from: classes2.dex */
    public class TrackSessionServiceBinder extends Binder {
        public TrackSessionServiceBinder() {
        }

        @NotNull
        public final WorkoutSessionService getService() {
            return WorkoutSessionService.this;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ActivityMode.values().length];
            try {
                iArr[ActivityMode.WALK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ActivityMode.RUN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.services.WorkoutSessionService$deleteSessionFromDB$1", f = "WorkoutSessionService.kt", i = {}, l = {472, 474}, m = "invokeSuspend", n = {}, s = {})
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
                WorkoutSessionRepository workoutSessionRepository = WorkoutSessionService.this.getWorkoutSessionRepository();
                String str = WorkoutSessionService.this.J;
                this.label = 1;
                obj = workoutSessionRepository.getSession(str, this);
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
            EntityWorkoutSession entityWorkoutSession = (EntityWorkoutSession) obj;
            if (entityWorkoutSession != null) {
                WorkoutSessionRepository workoutSessionRepository2 = WorkoutSessionService.this.getWorkoutSessionRepository();
                this.label = 2;
                if (workoutSessionRepository2.deleteSession(entityWorkoutSession, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.services.WorkoutSessionService$endSession$1", f = "WorkoutSessionService.kt", i = {}, l = {253, 257, 259, 260, 261}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $sessionDestroyed;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(boolean z, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$sessionDestroyed = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$sessionDestroyed, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0065 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0076 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0089 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 5
                r3 = 4
                r4 = 3
                r5 = 2
                r6 = 1
                r7 = 0
                if (r1 == 0) goto L34
                if (r1 == r6) goto L30
                if (r1 == r5) goto L2c
                if (r1 == r4) goto L28
                if (r1 == r3) goto L24
                if (r1 != r2) goto L1c
                kotlin.ResultKt.throwOnFailure(r9)
                goto L8a
            L1c:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L24:
                kotlin.ResultKt.throwOnFailure(r9)
                goto L77
            L28:
                kotlin.ResultKt.throwOnFailure(r9)
                goto L66
            L2c:
                kotlin.ResultKt.throwOnFailure(r9)
                goto L5b
            L30:
                kotlin.ResultKt.throwOnFailure(r9)
                goto L4c
            L34:
                kotlin.ResultKt.throwOnFailure(r9)
                com.coveiot.android.activitymodes.services.WorkoutSessionService r9 = com.coveiot.android.activitymodes.services.WorkoutSessionService.this
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r9 = r9.getWorkoutSessionRepository()
                com.coveiot.android.activitymodes.services.WorkoutSessionService r1 = com.coveiot.android.activitymodes.services.WorkoutSessionService.this
                com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment r1 = com.coveiot.android.activitymodes.services.WorkoutSessionService.access$getEntityWorkoutSessionSegment(r1)
                r8.label = r6
                java.lang.Object r9 = r9.insertSessionSegment(r1, r8)
                if (r9 != r0) goto L4c
                return r0
            L4c:
                boolean r9 = r8.$sessionDestroyed
                if (r9 == 0) goto L5b
                com.coveiot.android.activitymodes.services.WorkoutSessionService r9 = com.coveiot.android.activitymodes.services.WorkoutSessionService.this
                r8.label = r5
                java.lang.Object r9 = com.coveiot.android.activitymodes.services.WorkoutSessionService.access$insertSamplesToDB(r9, r8)
                if (r9 != r0) goto L5b
                return r0
            L5b:
                com.coveiot.android.activitymodes.services.WorkoutSessionService r9 = com.coveiot.android.activitymodes.services.WorkoutSessionService.this
                r8.label = r4
                java.lang.Object r9 = com.coveiot.android.activitymodes.services.WorkoutSessionService.access$getEntityWorkoutSession(r9, r7, r8)
                if (r9 != r0) goto L66
                return r0
            L66:
                com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r9 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r9
                com.coveiot.android.activitymodes.services.WorkoutSessionService r1 = com.coveiot.android.activitymodes.services.WorkoutSessionService.this
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r1 = r1.getWorkoutSessionRepository()
                r8.label = r3
                java.lang.Object r9 = r1.updateSession(r9, r8)
                if (r9 != r0) goto L77
                return r0
            L77:
                com.coveiot.android.activitymodes.services.WorkoutSessionService r9 = com.coveiot.android.activitymodes.services.WorkoutSessionService.this
                com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r9 = r9.getWorkoutSessionRepository()
                long r3 = java.lang.System.currentTimeMillis()
                r8.label = r2
                java.lang.Object r9 = r9.saveUnSyncedSessionsToServer(r3, r8)
                if (r9 != r0) goto L8a
                return r0
            L8a:
                com.coveiot.android.activitymodes.services.WorkoutSessionService r9 = com.coveiot.android.activitymodes.services.WorkoutSessionService.this
                com.coveiot.android.activitymodes.services.WorkoutSessionService.access$setTotalHeartRate$p(r9, r7)
                com.coveiot.android.activitymodes.services.WorkoutSessionService r9 = com.coveiot.android.activitymodes.services.WorkoutSessionService.this
                com.coveiot.android.activitymodes.services.WorkoutSessionService.access$setTotalSampleCount$p(r9, r7)
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.services.WorkoutSessionService.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.services.WorkoutSessionService", f = "WorkoutSessionService.kt", i = {0, 0, 1}, l = {549, com.veryfit.multi.nativeprotocol.b.l2}, m = "getEntityWorkoutSession", n = {"this", "entityWorkoutSession", "entityWorkoutSession"}, s = {"L$0", "L$1", "L$0"})
    /* loaded from: classes2.dex */
    public static final class c extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
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
            return WorkoutSessionService.this.i(false, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.services.WorkoutSessionService", f = "WorkoutSessionService.kt", i = {0, 0, 0, 1, 1, 1}, l = {815, 828}, m = "getTimeSpentPerHeartRateZone", n = {"timeSpentHeartRateZone", "heartRateZoneRanges", "sampleRate", "timeSpentHeartRateZone", "heartRateZoneRanges", "sampleRate"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0"})
    /* loaded from: classes2.dex */
    public static final class d extends ContinuationImpl {
        public int I$0;
        public Object L$0;
        public Object L$1;
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
            return WorkoutSessionService.this.m(this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.services.WorkoutSessionService", f = "WorkoutSessionService.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {657, 669}, m = "getTotalSessionSampleValues", n = {"stepsSample", "totalSteps", "totalCalories", "totalDistance", "stepsSample", "totalSteps", "totalCalories", "totalDistance"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    /* loaded from: classes2.dex */
    public static final class e extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;

        public e(Continuation<? super e> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WorkoutSessionService.this.n(this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.services.WorkoutSessionService$onRunPaused$1", f = "WorkoutSessionService.kt", i = {}, l = {215, Command.CMD_SET_DEVICE_STORAGE}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
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
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WorkoutSessionRepository workoutSessionRepository = WorkoutSessionService.this.getWorkoutSessionRepository();
                EntityWorkoutSessionSegment j = WorkoutSessionService.this.j();
                this.label = 1;
                if (workoutSessionRepository.insertSessionSegment(j, this) == coroutine_suspended) {
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
            WorkoutSessionService workoutSessionService = WorkoutSessionService.this;
            this.label = 2;
            if (workoutSessionService.p(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.services.WorkoutSessionService$onRunStarted$1", f = "WorkoutSessionService.kt", i = {}, l = {186, 186}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
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
            WorkoutSessionRepository workoutSessionRepository;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                workoutSessionRepository = WorkoutSessionService.this.getWorkoutSessionRepository();
                WorkoutSessionService workoutSessionService = WorkoutSessionService.this;
                this.L$0 = workoutSessionRepository;
                this.label = 1;
                obj = workoutSessionService.i(true, this);
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
                workoutSessionRepository = (WorkoutSessionRepository) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = null;
            this.label = 2;
            if (workoutSessionRepository.insertSession((EntityWorkoutSession) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ void e(WorkoutSessionService workoutSessionService, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        workoutSessionService.d(z, z2);
    }

    public final void a() {
        this.y = 0;
        this.z = 0;
        this.B = 0.0f;
        this.S = null;
    }

    public final void b() {
        if (Build.VERSION.SDK_INT >= 26) {
            String string = getString(R.string.run_session_service_channel);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…_session_service_channel)");
            String string2 = getString(R.string.run_session_channel_description);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…sion_channel_description)");
            NotificationChannel notificationChannel = new NotificationChannel(this.n, string, 1);
            notificationChannel.setDescription(string2);
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
        }
    }

    public final void c() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new a(null), 3, null);
    }

    public final void d(boolean z, boolean z2) {
        new PreferenceManager(this).setRunSessionActive(false);
        if (z) {
            Log.d(this.j, "Session saved");
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new b(z2, null), 3, null);
        } else {
            Log.d(this.j, "Session discarded");
            c();
            this.Q = 0;
            this.R = 0;
        }
        u();
        stopForeground(true);
        stopSelf();
    }

    public final int f() {
        int i;
        int i2 = this.R;
        if (i2 == 0 || (i = this.Q) == 0) {
            return 0;
        }
        return i / i2;
    }

    public final String g() {
        if (this.A == 0 || this.E == 0) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        String str = this.j;
        Log.d(str, "distance: " + this.A + " sessionTimeElapsed: " + this.E);
        int i = (int) (((float) this.E) / (((float) this.A) / 100000.0f));
        int i2 = i / 60;
        int i3 = i % 60;
        if (i2 <= 0) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        if (i2 >= 99) {
            return "99'99''";
        }
        return i2 + '\'' + i3 + "''";
    }

    @NotNull
    public final List<HeartRateSample> getHrSamples() {
        return this.N;
    }

    public final int getInitialSteps() {
        return this.W;
    }

    @NotNull
    public final ArrayList<LocationSample> getLocationSamples() {
        return this.P;
    }

    public final int getRunStrideLegth() {
        return this.Y;
    }

    public final int getStepsBeforePaused() {
        return this.T;
    }

    @NotNull
    public final ArrayList<StepsSample> getStepsSamples() {
        return this.O;
    }

    public final int getWalkStrideLegth() {
        return this.X;
    }

    @NotNull
    public final WorkoutSessionRepository getWorkoutSessionRepository() {
        WorkoutSessionRepository workoutSessionRepository = this.workoutSessionRepository;
        if (workoutSessionRepository != null) {
            return workoutSessionRepository;
        }
        Intrinsics.throwUninitializedPropertyAccessException("workoutSessionRepository");
        return null;
    }

    public final String h() {
        ProfileData userDetails = SessionManager.getInstance(this).getUserDetails();
        String userDeviceID = com.coveiot.coveaccess.prefs.PreferenceManager.getInstance().getUserDeviceID();
        StringBuilder sb = new StringBuilder();
        sb.append(userDetails.getUserId());
        sb.append(';');
        sb.append(userDeviceID);
        sb.append(';');
        String lowerCase = this.u.toString().toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        sb.append(lowerCase);
        sb.append(';');
        sb.append(AppUtils.formatDateUTC(new Date(this.H), UtilConstants.SERVER_TIME_FORMAT2));
        sb.append(';');
        sb.append(AppUtils.formatDateUTC(new Date(this.I), UtilConstants.SERVER_TIME_FORMAT2));
        sb.append(';');
        sb.append(this.R);
        String sb2 = sb.toString();
        String str = this.j;
        Log.d(str, "clientRefString: " + sb2);
        return AppUtils.convertStringToMD5(sb2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x012b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x012c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object i(boolean r9, kotlin.coroutines.Continuation<? super com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession> r10) {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.services.WorkoutSessionService.i(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean isDiconnectedWhenPaused() {
        return this.U;
    }

    public final boolean isPausedManually() {
        return this.V;
    }

    public final EntityWorkoutSessionSegment j() {
        EntityWorkoutSessionSegment entityWorkoutSessionSegment = new EntityWorkoutSessionSegment();
        entityWorkoutSessionSegment.setSess_id(this.J);
        entityWorkoutSessionSegment.setSegment_id(this.K);
        entityWorkoutSessionSegment.setSegment_duration(this.D);
        entityWorkoutSessionSegment.setStart_time(this.F);
        entityWorkoutSessionSegment.setEnd_time(this.G);
        return entityWorkoutSessionSegment;
    }

    public final float k(int i) {
        int i2 = this.E;
        if (i2 == 0 || i == 0) {
            return 0.0f;
        }
        return i2 / (i / 100000.0f);
    }

    public final ArrayList<RunSample> l() {
        ArrayList<RunSample> arrayList = new ArrayList<>();
        int size = this.O.size();
        for (int i = 0; i < size; i++) {
            StepsSample stepsSample = this.O.get(i);
            Intrinsics.checkNotNullExpressionValue(stepsSample, "stepsSamples[i]");
            StepsSample stepsSample2 = stepsSample;
            LocationSample locationSample = this.P.get(i);
            SampleData sampleData = new SampleData();
            RunSample runSample = new RunSample();
            runSample.setStepCount(stepsSample2.getStepCount());
            runSample.setSess_id(this.J);
            runSample.setSeg_id(this.K);
            sampleData.setCalories(stepsSample2.getCalories());
            sampleData.setDistance(stepsSample2.getDistance());
            sampleData.setHr_value(this.N.get(i).getHr_value());
            double d2 = 0.0d;
            sampleData.setLatitude(locationSample != null ? locationSample.getLatitude() : 0.0d);
            if (locationSample != null) {
                d2 = locationSample.getLongitude();
            }
            sampleData.setLongitude(d2);
            sampleData.setTimeStamp(stepsSample2.getTimeStamp());
            runSample.setSampleData(sampleData);
            arrayList.add(runSample);
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x015e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m(kotlin.coroutines.Continuation<? super com.coveiot.android.activitymodes.database.models.TimeSpentHeartRateZone> r11) {
        /*
            Method dump skipped, instructions count: 497
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.services.WorkoutSessionService.m(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00af A[LOOP:0: B:25:0x00a9->B:27:0x00af, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0112 A[LOOP:1: B:34:0x010c->B:36:0x0112, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object n(kotlin.coroutines.Continuation<? super com.coveiot.android.activitymodes.models.StepsSample> r13) {
        /*
            Method dump skipped, instructions count: 333
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.services.WorkoutSessionService.n(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final ArrayList<WalkSample> o() {
        ArrayList<WalkSample> arrayList = new ArrayList<>();
        int size = this.O.size();
        for (int i = 0; i < size; i++) {
            StepsSample stepsSample = this.O.get(i);
            Intrinsics.checkNotNullExpressionValue(stepsSample, "stepsSamples[i]");
            StepsSample stepsSample2 = stepsSample;
            LocationSample locationSample = this.P.get(i);
            SampleData sampleData = new SampleData();
            WalkSample walkSample = new WalkSample();
            walkSample.setStepCount(stepsSample2.getStepCount());
            walkSample.setSess_id(this.J);
            walkSample.setSeg_id(this.K);
            sampleData.setCalories(stepsSample2.getCalories());
            sampleData.setDistance(stepsSample2.getDistance());
            sampleData.setHr_value(this.N.get(i).getHr_value());
            double d2 = 0.0d;
            sampleData.setLatitude(locationSample != null ? locationSample.getLatitude() : 0.0d);
            if (locationSample != null) {
                d2 = locationSample.getLongitude();
            }
            sampleData.setLongitude(d2);
            sampleData.setTimeStamp(stepsSample2.getTimeStamp());
            walkSample.setSampleData(sampleData);
            arrayList.add(walkSample);
        }
        return arrayList;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return this.s;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d(this.j, "onCreate: called");
        q();
        this.r.register(this);
        setWorkoutSessionRepository(WorkoutSessionRepository.Companion.getInstance(this));
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(this)");
        this.p = fusedLocationProviderClient;
        this.q = new LocationCallback() { // from class: com.coveiot.android.activitymodes.services.WorkoutSessionService$onCreate$1
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(@NotNull LocationResult locationResult) {
                Intrinsics.checkNotNullParameter(locationResult, "locationResult");
                Location lastLocation = locationResult.getLastLocation();
                WorkoutSessionService workoutSessionService = WorkoutSessionService.this;
                Intrinsics.checkNotNull(lastLocation);
                workoutSessionService.S = new LocationSample(lastLocation.getLatitude(), lastLocation.getLongitude(), WorkoutUtils.INSTANCE.getCurrentTimeInSecs());
            }
        };
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (this.m) {
            v();
        }
        this.r.unregister(this);
    }

    @Subscribe
    public final void onDeviceConnected(@NotNull OnDeviceConnected onDeviceConnected) {
        Intrinsics.checkNotNullParameter(onDeviceConnected, "onDeviceConnected");
        Log.d(this.j, "onDeviceConnected");
        u();
    }

    @Subscribe
    public final void onDeviceDisconnected(@NotNull OnDeviceDisconnected onDeviceDisconnected) {
        Intrinsics.checkNotNullParameter(onDeviceDisconnected, "onDeviceDisconnected");
        Log.d(this.j, "onDeviceDisconnected");
        this.U = this.V;
        r();
    }

    @Subscribe
    public final void onHeartRateReceived(@NotNull LiveHeartRate liveHeartRate) {
        Intrinsics.checkNotNullParameter(liveHeartRate, "liveHeartRate");
        String str = this.j;
        Log.d(str, "currentHeartRate: " + this.y);
        if (liveHeartRate.getValue() > 0) {
            this.y = liveHeartRate.getValue();
        }
        int i = this.y;
        if (i != 0 && this.w == 0) {
            this.w = i;
        }
        if (i != 0 && i < this.w) {
            this.w = i;
        }
        if (i > this.x) {
            this.x = i;
        }
    }

    @Subscribe
    public final void onRunPaused(@Nullable OnRunPaused onRunPaused) {
        this.W = -1;
        this.T = this.i;
        this.G = WorkoutUtils.INSTANCE.getCurrentTimeInSecs();
        w();
        if (this.m) {
            v();
        }
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new f(null), 3, null);
    }

    @Subscribe
    public final void onRunResumed(@Nullable OnRunResumed onRunResumed) {
        t();
        if (this.v == IndoorOutdoor.OUTDOOR && !this.m && AppUtils.checkIfLocationPermissionExists(this)) {
            s();
        }
        this.V = false;
    }

    @Subscribe
    public final void onRunStarted(@NotNull OnRunStarted onRunStarted) {
        Intrinsics.checkNotNullParameter(onRunStarted, "onRunStarted");
        Log.d(this.j, "OnRunStarted");
        long currentTimeInSecs = WorkoutUtils.INSTANCE.getCurrentTimeInSecs();
        this.F = currentTimeInSecs;
        this.H = currentTimeInSecs;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        this.J = uuid;
        this.E = 0;
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new g(null), 3, null);
        t();
    }

    @Subscribe
    public final void onRunStopped(@Nullable OnRunEnd onRunEnd) {
        Log.d(this.j, "onRunEnd");
        w();
        long currentTimeInSecs = WorkoutUtils.INSTANCE.getCurrentTimeInSecs();
        this.G = currentTimeInSecs;
        this.I = currentTimeInSecs;
        Intrinsics.checkNotNull(onRunEnd);
        d(onRunEnd.isSaveSession(), onRunEnd.isSessionDestroyed());
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        Log.d(this.j, "onStartCommand: called");
        String str = this.j;
        Log.d(str, "initialSteps: " + new PreferenceManager(this).getInitialSteps());
        this.l = new Handler(Looper.getMainLooper());
        Serializable serializableExtra = intent != null ? intent.getSerializableExtra(WorkoutConstants.ACTIVITY_MODE) : null;
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.activitymodes.utils.ActivityMode");
        this.u = (ActivityMode) serializableExtra;
        Serializable serializableExtra2 = intent != null ? intent.getSerializableExtra(WorkoutConstants.INDOOR_OUTDOOR) : null;
        Intrinsics.checkNotNull(serializableExtra2, "null cannot be cast to non-null type com.coveiot.android.activitymodes.utils.IndoorOutdoor");
        IndoorOutdoor indoorOutdoor = (IndoorOutdoor) serializableExtra2;
        this.v = indoorOutdoor;
        if (indoorOutdoor == IndoorOutdoor.OUTDOOR && !this.m && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            s();
            return 3;
        }
        return 3;
    }

    @Subscribe
    public final void onStepsReceived(@NotNull LiveSteps liveSteps) {
        Intrinsics.checkNotNullParameter(liveSteps, "liveSteps");
        PreferenceManager preferenceManager = new PreferenceManager(this);
        int value = (liveSteps.getValue() + this.T) - new PreferenceManager(this).getInitialSteps();
        if (this.h == -1 || value - this.k <= ((System.currentTimeMillis() - this.h) / 1000) * 7) {
            this.z = value;
            this.i = value;
            String str = this.j;
            Log.d(str, "stepsBeforePaused: " + this.T);
            String str2 = this.j;
            Log.d(str2, "initialSteps: " + new PreferenceManager(this).getInitialSteps());
            String str3 = this.j;
            Log.d(str3, "liveSteps: " + liveSteps.getValue());
            String str4 = this.j;
            Log.d(str4, "CalculatedSteps: " + this.z);
            this.B = (float) AppUtils.calculateCalories(this.z, preferenceManager.getHeight(), preferenceManager.getWeight(), this.u.name(), this.X, this.Y);
            this.A = AppUtils.calculateDistanceInCentimeters(this.z, preferenceManager.getHeight(), this.u.name(), this.X, this.Y);
        }
    }

    public final Object p(Continuation<? super Unit> continuation) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.u.ordinal()];
        if (i == 1) {
            ArrayList<WalkSample> o = o();
            if ((o != null ? o.size() : 0) > 0) {
                WorkoutSessionRepository workoutSessionRepository = getWorkoutSessionRepository();
                Intrinsics.checkNotNull(o);
                Object insertWalkSampleList = workoutSessionRepository.insertWalkSampleList(o, continuation);
                return insertWalkSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertWalkSampleList : Unit.INSTANCE;
            }
        } else if (i == 2) {
            ArrayList<RunSample> l = l();
            if ((l != null ? l.size() : 0) > 0) {
                WorkoutSessionRepository workoutSessionRepository2 = getWorkoutSessionRepository();
                Intrinsics.checkNotNull(l);
                Object insertRunSampleList = workoutSessionRepository2.insertRunSampleList(l, continuation);
                return insertRunSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertRunSampleList : Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    public final void q() {
        NotificationCompat.Builder builder;
        Intent intent = new Intent(this, ActivityWorkoutSession.class);
        intent.putExtra(WorkoutConstants.ACTIVITY_MODE, this.u);
        intent.putExtra(WorkoutConstants.INDOOR_OUTDOOR, this.v);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 67108864);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(this, 0, int…ingIntent.FLAG_IMMUTABLE)");
        String string = getString(com.coveiot.android.theme.R.string.app_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.app_name)");
        String string2 = getString(R.string.workout_session_active);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…g.workout_session_active)");
        if (Build.VERSION.SDK_INT >= 26) {
            b();
            builder = new NotificationCompat.Builder(this, this.n).setChannelId(this.n);
            Intrinsics.checkNotNullExpressionValue(builder, "Builder(this, CHANNEL_ID….setChannelId(CHANNEL_ID)");
        } else {
            builder = new NotificationCompat.Builder(this);
        }
        builder.setSmallIcon(com.coveiot.android.theme.R.drawable.ic_stat_notification_icon_cove).setContentTitle(string).setContentText(string2).setColor(getResources().getColor(com.coveiot.android.theme.R.color.colorPrimary)).setContentIntent(activity).setPriority(-2);
        startForeground(this.o, builder.build());
    }

    public final void r() {
        if (this.M == null) {
            CountDownTimer countDownTimer = new CountDownTimer() { // from class: com.coveiot.android.activitymodes.services.WorkoutSessionService$startDisconnectTimer$1
                {
                    super(Constants.ONE_MIN_IN_MILLIS, 1000L);
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    int i;
                    ActivityMode activityMode;
                    i = WorkoutSessionService.this.z;
                    WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
                    activityMode = WorkoutSessionService.this.u;
                    if (i < workoutUtils.getMinUnitsForSession(activityMode)) {
                        CoveEventBusManager.getInstance().getEventBus().post(new OnRunEnd(false));
                        CoveEventBusManager.getInstance().getEventBus().post(new OnStopSession(false));
                        WorkoutSessionService.e(WorkoutSessionService.this, false, false, 2, null);
                        return;
                    }
                    WorkoutSessionService.e(WorkoutSessionService.this, true, false, 2, null);
                    CoveEventBusManager.getInstance().getEventBus().post(new OnStopSession(true));
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                    String str;
                    str = WorkoutSessionService.this.j;
                    Log.d(str, "Disconnected Time: " + j);
                }
            };
            this.M = countDownTimer;
            countDownTimer.start();
        }
    }

    public final void registerClient(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.t = (RunSessionCallbacks) activity;
    }

    @SuppressLint({"MissingPermission"})
    public final void s() {
        this.m = true;
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(5000L);
        locationRequest.setFastestInterval(2500L);
        locationRequest.setPriority(100);
        FusedLocationProviderClient fusedLocationProviderClient = this.p;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
            fusedLocationProviderClient = null;
        }
        LocationCallback locationCallback = this.q;
        if (locationCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationCallback");
            locationCallback = null;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, (Looper) null);
    }

    public final void setDiconnectedWhenPaused(boolean z) {
        this.U = z;
    }

    public final void setInitialSteps(int i) {
        this.W = i;
    }

    public final void setPausedManually(boolean z) {
        this.V = z;
    }

    public final void setRunStrideLegth(int i) {
        this.Y = i;
    }

    public final void setStepsBeforePaused(int i) {
        this.T = i;
    }

    public final void setWalkStrideLegth(int i) {
        this.X = i;
    }

    public final void setWorkoutSessionRepository(@NotNull WorkoutSessionRepository workoutSessionRepository) {
        Intrinsics.checkNotNullParameter(workoutSessionRepository, "<set-?>");
        this.workoutSessionRepository = workoutSessionRepository;
    }

    public final void showFeedbackScreen() {
        Intent intent = new Intent(getApplicationContext(), ActivityWorkoutFeedback.class);
        intent.putExtra(WorkoutConstants.SESSION_ID, this.J);
        if (Build.VERSION.SDK_INT >= 28) {
            intent.addFlags(268435456);
        }
        startActivity(intent);
    }

    public final void t() {
        PreferenceManager preferenceManager = new PreferenceManager(this);
        this.K = String.valueOf(WorkoutUtils.INSTANCE.getCurrentTimeInSecs() / 1000);
        this.D = 0;
        this.N.clear();
        this.O.clear();
        w();
        Timer timer = new Timer();
        this.L = timer;
        timer.scheduleAtFixedRate(new WorkoutSessionService$startTimerWithSampleRate$1(this, preferenceManager), 0L, 1000L);
    }

    public final void u() {
        Log.d(this.j, "stopSampleRateTimer: called");
        CountDownTimer countDownTimer = this.M;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.M = null;
    }

    public final void unregisterClient() {
        this.t = null;
    }

    public final void v() {
        this.m = false;
        FusedLocationProviderClient fusedLocationProviderClient = this.p;
        LocationCallback locationCallback = null;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
            fusedLocationProviderClient = null;
        }
        LocationCallback locationCallback2 = this.q;
        if (locationCallback2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationCallback");
        } else {
            locationCallback = locationCallback2;
        }
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    public final void w() {
        Log.d(this.j, "stopSampleRateTimer: called");
        Timer timer = this.L;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = this.L;
        if (timer2 != null) {
            timer2.purge();
        }
        this.L = null;
    }

    public final void x() {
        RunSessionCallbacks runSessionCallbacks = this.t;
        if (runSessionCallbacks != null) {
            runSessionCallbacks.updateDuration(this.E);
        }
    }

    public final void y() {
        RunSessionCallbacks runSessionCallbacks;
        RunSessionCallbacks runSessionCallbacks2;
        int i = this.z;
        if (i > 0 && (runSessionCallbacks2 = this.t) != null) {
            runSessionCallbacks2.updateStepsInfo(i, this.A, this.B);
        }
        RunSessionCallbacks runSessionCallbacks3 = this.t;
        if (runSessionCallbacks3 != null) {
            runSessionCallbacks3.updatePace(this.C);
        }
        int i2 = this.y;
        if (i2 == 0 || (runSessionCallbacks = this.t) == null) {
            return;
        }
        runSessionCallbacks.updateHeartRateInfo(i2, this.x, this.w);
    }
}
