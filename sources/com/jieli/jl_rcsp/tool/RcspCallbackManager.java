package com.jieli.jl_rcsp.tool;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.base.CommandBase;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes11.dex */
public class RcspCallbackManager extends OnRcspCallback {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<OnRcspCallback> f12444a = new ArrayList<>();
    private final Handler b = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public class OnRcspRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final RcspCallbackImpl f12453a;

        public OnRcspRunnable(RcspCallbackImpl rcspCallbackImpl) {
            this.f12453a = rcspCallbackImpl;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (RcspCallbackManager.this.f12444a.isEmpty() || this.f12453a == null) {
                return;
            }
            Iterator it = new ArrayList(RcspCallbackManager.this.f12444a).iterator();
            while (it.hasNext()) {
                this.f12453a.onCallback((OnRcspCallback) it.next());
            }
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class RcspCallbackImpl {
        private RcspCallbackImpl() {
        }

        public abstract void onCallback(OnRcspCallback onRcspCallback);
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onConnectStateChange(final BluetoothDevice bluetoothDevice, final int i) {
        a(new RcspCallbackImpl() { // from class: com.jieli.jl_rcsp.tool.RcspCallbackManager.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspCallbackManager.RcspCallbackImpl
            public void onCallback(OnRcspCallback onRcspCallback) {
                onRcspCallback.onConnectStateChange(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onMandatoryUpgrade(final BluetoothDevice bluetoothDevice) {
        a(new RcspCallbackImpl() { // from class: com.jieli.jl_rcsp.tool.RcspCallbackManager.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspCallbackManager.RcspCallbackImpl
            public void onCallback(OnRcspCallback onRcspCallback) {
                onRcspCallback.onMandatoryUpgrade(bluetoothDevice);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onPutDataToDataHandler(final BluetoothDevice bluetoothDevice, final byte[] bArr) {
        a(new RcspCallbackImpl() { // from class: com.jieli.jl_rcsp.tool.RcspCallbackManager.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspCallbackManager.RcspCallbackImpl
            public void onCallback(OnRcspCallback onRcspCallback) {
                onRcspCallback.onPutDataToDataHandler(bluetoothDevice, bArr);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onRcspCommand(final BluetoothDevice bluetoothDevice, final CommandBase commandBase) {
        a(new RcspCallbackImpl() { // from class: com.jieli.jl_rcsp.tool.RcspCallbackManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspCallbackManager.RcspCallbackImpl
            public void onCallback(OnRcspCallback onRcspCallback) {
                onRcspCallback.onRcspCommand(bluetoothDevice, commandBase);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onRcspDataCmd(final BluetoothDevice bluetoothDevice, final CommandBase commandBase) {
        a(new RcspCallbackImpl() { // from class: com.jieli.jl_rcsp.tool.RcspCallbackManager.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspCallbackManager.RcspCallbackImpl
            public void onCallback(OnRcspCallback onRcspCallback) {
                onRcspCallback.onRcspDataCmd(bluetoothDevice, commandBase);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onRcspError(final BluetoothDevice bluetoothDevice, final BaseError baseError) {
        a(new RcspCallbackImpl() { // from class: com.jieli.jl_rcsp.tool.RcspCallbackManager.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspCallbackManager.RcspCallbackImpl
            public void onCallback(OnRcspCallback onRcspCallback) {
                onRcspCallback.onRcspError(bluetoothDevice, baseError);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onRcspInit(final BluetoothDevice bluetoothDevice, final boolean z) {
        a(new RcspCallbackImpl() { // from class: com.jieli.jl_rcsp.tool.RcspCallbackManager.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspCallbackManager.RcspCallbackImpl
            public void onCallback(OnRcspCallback onRcspCallback) {
                onRcspCallback.onRcspInit(bluetoothDevice, z);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.interfaces.rcsp.OnRcspCallback
    public void onSwitchConnectedDevice(final BluetoothDevice bluetoothDevice) {
        a(new RcspCallbackImpl() { // from class: com.jieli.jl_rcsp.tool.RcspCallbackManager.7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.jl_rcsp.tool.RcspCallbackManager.RcspCallbackImpl
            public void onCallback(OnRcspCallback onRcspCallback) {
                onRcspCallback.onSwitchConnectedDevice(bluetoothDevice);
            }
        });
    }

    public void registerRcspCallback(OnRcspCallback onRcspCallback) {
        if (onRcspCallback == null || this.f12444a.contains(onRcspCallback)) {
            return;
        }
        this.f12444a.add(onRcspCallback);
    }

    public void release() {
        this.f12444a.clear();
        this.b.removeCallbacksAndMessages(null);
    }

    public void unregisterRcspCallback(OnRcspCallback onRcspCallback) {
        if (onRcspCallback == null || this.f12444a.isEmpty()) {
            return;
        }
        this.f12444a.remove(onRcspCallback);
    }

    private void a(RcspCallbackImpl rcspCallbackImpl) {
        OnRcspRunnable onRcspRunnable = new OnRcspRunnable(rcspCallbackImpl);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.b.post(onRcspRunnable);
        } else {
            onRcspRunnable.run();
        }
    }
}
