package com.google.android.libraries.places.widget.internal.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzdk;
import java.util.List;
/* loaded from: classes10.dex */
public final class zzl extends ListAdapter<AutocompletePrediction, zzm> {
    private final zzo zza;
    private int zzb;
    private boolean zzc;

    public zzl(@NonNull zzo zzoVar) {
        super(new zzn());
        this.zzc = true;
        this.zza = zzoVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: zza */
    public final zzm onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        try {
            return new zzm(this.zza, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.places_autocomplete_prediction, viewGroup, false));
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final /* synthetic */ void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        try {
            ((zzm) viewHolder).zza(getItem(i), this.zzc);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.ListAdapter
    public final void submitList(@Nullable List<AutocompletePrediction> list) {
        try {
            int i = 0;
            this.zzc = (this.zzb != 0 || list == null || list.isEmpty()) ? false : true;
            if (list != null) {
                i = list.size();
            }
            this.zzb = i;
            super.submitList(list);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }
}
