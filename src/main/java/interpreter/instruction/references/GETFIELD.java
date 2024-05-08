package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;
import runtime.classdata.constant.FieldRef;
import runtime.reference.FieldReference;

public class GETFIELD implements Instruction {
    private final int index;

    public GETFIELD(ProgramCounter programCounter, Method method){
        index = programCounter.readShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        FieldRef ref = (FieldRef) curFrame.getDynamicLink().getConstant(index);
        try {
            curFrame.getOperandStack().pushReference(new FieldReference(ref.getField()));
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.toString());
        }
    }

    @Override
    public String toString(){
        return "getfield "+index;
    }
}
