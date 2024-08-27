package com.example.springbasicdb.service;

import com.example.springbasicdb.dto.MemoRequestDto;
import com.example.springbasicdb.dto.MemoResponseDto;
import com.example.springbasicdb.entity.Memo;
import com.example.springbasicdb.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Annotation @Service는 @Component와 같다, Spring Bean으로 등록한다는 뜻.
 * Spring Bean으로 등록되면 다른 클래스에서 주입하여 사용할 수 있다.
 * 명시적으로 Service Layer 라는것을 나타낸다.
 * 비지니스 로직을 수행한다.
 */
@Service
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public MemoResponseDto saveMemo(MemoRequestDto requestDto) {

        // 요청받은 데이터로 Memo 객체 생성
        Memo memo = new Memo(requestDto.getTitle(), requestDto.getContents());

        // 저장
        return memoRepository.saveMemo(memo);
    }

    @Override
    public List<MemoResponseDto> findAllMemos() {
        // init List
        List<MemoResponseDto> responseList = new ArrayList<>();
//
//        // 전체 조회
        Map<Long, Memo> memoList = memoRepository.findAllMemos();
//
//        // HashMap<Memo> -> List<MemoResponseDto>
        for (Memo memo : memoList.values()) {
            MemoResponseDto responseDto = new MemoResponseDto(memo);
            responseList.add(responseDto);
        }

        return responseList;
    }

    @Override
    public MemoResponseDto findMemoById(Long id) {
        // 식별자의 Memo가 없다면?
        Memo memo = memoRepository.findMemoById(id);

        // NPE 방지
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateMemo(Long id, String title, String contents) {
        // memo 조회
        Memo memo = memoRepository.findMemoById(id);

        // NPE 방지
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        // 필수값 검증
        if (title == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        // memo 수정
        memo.update(title, contents);

        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateTitle(Long id, String title, String contents) {
        // memo 조회
        Memo memo = memoRepository.findMemoById(id);

        // NPE 방지
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        // 필수값 검증
        if (title == null || contents != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        memo.update(title);

        return new MemoResponseDto(memo);
    }

    @Override
    public void deleteMemo(Long id) {
        // memo 조회
        Memo memo = memoRepository.findMemoById(id);

        // NPE 방지
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        memoRepository.deleteMemo(id);

    }

}
