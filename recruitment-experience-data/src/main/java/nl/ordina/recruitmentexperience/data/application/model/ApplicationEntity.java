package nl.ordina.recruitmentexperience.data.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity(name = "application")
@Table(name = "application")
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String state;
    private String motivationLetterLink;
    private String title;
    private String firstInterviewDateTime;
    private String secondInterviewDateTime;
}
