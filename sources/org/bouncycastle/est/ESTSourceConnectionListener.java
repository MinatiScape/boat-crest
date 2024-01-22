package org.bouncycastle.est;

import java.io.IOException;
/* loaded from: classes13.dex */
public interface ESTSourceConnectionListener<T, I> {
    ESTRequest onConnection(Source<T> source, ESTRequest eSTRequest) throws IOException;
}
