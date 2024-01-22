package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class g implements Converter<ResponseBody, Integer> {

    /* renamed from: a  reason: collision with root package name */
    public static final g f15613a = new g();

    @Override // retrofit2.Converter
    /* renamed from: a */
    public Integer convert(ResponseBody responseBody) throws IOException {
        return Integer.valueOf(responseBody.string());
    }
}
