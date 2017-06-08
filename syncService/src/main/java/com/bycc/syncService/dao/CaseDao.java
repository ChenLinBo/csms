package com.bycc.syncService.dao;

import com.bycc.syncService.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wanghaidong on 2017/5/26.
 */
@Repository
public interface CaseDao extends JpaRepository<Case, Integer> {
}
