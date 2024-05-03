package interpreter.instruction.comparisons;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class IFNE implements Instruction {
    private final int branch;

    public IFNE(ProgramCounter programCounter, Method method) {
        branch = programCounter.readUnsignedShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        int value = curFrame.getOperandStack().popInt();
        if(value != 0){
            ProgramCounter pc = curFrame.getProgramCounter();
            pc.setPosition(pc.getPosition() + branch - 3);
        }
    }

    @Override
    public String toString(){
        return "ifne "+branch;
    }
}
