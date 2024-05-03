package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import interpreter.instruction.InstructionExecutionError;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;
import runtime.classdata.constant.Constant;
import runtime.classdata.constant.DoubleConstant;
import runtime.classdata.constant.LongConstant;

public class LDC2_W implements Instruction {
    private final int index;

    public LDC2_W(ProgramCounter programCounter, Method method) {
        index = programCounter.ushort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        Constant constant = curFrame.getDynamicLink().getConstant(index);
        if(constant instanceof LongConstant){
            curFrame.getOperandStack().pushLong(((LongConstant) constant).getValue());
        } else if(constant instanceof DoubleConstant){
            curFrame.getOperandStack().pushDouble(((DoubleConstant) constant).getValue());
        } else {
            throw new InstructionExecutionError("ldc2_w only works with long and double");
        }
    }

    @Override
    public String toString(){
        return "ldc2_w " + index;
    }
}
