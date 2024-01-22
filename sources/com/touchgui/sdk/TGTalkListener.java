package com.touchgui.sdk;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public interface TGTalkListener {
    void onError(int i, @NonNull String str);

    void onPrepareTalk();

    void onTalkStart();

    void onTalkStop(int i, byte[] bArr);
}
