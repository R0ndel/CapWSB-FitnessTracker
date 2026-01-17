package pl.wsb.fitnesstracker.reporting.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.time.*;
import java.util.List;

@Service
@Slf4j
public class WeeklyTrainingReportService {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    public WeeklyTrainingReportService(
            UserRepository userRepository,
            TrainingRepository trainingRepository) {
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    public void generateWeeklyReport() {
        LocalDate today = LocalDate.of(2024, 1, 19);
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        log.info("=== WEEKLY TRAINING REPORT [{} - {}] ===",
                startOfWeek, endOfWeek);

        List<Training> allTrainings = trainingRepository.findAll();

        for (User user : userRepository.findAll()) {
            List<Training> userTrainings = allTrainings.stream()
                    .filter(t -> t.getUser().getId().equals(user.getId()))
                    .filter(t -> {
                        LocalDate trainingDate =
                                t.getStartTime().toInstant()
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate();
                        return !trainingDate.isBefore(startOfWeek)
                                && !trainingDate.isAfter(endOfWeek);
                    })
                    .toList();

            log.info("User: {} {} ({} trainings)",
                    user.getFirstName(),
                    user.getLastName(),
                    userTrainings.size());
        }
    }
}
