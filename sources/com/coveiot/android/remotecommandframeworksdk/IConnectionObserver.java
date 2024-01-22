package com.coveiot.android.remotecommandframeworksdk;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/IConnectionObserver;", "Lcom/coveiot/android/remotecommandframeworksdk/IObserver;", "", NotificationCompat.CATEGORY_STATUS, "", "onConnectionUpdate", "(Z)V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface IConnectionObserver extends IObserver {
    void onConnectionUpdate(boolean z);
}
