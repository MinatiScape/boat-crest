package org.bouncycastle.cert.dane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes12.dex */
public class DANECertificateFetcher {

    /* renamed from: a  reason: collision with root package name */
    public final DANEEntryFetcherFactory f14477a;
    public final DANEEntrySelectorFactory b;

    public DANECertificateFetcher(DANEEntryFetcherFactory dANEEntryFetcherFactory, DigestCalculator digestCalculator) {
        this.f14477a = dANEEntryFetcherFactory;
        this.b = new DANEEntrySelectorFactory(digestCalculator);
    }

    public List fetch(String str) throws DANEException {
        DANEEntrySelector createSelector = this.b.createSelector(str);
        List<DANEEntry> entries = this.f14477a.build(createSelector.getDomainName()).getEntries();
        ArrayList arrayList = new ArrayList(entries.size());
        for (DANEEntry dANEEntry : entries) {
            if (createSelector.match(dANEEntry)) {
                arrayList.add(dANEEntry.getCertificate());
            }
        }
        return Collections.unmodifiableList(arrayList);
    }
}
