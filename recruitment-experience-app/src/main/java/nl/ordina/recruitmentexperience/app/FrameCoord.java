package nl.ordina.recruitmentexperience.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FrameCoord {

    private int x;
    private int y;

    @Override
    public String toString() {
        return String.format("%d,%d", getX(), getY());
    }
}
