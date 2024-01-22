package org.bouncycastle.tsp;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.tsp.Accuracy;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
/* loaded from: classes13.dex */
public class TimeStampTokenInfo {

    /* renamed from: a  reason: collision with root package name */
    public TSTInfo f15390a;
    public Date b;

    public TimeStampTokenInfo(TSTInfo tSTInfo) throws TSPException, IOException {
        this.f15390a = tSTInfo;
        try {
            this.b = tSTInfo.getGenTime().getDate();
        } catch (ParseException unused) {
            throw new TSPException("unable to parse genTime field");
        }
    }

    public Accuracy getAccuracy() {
        return this.f15390a.getAccuracy();
    }

    public byte[] getEncoded() throws IOException {
        return this.f15390a.getEncoded();
    }

    public Extensions getExtensions() {
        return this.f15390a.getExtensions();
    }

    public Date getGenTime() {
        return this.b;
    }

    public GenTimeAccuracy getGenTimeAccuracy() {
        if (getAccuracy() != null) {
            return new GenTimeAccuracy(getAccuracy());
        }
        return null;
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.f15390a.getMessageImprint().getHashAlgorithm();
    }

    public ASN1ObjectIdentifier getMessageImprintAlgOID() {
        return this.f15390a.getMessageImprint().getHashAlgorithm().getAlgorithm();
    }

    public byte[] getMessageImprintDigest() {
        return this.f15390a.getMessageImprint().getHashedMessage();
    }

    public BigInteger getNonce() {
        if (this.f15390a.getNonce() != null) {
            return this.f15390a.getNonce().getValue();
        }
        return null;
    }

    public ASN1ObjectIdentifier getPolicy() {
        return this.f15390a.getPolicy();
    }

    public BigInteger getSerialNumber() {
        return this.f15390a.getSerialNumber().getValue();
    }

    public GeneralName getTsa() {
        return this.f15390a.getTsa();
    }

    public boolean isOrdered() {
        return this.f15390a.getOrdering().isTrue();
    }

    public TSTInfo toASN1Structure() {
        return this.f15390a;
    }

    public TSTInfo toTSTInfo() {
        return this.f15390a;
    }
}
