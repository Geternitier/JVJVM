package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FSUB implements Instruction {
    public FSUB(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        float value2 = curFrame.getOperandStack().popFloat();
        float value1 = curFrame.getOperandStack().popFloat();
        curFrame.getOperandStack().pushFloat(value1 - value2);
    }


    @Override
    public String toString(){
        return "fsub";
    }
}
