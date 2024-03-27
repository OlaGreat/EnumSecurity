package Enum.data.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "program")
@Getter
@Setter
@RequiredArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NonNull
    @Column(nullable = false)
    private String programName;

    @ManyToOne
    @JoinColumn(name = "cohort_id")
    private Cohort cohort;

    public Program() {

    }
}
