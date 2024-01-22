package com.github.barteksc.pdfviewer;

import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.github.barteksc.pdfviewer.model.PagePart;
import com.github.barteksc.pdfviewer.util.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final PriorityQueue<PagePart> f7909a;
    public final PriorityQueue<PagePart> b;
    public final List<PagePart> c;
    public final Object d = new Object();
    public final a e;

    /* loaded from: classes9.dex */
    public class a implements Comparator<PagePart> {
        public a(b bVar) {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(PagePart pagePart, PagePart pagePart2) {
            if (pagePart.getCacheOrder() == pagePart2.getCacheOrder()) {
                return 0;
            }
            return pagePart.getCacheOrder() > pagePart2.getCacheOrder() ? 1 : -1;
        }
    }

    public b() {
        a aVar = new a(this);
        this.e = aVar;
        this.b = new PriorityQueue<>(Constants.Cache.CACHE_SIZE, aVar);
        this.f7909a = new PriorityQueue<>(Constants.Cache.CACHE_SIZE, aVar);
        this.c = new ArrayList();
    }

    @Nullable
    public static PagePart e(PriorityQueue<PagePart> priorityQueue, PagePart pagePart) {
        Iterator<PagePart> it = priorityQueue.iterator();
        while (it.hasNext()) {
            PagePart next = it.next();
            if (next.equals(pagePart)) {
                return next;
            }
        }
        return null;
    }

    public final void a(Collection<PagePart> collection, PagePart pagePart) {
        for (PagePart pagePart2 : collection) {
            if (pagePart2.equals(pagePart)) {
                pagePart.getRenderedBitmap().recycle();
                return;
            }
        }
        collection.add(pagePart);
    }

    public void b(PagePart pagePart) {
        synchronized (this.d) {
            h();
            this.b.offer(pagePart);
        }
    }

    public void c(PagePart pagePart) {
        synchronized (this.c) {
            while (this.c.size() >= Constants.Cache.THUMBNAILS_CACHE_SIZE) {
                this.c.remove(0).getRenderedBitmap().recycle();
            }
            a(this.c, pagePart);
        }
    }

    public boolean d(int i, RectF rectF) {
        PagePart pagePart = new PagePart(i, null, rectF, true, 0);
        synchronized (this.c) {
            for (PagePart pagePart2 : this.c) {
                if (pagePart2.equals(pagePart)) {
                    return true;
                }
            }
            return false;
        }
    }

    public List<PagePart> f() {
        ArrayList arrayList;
        synchronized (this.d) {
            arrayList = new ArrayList(this.f7909a);
            arrayList.addAll(this.b);
        }
        return arrayList;
    }

    public List<PagePart> g() {
        List<PagePart> list;
        synchronized (this.c) {
            list = this.c;
        }
        return list;
    }

    public final void h() {
        synchronized (this.d) {
            while (this.b.size() + this.f7909a.size() >= Constants.Cache.CACHE_SIZE && !this.f7909a.isEmpty()) {
                this.f7909a.poll().getRenderedBitmap().recycle();
            }
            while (this.b.size() + this.f7909a.size() >= Constants.Cache.CACHE_SIZE && !this.b.isEmpty()) {
                this.b.poll().getRenderedBitmap().recycle();
            }
        }
    }

    public void i() {
        synchronized (this.d) {
            this.f7909a.addAll(this.b);
            this.b.clear();
        }
    }

    public void j() {
        synchronized (this.d) {
            Iterator<PagePart> it = this.f7909a.iterator();
            while (it.hasNext()) {
                it.next().getRenderedBitmap().recycle();
            }
            this.f7909a.clear();
            Iterator<PagePart> it2 = this.b.iterator();
            while (it2.hasNext()) {
                it2.next().getRenderedBitmap().recycle();
            }
            this.b.clear();
        }
        synchronized (this.c) {
            for (PagePart pagePart : this.c) {
                pagePart.getRenderedBitmap().recycle();
            }
            this.c.clear();
        }
    }

    public boolean k(int i, RectF rectF, int i2) {
        PagePart pagePart = new PagePart(i, null, rectF, false, 0);
        synchronized (this.d) {
            PagePart e = e(this.f7909a, pagePart);
            boolean z = true;
            if (e != null) {
                this.f7909a.remove(e);
                e.setCacheOrder(i2);
                this.b.offer(e);
                return true;
            }
            if (e(this.b, pagePart) == null) {
                z = false;
            }
            return z;
        }
    }
}
