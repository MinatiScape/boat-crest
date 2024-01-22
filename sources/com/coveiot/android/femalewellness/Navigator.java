package com.coveiot.android.femalewellness;

import android.content.Context;
import android.content.Intent;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessCalendarView;
import com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessSettings;
import com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessSymptoms;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class Navigator {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void navigateToFemaleWellness(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityFemaleWellnessSettings.class));
        }

        public final void navigateToFemaleWellnessCalendarViewOrSettings(@NotNull Context context) {
            Intent intent;
            Intrinsics.checkNotNullParameter(context, "context");
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCADevice(context) && !companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
                intent = new Intent(context, ActivityFemaleWellnessSettings.class);
            } else {
                intent = new Intent(context, ActivityFemaleWellnessCalendarView.class);
            }
            context.startActivity(intent);
        }

        public final void navigateToFemaleWellnessSymptoms(@NotNull Context context, @NotNull String selectedDate, @NotNull String phase, @NotNull ArrayList<String> recordedSymptoms, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
            Intrinsics.checkNotNullParameter(phase, "phase");
            Intrinsics.checkNotNullParameter(recordedSymptoms, "recordedSymptoms");
            Intent intent = new Intent(context, ActivityFemaleWellnessSymptoms.class);
            intent.putExtra(Constants.SELECTED_DATE.getValue(), selectedDate);
            intent.putExtra(Constants.PHASE.getValue(), phase);
            intent.putExtra(Constants.EDIT_SYMPTOMS.getValue(), z);
            intent.putExtra(Constants.EDIT_SYMPTOMS_LIST.getValue(), recordedSymptoms);
            context.startActivity(intent);
        }
    }
}
