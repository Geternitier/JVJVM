package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.ConstantPool;
import runtime.classdata.Field;
import runtime.classdata.Method;
import runtime.classdata.constant.FieldRef;

public class GETSTATIC implements Instruction {
    private final int index;

    public GETSTATIC(ProgramCounter pc, Method method){
        index = pc.short_();
    }


    @Override
    public void run(JThread thread) {
        ConstantPool constantPool = thread.top().getJClass().getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(index);
        Field field = fieldRef.getJClass().getField(fieldRef.getName(), fieldRef.getType());
        // TODO
    }


}
