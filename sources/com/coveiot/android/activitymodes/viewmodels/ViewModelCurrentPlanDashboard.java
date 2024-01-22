package com.coveiot.android.activitymodes.viewmodels;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationActivity;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.PlanStatus;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.covepreferences.UserDataManager;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewModelCurrentPlanDashboard extends AndroidViewModel {
    @NotNull
    public Context d;
    @NotNull
    public MutableLiveData<PlanStatus> e;
    @NotNull
    public MutableLiveData<Pair<EntityPreparationDay, EntityPreparationWeek>> f;
    @NotNull
    public MutableLiveData<EntityPreparationPlan> g;

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$checkIfPlanOngoingOrFinished$1", f = "ViewModelCurrentPlanDashboard.kt", i = {0, 1, 1, 1, 1}, l = {54, 60}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "curDate", "curDateStr", "planStartDate"}, s = {"L$0", "L$0", "L$2", "L$3", "L$4"})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(continuation);
            aVar.L$0 = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x009b  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00a5  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00f3  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
            /*
                Method dump skipped, instructions count: 255
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard", f = "ViewModelCurrentPlanDashboard.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3}, l = {256, 259, DfuException.ERROR_CONNECT_ERROR, DfuException.ERROR_READ_APP_INFO_ERROR}, m = "getActivitiesCompletedCount", n = {"this", "activityCompletedCount", "activityCompletedDistance", "this", "activityCompletedCount", "activityCompletedDistance", "plan", "planStartDate", "this", "activityCompletedCount", "activityCompletedDistance", "this", "activityCompletedCount", "activityCompletedDistance"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
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
            return ViewModelCurrentPlanDashboard.this.getActivitiesCompletedCount(this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard", f = "ViewModelCurrentPlanDashboard.kt", i = {0, 0, 0, 0, 1, 1, 1, 2, 2}, l = {300, 304, 310}, m = "getActivitiesCompletedCountForToday", n = {"this", "activityCompletedCount", "isUpcomingPlan", "activityCompletedDistance", "this", "activityCompletedCount", "activityCompletedDistance", "activityCompletedCount", "activityCompletedDistance"}, s = {"L$0", "L$1", "Z$0", "I$0", "L$0", "L$1", "I$0", "L$0", "I$0"})
    /* loaded from: classes2.dex */
    public static final class c extends ContinuationImpl {
        public int I$0;
        public Object L$0;
        public Object L$1;
        public boolean Z$0;
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
            return ViewModelCurrentPlanDashboard.this.getActivitiesCompletedCountForToday(false, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard", f = "ViewModelCurrentPlanDashboard.kt", i = {}, l = {116}, m = "getCurrentDayPlanData", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d extends ContinuationImpl {
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
            return ViewModelCurrentPlanDashboard.this.getCurrentDayPlanData(false, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard", f = "ViewModelCurrentPlanDashboard.kt", i = {0}, l = {121}, m = "getCurrentPlan", n = {"this"}, s = {"L$0"})
    /* loaded from: classes2.dex */
    public static final class e extends ContinuationImpl {
        public Object L$0;
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
            return ViewModelCurrentPlanDashboard.this.getCurrentPlan(this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard", f = "ViewModelCurrentPlanDashboard.kt", i = {}, l = {198}, m = "getDayNumber", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class f extends ContinuationImpl {
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
            return ViewModelCurrentPlanDashboard.this.getDayNumber(this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard", f = "ViewModelCurrentPlanDashboard.kt", i = {0, 1}, l = {CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 191}, m = "getDayProgress", n = {"this", "planDurationInDays"}, s = {"L$0", "I$0"})
    /* loaded from: classes2.dex */
    public static final class g extends ContinuationImpl {
        public int I$0;
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
            return ViewModelCurrentPlanDashboard.this.getDayProgress(this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard", f = "ViewModelCurrentPlanDashboard.kt", i = {0, 1, 1}, l = {103, 106}, m = "getOngoingPlanData", n = {"this", "this", "entityPreparationDay"}, s = {"L$0", "L$0", "L$1"})
    /* loaded from: classes2.dex */
    public static final class h extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
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
            return ViewModelCurrentPlanDashboard.this.getOngoingPlanData(false, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard", f = "ViewModelCurrentPlanDashboard.kt", i = {0, 1}, l = {176, 180}, m = "getPlanDurationInDays", n = {"this", "planStartDate"}, s = {"L$0", "L$0"})
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
            return ViewModelCurrentPlanDashboard.this.getPlanDurationInDays(this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard", f = "ViewModelCurrentPlanDashboard.kt", i = {0, 0, 0, 1, 1, 1}, l = {Command.CMD_RECEIVE_SPEECH_CANCEL, Command.CMD_GET_LOW_LATENCY_SETTINGS}, m = "getTotalActivitiesAndDistance", n = {"this", "totalActivities", "totalDistance", "this", "totalActivities", "totalDistance"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
    /* loaded from: classes2.dex */
    public static final class j extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
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
            return ViewModelCurrentPlanDashboard.this.getTotalActivitiesAndDistance(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewModelCurrentPlanDashboard(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.d = application;
        this.e = new MutableLiveData<>();
    }

    public final void checkIfPlanOngoingOrFinished() {
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ee A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0163 A[LOOP:0: B:60:0x015d->B:62:0x0163, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x017a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x0153 -> B:59:0x0156). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getActivitiesCompletedCount(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<java.lang.Integer, java.lang.Integer>> r15) {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.getActivitiesCompletedCount(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f3  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getActivitiesCompletedCountForToday(boolean r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<java.lang.Integer, java.lang.Integer>> r13) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.getActivitiesCompletedCountForToday(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final String getActivitiesString(@NotNull EntityPreparationDay entityPreparationDay) {
        WorkoutUtils workoutUtils;
        String target;
        WorkoutUtils workoutUtils2;
        String target2;
        Intrinsics.checkNotNullParameter(entityPreparationDay, "entityPreparationDay");
        StringBuilder sb = new StringBuilder();
        if (WorkoutUtils.INSTANCE.getActivitiesTotalTarget(entityPreparationDay.getActivities()) > 0) {
            int i2 = 0;
            for (Object obj : entityPreparationDay.getActivities()) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                EntityPreparationActivity entityPreparationActivity = (EntityPreparationActivity) obj;
                if (i2 != entityPreparationDay.getActivities().size() - 1) {
                    if (entityPreparationActivity.getActivityType() != null) {
                        if (!UserDataManager.getInstance(this.d).isDistanceUnitInMile().booleanValue()) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(entityPreparationActivity.getActivityType());
                            sb2.append(" for ");
                            WorkoutUtils workoutUtils3 = WorkoutUtils.INSTANCE;
                            String target3 = entityPreparationActivity.getTarget();
                            sb2.append(workoutUtils3.convertMetersToKmFloat(target3 != null ? Integer.parseInt(target3) : 0));
                            sb2.append(' ');
                            sb2.append(this.d.getResources().getString(R.string.km));
                            sb.append(sb2.toString());
                            sb.append("\n");
                        } else {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(entityPreparationActivity.getActivityType());
                            sb3.append(" for ");
                            sb3.append(WorkoutUtils.INSTANCE.convertKMToMiles(workoutUtils2.convertMetersToKmFloat(entityPreparationActivity.getTarget() != null ? Integer.parseInt(target2) : 0)));
                            sb3.append(' ');
                            sb3.append(this.d.getResources().getString(R.string.mil));
                            sb.append(sb3.toString());
                            sb.append("\n");
                        }
                    }
                } else if (entityPreparationActivity.getActivityType() != null) {
                    if (!UserDataManager.getInstance(this.d).isDistanceUnitInMile().booleanValue()) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(entityPreparationActivity.getActivityType());
                        sb4.append(" for ");
                        WorkoutUtils workoutUtils4 = WorkoutUtils.INSTANCE;
                        String target4 = entityPreparationActivity.getTarget();
                        sb4.append(workoutUtils4.convertMetersToKmFloat(target4 != null ? Integer.parseInt(target4) : 0));
                        sb4.append(' ');
                        sb4.append(this.d.getResources().getString(R.string.km));
                        sb.append(sb4.toString());
                    } else {
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append(entityPreparationActivity.getActivityType());
                        sb5.append(" for ");
                        sb5.append(WorkoutUtils.INSTANCE.convertKMToMiles(workoutUtils.convertMetersToKmFloat(entityPreparationActivity.getTarget() != null ? Integer.parseInt(target) : 0)));
                        sb5.append(' ');
                        sb5.append(this.d.getResources().getString(R.string.mil));
                        sb.append(sb5.toString());
                    }
                }
                i2 = i3;
            }
            StringsKt__StringsKt.removeSuffix(sb, "\n");
        } else {
            sb.append(this.d.getString(R.string.rest_day));
        }
        String sb6 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb6, "activityStr.toString()");
        return sb6;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCurrentDayPlanData(boolean r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.coveiot.android.activitymodes.database.entities.EntityPreparationDay> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.d
            if (r0 == 0) goto L13
            r0 = r6
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$d r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.d) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$d r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$d
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L56
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            if (r5 == 0) goto L3d
            com.coveiot.android.activitymodes.utils.WorkoutUtils r5 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            java.lang.String r5 = r5.getCurrentDatePlusOne()
            goto L43
        L3d:
            com.coveiot.android.activitymodes.utils.WorkoutUtils r5 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            java.lang.String r5 = r5.getCurrentDate()
        L43:
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$Companion r6 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.Companion
            android.content.Context r2 = r4.d
            java.lang.Object r6 = r6.getInstance(r2)
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository r6 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository) r6
            r0.label = r3
            java.lang.Object r6 = r6.getDayLevelInfoBasedOnDate(r5, r0)
            if (r6 != r1) goto L56
            return r1
        L56:
            com.coveiot.android.activitymodes.database.entities.EntityPreparationDay r6 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationDay) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.getCurrentDayPlanData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCurrentPlan(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.e
            if (r0 == 0) goto L13
            r0 = r5
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$e r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.e) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$e r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$e
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4e
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$Companion r5 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.Companion
            android.content.Context r2 = r4.d
            java.lang.Object r5 = r5.getInstance(r2)
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository r5 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository) r5
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.getCurrentPlan(r0)
            if (r5 != r1) goto L4d
            return r1
        L4d:
            r0 = r4
        L4e:
            com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan r5 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan) r5
            androidx.lifecycle.MutableLiveData<com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan> r0 = r0.g
            r0.postValue(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.getCurrentPlan(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final MutableLiveData<EntityPreparationPlan> getCurrentPlanLiveData() {
        return this.g;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getDayNumber(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.f
            if (r0 == 0) goto L13
            r0 = r6
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$f r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.f) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$f r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$f
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L32
            if (r2 != r4) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L3e
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.label = r4
            java.lang.Object r6 = r5.getCurrentDayPlanData(r3, r0)
            if (r6 != r1) goto L3e
            return r1
        L3e:
            com.coveiot.android.activitymodes.database.entities.EntityPreparationDay r6 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationDay) r6
            if (r6 == 0) goto L53
            int r0 = r6.getWeek_number()
            int r0 = r0 - r4
            int r0 = r0 * 7
            int r6 = r6.getDay_number()
            int r0 = r0 + r6
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            return r6
        L53:
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.getDayNumber(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getDayProgress(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Float> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.g
            if (r0 == 0) goto L13
            r0 = r8
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$g r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.g) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$g r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$g
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3e
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            int r0 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6a
        L2e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L36:
            java.lang.Object r2 = r0.L$0
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard r2 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4d
        L3e:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r8 = r7.getPlanDurationInDays(r0)
            if (r8 != r1) goto L4c
            return r1
        L4c:
            r2 = r7
        L4d:
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            float r4 = (float) r8
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L7b
            r4 = 0
            r0.L$0 = r4
            r0.I$0 = r8
            r0.label = r3
            java.lang.Object r0 = r2.getDayNumber(r0)
            if (r0 != r1) goto L67
            return r1
        L67:
            r6 = r0
            r0 = r8
            r8 = r6
        L6a:
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            float r8 = (float) r8
            float r0 = (float) r0
            float r8 = r8 / r0
            r0 = 1120403456(0x42c80000, float:100.0)
            float r8 = r8 * r0
            java.lang.Float r8 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r8)
            return r8
        L7b:
            java.lang.Float r8 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r5)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.getDayProgress(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object getDistanceByActivityNCategoryIDs(@NotNull String str, int i2, int i3, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getDistanceByActivityNCategoryIDs(str, i2, i3, continuation);
    }

    @Nullable
    public final Object getDistanceByActivityType(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getDistanceByType(str, str2, continuation);
    }

    @Nullable
    public final Object getDistanceForWeekByActivityNCategoryIDs(int i2, int i3, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getDistanceForWeekByActivityNCategoryIDs(i2, i3, str, str2, continuation);
    }

    @Nullable
    public final Object getDistanceForWeekByActivityType(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Continuation<? super Integer> continuation) {
        return WorkoutSessionRepository.Companion.getInstance(this.d).getDistanceForWeekByType(str, str2, str3, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0096 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ad  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getOngoingPlanData(boolean r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<com.coveiot.android.activitymodes.database.entities.EntityPreparationDay, com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek>> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.h
            if (r0 == 0) goto L13
            r0 = r9
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$h r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.h) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$h r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$h
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L46
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r8 = r0.L$1
            com.coveiot.android.activitymodes.database.entities.EntityPreparationDay r8 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationDay) r8
            java.lang.Object r0 = r0.L$0
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L9a
        L36:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3e:
            java.lang.Object r8 = r0.L$0
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard r8 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L70
        L46:
            kotlin.ResultKt.throwOnFailure(r9)
            if (r8 == 0) goto L52
            com.coveiot.android.activitymodes.utils.WorkoutUtils r8 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            java.lang.String r8 = r8.getCurrentDatePlusOne()
            goto L58
        L52:
            com.coveiot.android.activitymodes.utils.WorkoutUtils r8 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            java.lang.String r8 = r8.getCurrentDate()
        L58:
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$Companion r9 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.Companion
            android.content.Context r2 = r7.d
            java.lang.Object r9 = r9.getInstance(r2)
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository r9 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository) r9
            if (r9 == 0) goto L76
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r9 = r9.getDayLevelInfoBasedOnDate(r8, r0)
            if (r9 != r1) goto L6f
            return r1
        L6f:
            r8 = r7
        L70:
            com.coveiot.android.activitymodes.database.entities.EntityPreparationDay r9 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationDay) r9
            r6 = r9
            r9 = r8
            r8 = r6
            goto L78
        L76:
            r9 = r7
            r8 = r5
        L78:
            if (r8 == 0) goto L9e
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$Companion r2 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.Companion
            android.content.Context r4 = r9.d
            java.lang.Object r2 = r2.getInstance(r4)
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository r2 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository) r2
            if (r2 == 0) goto L9e
            int r4 = r8.getWeek_number()
            r0.L$0 = r9
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r0 = r2.getWeekLevelInfo(r4, r0)
            if (r0 != r1) goto L97
            return r1
        L97:
            r6 = r0
            r0 = r9
            r9 = r6
        L9a:
            com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek r9 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek) r9
            r5 = r9
            r9 = r0
        L9e:
            kotlin.Pair r0 = new kotlin.Pair
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r0.<init>(r8, r5)
            androidx.lifecycle.MutableLiveData<kotlin.Pair<com.coveiot.android.activitymodes.database.entities.EntityPreparationDay, com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek>> r8 = r9.f
            if (r8 == 0) goto Lb0
            r8.postValue(r0)
        Lb0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.getOngoingPlanData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final MutableLiveData<Pair<EntityPreparationDay, EntityPreparationWeek>> getOngoingPlanLiveData() {
        return this.f;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0087  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getPlanDurationInDays(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.i
            if (r0 == 0) goto L13
            r0 = r9
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$i r0 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.i) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$i r0 = new com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard$i
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "yyyy-MM-dd"
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L42
            if (r2 == r5) goto L3a
            if (r2 != r4) goto L32
            java.lang.Object r0 = r0.L$0
            java.util.Date r0 = (java.util.Date) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L77
        L32:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L3a:
            java.lang.Object r2 = r0.L$0
            com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard r2 = (com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L51
        L42:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r8
            r0.label = r5
            java.lang.Object r9 = r8.getCurrentPlan(r0)
            if (r9 != r1) goto L50
            return r1
        L50:
            r2 = r8
        L51:
            com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan r9 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan) r9
            if (r9 == 0) goto L87
            java.lang.String r6 = r9.getStartDate()
            java.util.Date r6 = com.coveiot.utils.utility.AppUtils.parseDate(r6, r3)
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$Companion r7 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.Companion
            android.content.Context r2 = r2.d
            java.lang.Object r2 = r7.getInstance(r2)
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository r2 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository) r2
            java.lang.String r9 = r9.getPlanId()
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r9 = r2.getPlanEndDate(r9, r0)
            if (r9 != r1) goto L76
            return r1
        L76:
            r0 = r6
        L77:
            java.lang.String r9 = (java.lang.String) r9
            java.util.Date r9 = com.coveiot.utils.utility.AppUtils.parseDate(r9, r3)
            int r9 = com.coveiot.utils.utility.AppUtils.findDateDifference(r0, r9)
            int r9 = r9 + r5
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            return r9
        L87:
            r9 = 0
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.getPlanDurationInDays(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final MutableLiveData<PlanStatus> getPlanStatusLiveData() {
        return this.e;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f1  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00b0 -> B:28:0x00b3). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getTotalActivitiesAndDistance(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<java.lang.Integer, java.lang.Integer>> r12) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard.getTotalActivitiesAndDistance(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void setPlanStatusLiveData(@NotNull MutableLiveData<PlanStatus> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }
}
