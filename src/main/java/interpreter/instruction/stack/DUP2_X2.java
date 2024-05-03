package interpreter.instruction.stack;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.Variable;
import runtime.classdata.Method;

public class DUP2_X2 implements Instruction {
    public DUP2_X2(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        Variable value1 = curFrame.getOperandStack().pop();
        Variable value2 = curFrame.getOperandStack().pop();
        Variable value3 = curFrame.getOperandStack().pop();
        Variable value4 = curFrame.getOperandStack().pop();
        curFrame.getOperandStack().push(value2);
        curFrame.getOperandStack().push(value1);
        curFrame.getOperandStack().push(value4);
        curFrame.getOperandStack().push(value3);
        curFrame.getOperandStack().push(value2);
        curFrame.getOperandStack().push(value1);
    }

    @Override
    public String toString(){
        return "dup2_x2";
    }
}
