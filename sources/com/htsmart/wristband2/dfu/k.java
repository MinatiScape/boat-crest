package com.htsmart.wristband2.dfu;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public abstract class k {

    /* renamed from: a  reason: collision with root package name */
    public Context f12004a;
    public a b;

    /* loaded from: classes11.dex */
    public interface a {
        void a(int i);

        void onProgressChanged(int i);

        void onSuccess();
    }

    public k(Context context) {
        this.f12004a = context;
    }

    public abstract void a();

    public abstract void a(@NonNull BluetoothDevice bluetoothDevice, @NonNull String str, boolean z);

    public void a(a aVar) {
        this.b = aVar;
    }

    public abstract void b();
}
