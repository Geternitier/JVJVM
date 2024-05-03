package interpreter.instruction.comparisons;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LCMP implements Instruction {
    public LCMP(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        long value2 = curFrame.getOperandStack().popLong();
        long value1 = curFrame.getOperandStack().popLong();
        if(value1 > value2) curFrame.getOperandStack().pushInt(1);
        else if(value1 == value2) curFrame.getOperandStack().pushInt(0);
        else curFrame.getOperandStack().pushInt(-1);
    }

    @Override
    public String toString(){
        return "lcmp";
    }
}
