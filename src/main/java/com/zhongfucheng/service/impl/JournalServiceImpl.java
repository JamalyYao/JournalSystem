package com.zhongfucheng.service.impl;

import com.zhongfucheng.dao.JournalRepository;
import com.zhongfucheng.domain.Journal;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.ArchiveRecords;
import com.zhongfucheng.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2018/1/15.
 *
 * @author ozc
 * @version 1.0
 */
@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    private JournalRepository journalRepository;


    //每页显示5条
    private static final Integer PAGE_SIZE = 10;



    @Override
    public Journal saveJournal(Journal journal) {
        return journalRepository.save(journal);
    }

    @Override
    public List<Journal> selectAllJournals(User user) {
        return journalRepository.findJournalsByUserOrderByCreateTimeDesc(user);
    }

    @Override
    public List<ArchiveRecords> selectArchiveRecords(String userId) {


        List<Object[]> archiveRecords = journalRepository.findArchiveRecords(userId);

        //将Object[]数组转成DTO实体
        List<ArchiveRecords> archiveRecord = new ArrayList<>();

        for (Object[] record : archiveRecords) {
            ArchiveRecords records = new ArchiveRecords();
            records.setYear(record[0].toString());
            records.setMonth(record[1].toString());
            records.setNum(record[2].toString());

            archiveRecord.add(records);

        }

        return archiveRecord;
    }

    @Override
    public List<Journal> selectJournalsByTime(User user, Date start, Date end) {
        return journalRepository.findJournalsByUserAndCreateTimeBetweenOrderByCreateTimeDesc(user,start, end);
    }

    @Override
    public List<Journal> selectJournalsByTag(String tagName) {
        return journalRepository.selectJournalByTagName(tagName);
    }


    @Override
    public Journal findJournalDetailById(Integer journalId) {
        return journalRepository.findOne(journalId);
    }


    @Override
    public Page<Journal> paginateAndSort(Integer currentPage, User user) {

        //按照时间来排序(降序)
        Sort sort = new Sort(new Order(Direction.DESC, "createTime"));

        //index是从0开始的，并不是从1开始的
        Pageable pageable = new PageRequest(currentPage, PAGE_SIZE, sort);


        //添加过滤条件(查询当前用户下的)
        Specification<Journal> journalSpecification = (root, query, cb) -> {

            Path path = root.get("user");

            return cb.equal(path, user);
        };

        Page<Journal> journals = journalRepository.findAll(journalSpecification, pageable);


        return journals;
    }

    @Override
    public void deleteJournalById(Integer journalId) {

        journalRepository.delete(journalId);

    }
    @Override
    public void updateJournalContent(Journal journal) {
        journalRepository.save(journal);
    }


}
