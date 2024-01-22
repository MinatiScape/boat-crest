package com.polidea.rxandroidble2.helpers;

import android.content.Context;
import androidx.annotation.NonNull;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.DaggerClientComponent;
import io.reactivex.Observable;
import io.reactivex.Observer;
/* loaded from: classes9.dex */
public class LocationServicesOkObservable extends Observable<Boolean> {
    @NonNull
    public final Observable<Boolean> h;

    @Inject
    public LocationServicesOkObservable(@NonNull @Named("location-ok-boolean-observable") Observable<Boolean> observable) {
        this.h = observable;
    }

    public static LocationServicesOkObservable createInstance(@NonNull Context context) {
        return DaggerClientComponent.builder().applicationContext(context.getApplicationContext()).build().locationServicesOkObservable();
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Boolean> observer) {
        this.h.subscribe(observer);
    }
}
