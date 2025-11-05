import java.io.Serializable;

public record TagData(String tag, int count) implements Serializable {
    @Override
    public String toString() {
        return tag + " -> " + count;
    }
}