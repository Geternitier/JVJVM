package runtime.classdata.attribute;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class ExceptionTable {
    @Getter
    private final int startPc;
    @Getter
    private final int endPc;
    @Getter
    private final int handlerPc;
    @Getter
    private final int catchType;

    @SneakyThrows
    public ExceptionTable(DataInput input){
        startPc = input.readUnsignedShort();
        endPc = input.readUnsignedShort();
        handlerPc = input.readUnsignedShort();
        catchType = input.readUnsignedShort();
    }
}
