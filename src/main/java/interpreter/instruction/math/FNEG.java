package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FNEG implements Instruction {
    public FNEG(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        float value = curFrame.getOperandStack().popFloat();
        curFrame.getOperandStack().pushFloat(-value);
    }

    @Override
    public String toString(){
        return "fneg";
    }
}
