package com.h6ah4i.android.widget.advrecyclerview.expandable;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemIdComposer;
import com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import java.util.List;
/* loaded from: classes11.dex */
public class c extends SimpleWrapperAdapter<RecyclerView.ViewHolder> implements DraggableItemAdapter<RecyclerView.ViewHolder>, SwipeableItemAdapter<RecyclerView.ViewHolder> {
    public ExpandableItemAdapter j;
    public RecyclerViewExpandableItemManager k;
    public b l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public RecyclerViewExpandableItemManager.OnGroupExpandListener u;
    public RecyclerViewExpandableItemManager.OnGroupCollapseListener v;

    public c(RecyclerViewExpandableItemManager recyclerViewExpandableItemManager, RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, long[] jArr) {
        super(adapter);
        this.m = -1;
        this.n = -1;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = -1;
        this.t = -1;
        ExpandableItemAdapter h = h(adapter);
        this.j = h;
        if (h == null) {
            throw new IllegalArgumentException("adapter does not implement ExpandableItemAdapter");
        }
        if (recyclerViewExpandableItemManager != null) {
            this.k = recyclerViewExpandableItemManager;
            b bVar = new b();
            this.l = bVar;
            bVar.b(this.j, 0, this.k.getDefaultGroupsExpandedState());
            if (jArr != null) {
                this.l.B(jArr, null, null, null);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("manager cannot be null");
    }

    public static void L(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ExpandableItemViewHolder) {
            ExpandableItemViewHolder expandableItemViewHolder = (ExpandableItemViewHolder) viewHolder;
            int expandStateFlags = expandableItemViewHolder.getExpandStateFlags();
            if (expandStateFlags != -1 && ((expandStateFlags ^ i) & 4) != 0) {
                i |= 8;
            }
            if (expandStateFlags == -1 || ((expandStateFlags ^ i) & Integer.MAX_VALUE) != 0) {
                i |= Integer.MIN_VALUE;
            }
            expandableItemViewHolder.setExpandStateFlags(i);
        }
    }

    public static ExpandableItemAdapter h(RecyclerView.Adapter adapter) {
        return (ExpandableItemAdapter) WrapperAdapterUtils.findWrappedAdapter(adapter, ExpandableItemAdapter.class);
    }

    public static boolean o(ItemDraggableRange itemDraggableRange) {
        return itemDraggableRange.getClass().equals(ChildPositionItemDraggableRange.class);
    }

    public static boolean q(ItemDraggableRange itemDraggableRange) {
        return itemDraggableRange.getClass().equals(GroupPositionItemDraggableRange.class) || itemDraggableRange.getClass().equals(ItemDraggableRange.class);
    }

    public void A(int i, Object obj) {
        int j = this.l.j(a.c(i));
        int m = this.l.m(i);
        if (j != -1) {
            notifyItemRangeChanged(j, m + 1, obj);
        }
    }

    public void B(int i, Object obj) {
        int j = this.l.j(a.c(i));
        if (j != -1) {
            notifyItemChanged(j, obj);
        }
    }

    public void C(int i, boolean z) {
        if (this.l.p(i, z) > 0) {
            notifyItemInserted(this.l.j(a.c(i)));
            I(i, 1, false, null);
        }
    }

    public void D(int i, int i2) {
        long packedPositionForGroup = RecyclerViewExpandableItemManager.getPackedPositionForGroup(i);
        long packedPositionForGroup2 = RecyclerViewExpandableItemManager.getPackedPositionForGroup(i2);
        int l = l(packedPositionForGroup);
        int l2 = l(packedPositionForGroup2);
        boolean p = p(i);
        boolean p2 = p(i2);
        this.l.w(i, i2);
        if (!p && !p2) {
            notifyItemMoved(l, l2);
        } else {
            notifyDataSetChanged();
        }
    }

    public void E(int i, int i2, boolean z) {
        int q = this.l.q(i, i2, z);
        if (q > 0) {
            notifyItemRangeInserted(this.l.j(a.c(i)), q);
            I(i, i2, false, null);
        }
    }

    public void F(int i, int i2) {
        int j = this.l.j(a.c(i));
        int A = this.l.A(i, i2);
        if (A > 0) {
            notifyItemRangeRemoved(j, A);
        }
    }

    public void G(int i) {
        int j = this.l.j(a.c(i));
        int z = this.l.z(i);
        if (z > 0) {
            notifyItemRangeRemoved(j, z);
        }
    }

    public boolean H(@NonNull RecyclerView.ViewHolder viewHolder, int i, int i2, int i3) {
        if (this.j == null) {
            return false;
        }
        long h = this.l.h(i);
        int d = a.d(h);
        if (a.a(h) != -1) {
            return false;
        }
        boolean z = !this.l.u(d);
        if (this.j.onCheckCanExpandOrCollapseGroup(viewHolder, d, i2, i3, z)) {
            if (z) {
                f(d, true, null);
            } else {
                c(d, true, null);
            }
            return true;
        }
        return false;
    }

    public final void I(int i, int i2, boolean z, Object obj) {
        if (this.u != null) {
            for (int i3 = 0; i3 < i2; i3++) {
                this.u.onGroupExpand(i + i3, z, obj);
            }
        }
    }

    public final void J() {
        b bVar = this.l;
        if (bVar != null) {
            long[] l = bVar.l();
            this.l.b(this.j, 0, this.k.getDefaultGroupsExpandedState());
            this.l.B(l, null, null, null);
        }
    }

    public void K(long[] jArr, boolean z, boolean z2) {
        this.l.B(jArr, z ? this.j : null, z2 ? this.u : null, z2 ? this.v : null);
    }

    public void M(RecyclerViewExpandableItemManager.OnGroupCollapseListener onGroupCollapseListener) {
        this.v = onGroupCollapseListener;
    }

    public void N(RecyclerViewExpandableItemManager.OnGroupExpandListener onGroupExpandListener) {
        this.u = onGroupExpandListener;
    }

    public void b() {
        if (this.l.t() || this.l.r()) {
            return;
        }
        this.l.b(this.j, 2, this.k.getDefaultGroupsExpandedState());
        notifyDataSetChanged();
    }

    public boolean c(int i, boolean z, Object obj) {
        if (this.l.u(i) && this.j.onHookGroupCollapse(i, z, obj)) {
            if (this.l.c(i)) {
                notifyItemRangeRemoved(this.l.j(a.c(i)) + 1, this.l.f(i));
            }
            notifyItemChanged(this.l.j(a.c(i)), obj);
            RecyclerViewExpandableItemManager.OnGroupCollapseListener onGroupCollapseListener = this.v;
            if (onGroupCollapseListener != null) {
                onGroupCollapseListener.onGroupCollapse(i, z, obj);
            }
            return true;
        }
        return false;
    }

    public final void d(RecyclerView.ViewHolder viewHolder, int i, int i2) {
        if (viewHolder instanceof DraggableItemViewHolder) {
            DraggableItemViewHolder draggableItemViewHolder = (DraggableItemViewHolder) viewHolder;
            int i3 = this.m;
            boolean z = false;
            boolean z2 = (i3 == -1 || this.n == -1) ? false : true;
            int i4 = this.o;
            boolean z3 = (i4 == -1 || this.p == -1) ? false : true;
            boolean z4 = i >= i3 && i <= this.n;
            boolean z5 = i != -1 && i2 >= i4 && i2 <= this.p;
            int dragStateFlags = draggableItemViewHolder.getDragStateFlags();
            if ((dragStateFlags & 1) != 0 && (dragStateFlags & 4) == 0 && ((!z2 || z4) && (!z3 || (z3 && z5)))) {
                z = true;
            }
            if (z) {
                draggableItemViewHolder.setDragStateFlags(dragStateFlags | 4 | Integer.MIN_VALUE);
            }
        }
    }

    public void e() {
        if (this.l.t() || this.l.s()) {
            return;
        }
        this.l.b(this.j, 1, this.k.getDefaultGroupsExpandedState());
        notifyDataSetChanged();
    }

    public boolean f(int i, boolean z, Object obj) {
        if (!this.l.u(i) && this.j.onHookGroupExpand(i, z, obj)) {
            if (this.l.e(i)) {
                notifyItemRangeInserted(this.l.j(a.c(i)) + 1, this.l.f(i));
            }
            notifyItemChanged(this.l.j(a.c(i)), obj);
            RecyclerViewExpandableItemManager.OnGroupExpandListener onGroupExpandListener = this.u;
            if (onGroupExpandListener != null) {
                onGroupExpandListener.onGroupExpand(i, z, obj);
            }
            return true;
        }
        return false;
    }

    public int g() {
        return this.l.g();
    }

    public int getChildCount(int i) {
        return this.j.getChildCount(i);
    }

    public int getGroupCount() {
        return this.j.getGroupCount();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.l.k();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        if (this.j == null) {
            return -1L;
        }
        long h = this.l.h(i);
        int d = a.d(h);
        int a2 = a.a(h);
        if (a2 == -1) {
            return ItemIdComposer.composeExpandableGroupId(this.j.getGroupId(d));
        }
        return ItemIdComposer.composeExpandableChildId(this.j.getGroupId(d), this.j.getChildId(d, a2));
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        int childItemViewType;
        if (this.j == null) {
            return 0;
        }
        long h = this.l.h(i);
        int d = a.d(h);
        int a2 = a.a(h);
        if (a2 == -1) {
            childItemViewType = this.j.getGroupItemViewType(d);
        } else {
            childItemViewType = this.j.getChildItemViewType(d, a2);
        }
        if ((childItemViewType & Integer.MIN_VALUE) == 0) {
            return a2 == -1 ? childItemViewType | Integer.MIN_VALUE : childItemViewType;
        }
        throw new IllegalStateException("Illegal view type (type = " + Integer.toHexString(childItemViewType) + ")");
    }

    public long i(int i) {
        return this.l.h(i);
    }

    public int j() {
        return this.l.i();
    }

    public long[] k() {
        b bVar = this.l;
        if (bVar != null) {
            return bVar.l();
        }
        return null;
    }

    public int l(long j) {
        return this.l.j(j);
    }

    public boolean m() {
        return this.l.r();
    }

    public boolean n() {
        return this.l.s();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i, @NonNull List<Object> list) {
        if (this.j == null) {
            return;
        }
        long h = this.l.h(i);
        int d = a.d(h);
        int a2 = a.a(h);
        int itemViewType = viewHolder.getItemViewType() & Integer.MAX_VALUE;
        int i2 = a2 == -1 ? 1 : 2;
        if (this.l.u(d)) {
            i2 |= 4;
        }
        L(viewHolder, i2);
        d(viewHolder, d, a2);
        if (a2 == -1) {
            this.j.onBindGroupViewHolder(viewHolder, d, itemViewType, list);
        } else {
            this.j.onBindChildViewHolder(viewHolder, d, a2, itemViewType, list);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    public boolean onCheckCanDrop(int i, int i2) {
        ExpandableItemAdapter expandableItemAdapter = this.j;
        if (expandableItemAdapter instanceof ExpandableDraggableItemAdapter) {
            if (expandableItemAdapter.getGroupCount() < 1) {
                return false;
            }
            ExpandableDraggableItemAdapter expandableDraggableItemAdapter = (ExpandableDraggableItemAdapter) this.j;
            long h = this.l.h(i);
            int d = a.d(h);
            int a2 = a.a(h);
            long h2 = this.l.h(i2);
            int d2 = a.d(h2);
            int a3 = a.a(h2);
            boolean z = a2 == -1;
            boolean z2 = a3 == -1;
            if (z) {
                if (d != d2 && i < i2) {
                    boolean u = this.l.u(d2);
                    int m = this.l.m(d2);
                    if (z2) {
                        z2 = !u;
                    } else {
                        z2 = a3 == m - 1;
                    }
                }
                if (z2) {
                    return expandableDraggableItemAdapter.onCheckGroupCanDrop(d, d2);
                }
                return false;
            }
            boolean u2 = this.l.u(d2);
            if (i < i2) {
                if (z2) {
                    a3 = u2 ? 0 : this.l.f(d2);
                }
            } else if (z2) {
                if (d2 > 0) {
                    d2--;
                    a3 = this.l.f(d2);
                } else {
                    r2 = false;
                }
            }
            if (r2) {
                return expandableDraggableItemAdapter.onCheckChildCanDrop(d, a2, d2, a3);
            }
            return false;
        }
        return true;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    public boolean onCheckCanStartDrag(@NonNull RecyclerView.ViewHolder viewHolder, int i, int i2, int i3) {
        boolean onCheckChildCanStartDrag;
        ExpandableItemAdapter expandableItemAdapter = this.j;
        if (expandableItemAdapter instanceof ExpandableDraggableItemAdapter) {
            ExpandableDraggableItemAdapter expandableDraggableItemAdapter = (ExpandableDraggableItemAdapter) expandableItemAdapter;
            long h = this.l.h(i);
            int d = a.d(h);
            int a2 = a.a(h);
            if (a2 == -1) {
                onCheckChildCanStartDrag = expandableDraggableItemAdapter.onCheckGroupCanStartDrag(viewHolder, d, i2, i3);
            } else {
                onCheckChildCanStartDrag = expandableDraggableItemAdapter.onCheckChildCanStartDrag(viewHolder, d, a2, i2, i3);
            }
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            return onCheckChildCanStartDrag;
        }
        return false;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder onCreateChildViewHolder;
        ExpandableItemAdapter expandableItemAdapter = this.j;
        if (expandableItemAdapter != null) {
            int i2 = Integer.MAX_VALUE & i;
            if ((i & Integer.MIN_VALUE) != 0) {
                onCreateChildViewHolder = expandableItemAdapter.onCreateGroupViewHolder(viewGroup, i2);
            } else {
                onCreateChildViewHolder = expandableItemAdapter.onCreateChildViewHolder(viewGroup, i2);
            }
            if (onCreateChildViewHolder instanceof ExpandableItemViewHolder) {
                ((ExpandableItemViewHolder) onCreateChildViewHolder).setExpandStateFlags(-1);
            }
            return onCreateChildViewHolder;
        }
        throw new IllegalStateException();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    public ItemDraggableRange onGetItemDraggableRange(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ExpandableItemAdapter expandableItemAdapter = this.j;
        if ((expandableItemAdapter instanceof ExpandableDraggableItemAdapter) && expandableItemAdapter.getGroupCount() >= 1) {
            ExpandableDraggableItemAdapter expandableDraggableItemAdapter = (ExpandableDraggableItemAdapter) this.j;
            long h = this.l.h(i);
            int d = a.d(h);
            int a2 = a.a(h);
            if (a2 == -1) {
                ItemDraggableRange onGetGroupItemDraggableRange = expandableDraggableItemAdapter.onGetGroupItemDraggableRange(viewHolder, d);
                if (onGetGroupItemDraggableRange == null) {
                    return new ItemDraggableRange(0, Math.max(0, (this.l.k() - this.l.m(Math.max(0, this.j.getGroupCount() - 1))) - 1));
                } else if (q(onGetGroupItemDraggableRange)) {
                    long c = a.c(onGetGroupItemDraggableRange.getStart());
                    long c2 = a.c(onGetGroupItemDraggableRange.getEnd());
                    int j = this.l.j(c);
                    int j2 = this.l.j(c2);
                    if (onGetGroupItemDraggableRange.getEnd() > d) {
                        j2 += this.l.m(onGetGroupItemDraggableRange.getEnd());
                    }
                    this.m = onGetGroupItemDraggableRange.getStart();
                    this.n = onGetGroupItemDraggableRange.getEnd();
                    return new ItemDraggableRange(j, j2);
                } else {
                    throw new IllegalStateException("Invalid range specified: " + onGetGroupItemDraggableRange);
                }
            }
            ItemDraggableRange onGetChildItemDraggableRange = expandableDraggableItemAdapter.onGetChildItemDraggableRange(viewHolder, d, a2);
            if (onGetChildItemDraggableRange == null) {
                return new ItemDraggableRange(1, Math.max(1, this.l.k() - 1));
            }
            if (q(onGetChildItemDraggableRange)) {
                long c3 = a.c(onGetChildItemDraggableRange.getStart());
                int j3 = this.l.j(a.c(onGetChildItemDraggableRange.getEnd())) + this.l.m(onGetChildItemDraggableRange.getEnd());
                int min = Math.min(this.l.j(c3) + 1, j3);
                this.m = onGetChildItemDraggableRange.getStart();
                this.n = onGetChildItemDraggableRange.getEnd();
                return new ItemDraggableRange(min, j3);
            } else if (o(onGetChildItemDraggableRange)) {
                int max = Math.max(this.l.m(d) - 1, 0);
                int min2 = Math.min(onGetChildItemDraggableRange.getStart(), max);
                int min3 = Math.min(onGetChildItemDraggableRange.getEnd(), max);
                long b = a.b(d, min2);
                long b2 = a.b(d, min3);
                int j4 = this.l.j(b);
                int j5 = this.l.j(b2);
                this.o = min2;
                this.p = min3;
                return new ItemDraggableRange(j4, j5);
            } else {
                throw new IllegalStateException("Invalid range specified: " + onGetChildItemDraggableRange);
            }
        }
        return null;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter
    public int onGetSwipeReactionType(@NonNull RecyclerView.ViewHolder viewHolder, int i, int i2, int i3) {
        ExpandableItemAdapter expandableItemAdapter = this.j;
        if (expandableItemAdapter instanceof BaseExpandableSwipeableItemAdapter) {
            BaseExpandableSwipeableItemAdapter baseExpandableSwipeableItemAdapter = (BaseExpandableSwipeableItemAdapter) expandableItemAdapter;
            long h = this.l.h(i);
            int d = a.d(h);
            int a2 = a.a(h);
            if (a2 == -1) {
                return baseExpandableSwipeableItemAdapter.onGetGroupItemSwipeReactionType(viewHolder, d, i2, i3);
            }
            return baseExpandableSwipeableItemAdapter.onGetChildItemSwipeReactionType(viewHolder, d, a2, i2, i3);
        }
        return 0;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterChanged() {
        J();
        super.onHandleWrappedAdapterChanged();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterItemRangeChanged(int i, int i2) {
        super.onHandleWrappedAdapterItemRangeChanged(i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterItemRangeInserted(int i, int i2) {
        J();
        super.onHandleWrappedAdapterItemRangeInserted(i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterItemRangeRemoved(int i, int i2) {
        if (i2 == 1) {
            long h = this.l.h(i);
            int d = a.d(h);
            int a2 = a.a(h);
            if (a2 == -1) {
                this.l.z(d);
            } else {
                this.l.x(d, a2);
            }
        } else {
            J();
        }
        super.onHandleWrappedAdapterItemRangeRemoved(i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterRangeMoved(int i, int i2, int i3) {
        J();
        super.onHandleWrappedAdapterRangeMoved(i, i2, i3);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    public void onItemDragFinished(int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = this.q;
        int i8 = this.r;
        int i9 = this.s;
        int i10 = this.t;
        this.m = -1;
        this.n = -1;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = -1;
        this.t = -1;
        if (this.j instanceof ExpandableDraggableItemAdapter) {
            if (i7 == -1 && i8 == -1) {
                long h = this.l.h(i);
                int d = a.d(h);
                i4 = a.a(h);
                i6 = i4;
                i3 = d;
                i5 = i3;
            } else {
                i3 = i7;
                i4 = i8;
                i5 = i9;
                i6 = i10;
            }
            ExpandableDraggableItemAdapter expandableDraggableItemAdapter = (ExpandableDraggableItemAdapter) this.j;
            if (i4 == -1) {
                expandableDraggableItemAdapter.onGroupDragFinished(i3, i5, z);
            } else {
                expandableDraggableItemAdapter.onChildDragFinished(i3, i4, i5, i6, z);
            }
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    public void onItemDragStarted(int i) {
        ExpandableItemAdapter expandableItemAdapter = this.j;
        if (expandableItemAdapter instanceof ExpandableDraggableItemAdapter) {
            ExpandableDraggableItemAdapter expandableDraggableItemAdapter = (ExpandableDraggableItemAdapter) expandableItemAdapter;
            long h = this.l.h(i);
            int d = a.d(h);
            int a2 = a.a(h);
            if (a2 == -1) {
                expandableDraggableItemAdapter.onGroupDragStarted(d);
            } else {
                expandableDraggableItemAdapter.onChildDragStarted(d, a2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0095 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a5  */
    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMoveItem(int r11, int r12) {
        /*
            Method dump skipped, instructions count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.expandable.c.onMoveItem(int, int):void");
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onRelease() {
        super.onRelease();
        this.j = null;
        this.k = null;
        this.u = null;
        this.v = null;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter
    public void onSetSwipeBackground(@NonNull RecyclerView.ViewHolder viewHolder, int i, int i2) {
        ExpandableItemAdapter expandableItemAdapter = this.j;
        if (expandableItemAdapter instanceof BaseExpandableSwipeableItemAdapter) {
            BaseExpandableSwipeableItemAdapter baseExpandableSwipeableItemAdapter = (BaseExpandableSwipeableItemAdapter) expandableItemAdapter;
            long h = this.l.h(i);
            int d = a.d(h);
            int a2 = a.a(h);
            if (a2 == -1) {
                baseExpandableSwipeableItemAdapter.onSetGroupItemSwipeBackground(viewHolder, d, i2);
            } else {
                baseExpandableSwipeableItemAdapter.onSetChildItemSwipeBackground(viewHolder, d, a2, i2);
            }
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter
    public SwipeResultAction onSwipeItem(@NonNull RecyclerView.ViewHolder viewHolder, int i, int i2) {
        ExpandableItemAdapter expandableItemAdapter = this.j;
        if ((expandableItemAdapter instanceof BaseExpandableSwipeableItemAdapter) && i != -1) {
            long h = this.l.h(i);
            return d.a((BaseExpandableSwipeableItemAdapter) expandableItemAdapter, viewHolder, a.d(h), a.a(h), i2);
        }
        return null;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter
    public void onSwipeItemStarted(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ExpandableItemAdapter expandableItemAdapter = this.j;
        if (expandableItemAdapter instanceof BaseExpandableSwipeableItemAdapter) {
            BaseExpandableSwipeableItemAdapter baseExpandableSwipeableItemAdapter = (BaseExpandableSwipeableItemAdapter) expandableItemAdapter;
            long h = this.l.h(i);
            int d = a.d(h);
            int a2 = a.a(h);
            if (a2 == -1) {
                baseExpandableSwipeableItemAdapter.onSwipeGroupItemStarted(viewHolder, d);
            } else {
                baseExpandableSwipeableItemAdapter.onSwipeChildItemStarted(viewHolder, d, a2);
            }
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ExpandableItemViewHolder) {
            ((ExpandableItemViewHolder) viewHolder).setExpandStateFlags(-1);
        }
        super.onViewRecycled(viewHolder, i);
    }

    public boolean p(int i) {
        return this.l.u(i);
    }

    public void r(int i, int i2, Object obj) {
        v(i, i2, 1, obj);
    }

    public void s(int i, int i2) {
        this.l.n(i, i2);
        int j = this.l.j(a.b(i, i2));
        if (j != -1) {
            notifyItemInserted(j);
        }
    }

    public void t(int i, int i2, int i3) {
        u(i, i2, i, i3);
    }

    public void u(int i, int i2, int i3, int i4) {
        long packedPositionForChild = RecyclerViewExpandableItemManager.getPackedPositionForChild(i, i2);
        long packedPositionForChild2 = RecyclerViewExpandableItemManager.getPackedPositionForChild(i3, i4);
        int l = l(packedPositionForChild);
        int l2 = l(packedPositionForChild2);
        this.l.v(i, i2, i3, i4);
        if (l != -1 && l2 != -1) {
            notifyItemMoved(l, l2);
        } else if (l != -1) {
            notifyItemRemoved(l);
        } else if (l2 != -1) {
            notifyItemInserted(l2);
        }
    }

    public void v(int i, int i2, int i3, Object obj) {
        int m = this.l.m(i);
        if (m <= 0 || i2 >= m) {
            return;
        }
        int j = this.l.j(a.b(i, 0));
        if (j != -1) {
            notifyItemRangeChanged(j + i2, Math.min(i3, m - i2), obj);
        }
    }

    public void w(int i, int i2, int i3) {
        this.l.o(i, i2, i3);
        int j = this.l.j(a.b(i, i2));
        if (j != -1) {
            notifyItemRangeInserted(j, i3);
        }
    }

    public void x(int i, int i2, int i3) {
        int j = this.l.j(a.b(i, i2));
        this.l.y(i, i2, i3);
        if (j != -1) {
            notifyItemRangeRemoved(j, i3);
        }
    }

    public void y(int i, int i2) {
        int j = this.l.j(a.b(i, i2));
        this.l.x(i, i2);
        if (j != -1) {
            notifyItemRemoved(j);
        }
    }

    public void z(int i, Object obj) {
        int m = this.l.m(i);
        if (m > 0) {
            int j = this.l.j(a.b(i, 0));
            if (j != -1) {
                notifyItemRangeChanged(j, m, obj);
            }
        }
    }
}
