package interpreter;

import interpreter.instruction.Decoder;
import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.LocalVariables;
import runtime.classdata.Method;

public class JInterpreter {

    public void invoke(Method method, JThread thread, LocalVariables variables){
        JFrame frame = new JFrame(method, variables);
        thread.push(frame);
        if(method.native_()){
            runNative(thread);
        } else run(thread);
    }

    public void run(JThread thread){
        JFrame frame = thread.top();
        while (thread.top() == frame){
            Instruction instruction = Decoder.decode(frame.getProgramCounter(), frame.getMethod());
            instruction.run(thread);
        }
    }

    public void runNative(JThread thread){

    }

}