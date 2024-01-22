package com.coveiot.android.activitymodes.utils;

import android.content.Context;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.SingletonHolder;
import com.coveiot.android.activitymodes.models.ActivitiesItem;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import java.util.ArrayList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class EastApexWorkoutUtils implements WorkoutParameterInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f2869a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<EastApexWorkoutUtils, Context> {

        /* loaded from: classes2.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, EastApexWorkoutUtils> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, EastApexWorkoutUtils.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final EastApexWorkoutUtils invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EastApexWorkoutUtils(p0);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EastApexWorkoutUtils(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2869a = context;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    public void getActivitiesListFromRemoteConfig(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @NotNull
    public final Context getContext() {
        return this.f2869a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4 */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    @NotNull
    public WorkoutImageBean getWorkoutImageBean(@NotNull ActivityMode activityMode, @Nullable String str, boolean z) {
        ActivitiesItem activitiesItem;
        String iconUrl;
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        ArrayList<ActivitiesItem> eastApexActivityIcons = new PreferenceManager(this.f2869a).getEastApexActivityIcons();
        Integer valueOf = Integer.valueOf(R.drawable.activity_walking_icon);
        if (!(eastApexActivityIcons == null || eastApexActivityIcons.isEmpty()) && (activitiesItem = WorkoutUtils.INSTANCE.getActivitiesItem(activityMode, str, eastApexActivityIcons)) != null) {
            if (z) {
                iconUrl = activitiesItem.getIconUrl();
                Intrinsics.checkNotNull(iconUrl);
            } else {
                iconUrl = activitiesItem.getIconUrl();
                Intrinsics.checkNotNull(iconUrl);
            }
            valueOf = iconUrl;
        }
        return new WorkoutImageBean(valueOf);
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
                case -604568430: goto L38;
                case 81515: goto L2f;
                case 2656713: goto L26;
                case 2052409245: goto L1d;
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
            java.lang.String r1 = "MOUNTAINEERING"
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
            java.lang.String r1 = "TRAIL_RUN"
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.EastApexWorkoutUtils.isDistanceApplicable(android.content.Context, java.lang.String, java.lang.Integer, com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider$ScreenType, java.lang.Integer, java.lang.Integer, java.lang.String):boolean");
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

    /* JADX WARN: Removed duplicated region for block: B:20:0x003f A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0041 A[ORIG_RETURN, RETURN] */
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
                case -604568430: goto L36;
                case 81515: goto L2d;
                case 2656713: goto L24;
                case 2052409245: goto L1b;
                case 2131022872: goto L12;
                default: goto L11;
            }
        L11:
            goto L41
        L12:
            java.lang.String r1 = "HIKING"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L41
            goto L3f
        L1b:
            java.lang.String r1 = "MOUNTAINEERING"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L3f
            goto L41
        L24:
            java.lang.String r1 = "WALK"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L3f
            goto L41
        L2d:
            java.lang.String r1 = "RUN"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L3f
            goto L41
        L36:
            java.lang.String r1 = "TRAIL_RUN"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L3f
            goto L41
        L3f:
            r1 = 1
            goto L42
        L41:
            r1 = 0
        L42:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.EastApexWorkoutUtils.isPaceApplicable(android.content.Context, java.lang.String, com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider$ScreenType, java.lang.Integer, java.lang.Integer, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0076 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0078 A[ORIG_RETURN, RETURN] */
    @Override // com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isSPMApplicable(@org.jetbrains.annotations.NotNull android.content.Context r2, @org.jetbrains.annotations.NotNull java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r2 = "activityMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            int r2 = r3.hashCode()
            switch(r2) {
                case -1871754296: goto L6d;
                case -1586487546: goto L64;
                case -1175571791: goto L5b;
                case -1025285387: goto L52;
                case -604568430: goto L49;
                case 81515: goto L40;
                case 2656713: goto L37;
                case 1440891949: goto L2e;
                case 1959274292: goto L25;
                case 2052409245: goto L1c;
                case 2131022872: goto L13;
                default: goto L11;
            }
        L11:
            goto L78
        L13:
            java.lang.String r2 = "HIKING"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L76
            goto L78
        L1c:
            java.lang.String r2 = "MOUNTAINEERING"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L76
            goto L78
        L25:
            java.lang.String r2 = "CLIMB_STAIRS"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L76
            goto L78
        L2e:
            java.lang.String r2 = "ELLIPTICAL"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L78
            goto L76
        L37:
            java.lang.String r2 = "WALK"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L76
            goto L78
        L40:
            java.lang.String r2 = "RUN"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L76
            goto L78
        L49:
            java.lang.String r2 = "TRAIL_RUN"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L76
            goto L78
        L52:
            java.lang.String r2 = "CLIMBING_MACHINE"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L76
            goto L78
        L5b:
            java.lang.String r2 = "STEPPER"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L76
            goto L78
        L64:
            java.lang.String r2 = "TREADMILL"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L76
            goto L78
        L6d:
            java.lang.String r2 = "ROWING"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L76
            goto L78
        L76:
            r2 = 1
            goto L79
        L78:
            r2 = 0
        L79:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.EastApexWorkoutUtils.isSPMApplicable(android.content.Context, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0078 A[RETURN, SYNTHETIC] */
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
            if (r3 == 0) goto L7a
            int r1 = r2.hashCode()
            switch(r1) {
                case -1871754296: goto L6f;
                case -1586487546: goto L66;
                case -1175571791: goto L5d;
                case -1025285387: goto L54;
                case -604568430: goto L4b;
                case 81515: goto L42;
                case 2656713: goto L39;
                case 1440891949: goto L30;
                case 1959274292: goto L27;
                case 2052409245: goto L1e;
                case 2131022872: goto L15;
                default: goto L13;
            }
        L13:
            goto L7a
        L15:
            java.lang.String r1 = "HIKING"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L78
            goto L7a
        L1e:
            java.lang.String r1 = "MOUNTAINEERING"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L78
            goto L7a
        L27:
            java.lang.String r1 = "CLIMB_STAIRS"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L78
            goto L7a
        L30:
            java.lang.String r1 = "ELLIPTICAL"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L78
            goto L7a
        L39:
            java.lang.String r1 = "WALK"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L78
            goto L7a
        L42:
            java.lang.String r1 = "RUN"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L78
            goto L7a
        L4b:
            java.lang.String r1 = "TRAIL_RUN"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L78
            goto L7a
        L54:
            java.lang.String r1 = "CLIMBING_MACHINE"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L78
            goto L7a
        L5d:
            java.lang.String r1 = "STEPPER"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L7a
            goto L78
        L66:
            java.lang.String r1 = "TREADMILL"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L78
            goto L7a
        L6f:
            java.lang.String r1 = "ROWING"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L78
            goto L7a
        L78:
            r1 = 1
            goto L7b
        L7a:
            r1 = 0
        L7b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.EastApexWorkoutUtils.isStepsApplicable(android.content.Context, java.lang.String, java.lang.Integer, com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider$ScreenType, java.lang.Integer, java.lang.Integer):boolean");
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
        this.f2869a = context;
    }
}
