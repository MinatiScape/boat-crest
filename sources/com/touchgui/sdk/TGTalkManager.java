package com.touchgui.sdk;

import com.touchgui.sdk.bean.TGTalkText;
/* loaded from: classes12.dex */
public interface TGTalkManager {
    void addTalkListener(TGTalkListener tGTalkListener);

    void openMic();

    void prepareFailure();

    void prepareSuccess();

    void removeTalkListener(TGTalkListener tGTalkListener);

    TGCommand<Integer> syncTalkText(TGTalkText tGTalkText);

    TGCommand<Integer> talkError();
}
