package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import com.codingrecipe.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    /**
     * 게시글을 저장하는 메서드입니다.
     * 첨부파일 처리 메서드 입니다.
     * @throws IOException 파일 저장 중 발생할 수 있는 예외
     */
    public void save(BoardDTO boardDTO) throws IOException {

        if (boardDTO.getBoardFile().get(0).isEmpty()) {
            boardDTO.setFileAttached(0); // 첨부파일이 없으면 fileAttached를 0으로 설정
            boardRepository.save(boardDTO);
        } else {
            boardDTO.setFileAttached(1); // 첨부파일이 있으면 fileAttached를 1로 설정
//            게시물 저장 후 id값 활용을 위해 리턴 받음.
            BoardDTO savedBoard = boardRepository.save(boardDTO);
//            파일만 따로 가져오기
            for (MultipartFile boardFile : boardDTO.getBoardFile()) {
//            파일 이름 가져오기 가져올때 lombok 에서 제공하는 메서드가 아닌 MultipartFile 에서 제공하는 메서드 사용
//                그게 아래에 있는 getter임.
                String originalFileName = boardFile.getOriginalFilename();
                System.out.println("originalFilename = " + originalFileName);
//            저장용 이름 만들기 언제 저장되었는지 시간정보 추가.
                System.out.println(System.currentTimeMillis());
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
                System.out.println("storedFilename = " + storedFileName);
//            boardFileDTO 셋팅
                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setOriginalFileName(originalFileName);
                boardFileDTO.setStoredFileName(storedFileName);
                // 게시글 ID를 설정합니다. 게시글이 저장된 후에 ID를 가져와야 합니다. 그래서 다른 테이블의
//            열값으로 넣습니다. 이건 연관관계 매핑입니다. 조인했다고 볼 수 있음. 게시판 관련 테이블의 Pk를
//            파일첨부 테이블의 외래키 FK 로 설정하는 것과 비슷합니다.
                boardFileDTO.setBoardId(savedBoard.getId());
//            파일 저장용 폴더에 파일 저장 처리 첨부된 애 1개의 경로, 최종적으로 담기는 파일의 이름 = + storedFileName;
                String savePath = "C:/Users/huge4/upload/" + storedFileName;
                boardFile.transferTo(new java.io.File(savePath)); // 파일을 지정된 경로에 저장합니다.
                boardRepository.saveFile(boardFileDTO); // 파일 정보를 데이터베이스에 저장합니다.
            }

        }

    }

    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
        // 삭제 메서드를 추가하여 게시글을 삭제합니다.
        // 이 메서드는 BoardRepository에서 delete 메서드를 호출하여 게시글을 삭제합니다.
        // BoardRepository에 delete 메서드가 정의되어 있어야 합니다.
    }

    public List<BoardFileDTO> findFile(Long id) {
        // 게시글 ID를 사용하여 해당 게시글의 파일 정보를 조회합니다.
        return boardRepository.findFile(id);
    }
}
