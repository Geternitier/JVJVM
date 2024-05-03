package interpreter.instruction.comparisons;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FCMPL implements Instruction {
    public FCMPL(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        float value2 = curFrame.getOperandStack().popFloat();
        float value1 = curFrame.getOperandStack().popFloat();
        if(Float.isNaN(value1) || Float.isNaN(value2)){
            curFrame.getOperandStack().pushInt(-1);
        } else if(value1 > value2) curFrame.getOperandStack().pushInt(1);
        else if(value1 == value2) curFrame.getOperandStack().pushInt(0);
        else curFrame.getOperandStack().pushInt(-1);
    }

    @Override
    public String toString(){
        return "fcmpl";
    }
}
