package com.example.springbasicdb.repository;

import com.example.springbasicdb.dto.MemoResponseDto;
import com.example.springbasicdb.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    MemoResponseDto saveMemo(Memo memo);

    List<MemoResponseDto> findAllMemos();

    Optional<Memo> findMemoById(Long id);

    void deleteMemo(Long id);

}
