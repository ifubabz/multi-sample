package com.openlabs.sample.interceptor;

import java.sql.Connection;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MyBatisPagingInterceptor implements Interceptor {

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
	private DatabaseType databaseType;
	
	public enum DatabaseType {
		ORACLE, MYSQL
		;
		public static DatabaseType get(String productName) {
			log.debug("PRODUCTNAME:{}", productName);
			for(DatabaseType dbmsName : DatabaseType.values()) {
				if(dbmsName.name().equalsIgnoreCase(productName)) {
					return dbmsName;
				}
			}
			return null;
		}
	}
	
	public MyBatisPagingInterceptor(DatabaseType databaseType) {
		this.databaseType = databaseType;
	}
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		RowBounds rb = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");

		log.debug("originalSql =\n{}", originalSql);
		log.debug("RowBounds = {}, isDEFAULT = {}", rb, rb==RowBounds.DEFAULT);

		if (rb == null || rb == RowBounds.DEFAULT) {
			return invocation.proceed();
		}

		String sql = PagingSqlHelper.getSql(databaseType, originalSql, rb);

		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		metaStatementHandler.setValue("delegate.boundSql.sql", sql);

		return invocation.proceed();
	}

}
