package com.example.demo.multidatasource.dao.cluster;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.multidatasource.domain.ScheduleJob;

@Mapper
public interface JobDao {
    ScheduleJob select(@Param("id") long id);

    Integer update(ScheduleJob scheduleJob);

    Integer insert(ScheduleJob scheduleJob);

    Integer delete(Long productId);

    List<ScheduleJob> getAllJob();

    List<ScheduleJob> getAllEnableJob();

}
