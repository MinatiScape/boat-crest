package com.crrepa.ble.conn.listener;
/* loaded from: classes9.dex */
public interface CRPFileTransListener {
    public static final int FILE_CRC_ERROR = 3;
    public static final int FILE_NULL = 1;
    public static final int FILE_TIMEOUT = 2;
    public static final int FILE_TRANS_ERROR = 4;

    void onError(int i);

    void onTransCompleted();

    void onTransProgressChanged(int i);

    void onTransProgressStarting();
}
