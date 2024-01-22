package com.h6ah4i.android.widget.advrecyclerview.expandable;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.adapter.AdapterPath;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemIdComposer;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemViewTypeComposer;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
/* loaded from: classes11.dex */
public class RecyclerViewExpandableItemManager implements ExpandableItemConstants {
    public static final long NO_EXPANDABLE_POSITION = -1;

    /* renamed from: a  reason: collision with root package name */
    public SavedState f11915a;
    public RecyclerView b;
    public c c;
    public OnGroupExpandListener e;
    public OnGroupCollapseListener f;
    public int h;
    public int i;
    public int j;
    public long g = -1;
    public boolean k = false;
    public RecyclerView.OnItemTouchListener d = new a();

    /* loaded from: classes11.dex */
    public interface OnGroupCollapseListener {
        void onGroupCollapse(int i, boolean z, Object obj);
    }

    /* loaded from: classes11.dex */
    public interface OnGroupExpandListener {
        void onGroupExpand(int i, boolean z, Object obj);
    }

    /* loaded from: classes11.dex */
    public class a implements RecyclerView.OnItemTouchListener {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            return RecyclerViewExpandableItemManager.this.c(recyclerView, motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        }
    }

    public RecyclerViewExpandableItemManager(@Nullable Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.f11915a = (SavedState) parcelable;
        }
    }

    public static long getChildItemId(long j) {
        return ItemIdComposer.extractExpandableChildIdPart(j);
    }

    public static int getChildViewType(int i) {
        return ItemViewTypeComposer.extractWrappedViewTypePart(i);
    }

    public static long getCombinedChildId(long j, long j2) {
        return ItemIdComposer.composeExpandableChildId(j, j2);
    }

    public static long getCombinedGroupId(long j) {
        return ItemIdComposer.composeExpandableGroupId(j);
    }

    public static long getGroupItemId(long j) {
        return ItemIdComposer.extractExpandableGroupIdPart(j);
    }

    public static int getGroupViewType(int i) {
        return ItemViewTypeComposer.extractWrappedViewTypePart(i);
    }

    public static int getPackedPositionChild(long j) {
        return com.h6ah4i.android.widget.advrecyclerview.expandable.a.a(j);
    }

    public static long getPackedPositionForChild(int i, int i2) {
        return com.h6ah4i.android.widget.advrecyclerview.expandable.a.b(i, i2);
    }

    public static long getPackedPositionForGroup(int i) {
        return com.h6ah4i.android.widget.advrecyclerview.expandable.a.c(i);
    }

    public static int getPackedPositionGroup(long j) {
        return com.h6ah4i.android.widget.advrecyclerview.expandable.a.d(j);
    }

    public static boolean isGroupItemId(long j) {
        return ItemIdComposer.isExpandableGroup(j);
    }

    public static boolean isGroupViewType(int i) {
        return ItemViewTypeComposer.isExpandableGroup(i);
    }

    public final void a(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        RecyclerView.ViewHolder findChildViewHolderUnderWithTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithTranslation(recyclerView, motionEvent.getX(), motionEvent.getY());
        this.i = (int) (motionEvent.getX() + 0.5f);
        this.j = (int) (motionEvent.getY() + 0.5f);
        if (findChildViewHolderUnderWithTranslation instanceof ExpandableItemViewHolder) {
            this.g = findChildViewHolderUnderWithTranslation.getItemId();
        } else {
            this.g = -1L;
        }
    }

    public void attachRecyclerView(@NonNull RecyclerView recyclerView) {
        if (!isReleased()) {
            if (this.b == null) {
                this.b = recyclerView;
                recyclerView.addOnItemTouchListener(this.d);
                this.h = ViewConfiguration.get(this.b.getContext()).getScaledTouchSlop();
                return;
            }
            throw new IllegalStateException("RecyclerView instance has already been set");
        }
        throw new IllegalStateException("Accessing released object");
    }

    public final boolean b(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        RecyclerView.ViewHolder findChildViewHolderUnderWithTranslation;
        long j = this.g;
        int i = this.i;
        int i2 = this.j;
        this.g = -1L;
        this.i = 0;
        this.j = 0;
        if (j == -1 || motionEvent.getActionMasked() != 1 || this.b.isComputingLayout()) {
            return false;
        }
        int x = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        int i3 = y - i2;
        if (Math.abs(x - i) < this.h && Math.abs(i3) < this.h && (findChildViewHolderUnderWithTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithTranslation(recyclerView, motionEvent.getX(), motionEvent.getY())) != null && findChildViewHolderUnderWithTranslation.getItemId() == j) {
            int unwrapPosition = WrapperAdapterUtils.unwrapPosition(this.b.getAdapter(), this.c, CustomRecyclerViewUtils.getSynchronizedPosition(findChildViewHolderUnderWithTranslation));
            if (unwrapPosition == -1) {
                return false;
            }
            View view = findChildViewHolderUnderWithTranslation.itemView;
            int left = view.getLeft();
            return this.c.H(findChildViewHolderUnderWithTranslation, unwrapPosition, x - (left + ((int) (view.getTranslationX() + 0.5f))), y - (view.getTop() + ((int) (view.getTranslationY() + 0.5f))));
        }
        return false;
    }

    public boolean c(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        if (this.c == null) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            a(recyclerView, motionEvent);
        } else if (actionMasked == 1 || actionMasked == 3) {
            b(recyclerView, motionEvent);
            return false;
        }
        return false;
    }

    public void collapseAll() {
        c cVar = this.c;
        if (cVar != null) {
            cVar.b();
        }
    }

    public boolean collapseGroup(int i) {
        return collapseGroup(i, null);
    }

    @NonNull
    public RecyclerView.Adapter createWrappedAdapter(@NonNull RecyclerView.Adapter adapter) {
        if (adapter.hasStableIds()) {
            if (this.c == null) {
                SavedState savedState = this.f11915a;
                long[] jArr = savedState != null ? savedState.h : null;
                this.f11915a = null;
                c cVar = new c(this, adapter, jArr);
                this.c = cVar;
                cVar.N(this.e);
                this.e = null;
                this.c.M(this.f);
                this.f = null;
                return this.c;
            }
            throw new IllegalStateException("already have a wrapped adapter");
        }
        throw new IllegalArgumentException("The passed adapter does not support stable IDs");
    }

    public void expandAll() {
        c cVar = this.c;
        if (cVar != null) {
            cVar.e();
        }
    }

    public boolean expandGroup(int i) {
        return expandGroup(i, null);
    }

    public int getChildCount(int i) {
        return this.c.getChildCount(i);
    }

    public int getCollapsedGroupsCount() {
        return this.c.g();
    }

    public boolean getDefaultGroupsExpandedState() {
        return this.k;
    }

    public long getExpandablePosition(int i) {
        c cVar = this.c;
        if (cVar == null) {
            return -1L;
        }
        return cVar.i(i);
    }

    public int getExpandedGroupsCount() {
        return this.c.j();
    }

    public int getFlatPosition(long j) {
        c cVar = this.c;
        if (cVar == null) {
            return -1;
        }
        return cVar.l(j);
    }

    public int getGroupCount() {
        return this.c.getGroupCount();
    }

    @NonNull
    public Parcelable getSavedState() {
        c cVar = this.c;
        return new SavedState(cVar != null ? cVar.k() : null);
    }

    public boolean isAllGroupsCollapsed() {
        return this.c.m();
    }

    public boolean isAllGroupsExpanded() {
        return this.c.n();
    }

    public boolean isGroupExpanded(int i) {
        c cVar = this.c;
        return cVar != null && cVar.p(i);
    }

    public boolean isReleased() {
        return this.d == null;
    }

    public void notifyChildItemChanged(int i, int i2) {
        this.c.r(i, i2, null);
    }

    public void notifyChildItemInserted(int i, int i2) {
        this.c.s(i, i2);
    }

    public void notifyChildItemMoved(int i, int i2, int i3) {
        this.c.t(i, i2, i3);
    }

    public void notifyChildItemRangeChanged(int i, int i2, int i3) {
        this.c.v(i, i2, i3, null);
    }

    public void notifyChildItemRangeInserted(int i, int i2, int i3) {
        this.c.w(i, i2, i3);
    }

    public void notifyChildItemRangeRemoved(int i, int i2, int i3) {
        this.c.x(i, i2, i3);
    }

    public void notifyChildItemRemoved(int i, int i2) {
        this.c.y(i, i2);
    }

    public void notifyChildrenOfGroupItemChanged(int i) {
        this.c.z(i, null);
    }

    public void notifyGroupAndChildrenItemsChanged(int i) {
        this.c.A(i, null);
    }

    public void notifyGroupItemChanged(int i) {
        this.c.B(i, null);
    }

    public void notifyGroupItemInserted(int i) {
        notifyGroupItemInserted(i, this.k);
    }

    public void notifyGroupItemMoved(int i, int i2) {
        this.c.D(i, i2);
    }

    public void notifyGroupItemRangeInserted(int i, int i2) {
        notifyGroupItemRangeInserted(i, i2, this.k);
    }

    public void notifyGroupItemRangeRemoved(int i, int i2) {
        this.c.F(i, i2);
    }

    public void notifyGroupItemRemoved(int i) {
        this.c.G(i);
    }

    public void release() {
        RecyclerView.OnItemTouchListener onItemTouchListener;
        RecyclerView recyclerView = this.b;
        if (recyclerView != null && (onItemTouchListener = this.d) != null) {
            recyclerView.removeOnItemTouchListener(onItemTouchListener);
        }
        this.d = null;
        this.e = null;
        this.f = null;
        this.b = null;
        this.f11915a = null;
    }

    public void restoreState(@Nullable Parcelable parcelable) {
        restoreState(parcelable, false, false);
    }

    public void scrollToGroup(int i, int i2) {
        scrollToGroup(i, i2, 0, 0, null);
    }

    public void scrollToGroupWithTotalChildrenHeight(int i, int i2, int i3, int i4) {
        scrollToGroupWithTotalChildrenHeight(i, i2, i3, i4, null);
    }

    public void setDefaultGroupsExpandedState(boolean z) {
        this.k = z;
    }

    public void setOnGroupCollapseListener(@Nullable OnGroupCollapseListener onGroupCollapseListener) {
        c cVar = this.c;
        if (cVar != null) {
            cVar.M(onGroupCollapseListener);
        } else {
            this.f = onGroupCollapseListener;
        }
    }

    public void setOnGroupExpandListener(@Nullable OnGroupExpandListener onGroupExpandListener) {
        c cVar = this.c;
        if (cVar != null) {
            cVar.N(onGroupExpandListener);
        } else {
            this.e = onGroupExpandListener;
        }
    }

    /* loaded from: classes11.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final long[] h;

        /* loaded from: classes11.dex */
        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(long[] jArr) {
            this.h = jArr;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLongArray(this.h);
        }

        public SavedState(Parcel parcel) {
            this.h = parcel.createLongArray();
        }
    }

    public boolean collapseGroup(int i, Object obj) {
        c cVar = this.c;
        return cVar != null && cVar.c(i, false, obj);
    }

    public boolean expandGroup(int i, Object obj) {
        c cVar = this.c;
        return cVar != null && cVar.f(i, false, obj);
    }

    public void notifyChildItemChanged(int i, int i2, Object obj) {
        this.c.r(i, i2, obj);
    }

    public void notifyChildItemMoved(int i, int i2, int i3, int i4) {
        this.c.u(i, i2, i3, i4);
    }

    public void notifyChildItemRangeChanged(int i, int i2, int i3, Object obj) {
        this.c.v(i, i2, i3, obj);
    }

    public void notifyChildrenOfGroupItemChanged(int i, Object obj) {
        this.c.z(i, obj);
    }

    public void notifyGroupAndChildrenItemsChanged(int i, Object obj) {
        this.c.A(i, obj);
    }

    public void notifyGroupItemChanged(int i, Object obj) {
        this.c.B(i, obj);
    }

    public void notifyGroupItemInserted(int i, boolean z) {
        this.c.C(i, z);
    }

    public void notifyGroupItemRangeInserted(int i, int i2, boolean z) {
        this.c.E(i, i2, z);
    }

    public void restoreState(@Nullable Parcelable parcelable, boolean z, boolean z2) {
        if (parcelable == null) {
            return;
        }
        if (parcelable instanceof SavedState) {
            c cVar = this.c;
            if (cVar != null && this.b != null) {
                cVar.K(((SavedState) parcelable).h, z, z2);
                return;
            }
            throw new IllegalStateException("RecyclerView has not been attached");
        }
        throw new IllegalArgumentException("Illegal saved state object passed");
    }

    public void scrollToGroup(int i, int i2, int i3, int i4) {
        scrollToGroupWithTotalChildrenHeight(i, getChildCount(i) * i2, i3, i4, null);
    }

    public void scrollToGroupWithTotalChildrenHeight(int i, int i2, int i3, int i4, @Nullable AdapterPath adapterPath) {
        int flatPosition = getFlatPosition(getPackedPositionForGroup(i));
        if (adapterPath != null) {
            flatPosition = WrapperAdapterUtils.wrapPosition(adapterPath, this.c, this.b.getAdapter(), flatPosition);
        }
        RecyclerView.ViewHolder findViewHolderForLayoutPosition = this.b.findViewHolderForLayoutPosition(flatPosition);
        if (findViewHolderForLayoutPosition == null) {
            return;
        }
        if (!isGroupExpanded(i)) {
            i2 = 0;
        }
        int top = findViewHolderForLayoutPosition.itemView.getTop();
        int height = this.b.getHeight() - findViewHolderForLayoutPosition.itemView.getBottom();
        if (top <= i3) {
            ((LinearLayoutManager) this.b.getLayoutManager()).scrollToPositionWithOffset(flatPosition, (i3 - this.b.getPaddingTop()) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) findViewHolderForLayoutPosition.itemView.getLayoutParams())).topMargin);
            return;
        }
        int i5 = i2 + i4;
        if (height >= i5) {
            return;
        }
        this.b.smoothScrollBy(0, Math.min(top - i3, Math.max(0, i5 - height)));
    }

    public void scrollToGroup(int i, int i2, int i3, int i4, AdapterPath adapterPath) {
        scrollToGroupWithTotalChildrenHeight(i, getChildCount(i) * i2, i3, i4, adapterPath);
    }
}
