package com.jieli.jl_bt_ota.interfaces;

import com.jieli.jl_bt_ota.model.base.BaseError;
/* loaded from: classes11.dex */
public interface IActionCallback<T> {
    void onError(BaseError baseError);

    void onSuccess(T t);
}
