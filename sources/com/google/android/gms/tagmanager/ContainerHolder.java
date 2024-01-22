package com.google.android.gms.tagmanager;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes10.dex */
public interface ContainerHolder extends Result, Releasable {

    /* loaded from: classes10.dex */
    public interface ContainerAvailableListener {
        void onContainerAvailable(@RecentlyNonNull ContainerHolder containerHolder, @RecentlyNonNull String str);
    }

    @RecentlyNonNull
    Container getContainer();

    void refresh();

    void setContainerAvailableListener(@RecentlyNonNull ContainerAvailableListener containerAvailableListener);
}
