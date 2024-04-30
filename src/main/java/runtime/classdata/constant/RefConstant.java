package runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import runtime.JClass;

import java.io.DataInput;

public class RefConstant implements Constant{
    @Getter
    private final JClass jClass;
    private final int classIndex;
    private final int nameAndTypeIndex;
    private ClassConstant classConstant;
    private NameAndTypeConstant nameAndTypeConstant;

    @SneakyThrows
    public RefConstant(DataInput dataInput, JClass jClass){
        this.jClass = jClass;
        classIndex = dataInput.readUnsignedShort();
        nameAndTypeIndex = dataInput.readUnsignedShort();
    }

    public ClassConstant getClassConstant(){
        if(classConstant == null)
            classConstant = (ClassConstant) jClass.getConstantPool().getConstant(classIndex);
        return classConstant;
    }

    public NameAndTypeConstant getNameAndTypeConstant(){
        if(nameAndTypeConstant == null)
            nameAndTypeConstant = (NameAndTypeConstant) jClass.getConstantPool().getConstant(nameAndTypeIndex);
        return nameAndTypeConstant;
    }
}
