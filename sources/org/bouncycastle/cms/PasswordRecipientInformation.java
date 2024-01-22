package org.bouncycastle.cms;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Integers;
/* loaded from: classes12.dex */
public class PasswordRecipientInformation extends RecipientInformation {
    public static Map e = new HashMap();
    public static Map f;
    public PasswordRecipientInfo d;

    static {
        HashMap hashMap = new HashMap();
        f = hashMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = CMSAlgorithm.DES_EDE3_CBC;
        hashMap.put(aSN1ObjectIdentifier, Integers.valueOf(8));
        Map map = f;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = CMSAlgorithm.AES128_CBC;
        map.put(aSN1ObjectIdentifier2, Integers.valueOf(16));
        Map map2 = f;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = CMSAlgorithm.AES192_CBC;
        map2.put(aSN1ObjectIdentifier3, Integers.valueOf(16));
        Map map3 = f;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = CMSAlgorithm.AES256_CBC;
        map3.put(aSN1ObjectIdentifier4, Integers.valueOf(16));
        e.put(aSN1ObjectIdentifier, Integers.valueOf(192));
        e.put(aSN1ObjectIdentifier2, Integers.valueOf(128));
        e.put(aSN1ObjectIdentifier3, Integers.valueOf(192));
        e.put(aSN1ObjectIdentifier4, Integers.valueOf(256));
    }

    public PasswordRecipientInformation(PasswordRecipientInfo passwordRecipientInfo, AlgorithmIdentifier algorithmIdentifier, e eVar, a aVar) {
        super(passwordRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, eVar, aVar);
        this.d = passwordRecipientInfo;
        this.rid = new PasswordRecipientId();
    }

    public String getKeyDerivationAlgOID() {
        if (this.d.getKeyDerivationAlgorithm() != null) {
            return this.d.getKeyDerivationAlgorithm().getAlgorithm().getId();
        }
        return null;
    }

    public byte[] getKeyDerivationAlgParams() {
        ASN1Encodable parameters;
        try {
            if (this.d.getKeyDerivationAlgorithm() == null || (parameters = this.d.getKeyDerivationAlgorithm().getParameters()) == null) {
                return null;
            }
            return parameters.toASN1Primitive().getEncoded();
        } catch (Exception e2) {
            throw new RuntimeException("exception getting encryption parameters " + e2);
        }
    }

    public AlgorithmIdentifier getKeyDerivationAlgorithm() {
        return this.d.getKeyDerivationAlgorithm();
    }

    @Override // org.bouncycastle.cms.RecipientInformation
    public RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException, IOException {
        PasswordRecipient passwordRecipient = (PasswordRecipient) recipient;
        AlgorithmIdentifier algorithmIdentifier = AlgorithmIdentifier.getInstance(AlgorithmIdentifier.getInstance(this.d.getKeyEncryptionAlgorithm()).getParameters());
        return passwordRecipient.getRecipientOperator(algorithmIdentifier, this.messageAlgorithm, passwordRecipient.calculateDerivedKey(passwordRecipient.getPasswordConversionScheme(), getKeyDerivationAlgorithm(), ((Integer) e.get(algorithmIdentifier.getAlgorithm())).intValue()), this.d.getEncryptedKey().getOctets());
    }
}
