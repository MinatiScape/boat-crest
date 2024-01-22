package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class h implements Converter<ResponseBody, Long> {

    /* renamed from: a  reason: collision with root package name */
    public static final h f15614a = new h();

    @Override // retrofit2.Converter
    /* renamed from: a */
    public Long convert(ResponseBody responseBody) throws IOException {
        return Long.valueOf(responseBody.string());
    }
}
