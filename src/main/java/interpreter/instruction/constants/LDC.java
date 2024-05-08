package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;
import runtime.classdata.constant.*;
import runtime.reference.ClassReference;
import runtime.reference.StringReference;

public class LDC implements Instruction {
    private final int index;

    public LDC(ProgramCounter programCounter, Method method) {
        index = programCounter.readUnsignedByte();
    }

    public LDC(int index){this.index = index;}

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        Constant constant = curFrame.getDynamicLink().getConstant(index);
        if(constant instanceof IntegerConstant){
            curFrame.getOperandStack().pushInt(((IntegerConstant) constant).getValue());
        } else if(constant instanceof FloatConstant){
            curFrame.getOperandStack().pushFloat(((FloatConstant) constant).getValue());
        } else if(constant instanceof StringConstant){
            curFrame.getOperandStack().pushReference(new StringReference((StringConstant) constant));
        } else if(constant instanceof ClassConstant){
            curFrame.getOperandStack().pushReference(new ClassReference((ClassConstant) constant));
        } // TODO: MethodType MethodHandle
    }

    @Override
    public String toString(){
        return "ldc " + index;
    }
}
