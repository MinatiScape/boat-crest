package org.greenrobot.greendao.rx;

import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import rx.Observable;
import rx.Scheduler;
@Experimental
/* loaded from: classes13.dex */
public class RxDao<T, K> extends org.greenrobot.greendao.rx.a {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractDao<T, K> f15491a;

    /* loaded from: classes13.dex */
    public class a implements Callable<T> {
        public final /* synthetic */ Object h;

        public a(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            RxDao.this.f15491a.save(this.h);
            return (T) this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class b implements Callable<Iterable<T>> {
        public final /* synthetic */ Iterable h;

        public b(Iterable iterable) {
            this.h = iterable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Iterable<T> call() throws Exception {
            RxDao.this.f15491a.saveInTx(this.h);
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class c implements Callable<Object[]> {
        public final /* synthetic */ Object[] h;

        public c(Object[] objArr) {
            this.h = objArr;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Object[] call() throws Exception {
            RxDao.this.f15491a.saveInTx(this.h);
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class d implements Callable<T> {
        public final /* synthetic */ Object h;

        public d(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            RxDao.this.f15491a.update(this.h);
            return (T) this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class e implements Callable<Iterable<T>> {
        public final /* synthetic */ Iterable h;

        public e(Iterable iterable) {
            this.h = iterable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Iterable<T> call() throws Exception {
            RxDao.this.f15491a.updateInTx(this.h);
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class f implements Callable<Object[]> {
        public final /* synthetic */ Object[] h;

        public f(Object[] objArr) {
            this.h = objArr;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Object[] call() throws Exception {
            RxDao.this.f15491a.updateInTx(this.h);
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class g implements Callable<Void> {
        public final /* synthetic */ Object h;

        public g(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            RxDao.this.f15491a.delete(this.h);
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public class h implements Callable<Void> {
        public final /* synthetic */ Object h;

        public h(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            RxDao.this.f15491a.deleteByKey(this.h);
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public class i implements Callable<Void> {
        public i() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            RxDao.this.f15491a.deleteAll();
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public class j implements Callable<Void> {
        public final /* synthetic */ Iterable h;

        public j(Iterable iterable) {
            this.h = iterable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            RxDao.this.f15491a.deleteInTx(this.h);
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public class k implements Callable<List<T>> {
        public k() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<T> call() throws Exception {
            return RxDao.this.f15491a.loadAll();
        }
    }

    /* loaded from: classes13.dex */
    public class l implements Callable<Void> {
        public final /* synthetic */ Object[] h;

        public l(Object[] objArr) {
            this.h = objArr;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            RxDao.this.f15491a.deleteInTx(this.h);
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public class m implements Callable<Void> {
        public final /* synthetic */ Iterable h;

        public m(Iterable iterable) {
            this.h = iterable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            RxDao.this.f15491a.deleteByKeyInTx(this.h);
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public class n implements Callable<Void> {
        public final /* synthetic */ Object[] h;

        public n(Object[] objArr) {
            this.h = objArr;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            RxDao.this.f15491a.deleteByKeyInTx(this.h);
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public class o implements Callable<Long> {
        public o() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Long call() throws Exception {
            return Long.valueOf(RxDao.this.f15491a.count());
        }
    }

    /* loaded from: classes13.dex */
    public class p implements Callable<T> {
        public final /* synthetic */ Object h;

        public p(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            return (T) RxDao.this.f15491a.load(this.h);
        }
    }

    /* loaded from: classes13.dex */
    public class q implements Callable<T> {
        public final /* synthetic */ Object h;

        public q(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            RxDao.this.f15491a.refresh(this.h);
            return (T) this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class r implements Callable<T> {
        public final /* synthetic */ Object h;

        public r(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            RxDao.this.f15491a.insert(this.h);
            return (T) this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class s implements Callable<Iterable<T>> {
        public final /* synthetic */ Iterable h;

        public s(Iterable iterable) {
            this.h = iterable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Iterable<T> call() throws Exception {
            RxDao.this.f15491a.insertInTx(this.h);
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class t implements Callable<Object[]> {
        public final /* synthetic */ Object[] h;

        public t(Object[] objArr) {
            this.h = objArr;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Object[] call() throws Exception {
            RxDao.this.f15491a.insertInTx(this.h);
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class u implements Callable<T> {
        public final /* synthetic */ Object h;

        public u(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            RxDao.this.f15491a.insertOrReplace(this.h);
            return (T) this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class v implements Callable<Iterable<T>> {
        public final /* synthetic */ Iterable h;

        public v(Iterable iterable) {
            this.h = iterable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Iterable<T> call() throws Exception {
            RxDao.this.f15491a.insertOrReplaceInTx(this.h);
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class w implements Callable<Object[]> {
        public final /* synthetic */ Object[] h;

        public w(Object[] objArr) {
            this.h = objArr;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Object[] call() throws Exception {
            RxDao.this.f15491a.insertOrReplaceInTx(this.h);
            return this.h;
        }
    }

    @Experimental
    public RxDao(AbstractDao<T, K> abstractDao) {
        this(abstractDao, null);
    }

    @Experimental
    public Observable<Long> count() {
        return wrap(new o());
    }

    @Experimental
    public Observable<Void> delete(T t2) {
        return wrap(new g(t2));
    }

    @Experimental
    public Observable<Void> deleteAll() {
        return wrap(new i());
    }

    @Experimental
    public Observable<Void> deleteByKey(K k2) {
        return wrap(new h(k2));
    }

    @Experimental
    public Observable<Void> deleteByKeyInTx(Iterable<K> iterable) {
        return wrap(new m(iterable));
    }

    @Experimental
    public Observable<Void> deleteInTx(Iterable<T> iterable) {
        return wrap(new j(iterable));
    }

    @Experimental
    public AbstractDao<T, K> getDao() {
        return this.f15491a;
    }

    @Override // org.greenrobot.greendao.rx.a
    @Experimental
    public /* bridge */ /* synthetic */ Scheduler getScheduler() {
        return super.getScheduler();
    }

    @Experimental
    public Observable<T> insert(T t2) {
        return (Observable<T>) wrap(new r(t2));
    }

    @Experimental
    public Observable<Iterable<T>> insertInTx(Iterable<T> iterable) {
        return (Observable<Iterable<T>>) wrap(new s(iterable));
    }

    @Experimental
    public Observable<T> insertOrReplace(T t2) {
        return (Observable<T>) wrap(new u(t2));
    }

    @Experimental
    public Observable<Iterable<T>> insertOrReplaceInTx(Iterable<T> iterable) {
        return (Observable<Iterable<T>>) wrap(new v(iterable));
    }

    @Experimental
    public Observable<T> load(K k2) {
        return (Observable<T>) wrap(new p(k2));
    }

    @Experimental
    public Observable<List<T>> loadAll() {
        return (Observable<List<T>>) wrap(new k());
    }

    @Experimental
    public Observable<T> refresh(T t2) {
        return (Observable<T>) wrap(new q(t2));
    }

    @Experimental
    public Observable<T> save(T t2) {
        return (Observable<T>) wrap(new a(t2));
    }

    @Experimental
    public Observable<Iterable<T>> saveInTx(Iterable<T> iterable) {
        return (Observable<Iterable<T>>) wrap(new b(iterable));
    }

    @Experimental
    public Observable<T> update(T t2) {
        return (Observable<T>) wrap(new d(t2));
    }

    @Experimental
    public Observable<Iterable<T>> updateInTx(Iterable<T> iterable) {
        return (Observable<Iterable<T>>) wrap(new e(iterable));
    }

    @Experimental
    public RxDao(AbstractDao<T, K> abstractDao, Scheduler scheduler) {
        super(scheduler);
        this.f15491a = abstractDao;
    }

    @Experimental
    public Observable<Void> deleteByKeyInTx(K... kArr) {
        return wrap(new n(kArr));
    }

    @Experimental
    public Observable<Void> deleteInTx(T... tArr) {
        return wrap(new l(tArr));
    }

    @Experimental
    public Observable<Object[]> insertInTx(T... tArr) {
        return wrap(new t(tArr));
    }

    @Experimental
    public Observable<Object[]> insertOrReplaceInTx(T... tArr) {
        return wrap(new w(tArr));
    }

    @Experimental
    public Observable<Object[]> saveInTx(T... tArr) {
        return wrap(new c(tArr));
    }

    @Experimental
    public Observable<Object[]> updateInTx(T... tArr) {
        return wrap(new f(tArr));
    }
}
