package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LAND implements Instruction {
    public LAND(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        long value1 = curFrame.getOperandStack().popLong();
        long value2 = curFrame.getOperandStack().popLong();
        curFrame.getOperandStack().pushLong(value1 & value2);
    }


    @Override
    public String toString(){
        return "land";
    }
}
