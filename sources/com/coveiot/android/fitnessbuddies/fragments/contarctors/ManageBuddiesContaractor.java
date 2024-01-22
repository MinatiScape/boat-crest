package com.coveiot.android.fitnessbuddies.fragments.contarctors;

import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import java.util.LinkedHashMap;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public interface ManageBuddiesContaractor {
    void dismissPerogress();

    void showContent(@NotNull LinkedHashMap<String, List<Requests>> linkedHashMap);

    void showEmptyView();

    void showMessage(@NotNull String str);

    void showProgress();
}
