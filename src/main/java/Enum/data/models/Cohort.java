package Enum.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static Enum.utils.AppUtils.ONE_YEAR;
import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@Entity
@Table(name = "cohorts")
public class Cohort {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cohortName;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT", length = 1000)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Program> programs;
    private String startDate;

    private String endDate;

    private String  avatarImageUrl;



//    @PrePersist
//    public void setCreatedAt(){
//        LocalDateTime currentTime = LocalDateTime.now();
//        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        startDate= currentTime.format(formatter);
//
//        LocalDate currentDate = LocalDate.now().plusDays(ONE_YEAR);
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyy");
//        endDate = currentDate.format(format);
//    }


}
