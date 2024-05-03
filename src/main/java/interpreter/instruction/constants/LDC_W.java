package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LDC_W implements Instruction {
    private final int index;
    private final LDC ldc;

    public LDC_W(ProgramCounter programCounter, Method method) {
        index = programCounter.readUnsignedShort();
        ldc = new LDC(index);
    }

    @Override
    public void run(JThread thread) {
        ldc.run(thread);
    }


    @Override
    public String toString(){
        return "ldc_w " + index;
    }
}
