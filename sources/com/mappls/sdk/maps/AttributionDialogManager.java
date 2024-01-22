package com.mappls.sdk.maps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.attribution.Attribution;
import com.mappls.sdk.maps.attribution.AttributionParser;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes11.dex */
public class AttributionDialogManager implements View.OnClickListener, DialogInterface.OnClickListener {
    @NonNull
    public final Context h;
    @NonNull
    public final MapplsMap i;
    public Set<Attribution> j;
    public AlertDialog k;

    /* loaded from: classes11.dex */
    public static class a {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<Context> f12623a;

        public a(MapplsMap mapplsMap, Context context) {
            this.f12623a = new WeakReference<>(context);
        }

        public final Set<Attribution> b() {
            Context context = this.f12623a.get();
            if (context == null) {
                return Collections.emptySet();
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add("@OpenStreetMap");
            return new AttributionParser.Options(context).withCopyrightSign(true).withImproveMap(true).withAttributionData((String[]) arrayList.toArray(new String[arrayList.size()])).build().getAttributions();
        }
    }

    public AttributionDialogManager(@NonNull Context context, @NonNull MapplsMap mapplsMap) {
        this.h = context;
        this.i = mapplsMap;
    }

    public final String[] a() {
        ArrayList arrayList = new ArrayList();
        for (Attribution attribution : this.j) {
            arrayList.add(attribution.getTitle());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@NonNull View view) {
        this.j = new a(this.i, view.getContext()).b();
        Context context = this.h;
        if (context instanceof Activity ? ((Activity) context).isFinishing() : false) {
            return;
        }
        showAttributionDialog(a());
    }

    public void onStop() {
        AlertDialog alertDialog = this.k;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.k.dismiss();
    }

    public void showAttributionDialog(@NonNull String[] strArr) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.h);
        builder.setCustomTitle(LayoutInflater.from(this.h).inflate(R.layout.mappls_maps_attribution_title, (ViewGroup) null, false));
        builder.setAdapter(new ArrayAdapter(this.h, R.layout.mappls_maps_attribution_list_item, strArr), this);
        this.k = builder.show();
    }
}
