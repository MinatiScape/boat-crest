package com.google.crypto.tink;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes10.dex */
public final class KmsClients {

    /* renamed from: a  reason: collision with root package name */
    public static List<KmsClient> f10827a;
    public static final CopyOnWriteArrayList<KmsClient> b = new CopyOnWriteArrayList<>();

    public static List<KmsClient> a() {
        ArrayList arrayList = new ArrayList();
        Iterator it = ServiceLoader.load(KmsClient.class).iterator();
        while (it.hasNext()) {
            arrayList.add((KmsClient) it.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static void add(KmsClient kmsClient) {
        b.add(kmsClient);
    }

    public static KmsClient get(String str) throws GeneralSecurityException {
        Iterator<KmsClient> it = b.iterator();
        while (it.hasNext()) {
            KmsClient next = it.next();
            if (next.doesSupport(str)) {
                return next;
            }
        }
        throw new GeneralSecurityException("No KMS client does support: " + str);
    }

    public static synchronized KmsClient getAutoLoaded(String str) throws GeneralSecurityException {
        KmsClient next;
        synchronized (KmsClients.class) {
            if (f10827a == null) {
                f10827a = a();
            }
            Iterator<KmsClient> it = f10827a.iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next.doesSupport(str)) {
                }
            }
            throw new GeneralSecurityException("No KMS client does support: " + str);
        }
        return next;
    }
}
