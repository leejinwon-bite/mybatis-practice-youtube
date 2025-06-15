package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO {

    private Long id; // 파일 ID
    private Long boardId; // 게시글 ID
    private String originalFileName; // 원본 파일 이름. 사용자가 올리는 파일들
    private String storedFileName; // 저장된 파일 이름. 서버에 저장되는 파일 이름



    // 추가적인 필드가 필요하면 여기에 정의할 수 있습니다.

}
