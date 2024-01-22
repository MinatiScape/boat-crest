package com.coveiot.android.leonardo.more.fragments;

import android.app.NotificationManager;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.AppSessionManager;
import kotlin.jvm.internal.Intrinsics;
import no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class DFUWatchListFragment$mDfuProgressListener$1 extends DfuProgressListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DFUWatchListFragment f5109a;

    public DFUWatchListFragment$mDfuProgressListener$1(DFUWatchListFragment dFUWatchListFragment) {
        this.f5109a = dFUWatchListFragment;
    }

    public static final void d(DFUWatchListFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.q();
        Object systemService = this$0.requireContext().getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void e(DFUWatchListFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p();
        Object systemService = this$0.requireContext().getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void f(DFUWatchListFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object systemService = this$0.requireContext().getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000d, code lost:
        r3 = r2.f5109a.t;
     */
    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDeviceConnecting(@org.jetbrains.annotations.NotNull java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = "deviceAddress"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r3 = r2.f5109a
            boolean r3 = r3.isAdded()
            if (r3 == 0) goto L1a
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r3 = r2.f5109a
            android.widget.ProgressBar r3 = com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment.access$getUpdateProgressBar$p(r3)
            if (r3 != 0) goto L16
            goto L1a
        L16:
            r0 = 1
            r3.setIndeterminate(r0)
        L1a:
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r3 = r2.f5109a
            android.widget.TextView r3 = com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment.access$getTvUpdateProgress$p(r3)
            if (r3 == 0) goto L32
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r0 = r2.f5109a
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131952597(0x7f1303d5, float:1.9541641E38)
            java.lang.String r0 = r0.getString(r1)
            r3.setText(r0)
        L32:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$mDfuProgressListener$1.onDeviceConnecting(java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0008, code lost:
        r2 = r1.f5109a.t;
     */
    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDeviceDisconnecting(@org.jetbrains.annotations.Nullable java.lang.String r2) {
        /*
            r1 = this;
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            boolean r2 = r2.isAdded()
            if (r2 == 0) goto L15
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            android.widget.ProgressBar r2 = com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment.access$getUpdateProgressBar$p(r2)
            if (r2 != 0) goto L11
            goto L15
        L11:
            r0 = 1
            r2.setIndeterminate(r0)
        L15:
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            android.widget.TextView r2 = com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment.access$getTvUpdateProgress$p(r2)
            if (r2 == 0) goto L23
            r0 = 2131952599(0x7f1303d7, float:1.9541645E38)
            r2.setText(r0)
        L23:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$mDfuProgressListener$1.onDeviceDisconnecting(java.lang.String):void");
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDfuAborted(@NotNull String deviceAddress) {
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        textView = this.f5109a.u;
        if (textView != null) {
            textView.setText(R.string.dfu_status_aborted);
        }
        Handler handler = new Handler();
        final DFUWatchListFragment dFUWatchListFragment = this.f5109a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.fragments.l
            @Override // java.lang.Runnable
            public final void run() {
                DFUWatchListFragment$mDfuProgressListener$1.d(DFUWatchListFragment.this);
            }
        }, 200L);
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onDfuCompleted(@NotNull String deviceAddress) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        AppSessionManager.getInstance(this.f5109a.requireContext()).setIsDfuFailed(false);
        Handler handler = new Handler();
        final DFUWatchListFragment dFUWatchListFragment = this.f5109a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.fragments.m
            @Override // java.lang.Runnable
            public final void run() {
                DFUWatchListFragment$mDfuProgressListener$1.e(DFUWatchListFragment.this);
            }
        }, 200L);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000d, code lost:
        r2 = r1.f5109a.t;
     */
    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDfuProcessStarting(@org.jetbrains.annotations.NotNull java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "deviceAddress"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            boolean r2 = r2.isAdded()
            if (r2 == 0) goto L1a
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            android.widget.ProgressBar r2 = com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment.access$getUpdateProgressBar$p(r2)
            if (r2 != 0) goto L16
            goto L1a
        L16:
            r0 = 1
            r2.setIndeterminate(r0)
        L1a:
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            android.widget.TextView r2 = com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment.access$getTvUpdateProgress$p(r2)
            if (r2 == 0) goto L28
            r0 = 2131952606(0x7f1303de, float:1.954166E38)
            r2.setText(r0)
        L28:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$mDfuProgressListener$1.onDfuProcessStarting(java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000d, code lost:
        r2 = r1.f5109a.t;
     */
    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onEnablingDfuMode(@org.jetbrains.annotations.NotNull java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "deviceAddress"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            boolean r2 = r2.isAdded()
            if (r2 == 0) goto L1a
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            android.widget.ProgressBar r2 = com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment.access$getUpdateProgressBar$p(r2)
            if (r2 != 0) goto L16
            goto L1a
        L16:
            r0 = 1
            r2.setIndeterminate(r0)
        L1a:
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            android.widget.TextView r2 = com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment.access$getTvUpdateProgress$p(r2)
            if (r2 == 0) goto L28
            r0 = 2131952608(0x7f1303e0, float:1.9541664E38)
            r2.setText(r0)
        L28:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$mDfuProgressListener$1.onEnablingDfuMode(java.lang.String):void");
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onError(@NotNull String deviceAddress, int i, int i2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        if (this.f5109a.isAdded()) {
            Handler handler = new Handler();
            final DFUWatchListFragment dFUWatchListFragment = this.f5109a;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.fragments.n
                @Override // java.lang.Runnable
                public final void run() {
                    DFUWatchListFragment$mDfuProgressListener$1.f(DFUWatchListFragment.this);
                }
            }, 200L);
            DFUWatchListFragment dFUWatchListFragment2 = this.f5109a;
            String string = dFUWatchListFragment2.getString(R.string.failed_to_restore);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.failed_to_restore)");
            dFUWatchListFragment2.v(string, "", false);
            AppSessionManager.getInstance(this.f5109a.requireContext()).setIsDfuFailed(true);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000d, code lost:
        r2 = r1.f5109a.t;
     */
    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onFirmwareValidating(@org.jetbrains.annotations.NotNull java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "deviceAddress"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            boolean r2 = r2.isAdded()
            if (r2 == 0) goto L1a
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            android.widget.ProgressBar r2 = com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment.access$getUpdateProgressBar$p(r2)
            if (r2 != 0) goto L16
            goto L1a
        L16:
            r0 = 1
            r2.setIndeterminate(r0)
        L1a:
            com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment r2 = r1.f5109a
            android.widget.TextView r2 = com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment.access$getTvUpdateProgress$p(r2)
            if (r2 == 0) goto L28
            r0 = 2131952613(0x7f1303e5, float:1.9541674E38)
            r2.setText(r0)
        L28:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.DFUWatchListFragment$mDfuProgressListener$1.onFirmwareValidating(java.lang.String):void");
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressListenerAdapter, no.nordicsemi.android.dfu.DfuProgressListener
    public void onProgressChanged(@NotNull String deviceAddress, int i, float f, float f2, int i2, int i3) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        TextView textView;
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        if (this.f5109a.isAdded()) {
            progressBar = this.f5109a.t;
            if (progressBar != null) {
                progressBar.setIndeterminate(false);
            }
            progressBar2 = this.f5109a.t;
            if (progressBar2 != null) {
                progressBar2.setProgress(i);
            }
            textView = this.f5109a.u;
            if (textView != null) {
                textView.setText(this.f5109a.getResources().getString(R.string.dfu_uploading_percentage, Integer.valueOf(i)));
            }
        }
    }
}
