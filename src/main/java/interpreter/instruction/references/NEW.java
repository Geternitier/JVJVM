package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.JClass;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;
import runtime.classdata.constant.ClassConstant;
import runtime.reference.ClassReference;

public class NEW implements Instruction {
    private final int index;

    public NEW(ProgramCounter programCounter, Method method) {
        index = programCounter.readUnsignedShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        ClassConstant constant = (ClassConstant) curFrame.getJClass().getConstantPool().getConstant(index);
        ClassReference reference = new ClassReference(constant);
        curFrame.getOperandStack().pushReference(reference);
    }

    @Override
    public String toString(){
        return "new "+index;
    }
}
