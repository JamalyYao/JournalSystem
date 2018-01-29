package com.zhongfucheng.dao;

import com.zhongfucheng.domain.Journal;
import com.zhongfucheng.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface JournalRepository extends JpaRepository<Journal, Integer> ,JpaSpecificationExecutor<Journal> {

    /**
     * 根据用户获取所有的日志,按照日志的时间排序
     * @param user
     * @return
     */
    List<Journal> findJournalsByUserOrderByCreateTimeDesc(User user);

    /**
     * 查询文章存档记录数
     */
    @Query(value = "SELECT t.myYear  AS year, t.monthNo AS month, count(t.monthNo)  AS num FROM ( SELECT month(t_journal.create_time) AS monthNo, year(t_journal.create_time)  AS myYear FROM t_journal WHERE user_id = :userId ) t WHERE  t.myYear = '2018' OR t.myYear = '2017' GROUP BY t.monthNo,t.myYear ORDER BY t.myYear DESC,t.monthNo DESC", nativeQuery = true)
    List<Object[]> findArchiveRecords(@Param("userId") String userId);

    /**
     * 根据日期查询相关的日志(按时间倒序)
     */
    List<Journal> findJournalsByUserAndCreateTimeBetweenOrderByCreateTimeDesc(User user, Date start, Date end);


    /**
     * 根据标签查询相关的日志(按时间倒序)
     */
    @Query("SELECT j FROM Tag t ,Journal j WHERE j.journalId = t.journal and t.tagName = :tagName order by j.createTime desc ")
    List<Journal> selectJournalByTagName(@Param("tagName") String tagName);


}
