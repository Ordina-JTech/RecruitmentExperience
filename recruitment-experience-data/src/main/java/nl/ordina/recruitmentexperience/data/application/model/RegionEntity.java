package nl.ordina.recruitmentexperience.data.application.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity(name = "region")
@Table(name = "region")
@NoArgsConstructor
@AllArgsConstructor
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(
            mappedBy = "region",
            cascade = CascadeType.ALL
    )
    private List<ApplicationEntity> applications;
}
