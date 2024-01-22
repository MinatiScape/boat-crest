package com.crrepa.j;

import com.crrepa.ble.conn.type.CRPProtocolVersion;
/* loaded from: classes9.dex */
public class n {
    public static CRPProtocolVersion a(String str) {
        str.hashCode();
        return !str.equals("MOYOUNG-V2") ? !str.equals("MOYOUNG-V3") ? CRPProtocolVersion.V1 : CRPProtocolVersion.V3 : CRPProtocolVersion.V2;
    }
}
