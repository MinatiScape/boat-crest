package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;
/* loaded from: classes13.dex */
public final class b<T> implements Converter<ResponseBody, T> {

    /* renamed from: a  reason: collision with root package name */
    public final Gson f15606a;
    public final TypeAdapter<T> b;

    public b(Gson gson, TypeAdapter<T> typeAdapter) {
        this.f15606a = gson;
        this.b = typeAdapter;
    }

    @Override // retrofit2.Converter
    /* renamed from: a */
    public T convert(ResponseBody responseBody) throws IOException {
        JsonReader newJsonReader = this.f15606a.newJsonReader(responseBody.charStream());
        try {
            T read = this.b.read(newJsonReader);
            if (newJsonReader.peek() == JsonToken.END_DOCUMENT) {
                return read;
            }
            throw new JsonIOException("JSON document was not fully consumed.");
        } finally {
            responseBody.close();
        }
    }
}
