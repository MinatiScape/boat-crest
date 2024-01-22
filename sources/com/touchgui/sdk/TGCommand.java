package com.touchgui.sdk;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface TGCommand<T> {
    void cancel();

    void execute(@Nullable TGCallback<T> tGCallback);
}
