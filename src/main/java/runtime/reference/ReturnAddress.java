package runtime.reference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import runtime.reference.Reference;

@AllArgsConstructor
public class ReturnAddress implements Reference {
    @Getter
    private final int address;
}
