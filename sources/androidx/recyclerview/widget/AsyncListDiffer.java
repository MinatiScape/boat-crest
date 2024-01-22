package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class AsyncListDiffer<T> {
    public static final Executor h = new b();

    /* renamed from: a  reason: collision with root package name */
    public final ListUpdateCallback f1575a;
    public final AsyncDifferConfig<T> b;
    public Executor c;
    public final List<ListListener<T>> d;
    @Nullable
    public List<T> e;
    @NonNull
    public List<T> f;
    public int g;

    /* loaded from: classes.dex */
    public interface ListListener<T> {
        void onCurrentListChanged(@NonNull List<T> list, @NonNull List<T> list2);
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ List h;
        public final /* synthetic */ List i;
        public final /* synthetic */ int j;
        public final /* synthetic */ Runnable k;

        /* renamed from: androidx.recyclerview.widget.AsyncListDiffer$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0173a extends DiffUtil.Callback {
            public C0173a() {
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areContentsTheSame(int i, int i2) {
                Object obj = a.this.h.get(i);
                Object obj2 = a.this.i.get(i2);
                if (obj == null || obj2 == null) {
                    if (obj == null && obj2 == null) {
                        return true;
                    }
                    throw new AssertionError();
                }
                return AsyncListDiffer.this.b.getDiffCallback().areContentsTheSame(obj, obj2);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areItemsTheSame(int i, int i2) {
                Object obj = a.this.h.get(i);
                Object obj2 = a.this.i.get(i2);
                if (obj == null || obj2 == null) {
                    return obj == null && obj2 == null;
                }
                return AsyncListDiffer.this.b.getDiffCallback().areItemsTheSame(obj, obj2);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            @Nullable
            public Object getChangePayload(int i, int i2) {
                Object obj = a.this.h.get(i);
                Object obj2 = a.this.i.get(i2);
                if (obj != null && obj2 != null) {
                    return AsyncListDiffer.this.b.getDiffCallback().getChangePayload(obj, obj2);
                }
                throw new AssertionError();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getNewListSize() {
                return a.this.i.size();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getOldListSize() {
                return a.this.h.size();
            }
        }

        /* loaded from: classes.dex */
        public class b implements Runnable {
            public final /* synthetic */ DiffUtil.DiffResult h;

            public b(DiffUtil.DiffResult diffResult) {
                this.h = diffResult;
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                AsyncListDiffer asyncListDiffer = AsyncListDiffer.this;
                if (asyncListDiffer.g == aVar.j) {
                    asyncListDiffer.a(aVar.i, this.h, aVar.k);
                }
            }
        }

        public a(List list, List list2, int i, Runnable runnable) {
            this.h = list;
            this.i = list2;
            this.j = i;
            this.k = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            AsyncListDiffer.this.c.execute(new b(DiffUtil.calculateDiff(new C0173a())));
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Executor {
        public final Handler h = new Handler(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            this.h.post(runnable);
        }
    }

    public AsyncListDiffer(@NonNull RecyclerView.Adapter adapter, @NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this(new AdapterListUpdateCallback(adapter), new AsyncDifferConfig.Builder(itemCallback).build());
    }

    public void a(@NonNull List<T> list, @NonNull DiffUtil.DiffResult diffResult, @Nullable Runnable runnable) {
        List<T> list2 = this.f;
        this.e = list;
        this.f = Collections.unmodifiableList(list);
        diffResult.dispatchUpdatesTo(this.f1575a);
        b(list2, runnable);
    }

    public void addListListener(@NonNull ListListener<T> listListener) {
        this.d.add(listListener);
    }

    public final void b(@NonNull List<T> list, @Nullable Runnable runnable) {
        for (ListListener<T> listListener : this.d) {
            listListener.onCurrentListChanged(list, this.f);
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    @NonNull
    public List<T> getCurrentList() {
        return this.f;
    }

    public void removeListListener(@NonNull ListListener<T> listListener) {
        this.d.remove(listListener);
    }

    public void submitList(@Nullable List<T> list) {
        submitList(list, null);
    }

    public void submitList(@Nullable List<T> list, @Nullable Runnable runnable) {
        int i = this.g + 1;
        this.g = i;
        List<T> list2 = this.e;
        if (list == list2) {
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        List<T> list3 = this.f;
        if (list == null) {
            int size = list2.size();
            this.e = null;
            this.f = Collections.emptyList();
            this.f1575a.onRemoved(0, size);
            b(list3, runnable);
        } else if (list2 == null) {
            this.e = list;
            this.f = Collections.unmodifiableList(list);
            this.f1575a.onInserted(0, list.size());
            b(list3, runnable);
        } else {
            this.b.getBackgroundThreadExecutor().execute(new a(list2, list, i, runnable));
        }
    }

    public AsyncListDiffer(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.d = new CopyOnWriteArrayList();
        this.f = Collections.emptyList();
        this.f1575a = listUpdateCallback;
        this.b = asyncDifferConfig;
        if (asyncDifferConfig.getMainThreadExecutor() != null) {
            this.c = asyncDifferConfig.getMainThreadExecutor();
        } else {
            this.c = h;
        }
    }
}
