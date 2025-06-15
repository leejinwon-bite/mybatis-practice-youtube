package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class BoardDTO {

    private Long id;

    private String boardWriter;

    private String boardPass;

    private String boardTitle;

    private String boardContents;

    private int boardHits;

    private String createdAt;


//   첨부파일 관련한 필드들
    private int fileAttached; // 첨부파일 여부 (0: 없음, 1: 있음)
    private List<MultipartFile> boardFile; // 첨부파일 자체를 담기위한 자료형임. 첨부파일에 관련한 자료형이라고 보면됨.
}
