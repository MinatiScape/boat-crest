package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.IncomingCallInfo;
import com.ido.ble.protocol.model.NewMessageInfo;
import com.ido.ble.protocol.model.NotificationPara;
import com.ido.ble.protocol.model.UnreadMessageInfo;
import com.ido.ble.protocol.model.V3MessageNotice;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class l {
    public static void a() {
        u.j();
    }

    public static void a(IncomingCallInfo incomingCallInfo) {
        u.a(com.ido.ble.common.c.d(incomingCallInfo.name), com.ido.ble.common.c.d(incomingCallInfo.phoneNumber));
    }

    public static void a(NewMessageInfo newMessageInfo) {
        u.a(newMessageInfo.type, com.ido.ble.common.c.d(newMessageInfo.name), com.ido.ble.common.c.d(newMessageInfo.number), com.ido.ble.common.c.d(newMessageInfo.content));
    }

    public static void a(NotificationPara notificationPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(notificationPara)), (int) com.veryfit.multi.nativeprotocol.b.W3);
    }

    @Deprecated
    public static void a(UnreadMessageInfo unreadMessageInfo) {
    }

    public static void a(V3MessageNotice v3MessageNotice) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(v3MessageNotice)), 5012);
    }

    public static void b() {
        u.l();
    }
}
