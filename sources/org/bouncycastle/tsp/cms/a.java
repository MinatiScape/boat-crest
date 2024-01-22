package org.bouncycastle.tsp.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.MetaData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes13.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final MetaData f15393a;

    public a(MetaData metaData) {
        this.f15393a = metaData;
    }

    public final String a(ASN1String aSN1String) {
        if (aSN1String != null) {
            return aSN1String.toString();
        }
        return null;
    }

    public String b() {
        MetaData metaData = this.f15393a;
        if (metaData != null) {
            return a(metaData.getFileName());
        }
        return null;
    }

    public String c() {
        MetaData metaData = this.f15393a;
        if (metaData != null) {
            return a(metaData.getMediaType());
        }
        return null;
    }

    public Attributes d() {
        MetaData metaData = this.f15393a;
        if (metaData != null) {
            return metaData.getOtherMetaData();
        }
        return null;
    }

    public void e(DigestCalculator digestCalculator) throws CMSException {
        MetaData metaData = this.f15393a;
        if (metaData == null || !metaData.isHashProtected()) {
            return;
        }
        try {
            digestCalculator.getOutputStream().write(this.f15393a.getEncoded(ASN1Encoding.DER));
        } catch (IOException e) {
            throw new CMSException("unable to initialise calculator from metaData: " + e.getMessage(), e);
        }
    }
}
