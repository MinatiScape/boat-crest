package com.coveiot.android.respiratoryrate.listener;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public interface DialogListener {
    void onDismiss();

    void onShowProgressDialog();

    void showErrorDialog(@Nullable String str);

    void showSuccessDialog();
}
