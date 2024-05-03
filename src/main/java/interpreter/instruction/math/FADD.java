package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FADD implements Instruction {
    public FADD(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        float value1 = curFrame.getOperandStack().popFloat();
        float value2 = curFrame.getOperandStack().popFloat();
        curFrame.getOperandStack().pushFloat(value1 + value2);
    }


    @Override
    public String toString(){
        return "fadd";
    }
}
