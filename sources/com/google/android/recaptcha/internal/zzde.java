package com.google.android.recaptcha.internal;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.recaptcha.RecaptchaAction;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzde extends SuspendLambda implements Function2 {
    public Object zza;
    public Object zzb;
    public Object zzc;
    public int zzd;
    public final /* synthetic */ RecaptchaAction zze;
    public final /* synthetic */ zzdi zzf;
    public final /* synthetic */ String zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzde(RecaptchaAction recaptchaAction, zzdi zzdiVar, String str, Continuation continuation) {
        super(2, continuation);
        this.zze = recaptchaAction;
        this.zzf = zzdiVar;
        this.zzg = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zzde(this.zze, this.zzf, this.zzg, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzde) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Map map;
        String str;
        String str2;
        Context context;
        zzq zzqVar;
        Object coroutine_suspended = a.getCOROUTINE_SUSPENDED();
        int i = this.zzd;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            zzdi zzdiVar = this.zzf;
            String str3 = this.zzg;
            RecaptchaAction recaptchaAction = this.zze;
            this.zza = zzdiVar;
            this.zzb = str3;
            this.zzc = recaptchaAction;
            this.zzd = 1;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            map = zzdiVar.zzk;
            map.put(str3, cancellableContinuationImpl);
            zzmr zzf = zzms.zzf();
            zzf.zze(str3);
            zzf.zzd(recaptchaAction.getAction());
            byte[] zzd = ((zzms) zzf.zzj()).zzd();
            String zzi = zzek.zzh().zzi(zzd, 0, zzd.length);
            zzai zzaiVar = zzai.zza;
            zzln zzlnVar = zzln.EXECUTE_NATIVE;
            str = zzdiVar.zzg;
            str2 = zzdiVar.zzh;
            zzaf zzafVar = new zzaf(zzlnVar, str, str2, str3, null);
            context = zzdiVar.zze;
            zzqVar = zzdiVar.zzf;
            zzai.zzc(zzafVar, context, zzqVar);
            WebView zzb = zzdiVar.zzb();
            zzb.evaluateJavascript("recaptcha.m.Main.execute(\"" + zzi + "\")", null);
            obj = cancellableContinuationImpl.getResult();
            if (obj == a.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return obj;
    }
}
