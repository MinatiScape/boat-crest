package com.crrepa.s;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.toolbox.JsonRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
/* loaded from: classes9.dex */
public abstract class a<T> {

    /* renamed from: a  reason: collision with root package name */
    public static Handler f7838a = new Handler(Looper.getMainLooper());

    /* renamed from: com.crrepa.s.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class RunnableC0356a implements Runnable {
        public final /* synthetic */ com.crrepa.r.c h;
        public final /* synthetic */ String i;

        public RunnableC0356a(com.crrepa.r.c cVar, String str) {
            this.h = cVar;
            this.i = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a(this.h.c, this.i);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ Object h;

        public b(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            a.this.a((a) this.h);
        }
    }

    public static String a(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, JsonRequest.PROTOCOL_CHARSET));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    inputStream.close();
                    return sb.toString();
                }
                sb.append(readLine + "\n");
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public void a(float f, long j) {
    }

    public abstract void a(int i, String str);

    public void a(com.crrepa.r.c cVar) {
        String a2;
        InputStream inputStream = cVar.f7835a;
        if (inputStream == null && (inputStream = cVar.b) == null) {
            Exception exc = cVar.e;
            a2 = exc != null ? exc.getMessage() : "";
        } else {
            a2 = a(inputStream);
        }
        f7838a.post(new RunnableC0356a(cVar, a2));
    }

    public abstract void a(T t);

    public abstract T b(com.crrepa.r.c cVar);

    public void c(com.crrepa.r.c cVar) {
        f7838a.post(new b(b(cVar)));
    }
}
