package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;
import runtime.classdata.constant.FieldRef;
import runtime.reference.ClassReference;
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
        ClassReference classReference = (ClassReference) curFrame.getOperandStack().popReference();
        FieldReference fieldRef = classReference.getField(ref.getName());
        String descriptor = fieldRef.getField().getDescriptor();
        switch (descriptor) {
            case "I" -> curFrame.getOperandStack().pushInt(classReference.getField(ref.getName()).getValue().getInt());
            default -> curFrame.getOperandStack().pushReference(fieldRef);
        }
    }

    @Override
    public String toString(){
        return "getfield "+index;
    }
}
