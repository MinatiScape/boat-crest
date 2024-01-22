package com.mappls.sdk.navigation.ui.navigation.infobar;

import android.view.View;
import com.mappls.sdk.navigation.ui.navigation.infobar.InfobarBottomSheetView;
import com.mappls.sdk.navigation.ui.navigation.infobar.b;
import java.util.List;
/* loaded from: classes11.dex */
public class a implements View.OnClickListener {
    public final /* synthetic */ int h;
    public final /* synthetic */ b i;

    public a(b bVar, int i) {
        this.i = bVar;
        this.h = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.a aVar;
        b.a aVar2;
        List list;
        aVar = this.i.b;
        if (aVar != null) {
            aVar2 = this.i.b;
            list = this.i.f13019a;
            int b = ((c) list.get(this.h)).b();
            InfobarBottomSheetView.a aVar3 = (InfobarBottomSheetView.a) aVar2;
            aVar3.getClass();
            if (b == 0) {
                if (InfobarBottomSheetView.this.onInfobarCallback != null) {
                    InfobarBottomSheetView.this.onInfobarCallback.c();
                }
                if (InfobarBottomSheetView.this.mBottomSheetBehavior == null) {
                    return;
                }
            } else if (b == 1) {
                if (InfobarBottomSheetView.this.onInfobarCallback != null) {
                    InfobarBottomSheetView.this.onInfobarCallback.b();
                }
                if (InfobarBottomSheetView.this.mBottomSheetBehavior == null) {
                    return;
                }
            } else if (b == 2) {
                if (InfobarBottomSheetView.this.onInfobarCallback != null) {
                    InfobarBottomSheetView.this.onInfobarCallback.a();
                }
                if (InfobarBottomSheetView.this.mBottomSheetBehavior == null) {
                    return;
                }
            } else if (b != 3) {
                return;
            } else {
                if (InfobarBottomSheetView.this.onInfobarCallback != null) {
                    InfobarBottomSheetView.this.onInfobarCallback.f();
                }
                if (InfobarBottomSheetView.this.mBottomSheetBehavior == null) {
                    return;
                }
            }
            InfobarBottomSheetView.this.mBottomSheetBehavior.setState(4);
        }
    }
}
