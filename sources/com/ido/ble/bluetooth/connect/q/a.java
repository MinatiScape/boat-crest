package com.ido.ble.bluetooth.connect.q;

import android.text.TextUtils;
import com.ido.ble.common.k;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.i;
import com.ido.ble.protocol.model.BindEncrypted;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class a {
    private static a d = new a();

    /* renamed from: a  reason: collision with root package name */
    private c f12091a;
    private com.ido.ble.callback.a b = new C0574a();
    private i.c c;

    /* renamed from: com.ido.ble.bluetooth.connect.q.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0574a extends com.ido.ble.callback.a {
        public C0574a() {
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo) {
            a.this.a(supportFunctionInfo);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements i.c {
        public b() {
        }

        @Override // com.ido.ble.protocol.handler.i.c
        public void a(boolean z) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [EncryptedTask]onEncrypted " + z);
            if (z) {
                a.this.f12091a.onSuccess();
                return;
            }
            LogTool.b(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [EncryptedTask]re get code. ");
            a.this.b();
        }

        @Override // com.ido.ble.protocol.handler.i.c
        public void a(int[] iArr) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [EncryptedTask]onGetCode " + Arrays.toString(iArr));
            a.this.b(iArr);
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void onFailed();

        void onSuccess();
    }

    private a() {
        b bVar = new b();
        this.c = bVar;
        i.a(bVar);
    }

    private void a() {
        com.ido.ble.callback.b.N().a(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SupportFunctionInfo supportFunctionInfo) {
        com.ido.ble.callback.b.N().b(this.b);
        if (supportFunctionInfo == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [EncryptedTask]return1Func, supportFunctionInfo is null");
            this.f12091a.onFailed();
        } else if (supportFunctionInfo.ex_table_main8_encrypted_auth) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [EncryptedTask]return1Func, support encrypted. to get code");
            b();
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [EncryptedTask]return1Func, is not support encrypted.");
            this.f12091a.onSuccess();
        }
    }

    private void a(int[] iArr) {
        BindEncrypted bindEncrypted = new BindEncrypted();
        bindEncrypted.autu_data = iArr;
        bindEncrypted.auth_length = (iArr == null || iArr.length <= 0) ? 0 : iArr.length;
        com.ido.ble.bluetooth.a.i(k.a(bindEncrypted));
        com.ido.ble.i.a.a.a(bindEncrypted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        i.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int[] iArr) {
        if (iArr == null) {
            this.f12091a.onFailed();
        } else {
            a(iArr);
        }
    }

    public static a c() {
        return d;
    }

    public void a(c cVar) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to encryptedAtConnected ");
        this.f12091a = cVar;
        if (!com.ido.ble.bluetooth.a.g()) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnected, is not bind, not need. ");
            this.f12091a.onSuccess();
            return;
        }
        SupportFunctionInfo Z = com.ido.ble.f.a.f.a.g0().Z();
        if (Z != null && !Z.ex_table_main8_encrypted_auth) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnected, device not support encrypt. ");
            this.f12091a.onSuccess();
            return;
        }
        String f = com.ido.ble.bluetooth.a.f();
        if (TextUtils.isEmpty(f)) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnected, jsonString is null, to get func...");
            a();
            return;
        }
        BindEncrypted bindEncrypted = (BindEncrypted) k.c(f, BindEncrypted.class);
        if (bindEncrypted != null) {
            a(bindEncrypted.autu_data);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] encryptedAtConnected, encrypted is null, to re get code...");
        b();
    }

    public void a(int[] iArr, c cVar) {
        this.f12091a = cVar;
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] start to encryptedAtBind ");
        a(iArr);
    }
}
