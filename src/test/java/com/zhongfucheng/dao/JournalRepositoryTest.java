package com.zhongfucheng.dao;

import com.zhongfucheng.domain.Journal;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.ArchiveRecords;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2018/1/16.
 *
 * @author ozc
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JournalRepositoryTest {

    @Autowired
    private JournalRepository journalRepository;

    @Test
    public void findBlogsByUser() {


        User user = new User();
        user.setUserId(1);
        List<Journal> blogsByUser = journalRepository.findJournalsByUserOrderByCreateTimeDesc(user);
        System.out.println(blogsByUser);

    }

    @Test
    public void selectBolgByData() {



        List<Object[]> ArchiveRecords = journalRepository.findArchiveRecords("1");
        List<ArchiveRecords> ArchiveRecord = new ArrayList<>();
        for (Object[] record : ArchiveRecords) {
            ArchiveRecords archiveRecords = new ArchiveRecords();
            archiveRecords.setYear( record[0].toString());
            archiveRecords.setMonth( record[1].toString());
            archiveRecords.setNum( record[2].toString());

            ArchiveRecord.add(archiveRecords);

        }

        System.out.println(ArchiveRecord);




    }


    @Test
    public void findBlogsByCreateTimeBetween() throws ParseException {


        User user = new User();
        user.setUserId(2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        String startDate1 = "2018-1-01";
        String endDate1 = "2018-" +
                "1-31";
        Date startDate = sdf.parse(startDate1);
        Date endDate = sdf.parse(endDate1);


        List<Journal> journals = journalRepository.findJournalsByUserAndCreateTimeBetweenOrderByCreateTimeDesc(user,startDate, endDate);

        System.out.println(journals);

    }

    @Test
    public void selectBlogByTagName() {

        String name = "日本";


        List<Journal> journals = journalRepository.selectJournalByTagName(name);

        System.out.println(journals);



    }
}