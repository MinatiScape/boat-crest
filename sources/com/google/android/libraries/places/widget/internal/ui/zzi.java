package com.google.android.libraries.places.widget.internal.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.internal.zzdk;
import java.util.List;
/* loaded from: classes10.dex */
final class zzi extends AnimatorListenerAdapter {
    private final /* synthetic */ View zza;
    private final /* synthetic */ RecyclerView.ViewHolder zzb;
    private final /* synthetic */ ViewPropertyAnimator zzc;
    private final /* synthetic */ zzj zzd;

    public zzi(zzj zzjVar, View view, RecyclerView.ViewHolder viewHolder, ViewPropertyAnimator viewPropertyAnimator) {
        this.zzd = zzjVar;
        this.zza = view;
        this.zzb = viewHolder;
        this.zzc = viewPropertyAnimator;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        try {
            zzj.zzb(this.zza);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        List list;
        try {
            this.zzc.setListener(null);
            this.zzd.dispatchAddFinished(this.zzb);
            list = this.zzd.zzc;
            list.remove(this.zzb);
            this.zzd.zza();
            this.zzc.setStartDelay(0L);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        try {
            this.zza.setAlpha(0.0f);
            this.zzd.dispatchAddStarting(this.zzb);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }
}
