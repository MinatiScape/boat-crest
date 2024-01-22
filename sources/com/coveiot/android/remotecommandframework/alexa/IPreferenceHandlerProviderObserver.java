package com.coveiot.android.remotecommandframework.alexa;

import com.coveiot.android.remotecommandframework.alexa.handler.IPreferenceHandlerProvider;
import com.coveiot.android.remotecommandframeworksdk.IObserver;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public interface IPreferenceHandlerProviderObserver extends IObserver {

    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void onPreferenceProviderUpdate$default(IPreferenceHandlerProviderObserver iPreferenceHandlerProviderObserver, IPreferenceHandlerProvider iPreferenceHandlerProvider, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onPreferenceProviderUpdate");
            }
            if ((i & 1) != 0) {
                iPreferenceHandlerProvider = null;
            }
            iPreferenceHandlerProviderObserver.onPreferenceProviderUpdate(iPreferenceHandlerProvider);
        }
    }

    void onPreferenceProviderUpdate(@Nullable IPreferenceHandlerProvider iPreferenceHandlerProvider);
}
