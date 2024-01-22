package com.google.android.youtube.player.internal;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.android.youtube.player.internal.j;
/* loaded from: classes10.dex */
public final class p extends com.google.android.youtube.player.internal.a {
    public final Handler e;
    public b f;
    public k g;
    public boolean h;
    public boolean i;

    /* loaded from: classes10.dex */
    public final class a extends j.a {

        /* renamed from: com.google.android.youtube.player.internal.p$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class RunnableC0450a implements Runnable {
            public final /* synthetic */ boolean h;
            public final /* synthetic */ boolean i;
            public final /* synthetic */ Bitmap j;
            public final /* synthetic */ String k;

            public RunnableC0450a(boolean z, boolean z2, Bitmap bitmap, String str) {
                this.h = z;
                this.i = z2;
                this.j = bitmap;
                this.k = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                p.this.h = this.h;
                p.this.i = this.i;
                p.this.a(this.j, this.k);
            }
        }

        /* loaded from: classes10.dex */
        public class b implements Runnable {
            public final /* synthetic */ boolean h;
            public final /* synthetic */ boolean i;
            public final /* synthetic */ String j;

            public b(boolean z, boolean z2, String str) {
                this.h = z;
                this.i = z2;
                this.j = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                p.this.h = this.h;
                p.this.i = this.i;
                p.this.b(this.j);
            }
        }

        public a() {
        }

        public /* synthetic */ a(p pVar, byte b2) {
            this();
        }

        @Override // com.google.android.youtube.player.internal.j
        public final void a(Bitmap bitmap, String str, boolean z, boolean z2) {
            p.this.e.post(new RunnableC0450a(z, z2, bitmap, str));
        }

        @Override // com.google.android.youtube.player.internal.j
        public final void a(String str, boolean z, boolean z2) {
            p.this.e.post(new b(z, z2, str));
        }
    }

    public p(b bVar, YouTubeThumbnailView youTubeThumbnailView) {
        super(youTubeThumbnailView);
        this.f = (b) ab.a(bVar, "connectionClient cannot be null");
        this.g = bVar.a(new a(this, (byte) 0));
        this.e = new Handler(Looper.getMainLooper());
    }

    @Override // com.google.android.youtube.player.internal.a
    public final void a(String str) {
        try {
            this.g.a(str);
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // com.google.android.youtube.player.internal.a
    public final void a(String str, int i) {
        try {
            this.g.a(str, i);
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // com.google.android.youtube.player.internal.a
    public final boolean a() {
        return super.a() && this.g != null;
    }

    @Override // com.google.android.youtube.player.internal.a
    public final void c() {
        try {
            this.g.a();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // com.google.android.youtube.player.internal.a
    public final void d() {
        try {
            this.g.b();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // com.google.android.youtube.player.internal.a
    public final void e() {
        try {
            this.g.c();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // com.google.android.youtube.player.internal.a
    public final boolean f() {
        return this.i;
    }

    @Override // com.google.android.youtube.player.internal.a
    public final boolean g() {
        return this.h;
    }

    @Override // com.google.android.youtube.player.internal.a
    public final void h() {
        try {
            this.g.d();
        } catch (RemoteException unused) {
        }
        this.f.d();
        this.g = null;
        this.f = null;
    }
}
