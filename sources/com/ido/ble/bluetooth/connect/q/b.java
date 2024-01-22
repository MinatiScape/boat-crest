package com.ido.ble.bluetooth.connect.q;

import android.text.TextUtils;
import com.ido.ble.common.k;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.i;
import com.ido.ble.protocol.model.BindEncrypted;
import com.ido.ble.protocol.model.SupportFunctionInfo;
/* loaded from: classes11.dex */
public class b {
    private static b c = new b();

    /* renamed from: a  reason: collision with root package name */
    private InterfaceC0575b f12094a;
    private i.c b;

    /* loaded from: classes11.dex */
    public class a implements i.c {
        public a() {
        }

        @Override // com.ido.ble.protocol.handler.i.c
        public void a(boolean z) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [EncryptedTask]onEncrypted " + z);
            if (z) {
                b.this.f12094a.onSuccess();
            } else {
                b.this.f12094a.onFailed();
            }
        }

        @Override // com.ido.ble.protocol.handler.i.c
        public void a(int[] iArr) {
        }
    }

    /* renamed from: com.ido.ble.bluetooth.connect.q.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public interface InterfaceC0575b {
        void onFailed();

        void onSuccess();
    }

    private b() {
        a aVar = new a();
        this.b = aVar;
        i.a(aVar);
    }

    private void a() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNull, supportFunctionInfo is null ");
        String f = com.ido.ble.bluetooth.a.f();
        if (TextUtils.isEmpty(f)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNull, jsonString is null");
            this.f12094a.onSuccess();
            return;
        }
        BindEncrypted bindEncrypted = (BindEncrypted) k.c(f, BindEncrypted.class);
        if (bindEncrypted != null) {
            a(bindEncrypted.autu_data);
            return;
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNull, encrypted is null");
        this.f12094a.onFailed();
    }

    private void a(SupportFunctionInfo supportFunctionInfo) {
        if (!supportFunctionInfo.ex_table_main8_encrypted_auth) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNotNull, device not support encrypt. ");
            this.f12094a.onSuccess();
            return;
        }
        String f = com.ido.ble.bluetooth.a.f();
        if (TextUtils.isEmpty(f)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNotNull, jsonString is null");
            this.f12094a.onFailed();
            return;
        }
        BindEncrypted bindEncrypted = (BindEncrypted) k.c(f, BindEncrypted.class);
        if (bindEncrypted != null) {
            a(bindEncrypted.autu_data);
            return;
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnectedIfFunctionInfoIsNotNull, encrypted is null");
        this.f12094a.onFailed();
    }

    private void a(int[] iArr) {
        BindEncrypted bindEncrypted = new BindEncrypted();
        bindEncrypted.autu_data = iArr;
        bindEncrypted.auth_length = (iArr == null || iArr.length <= 0) ? 0 : iArr.length;
        com.ido.ble.bluetooth.a.i(k.a(bindEncrypted));
        com.ido.ble.i.a.a.a(bindEncrypted);
    }

    public static b b() {
        return c;
    }

    public void a(InterfaceC0575b interfaceC0575b) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to encryptedAtConnected ");
        this.f12094a = interfaceC0575b;
        if (!com.ido.ble.bluetooth.a.g()) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnected, is not bind, not need. ");
            this.f12094a.onSuccess();
            return;
        }
        SupportFunctionInfo Z = com.ido.ble.f.a.f.a.g0().Z();
        if (Z == null) {
            a();
        } else {
            a(Z);
        }
    }

    public void a(int[] iArr, InterfaceC0575b interfaceC0575b) {
        this.f12094a = interfaceC0575b;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to encryptedAtBind ");
        a(iArr);
    }
}
