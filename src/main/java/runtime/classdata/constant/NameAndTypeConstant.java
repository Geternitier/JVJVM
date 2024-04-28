package runtime.classdata.constant;

import lombok.SneakyThrows;
import runtime.JClass;

import java.io.DataInput;

public class NameAndTypeConstant implements Constant{
    private final JClass jClass;
    private final int nameIndex;
    private final int descriptorIndex;
    private String name;
    private String descriptor;

    @SneakyThrows
    public NameAndTypeConstant(DataInput dataInput, JClass jClass){
        this.jClass = jClass;
        nameIndex = dataInput.readUnsignedShort();
        descriptorIndex = dataInput.readUnsignedShort();
    }

    public String getName(){
        if(name == null)
            name = ((UTF8Constant) jClass.getConstantPool().getConstant(nameIndex)).getValue();
        return name;
    }

    public String getType(){
        if(descriptor == null)
            descriptor = ((UTF8Constant) jClass.getConstantPool().getConstant(descriptorIndex)).getValue();
        return descriptor;
    }

    @Override
    public String toString(){
        return String.format("NameAndType: %s:%s", getName(), getType());
    }

}
