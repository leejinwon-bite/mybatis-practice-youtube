package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// BoardDTO의 필드중  private int fileAttached;의 값이 1일때 이 DTO의 필드들이 초기화되도록 설계를 해놓음.
// 첨부파일 관련한 내용들만 담은 DTO라고 보면됨. 동시에 BoardDTO와 연관관계 Mapping이 되어있음. 그 FK가 boardId임.
public class BoardFileDTO {

    private Long id; // 파일 ID
    private Long boardId; // 게시글 ID
    private String originalFileName; // 원본 파일 이름. 사용자가 올리는 파일들
    private String storedFileName; // 저장된 파일 이름. 서버에 저장되는 파일 이름



    // 추가적인 필드가 필요하면 여기에 정의할 수 있습니다.

}
