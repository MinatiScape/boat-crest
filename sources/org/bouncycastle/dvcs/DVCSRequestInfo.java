package org.bouncycastle.dvcs;

import java.math.BigInteger;
import java.util.Date;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformation;
import org.bouncycastle.asn1.dvcs.DVCSTime;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class DVCSRequestInfo {

    /* renamed from: a  reason: collision with root package name */
    public DVCSRequestInformation f14886a;

    public DVCSRequestInfo(DVCSRequestInformation dVCSRequestInformation) {
        this.f14886a = dVCSRequestInformation;
    }

    public DVCSRequestInfo(byte[] bArr) {
        this(DVCSRequestInformation.getInstance(bArr));
    }

    public static boolean a(Object obj, Object obj2) {
        return (obj == null && obj2 == null) || (obj != null && obj.equals(obj2));
    }

    public static boolean validate(DVCSRequestInfo dVCSRequestInfo, DVCSRequestInfo dVCSRequestInfo2) {
        DVCSRequestInformation dVCSRequestInformation = dVCSRequestInfo.f14886a;
        DVCSRequestInformation dVCSRequestInformation2 = dVCSRequestInfo2.f14886a;
        if (dVCSRequestInformation.getVersion() == dVCSRequestInformation2.getVersion() && a(dVCSRequestInformation.getService(), dVCSRequestInformation2.getService()) && a(dVCSRequestInformation.getRequestTime(), dVCSRequestInformation2.getRequestTime()) && a(dVCSRequestInformation.getRequestPolicy(), dVCSRequestInformation2.getRequestPolicy()) && a(dVCSRequestInformation.getExtensions(), dVCSRequestInformation2.getExtensions())) {
            if (dVCSRequestInformation.getNonce() != null) {
                if (dVCSRequestInformation2.getNonce() == null) {
                    return false;
                }
                byte[] byteArray = dVCSRequestInformation.getNonce().toByteArray();
                byte[] byteArray2 = dVCSRequestInformation2.getNonce().toByteArray();
                return byteArray2.length >= byteArray.length && Arrays.areEqual(byteArray, Arrays.copyOfRange(byteArray2, 0, byteArray.length));
            }
            return true;
        }
        return false;
    }

    public GeneralNames getDVCSNames() {
        return this.f14886a.getDVCS();
    }

    public GeneralNames getDataLocations() {
        return this.f14886a.getDataLocations();
    }

    public BigInteger getNonce() {
        return this.f14886a.getNonce();
    }

    public PolicyInformation getRequestPolicy() {
        if (this.f14886a.getRequestPolicy() != null) {
            return this.f14886a.getRequestPolicy();
        }
        return null;
    }

    public Date getRequestTime() throws DVCSParsingException {
        DVCSTime requestTime = this.f14886a.getRequestTime();
        if (requestTime == null) {
            return null;
        }
        try {
            return requestTime.getGenTime() != null ? requestTime.getGenTime().getDate() : new TimeStampToken(requestTime.getTimeStampToken()).getTimeStampInfo().getGenTime();
        } catch (Exception e) {
            throw new DVCSParsingException("unable to extract time: " + e.getMessage(), e);
        }
    }

    public GeneralNames getRequester() {
        return this.f14886a.getRequester();
    }

    public int getServiceType() {
        return this.f14886a.getService().getValue().intValue();
    }

    public int getVersion() {
        return this.f14886a.getVersion();
    }

    public DVCSRequestInformation toASN1Structure() {
        return this.f14886a;
    }
}
