package com.coveiot.android.activitymodes.repository;

import android.content.Context;
import android.util.Log;
import androidx.core.view.PointerIconCompat;
import com.abupdate.iot_libs.constant.Error;
import com.coveiot.android.activitymodes.BASEUNIT;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.activitymodes.database.entities.ActivityDataSample;
import com.coveiot.android.activitymodes.database.entities.BadmintonSample;
import com.coveiot.android.activitymodes.database.entities.BasketBallSample;
import com.coveiot.android.activitymodes.database.entities.ClimbingSample;
import com.coveiot.android.activitymodes.database.entities.CyclingSample;
import com.coveiot.android.activitymodes.database.entities.DanceSample;
import com.coveiot.android.activitymodes.database.entities.EllipticalSample;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationActivity;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment;
import com.coveiot.android.activitymodes.database.entities.FootballSample;
import com.coveiot.android.activitymodes.database.entities.FreeExerciseSample;
import com.coveiot.android.activitymodes.database.entities.HikingSample;
import com.coveiot.android.activitymodes.database.entities.MeditationSample;
import com.coveiot.android.activitymodes.database.entities.PhysicalActivitySample;
import com.coveiot.android.activitymodes.database.entities.RowingMachineSample;
import com.coveiot.android.activitymodes.database.entities.RunSample;
import com.coveiot.android.activitymodes.database.entities.Sample;
import com.coveiot.android.activitymodes.database.entities.SampleData;
import com.coveiot.android.activitymodes.database.entities.SkippingSample;
import com.coveiot.android.activitymodes.database.entities.TennisSample;
import com.coveiot.android.activitymodes.database.entities.TreadmillSample;
import com.coveiot.android.activitymodes.database.entities.WalkSample;
import com.coveiot.android.activitymodes.database.entities.WorkoutSample;
import com.coveiot.android.activitymodes.database.entities.YogaSample;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.coveaccess.activitysession.ActivitiesSampleDetails;
import com.coveiot.coveaccess.activitysession.BadmintonActivityDetails;
import com.coveiot.coveaccess.activitysession.BaseUnits;
import com.coveiot.coveaccess.activitysession.BasketBallActivityDetails;
import com.coveiot.coveaccess.activitysession.ClimbingActivityDetails;
import com.coveiot.coveaccess.activitysession.CycleActivityDetails;
import com.coveiot.coveaccess.activitysession.DanceActivityDetails;
import com.coveiot.coveaccess.activitysession.DeviceSpecificParams;
import com.coveiot.coveaccess.activitysession.EllipticalActivityDetails;
import com.coveiot.coveaccess.activitysession.FootBallActivityDetails;
import com.coveiot.coveaccess.activitysession.FreeExerciseActivityDetails;
import com.coveiot.coveaccess.activitysession.HikingActivityDetails;
import com.coveiot.coveaccess.activitysession.MeditationActivityDetails;
import com.coveiot.coveaccess.activitysession.OtherParams;
import com.coveiot.coveaccess.activitysession.PhysicalActivityDetails;
import com.coveiot.coveaccess.activitysession.PostActivitySessionDataRequest;
import com.coveiot.coveaccess.activitysession.RowingMachineActivityDetails;
import com.coveiot.coveaccess.activitysession.RunWalkActivityDetails;
import com.coveiot.coveaccess.activitysession.SkippingActivityDetails;
import com.coveiot.coveaccess.activitysession.TennisActivityDetails;
import com.coveiot.coveaccess.activitysession.TraqActivityLogs;
import com.coveiot.coveaccess.activitysession.TreadmillActivityDetails;
import com.coveiot.coveaccess.activitysession.WorkoutActivityDetails;
import com.coveiot.coveaccess.activitysession.YogaActivityDetails;
import com.coveiot.coveaccess.fitness.ActivityCode;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.model.server.GenericActivitySessionData;
import com.coveiot.coveaccess.model.server.SCurrentPreparationPlanRes;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import com.goodix.ble.gr.libdfu.task.sub.ResultCode;
import com.goodix.ble.libcomx.util.AccessLock;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.touchgui.sdk.TGEventListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class Formator {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes2.dex */
    public static final class Companion {

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getActivityDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1762, 1799}, m = "invokeSuspend", n = {"activitiesSampleDetails", "activitiesSampleDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super GenericActivitySessionData>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super GenericActivitySessionData> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:41:0x0199  */
            /* JADX WARN: Removed duplicated region for block: B:50:0x01de  */
            /* JADX WARN: Removed duplicated region for block: B:51:0x01ec  */
            /* JADX WARN: Removed duplicated region for block: B:54:0x01f0  */
            /* JADX WARN: Removed duplicated region for block: B:68:0x0291  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x01c9 -> B:44:0x01cc). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r18) {
                /*
                    Method dump skipped, instructions count: 664
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.a.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getRowingMachineDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1700, 1710}, m = "invokeSuspend", n = {"rowingMachineActivityDetails", "rowingMachineActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class a0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RowingMachineActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a0(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super a0> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a0(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super RowingMachineActivityDetails> continuation) {
                return ((a0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0186  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 397
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.a0.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2440}, m = "prepareFootBallDetailsObject", n = {"footBallActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class a1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public a1(Continuation<? super a1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.O(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getActivitySampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<ActivityDataSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super b> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<ActivityDataSample>> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getActivityData() : null) != null) {
                            GenericActivitySessionData activityData = this.$requestData.getActivityData();
                            if ((activityData != null ? activityData.getTraqActivityLogs() : null) != null) {
                                GenericActivitySessionData activityData2 = this.$requestData.getActivityData();
                                Boolean boxBoolean = (activityData2 == null || (traqActivityLogs = activityData2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    GenericActivitySessionData activityData3 = this.$requestData.getActivityData();
                                    List<TraqActivityLogs> traqActivityLogs2 = activityData3 != null ? activityData3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                int i = Formator.Companion.i(traqActivityLogs3);
                                                for (int i2 = 0; i2 < i; i2++) {
                                                    ActivityDataSample activityDataSample = new ActivityDataSample();
                                                    activityDataSample.setSessionID(this.$workoutSession.getSession_id());
                                                    activityDataSample.setSegmentID(str);
                                                    activityDataSample.setActivityType(this.$workoutSession.getActivity_type());
                                                    if (i2 == 0) {
                                                        activityDataSample.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                    } else {
                                                        activityDataSample.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                    }
                                                    if (traqActivityLogs3.getStepValues() != null && traqActivityLogs3.getStepValues().size() > 0 && i2 < traqActivityLogs3.getStepValues().size()) {
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        activityDataSample.setStepCount(num.intValue());
                                                    }
                                                    if (traqActivityLogs3.getCalorieValues() != null && traqActivityLogs3.getCalorieValues().size() > 0 && i2 < traqActivityLogs3.getCalorieValues().size()) {
                                                        Float f = traqActivityLogs3.getCalorieValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(f, "log.calorieValues[i]");
                                                        activityDataSample.setCalories(f.floatValue());
                                                    }
                                                    if (traqActivityLogs3.getDistanceValues() != null && traqActivityLogs3.getDistanceValues().size() > 0 && i2 < traqActivityLogs3.getDistanceValues().size()) {
                                                        Integer num2 = traqActivityLogs3.getDistanceValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num2, "log.distanceValues[i]");
                                                        activityDataSample.setDistance(num2.intValue());
                                                    }
                                                    if (traqActivityLogs3.getHrValues() != null && traqActivityLogs3.getHrValues().size() > 0 && i2 < traqActivityLogs3.getHrValues().size()) {
                                                        Integer num3 = traqActivityLogs3.getHrValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num3, "log.hrValues[i]");
                                                        activityDataSample.setHrValue(num3.intValue());
                                                    }
                                                    if (traqActivityLogs3.getCoordinateValues() != null && traqActivityLogs3.getCoordinateValues().size() > 0 && i2 < traqActivityLogs3.getCoordinateValues().size()) {
                                                        Double d = traqActivityLogs3.getCoordinateValues().get(i2).get(0);
                                                        Intrinsics.checkNotNullExpressionValue(d, "log.coordinateValues[i][0]");
                                                        activityDataSample.setLatitude(d.doubleValue());
                                                        Double d2 = traqActivityLogs3.getCoordinateValues().get(i2).get(1);
                                                        Intrinsics.checkNotNullExpressionValue(d2, "log.coordinateValues[i][1]");
                                                        activityDataSample.setLongitude(d2.doubleValue());
                                                    }
                                                    if (traqActivityLogs3.getSpeedValues() != null && traqActivityLogs3.getSpeedValues().size() > 0 && i2 < traqActivityLogs3.getSpeedValues().size()) {
                                                        Float f2 = traqActivityLogs3.getSpeedValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(f2, "log.speedValues[i]");
                                                        activityDataSample.setSpeedValue(f2.floatValue());
                                                    }
                                                    arrayList.add(activityDataSample);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getRowingMachineSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class b0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<RowingMachineSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b0(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super b0> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b0(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<RowingMachineSample>> continuation) {
                return ((b0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getRowingDetails() : null) != null) {
                            RowingMachineActivityDetails rowingDetails = this.$requestData.getRowingDetails();
                            if ((rowingDetails != null ? rowingDetails.getTraqActivityLogs() : null) != null) {
                                RowingMachineActivityDetails rowingDetails2 = this.$requestData.getRowingDetails();
                                Boolean boxBoolean = (rowingDetails2 == null || (traqActivityLogs = rowingDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    RowingMachineActivityDetails rowingDetails3 = this.$requestData.getRowingDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = rowingDetails3 != null ? rowingDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        RowingMachineSample rowingMachineSample = new RowingMachineSample();
                                                        rowingMachineSample.setSess_id(this.$workoutSession.getSession_id());
                                                        rowingMachineSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        if (traqActivityLogs3.getStepValues() != null) {
                                                            Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                            Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                            rowingMachineSample.setStepCount(num.intValue());
                                                        }
                                                        rowingMachineSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(rowingMachineSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2298}, m = "prepareFreeExercisenDetailsObject", n = {"freeExerciseActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class b1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public b1(Continuation<? super b1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.P(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getBadmintonDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1255, 1265}, m = "invokeSuspend", n = {"badmintonActivityDetails", "badmintonActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BadmintonActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public c(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super c> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new c(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super BadmintonActivityDetails> continuation) {
                return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0179  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 384
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.c.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getRunDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {603, com.veryfit.multi.nativeprotocol.b.I2}, m = "invokeSuspend", n = {"runWalkActivityDetails", "runWalkActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class c0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RunWalkActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public c0(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super c0> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new c0(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super RunWalkActivityDetails> continuation) {
                return ((c0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x0249  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r25) {
                /*
                    Method dump skipped, instructions count: 592
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.c0.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2105}, m = "prepareMeditationDetailsObject", n = {"meditationActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class c1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public c1(Continuation<? super c1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.Q(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getBadmintonSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<BadmintonSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super d> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new d(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<BadmintonSample>> continuation) {
                return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getBadmintonDetails() : null) != null) {
                            BadmintonActivityDetails badmintonDetails = this.$requestData.getBadmintonDetails();
                            if ((badmintonDetails != null ? badmintonDetails.getTraqActivityLogs() : null) != null) {
                                BadmintonActivityDetails badmintonDetails2 = this.$requestData.getBadmintonDetails();
                                Boolean boxBoolean = (badmintonDetails2 == null || (traqActivityLogs = badmintonDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    BadmintonActivityDetails badmintonDetails3 = this.$requestData.getBadmintonDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = badmintonDetails3 != null ? badmintonDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        BadmintonSample badmintonSample = new BadmintonSample();
                                                        badmintonSample.setSess_id(this.$workoutSession.getSession_id());
                                                        badmintonSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        badmintonSample.setStepCount(num.intValue());
                                                        badmintonSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(badmintonSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getRunSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class d0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<RunSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d0(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super d0> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new d0(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<RunSample>> continuation) {
                return ((d0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getRunDetails() : null) != null) {
                            RunWalkActivityDetails runDetails = this.$requestData.getRunDetails();
                            if ((runDetails != null ? runDetails.getTraqActivityLogs() : null) != null) {
                                RunWalkActivityDetails runDetails2 = this.$requestData.getRunDetails();
                                Boolean boxBoolean = (runDetails2 == null || (traqActivityLogs = runDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    RunWalkActivityDetails runDetails3 = this.$requestData.getRunDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = runDetails3 != null ? runDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        RunSample runSample = new RunSample();
                                                        runSample.setSess_id(this.$workoutSession.getSession_id());
                                                        runSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        runSample.setStepCount(num.intValue());
                                                        runSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(runSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {1919}, m = "preparePhysicalActivityDetailsObject", n = {"physicalActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class d1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public d1(Continuation<? super d1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.R(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getBasketBallDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1511, 1521}, m = "invokeSuspend", n = {"basketBallActivityDetails", "basketBallActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BasketBallActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public e(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super e> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new e(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super BasketBallActivityDetails> continuation) {
                return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0186  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 397
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.e.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getSegmentsList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class e0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<EntityWorkoutSessionSegment>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public e0(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super e0> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new e0(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<EntityWorkoutSessionSegment>> continuation) {
                return ((e0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                List<TraqActivityLogs> traqActivityLogs2;
                List<TraqActivityLogs> traqActivityLogs3;
                List<TraqActivityLogs> traqActivityLogs4;
                List<TraqActivityLogs> traqActivityLogs5;
                List<TraqActivityLogs> traqActivityLogs6;
                List<TraqActivityLogs> traqActivityLogs7;
                List<TraqActivityLogs> traqActivityLogs8;
                List<TraqActivityLogs> traqActivityLogs9;
                List<TraqActivityLogs> traqActivityLogs10;
                List<TraqActivityLogs> traqActivityLogs11;
                List<TraqActivityLogs> traqActivityLogs12;
                List<TraqActivityLogs> traqActivityLogs13;
                List<TraqActivityLogs> traqActivityLogs14;
                List<TraqActivityLogs> traqActivityLogs15;
                List<TraqActivityLogs> traqActivityLogs16;
                List<TraqActivityLogs> traqActivityLogs17;
                List<TraqActivityLogs> traqActivityLogs18;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getWalkDetails() : null) != null) {
                            RunWalkActivityDetails walkDetails = this.$requestData.getWalkDetails();
                            if ((walkDetails != null ? walkDetails.getTraqActivityLogs() : null) != null) {
                                RunWalkActivityDetails walkDetails2 = this.$requestData.getWalkDetails();
                                Boolean boxBoolean = (walkDetails2 == null || (traqActivityLogs18 = walkDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs18.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    RunWalkActivityDetails walkDetails3 = this.$requestData.getWalkDetails();
                                    traqActivityLogs = walkDetails3 != null ? walkDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs);
                                    HashMap y = Formator.Companion.y(traqActivityLogs);
                                    for (String segmentId : y.keySet()) {
                                        EntityWorkoutSessionSegment entityWorkoutSessionSegment = new EntityWorkoutSessionSegment();
                                        Intrinsics.checkNotNullExpressionValue(segmentId, "segmentId");
                                        entityWorkoutSessionSegment.setSegment_id(segmentId);
                                        entityWorkoutSessionSegment.setSess_id(this.$workoutSession.getSession_id());
                                        entityWorkoutSessionSegment.setSegment_duration(Formator.Companion.x((List) y.get(segmentId), this.$workoutSession.getSteps_sampling_rate()));
                                        Object obj2 = y.get(segmentId);
                                        Intrinsics.checkNotNull(obj2);
                                        entityWorkoutSessionSegment.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj2).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                        entityWorkoutSessionSegment.setEnd_time(entityWorkoutSessionSegment.getStart_time() + entityWorkoutSessionSegment.getSegment_duration());
                                        arrayList.add(entityWorkoutSessionSegment);
                                    }
                                }
                            }
                        } else {
                            PostActivitySessionDataRequest postActivitySessionDataRequest2 = this.$requestData;
                            if ((postActivitySessionDataRequest2 != null ? postActivitySessionDataRequest2.getRunDetails() : null) != null) {
                                RunWalkActivityDetails runDetails = this.$requestData.getRunDetails();
                                if ((runDetails != null ? runDetails.getTraqActivityLogs() : null) != null) {
                                    RunWalkActivityDetails runDetails2 = this.$requestData.getRunDetails();
                                    Boolean boxBoolean2 = (runDetails2 == null || (traqActivityLogs17 = runDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs17.isEmpty());
                                    Intrinsics.checkNotNull(boxBoolean2);
                                    if (boxBoolean2.booleanValue()) {
                                        RunWalkActivityDetails runDetails3 = this.$requestData.getRunDetails();
                                        traqActivityLogs = runDetails3 != null ? runDetails3.getTraqActivityLogs() : null;
                                        Intrinsics.checkNotNull(traqActivityLogs);
                                        HashMap y2 = Formator.Companion.y(traqActivityLogs);
                                        for (String segmentId2 : y2.keySet()) {
                                            EntityWorkoutSessionSegment entityWorkoutSessionSegment2 = new EntityWorkoutSessionSegment();
                                            Intrinsics.checkNotNullExpressionValue(segmentId2, "segmentId");
                                            entityWorkoutSessionSegment2.setSegment_id(segmentId2);
                                            entityWorkoutSessionSegment2.setSess_id(this.$workoutSession.getSession_id());
                                            entityWorkoutSessionSegment2.setSegment_duration(Formator.Companion.x((List) y2.get(segmentId2), this.$workoutSession.getSteps_sampling_rate()));
                                            Object obj3 = y2.get(segmentId2);
                                            Intrinsics.checkNotNull(obj3);
                                            entityWorkoutSessionSegment2.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj3).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                            entityWorkoutSessionSegment2.setEnd_time(entityWorkoutSessionSegment2.getStart_time() + entityWorkoutSessionSegment2.getSegment_duration());
                                            arrayList.add(entityWorkoutSessionSegment2);
                                        }
                                    }
                                }
                            } else {
                                PostActivitySessionDataRequest postActivitySessionDataRequest3 = this.$requestData;
                                if ((postActivitySessionDataRequest3 != null ? postActivitySessionDataRequest3.getHikingDetails() : null) != null) {
                                    HikingActivityDetails hikingDetails = this.$requestData.getHikingDetails();
                                    if ((hikingDetails != null ? hikingDetails.getTraqActivityLogs() : null) != null) {
                                        HikingActivityDetails hikingDetails2 = this.$requestData.getHikingDetails();
                                        Boolean boxBoolean3 = (hikingDetails2 == null || (traqActivityLogs16 = hikingDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs16.isEmpty());
                                        Intrinsics.checkNotNull(boxBoolean3);
                                        if (boxBoolean3.booleanValue()) {
                                            HikingActivityDetails hikingDetails3 = this.$requestData.getHikingDetails();
                                            traqActivityLogs = hikingDetails3 != null ? hikingDetails3.getTraqActivityLogs() : null;
                                            Intrinsics.checkNotNull(traqActivityLogs);
                                            HashMap y3 = Formator.Companion.y(traqActivityLogs);
                                            for (String segmentId3 : y3.keySet()) {
                                                EntityWorkoutSessionSegment entityWorkoutSessionSegment3 = new EntityWorkoutSessionSegment();
                                                Intrinsics.checkNotNullExpressionValue(segmentId3, "segmentId");
                                                entityWorkoutSessionSegment3.setSegment_id(segmentId3);
                                                entityWorkoutSessionSegment3.setSess_id(this.$workoutSession.getSession_id());
                                                entityWorkoutSessionSegment3.setSegment_duration(Formator.Companion.x((List) y3.get(segmentId3), this.$workoutSession.getSteps_sampling_rate()));
                                                Object obj4 = y3.get(segmentId3);
                                                Intrinsics.checkNotNull(obj4);
                                                entityWorkoutSessionSegment3.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj4).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                entityWorkoutSessionSegment3.setEnd_time(entityWorkoutSessionSegment3.getStart_time() + entityWorkoutSessionSegment3.getSegment_duration());
                                                arrayList.add(entityWorkoutSessionSegment3);
                                            }
                                        }
                                    }
                                } else {
                                    PostActivitySessionDataRequest postActivitySessionDataRequest4 = this.$requestData;
                                    if ((postActivitySessionDataRequest4 != null ? postActivitySessionDataRequest4.getBadmintonDetails() : null) != null) {
                                        BadmintonActivityDetails badmintonDetails = this.$requestData.getBadmintonDetails();
                                        if ((badmintonDetails != null ? badmintonDetails.getTraqActivityLogs() : null) != null) {
                                            BadmintonActivityDetails badmintonDetails2 = this.$requestData.getBadmintonDetails();
                                            Boolean boxBoolean4 = (badmintonDetails2 == null || (traqActivityLogs15 = badmintonDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs15.isEmpty());
                                            Intrinsics.checkNotNull(boxBoolean4);
                                            if (boxBoolean4.booleanValue()) {
                                                BadmintonActivityDetails badmintonDetails3 = this.$requestData.getBadmintonDetails();
                                                traqActivityLogs = badmintonDetails3 != null ? badmintonDetails3.getTraqActivityLogs() : null;
                                                Intrinsics.checkNotNull(traqActivityLogs);
                                                HashMap y4 = Formator.Companion.y(traqActivityLogs);
                                                for (String segmentId4 : y4.keySet()) {
                                                    EntityWorkoutSessionSegment entityWorkoutSessionSegment4 = new EntityWorkoutSessionSegment();
                                                    Intrinsics.checkNotNullExpressionValue(segmentId4, "segmentId");
                                                    entityWorkoutSessionSegment4.setSegment_id(segmentId4);
                                                    entityWorkoutSessionSegment4.setSess_id(this.$workoutSession.getSession_id());
                                                    entityWorkoutSessionSegment4.setSegment_duration(Formator.Companion.x((List) y4.get(segmentId4), this.$workoutSession.getSteps_sampling_rate()));
                                                    Object obj5 = y4.get(segmentId4);
                                                    Intrinsics.checkNotNull(obj5);
                                                    entityWorkoutSessionSegment4.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj5).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                    entityWorkoutSessionSegment4.setEnd_time(entityWorkoutSessionSegment4.getStart_time() + entityWorkoutSessionSegment4.getSegment_duration());
                                                    arrayList.add(entityWorkoutSessionSegment4);
                                                }
                                            }
                                        }
                                    } else {
                                        PostActivitySessionDataRequest postActivitySessionDataRequest5 = this.$requestData;
                                        if ((postActivitySessionDataRequest5 != null ? postActivitySessionDataRequest5.getFreeExerciseActivityDetails() : null) != null) {
                                            FreeExerciseActivityDetails freeExerciseActivityDetails = this.$requestData.getFreeExerciseActivityDetails();
                                            if ((freeExerciseActivityDetails != null ? freeExerciseActivityDetails.getTraqActivityLogs() : null) != null) {
                                                FreeExerciseActivityDetails freeExerciseActivityDetails2 = this.$requestData.getFreeExerciseActivityDetails();
                                                Boolean boxBoolean5 = (freeExerciseActivityDetails2 == null || (traqActivityLogs14 = freeExerciseActivityDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs14.isEmpty());
                                                Intrinsics.checkNotNull(boxBoolean5);
                                                if (boxBoolean5.booleanValue()) {
                                                    FreeExerciseActivityDetails freeExerciseActivityDetails3 = this.$requestData.getFreeExerciseActivityDetails();
                                                    traqActivityLogs = freeExerciseActivityDetails3 != null ? freeExerciseActivityDetails3.getTraqActivityLogs() : null;
                                                    Intrinsics.checkNotNull(traqActivityLogs);
                                                    HashMap y5 = Formator.Companion.y(traqActivityLogs);
                                                    for (String segmentId5 : y5.keySet()) {
                                                        EntityWorkoutSessionSegment entityWorkoutSessionSegment5 = new EntityWorkoutSessionSegment();
                                                        Intrinsics.checkNotNullExpressionValue(segmentId5, "segmentId");
                                                        entityWorkoutSessionSegment5.setSegment_id(segmentId5);
                                                        entityWorkoutSessionSegment5.setSess_id(this.$workoutSession.getSession_id());
                                                        entityWorkoutSessionSegment5.setSegment_duration(Formator.Companion.x((List) y5.get(segmentId5), this.$workoutSession.getSteps_sampling_rate()));
                                                        Object obj6 = y5.get(segmentId5);
                                                        Intrinsics.checkNotNull(obj6);
                                                        entityWorkoutSessionSegment5.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj6).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        entityWorkoutSessionSegment5.setEnd_time(entityWorkoutSessionSegment5.getStart_time() + entityWorkoutSessionSegment5.getSegment_duration());
                                                        arrayList.add(entityWorkoutSessionSegment5);
                                                    }
                                                }
                                            }
                                        } else {
                                            PostActivitySessionDataRequest postActivitySessionDataRequest6 = this.$requestData;
                                            if ((postActivitySessionDataRequest6 != null ? postActivitySessionDataRequest6.getDanceDetails() : null) != null) {
                                                DanceActivityDetails danceDetails = this.$requestData.getDanceDetails();
                                                if ((danceDetails != null ? danceDetails.getTraqActivityLogs() : null) != null) {
                                                    DanceActivityDetails danceDetails2 = this.$requestData.getDanceDetails();
                                                    Boolean boxBoolean6 = (danceDetails2 == null || (traqActivityLogs13 = danceDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs13.isEmpty());
                                                    Intrinsics.checkNotNull(boxBoolean6);
                                                    if (boxBoolean6.booleanValue()) {
                                                        DanceActivityDetails danceDetails3 = this.$requestData.getDanceDetails();
                                                        traqActivityLogs = danceDetails3 != null ? danceDetails3.getTraqActivityLogs() : null;
                                                        Intrinsics.checkNotNull(traqActivityLogs);
                                                        HashMap y6 = Formator.Companion.y(traqActivityLogs);
                                                        for (String segmentId6 : y6.keySet()) {
                                                            EntityWorkoutSessionSegment entityWorkoutSessionSegment6 = new EntityWorkoutSessionSegment();
                                                            Intrinsics.checkNotNullExpressionValue(segmentId6, "segmentId");
                                                            entityWorkoutSessionSegment6.setSegment_id(segmentId6);
                                                            entityWorkoutSessionSegment6.setSess_id(this.$workoutSession.getSession_id());
                                                            entityWorkoutSessionSegment6.setSegment_duration(Formator.Companion.x((List) y6.get(segmentId6), this.$workoutSession.getSteps_sampling_rate()));
                                                            Object obj7 = y6.get(segmentId6);
                                                            Intrinsics.checkNotNull(obj7);
                                                            entityWorkoutSessionSegment6.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj7).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                            entityWorkoutSessionSegment6.setEnd_time(entityWorkoutSessionSegment6.getStart_time() + entityWorkoutSessionSegment6.getSegment_duration());
                                                            arrayList.add(entityWorkoutSessionSegment6);
                                                        }
                                                    }
                                                }
                                            } else {
                                                PostActivitySessionDataRequest postActivitySessionDataRequest7 = this.$requestData;
                                                if ((postActivitySessionDataRequest7 != null ? postActivitySessionDataRequest7.getYogaDetails() : null) != null) {
                                                    YogaActivityDetails yogaDetails = this.$requestData.getYogaDetails();
                                                    if ((yogaDetails != null ? yogaDetails.getTraqActivityLogs() : null) != null) {
                                                        YogaActivityDetails yogaDetails2 = this.$requestData.getYogaDetails();
                                                        Boolean boxBoolean7 = (yogaDetails2 == null || (traqActivityLogs12 = yogaDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs12.isEmpty());
                                                        Intrinsics.checkNotNull(boxBoolean7);
                                                        if (boxBoolean7.booleanValue()) {
                                                            YogaActivityDetails yogaDetails3 = this.$requestData.getYogaDetails();
                                                            traqActivityLogs = yogaDetails3 != null ? yogaDetails3.getTraqActivityLogs() : null;
                                                            Intrinsics.checkNotNull(traqActivityLogs);
                                                            HashMap y7 = Formator.Companion.y(traqActivityLogs);
                                                            for (String segmentId7 : y7.keySet()) {
                                                                EntityWorkoutSessionSegment entityWorkoutSessionSegment7 = new EntityWorkoutSessionSegment();
                                                                Intrinsics.checkNotNullExpressionValue(segmentId7, "segmentId");
                                                                entityWorkoutSessionSegment7.setSegment_id(segmentId7);
                                                                entityWorkoutSessionSegment7.setSess_id(this.$workoutSession.getSession_id());
                                                                entityWorkoutSessionSegment7.setSegment_duration(Formator.Companion.x((List) y7.get(segmentId7), this.$workoutSession.getSteps_sampling_rate()));
                                                                Object obj8 = y7.get(segmentId7);
                                                                Intrinsics.checkNotNull(obj8);
                                                                entityWorkoutSessionSegment7.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj8).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                entityWorkoutSessionSegment7.setEnd_time(entityWorkoutSessionSegment7.getStart_time() + entityWorkoutSessionSegment7.getSegment_duration());
                                                                arrayList.add(entityWorkoutSessionSegment7);
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    PostActivitySessionDataRequest postActivitySessionDataRequest8 = this.$requestData;
                                                    if ((postActivitySessionDataRequest8 != null ? postActivitySessionDataRequest8.getWorkoutDetails() : null) != null) {
                                                        WorkoutActivityDetails workoutDetails = this.$requestData.getWorkoutDetails();
                                                        if ((workoutDetails != null ? workoutDetails.getTraqActivityLogs() : null) != null) {
                                                            WorkoutActivityDetails workoutDetails2 = this.$requestData.getWorkoutDetails();
                                                            Boolean boxBoolean8 = (workoutDetails2 == null || (traqActivityLogs11 = workoutDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs11.isEmpty());
                                                            Intrinsics.checkNotNull(boxBoolean8);
                                                            if (boxBoolean8.booleanValue()) {
                                                                WorkoutActivityDetails workoutDetails3 = this.$requestData.getWorkoutDetails();
                                                                traqActivityLogs = workoutDetails3 != null ? workoutDetails3.getTraqActivityLogs() : null;
                                                                Intrinsics.checkNotNull(traqActivityLogs);
                                                                HashMap y8 = Formator.Companion.y(traqActivityLogs);
                                                                for (String segmentId8 : y8.keySet()) {
                                                                    EntityWorkoutSessionSegment entityWorkoutSessionSegment8 = new EntityWorkoutSessionSegment();
                                                                    Intrinsics.checkNotNullExpressionValue(segmentId8, "segmentId");
                                                                    entityWorkoutSessionSegment8.setSegment_id(segmentId8);
                                                                    entityWorkoutSessionSegment8.setSess_id(this.$workoutSession.getSession_id());
                                                                    entityWorkoutSessionSegment8.setSegment_duration(Formator.Companion.x((List) y8.get(segmentId8), this.$workoutSession.getSteps_sampling_rate()));
                                                                    Object obj9 = y8.get(segmentId8);
                                                                    Intrinsics.checkNotNull(obj9);
                                                                    entityWorkoutSessionSegment8.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj9).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                    entityWorkoutSessionSegment8.setEnd_time(entityWorkoutSessionSegment8.getStart_time() + entityWorkoutSessionSegment8.getSegment_duration());
                                                                    arrayList.add(entityWorkoutSessionSegment8);
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        PostActivitySessionDataRequest postActivitySessionDataRequest9 = this.$requestData;
                                                        if ((postActivitySessionDataRequest9 != null ? postActivitySessionDataRequest9.getFootballDetails() : null) != null) {
                                                            FootBallActivityDetails footballDetails = this.$requestData.getFootballDetails();
                                                            if ((footballDetails != null ? footballDetails.getTraqActivityLogs() : null) != null) {
                                                                FootBallActivityDetails footballDetails2 = this.$requestData.getFootballDetails();
                                                                Boolean boxBoolean9 = (footballDetails2 == null || (traqActivityLogs10 = footballDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs10.isEmpty());
                                                                Intrinsics.checkNotNull(boxBoolean9);
                                                                if (boxBoolean9.booleanValue()) {
                                                                    FootBallActivityDetails footballDetails3 = this.$requestData.getFootballDetails();
                                                                    traqActivityLogs = footballDetails3 != null ? footballDetails3.getTraqActivityLogs() : null;
                                                                    Intrinsics.checkNotNull(traqActivityLogs);
                                                                    HashMap y9 = Formator.Companion.y(traqActivityLogs);
                                                                    for (String segmentId9 : y9.keySet()) {
                                                                        EntityWorkoutSessionSegment entityWorkoutSessionSegment9 = new EntityWorkoutSessionSegment();
                                                                        Intrinsics.checkNotNullExpressionValue(segmentId9, "segmentId");
                                                                        entityWorkoutSessionSegment9.setSegment_id(segmentId9);
                                                                        entityWorkoutSessionSegment9.setSess_id(this.$workoutSession.getSession_id());
                                                                        entityWorkoutSessionSegment9.setSegment_duration(Formator.Companion.x((List) y9.get(segmentId9), this.$workoutSession.getSteps_sampling_rate()));
                                                                        Object obj10 = y9.get(segmentId9);
                                                                        Intrinsics.checkNotNull(obj10);
                                                                        entityWorkoutSessionSegment9.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj10).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                        entityWorkoutSessionSegment9.setEnd_time(entityWorkoutSessionSegment9.getStart_time() + entityWorkoutSessionSegment9.getSegment_duration());
                                                                        arrayList.add(entityWorkoutSessionSegment9);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            PostActivitySessionDataRequest postActivitySessionDataRequest10 = this.$requestData;
                                                            if ((postActivitySessionDataRequest10 != null ? postActivitySessionDataRequest10.getBasketballDetails() : null) != null) {
                                                                BasketBallActivityDetails basketballDetails = this.$requestData.getBasketballDetails();
                                                                if ((basketballDetails != null ? basketballDetails.getTraqActivityLogs() : null) != null) {
                                                                    BasketBallActivityDetails basketballDetails2 = this.$requestData.getBasketballDetails();
                                                                    Boolean boxBoolean10 = (basketballDetails2 == null || (traqActivityLogs9 = basketballDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs9.isEmpty());
                                                                    Intrinsics.checkNotNull(boxBoolean10);
                                                                    if (boxBoolean10.booleanValue()) {
                                                                        BasketBallActivityDetails basketballDetails3 = this.$requestData.getBasketballDetails();
                                                                        traqActivityLogs = basketballDetails3 != null ? basketballDetails3.getTraqActivityLogs() : null;
                                                                        Intrinsics.checkNotNull(traqActivityLogs);
                                                                        HashMap y10 = Formator.Companion.y(traqActivityLogs);
                                                                        for (String segmentId10 : y10.keySet()) {
                                                                            EntityWorkoutSessionSegment entityWorkoutSessionSegment10 = new EntityWorkoutSessionSegment();
                                                                            Intrinsics.checkNotNullExpressionValue(segmentId10, "segmentId");
                                                                            entityWorkoutSessionSegment10.setSegment_id(segmentId10);
                                                                            entityWorkoutSessionSegment10.setSess_id(this.$workoutSession.getSession_id());
                                                                            entityWorkoutSessionSegment10.setSegment_duration(Formator.Companion.x((List) y10.get(segmentId10), this.$workoutSession.getSteps_sampling_rate()));
                                                                            Object obj11 = y10.get(segmentId10);
                                                                            Intrinsics.checkNotNull(obj11);
                                                                            entityWorkoutSessionSegment10.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj11).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                            entityWorkoutSessionSegment10.setEnd_time(entityWorkoutSessionSegment10.getStart_time() + entityWorkoutSessionSegment10.getSegment_duration());
                                                                            arrayList.add(entityWorkoutSessionSegment10);
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                PostActivitySessionDataRequest postActivitySessionDataRequest11 = this.$requestData;
                                                                if ((postActivitySessionDataRequest11 != null ? postActivitySessionDataRequest11.getTennisDetails() : null) != null) {
                                                                    TennisActivityDetails tennisDetails = this.$requestData.getTennisDetails();
                                                                    if ((tennisDetails != null ? tennisDetails.getTraqActivityLogs() : null) != null) {
                                                                        TennisActivityDetails tennisDetails2 = this.$requestData.getTennisDetails();
                                                                        Boolean boxBoolean11 = (tennisDetails2 == null || (traqActivityLogs8 = tennisDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs8.isEmpty());
                                                                        Intrinsics.checkNotNull(boxBoolean11);
                                                                        if (boxBoolean11.booleanValue()) {
                                                                            TennisActivityDetails tennisDetails3 = this.$requestData.getTennisDetails();
                                                                            traqActivityLogs = tennisDetails3 != null ? tennisDetails3.getTraqActivityLogs() : null;
                                                                            Intrinsics.checkNotNull(traqActivityLogs);
                                                                            HashMap y11 = Formator.Companion.y(traqActivityLogs);
                                                                            for (String segmentId11 : y11.keySet()) {
                                                                                EntityWorkoutSessionSegment entityWorkoutSessionSegment11 = new EntityWorkoutSessionSegment();
                                                                                Intrinsics.checkNotNullExpressionValue(segmentId11, "segmentId");
                                                                                entityWorkoutSessionSegment11.setSegment_id(segmentId11);
                                                                                entityWorkoutSessionSegment11.setSess_id(this.$workoutSession.getSession_id());
                                                                                entityWorkoutSessionSegment11.setSegment_duration(Formator.Companion.x((List) y11.get(segmentId11), this.$workoutSession.getSteps_sampling_rate()));
                                                                                Object obj12 = y11.get(segmentId11);
                                                                                Intrinsics.checkNotNull(obj12);
                                                                                entityWorkoutSessionSegment11.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj12).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                                entityWorkoutSessionSegment11.setEnd_time(entityWorkoutSessionSegment11.getStart_time() + entityWorkoutSessionSegment11.getSegment_duration());
                                                                                arrayList.add(entityWorkoutSessionSegment11);
                                                                            }
                                                                        }
                                                                    }
                                                                } else {
                                                                    PostActivitySessionDataRequest postActivitySessionDataRequest12 = this.$requestData;
                                                                    if ((postActivitySessionDataRequest12 != null ? postActivitySessionDataRequest12.getCycleDetails() : null) != null) {
                                                                        CycleActivityDetails cycleDetails = this.$requestData.getCycleDetails();
                                                                        if ((cycleDetails != null ? cycleDetails.getTraqActivityLogs() : null) != null) {
                                                                            CycleActivityDetails cycleDetails2 = this.$requestData.getCycleDetails();
                                                                            Boolean boxBoolean12 = (cycleDetails2 == null || (traqActivityLogs7 = cycleDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs7.isEmpty());
                                                                            Intrinsics.checkNotNull(boxBoolean12);
                                                                            if (boxBoolean12.booleanValue()) {
                                                                                CycleActivityDetails cycleDetails3 = this.$requestData.getCycleDetails();
                                                                                traqActivityLogs = cycleDetails3 != null ? cycleDetails3.getTraqActivityLogs() : null;
                                                                                Intrinsics.checkNotNull(traqActivityLogs);
                                                                                HashMap y12 = Formator.Companion.y(traqActivityLogs);
                                                                                for (String segmentId12 : y12.keySet()) {
                                                                                    EntityWorkoutSessionSegment entityWorkoutSessionSegment12 = new EntityWorkoutSessionSegment();
                                                                                    Intrinsics.checkNotNullExpressionValue(segmentId12, "segmentId");
                                                                                    entityWorkoutSessionSegment12.setSegment_id(segmentId12);
                                                                                    entityWorkoutSessionSegment12.setSess_id(this.$workoutSession.getSession_id());
                                                                                    entityWorkoutSessionSegment12.setSegment_duration(Formator.Companion.x((List) y12.get(segmentId12), this.$workoutSession.getSteps_sampling_rate()));
                                                                                    Object obj13 = y12.get(segmentId12);
                                                                                    Intrinsics.checkNotNull(obj13);
                                                                                    entityWorkoutSessionSegment12.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj13).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                                    entityWorkoutSessionSegment12.setEnd_time(entityWorkoutSessionSegment12.getStart_time() + entityWorkoutSessionSegment12.getSegment_duration());
                                                                                    arrayList.add(entityWorkoutSessionSegment12);
                                                                                }
                                                                            }
                                                                        }
                                                                    } else {
                                                                        PostActivitySessionDataRequest postActivitySessionDataRequest13 = this.$requestData;
                                                                        if ((postActivitySessionDataRequest13 != null ? postActivitySessionDataRequest13.getMeditationDetails() : null) != null) {
                                                                            MeditationActivityDetails meditationDetails = this.$requestData.getMeditationDetails();
                                                                            if ((meditationDetails != null ? meditationDetails.getTraqActivityLogs() : null) != null) {
                                                                                MeditationActivityDetails meditationDetails2 = this.$requestData.getMeditationDetails();
                                                                                Boolean boxBoolean13 = (meditationDetails2 == null || (traqActivityLogs6 = meditationDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs6.isEmpty());
                                                                                Intrinsics.checkNotNull(boxBoolean13);
                                                                                if (boxBoolean13.booleanValue()) {
                                                                                    MeditationActivityDetails meditationDetails3 = this.$requestData.getMeditationDetails();
                                                                                    traqActivityLogs = meditationDetails3 != null ? meditationDetails3.getTraqActivityLogs() : null;
                                                                                    Intrinsics.checkNotNull(traqActivityLogs);
                                                                                    HashMap y13 = Formator.Companion.y(traqActivityLogs);
                                                                                    for (String segmentId13 : y13.keySet()) {
                                                                                        EntityWorkoutSessionSegment entityWorkoutSessionSegment13 = new EntityWorkoutSessionSegment();
                                                                                        Intrinsics.checkNotNullExpressionValue(segmentId13, "segmentId");
                                                                                        entityWorkoutSessionSegment13.setSegment_id(segmentId13);
                                                                                        entityWorkoutSessionSegment13.setSess_id(this.$workoutSession.getSession_id());
                                                                                        entityWorkoutSessionSegment13.setSegment_duration(Formator.Companion.x((List) y13.get(segmentId13), this.$workoutSession.getSteps_sampling_rate()));
                                                                                        Object obj14 = y13.get(segmentId13);
                                                                                        Intrinsics.checkNotNull(obj14);
                                                                                        entityWorkoutSessionSegment13.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj14).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                                        entityWorkoutSessionSegment13.setEnd_time(entityWorkoutSessionSegment13.getStart_time() + entityWorkoutSessionSegment13.getSegment_duration());
                                                                                        arrayList.add(entityWorkoutSessionSegment13);
                                                                                    }
                                                                                }
                                                                            }
                                                                        } else {
                                                                            PostActivitySessionDataRequest postActivitySessionDataRequest14 = this.$requestData;
                                                                            if ((postActivitySessionDataRequest14 != null ? postActivitySessionDataRequest14.getSkippingActivityDetails() : null) != null) {
                                                                                SkippingActivityDetails skippingActivityDetails = this.$requestData.getSkippingActivityDetails();
                                                                                if ((skippingActivityDetails != null ? skippingActivityDetails.getTraqActivityLogs() : null) != null) {
                                                                                    SkippingActivityDetails skippingActivityDetails2 = this.$requestData.getSkippingActivityDetails();
                                                                                    Boolean boxBoolean14 = (skippingActivityDetails2 == null || (traqActivityLogs5 = skippingActivityDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs5.isEmpty());
                                                                                    Intrinsics.checkNotNull(boxBoolean14);
                                                                                    if (boxBoolean14.booleanValue()) {
                                                                                        SkippingActivityDetails skippingActivityDetails3 = this.$requestData.getSkippingActivityDetails();
                                                                                        traqActivityLogs = skippingActivityDetails3 != null ? skippingActivityDetails3.getTraqActivityLogs() : null;
                                                                                        Intrinsics.checkNotNull(traqActivityLogs);
                                                                                        HashMap y14 = Formator.Companion.y(traqActivityLogs);
                                                                                        for (String segmentId14 : y14.keySet()) {
                                                                                            EntityWorkoutSessionSegment entityWorkoutSessionSegment14 = new EntityWorkoutSessionSegment();
                                                                                            Intrinsics.checkNotNullExpressionValue(segmentId14, "segmentId");
                                                                                            entityWorkoutSessionSegment14.setSegment_id(segmentId14);
                                                                                            entityWorkoutSessionSegment14.setSess_id(this.$workoutSession.getSession_id());
                                                                                            entityWorkoutSessionSegment14.setSegment_duration(Formator.Companion.x((List) y14.get(segmentId14), this.$workoutSession.getSteps_sampling_rate()));
                                                                                            Object obj15 = y14.get(segmentId14);
                                                                                            Intrinsics.checkNotNull(obj15);
                                                                                            entityWorkoutSessionSegment14.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj15).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                                            entityWorkoutSessionSegment14.setEnd_time(entityWorkoutSessionSegment14.getStart_time() + entityWorkoutSessionSegment14.getSegment_duration());
                                                                                            arrayList.add(entityWorkoutSessionSegment14);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                PostActivitySessionDataRequest postActivitySessionDataRequest15 = this.$requestData;
                                                                                if ((postActivitySessionDataRequest15 != null ? postActivitySessionDataRequest15.getEllipticalDetails() : null) != null) {
                                                                                    EllipticalActivityDetails ellipticalDetails = this.$requestData.getEllipticalDetails();
                                                                                    if ((ellipticalDetails != null ? ellipticalDetails.getTraqActivityLogs() : null) != null) {
                                                                                        EllipticalActivityDetails ellipticalDetails2 = this.$requestData.getEllipticalDetails();
                                                                                        Boolean boxBoolean15 = (ellipticalDetails2 == null || (traqActivityLogs4 = ellipticalDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs4.isEmpty());
                                                                                        Intrinsics.checkNotNull(boxBoolean15);
                                                                                        if (boxBoolean15.booleanValue()) {
                                                                                            EllipticalActivityDetails ellipticalDetails3 = this.$requestData.getEllipticalDetails();
                                                                                            traqActivityLogs = ellipticalDetails3 != null ? ellipticalDetails3.getTraqActivityLogs() : null;
                                                                                            Intrinsics.checkNotNull(traqActivityLogs);
                                                                                            HashMap y15 = Formator.Companion.y(traqActivityLogs);
                                                                                            for (String segmentId15 : y15.keySet()) {
                                                                                                EntityWorkoutSessionSegment entityWorkoutSessionSegment15 = new EntityWorkoutSessionSegment();
                                                                                                Intrinsics.checkNotNullExpressionValue(segmentId15, "segmentId");
                                                                                                entityWorkoutSessionSegment15.setSegment_id(segmentId15);
                                                                                                entityWorkoutSessionSegment15.setSess_id(this.$workoutSession.getSession_id());
                                                                                                entityWorkoutSessionSegment15.setSegment_duration(Formator.Companion.x((List) y15.get(segmentId15), this.$workoutSession.getSteps_sampling_rate()));
                                                                                                Object obj16 = y15.get(segmentId15);
                                                                                                Intrinsics.checkNotNull(obj16);
                                                                                                entityWorkoutSessionSegment15.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj16).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                                                entityWorkoutSessionSegment15.setEnd_time(entityWorkoutSessionSegment15.getStart_time() + entityWorkoutSessionSegment15.getSegment_duration());
                                                                                                arrayList.add(entityWorkoutSessionSegment15);
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    PostActivitySessionDataRequest postActivitySessionDataRequest16 = this.$requestData;
                                                                                    if ((postActivitySessionDataRequest16 != null ? postActivitySessionDataRequest16.getRowingDetails() : null) != null) {
                                                                                        RowingMachineActivityDetails rowingDetails = this.$requestData.getRowingDetails();
                                                                                        if ((rowingDetails != null ? rowingDetails.getTraqActivityLogs() : null) != null) {
                                                                                            RowingMachineActivityDetails rowingDetails2 = this.$requestData.getRowingDetails();
                                                                                            Boolean boxBoolean16 = (rowingDetails2 == null || (traqActivityLogs3 = rowingDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs3.isEmpty());
                                                                                            Intrinsics.checkNotNull(boxBoolean16);
                                                                                            if (boxBoolean16.booleanValue()) {
                                                                                                RowingMachineActivityDetails rowingDetails3 = this.$requestData.getRowingDetails();
                                                                                                traqActivityLogs = rowingDetails3 != null ? rowingDetails3.getTraqActivityLogs() : null;
                                                                                                Intrinsics.checkNotNull(traqActivityLogs);
                                                                                                HashMap y16 = Formator.Companion.y(traqActivityLogs);
                                                                                                for (String segmentId16 : y16.keySet()) {
                                                                                                    EntityWorkoutSessionSegment entityWorkoutSessionSegment16 = new EntityWorkoutSessionSegment();
                                                                                                    Intrinsics.checkNotNullExpressionValue(segmentId16, "segmentId");
                                                                                                    entityWorkoutSessionSegment16.setSegment_id(segmentId16);
                                                                                                    entityWorkoutSessionSegment16.setSess_id(this.$workoutSession.getSession_id());
                                                                                                    entityWorkoutSessionSegment16.setSegment_duration(Formator.Companion.x((List) y16.get(segmentId16), this.$workoutSession.getSteps_sampling_rate()));
                                                                                                    Object obj17 = y16.get(segmentId16);
                                                                                                    Intrinsics.checkNotNull(obj17);
                                                                                                    entityWorkoutSessionSegment16.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj17).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                                                    entityWorkoutSessionSegment16.setEnd_time(entityWorkoutSessionSegment16.getStart_time() + entityWorkoutSessionSegment16.getSegment_duration());
                                                                                                    arrayList.add(entityWorkoutSessionSegment16);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        PostActivitySessionDataRequest postActivitySessionDataRequest17 = this.$requestData;
                                                                                        if ((postActivitySessionDataRequest17 != null ? postActivitySessionDataRequest17.getActivityData() : null) != null) {
                                                                                            GenericActivitySessionData activityData = this.$requestData.getActivityData();
                                                                                            if ((activityData != null ? activityData.getTraqActivityLogs() : null) != null) {
                                                                                                GenericActivitySessionData activityData2 = this.$requestData.getActivityData();
                                                                                                Boolean boxBoolean17 = (activityData2 == null || (traqActivityLogs2 = activityData2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs2.isEmpty());
                                                                                                Intrinsics.checkNotNull(boxBoolean17);
                                                                                                if (boxBoolean17.booleanValue()) {
                                                                                                    GenericActivitySessionData activityData3 = this.$requestData.getActivityData();
                                                                                                    traqActivityLogs = activityData3 != null ? activityData3.getTraqActivityLogs() : null;
                                                                                                    Intrinsics.checkNotNull(traqActivityLogs);
                                                                                                    HashMap y17 = Formator.Companion.y(traqActivityLogs);
                                                                                                    for (String segmentId17 : y17.keySet()) {
                                                                                                        EntityWorkoutSessionSegment entityWorkoutSessionSegment17 = new EntityWorkoutSessionSegment();
                                                                                                        Intrinsics.checkNotNullExpressionValue(segmentId17, "segmentId");
                                                                                                        entityWorkoutSessionSegment17.setSegment_id(segmentId17);
                                                                                                        entityWorkoutSessionSegment17.setSess_id(this.$workoutSession.getSession_id());
                                                                                                        entityWorkoutSessionSegment17.setSegment_duration(Formator.Companion.x((List) y17.get(segmentId17), this.$workoutSession.getSteps_sampling_rate()));
                                                                                                        Object obj18 = y17.get(segmentId17);
                                                                                                        Intrinsics.checkNotNull(obj18);
                                                                                                        entityWorkoutSessionSegment17.setStart_time(AppUtils.parseDateUTC(((TraqActivityLogs) ((List) obj18).get(0)).getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                                                                        entityWorkoutSessionSegment17.setEnd_time(entityWorkoutSessionSegment17.getStart_time() + entityWorkoutSessionSegment17.getSegment_duration());
                                                                                                        arrayList.add(entityWorkoutSessionSegment17);
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2678}, m = "prepareRowingMachineDetailsObject", n = {"rowingMachineActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class e1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public e1(Continuation<? super e1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.S(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getBasketBallSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<BasketBallSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public f(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super f> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new f(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<BasketBallSample>> continuation) {
                return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getBasketballDetails() : null) != null) {
                            BasketBallActivityDetails basketballDetails = this.$requestData.getBasketballDetails();
                            if ((basketballDetails != null ? basketballDetails.getTraqActivityLogs() : null) != null) {
                                BasketBallActivityDetails basketballDetails2 = this.$requestData.getBasketballDetails();
                                Boolean boxBoolean = (basketballDetails2 == null || (traqActivityLogs = basketballDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    BasketBallActivityDetails basketballDetails3 = this.$requestData.getBasketballDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = basketballDetails3 != null ? basketballDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        BasketBallSample basketBallSample = new BasketBallSample();
                                                        basketBallSample.setSess_id(this.$workoutSession.getSession_id());
                                                        basketBallSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        basketBallSample.setStepCount(num.intValue());
                                                        basketBallSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(basketBallSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getSkippingDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1573, 1583}, m = "invokeSuspend", n = {"skippingActivityDetails", "skippingActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class f0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SkippingActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public f0(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super f0> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new f0(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super SkippingActivityDetails> continuation) {
                return ((f0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0186  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 397
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.f0.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {1870}, m = "prepareRunWalkDetailsObject", n = {"runWalkActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class f1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public f1(Continuation<? super f1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.T(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getClimbingDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {795, 805}, m = "invokeSuspend", n = {"climbingActivityDetails", "climbingActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ClimbingActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public g(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super g> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new g(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ClimbingActivityDetails> continuation) {
                return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:51:0x01eb  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r23) {
                /*
                    Method dump skipped, instructions count: 498
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.g.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getSkippingSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class g0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<SkippingSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public g0(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super g0> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new g0(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<SkippingSample>> continuation) {
                return ((g0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getSkippingActivityDetails() : null) != null) {
                            SkippingActivityDetails skippingActivityDetails = this.$requestData.getSkippingActivityDetails();
                            if ((skippingActivityDetails != null ? skippingActivityDetails.getTraqActivityLogs() : null) != null) {
                                SkippingActivityDetails skippingActivityDetails2 = this.$requestData.getSkippingActivityDetails();
                                Boolean boxBoolean = (skippingActivityDetails2 == null || (traqActivityLogs = skippingActivityDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    SkippingActivityDetails skippingActivityDetails3 = this.$requestData.getSkippingActivityDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = skippingActivityDetails3 != null ? skippingActivityDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        SkippingSample skippingSample = new SkippingSample();
                                                        skippingSample.setSess_id(this.$workoutSession.getSession_id());
                                                        skippingSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        skippingSample.setStepCount(num.intValue());
                                                        skippingSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(skippingSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2582}, m = "prepareSkippingDetailsObject", n = {"skippingActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class g1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public g1(Continuation<? super g1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.U(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getClimbingSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<ClimbingSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public h(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super h> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new h(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<ClimbingSample>> continuation) {
                return ((h) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                int i;
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getClimbingActivityDetails() : null) != null) {
                            ClimbingActivityDetails climbingActivityDetails = this.$requestData.getClimbingActivityDetails();
                            if ((climbingActivityDetails != null ? climbingActivityDetails.getTraqActivityLogs() : null) != null) {
                                ClimbingActivityDetails climbingActivityDetails2 = this.$requestData.getClimbingActivityDetails();
                                Boolean boxBoolean = (climbingActivityDetails2 == null || (traqActivityLogs = climbingActivityDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    ClimbingActivityDetails climbingActivityDetails3 = this.$requestData.getClimbingActivityDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = climbingActivityDetails3 != null ? climbingActivityDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i2 = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i3 = 0; i3 < i2; i3++) {
                                                        ClimbingSample climbingSample = new ClimbingSample();
                                                        climbingSample.setSess_id(this.$workoutSession.getSession_id());
                                                        climbingSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i3 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i3));
                                                        }
                                                        if (traqActivityLogs3.getStepValues() != null) {
                                                            Integer num = traqActivityLogs3.getStepValues().get(i3);
                                                            Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                            i = num.intValue();
                                                        } else {
                                                            i = 0;
                                                        }
                                                        climbingSample.setStepCount(i);
                                                        climbingSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i3, sampleData));
                                                        arrayList.add(climbingSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getTennisDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1000, PointerIconCompat.TYPE_ALIAS}, m = "invokeSuspend", n = {"tennisActivityDetails", "tennisActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class h0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TennisActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public h0(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super h0> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new h0(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super TennisActivityDetails> continuation) {
                return ((h0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0179  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 384
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.h0.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2154}, m = "prepareTennisDetailsObject", n = {"tennisActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class h1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public h1(Continuation<? super h1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.V(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getCycleDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {870, 880}, m = "invokeSuspend", n = {"cycleActivityDetails", "cycleActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class i extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CycleActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public i(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super i> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new i(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super CycleActivityDetails> continuation) {
                return ((i) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:51:0x01de  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r23) {
                /*
                    Method dump skipped, instructions count: 485
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.i.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getTennisSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class i0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<TennisSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public i0(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super i0> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new i0(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<TennisSample>> continuation) {
                return ((i0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getTennisDetails() : null) != null) {
                            TennisActivityDetails tennisDetails = this.$requestData.getTennisDetails();
                            if ((tennisDetails != null ? tennisDetails.getTraqActivityLogs() : null) != null) {
                                TennisActivityDetails tennisDetails2 = this.$requestData.getTennisDetails();
                                Boolean boxBoolean = (tennisDetails2 == null || (traqActivityLogs = tennisDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    TennisActivityDetails tennisDetails3 = this.$requestData.getTennisDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = tennisDetails3 != null ? tennisDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        TennisSample tennisSample = new TennisSample();
                                                        tennisSample.setSess_id(this.$workoutSession.getSession_id());
                                                        tennisSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        tennisSample.setStepCount(num.intValue());
                                                        tennisSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(tennisSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {1966}, m = "prepareTreadmillDetailsObject", n = {"runWalkActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class i1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public i1(Continuation<? super i1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.W(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getCyclingSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class j extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<CyclingSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public j(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super j> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new j(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<CyclingSample>> continuation) {
                return ((j) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                int i;
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getCycleDetails() : null) != null) {
                            CycleActivityDetails cycleDetails = this.$requestData.getCycleDetails();
                            if ((cycleDetails != null ? cycleDetails.getTraqActivityLogs() : null) != null) {
                                CycleActivityDetails cycleDetails2 = this.$requestData.getCycleDetails();
                                Boolean boxBoolean = (cycleDetails2 == null || (traqActivityLogs = cycleDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    CycleActivityDetails cycleDetails3 = this.$requestData.getCycleDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = cycleDetails3 != null ? cycleDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i2 = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i3 = 0; i3 < i2; i3++) {
                                                        CyclingSample cyclingSample = new CyclingSample();
                                                        cyclingSample.setSess_id(this.$workoutSession.getSession_id());
                                                        cyclingSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i3 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i3));
                                                        }
                                                        if (traqActivityLogs3.getStepValues() != null) {
                                                            Integer num = traqActivityLogs3.getStepValues().get(i3);
                                                            Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                            i = num.intValue();
                                                        } else {
                                                            i = 0;
                                                        }
                                                        cyclingSample.setStepCount(i);
                                                        cyclingSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i3, sampleData));
                                                        arrayList.add(cyclingSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getTreadmillDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {699, 709}, m = "invokeSuspend", n = {"treadmillActivityDetails", "treadmillActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class j0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TreadmillActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public j0(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super j0> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new j0(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super TreadmillActivityDetails> continuation) {
                return ((j0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x0249  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r25) {
                /*
                    Method dump skipped, instructions count: 592
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.j0.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2393}, m = "prepareWorkoutDetailsObject", n = {"workoutActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class j1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public j1(Continuation<? super j1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.X(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getDanceDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1193, 1203}, m = "invokeSuspend", n = {"danceActivityDetails", "danceActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class k extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DanceActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public k(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super k> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new k(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super DanceActivityDetails> continuation) {
                return ((k) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0186  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 397
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.k.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getTreadmillSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class k0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<TreadmillSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public k0(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super k0> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new k0(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<TreadmillSample>> continuation) {
                return ((k0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                int i;
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getTreadmillActivityDetails() : null) != null) {
                            TreadmillActivityDetails treadmillActivityDetails = this.$requestData.getTreadmillActivityDetails();
                            if ((treadmillActivityDetails != null ? treadmillActivityDetails.getTraqActivityLogs() : null) != null) {
                                TreadmillActivityDetails treadmillActivityDetails2 = this.$requestData.getTreadmillActivityDetails();
                                Boolean boxBoolean = (treadmillActivityDetails2 == null || (traqActivityLogs = treadmillActivityDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    TreadmillActivityDetails treadmillActivityDetails3 = this.$requestData.getTreadmillActivityDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = treadmillActivityDetails3 != null ? treadmillActivityDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i2 = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i3 = 0; i3 < i2; i3++) {
                                                        TreadmillSample treadmillSample = new TreadmillSample();
                                                        treadmillSample.setSess_id(this.$workoutSession.getSession_id());
                                                        treadmillSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i3 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i3));
                                                        }
                                                        if (traqActivityLogs3.getStepValues() != null) {
                                                            Integer num = traqActivityLogs3.getStepValues().get(i3);
                                                            Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                            i = num.intValue();
                                                        } else {
                                                            i = 0;
                                                        }
                                                        treadmillSample.setStepCount(i);
                                                        treadmillSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i3, sampleData));
                                                        arrayList.add(treadmillSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2250}, m = "prepareYogaDetailsObject", n = {"yogaActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class k1 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public k1(Continuation<? super k1> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.Y(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getDanceSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class l extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<DanceSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public l(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super l> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new l(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<DanceSample>> continuation) {
                return ((l) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getDanceDetails() : null) != null) {
                            DanceActivityDetails danceDetails = this.$requestData.getDanceDetails();
                            if ((danceDetails != null ? danceDetails.getTraqActivityLogs() : null) != null) {
                                DanceActivityDetails danceDetails2 = this.$requestData.getDanceDetails();
                                Boolean boxBoolean = (danceDetails2 == null || (traqActivityLogs = danceDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    DanceActivityDetails danceDetails3 = this.$requestData.getDanceDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = danceDetails3 != null ? danceDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        DanceSample danceSample = new DanceSample();
                                                        danceSample.setSess_id(this.$workoutSession.getSession_id());
                                                        danceSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        danceSample.setStepCount(num.intValue());
                                                        danceSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(danceSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getWalkDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {507, 517}, m = "invokeSuspend", n = {"runWalkActivityDetails", "runWalkActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class l0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RunWalkActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public l0(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super l0> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new l0(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super RunWalkActivityDetails> continuation) {
                return ((l0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:20:0x0081  */
            /* JADX WARN: Removed duplicated region for block: B:62:0x0248  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00b1 -> B:23:0x00b4). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r25) {
                /*
                    Method dump skipped, instructions count: 591
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.l0.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getEllipticalDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1638, 1648}, m = "invokeSuspend", n = {"ellipticalActivityDetails", "ellipticalActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class m extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EllipticalActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public m(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super m> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new m(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EllipticalActivityDetails> continuation) {
                return ((m) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0186  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 397
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.m.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getWalkSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class m0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<WalkSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public m0(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super m0> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new m0(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<WalkSample>> continuation) {
                return ((m0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getWalkDetails() : null) != null) {
                            RunWalkActivityDetails walkDetails = this.$requestData.getWalkDetails();
                            if ((walkDetails != null ? walkDetails.getTraqActivityLogs() : null) != null) {
                                RunWalkActivityDetails walkDetails2 = this.$requestData.getWalkDetails();
                                Boolean boxBoolean = (walkDetails2 == null || (traqActivityLogs = walkDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    RunWalkActivityDetails walkDetails3 = this.$requestData.getWalkDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = walkDetails3 != null ? walkDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                int i = Formator.Companion.i(traqActivityLogs3);
                                                for (int i2 = 0; i2 < i; i2++) {
                                                    WalkSample walkSample = new WalkSample();
                                                    walkSample.setSess_id(this.$workoutSession.getSession_id());
                                                    walkSample.setSeg_id(str);
                                                    SampleData sampleData = new SampleData();
                                                    if (i2 == 0) {
                                                        sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                    } else {
                                                        sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                    }
                                                    Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                    Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                    walkSample.setStepCount(num.intValue());
                                                    walkSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                    arrayList.add(walkSample);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getEllipticalSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class n extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<EllipticalSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public n(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super n> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new n(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<EllipticalSample>> continuation) {
                return ((n) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getEllipticalDetails() : null) != null) {
                            EllipticalActivityDetails ellipticalDetails = this.$requestData.getEllipticalDetails();
                            if ((ellipticalDetails != null ? ellipticalDetails.getTraqActivityLogs() : null) != null) {
                                EllipticalActivityDetails ellipticalDetails2 = this.$requestData.getEllipticalDetails();
                                Boolean boxBoolean = (ellipticalDetails2 == null || (traqActivityLogs = ellipticalDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    EllipticalActivityDetails ellipticalDetails3 = this.$requestData.getEllipticalDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = ellipticalDetails3 != null ? ellipticalDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        EllipticalSample ellipticalSample = new EllipticalSample();
                                                        ellipticalSample.setSess_id(this.$workoutSession.getSession_id());
                                                        ellipticalSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        if (traqActivityLogs3.getStepValues() != null) {
                                                            Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                            Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                            ellipticalSample.setStepCount(num.intValue());
                                                        }
                                                        ellipticalSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(ellipticalSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getWorkoutDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1383, 1393}, m = "invokeSuspend", n = {"workoutActivityDetails", "workoutActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class n0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super WorkoutActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public n0(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super n0> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new n0(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super WorkoutActivityDetails> continuation) {
                return ((n0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0186  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 397
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.n0.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getFootBallDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1447, 1457}, m = "invokeSuspend", n = {"footBallActivityDetails", "footBallActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class o extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FootBallActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public o(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super o> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new o(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super FootBallActivityDetails> continuation) {
                return ((o) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0186  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 397
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.o.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getWorkoutSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class o0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<WorkoutSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public o0(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super o0> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new o0(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<WorkoutSample>> continuation) {
                return ((o0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getWorkoutDetails() : null) != null) {
                            WorkoutActivityDetails workoutDetails = this.$requestData.getWorkoutDetails();
                            if ((workoutDetails != null ? workoutDetails.getTraqActivityLogs() : null) != null) {
                                WorkoutActivityDetails workoutDetails2 = this.$requestData.getWorkoutDetails();
                                Boolean boxBoolean = (workoutDetails2 == null || (traqActivityLogs = workoutDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    WorkoutActivityDetails workoutDetails3 = this.$requestData.getWorkoutDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = workoutDetails3 != null ? workoutDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        WorkoutSample workoutSample = new WorkoutSample();
                                                        workoutSample.setSess_id(this.$workoutSession.getSession_id());
                                                        workoutSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        workoutSample.setStepCount(num.intValue());
                                                        workoutSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(workoutSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getFootBallSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class p extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<FootballSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public p(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super p> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new p(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<FootballSample>> continuation) {
                return ((p) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getFootballDetails() : null) != null) {
                            FootBallActivityDetails footballDetails = this.$requestData.getFootballDetails();
                            if ((footballDetails != null ? footballDetails.getTraqActivityLogs() : null) != null) {
                                FootBallActivityDetails footballDetails2 = this.$requestData.getFootballDetails();
                                Boolean boxBoolean = (footballDetails2 == null || (traqActivityLogs = footballDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    FootBallActivityDetails footballDetails3 = this.$requestData.getFootballDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = footballDetails3 != null ? footballDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        FootballSample footballSample = new FootballSample();
                                                        footballSample.setSess_id(this.$workoutSession.getSession_id());
                                                        footballSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        footballSample.setStepCount(num.intValue());
                                                        footballSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(footballSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getWorkoutSessionEntity$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class p0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityWorkoutSession>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ String $macAddress;
            public final /* synthetic */ PostActivitySessionDataRequest $postActivitySession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public p0(Context context, PostActivitySessionDataRequest postActivitySessionDataRequest, String str, Continuation<? super p0> continuation) {
                super(2, continuation);
                this.$context = context;
                this.$postActivitySession = postActivitySessionDataRequest;
                this.$macAddress = str;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new p0(this.$context, this.$postActivitySession, this.$macAddress, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityWorkoutSession> continuation) {
                return ((p0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:135:0x03ac  */
            /* JADX WARN: Removed duplicated region for block: B:138:0x03d7  */
            /* JADX WARN: Removed duplicated region for block: B:143:0x0476  */
            /* JADX WARN: Removed duplicated region for block: B:178:0x0542  */
            /* JADX WARN: Removed duplicated region for block: B:181:0x0552  */
            /* JADX WARN: Removed duplicated region for block: B:184:0x0562  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r20) {
                /*
                    Method dump skipped, instructions count: 1408
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.p0.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getFreeExerciseDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1319, 1329}, m = "invokeSuspend", n = {"freeExerciseActivityDetails", "freeExerciseActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class q extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FreeExerciseActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public q(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super q> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new q(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super FreeExerciseActivityDetails> continuation) {
                return ((q) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0179  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 384
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.q.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getYogaDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1060, 1070}, m = "invokeSuspend", n = {"yogaActivityDetails", "yogaActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class q0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super YogaActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public q0(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super q0> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new q0(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super YogaActivityDetails> continuation) {
                return ((q0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x0179  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 384
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.q0.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getFreeExerciseSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class r extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<FreeExerciseSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public r(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super r> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new r(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<FreeExerciseSample>> continuation) {
                return ((r) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                String str;
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null && this.$workoutSession != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getFreeExerciseActivityDetails() : null) != null) {
                            FreeExerciseActivityDetails freeExerciseActivityDetails = this.$requestData.getFreeExerciseActivityDetails();
                            if ((freeExerciseActivityDetails != null ? freeExerciseActivityDetails.getTraqActivityLogs() : null) != null) {
                                FreeExerciseActivityDetails freeExerciseActivityDetails2 = this.$requestData.getFreeExerciseActivityDetails();
                                boolean z = true;
                                Boolean boxBoolean = (freeExerciseActivityDetails2 == null || (traqActivityLogs = freeExerciseActivityDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    FreeExerciseActivityDetails freeExerciseActivityDetails3 = this.$requestData.getFreeExerciseActivityDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = freeExerciseActivityDetails3 != null ? freeExerciseActivityDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str2 : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str2);
                                        if (list != null && (list.isEmpty() ^ z)) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    int i2 = 0;
                                                    while (i2 < i) {
                                                        FreeExerciseSample freeExerciseSample = new FreeExerciseSample();
                                                        freeExerciseSample.setSess_id(this.$workoutSession.getSession_id());
                                                        freeExerciseSample.setSeg_id(str2);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                            str = str2;
                                                        } else {
                                                            str = str2;
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        List<Integer> stepValues = traqActivityLogs3.getStepValues();
                                                        if (!(stepValues == null || stepValues.isEmpty()) && traqActivityLogs3.getStepValues().get(i2) != null) {
                                                            Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                            Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                            freeExerciseSample.setStepCount(num.intValue());
                                                        } else {
                                                            freeExerciseSample.setStepCount(0);
                                                        }
                                                        freeExerciseSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(freeExerciseSample);
                                                        i2++;
                                                        str2 = str;
                                                        z = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getYogaSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class r0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<YogaSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public r0(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super r0> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new r0(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<YogaSample>> continuation) {
                return ((r0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getYogaDetails() : null) != null) {
                            YogaActivityDetails yogaDetails = this.$requestData.getYogaDetails();
                            if ((yogaDetails != null ? yogaDetails.getTraqActivityLogs() : null) != null) {
                                YogaActivityDetails yogaDetails2 = this.$requestData.getYogaDetails();
                                Boolean boxBoolean = (yogaDetails2 == null || (traqActivityLogs = yogaDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    YogaActivityDetails yogaDetails3 = this.$requestData.getYogaDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = yogaDetails3 != null ? yogaDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        YogaSample yogaSample = new YogaSample();
                                                        yogaSample.setSess_id(this.$workoutSession.getSession_id());
                                                        yogaSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        yogaSample.setStepCount(num.intValue());
                                                        yogaSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(yogaSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getHikingDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {1119, 1129}, m = "invokeSuspend", n = {"hikingActivityDetails", "hikingActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class s extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HikingActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public s(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super s> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new s(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super HikingActivityDetails> continuation) {
                return ((s) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:51:0x01eb  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r23) {
                /*
                    Method dump skipped, instructions count: 498
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.s.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2488}, m = "preparBasketBallDetailsObject", n = {"basketBallActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class s0 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public s0(Continuation<? super s0> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.G(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getHikingSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class t extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<HikingSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public t(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super t> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new t(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<HikingSample>> continuation) {
                return ((t) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getHikingDetails() : null) != null) {
                            HikingActivityDetails hikingDetails = this.$requestData.getHikingDetails();
                            if ((hikingDetails != null ? hikingDetails.getTraqActivityLogs() : null) != null) {
                                HikingActivityDetails hikingDetails2 = this.$requestData.getHikingDetails();
                                Boolean boxBoolean = (hikingDetails2 == null || (traqActivityLogs = hikingDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    HikingActivityDetails hikingDetails3 = this.$requestData.getHikingDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = hikingDetails3 != null ? hikingDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i2 = 0; i2 < i; i2++) {
                                                        HikingSample hikingSample = new HikingSample();
                                                        hikingSample.setSess_id(this.$workoutSession.getSession_id());
                                                        hikingSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i2 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                        }
                                                        Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                        Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                        hikingSample.setStepCount(num.intValue());
                                                        hikingSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                        arrayList.add(hikingSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2012}, m = "preparClimbingDetailsObject", n = {"hikingActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class t0 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public t0(Continuation<? super t0> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.H(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getMeditationDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {942, 952}, m = "invokeSuspend", n = {"meditationActivityDetails", "meditationActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class u extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MeditationActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public u(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super u> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new u(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super MeditationActivityDetails> continuation) {
                return ((u) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00c7  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d5  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
            /* JADX WARN: Removed duplicated region for block: B:45:0x014b  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00b2 -> B:21:0x00b5). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r18) {
                /*
                    Method dump skipped, instructions count: 338
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.u.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2535}, m = "preparHikingDetailsObject", n = {"hikingActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class u0 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public u0(Continuation<? super u0> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.I(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getMeditationSampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class v extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<MeditationSample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public v(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super v> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new v(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<MeditationSample>> continuation) {
                return ((v) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                int i;
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getMeditationDetails() : null) != null) {
                            MeditationActivityDetails meditationDetails = this.$requestData.getMeditationDetails();
                            if ((meditationDetails != null ? meditationDetails.getTraqActivityLogs() : null) != null) {
                                MeditationActivityDetails meditationDetails2 = this.$requestData.getMeditationDetails();
                                Boolean boxBoolean = (meditationDetails2 == null || (traqActivityLogs = meditationDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    MeditationActivityDetails meditationDetails3 = this.$requestData.getMeditationDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = meditationDetails3 != null ? meditationDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                if (traqActivityLogs3 != null) {
                                                    int i2 = Formator.Companion.i(traqActivityLogs3);
                                                    for (int i3 = 0; i3 < i2; i3++) {
                                                        MeditationSample meditationSample = new MeditationSample();
                                                        meditationSample.setSess_id(this.$workoutSession.getSession_id());
                                                        meditationSample.setSeg_id(str);
                                                        SampleData sampleData = new SampleData();
                                                        if (i3 == 0) {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                        } else {
                                                            sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i3));
                                                        }
                                                        if (traqActivityLogs3.getStepValues() != null) {
                                                            Integer num = traqActivityLogs3.getStepValues().get(i3);
                                                            Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                            i = num.intValue();
                                                        } else {
                                                            i = 0;
                                                        }
                                                        meditationSample.setStepCount(i);
                                                        meditationSample.setSampleData(Formator.Companion.w(traqActivityLogs3, i3, sampleData));
                                                        arrayList.add(meditationSample);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2747}, m = "prepareActivityDetailsObject", n = {"activitiesSampleDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class v0 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public v0(Continuation<? super v0> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.J(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getPhysicalActivityDetails$2", f = "Formator.kt", i = {0, 1, 1, 1, 1}, l = {com.veryfit.multi.nativeprotocol.b.D1, 421}, m = "invokeSuspend", n = {"physicalActivityDetails", "physicalActivityDetails", "logs", "segment", "boolAdd"}, s = {"L$0", "L$0", "L$1", "L$3", "I$0"})
        /* loaded from: classes2.dex */
        public static final class w extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PhysicalActivityDetails>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public final /* synthetic */ List<Sample> $samples;
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public w(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super w> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$samples = list;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new w(this.$entityWorkoutSession, this.$samples, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super PhysicalActivityDetails> continuation) {
                return ((w) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:20:0x0081  */
            /* JADX WARN: Removed duplicated region for block: B:62:0x0248  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00b1 -> B:23:0x00b4). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r25) {
                /*
                    Method dump skipped, instructions count: 591
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.w.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {Error.DOWNLOAD_DELTAID_NOT_EXIST}, m = "prepareBadmintonDetailsObject", n = {"badmintonActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class w0 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public w0(Continuation<? super w0> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.K(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getPhysicalActivitySampleList$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class x extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<PhysicalActivitySample>>, Object> {
            public final /* synthetic */ PostActivitySessionDataRequest $requestData;
            public final /* synthetic */ EntityWorkoutSession $workoutSession;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public x(PostActivitySessionDataRequest postActivitySessionDataRequest, EntityWorkoutSession entityWorkoutSession, Continuation<? super x> continuation) {
                super(2, continuation);
                this.$requestData = postActivitySessionDataRequest;
                this.$workoutSession = entityWorkoutSession;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new x(this.$requestData, this.$workoutSession, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super ArrayList<PhysicalActivitySample>> continuation) {
                return ((x) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                List<TraqActivityLogs> traqActivityLogs;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ArrayList arrayList = new ArrayList();
                    PostActivitySessionDataRequest postActivitySessionDataRequest = this.$requestData;
                    if (postActivitySessionDataRequest != null) {
                        if ((postActivitySessionDataRequest != null ? postActivitySessionDataRequest.getPhysicalActivityDetails() : null) != null) {
                            PhysicalActivityDetails physicalActivityDetails = this.$requestData.getPhysicalActivityDetails();
                            if ((physicalActivityDetails != null ? physicalActivityDetails.getTraqActivityLogs() : null) != null) {
                                PhysicalActivityDetails physicalActivityDetails2 = this.$requestData.getPhysicalActivityDetails();
                                Boolean boxBoolean = (physicalActivityDetails2 == null || (traqActivityLogs = physicalActivityDetails2.getTraqActivityLogs()) == null) ? null : Boxing.boxBoolean(!traqActivityLogs.isEmpty());
                                Intrinsics.checkNotNull(boxBoolean);
                                if (boxBoolean.booleanValue()) {
                                    Companion companion = Formator.Companion;
                                    PhysicalActivityDetails physicalActivityDetails3 = this.$requestData.getPhysicalActivityDetails();
                                    List<TraqActivityLogs> traqActivityLogs2 = physicalActivityDetails3 != null ? physicalActivityDetails3.getTraqActivityLogs() : null;
                                    Intrinsics.checkNotNull(traqActivityLogs2);
                                    HashMap y = companion.y(traqActivityLogs2);
                                    Set keySet = y.keySet();
                                    Intrinsics.checkNotNullExpressionValue(keySet, "segmentsMap.keys");
                                    List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) keySet);
                                    Collections.sort(mutableList);
                                    for (String str : mutableList) {
                                        List<TraqActivityLogs> list = (List) y.get(str);
                                        if (list != null && (!list.isEmpty())) {
                                            for (TraqActivityLogs traqActivityLogs3 : list) {
                                                int i = Formator.Companion.i(traqActivityLogs3);
                                                for (int i2 = 0; i2 < i; i2++) {
                                                    PhysicalActivitySample physicalActivitySample = new PhysicalActivitySample();
                                                    physicalActivitySample.setSess_id(this.$workoutSession.getSession_id());
                                                    physicalActivitySample.setSeg_id(str);
                                                    SampleData sampleData = new SampleData();
                                                    if (i2 == 0) {
                                                        sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                                                    } else {
                                                        sampleData.setTimeStamp(AppUtils.parseDateUTC(traqActivityLogs3.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() + (this.$workoutSession.getSteps_sampling_rate() * 1000 * i2));
                                                    }
                                                    Integer num = traqActivityLogs3.getStepValues().get(i2);
                                                    Intrinsics.checkNotNullExpressionValue(num, "log.stepValues[i]");
                                                    physicalActivitySample.setStepCount(num.intValue());
                                                    physicalActivitySample.setSampleData(Formator.Companion.w(traqActivityLogs3, i2, sampleData));
                                                    arrayList.add(physicalActivitySample);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return arrayList;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {DfuAdapter.STATE_BACKCONNECT_VALIDATE_FAILED}, m = "prepareCycleDetailsObject", n = {"cycleActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class x0 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public x0(Continuation<? super x0> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.L(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getPostActivitySessionDataRequest$2", f = "Formator.kt", i = {1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 8, 8, 9, 10, 10, 11, 12, 12, 13, 14, 14, 15, 16, 16, 17, 18, 18, 19, 20, 20, 21, 22, 22, 23, 24, 24, 25, 26, 26, 27, 28, 28, 29, 30, 30, 31, 32, 32, 33, 34, 34, 35, 36, 36, 37, 38, 38, 39, 40, 40}, l = {124, Command.CMD_GET_DEVICE_CONFIG_INFO, 219, 225, 227, 232, 234, 239, Command.CMD_PHONE_NUMBER_PLAY_MODE, 247, 249, 255, 257, DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS, DfuException.ERROR_CONNECT_ERROR, 269, DfuException.ERROR_READ_APP_INFO_ERROR, DfuException.ERROR_REQUEST_MTU_NO_CALLBACK, 278, DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE, 286, TGEventListener.SLEEP_STOP, TGEventListener.WATCH_FACE_INSTALLED, ResultCode.INVALID_TXRX, 300, 305, 306, 312, 314, com.veryfit.multi.nativeprotocol.b.e1, com.veryfit.multi.nativeprotocol.b.f1, com.veryfit.multi.nativeprotocol.b.j1, 328, com.veryfit.multi.nativeprotocol.b.n1, com.veryfit.multi.nativeprotocol.b.p1, 341, 343, 348, 350, AccessLock.EVT_LOCK_ACQUIRED, 357}, m = "invokeSuspend", n = {"postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples", "postActivitySessionDataRequest", "postActivitySessionDataRequest", "samples"}, s = {"L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1", "L$0", "L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class y extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PostActivitySessionDataRequest>, Object> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ EntityWorkoutSession $entityWorkoutSession;
            public Object L$0;
            public Object L$1;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public y(EntityWorkoutSession entityWorkoutSession, Context context, Continuation<? super y> continuation) {
                super(2, continuation);
                this.$entityWorkoutSession = entityWorkoutSession;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new y(this.$entityWorkoutSession, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super PostActivitySessionDataRequest> continuation) {
                return ((y) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:172:0x032e, code lost:
                if (r10.isBESDevice(r13.$context) == false) goto L228;
             */
            /* JADX WARN: Removed duplicated region for block: B:138:0x026e A[Catch: Exception -> 0x01d5, TryCatch #0 {Exception -> 0x01d5, blocks: (B:7:0x0021, B:421:0x0ae8, B:10:0x002a, B:417:0x0ace, B:13:0x0037, B:410:0x0a8b, B:16:0x0040, B:406:0x0a71, B:19:0x004d, B:399:0x0a2d, B:22:0x0056, B:395:0x0a13, B:25:0x0063, B:388:0x09d2, B:28:0x006c, B:384:0x09b8, B:31:0x0079, B:377:0x0977, B:34:0x0082, B:373:0x095d, B:37:0x008f, B:366:0x091c, B:40:0x0098, B:362:0x0902, B:43:0x00a5, B:355:0x08c1, B:46:0x00ae, B:351:0x08a7, B:49:0x00bb, B:344:0x0866, B:52:0x00c4, B:340:0x084c, B:55:0x00d1, B:333:0x080b, B:58:0x00da, B:329:0x07f1, B:61:0x00e7, B:322:0x07b0, B:64:0x00f0, B:318:0x0796, B:67:0x00fd, B:311:0x0755, B:70:0x0106, B:307:0x073b, B:73:0x0113, B:300:0x06fa, B:76:0x011c, B:296:0x06e0, B:79:0x0129, B:289:0x069f, B:82:0x0132, B:285:0x0685, B:85:0x013f, B:278:0x0644, B:88:0x0148, B:274:0x062a, B:91:0x0155, B:267:0x05e9, B:94:0x015e, B:263:0x05cf, B:97:0x016b, B:256:0x058e, B:100:0x0174, B:252:0x0574, B:103:0x0181, B:245:0x0533, B:106:0x018a, B:241:0x0519, B:109:0x0197, B:234:0x04d8, B:112:0x01a0, B:230:0x04bf, B:115:0x01ad, B:223:0x0480, B:118:0x01b6, B:219:0x0468, B:121:0x01c3, B:212:0x0429, B:124:0x01cc, B:208:0x0411, B:125:0x01d1, B:135:0x020b, B:136:0x021b, B:138:0x026e, B:140:0x0278, B:142:0x0282, B:144:0x028c, B:146:0x0296, B:148:0x02a0, B:150:0x02aa, B:152:0x02b4, B:154:0x02be, B:156:0x02c8, B:158:0x02d2, B:160:0x02dc, B:162:0x02e6, B:163:0x02ef, B:165:0x030d, B:167:0x0318, B:169:0x0320, B:171:0x0328, B:174:0x0345, B:176:0x0368, B:182:0x0374, B:183:0x038a, B:185:0x0392, B:186:0x039b, B:188:0x03a3, B:189:0x03ac, B:191:0x03b4, B:194:0x03bd, B:196:0x03c3, B:198:0x03ca, B:200:0x03d2, B:202:0x03de, B:204:0x03f2, B:213:0x0437, B:215:0x0449, B:224:0x048e, B:226:0x04a0, B:235:0x04e6, B:237:0x04f8, B:246:0x0541, B:248:0x0553, B:257:0x059c, B:259:0x05ae, B:268:0x05f7, B:270:0x0609, B:279:0x0652, B:281:0x0664, B:290:0x06ad, B:292:0x06bf, B:301:0x0708, B:303:0x071a, B:312:0x0763, B:314:0x0775, B:323:0x07be, B:325:0x07d0, B:334:0x0819, B:336:0x082b, B:345:0x0874, B:347:0x0886, B:356:0x08cf, B:358:0x08e1, B:367:0x092a, B:369:0x093c, B:378:0x0985, B:380:0x0997, B:389:0x09e0, B:391:0x09f2, B:400:0x0a3b, B:402:0x0a50, B:411:0x0a98, B:413:0x0aad, B:197:0x03c7, B:173:0x0330, B:130:0x01db, B:132:0x01ef), top: B:425:0x000e }] */
            /* JADX WARN: Removed duplicated region for block: B:165:0x030d A[Catch: Exception -> 0x01d5, TryCatch #0 {Exception -> 0x01d5, blocks: (B:7:0x0021, B:421:0x0ae8, B:10:0x002a, B:417:0x0ace, B:13:0x0037, B:410:0x0a8b, B:16:0x0040, B:406:0x0a71, B:19:0x004d, B:399:0x0a2d, B:22:0x0056, B:395:0x0a13, B:25:0x0063, B:388:0x09d2, B:28:0x006c, B:384:0x09b8, B:31:0x0079, B:377:0x0977, B:34:0x0082, B:373:0x095d, B:37:0x008f, B:366:0x091c, B:40:0x0098, B:362:0x0902, B:43:0x00a5, B:355:0x08c1, B:46:0x00ae, B:351:0x08a7, B:49:0x00bb, B:344:0x0866, B:52:0x00c4, B:340:0x084c, B:55:0x00d1, B:333:0x080b, B:58:0x00da, B:329:0x07f1, B:61:0x00e7, B:322:0x07b0, B:64:0x00f0, B:318:0x0796, B:67:0x00fd, B:311:0x0755, B:70:0x0106, B:307:0x073b, B:73:0x0113, B:300:0x06fa, B:76:0x011c, B:296:0x06e0, B:79:0x0129, B:289:0x069f, B:82:0x0132, B:285:0x0685, B:85:0x013f, B:278:0x0644, B:88:0x0148, B:274:0x062a, B:91:0x0155, B:267:0x05e9, B:94:0x015e, B:263:0x05cf, B:97:0x016b, B:256:0x058e, B:100:0x0174, B:252:0x0574, B:103:0x0181, B:245:0x0533, B:106:0x018a, B:241:0x0519, B:109:0x0197, B:234:0x04d8, B:112:0x01a0, B:230:0x04bf, B:115:0x01ad, B:223:0x0480, B:118:0x01b6, B:219:0x0468, B:121:0x01c3, B:212:0x0429, B:124:0x01cc, B:208:0x0411, B:125:0x01d1, B:135:0x020b, B:136:0x021b, B:138:0x026e, B:140:0x0278, B:142:0x0282, B:144:0x028c, B:146:0x0296, B:148:0x02a0, B:150:0x02aa, B:152:0x02b4, B:154:0x02be, B:156:0x02c8, B:158:0x02d2, B:160:0x02dc, B:162:0x02e6, B:163:0x02ef, B:165:0x030d, B:167:0x0318, B:169:0x0320, B:171:0x0328, B:174:0x0345, B:176:0x0368, B:182:0x0374, B:183:0x038a, B:185:0x0392, B:186:0x039b, B:188:0x03a3, B:189:0x03ac, B:191:0x03b4, B:194:0x03bd, B:196:0x03c3, B:198:0x03ca, B:200:0x03d2, B:202:0x03de, B:204:0x03f2, B:213:0x0437, B:215:0x0449, B:224:0x048e, B:226:0x04a0, B:235:0x04e6, B:237:0x04f8, B:246:0x0541, B:248:0x0553, B:257:0x059c, B:259:0x05ae, B:268:0x05f7, B:270:0x0609, B:279:0x0652, B:281:0x0664, B:290:0x06ad, B:292:0x06bf, B:301:0x0708, B:303:0x071a, B:312:0x0763, B:314:0x0775, B:323:0x07be, B:325:0x07d0, B:334:0x0819, B:336:0x082b, B:345:0x0874, B:347:0x0886, B:356:0x08cf, B:358:0x08e1, B:367:0x092a, B:369:0x093c, B:378:0x0985, B:380:0x0997, B:389:0x09e0, B:391:0x09f2, B:400:0x0a3b, B:402:0x0a50, B:411:0x0a98, B:413:0x0aad, B:197:0x03c7, B:173:0x0330, B:130:0x01db, B:132:0x01ef), top: B:425:0x000e }] */
            /* JADX WARN: Removed duplicated region for block: B:182:0x0374 A[Catch: Exception -> 0x01d5, TryCatch #0 {Exception -> 0x01d5, blocks: (B:7:0x0021, B:421:0x0ae8, B:10:0x002a, B:417:0x0ace, B:13:0x0037, B:410:0x0a8b, B:16:0x0040, B:406:0x0a71, B:19:0x004d, B:399:0x0a2d, B:22:0x0056, B:395:0x0a13, B:25:0x0063, B:388:0x09d2, B:28:0x006c, B:384:0x09b8, B:31:0x0079, B:377:0x0977, B:34:0x0082, B:373:0x095d, B:37:0x008f, B:366:0x091c, B:40:0x0098, B:362:0x0902, B:43:0x00a5, B:355:0x08c1, B:46:0x00ae, B:351:0x08a7, B:49:0x00bb, B:344:0x0866, B:52:0x00c4, B:340:0x084c, B:55:0x00d1, B:333:0x080b, B:58:0x00da, B:329:0x07f1, B:61:0x00e7, B:322:0x07b0, B:64:0x00f0, B:318:0x0796, B:67:0x00fd, B:311:0x0755, B:70:0x0106, B:307:0x073b, B:73:0x0113, B:300:0x06fa, B:76:0x011c, B:296:0x06e0, B:79:0x0129, B:289:0x069f, B:82:0x0132, B:285:0x0685, B:85:0x013f, B:278:0x0644, B:88:0x0148, B:274:0x062a, B:91:0x0155, B:267:0x05e9, B:94:0x015e, B:263:0x05cf, B:97:0x016b, B:256:0x058e, B:100:0x0174, B:252:0x0574, B:103:0x0181, B:245:0x0533, B:106:0x018a, B:241:0x0519, B:109:0x0197, B:234:0x04d8, B:112:0x01a0, B:230:0x04bf, B:115:0x01ad, B:223:0x0480, B:118:0x01b6, B:219:0x0468, B:121:0x01c3, B:212:0x0429, B:124:0x01cc, B:208:0x0411, B:125:0x01d1, B:135:0x020b, B:136:0x021b, B:138:0x026e, B:140:0x0278, B:142:0x0282, B:144:0x028c, B:146:0x0296, B:148:0x02a0, B:150:0x02aa, B:152:0x02b4, B:154:0x02be, B:156:0x02c8, B:158:0x02d2, B:160:0x02dc, B:162:0x02e6, B:163:0x02ef, B:165:0x030d, B:167:0x0318, B:169:0x0320, B:171:0x0328, B:174:0x0345, B:176:0x0368, B:182:0x0374, B:183:0x038a, B:185:0x0392, B:186:0x039b, B:188:0x03a3, B:189:0x03ac, B:191:0x03b4, B:194:0x03bd, B:196:0x03c3, B:198:0x03ca, B:200:0x03d2, B:202:0x03de, B:204:0x03f2, B:213:0x0437, B:215:0x0449, B:224:0x048e, B:226:0x04a0, B:235:0x04e6, B:237:0x04f8, B:246:0x0541, B:248:0x0553, B:257:0x059c, B:259:0x05ae, B:268:0x05f7, B:270:0x0609, B:279:0x0652, B:281:0x0664, B:290:0x06ad, B:292:0x06bf, B:301:0x0708, B:303:0x071a, B:312:0x0763, B:314:0x0775, B:323:0x07be, B:325:0x07d0, B:334:0x0819, B:336:0x082b, B:345:0x0874, B:347:0x0886, B:356:0x08cf, B:358:0x08e1, B:367:0x092a, B:369:0x093c, B:378:0x0985, B:380:0x0997, B:389:0x09e0, B:391:0x09f2, B:400:0x0a3b, B:402:0x0a50, B:411:0x0a98, B:413:0x0aad, B:197:0x03c7, B:173:0x0330, B:130:0x01db, B:132:0x01ef), top: B:425:0x000e }] */
            /* JADX WARN: Removed duplicated region for block: B:185:0x0392 A[Catch: Exception -> 0x01d5, TryCatch #0 {Exception -> 0x01d5, blocks: (B:7:0x0021, B:421:0x0ae8, B:10:0x002a, B:417:0x0ace, B:13:0x0037, B:410:0x0a8b, B:16:0x0040, B:406:0x0a71, B:19:0x004d, B:399:0x0a2d, B:22:0x0056, B:395:0x0a13, B:25:0x0063, B:388:0x09d2, B:28:0x006c, B:384:0x09b8, B:31:0x0079, B:377:0x0977, B:34:0x0082, B:373:0x095d, B:37:0x008f, B:366:0x091c, B:40:0x0098, B:362:0x0902, B:43:0x00a5, B:355:0x08c1, B:46:0x00ae, B:351:0x08a7, B:49:0x00bb, B:344:0x0866, B:52:0x00c4, B:340:0x084c, B:55:0x00d1, B:333:0x080b, B:58:0x00da, B:329:0x07f1, B:61:0x00e7, B:322:0x07b0, B:64:0x00f0, B:318:0x0796, B:67:0x00fd, B:311:0x0755, B:70:0x0106, B:307:0x073b, B:73:0x0113, B:300:0x06fa, B:76:0x011c, B:296:0x06e0, B:79:0x0129, B:289:0x069f, B:82:0x0132, B:285:0x0685, B:85:0x013f, B:278:0x0644, B:88:0x0148, B:274:0x062a, B:91:0x0155, B:267:0x05e9, B:94:0x015e, B:263:0x05cf, B:97:0x016b, B:256:0x058e, B:100:0x0174, B:252:0x0574, B:103:0x0181, B:245:0x0533, B:106:0x018a, B:241:0x0519, B:109:0x0197, B:234:0x04d8, B:112:0x01a0, B:230:0x04bf, B:115:0x01ad, B:223:0x0480, B:118:0x01b6, B:219:0x0468, B:121:0x01c3, B:212:0x0429, B:124:0x01cc, B:208:0x0411, B:125:0x01d1, B:135:0x020b, B:136:0x021b, B:138:0x026e, B:140:0x0278, B:142:0x0282, B:144:0x028c, B:146:0x0296, B:148:0x02a0, B:150:0x02aa, B:152:0x02b4, B:154:0x02be, B:156:0x02c8, B:158:0x02d2, B:160:0x02dc, B:162:0x02e6, B:163:0x02ef, B:165:0x030d, B:167:0x0318, B:169:0x0320, B:171:0x0328, B:174:0x0345, B:176:0x0368, B:182:0x0374, B:183:0x038a, B:185:0x0392, B:186:0x039b, B:188:0x03a3, B:189:0x03ac, B:191:0x03b4, B:194:0x03bd, B:196:0x03c3, B:198:0x03ca, B:200:0x03d2, B:202:0x03de, B:204:0x03f2, B:213:0x0437, B:215:0x0449, B:224:0x048e, B:226:0x04a0, B:235:0x04e6, B:237:0x04f8, B:246:0x0541, B:248:0x0553, B:257:0x059c, B:259:0x05ae, B:268:0x05f7, B:270:0x0609, B:279:0x0652, B:281:0x0664, B:290:0x06ad, B:292:0x06bf, B:301:0x0708, B:303:0x071a, B:312:0x0763, B:314:0x0775, B:323:0x07be, B:325:0x07d0, B:334:0x0819, B:336:0x082b, B:345:0x0874, B:347:0x0886, B:356:0x08cf, B:358:0x08e1, B:367:0x092a, B:369:0x093c, B:378:0x0985, B:380:0x0997, B:389:0x09e0, B:391:0x09f2, B:400:0x0a3b, B:402:0x0a50, B:411:0x0a98, B:413:0x0aad, B:197:0x03c7, B:173:0x0330, B:130:0x01db, B:132:0x01ef), top: B:425:0x000e }] */
            /* JADX WARN: Removed duplicated region for block: B:188:0x03a3 A[Catch: Exception -> 0x01d5, TryCatch #0 {Exception -> 0x01d5, blocks: (B:7:0x0021, B:421:0x0ae8, B:10:0x002a, B:417:0x0ace, B:13:0x0037, B:410:0x0a8b, B:16:0x0040, B:406:0x0a71, B:19:0x004d, B:399:0x0a2d, B:22:0x0056, B:395:0x0a13, B:25:0x0063, B:388:0x09d2, B:28:0x006c, B:384:0x09b8, B:31:0x0079, B:377:0x0977, B:34:0x0082, B:373:0x095d, B:37:0x008f, B:366:0x091c, B:40:0x0098, B:362:0x0902, B:43:0x00a5, B:355:0x08c1, B:46:0x00ae, B:351:0x08a7, B:49:0x00bb, B:344:0x0866, B:52:0x00c4, B:340:0x084c, B:55:0x00d1, B:333:0x080b, B:58:0x00da, B:329:0x07f1, B:61:0x00e7, B:322:0x07b0, B:64:0x00f0, B:318:0x0796, B:67:0x00fd, B:311:0x0755, B:70:0x0106, B:307:0x073b, B:73:0x0113, B:300:0x06fa, B:76:0x011c, B:296:0x06e0, B:79:0x0129, B:289:0x069f, B:82:0x0132, B:285:0x0685, B:85:0x013f, B:278:0x0644, B:88:0x0148, B:274:0x062a, B:91:0x0155, B:267:0x05e9, B:94:0x015e, B:263:0x05cf, B:97:0x016b, B:256:0x058e, B:100:0x0174, B:252:0x0574, B:103:0x0181, B:245:0x0533, B:106:0x018a, B:241:0x0519, B:109:0x0197, B:234:0x04d8, B:112:0x01a0, B:230:0x04bf, B:115:0x01ad, B:223:0x0480, B:118:0x01b6, B:219:0x0468, B:121:0x01c3, B:212:0x0429, B:124:0x01cc, B:208:0x0411, B:125:0x01d1, B:135:0x020b, B:136:0x021b, B:138:0x026e, B:140:0x0278, B:142:0x0282, B:144:0x028c, B:146:0x0296, B:148:0x02a0, B:150:0x02aa, B:152:0x02b4, B:154:0x02be, B:156:0x02c8, B:158:0x02d2, B:160:0x02dc, B:162:0x02e6, B:163:0x02ef, B:165:0x030d, B:167:0x0318, B:169:0x0320, B:171:0x0328, B:174:0x0345, B:176:0x0368, B:182:0x0374, B:183:0x038a, B:185:0x0392, B:186:0x039b, B:188:0x03a3, B:189:0x03ac, B:191:0x03b4, B:194:0x03bd, B:196:0x03c3, B:198:0x03ca, B:200:0x03d2, B:202:0x03de, B:204:0x03f2, B:213:0x0437, B:215:0x0449, B:224:0x048e, B:226:0x04a0, B:235:0x04e6, B:237:0x04f8, B:246:0x0541, B:248:0x0553, B:257:0x059c, B:259:0x05ae, B:268:0x05f7, B:270:0x0609, B:279:0x0652, B:281:0x0664, B:290:0x06ad, B:292:0x06bf, B:301:0x0708, B:303:0x071a, B:312:0x0763, B:314:0x0775, B:323:0x07be, B:325:0x07d0, B:334:0x0819, B:336:0x082b, B:345:0x0874, B:347:0x0886, B:356:0x08cf, B:358:0x08e1, B:367:0x092a, B:369:0x093c, B:378:0x0985, B:380:0x0997, B:389:0x09e0, B:391:0x09f2, B:400:0x0a3b, B:402:0x0a50, B:411:0x0a98, B:413:0x0aad, B:197:0x03c7, B:173:0x0330, B:130:0x01db, B:132:0x01ef), top: B:425:0x000e }] */
            /* JADX WARN: Removed duplicated region for block: B:191:0x03b4 A[Catch: Exception -> 0x01d5, TryCatch #0 {Exception -> 0x01d5, blocks: (B:7:0x0021, B:421:0x0ae8, B:10:0x002a, B:417:0x0ace, B:13:0x0037, B:410:0x0a8b, B:16:0x0040, B:406:0x0a71, B:19:0x004d, B:399:0x0a2d, B:22:0x0056, B:395:0x0a13, B:25:0x0063, B:388:0x09d2, B:28:0x006c, B:384:0x09b8, B:31:0x0079, B:377:0x0977, B:34:0x0082, B:373:0x095d, B:37:0x008f, B:366:0x091c, B:40:0x0098, B:362:0x0902, B:43:0x00a5, B:355:0x08c1, B:46:0x00ae, B:351:0x08a7, B:49:0x00bb, B:344:0x0866, B:52:0x00c4, B:340:0x084c, B:55:0x00d1, B:333:0x080b, B:58:0x00da, B:329:0x07f1, B:61:0x00e7, B:322:0x07b0, B:64:0x00f0, B:318:0x0796, B:67:0x00fd, B:311:0x0755, B:70:0x0106, B:307:0x073b, B:73:0x0113, B:300:0x06fa, B:76:0x011c, B:296:0x06e0, B:79:0x0129, B:289:0x069f, B:82:0x0132, B:285:0x0685, B:85:0x013f, B:278:0x0644, B:88:0x0148, B:274:0x062a, B:91:0x0155, B:267:0x05e9, B:94:0x015e, B:263:0x05cf, B:97:0x016b, B:256:0x058e, B:100:0x0174, B:252:0x0574, B:103:0x0181, B:245:0x0533, B:106:0x018a, B:241:0x0519, B:109:0x0197, B:234:0x04d8, B:112:0x01a0, B:230:0x04bf, B:115:0x01ad, B:223:0x0480, B:118:0x01b6, B:219:0x0468, B:121:0x01c3, B:212:0x0429, B:124:0x01cc, B:208:0x0411, B:125:0x01d1, B:135:0x020b, B:136:0x021b, B:138:0x026e, B:140:0x0278, B:142:0x0282, B:144:0x028c, B:146:0x0296, B:148:0x02a0, B:150:0x02aa, B:152:0x02b4, B:154:0x02be, B:156:0x02c8, B:158:0x02d2, B:160:0x02dc, B:162:0x02e6, B:163:0x02ef, B:165:0x030d, B:167:0x0318, B:169:0x0320, B:171:0x0328, B:174:0x0345, B:176:0x0368, B:182:0x0374, B:183:0x038a, B:185:0x0392, B:186:0x039b, B:188:0x03a3, B:189:0x03ac, B:191:0x03b4, B:194:0x03bd, B:196:0x03c3, B:198:0x03ca, B:200:0x03d2, B:202:0x03de, B:204:0x03f2, B:213:0x0437, B:215:0x0449, B:224:0x048e, B:226:0x04a0, B:235:0x04e6, B:237:0x04f8, B:246:0x0541, B:248:0x0553, B:257:0x059c, B:259:0x05ae, B:268:0x05f7, B:270:0x0609, B:279:0x0652, B:281:0x0664, B:290:0x06ad, B:292:0x06bf, B:301:0x0708, B:303:0x071a, B:312:0x0763, B:314:0x0775, B:323:0x07be, B:325:0x07d0, B:334:0x0819, B:336:0x082b, B:345:0x0874, B:347:0x0886, B:356:0x08cf, B:358:0x08e1, B:367:0x092a, B:369:0x093c, B:378:0x0985, B:380:0x0997, B:389:0x09e0, B:391:0x09f2, B:400:0x0a3b, B:402:0x0a50, B:411:0x0a98, B:413:0x0aad, B:197:0x03c7, B:173:0x0330, B:130:0x01db, B:132:0x01ef), top: B:425:0x000e }] */
            /* JADX WARN: Removed duplicated region for block: B:200:0x03d2 A[Catch: Exception -> 0x01d5, TryCatch #0 {Exception -> 0x01d5, blocks: (B:7:0x0021, B:421:0x0ae8, B:10:0x002a, B:417:0x0ace, B:13:0x0037, B:410:0x0a8b, B:16:0x0040, B:406:0x0a71, B:19:0x004d, B:399:0x0a2d, B:22:0x0056, B:395:0x0a13, B:25:0x0063, B:388:0x09d2, B:28:0x006c, B:384:0x09b8, B:31:0x0079, B:377:0x0977, B:34:0x0082, B:373:0x095d, B:37:0x008f, B:366:0x091c, B:40:0x0098, B:362:0x0902, B:43:0x00a5, B:355:0x08c1, B:46:0x00ae, B:351:0x08a7, B:49:0x00bb, B:344:0x0866, B:52:0x00c4, B:340:0x084c, B:55:0x00d1, B:333:0x080b, B:58:0x00da, B:329:0x07f1, B:61:0x00e7, B:322:0x07b0, B:64:0x00f0, B:318:0x0796, B:67:0x00fd, B:311:0x0755, B:70:0x0106, B:307:0x073b, B:73:0x0113, B:300:0x06fa, B:76:0x011c, B:296:0x06e0, B:79:0x0129, B:289:0x069f, B:82:0x0132, B:285:0x0685, B:85:0x013f, B:278:0x0644, B:88:0x0148, B:274:0x062a, B:91:0x0155, B:267:0x05e9, B:94:0x015e, B:263:0x05cf, B:97:0x016b, B:256:0x058e, B:100:0x0174, B:252:0x0574, B:103:0x0181, B:245:0x0533, B:106:0x018a, B:241:0x0519, B:109:0x0197, B:234:0x04d8, B:112:0x01a0, B:230:0x04bf, B:115:0x01ad, B:223:0x0480, B:118:0x01b6, B:219:0x0468, B:121:0x01c3, B:212:0x0429, B:124:0x01cc, B:208:0x0411, B:125:0x01d1, B:135:0x020b, B:136:0x021b, B:138:0x026e, B:140:0x0278, B:142:0x0282, B:144:0x028c, B:146:0x0296, B:148:0x02a0, B:150:0x02aa, B:152:0x02b4, B:154:0x02be, B:156:0x02c8, B:158:0x02d2, B:160:0x02dc, B:162:0x02e6, B:163:0x02ef, B:165:0x030d, B:167:0x0318, B:169:0x0320, B:171:0x0328, B:174:0x0345, B:176:0x0368, B:182:0x0374, B:183:0x038a, B:185:0x0392, B:186:0x039b, B:188:0x03a3, B:189:0x03ac, B:191:0x03b4, B:194:0x03bd, B:196:0x03c3, B:198:0x03ca, B:200:0x03d2, B:202:0x03de, B:204:0x03f2, B:213:0x0437, B:215:0x0449, B:224:0x048e, B:226:0x04a0, B:235:0x04e6, B:237:0x04f8, B:246:0x0541, B:248:0x0553, B:257:0x059c, B:259:0x05ae, B:268:0x05f7, B:270:0x0609, B:279:0x0652, B:281:0x0664, B:290:0x06ad, B:292:0x06bf, B:301:0x0708, B:303:0x071a, B:312:0x0763, B:314:0x0775, B:323:0x07be, B:325:0x07d0, B:334:0x0819, B:336:0x082b, B:345:0x0874, B:347:0x0886, B:356:0x08cf, B:358:0x08e1, B:367:0x092a, B:369:0x093c, B:378:0x0985, B:380:0x0997, B:389:0x09e0, B:391:0x09f2, B:400:0x0a3b, B:402:0x0a50, B:411:0x0a98, B:413:0x0aad, B:197:0x03c7, B:173:0x0330, B:130:0x01db, B:132:0x01ef), top: B:425:0x000e }] */
            /* JADX WARN: Removed duplicated region for block: B:210:0x0425 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:211:0x0426  */
            /* JADX WARN: Removed duplicated region for block: B:215:0x0449 A[Catch: Exception -> 0x01d5, TryCatch #0 {Exception -> 0x01d5, blocks: (B:7:0x0021, B:421:0x0ae8, B:10:0x002a, B:417:0x0ace, B:13:0x0037, B:410:0x0a8b, B:16:0x0040, B:406:0x0a71, B:19:0x004d, B:399:0x0a2d, B:22:0x0056, B:395:0x0a13, B:25:0x0063, B:388:0x09d2, B:28:0x006c, B:384:0x09b8, B:31:0x0079, B:377:0x0977, B:34:0x0082, B:373:0x095d, B:37:0x008f, B:366:0x091c, B:40:0x0098, B:362:0x0902, B:43:0x00a5, B:355:0x08c1, B:46:0x00ae, B:351:0x08a7, B:49:0x00bb, B:344:0x0866, B:52:0x00c4, B:340:0x084c, B:55:0x00d1, B:333:0x080b, B:58:0x00da, B:329:0x07f1, B:61:0x00e7, B:322:0x07b0, B:64:0x00f0, B:318:0x0796, B:67:0x00fd, B:311:0x0755, B:70:0x0106, B:307:0x073b, B:73:0x0113, B:300:0x06fa, B:76:0x011c, B:296:0x06e0, B:79:0x0129, B:289:0x069f, B:82:0x0132, B:285:0x0685, B:85:0x013f, B:278:0x0644, B:88:0x0148, B:274:0x062a, B:91:0x0155, B:267:0x05e9, B:94:0x015e, B:263:0x05cf, B:97:0x016b, B:256:0x058e, B:100:0x0174, B:252:0x0574, B:103:0x0181, B:245:0x0533, B:106:0x018a, B:241:0x0519, B:109:0x0197, B:234:0x04d8, B:112:0x01a0, B:230:0x04bf, B:115:0x01ad, B:223:0x0480, B:118:0x01b6, B:219:0x0468, B:121:0x01c3, B:212:0x0429, B:124:0x01cc, B:208:0x0411, B:125:0x01d1, B:135:0x020b, B:136:0x021b, B:138:0x026e, B:140:0x0278, B:142:0x0282, B:144:0x028c, B:146:0x0296, B:148:0x02a0, B:150:0x02aa, B:152:0x02b4, B:154:0x02be, B:156:0x02c8, B:158:0x02d2, B:160:0x02dc, B:162:0x02e6, B:163:0x02ef, B:165:0x030d, B:167:0x0318, B:169:0x0320, B:171:0x0328, B:174:0x0345, B:176:0x0368, B:182:0x0374, B:183:0x038a, B:185:0x0392, B:186:0x039b, B:188:0x03a3, B:189:0x03ac, B:191:0x03b4, B:194:0x03bd, B:196:0x03c3, B:198:0x03ca, B:200:0x03d2, B:202:0x03de, B:204:0x03f2, B:213:0x0437, B:215:0x0449, B:224:0x048e, B:226:0x04a0, B:235:0x04e6, B:237:0x04f8, B:246:0x0541, B:248:0x0553, B:257:0x059c, B:259:0x05ae, B:268:0x05f7, B:270:0x0609, B:279:0x0652, B:281:0x0664, B:290:0x06ad, B:292:0x06bf, B:301:0x0708, B:303:0x071a, B:312:0x0763, B:314:0x0775, B:323:0x07be, B:325:0x07d0, B:334:0x0819, B:336:0x082b, B:345:0x0874, B:347:0x0886, B:356:0x08cf, B:358:0x08e1, B:367:0x092a, B:369:0x093c, B:378:0x0985, B:380:0x0997, B:389:0x09e0, B:391:0x09f2, B:400:0x0a3b, B:402:0x0a50, B:411:0x0a98, B:413:0x0aad, B:197:0x03c7, B:173:0x0330, B:130:0x01db, B:132:0x01ef), top: B:425:0x000e }] */
            /* JADX WARN: Removed duplicated region for block: B:221:0x047c A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:222:0x047d  */
            /* JADX WARN: Removed duplicated region for block: B:224:0x048e A[Catch: Exception -> 0x01d5, TryCatch #0 {Exception -> 0x01d5, blocks: (B:7:0x0021, B:421:0x0ae8, B:10:0x002a, B:417:0x0ace, B:13:0x0037, B:410:0x0a8b, B:16:0x0040, B:406:0x0a71, B:19:0x004d, B:399:0x0a2d, B:22:0x0056, B:395:0x0a13, B:25:0x0063, B:388:0x09d2, B:28:0x006c, B:384:0x09b8, B:31:0x0079, B:377:0x0977, B:34:0x0082, B:373:0x095d, B:37:0x008f, B:366:0x091c, B:40:0x0098, B:362:0x0902, B:43:0x00a5, B:355:0x08c1, B:46:0x00ae, B:351:0x08a7, B:49:0x00bb, B:344:0x0866, B:52:0x00c4, B:340:0x084c, B:55:0x00d1, B:333:0x080b, B:58:0x00da, B:329:0x07f1, B:61:0x00e7, B:322:0x07b0, B:64:0x00f0, B:318:0x0796, B:67:0x00fd, B:311:0x0755, B:70:0x0106, B:307:0x073b, B:73:0x0113, B:300:0x06fa, B:76:0x011c, B:296:0x06e0, B:79:0x0129, B:289:0x069f, B:82:0x0132, B:285:0x0685, B:85:0x013f, B:278:0x0644, B:88:0x0148, B:274:0x062a, B:91:0x0155, B:267:0x05e9, B:94:0x015e, B:263:0x05cf, B:97:0x016b, B:256:0x058e, B:100:0x0174, B:252:0x0574, B:103:0x0181, B:245:0x0533, B:106:0x018a, B:241:0x0519, B:109:0x0197, B:234:0x04d8, B:112:0x01a0, B:230:0x04bf, B:115:0x01ad, B:223:0x0480, B:118:0x01b6, B:219:0x0468, B:121:0x01c3, B:212:0x0429, B:124:0x01cc, B:208:0x0411, B:125:0x01d1, B:135:0x020b, B:136:0x021b, B:138:0x026e, B:140:0x0278, B:142:0x0282, B:144:0x028c, B:146:0x0296, B:148:0x02a0, B:150:0x02aa, B:152:0x02b4, B:154:0x02be, B:156:0x02c8, B:158:0x02d2, B:160:0x02dc, B:162:0x02e6, B:163:0x02ef, B:165:0x030d, B:167:0x0318, B:169:0x0320, B:171:0x0328, B:174:0x0345, B:176:0x0368, B:182:0x0374, B:183:0x038a, B:185:0x0392, B:186:0x039b, B:188:0x03a3, B:189:0x03ac, B:191:0x03b4, B:194:0x03bd, B:196:0x03c3, B:198:0x03ca, B:200:0x03d2, B:202:0x03de, B:204:0x03f2, B:213:0x0437, B:215:0x0449, B:224:0x048e, B:226:0x04a0, B:235:0x04e6, B:237:0x04f8, B:246:0x0541, B:248:0x0553, B:257:0x059c, B:259:0x05ae, B:268:0x05f7, B:270:0x0609, B:279:0x0652, B:281:0x0664, B:290:0x06ad, B:292:0x06bf, B:301:0x0708, B:303:0x071a, B:312:0x0763, B:314:0x0775, B:323:0x07be, B:325:0x07d0, B:334:0x0819, B:336:0x082b, B:345:0x0874, B:347:0x0886, B:356:0x08cf, B:358:0x08e1, B:367:0x092a, B:369:0x093c, B:378:0x0985, B:380:0x0997, B:389:0x09e0, B:391:0x09f2, B:400:0x0a3b, B:402:0x0a50, B:411:0x0a98, B:413:0x0aad, B:197:0x03c7, B:173:0x0330, B:130:0x01db, B:132:0x01ef), top: B:425:0x000e }] */
            /* JADX WARN: Removed duplicated region for block: B:232:0x04d4 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:233:0x04d5  */
            /* JADX WARN: Removed duplicated region for block: B:243:0x052f A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:244:0x0530  */
            /* JADX WARN: Removed duplicated region for block: B:254:0x058a A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:255:0x058b  */
            /* JADX WARN: Removed duplicated region for block: B:265:0x05e5 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:266:0x05e6  */
            /* JADX WARN: Removed duplicated region for block: B:276:0x0640 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:277:0x0641  */
            /* JADX WARN: Removed duplicated region for block: B:287:0x069b A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:288:0x069c  */
            /* JADX WARN: Removed duplicated region for block: B:298:0x06f6 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:299:0x06f7  */
            /* JADX WARN: Removed duplicated region for block: B:309:0x0751 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:310:0x0752  */
            /* JADX WARN: Removed duplicated region for block: B:320:0x07ac A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:321:0x07ad  */
            /* JADX WARN: Removed duplicated region for block: B:331:0x0807 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:332:0x0808  */
            /* JADX WARN: Removed duplicated region for block: B:342:0x0862 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:343:0x0863  */
            /* JADX WARN: Removed duplicated region for block: B:353:0x08bd A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:354:0x08be  */
            /* JADX WARN: Removed duplicated region for block: B:364:0x0918 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:365:0x0919  */
            /* JADX WARN: Removed duplicated region for block: B:375:0x0973 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:376:0x0974  */
            /* JADX WARN: Removed duplicated region for block: B:386:0x09ce A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:387:0x09cf  */
            /* JADX WARN: Removed duplicated region for block: B:397:0x0a29 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:398:0x0a2a  */
            /* JADX WARN: Removed duplicated region for block: B:408:0x0a87 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:409:0x0a88  */
            /* JADX WARN: Removed duplicated region for block: B:419:0x0ae4 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:420:0x0ae5  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r14) {
                /*
                    Method dump skipped, instructions count: 2904
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.y.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2346}, m = "prepareDanceDetailsObject", n = {"danceActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class y0 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public y0(Continuation<? super y0> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.M(null, null, null, null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion$getPreparationPlan$2", f = "Formator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class z extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PlanDataHolder>, Object> {
            public final /* synthetic */ SCurrentPreparationPlanRes $p0;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public z(SCurrentPreparationPlanRes sCurrentPreparationPlanRes, Continuation<? super z> continuation) {
                super(2, continuation);
                this.$p0 = sCurrentPreparationPlanRes;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new z(this.$p0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super PlanDataHolder> continuation) {
                return ((z) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:33:0x0294  */
            /* JADX WARN: Removed duplicated region for block: B:46:0x029b A[SYNTHETIC] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r23) {
                /*
                    Method dump skipped, instructions count: 763
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.z.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.Formator$Companion", f = "Formator.kt", i = {0, 0}, l = {2630}, m = "prepareEllipticalDetailsObject", n = {"ellipticalActivityDetails", "entityWorkoutSession"}, s = {"L$0", "L$1"})
        /* loaded from: classes2.dex */
        public static final class z0 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public z0(Continuation<? super z0> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.N(null, null, null, null, this);
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Object A(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super TennisActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new h0(entityWorkoutSession, list, context, null), continuation);
        }

        public final int B(Context context, PostActivitySessionDataRequest postActivitySessionDataRequest) {
            if (BleApiManager.getInstance(context) != null && BleApiManager.getInstance(context).getBleApi() != null && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isGenericActivityDataSampleSupported()) {
                if (postActivitySessionDataRequest.getActivityData().getTotalSteps() != null) {
                    Integer totalSteps = postActivitySessionDataRequest.getActivityData().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps, "{\n                    po…alSteps\n                }");
                    return totalSteps.intValue();
                }
                return 0;
            }
            String sessionType = postActivitySessionDataRequest.getSessionType();
            if (Intrinsics.areEqual(sessionType, ActivityType.WALK.toString())) {
                if (postActivitySessionDataRequest.getWalkDetails().getTotalSteps() != null) {
                    Integer totalSteps2 = postActivitySessionDataRequest.getWalkDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps2, "{\n                      …                        }");
                    return totalSteps2.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityMode.TREADMILL.toString())) {
                if (postActivitySessionDataRequest.getTreadmillActivityDetails().getTotalSteps() != null) {
                    Integer totalSteps3 = postActivitySessionDataRequest.getTreadmillActivityDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps3, "{\n                      …                        }");
                    return totalSteps3.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityMode.CLIMBING.toString())) {
                if (postActivitySessionDataRequest.getClimbingActivityDetails().getTotalSteps() != null) {
                    Integer totalSteps4 = postActivitySessionDataRequest.getClimbingActivityDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps4, "{\n                      …                        }");
                    return totalSteps4.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.RUN.toString())) {
                if (postActivitySessionDataRequest.getRunDetails().getTotalSteps() != null) {
                    Integer totalSteps5 = postActivitySessionDataRequest.getRunDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps5, "{\n                      …                        }");
                    return totalSteps5.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.HIKING.toString())) {
                if (postActivitySessionDataRequest.getHikingDetails().getTotalSteps() != null) {
                    Integer totalSteps6 = postActivitySessionDataRequest.getHikingDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps6, "{\n                      …                        }");
                    return totalSteps6.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.DANCE.toString())) {
                if (postActivitySessionDataRequest.getDanceDetails().getTotalSteps() != null) {
                    Integer totalSteps7 = postActivitySessionDataRequest.getDanceDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps7, "{\n                      …                        }");
                    return totalSteps7.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.FREE_EXERCISE.toString())) {
                if (postActivitySessionDataRequest.getFreeExerciseActivityDetails().getTotalSteps() != null) {
                    Integer totalSteps8 = postActivitySessionDataRequest.getFreeExerciseActivityDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps8, "{\n                      …                        }");
                    return totalSteps8.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.BASKETBALL.toString())) {
                if (postActivitySessionDataRequest.getBasketballDetails().getTotalSteps() != null) {
                    Integer totalSteps9 = postActivitySessionDataRequest.getBasketballDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps9, "{\n                      …                        }");
                    return totalSteps9.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.FOOTBALL.toString())) {
                if (postActivitySessionDataRequest.getFootballDetails().getTotalSteps() != null) {
                    Integer totalSteps10 = postActivitySessionDataRequest.getFootballDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps10, "{\n                      …                        }");
                    return totalSteps10.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.YOGA.toString())) {
                if (postActivitySessionDataRequest.getYogaDetails().getTotalSteps() != null) {
                    Integer totalSteps11 = postActivitySessionDataRequest.getYogaDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps11, "{\n                      …                        }");
                    return totalSteps11.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.CYCLE.toString())) {
                if (postActivitySessionDataRequest.getCycleDetails().getTotalSteps() != null) {
                    Integer totalSteps12 = postActivitySessionDataRequest.getCycleDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps12, "{\n                      …                        }");
                    return totalSteps12.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.TENNIS.toString())) {
                if (postActivitySessionDataRequest.getTennisDetails().getTotalSteps() != null) {
                    Integer totalSteps13 = postActivitySessionDataRequest.getTennisDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps13, "{\n                      …                        }");
                    return totalSteps13.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.WORKOUT.toString())) {
                if (postActivitySessionDataRequest.getWorkoutDetails().getTotalSteps() != null) {
                    Integer totalSteps14 = postActivitySessionDataRequest.getWorkoutDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps14, "{\n                      …                        }");
                    return totalSteps14.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.MEDITATION.toString())) {
                if (postActivitySessionDataRequest.getMeditationDetails().getTotalSteps() != null) {
                    Integer totalSteps15 = postActivitySessionDataRequest.getMeditationDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps15, "{\n                      …                        }");
                    return totalSteps15.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.BADMINTON.toString())) {
                if (postActivitySessionDataRequest.getBadmintonDetails().getTotalSteps() != null) {
                    Integer totalSteps16 = postActivitySessionDataRequest.getBadmintonDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps16, "{\n                      …                        }");
                    return totalSteps16.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.SKIPPING.toString())) {
                if (postActivitySessionDataRequest.getSkippingActivityDetails().getTotalSteps() != null) {
                    Integer totalSteps17 = postActivitySessionDataRequest.getSkippingActivityDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps17, "{\n                      …                        }");
                    return totalSteps17.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.ELLIPTICAL.toString())) {
                if (postActivitySessionDataRequest.getEllipticalDetails().getTotalSteps() != null) {
                    Integer totalSteps18 = postActivitySessionDataRequest.getEllipticalDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps18, "{\n                      …                        }");
                    return totalSteps18.intValue();
                }
                return 0;
            } else if (Intrinsics.areEqual(sessionType, ActivityType.ROWING.toString())) {
                if (postActivitySessionDataRequest.getRowingDetails().getTotalSteps() != null) {
                    Integer totalSteps19 = postActivitySessionDataRequest.getRowingDetails().getTotalSteps();
                    Intrinsics.checkNotNullExpressionValue(totalSteps19, "{\n                      …                        }");
                    return totalSteps19.intValue();
                }
                return 0;
            } else if (!Intrinsics.areEqual(sessionType, ActivityType.PHYSICAL_ACTIVITY.toString()) || postActivitySessionDataRequest.getPhysicalActivityDetails().getTotalSteps() == null) {
                return 0;
            } else {
                Integer totalSteps20 = postActivitySessionDataRequest.getPhysicalActivityDetails().getTotalSteps();
                Intrinsics.checkNotNullExpressionValue(totalSteps20, "{\n                      …                        }");
                return totalSteps20.intValue();
            }
        }

        public final Object C(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super TreadmillActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new j0(entityWorkoutSession, list, context, null), continuation);
        }

        public final Object D(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super RunWalkActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new l0(entityWorkoutSession, list, context, null), continuation);
        }

        public final Object E(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super WorkoutActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new n0(entityWorkoutSession, list, context, null), continuation);
        }

        public final Object F(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super YogaActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new q0(entityWorkoutSession, list, context, null), continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object G(com.coveiot.coveaccess.activitysession.BasketBallActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.G(com.coveiot.coveaccess.activitysession.BasketBallActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0101  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x010a  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x011a  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x012a  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x013a  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x014a  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x015a  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0161  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object H(com.coveiot.coveaccess.activitysession.ClimbingActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 397
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.H(com.coveiot.coveaccess.activitysession.ClimbingActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object I(com.coveiot.coveaccess.activitysession.HikingActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.I(com.coveiot.coveaccess.activitysession.HikingActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x016e  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0177  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x017e  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0187  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x018e  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0197  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x019e  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x01a7  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x01ae  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x01b7  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x01be  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x01c7  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x01ce  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object J(com.coveiot.coveaccess.model.server.GenericActivitySessionData r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 506
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.J(com.coveiot.coveaccess.model.server.GenericActivitySessionData, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object K(com.coveiot.coveaccess.activitysession.BadmintonActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.K(com.coveiot.coveaccess.activitysession.BadmintonActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0113  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x011c  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0123  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x012c  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x013c  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0143  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x014c  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0153  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x015c  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0163  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x016c  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0173  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object L(com.coveiot.coveaccess.activitysession.CycleActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 415
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.L(com.coveiot.coveaccess.activitysession.CycleActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object M(com.coveiot.coveaccess.activitysession.DanceActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.M(com.coveiot.coveaccess.activitysession.DanceActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object N(com.coveiot.coveaccess.activitysession.EllipticalActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.N(com.coveiot.coveaccess.activitysession.EllipticalActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object O(com.coveiot.coveaccess.activitysession.FootBallActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.O(com.coveiot.coveaccess.activitysession.FootBallActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object P(com.coveiot.coveaccess.activitysession.FreeExerciseActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.P(com.coveiot.coveaccess.activitysession.FreeExerciseActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00e6  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00ef  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00f6  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00ff  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0106  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x010f  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0116  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x011f  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0126  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x012f  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0136  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x013f  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0146  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object Q(com.coveiot.coveaccess.activitysession.MeditationActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 370
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.Q(com.coveiot.coveaccess.activitysession.MeditationActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x010b  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0114  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x011b  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0124  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x012b  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0134  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x013b  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0144  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x014b  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0154  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x015b  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0164  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x016b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object R(com.coveiot.coveaccess.activitysession.PhysicalActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 407
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.R(com.coveiot.coveaccess.activitysession.PhysicalActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object S(com.coveiot.coveaccess.activitysession.RowingMachineActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.S(com.coveiot.coveaccess.activitysession.RowingMachineActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0107  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0110  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0117  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0120  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0127  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0130  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0137  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0140  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0147  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0150  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0157  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0160  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0167  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object T(com.coveiot.coveaccess.activitysession.RunWalkActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 403
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.T(com.coveiot.coveaccess.activitysession.RunWalkActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object U(com.coveiot.coveaccess.activitysession.SkippingActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.U(com.coveiot.coveaccess.activitysession.SkippingActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object V(com.coveiot.coveaccess.activitysession.TennisActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.V(com.coveiot.coveaccess.activitysession.TennisActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0107  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0110  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0117  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0120  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0127  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0130  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0137  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0140  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0147  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0150  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0157  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0160  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0167  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object W(com.coveiot.coveaccess.activitysession.TreadmillActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 403
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.W(com.coveiot.coveaccess.activitysession.TreadmillActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object X(com.coveiot.coveaccess.activitysession.WorkoutActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.X(com.coveiot.coveaccess.activitysession.WorkoutActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0108  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0128  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0141  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object Y(com.coveiot.coveaccess.activitysession.YogaActivityDetails r9, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r10, java.util.List<? extends com.coveiot.android.activitymodes.database.entities.Sample> r11, android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<com.coveiot.android.activitymodes.database.entities.EntityWorkoutSessionSegment>, ? extends java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>>> r13) {
            /*
                Method dump skipped, instructions count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.Formator.Companion.Y(com.coveiot.coveaccess.activitysession.YogaActivityDetails, com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession, java.util.List, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        public final ActivitiesSampleDetails a(Context context, PostActivitySessionDataRequest postActivitySessionDataRequest) {
            if (BleApiManager.getInstance(context) != null && BleApiManager.getInstance(context).getBleApi() != null && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isGenericActivityDataSampleSupported()) {
                return postActivitySessionDataRequest.getActivityData();
            }
            String sessionType = postActivitySessionDataRequest.getSessionType();
            if (Intrinsics.areEqual(sessionType, ActivityType.WALK.toString())) {
                return postActivitySessionDataRequest.getWalkDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.RUN.toString())) {
                return postActivitySessionDataRequest.getRunDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.HIKING.toString())) {
                return postActivitySessionDataRequest.getHikingDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.BADMINTON.toString())) {
                return postActivitySessionDataRequest.getBadmintonDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.FREE_EXERCISE.toString())) {
                return postActivitySessionDataRequest.getFreeExerciseActivityDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.BASKETBALL.toString())) {
                return postActivitySessionDataRequest.getBasketballDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.FOOTBALL.toString())) {
                return postActivitySessionDataRequest.getFootballDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.WORKOUT.toString())) {
                return postActivitySessionDataRequest.getWorkoutDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.CYCLE.toString())) {
                return postActivitySessionDataRequest.getCycleDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.YOGA.toString())) {
                return postActivitySessionDataRequest.getYogaDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.DANCE.toString())) {
                return postActivitySessionDataRequest.getDanceDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.TENNIS.toString())) {
                return postActivitySessionDataRequest.getTennisDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.MEDITATION.toString())) {
                return postActivitySessionDataRequest.getMeditationDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.TREADMILL.toString())) {
                return postActivitySessionDataRequest.getTreadmillActivityDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.CLIMBING.toString())) {
                return postActivitySessionDataRequest.getClimbingActivityDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.SKIPPING.toString())) {
                return postActivitySessionDataRequest.getSkippingActivityDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.ELLIPTICAL.toString())) {
                return postActivitySessionDataRequest.getEllipticalDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.ROWING.toString())) {
                return postActivitySessionDataRequest.getRowingDetails();
            }
            if (Intrinsics.areEqual(sessionType, ActivityType.PHYSICAL_ACTIVITY.toString())) {
                return postActivitySessionDataRequest.getPhysicalActivityDetails();
            }
            return null;
        }

        public final String b(Integer num, Integer num2, Context context) {
            return PhysicalActivityRepository.Companion.getInstance(context).getPhysicalActivity(num, num2).getActivityCode();
        }

        public final Object c(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super GenericActivitySessionData> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new a(entityWorkoutSession, list, context, null), continuation);
        }

        public final Object d(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super BadmintonActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new c(entityWorkoutSession, list, context, null), continuation);
        }

        public final BaseUnits e(Context context, String str) {
            BaseUnits baseUnits = new BaseUnits();
            if (BleApiManager.getInstance(context) != null && BleApiManager.getInstance(context).getBleApi() != null && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isGenericActivityDataSampleSupported()) {
                BASEUNIT baseunit = BASEUNIT.SECONDS;
                baseUnits.setActivityDuration(baseunit.toString());
                baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                BASEUNIT baseunit2 = BASEUNIT.CENTIMETERS;
                baseUnits.setUserHeight(baseunit2.toString());
                baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                baseUnits.setDistance(baseunit2.toString());
                baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                baseUnits.setStrideLength(baseunit2.toString());
                baseUnits.setHrZoneDuration(baseunit.toString());
                baseUnits.setSampleRate(baseunit.toString());
                baseUnits.setSpeed(BASEUNIT.KM_PER_HOUR.toString());
                baseUnits.setStepFreq(BASEUNIT.STEPS_PER_MIN.toString());
                baseUnits.setStrokeLength(baseunit2.toString());
                baseUnits.setPoolLength(baseunit2.toString());
                baseUnits.setStrokeFreq(BASEUNIT.STROKES_PER_MIN.toString());
            } else if (Intrinsics.areEqual(str, ActivityType.WALK.toString())) {
                BASEUNIT baseunit3 = BASEUNIT.SECONDS;
                baseUnits.setActivityDuration(baseunit3.toString());
                baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                BASEUNIT baseunit4 = BASEUNIT.CENTIMETERS;
                baseUnits.setUserHeight(baseunit4.toString());
                baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                baseUnits.setStrideLength(baseunit4.toString());
                baseUnits.setSpeed(BASEUNIT.KM_PER_HOUR.toString());
                baseUnits.setSampleRate(baseunit3.toString());
                baseUnits.setDistance(baseunit4.toString());
                baseUnits.setHrZoneDuration(baseunit3.toString());
            } else {
                if (Intrinsics.areEqual(str, ActivityType.RUN.toString()) ? true : Intrinsics.areEqual(str, ActivityMode.TREADMILL.toString())) {
                    BASEUNIT baseunit5 = BASEUNIT.SECONDS;
                    baseUnits.setActivityDuration(baseunit5.toString());
                    baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                    BASEUNIT baseunit6 = BASEUNIT.CENTIMETERS;
                    baseUnits.setUserHeight(baseunit6.toString());
                    baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                    baseUnits.setStrideLength(baseunit6.toString());
                    baseUnits.setSpeed(BASEUNIT.KM_PER_HOUR.toString());
                    baseUnits.setSampleRate(baseunit5.toString());
                    baseUnits.setDistance(baseunit6.toString());
                    baseUnits.setHrZoneDuration(baseunit5.toString());
                } else {
                    if (Intrinsics.areEqual(str, ActivityType.PHYSICAL_ACTIVITY.toString()) ? true : Intrinsics.areEqual(str, ActivityMode.TREADMILL.toString())) {
                        BASEUNIT baseunit7 = BASEUNIT.SECONDS;
                        baseUnits.setActivityDuration(baseunit7.toString());
                        baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                        BASEUNIT baseunit8 = BASEUNIT.CENTIMETERS;
                        baseUnits.setUserHeight(baseunit8.toString());
                        baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                        baseUnits.setStrideLength(baseunit8.toString());
                        baseUnits.setSpeed(BASEUNIT.KM_PER_HOUR.toString());
                        baseUnits.setSampleRate(baseunit7.toString());
                        baseUnits.setDistance(baseunit8.toString());
                        baseUnits.setHrZoneDuration(baseunit7.toString());
                    } else if (Intrinsics.areEqual(str, ActivityType.SWIM.toString())) {
                        BASEUNIT baseunit9 = BASEUNIT.SECONDS;
                        baseUnits.setActivityDuration(baseunit9.toString());
                        baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                        BASEUNIT baseunit10 = BASEUNIT.CENTIMETERS;
                        baseUnits.setUserHeight(baseunit10.toString());
                        baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                        baseUnits.setStrokeLength(baseunit10.toString());
                        baseUnits.setSpeed(BASEUNIT.KM_PER_HOUR.toString());
                        baseUnits.setSampleRate(baseunit9.toString());
                        baseUnits.setDistance(baseunit10.toString());
                        baseUnits.setHrZoneDuration(baseunit9.toString());
                    } else if (Intrinsics.areEqual(str, ActivityType.CYCLE.toString())) {
                        BASEUNIT baseunit11 = BASEUNIT.SECONDS;
                        baseUnits.setActivityDuration(baseunit11.toString());
                        baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                        BASEUNIT baseunit12 = BASEUNIT.CENTIMETERS;
                        baseUnits.setUserHeight(baseunit12.toString());
                        baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                        baseUnits.setSpeed(BASEUNIT.KM_PER_HOUR.toString());
                        baseUnits.setCadence(BASEUNIT.REV_PER_MINUTE.toString());
                        baseUnits.setPower(BASEUNIT.WATT.toString());
                        baseUnits.setSampleRate(baseunit11.toString());
                        baseUnits.setDistance(baseunit12.toString());
                        baseUnits.setHrZoneDuration(baseunit11.toString());
                    } else if (Intrinsics.areEqual(str, ActivityType.TENNIS.toString())) {
                        BASEUNIT baseunit13 = BASEUNIT.SECONDS;
                        baseUnits.setActivityDuration(baseunit13.toString());
                        baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                        BASEUNIT baseunit14 = BASEUNIT.CENTIMETERS;
                        baseUnits.setUserHeight(baseunit14.toString());
                        baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                        baseUnits.setDistance(baseunit14.toString());
                        baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                        baseUnits.setStrideLength(baseunit14.toString());
                        baseUnits.setHrZoneDuration(baseunit13.toString());
                    } else if (Intrinsics.areEqual(str, ActivityType.BADMINTON.toString())) {
                        BASEUNIT baseunit15 = BASEUNIT.SECONDS;
                        baseUnits.setActivityDuration(baseunit15.toString());
                        baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                        BASEUNIT baseunit16 = BASEUNIT.CENTIMETERS;
                        baseUnits.setUserHeight(baseunit16.toString());
                        baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                        baseUnits.setDistance(baseunit16.toString());
                        baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                        baseUnits.setStrideLength(baseunit16.toString());
                        baseUnits.setHrZoneDuration(baseunit15.toString());
                    } else if (Intrinsics.areEqual(str, ActivityType.FREE_EXERCISE.toString())) {
                        BASEUNIT baseunit17 = BASEUNIT.SECONDS;
                        baseUnits.setActivityDuration(baseunit17.toString());
                        baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                        BASEUNIT baseunit18 = BASEUNIT.CENTIMETERS;
                        baseUnits.setUserHeight(baseunit18.toString());
                        baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                        baseUnits.setDistance(baseunit18.toString());
                        baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                        baseUnits.setStrideLength(baseunit18.toString());
                        baseUnits.setHrZoneDuration(baseunit17.toString());
                    } else if (Intrinsics.areEqual(str, ActivityType.DANCE.toString())) {
                        BASEUNIT baseunit19 = BASEUNIT.SECONDS;
                        baseUnits.setActivityDuration(baseunit19.toString());
                        baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                        BASEUNIT baseunit20 = BASEUNIT.CENTIMETERS;
                        baseUnits.setUserHeight(baseunit20.toString());
                        baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                        baseUnits.setDistance(baseunit20.toString());
                        baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                        baseUnits.setStrideLength(baseunit20.toString());
                        baseUnits.setHrZoneDuration(baseunit19.toString());
                    } else if (Intrinsics.areEqual(str, ActivityType.YOGA.toString())) {
                        BASEUNIT baseunit21 = BASEUNIT.SECONDS;
                        baseUnits.setActivityDuration(baseunit21.toString());
                        baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                        BASEUNIT baseunit22 = BASEUNIT.CENTIMETERS;
                        baseUnits.setUserHeight(baseunit22.toString());
                        baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                        baseUnits.setDistance(baseunit22.toString());
                        baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                        baseUnits.setStrideLength(baseunit22.toString());
                        baseUnits.setHrZoneDuration(baseunit21.toString());
                    } else if (Intrinsics.areEqual(str, ActivityType.MEDITATION.toString())) {
                        BASEUNIT baseunit23 = BASEUNIT.SECONDS;
                        baseUnits.setActivityDuration(baseunit23.toString());
                        baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                        BASEUNIT baseunit24 = BASEUNIT.CENTIMETERS;
                        baseUnits.setUserHeight(baseunit24.toString());
                        baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                        baseUnits.setDistance(baseunit24.toString());
                        baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                        baseUnits.setStrideLength(baseunit24.toString());
                        baseUnits.setHrZoneDuration(baseunit23.toString());
                    } else if (Intrinsics.areEqual(str, ActivityType.WORKOUT.toString())) {
                        BASEUNIT baseunit25 = BASEUNIT.SECONDS;
                        baseUnits.setActivityDuration(baseunit25.toString());
                        baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                        BASEUNIT baseunit26 = BASEUNIT.CENTIMETERS;
                        baseUnits.setUserHeight(baseunit26.toString());
                        baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                        baseUnits.setDistance(baseunit26.toString());
                        baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                        baseUnits.setStrideLength(baseunit26.toString());
                        baseUnits.setHrZoneDuration(baseunit25.toString());
                    } else {
                        if (Intrinsics.areEqual(str, ActivityType.FOOTBALL.toString()) ? true : Intrinsics.areEqual(str, ActivityType.ELLIPTICAL.toString()) ? true : Intrinsics.areEqual(str, ActivityType.ROWING.toString())) {
                            BASEUNIT baseunit27 = BASEUNIT.SECONDS;
                            baseUnits.setActivityDuration(baseunit27.toString());
                            baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                            BASEUNIT baseunit28 = BASEUNIT.CENTIMETERS;
                            baseUnits.setUserHeight(baseunit28.toString());
                            baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                            baseUnits.setDistance(baseunit28.toString());
                            baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                            baseUnits.setStrideLength(baseunit28.toString());
                            baseUnits.setHrZoneDuration(baseunit27.toString());
                        } else if (Intrinsics.areEqual(str, ActivityType.BASKETBALL.toString())) {
                            BASEUNIT baseunit29 = BASEUNIT.SECONDS;
                            baseUnits.setActivityDuration(baseunit29.toString());
                            baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                            BASEUNIT baseunit30 = BASEUNIT.CENTIMETERS;
                            baseUnits.setUserHeight(baseunit30.toString());
                            baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                            baseUnits.setDistance(baseunit30.toString());
                            baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                            baseUnits.setStrideLength(baseunit30.toString());
                            baseUnits.setHrZoneDuration(baseunit29.toString());
                        } else {
                            if (Intrinsics.areEqual(str, ActivityType.HIKING.toString()) ? true : Intrinsics.areEqual(str, ActivityMode.CLIMBING.toString())) {
                                BASEUNIT baseunit31 = BASEUNIT.SECONDS;
                                baseUnits.setActivityDuration(baseunit31.toString());
                                baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                                BASEUNIT baseunit32 = BASEUNIT.CENTIMETERS;
                                baseUnits.setUserHeight(baseunit32.toString());
                                baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                                baseUnits.setDistance(baseunit32.toString());
                                baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                                baseUnits.setStrideLength(baseunit32.toString());
                                baseUnits.setHrZoneDuration(baseunit31.toString());
                            } else if (Intrinsics.areEqual(str, ActivityType.SKIPPING.toString())) {
                                BASEUNIT baseunit33 = BASEUNIT.SECONDS;
                                baseUnits.setActivityDuration(baseunit33.toString());
                                baseUnits.setUserAge(BASEUNIT.YEARS.toString());
                                BASEUNIT baseunit34 = BASEUNIT.CENTIMETERS;
                                baseUnits.setUserHeight(baseunit34.toString());
                                baseUnits.setUserWeight(BASEUNIT.KILOGRAMS.toString());
                                baseUnits.setDistance(baseunit34.toString());
                                baseUnits.setPace(BASEUNIT.SEC_PER_KM.toString());
                                baseUnits.setStrideLength(baseunit34.toString());
                                baseUnits.setHrZoneDuration(baseunit33.toString());
                            }
                        }
                    }
                }
            }
            return baseUnits;
        }

        public final Object f(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super BasketBallActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new e(entityWorkoutSession, list, context, null), continuation);
        }

        public final String g(EntityWorkoutSession entityWorkoutSession, int i2, Context context) {
            String activity_type = entityWorkoutSession.getActivity_type();
            if (kotlin.text.m.equals$default(entityWorkoutSession.getActivity_type(), "PHYSICAL_ACTIVITY", false, 2, null)) {
                activity_type = b(entityWorkoutSession.getCategoryId(), entityWorkoutSession.getActivityId(), context);
            }
            String str = PreferenceManager.getInstance().getUserId().intValue() + ';' + PreferenceManager.getInstance().getUserDeviceID() + ';' + activity_type + ';' + AppUtils.formatDateUTC(new Date(entityWorkoutSession.getStart_time()), UtilConstants.SERVER_TIME_FORMAT2) + ';' + AppUtils.formatDateUTC(new Date(entityWorkoutSession.getEnd_time()), UtilConstants.SERVER_TIME_FORMAT2) + ';' + i2;
            Log.d("FormatorServerToEntity", "clientRefString: " + str);
            return AppUtils.convertStringToMD5(str);
        }

        @Nullable
        public final Object getActivitySampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<ActivityDataSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new b(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getBadmintonSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<BadmintonSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new d(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getBasketBallSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<BasketBallSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new f(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getClimbingSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<ClimbingSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new h(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getCyclingSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<CyclingSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new j(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getDanceSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<DanceSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new l(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getEllipticalSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<EllipticalSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new n(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getFootBallSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<FootballSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new p(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getFreeExerciseSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<FreeExerciseSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new r(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getHikingSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<HikingSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new t(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getMeditationSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<MeditationSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new v(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getPhysicalActivitySampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<PhysicalActivitySample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new x(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @JvmStatic
        @Nullable
        public final Object getPostActivitySessionDataRequest(@NotNull EntityWorkoutSession entityWorkoutSession, @NotNull Context context, @NotNull Continuation<? super PostActivitySessionDataRequest> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new y(entityWorkoutSession, context, null), continuation);
        }

        @Nullable
        public final Object getPreparationPlan(@NotNull SCurrentPreparationPlanRes sCurrentPreparationPlanRes, @NotNull Continuation<? super PlanDataHolder> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new z(sCurrentPreparationPlanRes, null), continuation);
        }

        @Nullable
        public final Object getRowingMachineSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<RowingMachineSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new b0(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getRunSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<RunSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new d0(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getSegmentsList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<EntityWorkoutSessionSegment>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new e0(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getSkippingSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<SkippingSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new g0(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getTennisSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<TennisSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new i0(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getTreadmillSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<TreadmillSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new k0(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getWalkSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<WalkSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new m0(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getWorkoutSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<WorkoutSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new o0(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        @Nullable
        public final Object getWorkoutSessionEntity(@NotNull Context context, @NotNull PostActivitySessionDataRequest postActivitySessionDataRequest, @Nullable String str, @NotNull Continuation<? super EntityWorkoutSession> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new p0(context, postActivitySessionDataRequest, str, null), continuation);
        }

        @Nullable
        public final Object getYogaSampleList(@NotNull EntityWorkoutSession entityWorkoutSession, @Nullable PostActivitySessionDataRequest postActivitySessionDataRequest, @NotNull Continuation<? super List<YogaSample>> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new r0(postActivitySessionDataRequest, entityWorkoutSession, null), continuation);
        }

        public final Object h(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super ClimbingActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new g(entityWorkoutSession, list, context, null), continuation);
        }

        public final int i(TraqActivityLogs traqActivityLogs) {
            int size = (traqActivityLogs.getStepValues() == null || traqActivityLogs.getStepValues().size() <= 0) ? 0 : traqActivityLogs.getStepValues().size();
            if (traqActivityLogs.getHrValues() != null && traqActivityLogs.getHrValues().size() > 0 && size < traqActivityLogs.getHrValues().size()) {
                size = traqActivityLogs.getHrValues().size();
            }
            return (traqActivityLogs.getCoordinateValues() == null || traqActivityLogs.getCoordinateValues().size() <= 0 || size >= traqActivityLogs.getCoordinateValues().size()) ? size : traqActivityLogs.getCoordinateValues().size();
        }

        public final Object j(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super CycleActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new i(entityWorkoutSession, list, context, null), continuation);
        }

        public final Object k(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super DanceActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new k(entityWorkoutSession, list, context, null), continuation);
        }

        public final DeviceSpecificParams l(Context context, EntityWorkoutSession entityWorkoutSession, String str) {
            DeviceSpecificParams deviceSpecificParams = new DeviceSpecificParams();
            if (DeviceUtils.Companion.isIDODevice(context)) {
                deviceSpecificParams.setVersion(0);
                deviceSpecificParams.setActivityDataItemCount(0);
                deviceSpecificParams.setDataLength(0);
                deviceSpecificParams.setPacketCount(0);
                deviceSpecificParams.setFrequencyCount(0);
                deviceSpecificParams.setHrItemCount(0);
                deviceSpecificParams.setKmSpeedCount(0);
            } else {
                if (Intrinsics.areEqual(str, ActivityType.RUN.toString()) ? true : Intrinsics.areEqual(str, ActivityMode.TREADMILL.toString())) {
                    deviceSpecificParams.setMagicCode(101L);
                    deviceSpecificParams.setAvgHrCount(90);
                    deviceSpecificParams.setAvgHrTime(202);
                    deviceSpecificParams.setAvgSpeedCount(34);
                    deviceSpecificParams.setAvgSpeedTime(89);
                } else if (Intrinsics.areEqual(str, ActivityType.WALK.toString())) {
                    deviceSpecificParams.setMagicCode(101L);
                    deviceSpecificParams.setAvgHrCount(90);
                    deviceSpecificParams.setAvgHrTime(202);
                    deviceSpecificParams.setAvgSpeedCount(34);
                    deviceSpecificParams.setAvgSpeedTime(89);
                } else {
                    ActivityType activityType = ActivityType.CYCLE;
                    if (Intrinsics.areEqual(str, activityType.toString())) {
                        deviceSpecificParams.setMagicCode(101L);
                        deviceSpecificParams.setAvgCadenceCount(40);
                        deviceSpecificParams.setAvgCadenceTime(123);
                        deviceSpecificParams.setAvgHrCount(90);
                        deviceSpecificParams.setAvgHrTime(202);
                        deviceSpecificParams.setAvgPowerCount(98);
                        deviceSpecificParams.setAvgPowerTime(90);
                        deviceSpecificParams.setAvgSpeedCount(34);
                        deviceSpecificParams.setAvgSpeedTime(89);
                    } else if (Intrinsics.areEqual(str, activityType.toString())) {
                        deviceSpecificParams.setMagicCode(101L);
                        deviceSpecificParams.setAvgCadenceCount(40);
                        deviceSpecificParams.setAvgCadenceTime(123);
                        deviceSpecificParams.setAvgHrCount(90);
                        deviceSpecificParams.setAvgHrTime(202);
                        deviceSpecificParams.setAvgPowerCount(98);
                        deviceSpecificParams.setAvgPowerTime(90);
                        deviceSpecificParams.setAvgSpeedCount(34);
                        deviceSpecificParams.setAvgSpeedTime(89);
                    } else if (Intrinsics.areEqual(str, ActivityType.SWIM.toString())) {
                        deviceSpecificParams.setMagicCode(101L);
                        deviceSpecificParams.setAvgHrCount(90);
                        deviceSpecificParams.setAvgHrTime(202);
                        deviceSpecificParams.setAvgSpeedCount(34);
                        deviceSpecificParams.setAvgSpeedTime(89);
                    }
                }
            }
            return deviceSpecificParams;
        }

        public final Object m(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super EllipticalActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new m(entityWorkoutSession, list, context, null), continuation);
        }

        public final Object n(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super FootBallActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new o(entityWorkoutSession, list, context, null), continuation);
        }

        public final Object o(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super FreeExerciseActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new q(entityWorkoutSession, list, context, null), continuation);
        }

        public final Object p(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super HikingActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new s(entityWorkoutSession, list, context, null), continuation);
        }

        public final ArrayList<EntityPreparationActivity> q(ArrayList<EntityPreparationActivity> arrayList) {
            ArrayList<EntityPreparationActivity> arrayList2 = new ArrayList<>();
            EntityPreparationActivity entityPreparationActivity = new EntityPreparationActivity();
            entityPreparationActivity.setActivityCode(ActivityCode.WALK.getActivityType());
            entityPreparationActivity.setTitle(WorkoutConstants.WALK);
            arrayList2.add(entityPreparationActivity);
            EntityPreparationActivity entityPreparationActivity2 = new EntityPreparationActivity();
            entityPreparationActivity2.setActivityCode(ActivityCode.LIGHT_RUN.getActivityType());
            entityPreparationActivity2.setTitle(WorkoutConstants.LIGHT_RUN);
            arrayList2.add(entityPreparationActivity2);
            EntityPreparationActivity entityPreparationActivity3 = new EntityPreparationActivity();
            entityPreparationActivity3.setActivityCode(ActivityCode.RUN.getActivityType());
            entityPreparationActivity3.setTitle(WorkoutConstants.RUN);
            arrayList2.add(entityPreparationActivity3);
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (arrayList2.contains(arrayList.get(i2))) {
                    arrayList2.set(arrayList2.indexOf(arrayList.get(i2)), arrayList.get(i2));
                }
            }
            return arrayList2;
        }

        public final Object r(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super MeditationActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new u(entityWorkoutSession, list, context, null), continuation);
        }

        public final OtherParams s(Context context) {
            OtherParams otherParams = new OtherParams();
            EntityProfile theLatestProfileData = CoveAppDatabase.getAppDatabase(context).daoProfile().getTheLatestProfileData();
            otherParams.setUserAge(theLatestProfileData.getAge() == 0 ? 50 : Integer.valueOf(theLatestProfileData.getAge()));
            otherParams.setUserGender(theLatestProfileData.gender == 0 ? "MALE" : "FEMALE");
            otherParams.setUserHeight(Integer.valueOf(theLatestProfileData.height));
            otherParams.setUserWeight(Integer.valueOf(kotlin.math.c.roundToInt(theLatestProfileData.weight)));
            otherParams.setUserMaxHr(Integer.valueOf(220 - theLatestProfileData.getAge()));
            int i2 = theLatestProfileData.restingHr;
            if (i2 > 0) {
                otherParams.setUserRestHr(Integer.valueOf(i2));
            } else {
                otherParams.setUserRestHr(50);
            }
            otherParams.setUserRunningStrideLength(Integer.valueOf(AppUtils.caluclateRunningStrideLenght(theLatestProfileData.height)));
            otherParams.setUserSwimmingStrokeLength(1);
            otherParams.setUserWalkingStrideLength(Integer.valueOf(AppUtils.caluclateWalkingStrideLenght(theLatestProfileData.height)));
            return otherParams;
        }

        public final Object t(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super PhysicalActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new w(entityWorkoutSession, list, context, null), continuation);
        }

        public final Object u(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super RowingMachineActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new a0(entityWorkoutSession, list, context, null), continuation);
        }

        public final Object v(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super RunWalkActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new c0(entityWorkoutSession, list, context, null), continuation);
        }

        public final SampleData w(TraqActivityLogs traqActivityLogs, int i2, SampleData sampleData) {
            if (traqActivityLogs.getCalorieValues() != null && traqActivityLogs.getCalorieValues().size() > 0 && i2 < traqActivityLogs.getCalorieValues().size()) {
                Float f2 = traqActivityLogs.getCalorieValues().get(i2);
                Intrinsics.checkNotNullExpressionValue(f2, "log.calorieValues[i]");
                sampleData.setCalories(f2.floatValue());
            }
            if (traqActivityLogs.getDistanceValues() != null && traqActivityLogs.getDistanceValues().size() > 0 && i2 < traqActivityLogs.getDistanceValues().size()) {
                Integer num = traqActivityLogs.getDistanceValues().get(i2);
                Intrinsics.checkNotNullExpressionValue(num, "log.distanceValues[i]");
                sampleData.setDistance(num.intValue());
            }
            if (traqActivityLogs.getHrValues() != null && traqActivityLogs.getHrValues().size() > 0 && i2 < traqActivityLogs.getHrValues().size()) {
                Integer num2 = traqActivityLogs.getHrValues().get(i2);
                Intrinsics.checkNotNullExpressionValue(num2, "log.hrValues[i]");
                sampleData.setHr_value(num2.intValue());
            }
            if (traqActivityLogs.getCoordinateValues() != null && traqActivityLogs.getCoordinateValues().size() > 0 && i2 < traqActivityLogs.getCoordinateValues().size()) {
                Double d2 = traqActivityLogs.getCoordinateValues().get(i2).get(0);
                Intrinsics.checkNotNullExpressionValue(d2, "log.coordinateValues[i][0]");
                sampleData.setLatitude(d2.doubleValue());
                Double d3 = traqActivityLogs.getCoordinateValues().get(i2).get(1);
                Intrinsics.checkNotNullExpressionValue(d3, "log.coordinateValues[i][1]");
                sampleData.setLongitude(d3.doubleValue());
            }
            if (traqActivityLogs.getSpeedValues() != null && traqActivityLogs.getSpeedValues().size() > 0 && i2 < traqActivityLogs.getSpeedValues().size()) {
                Float f3 = traqActivityLogs.getSpeedValues().get(i2);
                Intrinsics.checkNotNullExpressionValue(f3, "log.speedValues[i]");
                sampleData.setSpeed_value(f3.floatValue());
            }
            return sampleData;
        }

        public final int x(List<? extends TraqActivityLogs> list, int i2) {
            int i3 = 0;
            if (list != null) {
                for (TraqActivityLogs traqActivityLogs : list) {
                    i3 += (traqActivityLogs.getStepValues() != null ? traqActivityLogs.getStepValues() : traqActivityLogs.getHrValues()).size() * i2;
                }
            }
            return i3;
        }

        public final HashMap<String, List<TraqActivityLogs>> y(List<? extends TraqActivityLogs> list) {
            new ArrayList();
            Collections.sort(list, new LogsComparator());
            HashMap<String, List<TraqActivityLogs>> hashMap = new HashMap<>();
            for (TraqActivityLogs traqActivityLogs : list) {
                if (traqActivityLogs.getSegmentId() == null) {
                    traqActivityLogs.setSegmentId(String.valueOf(AppUtils.parseDateUTC(traqActivityLogs.getStartTime(), UtilConstants.SERVER_TIME_FORMAT).getTime() / 1000));
                }
                if (hashMap.containsKey(traqActivityLogs.getSegmentId())) {
                    List<TraqActivityLogs> list2 = hashMap.get(traqActivityLogs.getSegmentId());
                    Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.coveaccess.activitysession.TraqActivityLogs> }");
                    ((ArrayList) list2).add(traqActivityLogs);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(traqActivityLogs);
                    hashMap.put(traqActivityLogs.getSegmentId(), arrayList);
                }
            }
            return hashMap;
        }

        public final Object z(EntityWorkoutSession entityWorkoutSession, List<? extends Sample> list, Context context, Continuation<? super SkippingActivityDetails> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new f0(entityWorkoutSession, list, context, null), continuation);
        }
    }

    @JvmStatic
    @Nullable
    public static final Object getPostActivitySessionDataRequest(@NotNull EntityWorkoutSession entityWorkoutSession, @NotNull Context context, @NotNull Continuation<? super PostActivitySessionDataRequest> continuation) {
        return Companion.getPostActivitySessionDataRequest(entityWorkoutSession, context, continuation);
    }
}
