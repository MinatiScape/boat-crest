package com.google.android.youtube.player.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.youtube.player.internal.l;
import com.google.android.youtube.player.internal.r;
import com.google.android.youtube.player.internal.t;
/* loaded from: classes10.dex */
public final class o extends r<l> implements b {
    public final String k;
    public final String l;
    public final String m;
    public boolean n;

    public o(Context context, String str, String str2, String str3, t.a aVar, t.b bVar) {
        super(context, aVar, bVar);
        this.k = (String) ab.a(str);
        this.l = ab.a(str2, (Object) "callingPackage cannot be null or empty");
        this.m = ab.a(str3, (Object) "callingAppVersion cannot be null or empty");
    }

    @Override // com.google.android.youtube.player.internal.b
    public final IBinder a() {
        q();
        try {
            return j().a();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // com.google.android.youtube.player.internal.r
    public final /* synthetic */ l a(IBinder iBinder) {
        return l.a.a(iBinder);
    }

    @Override // com.google.android.youtube.player.internal.b
    public final k a(j jVar) {
        q();
        try {
            return j().a(jVar);
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // com.google.android.youtube.player.internal.r
    public final void a(i iVar, r.d dVar) throws RemoteException {
        iVar.a(dVar, 1202, this.l, this.m, this.k, null);
    }

    @Override // com.google.android.youtube.player.internal.b
    public final void a(boolean z) {
        if (f()) {
            try {
                j().a(z);
            } catch (RemoteException unused) {
            }
            this.n = true;
        }
    }

    @Override // com.google.android.youtube.player.internal.r
    public final String b() {
        return "com.google.android.youtube.player.internal.IYouTubeService";
    }

    @Override // com.google.android.youtube.player.internal.r
    public final String c() {
        return "com.google.android.youtube.api.service.START";
    }

    @Override // com.google.android.youtube.player.internal.r, com.google.android.youtube.player.internal.t
    public final void d() {
        if (!this.n) {
            a(true);
        }
        super.d();
    }

    public final void q() {
        i();
        if (this.n) {
            throw new IllegalStateException("Connection client has been released");
        }
    }
}
