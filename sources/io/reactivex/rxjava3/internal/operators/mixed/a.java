package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeToObservable;
import io.reactivex.rxjava3.internal.operators.single.SingleToObservable;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class a {
    public static <T> boolean a(Object obj, Function<? super T, ? extends CompletableSource> function, CompletableObserver completableObserver) {
        if (obj instanceof Supplier) {
            CompletableSource completableSource = null;
            try {
                Object obj2 = (Object) ((Supplier) obj).get();
                if (obj2 != 0) {
                    CompletableSource apply = function.apply(obj2);
                    Objects.requireNonNull(apply, "The mapper returned a null CompletableSource");
                    completableSource = apply;
                }
                if (completableSource == null) {
                    EmptyDisposable.complete(completableObserver);
                } else {
                    completableSource.subscribe(completableObserver);
                }
                return true;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, completableObserver);
                return true;
            }
        }
        return false;
    }

    public static <T, R> boolean b(Object obj, Function<? super T, ? extends MaybeSource<? extends R>> function, Observer<? super R> observer) {
        if (obj instanceof Supplier) {
            MaybeSource<? extends R> maybeSource = null;
            try {
                Object obj2 = (Object) ((Supplier) obj).get();
                if (obj2 != 0) {
                    MaybeSource<? extends R> apply = function.apply(obj2);
                    Objects.requireNonNull(apply, "The mapper returned a null MaybeSource");
                    maybeSource = apply;
                }
                if (maybeSource == null) {
                    EmptyDisposable.complete(observer);
                } else {
                    maybeSource.subscribe(MaybeToObservable.create(observer));
                }
                return true;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, observer);
                return true;
            }
        }
        return false;
    }

    public static <T, R> boolean c(Object obj, Function<? super T, ? extends SingleSource<? extends R>> function, Observer<? super R> observer) {
        if (obj instanceof Supplier) {
            SingleSource<? extends R> singleSource = null;
            try {
                Object obj2 = (Object) ((Supplier) obj).get();
                if (obj2 != 0) {
                    SingleSource<? extends R> apply = function.apply(obj2);
                    Objects.requireNonNull(apply, "The mapper returned a null SingleSource");
                    singleSource = apply;
                }
                if (singleSource == null) {
                    EmptyDisposable.complete(observer);
                } else {
                    singleSource.subscribe(SingleToObservable.create(observer));
                }
                return true;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, observer);
                return true;
            }
        }
        return false;
    }
}
