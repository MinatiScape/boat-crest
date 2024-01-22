package com.mappls.sdk.direction.ui.fragment;

import com.google.android.material.tabs.TabLayout;
/* loaded from: classes11.dex */
public final class d0 implements TabLayout.OnTabSelectedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ l f12591a;

    public d0(l lVar) {
        this.f12591a = lVar;
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public final void onTabReselected(TabLayout.Tab tab) {
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onTabSelected(com.google.android.material.tabs.TabLayout.Tab r2) {
        /*
            r1 = this;
            int r2 = r2.getPosition()
            if (r2 == 0) goto L1f
            r0 = 1
            if (r2 == r0) goto L16
            r0 = 2
            if (r2 == r0) goto Ld
            goto L29
        Ld:
            com.mappls.sdk.direction.ui.fragment.l r2 = r1.f12591a
            com.mappls.sdk.direction.ui.DirectionViewModel r2 = com.mappls.sdk.direction.ui.fragment.l.l(r2)
            java.lang.String r0 = "walking"
            goto L27
        L16:
            com.mappls.sdk.direction.ui.fragment.l r2 = r1.f12591a
            com.mappls.sdk.direction.ui.DirectionViewModel r2 = com.mappls.sdk.direction.ui.fragment.l.l(r2)
            java.lang.String r0 = "biking"
            goto L27
        L1f:
            com.mappls.sdk.direction.ui.fragment.l r2 = r1.f12591a
            com.mappls.sdk.direction.ui.DirectionViewModel r2 = com.mappls.sdk.direction.ui.fragment.l.l(r2)
            java.lang.String r0 = "driving"
        L27:
            r2.profile = r0
        L29:
            com.mappls.sdk.direction.ui.fragment.l r2 = r1.f12591a
            com.mappls.sdk.maps.MapplsMap r2 = com.mappls.sdk.direction.ui.fragment.l.E(r2)
            if (r2 == 0) goto L36
            com.mappls.sdk.direction.ui.fragment.l r2 = r1.f12591a
            com.mappls.sdk.direction.ui.fragment.l.z(r2)
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.direction.ui.fragment.d0.onTabSelected(com.google.android.material.tabs.TabLayout$Tab):void");
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public final void onTabUnselected(TabLayout.Tab tab) {
    }
}
