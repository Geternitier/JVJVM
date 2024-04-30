package interpreter.instruction;

import runtime.JThread;

public interface Instruction {
    void run(JThread thread);
}
