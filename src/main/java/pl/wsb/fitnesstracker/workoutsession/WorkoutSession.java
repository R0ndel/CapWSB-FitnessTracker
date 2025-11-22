package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "workout_session")
@Getter
@Setter
@NoArgsConstructor
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private pl.wsb.fitnesstracker.training.api.Training training;

    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
    private double altitude;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}
