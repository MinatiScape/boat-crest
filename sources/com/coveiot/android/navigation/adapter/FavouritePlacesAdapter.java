package com.coveiot.android.navigation.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.databinding.PlacesItemLayoutBinding;
import com.coveiot.android.navigation.interfaces.FavouritePlaceListener;
import com.coveiot.android.navigation.models.FavouritePlaceData;
import com.coveiot.android.theme.R;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class FavouritePlacesAdapter extends RecyclerView.Adapter<FavouritePlacesViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final FavouritePlaceListener f5499a;
    @NotNull
    public final ArrayList<FavouritePlaceData> b;

    /* loaded from: classes5.dex */
    public final class FavouritePlacesViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final PlacesItemLayoutBinding f5500a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FavouritePlacesViewHolder(@NotNull FavouritePlacesAdapter favouritePlacesAdapter, PlacesItemLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5500a = binding;
        }

        @NotNull
        public final PlacesItemLayoutBinding getBinding() {
            return this.f5500a;
        }
    }

    public FavouritePlacesAdapter(@NotNull FavouritePlaceListener listener, @NotNull ArrayList<FavouritePlaceData> favouritePlaceData) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(favouritePlaceData, "favouritePlaceData");
        this.f5499a = listener;
        this.b = favouritePlaceData;
    }

    public static final void c(int i, FavouritePlacesAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 5) {
            this$0.f5499a.onFavouritePlaceSelected(true, null, this$0.b);
        } else {
            this$0.f5499a.onFavouritePlaceSelected(false, this$0.b.get(i), this$0.b);
        }
    }

    public final void b(FavouritePlacesViewHolder favouritePlacesViewHolder, final int i) {
        if (i == 5) {
            TextView textView = favouritePlacesViewHolder.getBinding().tvTagName;
            textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(textView.getContext(), R.drawable.ic_edit_with_underline), (Drawable) null, (Drawable) null, (Drawable) null);
        }
        favouritePlacesViewHolder.getBinding().tvTagName.setText(this.b.get(i).getLabel());
        favouritePlacesViewHolder.getBinding().tvTagName.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.adapter.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FavouritePlacesAdapter.c(i, this, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull FavouritePlacesViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        b(holder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public FavouritePlacesViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        PlacesItemLayoutBinding inflate = PlacesItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new FavouritePlacesViewHolder(this, inflate);
    }
}
