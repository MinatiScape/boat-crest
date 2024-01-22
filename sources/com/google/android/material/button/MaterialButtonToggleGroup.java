package com.google.android.material.button;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.annotation.BoolRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
/* loaded from: classes10.dex */
public class MaterialButtonToggleGroup extends LinearLayout {
    public static final String r = MaterialButtonToggleGroup.class.getSimpleName();
    public static final int s = R.style.Widget_MaterialComponents_MaterialButtonToggleGroup;
    public final List<c> h;
    public final d i;
    public final LinkedHashSet<OnButtonCheckedListener> j;
    public final Comparator<MaterialButton> k;
    public Integer[] l;
    public boolean m;
    public boolean n;
    public boolean o;
    @IdRes
    public final int p;
    public Set<Integer> q;

    /* loaded from: classes10.dex */
    public interface OnButtonCheckedListener {
        void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, @IdRes int i, boolean z);
    }

    /* loaded from: classes10.dex */
    public class a implements Comparator<MaterialButton> {
        public a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(MaterialButton materialButton, MaterialButton materialButton2) {
            int compareTo = Boolean.valueOf(materialButton.isChecked()).compareTo(Boolean.valueOf(materialButton2.isChecked()));
            if (compareTo != 0) {
                return compareTo;
            }
            int compareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
            return compareTo2 != 0 ? compareTo2 : Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton)).compareTo(Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton2)));
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AccessibilityDelegateCompat {
        public b() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, MaterialButtonToggleGroup.this.g(view), 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    /* loaded from: classes10.dex */
    public static class c {
        public static final CornerSize e = new AbsoluteCornerSize(0.0f);

        /* renamed from: a  reason: collision with root package name */
        public CornerSize f10239a;
        public CornerSize b;
        public CornerSize c;
        public CornerSize d;

        public c(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4) {
            this.f10239a = cornerSize;
            this.b = cornerSize3;
            this.c = cornerSize4;
            this.d = cornerSize2;
        }

        public static c a(c cVar) {
            CornerSize cornerSize = e;
            return new c(cornerSize, cVar.d, cornerSize, cVar.c);
        }

        public static c b(c cVar, View view) {
            return ViewUtils.isLayoutRtl(view) ? c(cVar) : d(cVar);
        }

        public static c c(c cVar) {
            CornerSize cornerSize = cVar.f10239a;
            CornerSize cornerSize2 = cVar.d;
            CornerSize cornerSize3 = e;
            return new c(cornerSize, cornerSize2, cornerSize3, cornerSize3);
        }

        public static c d(c cVar) {
            CornerSize cornerSize = e;
            return new c(cornerSize, cornerSize, cVar.b, cVar.c);
        }

        public static c e(c cVar, View view) {
            return ViewUtils.isLayoutRtl(view) ? d(cVar) : c(cVar);
        }

        public static c f(c cVar) {
            CornerSize cornerSize = cVar.f10239a;
            CornerSize cornerSize2 = e;
            return new c(cornerSize, cornerSize2, cVar.b, cornerSize2);
        }
    }

    /* loaded from: classes10.dex */
    public class d implements MaterialButton.a {
        public d() {
        }

        @Override // com.google.android.material.button.MaterialButton.a
        public void a(@NonNull MaterialButton materialButton, boolean z) {
            MaterialButtonToggleGroup.this.invalidate();
        }

        public /* synthetic */ d(MaterialButtonToggleGroup materialButtonToggleGroup, a aVar) {
            this();
        }
    }

    public MaterialButtonToggleGroup(@NonNull Context context) {
        this(context, null);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (i(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if ((getChildAt(i2) instanceof MaterialButton) && i(i2)) {
                i++;
            }
        }
        return i;
    }

    public static void m(ShapeAppearanceModel.Builder builder, @Nullable c cVar) {
        if (cVar == null) {
            builder.setAllCornerSizes(0.0f);
        } else {
            builder.setTopLeftCornerSize(cVar.f10239a).setBottomLeftCornerSize(cVar.d).setTopRightCornerSize(cVar.b).setBottomRightCornerSize(cVar.c);
        }
    }

    private void setGeneratedIdIfNeeded(@NonNull MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(ViewCompat.generateViewId());
        }
    }

    private void setupButtonChild(@NonNull MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.setOnPressedChangeListenerInternal(this.i);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    public void addOnButtonCheckedListener(@NonNull OnButtonCheckedListener onButtonCheckedListener) {
        this.j.add(onButtonCheckedListener);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(r, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        d(materialButton.getId(), materialButton.isChecked());
        ShapeAppearanceModel shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.h.add(new c(shapeAppearanceModel.getTopLeftCornerSize(), shapeAppearanceModel.getBottomLeftCornerSize(), shapeAppearanceModel.getTopRightCornerSize(), shapeAppearanceModel.getBottomRightCornerSize()));
        ViewCompat.setAccessibilityDelegate(materialButton, new b());
    }

    public final void b() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
            MaterialButton f = f(i);
            int min = Math.min(f.getStrokeWidth(), f(i - 1).getStrokeWidth());
            LinearLayout.LayoutParams c2 = c(f);
            if (getOrientation() == 0) {
                MarginLayoutParamsCompat.setMarginEnd(c2, 0);
                MarginLayoutParamsCompat.setMarginStart(c2, -min);
                c2.topMargin = 0;
            } else {
                c2.bottomMargin = 0;
                c2.topMargin = -min;
                MarginLayoutParamsCompat.setMarginStart(c2, 0);
            }
            f.setLayoutParams(c2);
        }
        k(firstVisibleChildIndex);
    }

    @NonNull
    public final LinearLayout.LayoutParams c(@NonNull View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return (LinearLayout.LayoutParams) layoutParams;
        }
        return new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    public void check(@IdRes int i) {
        d(i, true);
    }

    public void clearChecked() {
        n(new HashSet());
    }

    public void clearOnButtonCheckedListeners() {
        this.j.clear();
    }

    public final void d(@IdRes int i, boolean z) {
        if (i == -1) {
            String str = r;
            Log.e(str, "Button ID is not valid: " + i);
            return;
        }
        HashSet hashSet = new HashSet(this.q);
        if (z && !hashSet.contains(Integer.valueOf(i))) {
            if (this.n && !hashSet.isEmpty()) {
                hashSet.clear();
            }
            hashSet.add(Integer.valueOf(i));
        } else if (z || !hashSet.contains(Integer.valueOf(i))) {
            return;
        } else {
            if (!this.o || hashSet.size() > 1) {
                hashSet.remove(Integer.valueOf(i));
            }
        }
        n(hashSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(@NonNull Canvas canvas) {
        o();
        super.dispatchDraw(canvas);
    }

    public final void e(@IdRes int i, boolean z) {
        Iterator<OnButtonCheckedListener> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().onButtonChecked(this, i, z);
        }
    }

    public final MaterialButton f(int i) {
        return (MaterialButton) getChildAt(i);
    }

    public final int g(@Nullable View view) {
        if (view instanceof MaterialButton) {
            int i = 0;
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                if (getChildAt(i2) == view) {
                    return i;
                }
                if ((getChildAt(i2) instanceof MaterialButton) && i(i2)) {
                    i++;
                }
            }
            return -1;
        }
        return -1;
    }

    @IdRes
    public int getCheckedButtonId() {
        if (!this.n || this.q.isEmpty()) {
            return -1;
        }
        return this.q.iterator().next().intValue();
    }

    @NonNull
    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            int id = f(i).getId();
            if (this.q.contains(Integer.valueOf(id))) {
                arrayList.add(Integer.valueOf(id));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.l;
        if (numArr != null && i2 < numArr.length) {
            return numArr[i2].intValue();
        }
        Log.w(r, "Child order wasn't updated");
        return i2;
    }

    @Nullable
    public final c h(int i, int i2, int i3) {
        c cVar = this.h.get(i);
        if (i2 == i3) {
            return cVar;
        }
        boolean z = getOrientation() == 0;
        if (i == i2) {
            return z ? c.e(cVar, this) : c.f(cVar);
        } else if (i == i3) {
            return z ? c.b(cVar, this) : c.a(cVar);
        } else {
            return null;
        }
    }

    public final boolean i(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    public boolean isSelectionRequired() {
        return this.o;
    }

    public boolean isSingleSelection() {
        return this.n;
    }

    public void j(@NonNull MaterialButton materialButton, boolean z) {
        if (this.m) {
            return;
        }
        d(materialButton.getId(), z);
    }

    public final void k(int i) {
        if (getChildCount() == 0 || i == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) f(i).getLayoutParams();
        if (getOrientation() == 1) {
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
            return;
        }
        MarginLayoutParamsCompat.setMarginEnd(layoutParams, 0);
        MarginLayoutParamsCompat.setMarginStart(layoutParams, 0);
        layoutParams.leftMargin = 0;
        layoutParams.rightMargin = 0;
    }

    public final void l(@IdRes int i, boolean z) {
        View findViewById = findViewById(i);
        if (findViewById instanceof MaterialButton) {
            this.m = true;
            ((MaterialButton) findViewById).setChecked(z);
            this.m = false;
        }
    }

    public final void n(Set<Integer> set) {
        Set<Integer> set2 = this.q;
        this.q = new HashSet(set);
        for (int i = 0; i < getChildCount(); i++) {
            int id = f(i).getId();
            l(id, set.contains(Integer.valueOf(id)));
            if (set2.contains(Integer.valueOf(id)) != set.contains(Integer.valueOf(id))) {
                e(id, set.contains(Integer.valueOf(id)));
            }
        }
        invalidate();
    }

    public final void o() {
        TreeMap treeMap = new TreeMap(this.k);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put(f(i), Integer.valueOf(i));
        }
        this.l = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        int i = this.p;
        if (i != -1) {
            n(Collections.singleton(Integer.valueOf(i)));
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, getVisibleButtonCount(), false, isSingleSelection() ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        p();
        b();
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            ((MaterialButton) view).setOnPressedChangeListenerInternal(null);
        }
        int indexOfChild = indexOfChild(view);
        if (indexOfChild >= 0) {
            this.h.remove(indexOfChild);
        }
        p();
        b();
    }

    @VisibleForTesting
    public void p() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i = 0; i < childCount; i++) {
            MaterialButton f = f(i);
            if (f.getVisibility() != 8) {
                ShapeAppearanceModel.Builder builder = f.getShapeAppearanceModel().toBuilder();
                m(builder, h(i, firstVisibleChildIndex, lastVisibleChildIndex));
                f.setShapeAppearanceModel(builder.build());
            }
        }
    }

    public void removeOnButtonCheckedListener(@NonNull OnButtonCheckedListener onButtonCheckedListener) {
        this.j.remove(onButtonCheckedListener);
    }

    public void setSelectionRequired(boolean z) {
        this.o = z;
    }

    public void setSingleSelection(boolean z) {
        if (this.n != z) {
            this.n = z;
            clearChecked();
        }
    }

    public void uncheck(@IdRes int i) {
        d(i, false);
    }

    public MaterialButtonToggleGroup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialButtonToggleGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialButtonToggleGroup(@androidx.annotation.NonNull android.content.Context r7, @androidx.annotation.Nullable android.util.AttributeSet r8, int r9) {
        /*
            r6 = this;
            int r4 = com.google.android.material.button.MaterialButtonToggleGroup.s
            android.content.Context r7 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r7, r8, r9, r4)
            r6.<init>(r7, r8, r9)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r6.h = r7
            com.google.android.material.button.MaterialButtonToggleGroup$d r7 = new com.google.android.material.button.MaterialButtonToggleGroup$d
            r0 = 0
            r7.<init>(r6, r0)
            r6.i = r7
            java.util.LinkedHashSet r7 = new java.util.LinkedHashSet
            r7.<init>()
            r6.j = r7
            com.google.android.material.button.MaterialButtonToggleGroup$a r7 = new com.google.android.material.button.MaterialButtonToggleGroup$a
            r7.<init>()
            r6.k = r7
            r7 = 0
            r6.m = r7
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r6.q = r0
            android.content.Context r0 = r6.getContext()
            int[] r2 = com.google.android.material.R.styleable.MaterialButtonToggleGroup
            int[] r5 = new int[r7]
            r1 = r8
            r3 = r9
            android.content.res.TypedArray r8 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r0, r1, r2, r3, r4, r5)
            int r9 = com.google.android.material.R.styleable.MaterialButtonToggleGroup_singleSelection
            boolean r9 = r8.getBoolean(r9, r7)
            r6.setSingleSelection(r9)
            int r9 = com.google.android.material.R.styleable.MaterialButtonToggleGroup_checkedButton
            r0 = -1
            int r9 = r8.getResourceId(r9, r0)
            r6.p = r9
            int r9 = com.google.android.material.R.styleable.MaterialButtonToggleGroup_selectionRequired
            boolean r7 = r8.getBoolean(r9, r7)
            r6.o = r7
            r7 = 1
            r6.setChildrenDrawingOrderEnabled(r7)
            r8.recycle()
            androidx.core.view.ViewCompat.setImportantForAccessibility(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.button.MaterialButtonToggleGroup.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setSingleSelection(@BoolRes int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
