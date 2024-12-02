package zerobase.weather.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.service.DiaryService;
import zerobase.weather.service.SchedulerService;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final DiaryService diaryService;

    @Override
    @Transactional
    @Scheduled(cron = "0 0 1 * * *")
    public void saveWeather() {
        diaryService.saveWeatherDate();
    }
}
