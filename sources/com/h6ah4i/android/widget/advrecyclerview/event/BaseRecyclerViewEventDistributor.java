package com.h6ah4i.android.widget.advrecyclerview.event;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class BaseRecyclerViewEventDistributor<T> {
    public List<T> mListeners;
    public boolean mPerformingClearMethod;
    public RecyclerView mRecyclerView;
    public boolean mReleased;

    public boolean add(@NonNull T t) {
        return add(t, -1);
    }

    public void attachRecyclerView(@NonNull RecyclerView recyclerView) {
        verifyIsNotReleased("attachRecyclerView()");
        verifyIsNotPerformingClearMethod("attachRecyclerView()");
        onRecyclerViewAttached(recyclerView);
    }

    public void clear() {
        clear(false);
    }

    public boolean contains(T t) {
        List<T> list = this.mListeners;
        if (list != null) {
            return list.contains(t);
        }
        return false;
    }

    @Nullable
    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public boolean isReleased() {
        return this.mReleased;
    }

    public void onRecyclerViewAttached(@NonNull RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    public void onRelease() {
        this.mRecyclerView = null;
        this.mListeners = null;
        this.mPerformingClearMethod = false;
    }

    public void release() {
        if (this.mReleased) {
            return;
        }
        this.mReleased = true;
        clear(true);
        onRelease();
    }

    public boolean remove(@NonNull T t) {
        verifyIsNotPerformingClearMethod("remove()");
        verifyIsNotReleased("remove()");
        List<T> list = this.mListeners;
        if (list == null) {
            return false;
        }
        boolean remove = list.remove(t);
        if (remove && (t instanceof RecyclerViewEventDistributorListener)) {
            ((RecyclerViewEventDistributorListener) t).onRemovedFromEventDistributor(this);
        }
        return remove;
    }

    public int size() {
        List<T> list = this.mListeners;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void verifyIsNotPerformingClearMethod(@NonNull String str) {
        if (this.mPerformingClearMethod) {
            throw new IllegalStateException(str + " can not be called while performing the clear() method");
        }
    }

    public void verifyIsNotReleased(@NonNull String str) {
        if (this.mReleased) {
            throw new IllegalStateException(str + " can not be called after release() method called");
        }
    }

    public boolean add(@NonNull T t, int i) {
        verifyIsNotReleased("add()");
        verifyIsNotPerformingClearMethod("add()");
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        if (this.mListeners.contains(t)) {
            return true;
        }
        if (i < 0) {
            this.mListeners.add(t);
        } else {
            this.mListeners.add(i, t);
        }
        if (t instanceof RecyclerViewEventDistributorListener) {
            ((RecyclerViewEventDistributorListener) t).onAddedToEventDistributor(this);
            return true;
        }
        return true;
    }

    public void clear(boolean z) {
        if (!z) {
            verifyIsNotReleased("clear()");
        }
        verifyIsNotPerformingClearMethod("clear()");
        List<T> list = this.mListeners;
        if (list == null) {
            return;
        }
        try {
            this.mPerformingClearMethod = true;
            for (int size = list.size() - 1; size >= 0; size--) {
                T remove = this.mListeners.remove(size);
                if (remove instanceof RecyclerViewEventDistributorListener) {
                    ((RecyclerViewEventDistributorListener) remove).onRemovedFromEventDistributor(this);
                }
            }
        } finally {
            this.mPerformingClearMethod = false;
        }
    }
}
