package com.example.banking.config.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.example.banking.enums.AccountType;

public class AccountTypeHandler extends BaseTypeHandler<AccountType>{

	@Override
    public void setNonNullParameter(PreparedStatement ps, int i, AccountType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public AccountType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : AccountType.valueOf(value);
    }

    @Override
    public AccountType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : AccountType.valueOf(value);
    }

    @Override
    public AccountType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : AccountType.valueOf(value);
    }
}
