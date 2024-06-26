package runtime.classdata;

import classdefs.MethodDescriptor;
import lombok.Getter;
import lombok.SneakyThrows;
import runtime.JClass;
import runtime.ProgramCounter;
import runtime.classdata.attribute.Attribute;
import runtime.classdata.attribute.Code;
import runtime.classdata.constant.UTF8Constant;

import java.io.DataInput;

import static classdefs.MethodAccessFlags.*;

public class Method {
    @Getter
    private final JClass jClass;
    private final int accessFlags;
    @Getter
    private final String name;
    @Getter
    private final String descriptor;
    private final Attribute[] attributes;
    @Getter
    private Code code;

    @SneakyThrows
    public Method(DataInput dataInput, JClass jClass){
        this.jClass = jClass;
        accessFlags = dataInput.readUnsignedShort();
        int nameIndex = dataInput.readUnsignedShort();
        int descriptorIndex = dataInput.readUnsignedShort();
        name = ((UTF8Constant) jClass.getConstantPool().getConstant(nameIndex)).getValue();
        descriptor = ((UTF8Constant) jClass.getConstantPool().getConstant(descriptorIndex)).getValue();

        int attributeCount = dataInput.readUnsignedShort();
        attributes = new Attribute[attributeCount];
        for(int i = 0;i < attributeCount;i++){
            attributes[i] = Attribute.constructFromData(dataInput, jClass.getConstantPool());
        }

        for(Attribute i: attributes){
            if(i instanceof Code){
                code = (Code) i;
                break;
            }
        }
    }

    public int countArgc(){
        return MethodDescriptor.countArgc(descriptor);
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

    public boolean synchronized_() {
        return (accessFlags & ACC_SYNCHRONIZED) != 0;
    }

    public boolean bridge() {
        return (accessFlags & ACC_BRIDGE) != 0;
    }

    public boolean varargs() {
        return (accessFlags & ACC_VARARGS) != 0;
    }

    public boolean native_() {
        return (accessFlags & ACC_NATIVE) != 0;
    }

    public boolean abstract_() {
        return (accessFlags & ACC_ABSTRACT) != 0;
    }

    public boolean strict() {
        return (accessFlags & ACC_STRICT) != 0;
    }

    public boolean synthetic() {
        return (accessFlags & ACC_SYNTHETIC) != 0;
    }

    public String getContent(){
        StringBuilder sb = new StringBuilder();

        if(public_()) sb.append("public ");
        else if(private_()) sb.append("private ");
        else if(protected_()) sb.append("protected ");

        if(static_()) sb.append("static ");
        else if(abstract_()) sb.append("abstract ");

        if(synchronized_()) sb.append("synchronized ");

        if(final_()) sb.append("final ");

        sb.append(getName()).append("();\n");
        sb.append("  descriptor: ").append(getDescriptor()).append("\n");
        sb.append("  flags: (0x").append(Integer.toHexString(accessFlags)).append(")\n");
        if(code != null){
            sb.append("  Code:\n    ");
            sb.append(String.format("stack=%d, locals=%d, args_size=%d\n",
                    code.getMaxStack(), code.getMaxLocals(), countArgc()));
            ProgramCounter pc = new ProgramCounter(code.getCode());
            String[] instructions = pc.getCode(this).split("\n");
            for(String s: instructions){
                sb.append("      ").append(s).append("\n");
            }
        }
        return sb.toString();
    }
}
