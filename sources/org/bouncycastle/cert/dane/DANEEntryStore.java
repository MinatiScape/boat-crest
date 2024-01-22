package org.bouncycastle.cert.dane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;
/* loaded from: classes12.dex */
public class DANEEntryStore implements Store {
    public final Map h;

    public DANEEntryStore(List list) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DANEEntry dANEEntry = (DANEEntry) it.next();
            hashMap.put(dANEEntry.getDomainName(), dANEEntry);
        }
        this.h = Collections.unmodifiableMap(hashMap);
    }

    @Override // org.bouncycastle.util.Store
    public Collection getMatches(Selector selector) throws StoreException {
        if (selector == null) {
            return this.h.values();
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.h.values()) {
            if (selector.match(obj)) {
                arrayList.add(obj);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Store toCertificateStore() {
        Collection<DANEEntry> matches = getMatches(null);
        ArrayList arrayList = new ArrayList(matches.size());
        for (DANEEntry dANEEntry : matches) {
            arrayList.add(dANEEntry.getCertificate());
        }
        return new CollectionStore(arrayList);
    }
}
