package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class IOR implements Instruction {
    public IOR(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        int value2 = curFrame.getOperandStack().popInt();
        int value1 = curFrame.getOperandStack().popInt();
        curFrame.getOperandStack().pushInt(value1 | value2);
    }


    @Override
    public String toString(){
        return "ior";
    }
}
