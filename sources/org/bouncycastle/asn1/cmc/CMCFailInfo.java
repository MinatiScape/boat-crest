package org.bouncycastle.asn1.cmc;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
/* loaded from: classes12.dex */
public class CMCFailInfo extends ASN1Object {
    public static final CMCFailInfo authDataFail;
    public static final CMCFailInfo badAlg;
    public static final CMCFailInfo badCertId;
    public static final CMCFailInfo badIdentity;
    public static final CMCFailInfo badMessageCheck;
    public static final CMCFailInfo badRequest;
    public static final CMCFailInfo badTime;
    public static Map i;
    public static final CMCFailInfo internalCAError;
    public static final CMCFailInfo mustArchiveKeys;
    public static final CMCFailInfo noKeyReuse;
    public static final CMCFailInfo popFailed;
    public static final CMCFailInfo popRequired;
    public static final CMCFailInfo tryLater;
    public static final CMCFailInfo unsupportedExt;
    public final ASN1Integer h;

    static {
        CMCFailInfo cMCFailInfo = new CMCFailInfo(new ASN1Integer(0L));
        badAlg = cMCFailInfo;
        CMCFailInfo cMCFailInfo2 = new CMCFailInfo(new ASN1Integer(1L));
        badMessageCheck = cMCFailInfo2;
        CMCFailInfo cMCFailInfo3 = new CMCFailInfo(new ASN1Integer(2L));
        badRequest = cMCFailInfo3;
        CMCFailInfo cMCFailInfo4 = new CMCFailInfo(new ASN1Integer(3L));
        badTime = cMCFailInfo4;
        CMCFailInfo cMCFailInfo5 = new CMCFailInfo(new ASN1Integer(4L));
        badCertId = cMCFailInfo5;
        CMCFailInfo cMCFailInfo6 = new CMCFailInfo(new ASN1Integer(5L));
        unsupportedExt = cMCFailInfo6;
        CMCFailInfo cMCFailInfo7 = new CMCFailInfo(new ASN1Integer(6L));
        mustArchiveKeys = cMCFailInfo7;
        CMCFailInfo cMCFailInfo8 = new CMCFailInfo(new ASN1Integer(7L));
        badIdentity = cMCFailInfo8;
        CMCFailInfo cMCFailInfo9 = new CMCFailInfo(new ASN1Integer(8L));
        popRequired = cMCFailInfo9;
        CMCFailInfo cMCFailInfo10 = new CMCFailInfo(new ASN1Integer(9L));
        popFailed = cMCFailInfo10;
        CMCFailInfo cMCFailInfo11 = new CMCFailInfo(new ASN1Integer(10L));
        noKeyReuse = cMCFailInfo11;
        CMCFailInfo cMCFailInfo12 = new CMCFailInfo(new ASN1Integer(11L));
        internalCAError = cMCFailInfo12;
        CMCFailInfo cMCFailInfo13 = new CMCFailInfo(new ASN1Integer(12L));
        tryLater = cMCFailInfo13;
        CMCFailInfo cMCFailInfo14 = new CMCFailInfo(new ASN1Integer(13L));
        authDataFail = cMCFailInfo14;
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put(cMCFailInfo.h, cMCFailInfo);
        i.put(cMCFailInfo2.h, cMCFailInfo2);
        i.put(cMCFailInfo3.h, cMCFailInfo3);
        i.put(cMCFailInfo4.h, cMCFailInfo4);
        i.put(cMCFailInfo5.h, cMCFailInfo5);
        i.put(cMCFailInfo9.h, cMCFailInfo9);
        i.put(cMCFailInfo6.h, cMCFailInfo6);
        i.put(cMCFailInfo7.h, cMCFailInfo7);
        i.put(cMCFailInfo8.h, cMCFailInfo8);
        i.put(cMCFailInfo9.h, cMCFailInfo9);
        i.put(cMCFailInfo10.h, cMCFailInfo10);
        i.put(cMCFailInfo5.h, cMCFailInfo5);
        i.put(cMCFailInfo9.h, cMCFailInfo9);
        i.put(cMCFailInfo11.h, cMCFailInfo11);
        i.put(cMCFailInfo12.h, cMCFailInfo12);
        i.put(cMCFailInfo13.h, cMCFailInfo13);
        i.put(cMCFailInfo14.h, cMCFailInfo14);
    }

    public CMCFailInfo(ASN1Integer aSN1Integer) {
        this.h = aSN1Integer;
    }

    public static CMCFailInfo getInstance(Object obj) {
        if (obj instanceof CMCFailInfo) {
            return (CMCFailInfo) obj;
        }
        if (obj != null) {
            CMCFailInfo cMCFailInfo = (CMCFailInfo) i.get(ASN1Integer.getInstance(obj));
            if (cMCFailInfo != null) {
                return cMCFailInfo;
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + obj.getClass().getName());
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
