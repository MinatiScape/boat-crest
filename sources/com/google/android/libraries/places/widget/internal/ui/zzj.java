package com.google.android.libraries.places.widget.internal.ui;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.internal.zzdk;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public final class zzj extends DefaultItemAnimator {
    private final List<RecyclerView.ViewHolder> zza = new ArrayList();
    private final List<RecyclerView.ViewHolder> zzb = new ArrayList();
    private final List<RecyclerView.ViewHolder> zzc = new ArrayList();
    private final int zzd;

    public zzj(Resources resources) {
        this.zzd = resources.getDimensionPixelSize(R.dimen.places_autocomplete_vertical_dropdown);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza() {
        if (isRunning()) {
            return;
        }
        dispatchAnimationsFinished();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(View view) {
        view.setAlpha(1.0f);
        view.setTranslationY(0.0f);
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.SimpleItemAnimator
    public final boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        try {
            endAnimation(viewHolder);
            viewHolder.itemView.setAlpha(0.0f);
            if (((zzm) viewHolder).zza()) {
                this.zza.add(viewHolder);
                return true;
            }
            this.zzb.add(viewHolder);
            return true;
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public final void endAnimation(RecyclerView.ViewHolder viewHolder) {
        try {
            super.endAnimation(viewHolder);
            if (this.zza.remove(viewHolder)) {
                zzb(viewHolder.itemView);
                dispatchAddFinished(viewHolder);
            }
            zza();
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public final void endAnimations() {
        try {
            for (int size = this.zza.size() - 1; size >= 0; size--) {
                RecyclerView.ViewHolder viewHolder = this.zza.get(size);
                zzb(viewHolder.itemView);
                dispatchAddFinished(viewHolder);
                this.zza.remove(size);
            }
            List<RecyclerView.ViewHolder> list = this.zzc;
            for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                list.get(size2).itemView.animate().cancel();
            }
            super.endAnimations();
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public final boolean isRunning() {
        try {
            if (!super.isRunning() && this.zzb.isEmpty() && this.zza.isEmpty()) {
                return !this.zzc.isEmpty();
            }
            return true;
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public final void runPendingAnimations() {
        try {
            for (RecyclerView.ViewHolder viewHolder : this.zzb) {
                super.animateAdd(viewHolder);
            }
            this.zzb.clear();
            super.runPendingAnimations();
            if (this.zza.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList(this.zza);
            this.zza.clear();
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                RecyclerView.ViewHolder viewHolder2 = (RecyclerView.ViewHolder) obj;
                View view = viewHolder2.itemView;
                this.zzc.add(viewHolder2);
                long moveDuration = getMoveDuration() + (viewHolder2.getLayoutPosition() * 67);
                view.setTranslationY(-this.zzd);
                view.setAlpha(0.0f);
                ViewPropertyAnimator animate = view.animate();
                animate.cancel();
                animate.translationY(0.0f).alpha(1.0f).setDuration(133L).setInterpolator(new FastOutSlowInInterpolator()).setStartDelay(moveDuration);
                animate.setListener(new zzi(this, view, viewHolder2, animate)).start();
            }
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }
}
