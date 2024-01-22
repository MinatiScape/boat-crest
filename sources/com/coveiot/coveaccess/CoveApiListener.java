package com.coveiot.coveaccess;
/* loaded from: classes8.dex */
public interface CoveApiListener<T, E> {
    void onError(E e);

    void onSuccess(T t);
}
