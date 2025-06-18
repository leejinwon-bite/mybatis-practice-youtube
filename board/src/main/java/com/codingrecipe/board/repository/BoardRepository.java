package com.codingrecipe.board.repository;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor  //mybatis의존성을 주입해야하는 장소가 repository이므로 @RequiredArgsConstructor를 사용합니다.
// @Repository 어노테이션은 스프링이 해당 클래스를 데이터베이스와의 상호작용을 담당하는 컴포넌트로 인식하게 합니다.
// jpa에서도 repository에서 DB 쿼리 메서드를 만들었음. 그래서 mybatis 관련 의존성을 주입해야 합니다.
public class BoardRepository {

    private final SqlSession sql; // mybatis의존성을 주입합니다.

    public BoardDTO save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO); // mybatis의 xml 파일에 있는 쿼리문을 실행합니다.
//        문자열 "Board.save"는 mybatis의 board-mapper.xml 파일에서 쿼리문을 찾기 위한 키입니다.
        // "Board"는 네임스페이스(namespace)로, "save"는 해당 네임스페이스 내의 SQL ID를 나타냅니다.
        // 이 구조는 mybatis에서 SQL 쿼리를 관리하는 일반적인 방식입니다.
        return boardDTO;
//        원래는 저장관련한 SQL문은 return값이 없는데, 다른 테이블 조회를 위해
        // 여기서는 게시글 저장 후 id값을 활용하기 위해서 return값을 BoardDTO로 설정했습니다.
        // 이건 연관관계 매핑입니다. 조인했다고 볼 수 있음. 게시판 관련 테이블의 Pk를
        // 파일첨부 테이블의 외래키 FK 로 설정하는 것과 비슷합니다.
    }

    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
        // selectList 메서드는 여러 개의 결과를 반환하는 쿼리를 실행할 때 사용됩니다.
        // "Board.findAll"은 mybatis의 board-mapper.xml 파일에서 모든 게시글을 조회하는 SQL ID를 나타냅니다.
    }

    public void updateHits(Long id) {
//        뭔가를 클릭하면 controller에서 이 메서드가 실행되고, SQL 함수가 중간에 실행이 되며 그 결과를 DB에 담아서
//        다시 select문으로 꺼내다가 쓰는 거임. 이런식으로 조회수 늘리기를 구현함.
        sql.update("Board.updateHits", id);
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id); // mybatis의 xml 파일에 있는 쿼리문을 실행합니다.
        // selectOne 메서드는 단일 결과를 반환하는 쿼리를 실행할 때 사용됩니다.
        // "Board.findById"는 mybatis의 board-mapper.xml 파일에서 특정 게시글을 조회하는 SQL ID를 나타냅니다.
    }

    public void update(BoardDTO boardDTO) {
        sql.update("Board.update", boardDTO);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }

    public void saveFile(BoardFileDTO boardFileDTO) {
        sql.insert("Board.saveFile", boardFileDTO);
    }

//    매개에 Long id 1개만 들어간거는 연관관계에 있는 다른 테이블을 추가 조회해서 총 2번 조회하기 위함임. 그래서
//    다른 테이블 관련한 자료형의 메서드의 매개로 PK가 들어간거임. 그리고 1개의 form에 여러가지 첨부파일이 담기면
//    FK 인 boardId가 여러개 중복되어 들어갈 수 있음. 그래서 List<BoardFileDTO>로 반환타입을 설정함.
    public List<BoardFileDTO> findFile(Long id) {
        return sql.selectList("Board.findFile", id);
    }
}
