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
public class FrameData {

    private FrameCoord topLeft;
    private FrameCoord topRight;
    private FrameCoord bottomLeft;
    private FrameCoord bottomRight;

}
