package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.view.ViewCompat;
import androidx.core.widget.PopupWindowCompat;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class ListPopupWindow implements ShowableListMenu {
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static Method N = null;
    public static Method O = null;
    public static Method P = null;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    public static final int WRAP_CONTENT = -2;
    public Drawable A;
    public AdapterView.OnItemClickListener B;
    public AdapterView.OnItemSelectedListener C;
    public final j D;
    public final i E;
    public final h F;
    public final f G;
    public Runnable H;
    public final Handler I;
    public final Rect J;
    public Rect K;
    public boolean L;
    public PopupWindow M;
    public Context h;
    public ListAdapter i;
    public o j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public boolean q;
    public boolean r;
    public int s;
    public boolean t;
    public boolean u;
    public int v;
    public View w;
    public int x;
    public DataSetObserver y;
    public View z;

    /* loaded from: classes.dex */
    public class a extends ForwardingListener {
        public a(View view) {
            super(view);
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        /* renamed from: h */
        public ListPopupWindow getPopup() {
            return ListPopupWindow.this;
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View anchorView = ListPopupWindow.this.getAnchorView();
            if (anchorView == null || anchorView.getWindowToken() == null) {
                return;
            }
            ListPopupWindow.this.show();
        }
    }

    /* loaded from: classes.dex */
    public class c implements AdapterView.OnItemSelectedListener {
        public c() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            o oVar;
            if (i == -1 || (oVar = ListPopupWindow.this.j) == null) {
                return;
            }
            oVar.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    @RequiresApi(24)
    /* loaded from: classes.dex */
    public static class d {
        @DoNotInline
        public static int a(PopupWindow popupWindow, View view, int i, boolean z) {
            return popupWindow.getMaxAvailableHeight(view, i, z);
        }
    }

    @RequiresApi(29)
    /* loaded from: classes.dex */
    public static class e {
        @DoNotInline
        public static void a(PopupWindow popupWindow, Rect rect) {
            popupWindow.setEpicenterBounds(rect);
        }

        @DoNotInline
        public static void b(PopupWindow popupWindow, boolean z) {
            popupWindow.setIsClippedToScreen(z);
        }
    }

    /* loaded from: classes.dex */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    /* loaded from: classes.dex */
    public class g extends DataSetObserver {
        public g() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class h implements AbsListView.OnScrollListener {
        public h() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i != 1 || ListPopupWindow.this.isInputMethodNotNeeded() || ListPopupWindow.this.M.getContentView() == null) {
                return;
            }
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            listPopupWindow.I.removeCallbacks(listPopupWindow.D);
            ListPopupWindow.this.D.run();
        }
    }

    /* loaded from: classes.dex */
    public class i implements View.OnTouchListener {
        public i() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.M) != null && popupWindow.isShowing() && x >= 0 && x < ListPopupWindow.this.M.getWidth() && y >= 0 && y < ListPopupWindow.this.M.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.I.postDelayed(listPopupWindow.D, 250L);
                return false;
            } else if (action == 1) {
                ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
                listPopupWindow2.I.removeCallbacks(listPopupWindow2.D);
                return false;
            } else {
                return false;
            }
        }
    }

    /* loaded from: classes.dex */
    public class j implements Runnable {
        public j() {
        }

        @Override // java.lang.Runnable
        public void run() {
            o oVar = ListPopupWindow.this.j;
            if (oVar == null || !ViewCompat.isAttachedToWindow(oVar) || ListPopupWindow.this.j.getCount() <= ListPopupWindow.this.j.getChildCount()) {
                return;
            }
            int childCount = ListPopupWindow.this.j.getChildCount();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (childCount <= listPopupWindow.v) {
                listPopupWindow.M.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                N = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                P = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                O = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(@NonNull Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    public static boolean h(int i2) {
        return i2 == 66 || i2 == 23;
    }

    public void clearListSelection() {
        o oVar = this.j;
        if (oVar != null) {
            oVar.setListSelectionHidden(true);
            oVar.requestLayout();
        }
    }

    public View.OnTouchListener createDragToOpenListener(View view) {
        return new a(view);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        this.M.dismiss();
        i();
        this.M.setContentView(null);
        this.j = null;
        this.I.removeCallbacks(this.D);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int e() {
        int i2;
        int i3;
        int makeMeasureSpec;
        int i4;
        if (this.j == null) {
            Context context = this.h;
            this.H = new b();
            o f2 = f(context, !this.L);
            this.j = f2;
            Drawable drawable = this.A;
            if (drawable != null) {
                f2.setSelector(drawable);
            }
            this.j.setAdapter(this.i);
            this.j.setOnItemClickListener(this.B);
            this.j.setFocusable(true);
            this.j.setFocusableInTouchMode(true);
            this.j.setOnItemSelectedListener(new c());
            this.j.setOnScrollListener(this.F);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.C;
            if (onItemSelectedListener != null) {
                this.j.setOnItemSelectedListener(onItemSelectedListener);
            }
            o oVar = this.j;
            View view = this.w;
            if (view != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i5 = this.x;
                if (i5 == 0) {
                    linearLayout.addView(view);
                    linearLayout.addView(oVar, layoutParams);
                } else if (i5 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.x);
                } else {
                    linearLayout.addView(oVar, layoutParams);
                    linearLayout.addView(view);
                }
                int i6 = this.l;
                if (i6 >= 0) {
                    i4 = Integer.MIN_VALUE;
                } else {
                    i6 = 0;
                    i4 = 0;
                }
                view.measure(View.MeasureSpec.makeMeasureSpec(i6, i4), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view.getLayoutParams();
                i2 = view.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                oVar = linearLayout;
            } else {
                i2 = 0;
            }
            this.M.setContentView(oVar);
        } else {
            ViewGroup viewGroup = (ViewGroup) this.M.getContentView();
            View view2 = this.w;
            if (view2 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                i2 = view2.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                i2 = 0;
            }
        }
        Drawable background = this.M.getBackground();
        if (background != null) {
            background.getPadding(this.J);
            Rect rect = this.J;
            int i7 = rect.top;
            i3 = rect.bottom + i7;
            if (!this.p) {
                this.n = -i7;
            }
        } else {
            this.J.setEmpty();
            i3 = 0;
        }
        int g2 = g(getAnchorView(), this.n, this.M.getInputMethodMode() == 2);
        if (this.t || this.k == -1) {
            return g2 + i3;
        }
        int i8 = this.l;
        if (i8 == -2) {
            int i9 = this.h.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.J;
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i9 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i8 != -1) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
        } else {
            int i10 = this.h.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.J;
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i10 - (rect3.left + rect3.right), 1073741824);
        }
        int measureHeightOfChildrenCompat = this.j.measureHeightOfChildrenCompat(makeMeasureSpec, 0, -1, g2 - i2, -1);
        if (measureHeightOfChildrenCompat > 0) {
            i2 += i3 + this.j.getPaddingTop() + this.j.getPaddingBottom();
        }
        return measureHeightOfChildrenCompat + i2;
    }

    @NonNull
    public o f(Context context, boolean z) {
        return new o(context, z);
    }

    public final int g(View view, int i2, boolean z) {
        if (Build.VERSION.SDK_INT <= 23) {
            Method method = O;
            if (method != null) {
                try {
                    return ((Integer) method.invoke(this.M, view, Integer.valueOf(i2), Boolean.valueOf(z))).intValue();
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
                }
            }
            return this.M.getMaxAvailableHeight(view, i2);
        }
        return d.a(this.M, view, i2, z);
    }

    @Nullable
    public View getAnchorView() {
        return this.z;
    }

    @StyleRes
    public int getAnimationStyle() {
        return this.M.getAnimationStyle();
    }

    @Nullable
    public Drawable getBackground() {
        return this.M.getBackground();
    }

    @Nullable
    public Rect getEpicenterBounds() {
        if (this.K != null) {
            return new Rect(this.K);
        }
        return null;
    }

    public int getHeight() {
        return this.k;
    }

    public int getHorizontalOffset() {
        return this.m;
    }

    public int getInputMethodMode() {
        return this.M.getInputMethodMode();
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    @Nullable
    public ListView getListView() {
        return this.j;
    }

    public int getPromptPosition() {
        return this.x;
    }

    @Nullable
    public Object getSelectedItem() {
        if (isShowing()) {
            return this.j.getSelectedItem();
        }
        return null;
    }

    public long getSelectedItemId() {
        if (isShowing()) {
            return this.j.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int getSelectedItemPosition() {
        if (isShowing()) {
            return this.j.getSelectedItemPosition();
        }
        return -1;
    }

    @Nullable
    public View getSelectedView() {
        if (isShowing()) {
            return this.j.getSelectedView();
        }
        return null;
    }

    public int getSoftInputMode() {
        return this.M.getSoftInputMode();
    }

    public int getVerticalOffset() {
        if (this.p) {
            return this.n;
        }
        return 0;
    }

    public int getWidth() {
        return this.l;
    }

    public final void i() {
        View view = this.w;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.w);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public boolean isDropDownAlwaysVisible() {
        return this.t;
    }

    public boolean isInputMethodNotNeeded() {
        return this.M.getInputMethodMode() == 2;
    }

    public boolean isModal() {
        return this.L;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.M.isShowing();
    }

    public final void j(boolean z) {
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = N;
            if (method != null) {
                try {
                    method.invoke(this.M, Boolean.valueOf(z));
                    return;
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                    return;
                }
            }
            return;
        }
        e.b(this.M, z);
    }

    public boolean onKeyDown(int i2, @NonNull KeyEvent keyEvent) {
        int lookForSelectablePosition;
        if (isShowing() && i2 != 62 && (this.j.getSelectedItemPosition() >= 0 || !h(i2))) {
            int selectedItemPosition = this.j.getSelectedItemPosition();
            boolean z = !this.M.isAboveAnchor();
            ListAdapter listAdapter = this.i;
            int i3 = Integer.MAX_VALUE;
            int i4 = Integer.MIN_VALUE;
            if (listAdapter != null) {
                boolean areAllItemsEnabled = listAdapter.areAllItemsEnabled();
                int lookForSelectablePosition2 = areAllItemsEnabled ? 0 : this.j.lookForSelectablePosition(0, true);
                if (areAllItemsEnabled) {
                    lookForSelectablePosition = listAdapter.getCount() - 1;
                } else {
                    lookForSelectablePosition = this.j.lookForSelectablePosition(listAdapter.getCount() - 1, false);
                }
                i3 = lookForSelectablePosition2;
                i4 = lookForSelectablePosition;
            }
            if ((z && i2 == 19 && selectedItemPosition <= i3) || (!z && i2 == 20 && selectedItemPosition >= i4)) {
                clearListSelection();
                this.M.setInputMethodMode(1);
                show();
                return true;
            }
            this.j.setListSelectionHidden(false);
            if (this.j.onKeyDown(i2, keyEvent)) {
                this.M.setInputMethodMode(2);
                this.j.requestFocusFromTouch();
                show();
                if (i2 == 19 || i2 == 20 || i2 == 23 || i2 == 66) {
                    return true;
                }
            } else if (z && i2 == 20) {
                if (selectedItemPosition == i4) {
                    return true;
                }
            } else if (!z && i2 == 19 && selectedItemPosition == i3) {
                return true;
            }
        }
        return false;
    }

    public boolean onKeyPreIme(int i2, @NonNull KeyEvent keyEvent) {
        if (i2 == 4 && isShowing()) {
            View view = this.z;
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState = view.getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.startTracking(keyEvent, this);
                }
                return true;
            } else if (keyEvent.getAction() == 1) {
                KeyEvent.DispatcherState keyDispatcherState2 = view.getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.handleUpEvent(keyEvent);
                }
                if (!keyEvent.isTracking() || keyEvent.isCanceled()) {
                    return false;
                }
                dismiss();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean onKeyUp(int i2, @NonNull KeyEvent keyEvent) {
        if (!isShowing() || this.j.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean onKeyUp = this.j.onKeyUp(i2, keyEvent);
        if (onKeyUp && h(i2)) {
            dismiss();
        }
        return onKeyUp;
    }

    public boolean performItemClick(int i2) {
        if (isShowing()) {
            if (this.B != null) {
                o oVar = this.j;
                this.B.onItemClick(oVar, oVar.getChildAt(i2 - oVar.getFirstVisiblePosition()), i2, oVar.getAdapter().getItemId(i2));
                return true;
            }
            return true;
        }
        return false;
    }

    public void postShow() {
        this.I.post(this.H);
    }

    public void setAdapter(@Nullable ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.y;
        if (dataSetObserver == null) {
            this.y = new g();
        } else {
            ListAdapter listAdapter2 = this.i;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.i = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.y);
        }
        o oVar = this.j;
        if (oVar != null) {
            oVar.setAdapter(this.i);
        }
    }

    public void setAnchorView(@Nullable View view) {
        this.z = view;
    }

    public void setAnimationStyle(@StyleRes int i2) {
        this.M.setAnimationStyle(i2);
    }

    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        this.M.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i2) {
        Drawable background = this.M.getBackground();
        if (background != null) {
            background.getPadding(this.J);
            Rect rect = this.J;
            this.l = rect.left + rect.right + i2;
            return;
        }
        setWidth(i2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setDropDownAlwaysVisible(boolean z) {
        this.t = z;
    }

    public void setDropDownGravity(int i2) {
        this.s = i2;
    }

    public void setEpicenterBounds(@Nullable Rect rect) {
        this.K = rect != null ? new Rect(rect) : null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setForceIgnoreOutsideTouch(boolean z) {
        this.u = z;
    }

    public void setHeight(int i2) {
        if (i2 < 0 && -2 != i2 && -1 != i2) {
            throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
        }
        this.k = i2;
    }

    public void setHorizontalOffset(int i2) {
        this.m = i2;
    }

    public void setInputMethodMode(int i2) {
        this.M.setInputMethodMode(i2);
    }

    public void setListSelector(Drawable drawable) {
        this.A = drawable;
    }

    public void setModal(boolean z) {
        this.L = z;
        this.M.setFocusable(z);
    }

    public void setOnDismissListener(@Nullable PopupWindow.OnDismissListener onDismissListener) {
        this.M.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(@Nullable AdapterView.OnItemClickListener onItemClickListener) {
        this.B = onItemClickListener;
    }

    public void setOnItemSelectedListener(@Nullable AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.C = onItemSelectedListener;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setOverlapAnchor(boolean z) {
        this.r = true;
        this.q = z;
    }

    public void setPromptPosition(int i2) {
        this.x = i2;
    }

    public void setPromptView(@Nullable View view) {
        boolean isShowing = isShowing();
        if (isShowing) {
            i();
        }
        this.w = view;
        if (isShowing) {
            show();
        }
    }

    public void setSelection(int i2) {
        o oVar = this.j;
        if (!isShowing() || oVar == null) {
            return;
        }
        oVar.setListSelectionHidden(false);
        oVar.setSelection(i2);
        if (oVar.getChoiceMode() != 0) {
            oVar.setItemChecked(i2, true);
        }
    }

    public void setSoftInputMode(int i2) {
        this.M.setSoftInputMode(i2);
    }

    public void setVerticalOffset(int i2) {
        this.n = i2;
        this.p = true;
    }

    public void setWidth(int i2) {
        this.l = i2;
    }

    public void setWindowLayoutType(int i2) {
        this.o = i2;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        int e2 = e();
        boolean isInputMethodNotNeeded = isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.M, this.o);
        boolean z = true;
        if (this.M.isShowing()) {
            if (ViewCompat.isAttachedToWindow(getAnchorView())) {
                int i2 = this.l;
                if (i2 == -1) {
                    i2 = -1;
                } else if (i2 == -2) {
                    i2 = getAnchorView().getWidth();
                }
                int i3 = this.k;
                if (i3 == -1) {
                    if (!isInputMethodNotNeeded) {
                        e2 = -1;
                    }
                    if (isInputMethodNotNeeded) {
                        this.M.setWidth(this.l == -1 ? -1 : 0);
                        this.M.setHeight(0);
                    } else {
                        this.M.setWidth(this.l == -1 ? -1 : 0);
                        this.M.setHeight(-1);
                    }
                } else if (i3 != -2) {
                    e2 = i3;
                }
                PopupWindow popupWindow = this.M;
                if (this.u || this.t) {
                    z = false;
                }
                popupWindow.setOutsideTouchable(z);
                this.M.update(getAnchorView(), this.m, this.n, i2 < 0 ? -1 : i2, e2 < 0 ? -1 : e2);
                return;
            }
            return;
        }
        int i4 = this.l;
        if (i4 == -1) {
            i4 = -1;
        } else if (i4 == -2) {
            i4 = getAnchorView().getWidth();
        }
        int i5 = this.k;
        if (i5 == -1) {
            e2 = -1;
        } else if (i5 != -2) {
            e2 = i5;
        }
        this.M.setWidth(i4);
        this.M.setHeight(e2);
        j(true);
        this.M.setOutsideTouchable((this.u || this.t) ? false : true);
        this.M.setTouchInterceptor(this.E);
        if (this.r) {
            PopupWindowCompat.setOverlapAnchor(this.M, this.q);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = P;
            if (method != null) {
                try {
                    method.invoke(this.M, this.K);
                } catch (Exception e3) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e3);
                }
            }
        } else {
            e.a(this.M, this.K);
        }
        PopupWindowCompat.showAsDropDown(this.M, getAnchorView(), this.m, this.n, this.s);
        this.j.setSelection(-1);
        if (!this.L || this.j.isInTouchMode()) {
            clearListSelection();
        }
        if (this.L) {
            return;
        }
        this.I.post(this.G);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        this.k = -2;
        this.l = -2;
        this.o = 1002;
        this.s = 0;
        this.t = false;
        this.u = false;
        this.v = Integer.MAX_VALUE;
        this.x = 0;
        this.D = new j();
        this.E = new i();
        this.F = new h();
        this.G = new f();
        this.J = new Rect();
        this.h = context;
        this.I = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListPopupWindow, i2, i3);
        this.m = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.n = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.p = true;
        }
        obtainStyledAttributes.recycle();
        androidx.appcompat.widget.h hVar = new androidx.appcompat.widget.h(context, attributeSet, i2, i3);
        this.M = hVar;
        hVar.setInputMethodMode(1);
    }
}
