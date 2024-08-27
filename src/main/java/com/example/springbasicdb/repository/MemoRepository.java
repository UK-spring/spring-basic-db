package com.example.springbasicdb.repository;

import com.example.springbasicdb.entity.Memo;

import java.util.Map;

public interface MemoRepository {

    Long saveMemo(Memo memo);

    Map<Long, Memo> findAllMemos();

    Memo findMemoById(Long id);

    void deleteMemo(Long id);

}
