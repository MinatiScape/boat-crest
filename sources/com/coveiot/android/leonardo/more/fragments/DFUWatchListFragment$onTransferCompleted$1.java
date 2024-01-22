package com.coveiot.android.leonardo.more.fragments;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class DFUWatchListFragment$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ DFUWatchListFragment h;

    public DFUWatchListFragment$onTransferCompleted$1(DFUWatchListFragment dFUWatchListFragment) {
        this.h = dFUWatchListFragment;
    }

    public static final void b(DFUWatchListFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            handler = this.h.n;
            final DFUWatchListFragment dFUWatchListFragment = this.h;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.fragments.o
                @Override // java.lang.Runnable
                public final void run() {
                    DFUWatchListFragment$onTransferCompleted$1.b(DFUWatchListFragment.this);
                }
            }, 3000L);
        }
    }
}
