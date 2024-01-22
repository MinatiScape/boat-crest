package org.bouncycastle.est;
/* loaded from: classes13.dex */
public interface ESTClientProvider {
    boolean isTrusted();

    ESTClient makeClient() throws ESTException;
}
