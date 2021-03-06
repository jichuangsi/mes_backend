package com.jichuangsi.mes.repository;

import com.jichuangsi.mes.entity.PPSemiFinishedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PPSemiFinishedProductsRepository  extends JpaRepository<PPSemiFinishedProducts,Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE pp_semiFinishedProducts SET delete_no = 1 AND pppid = ?1",nativeQuery = true)
    void updateByPPPId(Integer orderId);
}
