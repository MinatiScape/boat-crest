package com.coveiot.android.fitnessbuddies.fragments.contarctors;

import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddiesGoal;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public interface NotificationsContarctor {
    void dismissProgress();

    void showBuddyContents(@NotNull List<GetBuddyItems> list);

    void showContents(@NotNull List<? extends BuddiesGoal> list);

    void showEmptyLayout();

    void showProgress();
}
