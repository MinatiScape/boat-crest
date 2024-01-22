package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.Data;
/* loaded from: classes13.dex */
public abstract class DVCSRequestData {
    public Data data;

    public DVCSRequestData(Data data) {
        this.data = data;
    }

    public Data toASN1Structure() {
        return this.data;
    }
}
