package com.google.android.recaptcha;

import android.app.Application;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.internal.zzb;
import com.google.android.recaptcha.internal.zzp;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class Recaptcha {
    @NotNull
    public static final Recaptcha INSTANCE = new Recaptcha();

    private Recaptcha() {
    }

    @NonNull
    /* renamed from: getClient-BWLJW6A$default  reason: not valid java name */
    public static /* synthetic */ Object m110getClientBWLJW6A$default(@NonNull Recaptcha recaptcha, @NonNull Application application, @NonNull String str, long j, @NonNull Continuation continuation, int i, @NonNull Object obj) {
        if ((i & 4) != 0) {
            j = 10000;
        }
        return recaptcha.m111getClientBWLJW6A(application, str, j, continuation);
    }

    @JvmStatic
    @NotNull
    public static final Task<RecaptchaTasksClient> getTasksClient(@NonNull Application application, @NonNull String str) {
        Deferred b;
        zzp zzpVar = zzp.zza;
        b = e.b(zzp.zzb(), null, null, new Recaptcha$getTasksClient$1(application, str, null), 3, null);
        return zzb.zza(b);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    @androidx.annotation.NonNull
    @org.jetbrains.annotations.Nullable
    /* renamed from: getClient-BWLJW6A  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m111getClientBWLJW6A(@androidx.annotation.NonNull android.app.Application r11, @androidx.annotation.NonNull java.lang.String r12, long r13, @androidx.annotation.NonNull kotlin.coroutines.Continuation<? super kotlin.Result<? extends com.google.android.recaptcha.RecaptchaClient>> r15) {
        /*
            r10 = this;
            boolean r0 = r15 instanceof com.google.android.recaptcha.Recaptcha$getClient$1
            if (r0 == 0) goto L13
            r0 = r15
            com.google.android.recaptcha.Recaptcha$getClient$1 r0 = (com.google.android.recaptcha.Recaptcha$getClient$1) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.zzc = r1
            goto L18
        L13:
            com.google.android.recaptcha.Recaptcha$getClient$1 r0 = new com.google.android.recaptcha.Recaptcha$getClient$1
            r0.<init>(r10, r15)
        L18:
            java.lang.Object r15 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.lang.Throwable -> L29
            goto L55
        L29:
            r11 = move-exception
            goto L5c
        L2b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L33:
            kotlin.ResultKt.throwOnFailure(r15)
            kotlin.Result$Companion r15 = kotlin.Result.Companion     // Catch: java.lang.Throwable -> L29
            com.google.android.recaptcha.internal.zzp r15 = com.google.android.recaptcha.internal.zzp.zza     // Catch: java.lang.Throwable -> L29
            kotlinx.coroutines.CoroutineScope r15 = com.google.android.recaptcha.internal.zzp.zzb()     // Catch: java.lang.Throwable -> L29
            kotlin.coroutines.CoroutineContext r15 = r15.getCoroutineContext()     // Catch: java.lang.Throwable -> L29
            com.google.android.recaptcha.Recaptcha$getClient$2$1 r2 = new com.google.android.recaptcha.Recaptcha$getClient$2$1     // Catch: java.lang.Throwable -> L29
            r9 = 0
            r4 = r2
            r5 = r11
            r6 = r12
            r7 = r13
            r4.<init>(r5, r6, r7, r9)     // Catch: java.lang.Throwable -> L29
            r0.zzc = r3     // Catch: java.lang.Throwable -> L29
            java.lang.Object r15 = kotlinx.coroutines.BuildersKt.withContext(r15, r2, r0)     // Catch: java.lang.Throwable -> L29
            if (r15 != r1) goto L55
            return r1
        L55:
            com.google.android.recaptcha.internal.zzaa r15 = (com.google.android.recaptcha.internal.zzaa) r15     // Catch: java.lang.Throwable -> L29
            java.lang.Object r11 = kotlin.Result.m123constructorimpl(r15)     // Catch: java.lang.Throwable -> L29
            goto L66
        L5c:
            kotlin.Result$Companion r12 = kotlin.Result.Companion
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)
            java.lang.Object r11 = kotlin.Result.m123constructorimpl(r11)
        L66:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.Recaptcha.m111getClientBWLJW6A(android.app.Application, java.lang.String, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @JvmStatic
    @NotNull
    public static final Task<RecaptchaTasksClient> getTasksClient(@NonNull Application application, @NonNull String str, long j) {
        Deferred b;
        zzp zzpVar = zzp.zza;
        b = e.b(zzp.zzb(), null, null, new Recaptcha$getTasksClient$2(application, str, j, null), 3, null);
        return zzb.zza(b);
    }
}
