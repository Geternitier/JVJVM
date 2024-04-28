package runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class DoubleConstant implements Constant{
    @Getter
    private final double value;

    @SneakyThrows
    public DoubleConstant(DataInput dataInput){
        value = dataInput.readDouble();
    }

    @Override
    public String toString() {
        return String.format("Double: %a", value);
    }
}
