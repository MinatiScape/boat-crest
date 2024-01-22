package com.jieli.bmp_convert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes11.dex */
public class BmpConvert {
    public static final int ERR_IO_EXCEPTION = -3586;
    public static final int ERR_NOT_FOUND_FILE = -3585;
    public static final int ERR_PARAM = -3584;
    private static final String TAG = "BmpConvert";
    public static final int TYPE_BR_23 = 0;
    public static final int TYPE_BR_28 = 1;
    public static final int TYPE_BR_28_ALPHA = 2;
    public static final int TYPE_BR_28_ALPHA_RAW = 4;
    public static final int TYPE_BR_28_RAW = 3;
    private final ExecutorService mThreadPool = Executors.newSingleThreadExecutor();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    static {
        try {
            System.loadLibrary("jl_bmp_convert");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bitmapConvert$2(final OnConvertListener onConvertListener, final String str, int i, final String str2) {
        if (onConvertListener != null) {
            this.mHandler.post(new Runnable() { // from class: com.jieli.bmp_convert.c
                @Override // java.lang.Runnable
                public final void run() {
                    OnConvertListener.this.onStart(str);
                }
            });
        }
        final int bitmapConvertBlock = bitmapConvertBlock(i, str, str2);
        if (onConvertListener != null) {
            this.mHandler.post(new Runnable() { // from class: com.jieli.bmp_convert.a
                @Override // java.lang.Runnable
                public final void run() {
                    BmpConvert.lambda$null$1(bitmapConvertBlock, onConvertListener, str2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$1(int i, OnConvertListener onConvertListener, String str) {
        if (i > 0) {
            onConvertListener.onStop(true, str);
        } else {
            onConvertListener.onStop(false, null);
        }
    }

    public void bitmapConvert(String str, String str2, OnConvertListener onConvertListener) {
        bitmapConvert(0, str, str2, onConvertListener);
    }

    public int bitmapConvertBlock(int i, String str, String str2) {
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            int height = decodeFile.getHeight() * decodeFile.getWidth();
            int[] iArr = new int[height];
            decodeFile.getPixels(iArr, 0, decodeFile.getWidth(), 0, 0, decodeFile.getWidth(), decodeFile.getHeight());
            byte[] bArr = new byte[height * 4];
            for (int i2 = 0; i2 < height; i2++) {
                int i3 = i2 << 2;
                bArr[i3] = (byte) (iArr[i2] & 255);
                bArr[i3 + 1] = (byte) ((iArr[i2] >> 8) & 255);
                bArr[i3 + 2] = (byte) ((iArr[i2] >> 16) & 255);
                bArr[i3 + 3] = (byte) ((iArr[i2] >> 24) & 255);
            }
            String str3 = str.substring(0, str.lastIndexOf(".")) + ".bin";
            File file2 = new File(str3);
            try {
                if (file2.exists() && !file2.delete()) {
                    Log.e(TAG, "bitmapConvertBlock : IO exception : delete." + str3);
                }
                if (!file2.createNewFile()) {
                    Log.e(TAG, "bitmapConvertBlock : IO exception : createNewFile." + str3);
                    return ERR_IO_EXCEPTION;
                }
                FileOutputStream fileOutputStream = new FileOutputStream(str3);
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                int bmpConvert_native = bmpConvert_native(i, str3, decodeFile.getWidth(), decodeFile.getHeight(), str2);
                if (bmpConvert_native > 0 && !file2.delete()) {
                    Log.e(TAG, "bitmapConvert: IO exception >> delete cache file failure." + str3);
                }
                return bmpConvert_native;
            } catch (IOException e) {
                e.printStackTrace();
                return ERR_IO_EXCEPTION;
            }
        }
        Log.e(TAG, "bitmapConvertBlock : file not found." + str);
        return ERR_NOT_FOUND_FILE;
    }

    public native int bmpConvert_native(int i, String str, int i2, int i3, String str2);

    public void release() {
        this.mThreadPool.shutdownNow();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void bitmapConvert(final int i, final String str, final String str2, final OnConvertListener onConvertListener) {
        if (i != 0 && i != 1 && i != 2 && i != 3 && i != 4) {
            throw new RuntimeException("illegal type");
        }
        if (this.mThreadPool.isShutdown()) {
            Log.e(TAG, "bitmapConvert : thread is exited.");
            if (onConvertListener != null) {
                onConvertListener.onStop(false, null);
                return;
            }
            return;
        }
        this.mThreadPool.submit(new Runnable() { // from class: com.jieli.bmp_convert.b
            @Override // java.lang.Runnable
            public final void run() {
                BmpConvert.this.lambda$bitmapConvert$2(onConvertListener, str, i, str2);
            }
        });
    }
}
