package runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class LongConstant implements Constant{
    @Getter
    private final long value;

    @SneakyThrows
    public LongConstant(DataInput dataInput){
        value = dataInput.readLong();
    }

    @Override
    public String toString() {
        return String.format("Long: %d", value);
    }
}
