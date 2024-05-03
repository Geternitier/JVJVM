package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LUSHR implements Instruction {
    public LUSHR(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        int value2 = curFrame.getOperandStack().popInt();
        long value1 = curFrame.getOperandStack().popLong();
        int s = value2 & 0x3f;
        curFrame.getOperandStack().pushLong((value1 > 0)?value1 >> s:((value1 >> s) + (2L << ~s)));
    }

    @Override
    public String toString(){
        return "lushr";
    }
}
