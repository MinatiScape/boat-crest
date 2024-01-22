package com.mappls.sdk.navigation.ui.navigation.infobar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutBottomSheetAdapterBinding;
import java.util.List;
/* loaded from: classes11.dex */
public class b extends RecyclerView.Adapter<C0651b> {

    /* renamed from: a  reason: collision with root package name */
    public List<c> f13019a;
    public a b;

    /* loaded from: classes11.dex */
    public interface a {
    }

    /* renamed from: com.mappls.sdk.navigation.ui.navigation.infobar.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0651b extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public LayoutBottomSheetAdapterBinding f13020a;

        public C0651b(@NonNull b bVar, LayoutBottomSheetAdapterBinding layoutBottomSheetAdapterBinding) {
            super(layoutBottomSheetAdapterBinding.getRoot());
            this.f13020a = layoutBottomSheetAdapterBinding;
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(List<c> list) {
        this.f13019a = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<c> list = this.f13019a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull C0651b c0651b, int i) {
        C0651b c0651b2 = c0651b;
        c0651b2.f13020a.ivBottomItem.setBackgroundResource(com.mappls.sdk.navigation.ui.theme.a.e(c0651b2.f13020a.getRoot().getContext(), R.attr.navigationViewBottomSheetImageBackground));
        c0651b2.f13020a.ivBottomItem.setImageDrawable(com.mappls.sdk.navigation.ui.theme.a.d(c0651b2.f13020a.getRoot().getContext(), this.f13019a.get(i).a()));
        c0651b2.f13020a.tvBottomItem.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(c0651b2.f13020a.getRoot().getContext(), R.attr.navigationTextColorSecondary));
        c0651b2.f13020a.tvBottomItem.setText(this.f13019a.get(i).c());
        c0651b2.f13020a.getRoot().setOnClickListener(new com.mappls.sdk.navigation.ui.navigation.infobar.a(this, i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public C0651b onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new C0651b(this, (LayoutBottomSheetAdapterBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.layout_bottom_sheet_adapter, viewGroup, false));
    }
}
