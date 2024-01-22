package com.ido.ble.dfu.nodic.firmware;

import com.ido.ble.dfu.nodic.firmware.FirmwareListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* loaded from: classes11.dex */
public class c {
    public static void a(CheckNewVersionPara checkNewVersionPara, FirmwareListener.ICheckNewVersionListener iCheckNewVersionListener) {
        a.a(checkNewVersionPara, iCheckNewVersionListener);
    }

    public static void a(String str, String str2, String str3, FirmwareListener.IDownloadListener iDownloadListener) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url(str).build();
        try {
            String str4 = str2 + File.separator + str3;
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            iDownloadListener.onStart();
            Response execute = okHttpClient.newCall(build).execute();
            if (!execute.isSuccessful()) {
                return;
            }
            ResponseBody body = execute.body();
            long contentLength = body.contentLength();
            long j = 0;
            InputStream byteStream = body.byteStream();
            FileOutputStream fileOutputStream = new FileOutputStream(str4);
            byte[] bArr = new byte[512];
            while (true) {
                int read = byteStream.read(bArr);
                if (read == -1) {
                    fileOutputStream.close();
                    iDownloadListener.onSuccess();
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
                j += read;
                iDownloadListener.onProgress(Math.round(((float) (100 * j)) / (((float) contentLength) * 1.0f)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            iDownloadListener.onFailed();
        }
    }
}
