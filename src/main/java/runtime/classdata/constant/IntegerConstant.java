package runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class IntegerConstant implements Constant{
    @Getter
    private final int value;

    @SneakyThrows
    public IntegerConstant(DataInput dataInput) {
        value = dataInput.readInt();
    }

    @Override
    public String toString() {
        return String.format("Integer: %d", value);
    }
}
