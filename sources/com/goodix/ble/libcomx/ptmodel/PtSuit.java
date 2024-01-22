package com.goodix.ble.libcomx.ptmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes5.dex */
public final class PtSuit {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<PtCase> f8042a = new ArrayList<>(32);

    public void abort() {
        Iterator<PtCase> it = this.f8042a.iterator();
        while (it.hasNext()) {
            it.next().abort();
        }
    }

    public void addCase(PtCase ptCase) {
        this.f8042a.add(ptCase);
    }

    public PtCase getCase(int i) {
        if (i < this.f8042a.size()) {
            return this.f8042a.get(i);
        }
        return null;
    }

    public int getCaseCount() {
        return this.f8042a.size();
    }

    public List<PtJudge> getResults(List<PtJudge> list) {
        if (list == null) {
            list = new ArrayList<>(this.f8042a.size() * 8);
        }
        Iterator<PtCase> it = this.f8042a.iterator();
        while (it.hasNext()) {
            it.next().getResults(list);
        }
        return list;
    }

    public boolean isPass() {
        Iterator<PtCase> it = this.f8042a.iterator();
        while (it.hasNext()) {
            if (!it.next().isPass()) {
                return false;
            }
        }
        return true;
    }

    public boolean isTested(boolean z) {
        if (z) {
            Iterator<PtCase> it = this.f8042a.iterator();
            while (it.hasNext()) {
                if (!it.next().isTested(false)) {
                    return false;
                }
            }
            return true;
        }
        Iterator<PtCase> it2 = this.f8042a.iterator();
        while (it2.hasNext()) {
            if (it2.next().isTested(false)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTesting() {
        Iterator<PtCase> it = this.f8042a.iterator();
        while (it.hasNext()) {
            if (it.next().isStarted()) {
                return true;
            }
        }
        return false;
    }

    public <T extends PtCase> T getCase(Class<T> cls) {
        if (cls != null) {
            Iterator<PtCase> it = this.f8042a.iterator();
            while (it.hasNext()) {
                T t = (T) it.next();
                if (cls.equals(t.getClass())) {
                    return t;
                }
            }
            return null;
        }
        return null;
    }

    public <T extends PtCase> T getCase(String str) {
        if (str != null) {
            Iterator<PtCase> it = this.f8042a.iterator();
            while (it.hasNext()) {
                T t = (T) it.next();
                if (str.equals(t.getName())) {
                    return t;
                }
            }
            return null;
        }
        return null;
    }
}
