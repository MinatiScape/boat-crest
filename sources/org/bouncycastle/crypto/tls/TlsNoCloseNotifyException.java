package org.bouncycastle.crypto.tls;

import java.io.EOFException;
/* loaded from: classes13.dex */
public class TlsNoCloseNotifyException extends EOFException {
    public TlsNoCloseNotifyException() {
        super("No close_notify alert received before connection closed");
    }
}
