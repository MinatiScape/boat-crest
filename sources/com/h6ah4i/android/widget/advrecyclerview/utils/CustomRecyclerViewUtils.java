package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
/* loaded from: classes11.dex */
public class CustomRecyclerViewUtils {
    public static final int INVALID_SPAN_COUNT = -1;
    public static final int INVALID_SPAN_ID = -1;
    public static final int LAYOUT_TYPE_GRID_HORIZONTAL = 2;
    public static final int LAYOUT_TYPE_GRID_VERTICAL = 3;
    public static final int LAYOUT_TYPE_LINEAR_HORIZONTAL = 0;
    public static final int LAYOUT_TYPE_LINEAR_VERTICAL = 1;
    public static final int LAYOUT_TYPE_STAGGERED_GRID_HORIZONTAL = 4;
    public static final int LAYOUT_TYPE_STAGGERED_GRID_VERTICAL = 5;
    public static final int LAYOUT_TYPE_UNKNOWN = -1;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_UNKNOWN = -1;
    public static final int ORIENTATION_VERTICAL = 1;

    public static View a(@NonNull ViewGroup viewGroup, float f, float f2) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (f >= childAt.getLeft() && f <= childAt.getRight() && f2 >= childAt.getTop() && f2 <= childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public static int b(LinearLayoutManager linearLayoutManager) {
        View d = d(linearLayoutManager, 0, linearLayoutManager.getChildCount(), false, true);
        if (d == null) {
            return -1;
        }
        return linearLayoutManager.getPosition(d);
    }

    public static int c(LinearLayoutManager linearLayoutManager) {
        View d = d(linearLayoutManager, linearLayoutManager.getChildCount() - 1, -1, false, true);
        if (d == null) {
            return -1;
        }
        return linearLayoutManager.getPosition(d);
    }

    public static View d(LinearLayoutManager linearLayoutManager, int i, int i2, boolean z, boolean z2) {
        boolean z3 = linearLayoutManager.getOrientation() == 1;
        int height = z3 ? linearLayoutManager.getHeight() : linearLayoutManager.getWidth();
        int i3 = i2 <= i ? -1 : 1;
        View view = null;
        while (i != i2) {
            View childAt = linearLayoutManager.getChildAt(i);
            int top = z3 ? childAt.getTop() : childAt.getLeft();
            int bottom = z3 ? childAt.getBottom() : childAt.getRight();
            if (top < height && bottom > 0) {
                if (!z) {
                    return childAt;
                }
                if (top >= 0 && bottom <= height) {
                    return childAt;
                }
                if (z2 && view == null) {
                    view = childAt;
                }
            }
            i += i3;
        }
        return view;
    }

    public static View e(@Nullable RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            return null;
        }
        View view = viewHolder.itemView;
        if (ViewCompat.isLaidOut(view)) {
            return view;
        }
        return null;
    }

    public static int extractOrientation(int i) {
        switch (i) {
            case -1:
                return -1;
            case 0:
            case 2:
            case 4:
                return 0;
            case 1:
            case 3:
            case 5:
                return 1;
            default:
                throw new IllegalArgumentException("Unknown layout type (= " + i + ")");
        }
    }

    public static RecyclerView.ViewHolder findChildViewHolderUnderWithTranslation(@NonNull RecyclerView recyclerView, float f, float f2) {
        View findChildViewUnder = recyclerView.findChildViewUnder(f, f2);
        if (findChildViewUnder != null) {
            return recyclerView.getChildViewHolder(findChildViewUnder);
        }
        return null;
    }

    public static RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation(@NonNull RecyclerView recyclerView, float f, float f2) {
        View a2 = a(recyclerView, f, f2);
        if (a2 != null) {
            return recyclerView.getChildViewHolder(a2);
        }
        return null;
    }

    public static int findFirstCompletelyVisibleItemPosition(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        return -1;
    }

