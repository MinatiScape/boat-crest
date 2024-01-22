package com.google.android.recaptcha.internal;

import android.content.Context;
import android.os.Build;
import android.webkit.WebView;
import com.google.firebase.crashlytics.BuildConfig;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzdh extends SuspendLambda implements Function2 {
    public int zza;
    public final /* synthetic */ zzdi zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdh(zzdi zzdiVar, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzdiVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zzdh(this.zzb, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdh) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Context context;
        String str;
        Context context2;
        String str2;
        String str3;
        String str4;
        String str5;
        Context context3;
        zzq zzqVar;
        String str6;
        String str7;
        String str8;
        String str9;
        zzdt zzdtVar;
        zzdt zzdtVar2;
        zzq zzqVar2;
        Object coroutine_suspended = a.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            zzt zztVar = zzt.zza;
            context = this.zzb.zze;
            String zza = zzt.zza(context);
            str = this.zzb.zzd;
            context2 = this.zzb.zze;
            String packageName = context2.getPackageName();
            str2 = this.zzb.zzh;
            int i2 = Build.VERSION.SDK_INT;
            String encode = URLEncoder.encode(str, "UTF-8");
            String encode2 = URLEncoder.encode(packageName, "UTF-8");
            String encode3 = URLEncoder.encode(zza, "UTF-8");
            String encode4 = URLEncoder.encode(BuildConfig.VERSION_NAME, "UTF-8");
            String encode5 = URLEncoder.encode(str2, "UTF-8");
            byte[] bytes = ("k=" + encode + "&pk=" + encode2 + "&mst=" + encode3 + "&msv=" + encode4 + "&msi=" + encode5 + "&mov=" + i2).getBytes(Charset.forName("UTF-8"));
            zzai zzaiVar = zzai.zza;
            zzln zzlnVar = zzln.INIT_NATIVE;
            str3 = this.zzb.zzg;
            str4 = this.zzb.zzh;
            str5 = this.zzb.zzh;
            zzaf zzafVar = new zzaf(zzlnVar, str3, str4, str5, null);
            context3 = this.zzb.zze;
            zzqVar = this.zzb.zzf;
            zzai.zzc(zzafVar, context3, zzqVar);
            zzln zzlnVar2 = zzln.INIT_NETWORK;
            str6 = this.zzb.zzg;
            str7 = this.zzb.zzh;
            str8 = this.zzb.zzh;
            zzaf zzafVar2 = new zzaf(zzlnVar2, str6, str7, str8, null);
            str9 = this.zzb.zzd;
            zzai.zzb(zzafVar2, str9, new zzr());
            zzp zzpVar = zzp.zza;
            e.e(zzp.zza(), null, null, new zzdg(this.zzb, zza, null), 3, null);
            zzdtVar = this.zzb.zzn;
            zzdtVar.zzd();
            zzdtVar2 = this.zzb.zzn;
            zzdtVar2.zze();
            zzdi zzdiVar = this.zzb;
            WebView zzb = zzdiVar.zzb();
            zzqVar2 = zzdiVar.zzf;
            zzb.postUrl(zzqVar2.zza(), bytes);
            Boxing.boxInt(this.zzb.zzm().hashCode());
            CompletableDeferred zzm = this.zzb.zzm();
            this.zza = 1;
            if (zzm.await(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Result.m122boximpl(Result.m123constructorimpl(Unit.INSTANCE));
    }
}
