package com.crrepa.f0;

import com.crrepa.ble.conn.bean.CRPWatchFaceInfo;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceStoreCallback;
import com.crrepa.ble.trans.watchface.entity.WatchFaceStoreEntity;
import com.crrepa.i0.l;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes9.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public CRPDeviceWatchFaceStoreCallback f7717a;

    /* loaded from: classes9.dex */
    public class a extends com.crrepa.s.d {
        public a() {
        }

        @Override // com.crrepa.s.a
        public void a(int i, String str) {
            c.this.a();
        }

        @Override // com.crrepa.s.a
        public void a(Object obj) {
            if (!(obj instanceof String)) {
                c.this.a();
                return;
            }
            c.this.d((String) obj);
        }
    }

    public c(CRPDeviceWatchFaceStoreCallback cRPDeviceWatchFaceStoreCallback) {
        this.f7717a = cRPDeviceWatchFaceStoreCallback;
    }

    public final void a() {
        this.f7717a.onError("Network exception");
    }

    public void a(List<Integer> list, String str, int i, int i2) {
        String a2 = d.a(list);
        HashMap hashMap = new HashMap();
        hashMap.put("tpls", a2);
        hashMap.put("fv", str);
        hashMap.put("per_page", String.valueOf(i));
        hashMap.put(RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, String.valueOf(i2));
        com.crrepa.r.a.b("https://api.moyoung.com/v2/faces", hashMap, new a());
    }

    public final void d(String str) {
        com.crrepa.i0.c.a("response: " + str);
        WatchFaceStoreEntity watchFaceStoreEntity = (WatchFaceStoreEntity) l.a(str, WatchFaceStoreEntity.class);
        if (watchFaceStoreEntity == null || watchFaceStoreEntity.getCode() != 0) {
            a();
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<WatchFaceStoreEntity.FacesBean> faces = watchFaceStoreEntity.getFaces();
        if (faces == null || faces.isEmpty()) {
            this.f7717a.onWatchFaceStoreChange(null);
            return;
        }
        for (WatchFaceStoreEntity.FacesBean facesBean : faces) {
            arrayList.add(new CRPWatchFaceInfo.WatchFaceBean(facesBean.getId(), facesBean.getPreview(), facesBean.getFile()));
        }
        try {
            this.f7717a.onWatchFaceStoreChange(new CRPWatchFaceInfo(watchFaceStoreEntity.getTotal(), Integer.parseInt(watchFaceStoreEntity.getPer_page()), Integer.parseInt(watchFaceStoreEntity.getCurrent_page()), arrayList));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
