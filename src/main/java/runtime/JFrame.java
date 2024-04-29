package runtime;

import lombok.Getter;
import runtime.classdata.ConstantPool;
import runtime.classdata.Method;
import runtime.classdata.attribute.Code;

@Getter
public class JFrame {
    private final LocalVariables localVariables;
    private final OperandStack operandStack;
    private final JClass jClass;
    private final ConstantPool dynamicLink;
    private final Method method;
    private final ProgramCounter programCounter;

    public JFrame(Method method, LocalVariables localVariables){
        this.method = method;
        jClass = method.getJClass();
        dynamicLink = jClass.getConstantPool();

        if(method.native_()){
            this.localVariables = new LocalVariables(localVariables.size());
            operandStack = null;
            programCounter = null;
        } else {
            Code code = method.getCode();
            this.localVariables = new LocalVariables(code.getMaxLocals());
            operandStack = new OperandStack(code.getMaxStack());
            programCounter = new ProgramCounter(code.getCode());
        }

        localVariables.copyTo(0, localVariables.size(), this.localVariables, 0);
    }
}
