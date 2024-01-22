package com.coveiot.android.activitymodes.utils;

import android.content.Context;
import com.coveiot.android.activitymodes.SingletonHolder;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class MoyangWorkoutUtils implements WorkoutParameterInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f2875a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<MoyangWorkoutUtils, Context> {

        /* loaded from: classes2.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, MoyangWorkoutUtils> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, MoyangWorkoutUtils.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final MoyangWorkoutUtils invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new MoyangWorkoutUtils(p0);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MoyangWorkoutUtils(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2875a = context;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public void getActivitiesListFromRemoteConfig(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @NotNull
    public final Context getContext() {
        return this.f2875a;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    @Nullable
    public WorkoutImageBean getWorkoutImageBean(@NotNull ActivityMode activityMode, @Nullable String str, boolean z) {
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return null;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isAvgSPeedApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isAvgStrideLengthApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isAvgSwolfApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0041 A[RETURN, SYNTHETIC] */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isDistanceApplicable(@org.jetbrains.annotations.NotNull android.content.Context r1, @org.jetbrains.annotations.NotNull java.lang.String r2, @org.jetbrains.annotations.Nullable java.lang.Integer r3, @org.jetbrains.annotations.Nullable com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider.ScreenType r4, @org.jetbrains.annotations.Nullable java.lang.Integer r5, @org.jetbrains.annotations.Nullable java.lang.Integer r6, @org.jetbrains.annotations.Nullable java.lang.String r7) {
        /*
            r0 = this;
            java.lang.String r4 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r1 = "activityMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            if (r3 == 0) goto L43
            int r1 = r2.hashCode()
            switch(r1) {
                case -1586487546: goto L38;
                case 81515: goto L2f;
                case 2656713: goto L26;
                case 1698056461: goto L1d;
                case 2131022872: goto L14;
                default: goto L13;
            }
        L13:
            goto L43
        L14:
            java.lang.String r1 = "HIKING"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L43
            goto L41
        L1d:
            java.lang.String r1 = "CLIMBING"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L41
            goto L43
        L26:
            java.lang.String r1 = "WALK"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L41
            goto L43
        L2f:
            java.lang.String r1 = "RUN"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L41
            goto L43
        L38:
            java.lang.String r1 = "TREADMILL"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L41
            goto L43
        L41:
            r1 = 1
            goto L44
        L43:
            r1 = 0
        L44:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.MoyangWorkoutUtils.isDistanceApplicable(android.content.Context, java.lang.String, java.lang.Integer, com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider$ScreenType, java.lang.Integer, java.lang.Integer, java.lang.String):boolean");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isFastestPaceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isLapsApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isLowestPaceApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable WorkoutUiBeanProvider.ScreenType screenType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isMaxSPMApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0038 A[ORIG_RETURN, RETURN] */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isPaceApplicable(@org.jetbrains.annotations.NotNull android.content.Context r1, @org.jetbrains.annotations.NotNull java.lang.String r2, @org.jetbrains.annotations.Nullable com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider.ScreenType r3, @org.jetbrains.annotations.Nullable java.lang.Integer r4, @org.jetbrains.annotations.Nullable java.lang.Integer r5, @org.jetbrains.annotations.Nullable java.lang.String r6) {
        /*
            r0 = this;
            java.lang.String r3 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r1 = "activityMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            int r1 = r2.hashCode()
            switch(r1) {
                case -1586487546: goto L2d;
                case 81515: goto L24;
                case 2656713: goto L1b;
                case 1698056461: goto L12;
                default: goto L11;
            }
        L11:
            goto L38
        L12:
            java.lang.String r1 = "CLIMBING"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L38
            goto L36
        L1b:
            java.lang.String r1 = "WALK"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L36
            goto L38
        L24:
            java.lang.String r1 = "RUN"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L36
            goto L38
        L2d:
            java.lang.String r1 = "TREADMILL"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L36
            goto L38
        L36:
            r1 = 1
            goto L39
        L38:
            r1 = 0
        L39:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.MoyangWorkoutUtils.isPaceApplicable(android.content.Context, java.lang.String, com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider$ScreenType, java.lang.Integer, java.lang.Integer, java.lang.String):boolean");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isSPMApplicable(@NotNull Context context, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0053 A[RETURN, SYNTHETIC] */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isStepsApplicable(@org.jetbrains.annotations.NotNull android.content.Context r1, @org.jetbrains.annotations.NotNull java.lang.String r2, @org.jetbrains.annotations.Nullable java.lang.Integer r3, @org.jetbrains.annotations.Nullable com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider.ScreenType r4, @org.jetbrains.annotations.Nullable java.lang.Integer r5, @org.jetbrains.annotations.Nullable java.lang.Integer r6) {
        /*
            r0 = this;
            java.lang.String r4 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r1 = "activityMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            if (r3 == 0) goto L55
            int r1 = r2.hashCode()
            switch(r1) {
                case -1586487546: goto L4a;
                case 81515: goto L41;
                case 2656713: goto L38;
                case 780621286: goto L2f;
                case 1177295725: goto L26;
                case 1212242117: goto L1d;
                case 1698056461: goto L14;
                default: goto L13;
            }
        L13:
            goto L55
        L14:
            java.lang.String r1 = "CLIMBING"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L55
            goto L53
        L1d:
            java.lang.String r1 = "BASKETBALL"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L53
            goto L55
        L26:
            java.lang.String r1 = "FOOTBALL"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L53
            goto L55
        L2f:
            java.lang.String r1 = "BADMINTON"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L53
            goto L55
        L38:
            java.lang.String r1 = "WALK"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L53
            goto L55
        L41:
            java.lang.String r1 = "RUN"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L53
            goto L55
        L4a:
            java.lang.String r1 = "TREADMILL"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L53
            goto L55
        L53:
            r1 = 1
            goto L56
        L55:
            r1 = 0
        L56:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.MoyangWorkoutUtils.isStepsApplicable(android.content.Context, java.lang.String, java.lang.Integer, com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider$ScreenType, java.lang.Integer, java.lang.Integer):boolean");
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isStrokeFrequencyApplicable(@NotNull Context context, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isStrokeOrCountApplicable(@NotNull Context context, @NotNull String activityMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isSwimStrokeApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public boolean isTotalStrokeApplicable(@NotNull Context context, @NotNull String activityMode, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        return false;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f2875a = context;
    }
}
