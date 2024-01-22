package com.jieli.bluetooth_connect.tool;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class BtPairEventCbManager implements OnBtDevicePairListener {
    private final List<OnBtDevicePairListener> mOnBtDevicePairListeners = new ArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public static abstract class BtPairCbImpl {
        private BtPairCbImpl() {
        }

        public abstract void notify(OnBtDevicePairListener onBtDevicePairListener);
    }

    /* loaded from: classes11.dex */
    public class BtPairCbRunnable implements Runnable {
        private final BtPairCbImpl mImpl;

        public BtPairCbRunnable(BtPairCbImpl btPairCbImpl) {
            this.mImpl = btPairCbImpl;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mImpl == null || BtPairEventCbManager.this.mOnBtDevicePairListeners.isEmpty()) {
                return;
            }
            Iterator it = new ArrayList(BtPairEventCbManager.this.mOnBtDevicePairListeners).iterator();
            while (it.hasNext()) {
                this.mImpl.notify((OnBtDevicePairListener) it.next());
            }
        }
    }

    private void notifyEvent(BtPairCbImpl btPairCbImpl) {
        BtPairCbRunnable btPairCbRunnable = new BtPairCbRunnable(btPairCbImpl);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.mHandler.post(btPairCbRunnable);
        } else {
            btPairCbRunnable.run();
        }
    }

    public void addListener(OnBtDevicePairListener onBtDevicePairListener) {
        if (onBtDevicePairListener != null) {
            this.mOnBtDevicePairListeners.add(onBtDevicePairListener);
        }
    }

    public void destroy() {
        this.mOnBtDevicePairListeners.clear();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
    public void onAdapterStatus(final boolean z, final boolean z2) {
        notifyEvent(new BtPairCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BtPairEventCbManager.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BtPairEventCbManager.BtPairCbImpl
            public void notify(OnBtDevicePairListener onBtDevicePairListener) {
                onBtDevicePairListener.onAdapterStatus(z, z2);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
    public void onBtDeviceBond(final BluetoothDevice bluetoothDevice, final int i) {
        notifyEvent(new BtPairCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BtPairEventCbManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BtPairEventCbManager.BtPairCbImpl
            public void notify(OnBtDevicePairListener onBtDevicePairListener) {
                onBtDevicePairListener.onBtDeviceBond(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
    public void onPairError(final BluetoothDevice bluetoothDevice, final ErrorInfo errorInfo) {
        notifyEvent(new BtPairCbImpl() { // from class: com.jieli.bluetooth_connect.tool.BtPairEventCbManager.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BtPairEventCbManager.BtPairCbImpl
            public void notify(OnBtDevicePairListener onBtDevicePairListener) {
                onBtDevicePairListener.onPairError(bluetoothDevice, errorInfo);
            }
        });
    }

    public void removeListener(OnBtDevicePairListener onBtDevicePairListener) {
        if (onBtDevicePairListener != null) {
            this.mOnBtDevicePairListeners.remove(onBtDevicePairListener);
        }
    }
}
