package org.bouncycastle.cert.dane;
/* loaded from: classes12.dex */
public class DANEEntryStoreBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final DANEEntryFetcherFactory f14481a;

    public DANEEntryStoreBuilder(DANEEntryFetcherFactory dANEEntryFetcherFactory) {
        this.f14481a = dANEEntryFetcherFactory;
    }

    public DANEEntryStore build(String str) throws DANEException {
        return new DANEEntryStore(this.f14481a.build(str).getEntries());
    }
}
