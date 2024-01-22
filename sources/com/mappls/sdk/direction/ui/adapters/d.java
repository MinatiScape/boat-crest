package com.mappls.sdk.direction.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionFuelTypeAdapterBinding;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class d extends RecyclerView.Adapter<a> {

    /* renamed from: a  reason: collision with root package name */
    public final List<com.mappls.sdk.direction.ui.model.d> f12570a;
    public b b;

    /* loaded from: classes11.dex */
    public class a extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final MapplsDirectionFuelTypeAdapterBinding f12571a;

        public a(@NonNull MapplsDirectionFuelTypeAdapterBinding mapplsDirectionFuelTypeAdapterBinding) {
            super(mapplsDirectionFuelTypeAdapterBinding.getRoot());
            this.f12571a = mapplsDirectionFuelTypeAdapterBinding;
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a(String str);
    }

    public d(ArrayList arrayList) {
        this.f12570a = arrayList;
    }

    public final void a(b bVar) {
        this.b = bVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<com.mappls.sdk.direction.ui.model.d> list = this.f12570a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull a aVar, int i) {
        TextView textView;
        Context context;
        int i2;
        a aVar2 = aVar;
        com.mappls.sdk.direction.ui.model.d dVar = this.f12570a.get(i);
        if (dVar.e().booleanValue()) {
            aVar2.f12571a.parentLayout.setBackgroundResource(dVar.f().intValue());
            aVar2.f12571a.imageViewFuelType.setImageDrawable(dVar.g());
            textView = aVar2.f12571a.textViewTypeFuel;
            context = aVar2.itemView.getContext();
            i2 = R.color.mappls_directions_white;
        } else {
            aVar2.f12571a.parentLayout.setBackgroundResource(dVar.b().intValue());
            aVar2.f12571a.imageViewFuelType.setImageDrawable(dVar.c());
            textView = aVar2.f12571a.textViewTypeFuel;
            context = aVar2.itemView.getContext();
            i2 = R.color.colorTextSecondary2;
        }
        textView.setTextColor(ContextCompat.getColor(context, i2));
        aVar2.f12571a.textViewTypeFuel.setText(dVar.d());
        aVar2.f12571a.getRoot().setOnClickListener(new c(this, dVar));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a((MapplsDirectionFuelTypeAdapterBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.mappls_direction_fuel_type_adapter, viewGroup, false));
    }
}
