package org.bouncycastle.cms;

import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class KEKRecipientId extends RecipientId {
    public byte[] i;

    public KEKRecipientId(byte[] bArr) {
        super(1);
        this.i = bArr;
    }

    @Override // org.bouncycastle.cms.RecipientId, org.bouncycastle.util.Selector
    public Object clone() {
        return new KEKRecipientId(this.i);
    }

    public boolean equals(Object obj) {
        if (obj instanceof KEKRecipientId) {
            return Arrays.areEqual(this.i, ((KEKRecipientId) obj).i);
        }
        return false;
    }

    public byte[] getKeyIdentifier() {
        return Arrays.clone(this.i);
    }

    public int hashCode() {
        return Arrays.hashCode(this.i);
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof byte[]) {
            return Arrays.areEqual(this.i, (byte[]) obj);
        }
        if (obj instanceof KEKRecipientInformation) {
            return ((KEKRecipientInformation) obj).getRID().equals(this);
        }
        return false;
    }
}
