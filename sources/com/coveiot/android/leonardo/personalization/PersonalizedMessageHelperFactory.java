package com.coveiot.android.leonardo.personalization;

import android.content.Context;
import com.coveiot.android.devicemodels.DeviceUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class PersonalizedMessageHelperFactory {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PersonalizedMessageHelper getPersonalizedMessageHelper(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (DeviceUtils.Companion.isPS1Device(context)) {
                return PersonalizedMessageHelperPS1.INSTANCE;
            }
            return PersonalizedMessageHelperPS1.INSTANCE;
        }
    }
}
