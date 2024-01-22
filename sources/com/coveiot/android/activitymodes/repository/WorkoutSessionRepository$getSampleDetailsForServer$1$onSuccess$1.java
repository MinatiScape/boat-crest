package com.coveiot.android.activitymodes.repository;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.coveiot.coveaccess.activitysession.PostActivitySessionHeaderResponse;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.goodix.ble.libcomx.task.ITaskSet;
import com.goodix.ble.libcomx.task.TaskPipe;
import com.touchgui.sdk.TGEventListener;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSampleDetailsForServer$1$onSuccess$1", f = "WorkoutSessionRepository.kt", i = {1, 2, 3}, l = {679, 688, 690, 691, 698, TypedValues.TransitionType.TYPE_TO, 708, 709, 713, 714, 718, 719, 723, 727, 731, 735, 739, 743, 749, 750, 757, TaskPipe.EVT_BUSY, 765, TGEventListener.ALARM_UPDATED, 773, 774, 778, 782, 786, 790, 795, 799, 803, 807, 811, 815, 819, ITaskSet.EVT_SUBTASK_FINISH, 828, 832, 837, 841, 846, 850}, m = "invokeSuspend", n = {"workoutSession", "workoutSession", "workoutSession"}, s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class WorkoutSessionRepository$getSampleDetailsForServer$1$onSuccess$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ PostActivitySessionHeaderResponse $p0;
    public final /* synthetic */ ActivityType $sessionType;
    public final /* synthetic */ String $session_id;
    public Object L$0;
    public int label;
    public final /* synthetic */ WorkoutSessionRepository this$0;

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ActivityType.values().length];
            try {
                iArr[ActivityType.WALK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ActivityType.RUN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ActivityType.DANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ActivityType.HIKING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ActivityType.TENNIS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ActivityType.BADMINTON.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ActivityType.FREE_EXERCISE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ActivityType.BASKETBALL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ActivityType.FOOTBALL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ActivityType.YOGA.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ActivityType.WORKOUT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ActivityType.CYCLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ActivityType.MEDITATION.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[ActivityType.CLIMBING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[ActivityType.TREADMILL.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[ActivityType.SKIPPING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[ActivityType.ELLIPTICAL.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[ActivityType.ROWING.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[ActivityType.PHYSICAL_ACTIVITY.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WorkoutSessionRepository$getSampleDetailsForServer$1$onSuccess$1(WorkoutSessionRepository workoutSessionRepository, PostActivitySessionHeaderResponse postActivitySessionHeaderResponse, String str, ActivityType activityType, Continuation<? super WorkoutSessionRepository$getSampleDetailsForServer$1$onSuccess$1> continuation) {
        super(2, continuation);
        this.this$0 = workoutSessionRepository;
        this.$p0 = postActivitySessionHeaderResponse;
        this.$session_id = str;
        this.$sessionType = activityType;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WorkoutSessionRepository$getSampleDetailsForServer$1$onSuccess$1(this.this$0, this.$p0, this.$session_id, this.$sessionType, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WorkoutSessionRepository$getSampleDetailsForServer$1$onSuccess$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x02af A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02d8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0301 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x032a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0353 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x037c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x03aa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03d3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x03d4  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03fc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0425 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x044e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0477 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x049f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00fd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x010f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0126 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0181 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01b9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01e2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x020b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0234 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x025d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0286 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0287  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
        /*
            Method dump skipped, instructions count: 1324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$getSampleDetailsForServer$1$onSuccess$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
