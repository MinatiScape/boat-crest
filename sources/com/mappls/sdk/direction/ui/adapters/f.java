package com.mappls.sdk.direction.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.mappls.sdk.direction.ui.fragment.k;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import com.mappls.sdk.services.api.event.route.model.RouteReport;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class f extends FragmentPagerAdapter {
    public final List<RouteReport> h;
    public com.mappls.sdk.direction.ui.fragment.f i;

    /* loaded from: classes11.dex */
    public class a implements k.b {
        public a() {
        }

        @Override // com.mappls.sdk.direction.ui.fragment.k.b
        public final void a(String str, ArrayList<ReportDetails> arrayList) {
            if (f.this.i != null) {
                f.this.i.a(str, arrayList);
            }
        }
    }

    public f(@NonNull FragmentManager fragmentManager, List<RouteReport> list) {
        super(fragmentManager);
        this.h = list;
    }

    public final void a(com.mappls.sdk.direction.ui.fragment.f fVar) {
        this.i = fVar;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final int getCount() {
        List<RouteReport> list = this.h;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NonNull
    public final Fragment getItem(int i) {
        com.mappls.sdk.direction.ui.fragment.k kVar = new com.mappls.sdk.direction.ui.fragment.k(this.h.get(i));
        kVar.a(new a());
        return kVar;
    }
}
