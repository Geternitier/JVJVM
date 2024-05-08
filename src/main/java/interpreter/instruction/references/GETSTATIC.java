package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.JClass;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.ConstantPool;
import runtime.classdata.Field;
import runtime.classdata.Method;
import runtime.classdata.constant.FieldRef;
import runtime.reference.FieldReference;

public class GETSTATIC implements Instruction {
    private final int index;

    public GETSTATIC(ProgramCounter pc, Method method){
        index = pc.readShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        ConstantPool constantPool = curFrame.getJClass().getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(index);
        try {
            JClass jClass = fieldRef.getClassConstant().getValue();
            Field field = jClass.getField(fieldRef.getName(), fieldRef.getType());
            curFrame.getOperandStack().pushReference(new FieldReference(field));
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.toString());
        }
    }

    @Override
    public String toString(){
        return "getstatic "+index;
    }
}
