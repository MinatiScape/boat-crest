package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.Iterable;
/* loaded from: classes12.dex */
public class RecipientInformationStore implements Iterable<RecipientInformation> {
    public final List h;
    public final Map i;

    public RecipientInformationStore(Collection<RecipientInformation> collection) {
        this.i = new HashMap();
        for (RecipientInformation recipientInformation : collection) {
            RecipientId rid = recipientInformation.getRID();
            ArrayList arrayList = (ArrayList) this.i.get(rid);
            if (arrayList == null) {
                arrayList = new ArrayList(1);
                this.i.put(rid, arrayList);
            }
            arrayList.add(recipientInformation);
        }
        this.h = new ArrayList(collection);
    }

    public RecipientInformationStore(RecipientInformation recipientInformation) {
        HashMap hashMap = new HashMap();
        this.i = hashMap;
        ArrayList arrayList = new ArrayList(1);
        this.h = arrayList;
        arrayList.add(recipientInformation);
        hashMap.put(recipientInformation.getRID(), arrayList);
    }

    public RecipientInformation get(RecipientId recipientId) {
        Collection<Recipient> recipients = getRecipients(recipientId);
        if (recipients.size() == 0) {
            return null;
        }
        return (RecipientInformation) recipients.iterator().next();
    }

    public Collection<RecipientInformation> getRecipients() {
        return new ArrayList(this.h);
    }

    public Collection<Recipient> getRecipients(RecipientId recipientId) {
        if (recipientId instanceof KeyTransRecipientId) {
            KeyTransRecipientId keyTransRecipientId = (KeyTransRecipientId) recipientId;
            X500Name issuer = keyTransRecipientId.getIssuer();
            byte[] subjectKeyIdentifier = keyTransRecipientId.getSubjectKeyIdentifier();
            if (issuer != null && subjectKeyIdentifier != null) {
                ArrayList arrayList = new ArrayList();
                Collection<Recipient> recipients = getRecipients(new KeyTransRecipientId(issuer, keyTransRecipientId.getSerialNumber()));
                if (recipients != null) {
                    arrayList.addAll(recipients);
                }
                Collection<Recipient> recipients2 = getRecipients(new KeyTransRecipientId(subjectKeyIdentifier));
                if (recipients2 != null) {
                    arrayList.addAll(recipients2);
                }
                return arrayList;
            }
        }
        ArrayList arrayList2 = (ArrayList) this.i.get(recipientId);
        return arrayList2 == null ? new ArrayList() : new ArrayList(arrayList2);
    }

    @Override // org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<RecipientInformation> iterator() {
        return getRecipients().iterator();
    }

    public int size() {
        return this.h.size();
    }
}
