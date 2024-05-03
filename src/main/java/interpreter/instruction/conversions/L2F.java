package interpreter.instruction.conversions;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class L2F implements Instruction {
    public L2F(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        long value = curFrame.getOperandStack().popLong();
        curFrame.getOperandStack().pushFloat((float) value);
    }

    @Override
    public String toString(){
        return "l2f";
    }
}
