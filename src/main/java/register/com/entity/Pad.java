package register.com.entity;

import lombok.Data;

@Data
public class Pad {
    private String name;
    private String cpuid;
    private boolean online;

    public Pad(String name, String cpuid) {
        this.name = name;
        this.cpuid = cpuid;
    }

    public Pad(String cpuid){
        this.cpuid = cpuid;
    }
}
