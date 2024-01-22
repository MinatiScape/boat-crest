package com.coveiot.android.fitnessbuddies.fragments.contarctors;

import com.coveiot.coveaccess.fitnessbuddies.model.common.Messages;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public interface MessagesContractor {
    void dismissProgress();

    void showContent(@NotNull List<? extends Messages> list);

    void showEmptyLayout();

    void showProgress();
}
