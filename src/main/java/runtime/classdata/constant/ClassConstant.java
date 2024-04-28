package runtime.classdata.constant;

import classdefs.FieldDescriptor;
import lombok.SneakyThrows;
import runtime.JClass;

import java.io.DataInput;

public class ClassConstant implements Constant{
    private final JClass jClass;
    private final int nameIndex;
    private String name;
    private JClass value;

    @SneakyThrows
    public ClassConstant(DataInput dataInput, JClass jClass){
        this.jClass = jClass;
        nameIndex = dataInput.readUnsignedShort();
    }

    public String getName() {
        if(name == null)
            name = ((UTF8Constant) jClass.getConstantPool().getConstant(nameIndex)).getValue();
        return name;
    }

    public JClass getValue() {
        if(value == null){
            if(getName().equals(jClass.getThisClass().getName())) value = jClass;
            else value = jClass.getClassLoader().loadClass(FieldDescriptor.of(getName()));
        }
        return value;
    }

    @Override
    public String toString(){
        return String.format("Class: \"%s\"", getName());
    }
}
