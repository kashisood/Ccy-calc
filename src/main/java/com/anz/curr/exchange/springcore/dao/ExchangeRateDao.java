package com.anz.curr.exchange.springcore.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.anz.curr.exchange.springcore.Messages;

@Component
public class ExchangeRateDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Double> getRates() {

		return jdbcTemplate.query(Messages.getString("exchangeRateSQL"), (ResultSet rs) -> { //$NON-NLS-1$
			HashMap<String, Double> results = new HashMap<>();
			while (rs.next()) {
				results.put(rs.getString(Messages.getString("exchangeRatekey")), //$NON-NLS-1$
						rs.getDouble(Messages.getString("exchangeRateValue"))); //$NON-NLS-1$
			}
			return results;
		});
	}

}
