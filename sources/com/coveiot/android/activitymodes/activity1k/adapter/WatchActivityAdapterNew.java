package com.coveiot.android.activitymodes.activity1k.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapter;
import com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapterNew;
import com.coveiot.android.activitymodes.activity1k.db.PhysicalActivityDatabase;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WatchActivityAdapterNew extends RecyclerView.Adapter<ActivityViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<Integer> f2744a;
    @NotNull
    public HashMap<Integer, ArrayList<CategoryAndActivityModel>> b;
    @NotNull
    public final ItemClickListener c;
    @NotNull
    public HashMap<Integer, ArrayList<CategoryAndActivityModel>> d;

    /* loaded from: classes2.dex */
    public static final class ActivityViewHolder extends RecyclerView.ViewHolder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public TextView f2745a;
        @Nullable
        public RecyclerView b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityViewHolder(@Nullable View view) {
            super(view);
            Intrinsics.checkNotNull(view);
            this.f2745a = (TextView) view.findViewById(R.id.category_name);
            this.b = (RecyclerView) view.findViewById(R.id.rcv_activities);
        }

        public final void bindData(int i, @NotNull ArrayList<CategoryAndActivityModel> activityList, @NotNull final ItemClickListener listener) {
            Intrinsics.checkNotNullParameter(activityList, "activityList");
            Intrinsics.checkNotNullParameter(listener, "listener");
            TextView textView = this.f2745a;
            if (textView != null) {
                textView.setText(PhysicalActivityDatabase.getDatabase(this.itemView.getContext()).physicalActivityDao().getPhysicalActivityCategoryName(i));
            }
            RecyclerView recyclerView = this.b;
            if (recyclerView != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(this.itemView.getContext()));
            }
            RecyclerView recyclerView2 = this.b;
            if (recyclerView2 == null) {
                return;
            }
            recyclerView2.setAdapter(new WatchActivityAdapter(activityList, new WatchActivityAdapter.ItemClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapterNew$ActivityViewHolder$bindData$1
                @Override // com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapter.ItemClickListener
                public void onClicked(@Nullable String str, boolean z, @NotNull CategoryAndActivityModel categoryAndActivityModel) {
                    Intrinsics.checkNotNullParameter(categoryAndActivityModel, "categoryAndActivityModel");
                    WatchActivityAdapterNew.ItemClickListener.this.onClicked(str, z, categoryAndActivityModel);
                }

                @Override // com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapter.ItemClickListener
                public void onInfoClicked(@NotNull CategoryAndActivityModel categoryAndActivityModel) {
                    Intrinsics.checkNotNullParameter(categoryAndActivityModel, "categoryAndActivityModel");
                    WatchActivityAdapterNew.ItemClickListener.this.onInfoClicked(categoryAndActivityModel);
                }

                @Override // com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapter.ItemClickListener
                public void onLongClicked(int i2) {
                }
            }));
        }

        @Nullable
        public final RecyclerView getRcvActivitiesList() {
            return this.b;
        }

        @Nullable
        public final TextView getTxtCategoryName() {
            return this.f2745a;
        }

        public final void setRcvActivitiesList(@Nullable RecyclerView recyclerView) {
            this.b = recyclerView;
        }

        public final void setTxtCategoryName(@Nullable TextView textView) {
            this.f2745a = textView;
        }
    }

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onClicked(@Nullable String str, boolean z, @NotNull CategoryAndActivityModel categoryAndActivityModel);

        void onInfoClicked(@NotNull CategoryAndActivityModel categoryAndActivityModel);

        void onLongClicked(int i);
    }

    public WatchActivityAdapterNew(@NotNull List<Integer> categoryIdList, @NotNull HashMap<Integer, ArrayList<CategoryAndActivityModel>> originalList, @NotNull ItemClickListener listener, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(categoryIdList, "categoryIdList");
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2744a = categoryIdList;
        this.b = originalList;
        this.c = listener;
        Intrinsics.checkNotNull(originalList, "null cannot be cast to non-null type java.util.HashMap<kotlin.Int, java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel>>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.Int, java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel> }> }");
        this.d = originalList;
    }

    @NotNull
    public final List<Integer> getCategoryIdList() {
        return this.f2744a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<Integer> list = this.f2744a;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @NotNull
    public final ItemClickListener getListener() {
        return this.c;
    }

    @NotNull
    public final HashMap<Integer, ArrayList<CategoryAndActivityModel>> getOriginalList() {
        return this.b;
    }

    public final void notifyAdapter(@NotNull List<Integer> categoryIdList, @NotNull HashMap<Integer, ArrayList<CategoryAndActivityModel>> activityList) {
        Intrinsics.checkNotNullParameter(categoryIdList, "categoryIdList");
        Intrinsics.checkNotNullParameter(activityList, "activityList");
        this.d = activityList;
        this.f2744a = categoryIdList;
        notifyDataSetChanged();
    }

    public final void setCategoryIdList(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f2744a = list;
    }

    public final void setOriginalList(@NotNull HashMap<Integer, ArrayList<CategoryAndActivityModel>> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
        this.b = hashMap;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        int intValue = this.f2744a.get(i).intValue();
        HashMap<Integer, ArrayList<CategoryAndActivityModel>> hashMap = this.d;
        Intrinsics.checkNotNull(hashMap);
        ArrayList<CategoryAndActivityModel> arrayList = hashMap.get(this.f2744a.get(i));
        Intrinsics.checkNotNull(arrayList);
        holder.bindData(intValue, arrayList, this.c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new ActivityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.watch_activities_list_item, parent, false));
    }
}
