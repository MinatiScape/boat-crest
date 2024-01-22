package com.crrepa.f0;

import com.crrepa.ble.conn.bean.CRPWatchFaceInfo;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceCallback;
import com.crrepa.ble.trans.watchface.entity.WatchFaceEntity;
import com.crrepa.i0.l;
import java.util.HashMap;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public CRPDeviceWatchFaceCallback f7716a;

    /* loaded from: classes9.dex */
    public class a extends com.crrepa.s.d {
        public final /* synthetic */ int b;

        public a(int i) {
            this.b = i;
        }

        @Override // com.crrepa.s.a
        public void a(int i, String str) {
            b.this.a();
        }

        @Override // com.crrepa.s.a
        public void a(Object obj) {
            if (!(obj instanceof String)) {
                b.this.a();
                return;
            }
            b.this.d((String) obj, this.b);
        }
    }

    public b(CRPDeviceWatchFaceCallback cRPDeviceWatchFaceCallback) {
        this.f7716a = cRPDeviceWatchFaceCallback;
    }

    public final void a() {
        this.f7716a.onError("Network exception");
    }

    public void a(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", String.valueOf(i));
        com.crrepa.r.a.b("http://api2.crrepa.com/face-detail", hashMap, new a(i));
    }

    public final void d(String str, int i) {
        com.crrepa.i0.c.a("response: " + str);
        WatchFaceEntity watchFaceEntity = (WatchFaceEntity) l.a(str, WatchFaceEntity.class);
        if (watchFaceEntity == null || watchFaceEntity.getCode() != 0) {
            a();
            return;
        }
        String file = watchFaceEntity.getFile();
        this.f7716a.onWatchFaceChange(new CRPWatchFaceInfo.WatchFaceBean(i, watchFaceEntity.getPreview(), file));
    }
}
