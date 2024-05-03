package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LNEG implements Instruction {
    public LNEG(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        long value = curFrame.getOperandStack().popLong();
        curFrame.getOperandStack().pushLong(-value);
    }

    @Override
    public String toString(){
        return "lneg";
    }
}
