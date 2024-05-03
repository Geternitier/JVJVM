package interpreter.instruction.stack;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DUP implements Instruction {
    public DUP(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().push(curFrame.getOperandStack().top());
    }

    @Override
    public String toString(){
        return "dup";
    }
}
