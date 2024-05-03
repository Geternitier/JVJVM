package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class INEG implements Instruction {
    public INEG(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        int value = curFrame.getOperandStack().popInt();
        curFrame.getOperandStack().pushInt(-value);
    }

    @Override
    public String toString(){
        return "ineg";
    }
}
