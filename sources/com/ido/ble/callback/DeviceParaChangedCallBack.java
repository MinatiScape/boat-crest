package com.ido.ble.callback;

import com.ido.ble.protocol.model.DeviceChangedPara;
/* loaded from: classes11.dex */
public class DeviceParaChangedCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onChanged(DeviceChangedPara deviceChangedPara);
    }

    public static void onChanged(final DeviceChangedPara deviceChangedPara) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.DeviceParaChangedCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().m()) {
                    iCallBack.onChanged(DeviceChangedPara.this);
                }
            }
        });
    }
}
