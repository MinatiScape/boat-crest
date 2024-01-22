package com.google.firebase.messaging;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_messaging.zzl;
import com.google.android.gms.internal.firebase_messaging.zzm;
import com.google.android.gms.internal.firebase_messaging.zzt;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public class f0 implements Closeable {
    public final URL h;
    @Nullable
    public Task<Bitmap> i;
    @Nullable
    public volatile InputStream j;

    public f0(URL url) {
        this.h = url;
    }

    @Nullable
    public static f0 c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new f0(new URL(str));
        } catch (MalformedURLException unused) {
            String valueOf = String.valueOf(str);
            Log.w(Constants.TAG, valueOf.length() != 0 ? "Not downloading image, bad URL: ".concat(valueOf) : new String("Not downloading image, bad URL: "));
            return null;
        }
    }

    public Bitmap a() throws IOException {
        String valueOf = String.valueOf(this.h);
        StringBuilder sb = new StringBuilder(valueOf.length() + 22);
        sb.append("Starting download of: ");
        sb.append(valueOf);
        Log.i(Constants.TAG, sb.toString());
        byte[] b = b();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(b, 0, b.length);
        if (decodeByteArray != null) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                String valueOf2 = String.valueOf(this.h);
                StringBuilder sb2 = new StringBuilder(valueOf2.length() + 31);
                sb2.append("Successfully downloaded image: ");
                sb2.append(valueOf2);
                Log.d(Constants.TAG, sb2.toString());
            }
            return decodeByteArray;
        }
        String valueOf3 = String.valueOf(this.h);
        StringBuilder sb3 = new StringBuilder(valueOf3.length() + 24);
        sb3.append("Failed to decode image: ");
        sb3.append(valueOf3);
        throw new IOException(sb3.toString());
    }

    public final byte[] b() throws IOException {
        URLConnection openConnection = this.h.openConnection();
        if (openConnection.getContentLength() <= 1048576) {
            InputStream inputStream = openConnection.getInputStream();
            try {
                this.j = inputStream;
                byte[] zza = zzl.zza(zzl.zzb(inputStream, 1048577L));
                if (inputStream != null) {
                    inputStream.close();
                }
                if (Log.isLoggable(Constants.TAG, 2)) {
                    String valueOf = String.valueOf(this.h);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 34);
                    sb.append("Downloaded ");
                    sb.append(zza.length);
                    sb.append(" bytes from ");
                    sb.append(valueOf);
                    Log.v(Constants.TAG, sb.toString());
                }
                if (zza.length <= 1048576) {
                    return zza;
                }
                throw new IOException("Image exceeds max size of 1048576");
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th2) {
                        zzt.zza(th, th2);
                    }
                }
                throw th;
            }
        }
        throw new IOException("Content-Length exceeds max size of 1048576");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            zzm.zza(this.j);
        } catch (NullPointerException e) {
            Log.e(Constants.TAG, "Failed to close the image download stream.", e);
        }
    }

    public Task<Bitmap> d() {
        return (Task) Preconditions.checkNotNull(this.i);
    }

    public void e(Executor executor) {
        this.i = Tasks.call(executor, new Callable(this) { // from class: com.google.firebase.messaging.e0
            public final f0 h;

            {
                this.h = this;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                return this.h.a();
            }
        });
    }
}
