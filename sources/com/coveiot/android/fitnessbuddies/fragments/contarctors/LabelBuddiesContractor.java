package com.coveiot.android.fitnessbuddies.fragments.contarctors;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public interface LabelBuddiesContractor {
    void dismissProgress();

    void onError(@NotNull String str);

    void removeBuddySuccess();

    void showLabelSuccessMessage(@NotNull String str);

    void showProgress();
}
