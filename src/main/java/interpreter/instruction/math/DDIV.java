package interpreter.instruction.math;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DDIV implements Instruction {
    public DDIV(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        double value2 = curFrame.getOperandStack().popDouble();
        double value1 = curFrame.getOperandStack().popDouble();
        curFrame.getOperandStack().pushDouble(value1 / value2);
    }

    @Override
    public String toString(){
        return "ddiv";
    }
}
