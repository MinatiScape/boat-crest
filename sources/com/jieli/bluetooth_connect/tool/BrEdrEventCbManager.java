package com.jieli.bluetooth_connect.tool;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;
import com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class BrEdrEventCbManager implements OnBrEdrListener {
    private final List<OnBrEdrListener> mOnBrEdrListeners = new ArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public class BrEdrCbRunnable implements Runnable {
        private final BrEdrEventImpl mImpl;

        public BrEdrCbRunnable(BrEdrEventImpl brEdrEventImpl) {
            this.mImpl = brEdrEventImpl;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mImpl == null || BrEdrEventCbManager.this.mOnBrEdrListeners.isEmpty()) {
                return;
            }
            Iterator it = new ArrayList(BrEdrEventCbManager.this.mOnBrEdrListeners).iterator();
            while (it.hasNext()) {
                this.mImpl.notify((OnBrEdrListener) it.next());
            }
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class BrEdrEventImpl {
        private BrEdrEventImpl() {
        }

        public abstract void notify(OnBrEdrListener onBrEdrListener);
    }

    private void notifyEvent(BrEdrEventImpl brEdrEventImpl) {
        BrEdrCbRunnable brEdrCbRunnable = new BrEdrCbRunnable(brEdrEventImpl);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.mHandler.post(brEdrCbRunnable);
        } else {
            brEdrCbRunnable.run();
        }
    }

    public void addListener(OnBrEdrListener onBrEdrListener) {
        if (onBrEdrListener != null) {
            this.mOnBrEdrListeners.add(onBrEdrListener);
        }
    }

    public void destroy() {
        this.mOnBrEdrListeners.clear();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
    public void onA2dpStatus(final BluetoothDevice bluetoothDevice, final int i) {
        notifyEvent(new BrEdrEventImpl() { // from class: com.jieli.bluetooth_connect.tool.BrEdrEventCbManager.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BrEdrEventCbManager.BrEdrEventImpl
            public void notify(OnBrEdrListener onBrEdrListener) {
                onBrEdrListener.onA2dpStatus(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
    public void onBrEdrConnection(final BluetoothDevice bluetoothDevice, final int i) {
        notifyEvent(new BrEdrEventImpl() { // from class: com.jieli.bluetooth_connect.tool.BrEdrEventCbManager.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BrEdrEventCbManager.BrEdrEventImpl
            public void notify(OnBrEdrListener onBrEdrListener) {
                onBrEdrListener.onBrEdrConnection(bluetoothDevice, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
    public void onDeviceUuids(final BluetoothDevice bluetoothDevice, final ParcelUuid[] parcelUuidArr) {
        notifyEvent(new BrEdrEventImpl() { // from class: com.jieli.bluetooth_connect.tool.BrEdrEventCbManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BrEdrEventCbManager.BrEdrEventImpl
            public void notify(OnBrEdrListener onBrEdrListener) {
                onBrEdrListener.onDeviceUuids(bluetoothDevice, parcelUuidArr);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
    public void onEdrService(final boolean z, final int i, final BluetoothProfile bluetoothProfile) {
        notifyEvent(new BrEdrEventImpl() { // from class: com.jieli.bluetooth_connect.tool.BrEdrEventCbManager.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BrEdrEventCbManager.BrEdrEventImpl
            public void notify(OnBrEdrListener onBrEdrListener) {
                onBrEdrListener.onEdrService(z, i, bluetoothProfile);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
    public void onHfpStatus(final BluetoothDevice bluetoothDevice, final int i) {
        notifyEvent(new BrEdrEventImpl() { // from class: com.jieli.bluetooth_connect.tool.BrEdrEventCbManager.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.BrEdrEventCbManager.BrEdrEventImpl
            public void notify(OnBrEdrListener onBrEdrListener) {
                onBrEdrListener.onHfpStatus(bluetoothDevice, i);
            }
        });
    }

    public void removeListener(OnBrEdrListener onBrEdrListener) {
        if (onBrEdrListener != null) {
            this.mOnBrEdrListeners.remove(onBrEdrListener);
        }
    }
}
