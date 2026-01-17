package pl.wsb.fitnesstracker.async;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.reporting.internal.WeeklyTrainingReportService;

@Component
class WeeklyTrainingReportJob {

    private final WeeklyTrainingReportService reportService;

    WeeklyTrainingReportJob(WeeklyTrainingReportService reportService) {
        this.reportService = reportService;
    }

    // DO TESTÓW: co minutę
    
    // PRODUKCJA / ZALICZENIE: co poniedziałek 08:00
    // @Scheduled(cron = "0 0 8 * * MON")
    @Scheduled(cron = "0 * * * * *")
    public void runWeeklyReport() {
        reportService.generateWeeklyReport();
    }
}
