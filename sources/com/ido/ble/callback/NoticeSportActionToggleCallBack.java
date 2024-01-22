package com.ido.ble.callback;

import com.ido.ble.protocol.model.NoticeSportActionToggle;
/* loaded from: classes11.dex */
public class NoticeSportActionToggleCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onDeviceNotify(int i, boolean z, NoticeSportActionToggle noticeSportActionToggle);

        void onSettintResult(int i, boolean z, NoticeSportActionToggle noticeSportActionToggle);
    }

    public static final void onDeviceNotify(final int i, final boolean z, final NoticeSportActionToggle noticeSportActionToggle) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.NoticeSportActionToggleCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().t()) {
                    iCallBack.onDeviceNotify(i, z, noticeSportActionToggle);
                }
            }
        });
    }

    public static final void onSettingResult(final int i, final boolean z, final NoticeSportActionToggle noticeSportActionToggle) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.NoticeSportActionToggleCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().t()) {
                    iCallBack.onSettintResult(i, z, noticeSportActionToggle);
                }
            }
        });
    }
}
