package com.mappls.sdk.maps;

import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import java.util.List;
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes11.dex */
public class BaseMapplsHelper {

    /* loaded from: classes11.dex */
    public class a implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoordinateCallback f12624a;

        public a(BaseMapplsHelper baseMapplsHelper, CoordinateCallback coordinateCallback) {
            this.f12624a = coordinateCallback;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            this.f12624a.coordinateResultSuccess(list);
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
            this.f12624a.onFailure();
        }
    }

    /* loaded from: classes11.dex */
    public class b implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoordinateCallback f12625a;

        public b(BaseMapplsHelper baseMapplsHelper, CoordinateCallback coordinateCallback) {
            this.f12625a = coordinateCallback;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            this.f12625a.coordinateResultSuccess(list);
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
            this.f12625a.onFailure();
        }
    }

    public void getAnnotation(String str, CoordinateCallback coordinateCallback) {
        o.d().b(str, new a(this, coordinateCallback));
    }

    public void getAnnotation(List<String> list, CoordinateCallback coordinateCallback) {
        o.d().c(list, new b(this, coordinateCallback));
    }
}
