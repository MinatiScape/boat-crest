package com.realsil.sdk.dfu.s;

import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.RtkDfu;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.params.QcConfig;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f13648a = true;
    public static boolean b = false;
    public int c;
    public int d;
    public SppTransportLayer e;
    public OtaDeviceInfo f;
    public b h;
    public List<OtaModeInfo> g = new ArrayList();
    public int i = 0;
    public TransportLayerCallback j = new C0725a();
    public Object k = new Object();

    /* renamed from: com.realsil.sdk.dfu.s.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class C0725a extends TransportLayerCallback {
        public C0725a() {
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onAckReceive(AckPacket ackPacket) {
            super.onAckReceive(ackPacket);
            a.this.a(ackPacket);
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onDataReceive(TransportLayerPacket transportLayerPacket) {
            super.onDataReceive(transportLayerPacket);
            try {
                a.this.a(transportLayerPacket);
            } catch (Exception e) {
                ZLogger.w(e.toString());
            }
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onError(int i) {
            super.onError(i);
        }
    }

    /* loaded from: classes12.dex */
    public interface b {
        void a(int i);
    }

    public void a(int i, b bVar) {
        f13648a = RtkDfu.DEBUG_ENABLE;
        this.i = i;
        this.h = bVar;
        this.f = new OtaDeviceInfo(this.c, 2);
        this.g = new ArrayList();
        SppTransportLayer sppTransportLayer = SppTransportLayer.getInstance();
        this.e = sppTransportLayer;
        sppTransportLayer.register(this.j);
    }

    public void a(AckPacket ackPacket) {
    }

    public void a(TransportLayerPacket transportLayerPacket) {
    }

    public boolean a(OtaDeviceInfo otaDeviceInfo, QcConfig qcConfig) {
        return false;
    }

    public OtaDeviceInfo b() {
        if (this.f == null) {
            this.f = new OtaDeviceInfo(this.c, 2);
        }
        return this.f;
    }

    public SppTransportLayer c() {
        if (this.e == null) {
            SppTransportLayer sppTransportLayer = SppTransportLayer.getInstance();
            this.e = sppTransportLayer;
            sppTransportLayer.register(this.j);
        }
        return this.e;
    }

    public List<OtaModeInfo> d() {
        return this.g;
    }

    public boolean e() {
        return (this.d & 256) == 256;
    }

    public void f() {
        ArrayList arrayList = new ArrayList();
        this.g = arrayList;
        arrayList.add(new OtaModeInfo(16));
    }

    public void g() {
        synchronized (this.k) {
            this.k.notifyAll();
        }
    }

    public void h() {
        synchronized (this.k) {
            try {
                this.k.wait(5000L);
            } catch (InterruptedException e) {
                ZLogger.d("wait sync data interrupted: " + e.toString());
            }
        }
    }

    public void b(int i) {
        ZLogger.d(String.format("syndata 0x%04X >> 0x%04X", Integer.valueOf(this.d), Integer.valueOf(i)));
        this.d = i;
        b bVar = this.h;
        if (bVar != null) {
            bVar.a(i);
        } else {
            ZLogger.v(false, "no callback registered");
        }
    }

    public void a() {
        this.d = 0;
        SppTransportLayer sppTransportLayer = this.e;
        if (sppTransportLayer != null) {
            sppTransportLayer.unregister(this.j);
        }
    }

    public OtaModeInfo a(int i) {
        List<OtaModeInfo> list = this.g;
        if (list != null && list.size() > 0) {
            for (OtaModeInfo otaModeInfo : this.g) {
                if (otaModeInfo.getWorkmode() == i) {
                    return otaModeInfo;
                }
            }
            return this.g.get(0);
        }
        return new OtaModeInfo(0);
    }
}
