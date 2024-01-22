package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.BindAuth;
import com.ido.ble.protocol.model.BindEncrypted;
import com.ido.ble.protocol.model.BindPara;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class d {
    public static int a(BindAuth bindAuth) {
        return u.b(com.ido.ble.common.c.b(new Gson().toJson(bindAuth)), 202);
    }

    public static int a(BindEncrypted bindEncrypted) {
        return u.b(com.ido.ble.common.c.b(new Gson().toJson(bindEncrypted)), 204);
    }

    public static int a(BindPara bindPara) {
        return u.b(com.ido.ble.common.c.b(new Gson().toJson(bindPara)), 200);
    }

    public static void a() {
    }

    public static int b() {
        return u.a((int) com.veryfit.multi.nativeprotocol.b.t5, 201, 0, 0);
    }
}
