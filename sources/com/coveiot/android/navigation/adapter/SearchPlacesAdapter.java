package com.coveiot.android.navigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.databinding.ItemSearchPlacesBinding;
import com.coveiot.android.navigation.interfaces.AutoSuggestPlaceListener;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SearchPlacesAdapter extends RecyclerView.Adapter<SearchPlacesViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final AutoSuggestPlaceListener f5503a;
    @NotNull
    public ArrayList<AutoSuggestionData> b;

    /* loaded from: classes5.dex */
    public final class SearchPlacesViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ItemSearchPlacesBinding f5504a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SearchPlacesViewHolder(@NotNull SearchPlacesAdapter searchPlacesAdapter, ItemSearchPlacesBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5504a = binding;
        }

        @NotNull
        public final ItemSearchPlacesBinding getBinding() {
            return this.f5504a;
        }
    }

    public SearchPlacesAdapter(@NotNull ArrayList<AutoSuggestionData> data, @NotNull AutoSuggestPlaceListener listener) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5503a = listener;
        this.b = new ArrayList<>();
        this.b = data;
    }

    public static final void b(SearchPlacesAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoSuggestPlaceListener autoSuggestPlaceListener = this$0.f5503a;
        AutoSuggestionData autoSuggestionData = this$0.b.get(i);
        Intrinsics.checkNotNullExpressionValue(autoSuggestionData, "mData.get(position)");
        autoSuggestPlaceListener.onPlaceSelected(autoSuggestionData);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull SearchPlacesViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        TextView textView = holder.getBinding().tvSearchPlaceDistance;
        textView.setText(this.b.get(i).getDistance() + "Km");
        holder.getBinding().tvSearchPlacesAddress1.setText(this.b.get(i).getPlaceName());
        holder.getBinding().tvSearchPlacesAddress2.setText(this.b.get(i).getPlaceAddress());
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.adapter.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchPlacesAdapter.b(SearchPlacesAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public SearchPlacesViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSearchPlacesBinding inflate = ItemSearchPlacesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new SearchPlacesViewHolder(this, inflate);
    }
}
