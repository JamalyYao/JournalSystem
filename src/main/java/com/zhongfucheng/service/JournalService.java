package com.zhongfucheng.service;

import com.zhongfucheng.domain.Journal;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.ArchiveRecords;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface JournalService {

    Journal saveJournal(Journal journal);

    /**
     * 根据用户id获取所有的文章
     * @param user
     * @return
     */
    List<Journal> selectAllJournals(User user);


    /**
     * 文章存档数据查询(在当前用户下)
     *
     * @return
     */
    List<ArchiveRecords> selectArchiveRecords(String userId);

    /**
     * 根据日期查询相关的日志(按时间倒序,当前用户下)
     */
    List<Journal> selectJournalsByTime(User user, Date start, Date end);


    /**
     * 根据标签名来查询对应的文章
     */

    List<Journal> selectJournalsByTag(String tagName);



    /**
     * 根据Id查询文章的详情
     */
    Journal findJournalDetailById(Integer blogId);


    /**
     * 分页+条件查询文章的数据
     */
    Page<Journal> paginateAndSort(Integer currentPage, User user);


    /**
     * 根据文章Id删除文章
     * @param blogId
     */
    void deleteJournalById(Integer blogId);

    /**
     * 更新文章的内容
     */
    void updateJournalContent(Journal journal);










}
