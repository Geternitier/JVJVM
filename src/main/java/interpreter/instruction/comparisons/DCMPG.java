package interpreter.instruction.comparisons;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DCMPG implements Instruction {
    public DCMPG(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        double value2 = curFrame.getOperandStack().popDouble();
        double value1 = curFrame.getOperandStack().popDouble();
        if(Double.isNaN(value1) || Double.isNaN(value2)){
            curFrame.getOperandStack().pushInt(1);
        } else if(value1 > value2) curFrame.getOperandStack().pushInt(1);
        else if(value1 == value2) curFrame.getOperandStack().pushInt(0);
        else curFrame.getOperandStack().pushInt(-1);
    }

    @Override
    public String toString(){
        return "dcmpg";
    }
}
