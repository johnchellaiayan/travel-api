package com.ta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ta.dto.StatisticsDto;

@SuppressWarnings("deprecation")
@Repository
public class CommonDAO {

	@Autowired
	EntityManager entityManager;

	@SuppressWarnings({ "rawtypes","unchecked" })
	@Transactional
	public StatisticsDto getStatistics() {
		Session sess = entityManager.unwrap(Session.class);
		String sql = "SELECT"
				+ "  (SELECT COUNT(*) FROM driver ) AS totalDrivers,"
				+ "  (SELECT COUNT(*) FROM customer) AS totalCustomers,"
				+ "  (SELECT COUNT(*) FROM booking) AS totalCurrentBookings,"
				+ "  (SELECT COUNT(*) FROM user) AS totalUsers";

		Query q = sess.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatisticsDto.class));
		List<StatisticsDto> dto = (List<StatisticsDto>) q.list();
		return dto.get(0);
	}
}