    public static int findFirstVisibleItemPosition(@NonNull RecyclerView recyclerView, boolean z) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            if (z) {
                return b((LinearLayoutManager) layoutManager);
            }
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        return -1;
    }

    public static int findLastCompletelyVisibleItemPosition(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        return -1;
    }

    public static int findLastVisibleItemPosition(@NonNull RecyclerView recyclerView, boolean z) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            if (z) {
                return c((LinearLayoutManager) layoutManager);
            }
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        return -1;
    }

    public static View findViewByPosition(@NonNull RecyclerView.LayoutManager layoutManager, int i) {
        if (i != -1) {
            return layoutManager.findViewByPosition(i);
        }
        return null;
    }

    public static Rect getDecorationOffsets(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view, @NonNull Rect rect) {
        rect.left = layoutManager.getLeftDecorationWidth(view);
        rect.right = layoutManager.getRightDecorationWidth(view);
        rect.top = layoutManager.getTopDecorationHeight(view);
        rect.bottom = layoutManager.getBottomDecorationHeight(view);
        return rect;
    }

    public static Rect getLayoutMargins(@NonNull View view, @NonNull Rect rect) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            rect.left = marginLayoutParams.leftMargin;
            rect.right = marginLayoutParams.rightMargin;
            rect.top = marginLayoutParams.topMargin;
            rect.bottom = marginLayoutParams.bottomMargin;
        } else {
            rect.bottom = 0;
            rect.top = 0;
            rect.right = 0;
            rect.left = 0;
        }
        return rect;
    }

    public static int getLayoutType(@NonNull RecyclerView recyclerView) {
        return getLayoutType(recyclerView.getLayoutManager());
    }

    public static int getOrientation(@NonNull RecyclerView recyclerView) {
        return getOrientation(recyclerView.getLayoutManager());
    }

    public static int getSpanCount(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return 1;
    }

    public static int getSpanIndex(@Nullable RecyclerView.ViewHolder viewHolder) {
        View e = e(viewHolder);
        if (e == null) {
            return -1;
        }
        ViewGroup.LayoutParams layoutParams = e.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return ((StaggeredGridLayoutManager.LayoutParams) layoutParams).getSpanIndex();
        }
        if (layoutParams instanceof GridLayoutManager.LayoutParams) {
            return ((GridLayoutManager.LayoutParams) layoutParams).getSpanIndex();
        }
        return layoutParams instanceof RecyclerView.LayoutParams ? 0 : -1;
    }

    public static int getSpanSize(@Nullable RecyclerView.ViewHolder viewHolder) {
        View e = e(viewHolder);
        if (e == null) {
            return -1;
        }
        ViewGroup.LayoutParams layoutParams = e.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            if (((StaggeredGridLayoutManager.LayoutParams) layoutParams).isFullSpan()) {
                return getSpanCount((RecyclerView) e.getParent());
            }
            return 1;
        } else if (layoutParams instanceof GridLayoutManager.LayoutParams) {
            return ((GridLayoutManager.LayoutParams) layoutParams).getSpanSize();
        } else {
            return layoutParams instanceof RecyclerView.LayoutParams ? 1 : -1;
        }
    }

    public static int getSynchronizedPosition(@NonNull RecyclerView.ViewHolder viewHolder) {
        int layoutPosition = viewHolder.getLayoutPosition();
        if (layoutPosition == viewHolder.getAdapterPosition()) {
            return layoutPosition;
        }
        return -1;
    }

    public static Rect getViewBounds(@NonNull View view, @NonNull Rect rect) {
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        return rect;
    }

    public static boolean isFullSpan(@Nullable RecyclerView.ViewHolder viewHolder) {
        View e = e(viewHolder);
        if (e == null) {
            return true;
        }
        ViewGroup.LayoutParams layoutParams = e.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return ((StaggeredGridLayoutManager.LayoutParams) layoutParams).isFullSpan();
        }
        if (layoutParams instanceof GridLayoutManager.LayoutParams) {
            return getSpanCount((RecyclerView) e.getParent()) == ((GridLayoutManager.LayoutParams) layoutParams).getSpanSize();
        }
        boolean z = layoutParams instanceof RecyclerView.LayoutParams;
        return true;
    }

    public static boolean isGridLayout(int i) {
        return i == 3 || i == 2;
    }

    public static boolean isLinearLayout(int i) {
        return i == 1 || i == 0;
    }

    public static boolean isStaggeredGridLayout(int i) {
        return i == 5 || i == 4;
    }

    public static int safeGetAdapterPosition(@Nullable RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            return viewHolder.getAdapterPosition();
        }
        return -1;
    }

    public static int safeGetLayoutPosition(@Nullable RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            return viewHolder.getLayoutPosition();
        }
        return -1;
    }

    public static int getLayoutType(@Nullable RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getOrientation() == 0 ? 2 : 3;
        } else if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getOrientation() == 0 ? 0 : 1;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getOrientation() == 0 ? 4 : 5;
        } else {
            return -1;
        }
    }

    public static int getOrientation(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getOrientation();
        }
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getOrientation();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        }
        return -1;
    }
}
