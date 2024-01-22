package com.coveiot.android.leonardo.more;

import android.content.Context;
import com.coveiot.covepreferences.AppListPreference;
import java.util.ArrayList;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AppListHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void saveAppListFromSystem$default(Companion companion, Context context, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            companion.saveAppListFromSystem(context, z);
        }

        @Nullable
        public final Pair<ArrayList<String>, ArrayList<String>> loadAppListFromSystem(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return AppListPreference.Companion.getSystemAppList(context);
        }

        public final void saveAppListFromSystem(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (!z || AppListPreference.Companion.getSystemAppList(context) == null) {
                e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AppListHelper$Companion$saveAppListFromSystem$1(context, null), 2, null);
            }
        }
    }
}
