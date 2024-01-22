package com.ido.ble.protocol.handler;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.BindEncryptedDeviceReply;
import com.ido.ble.protocol.model.EncryptedGetCode;
/* loaded from: classes11.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final Handler f12308a = new Handler(Looper.getMainLooper());
    private static c b;

    /* loaded from: classes11.dex */
    public static class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ byte[] f12309a;

        public a(byte[] bArr) {
            this.f12309a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            i.c(this.f12309a);
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ byte[] f12310a;

        public b(byte[] bArr) {
            this.f12310a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            i.d(this.f12310a);
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void a(boolean z);

        void a(int[] iArr);
    }

    public static void a() {
        u.b((int) com.veryfit.multi.nativeprotocol.b.u5, 205);
    }

    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        Handler handler;
        Runnable bVar;
        if (i == 204) {
            handler = f12308a;
            bVar = new a(bArr);
        } else if (i != 205) {
            return;
        } else {
            handler = f12308a;
            bVar = new b(bArr);
        }
        handler.post(bVar);
    }

    public static void a(c cVar) {
        b = cVar;
    }

    public static boolean a(int i) {
        return i == 204 || i == 205;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[BIND_UNBIND] [handleBindEncryptedJsonResult], jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [handleBindEncryptedJsonResult], jsonString is " + d);
            BindEncryptedDeviceReply bindEncryptedDeviceReply = (BindEncryptedDeviceReply) com.ido.ble.common.k.c(d, BindEncryptedDeviceReply.class);
            if (bindEncryptedDeviceReply != null) {
                if (bindEncryptedDeviceReply.auth_code == 0) {
                    b.a(true);
                    return;
                } else {
                    b.a(false);
                    return;
                }
            }
            str = "[BIND_UNBIND] [handleBindEncryptedJsonResult], bindEncryptedDeviceReply is null";
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
        b.a(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [handleGetEncryptedCode], jsonString is null");
            b.a((int[]) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [handleGetEncryptedCode], jsonString is " + d);
        EncryptedGetCode encryptedGetCode = (EncryptedGetCode) com.ido.ble.common.k.c(d, EncryptedGetCode.class);
        if (encryptedGetCode != null) {
            b.a(encryptedGetCode.encrypted_data);
            return;
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, "[BIND_UNBIND] [handleGetEncryptedCode], bindEncrypted is null");
        b.a((int[]) null);
    }
}
