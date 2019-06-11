package nl.ordina.recruitmentexperience.data.application.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity(name = "application")
@Table(name = "application")
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "applicant_id")
    private ApplicantEntity applicant;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_unit_id")
    private BusinessUnitEntity businessUnit;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_unit_manager_id")
    private BusinessUnitManagerEntity businessUnitManager;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private RegionEntity region;
    private String applicationState;
    private String motivationLetterLink;
    private LocalDateTime firstInterviewDateTime;
    private LocalDateTime secondInterviewDateTime;
}
