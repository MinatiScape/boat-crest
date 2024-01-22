package com.apex.bluetooth.core;

import com.apex.bluetooth.callback.OtaCallback;
import com.apex.bluetooth.model.EABleOta;
import com.apex.bluetooth.model.EABleOtaInfo;
/* loaded from: classes.dex */
public class d implements com.apex.bluetooth.listener.b {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ c f2182a;

    public d(c cVar) {
        this.f2182a = cVar;
    }

    @Override // com.apex.bluetooth.listener.b
    public void a(EABleOtaInfo eABleOtaInfo) {
        c cVar = this.f2182a;
        cVar.n = null;
        cVar.d = false;
        EABleOtaInfo.OtaStatus otaStatus = eABleOtaInfo.getOtaStatus();
        if (otaStatus != null) {
            if (otaStatus == EABleOtaInfo.OtaStatus.accept) {
                int receive_bytes = eABleOtaInfo.getReceive_bytes();
                c cVar2 = this.f2182a;
                OtaCallback otaCallback = cVar2.N;
                if (otaCallback != null) {
                    otaCallback.progress(((cVar2.a0 + receive_bytes) * 100) / cVar2.Y);
                }
                if (this.f2182a.V.getOtaType() != EABleOta.OtaType.res && this.f2182a.V.getOtaType() != EABleOta.OtaType.user_wf) {
                    c.a(this.f2182a, receive_bytes);
                } else {
                    c.a(this.f2182a, receive_bytes + 4);
                }
            } else if (otaStatus == EABleOtaInfo.OtaStatus.complete) {
                if (this.f2182a.V.getOtaType() != EABleOta.OtaType.res && this.f2182a.V.getOtaType() != EABleOta.OtaType.user_wf) {
                    c cVar3 = this.f2182a;
                    OtaCallback otaCallback2 = cVar3.N;
                    int byteSize = cVar3.a0 + cVar3.V.getByteSize();
                    cVar3.a0 = byteSize;
                    otaCallback2.progress((byteSize * 100) / this.f2182a.Y);
                } else {
                    c cVar4 = this.f2182a;
                    OtaCallback otaCallback3 = cVar4.N;
                    int byteSize2 = cVar4.a0 + (cVar4.V.getByteSize() - 4);
                    cVar4.a0 = byteSize2;
                    otaCallback3.progress((byteSize2 * 100) / this.f2182a.Y);
                }
                this.f2182a.d();
            } else if (otaStatus == EABleOtaInfo.OtaStatus.crc_error) {
                OtaCallback otaCallback4 = this.f2182a.N;
                if (otaCallback4 != null) {
                    otaCallback4.mutualFail(19);
                }
                c cVar5 = this.f2182a;
                cVar5.N = null;
                cVar5.W.clear();
                c cVar6 = this.f2182a;
                cVar6.V = null;
                cVar6.Y = 0;
                cVar6.Z = 0;
                cVar6.a0 = 0;
                cVar6.O = false;
                this.f2182a.X = null;
            } else if (otaStatus == EABleOtaInfo.OtaStatus.proceed) {
                int receive_bytes2 = eABleOtaInfo.getReceive_bytes();
                c cVar7 = this.f2182a;
                OtaCallback otaCallback5 = cVar7.N;
                if (otaCallback5 != null) {
                    otaCallback5.progress(((cVar7.a0 + receive_bytes2) * 100) / cVar7.Y);
                }
                if (this.f2182a.V.getOtaType() != EABleOta.OtaType.res && this.f2182a.V.getOtaType() != EABleOta.OtaType.user_wf) {
                    c.a(this.f2182a, receive_bytes2);
                } else {
                    c.a(this.f2182a, receive_bytes2 + 4);
                }
            } else if (otaStatus == EABleOtaInfo.OtaStatus.reject) {
                OtaCallback otaCallback6 = this.f2182a.N;
                if (otaCallback6 != null) {
                    otaCallback6.mutualFail(18);
                }
                c cVar8 = this.f2182a;
                cVar8.N = null;
                cVar8.W.clear();
                c cVar9 = this.f2182a;
                cVar9.V = null;
                cVar9.Y = 0;
                cVar9.Z = 0;
                cVar9.a0 = 0;
                cVar9.O = false;
                this.f2182a.X = null;
            } else if (otaStatus == EABleOtaInfo.OtaStatus.reject_version_error) {
                if (this.f2182a.V.getOtaType() != EABleOta.OtaType.res && this.f2182a.V.getOtaType() != EABleOta.OtaType.user_wf) {
                    c cVar10 = this.f2182a;
                    OtaCallback otaCallback7 = cVar10.N;
                    int byteSize3 = cVar10.a0 + cVar10.V.getByteSize();
                    cVar10.a0 = byteSize3;
                    otaCallback7.progress((byteSize3 * 100) / this.f2182a.Y);
                } else {
                    c cVar11 = this.f2182a;
                    OtaCallback otaCallback8 = cVar11.N;
                    int byteSize4 = cVar11.a0 + (cVar11.V.getByteSize() - 4);
                    cVar11.a0 = byteSize4;
                    otaCallback8.progress((byteSize4 * 100) / this.f2182a.Y);
                }
                this.f2182a.d();
            }
        }
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        c cVar = this.f2182a;
        cVar.n = null;
        cVar.d = false;
        OtaCallback otaCallback = this.f2182a.N;
        if (otaCallback != null) {
            otaCallback.mutualFail(i);
        }
        c cVar2 = this.f2182a;
        cVar2.N = null;
        cVar2.W.clear();
        this.f2182a.O = false;
        c cVar3 = this.f2182a;
        cVar3.V = null;
        cVar3.Y = 0;
        cVar3.Z = 0;
        cVar3.a0 = 0;
        cVar3.X = null;
    }
}
