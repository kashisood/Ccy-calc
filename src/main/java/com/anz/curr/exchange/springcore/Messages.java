package com.anz.curr.exchange.springcore;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_NAME = "com.anz.curr.exchange.springcore.messages"; //$NON-NLS-1$
	private static final String DB_BUNDLE_NAME = "com.anz.curr.exchange.springcore.dao.dbQueries";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	private static final ResourceBundle DB_RESOURCE_BUNDLE = ResourceBundle.getBundle(DB_BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			try {
				return DB_RESOURCE_BUNDLE.getString(key);
			} catch (MissingResourceException e1) {
				return '!' + key + '!';
			}
		}
	}
}
