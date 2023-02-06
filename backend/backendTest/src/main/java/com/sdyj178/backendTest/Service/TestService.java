package com.sdyj178.backendTest.Service;

import com.sdyj178.backendTest.DTO.TestDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class TestService {
    static HashMap<Long, String> map = new HashMap<>();

    private HashMap getMap() {
        return this.map;
    }

    public List getMapList() {
        List<TestDto> list = new LinkedList();
        for (Object entry : getMap().entrySet()) {
            Map.Entry<Long, String> e = (Map.Entry<Long, String>) entry;
            list.add(new TestDto(e.getKey(), e.getValue()));
        }
        return list;
    }

    public TestDto getTestDto(long num) {
        return new TestDto(num, (String) getMap().get(num));
    }

    public String addTestDto(TestDto testDto) {
        if (getMap().containsKey(testDto.getNum())) return "이미 존재하는 인덱스입니다.";

        map.put(testDto.getNum(), testDto.getStr());
        return "성공적으로 정보를 저장했습니다.";
    }

    public String updateTestDto(TestDto testDto) {
        if (!getMap().containsKey(testDto.getNum())) return "존재하지 않는 인덱스입니다.";

        map.put(testDto.getNum(), testDto.getStr());
        return "성공적으로 정보를 수정했습니다.";
    }

    public String deleteTestDto(long num) {
        if (!getMap().containsKey(num)) return "존재하지 않는 인덱스입니다.";
        map.remove(num);
        return "성공적으로 정보를 삭제했습니다.";
    }


}
