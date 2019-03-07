package com.anz.curr.exchange.springcore.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.anz.curr.exchange.springcore.Messages;

@Component
public class CurrencyMappingDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, String> fetchCurrencyMapping() {

		return jdbcTemplate.query(Messages.getString("CcyMapSQL"), (ResultSet rs) -> { //$NON-NLS-1$
			HashMap<String, String> results = new HashMap<>();
			while (rs.next()) {
				results.put(rs.getString(Messages.getString("CcyMapBase_ID")) + rs.getString(Messages.getString("CcyMapTerm_ID")), rs.getString(Messages.getString("CcyMapValue"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			return results;
		});
	}

}
