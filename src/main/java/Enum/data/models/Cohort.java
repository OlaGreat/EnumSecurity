package Enum.data.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Enum.utils.AppUtils.ONE_YEAR;

@Getter
@Setter
@Entity
@Table(name = "cohorts")
public class Cohort {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = tru`   e, nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT", length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Program program;
    private String startDate;

    private String endDate;

    @PrePersist
    public void setCreatedAt(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        startDate= currentTime.format(formatter);

        LocalDate currentDate = LocalDate.now().plusDays(ONE_YEAR);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyy");
        endDate = currentDate.format(format);
    }


}
