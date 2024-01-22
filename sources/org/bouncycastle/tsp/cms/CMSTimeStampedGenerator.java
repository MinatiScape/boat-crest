package org.bouncycastle.tsp.cms;

import java.net.URI;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.MetaData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes13.dex */
public class CMSTimeStampedGenerator {
    public URI dataUri;
    public MetaData metaData;

    public final void a(boolean z, DERUTF8String dERUTF8String, DERIA5String dERIA5String, Attributes attributes) {
        this.metaData = new MetaData(ASN1Boolean.getInstance(z), dERUTF8String, dERIA5String, attributes);
    }

    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        new a(this.metaData).e(digestCalculator);
    }

    public void setDataUri(URI uri) {
        this.dataUri = uri;
    }

    public void setMetaData(boolean z, String str, String str2) {
        setMetaData(z, str, str2, null);
    }

    public void setMetaData(boolean z, String str, String str2, Attributes attributes) {
        a(z, str != null ? new DERUTF8String(str) : null, str2 != null ? new DERIA5String(str2) : null, attributes);
    }
}
