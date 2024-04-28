package runtime.classdata;

import lombok.Getter;
import lombok.SneakyThrows;
import runtime.JClass;
import runtime.classdata.attribute.Attribute;
import runtime.classdata.constant.UTF8Constant;

import java.io.DataInput;

import static classdefs.FieldAccessFlags.*;


public class Field {
    @Getter
    private final JClass jClass;
    private final int accessFlags;
    @Getter
    private final String name;
    @Getter
    private final String descriptor;
    private final Attribute[] attributes;

    @SneakyThrows
    public Field(DataInput dataInput, JClass jClass){
        this.jClass = jClass;
        accessFlags = dataInput.readUnsignedShort();
        name = ((UTF8Constant) jClass.getConstantPool().getConstant(dataInput.readUnsignedShort())).getValue();
        descriptor = ((UTF8Constant) jClass.getConstantPool().getConstant(dataInput.readUnsignedShort())).getValue();
        int attributeCount = dataInput.readUnsignedShort();
        attributes = new Attribute[attributeCount];
        for(int i = 0;i < attributeCount;i++){
            attributes[i] = Attribute.constructFromData(dataInput, jClass.getConstantPool());
        }
    }

    public int getAttributeCount(){
        return attributes.length;
    }

    public Attribute getAttribute(int index){
        return attributes[index];
    }

    public boolean public_() {
        return (accessFlags & ACC_PUBLIC) != 0;
    }

    public boolean private_() {
        return (accessFlags & ACC_PRIVATE) != 0;
    }

    public boolean protected_() {
        return (accessFlags & ACC_PROTECTED) != 0;
    }

    public boolean static_() {
        return (accessFlags & ACC_STATIC) != 0;
    }

    public boolean final_() {
        return (accessFlags & ACC_FINAL) != 0;
    }

    public boolean transient_() {
        return (accessFlags & ACC_TRANSIENT) != 0;
    }

    public boolean synthetic() {
        return (accessFlags & ACC_SYNTHETIC) != 0;
    }

    public boolean enum_() {
        return (accessFlags & ACC_ENUM) != 0;
    }
}
