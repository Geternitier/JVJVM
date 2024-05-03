package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DADD implements Instruction {
    public DADD(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        double value1 = curFrame.getOperandStack().popDouble();
        double value2 = curFrame.getOperandStack().popDouble();
        curFrame.getOperandStack().pushDouble(value1 + value2);
    }


    @Override
    public String toString(){
        return "dadd";
    }
}
