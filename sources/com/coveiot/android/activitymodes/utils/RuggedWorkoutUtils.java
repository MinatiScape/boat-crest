package com.coveiot.android.activitymodes.utils;

import android.content.Context;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.SingletonHolder;
import com.coveiot.android.activitymodes.models.ActivitiesItem;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import java.util.ArrayList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class RuggedWorkoutUtils extends MatrixWorkoutUtils {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<RuggedWorkoutUtils, Context> {

        /* loaded from: classes2.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, RuggedWorkoutUtils> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, RuggedWorkoutUtils.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final RuggedWorkoutUtils invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new RuggedWorkoutUtils(p0);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RuggedWorkoutUtils(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4 */
    @Override // com.coveiot.android.activitymodes.utils.MatrixWorkoutUtils, com.coveiot.android.activitymodes.utils.WorkoutParameterInterface
    @NotNull
    public WorkoutImageBean getWorkoutImageBean(@NotNull ActivityMode activityMode, @Nullable String str, boolean z) {
        ActivitiesItem activitiesItem;
        String iconUrl;
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        ArrayList<ActivitiesItem> ruggedActivityIcons = new PreferenceManager(getContext()).getRuggedActivityIcons();
        Integer valueOf = Integer.valueOf(R.drawable.activity_walking_icon);
        if (!(ruggedActivityIcons == null || ruggedActivityIcons.isEmpty()) && (activitiesItem = WorkoutUtils.INSTANCE.getActivitiesItem(activityMode, str, ruggedActivityIcons)) != null) {
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
}
