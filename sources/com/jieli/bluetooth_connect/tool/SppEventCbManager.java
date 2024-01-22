package com.jieli.bluetooth_connect.tool;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtSppListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
/* loaded from: classes11.dex */
public class SppEventCbManager implements OnBtSppListener {
    private final List<OnBtSppListener> mOnBtSppListeners = new ArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public static abstract class SppEventCbImpl {
        private SppEventCbImpl() {
        }

        public abstract void notify(OnBtSppListener onBtSppListener);
    }

    /* loaded from: classes11.dex */
    public class SppEventCbRunnable implements Runnable {
        private final SppEventCbImpl mImpl;

        public SppEventCbRunnable(SppEventCbImpl sppEventCbImpl) {
            this.mImpl = sppEventCbImpl;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mImpl == null || SppEventCbManager.this.mOnBtSppListeners.isEmpty()) {
                return;
            }
            Iterator it = new ArrayList(SppEventCbManager.this.mOnBtSppListeners).iterator();
            while (it.hasNext()) {
                this.mImpl.notify((OnBtSppListener) it.next());
            }
        }
    }

    private void notifyEvent(SppEventCbImpl sppEventCbImpl) {
        SppEventCbRunnable sppEventCbRunnable = new SppEventCbRunnable(sppEventCbImpl);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.mHandler.post(sppEventCbRunnable);
        } else {
            sppEventCbRunnable.run();
        }
    }

    public void addListener(OnBtSppListener onBtSppListener) {
        if (onBtSppListener != null) {
            this.mOnBtSppListeners.add(onBtSppListener);
        }
    }

    public void destroy() {
        this.mOnBtSppListeners.clear();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtSppListener
    public void onSppConnection(final BluetoothDevice bluetoothDevice, final UUID uuid, final int i) {
        notifyEvent(new SppEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.SppEventCbManager.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.SppEventCbManager.SppEventCbImpl
            public void notify(OnBtSppListener onBtSppListener) {
                onBtSppListener.onSppConnection(bluetoothDevice, uuid, i);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtSppListener
    public void onSppDataNotify(final BluetoothDevice bluetoothDevice, final UUID uuid, final byte[] bArr) {
        if (bArr == null) {
            return;
        }
        notifyEvent(new SppEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.SppEventCbManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.SppEventCbManager.SppEventCbImpl
            public void notify(OnBtSppListener onBtSppListener) {
                onBtSppListener.onSppDataNotify(bluetoothDevice, uuid, bArr);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtSppListener
    public void onSwitchSppDevice(final BluetoothDevice bluetoothDevice) {
        notifyEvent(new SppEventCbImpl() { // from class: com.jieli.bluetooth_connect.tool.SppEventCbManager.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.tool.SppEventCbManager.SppEventCbImpl
            public void notify(OnBtSppListener onBtSppListener) {
                onBtSppListener.onSwitchSppDevice(bluetoothDevice);
            }
        });
    }

    public void removeListener(OnBtSppListener onBtSppListener) {
        if (onBtSppListener != null) {
            this.mOnBtSppListeners.remove(onBtSppListener);
        }
    }
}
