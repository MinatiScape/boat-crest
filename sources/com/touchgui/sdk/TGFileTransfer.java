package com.touchgui.sdk;

import java.io.File;
/* loaded from: classes12.dex */
public interface TGFileTransfer {
    public static final int FILE_TYPE_APOLLO_BOOTLOADER = 142;
    public static final int FILE_TYPE_A_BIN = 35;
    public static final int FILE_TYPE_B_BIN = 33;
    public static final int FILE_TYPE_DEFAULT = 255;
    public static final int FILE_TYPE_FONT = 17;
    public static final int FILE_TYPE_GPS = 20;
    public static final int FILE_TYPE_GPS_FIRMWARE = 36;
    public static final int FILE_TYPE_PICTURE_WATCH = 18;
    public static final int FILE_TYPE_PICTURE_WATCH_CFG = 19;
    public static final int FILE_TYPE_ST_BOOTLOADER = 143;

    /* loaded from: classes12.dex */
    public interface OnProgressListener {
        void onCompleted();

        void onError(Throwable th);

        void onProgress(int i, int i2, int i3);
    }

    void abortTransfer();

    void addOnProgressListener(OnProgressListener onProgressListener);

    void removeOnProgressListener(OnProgressListener onProgressListener);

    void setTotalFileSize(long j, TGCallback<Void> tGCallback);

    void transfer(File file, String str, int i);
}
