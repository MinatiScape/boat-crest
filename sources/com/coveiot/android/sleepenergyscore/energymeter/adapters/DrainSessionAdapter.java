package com.coveiot.android.sleepenergyscore.energymeter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sleepenergyscore.R;
import java.util.ArrayList;
import java.util.TreeMap;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class DrainSessionAdapter extends RecyclerView.Adapter<ActivityHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5698a;
    @Nullable
    public ArrayList<String> b;
    @Nullable
    public TreeMap<String, Integer> c;

    /* loaded from: classes6.dex */
    public final class ActivityHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f5699a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityHolder(@NotNull DrainSessionAdapter drainSessionAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.activity_type_image);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.activity_type_image)");
            this.f5699a = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.activity_type_text);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.activity_type_text)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.activity_duration_txt);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.activity_duration_txt)");
            this.c = (TextView) findViewById3;
        }

        @NotNull
        public final TextView getActivityDuration() {
            return this.c;
        }

        @NotNull
        public final ImageView getActivityIcon() {
            return this.f5699a;
        }

        @NotNull
        public final TextView getActivityName() {
            return this.b;
        }
    }

    public DrainSessionAdapter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5698a = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        TreeMap<String, Integer> treeMap = this.c;
        if (treeMap != null) {
            Intrinsics.checkNotNull(treeMap);
            return treeMap.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Nullable
    public final TreeMap<String, Integer> getSessionHasMap() {
        return this.c;
    }

    public final void setData(@NotNull TreeMap<String, Integer> sessionListMap) {
        Intrinsics.checkNotNullParameter(sessionListMap, "sessionListMap");
        this.c = sessionListMap;
        this.b = new ArrayList<>(sessionListMap.keySet());
        notifyDataSetChanged();
    }

    public final void setSessionHasMap(@Nullable TreeMap<String, Integer> treeMap) {
        this.c = treeMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0154  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull com.coveiot.android.sleepenergyscore.energymeter.adapters.DrainSessionAdapter.ActivityHolder r14, int r15) {
        /*
            Method dump skipped, instructions count: 391
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sleepenergyscore.energymeter.adapters.DrainSessionAdapter.onBindViewHolder(com.coveiot.android.sleepenergyscore.energymeter.adapters.DrainSessionAdapter$ActivityHolder, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.drain_list_adap, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…      false\n            )");
        return new ActivityHolder(this, inflate);
    }
}
