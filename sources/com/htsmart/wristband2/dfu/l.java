package com.htsmart.wristband2.dfu;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.htsmart.wristband2.utils.Utils;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes11.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12005a = 1001;
    public static final int b = 1002;
    public static final int c = 1003;
    public static final int d = 1004;
    public static final int e = 1005;
    public static final int f = 1006;
    public c g;
    public final Context i;
    public volatile Call k;
    public int j = 20;
    public final b h = new b(Looper.getMainLooper(), this, null);

    /* loaded from: classes11.dex */
    public class a implements Callback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f12006a;

        public a(String str) {
            this.f12006a = str;
        }

        @Override // okhttp3.Callback
        public void onFailure(@NonNull Call call, @NonNull IOException iOException) {
            l.this.h.b(1004);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00aa A[Catch: all -> 0x00cf, TryCatch #7 {all -> 0x00cf, blocks: (B:10:0x003c, B:11:0x003e, B:13:0x0045, B:15:0x0050, B:17:0x0063, B:16:0x005a, B:18:0x0068, B:20:0x0075, B:21:0x0081, B:35:0x009f, B:37:0x00aa, B:38:0x00b4), top: B:63:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00b4 A[Catch: all -> 0x00cf, TRY_LEAVE, TryCatch #7 {all -> 0x00cf, blocks: (B:10:0x003c, B:11:0x003e, B:13:0x0045, B:15:0x0050, B:17:0x0063, B:16:0x005a, B:18:0x0068, B:20:0x0075, B:21:0x0081, B:35:0x009f, B:37:0x00aa, B:38:0x00b4), top: B:63:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00bf A[Catch: IOException -> 0x00c3, TRY_ENTER, TryCatch #4 {IOException -> 0x00c3, blocks: (B:22:0x008a, B:40:0x00bf, B:44:0x00c7), top: B:61:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00c7 A[Catch: IOException -> 0x00c3, TRY_LEAVE, TryCatch #4 {IOException -> 0x00c3, blocks: (B:22:0x008a, B:40:0x00bf, B:44:0x00c7), top: B:61:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v0 */
        /* JADX WARN: Type inference failed for: r0v1 */
        /* JADX WARN: Type inference failed for: r0v18 */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.io.FileOutputStream] */
        @Override // okhttp3.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onResponse(@androidx.annotation.NonNull okhttp3.Call r13, @androidx.annotation.NonNull okhttp3.Response r14) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 228
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htsmart.wristband2.dfu.l.a.onResponse(okhttp3.Call, okhttp3.Response):void");
        }
    }

    /* loaded from: classes11.dex */
    public static class b extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<l> f12007a;

        public b(Looper looper, l lVar) {
            super(looper);
            this.f12007a = new WeakReference<>(lVar);
        }

        public /* synthetic */ b(Looper looper, l lVar, a aVar) {
            this(looper, lVar);
        }

        public final void a() {
            sendEmptyMessage(4);
        }

        public final void b(int i) {
            Message obtainMessage = obtainMessage(3);
            obtainMessage.arg1 = i;
            obtainMessage.sendToTarget();
        }

        public final void f(String str) {
            Message obtainMessage = obtainMessage(2);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }

        public final void g(int i) {
            Message obtainMessage = obtainMessage(1);
            obtainMessage.arg1 = i;
            obtainMessage.sendToTarget();
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            l lVar = this.f12007a.get();
            if (lVar == null || lVar.g == null) {
                return;
            }
            int i = message.what;
            if (i == 1) {
                lVar.g.b(message.arg1);
            } else if (i == 2) {
                lVar.g.a((String) message.obj);
            } else if (i == 3) {
                lVar.g.a(message.arg1);
            } else if (i != 4) {
            } else {
                lVar.g.a();
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void a();

        void a(int i);

        void a(@NonNull String str);

        void b(int i);
    }

    /* loaded from: classes11.dex */
    public static class d implements c {
        @Override // com.htsmart.wristband2.dfu.l.c
        public void a() {
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void a(int i) {
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void a(@NonNull String str) {
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void b(int i) {
        }
    }

    public l(Context context) {
        this.i = context.getApplicationContext();
    }

    public static double a(@NonNull File file) {
        StatFs statFs;
        try {
            statFs = new StatFs(file.getAbsolutePath());
        } catch (Exception e2) {
            e2.printStackTrace();
            statFs = null;
        }
        if (statFs == null) {
            return 0.0d;
        }
        return (((float) (statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong())) / 1024.0f) / 1024.0f;
    }

    public static String g(@NonNull String str) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                int i2 = digest[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    sb.append(BleConst.GetDeviceTime);
                }
                sb.append(Integer.toHexString(i2));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public void a() {
        if (this.k != null) {
            this.k.cancel();
            this.k = null;
        }
    }

    public void a(int i) {
        this.j = i;
    }

    public void a(c cVar) {
        this.g = cVar;
    }

    public void b() {
        this.g = null;
        a();
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str) || !(str.startsWith("http") || str.startsWith("https"))) {
            this.h.b(1001);
            return;
        }
        File[] externalFilesDirs = Utils.getExternalFilesDirs(this.i, Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDirs.length <= 0 || externalFilesDirs[0] == null) {
            this.h.b(1002);
            return;
        }
        File file = externalFilesDirs[0];
        if (!file.exists() && !file.mkdirs()) {
            this.h.b(1003);
        } else if (a(file) < this.j) {
            this.h.b(1005);
        } else {
            File file2 = new File(file, c(str));
            if (file2.exists() && file2.length() > PlaybackStateCompat.ACTION_PREPARE_FROM_URI) {
                this.h.f(file2.getAbsolutePath());
                return;
            }
            a();
            d(str, file2.getAbsolutePath());
        }
    }

    public final String c(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("url can't be empty");
        }
        String substring = str.substring(str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) + 1, str.length());
        int lastIndexOf = substring.lastIndexOf(".");
        String substring2 = lastIndexOf != -1 ? substring.substring(lastIndexOf, substring.length()) : "";
        return g(str) + substring2;
    }

    public final void d(@NonNull String str, @NonNull String str2) {
        this.k = new OkHttpClient().newCall(new Request.Builder().url(str).build());
        this.k.enqueue(new a(str2));
    }
}
