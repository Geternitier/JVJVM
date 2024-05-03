package runtime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ReturnAddress implements Reference {
    @Getter
    private final int address;
}
