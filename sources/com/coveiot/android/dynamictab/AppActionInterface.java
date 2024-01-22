package com.coveiot.android.dynamictab;

import com.coveiot.android.dynamictab.sports.CoveJsInterface;
/* loaded from: classes4.dex */
public interface AppActionInterface {
    String getLanguage();

    void onHealthQuestionaryComplete();

    void onRequestLocation(CoveJsInterface.LocationResultListener locationResultListener);
}
