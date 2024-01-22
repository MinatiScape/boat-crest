package com.mappls.sdk.navigation.voice;

import android.speech.tts.TextToSpeech;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.h;
/* loaded from: classes11.dex */
public final class f implements TextToSpeech.OnUtteranceCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ g f13049a;

    public f(g gVar) {
        this.f13049a = gVar;
    }

    @Override // android.speech.tts.TextToSpeech.OnUtteranceCompletedListener
    public final synchronized void onUtteranceCompleted(String str) {
        int i;
        int i2;
        if (g.o() <= 0) {
            this.f13049a.a();
        }
        StringBuilder a2 = h.a("ttsRequests=");
        i = g.l;
        a2.append(i);
        NavigationLogger.d(a2.toString(), new Object[0]);
        i2 = g.l;
        if (i2 < 0) {
            g.l = 0;
        }
    }
}
