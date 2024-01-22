package com.crrepa.ble.conn.listener;
/* loaded from: classes9.dex */
public interface CRPTransListener {
    public static final int DOWNLOAD_FAILED = 3;
    public static final int TRANS_FILE_NULL = 1;
    public static final int TRANS_PROCESS_FAILED = 2;
    public static final int TRANS_TIMEOUT = 4;

    void onError(int i);

    void onTransCompleted();

    void onTransProgressChanged(int i);

    void onTransProgressStarting();
}
