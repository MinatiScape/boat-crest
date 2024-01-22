package androidx.core.provider;

import android.graphics.Typeface;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.core.provider.FontsContractCompat;
import androidx.core.provider.e;
/* loaded from: classes.dex */
public class a {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final FontsContractCompat.FontRequestCallback f1085a;
    @NonNull
    public final Handler b;

    /* renamed from: androidx.core.provider.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0130a implements Runnable {
        public final /* synthetic */ FontsContractCompat.FontRequestCallback h;
        public final /* synthetic */ Typeface i;

        public RunnableC0130a(a aVar, FontsContractCompat.FontRequestCallback fontRequestCallback, Typeface typeface) {
            this.h = fontRequestCallback;
            this.i = typeface;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.onTypefaceRetrieved(this.i);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ FontsContractCompat.FontRequestCallback h;
        public final /* synthetic */ int i;

        public b(a aVar, FontsContractCompat.FontRequestCallback fontRequestCallback, int i) {
            this.h = fontRequestCallback;
            this.i = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.onTypefaceRequestFailed(this.i);
        }
    }

    public a(@NonNull FontsContractCompat.FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        this.f1085a = fontRequestCallback;
        this.b = handler;
    }

    public final void a(int i) {
        this.b.post(new b(this, this.f1085a, i));
    }

    public void b(@NonNull e.C0131e c0131e) {
        if (c0131e.a()) {
            c(c0131e.f1088a);
        } else {
            a(c0131e.b);
        }
    }

    public final void c(@NonNull Typeface typeface) {
        this.b.post(new RunnableC0130a(this, this.f1085a, typeface));
    }

    public a(@NonNull FontsContractCompat.FontRequestCallback fontRequestCallback) {
        this.f1085a = fontRequestCallback;
        this.b = androidx.core.provider.b.a();
    }
}
