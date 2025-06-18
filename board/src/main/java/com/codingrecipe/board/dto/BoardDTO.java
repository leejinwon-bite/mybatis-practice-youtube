package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import java.util.List; // List<MultipartFile> boardFile; // 첨부파일을 여러개 올릴 수 있도록 List로 선언함.
// MultipartFile은 스프링에서 제공하는 파일 업로드를 위한 자료형임.

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


//   첨부파일 관련한 필드들 얘네 2개의 필드가 BoardFileDTO에 있지 않고 여기 있는 이유는 아마도 맨 처음 첨부파일을 저장을할때
//   1개의 form에서 저장되기에 다른 게시글 내용과 함께 저장되어서 BoardDTO에 쓰인거 같음. 특히 fileAttached는
//   첨부파일이 있는지 없는지 여부를 나타내는 필드로 0,1 값에 따라서 BoardFileDTO에 필드가 초기화 되고 안되고를 결정.
    private int fileAttached; // 첨부파일 여부 (0: 없음, 1: 있음)
    // 첨부파일 자체를 담기위한 자료형임. 첨부파일에 관련한 자료형이라고 보면됨. 만약 다중 첨부파일이 들어올경우 이 필드엔
//    그 여러개의 다중 첨부파일이 배열의 형태로 담긴다고 볼 수 잇음.
    private List<MultipartFile> boardFile;
}
