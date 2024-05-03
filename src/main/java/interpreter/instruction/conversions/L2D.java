package interpreter.instruction.conversions;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class L2D implements Instruction {
    public L2D(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        long value = curFrame.getOperandStack().popLong();
        curFrame.getOperandStack().pushDouble((double) value);
    }

    @Override
    public String toString(){
        return "l2d";
    }
}
