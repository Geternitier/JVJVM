package runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class FloatConstant implements Constant{
    @Getter
    private final float value;

    @SneakyThrows
    public FloatConstant(DataInput dataInput){
        value = dataInput.readFloat();
    }

    @Override
    public String toString() {
        return String.format("Float: %a", value);
    }

}
