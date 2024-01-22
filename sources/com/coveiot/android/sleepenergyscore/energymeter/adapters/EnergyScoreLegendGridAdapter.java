package com.coveiot.android.sleepenergyscore.energymeter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableCompat;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import java.util.List;
/* loaded from: classes6.dex */
public class EnergyScoreLegendGridAdapter extends BaseAdapter {
    public Context h;
    public int[] i;
    public List<String> j;
    public LayoutInflater k;

    public EnergyScoreLegendGridAdapter(Context context, int[] iArr, List<String> list) {
        this.h = context;
        this.i = iArr;
        this.j = list;
        this.k = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.i.length;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        String str;
        View inflate = this.k.inflate(R.layout.table_row_legend, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.textView);
        DrawableCompat.setTint(DrawableCompat.wrap(((ImageView) inflate.findViewById(R.id.imageView)).getDrawable()), this.i[i]);
        if (this.j.get(i).contains(":")) {
            String[] split = this.j.get(i).split(":");
            str = Utils.getActivityModeNames(this.h, Integer.valueOf(split[1]).intValue(), Integer.valueOf(split[0]).intValue());
        } else {
            str = this.j.get(i);
        }
        textView.setText(str);
        return inflate;
    }
}
