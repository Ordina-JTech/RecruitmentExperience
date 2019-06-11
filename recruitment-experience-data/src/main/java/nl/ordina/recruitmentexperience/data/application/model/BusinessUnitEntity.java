package nl.ordina.recruitmentexperience.data.application.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity(name = "business_unit")
@Table(name = "business_unit")
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUnitEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
