package com.mappls.sdk.maps.http;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.mappls.sdk.maps.MapStrictMode;
import com.mappls.sdk.maps.Mappls;
import com.mappls.sdk.maps.log.Logger;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes11.dex */
public class LocalRequestTask extends AsyncTask<String, Void, byte[]> {

    /* renamed from: a  reason: collision with root package name */
    public OnLocalRequestResponse f12727a;

    /* loaded from: classes11.dex */
    public interface OnLocalRequestResponse {
        void onResponse(byte[] bArr);
    }

    public LocalRequestTask(OnLocalRequestResponse onLocalRequestResponse) {
        this.f12727a = onLocalRequestResponse;
    }

    @Nullable
    public static byte[] b(AssetManager assetManager, @NonNull String str) {
        byte[] bArr;
        InputStream open;
        InputStream inputStream = null;
        byte[] bArr2 = null;
        inputStream = null;
        try {
            try {
                open = assetManager.open(str);
            } catch (IOException e) {
                e = e;
                bArr = null;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bArr2 = new byte[open.available()];
            open.read(bArr2);
            try {
                open.close();
                return bArr2;
            } catch (IOException e2) {
                c(e2);
                return bArr2;
            }
        } catch (IOException e3) {
            e = e3;
            byte[] bArr3 = bArr2;
            inputStream = open;
            bArr = bArr3;
            c(e);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    c(e4);
                }
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            inputStream = open;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    c(e5);
                }
            }
            throw th;
        }
    }

    public static void c(Exception exc) {
        Logger.e("Mbgl-LocalRequestTask", "Load file failed", exc);
        MapStrictMode.strictModeViolation("Load file failed", exc);
    }

    @Override // android.os.AsyncTask
    @Nullable
    /* renamed from: a */
    public byte[] doInBackground(String... strArr) {
        AssetManager assets = Mappls.getApplicationContext().getAssets();
        return b(assets, "integration/" + strArr[0].substring(8).replaceAll("%20", HexStringBuilder.DEFAULT_SEPARATOR).replaceAll("%2c", Constants.SEPARATOR_COMMA));
    }

    @Override // android.os.AsyncTask
    /* renamed from: d */
    public void onPostExecute(@Nullable byte[] bArr) {
        OnLocalRequestResponse onLocalRequestResponse;
        super.onPostExecute(bArr);
        if (bArr == null || (onLocalRequestResponse = this.f12727a) == null) {
            return;
        }
        onLocalRequestResponse.onResponse(bArr);
    }
}
