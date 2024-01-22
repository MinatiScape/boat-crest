package org.bouncycastle.asn1.eac;

import java.util.Hashtable;
/* loaded from: classes12.dex */
public class BidirectionalMap extends Hashtable {
    private static final long serialVersionUID = -7457289971962812909L;
    public Hashtable reverseMap = new Hashtable();

    public Object getReverse(Object obj) {
        return this.reverseMap.get(obj);
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public Object put(Object obj, Object obj2) {
        this.reverseMap.put(obj2, obj);
        return super.put(obj, obj2);
    }
}
