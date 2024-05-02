package runtime.classdata.attribute;

import lombok.SneakyThrows;
import runtime.classdata.ConstantPool;
import runtime.classdata.constant.UTF8Constant;

import java.io.DataInput;

import static classdefs.PredefinedAttributes.*;

public abstract class Attribute {
    protected String name;
    protected int attributeLength;
    @SneakyThrows
    public static Attribute constructFromData(DataInput dataInput, ConstantPool constantPool){
        int nameIndex = dataInput.readUnsignedShort();
        String name = ((UTF8Constant) constantPool.getConstant(nameIndex)).getValue();
        int attributeLength = dataInput.readInt();
        switch (name){
            case ATTR_ConstantValue -> {
                return new ConstantValue(name, attributeLength, dataInput, constantPool);
            }
            case ATTR_Code -> {
                return new Code(name, attributeLength, dataInput, constantPool);
            }
            default -> {
                return new TODOAttribute(name, attributeLength, dataInput);
            }
        }
    }

    @Override
    public String toString(){
        return "Attribute: " + name;
    }
}
