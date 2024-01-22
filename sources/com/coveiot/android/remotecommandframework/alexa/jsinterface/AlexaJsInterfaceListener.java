package com.coveiot.android.remotecommandframework.alexa.jsinterface;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public interface AlexaJsInterfaceListener {
    void onAccountLinkingCompleted(boolean z);

    void onAppOut(@Nullable String str);

    void winClose();
}
