package interpreter.instruction.loads;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DLOAD implements Instruction {
    private final int index;

    public DLOAD(ProgramCounter programCounter, Method method) {
        index = programCounter.readUnsignedByte();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getOperandStack().pushDouble(curFrame.getLocalVariables().getDouble(index));
    }

    @Override
    public String toString(){
        return "dload";
    }
}
