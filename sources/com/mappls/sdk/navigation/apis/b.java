package com.mappls.sdk.navigation.apis;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.routing.h;
/* loaded from: classes11.dex */
public final class b implements AudioManager.OnAudioFocusChangeListener {
    public static boolean b = false;

    /* renamed from: a  reason: collision with root package name */
    public com.mappls.sdk.navigation.routing.d f12876a;

    public final boolean a(NavigationApplication navigationApplication, com.mappls.sdk.navigation.d dVar) {
        AudioManager audioManager = (AudioManager) navigationApplication.getSystemService("audio");
        this.f12876a = ((NavigationApplication) navigationApplication.getApplicationContext()).h();
        b = false;
        if (Build.VERSION.SDK_INT < 26) {
            return 1 == audioManager.abandonAudioFocus(this);
        }
        return 1 == audioManager.abandonAudioFocusRequest(new AudioFocusRequest.Builder(((NavigationApplication) navigationApplication.getApplicationContext()).k().t.a(dVar).booleanValue() ? 2 : 3).setAudioAttributes(new AudioAttributes.Builder().setUsage(((Integer) ((NavigationApplication) navigationApplication.getApplicationContext()).k().i0.get()).intValue()).setContentType(1).build()).setAcceptsDelayedFocusGain(false).setOnAudioFocusChangeListener(this).build());
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00aa, code lost:
        if (r8 == 2) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(com.mappls.sdk.navigation.NavigationApplication r8, com.mappls.sdk.navigation.d r9, int r10) {
        /*
            r7 = this;
            java.lang.String r0 = "audio"
            java.lang.Object r0 = r8.getSystemService(r0)
            android.media.AudioManager r0 = (android.media.AudioManager) r0
            android.content.Context r1 = r8.getApplicationContext()
            com.mappls.sdk.navigation.NavigationApplication r1 = (com.mappls.sdk.navigation.NavigationApplication) r1
            com.mappls.sdk.navigation.routing.d r1 = r1.h()
            r7.f12876a = r1
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 3
            r3 = 0
            r4 = 2
            r5 = 1
            r6 = 26
            if (r1 >= r6) goto L41
            android.content.Context r8 = r8.getApplicationContext()
            com.mappls.sdk.navigation.NavigationApplication r8 = (com.mappls.sdk.navigation.NavigationApplication) r8
            com.mappls.sdk.navigation.t r8 = r8.k()
            com.mappls.sdk.navigation.t$j<java.lang.Boolean> r8 = r8.t
            java.lang.Object r8 = r8.a(r9)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L37
            r2 = r4
        L37:
            int r8 = r0.requestAudioFocus(r7, r10, r2)
            if (r5 != r8) goto L3e
            r3 = r5
        L3e:
            com.mappls.sdk.navigation.apis.b.b = r3
            goto Laf
        L41:
            android.media.AudioAttributes$Builder r10 = new android.media.AudioAttributes$Builder
            r10.<init>()
            android.content.Context r1 = r8.getApplicationContext()
            com.mappls.sdk.navigation.NavigationApplication r1 = (com.mappls.sdk.navigation.NavigationApplication) r1
            com.mappls.sdk.navigation.t r1 = r1.k()
            com.mappls.sdk.navigation.t$j r1 = r1.i0
            java.lang.Object r1 = r1.get()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            android.media.AudioAttributes$Builder r10 = r10.setUsage(r1)
            android.media.AudioAttributes$Builder r10 = r10.setContentType(r5)
            android.media.AudioAttributes r10 = r10.build()
            android.media.AudioFocusRequest$Builder r1 = new android.media.AudioFocusRequest$Builder
            android.content.Context r8 = r8.getApplicationContext()
            com.mappls.sdk.navigation.NavigationApplication r8 = (com.mappls.sdk.navigation.NavigationApplication) r8
            com.mappls.sdk.navigation.t r8 = r8.k()
            com.mappls.sdk.navigation.t$j<java.lang.Boolean> r8 = r8.t
            java.lang.Object r8 = r8.a(r9)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L83
            r2 = r4
        L83:
            r1.<init>(r2)
            android.media.AudioFocusRequest$Builder r8 = r1.setAudioAttributes(r10)
            android.media.AudioFocusRequest$Builder r8 = r8.setAcceptsDelayedFocusGain(r3)
            android.media.AudioFocusRequest$Builder r8 = r8.setOnAudioFocusChangeListener(r7)
            android.media.AudioFocusRequest r8 = r8.build()
            java.lang.Object r9 = new java.lang.Object
            r9.<init>()
            int r8 = r0.requestAudioFocus(r8)
            monitor-enter(r9)
            if (r8 != r5) goto La7
            com.mappls.sdk.navigation.apis.b.b = r5     // Catch: java.lang.Throwable -> La5
            goto Lae
        La5:
            r8 = move-exception
            goto Lb2
        La7:
            if (r8 != 0) goto Laa
            goto Lac
        Laa:
            if (r8 != r4) goto Lae
        Lac:
            com.mappls.sdk.navigation.apis.b.b = r3     // Catch: java.lang.Throwable -> La5
        Lae:
            monitor-exit(r9)     // Catch: java.lang.Throwable -> La5
        Laf:
            boolean r8 = com.mappls.sdk.navigation.apis.b.b
            return r8
        Lb2:
            monitor-exit(r9)     // Catch: java.lang.Throwable -> La5
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.apis.b.a(com.mappls.sdk.navigation.NavigationApplication, com.mappls.sdk.navigation.d, int):boolean");
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public final void onAudioFocusChange(int i) {
        NavigationLogger.d("AudioFocusHelperImpl.onAudioFocusChange(): Unexpected audio focus change: " + i, new Object[0]);
        if (i == 1 || i == 2 || i == 3) {
            b = true;
        } else if (i == -1 || i == -2 || i == -3) {
            b = false;
            com.mappls.sdk.navigation.routing.d dVar = this.f12876a;
            if (dVar != null) {
                dVar.o().getClass();
                h.d();
            }
        }
    }
}
