package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class LiveDataUtils {

    /* JADX INFO: Add missing generic type declarations: [In] */
    /* loaded from: classes.dex */
    public class a<In> implements Observer<In> {
        public Out h = null;
        public final /* synthetic */ TaskExecutor i;
        public final /* synthetic */ Object j;
        public final /* synthetic */ Function k;
        public final /* synthetic */ MediatorLiveData l;

        /* renamed from: androidx.work.impl.utils.LiveDataUtils$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0190a implements Runnable {
            public final /* synthetic */ Object h;

            public RunnableC0190a(Object obj) {
                this.h = obj;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object, Out] */
            @Override // java.lang.Runnable
            public void run() {
                synchronized (a.this.j) {
                    ?? apply = a.this.k.apply(this.h);
                    a aVar = a.this;
                    Out out = aVar.h;
                    if (out == 0 && apply != 0) {
                        aVar.h = apply;
                        aVar.l.postValue(apply);
                    } else if (out != 0 && !out.equals(apply)) {
                        a aVar2 = a.this;
                        aVar2.h = apply;
                        aVar2.l.postValue(apply);
                    }
                }
            }
        }

        public a(TaskExecutor taskExecutor, Object obj, Function function, MediatorLiveData mediatorLiveData) {
            this.i = taskExecutor;
            this.j = obj;
            this.k = function;
            this.l = mediatorLiveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable In in) {
            this.i.executeOnBackgroundThread(new RunnableC0190a(in));
        }
    }

    public static <In, Out> LiveData<Out> dedupedMappedLiveDataFor(@NonNull LiveData<In> liveData, @NonNull Function<In, Out> function, @NonNull TaskExecutor taskExecutor) {
        Object obj = new Object();
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new a(taskExecutor, obj, function, mediatorLiveData));
        return mediatorLiveData;
    }
}
