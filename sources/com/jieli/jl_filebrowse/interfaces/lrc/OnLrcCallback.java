package com.jieli.jl_filebrowse.interfaces.lrc;
/* loaded from: classes11.dex */
public interface OnLrcCallback {
    void onLrcDataReceive(byte[] bArr);

    void onLrcReadFailed(int i);

    void onLrcReadStart();

    void onLrcReadStop();
}
