package com.coveiot.android.watchfaceui.utils;

import android.app.Activity;
import android.content.Intent;
import com.blankj.utilcode.util.ActivityUtils;
import com.coveiot.android.watchfaceui.constants.Constants;
import com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public final class AppNavigator {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void navigateToWebViewer(@NotNull Activity context, @NotNull String title, @NotNull String url, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(url, "url");
            Intent intent = new Intent(context, ActivityInAppWebViewerWatchface.class);
            intent.putExtra(Constants.INTENT_EXTRA_URL.getValue(), url);
            intent.putExtra(Constants.INTENT_EXTRA_FROM_DASHBOARD.getValue(), z);
            intent.setFlags(67108864);
            ActivityUtils.startActivityForResult(context, intent, 123);
        }
    }
}
