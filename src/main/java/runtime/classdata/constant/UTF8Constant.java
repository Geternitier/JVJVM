package runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.text.StringEscapeUtils;

import java.io.DataInput;

public class UTF8Constant implements Constant{
    @Getter
    private final String value;

    @SneakyThrows
    public UTF8Constant(DataInput dataInput) {
        value = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return String.format("Utf8: \"%s\"", StringEscapeUtils.escapeJava(value));
    }
}
