package com.jieli.bluetooth_connect.tool;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.bean.ble.BleScanMessage;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class BtDiscoveryCbManager implements OnBtDiscoveryListener {
    private final List<OnBtDiscoveryListener> mOnBtDiscoveryListeners = new ArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public static abstract class BtDiscoveryCbImpl {
        private BtDiscoveryCbImpl() {
        }

        public abstract void notify(OnBtDiscoveryListener onBtDiscoveryListener);
    }

    /* loaded from: classes11.dex */
    public class BtDiscoveryCbRunnable implements Runnable {
        private final BtDiscoveryCbImpl mImpl;

        public BtDiscoveryCbRunnable(BtDiscoveryCbImpl btDiscoveryCbImpl) {
            this.mImpl = btDiscoveryCbImpl;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mImpl == null || BtDiscoveryCbManager.this.mOnBtDiscoveryListeners.isEmpty()) {
                return;
            }
            Iterator it = new ArrayList(BtDiscoveryCbManager.this.mOnBtDiscoveryListeners).iterator();
            while (it.hasNext()) {
                this.mImpl.notify((OnBtDiscoveryListener) it.next());
            }
        }
    }

    private void notifyEvent(BtDiscoveryCbImpl btDiscoveryCbImpl) {
        BtDiscoveryCbRunnable btDiscoveryCbRunnable = new BtDiscoveryCbRunnable(btDiscoveryCbImpl);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.mHandler.post(btDiscoveryCbRunnable);
        } else {
            btDiscoveryCbRunnable.run();
        }
    }

    public void addListener(OnBtDiscoveryListener onBtDiscoveryListener) {
        if (onBtDiscoveryListener != null) {
            this.mOnBtDiscoveryListeners.add(onBtDiscoveryListener);
        }
    }

    public void destroy() {
        this.mOnBtDiscoveryListeners.clear();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener
    public void onDiscoveryDevice(final BluetoothDevice bluetoothDevice, final BleScanMessage bleScanMessage) {
        notifyEvent(new BtDiscoveryCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BtDiscoveryCbManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BtDiscoveryCbManager.BtDiscoveryCbImpl
            public void notify(OnBtDiscoveryListener onBtDiscoveryListener) {
                onBtDiscoveryListener.onDiscoveryDevice(bluetoothDevice, bleScanMessage);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener
    public void onDiscoveryError(final ErrorInfo errorInfo) {
        notifyEvent(new BtDiscoveryCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BtDiscoveryCbManager.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BtDiscoveryCbManager.BtDiscoveryCbImpl
            public void notify(OnBtDiscoveryListener onBtDiscoveryListener) {
                onBtDiscoveryListener.onDiscoveryError(errorInfo);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener
    public void onDiscoveryStatusChange(final boolean z, final boolean z2) {
        notifyEvent(new BtDiscoveryCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BtDiscoveryCbManager.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BtDiscoveryCbManager.BtDiscoveryCbImpl
            public void notify(OnBtDiscoveryListener onBtDiscoveryListener) {
                onBtDiscoveryListener.onDiscoveryStatusChange(z, z2);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDiscoveryListener
    public void onShowProductDialog(final BluetoothDevice bluetoothDevice, final BleScanMessage bleScanMessage) {
        notifyEvent(new BtDiscoveryCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BtDiscoveryCbManager.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BtDiscoveryCbManager.BtDiscoveryCbImpl
            public void notify(OnBtDiscoveryListener onBtDiscoveryListener) {
                onBtDiscoveryListener.onShowProductDialog(bluetoothDevice, bleScanMessage);
            }
        });
    }

    public void removeListener(OnBtDiscoveryListener onBtDiscoveryListener) {
        if (onBtDiscoveryListener != null) {
            this.mOnBtDiscoveryListeners.remove(onBtDiscoveryListener);
        }
    }
}
