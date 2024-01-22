package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public class NestedScrollingParentHelper {

    /* renamed from: a  reason: collision with root package name */
    public int f1146a;
    public int b;

    public NestedScrollingParentHelper(@NonNull ViewGroup viewGroup) {
    }

    public int getNestedScrollAxes() {
        return this.f1146a | this.b;
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i) {
        onNestedScrollAccepted(view, view2, i, 0);
    }

    public void onStopNestedScroll(@NonNull View view) {
        onStopNestedScroll(view, 0);
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i, int i2) {
        if (i2 == 1) {
            this.b = i;
        } else {
            this.f1146a = i;
        }
    }

    public void onStopNestedScroll(@NonNull View view, int i) {
        if (i == 1) {
            this.b = 0;
        } else {
            this.f1146a = 0;
        }
    }
}
