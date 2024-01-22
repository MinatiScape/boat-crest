package org.bouncycastle.asn1.bc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class ObjectStore extends ASN1Object {
    public final ASN1Encodable h;
    public final ObjectStoreIntegrityCheck i;

    public ObjectStore(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("malformed sequence");
        }
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(0);
        if (!(objectAt instanceof EncryptedObjectStoreData) && !(objectAt instanceof ObjectStoreData)) {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(objectAt);
            objectAt = aSN1Sequence2.size() == 2 ? EncryptedObjectStoreData.getInstance(aSN1Sequence2) : ObjectStoreData.getInstance(aSN1Sequence2);
        }
        this.h = objectAt;
        this.i = ObjectStoreIntegrityCheck.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public ObjectStore(EncryptedObjectStoreData encryptedObjectStoreData, ObjectStoreIntegrityCheck objectStoreIntegrityCheck) {
        this.h = encryptedObjectStoreData;
        this.i = objectStoreIntegrityCheck;
    }

    public ObjectStore(ObjectStoreData objectStoreData, ObjectStoreIntegrityCheck objectStoreIntegrityCheck) {
        this.h = objectStoreData;
        this.i = objectStoreIntegrityCheck;
    }

    public static ObjectStore getInstance(Object obj) {
        if (obj instanceof ObjectStore) {
            return (ObjectStore) obj;
        }
        if (obj != null) {
            return new ObjectStore(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ObjectStoreIntegrityCheck getIntegrityCheck() {
        return this.i;
    }

    public ASN1Encodable getStoreData() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        return new DERSequence(aSN1EncodableVector);
    }
}
