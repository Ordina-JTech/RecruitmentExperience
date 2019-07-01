package nl.ordina.recruitmentexperience.data.application.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity(name = "business_unit_manager")
@Table(name = "business_unit_manager")
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUnitManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String prefix;
    private String lastName;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_unit_id")
    private BusinessUnitEntity businessUnit;
}
