package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
//    IOException을 throws로 던졌는데, 이건그냥 귀찮아서 대충 던진거고, 예외 발생시 View page쪽에서
//    다른 경고창이나 뭐 다른거 띄우도록 하기위해 던지지 않고, try-catch로 처리하는게 더 완성도 있음. 다른 창으로
//    return해서 javascript alert로 경고창 띄우는 것도 가능함.
    public String save(BoardDTO boardDTO) throws IOException {
        System.out.println("BoardDTO = " + boardDTO);
        boardService.save(boardDTO); // 첨부파일도 같이 save
        return "redirect:/list"; // 저장 후 목록 페이지로 리다이렉트
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        System.out.println("boardDTOList = " + boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
//    hidden 타입으로 넘어오는 id를 @PathVariable로 받음
    public String findById(Model model, @PathVariable("id") Long id) {
//        조회수 처리
        boardService.updateHits(id);

//       첨부파일이 아닌 상세내용 가져옴
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        System.out.println("boardDTO = " + boardDTO);
//        첨부파일 관련 상세내용 가져옴
        if (boardDTO.getFileAttached() == 1) {
            List<BoardFileDTO> boardFileDTOList = boardService.findFile(id);
            model.addAttribute("boardFileList", boardFileDTOList);
        }
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(BoardDTO boardDTO, Model model) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.update(boardDTO);
//        @GetMapping("/{id}")에 있는 조회수 업데이트와 동일한 로직을 사용
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/list";
    }

    @GetMapping("/favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
        // 아무 동작도 하지 않음
    }

}
