package com.example.springbasicdb.repository;

import com.example.springbasicdb.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Annotation @Repository는 @Component와 같다, Spring Bean으로 등록한다는 뜻.
 * Spring Bean으로 등록되면 다른 클래스에서 주입하여 사용할 수 있다.
 * 명시적으로 Repository Layer 라는것을 나타낸다.
 * DB와 상호작용하여 데이터를 CRUD하는 작업을 수행한다.
 */
@Repository
public class MemoRepositoryImpl implements MemoRepository {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @Override
    public Long getMemoId() {

        return memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
    }

    @Override
    public void saveMemo(Memo memo) {

        memoList.put(memo.getId(), memo);
    }

    @Override
    public Map<Long, Memo> findAllMemos() {

        return memoList;
    }

    @Override
    public Memo findMemoById(Long id) {

        return memoList.get(id);
    }

    @Override
    public void deleteMemo(Long id) {

        memoList.remove(id);
    }

}
