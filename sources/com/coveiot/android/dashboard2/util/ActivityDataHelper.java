package com.coveiot.android.dashboard2.util;

import android.content.Context;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
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
import com.coveiot.android.activitymodes.database.entities.SampleData;
import com.coveiot.android.activitymodes.database.entities.SkippingSample;
import com.coveiot.android.activitymodes.database.entities.TennisSample;
import com.coveiot.android.activitymodes.database.entities.TreadmillSample;
import com.coveiot.android.activitymodes.database.entities.WalkSample;
import com.coveiot.android.activitymodes.database.entities.WorkoutSample;
import com.coveiot.android.activitymodes.database.entities.YogaSample;
import com.coveiot.android.activitymodes.database.models.HeartRateZoneRanges;
import com.coveiot.android.activitymodes.database.models.TimeSpentHeartRateZone;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.IndoorOutdoor;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ActivityGPSSample;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.models.ActivityStepsSample;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.UtilConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ActivityDataHelper {
    @NotNull
    public static final ActivityDataHelper INSTANCE = new ActivityDataHelper();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f4248a = "ActivityDataHelper";

    /* loaded from: classes4.dex */
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
            try {
                iArr[ActivityMode.PHYSICAL_ACTIVITY.ordinal()] = 3;
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
                iArr[ActivityMode.HIKING.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ActivityMode.FOOTBALL.ordinal()] = 13;
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
                iArr[ActivityMode.ELLIPTICAL.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[ActivityMode.ROWING.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.util.ActivityDataHelper$processBleResponse$1", f = "ActivityDataHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Context $context;
        public final /* synthetic */ BleBaseResponse $response;
        public final /* synthetic */ SuccessResultListener $successResultListener;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.dashboard2.util.ActivityDataHelper$processBleResponse$1$1", f = "ActivityDataHelper.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 3, 4}, l = {80, 83, 92, 106, 119}, m = "invokeSuspend", n = {"sportsHistoryResponse", "entityWorkoutSession", "sportsHistoryResponse", "entityWorkoutSession", "segmentID", "sportsHistoryResponse", "entityWorkoutSession", "segmentID", "sportsHistoryResponse", "sportsHistoryResponse"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$0"})
        /* renamed from: com.coveiot.android.dashboard2.util.ActivityDataHelper$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public static final class C0263a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ BleBaseResponse $response;
            public final /* synthetic */ long $startTime;
            public final /* synthetic */ SuccessResultListener $successResultListener;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public int label;

            @DebugMetadata(c = "com.coveiot.android.dashboard2.util.ActivityDataHelper$processBleResponse$1$1$1", f = "ActivityDataHelper.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.dashboard2.util.ActivityDataHelper$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes4.dex */
            public static final class C0264a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ Context $context;
                public final /* synthetic */ long $startTime;
                public int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0264a(Context context, long j, Continuation<? super C0264a> continuation) {
                    super(2, continuation);
                    this.$context = context;
                    this.$startTime = j;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0264a(this.$context, this.$startTime, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0264a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        long j = this.$startTime;
                        this.label = 1;
                        if (WorkoutSessionRepository.Companion.getInstance(this.$context).saveUnSyncedSessionsToServer(j, this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0263a(BleBaseResponse bleBaseResponse, Context context, SuccessResultListener successResultListener, long j, Continuation<? super C0263a> continuation) {
                super(2, continuation);
                this.$response = bleBaseResponse;
                this.$context = context;
                this.$successResultListener = successResultListener;
                this.$startTime = j;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0263a(this.$response, this.$context, this.$successResultListener, this.$startTime, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0263a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:27:0x011e, code lost:
                if ((true ^ r7.isEmpty()) != false) goto L48;
             */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0112  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x0130 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:31:0x0131  */
            /* JADX WARN: Removed duplicated region for block: B:34:0x014a  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x0152  */
            /* JADX WARN: Removed duplicated region for block: B:41:0x0173  */
            /* JADX WARN: Removed duplicated region for block: B:52:0x0258 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:53:0x0259  */
            /* JADX WARN: Removed duplicated region for block: B:56:0x0262  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r21) {
                /*
                    Method dump skipped, instructions count: 667
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.util.ActivityDataHelper.a.C0263a.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.dashboard2.util.ActivityDataHelper$processBleResponse$1$2", f = "ActivityDataHelper.kt", i = {}, l = {153}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ long $startTime;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(Context context, long j, Continuation<? super b> continuation) {
                super(2, continuation);
                this.$context = context;
                this.$startTime = j;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.$context, this.$startTime, continuation);
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
                    long j = this.$startTime;
                    this.label = 1;
                    if (WorkoutSessionRepository.Companion.getInstance(this.$context).saveUnSyncedSessionsToServer(j, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(BleBaseResponse bleBaseResponse, Context context, SuccessResultListener successResultListener, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$response = bleBaseResponse;
            this.$context = context;
            this.$successResultListener = successResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$response, this.$context, this.$successResultListener, continuation);
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
                if (this.$response != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (this.$response.getResponseData() == null) {
                        e.e(GlobalScope.INSTANCE, null, null, new b(this.$context, currentTimeMillis, null), 3, null);
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (!companion.isCZDevice(this.$context) && !companion.isCADevice(this.$context) && !companion.isCYDevice(this.$context) && !companion.isPS1Device(this.$context) && !companion.isBESDevice(this.$context)) {
                            SuccessResultListener successResultListener = this.$successResultListener;
                            if (successResultListener != null) {
                                successResultListener.onSuccess();
                            }
                        } else if (this.$response.isCompleted()) {
                            UserDataManager.getInstance(this.$context).saveLastActivitySyncedDate(Calendar.getInstance().getTime());
                            SuccessResultListener successResultListener2 = this.$successResultListener;
                            if (successResultListener2 != null) {
                                successResultListener2.onSuccess();
                            }
                        }
                    } else if (this.$response.getResponseData() instanceof ActivityModeSummaryResponse) {
                        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C0263a(this.$response, this.$context, this.$successResultListener, currentTimeMillis, null), 2, null);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static /* synthetic */ String e(ActivityDataHelper activityDataHelper, ActivityModeSummaryResponse activityModeSummaryResponse, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return activityDataHelper.d(activityModeSummaryResponse, z);
    }

    public static /* synthetic */ void processBleResponse$default(ActivityDataHelper activityDataHelper, BleBaseResponse bleBaseResponse, Context context, SuccessResultListener successResultListener, int i, Object obj) {
        if ((i & 4) != 0) {
            successResultListener = null;
        }
        activityDataHelper.processBleResponse(bleBaseResponse, context, successResultListener);
    }

    public final ArrayList<WorkoutSample> A(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ActivityDataHelper activityDataHelper = this;
        int i2 = i;
        ArrayList<WorkoutSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    WorkoutSample workoutSample = new WorkoutSample();
                    workoutSample.setSess_id(str);
                    workoutSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = activityDataHelper.c(arrayList, j4, i2);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        workoutSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = activityDataHelper.b(arrayList2, j4, i2);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    ActivityGPSSample a2 = activityDataHelper.a(arrayList3, j4, i2);
                    if (a2 != null) {
                        sampleData.setLatitude(a2.getLocation().latitude);
                        sampleData.setLongitude(a2.getLocation().longitude);
                    }
                    workoutSample.setSampleData(sampleData);
                    arrayList4.add(workoutSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    activityDataHelper = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<YogaSample> B(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<YogaSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    YogaSample yogaSample = new YogaSample();
                    yogaSample.setSess_id(str);
                    yogaSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = c(arrayList, j4, i);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        yogaSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = b(arrayList2, j4, i);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    yogaSample.setSampleData(sampleData);
                    arrayList3.add(yogaSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final Object C(Context context, ActivityMode activityMode, ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, String str, String str2, int i, Continuation<? super Unit> continuation) {
        ActivityDataHelper activityDataHelper = this;
        int i2 = i;
        ArrayList<ActivityDataSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    ActivityDataSample activityDataSample = new ActivityDataSample();
                    activityDataSample.setSessionID(str);
                    activityDataSample.setSegmentID(str2);
                    activityDataSample.setActivityType(activityMode.name());
                    activityDataSample.setTimeStamp(j4);
                    ActivityStepsSample c = activityDataHelper.c(arrayList, j4, i2);
                    if (c != null) {
                        activityDataSample.setCalories((float) c.getCalories());
                        activityDataSample.setDistance((int) c.getDistance());
                        activityDataSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = activityDataHelper.b(arrayList2, j4, i2);
                    if (b != null) {
                        activityDataSample.setHrValue(b.getHrValue());
                    }
                    ActivityGPSSample a2 = activityDataHelper.a(arrayList3, j4, i2);
                    if (a2 != null) {
                        activityDataSample.setLatitude(a2.getLocation().latitude);
                        activityDataSample.setLongitude(a2.getLocation().longitude);
                    }
                    arrayList4.add(activityDataSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    activityDataHelper = this;
                    i2 = i;
                }
            }
            if (arrayList4.size() > 0) {
                Object insertGenericActivitySampleList = WorkoutSessionRepository.Companion.getInstance(context).insertGenericActivitySampleList(arrayList4, continuation);
                return insertGenericActivitySampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertGenericActivitySampleList : Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final Object D(Context context, ActivityMode activityMode, ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, String str, String str2, int i, Continuation<? super Unit> continuation) {
        WorkoutSessionRepository singletonHolder = WorkoutSessionRepository.Companion.getInstance(context);
        switch (WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()]) {
            case 1:
                ArrayList<WalkSample> z = z(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((z != null ? z.size() : 0) > 0) {
                    Intrinsics.checkNotNull(z);
                    Object insertWalkSampleList = singletonHolder.insertWalkSampleList(z, continuation);
                    return insertWalkSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertWalkSampleList : Unit.INSTANCE;
                }
                break;
            case 2:
                ArrayList<RunSample> u = u(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((u != null ? u.size() : 0) > 0) {
                    Intrinsics.checkNotNull(u);
                    Object insertRunSampleList = singletonHolder.insertRunSampleList(u, continuation);
                    return insertRunSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertRunSampleList : Unit.INSTANCE;
                }
                break;
            case 3:
                ArrayList<PhysicalActivitySample> s = s(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((s != null ? s.size() : 0) > 0) {
                    Intrinsics.checkNotNull(s);
                    Object insertPhysicalActivitySamples = singletonHolder.insertPhysicalActivitySamples(s, continuation);
                    return insertPhysicalActivitySamples == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertPhysicalActivitySamples : Unit.INSTANCE;
                }
                break;
            case 4:
                ArrayList<BadmintonSample> f = f(arrayList, arrayList2, j, j2, i, str, str2);
                if ((f != null ? f.size() : 0) > 0) {
                    Intrinsics.checkNotNull(f);
                    Object insertBadmintonSampleList = singletonHolder.insertBadmintonSampleList(f, continuation);
                    return insertBadmintonSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertBadmintonSampleList : Unit.INSTANCE;
                }
                break;
            case 5:
                ArrayList<FreeExerciseSample> p = p(arrayList, arrayList2, j, j2, i, str, str2);
                if ((p != null ? p.size() : 0) > 0) {
                    Intrinsics.checkNotNull(p);
                    Object insertFreeExerciseSampleList = singletonHolder.insertFreeExerciseSampleList(p, continuation);
                    return insertFreeExerciseSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertFreeExerciseSampleList : Unit.INSTANCE;
                }
                break;
            case 6:
                ArrayList<BasketBallSample> g = g(arrayList, arrayList2, j, j2, i, str, str2);
                if ((g != null ? g.size() : 0) > 0) {
                    Intrinsics.checkNotNull(g);
                    Object insertBasketBallSampleList = singletonHolder.insertBasketBallSampleList(g, continuation);
                    return insertBasketBallSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertBasketBallSampleList : Unit.INSTANCE;
                }
                break;
            case 7:
                ArrayList<CyclingSample> j3 = j(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((j3 != null ? j3.size() : 0) > 0) {
                    Intrinsics.checkNotNull(j3);
                    Object insertCyclingSampleList = singletonHolder.insertCyclingSampleList(j3, continuation);
                    return insertCyclingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertCyclingSampleList : Unit.INSTANCE;
                }
                break;
            case 8:
                ArrayList<MeditationSample> r = r(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((r != null ? r.size() : 0) > 0) {
                    Intrinsics.checkNotNull(r);
                    Object insertMeditationSampleList = singletonHolder.insertMeditationSampleList(r, continuation);
                    return insertMeditationSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertMeditationSampleList : Unit.INSTANCE;
                }
                break;
            case 9:
                ArrayList<TreadmillSample> y = y(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((y != null ? y.size() : 0) > 0) {
                    Intrinsics.checkNotNull(y);
                    Object insertTreadmillSampleList = singletonHolder.insertTreadmillSampleList(y, continuation);
                    return insertTreadmillSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertTreadmillSampleList : Unit.INSTANCE;
                }
                break;
            case 10:
                ArrayList<ClimbingSample> i2 = i(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((i2 != null ? i2.size() : 0) > 0) {
                    Intrinsics.checkNotNull(i2);
                    Object insertClimbingSampleList = singletonHolder.insertClimbingSampleList(i2, continuation);
                    return insertClimbingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertClimbingSampleList : Unit.INSTANCE;
                }
                break;
            case 11:
                ArrayList<DanceSample> k = k(arrayList, arrayList2, j, j2, i, str, str2);
                if ((k != null ? k.size() : 0) > 0) {
                    Intrinsics.checkNotNull(k);
                    Object insertDanceSampleList = singletonHolder.insertDanceSampleList(k, continuation);
                    return insertDanceSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertDanceSampleList : Unit.INSTANCE;
                }
                break;
            case 12:
                ArrayList<HikingSample> q = q(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((q != null ? q.size() : 0) > 0) {
                    Intrinsics.checkNotNull(q);
                    Object insertHikingSampleList = singletonHolder.insertHikingSampleList(q, continuation);
                    return insertHikingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertHikingSampleList : Unit.INSTANCE;
                }
                break;
            case 13:
                ArrayList<FootballSample> o = o(arrayList, arrayList2, j, j2, i, str, str2);
                if ((o != null ? o.size() : 0) > 0) {
                    Intrinsics.checkNotNull(o);
                    Object insertFootBallSampleList = singletonHolder.insertFootBallSampleList(o, continuation);
                    return insertFootBallSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertFootBallSampleList : Unit.INSTANCE;
                }
                break;
            case 14:
                ArrayList<TennisSample> w = w(arrayList, arrayList2, j, j2, i, str, str2);
                if ((w != null ? w.size() : 0) > 0) {
                    Intrinsics.checkNotNull(w);
                    Object insertTennisSampleList = singletonHolder.insertTennisSampleList(w, continuation);
                    return insertTennisSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertTennisSampleList : Unit.INSTANCE;
                }
                break;
            case 15:
                ArrayList<WorkoutSample> A = A(arrayList, arrayList2, arrayList3, j, j2, i, str, str2);
                if ((A != null ? A.size() : 0) > 0) {
                    Intrinsics.checkNotNull(A);
                    Object insertWorkoutSampleList = singletonHolder.insertWorkoutSampleList(A, continuation);
                    return insertWorkoutSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertWorkoutSampleList : Unit.INSTANCE;
                }
                break;
            case 16:
                ArrayList<YogaSample> B = B(arrayList, arrayList2, j, j2, i, str, str2);
                if ((B != null ? B.size() : 0) > 0) {
                    Intrinsics.checkNotNull(B);
                    Object insertYogaSampleList = singletonHolder.insertYogaSampleList(B, continuation);
                    return insertYogaSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertYogaSampleList : Unit.INSTANCE;
                }
                break;
            case 17:
                ArrayList<SkippingSample> v = v(arrayList, arrayList2, j, j2, i, str, str2);
                if ((v != null ? v.size() : 0) > 0) {
                    Intrinsics.checkNotNull(v);
                    Object insertSkippingSampleList = singletonHolder.insertSkippingSampleList(v, continuation);
                    return insertSkippingSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertSkippingSampleList : Unit.INSTANCE;
                }
                break;
            case 18:
                ArrayList<EllipticalSample> l = l(arrayList, arrayList2, j, j2, i, str, str2);
                if ((l != null ? l.size() : 0) > 0) {
                    Intrinsics.checkNotNull(l);
                    Object insertEllipticalSampleList = singletonHolder.insertEllipticalSampleList(l, continuation);
                    return insertEllipticalSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertEllipticalSampleList : Unit.INSTANCE;
                }
                break;
            case 19:
                ArrayList<RowingMachineSample> t = t(arrayList, arrayList2, j, j2, i, str, str2);
                if ((t != null ? t.size() : 0) > 0) {
                    Intrinsics.checkNotNull(t);
                    Object insertRowingMachineSampleList = singletonHolder.insertRowingMachineSampleList(t, continuation);
                    return insertRowingMachineSampleList == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? insertRowingMachineSampleList : Unit.INSTANCE;
                }
                break;
        }
        return Unit.INSTANCE;
    }

    public final ActivityGPSSample a(ArrayList<ActivityGPSSample> arrayList, long j, int i) {
        if (AppUtils.isEmpty(arrayList)) {
            return null;
        }
        Intrinsics.checkNotNull(arrayList);
        Iterator<ActivityGPSSample> it = arrayList.iterator();
        while (it.hasNext()) {
            ActivityGPSSample next = it.next();
            long j2 = i * 1000;
            if (next.getGpsTimeStamp() / j2 == j / j2) {
                return next;
            }
        }
        return null;
    }

    public final ActivityHeartRateSample b(ArrayList<ActivityHeartRateSample> arrayList, long j, int i) {
        Iterator<ActivityHeartRateSample> it = arrayList.iterator();
        while (it.hasNext()) {
            ActivityHeartRateSample next = it.next();
            long j2 = i * 1000;
            if (next.getHrTimeStamp() / j2 == j / j2) {
                return next;
            }
        }
        return null;
    }

    public final ActivityStepsSample c(ArrayList<ActivityStepsSample> arrayList, long j, int i) {
        Iterator<ActivityStepsSample> it = arrayList.iterator();
        while (it.hasNext()) {
            ActivityStepsSample next = it.next();
            long j2 = i * 1000;
            if (next.getStepsTimeStamp() / j2 == j / j2) {
                return next;
            }
        }
        return null;
    }

    public final String d(ActivityModeSummaryResponse activityModeSummaryResponse, boolean z) {
        if (z) {
            if (Intrinsics.areEqual(activityModeSummaryResponse.getActivityMode(), "SPINNING")) {
                String lowerCase = CoveApiConstants.CYCLE.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                return lowerCase;
            } else if (Intrinsics.areEqual(activityModeSummaryResponse.getActivityMode(), "FREE_EXERCISE")) {
                String lowerCase2 = "free_exercise".toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                return lowerCase2;
            } else if (Intrinsics.areEqual(activityModeSummaryResponse.getActivityMode(), "ROWING_MACHINE")) {
                String lowerCase3 = "ROWING".toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                return lowerCase3;
            } else {
                String activityMode = activityModeSummaryResponse.getActivityMode();
                Intrinsics.checkNotNull(activityMode);
                String lowerCase4 = activityMode.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                return lowerCase4;
            }
        } else if (Intrinsics.areEqual(activityModeSummaryResponse.getActivityMode(), "SPINNING")) {
            return CoveApiConstants.CYCLE;
        } else {
            if (Intrinsics.areEqual(activityModeSummaryResponse.getActivityMode(), "ROWING_MACHINE")) {
                return "ROWING";
            }
            String activityMode2 = activityModeSummaryResponse.getActivityMode();
            Intrinsics.checkNotNull(activityMode2);
            return activityMode2;
        }
    }

    public final ArrayList<BadmintonSample> f(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<BadmintonSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    BadmintonSample badmintonSample = new BadmintonSample();
                    badmintonSample.setSess_id(str);
                    badmintonSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = c(arrayList, j4, i);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        badmintonSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = b(arrayList2, j4, i);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    badmintonSample.setSampleData(sampleData);
                    arrayList3.add(badmintonSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<BasketBallSample> g(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<BasketBallSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    BasketBallSample basketBallSample = new BasketBallSample();
                    basketBallSample.setSess_id(str);
                    basketBallSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = c(arrayList, j4, i);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        basketBallSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = b(arrayList2, j4, i);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    basketBallSample.setSampleData(sampleData);
                    arrayList3.add(basketBallSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    @NotNull
    public final String getTAG() {
        return f4248a;
    }

    public final String h(Context context, Integer num, Integer num2, String str, long j, long j2, int i) {
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
        if (m.equals(str, "PHYSICAL_ACTIVITY", true)) {
            EntityPhysicalActivities physicalActivityByFwActivityId = PhysicalActivityRepository.Companion.getInstance(context).getPhysicalActivityByFwActivityId(num, num2);
            String activityCode = physicalActivityByFwActivityId != null ? physicalActivityByFwActivityId.getActivityCode() : null;
            if (activityCode != null) {
                str = activityCode;
            }
        }
        String str2 = userDetails.getUserId() + ';' + userDeviceID + ';' + str + ';' + AppUtils.formatDateUTC(new Date(j), UtilConstants.SERVER_TIME_FORMAT2) + ';' + AppUtils.formatDateUTC(new Date(j2), UtilConstants.SERVER_TIME_FORMAT2) + ';' + i;
        LogHelper.d(f4248a, "clientRefString: " + str2);
        String convertStringToMD5 = AppUtils.convertStringToMD5(str2);
        Intrinsics.checkNotNullExpressionValue(convertStringToMD5, "convertStringToMD5(clientRefString)");
        return convertStringToMD5;
    }

    public final ArrayList<ClimbingSample> i(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ActivityDataHelper activityDataHelper = this;
        int i2 = i;
        ArrayList<ClimbingSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    ClimbingSample climbingSample = new ClimbingSample();
                    climbingSample.setSess_id(str);
                    climbingSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = activityDataHelper.c(arrayList, j4, i2);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        climbingSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = activityDataHelper.b(arrayList2, j4, i2);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    ActivityGPSSample a2 = activityDataHelper.a(arrayList3, j4, i2);
                    if (a2 != null) {
                        sampleData.setLatitude(a2.getLocation().latitude);
                        sampleData.setLongitude(a2.getLocation().longitude);
                    }
                    climbingSample.setSampleData(sampleData);
                    arrayList4.add(climbingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    activityDataHelper = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<CyclingSample> j(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ActivityDataHelper activityDataHelper = this;
        int i2 = i;
        ArrayList<CyclingSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    CyclingSample cyclingSample = new CyclingSample();
                    cyclingSample.setSess_id(str);
                    cyclingSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = activityDataHelper.c(arrayList, j4, i2);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        cyclingSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = activityDataHelper.b(arrayList2, j4, i2);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    ActivityGPSSample a2 = activityDataHelper.a(arrayList3, j4, i2);
                    if (a2 != null) {
                        sampleData.setLatitude(a2.getLocation().latitude);
                        sampleData.setLongitude(a2.getLocation().longitude);
                    }
                    cyclingSample.setSampleData(sampleData);
                    arrayList4.add(cyclingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    activityDataHelper = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<DanceSample> k(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<DanceSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    DanceSample danceSample = new DanceSample();
                    danceSample.setSess_id(str);
                    danceSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = c(arrayList, j4, i);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        danceSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = b(arrayList2, j4, i);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    danceSample.setSampleData(sampleData);
                    arrayList3.add(danceSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<EllipticalSample> l(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<EllipticalSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    EllipticalSample ellipticalSample = new EllipticalSample();
                    ellipticalSample.setSess_id(str);
                    ellipticalSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = c(arrayList, j4, i);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        ellipticalSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = b(arrayList2, j4, i);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    ellipticalSample.setSampleData(sampleData);
                    arrayList3.add(ellipticalSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    public final EntityWorkoutSession m(Context context, ActivityModeSummaryResponse activityModeSummaryResponse) {
        int i;
        EntityWorkoutSession entityWorkoutSession = new EntityWorkoutSession();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        long j = 0;
        if ((!companion.isIDODevice(context) || !m.equals$default(activityModeSummaryResponse.getActivityMode(), ActivityMode.SWIM.name(), false, 2, null)) && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isSampleDataSupportedInSportMode()) {
            Long endDateTime = activityModeSummaryResponse.getEndDateTime();
            Intrinsics.checkNotNull(endDateTime);
            long longValue = endDateTime.longValue();
            Long startDateTime = activityModeSummaryResponse.getStartDateTime();
            Intrinsics.checkNotNull(startDateTime);
            j = ((longValue - startDateTime.longValue()) / (activityModeSummaryResponse.getLowSamplingRate() * 1000)) + 1;
        }
        Integer categoryId = activityModeSummaryResponse.getCategoryId();
        Integer activityId = activityModeSummaryResponse.getActivityId();
        String d = d(activityModeSummaryResponse, true);
        Long startDateTime2 = activityModeSummaryResponse.getStartDateTime();
        Intrinsics.checkNotNull(startDateTime2);
        long longValue2 = startDateTime2.longValue();
        Long endDateTime2 = activityModeSummaryResponse.getEndDateTime();
        Intrinsics.checkNotNull(endDateTime2);
        entityWorkoutSession.setSession_id(h(context, categoryId, activityId, d, longValue2, endDateTime2.longValue(), (int) j));
        entityWorkoutSession.setClient_ref_id(entityWorkoutSession.getSession_id());
        entityWorkoutSession.setSerialNo(activityModeSummaryResponse.getMacAddress());
        if (activityModeSummaryResponse.getActivityId() != null) {
            Integer activityId2 = activityModeSummaryResponse.getActivityId();
            Intrinsics.checkNotNull(activityId2);
            entityWorkoutSession.setActivityId(PhysicalActivityRepository.Companion.getInstance(context).getActivityId(activityId2.intValue()));
        }
        if (activityModeSummaryResponse.getCategoryId() != null) {
            entityWorkoutSession.setCategoryId(activityModeSummaryResponse.getCategoryId());
        }
        String e = e(this, activityModeSummaryResponse, false, 2, null);
        Locale ENGLISH = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
        String upperCase = e.toUpperCase(ENGLISH);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        entityWorkoutSession.setActivity_type(upperCase);
        if (!companion.isSmaDevice(context) && !companion.isMoyangDevice(context) && (BleApiManager.getInstance(context) == null || BleApiManager.getInstance(context).getDeviceType() == null || BleApiManager.getInstance(context).getDeviceType() != DeviceType.matrix)) {
            if (!companion.isIDODevice(context) && !companion.isTouchELXDevice(context) && !companion.isEastApexDevice(context) && !companion.isRuggedDevice(context)) {
                if (activityModeSummaryResponse.getGpsSampleList() != null) {
                    List<ActivityGPSSample> gpsSampleList = activityModeSummaryResponse.getGpsSampleList();
                    Intrinsics.checkNotNull(gpsSampleList);
                    if (gpsSampleList.size() > 0) {
                        entityWorkoutSession.setIndoor_outdoor(IndoorOutdoor.OUTDOOR.toString());
                    }
                }
                entityWorkoutSession.setIndoor_outdoor(IndoorOutdoor.INDOOR.toString());
            } else {
                entityWorkoutSession.setIndoor_outdoor(activityModeSummaryResponse.getActivitySite());
            }
            i = 1;
        } else {
            i = 1;
            i = 1;
            if (!m.equals(activityModeSummaryResponse.getActivityMode(), context.getString(R.string.spinning), true) && !m.equals(activityModeSummaryResponse.getActivityMode(), ActivityMode.TREADMILL.name(), true)) {
                entityWorkoutSession.setIndoor_outdoor(IndoorOutdoor.OUTDOOR.toString());
            } else {
                entityWorkoutSession.setIndoor_outdoor(IndoorOutdoor.INDOOR.toString());
            }
        }
        Long startDateTime3 = activityModeSummaryResponse.getStartDateTime();
        Intrinsics.checkNotNull(startDateTime3);
        entityWorkoutSession.setStart_time(startDateTime3.longValue());
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        entityWorkoutSession.setSession_date(workoutUtils.getDateFromTimeStamp(activityModeSummaryResponse.getStartDateTime(), "yyyy-MM-dd"));
        entityWorkoutSession.setSession_duration(activityModeSummaryResponse.getActivityDuration());
        Long endDateTime3 = activityModeSummaryResponse.getEndDateTime();
        Intrinsics.checkNotNull(endDateTime3);
        entityWorkoutSession.setEnd_time(endDateTime3.longValue());
        entityWorkoutSession.setSteps_sampling_rate(activityModeSummaryResponse.getLowSamplingRate());
        entityWorkoutSession.setHr_sampling_rate(activityModeSummaryResponse.getLowSamplingRate());
        entityWorkoutSession.setAvg_hr(activityModeSummaryResponse.getHeartRate());
        entityWorkoutSession.setMax_hr(activityModeSummaryResponse.getMaxHeartRate());
        entityWorkoutSession.setMin_hr(activityModeSummaryResponse.getMinHeartRate());
        entityWorkoutSession.setTotal_steps(activityModeSummaryResponse.getTotalSteps());
        double totalCalories = activityModeSummaryResponse.getTotalCalories();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = new Object[i];
        objArr[0] = Double.valueOf(totalCalories);
        String format = String.format(ENGLISH, "%.2f", Arrays.copyOf(objArr, i));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        entityWorkoutSession.setTotal_calories(Float.parseFloat(format));
        entityWorkoutSession.setTotal_distance((int) (activityModeSummaryResponse.getTotalDistance() * 100000));
        entityWorkoutSession.setPace(activityModeSummaryResponse.getPaceInSeconds());
        entityWorkoutSession.setTarget(new com.coveiot.android.activitymodes.preference.PreferenceManager(context).getWorkoutTarget());
        entityWorkoutSession.setTarget_baseunit(new com.coveiot.android.activitymodes.preference.PreferenceManager(context).getWorkoutTargetUnit());
        entityWorkoutSession.setHrZoneRanges(workoutUtils.geHeartRateZoneRanges(new com.coveiot.android.activitymodes.preference.PreferenceManager(context).getAge()));
        if (companion.isEastApexDevice(context)) {
            TimeSpentHeartRateZone timeSpentHeartRateZone = new TimeSpentHeartRateZone();
            com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone heartRateTimeZone = activityModeSummaryResponse.getHeartRateTimeZone();
            Integer valueOf = heartRateTimeZone != null ? Integer.valueOf(heartRateTimeZone.getZone0TimeInSecs()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 0) {
                com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone heartRateTimeZone2 = activityModeSummaryResponse.getHeartRateTimeZone();
                Integer valueOf2 = heartRateTimeZone2 != null ? Integer.valueOf(heartRateTimeZone2.getZone0TimeInSecs()) : null;
                Intrinsics.checkNotNull(valueOf2);
                timeSpentHeartRateZone.setZone0Time(valueOf2.intValue());
            }
            com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone heartRateTimeZone3 = activityModeSummaryResponse.getHeartRateTimeZone();
            Integer valueOf3 = heartRateTimeZone3 != null ? Integer.valueOf(heartRateTimeZone3.getZone1TimeInSecs()) : null;
            Intrinsics.checkNotNull(valueOf3);
            if (valueOf3.intValue() > 0) {
                com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone heartRateTimeZone4 = activityModeSummaryResponse.getHeartRateTimeZone();
                Integer valueOf4 = heartRateTimeZone4 != null ? Integer.valueOf(heartRateTimeZone4.getZone1TimeInSecs()) : null;
                Intrinsics.checkNotNull(valueOf4);
                timeSpentHeartRateZone.setZone1Time(valueOf4.intValue());
            }
            com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone heartRateTimeZone5 = activityModeSummaryResponse.getHeartRateTimeZone();
            Integer valueOf5 = heartRateTimeZone5 != null ? Integer.valueOf(heartRateTimeZone5.getZone2TimeInSecs()) : null;
            Intrinsics.checkNotNull(valueOf5);
            if (valueOf5.intValue() > 0) {
                com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone heartRateTimeZone6 = activityModeSummaryResponse.getHeartRateTimeZone();
                Integer valueOf6 = heartRateTimeZone6 != null ? Integer.valueOf(heartRateTimeZone6.getZone2TimeInSecs()) : null;
                Intrinsics.checkNotNull(valueOf6);
                timeSpentHeartRateZone.setZone2Time(valueOf6.intValue());
            }
            com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone heartRateTimeZone7 = activityModeSummaryResponse.getHeartRateTimeZone();
            Integer valueOf7 = heartRateTimeZone7 != null ? Integer.valueOf(heartRateTimeZone7.getZone3TimeInSecs()) : null;
            Intrinsics.checkNotNull(valueOf7);
            if (valueOf7.intValue() > 0) {
                com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone heartRateTimeZone8 = activityModeSummaryResponse.getHeartRateTimeZone();
                Integer valueOf8 = heartRateTimeZone8 != null ? Integer.valueOf(heartRateTimeZone8.getZone3TimeInSecs()) : null;
                Intrinsics.checkNotNull(valueOf8);
                timeSpentHeartRateZone.setZone3Time(valueOf8.intValue());
            }
            com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone heartRateTimeZone9 = activityModeSummaryResponse.getHeartRateTimeZone();
            Integer valueOf9 = heartRateTimeZone9 != null ? Integer.valueOf(heartRateTimeZone9.getZone4TimeInSecs()) : null;
            Intrinsics.checkNotNull(valueOf9);
            if (valueOf9.intValue() > 0) {
                com.coveiot.android.bleabstract.response.TimeSpentHeartRateZone heartRateTimeZone10 = activityModeSummaryResponse.getHeartRateTimeZone();
                Integer valueOf10 = heartRateTimeZone10 != null ? Integer.valueOf(heartRateTimeZone10.getZone4TimeInSecs()) : null;
                Intrinsics.checkNotNull(valueOf10);
                timeSpentHeartRateZone.setZone4Time(valueOf10.intValue());
            }
            entityWorkoutSession.setTimespent_per_heartratezone(timeSpentHeartRateZone);
        } else {
            List<ActivityHeartRateSample> heartRateSampleList = activityModeSummaryResponse.getHeartRateSampleList();
            Intrinsics.checkNotNull(heartRateSampleList);
            entityWorkoutSession.setTimespent_per_heartratezone(x(context, heartRateSampleList));
        }
        entityWorkoutSession.setAppConnectivityCode(activityModeSummaryResponse.getAppConnectivityCode());
        entityWorkoutSession.setAvgStepFrequency(Integer.valueOf(activityModeSummaryResponse.getAvgStepFrequency()));
        entityWorkoutSession.setMaxStepFrequency(Integer.valueOf(activityModeSummaryResponse.getMaxStepFrequency()));
        entityWorkoutSession.setAvgSpeed(Float.valueOf(activityModeSummaryResponse.getAvgSpeed()));
        entityWorkoutSession.setMaxSpeed(Float.valueOf(activityModeSummaryResponse.getMaxSpeed()));
        entityWorkoutSession.setAvgStrideLength(Integer.valueOf(activityModeSummaryResponse.getAvgStrideLength()));
        entityWorkoutSession.setMaxStrideLength(Integer.valueOf(activityModeSummaryResponse.getMaxStrideLength()));
        entityWorkoutSession.setAvgPace(Float.valueOf(activityModeSummaryResponse.getAvgPace()));
        entityWorkoutSession.setMaxPace(Float.valueOf(activityModeSummaryResponse.getMaxPace()));
        entityWorkoutSession.setMinPace(Float.valueOf(activityModeSummaryResponse.getMinPace()));
        entityWorkoutSession.setSwimmingStyle(Dashboard2Utils.Companion.getSwimStyleByValue(activityModeSummaryResponse.getSwimmingPosture()));
        entityWorkoutSession.setTotalStrokes(Integer.valueOf(activityModeSummaryResponse.getTotalStrokesNumber()));
        entityWorkoutSession.setAvgSwolf(Integer.valueOf(activityModeSummaryResponse.getAverageSWOLF()));
        entityWorkoutSession.setPoolLength(Integer.valueOf(activityModeSummaryResponse.getPoolDistance()));
        entityWorkoutSession.setAvgStrokeFreq(Integer.valueOf(activityModeSummaryResponse.getAvgFrequency()));
        entityWorkoutSession.setTotalLaps(Integer.valueOf(activityModeSummaryResponse.getTrips()));
        entityWorkoutSession.setFromHAR(Integer.valueOf(activityModeSummaryResponse.isFromHAR()));
        if (companion.isTouchELXDevice(context)) {
            if (m.equals(activityModeSummaryResponse.getActivityMode(), ActivityMode.ROWING.name(), i)) {
                entityWorkoutSession.setAvgStrokeFreq(Integer.valueOf(activityModeSummaryResponse.getFrequency()));
                entityWorkoutSession.setTotalStrokes(Integer.valueOf(activityModeSummaryResponse.getCounter()));
            } else if (m.equals(activityModeSummaryResponse.getActivityMode(), ActivityMode.SKIPPING.name(), i)) {
                entityWorkoutSession.setAvgStrokeFreq(Integer.valueOf(activityModeSummaryResponse.getAvgFrequency()));
                entityWorkoutSession.setTotalStrokes(Integer.valueOf(activityModeSummaryResponse.getCounter()));
            }
        }
        return entityWorkoutSession;
    }

    public final EntityWorkoutSessionSegment n(EntityWorkoutSession entityWorkoutSession, String str) {
        EntityWorkoutSessionSegment entityWorkoutSessionSegment = new EntityWorkoutSessionSegment();
        entityWorkoutSessionSegment.setSess_id(entityWorkoutSession.getSession_id());
        entityWorkoutSessionSegment.setSegment_id(str);
        entityWorkoutSessionSegment.setSegment_duration((entityWorkoutSession.getSession_duration() / 60) + 1);
        entityWorkoutSessionSegment.setStart_time(entityWorkoutSession.getStart_time());
        entityWorkoutSessionSegment.setEnd_time(entityWorkoutSession.getEnd_time());
        return entityWorkoutSessionSegment;
    }

    public final ArrayList<FootballSample> o(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<FootballSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    FootballSample footballSample = new FootballSample();
                    footballSample.setSess_id(str);
                    footballSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = c(arrayList, j4, i);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        footballSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = b(arrayList2, j4, i);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    footballSample.setSampleData(sampleData);
                    arrayList3.add(footballSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<FreeExerciseSample> p(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<FreeExerciseSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    FreeExerciseSample freeExerciseSample = new FreeExerciseSample();
                    freeExerciseSample.setSess_id(str);
                    freeExerciseSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = c(arrayList, j4, i);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        freeExerciseSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = b(arrayList2, j4, i);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    freeExerciseSample.setSampleData(sampleData);
                    arrayList3.add(freeExerciseSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final void processBleResponse(@Nullable BleBaseResponse bleBaseResponse, @NotNull Context context, @Nullable SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(bleBaseResponse, context, successResultListener, null), 2, null);
    }

    public final ArrayList<HikingSample> q(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ActivityDataHelper activityDataHelper = this;
        int i2 = i;
        ArrayList<HikingSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    HikingSample hikingSample = new HikingSample();
                    hikingSample.setSess_id(str);
                    hikingSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = activityDataHelper.c(arrayList, j4, i2);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        hikingSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = activityDataHelper.b(arrayList2, j4, i2);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    ActivityGPSSample a2 = activityDataHelper.a(arrayList3, j4, i2);
                    if (a2 != null) {
                        sampleData.setLatitude(a2.getLocation().latitude);
                        sampleData.setLongitude(a2.getLocation().longitude);
                    }
                    hikingSample.setSampleData(sampleData);
                    arrayList4.add(hikingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    activityDataHelper = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<MeditationSample> r(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ActivityDataHelper activityDataHelper = this;
        int i2 = i;
        ArrayList<MeditationSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    MeditationSample meditationSample = new MeditationSample();
                    meditationSample.setSess_id(str);
                    meditationSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = activityDataHelper.c(arrayList, j4, i2);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        meditationSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = activityDataHelper.b(arrayList2, j4, i2);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    ActivityGPSSample a2 = activityDataHelper.a(arrayList3, j4, i2);
                    if (a2 != null) {
                        sampleData.setLatitude(a2.getLocation().latitude);
                        sampleData.setLongitude(a2.getLocation().longitude);
                    }
                    meditationSample.setSampleData(sampleData);
                    arrayList4.add(meditationSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    activityDataHelper = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<PhysicalActivitySample> s(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ActivityDataHelper activityDataHelper = this;
        int i2 = i;
        ArrayList<PhysicalActivitySample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    PhysicalActivitySample physicalActivitySample = new PhysicalActivitySample();
                    physicalActivitySample.setSess_id(str);
                    physicalActivitySample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = activityDataHelper.c(arrayList, j4, i2);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        physicalActivitySample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = activityDataHelper.b(arrayList2, j4, i2);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    ActivityGPSSample a2 = activityDataHelper.a(arrayList3, j4, i2);
                    if (a2 != null) {
                        sampleData.setLatitude(a2.getLocation().latitude);
                        sampleData.setLongitude(a2.getLocation().longitude);
                    }
                    physicalActivitySample.setSampleData(sampleData);
                    arrayList4.add(physicalActivitySample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    activityDataHelper = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<RowingMachineSample> t(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<RowingMachineSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    RowingMachineSample rowingMachineSample = new RowingMachineSample();
                    rowingMachineSample.setSess_id(str);
                    rowingMachineSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = c(arrayList, j4, i);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        rowingMachineSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = b(arrayList2, j4, i);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    rowingMachineSample.setSampleData(sampleData);
                    arrayList3.add(rowingMachineSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<RunSample> u(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ActivityDataHelper activityDataHelper = this;
        int i2 = i;
        ArrayList<RunSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    RunSample runSample = new RunSample();
                    runSample.setSess_id(str);
                    runSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = activityDataHelper.c(arrayList, j4, i2);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        runSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = activityDataHelper.b(arrayList2, j4, i2);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    ActivityGPSSample a2 = activityDataHelper.a(arrayList3, j4, i2);
                    if (a2 != null) {
                        sampleData.setLatitude(a2.getLocation().latitude);
                        sampleData.setLongitude(a2.getLocation().longitude);
                    }
                    runSample.setSampleData(sampleData);
                    arrayList4.add(runSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    activityDataHelper = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<SkippingSample> v(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<SkippingSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    SkippingSample skippingSample = new SkippingSample();
                    skippingSample.setSess_id(str);
                    skippingSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = c(arrayList, j4, i);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        skippingSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = b(arrayList2, j4, i);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    skippingSample.setSampleData(sampleData);
                    arrayList3.add(skippingSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<TennisSample> w(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, long j, long j2, int i, String str, String str2) {
        ArrayList<TennisSample> arrayList3 = new ArrayList<>();
        long j3 = i * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    TennisSample tennisSample = new TennisSample();
                    tennisSample.setSess_id(str);
                    tennisSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = c(arrayList, j4, i);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        tennisSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = b(arrayList2, j4, i);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    tennisSample.setSampleData(sampleData);
                    arrayList3.add(tennisSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final TimeSpentHeartRateZone x(Context context, List<ActivityHeartRateSample> list) {
        TimeSpentHeartRateZone timeSpentHeartRateZone = new TimeSpentHeartRateZone();
        HeartRateZoneRanges geHeartRateZoneRanges = WorkoutUtils.INSTANCE.geHeartRateZoneRanges(new com.coveiot.android.activitymodes.preference.PreferenceManager(context).getAge());
        int sampleRateForSessionInSecs = Dashboard2Utils.Companion.getSampleRateForSessionInSecs(context);
        for (ActivityHeartRateSample activityHeartRateSample : list) {
            int hrValue = activityHeartRateSample.getHrValue();
            boolean z = true;
            if (hrValue <= geHeartRateZoneRanges.getZone2LowLimit() && geHeartRateZoneRanges.getZone1LowLimit() <= hrValue) {
                timeSpentHeartRateZone.setZone1Time(timeSpentHeartRateZone.getZone1Time() + sampleRateForSessionInSecs);
            } else if (hrValue <= geHeartRateZoneRanges.getZone3LowLimit() && geHeartRateZoneRanges.getZone2LowLimit() <= hrValue) {
                timeSpentHeartRateZone.setZone2Time(timeSpentHeartRateZone.getZone2Time() + sampleRateForSessionInSecs);
            } else if (hrValue <= geHeartRateZoneRanges.getZone4LowLimit() && geHeartRateZoneRanges.getZone3LowLimit() <= hrValue) {
                timeSpentHeartRateZone.setZone3Time(timeSpentHeartRateZone.getZone3Time() + sampleRateForSessionInSecs);
            } else if (hrValue <= geHeartRateZoneRanges.getZone5LowLimit() && geHeartRateZoneRanges.getZone4LowLimit() <= hrValue) {
                timeSpentHeartRateZone.setZone4Time(timeSpentHeartRateZone.getZone4Time() + sampleRateForSessionInSecs);
            } else {
                if ((hrValue > geHeartRateZoneRanges.getZone5HighLimit() || geHeartRateZoneRanges.getZone5LowLimit() > hrValue) ? false : false) {
                    timeSpentHeartRateZone.setZone5Time(timeSpentHeartRateZone.getZone5Time() + sampleRateForSessionInSecs);
                }
            }
        }
        return timeSpentHeartRateZone;
    }

    public final ArrayList<TreadmillSample> y(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ActivityDataHelper activityDataHelper = this;
        int i2 = i;
        ArrayList<TreadmillSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    TreadmillSample treadmillSample = new TreadmillSample();
                    treadmillSample.setSess_id(str);
                    treadmillSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = activityDataHelper.c(arrayList, j4, i2);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        treadmillSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = activityDataHelper.b(arrayList2, j4, i2);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    ActivityGPSSample a2 = activityDataHelper.a(arrayList3, j4, i2);
                    if (a2 != null) {
                        sampleData.setLatitude(a2.getLocation().latitude);
                        sampleData.setLongitude(a2.getLocation().longitude);
                    }
                    treadmillSample.setSampleData(sampleData);
                    arrayList4.add(treadmillSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    activityDataHelper = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }

    public final ArrayList<WalkSample> z(ArrayList<ActivityStepsSample> arrayList, ArrayList<ActivityHeartRateSample> arrayList2, ArrayList<ActivityGPSSample> arrayList3, long j, long j2, int i, String str, String str2) {
        ActivityDataHelper activityDataHelper = this;
        int i2 = i;
        ArrayList<WalkSample> arrayList4 = new ArrayList<>();
        long j3 = i2 * 1000;
        if (j3 > 0) {
            long progressionLastElement = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            if (j <= progressionLastElement) {
                long j4 = j;
                while (true) {
                    WalkSample walkSample = new WalkSample();
                    walkSample.setSess_id(str);
                    walkSample.setSeg_id(str2);
                    SampleData sampleData = new SampleData();
                    sampleData.setTimeStamp(j4);
                    ActivityStepsSample c = activityDataHelper.c(arrayList, j4, i2);
                    if (c != null) {
                        sampleData.setCalories((float) c.getCalories());
                        sampleData.setDistance((int) c.getDistance());
                        walkSample.setStepCount(c.getStepsValue());
                    }
                    ActivityHeartRateSample b = activityDataHelper.b(arrayList2, j4, i2);
                    if (b != null) {
                        sampleData.setHr_value(b.getHrValue());
                    }
                    ActivityGPSSample a2 = activityDataHelper.a(arrayList3, j4, i2);
                    if (a2 != null) {
                        sampleData.setLatitude(a2.getLocation().latitude);
                        sampleData.setLongitude(a2.getLocation().longitude);
                    }
                    walkSample.setSampleData(sampleData);
                    arrayList4.add(walkSample);
                    if (j4 == progressionLastElement) {
                        break;
                    }
                    j4 += j3;
                    activityDataHelper = this;
                    i2 = i;
                }
            }
            return arrayList4;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + j3 + '.');
    }
}
