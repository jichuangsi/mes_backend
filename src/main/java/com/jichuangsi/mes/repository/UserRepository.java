package com.jichuangsi.mes.repository;

import com.jichuangsi.mes.entity.SStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<SStaff,Integer> {
    int countByStaffNum(String staffnum);

    SStaff findByStaffNumAndLoginPassword(String account,String Pwd);

    SStaff findByid(Integer id);

    @Query(value = "SELECT count(1) FROM s_staff",nativeQuery = true)
    Integer countByid();

}
