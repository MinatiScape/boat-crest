package retrofit2.adapter.rxjava;

import java.util.Objects;
import javax.annotation.Nullable;
import retrofit2.Response;
/* loaded from: classes13.dex */
public final class Result<T> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Response<T> f15592a;
    @Nullable
    public final Throwable b;

    public Result(@Nullable Response<T> response, @Nullable Throwable th) {
        this.f15592a = response;
        this.b = th;
    }

    public static <T> Result<T> error(Throwable th) {
        Objects.requireNonNull(th, "error == null");
        return new Result<>(null, th);
    }

    public static <T> Result<T> response(Response<T> response) {
        Objects.requireNonNull(response, "response == null");
        return new Result<>(response, null);
    }

    public boolean isError() {
        return this.b != null;
    }

    public String toString() {
        if (this.b != null) {
            return "Result{isError=true, error=\"" + this.b + "\"}";
        }
        return "Result{isError=false, response=" + this.f15592a + '}';
    }

    @Nullable
    public Throwable error() {
        return this.b;
    }

    @Nullable
    public Response<T> response() {
        return this.f15592a;
    }
}
