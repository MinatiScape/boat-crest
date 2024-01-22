package com.coveiot.android.navigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.databinding.ItemYourPlacesBinding;
import com.coveiot.android.navigation.interfaces.FavouritePlaceListener;
import com.coveiot.android.navigation.models.FavouritePlaceData;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class YourPlacesAdapter extends RecyclerView.Adapter<YourPlacesViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final FavouritePlaceListener f5505a;
    @NotNull
    public final ArrayList<FavouritePlaceData> b;

    /* loaded from: classes5.dex */
    public final class YourPlacesViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ItemYourPlacesBinding f5506a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public YourPlacesViewHolder(@NotNull YourPlacesAdapter yourPlacesAdapter, ItemYourPlacesBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5506a = binding;
        }

        @NotNull
        public final ItemYourPlacesBinding getBinding() {
            return this.f5506a;
        }
    }

    public YourPlacesAdapter(@NotNull FavouritePlaceListener listener, @NotNull ArrayList<FavouritePlaceData> favouritePlaceData) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(favouritePlaceData, "favouritePlaceData");
        this.f5505a = listener;
        this.b = favouritePlaceData;
    }

    public static final void b(YourPlacesAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.f5505a.onFavouritePlaceSelected(false, this$0.b.get(i), this$0.b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size() - 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull YourPlacesViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getBinding().tvYourPlacesHome.setText(this.b.get(i).getLabel());
        holder.getBinding().clYourPlaces.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.adapter.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YourPlacesAdapter.b(YourPlacesAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public YourPlacesViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemYourPlacesBinding inflate = ItemYourPlacesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new YourPlacesViewHolder(this, inflate);
    }
}
