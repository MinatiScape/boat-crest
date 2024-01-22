package com.coveiot.android.sportsnotification.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.sportsnotification.SportsPreference;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SportsActivityViewModel extends AndroidViewModel {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SportsActivityViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
    }

    public final boolean isNotificationEnabled(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return SportsPreference.Companion.isNotificationEnabled(context);
    }
}
