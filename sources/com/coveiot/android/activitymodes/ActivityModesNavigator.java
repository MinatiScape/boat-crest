package com.coveiot.android.activitymodes;

import android.content.Context;
import android.content.Intent;
import com.coveiot.android.activitymodes.activities.ActivityPlanDetails;
import com.coveiot.android.activitymodes.activities.ActivityWorkoutWebViewer;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivityModesNavigator {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void navigateToPlanDetails(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityPlanDetails.class));
        }

        @JvmStatic
        public final void navigateToWorkoutWebViewer(@NotNull Context context, @NotNull String title, @NotNull String url) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(url, "url");
            Intent intent = new Intent(context, ActivityWorkoutWebViewer.class);
            intent.putExtra(WorkoutConstants.INTENT_EXTRA_TITLE, title);
            intent.putExtra(WorkoutConstants.INTENT_EXTRA_URL, url);
            context.startActivity(intent);
        }
    }

    @JvmStatic
    public static final void navigateToPlanDetails(@NotNull Context context) {
        Companion.navigateToPlanDetails(context);
    }

    @JvmStatic
    public static final void navigateToWorkoutWebViewer(@NotNull Context context, @NotNull String str, @NotNull String str2) {
        Companion.navigateToWorkoutWebViewer(context, str, str2);
    }
}
