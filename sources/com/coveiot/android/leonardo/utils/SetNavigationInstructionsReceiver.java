package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.utils.utility.LogHelper;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SetNavigationInstructionsReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (intent.hasExtra(Constants.REQUEST_NAVIGATION_FEATURE_CONTENT_BROADCAST_INTENT)) {
            Date date = new Date();
            LogHelper.d("RequestNavigationStatusReceiver", "RequestNavigationFeatureContentReceiver " + date);
            Toast.makeText(context, "Send set common application content", 1).show();
        }
    }
}
