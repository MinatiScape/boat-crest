package com.coveiot.android.leonardo.dashboard.socialshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.GridviewlayoutSsBinding;
import com.coveiot.android.leonardo.dashboard.socialshare.model.ShareData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class SocialShareGridviewAdapter extends RecyclerView.Adapter<SocialShareViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<ShareData> f4808a;
    @NotNull
    public Context b;

    /* loaded from: classes4.dex */
    public static final class SocialShareViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final GridviewlayoutSsBinding f4809a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SocialShareViewHolder(@NotNull GridviewlayoutSsBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f4809a = binding;
        }

        @NotNull
        public final GridviewlayoutSsBinding getBinding() {
            return this.f4809a;
        }
    }

    public SocialShareGridviewAdapter(@NotNull List<ShareData> shareDataList, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(shareDataList, "shareDataList");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4808a = shareDataList;
        this.b = context;
    }

    @NotNull
    public final Context getContext() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f4808a.size();
    }

    @NotNull
    public final List<ShareData> getShareDataList() {
        return this.f4808a;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.b = context;
    }

    public final void setShareDataList(@NotNull List<ShareData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f4808a = list;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x046c  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x08c4  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x08d6  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x03c4  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull com.coveiot.android.leonardo.dashboard.socialshare.adapters.SocialShareGridviewAdapter.SocialShareViewHolder r13, int r14) {
        /*
            Method dump skipped, instructions count: 2278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.socialshare.adapters.SocialShareGridviewAdapter.onBindViewHolder(com.coveiot.android.leonardo.dashboard.socialshare.adapters.SocialShareGridviewAdapter$SocialShareViewHolder, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public SocialShareViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        GridviewlayoutSsBinding inflate = GridviewlayoutSsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new SocialShareViewHolder(inflate);
    }
}
