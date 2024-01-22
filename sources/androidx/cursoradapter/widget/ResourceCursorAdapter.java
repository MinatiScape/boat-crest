package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes.dex */
public abstract class ResourceCursorAdapter extends CursorAdapter {
    public int h;
    public int i;
    public LayoutInflater j;

    @Deprecated
    public ResourceCursorAdapter(Context context, int i, Cursor cursor) {
        super(context, cursor);
        this.i = i;
        this.h = i;
        this.j = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.j.inflate(this.i, viewGroup, false);
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.j.inflate(this.h, viewGroup, false);
    }

    public void setDropDownViewResource(int i) {
        this.i = i;
    }

    public void setViewResource(int i) {
        this.h = i;
    }

    @Deprecated
    public ResourceCursorAdapter(Context context, int i, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.i = i;
        this.h = i;
        this.j = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public ResourceCursorAdapter(Context context, int i, Cursor cursor, int i2) {
        super(context, cursor, i2);
        this.i = i;
        this.h = i;
        this.j = (LayoutInflater) context.getSystemService("layout_inflater");
    }
}
