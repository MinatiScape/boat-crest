package com.goodix.ble.libcomx.pool;
/* loaded from: classes5.dex */
public interface IRecyclable {
    int getRefCnt();

    void release();

    void retain();

    void reuse(Pool pool);
}
