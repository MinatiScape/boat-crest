package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class i implements Converter<ResponseBody, Short> {

    /* renamed from: a  reason: collision with root package name */
    public static final i f15615a = new i();

    @Override // retrofit2.Converter
    /* renamed from: a */
    public Short convert(ResponseBody responseBody) throws IOException {
        return Short.valueOf(responseBody.string());
    }
}
