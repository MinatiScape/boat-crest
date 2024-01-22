package com.szabh.smable3.component;

import com.bestmafen.baseble.messenger.BleMessage;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface BleMessengerCallback {
    void onRetry();

    void onTimeout(@NotNull BleMessage bleMessage);
}
