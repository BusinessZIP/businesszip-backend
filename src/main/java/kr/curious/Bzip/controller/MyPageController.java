package kr.curious.Bzip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mypage")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
public class MyPageController {

}