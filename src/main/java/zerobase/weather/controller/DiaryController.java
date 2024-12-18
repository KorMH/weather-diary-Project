package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장", notes = "API 세부 내용입니다.")  //test -> @DisplayName()
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                     @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @GetMapping("/read/diary")
    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져옵니다")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE)
                          @ApiParam(value = "조회할 날짜", example = "2024-11-22") LocalDate date) {
        return diaryService.readDiary(date);
    }

    @GetMapping("/read/diaries")
    @ApiOperation("선택한 기간의 모든 일기 데이터를 가져옵니다.")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = ISO.DATE)
                            @ApiParam(value = "조회할 기간의 첫째 날", example = "2024-11-21") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = ISO.DATE)
                            @ApiParam(value = "조회할 기간의 마지막 날", example = "2024-12-31") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @PutMapping("/update/diary")
    @ApiOperation("특정 날짜의 첫번째 일기 수정")
    void updateDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @DeleteMapping("/delete/diary")
    @ApiOperation("특정 날짜의 모든 일기 삭제")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE)
                     @ApiParam(value = "삭제할 날짜", example = "2024-11-28") LocalDate date) {
        diaryService.deleteDiary(date);
    }
}