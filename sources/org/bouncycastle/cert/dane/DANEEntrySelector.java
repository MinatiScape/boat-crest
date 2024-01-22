package org.bouncycastle.cert.dane;

import org.bouncycastle.util.Selector;
/* loaded from: classes12.dex */
public class DANEEntrySelector implements Selector {
    public final String h;

    public DANEEntrySelector(String str) {
        this.h = str;
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        return this;
    }

    public String getDomainName() {
        return this.h;
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        return ((DANEEntry) obj).getDomainName().equals(this.h);
    }
}
