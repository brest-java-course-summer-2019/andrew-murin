package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Ticket;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class TicketDaoJdbcImplTest {

    @Autowired
    TicketDao ticketDao;

    @Test
    public void findAll() {
        List<Ticket> tickets = ticketDao.findAll();
        Assert.assertNotNull(tickets);
        Assert.assertTrue(tickets.size() > 0);
    }

    @Test
    public void addTicket() {
        Ticket testTicket = new Ticket();
        testTicket.setDirection("Warsaw");

        Ticket newTicket = ticketDao.add(testTicket);
        assertNotNull(newTicket.getTicketId());
    }
/*
    @Test
    public void updateDepartment() {
        Department testDepartment = new Department();
        testDepartment.setDepartmentName("Тест");
        testDepartment.setDepartmentId(2);

        Department updateDepartment = departmentDao.update(testDepartment);
        Assert.assertNotNull(updateDepartment);
        Assert.assertNotNull(updateDepartment.getDepartmentId());
        Assert.assertEquals("Тест", updateDepartment.getDepartmentName());
    }

    @Test
    public void deleteDepartment() {
        Department testDepartment = new Department();
        testDepartment.setDepartmentName("Бухгалтерия");

        Department newDepartment = departmentDao.add(testDepartment);

        Department deleteDepartment = departmentDao.delete(testDepartment.getDepartmentId());

        Assert.assertEquals(1, departmentDao.delete(testDepartment.getDepartmentId()));
       }
*/



}

