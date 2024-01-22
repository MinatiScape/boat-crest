package com.google.android.recaptcha.internal;

import android.app.Application;
import android.webkit.WebView;
import com.google.android.gms.common.api.ApiException;
import com.google.android.recaptcha.RecaptchaException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.TimeoutCancellationException;
/* loaded from: classes10.dex */
public final class zzv {
    private zzv() {
    }

    public /* synthetic */ zzv(DefaultConstructorMarker defaultConstructorMarker) {
    }

    public static /* synthetic */ Object zzb(zzv zzvVar, Application application, String str, long j, zzq zzqVar, WebView webView, Continuation continuation, int i, Object obj) throws TimeoutCancellationException, ApiException, RecaptchaException {
        return zzvVar.zza(application, str, j, new zzq("https://www.recaptcha.net/recaptcha/api3"), null, continuation);
    }

    /* JADX WARN: Not initialized variable reg: 13, insn: 0x0285: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r13 I:??[OBJECT, ARRAY]), block:B:180:0x0285 */
    /* JADX WARN: Removed duplicated region for block: B:105:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x00bb A[Catch: all -> 0x0284, TRY_ENTER, TryCatch #0 {all -> 0x0284, blocks: (B:159:0x0204, B:161:0x020a, B:164:0x0220, B:167:0x0227, B:171:0x0242, B:126:0x00bb, B:129:0x00ca, B:132:0x00d1, B:133:0x011f, B:134:0x0120, B:136:0x0128, B:138:0x012e, B:141:0x013a, B:142:0x015e, B:143:0x015f, B:146:0x017e, B:149:0x0186, B:151:0x01a3, B:152:0x01a8, B:156:0x01b8, B:177:0x027c, B:178:0x0283), top: B:184:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0120 A[Catch: all -> 0x0284, TryCatch #0 {all -> 0x0284, blocks: (B:159:0x0204, B:161:0x020a, B:164:0x0220, B:167:0x0227, B:171:0x0242, B:126:0x00bb, B:129:0x00ca, B:132:0x00d1, B:133:0x011f, B:134:0x0120, B:136:0x0128, B:138:0x012e, B:141:0x013a, B:142:0x015e, B:143:0x015f, B:146:0x017e, B:149:0x0186, B:151:0x01a3, B:152:0x01a8, B:156:0x01b8, B:177:0x027c, B:178:0x0283), top: B:184:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x020a A[Catch: all -> 0x0284, TryCatch #0 {all -> 0x0284, blocks: (B:159:0x0204, B:161:0x020a, B:164:0x0220, B:167:0x0227, B:171:0x0242, B:126:0x00bb, B:129:0x00ca, B:132:0x00d1, B:133:0x011f, B:134:0x0120, B:136:0x0128, B:138:0x012e, B:141:0x013a, B:142:0x015e, B:143:0x015f, B:146:0x017e, B:149:0x0186, B:151:0x01a3, B:152:0x01a8, B:156:0x01b8, B:177:0x027c, B:178:0x0283), top: B:184:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0242 A[Catch: all -> 0x0284, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0284, blocks: (B:159:0x0204, B:161:0x020a, B:164:0x0220, B:167:0x0227, B:171:0x0242, B:126:0x00bb, B:129:0x00ca, B:132:0x00d1, B:133:0x011f, B:134:0x0120, B:136:0x0128, B:138:0x012e, B:141:0x013a, B:142:0x015e, B:143:0x015f, B:146:0x017e, B:149:0x0186, B:151:0x01a3, B:152:0x01a8, B:156:0x01b8, B:177:0x027c, B:178:0x0283), top: B:184:0x002a }] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object zza(@org.jetbrains.annotations.NotNull android.app.Application r27, @org.jetbrains.annotations.NotNull java.lang.String r28, long r29, @org.jetbrains.annotations.NotNull com.google.android.recaptcha.internal.zzq r31, @org.jetbrains.annotations.Nullable android.webkit.WebView r32, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r33) throws kotlinx.coroutines.TimeoutCancellationException, com.google.android.gms.common.api.ApiException, com.google.android.recaptcha.RecaptchaException {
        /*
            Method dump skipped, instructions count: 651
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzv.zza(android.app.Application, java.lang.String, long, com.google.android.recaptcha.internal.zzq, android.webkit.WebView, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
