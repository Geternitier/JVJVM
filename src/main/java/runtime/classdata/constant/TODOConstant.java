package runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class TODOConstant implements Constant{
    @Getter
    private final byte[] data;

    @SneakyThrows
    public TODOConstant(DataInput dataInput, int length){
        data = new byte[length];
        dataInput.readFully(data);
    }

    @Override
    public String toString() {
        return "TODOConstant";
    }
}
