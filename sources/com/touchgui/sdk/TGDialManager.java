package com.touchgui.sdk;

import com.touchgui.sdk.bean.TGMyDials;
/* loaded from: classes12.dex */
public interface TGDialManager {

    /* loaded from: classes12.dex */
    public interface OnSyncDialListener {
        void onCompleted();

        void onError(Throwable th);

        void onInstalling();

        void onProgress(int i);
    }

    void addOnSyncDialListener(OnSyncDialListener onSyncDialListener);

    TGCommand<Integer> applyDial(int i);

    default boolean checkDial(TGDial tGDial) {
        return true;
    }

    TGCommand<Integer> deleteDial(int i);

    int getDialCount();

    int getDynamicDialCount();

    TGCommand<TGMyDials> getMyDials();

    void removeOnSyncDialListener(OnSyncDialListener onSyncDialListener);

    TGCommand<Void> setMyDials(TGMyDials tGMyDials);

    void syncDial(TGDial tGDial);
}
