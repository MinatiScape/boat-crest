package com.mappls.sdk.navigation.voice;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class e implements TextToSpeech.OnInitListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Locale f13048a;
    public final /* synthetic */ float b;
    public final /* synthetic */ g c;

    public e(g gVar, Locale locale, Context context, float f) {
        this.c = gVar;
        this.f13048a = locale;
        this.b = f;
    }

    public static void a() {
        TextToSpeech textToSpeech;
        TextToSpeech textToSpeech2;
        TextToSpeech textToSpeech3;
        TextToSpeech textToSpeech4;
        try {
            textToSpeech = g.j;
            if (textToSpeech == null) {
                return;
            }
            if (Build.VERSION.SDK_INT < 21) {
                textToSpeech2 = g.j;
                Objects.toString(textToSpeech2.getLanguage());
                return;
            }
            textToSpeech3 = g.j;
            if (textToSpeech3.getVoice() != null) {
                textToSpeech4 = g.j;
                textToSpeech4.getVoice().toString();
            }
        } catch (RuntimeException e) {
            NavigationLogger.d(e);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:11|(2:13|(8:15|(1:37)|17|18|19|(1:21)|22|(1:24)(3:28|26|27)))(1:43)|38|(1:40)(1:42)|41|17|18|19|(0)|22|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0082, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0083, code lost:
        com.mappls.sdk.navigation.apis.NavigationLogger.d(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0086, code lost:
        r3 = com.mappls.sdk.navigation.voice.g.j;
        r3.setLanguage(java.util.Locale.getDefault());
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0092, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0093, code lost:
        com.mappls.sdk.navigation.apis.NavigationLogger.e(r3);
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c2  */
    @Override // android.speech.tts.TextToSpeech.OnInitListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onInit(int r3) {
        /*
            r2 = this;
            if (r3 == 0) goto Le
            java.lang.String r3 = "NO INIT SUCCESS"
            com.mappls.sdk.navigation.voice.g.f(r3)
            com.mappls.sdk.navigation.voice.g r3 = r2.c
            com.mappls.sdk.navigation.voice.g.e(r3)
            goto Lf5
        Le:
            android.speech.tts.TextToSpeech r3 = com.mappls.sdk.navigation.voice.g.l()
            if (r3 == 0) goto Lf5
            com.mappls.sdk.navigation.voice.g r3 = r2.c
            com.mappls.sdk.navigation.voice.g.g(r3)
            android.speech.tts.TextToSpeech r3 = com.mappls.sdk.navigation.voice.g.l()
            java.util.Locale r0 = r2.f13048a
            int r3 = r3.isLanguageAvailable(r0)
            r0 = -2
            if (r3 == r0) goto Ld8
            r0 = -1
            if (r3 == r0) goto Lc7
            java.lang.String r0 = ""
            if (r3 == 0) goto L35
            r1 = 1
            if (r3 == r1) goto L4f
            r1 = 2
            if (r3 == r1) goto L78
            goto Lf5
        L35:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.util.Locale r1 = r2.f13048a
            java.lang.String r1 = r1.getDisplayName()
            r3.append(r1)
            java.lang.String r1 = ": LANG_AVAILABLE"
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            com.mappls.sdk.navigation.voice.g.f(r3)
        L4f:
            java.lang.String r3 = com.mappls.sdk.navigation.voice.g.k()
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L71
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.util.Locale r1 = r2.f13048a
            java.lang.String r1 = r1.getDisplayName()
            r3.append(r1)
            java.lang.String r1 = ": LANG_COUNTRY_AVAILABLE"
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            goto L75
        L71:
            java.lang.String r3 = com.mappls.sdk.navigation.voice.g.k()
        L75:
            com.mappls.sdk.navigation.voice.g.f(r3)
        L78:
            android.speech.tts.TextToSpeech r3 = com.mappls.sdk.navigation.voice.g.l()     // Catch: java.lang.Exception -> L82
            java.util.Locale r1 = r2.f13048a     // Catch: java.lang.Exception -> L82
            r3.setLanguage(r1)     // Catch: java.lang.Exception -> L82
            goto L96
        L82:
            r3 = move-exception
            com.mappls.sdk.navigation.apis.NavigationLogger.d(r3)
            android.speech.tts.TextToSpeech r3 = com.mappls.sdk.navigation.voice.g.l()     // Catch: java.lang.Exception -> L92
            java.util.Locale r1 = java.util.Locale.getDefault()     // Catch: java.lang.Exception -> L92
            r3.setLanguage(r1)     // Catch: java.lang.Exception -> L92
            goto L96
        L92:
            r3 = move-exception
            com.mappls.sdk.navigation.apis.NavigationLogger.e(r3)
        L96:
            float r3 = r2.b
            r1 = 1065353216(0x3f800000, float:1.0)
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 == 0) goto La7
            android.speech.tts.TextToSpeech r3 = com.mappls.sdk.navigation.voice.g.l()
            float r1 = r2.b
            r3.setSpeechRate(r1)
        La7:
            java.lang.String r3 = com.mappls.sdk.navigation.voice.g.k()
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto Lc2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.util.Locale r0 = r2.f13048a
            java.lang.String r0 = r0.getDisplayName()
            r3.append(r0)
            java.lang.String r0 = ": LANG_COUNTRY_VAR_AVAILABLE"
            goto Le8
        Lc2:
            java.lang.String r3 = com.mappls.sdk.navigation.voice.g.k()
            goto Lef
        Lc7:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.util.Locale r0 = r2.f13048a
            java.lang.String r0 = r0.getDisplayName()
            r3.append(r0)
            java.lang.String r0 = ": LANG_MISSING_DATA"
            goto Le8
        Ld8:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.util.Locale r0 = r2.f13048a
            java.lang.String r0 = r0.getDisplayName()
            r3.append(r0)
            java.lang.String r0 = ": LANG_NOT_SUPPORTED"
        Le8:
            r3.append(r0)
            java.lang.String r3 = r3.toString()
        Lef:
            com.mappls.sdk.navigation.voice.g.f(r3)
            a()
        Lf5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.voice.e.onInit(int):void");
    }
}
