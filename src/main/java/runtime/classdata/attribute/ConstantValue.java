package runtime.classdata.attribute;

import lombok.Getter;
import lombok.SneakyThrows;
import runtime.JClass;
import runtime.classdata.ConstantPool;
import runtime.classdata.constant.Constant;

import java.io.DataInput;

public class ConstantValue extends Attribute{
    @Getter
    private final Constant value;

    @SneakyThrows
    public ConstantValue(String name, int length, DataInput dataInput, ConstantPool constantPool){
        this.name = name;
        attributeLength = length;
        int valueIndex = dataInput.readUnsignedShort();
        value = constantPool.getConstant(valueIndex);
    }
}
