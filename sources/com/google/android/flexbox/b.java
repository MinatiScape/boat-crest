package com.google.android.flexbox;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.widget.CompoundButtonCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes6.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final com.google.android.flexbox.a f8171a;
    public boolean[] b;
    @Nullable
    public int[] c;
    @Nullable
    public long[] d;
    @Nullable
    public long[] e;

    /* renamed from: com.google.android.flexbox.b$b  reason: collision with other inner class name */
    /* loaded from: classes6.dex */
    public static class C0372b {

        /* renamed from: a  reason: collision with root package name */
        public List<FlexLine> f8172a;
        public int b;

        public void a() {
            this.f8172a = null;
            this.b = 0;
        }
    }

    /* loaded from: classes6.dex */
    public static class c implements Comparable<c> {
        public int h;
        public int i;

        public c() {
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(@NonNull c cVar) {
            int i = this.i;
            int i2 = cVar.i;
            return i != i2 ? i - i2 : this.h - cVar.h;
        }

        @NonNull
        public String toString() {
            return "Order{order=" + this.i + ", index=" + this.h + '}';
        }
    }

    public b(com.google.android.flexbox.a aVar) {
        this.f8171a = aVar;
    }

    public final int A(int i, FlexItem flexItem, int i2) {
        com.google.android.flexbox.a aVar = this.f8171a;
        int childWidthMeasureSpec = aVar.getChildWidthMeasureSpec(i, aVar.getPaddingLeft() + this.f8171a.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i2, flexItem.getWidth());
        int size = View.MeasureSpec.getSize(childWidthMeasureSpec);
        if (size > flexItem.getMaxWidth()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        return size < flexItem.getMinWidth() ? View.MeasureSpec.makeMeasureSpec(flexItem.getMinWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec)) : childWidthMeasureSpec;
    }

    public final int B(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginBottom();
        }
        return flexItem.getMarginRight();
    }

    public final int C(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginRight();
        }
        return flexItem.getMarginBottom();
    }

    public final int D(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginTop();
        }
        return flexItem.getMarginLeft();
    }

    public final int E(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginLeft();
        }
        return flexItem.getMarginTop();
    }

    public final int F(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getHeight();
        }
        return flexItem.getWidth();
    }

    public final int G(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getWidth();
        }
        return flexItem.getHeight();
    }

    public final int H(boolean z) {
        if (z) {
            return this.f8171a.getPaddingBottom();
        }
        return this.f8171a.getPaddingEnd();
    }

    public final int I(boolean z) {
        if (z) {
            return this.f8171a.getPaddingEnd();
        }
        return this.f8171a.getPaddingBottom();
    }

    public final int J(boolean z) {
        if (z) {
            return this.f8171a.getPaddingTop();
        }
        return this.f8171a.getPaddingStart();
    }

    public final int K(boolean z) {
        if (z) {
            return this.f8171a.getPaddingStart();
        }
        return this.f8171a.getPaddingTop();
    }

    public final int L(View view, boolean z) {
        if (z) {
            return view.getMeasuredHeight();
        }
        return view.getMeasuredWidth();
    }

    public final int M(View view, boolean z) {
        if (z) {
            return view.getMeasuredWidth();
        }
        return view.getMeasuredHeight();
    }

    public final boolean N(int i, int i2, FlexLine flexLine) {
        return i == i2 - 1 && flexLine.getItemCountNotGone() != 0;
    }

    public boolean O(SparseIntArray sparseIntArray) {
        int flexItemCount = this.f8171a.getFlexItemCount();
        if (sparseIntArray.size() != flexItemCount) {
            return true;
        }
        for (int i = 0; i < flexItemCount; i++) {
            View flexItemAt = this.f8171a.getFlexItemAt(i);
            if (flexItemAt != null && ((FlexItem) flexItemAt.getLayoutParams()).getOrder() != sparseIntArray.get(i)) {
                return true;
            }
        }
        return false;
    }

    public final boolean P(View view, int i, int i2, int i3, int i4, FlexItem flexItem, int i5, int i6, int i7) {
        if (this.f8171a.getFlexWrap() == 0) {
            return false;
        }
        if (flexItem.isWrapBefore()) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        int maxLine = this.f8171a.getMaxLine();
        if (maxLine == -1 || maxLine > i7 + 1) {
            int decorationLengthMainAxis = this.f8171a.getDecorationLengthMainAxis(view, i5, i6);
            if (decorationLengthMainAxis > 0) {
                i4 += decorationLengthMainAxis;
            }
            return i2 < i3 + i4;
        }
        return false;
    }

    public void Q(View view, FlexLine flexLine, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.f8171a.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i5 = flexLine.g;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (this.f8171a.getFlexWrap() != 2) {
                    int i6 = i2 + i5;
                    view.layout(i, (i6 - view.getMeasuredHeight()) - flexItem.getMarginBottom(), i3, i6 - flexItem.getMarginBottom());
                    return;
                }
                view.layout(i, (i2 - i5) + view.getMeasuredHeight() + flexItem.getMarginTop(), i3, (i4 - i5) + view.getMeasuredHeight() + flexItem.getMarginTop());
                return;
            } else if (alignItems == 2) {
                int measuredHeight = (((i5 - view.getMeasuredHeight()) + flexItem.getMarginTop()) - flexItem.getMarginBottom()) / 2;
                if (this.f8171a.getFlexWrap() != 2) {
                    int i7 = i2 + measuredHeight;
                    view.layout(i, i7, i3, view.getMeasuredHeight() + i7);
                    return;
                }
                int i8 = i2 - measuredHeight;
                view.layout(i, i8, i3, view.getMeasuredHeight() + i8);
                return;
            } else if (alignItems == 3) {
                if (this.f8171a.getFlexWrap() != 2) {
                    int max = Math.max(flexLine.l - view.getBaseline(), flexItem.getMarginTop());
                    view.layout(i, i2 + max, i3, i4 + max);
                    return;
                }
                int max2 = Math.max((flexLine.l - view.getMeasuredHeight()) + view.getBaseline(), flexItem.getMarginBottom());
                view.layout(i, i2 - max2, i3, i4 - max2);
                return;
            } else if (alignItems != 4) {
                return;
            }
        }
        if (this.f8171a.getFlexWrap() != 2) {
            view.layout(i, i2 + flexItem.getMarginTop(), i3, i4 + flexItem.getMarginTop());
        } else {
            view.layout(i, i2 - flexItem.getMarginBottom(), i3, i4 - flexItem.getMarginBottom());
        }
    }

    public void R(View view, FlexLine flexLine, boolean z, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.f8171a.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i5 = flexLine.g;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (!z) {
                    view.layout(((i + i5) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i2, ((i3 + i5) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i4);
                    return;
                } else {
                    view.layout((i - i5) + view.getMeasuredWidth() + flexItem.getMarginLeft(), i2, (i3 - i5) + view.getMeasuredWidth() + flexItem.getMarginLeft(), i4);
                    return;
                }
            } else if (alignItems == 2) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int measuredWidth = (((i5 - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams)) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                if (!z) {
                    view.layout(i + measuredWidth, i2, i3 + measuredWidth, i4);
                    return;
                } else {
                    view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                    return;
                }
            } else if (alignItems != 3 && alignItems != 4) {
                return;
            }
        }
        if (!z) {
            view.layout(i + flexItem.getMarginLeft(), i2, i3 + flexItem.getMarginLeft(), i4);
        } else {
            view.layout(i - flexItem.getMarginRight(), i2, i3 - flexItem.getMarginRight(), i4);
        }
    }

    @VisibleForTesting
    public long S(int i, int i2) {
        return (i & 4294967295L) | (i2 << 32);
    }

    public final void T(int i, int i2, FlexLine flexLine, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8 = flexLine.e;
        float f = flexLine.k;
        float f2 = 0.0f;
        if (f <= 0.0f || i3 > i8) {
            return;
        }
        float f3 = (i8 - i3) / f;
        flexLine.e = i4 + flexLine.f;
        if (!z) {
            flexLine.g = Integer.MIN_VALUE;
        }
        int i9 = 0;
        boolean z2 = false;
        int i10 = 0;
        float f4 = 0.0f;
        while (i9 < flexLine.h) {
            int i11 = flexLine.o + i9;
            View reorderedFlexItemAt = this.f8171a.getReorderedFlexItemAt(i11);
            if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                i5 = i8;
                i6 = i9;
            } else {
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int flexDirection = this.f8171a.getFlexDirection();
                if (flexDirection != 0 && flexDirection != 1) {
                    int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr = this.e;
                    if (jArr != null) {
                        measuredHeight = x(jArr[i11]);
                    }
                    int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr2 = this.e;
                    if (jArr2 != null) {
                        measuredWidth = y(jArr2[i11]);
                    }
                    if (this.b[i11] || flexItem.getFlexShrink() <= f2) {
                        i5 = i8;
                        i6 = i9;
                    } else {
                        float flexShrink = measuredHeight - (flexItem.getFlexShrink() * f3);
                        if (i9 == flexLine.h - 1) {
                            flexShrink += f4;
                            f4 = f2;
                        }
                        int round = Math.round(flexShrink);
                        if (round < flexItem.getMinHeight()) {
                            round = flexItem.getMinHeight();
                            this.b[i11] = true;
                            flexLine.k -= flexItem.getFlexShrink();
                            i5 = i8;
                            i6 = i9;
                            z2 = true;
                        } else {
                            f4 += flexShrink - round;
                            i5 = i8;
                            i6 = i9;
                            double d = f4;
                            if (d > 1.0d) {
                                round++;
                                f4 -= 1.0f;
                            } else if (d < -1.0d) {
                                round--;
                                f4 += 1.0f;
                            }
                        }
                        int A = A(i, flexItem, flexLine.m);
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                        reorderedFlexItemAt.measure(A, makeMeasureSpec);
                        measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                        Z(i11, A, makeMeasureSpec, reorderedFlexItemAt);
                        this.f8171a.updateViewCache(i11, reorderedFlexItemAt);
                        measuredHeight = measuredHeight2;
                    }
                    i7 = Math.max(i10, measuredWidth + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.f8171a.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.e += measuredHeight + flexItem.getMarginTop() + flexItem.getMarginBottom();
                } else {
                    i5 = i8;
                    int i12 = i9;
                    int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr3 = this.e;
                    if (jArr3 != null) {
                        measuredWidth2 = y(jArr3[i11]);
                    }
                    int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr4 = this.e;
                    if (jArr4 != null) {
                        measuredHeight3 = x(jArr4[i11]);
                    }
                    if (this.b[i11] || flexItem.getFlexShrink() <= 0.0f) {
                        i6 = i12;
                    } else {
                        float flexShrink2 = measuredWidth2 - (flexItem.getFlexShrink() * f3);
                        i6 = i12;
                        if (i6 == flexLine.h - 1) {
                            flexShrink2 += f4;
                            f4 = 0.0f;
                        }
                        int round2 = Math.round(flexShrink2);
                        if (round2 < flexItem.getMinWidth()) {
                            round2 = flexItem.getMinWidth();
                            this.b[i11] = true;
                            flexLine.k -= flexItem.getFlexShrink();
                            z2 = true;
                        } else {
                            f4 += flexShrink2 - round2;
                            double d2 = f4;
                            if (d2 > 1.0d) {
                                round2++;
                                f4 -= 1.0f;
                            } else if (d2 < -1.0d) {
                                round2--;
                                f4 += 1.0f;
                            }
                        }
                        int z3 = z(i2, flexItem, flexLine.m);
                        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                        reorderedFlexItemAt.measure(makeMeasureSpec2, z3);
                        int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                        Z(i11, makeMeasureSpec2, z3, reorderedFlexItemAt);
                        this.f8171a.updateViewCache(i11, reorderedFlexItemAt);
                        measuredWidth2 = measuredWidth3;
                        measuredHeight3 = measuredHeight4;
                    }
                    int max = Math.max(i10, measuredHeight3 + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.f8171a.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.e += measuredWidth2 + flexItem.getMarginLeft() + flexItem.getMarginRight();
                    i7 = max;
                }
                flexLine.g = Math.max(flexLine.g, i7);
                i10 = i7;
            }
            i9 = i6 + 1;
            i8 = i5;
            f2 = 0.0f;
        }
        int i13 = i8;
        if (!z2 || i13 == flexLine.e) {
            return;
        }
        T(i, i2, flexLine, i3, i4, true);
    }

    public final int[] U(int i, List<c> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (c cVar : list) {
            int i3 = cVar.h;
            iArr[i2] = i3;
            sparseIntArray.append(i3, cVar.i);
            i2++;
        }
        return iArr;
    }

    public final void V(View view, int i, int i2) {
        int measuredHeight;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.getMarginLeft()) - flexItem.getMarginRight()) - this.f8171a.getDecorationLengthCrossAxis(view), flexItem.getMinWidth()), flexItem.getMaxWidth());
        long[] jArr = this.e;
        if (jArr != null) {
            measuredHeight = x(jArr[i2]);
        } else {
            measuredHeight = view.getMeasuredHeight();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        Z(i2, makeMeasureSpec2, makeMeasureSpec, view);
        this.f8171a.updateViewCache(i2, view);
    }

    public final void W(View view, int i, int i2) {
        int measuredWidth;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.getMarginTop()) - flexItem.getMarginBottom()) - this.f8171a.getDecorationLengthCrossAxis(view), flexItem.getMinHeight()), flexItem.getMaxHeight());
        long[] jArr = this.e;
        if (jArr != null) {
            measuredWidth = y(jArr[i2]);
        } else {
            measuredWidth = view.getMeasuredWidth();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        Z(i2, makeMeasureSpec, makeMeasureSpec2, view);
        this.f8171a.updateViewCache(i2, view);
    }

    public void X() {
        Y(0);
    }

    public void Y(int i) {
        View reorderedFlexItemAt;
        if (i >= this.f8171a.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.f8171a.getFlexDirection();
        if (this.f8171a.getAlignItems() == 4) {
            int[] iArr = this.c;
            List<FlexLine> flexLinesInternal = this.f8171a.getFlexLinesInternal();
            int size = flexLinesInternal.size();
            for (int i2 = iArr != null ? iArr[i] : 0; i2 < size; i2++) {
                FlexLine flexLine = flexLinesInternal.get(i2);
                int i3 = flexLine.h;
                for (int i4 = 0; i4 < i3; i4++) {
                    int i5 = flexLine.o + i4;
                    if (i4 < this.f8171a.getFlexItemCount() && (reorderedFlexItemAt = this.f8171a.getReorderedFlexItemAt(i5)) != null && reorderedFlexItemAt.getVisibility() != 8) {
                        FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                        if (flexItem.getAlignSelf() == -1 || flexItem.getAlignSelf() == 4) {
                            if (flexDirection == 0 || flexDirection == 1) {
                                W(reorderedFlexItemAt, flexLine.g, i5);
                            } else if (flexDirection != 2 && flexDirection != 3) {
                                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                            } else {
                                V(reorderedFlexItemAt, flexLine.g, i5);
                            }
                        }
                    }
                }
            }
            return;
        }
        for (FlexLine flexLine2 : this.f8171a.getFlexLinesInternal()) {
            for (Integer num : flexLine2.n) {
                View reorderedFlexItemAt2 = this.f8171a.getReorderedFlexItemAt(num.intValue());
                if (flexDirection == 0 || flexDirection == 1) {
                    W(reorderedFlexItemAt2, flexLine2.g, num.intValue());
                } else if (flexDirection != 2 && flexDirection != 3) {
                    throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                } else {
                    V(reorderedFlexItemAt2, flexLine2.g, num.intValue());
                }
            }
        }
    }

    public final void Z(int i, int i2, int i3, View view) {
        long[] jArr = this.d;
        if (jArr != null) {
            jArr[i] = S(i2, i3);
        }
        long[] jArr2 = this.e;
        if (jArr2 != null) {
            jArr2[i] = S(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    public final void a(List<FlexLine> list, FlexLine flexLine, int i, int i2) {
        flexLine.m = i2;
        this.f8171a.onNewFlexLineAdded(flexLine);
        flexLine.p = i;
        list.add(flexLine);
    }

    public void b(C0372b c0372b, int i, int i2, int i3, int i4, int i5, @Nullable List<FlexLine> list) {
        int i6;
        C0372b c0372b2;
        int i7;
        int i8;
        int i9;
        List<FlexLine> list2;
        int i10;
        View view;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17 = i;
        int i18 = i2;
        int i19 = i5;
        boolean isMainAxisDirectionHorizontal = this.f8171a.isMainAxisDirectionHorizontal();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        ArrayList arrayList = list == null ? new ArrayList() : list;
        c0372b.f8172a = arrayList;
        boolean z = i19 == -1;
        int K = K(isMainAxisDirectionHorizontal);
        int I = I(isMainAxisDirectionHorizontal);
        int J = J(isMainAxisDirectionHorizontal);
        int H = H(isMainAxisDirectionHorizontal);
        FlexLine flexLine = new FlexLine();
        int i20 = i4;
        flexLine.o = i20;
        int i21 = I + K;
        flexLine.e = i21;
        int flexItemCount = this.f8171a.getFlexItemCount();
        boolean z2 = z;
        int i22 = Integer.MIN_VALUE;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        while (true) {
            if (i20 >= flexItemCount) {
                i6 = i24;
                c0372b2 = c0372b;
                break;
            }
            View reorderedFlexItemAt = this.f8171a.getReorderedFlexItemAt(i20);
            if (reorderedFlexItemAt == null) {
                if (N(i20, flexItemCount, flexLine)) {
                    a(arrayList, flexLine, i20, i23);
                }
            } else if (reorderedFlexItemAt.getVisibility() == 8) {
                flexLine.i++;
                flexLine.h++;
                if (N(i20, flexItemCount, flexLine)) {
                    a(arrayList, flexLine, i20, i23);
                }
            } else {
                if (reorderedFlexItemAt instanceof CompoundButton) {
                    v((CompoundButton) reorderedFlexItemAt);
                }
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int i26 = flexItemCount;
                if (flexItem.getAlignSelf() == 4) {
                    flexLine.n.add(Integer.valueOf(i20));
                }
                int G = G(flexItem, isMainAxisDirectionHorizontal);
                if (flexItem.getFlexBasisPercent() != -1.0f && mode == 1073741824) {
                    G = Math.round(size * flexItem.getFlexBasisPercent());
                }
                if (isMainAxisDirectionHorizontal) {
                    int childWidthMeasureSpec = this.f8171a.getChildWidthMeasureSpec(i17, i21 + E(flexItem, true) + C(flexItem, true), G);
                    i7 = size;
                    i8 = mode;
                    int childHeightMeasureSpec = this.f8171a.getChildHeightMeasureSpec(i18, J + H + D(flexItem, true) + B(flexItem, true) + i23, F(flexItem, true));
                    reorderedFlexItemAt.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                    Z(i20, childWidthMeasureSpec, childHeightMeasureSpec, reorderedFlexItemAt);
                    i9 = childWidthMeasureSpec;
                } else {
                    i7 = size;
                    i8 = mode;
                    int childWidthMeasureSpec2 = this.f8171a.getChildWidthMeasureSpec(i18, J + H + D(flexItem, false) + B(flexItem, false) + i23, F(flexItem, false));
                    int childHeightMeasureSpec2 = this.f8171a.getChildHeightMeasureSpec(i17, E(flexItem, false) + i21 + C(flexItem, false), G);
                    reorderedFlexItemAt.measure(childWidthMeasureSpec2, childHeightMeasureSpec2);
                    Z(i20, childWidthMeasureSpec2, childHeightMeasureSpec2, reorderedFlexItemAt);
                    i9 = childHeightMeasureSpec2;
                }
                this.f8171a.updateViewCache(i20, reorderedFlexItemAt);
                i(reorderedFlexItemAt, i20);
                i24 = View.combineMeasuredStates(i24, reorderedFlexItemAt.getMeasuredState());
                int i27 = i23;
                int i28 = i21;
                FlexLine flexLine2 = flexLine;
                int i29 = i20;
                list2 = arrayList;
                int i30 = i9;
                if (P(reorderedFlexItemAt, i8, i7, flexLine.e, C(flexItem, isMainAxisDirectionHorizontal) + M(reorderedFlexItemAt, isMainAxisDirectionHorizontal) + E(flexItem, isMainAxisDirectionHorizontal), flexItem, i29, i25, arrayList.size())) {
                    if (flexLine2.getItemCountNotGone() > 0) {
                        a(list2, flexLine2, i29 > 0 ? i29 - 1 : 0, i27);
                        i23 = flexLine2.g + i27;
                    } else {
                        i23 = i27;
                    }
                    if (isMainAxisDirectionHorizontal) {
                        if (flexItem.getHeight() == -1) {
                            com.google.android.flexbox.a aVar = this.f8171a;
                            i10 = i2;
                            i20 = i29;
                            view = reorderedFlexItemAt;
                            view.measure(i30, aVar.getChildHeightMeasureSpec(i10, aVar.getPaddingTop() + this.f8171a.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i23, flexItem.getHeight()));
                            i(view, i20);
                        } else {
                            i10 = i2;
                            view = reorderedFlexItemAt;
                            i20 = i29;
                        }
                    } else {
                        i10 = i2;
                        view = reorderedFlexItemAt;
                        i20 = i29;
                        if (flexItem.getWidth() == -1) {
                            com.google.android.flexbox.a aVar2 = this.f8171a;
                            view.measure(aVar2.getChildWidthMeasureSpec(i10, aVar2.getPaddingLeft() + this.f8171a.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i23, flexItem.getWidth()), i30);
                            i(view, i20);
                        }
                    }
                    flexLine = new FlexLine();
                    flexLine.h = 1;
                    i11 = i28;
                    flexLine.e = i11;
                    flexLine.o = i20;
                    i13 = Integer.MIN_VALUE;
                    i12 = 0;
                } else {
                    i10 = i2;
                    view = reorderedFlexItemAt;
                    i20 = i29;
                    flexLine = flexLine2;
                    i11 = i28;
                    flexLine.h++;
                    i12 = i25 + 1;
                    i23 = i27;
                    i13 = i22;
                }
                flexLine.q |= flexItem.getFlexGrow() != 0.0f;
                flexLine.r |= flexItem.getFlexShrink() != 0.0f;
                int[] iArr = this.c;
                if (iArr != null) {
                    iArr[i20] = list2.size();
                }
                flexLine.e += M(view, isMainAxisDirectionHorizontal) + E(flexItem, isMainAxisDirectionHorizontal) + C(flexItem, isMainAxisDirectionHorizontal);
                flexLine.j += flexItem.getFlexGrow();
                flexLine.k += flexItem.getFlexShrink();
                this.f8171a.onNewFlexItemAdded(view, i20, i12, flexLine);
                int max = Math.max(i13, L(view, isMainAxisDirectionHorizontal) + D(flexItem, isMainAxisDirectionHorizontal) + B(flexItem, isMainAxisDirectionHorizontal) + this.f8171a.getDecorationLengthCrossAxis(view));
                flexLine.g = Math.max(flexLine.g, max);
                if (isMainAxisDirectionHorizontal) {
                    if (this.f8171a.getFlexWrap() != 2) {
                        flexLine.l = Math.max(flexLine.l, view.getBaseline() + flexItem.getMarginTop());
                    } else {
                        flexLine.l = Math.max(flexLine.l, (view.getMeasuredHeight() - view.getBaseline()) + flexItem.getMarginBottom());
                    }
                }
                i14 = i26;
                if (N(i20, i14, flexLine)) {
                    a(list2, flexLine, i20, i23);
                    i23 += flexLine.g;
                }
                i15 = i5;
                if (i15 != -1 && list2.size() > 0) {
                    if (list2.get(list2.size() - 1).p >= i15 && i20 >= i15 && !z2) {
                        i23 = -flexLine.getCrossSize();
                        i16 = i3;
                        z2 = true;
                        if (i23 <= i16 && z2) {
                            c0372b2 = c0372b;
                            i6 = i24;
                            break;
                        }
                        i25 = i12;
                        i22 = max;
                        i20++;
                        i17 = i;
                        flexItemCount = i14;
                        i18 = i10;
                        i21 = i11;
                        arrayList = list2;
                        mode = i8;
                        i19 = i15;
                        size = i7;
                    }
                }
                i16 = i3;
                if (i23 <= i16) {
                }
                i25 = i12;
                i22 = max;
                i20++;
                i17 = i;
                flexItemCount = i14;
                i18 = i10;
                i21 = i11;
                arrayList = list2;
                mode = i8;
                i19 = i15;
                size = i7;
            }
            i7 = size;
            i8 = mode;
            i10 = i18;
            i15 = i19;
            list2 = arrayList;
            i11 = i21;
            i14 = flexItemCount;
            i20++;
            i17 = i;
            flexItemCount = i14;
            i18 = i10;
            i21 = i11;
            arrayList = list2;
            mode = i8;
            i19 = i15;
            size = i7;
        }
        c0372b2.b = i6;
    }

    public void c(C0372b c0372b, int i, int i2) {
        b(c0372b, i, i2, Integer.MAX_VALUE, 0, -1, null);
    }

    public void d(C0372b c0372b, int i, int i2, int i3, int i4, @Nullable List<FlexLine> list) {
        b(c0372b, i, i2, i3, i4, -1, list);
    }

    public void e(C0372b c0372b, int i, int i2, int i3, int i4, List<FlexLine> list) {
        b(c0372b, i, i2, i3, 0, i4, list);
    }

    public void f(C0372b c0372b, int i, int i2) {
        b(c0372b, i2, i, Integer.MAX_VALUE, 0, -1, null);
    }

    public void g(C0372b c0372b, int i, int i2, int i3, int i4, @Nullable List<FlexLine> list) {
        b(c0372b, i2, i, i3, i4, -1, list);
    }

    public void h(C0372b c0372b, int i, int i2, int i3, int i4, List<FlexLine> list) {
        b(c0372b, i2, i, i3, 0, i4, list);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void i(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexItem r0 = (com.google.android.flexbox.FlexItem) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.getMinWidth()
            r4 = 1
            if (r1 >= r3) goto L1b
            int r1 = r0.getMinWidth()
        L19:
            r3 = r4
            goto L27
        L1b:
            int r3 = r0.getMaxWidth()
            if (r1 <= r3) goto L26
            int r1 = r0.getMaxWidth()
            goto L19
        L26:
            r3 = 0
        L27:
            int r5 = r0.getMinHeight()
            if (r2 >= r5) goto L32
            int r2 = r0.getMinHeight()
            goto L3e
        L32:
            int r5 = r0.getMaxHeight()
            if (r2 <= r5) goto L3d
            int r2 = r0.getMaxHeight()
            goto L3e
        L3d:
            r4 = r3
        L3e:
            if (r4 == 0) goto L55
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.Z(r8, r1, r0, r7)
            com.google.android.flexbox.a r0 = r6.f8171a
            r0.updateViewCache(r8, r7)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.b.i(android.view.View, int):void");
    }

    public void j(List<FlexLine> list, int i) {
        int i2 = this.c[i];
        if (i2 == -1) {
            i2 = 0;
        }
        if (list.size() > i2) {
            list.subList(i2, list.size()).clear();
        }
        int[] iArr = this.c;
        int length = iArr.length - 1;
        if (i > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i, length, -1);
        }
        long[] jArr = this.d;
        int length2 = jArr.length - 1;
        if (i > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i, length2, 0L);
        }
    }

    public final List<FlexLine> k(List<FlexLine> list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        FlexLine flexLine = new FlexLine();
        flexLine.g = (i - i2) / 2;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 == 0) {
                arrayList.add(flexLine);
            }
            arrayList.add(list.get(i3));
            if (i3 == list.size() - 1) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    @NonNull
    public final List<c> l(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            c cVar = new c();
            cVar.i = ((FlexItem) this.f8171a.getFlexItemAt(i2).getLayoutParams()).getOrder();
            cVar.h = i2;
            arrayList.add(cVar);
        }
        return arrayList;
    }

    public int[] m(SparseIntArray sparseIntArray) {
        int flexItemCount = this.f8171a.getFlexItemCount();
        return U(flexItemCount, l(flexItemCount), sparseIntArray);
    }

    public int[] n(View view, int i, ViewGroup.LayoutParams layoutParams, SparseIntArray sparseIntArray) {
        int flexItemCount = this.f8171a.getFlexItemCount();
        List<c> l = l(flexItemCount);
        c cVar = new c();
        if (view != null && (layoutParams instanceof FlexItem)) {
            cVar.i = ((FlexItem) layoutParams).getOrder();
        } else {
            cVar.i = 1;
        }
        if (i != -1 && i != flexItemCount) {
            if (i < this.f8171a.getFlexItemCount()) {
                cVar.h = i;
                while (i < flexItemCount) {
                    l.get(i).h++;
                    i++;
                }
            } else {
                cVar.h = flexItemCount;
            }
        } else {
            cVar.h = flexItemCount;
        }
        l.add(cVar);
        return U(flexItemCount + 1, l, sparseIntArray);
    }

    public void o(int i, int i2, int i3) {
        int i4;
        int i5;
        int flexDirection = this.f8171a.getFlexDirection();
        if (flexDirection == 0 || flexDirection == 1) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            i4 = mode;
            i5 = size;
        } else if (flexDirection != 2 && flexDirection != 3) {
            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        } else {
            i4 = View.MeasureSpec.getMode(i);
            i5 = View.MeasureSpec.getSize(i);
        }
        List<FlexLine> flexLinesInternal = this.f8171a.getFlexLinesInternal();
        if (i4 == 1073741824) {
            int sumOfCrossSize = this.f8171a.getSumOfCrossSize() + i3;
            int i6 = 0;
            if (flexLinesInternal.size() == 1) {
                flexLinesInternal.get(0).g = i5 - i3;
            } else if (flexLinesInternal.size() >= 2) {
                int alignContent = this.f8171a.getAlignContent();
                if (alignContent == 1) {
                    int i7 = i5 - sumOfCrossSize;
                    FlexLine flexLine = new FlexLine();
                    flexLine.g = i7;
                    flexLinesInternal.add(0, flexLine);
                } else if (alignContent == 2) {
                    this.f8171a.setFlexLines(k(flexLinesInternal, i5, sumOfCrossSize));
                } else if (alignContent == 3) {
                    if (sumOfCrossSize >= i5) {
                        return;
                    }
                    float size2 = (i5 - sumOfCrossSize) / (flexLinesInternal.size() - 1);
                    ArrayList arrayList = new ArrayList();
                    int size3 = flexLinesInternal.size();
                    float f = 0.0f;
                    while (i6 < size3) {
                        arrayList.add(flexLinesInternal.get(i6));
                        if (i6 != flexLinesInternal.size() - 1) {
                            FlexLine flexLine2 = new FlexLine();
                            if (i6 == flexLinesInternal.size() - 2) {
                                flexLine2.g = Math.round(f + size2);
                                f = 0.0f;
                            } else {
                                flexLine2.g = Math.round(size2);
                            }
                            int i8 = flexLine2.g;
                            f += size2 - i8;
                            if (f > 1.0f) {
                                flexLine2.g = i8 + 1;
                                f -= 1.0f;
                            } else if (f < -1.0f) {
                                flexLine2.g = i8 - 1;
                                f += 1.0f;
                            }
                            arrayList.add(flexLine2);
                        }
                        i6++;
                    }
                    this.f8171a.setFlexLines(arrayList);
                } else if (alignContent == 4) {
                    if (sumOfCrossSize >= i5) {
                        this.f8171a.setFlexLines(k(flexLinesInternal, i5, sumOfCrossSize));
                        return;
                    }
                    int size4 = (i5 - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                    ArrayList arrayList2 = new ArrayList();
                    FlexLine flexLine3 = new FlexLine();
                    flexLine3.g = size4;
                    for (FlexLine flexLine4 : flexLinesInternal) {
                        arrayList2.add(flexLine3);
                        arrayList2.add(flexLine4);
                        arrayList2.add(flexLine3);
                    }
                    this.f8171a.setFlexLines(arrayList2);
                } else if (alignContent == 5 && sumOfCrossSize < i5) {
                    float size5 = (i5 - sumOfCrossSize) / flexLinesInternal.size();
                    int size6 = flexLinesInternal.size();
                    float f2 = 0.0f;
                    while (i6 < size6) {
                        FlexLine flexLine5 = flexLinesInternal.get(i6);
                        float f3 = flexLine5.g + size5;
                        if (i6 == flexLinesInternal.size() - 1) {
                            f3 += f2;
                            f2 = 0.0f;
                        }
                        int round = Math.round(f3);
                        f2 += f3 - round;
                        if (f2 > 1.0f) {
                            round++;
                            f2 -= 1.0f;
                        } else if (f2 < -1.0f) {
                            round--;
                            f2 += 1.0f;
                        }
                        flexLine5.g = round;
                        i6++;
                    }
                }
            }
        }
    }

    public void p(int i, int i2) {
        q(i, i2, 0);
    }

    public void q(int i, int i2, int i3) {
        int size;
        int paddingLeft;
        int paddingRight;
        r(this.f8171a.getFlexItemCount());
        if (i3 >= this.f8171a.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.f8171a.getFlexDirection();
        int flexDirection2 = this.f8171a.getFlexDirection();
        if (flexDirection2 == 0 || flexDirection2 == 1) {
            int mode = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
            int largestMainSize = this.f8171a.getLargestMainSize();
            if (mode != 1073741824) {
                size = Math.min(largestMainSize, size);
            }
            paddingLeft = this.f8171a.getPaddingLeft();
            paddingRight = this.f8171a.getPaddingRight();
        } else if (flexDirection2 != 2 && flexDirection2 != 3) {
            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        } else {
            int mode2 = View.MeasureSpec.getMode(i2);
            size = View.MeasureSpec.getSize(i2);
            if (mode2 != 1073741824) {
                size = this.f8171a.getLargestMainSize();
            }
            paddingLeft = this.f8171a.getPaddingTop();
            paddingRight = this.f8171a.getPaddingBottom();
        }
        int i4 = paddingLeft + paddingRight;
        int[] iArr = this.c;
        int i5 = iArr != null ? iArr[i3] : 0;
        List<FlexLine> flexLinesInternal = this.f8171a.getFlexLinesInternal();
        int size2 = flexLinesInternal.size();
        for (int i6 = i5; i6 < size2; i6++) {
            FlexLine flexLine = flexLinesInternal.get(i6);
            int i7 = flexLine.e;
            if (i7 < size && flexLine.q) {
                w(i, i2, flexLine, size, i4, false);
            } else if (i7 > size && flexLine.r) {
                T(i, i2, flexLine, size, i4, false);
            }
        }
    }

    public final void r(int i) {
        boolean[] zArr = this.b;
        if (zArr == null) {
            this.b = new boolean[Math.max(i, 10)];
        } else if (zArr.length < i) {
            this.b = new boolean[Math.max(zArr.length * 2, i)];
        } else {
            Arrays.fill(zArr, false);
        }
    }

    public void s(int i) {
        int[] iArr = this.c;
        if (iArr == null) {
            this.c = new int[Math.max(i, 10)];
        } else if (iArr.length < i) {
            this.c = Arrays.copyOf(this.c, Math.max(iArr.length * 2, i));
        }
    }

    public void t(int i) {
        long[] jArr = this.d;
        if (jArr == null) {
            this.d = new long[Math.max(i, 10)];
        } else if (jArr.length < i) {
            this.d = Arrays.copyOf(this.d, Math.max(jArr.length * 2, i));
        }
    }

    public void u(int i) {
        long[] jArr = this.e;
        if (jArr == null) {
            this.e = new long[Math.max(i, 10)];
        } else if (jArr.length < i) {
            this.e = Arrays.copyOf(this.e, Math.max(jArr.length * 2, i));
        }
    }

    public final void v(CompoundButton compoundButton) {
        FlexItem flexItem = (FlexItem) compoundButton.getLayoutParams();
        int minWidth = flexItem.getMinWidth();
        int minHeight = flexItem.getMinHeight();
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(compoundButton);
        int minimumWidth = buttonDrawable == null ? 0 : buttonDrawable.getMinimumWidth();
        int minimumHeight = buttonDrawable != null ? buttonDrawable.getMinimumHeight() : 0;
        if (minWidth == -1) {
            minWidth = minimumWidth;
        }
        flexItem.setMinWidth(minWidth);
        if (minHeight == -1) {
            minHeight = minimumHeight;
        }
        flexItem.setMinHeight(minHeight);
    }

    public final void w(int i, int i2, FlexLine flexLine, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        double d;
        int i8;
        double d2;
        float f = flexLine.j;
        float f2 = 0.0f;
        if (f <= 0.0f || i3 < (i5 = flexLine.e)) {
            return;
        }
        float f3 = (i3 - i5) / f;
        flexLine.e = i4 + flexLine.f;
        if (!z) {
            flexLine.g = Integer.MIN_VALUE;
        }
        int i9 = 0;
        boolean z2 = false;
        int i10 = 0;
        float f4 = 0.0f;
        while (i9 < flexLine.h) {
            int i11 = flexLine.o + i9;
            View reorderedFlexItemAt = this.f8171a.getReorderedFlexItemAt(i11);
            if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                i6 = i5;
            } else {
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int flexDirection = this.f8171a.getFlexDirection();
                if (flexDirection != 0 && flexDirection != 1) {
                    int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr = this.e;
                    if (jArr != null) {
                        measuredHeight = x(jArr[i11]);
                    }
                    int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr2 = this.e;
                    if (jArr2 != null) {
                        measuredWidth = y(jArr2[i11]);
                    }
                    if (this.b[i11] || flexItem.getFlexGrow() <= f2) {
                        i8 = i5;
                    } else {
                        float flexGrow = measuredHeight + (flexItem.getFlexGrow() * f3);
                        if (i9 == flexLine.h - 1) {
                            flexGrow += f4;
                            f4 = f2;
                        }
                        int round = Math.round(flexGrow);
                        if (round > flexItem.getMaxHeight()) {
                            round = flexItem.getMaxHeight();
                            this.b[i11] = true;
                            flexLine.j -= flexItem.getFlexGrow();
                            i8 = i5;
                            z2 = true;
                        } else {
                            f4 += flexGrow - round;
                            i8 = i5;
                            double d3 = f4;
                            if (d3 > 1.0d) {
                                round++;
                                d2 = d3 - 1.0d;
                            } else if (d3 < -1.0d) {
                                round--;
                                d2 = d3 + 1.0d;
                            }
                            f4 = (float) d2;
                        }
                        int A = A(i, flexItem, flexLine.m);
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                        reorderedFlexItemAt.measure(A, makeMeasureSpec);
                        measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                        Z(i11, A, makeMeasureSpec, reorderedFlexItemAt);
                        this.f8171a.updateViewCache(i11, reorderedFlexItemAt);
                        measuredHeight = measuredHeight2;
                    }
                    i7 = Math.max(i10, measuredWidth + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.f8171a.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.e += measuredHeight + flexItem.getMarginTop() + flexItem.getMarginBottom();
                    i6 = i8;
                } else {
                    int i12 = i5;
                    int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr3 = this.e;
                    if (jArr3 != null) {
                        measuredWidth2 = y(jArr3[i11]);
                    }
                    int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr4 = this.e;
                    i6 = i12;
                    if (jArr4 != null) {
                        measuredHeight3 = x(jArr4[i11]);
                    }
                    if (!this.b[i11] && flexItem.getFlexGrow() > 0.0f) {
                        float flexGrow2 = measuredWidth2 + (flexItem.getFlexGrow() * f3);
                        if (i9 == flexLine.h - 1) {
                            flexGrow2 += f4;
                            f4 = 0.0f;
                        }
                        int round2 = Math.round(flexGrow2);
                        if (round2 > flexItem.getMaxWidth()) {
                            round2 = flexItem.getMaxWidth();
                            this.b[i11] = true;
                            flexLine.j -= flexItem.getFlexGrow();
                            z2 = true;
                        } else {
                            f4 += flexGrow2 - round2;
                            double d4 = f4;
                            if (d4 > 1.0d) {
                                round2++;
                                d = d4 - 1.0d;
                            } else if (d4 < -1.0d) {
                                round2--;
                                d = d4 + 1.0d;
                            }
                            f4 = (float) d;
                        }
                        int z3 = z(i2, flexItem, flexLine.m);
                        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                        reorderedFlexItemAt.measure(makeMeasureSpec2, z3);
                        int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                        Z(i11, makeMeasureSpec2, z3, reorderedFlexItemAt);
                        this.f8171a.updateViewCache(i11, reorderedFlexItemAt);
                        measuredWidth2 = measuredWidth3;
                        measuredHeight3 = measuredHeight4;
                    }
                    int max = Math.max(i10, measuredHeight3 + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.f8171a.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.e += measuredWidth2 + flexItem.getMarginLeft() + flexItem.getMarginRight();
                    i7 = max;
                }
                flexLine.g = Math.max(flexLine.g, i7);
                i10 = i7;
            }
            i9++;
            i5 = i6;
            f2 = 0.0f;
        }
        int i13 = i5;
        if (!z2 || i13 == flexLine.e) {
            return;
        }
        w(i, i2, flexLine, i3, i4, true);
    }

    public int x(long j) {
        return (int) (j >> 32);
    }

    public int y(long j) {
        return (int) j;
    }

    public final int z(int i, FlexItem flexItem, int i2) {
        com.google.android.flexbox.a aVar = this.f8171a;
        int childHeightMeasureSpec = aVar.getChildHeightMeasureSpec(i, aVar.getPaddingTop() + this.f8171a.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i2, flexItem.getHeight());
        int size = View.MeasureSpec.getSize(childHeightMeasureSpec);
        if (size > flexItem.getMaxHeight()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        return size < flexItem.getMinHeight() ? View.MeasureSpec.makeMeasureSpec(flexItem.getMinHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec)) : childHeightMeasureSpec;
    }
}
