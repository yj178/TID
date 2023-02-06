package com.sdyj178.backendTest.Controller;

import com.sdyj178.backendTest.DTO.TestDto;
import com.sdyj178.backendTest.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/num")
    public ResponseEntity getList() {
        return new ResponseEntity<>(testService.getMapList(), HttpStatus.OK);
    }

    @GetMapping("/num/{number}")
    public ResponseEntity getDto(@PathVariable(value = "number") int number) {
        return new ResponseEntity<>(testService.getTestDto(number), HttpStatus.OK);
    }

    @PostMapping("/num")
    public ResponseEntity addDto(@RequestBody TestDto testDto) {
        System.out.println(testDto);
        return new ResponseEntity<>(testService.addTestDto(testDto), HttpStatus.OK);
    }

    @PutMapping("/num")
    public ResponseEntity updateDto(@RequestBody TestDto testDto) {
        return new ResponseEntity<>(testService.updateTestDto(testDto), HttpStatus.OK);
    }

    @DeleteMapping("/num/{number}")
    public ResponseEntity deleteDto(@PathVariable(value = "number") int number) {
        return new ResponseEntity<>(testService.deleteTestDto(number), HttpStatus.OK);
    }


}
