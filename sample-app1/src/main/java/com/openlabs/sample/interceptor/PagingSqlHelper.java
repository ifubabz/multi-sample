package com.openlabs.sample.interceptor;

import org.apache.ibatis.session.RowBounds;

import com.openlabs.sample.interceptor.MyBatisPagingInterceptor.DatabaseType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagingSqlHelper {

	public static String getSql(DatabaseType databaseType, String originalSql, RowBounds rowBounds) {
		StringBuffer sb = new StringBuffer();
		switch (databaseType) {
		case ORACLE:
			sb.append(originalSql);
			sb.append("\n");
			sb.append("OFFSET ");
			sb.append(rowBounds.getOffset());
			sb.append(" ROWS FETCH FIRST ");
			sb.append(rowBounds.getLimit());
			sb.append(" ROWS ONLY ");
			break;
			
		case IGNITE:
			sb.append(originalSql);
			sb.append("\n");
			sb.append("LIMIT ");
			sb.append(rowBounds.getLimit());
			sb.append(" OFFSET ");
			sb.append(rowBounds.getOffset());
			break;
			
		default:
			sb.append(originalSql);
			break;
		}
		String sql = sb.toString();
		log.debug("SQL:{}", sql);
		return sql;
	}
}
