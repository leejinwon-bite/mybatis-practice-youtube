package com.codingrecipe.board.repository;

import com.codingrecipe.board.dto.BoardDTO;
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

    public void save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO); // mybatis의 xml 파일에 있는 쿼리문을 실행합니다.
//        DTO는 data transfer object의 약자로, 데이터 전송 객체를 의미합니다. 그래서 매개로 들어간듯함.
//        문자열 "Board.save"는 mybatis의 board-mapper.xml 파일에서 쿼리문을 찾기 위한 키입니다.
        // "Board"는 네임스페이스(namespace)로, "save"는 해당 네임스페이스 내의 SQL ID를 나타냅니다.
        // 이 구조는 mybatis에서 SQL 쿼리를 관리하는 일반적인 방식입니다.
    }

    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll"); // mybatis의 xml 파일에 있는 쿼리문을 실행합니다.
        // selectList 메서드는 여러 개의 결과를 반환하는 쿼리를 실행할 때 사용됩니다.
        // "Board.findAll"은 mybatis의 board-mapper.xml 파일에서 모든 게시글을 조회하는 SQL ID를 나타냅니다.
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits", id); // mybatis의 xml 파일에 있는 쿼리문을 실행합니다.
        // update 메서드는 데이터베이스의 데이터를 수정하는 쿼리를 실행할 때 사용됩니다.
        // "Board.updateHits"는 mybatis의 board-mapper.xml 파일에서 게시글 조회수를 업데이트하는 SQL ID를 나타냅니다.
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id); // mybatis의 xml 파일에 있는 쿼리문을 실행합니다.
        // selectOne 메서드는 단일 결과를 반환하는 쿼리를 실행할 때 사용됩니다.
        // "Board.findById"는 mybatis의 board-mapper.xml 파일에서 특정 게시글을 조회하는 SQL ID를 나타냅니다.
    }

    public void update(BoardDTO boardDTO) {
        sql.update("Board.update", boardDTO); // mybatis의 xml 파일에 있는 쿼리문을 실행합니다.
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id); // mybatis의 xml 파일에 있는 쿼리문을 실행합니다.
    }
}
