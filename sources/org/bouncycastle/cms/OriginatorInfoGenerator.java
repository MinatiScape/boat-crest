package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Store;
/* loaded from: classes12.dex */
public class OriginatorInfoGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final List f14539a;
    public final List b;

    public OriginatorInfoGenerator(X509CertificateHolder x509CertificateHolder) {
        ArrayList arrayList = new ArrayList(1);
        this.f14539a = arrayList;
        this.b = null;
        arrayList.add(x509CertificateHolder.toASN1Structure());
    }

    public OriginatorInfoGenerator(Store store) throws CMSException {
        this(store, null);
    }

    public OriginatorInfoGenerator(Store store, Store store2) throws CMSException {
        this.f14539a = g.h(store);
        this.b = store2 != null ? g.g(store2) : null;
    }

    public OriginatorInformation generate() {
        return this.b != null ? new OriginatorInformation(new OriginatorInfo(g.e(this.f14539a), g.e(this.b))) : new OriginatorInformation(new OriginatorInfo(g.e(this.f14539a), null));
    }
}
