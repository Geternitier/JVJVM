package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ISHR implements Instruction {
    public ISHR(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        int value2 = curFrame.getOperandStack().popInt();
        int value1 = curFrame.getOperandStack().popInt();
        int s = value2 & 0x1f;
        curFrame.getOperandStack().pushInt(value1 >> s);
    }

    @Override
    public String toString(){
        return "ishr";
    }
}
